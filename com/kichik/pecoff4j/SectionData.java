/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.kichik.pecoff4j;

public class SectionData {
    private byte[] data;
    private byte[] preamble;

    public byte[] getPreamble() {
        return this.preamble;
    }

    public void setPreamble(byte[] preamble) {
        this.preamble = preamble;
    }

    public byte[] getData() {
        return this.data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }
}

