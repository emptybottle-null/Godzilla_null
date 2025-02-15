/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.kichik.pecoff4j.resources;

import com.kichik.pecoff4j.io.DataReader;
import java.io.IOException;

public class BitmapFileHeader {
    private int type;
    private int size;
    private int reserved1;
    private int reserved2;
    private int offBits;

    public static BitmapFileHeader read(DataReader dr) throws IOException {
        BitmapFileHeader bfh = new BitmapFileHeader();
        bfh.type = dr.readWord();
        bfh.size = dr.readDoubleWord();
        bfh.reserved1 = dr.readWord();
        bfh.reserved2 = dr.readWord();
        bfh.offBits = dr.readDoubleWord();
        return bfh;
    }

    public int getType() {
        return this.type;
    }

    public int getSize() {
        return this.size;
    }

    public int getReserved1() {
        return this.reserved1;
    }

    public int getReserved2() {
        return this.reserved2;
    }

    public int getOffBits() {
        return this.offBits;
    }

    public void setType(int type) {
        this.type = type;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public void setReserved1(int reserved1) {
        this.reserved1 = reserved1;
    }

    public void setReserved2(int reserved2) {
        this.reserved2 = reserved2;
    }

    public void setOffBits(int offBits) {
        this.offBits = offBits;
    }
}

