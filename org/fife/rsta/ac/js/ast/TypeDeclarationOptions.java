/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.fife.rsta.ac.js.ast;

public class TypeDeclarationOptions {
    private String ownerScriptName;
    private boolean supportsLinks;
    private boolean preProcessing;

    public TypeDeclarationOptions(String ownerScriptName, boolean supportsLinks, boolean preProcessing) {
        this.ownerScriptName = ownerScriptName;
        this.supportsLinks = supportsLinks;
        this.preProcessing = preProcessing;
    }

    public String getOwnerScriptName() {
        return this.ownerScriptName;
    }

    public void setOwnerScriptName(String ownerScriptName) {
        this.ownerScriptName = ownerScriptName;
    }

    public boolean isSupportsLinks() {
        return this.supportsLinks;
    }

    public void setSupportsLinks(boolean supportsLinks) {
        this.supportsLinks = supportsLinks;
    }

    public boolean isPreProcessing() {
        return this.preProcessing;
    }

    public void setPreProcessing(boolean preProcessing) {
        this.preProcessing = preProcessing;
    }
}

