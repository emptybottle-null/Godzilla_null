/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.openxml4j.opc;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.TreeMap;
import org.apache.poi.ooxml.util.DocumentHelper;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.exceptions.InvalidOperationException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.openxml4j.opc.PackageAccess;
import org.apache.poi.openxml4j.opc.PackagePart;
import org.apache.poi.openxml4j.opc.PackagePartName;
import org.apache.poi.openxml4j.opc.PackageRelationship;
import org.apache.poi.openxml4j.opc.PackagingURIHelper;
import org.apache.poi.openxml4j.opc.TargetMode;
import org.apache.poi.util.POILogFactory;
import org.apache.poi.util.POILogger;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public final class PackageRelationshipCollection
implements Iterable<PackageRelationship> {
    private static final POILogger logger = POILogFactory.getLogger(PackageRelationshipCollection.class);
    private final TreeMap<String, PackageRelationship> relationshipsByID = new TreeMap();
    private final TreeMap<String, PackageRelationship> relationshipsByType = new TreeMap();
    private HashMap<String, PackageRelationship> internalRelationshipsByTargetName = new HashMap();
    private PackagePart relationshipPart;
    private PackagePart sourcePart;
    private PackagePartName partName;
    private OPCPackage container;
    private int nextRelationshipId = -1;

    PackageRelationshipCollection() {
    }

    public PackageRelationshipCollection(PackageRelationshipCollection coll, String filter) {
        this();
        for (PackageRelationship rel : coll.relationshipsByID.values()) {
            if (filter != null && !rel.getRelationshipType().equals(filter)) continue;
            this.addRelationship(rel);
        }
    }

    public PackageRelationshipCollection(OPCPackage container) throws InvalidFormatException {
        this(container, null);
    }

    public PackageRelationshipCollection(PackagePart part) throws InvalidFormatException {
        this(part._container, part);
    }

    public PackageRelationshipCollection(OPCPackage container, PackagePart part) throws InvalidFormatException {
        if (container == null) {
            throw new IllegalArgumentException("container needs to be specified");
        }
        if (part != null && part.isRelationshipPart()) {
            throw new IllegalArgumentException("part");
        }
        this.container = container;
        this.sourcePart = part;
        this.partName = PackageRelationshipCollection.getRelationshipPartName(part);
        if (container.getPackageAccess() != PackageAccess.WRITE && container.containPart(this.partName)) {
            this.relationshipPart = container.getPart(this.partName);
            this.parseRelationshipsPart(this.relationshipPart);
        }
    }

    private static PackagePartName getRelationshipPartName(PackagePart part) throws InvalidOperationException {
        PackagePartName partName = part == null ? PackagingURIHelper.PACKAGE_ROOT_PART_NAME : part.getPartName();
        return PackagingURIHelper.getRelationshipPartName(partName);
    }

    public void addRelationship(PackageRelationship relPart) {
        if (relPart == null || relPart.getId() == null || relPart.getId().isEmpty()) {
            throw new IllegalArgumentException("invalid relationship part/id");
        }
        this.relationshipsByID.put(relPart.getId(), relPart);
        this.relationshipsByType.put(relPart.getRelationshipType(), relPart);
    }

    public PackageRelationship addRelationship(URI targetUri, TargetMode targetMode, String relationshipType, String id) {
        if (id == null) {
            if (this.nextRelationshipId == -1) {
                this.nextRelationshipId = this.size() + 1;
            }
            while (this.relationshipsByID.get(id = "rId" + this.nextRelationshipId++) != null) {
            }
        }
        PackageRelationship rel = new PackageRelationship(this.container, this.sourcePart, targetUri, targetMode, relationshipType, id);
        this.addRelationship(rel);
        if (targetMode == TargetMode.INTERNAL) {
            this.internalRelationshipsByTargetName.put(targetUri.toASCIIString(), rel);
        }
        return rel;
    }

    public void removeRelationship(String id) {
        PackageRelationship rel = this.relationshipsByID.get(id);
        if (rel != null) {
            this.relationshipsByID.remove(rel.getId());
            this.relationshipsByType.values().remove(rel);
            this.internalRelationshipsByTargetName.values().remove(rel);
        }
    }

    public PackageRelationship getRelationship(int index) {
        if (index < 0 || index > this.relationshipsByID.values().size()) {
            throw new IllegalArgumentException("index");
        }
        int i = 0;
        for (PackageRelationship rel : this.relationshipsByID.values()) {
            if (index != i++) continue;
            return rel;
        }
        return null;
    }

    public PackageRelationship getRelationshipByID(String id) {
        return this.relationshipsByID.get(id);
    }

    public int size() {
        return this.relationshipsByID.values().size();
    }

    public void parseRelationshipsPart(PackagePart relPart) throws InvalidFormatException {
        try {
            logger.log(1, "Parsing relationship: " + relPart.getPartName());
            Document xmlRelationshipsDoc = DocumentHelper.readDocument(relPart.getInputStream());
            Element root = xmlRelationshipsDoc.getDocumentElement();
            boolean fCorePropertiesRelationship = false;
            NodeList nodeList = root.getElementsByTagNameNS("http://schemas.openxmlformats.org/package/2006/relationships", "Relationship");
            int nodeCount = nodeList.getLength();
            for (int i = 0; i < nodeCount; ++i) {
                Element element = (Element)nodeList.item(i);
                String id = element.getAttribute("Id");
                String type = element.getAttribute("Type");
                if (type.equals("http://schemas.openxmlformats.org/package/2006/relationships/metadata/core-properties")) {
                    if (!fCorePropertiesRelationship) {
                        fCorePropertiesRelationship = true;
                    } else {
                        throw new InvalidFormatException("OPC Compliance error [M4.1]: there is more than one core properties relationship in the package !");
                    }
                }
                Attr targetModeAttr = element.getAttributeNode("TargetMode");
                TargetMode targetMode = TargetMode.INTERNAL;
                if (targetModeAttr != null) {
                    targetMode = targetModeAttr.getValue().toLowerCase(Locale.ROOT).equals("internal") ? TargetMode.INTERNAL : TargetMode.EXTERNAL;
                }
                URI target = PackagingURIHelper.toURI("http://invalid.uri");
                String value = element.getAttribute("Target");
                try {
                    target = PackagingURIHelper.toURI(value);
                } catch (URISyntaxException e) {
                    logger.log(7, "Cannot convert " + value + " in a valid relationship URI-> dummy-URI used", e);
                }
                this.addRelationship(target, targetMode, type, id);
            }
        } catch (Exception e) {
            logger.log(7, e);
            throw new InvalidFormatException(e.getMessage());
        }
    }

    public PackageRelationshipCollection getRelationships(String typeFilter) {
        return new PackageRelationshipCollection(this, typeFilter);
    }

    @Override
    public Iterator<PackageRelationship> iterator() {
        return this.relationshipsByID.values().iterator();
    }

    public Iterator<PackageRelationship> iterator(String typeFilter) {
        ArrayList<PackageRelationship> retArr = new ArrayList<PackageRelationship>();
        for (PackageRelationship rel : this.relationshipsByID.values()) {
            if (!rel.getRelationshipType().equals(typeFilter)) continue;
            retArr.add(rel);
        }
        return retArr.iterator();
    }

    public void clear() {
        this.relationshipsByID.clear();
        this.relationshipsByType.clear();
        this.internalRelationshipsByTargetName.clear();
    }

    public PackageRelationship findExistingInternalRelation(PackagePart packagePart) {
        return this.internalRelationshipsByTargetName.get(packagePart.getPartName().getName());
    }

    public String toString() {
        String str = this.relationshipsByID.size() + " relationship(s) = [";
        str = this.relationshipPart != null && this.relationshipPart._partName != null ? str + this.relationshipPart._partName : str + "relationshipPart=null";
        str = this.sourcePart != null && this.sourcePart._partName != null ? str + "," + this.sourcePart._partName : str + ",sourcePart=null";
        str = this.partName != null ? str + "," + this.partName : str + ",uri=null)";
        return str + "]";
    }
}

