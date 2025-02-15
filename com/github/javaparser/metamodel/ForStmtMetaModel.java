/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.github.javaparser.metamodel;

import com.github.javaparser.ast.stmt.ForStmt;
import com.github.javaparser.metamodel.BaseNodeMetaModel;
import com.github.javaparser.metamodel.PropertyMetaModel;
import com.github.javaparser.metamodel.StatementMetaModel;
import java.util.Optional;

public class ForStmtMetaModel
extends StatementMetaModel {
    public PropertyMetaModel bodyPropertyMetaModel;
    public PropertyMetaModel comparePropertyMetaModel;
    public PropertyMetaModel initializationPropertyMetaModel;
    public PropertyMetaModel updatePropertyMetaModel;

    ForStmtMetaModel(Optional<BaseNodeMetaModel> superBaseNodeMetaModel) {
        super(superBaseNodeMetaModel, ForStmt.class, "ForStmt", "com.github.javaparser.ast.stmt", false, false);
    }
}

