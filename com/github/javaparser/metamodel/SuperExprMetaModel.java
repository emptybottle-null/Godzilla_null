/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.github.javaparser.metamodel;

import com.github.javaparser.ast.expr.SuperExpr;
import com.github.javaparser.metamodel.BaseNodeMetaModel;
import com.github.javaparser.metamodel.ExpressionMetaModel;
import com.github.javaparser.metamodel.PropertyMetaModel;
import java.util.Optional;

public class SuperExprMetaModel
extends ExpressionMetaModel {
    public PropertyMetaModel typeNamePropertyMetaModel;

    SuperExprMetaModel(Optional<BaseNodeMetaModel> superBaseNodeMetaModel) {
        super(superBaseNodeMetaModel, SuperExpr.class, "SuperExpr", "com.github.javaparser.ast.expr", false, false);
    }
}

