/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.github.javaparser.metamodel;

import com.github.javaparser.ast.stmt.ReturnStmt;
import com.github.javaparser.metamodel.BaseNodeMetaModel;
import com.github.javaparser.metamodel.PropertyMetaModel;
import com.github.javaparser.metamodel.StatementMetaModel;
import java.util.Optional;

public class ReturnStmtMetaModel
extends StatementMetaModel {
    public PropertyMetaModel expressionPropertyMetaModel;

    ReturnStmtMetaModel(Optional<BaseNodeMetaModel> superBaseNodeMetaModel) {
        super(superBaseNodeMetaModel, ReturnStmt.class, "ReturnStmt", "com.github.javaparser.ast.stmt", false, false);
    }
}

