/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.github.javaparser.metamodel;

import com.github.javaparser.ast.stmt.UnparsableStmt;
import com.github.javaparser.metamodel.BaseNodeMetaModel;
import com.github.javaparser.metamodel.StatementMetaModel;
import java.util.Optional;

public class UnparsableStmtMetaModel
extends StatementMetaModel {
    UnparsableStmtMetaModel(Optional<BaseNodeMetaModel> superBaseNodeMetaModel) {
        super(superBaseNodeMetaModel, UnparsableStmt.class, "UnparsableStmt", "com.github.javaparser.ast.stmt", false, false);
    }
}

