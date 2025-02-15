/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.github.javaparser.metamodel;

import com.github.javaparser.ast.stmt.BreakStmt;
import com.github.javaparser.metamodel.BaseNodeMetaModel;
import com.github.javaparser.metamodel.PropertyMetaModel;
import com.github.javaparser.metamodel.StatementMetaModel;
import java.util.Optional;

public class BreakStmtMetaModel
extends StatementMetaModel {
    public PropertyMetaModel labelPropertyMetaModel;

    BreakStmtMetaModel(Optional<BaseNodeMetaModel> superBaseNodeMetaModel) {
        super(superBaseNodeMetaModel, BreakStmt.class, "BreakStmt", "com.github.javaparser.ast.stmt", false, false);
    }
}

