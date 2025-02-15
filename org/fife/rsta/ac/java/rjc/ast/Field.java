/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.fife.rsta.ac.java.rjc.ast;

import org.fife.rsta.ac.java.rjc.ast.AbstractMember;
import org.fife.rsta.ac.java.rjc.lang.Modifiers;
import org.fife.rsta.ac.java.rjc.lang.Type;
import org.fife.rsta.ac.java.rjc.lexer.Scanner;
import org.fife.rsta.ac.java.rjc.lexer.Token;

public class Field
extends AbstractMember {
    private Modifiers modifiers;
    private Type type;
    private boolean deprecated;
    private String docComment;

    public Field(Scanner s, Modifiers modifiers, Type type, Token t) {
        super(t.getLexeme(), s.createOffset(t.getOffset()));
        this.setDeclarationEndOffset(s.createOffset(t.getOffset() + t.getLength()));
        if (modifiers == null) {
            modifiers = new Modifiers();
        }
        this.modifiers = modifiers;
        this.type = type;
    }

    @Override
    public String getDocComment() {
        return this.docComment;
    }

    @Override
    public Modifiers getModifiers() {
        return this.modifiers;
    }

    @Override
    public Type getType() {
        return this.type;
    }

    @Override
    public boolean isDeprecated() {
        return this.deprecated;
    }

    public void setDeprecated(boolean deprecated) {
        this.deprecated = deprecated;
    }

    public void setDocComment(String comment) {
        this.docComment = comment;
    }
}

