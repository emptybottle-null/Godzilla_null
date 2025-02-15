/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.kichik.pecoff4j;

import com.kichik.pecoff4j.COFFHeader;
import com.kichik.pecoff4j.DOSHeader;
import com.kichik.pecoff4j.DOSStub;
import com.kichik.pecoff4j.ImageData;
import com.kichik.pecoff4j.OptionalHeader;
import com.kichik.pecoff4j.PESignature;
import com.kichik.pecoff4j.SectionTable;

public class PE {
    private DOSHeader dosHeader;
    private DOSStub stub;
    private PESignature signature;
    private COFFHeader coffHeader;
    private OptionalHeader optionalHeader;
    private ImageData imageData;
    private SectionTable sectionTable;
    private boolean is64bit;

    public DOSHeader getDosHeader() {
        return this.dosHeader;
    }

    public DOSStub getStub() {
        return this.stub;
    }

    public PESignature getSignature() {
        return this.signature;
    }

    public COFFHeader getCoffHeader() {
        return this.coffHeader;
    }

    public OptionalHeader getOptionalHeader() {
        return this.optionalHeader;
    }

    public boolean is64() {
        return this.is64bit;
    }

    public SectionTable getSectionTable() {
        return this.sectionTable;
    }

    public void setDosHeader(DOSHeader dosHeader) {
        this.dosHeader = dosHeader;
    }

    public void setStub(DOSStub stub) {
        this.stub = stub;
    }

    public void setSignature(PESignature signature) {
        this.signature = signature;
    }

    public void setCoffHeader(COFFHeader coffHeader) {
        this.coffHeader = coffHeader;
    }

    public void setOptionalHeader(OptionalHeader optionalHeader) {
        this.optionalHeader = optionalHeader;
    }

    public void set64(boolean is64bit) {
        this.is64bit = is64bit;
    }

    public void setSectionTable(SectionTable sectionTable) {
        this.sectionTable = sectionTable;
    }

    public ImageData getImageData() {
        if (this.imageData == null) {
            this.imageData = new ImageData();
        }
        return this.imageData;
    }

    public void setImageData(ImageData imageData) {
        this.imageData = imageData;
    }
}

