/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.kichik.pecoff4j;

public class SectionHeader {
    private String name;
    private int virtualSize;
    private int virtualAddress;
    private int sizeOfRawData;
    private int pointerToRawData;
    private int pointerToRelocations;
    private int pointerToLineNumbers;
    private int numberOfRelocations;
    private int numberOfLineNumbers;
    private int characteristics;

    public String getName() {
        return this.name;
    }

    public int getVirtualSize() {
        return this.virtualSize;
    }

    public int getVirtualAddress() {
        return this.virtualAddress;
    }

    public int getSizeOfRawData() {
        return this.sizeOfRawData;
    }

    public int getPointerToRawData() {
        return this.pointerToRawData;
    }

    public int getPointerToRelocations() {
        return this.pointerToRelocations;
    }

    public int getPointerToLineNumbers() {
        return this.pointerToLineNumbers;
    }

    public int getNumberOfRelocations() {
        return this.numberOfRelocations;
    }

    public int getNumberOfLineNumbers() {
        return this.numberOfLineNumbers;
    }

    public int getCharacteristics() {
        return this.characteristics;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setVirtualSize(int virtualSize) {
        this.virtualSize = virtualSize;
    }

    public void setVirtualAddress(int virtualAddress) {
        this.virtualAddress = virtualAddress;
    }

    public void setSizeOfRawData(int sizeOfRawData) {
        this.sizeOfRawData = sizeOfRawData;
    }

    public void setPointerToRawData(int pointerToRawData) {
        this.pointerToRawData = pointerToRawData;
    }

    public void setPointerToRelocations(int pointerToRelocations) {
        this.pointerToRelocations = pointerToRelocations;
    }

    public void setPointerToLineNumbers(int pointerToLineNumbers) {
        this.pointerToLineNumbers = pointerToLineNumbers;
    }

    public void setNumberOfRelocations(int numberOfRelocations) {
        this.numberOfRelocations = numberOfRelocations;
    }

    public void setNumberOfLineNumbers(int numberOfLineNumbers) {
        this.numberOfLineNumbers = numberOfLineNumbers;
    }

    public void setCharacteristics(int characteristics) {
        this.characteristics = characteristics;
    }
}

