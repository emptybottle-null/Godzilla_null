/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.ooxml.util;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;
import org.apache.poi.ooxml.POIXMLException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.exceptions.OpenXML4JException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.openxml4j.opc.PackagePart;
import org.apache.poi.openxml4j.opc.PackagePartName;
import org.apache.poi.openxml4j.opc.PackageProperties;
import org.apache.poi.openxml4j.opc.PackageRelationship;
import org.apache.poi.openxml4j.opc.PackageRelationshipCollection;
import org.apache.poi.openxml4j.opc.PackagingURIHelper;
import org.apache.poi.openxml4j.opc.TargetMode;
import org.apache.poi.util.IOUtils;

public final class PackageHelper {
    public static OPCPackage open(InputStream is) throws IOException {
        try {
            return OPCPackage.open(is);
        } catch (InvalidFormatException e) {
            throw new POIXMLException(e);
        }
    }

    public static OPCPackage clone(OPCPackage pkg, File file) throws OpenXML4JException, IOException {
        String path = file.getAbsolutePath();
        try (OPCPackage dest = OPCPackage.create(path);){
            PackageRelationshipCollection rels = pkg.getRelationships();
            for (PackageRelationship rel : rels) {
                PackagePart part = pkg.getPart(rel);
                if (rel.getRelationshipType().equals("http://schemas.openxmlformats.org/package/2006/relationships/metadata/core-properties")) {
                    PackageHelper.copyProperties(pkg.getPackageProperties(), dest.getPackageProperties());
                    continue;
                }
                dest.addRelationship(part.getPartName(), rel.getTargetMode(), rel.getRelationshipType());
                PackagePart part_tgt = dest.createPart(part.getPartName(), part.getContentType());
                OutputStream out = part_tgt.getOutputStream();
                IOUtils.copy(part.getInputStream(), out);
                out.close();
                if (!part.hasRelationships()) continue;
                PackageHelper.copy(pkg, part, dest, part_tgt);
            }
        }
        new File(path).deleteOnExit();
        return OPCPackage.open(path);
    }

    private static void copy(OPCPackage pkg, PackagePart part, OPCPackage tgt, PackagePart part_tgt) throws OpenXML4JException, IOException {
        PackageRelationshipCollection rels = part.getRelationships();
        if (rels != null) {
            for (PackageRelationship rel : rels) {
                if (rel.getTargetMode() == TargetMode.EXTERNAL) {
                    part_tgt.addExternalRelationship(rel.getTargetURI().toString(), rel.getRelationshipType(), rel.getId());
                    continue;
                }
                URI uri = rel.getTargetURI();
                if (uri.getRawFragment() != null) {
                    part_tgt.addRelationship(uri, rel.getTargetMode(), rel.getRelationshipType(), rel.getId());
                    continue;
                }
                PackagePartName relName = PackagingURIHelper.createPartName(rel.getTargetURI());
                PackagePart p = pkg.getPart(relName);
                part_tgt.addRelationship(p.getPartName(), rel.getTargetMode(), rel.getRelationshipType(), rel.getId());
                if (tgt.containPart(p.getPartName())) continue;
                PackagePart dest = tgt.createPart(p.getPartName(), p.getContentType());
                OutputStream out = dest.getOutputStream();
                IOUtils.copy(p.getInputStream(), out);
                out.close();
                PackageHelper.copy(pkg, p, tgt, dest);
            }
        }
    }

    private static void copyProperties(PackageProperties src, PackageProperties tgt) {
        tgt.setCategoryProperty(src.getCategoryProperty());
        tgt.setContentStatusProperty(src.getContentStatusProperty());
        tgt.setContentTypeProperty(src.getContentTypeProperty());
        tgt.setCreatorProperty(src.getCreatorProperty());
        tgt.setDescriptionProperty(src.getDescriptionProperty());
        tgt.setIdentifierProperty(src.getIdentifierProperty());
        tgt.setKeywordsProperty(src.getKeywordsProperty());
        tgt.setLanguageProperty(src.getLanguageProperty());
        tgt.setRevisionProperty(src.getRevisionProperty());
        tgt.setSubjectProperty(src.getSubjectProperty());
        tgt.setTitleProperty(src.getTitleProperty());
        tgt.setVersionProperty(src.getVersionProperty());
    }
}

