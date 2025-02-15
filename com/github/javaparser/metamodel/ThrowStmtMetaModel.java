/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.github.javaparser.metamodel;

import com.github.javaparser.ast.stmt.ThrowStmt;
import com.github.javaparser.metamodel.BaseNodeMetaModel;
import com.github.javaparser.metamodel.PropertyMetaModel;
import com.github.javaparser.metamodel.StatementMetaModel;
import java.util.Optional;

public class ThrowStmtMetaModel
extends StatementMetaModel {
    public PropertyMetaModel expressionPropertyMetaModel;

    ThrowStmtMetaModel(Optional<BaseNodeMetaModel> superBaseNodeMetaModel) {
        super(superBaseNodeMetaModel, ThrowStmt.class, "ThrowStmt", "com.github.javaparser.ast.stmt", false, false);
    }
}

