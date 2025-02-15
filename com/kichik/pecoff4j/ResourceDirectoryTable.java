/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.kichik.pecoff4j;

public class ResourceDirectoryTable {
    private int characteristics;
    private int timeDateStamp;
    private int majorVersion;
    private int minVersion;
    private int numNameEntries;
    private int numIdEntries;

    public int getCharacteristics() {
        return this.characteristics;
    }

    public void setCharacteristics(int characteristics) {
        this.characteristics = characteristics;
    }

    public int getTimeDateStamp() {
        return this.timeDateStamp;
    }

    public void setTimeDateStamp(int timeDateStamp) {
        this.timeDateStamp = timeDateStamp;
    }

    public int getMajorVersion() {
        return this.majorVersion;
    }

    public void setMajorVersion(int majorVersion) {
        this.majorVersion = majorVersion;
    }

    public int getMinVersion() {
        return this.minVersion;
    }

    public void setMinVersion(int minVersion) {
        this.minVersion = minVersion;
    }

    public int getNumNameEntries() {
        return this.numNameEntries;
    }

    public void setNumNameEntries(int numNameEntries) {
        this.numNameEntries = numNameEntries;
    }

    public int getNumIdEntries() {
        return this.numIdEntries;
    }

    public void setNumIdEntries(int numIdEntries) {
        this.numIdEntries = numIdEntries;
    }
}

