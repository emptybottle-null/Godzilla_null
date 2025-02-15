/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.hssf.usermodel;

import org.apache.poi.ddf.EscherBlipRecord;
import org.apache.poi.ddf.EscherRecordTypes;
import org.apache.poi.sl.image.ImageHeaderPNG;
import org.apache.poi.ss.usermodel.PictureData;

public class HSSFPictureData
implements PictureData {
    public static final short MSOBI_WMF = 8544;
    public static final short MSOBI_EMF = 15680;
    public static final short MSOBI_PICT = 21536;
    public static final short MSOBI_PNG = 28160;
    public static final short MSOBI_JPEG = 18080;
    public static final short MSOBI_DIB = 31360;
    public static final short FORMAT_MASK = -16;
    private EscherBlipRecord blip;

    public HSSFPictureData(EscherBlipRecord blip) {
        this.blip = blip;
    }

    @Override
    public byte[] getData() {
        return new ImageHeaderPNG(this.blip.getPicturedata()).extractPNG();
    }

    public int getFormat() {
        return this.blip.getRecordId() - -4072;
    }

    @Override
    public String suggestFileExtension() {
        switch (EscherRecordTypes.forTypeID(this.blip.getRecordId())) {
            case BLIP_WMF: {
                return "wmf";
            }
            case BLIP_EMF: {
                return "emf";
            }
            case BLIP_PICT: {
                return "pict";
            }
            case BLIP_PNG: {
                return "png";
            }
            case BLIP_JPEG: {
                return "jpeg";
            }
            case BLIP_DIB: {
                return "dib";
            }
        }
        return "";
    }

    @Override
    public String getMimeType() {
        switch (EscherRecordTypes.forTypeID(this.blip.getRecordId())) {
            case BLIP_WMF: {
                return "image/x-wmf";
            }
            case BLIP_EMF: {
                return "image/x-emf";
            }
            case BLIP_PICT: {
                return "image/x-pict";
            }
            case BLIP_PNG: {
                return "image/png";
            }
            case BLIP_JPEG: {
                return "image/jpeg";
            }
            case BLIP_DIB: {
                return "image/bmp";
            }
        }
        return "image/unknown";
    }

    @Override
    public int getPictureType() {
        switch (EscherRecordTypes.forTypeID(this.blip.getRecordId())) {
            case BLIP_WMF: {
                return 3;
            }
            case BLIP_EMF: {
                return 2;
            }
            case BLIP_PICT: {
                return 4;
            }
            case BLIP_PNG: {
                return 6;
            }
            case BLIP_JPEG: {
                return 5;
            }
            case BLIP_DIB: {
                return 7;
            }
        }
        return -1;
    }
}

