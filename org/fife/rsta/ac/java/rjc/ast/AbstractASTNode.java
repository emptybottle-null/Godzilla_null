/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.fife.rsta.ac.java.rjc.ast;

import org.fife.rsta.ac.java.rjc.ast.ASTNode;
import org.fife.rsta.ac.java.rjc.lexer.Offset;

abstract class AbstractASTNode
implements ASTNode {
    private String name;
    private Offset startOffs;
    private Offset endOffs;

    protected AbstractASTNode(String name, Offset start) {
        this(name, start, null);
    }

    protected AbstractASTNode(String name, Offset start, Offset end) {
        this.name = name;
        this.startOffs = start;
        this.endOffs = end;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public int getNameEndOffset() {
        return this.endOffs != null ? this.endOffs.getOffset() : Integer.MAX_VALUE;
    }

    @Override
    public int getNameStartOffset() {
        return this.startOffs != null ? this.startOffs.getOffset() : 0;
    }

    public void setDeclarationEndOffset(Offset end) {
        this.endOffs = end;
    }

    protected void setDeclarationOffsets(Offset start, Offset end) {
        this.startOffs = start;
        this.endOffs = end;
    }

    public String toString() {
        return this.getName();
    }
}

