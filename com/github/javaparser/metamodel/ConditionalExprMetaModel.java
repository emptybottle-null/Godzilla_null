/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.github.javaparser.metamodel;

import com.github.javaparser.ast.expr.ConditionalExpr;
import com.github.javaparser.metamodel.BaseNodeMetaModel;
import com.github.javaparser.metamodel.ExpressionMetaModel;
import com.github.javaparser.metamodel.PropertyMetaModel;
import java.util.Optional;

public class ConditionalExprMetaModel
extends ExpressionMetaModel {
    public PropertyMetaModel conditionPropertyMetaModel;
    public PropertyMetaModel elseExprPropertyMetaModel;
    public PropertyMetaModel thenExprPropertyMetaModel;

    ConditionalExprMetaModel(Optional<BaseNodeMetaModel> superBaseNodeMetaModel) {
        super(superBaseNodeMetaModel, ConditionalExpr.class, "ConditionalExpr", "com.github.javaparser.ast.expr", false, false);
    }
}

