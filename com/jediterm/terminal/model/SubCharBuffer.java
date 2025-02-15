/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.jediterm.terminal.model;

import com.jediterm.terminal.model.CharBuffer;
import org.jetbrains.annotations.NotNull;

public class SubCharBuffer
extends CharBuffer {
    private final CharBuffer myParent;
    private final int myOffset;

    public SubCharBuffer(@NotNull CharBuffer parent, int offset, int length) {
        if (parent == null) {
            SubCharBuffer.$$$reportNull$$$0(0);
        }
        super(parent.getBuf(), parent.getStart() + offset, length);
        this.myParent = parent;
        this.myOffset = offset;
    }

    @NotNull
    public CharBuffer getParent() {
        CharBuffer charBuffer = this.myParent;
        if (charBuffer == null) {
            SubCharBuffer.$$$reportNull$$$0(1);
        }
        return charBuffer;
    }

    public int getOffset() {
        return this.myOffset;
    }

    private static /* synthetic */ void $$$reportNull$$$0(int n) {
        RuntimeException runtimeException;
        Object[] objectArray;
        Object[] objectArray2;
        int n2;
        String string;
        switch (n) {
            default: {
                string = "Argument for @NotNull parameter '%s' of %s.%s must not be null";
                break;
            }
            case 1: {
                string = "@NotNull method %s.%s must not return null";
                break;
            }
        }
        switch (n) {
            default: {
                n2 = 3;
                break;
            }
            case 1: {
                n2 = 2;
                break;
            }
        }
        Object[] objectArray3 = new Object[n2];
        switch (n) {
            default: {
                objectArray2 = objectArray3;
                objectArray3[0] = "parent";
                break;
            }
            case 1: {
                objectArray2 = objectArray3;
                objectArray3[0] = "com/jediterm/terminal/model/SubCharBuffer";
                break;
            }
        }
        switch (n) {
            default: {
                objectArray = objectArray2;
                objectArray2[1] = "com/jediterm/terminal/model/SubCharBuffer";
                break;
            }
            case 1: {
                objectArray = objectArray2;
                objectArray2[1] = "getParent";
                break;
            }
        }
        switch (n) {
            default: {
                objectArray = objectArray;
                objectArray[2] = "<init>";
                break;
            }
            case 1: {
                break;
            }
        }
        String string2 = String.format(string, objectArray);
        switch (n) {
            default: {
                runtimeException = new IllegalArgumentException(string2);
                break;
            }
            case 1: {
                runtimeException = new IllegalStateException(string2);
                break;
            }
        }
        throw runtimeException;
    }
}

