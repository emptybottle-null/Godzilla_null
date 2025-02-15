/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.fife.rsta.ac.java.rjc.ast;

import org.fife.rsta.ac.java.rjc.ast.ASTNode;
import org.fife.rsta.ac.java.rjc.ast.TypeDeclaration;
import org.fife.rsta.ac.java.rjc.lang.Modifiers;
import org.fife.rsta.ac.java.rjc.lang.Type;

public interface Member
extends ASTNode {
    public String getDocComment();

    @Override
    public int getNameEndOffset();

    @Override
    public int getNameStartOffset();

    public Modifiers getModifiers();

    @Override
    public String getName();

    public TypeDeclaration getParentTypeDeclaration();

    public Type getType();

    public boolean isDeprecated();

    public boolean isStatic();

    public void setParentTypeDeclaration(TypeDeclaration var1);
}

