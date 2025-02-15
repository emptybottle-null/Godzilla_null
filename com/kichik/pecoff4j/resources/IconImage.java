/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.kichik.pecoff4j.resources;

import com.kichik.pecoff4j.resources.BitmapInfoHeader;
import com.kichik.pecoff4j.resources.RGBQuad;

public class IconImage {
    private BitmapInfoHeader header;
    private RGBQuad[] colors;
    private byte[] xorMask;
    private byte[] andMask;
    private byte[] pngData;

    public BitmapInfoHeader getHeader() {
        return this.header;
    }

    public RGBQuad[] getColors() {
        return this.colors;
    }

    public byte[] getXorMask() {
        return this.xorMask;
    }

    public byte[] getAndMask() {
        return this.andMask;
    }

    public byte[] getPNG() {
        return this.pngData;
    }

    public void setHeader(BitmapInfoHeader header) {
        this.header = header;
    }

    public void setColors(RGBQuad[] colors) {
        this.colors = colors;
    }

    public void setXorMask(byte[] xorMask) {
        this.xorMask = xorMask;
    }

    public void setAndMask(byte[] andMask) {
        this.andMask = andMask;
    }

    public void setPngData(byte[] pngData) {
        this.pngData = pngData;
    }

    public int sizeOf() {
        return this.header == null ? this.pngData.length : this.header.getSize() + (this.colors == null ? 0 : this.colors.length * 4) + this.xorMask.length + this.andMask.length;
    }
}

