/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.springframework.cglib.core;

import org.springframework.asm.Label;
import org.springframework.cglib.core.CodeEmitter;

public class Block {
    private CodeEmitter e;
    private Label start;
    private Label end;

    public Block(CodeEmitter e) {
        this.e = e;
        this.start = e.mark();
    }

    public CodeEmitter getCodeEmitter() {
        return this.e;
    }

    public void end() {
        if (this.end != null) {
            throw new IllegalStateException("end of label already set");
        }
        this.end = this.e.mark();
    }

    public Label getStart() {
        return this.start;
    }

    public Label getEnd() {
        return this.end;
    }
}

