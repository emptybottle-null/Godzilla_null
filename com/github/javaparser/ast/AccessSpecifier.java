/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.github.javaparser.ast;

public enum AccessSpecifier {
    PUBLIC("public"),
    PRIVATE("private"),
    PROTECTED("protected"),
    PACKAGE_PRIVATE("");

    private String codeRepresenation;

    private AccessSpecifier(String codeRepresentation) {
        this.codeRepresenation = codeRepresentation;
    }

    public String asString() {
        return this.codeRepresenation;
    }
}

