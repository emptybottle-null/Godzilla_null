/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.github.javaparser.metamodel;

import com.github.javaparser.ast.expr.CastExpr;
import com.github.javaparser.metamodel.BaseNodeMetaModel;
import com.github.javaparser.metamodel.ExpressionMetaModel;
import com.github.javaparser.metamodel.PropertyMetaModel;
import java.util.Optional;

public class CastExprMetaModel
extends ExpressionMetaModel {
    public PropertyMetaModel expressionPropertyMetaModel;
    public PropertyMetaModel typePropertyMetaModel;

    CastExprMetaModel(Optional<BaseNodeMetaModel> superBaseNodeMetaModel) {
        super(superBaseNodeMetaModel, CastExpr.class, "CastExpr", "com.github.javaparser.ast.expr", false, false);
    }
}

