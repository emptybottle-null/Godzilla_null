/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.fife.rsta.ac.java.rjc.lang;

import org.fife.rsta.ac.java.rjc.lang.Type;
import org.fife.rsta.ac.java.rjc.lexer.Token;

public abstract class Variable {
    private boolean isFinal;
    private Type type;
    private Token name;

    public Variable(boolean isFinal, Type type, Token name) {
        this.isFinal = isFinal;
        this.type = type;
        this.name = name;
    }

    public String getName() {
        return this.name.getLexeme();
    }

    public Type getType() {
        return this.type;
    }

    public boolean isFinal() {
        return this.isFinal;
    }
}

