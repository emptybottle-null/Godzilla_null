/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.kichik.pecoff4j;

public class DOSHeader {
    public static final int DOS_MAGIC = 0;
    private int magic;
    private int usedBytesInLastPage;
    private int fileSizeInPages;
    private int numRelocationItems;
    private int headerSizeInParagraphs;
    private int minExtraParagraphs;
    private int maxExtraParagraphs;
    private int initialSS;
    private int initialSP;
    private int checksum;
    private int initialIP;
    private int initialRelativeCS;
    private int addressOfRelocationTable;
    private int overlayNumber;
    private int[] reserved;
    private int[] reserved2;
    private int oemId;
    private int oemInfo;
    private int addressOfNewExeHeader;
    private int stubSize;

    public int getMagic() {
        return this.magic;
    }

    public boolean isValidMagic() {
        return this.magic == 0;
    }

    public int getUsedBytesInLastPage() {
        return this.usedBytesInLastPage;
    }

    public int getFileSizeInPages() {
        return this.fileSizeInPages;
    }

    public int getNumRelocationItems() {
        return this.numRelocationItems;
    }

    public int getHeaderSizeInParagraphs() {
        return this.headerSizeInParagraphs;
    }

    public int getMinExtraParagraphs() {
        return this.minExtraParagraphs;
    }

    public int getMaxExtraParagraphs() {
        return this.maxExtraParagraphs;
    }

    public int getInitialSS() {
        return this.initialSS;
    }

    public int getInitialSP() {
        return this.initialSP;
    }

    public int getChecksum() {
        return this.checksum;
    }

    public int getInitialIP() {
        return this.initialIP;
    }

    public int getInitialRelativeCS() {
        return this.initialRelativeCS;
    }

    public int getAddressOfRelocationTable() {
        return this.addressOfRelocationTable;
    }

    public int getOverlayNumber() {
        return this.overlayNumber;
    }

    public int getOemId() {
        return this.oemId;
    }

    public int getOemInfo() {
        return this.oemInfo;
    }

    public int getAddressOfNewExeHeader() {
        return this.addressOfNewExeHeader;
    }

    public int[] getReserved() {
        return this.reserved;
    }

    public int[] getReserved2() {
        return this.reserved2;
    }

    public int getStubSize() {
        return this.stubSize;
    }

    public void setMagic(int magic) {
        this.magic = magic;
    }

    public void setUsedBytesInLastPage(int usedBytesInLastPage) {
        this.usedBytesInLastPage = usedBytesInLastPage;
    }

    public void setFileSizeInPages(int fileSizeInPages) {
        this.fileSizeInPages = fileSizeInPages;
    }

    public void setNumRelocationItems(int numRelocationItems) {
        this.numRelocationItems = numRelocationItems;
    }

    public void setHeaderSizeInParagraphs(int headerSizeInParagraphs) {
        this.headerSizeInParagraphs = headerSizeInParagraphs;
    }

    public void setMinExtraParagraphs(int minExtraParagraphs) {
        this.minExtraParagraphs = minExtraParagraphs;
    }

    public void setMaxExtraParagraphs(int maxExtraParagraphs) {
        this.maxExtraParagraphs = maxExtraParagraphs;
    }

    public void setInitialSS(int initialSS) {
        this.initialSS = initialSS;
    }

    public void setInitialSP(int initialSP) {
        this.initialSP = initialSP;
    }

    public void setChecksum(int checksum) {
        this.checksum = checksum;
    }

    public void setInitialIP(int initialIP) {
        this.initialIP = initialIP;
    }

    public void setInitialRelativeCS(int initialRelativeCS) {
        this.initialRelativeCS = initialRelativeCS;
    }

    public void setAddressOfRelocationTable(int addressOfRelocationTable) {
        this.addressOfRelocationTable = addressOfRelocationTable;
    }

    public void setOverlayNumber(int overlayNumber) {
        this.overlayNumber = overlayNumber;
    }

    public void setReserved(int[] reserved) {
        this.reserved = reserved;
    }

    public void setReserved2(int[] reserved2) {
        this.reserved2 = reserved2;
    }

    public void setOemId(int oemId) {
        this.oemId = oemId;
    }

    public void setOemInfo(int oemInfo) {
        this.oemInfo = oemInfo;
    }

    public void setAddressOfNewExeHeader(int addressOfNewExeHeader) {
        this.addressOfNewExeHeader = addressOfNewExeHeader;
    }

    public void setStubSize(int stubSize) {
        this.stubSize = stubSize;
    }
}

