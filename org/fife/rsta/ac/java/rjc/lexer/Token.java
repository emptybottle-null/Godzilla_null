/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.fife.rsta.ac.java.rjc.lexer;

import org.fife.rsta.ac.java.rjc.lexer.TokenTypes;

public interface Token
extends TokenTypes {
    public int getColumn();

    public String getLexeme();

    public int getLength();

    public int getLine();

    public int getOffset();

    public int getType();

    public boolean isBasicType();

    public boolean isIdentifier();

    public boolean isInvalid();

    public boolean isOperator();

    public boolean isType(int var1);
}

