/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.ooxml;

import java.io.Closeable;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import org.apache.poi.ooxml.POIXMLDocumentPart;
import org.apache.poi.ooxml.POIXMLException;
import org.apache.poi.ooxml.POIXMLFactory;
import org.apache.poi.ooxml.POIXMLProperties;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.exceptions.OpenXML4JException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.openxml4j.opc.PackageAccess;
import org.apache.poi.openxml4j.opc.PackagePart;
import org.apache.poi.openxml4j.opc.PackageRelationship;
import org.apache.poi.openxml4j.opc.PackageRelationshipCollection;
import org.apache.poi.util.Removal;
import org.apache.xmlbeans.impl.common.SystemCache;

public abstract class POIXMLDocument
extends POIXMLDocumentPart
implements Closeable {
    public static final String DOCUMENT_CREATOR = "Apache POI";
    public static final String OLE_OBJECT_REL_TYPE = "http://schemas.openxmlformats.org/officeDocument/2006/relationships/oleObject";
    public static final String PACK_OBJECT_REL_TYPE = "http://schemas.openxmlformats.org/officeDocument/2006/relationships/package";
    private OPCPackage pkg;
    private POIXMLProperties properties;

    protected POIXMLDocument(OPCPackage pkg) {
        super(pkg);
        this.init(pkg);
    }

    protected POIXMLDocument(OPCPackage pkg, String coreDocumentRel) {
        super(pkg, coreDocumentRel);
        this.init(pkg);
    }

    private void init(OPCPackage p) {
        this.pkg = p;
        SystemCache.get().setSaxLoader(null);
    }

    public static OPCPackage openPackage(String path) throws IOException {
        try {
            return OPCPackage.open(path);
        } catch (InvalidFormatException e) {
            throw new IOException(e.toString(), e);
        }
    }

    public OPCPackage getPackage() {
        return this.pkg;
    }

    protected PackagePart getCorePart() {
        return this.getPackagePart();
    }

    protected PackagePart[] getRelatedByType(String contentType) throws InvalidFormatException {
        PackageRelationshipCollection partsC = this.getPackagePart().getRelationshipsByType(contentType);
        PackagePart[] parts = new PackagePart[partsC.size()];
        int count = 0;
        for (PackageRelationship rel : partsC) {
            parts[count] = this.getPackagePart().getRelatedPart(rel);
            ++count;
        }
        return parts;
    }

    public POIXMLProperties getProperties() {
        if (this.properties == null) {
            try {
                this.properties = new POIXMLProperties(this.pkg);
            } catch (Exception e) {
                throw new POIXMLException(e);
            }
        }
        return this.properties;
    }

    @Deprecated
    @Removal(version="4.2")
    public List<PackagePart> getAllEmbedds() throws OpenXML4JException {
        return this.getAllEmbeddedParts();
    }

    public abstract List<PackagePart> getAllEmbeddedParts() throws OpenXML4JException;

    protected final void load(POIXMLFactory factory) throws IOException {
        HashMap<PackagePart, POIXMLDocumentPart> context = new HashMap<PackagePart, POIXMLDocumentPart>();
        try {
            this.read(factory, context);
        } catch (OpenXML4JException e) {
            throw new POIXMLException(e);
        }
        this.onDocumentRead();
        context.clear();
    }

    @Override
    public void close() throws IOException {
        if (this.pkg != null) {
            if (this.pkg.getPackageAccess() == PackageAccess.READ) {
                this.pkg.revert();
            } else {
                this.pkg.close();
            }
            this.pkg = null;
        }
    }

    public final void write(OutputStream stream) throws IOException {
        OPCPackage p = this.getPackage();
        if (p == null) {
            throw new IOException("Cannot write data, document seems to have been closed already");
        }
        HashSet<PackagePart> context = new HashSet<PackagePart>();
        this.onSave(context);
        context.clear();
        this.getProperties().commit();
        p.save(stream);
    }
}

