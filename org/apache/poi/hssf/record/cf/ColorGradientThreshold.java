/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.hssf.record.cf;

import org.apache.poi.common.Duplicatable;
import org.apache.poi.hssf.record.cf.Threshold;
import org.apache.poi.util.LittleEndianInput;
import org.apache.poi.util.LittleEndianOutput;
import org.apache.poi.util.Removal;

public final class ColorGradientThreshold
extends Threshold
implements Duplicatable {
    private double position;

    public ColorGradientThreshold() {
        this.position = 0.0;
    }

    public ColorGradientThreshold(ColorGradientThreshold other) {
        super(other);
        this.position = other.position;
    }

    public ColorGradientThreshold(LittleEndianInput in) {
        super(in);
        this.position = in.readDouble();
    }

    public double getPosition() {
        return this.position;
    }

    public void setPosition(double position) {
        this.position = position;
    }

    @Override
    public int getDataLength() {
        return super.getDataLength() + 8;
    }

    @Deprecated
    @Removal(version="5.0.0")
    public ColorGradientThreshold clone() {
        return this.copy();
    }

    @Override
    public ColorGradientThreshold copy() {
        return new ColorGradientThreshold(this);
    }

    @Override
    public void serialize(LittleEndianOutput out) {
        super.serialize(out);
        out.writeDouble(this.position);
    }
}

