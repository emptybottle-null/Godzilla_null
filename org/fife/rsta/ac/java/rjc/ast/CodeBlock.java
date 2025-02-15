/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.fife.rsta.ac.java.rjc.ast;

import java.util.ArrayList;
import java.util.List;
import org.fife.rsta.ac.java.rjc.ast.AbstractMember;
import org.fife.rsta.ac.java.rjc.ast.LocalVariable;
import org.fife.rsta.ac.java.rjc.lang.Modifiers;
import org.fife.rsta.ac.java.rjc.lang.Type;
import org.fife.rsta.ac.java.rjc.lexer.Offset;

public class CodeBlock
extends AbstractMember {
    public static final String NAME = "{...}";
    private CodeBlock parent;
    private List<CodeBlock> children;
    private List<LocalVariable> localVars;
    private boolean isStatic;

    public CodeBlock(boolean isStatic, Offset startOffs) {
        super(NAME, startOffs);
        this.isStatic = isStatic;
    }

    public void add(CodeBlock child) {
        if (this.children == null) {
            this.children = new ArrayList<CodeBlock>();
        }
        this.children.add(child);
        child.setParent(this);
    }

    public void addLocalVariable(LocalVariable localVar) {
        if (this.localVars == null) {
            this.localVars = new ArrayList<LocalVariable>();
        }
        this.localVars.add(localVar);
    }

    public boolean containsOffset(int offs) {
        return this.getNameEndOffset() >= offs && this.getNameStartOffset() <= offs;
    }

    public CodeBlock getChildBlock(int index) {
        return this.children.get(index);
    }

    public int getChildBlockCount() {
        return this.children == null ? 0 : this.children.size();
    }

    public CodeBlock getDeepestCodeBlockContaining(int offs) {
        if (!this.containsOffset(offs)) {
            return null;
        }
        for (int i = 0; i < this.getChildBlockCount(); ++i) {
            CodeBlock child = this.getChildBlock(i);
            if (!child.containsOffset(offs)) continue;
            return child.getDeepestCodeBlockContaining(offs);
        }
        return this;
    }

    @Override
    public String getDocComment() {
        return null;
    }

    public LocalVariable getLocalVar(int index) {
        return this.localVars.get(index);
    }

    public int getLocalVarCount() {
        return this.localVars == null ? 0 : this.localVars.size();
    }

    public List<LocalVariable> getLocalVarsBefore(int offs) {
        ArrayList<LocalVariable> vars = new ArrayList<LocalVariable>();
        if (this.localVars != null) {
            LocalVariable localVar;
            for (int i = 0; i < this.getLocalVarCount() && (localVar = this.getLocalVar(i)).getNameStartOffset() < offs; ++i) {
                vars.add(localVar);
            }
        }
        if (this.parent != null) {
            vars.addAll(this.parent.getLocalVarsBefore(offs));
        }
        return vars;
    }

    @Override
    public Modifiers getModifiers() {
        Modifiers modifiers = new Modifiers();
        if (this.isStatic) {
            modifiers.addModifier(65574);
        }
        return modifiers;
    }

    public CodeBlock getParent() {
        return this.parent;
    }

    @Override
    public Type getType() {
        return null;
    }

    @Override
    public boolean isDeprecated() {
        return false;
    }

    @Override
    public boolean isStatic() {
        return this.isStatic;
    }

    public void setParent(CodeBlock parent) {
        this.parent = parent;
    }
}

