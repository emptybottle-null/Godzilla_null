/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.kichik.pecoff4j;

public class COFFHeader {
    private int machine;
    private int numberOfSections;
    private int timeDateStamp;
    private int pointerToSymbolTable;
    private int numberOfSymbols;
    private int sizeOfOptionalHeader;
    private int characteristics;

    public int getMachine() {
        return this.machine;
    }

    public int getNumberOfSections() {
        return this.numberOfSections;
    }

    public int getTimeDateStamp() {
        return this.timeDateStamp;
    }

    public int getPointerToSymbolTable() {
        return this.pointerToSymbolTable;
    }

    public int getNumberOfSymbols() {
        return this.numberOfSymbols;
    }

    public int getSizeOfOptionalHeader() {
        return this.sizeOfOptionalHeader;
    }

    public int getCharacteristics() {
        return this.characteristics;
    }

    public void setMachine(int machine) {
        this.machine = machine;
    }

    public void setNumberOfSections(int numberOfSections) {
        this.numberOfSections = numberOfSections;
    }

    public void setTimeDateStamp(int timeDateStamp) {
        this.timeDateStamp = timeDateStamp;
    }

    public void setPointerToSymbolTable(int pointerToSymbolTable) {
        this.pointerToSymbolTable = pointerToSymbolTable;
    }

    public void setNumberOfSymbols(int numberOfSymbols) {
        this.numberOfSymbols = numberOfSymbols;
    }

    public void setSizeOfOptionalHeader(int sizeOfOptionalHeader) {
        this.sizeOfOptionalHeader = sizeOfOptionalHeader;
    }

    public void setCharacteristics(int characteristics) {
        this.characteristics = characteristics;
    }
}

