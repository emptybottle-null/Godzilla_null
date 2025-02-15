/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.github.javaparser.ast.nodeTypes;

import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.stmt.BlockStmt;
import com.github.javaparser.ast.stmt.Statement;

public interface NodeWithBody<N extends Node> {
    public Statement getBody();

    public N setBody(Statement var1);

    default public BlockStmt createBlockStatementAsBody() {
        BlockStmt b = new BlockStmt();
        this.setBody(b);
        return b;
    }

    default public boolean hasEmptyBody() {
        Statement body = this.getBody();
        return body.toBlockStmt().map(bs -> bs.isEmpty()).orElse(body.isEmptyStmt());
    }
}

