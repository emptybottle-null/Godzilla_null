/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.github.javaparser.metamodel;

import com.github.javaparser.ast.stmt.LabeledStmt;
import com.github.javaparser.metamodel.BaseNodeMetaModel;
import com.github.javaparser.metamodel.PropertyMetaModel;
import com.github.javaparser.metamodel.StatementMetaModel;
import java.util.Optional;

public class LabeledStmtMetaModel
extends StatementMetaModel {
    public PropertyMetaModel labelPropertyMetaModel;
    public PropertyMetaModel statementPropertyMetaModel;

    LabeledStmtMetaModel(Optional<BaseNodeMetaModel> superBaseNodeMetaModel) {
        super(superBaseNodeMetaModel, LabeledStmt.class, "LabeledStmt", "com.github.javaparser.ast.stmt", false, false);
    }
}

