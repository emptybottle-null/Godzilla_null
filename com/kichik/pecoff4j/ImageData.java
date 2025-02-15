/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.kichik.pecoff4j;

import com.kichik.pecoff4j.AttributeCertificateTable;
import com.kichik.pecoff4j.BoundImportDirectoryTable;
import com.kichik.pecoff4j.DebugDirectory;
import com.kichik.pecoff4j.ExportDirectory;
import com.kichik.pecoff4j.ImportDirectory;
import com.kichik.pecoff4j.LoadConfigDirectory;
import com.kichik.pecoff4j.ResourceDirectory;
import com.kichik.pecoff4j.util.IntMap;

public class ImageData {
    private byte[] headerPadding;
    private ExportDirectory exportTable;
    private ImportDirectory importTable;
    private ResourceDirectory resourceTable;
    private byte[] exceptionTable;
    private AttributeCertificateTable certificateTable;
    private byte[] baseRelocationTable;
    private DebugDirectory debug;
    private byte[] architecture;
    private byte[] globalPtr;
    private byte[] tlsTable;
    private LoadConfigDirectory loadConfigTable;
    private BoundImportDirectoryTable boundImports;
    private byte[] iat;
    private byte[] delayImportDescriptor;
    private byte[] clrRuntimeHeader;
    private byte[] reserved;
    private byte[] debugRawDataPreamble;
    private byte[] debugRawData;
    private IntMap preambles = new IntMap();
    private byte[] trailingData;

    public byte[] getHeaderPadding() {
        return this.headerPadding;
    }

    public void setHeaderPadding(byte[] headerPadding) {
        this.headerPadding = headerPadding;
    }

    public byte[] getPreamble(int directory) {
        return (byte[])this.preambles.get(directory);
    }

    public void put(int directory, byte[] preamble) {
        this.preambles.put(directory, preamble);
    }

    public ExportDirectory getExportTable() {
        return this.exportTable;
    }

    public void setExportTable(ExportDirectory exportTable) {
        this.exportTable = exportTable;
    }

    public ImportDirectory getImportTable() {
        return this.importTable;
    }

    public void setImportTable(ImportDirectory importTable) {
        this.importTable = importTable;
    }

    public ResourceDirectory getResourceTable() {
        return this.resourceTable;
    }

    public void setResourceTable(ResourceDirectory resourceTable) {
        this.resourceTable = resourceTable;
    }

    public byte[] getExceptionTable() {
        return this.exceptionTable;
    }

    public void setExceptionTable(byte[] exceptionTable) {
        this.exceptionTable = exceptionTable;
    }

    public AttributeCertificateTable getCertificateTable() {
        return this.certificateTable;
    }

    public void setCertificateTable(AttributeCertificateTable certificateTable) {
        this.certificateTable = certificateTable;
    }

    public byte[] getBaseRelocationTable() {
        return this.baseRelocationTable;
    }

    public void setBaseRelocationTable(byte[] baseRelocationTable) {
        this.baseRelocationTable = baseRelocationTable;
    }

    public DebugDirectory getDebug() {
        return this.debug;
    }

    public void setDebug(DebugDirectory debug) {
        this.debug = debug;
    }

    public byte[] getArchitecture() {
        return this.architecture;
    }

    public void setArchitecture(byte[] architecture) {
        this.architecture = architecture;
    }

    public byte[] getGlobalPtr() {
        return this.globalPtr;
    }

    public void setGlobalPtr(byte[] globalPtr) {
        this.globalPtr = globalPtr;
    }

    public byte[] getTlsTable() {
        return this.tlsTable;
    }

    public void setTlsTable(byte[] tlsTable) {
        this.tlsTable = tlsTable;
    }

    public LoadConfigDirectory getLoadConfigTable() {
        return this.loadConfigTable;
    }

    public void setLoadConfigTable(LoadConfigDirectory loadConfigTable) {
        this.loadConfigTable = loadConfigTable;
    }

    public BoundImportDirectoryTable getBoundImports() {
        return this.boundImports;
    }

    public void setBoundImports(BoundImportDirectoryTable boundImports) {
        this.boundImports = boundImports;
    }

    public byte[] getIat() {
        return this.iat;
    }

    public void setIat(byte[] iat) {
        this.iat = iat;
    }

    public byte[] getDelayImportDescriptor() {
        return this.delayImportDescriptor;
    }

    public void setDelayImportDescriptor(byte[] delayImportDescriptor) {
        this.delayImportDescriptor = delayImportDescriptor;
    }

    public byte[] getClrRuntimeHeader() {
        return this.clrRuntimeHeader;
    }

    public void setClrRuntimeHeader(byte[] clrRuntimeHeader) {
        this.clrRuntimeHeader = clrRuntimeHeader;
    }

    public byte[] getReserved() {
        return this.reserved;
    }

    public void setReserved(byte[] reserved) {
        this.reserved = reserved;
    }

    public byte[] getDebugRawData() {
        return this.debugRawData;
    }

    public void setDebugRawData(byte[] debugRawData) {
        this.debugRawData = debugRawData;
    }

    public byte[] getTrailingData() {
        return this.trailingData;
    }

    public void setTrailingData(byte[] trailingData) {
        this.trailingData = trailingData;
    }

    public byte[] getDebugRawDataPreamble() {
        return this.debugRawDataPreamble;
    }

    public void setDebugRawDataPreamble(byte[] debugRawDataPreamble) {
        this.debugRawDataPreamble = debugRawDataPreamble;
    }
}

