/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.github.javaparser.metamodel;

import com.github.javaparser.ast.stmt.DoStmt;
import com.github.javaparser.metamodel.BaseNodeMetaModel;
import com.github.javaparser.metamodel.PropertyMetaModel;
import com.github.javaparser.metamodel.StatementMetaModel;
import java.util.Optional;

public class DoStmtMetaModel
extends StatementMetaModel {
    public PropertyMetaModel bodyPropertyMetaModel;
    public PropertyMetaModel conditionPropertyMetaModel;

    DoStmtMetaModel(Optional<BaseNodeMetaModel> superBaseNodeMetaModel) {
        super(superBaseNodeMetaModel, DoStmt.class, "DoStmt", "com.github.javaparser.ast.stmt", false, false);
    }
}

