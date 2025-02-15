/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.github.javaparser.metamodel;

import com.github.javaparser.ast.stmt.EmptyStmt;
import com.github.javaparser.metamodel.BaseNodeMetaModel;
import com.github.javaparser.metamodel.StatementMetaModel;
import java.util.Optional;

public class EmptyStmtMetaModel
extends StatementMetaModel {
    EmptyStmtMetaModel(Optional<BaseNodeMetaModel> superBaseNodeMetaModel) {
        super(superBaseNodeMetaModel, EmptyStmt.class, "EmptyStmt", "com.github.javaparser.ast.stmt", false, false);
    }
}

