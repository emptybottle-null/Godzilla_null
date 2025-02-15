/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.hssf.record.cf;

import org.apache.poi.common.Duplicatable;
import org.apache.poi.hssf.record.cf.DataBarThreshold;
import org.apache.poi.hssf.record.common.ExtendedColor;
import org.apache.poi.util.BitField;
import org.apache.poi.util.BitFieldFactory;
import org.apache.poi.util.LittleEndianInput;
import org.apache.poi.util.LittleEndianOutput;
import org.apache.poi.util.POILogFactory;
import org.apache.poi.util.POILogger;
import org.apache.poi.util.Removal;

public final class DataBarFormatting
implements Duplicatable {
    private static POILogger log = POILogFactory.getLogger(DataBarFormatting.class);
    private static final BitField iconOnly = BitFieldFactory.getInstance(1);
    private static final BitField reversed = BitFieldFactory.getInstance(4);
    private byte options;
    private byte percentMin;
    private byte percentMax;
    private ExtendedColor color;
    private DataBarThreshold thresholdMin;
    private DataBarThreshold thresholdMax;

    public DataBarFormatting() {
        this.options = (byte)2;
    }

    public DataBarFormatting(DataBarFormatting other) {
        this.options = other.options;
        this.percentMin = other.percentMin;
        this.percentMax = other.percentMax;
        this.color = other.color == null ? null : other.color.copy();
        this.thresholdMin = other.thresholdMin == null ? null : other.thresholdMin.copy();
        this.thresholdMax = other.thresholdMax == null ? null : other.thresholdMax.copy();
    }

    public DataBarFormatting(LittleEndianInput in) {
        in.readShort();
        in.readByte();
        this.options = in.readByte();
        this.percentMin = in.readByte();
        this.percentMax = in.readByte();
        if (this.percentMin < 0 || this.percentMin > 100) {
            log.log(5, "Inconsistent Minimum Percentage found " + this.percentMin);
        }
        if (this.percentMax < 0 || this.percentMax > 100) {
            log.log(5, "Inconsistent Minimum Percentage found " + this.percentMin);
        }
        this.color = new ExtendedColor(in);
        this.thresholdMin = new DataBarThreshold(in);
        this.thresholdMax = new DataBarThreshold(in);
    }

    public boolean isIconOnly() {
        return this.getOptionFlag(iconOnly);
    }

    public void setIconOnly(boolean only) {
        this.setOptionFlag(only, iconOnly);
    }

    public boolean isReversed() {
        return this.getOptionFlag(reversed);
    }

    public void setReversed(boolean rev) {
        this.setOptionFlag(rev, reversed);
    }

    private boolean getOptionFlag(BitField field) {
        int value = field.getValue(this.options);
        return value != 0;
    }

    private void setOptionFlag(boolean option, BitField field) {
        this.options = field.setByteBoolean(this.options, option);
    }

    public byte getPercentMin() {
        return this.percentMin;
    }

    public void setPercentMin(byte percentMin) {
        this.percentMin = percentMin;
    }

    public byte getPercentMax() {
        return this.percentMax;
    }

    public void setPercentMax(byte percentMax) {
        this.percentMax = percentMax;
    }

    public ExtendedColor getColor() {
        return this.color;
    }

    public void setColor(ExtendedColor color) {
        this.color = color;
    }

    public DataBarThreshold getThresholdMin() {
        return this.thresholdMin;
    }

    public void setThresholdMin(DataBarThreshold thresholdMin) {
        this.thresholdMin = thresholdMin;
    }

    public DataBarThreshold getThresholdMax() {
        return this.thresholdMax;
    }

    public void setThresholdMax(DataBarThreshold thresholdMax) {
        this.thresholdMax = thresholdMax;
    }

    public String toString() {
        StringBuilder buffer = new StringBuilder();
        buffer.append("    [Data Bar Formatting]\n");
        buffer.append("          .icon_only= ").append(this.isIconOnly()).append("\n");
        buffer.append("          .reversed = ").append(this.isReversed()).append("\n");
        buffer.append(this.color);
        buffer.append(this.thresholdMin);
        buffer.append(this.thresholdMax);
        buffer.append("    [/Data Bar Formatting]\n");
        return buffer.toString();
    }

    @Deprecated
    @Removal(version="5.0.0")
    public DataBarFormatting clone() {
        return this.copy();
    }

    @Override
    public DataBarFormatting copy() {
        return new DataBarFormatting(this);
    }

    public int getDataLength() {
        return 6 + this.color.getDataLength() + this.thresholdMin.getDataLength() + this.thresholdMax.getDataLength();
    }

    public void serialize(LittleEndianOutput out) {
        out.writeShort(0);
        out.writeByte(0);
        out.writeByte(this.options);
        out.writeByte(this.percentMin);
        out.writeByte(this.percentMax);
        this.color.serialize(out);
        this.thresholdMin.serialize(out);
        this.thresholdMax.serialize(out);
    }
}

