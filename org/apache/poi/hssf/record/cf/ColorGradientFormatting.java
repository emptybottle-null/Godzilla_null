/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.hssf.record.cf;

import java.util.stream.Stream;
import org.apache.poi.common.Duplicatable;
import org.apache.poi.hssf.record.cf.ColorGradientThreshold;
import org.apache.poi.hssf.record.cf.Threshold;
import org.apache.poi.hssf.record.common.ExtendedColor;
import org.apache.poi.util.BitField;
import org.apache.poi.util.BitFieldFactory;
import org.apache.poi.util.LittleEndianInput;
import org.apache.poi.util.LittleEndianOutput;
import org.apache.poi.util.POILogFactory;
import org.apache.poi.util.POILogger;
import org.apache.poi.util.Removal;

public final class ColorGradientFormatting
implements Duplicatable {
    private static final POILogger log = POILogFactory.getLogger(ColorGradientFormatting.class);
    private static final BitField clamp = BitFieldFactory.getInstance(1);
    private static final BitField background = BitFieldFactory.getInstance(2);
    private byte options;
    private ColorGradientThreshold[] thresholds;
    private ExtendedColor[] colors;

    public ColorGradientFormatting() {
        this.options = (byte)3;
        this.thresholds = new ColorGradientThreshold[3];
        this.colors = new ExtendedColor[3];
    }

    public ColorGradientFormatting(ColorGradientFormatting other) {
        this.options = other.options;
        if (other.thresholds != null) {
            this.thresholds = (ColorGradientThreshold[])Stream.of(other.thresholds).map(ColorGradientThreshold::copy).toArray(ColorGradientThreshold[]::new);
        }
        if (other.colors != null) {
            this.colors = (ExtendedColor[])Stream.of(other.colors).map(ExtendedColor::copy).toArray(ExtendedColor[]::new);
        }
    }

    public ColorGradientFormatting(LittleEndianInput in) {
        int i;
        in.readShort();
        in.readByte();
        byte numI = in.readByte();
        byte numG = in.readByte();
        if (numI != numG) {
            log.log(5, "Inconsistent Color Gradient defintion, found " + numI + " vs " + numG + " entries");
        }
        this.options = in.readByte();
        this.thresholds = new ColorGradientThreshold[numI];
        for (i = 0; i < this.thresholds.length; ++i) {
            this.thresholds[i] = new ColorGradientThreshold(in);
        }
        this.colors = new ExtendedColor[numG];
        for (i = 0; i < this.colors.length; ++i) {
            in.readDouble();
            this.colors[i] = new ExtendedColor(in);
        }
    }

    public int getNumControlPoints() {
        return this.thresholds.length;
    }

    public void setNumControlPoints(int num) {
        if (num != this.thresholds.length) {
            ColorGradientThreshold[] nt = new ColorGradientThreshold[num];
            ExtendedColor[] nc = new ExtendedColor[num];
            int copy = Math.min(this.thresholds.length, num);
            System.arraycopy(this.thresholds, 0, nt, 0, copy);
            System.arraycopy(this.colors, 0, nc, 0, copy);
            this.thresholds = nt;
            this.colors = nc;
            this.updateThresholdPositions();
        }
    }

    public ColorGradientThreshold[] getThresholds() {
        return this.thresholds;
    }

    public void setThresholds(ColorGradientThreshold[] thresholds) {
        this.thresholds = thresholds == null ? null : (ColorGradientThreshold[])thresholds.clone();
        this.updateThresholdPositions();
    }

    public ExtendedColor[] getColors() {
        return this.colors;
    }

    public void setColors(ExtendedColor[] colors) {
        this.colors = colors == null ? null : (ExtendedColor[])colors.clone();
    }

    public boolean isClampToCurve() {
        return this.getOptionFlag(clamp);
    }

    public boolean isAppliesToBackground() {
        return this.getOptionFlag(background);
    }

    private boolean getOptionFlag(BitField field) {
        return field.isSet(this.options);
    }

    private void updateThresholdPositions() {
        double step = 1.0 / (double)(this.thresholds.length - 1);
        for (int i = 0; i < this.thresholds.length; ++i) {
            this.thresholds[i].setPosition(step * (double)i);
        }
    }

    public String toString() {
        StringBuilder buffer = new StringBuilder();
        buffer.append("    [Color Gradient Formatting]\n");
        buffer.append("          .clamp     = ").append(this.isClampToCurve()).append("\n");
        buffer.append("          .background= ").append(this.isAppliesToBackground()).append("\n");
        for (ColorGradientThreshold colorGradientThreshold : this.thresholds) {
            buffer.append(colorGradientThreshold);
        }
        for (Duplicatable duplicatable : this.colors) {
            buffer.append(duplicatable);
        }
        buffer.append("    [/Color Gradient Formatting]\n");
        return buffer.toString();
    }

    @Deprecated
    @Removal(version="5.0.0")
    public ColorGradientFormatting clone() {
        return this.copy();
    }

    @Override
    public ColorGradientFormatting copy() {
        return new ColorGradientFormatting(this);
    }

    public int getDataLength() {
        int len = 6;
        for (ColorGradientThreshold colorGradientThreshold : this.thresholds) {
            len += ((Threshold)colorGradientThreshold).getDataLength();
        }
        for (Duplicatable duplicatable : this.colors) {
            len += ((ExtendedColor)duplicatable).getDataLength();
            len += 8;
        }
        return len;
    }

    public void serialize(LittleEndianOutput out) {
        out.writeShort(0);
        out.writeByte(0);
        out.writeByte(this.thresholds.length);
        out.writeByte(this.thresholds.length);
        out.writeByte(this.options);
        for (ColorGradientThreshold t : this.thresholds) {
            t.serialize(out);
        }
        double step = 1.0 / (double)(this.colors.length - 1);
        for (int i = 0; i < this.colors.length; ++i) {
            out.writeDouble((double)i * step);
            ExtendedColor c = this.colors[i];
            c.serialize(out);
        }
    }
}

