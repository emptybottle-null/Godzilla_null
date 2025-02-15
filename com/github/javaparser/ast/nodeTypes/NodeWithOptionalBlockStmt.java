/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.github.javaparser.ast.nodeTypes;

import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.stmt.BlockStmt;
import java.util.Optional;

public interface NodeWithOptionalBlockStmt<N extends Node> {
    public Optional<BlockStmt> getBody();

    public N setBody(BlockStmt var1);

    public N removeBody();

    default public BlockStmt createBody() {
        BlockStmt block = new BlockStmt();
        this.setBody(block);
        return block;
    }
}

