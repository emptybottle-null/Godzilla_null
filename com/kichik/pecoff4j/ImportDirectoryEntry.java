/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.kichik.pecoff4j;

public class ImportDirectoryEntry {
    private int importLookupTableRVA;
    private int timeDateStamp;
    private int forwarderChain;
    private int nameRVA;
    private int importAddressTableRVA;

    public int getImportLookupTableRVA() {
        return this.importLookupTableRVA;
    }

    public int getTimeDateStamp() {
        return this.timeDateStamp;
    }

    public int getForwarderChain() {
        return this.forwarderChain;
    }

    public int getNameRVA() {
        return this.nameRVA;
    }

    public int getImportAddressTableRVA() {
        return this.importAddressTableRVA;
    }

    public void setImportLookupTableRVA(int importLookupTableRVA) {
        this.importLookupTableRVA = importLookupTableRVA;
    }

    public void setTimeDateStamp(int timeDateStamp) {
        this.timeDateStamp = timeDateStamp;
    }

    public void setForwarderChain(int forwarderChain) {
        this.forwarderChain = forwarderChain;
    }

    public void setNameRVA(int nameRVA) {
        this.nameRVA = nameRVA;
    }

    public void setImportAddressTableRVA(int importAddressTableRVA) {
        this.importAddressTableRVA = importAddressTableRVA;
    }
}

