/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.github.javaparser.metamodel;

import com.github.javaparser.ast.stmt.AssertStmt;
import com.github.javaparser.metamodel.BaseNodeMetaModel;
import com.github.javaparser.metamodel.PropertyMetaModel;
import com.github.javaparser.metamodel.StatementMetaModel;
import java.util.Optional;

public class AssertStmtMetaModel
extends StatementMetaModel {
    public PropertyMetaModel checkPropertyMetaModel;
    public PropertyMetaModel messagePropertyMetaModel;

    AssertStmtMetaModel(Optional<BaseNodeMetaModel> superBaseNodeMetaModel) {
        super(superBaseNodeMetaModel, AssertStmt.class, "AssertStmt", "com.github.javaparser.ast.stmt", false, false);
    }
}

