/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.github.javaparser.metamodel;

import com.github.javaparser.ast.stmt.YieldStmt;
import com.github.javaparser.metamodel.BaseNodeMetaModel;
import com.github.javaparser.metamodel.PropertyMetaModel;
import com.github.javaparser.metamodel.StatementMetaModel;
import java.util.Optional;

public class YieldStmtMetaModel
extends StatementMetaModel {
    public PropertyMetaModel expressionPropertyMetaModel;

    YieldStmtMetaModel(Optional<BaseNodeMetaModel> superBaseNodeMetaModel) {
        super(superBaseNodeMetaModel, YieldStmt.class, "YieldStmt", "com.github.javaparser.ast.stmt", false, false);
    }
}

