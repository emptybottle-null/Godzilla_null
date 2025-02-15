/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.kichik.pecoff4j;

public class ImageDataDirectory {
    private int virtualAddress;
    private int size;

    public int getVirtualAddress() {
        return this.virtualAddress;
    }

    public int getSize() {
        return this.size;
    }

    public void setVirtualAddress(int virtualAddress) {
        this.virtualAddress = virtualAddress;
    }

    public void setSize(int size) {
        this.size = size;
    }
}

