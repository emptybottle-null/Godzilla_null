/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.hpsf;

import java.util.Arrays;
import org.apache.commons.codec.binary.Hex;
import org.apache.poi.common.Duplicatable;
import org.apache.poi.hpsf.ClassIDPredefined;
import org.apache.poi.util.LittleEndianInput;
import org.apache.poi.util.LittleEndianOutput;

public class ClassID
implements Duplicatable {
    @Deprecated
    public static final ClassID OLE10_PACKAGE = ClassIDPredefined.OLE_V1_PACKAGE.getClassID();
    @Deprecated
    public static final ClassID PPT_SHOW = ClassIDPredefined.POWERPOINT_V8.getClassID();
    @Deprecated
    public static final ClassID XLS_WORKBOOK = ClassIDPredefined.EXCEL_V7_WORKBOOK.getClassID();
    @Deprecated
    public static final ClassID TXT_ONLY = ClassIDPredefined.TXT_ONLY.getClassID();
    @Deprecated
    public static final ClassID EXCEL_V3 = ClassIDPredefined.EXCEL_V3.getClassID();
    @Deprecated
    public static final ClassID EXCEL_V3_CHART = ClassIDPredefined.EXCEL_V3_CHART.getClassID();
    @Deprecated
    public static final ClassID EXCEL_V3_MACRO = ClassIDPredefined.EXCEL_V3_MACRO.getClassID();
    @Deprecated
    public static final ClassID EXCEL95 = ClassIDPredefined.EXCEL_V7.getClassID();
    @Deprecated
    public static final ClassID EXCEL95_CHART = ClassIDPredefined.EXCEL_V7_CHART.getClassID();
    @Deprecated
    public static final ClassID EXCEL97 = ClassIDPredefined.EXCEL_V8.getClassID();
    @Deprecated
    public static final ClassID EXCEL97_CHART = ClassIDPredefined.EXCEL_V8_CHART.getClassID();
    @Deprecated
    public static final ClassID EXCEL2003 = ClassIDPredefined.EXCEL_V11.getClassID();
    @Deprecated
    public static final ClassID EXCEL2007 = ClassIDPredefined.EXCEL_V12.getClassID();
    @Deprecated
    public static final ClassID EXCEL2007_MACRO = ClassIDPredefined.EXCEL_V12_MACRO.getClassID();
    @Deprecated
    public static final ClassID EXCEL2007_XLSB = ClassIDPredefined.EXCEL_V12_XLSB.getClassID();
    @Deprecated
    public static final ClassID EXCEL2010 = ClassIDPredefined.EXCEL_V14.getClassID();
    @Deprecated
    public static final ClassID EXCEL2010_CHART = ClassIDPredefined.EXCEL_V14_CHART.getClassID();
    @Deprecated
    public static final ClassID EXCEL2010_ODS = ClassIDPredefined.EXCEL_V14_ODS.getClassID();
    @Deprecated
    public static final ClassID WORD95 = ClassIDPredefined.WORD_V7.getClassID();
    @Deprecated
    public static final ClassID WORD97 = ClassIDPredefined.WORD_V8.getClassID();
    @Deprecated
    public static final ClassID WORD2007 = ClassIDPredefined.WORD_V12.getClassID();
    @Deprecated
    public static final ClassID WORD2007_MACRO = ClassIDPredefined.WORD_V12_MACRO.getClassID();
    @Deprecated
    public static final ClassID POWERPOINT97 = ClassIDPredefined.POWERPOINT_V8.getClassID();
    @Deprecated
    public static final ClassID POWERPOINT95 = ClassIDPredefined.POWERPOINT_V7.getClassID();
    @Deprecated
    public static final ClassID POWERPOINT2007 = ClassIDPredefined.POWERPOINT_V12.getClassID();
    @Deprecated
    public static final ClassID POWERPOINT2007_MACRO = ClassIDPredefined.POWERPOINT_V12_MACRO.getClassID();
    @Deprecated
    public static final ClassID EQUATION30 = ClassIDPredefined.EQUATION_V3.getClassID();
    public static final int LENGTH = 16;
    private final byte[] bytes = new byte[16];

    public ClassID(byte[] src, int offset) {
        this.read(src, offset);
    }

    public ClassID() {
        Arrays.fill(this.bytes, (byte)0);
    }

    public ClassID(ClassID other) {
        System.arraycopy(other.bytes, 0, this.bytes, 0, this.bytes.length);
    }

    public ClassID(String externalForm) {
        String clsStr = externalForm.replaceAll("[{}-]", "");
        for (int i = 0; i < clsStr.length(); i += 2) {
            this.bytes[i / 2] = (byte)Integer.parseInt(clsStr.substring(i, i + 2), 16);
        }
    }

    public ClassID(LittleEndianInput lei) {
        byte[] buf = (byte[])this.bytes.clone();
        lei.readFully(buf);
        this.read(buf, 0);
    }

    public int length() {
        return 16;
    }

    public byte[] getBytes() {
        return this.bytes;
    }

    public void setBytes(byte[] bytes) {
        System.arraycopy(bytes, 0, this.bytes, 0, 16);
    }

    public byte[] read(byte[] src, int offset) {
        this.bytes[0] = src[3 + offset];
        this.bytes[1] = src[2 + offset];
        this.bytes[2] = src[1 + offset];
        this.bytes[3] = src[0 + offset];
        this.bytes[4] = src[5 + offset];
        this.bytes[5] = src[4 + offset];
        this.bytes[6] = src[7 + offset];
        this.bytes[7] = src[6 + offset];
        System.arraycopy(src, 8 + offset, this.bytes, 8, 8);
        return this.bytes;
    }

    public void write(byte[] dst, int offset) throws ArrayStoreException {
        if (dst.length < 16) {
            throw new ArrayStoreException("Destination byte[] must have room for at least 16 bytes, but has a length of only " + dst.length + ".");
        }
        dst[0 + offset] = this.bytes[3];
        dst[1 + offset] = this.bytes[2];
        dst[2 + offset] = this.bytes[1];
        dst[3 + offset] = this.bytes[0];
        dst[4 + offset] = this.bytes[5];
        dst[5 + offset] = this.bytes[4];
        dst[6 + offset] = this.bytes[7];
        dst[7 + offset] = this.bytes[6];
        System.arraycopy(this.bytes, 8, dst, 8 + offset, 8);
    }

    public void write(LittleEndianOutput leo) {
        byte[] buf = (byte[])this.bytes.clone();
        this.write(buf, 0);
        leo.write(buf);
    }

    public boolean equals(Object o) {
        return o instanceof ClassID && Arrays.equals(this.bytes, ((ClassID)o).bytes);
    }

    public boolean equalsInverted(ClassID o) {
        return o.bytes[0] == this.bytes[3] && o.bytes[1] == this.bytes[2] && o.bytes[2] == this.bytes[1] && o.bytes[3] == this.bytes[0] && o.bytes[4] == this.bytes[5] && o.bytes[5] == this.bytes[4] && o.bytes[6] == this.bytes[7] && o.bytes[7] == this.bytes[6] && o.bytes[8] == this.bytes[8] && o.bytes[9] == this.bytes[9] && o.bytes[10] == this.bytes[10] && o.bytes[11] == this.bytes[11] && o.bytes[12] == this.bytes[12] && o.bytes[13] == this.bytes[13] && o.bytes[14] == this.bytes[14] && o.bytes[15] == this.bytes[15];
    }

    public int hashCode() {
        return this.toString().hashCode();
    }

    public String toString() {
        String hex = Hex.encodeHexString(this.bytes, false);
        return "{" + hex.substring(0, 8) + "-" + hex.substring(8, 12) + "-" + hex.substring(12, 16) + "-" + hex.substring(16, 20) + "-" + hex.substring(20) + "}";
    }

    @Override
    public ClassID copy() {
        return new ClassID(this);
    }
}

