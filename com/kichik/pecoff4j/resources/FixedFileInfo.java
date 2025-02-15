/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.kichik.pecoff4j.resources;

public class FixedFileInfo {
    private int signature;
    private int strucVersion;
    private int fileVersionMS;
    private int fileVersionLS;
    private int productVersionMS;
    private int productVersionLS;
    private int fileFlagMask;
    private int fileFlags;
    private int fileOS;
    private int fileType;
    private int fileSubtype;
    private int fileDateMS;
    private int fileDateLS;

    public int getSignature() {
        return this.signature;
    }

    public void setSignature(int signature) {
        this.signature = signature;
    }

    public int getStrucVersion() {
        return this.strucVersion;
    }

    public void setStrucVersion(int strucVersion) {
        this.strucVersion = strucVersion;
    }

    public int getFileVersionMS() {
        return this.fileVersionMS;
    }

    public void setFileVersionMS(int fileVersionMS) {
        this.fileVersionMS = fileVersionMS;
    }

    public int getFileVersionLS() {
        return this.fileVersionLS;
    }

    public void setFileVersionLS(int fileVersionLS) {
        this.fileVersionLS = fileVersionLS;
    }

    public int getProductVersionMS() {
        return this.productVersionMS;
    }

    public void setProductVersionMS(int productVersionMS) {
        this.productVersionMS = productVersionMS;
    }

    public int getProductVersionLS() {
        return this.productVersionLS;
    }

    public void setProductVersionLS(int productVersionLS) {
        this.productVersionLS = productVersionLS;
    }

    public int getFileFlagMask() {
        return this.fileFlagMask;
    }

    public void setFileFlagMask(int fileFlagMask) {
        this.fileFlagMask = fileFlagMask;
    }

    public int getFileFlags() {
        return this.fileFlags;
    }

    public void setFileFlags(int fileFlags) {
        this.fileFlags = fileFlags;
    }

    public int getFileType() {
        return this.fileType;
    }

    public void setFileType(int fileType) {
        this.fileType = fileType;
    }

    public int getFileSubtype() {
        return this.fileSubtype;
    }

    public void setFileSubtype(int fileSubtype) {
        this.fileSubtype = fileSubtype;
    }

    public int getFileDateMS() {
        return this.fileDateMS;
    }

    public void setFileDateMS(int fileDateMS) {
        this.fileDateMS = fileDateMS;
    }

    public int getFileDateLS() {
        return this.fileDateLS;
    }

    public void setFileDateLS(int fileDateLS) {
        this.fileDateLS = fileDateLS;
    }

    public static int sizeOf() {
        return 52;
    }

    public int getFileOS() {
        return this.fileOS;
    }

    public void setFileOS(int fileOS) {
        this.fileOS = fileOS;
    }
}

