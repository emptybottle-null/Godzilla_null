/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.intellij.uiDesigner.compiler;

public class RecursiveFormNestingException
extends Exception {
    public RecursiveFormNestingException() {
        super("Recursive form nesting is not allowed");
    }
}

