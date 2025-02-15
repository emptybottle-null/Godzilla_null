/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.fife.rsta.ac.js.ast;

import java.util.ArrayList;
import java.util.List;
import org.fife.rsta.ac.js.ast.JavaScriptVariableDeclaration;

public class CodeBlock {
    private int start;
    private int end;
    private CodeBlock parent;
    private List<CodeBlock> children;
    private List<JavaScriptVariableDeclaration> varDecs;

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

    public void addVariable(JavaScriptVariableDeclaration varDec) {
        if (this.varDecs == null) {
            this.varDecs = new ArrayList<JavaScriptVariableDeclaration>();
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

    public int getEndOffset() {
        return this.end;
    }

    public CodeBlock getParent() {
        return this.parent;
    }

    public int getStartOffset() {
        return this.start;
    }

    public JavaScriptVariableDeclaration getVariableDeclaration(int index) {
        return this.varDecs.get(index);
    }

    public int getVariableDeclarationCount() {
        return this.varDecs == null ? 0 : this.varDecs.size();
    }

    public void setEndOffset(int end) {
        this.end = end;
    }

    public void setStartOffSet(int start) {
        this.start = start;
    }

    public void debug() {
        StringBuilder sb = new StringBuilder();
        this.outputChild(sb, this, 0);
        System.out.println(sb.toString());
    }

    private void outputChild(StringBuilder sb, CodeBlock block, int tab) {
        int i;
        String tabs = "";
        for (i = 0; i < tab; ++i) {
            tabs = tabs + "\t";
        }
        sb.append(tabs);
        sb.append("start: " + block.getStartOffset() + "\n");
        sb.append(tabs);
        sb.append("end: " + block.getEndOffset() + "\n");
        sb.append(tabs);
        sb.append("var count: " + block.getVariableDeclarationCount() + "\n\n");
        for (i = 0; i < block.getChildCodeBlockCount(); ++i) {
            CodeBlock childBlock = block.getChildCodeBlock(i);
            this.outputChild(sb, childBlock, tab++);
        }
    }
}

