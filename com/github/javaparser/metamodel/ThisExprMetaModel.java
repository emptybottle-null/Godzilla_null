/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.github.javaparser.metamodel;

import com.github.javaparser.ast.expr.ThisExpr;
import com.github.javaparser.metamodel.BaseNodeMetaModel;
import com.github.javaparser.metamodel.ExpressionMetaModel;
import com.github.javaparser.metamodel.PropertyMetaModel;
import java.util.Optional;

public class ThisExprMetaModel
extends ExpressionMetaModel {
    public PropertyMetaModel typeNamePropertyMetaModel;

    ThisExprMetaModel(Optional<BaseNodeMetaModel> superBaseNodeMetaModel) {
        super(superBaseNodeMetaModel, ThisExpr.class, "ThisExpr", "com.github.javaparser.ast.expr", false, false);
    }
}

