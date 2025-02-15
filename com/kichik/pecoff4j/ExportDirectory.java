/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.kichik.pecoff4j;

import com.kichik.pecoff4j.util.DataObject;
import com.kichik.pecoff4j.util.Reflection;

public class ExportDirectory
extends DataObject {
    private long exportFlags;
    private long timeDateStamp;
    private int majorVersion;
    private int minorVersion;
    private long nameRVA;
    private long ordinalBase;
    private long addressTableEntries;
    private long numberOfNamePointers;
    private long exportAddressTableRVA;
    private long namePointerRVA;
    private long ordinalTableRVA;

    public long getExportFlags() {
        return this.exportFlags;
    }

    public long getTimeDateStamp() {
        return this.timeDateStamp;
    }

    public int getMajorVersion() {
        return this.majorVersion;
    }

    public int getMinorVersion() {
        return this.minorVersion;
    }

    public long getNameRVA() {
        return this.nameRVA;
    }

    public long getOrdinalBase() {
        return this.ordinalBase;
    }

    public long getAddressTableEntries() {
        return this.addressTableEntries;
    }

    public long getNumberOfNamePointers() {
        return this.numberOfNamePointers;
    }

    public long getExportAddressTableRVA() {
        return this.exportAddressTableRVA;
    }

    public long getNamePointerRVA() {
        return this.namePointerRVA;
    }

    public long getOrdinalTableRVA() {
        return this.ordinalTableRVA;
    }

    public String toString() {
        return Reflection.toString(this);
    }

    public void setExportFlags(long exportFlags) {
        this.exportFlags = exportFlags;
    }

    public void setTimeDateStamp(long timeDateStamp) {
        this.timeDateStamp = timeDateStamp;
    }

    public void setMajorVersion(int majorVersion) {
        this.majorVersion = majorVersion;
    }

    public void setMinorVersion(int minorVersion) {
        this.minorVersion = minorVersion;
    }

    public void setNameRVA(long nameRVA) {
        this.nameRVA = nameRVA;
    }

    public void setOrdinalBase(long ordinalBase) {
        this.ordinalBase = ordinalBase;
    }

    public void setAddressTableEntries(long addressTableEntries) {
        this.addressTableEntries = addressTableEntries;
    }

    public void setNumberOfNamePointers(long numberOfNamePointers) {
        this.numberOfNamePointers = numberOfNamePointers;
    }

    public void setExportAddressTableRVA(long exportAddressTableRVA) {
        this.exportAddressTableRVA = exportAddressTableRVA;
    }

    public void setNamePointerRVA(long namePointerRVA) {
        this.namePointerRVA = namePointerRVA;
    }

    public void setOrdinalTableRVA(long ordinalTableRVA) {
        this.ordinalTableRVA = ordinalTableRVA;
    }
}

