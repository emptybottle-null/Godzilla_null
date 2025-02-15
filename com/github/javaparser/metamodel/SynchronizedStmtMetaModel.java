/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.github.javaparser.metamodel;

import com.github.javaparser.ast.stmt.SynchronizedStmt;
import com.github.javaparser.metamodel.BaseNodeMetaModel;
import com.github.javaparser.metamodel.PropertyMetaModel;
import com.github.javaparser.metamodel.StatementMetaModel;
import java.util.Optional;

public class SynchronizedStmtMetaModel
extends StatementMetaModel {
    public PropertyMetaModel bodyPropertyMetaModel;
    public PropertyMetaModel expressionPropertyMetaModel;

    SynchronizedStmtMetaModel(Optional<BaseNodeMetaModel> superBaseNodeMetaModel) {
        super(superBaseNodeMetaModel, SynchronizedStmt.class, "SynchronizedStmt", "com.github.javaparser.ast.stmt", false, false);
    }
}

