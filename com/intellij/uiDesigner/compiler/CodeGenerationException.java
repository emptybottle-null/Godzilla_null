/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.intellij.uiDesigner.compiler;

public class CodeGenerationException
extends Exception {
    private String myComponentId;

    public CodeGenerationException(String componentId, String message) {
        super(message);
        this.myComponentId = componentId;
    }

    public String getComponentId() {
        return this.myComponentId;
    }
}

