/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.fife.rsta.ac.common;

import java.util.ArrayList;
import java.util.List;
import org.fife.rsta.ac.common.VariableDeclaration;

public class CodeBlock {
    private int start;
    private int end;
    private CodeBlock parent;
    private List<CodeBlock> children;
    private List<VariableDeclaration> varDecs;

    public CodeBlock(int start) {
        this.start = start;
        this.end = Integer.MAX_VALUE;
    }

    public CodeBlock addChildCodeBlock(int start) {
        CodeBlock child = new CodeBlock(start);
        child.parent = this;
        if (this.children == null) {
            this.children = new ArrayList<CodeBlock>();
        }
        this.children.add(child);
        return child;
    }

    public void addVariable(VariableDeclaration varDec) {
        if (this.varDecs == null) {
            this.varDecs = new ArrayList<VariableDeclaration>();
        }
        this.varDecs.add(varDec);
    }

    public boolean contains(int offset) {
        return offset >= this.start && offset < this.end;
    }

    public CodeBlock getChildCodeBlock(int index) {
        return this.children.get(index);
    }

    public int getChildCodeBlockCount() {
        return this.children == null ? 0 : this.children.size();
    }

    public CodeBlock getDeepestCodeBlockContaining(int offs) {
        if (!this.contains(offs)) {
            return null;
        }
        for (int i = 0; i < this.getChildCodeBlockCount(); ++i) {
            CodeBlock child = this.getChildCodeBlock(i);
            if (!child.contains(offs)) continue;
            return child.getDeepestCodeBlockContaining(offs);
        }
        return this;
    }

    public int getEndOffset() {
        return this.end;
    }

    public CodeBlock getParent() {
        return this.parent;
    }

    public int getStartOffset() {
        return this.start;
    }

    public VariableDeclaration getVariableDeclaration(int index) {
        return this.varDecs.get(index);
    }

    public int getVariableDeclarationCount() {
        return this.varDecs == null ? 0 : this.varDecs.size();
    }

    public List<VariableDeclaration> getVariableDeclarationsBefore(int offs) {
        VariableDeclaration localVar;
        ArrayList<VariableDeclaration> vars = new ArrayList<VariableDeclaration>();
        int varCount = this.getVariableDeclarationCount();
        for (int i = 0; i < varCount && (localVar = this.getVariableDeclaration(i)).getOffset() < offs; ++i) {
            vars.add(localVar);
        }
        if (this.parent != null) {
            vars.addAll(this.parent.getVariableDeclarationsBefore(offs));
        }
        return vars;
    }

    public void setEndOffset(int end) {
        this.end = end;
    }
}

