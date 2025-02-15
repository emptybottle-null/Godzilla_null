/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.kichik.pecoff4j;

import com.kichik.pecoff4j.ImageDataDirectory;

public class OptionalHeader {
    public static final int MAGIC_PE32 = 267;
    public static final int MAGIC_PE32plus = 523;
    private int magic;
    private int majorLinkerVersion;
    private int minorLinkerVersion;
    private int sizeOfCode;
    private int sizeOfInitializedData;
    private int sizeOfUninitializedData;
    private int addressOfEntryPoint;
    private int baseOfCode;
    private int baseOfData;
    private long imageBase;
    private int sectionAlignment;
    private int fileAlignment;
    private int majorOperatingSystemVersion;
    private int minorOperatingSystemVersion;
    private int majorImageVersion;
    private int minorImageVersion;
    private int majorSubsystemVersion;
    private int minorSubsystemVersion;
    private int win32VersionValue;
    private int sizeOfImage;
    private int sizeOfHeaders;
    private int checkSum;
    private int subsystem;
    private int dllCharacteristics;
    private long sizeOfStackReserve;
    private long sizeOfStackCommit;
    private long sizeOfHeapReserve;
    private long sizeOfHeapCommit;
    private int loaderFlags;
    private int numberOfRvaAndSizes;
    private ImageDataDirectory[] dataDirectories;

    public int getMagic() {
        return this.magic;
    }

    public boolean isValid() {
        return this.magic == 267 || this.magic == 523;
    }

    public boolean isPE32plus() {
        return this.magic == 523;
    }

    public int getMajorLinkerVersion() {
        return this.majorLinkerVersion;
    }

    public int getMinorLinkerVersion() {
        return this.minorLinkerVersion;
    }

    public int getSizeOfCode() {
        return this.sizeOfCode;
    }

    public int getSizeOfInitializedData() {
        return this.sizeOfInitializedData;
    }

    public int getSizeOfUninitializedData() {
        return this.sizeOfUninitializedData;
    }

    public int getAddressOfEntryPoint() {
        return this.addressOfEntryPoint;
    }

    public int getBaseOfCode() {
        return this.baseOfCode;
    }

    public int getBaseOfData() {
        return this.baseOfData;
    }

    public long getImageBase() {
        return this.imageBase;
    }

    public int getSectionAlignment() {
        return this.sectionAlignment;
    }

    public int getFileAlignment() {
        return this.fileAlignment;
    }

    public int getMajorOperatingSystemVersion() {
        return this.majorOperatingSystemVersion;
    }

    public int getMinorOperatingSystemVersion() {
        return this.minorOperatingSystemVersion;
    }

    public int getMajorImageVersion() {
        return this.majorImageVersion;
    }

    public int getMinorImageVersion() {
        return this.minorImageVersion;
    }

    public int getMajorSubsystemVersion() {
        return this.majorSubsystemVersion;
    }

    public int getMinorSubsystemVersion() {
        return this.minorSubsystemVersion;
    }

    public int getWin32VersionValue() {
        return this.win32VersionValue;
    }

    public int getSizeOfImage() {
        return this.sizeOfImage;
    }

    public int getSizeOfHeaders() {
        return this.sizeOfHeaders;
    }

    public int getCheckSum() {
        return this.checkSum;
    }

    public int getSubsystem() {
        return this.subsystem;
    }

    public int getDllCharacteristics() {
        return this.dllCharacteristics;
    }

    public long getSizeOfStackReserve() {
        return this.sizeOfStackReserve;
    }

    public long getSizeOfStackCommit() {
        return this.sizeOfStackCommit;
    }

    public long getSizeOfHeapReserve() {
        return this.sizeOfHeapReserve;
    }

    public long getSizeOfHeapCommit() {
        return this.sizeOfHeapCommit;
    }

    public int getLoaderFlags() {
        return this.loaderFlags;
    }

    public int getNumberOfRvaAndSizes() {
        return this.numberOfRvaAndSizes;
    }

    public void setMagic(int magic) {
        this.magic = magic;
    }

    public void setMajorLinkerVersion(int majorLinkerVersion) {
        this.majorLinkerVersion = majorLinkerVersion;
    }

    public void setMinorLinkerVersion(int minorLinkerVersion) {
        this.minorLinkerVersion = minorLinkerVersion;
    }

    public void setSizeOfCode(int sizeOfCode) {
        this.sizeOfCode = sizeOfCode;
    }

    public void setSizeOfInitializedData(int sizeOfInitializedData) {
        this.sizeOfInitializedData = sizeOfInitializedData;
    }

    public void setSizeOfUninitializedData(int sizeOfUninitializedData) {
        this.sizeOfUninitializedData = sizeOfUninitializedData;
    }

    public void setAddressOfEntryPoint(int addressOfEntryPoint) {
        this.addressOfEntryPoint = addressOfEntryPoint;
    }

    public void setBaseOfCode(int baseOfCode) {
        this.baseOfCode = baseOfCode;
    }

    public void setBaseOfData(int baseOfData) {
        this.baseOfData = baseOfData;
    }

    public void setImageBase(long imageBase) {
        this.imageBase = imageBase;
    }

    public void setSectionAlignment(int sectionAlignment) {
        this.sectionAlignment = sectionAlignment;
    }

    public void setFileAlignment(int fileAlignment) {
        this.fileAlignment = fileAlignment;
    }

    public void setMajorOperatingSystemVersion(int majorOperatingSystemVersion) {
        this.majorOperatingSystemVersion = majorOperatingSystemVersion;
    }

    public void setMinorOperatingSystemVersion(int minorOperatingSystemVersion) {
        this.minorOperatingSystemVersion = minorOperatingSystemVersion;
    }

    public void setMajorImageVersion(int majorImageVersion) {
        this.majorImageVersion = majorImageVersion;
    }

    public void setMinorImageVersion(int minorImageVersion) {
        this.minorImageVersion = minorImageVersion;
    }

    public void setMajorSubsystemVersion(int majorSubsystemVersion) {
        this.majorSubsystemVersion = majorSubsystemVersion;
    }

    public void setMinorSubsystemVersion(int minorSubsystemVersion) {
        this.minorSubsystemVersion = minorSubsystemVersion;
    }

    public void setWin32VersionValue(int win32VersionValue) {
        this.win32VersionValue = win32VersionValue;
    }

    public void setSizeOfImage(int sizeOfImage) {
        this.sizeOfImage = sizeOfImage;
    }

    public void setSizeOfHeaders(int sizeOfHeaders) {
        this.sizeOfHeaders = sizeOfHeaders;
    }

    public void setCheckSum(int checkSum) {
        this.checkSum = checkSum;
    }

    public void setSubsystem(int subsystem) {
        this.subsystem = subsystem;
    }

    public void setDllCharacteristics(int dllCharacteristics) {
        this.dllCharacteristics = dllCharacteristics;
    }

    public void setSizeOfStackReserve(long sizeOfStackReserve) {
        this.sizeOfStackReserve = sizeOfStackReserve;
    }

    public void setSizeOfStackCommit(long sizeOfStackCommit) {
        this.sizeOfStackCommit = sizeOfStackCommit;
    }

    public void setSizeOfHeapReserve(long sizeOfHeapReserve) {
        this.sizeOfHeapReserve = sizeOfHeapReserve;
    }

    public void setSizeOfHeapCommit(long sizeOfHeapCommit) {
        this.sizeOfHeapCommit = sizeOfHeapCommit;
    }

    public void setLoaderFlags(int loaderFlags) {
        this.loaderFlags = loaderFlags;
    }

    public void setNumberOfRvaAndSizes(int numberOfRvaAndSizes) {
        this.numberOfRvaAndSizes = numberOfRvaAndSizes;
    }

    public int getDataDirectoryCount() {
        return this.dataDirectories.length;
    }

    public ImageDataDirectory[] getDataDirectories() {
        return this.dataDirectories;
    }

    public ImageDataDirectory getDataDirectory(int index) {
        return this.dataDirectories[index];
    }

    public void setDataDirectories(ImageDataDirectory[] dataDirectories) {
        this.dataDirectories = dataDirectories;
    }
}

