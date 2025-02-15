/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.github.javaparser.metamodel;

import com.github.javaparser.ast.stmt.ForEachStmt;
import com.github.javaparser.metamodel.BaseNodeMetaModel;
import com.github.javaparser.metamodel.PropertyMetaModel;
import com.github.javaparser.metamodel.StatementMetaModel;
import java.util.Optional;

public class ForEachStmtMetaModel
extends StatementMetaModel {
    public PropertyMetaModel bodyPropertyMetaModel;
    public PropertyMetaModel iterablePropertyMetaModel;
    public PropertyMetaModel variablePropertyMetaModel;

    ForEachStmtMetaModel(Optional<BaseNodeMetaModel> superBaseNodeMetaModel) {
        super(superBaseNodeMetaModel, ForEachStmt.class, "ForEachStmt", "com.github.javaparser.ast.stmt", false, false);
    }
}

