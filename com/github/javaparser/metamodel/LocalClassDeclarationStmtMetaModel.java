/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.github.javaparser.metamodel;

import com.github.javaparser.ast.stmt.LocalClassDeclarationStmt;
import com.github.javaparser.metamodel.BaseNodeMetaModel;
import com.github.javaparser.metamodel.PropertyMetaModel;
import com.github.javaparser.metamodel.StatementMetaModel;
import java.util.Optional;

public class LocalClassDeclarationStmtMetaModel
extends StatementMetaModel {
    public PropertyMetaModel classDeclarationPropertyMetaModel;

    LocalClassDeclarationStmtMetaModel(Optional<BaseNodeMetaModel> superBaseNodeMetaModel) {
        super(superBaseNodeMetaModel, LocalClassDeclarationStmt.class, "LocalClassDeclarationStmt", "com.github.javaparser.ast.stmt", false, false);
    }
}

