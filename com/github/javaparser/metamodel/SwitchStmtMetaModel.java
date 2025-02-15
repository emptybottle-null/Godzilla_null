/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.github.javaparser.metamodel;

import com.github.javaparser.ast.stmt.SwitchStmt;
import com.github.javaparser.metamodel.BaseNodeMetaModel;
import com.github.javaparser.metamodel.PropertyMetaModel;
import com.github.javaparser.metamodel.StatementMetaModel;
import java.util.Optional;

public class SwitchStmtMetaModel
extends StatementMetaModel {
    public PropertyMetaModel entriesPropertyMetaModel;
    public PropertyMetaModel selectorPropertyMetaModel;

    SwitchStmtMetaModel(Optional<BaseNodeMetaModel> superBaseNodeMetaModel) {
        super(superBaseNodeMetaModel, SwitchStmt.class, "SwitchStmt", "com.github.javaparser.ast.stmt", false, false);
    }
}

