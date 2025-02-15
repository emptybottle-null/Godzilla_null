/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.kichik.pecoff4j;

public class BoundImport {
    private long timestamp;
    private int offsetToModuleName;
    private String moduleName;
    private int numModuleForwarderRefs;

    public long getTimestamp() {
        return this.timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public String getModuleName() {
        return this.moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    public int getNumberOfModuleForwarderRefs() {
        return this.numModuleForwarderRefs;
    }

    public void setNumberOfModuleForwarderRefs(int numModuleForwarderRefs) {
        this.numModuleForwarderRefs = numModuleForwarderRefs;
    }

    public int getOffsetToModuleName() {
        return this.offsetToModuleName;
    }

    public void setOffsetToModuleName(int offsetToModuleName) {
        this.offsetToModuleName = offsetToModuleName;
    }
}

