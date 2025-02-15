/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.kichik.pecoff4j.resources;

import com.kichik.pecoff4j.io.IDataReader;
import com.kichik.pecoff4j.util.Reflection;
import java.io.IOException;

public class GroupIconDirectoryEntry {
    private int width;
    private int height;
    private int colorCount;
    private int reserved;
    private int planes;
    private int bitCount;
    private int bytesInRes;
    private int id;

    public static GroupIconDirectoryEntry read(IDataReader dr) throws IOException {
        GroupIconDirectoryEntry ge = new GroupIconDirectoryEntry();
        ge.width = dr.readByte();
        ge.height = dr.readByte();
        ge.colorCount = dr.readByte();
        ge.reserved = dr.readByte();
        ge.planes = dr.readWord();
        ge.bitCount = dr.readWord();
        ge.bytesInRes = dr.readDoubleWord();
        ge.id = dr.readWord();
        return ge;
    }

    public String toString() {
        return Reflection.toString(this);
    }

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

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }
}

