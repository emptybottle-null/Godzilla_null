/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.github.javaparser.metamodel;

import com.github.javaparser.ast.stmt.ExpressionStmt;
import com.github.javaparser.metamodel.BaseNodeMetaModel;
import com.github.javaparser.metamodel.PropertyMetaModel;
import com.github.javaparser.metamodel.StatementMetaModel;
import java.util.Optional;

public class ExpressionStmtMetaModel
extends StatementMetaModel {
    public PropertyMetaModel expressionPropertyMetaModel;

    ExpressionStmtMetaModel(Optional<BaseNodeMetaModel> superBaseNodeMetaModel) {
        super(superBaseNodeMetaModel, ExpressionStmt.class, "ExpressionStmt", "com.github.javaparser.ast.stmt", false, false);
    }
}

