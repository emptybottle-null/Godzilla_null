/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.fife.rsta.ac.java.rjc.notices;

import org.fife.rsta.ac.java.rjc.lexer.Token;

public class ParserNotice {
    private int line;
    private int column;
    private int length;
    private String message;

    public ParserNotice(Token t, String msg) {
        this.line = t.getLine();
        this.column = t.getColumn();
        this.length = t.getLexeme().length();
        this.message = msg;
    }

    public ParserNotice(int line, int column, int length, String message) {
        this.line = line;
        this.column = column;
        this.length = length;
        this.message = message;
    }

    public int getColumn() {
        return this.column;
    }

    public int getLength() {
        return this.length;
    }

    public int getLine() {
        return this.line;
    }

    public String getMessage() {
        return this.message;
    }

    public String toString() {
        return "(" + this.getLine() + ", " + this.getColumn() + ": " + this.getMessage();
    }
}

