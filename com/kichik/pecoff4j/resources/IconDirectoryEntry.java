/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.kichik.pecoff4j.resources;

import com.kichik.pecoff4j.resources.GroupIconDirectoryEntry;

public class IconDirectoryEntry {
    private int width;
    private int height;
    private int colorCount;
    private int reserved;
    private int planes;
    private int bitCount;
    private int bytesInRes;
    private int offset;

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }

    public int getColorCount() {
        return this.colorCount;
    }

    public int getReserved() {
        return this.reserved;
    }

    public int getPlanes() {
        return this.planes;
    }

    public int getBitCount() {
        return this.bitCount;
    }

    public int getBytesInRes() {
        return this.bytesInRes;
    }

    public int getOffset() {
        return this.offset;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setColorCount(int colorCount) {
        this.colorCount = colorCount;
    }

    public void setReserved(int reserved) {
        this.reserved = reserved;
    }

    public void setPlanes(int planes) {
        this.planes = planes;
    }

    public void setBitCount(int bitCount) {
        this.bitCount = bitCount;
    }

    public void setBytesInRes(int bytesInRes) {
        this.bytesInRes = bytesInRes;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public void copyFrom(GroupIconDirectoryEntry gide) {
        this.width = gide.getWidth();
        this.height = gide.getHeight();
        this.colorCount = gide.getColorCount();
        this.reserved = 0;
        this.planes = gide.getPlanes();
        this.bitCount = gide.getBitCount();
        this.bytesInRes = gide.getBitCount();
        this.offset = 0;
    }

    public static int sizeOf() {
        return 16;
    }
}

