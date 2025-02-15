/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.kitfox.svg.animation.parser;

import java.io.Serializable;

public class Token
implements Serializable {
    private static final long serialVersionUID = 1L;
    public int kind;
    public int beginLine;
    public int beginColumn;
    public int endLine;
    public int endColumn;
    public String image;
    public Token next;
    public Token specialToken;

    public Token() {
    }

    public Token(int nKind) {
        this(nKind, null);
    }

    public Token(int nKind, String sImage) {
        this.kind = nKind;
        this.image = sImage;
    }

    public Object getValue() {
        return null;
    }

    public String toString() {
        return this.image;
    }

    public static Token newToken(int ofKind, String image) {
        switch (ofKind) {
            default: 
        }
        return new Token(ofKind, image);
    }

    public static Token newToken(int ofKind) {
        return Token.newToken(ofKind, null);
    }
}

