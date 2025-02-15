/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.ooxml;

import java.io.IOException;
import org.apache.poi.ooxml.POIXMLDocumentPart;
import org.apache.poi.ooxml.POIXMLException;
import org.apache.poi.ooxml.POIXMLRelation;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.PackagePart;
import org.apache.poi.openxml4j.opc.PackageRelationship;
import org.apache.poi.util.POILogFactory;
import org.apache.poi.util.POILogger;
import org.apache.xmlbeans.XmlException;

public abstract class POIXMLFactory {
    private static final POILogger LOGGER = POILogFactory.getLogger(POIXMLFactory.class);

    public POIXMLDocumentPart createDocumentPart(POIXMLDocumentPart parent, PackagePart part) {
        PackageRelationship rel = this.getPackageRelationship(parent, part);
        String relType = rel.getRelationshipType();
        POIXMLRelation descriptor = this.getDescriptor(relType);
        try {
            if (descriptor != null && !"http://schemas.openxmlformats.org/officeDocument/2006/relationships/package".equals(relType)) {
                POIXMLRelation.ParentPartConstructor parentPartConstructor = descriptor.getParentPartConstructor();
                if (parentPartConstructor != null) {
                    return parentPartConstructor.init(parent, part);
                }
                POIXMLRelation.PackagePartConstructor packagePartConstructor = descriptor.getPackagePartConstructor();
                if (packagePartConstructor != null) {
                    return packagePartConstructor.init(part);
                }
            }
            LOGGER.log(1, "using default POIXMLDocumentPart for " + rel.getRelationshipType());
            return new POIXMLDocumentPart(parent, part);
        } catch (IOException | XmlException e) {
            throw new POIXMLException(e.getMessage(), e);
        }
    }

    protected abstract POIXMLRelation getDescriptor(String var1);

    public POIXMLDocumentPart newDocumentPart(POIXMLRelation descriptor) {
        if (descriptor == null || descriptor.getNoArgConstructor() == null) {
            throw new POIXMLException("can't initialize POIXMLDocumentPart");
        }
        return descriptor.getNoArgConstructor().init();
    }

    protected PackageRelationship getPackageRelationship(POIXMLDocumentPart parent, PackagePart part) {
        try {
            String partName = part.getPartName().getName();
            for (PackageRelationship pr : parent.getPackagePart().getRelationships()) {
                String packName = pr.getTargetURI().toASCIIString();
                if (!packName.equalsIgnoreCase(partName)) continue;
                return pr;
            }
        } catch (InvalidFormatException e) {
            throw new POIXMLException("error while determining package relations", e);
        }
        throw new POIXMLException("package part isn't a child of the parent document.");
    }
}

