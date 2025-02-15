/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.hssf.record.chart;

import org.apache.poi.hssf.record.RecordInputStream;
import org.apache.poi.hssf.record.StandardRecord;
import org.apache.poi.ss.formula.Formula;
import org.apache.poi.ss.formula.ptg.Ptg;
import org.apache.poi.util.BitField;
import org.apache.poi.util.BitFieldFactory;
import org.apache.poi.util.HexDump;
import org.apache.poi.util.LittleEndianOutput;
import org.apache.poi.util.Removal;

public final class LinkedDataRecord
extends StandardRecord {
    public static final short sid = 4177;
    public static final byte LINK_TYPE_TITLE_OR_TEXT = 0;
    public static final byte LINK_TYPE_VALUES = 1;
    public static final byte LINK_TYPE_CATEGORIES = 2;
    public static final byte LINK_TYPE_SECONDARY_CATEGORIES = 3;
    public static final byte REFERENCE_TYPE_DEFAULT_CATEGORIES = 0;
    public static final byte REFERENCE_TYPE_DIRECT = 1;
    public static final byte REFERENCE_TYPE_WORKSHEET = 2;
    public static final byte REFERENCE_TYPE_NOT_USED = 3;
    public static final byte REFERENCE_TYPE_ERROR_REPORTED = 4;
    private static final BitField customNumberFormat = BitFieldFactory.getInstance(1);
    private byte field_1_linkType;
    private byte field_2_referenceType;
    private short field_3_options;
    private short field_4_indexNumberFmtRecord;
    private Formula field_5_formulaOfLink;

    public LinkedDataRecord() {
    }

    public LinkedDataRecord(LinkedDataRecord other) {
        super(other);
        this.field_1_linkType = other.field_1_linkType;
        this.field_2_referenceType = other.field_2_referenceType;
        this.field_3_options = other.field_3_options;
        this.field_4_indexNumberFmtRecord = other.field_4_indexNumberFmtRecord;
        this.field_5_formulaOfLink = other.field_5_formulaOfLink == null ? null : other.field_5_formulaOfLink.copy();
    }

    public LinkedDataRecord(RecordInputStream in) {
        this.field_1_linkType = in.readByte();
        this.field_2_referenceType = in.readByte();
        this.field_3_options = in.readShort();
        this.field_4_indexNumberFmtRecord = in.readShort();
        int encodedTokenLen = in.readUShort();
        this.field_5_formulaOfLink = Formula.read(encodedTokenLen, in);
    }

    @Override
    public String toString() {
        StringBuilder buffer = new StringBuilder();
        buffer.append("[AI]\n");
        buffer.append("    .linkType             = ").append(HexDump.byteToHex(this.getLinkType())).append('\n');
        buffer.append("    .referenceType        = ").append(HexDump.byteToHex(this.getReferenceType())).append('\n');
        buffer.append("    .options              = ").append(HexDump.shortToHex(this.getOptions())).append('\n');
        buffer.append("    .customNumberFormat   = ").append(this.isCustomNumberFormat()).append('\n');
        buffer.append("    .indexNumberFmtRecord = ").append(HexDump.shortToHex(this.getIndexNumberFmtRecord())).append('\n');
        buffer.append("    .formulaOfLink        = ").append('\n');
        Ptg[] ptgs = this.field_5_formulaOfLink.getTokens();
        for (int i = 0; i < ptgs.length; ++i) {
            Ptg ptg = ptgs[i];
            buffer.append(ptg).append(ptg.getRVAType()).append('\n');
        }
        buffer.append("[/AI]\n");
        return buffer.toString();
    }

    @Override
    public void serialize(LittleEndianOutput out) {
        out.writeByte(this.field_1_linkType);
        out.writeByte(this.field_2_referenceType);
        out.writeShort(this.field_3_options);
        out.writeShort(this.field_4_indexNumberFmtRecord);
        this.field_5_formulaOfLink.serialize(out);
    }

    @Override
    protected int getDataSize() {
        return 6 + this.field_5_formulaOfLink.getEncodedSize();
    }

    @Override
    public short getSid() {
        return 4177;
    }

    @Deprecated
    @Removal(version="5.0.0")
    public LinkedDataRecord clone() {
        return this.copy();
    }

    @Override
    public LinkedDataRecord copy() {
        return new LinkedDataRecord(this);
    }

    public byte getLinkType() {
        return this.field_1_linkType;
    }

    public void setLinkType(byte field_1_linkType) {
        this.field_1_linkType = field_1_linkType;
    }

    public byte getReferenceType() {
        return this.field_2_referenceType;
    }

    public void setReferenceType(byte field_2_referenceType) {
        this.field_2_referenceType = field_2_referenceType;
    }

    public short getOptions() {
        return this.field_3_options;
    }

    public void setOptions(short field_3_options) {
        this.field_3_options = field_3_options;
    }

    public short getIndexNumberFmtRecord() {
        return this.field_4_indexNumberFmtRecord;
    }

    public void setIndexNumberFmtRecord(short field_4_indexNumberFmtRecord) {
        this.field_4_indexNumberFmtRecord = field_4_indexNumberFmtRecord;
    }

    public Ptg[] getFormulaOfLink() {
        return this.field_5_formulaOfLink.getTokens();
    }

    public void setFormulaOfLink(Ptg[] ptgs) {
        this.field_5_formulaOfLink = Formula.create(ptgs);
    }

    public void setCustomNumberFormat(boolean value) {
        this.field_3_options = customNumberFormat.setShortBoolean(this.field_3_options, value);
    }

    public boolean isCustomNumberFormat() {
        return customNumberFormat.isSet(this.field_3_options);
    }
}

