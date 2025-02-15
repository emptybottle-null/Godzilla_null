/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.github.javaparser.metamodel;

import com.github.javaparser.ast.stmt.ContinueStmt;
import com.github.javaparser.metamodel.BaseNodeMetaModel;
import com.github.javaparser.metamodel.PropertyMetaModel;
import com.github.javaparser.metamodel.StatementMetaModel;
import java.util.Optional;

public class ContinueStmtMetaModel
extends StatementMetaModel {
    public PropertyMetaModel labelPropertyMetaModel;

    ContinueStmtMetaModel(Optional<BaseNodeMetaModel> superBaseNodeMetaModel) {
        super(superBaseNodeMetaModel, ContinueStmt.class, "ContinueStmt", "com.github.javaparser.ast.stmt", false, false);
    }
}

