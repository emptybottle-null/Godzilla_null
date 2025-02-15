/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.jediterm.terminal.emulator.charset;

import com.jediterm.terminal.emulator.charset.CharacterSet;
import org.jetbrains.annotations.NotNull;

public class GraphicSet {
    private final int myIndex;
    private CharacterSet myDesignation;

    public GraphicSet(int index) {
        if (index < 0 || index > 3) {
            throw new IllegalArgumentException("Invalid index!");
        }
        this.myIndex = index;
        this.myDesignation = CharacterSet.valueOf(index == 1 ? (char)'0' : 'B');
    }

    public CharacterSet getDesignation() {
        return this.myDesignation;
    }

    public int getIndex() {
        return this.myIndex;
    }

    public int map(char original, int index) {
        int result = this.myDesignation.map(index);
        if (result < 0) {
            result = original;
        }
        return result;
    }

    public void setDesignation(@NotNull CharacterSet designation) {
        if (designation == null) {
            GraphicSet.$$$reportNull$$$0(0);
        }
        this.myDesignation = designation;
    }

    private static /* synthetic */ void $$$reportNull$$$0(int n) {
        throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "designation", "com/jediterm/terminal/emulator/charset/GraphicSet", "setDesignation"));
    }
}

