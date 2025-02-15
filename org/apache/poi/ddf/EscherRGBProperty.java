/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.ddf;

import org.apache.poi.ddf.EscherPropertyTypes;
import org.apache.poi.ddf.EscherSimpleProperty;

public class EscherRGBProperty
extends EscherSimpleProperty {
    public EscherRGBProperty(short propertyNumber, int rgbColor) {
        super(propertyNumber, rgbColor);
    }

    public EscherRGBProperty(EscherPropertyTypes propertyType, int rgbColor) {
        super(propertyType.propNumber, rgbColor);
    }

    public int getRgbColor() {
        return this.getPropertyValue();
    }

    public byte getRed() {
        return (byte)(this.getRgbColor() & 0xFF);
    }

    public byte getGreen() {
        return (byte)(this.getRgbColor() >> 8 & 0xFF);
    }

    public byte getBlue() {
        return (byte)(this.getRgbColor() >> 16 & 0xFF);
    }
}

