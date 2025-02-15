/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.kichik.pecoff4j.resources;

import com.kichik.pecoff4j.resources.BitmapFileHeader;
import com.kichik.pecoff4j.resources.BitmapInfoHeader;

public class Bitmap {
    private BitmapFileHeader fileHeader;
    private BitmapInfoHeader infoHeader;
    private byte[] colors;
    private byte[] bitmapBits;

    public BitmapFileHeader getFileHeader() {
        return this.fileHeader;
    }

    public BitmapInfoHeader getInfoHeader() {
        return this.infoHeader;
    }

    public byte[] getColors() {
        return this.colors;
    }

    public byte[] getBitmapBits() {
        return this.bitmapBits;
    }

    public void setFileHeader(BitmapFileHeader fileHeader) {
        this.fileHeader = fileHeader;
    }

    public void setInfoHeader(BitmapInfoHeader infoHeader) {
        this.infoHeader = infoHeader;
    }

    public void setColors(byte[] colors) {
        this.colors = colors;
    }

    public void setBitmapBits(byte[] bitmapBits) {
        this.bitmapBits = bitmapBits;
    }
}

