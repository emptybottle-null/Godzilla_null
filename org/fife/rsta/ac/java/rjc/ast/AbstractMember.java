/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.fife.rsta.ac.java.rjc.ast;

import org.fife.rsta.ac.java.rjc.ast.AbstractASTNode;
import org.fife.rsta.ac.java.rjc.ast.Member;
import org.fife.rsta.ac.java.rjc.ast.TypeDeclaration;
import org.fife.rsta.ac.java.rjc.lang.Modifiers;
import org.fife.rsta.ac.java.rjc.lexer.Offset;

abstract class AbstractMember
extends AbstractASTNode
implements Member {
    private TypeDeclaration parentTypeDec;

    protected AbstractMember(String name, Offset start) {
        super(name, start);
    }

    protected AbstractMember(String name, Offset start, Offset end) {
        super(name, start, end);
    }

    @Override
    public TypeDeclaration getParentTypeDeclaration() {
        return this.parentTypeDec;
    }

    @Override
    public boolean isStatic() {
        Modifiers modifiers = this.getModifiers();
        return modifiers != null && modifiers.isStatic();
    }

    @Override
    public void setParentTypeDeclaration(TypeDeclaration dec) {
        if (dec == null) {
            throw new InternalError("Parent TypeDeclaration cannot be null");
        }
        this.parentTypeDec = dec;
    }
}

