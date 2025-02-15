/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.kichik.pecoff4j;

import com.kichik.pecoff4j.util.DataObject;

public class DebugDirectory
extends DataObject {
    private int characteristics;
    private int timeDateStamp;
    private int majorVersion;
    private int type;
    private int sizeOfData;
    private int addressOfRawData;
    private int pointerToRawData;

    public int getCharacteristics() {
        return this.characteristics;
    }

    public int getTimeDateStamp() {
        return this.timeDateStamp;
    }

    public int getMajorVersion() {
        return this.majorVersion;
    }

    public int getType() {
        return this.type;
    }

    public int getSizeOfData() {
        return this.sizeOfData;
    }

    public int getAddressOfRawData() {
        return this.addressOfRawData;
    }

    public int getPointerToRawData() {
        return this.pointerToRawData;
    }

    public void setCharacteristics(int characteristics) {
        this.characteristics = characteristics;
    }

    public void setTimeDateStamp(int timeDateStamp) {
        this.timeDateStamp = timeDateStamp;
    }

    public void setMajorVersion(int majorVersion) {
        this.majorVersion = majorVersion;
    }

    public void setType(int type) {
        this.type = type;
    }

    public void setSizeOfData(int sizeOfData) {
        this.sizeOfData = sizeOfData;
    }

    public void setAddressOfRawData(int addressOfRawData) {
        this.addressOfRawData = addressOfRawData;
    }

    public void setPointerToRawData(int pointerToRawData) {
        this.pointerToRawData = pointerToRawData;
    }
}

