/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.xwpf.usermodel;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import org.apache.poi.ooxml.POIXMLDocumentPart;
import org.apache.poi.ooxml.POIXMLException;
import org.apache.poi.ooxml.POIXMLRelation;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.openxml4j.opc.PackagePart;
import org.apache.poi.util.IOUtils;
import org.apache.poi.xwpf.usermodel.XWPFRelation;

public class XWPFPictureData
extends POIXMLDocumentPart {
    protected static final POIXMLRelation[] RELATIONS = new POIXMLRelation[13];
    private Long checksum;

    protected XWPFPictureData() {
    }

    public XWPFPictureData(PackagePart part) {
        super(part);
    }

    @Override
    protected void onDocumentRead() throws IOException {
        super.onDocumentRead();
    }

    public byte[] getData() {
        try {
            return IOUtils.toByteArray(this.getPackagePart().getInputStream());
        } catch (IOException e) {
            throw new POIXMLException(e);
        }
    }

    public String getFileName() {
        String name = this.getPackagePart().getPartName().getName();
        return name.substring(name.lastIndexOf(47) + 1);
    }

    public String suggestFileExtension() {
        return this.getPackagePart().getPartName().getExtension();
    }

    public int getPictureType() {
        String contentType = this.getPackagePart().getContentType();
        for (int i = 0; i < RELATIONS.length; ++i) {
            if (RELATIONS[i] == null || !RELATIONS[i].getContentType().equals(contentType)) continue;
            return i;
        }
        return 0;
    }

    public Long getChecksum() {
        if (this.checksum == null) {
            byte[] data;
            InputStream is = null;
            try {
                is = this.getPackagePart().getInputStream();
                data = IOUtils.toByteArray(is);
            } catch (IOException e) {
                throw new POIXMLException(e);
            } finally {
                IOUtils.closeQuietly(is);
            }
            this.checksum = IOUtils.calculateChecksum(data);
        }
        return this.checksum;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof XWPFPictureData)) {
            return false;
        }
        XWPFPictureData picData = (XWPFPictureData)obj;
        PackagePart foreignPackagePart = picData.getPackagePart();
        PackagePart ownPackagePart = this.getPackagePart();
        if (foreignPackagePart != null && ownPackagePart == null || foreignPackagePart == null && ownPackagePart != null) {
            return false;
        }
        if (ownPackagePart != null) {
            OPCPackage foreignPackage = foreignPackagePart.getPackage();
            OPCPackage ownPackage = ownPackagePart.getPackage();
            if (foreignPackage != null && ownPackage == null || foreignPackage == null && ownPackage != null) {
                return false;
            }
            if (ownPackage != null && !ownPackage.equals(foreignPackage)) {
                return false;
            }
        }
        Long foreignChecksum = picData.getChecksum();
        Long localChecksum = this.getChecksum();
        if (!localChecksum.equals(foreignChecksum)) {
            return false;
        }
        return Arrays.equals(this.getData(), picData.getData());
    }

    public int hashCode() {
        return this.getChecksum().hashCode();
    }

    @Override
    protected void prepareForCommit() {
    }

    static {
        XWPFPictureData.RELATIONS[2] = XWPFRelation.IMAGE_EMF;
        XWPFPictureData.RELATIONS[3] = XWPFRelation.IMAGE_WMF;
        XWPFPictureData.RELATIONS[4] = XWPFRelation.IMAGE_PICT;
        XWPFPictureData.RELATIONS[5] = XWPFRelation.IMAGE_JPEG;
        XWPFPictureData.RELATIONS[6] = XWPFRelation.IMAGE_PNG;
        XWPFPictureData.RELATIONS[7] = XWPFRelation.IMAGE_DIB;
        XWPFPictureData.RELATIONS[8] = XWPFRelation.IMAGE_GIF;
        XWPFPictureData.RELATIONS[9] = XWPFRelation.IMAGE_TIFF;
        XWPFPictureData.RELATIONS[10] = XWPFRelation.IMAGE_EPS;
        XWPFPictureData.RELATIONS[11] = XWPFRelation.IMAGE_BMP;
        XWPFPictureData.RELATIONS[12] = XWPFRelation.IMAGE_WPG;
    }
}

