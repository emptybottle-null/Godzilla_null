/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.github.javaparser.metamodel;

import com.github.javaparser.ast.stmt.BlockStmt;
import com.github.javaparser.metamodel.BaseNodeMetaModel;
import com.github.javaparser.metamodel.PropertyMetaModel;
import com.github.javaparser.metamodel.StatementMetaModel;
import java.util.Optional;

public class BlockStmtMetaModel
extends StatementMetaModel {
    public PropertyMetaModel statementsPropertyMetaModel;

    BlockStmtMetaModel(Optional<BaseNodeMetaModel> superBaseNodeMetaModel) {
        super(superBaseNodeMetaModel, BlockStmt.class, "BlockStmt", "com.github.javaparser.ast.stmt", false, false);
    }
}

