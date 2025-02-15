/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.github.javaparser.metamodel;

import com.github.javaparser.ast.stmt.TryStmt;
import com.github.javaparser.metamodel.BaseNodeMetaModel;
import com.github.javaparser.metamodel.PropertyMetaModel;
import com.github.javaparser.metamodel.StatementMetaModel;
import java.util.Optional;

public class TryStmtMetaModel
extends StatementMetaModel {
    public PropertyMetaModel catchClausesPropertyMetaModel;
    public PropertyMetaModel finallyBlockPropertyMetaModel;
    public PropertyMetaModel resourcesPropertyMetaModel;
    public PropertyMetaModel tryBlockPropertyMetaModel;

    TryStmtMetaModel(Optional<BaseNodeMetaModel> superBaseNodeMetaModel) {
        super(superBaseNodeMetaModel, TryStmt.class, "TryStmt", "com.github.javaparser.ast.stmt", false, false);
    }
}

