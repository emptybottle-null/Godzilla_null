/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.github.javaparser.metamodel;

import com.github.javaparser.ast.stmt.IfStmt;
import com.github.javaparser.metamodel.BaseNodeMetaModel;
import com.github.javaparser.metamodel.PropertyMetaModel;
import com.github.javaparser.metamodel.StatementMetaModel;
import java.util.Optional;

public class IfStmtMetaModel
extends StatementMetaModel {
    public PropertyMetaModel conditionPropertyMetaModel;
    public PropertyMetaModel elseStmtPropertyMetaModel;
    public PropertyMetaModel thenStmtPropertyMetaModel;
    public PropertyMetaModel cascadingIfStmtPropertyMetaModel;
    public PropertyMetaModel elseBlockPropertyMetaModel;
    public PropertyMetaModel elseBranchPropertyMetaModel;
    public PropertyMetaModel thenBlockPropertyMetaModel;

    IfStmtMetaModel(Optional<BaseNodeMetaModel> superBaseNodeMetaModel) {
        super(superBaseNodeMetaModel, IfStmt.class, "IfStmt", "com.github.javaparser.ast.stmt", false, false);
    }
}

