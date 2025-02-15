/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.openxml4j.opc.internal.marshallers;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;
import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;
import org.apache.commons.compress.archivers.zip.ZipArchiveOutputStream;
import org.apache.poi.ooxml.util.DocumentHelper;
import org.apache.poi.openxml4j.exceptions.OpenXML4JException;
import org.apache.poi.openxml4j.opc.PackagePart;
import org.apache.poi.openxml4j.opc.PackagePartName;
import org.apache.poi.openxml4j.opc.PackageRelationship;
import org.apache.poi.openxml4j.opc.PackageRelationshipCollection;
import org.apache.poi.openxml4j.opc.PackagingURIHelper;
import org.apache.poi.openxml4j.opc.StreamHelper;
import org.apache.poi.openxml4j.opc.TargetMode;
import org.apache.poi.openxml4j.opc.internal.PartMarshaller;
import org.apache.poi.openxml4j.opc.internal.ZipHelper;
import org.apache.poi.util.IOUtils;
import org.apache.poi.util.POILogFactory;
import org.apache.poi.util.POILogger;
import org.apache.poi.xssf.usermodel.XSSFRelation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public final class ZipPartMarshaller
implements PartMarshaller {
    private static final POILogger logger = POILogFactory.getLogger(ZipPartMarshaller.class);

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean marshall(PackagePart part, OutputStream os) throws OpenXML4JException {
        if (!(os instanceof ZipArchiveOutputStream)) {
            logger.log(7, "Unexpected class " + os.getClass().getName());
            throw new OpenXML4JException("ZipOutputStream expected !");
        }
        if (part.getSize() == 0L && part.getPartName().getName().equals(XSSFRelation.SHARED_STRINGS.getDefaultFileName())) {
            return true;
        }
        ZipArchiveOutputStream zos = (ZipArchiveOutputStream)os;
        ZipArchiveEntry partEntry = new ZipArchiveEntry(ZipHelper.getZipItemNameFromOPCName(part.getPartName().getURI().getPath()));
        try {
            zos.putArchiveEntry(partEntry);
            try (InputStream ins = part.getInputStream();){
                IOUtils.copy(ins, zos);
            } finally {
                zos.closeArchiveEntry();
            }
        } catch (IOException ioe) {
            logger.log(7, "Cannot write: " + part.getPartName() + ": in ZIP", ioe);
            return false;
        }
        if (part.hasRelationships()) {
            PackagePartName relationshipPartName = PackagingURIHelper.getRelationshipPartName(part.getPartName());
            ZipPartMarshaller.marshallRelationshipPart(part.getRelationships(), relationshipPartName, zos);
        }
        return true;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static boolean marshallRelationshipPart(PackageRelationshipCollection rels, PackagePartName relPartName, ZipArchiveOutputStream zos) {
        boolean rel2;
        Document xmlOutDoc = DocumentHelper.createDocument();
        Element root = xmlOutDoc.createElementNS("http://schemas.openxmlformats.org/package/2006/relationships", "Relationships");
        xmlOutDoc.appendChild(root);
        URI sourcePartURI = PackagingURIHelper.getSourcePartUriFromRelationshipPartUri(relPartName.getURI());
        for (PackageRelationship rel2 : rels) {
            String targetValue;
            Element relElem = xmlOutDoc.createElementNS("http://schemas.openxmlformats.org/package/2006/relationships", "Relationship");
            root.appendChild(relElem);
            relElem.setAttribute("Id", rel2.getId());
            relElem.setAttribute("Type", rel2.getRelationshipType());
            URI uri = rel2.getTargetURI();
            if (rel2.getTargetMode() == TargetMode.EXTERNAL) {
                targetValue = uri.toString();
                relElem.setAttribute("TargetMode", "External");
            } else {
                URI targetURI = rel2.getTargetURI();
                targetValue = PackagingURIHelper.relativizeURI(sourcePartURI, targetURI, true).toString();
            }
            relElem.setAttribute("Target", targetValue);
        }
        xmlOutDoc.normalize();
        ZipArchiveEntry ctEntry = new ZipArchiveEntry(ZipHelper.getZipURIFromOPCName(relPartName.getURI().toASCIIString()).getPath());
        zos.putArchiveEntry(ctEntry);
        try {
            rel2 = StreamHelper.saveXmlInStream(xmlOutDoc, zos);
        } catch (Throwable throwable) {
            try {
                zos.closeArchiveEntry();
                throw throwable;
            } catch (IOException e) {
                logger.log(7, "Cannot create zip entry " + relPartName, e);
                return false;
            }
        }
        zos.closeArchiveEntry();
        return rel2;
    }
}

