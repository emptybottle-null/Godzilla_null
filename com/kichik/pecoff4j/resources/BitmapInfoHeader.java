/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.kichik.pecoff4j.resources;

public class BitmapInfoHeader {
    private int size;
    private int width;
    private int height;
    private int planes;
    private int bitCount;
    private int compression;
    private int sizeImage;
    private int xpelsPerMeter;
    private int ypelsPerMeter;
    private int clrUsed;
    private int clrImportant;

    public int getSize() {
        return this.size;
    }

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }

    public int getPlanes() {
        return this.planes;
    }

    public int getBitCount() {
        return this.bitCount;
    }

    public int getCompression() {
        return this.compression;
    }

    public int getSizeImage() {
        return this.sizeImage;
    }

    public int getXpelsPerMeter() {
        return this.xpelsPerMeter;
    }

    public int getYpelsPerMeter() {
        return this.ypelsPerMeter;
    }

    public int getClrUsed() {
        return this.clrUsed;
    }

    public int getClrImportant() {
        return this.clrImportant;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setPlanes(int planes) {
        this.planes = planes;
    }

    public void setBitCount(int bitCount) {
        this.bitCount = bitCount;
    }

    public void setCompression(int compression) {
        this.compression = compression;
    }

    public void setSizeImage(int sizeImage) {
        this.sizeImage = sizeImage;
    }

    public void setXpelsPerMeter(int xpelsPerMeter) {
        this.xpelsPerMeter = xpelsPerMeter;
    }

    public void setYpelsPerMeter(int ypelsPerMeter) {
        this.ypelsPerMeter = ypelsPerMeter;
    }

    public void setClrUsed(int clrUsed) {
        this.clrUsed = clrUsed;
    }

    public void setClrImportant(int clrImportant) {
        this.clrImportant = clrImportant;
    }
}

