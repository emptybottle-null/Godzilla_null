/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.hssf.record.cf;

import java.util.stream.Stream;
import org.apache.poi.common.Duplicatable;
import org.apache.poi.hssf.record.cf.IconMultiStateThreshold;
import org.apache.poi.hssf.record.cf.Threshold;
import org.apache.poi.ss.usermodel.IconMultiStateFormatting;
import org.apache.poi.util.BitField;
import org.apache.poi.util.BitFieldFactory;
import org.apache.poi.util.LittleEndianInput;
import org.apache.poi.util.LittleEndianOutput;
import org.apache.poi.util.POILogFactory;
import org.apache.poi.util.POILogger;
import org.apache.poi.util.Removal;

public final class IconMultiStateFormatting
implements Duplicatable {
    private static final POILogger log = POILogFactory.getLogger(IconMultiStateFormatting.class);
    private IconMultiStateFormatting.IconSet iconSet;
    private byte options;
    private Threshold[] thresholds;
    private static BitField iconOnly = BitFieldFactory.getInstance(1);
    private static BitField reversed = BitFieldFactory.getInstance(4);

    public IconMultiStateFormatting() {
        this.iconSet = IconMultiStateFormatting.IconSet.GYR_3_TRAFFIC_LIGHTS;
        this.options = 0;
        this.thresholds = new Threshold[this.iconSet.num];
    }

    public IconMultiStateFormatting(IconMultiStateFormatting other) {
        this.iconSet = other.iconSet;
        this.options = other.options;
        if (other.thresholds != null) {
            this.thresholds = (Threshold[])Stream.of(other.thresholds).map(Threshold::copy).toArray(Threshold[]::new);
        }
    }

    public IconMultiStateFormatting(LittleEndianInput in) {
        in.readShort();
        in.readByte();
        byte num = in.readByte();
        byte set = in.readByte();
        this.iconSet = IconMultiStateFormatting.IconSet.byId(set);
        if (this.iconSet.num != num) {
            log.log(5, "Inconsistent Icon Set defintion, found " + (Object)((Object)this.iconSet) + " but defined as " + num + " entries");
        }
        this.options = in.readByte();
        this.thresholds = new Threshold[this.iconSet.num];
        for (int i = 0; i < this.thresholds.length; ++i) {
            this.thresholds[i] = new IconMultiStateThreshold(in);
        }
    }

    public IconMultiStateFormatting.IconSet getIconSet() {
        return this.iconSet;
    }

    public void setIconSet(IconMultiStateFormatting.IconSet set) {
        this.iconSet = set;
    }

    public Threshold[] getThresholds() {
        return this.thresholds;
    }

    public void setThresholds(Threshold[] thresholds) {
        this.thresholds = thresholds == null ? null : (Threshold[])thresholds.clone();
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

    public String toString() {
        StringBuilder buffer = new StringBuilder();
        buffer.append("    [Icon Formatting]\n");
        buffer.append("          .icon_set = ").append((Object)this.iconSet).append("\n");
        buffer.append("          .icon_only= ").append(this.isIconOnly()).append("\n");
        buffer.append("          .reversed = ").append(this.isReversed()).append("\n");
        for (Threshold t : this.thresholds) {
            buffer.append(t);
        }
        buffer.append("    [/Icon Formatting]\n");
        return buffer.toString();
    }

    @Deprecated
    @Removal(version="5.0.0")
    public IconMultiStateFormatting clone() {
        return this.copy();
    }

    @Override
    public IconMultiStateFormatting copy() {
        return new IconMultiStateFormatting(this);
    }

    public int getDataLength() {
        int len = 6;
        for (Threshold t : this.thresholds) {
            len += t.getDataLength();
        }
        return len;
    }

    public void serialize(LittleEndianOutput out) {
        out.writeShort(0);
        out.writeByte(0);
        out.writeByte(this.iconSet.num);
        out.writeByte(this.iconSet.id);
        out.writeByte(this.options);
        for (Threshold t : this.thresholds) {
            t.serialize(out);
        }
    }
}

