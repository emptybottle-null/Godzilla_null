/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.fife.rsta.ac.js.ast;

import org.fife.ui.autocomplete.FunctionCompletion;

public class FunctionDeclaration {
    private FunctionCompletion fc;
    private int offset;

    public FunctionDeclaration(FunctionCompletion fc, int offset) {
        this.fc = fc;
        this.offset = offset;
    }

    public FunctionCompletion getFunction() {
        return this.fc;
    }

    public int getOffset() {
        return this.offset;
    }
}

