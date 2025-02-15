/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.fife.rsta.ac.java.rjc.ast;

import java.util.Iterator;
import java.util.List;
import org.fife.rsta.ac.java.rjc.ast.ASTNode;
import org.fife.rsta.ac.java.rjc.ast.Field;
import org.fife.rsta.ac.java.rjc.ast.Member;
import org.fife.rsta.ac.java.rjc.ast.Method;
import org.fife.rsta.ac.java.rjc.ast.Package;
import org.fife.rsta.ac.java.rjc.ast.TypeDeclarationContainer;
import org.fife.rsta.ac.java.rjc.lang.Modifiers;

public interface TypeDeclaration
extends ASTNode,
TypeDeclarationContainer {
    public boolean getBodyContainsOffset(int var1);

    public int getBodyEndOffset();

    public int getBodyStartOffset();

    public TypeDeclaration getChildType(int var1);

    public TypeDeclaration getChildTypeAtOffset(int var1);

    public int getChildTypeCount();

    public String getDocComment();

    public Iterator<Field> getFieldIterator();

    public Member getMember(int var1);

    public int getMemberCount();

    public Iterator<Member> getMemberIterator();

    public Iterator<Method> getMethodIterator();

    public List<Method> getMethodsByName(String var1);

    public Modifiers getModifiers();

    @Override
    public String getName();

    public String getName(boolean var1);

    public Package getPackage();

    public TypeDeclaration getParentType();

    public String getTypeString();

    public boolean isDeprecated();

    public boolean isStatic();

    public void setDocComment(String var1);

    public void setParentType(TypeDeclaration var1);
}

