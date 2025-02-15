/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.ooxml;

import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import org.apache.poi.ooxml.POIXMLDocumentPart;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.PackagePart;
import org.apache.poi.openxml4j.opc.PackagePartName;
import org.apache.poi.openxml4j.opc.PackageRelationship;
import org.apache.poi.openxml4j.opc.PackageRelationshipCollection;
import org.apache.poi.openxml4j.opc.PackagingURIHelper;
import org.apache.poi.util.Internal;
import org.apache.poi.util.POILogFactory;
import org.apache.poi.util.POILogger;
import org.apache.xmlbeans.XmlException;

public abstract class POIXMLRelation {
    private static final POILogger log = POILogFactory.getLogger(POIXMLRelation.class);
    private String _type;
    private String _relation;
    private String _defaultName;
    private final NoArgConstructor noArgConstructor;
    private final PackagePartConstructor packagePartConstructor;
    private final ParentPartConstructor parentPartConstructor;

    protected POIXMLRelation(String type, String rel, String defaultName, NoArgConstructor noArgConstructor, PackagePartConstructor packagePartConstructor, ParentPartConstructor parentPartConstructor) {
        this._type = type;
        this._relation = rel;
        this._defaultName = defaultName;
        this.noArgConstructor = noArgConstructor;
        this.packagePartConstructor = packagePartConstructor;
        this.parentPartConstructor = parentPartConstructor;
    }

    protected POIXMLRelation(String type, String rel, String defaultName) {
        this(type, rel, defaultName, null, null, null);
    }

    public String getContentType() {
        return this._type;
    }

    public String getRelation() {
        return this._relation;
    }

    public String getDefaultFileName() {
        return this._defaultName;
    }

    public String getFileName(int index) {
        if (!this._defaultName.contains("#")) {
            return this.getDefaultFileName();
        }
        return this._defaultName.replace("#", Integer.toString(index));
    }

    public Integer getFileNameIndex(POIXMLDocumentPart part) {
        String regex = this._defaultName.replace("#", "(\\d+)");
        return Integer.valueOf(part.getPackagePart().getPartName().getName().replaceAll(regex, "$1"));
    }

    public NoArgConstructor getNoArgConstructor() {
        return this.noArgConstructor;
    }

    public PackagePartConstructor getPackagePartConstructor() {
        return this.packagePartConstructor;
    }

    public ParentPartConstructor getParentPartConstructor() {
        return this.parentPartConstructor;
    }

    public InputStream getContents(PackagePart corePart) throws IOException, InvalidFormatException {
        PackageRelationshipCollection prc = corePart.getRelationshipsByType(this.getRelation());
        Iterator<PackageRelationship> it = prc.iterator();
        if (it.hasNext()) {
            PackageRelationship rel = it.next();
            PackagePartName relName = PackagingURIHelper.createPartName(rel.getTargetURI());
            PackagePart part = corePart.getPackage().getPart(relName);
            return part.getInputStream();
        }
        log.log(5, "No part " + this.getDefaultFileName() + " found");
        return null;
    }

    @Internal
    public static interface ParentPartConstructor {
        public POIXMLDocumentPart init(POIXMLDocumentPart var1, PackagePart var2) throws IOException, XmlException;
    }

    @Internal
    public static interface PackagePartConstructor {
        public POIXMLDocumentPart init(PackagePart var1) throws IOException, XmlException;
    }

    @Internal
    public static interface NoArgConstructor {
        public POIXMLDocumentPart init();
    }
}

