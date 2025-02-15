/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.github.javaparser.ast.nodeTypes;

import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.stmt.BlockStmt;

public interface NodeWithBlockStmt<N extends Node> {
    public BlockStmt getBody();

    public N setBody(BlockStmt var1);

    default public BlockStmt createBody() {
        BlockStmt block = new BlockStmt();
        this.setBody(block);
        return block;
    }
}

