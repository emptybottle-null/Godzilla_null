/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.intellij.uiDesigner.compiler;

public class FormErrorInfo {
    private String myComponentId;
    private String myErrorMessage;

    public FormErrorInfo(String componentId, String errorMessage) {
        this.myComponentId = componentId;
        this.myErrorMessage = errorMessage;
    }

    public String getComponentId() {
        return this.myComponentId;
    }

    public void setComponentId(String componentId) {
        this.myComponentId = componentId;
    }

    public String getErrorMessage() {
        return this.myErrorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.myErrorMessage = errorMessage;
    }
}

