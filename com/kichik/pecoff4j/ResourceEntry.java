/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.kichik.pecoff4j;

import com.kichik.pecoff4j.ResourceDirectory;

public class ResourceEntry {
    private int id;
    private String name;
    private int offset;
    private byte[] data;
    private ResourceDirectory directory;
    private int dataRVA;
    private int codePage;
    private int reserved;

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getOffset() {
        return this.offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public byte[] getData() {
        return this.data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    public ResourceDirectory getDirectory() {
        return this.directory;
    }

    public void setDirectory(ResourceDirectory directory) {
        this.directory = directory;
    }

    public int getDataRVA() {
        return this.dataRVA;
    }

    public void setDataRVA(int dataRVA) {
        this.dataRVA = dataRVA;
    }

    public int getCodePage() {
        return this.codePage;
    }

    public void setCodePage(int codePage) {
        this.codePage = codePage;
    }

    public int getReserved() {
        return this.reserved;
    }

    public void setReserved(int reserved) {
        this.reserved = reserved;
    }
}

