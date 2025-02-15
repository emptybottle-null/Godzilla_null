/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.formdev.flatlaf.demo.intellijthemes;

import java.io.File;
import java.io.Serializable;

public class IJThemeInfo
implements Serializable {
    protected String name;
    protected String resourceName;
    protected boolean dark;
    protected String license;
    protected String licenseFile;
    protected String sourceCodeUrl;
    protected String sourceCodePath;
    protected File themeFile;
    protected String lafClassName;

    public IJThemeInfo(String name, String resourceName, boolean dark, String license, String licenseFile, String sourceCodeUrl, String sourceCodePath, File themeFile, String lafClassName) {
        this.name = name;
        this.resourceName = resourceName;
        this.dark = dark;
        this.license = license;
        this.licenseFile = licenseFile;
        this.sourceCodeUrl = sourceCodeUrl;
        this.sourceCodePath = sourceCodePath;
        this.themeFile = themeFile;
        this.lafClassName = lafClassName;
    }

    public IJThemeInfo(String resourceName, String lafClassName) {
        this.resourceName = resourceName;
        this.lafClassName = lafClassName;
    }

    public String getResourceName() {
        return this.resourceName;
    }

    public void setResourceName(String resourceName) {
        this.resourceName = resourceName;
    }

    public String getLafClassName() {
        return this.lafClassName;
    }

    public void setLafClassName(String lafClassName) {
        this.lafClassName = lafClassName;
    }

    public String toString() {
        return "IJThemeInfo{name='" + this.name + '\'' + ", resourceName='" + this.resourceName + '\'' + ", dark=" + this.dark + ", license='" + this.license + '\'' + ", licenseFile='" + this.licenseFile + '\'' + ", sourceCodeUrl='" + this.sourceCodeUrl + '\'' + ", sourceCodePath='" + this.sourceCodePath + '\'' + ", themeFile=" + this.themeFile + ", lafClassName='" + this.lafClassName + '\'' + '}';
    }
}

