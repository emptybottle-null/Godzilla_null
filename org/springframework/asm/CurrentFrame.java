/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.springframework.asm;

import org.springframework.asm.Frame;
import org.springframework.asm.Label;
import org.springframework.asm.Symbol;
import org.springframework.asm.SymbolTable;

final class CurrentFrame
extends Frame {
    CurrentFrame(Label owner) {
        super(owner);
    }

    @Override
    void execute(int opcode, int arg, Symbol symbolArg, SymbolTable symbolTable) {
        super.execute(opcode, arg, symbolArg, symbolTable);
        Frame successor = new Frame(null);
        this.merge(symbolTable, successor, 0);
        this.copyFrom(successor);
    }
}

