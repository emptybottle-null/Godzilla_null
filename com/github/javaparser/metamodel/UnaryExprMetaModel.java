/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.github.javaparser.metamodel;

import com.github.javaparser.ast.expr.UnaryExpr;
import com.github.javaparser.metamodel.BaseNodeMetaModel;
import com.github.javaparser.metamodel.ExpressionMetaModel;
import com.github.javaparser.metamodel.PropertyMetaModel;
import java.util.Optional;

public class UnaryExprMetaModel
extends ExpressionMetaModel {
    public PropertyMetaModel expressionPropertyMetaModel;
    public PropertyMetaModel operatorPropertyMetaModel;
    public PropertyMetaModel postfixPropertyMetaModel;
    public PropertyMetaModel prefixPropertyMetaModel;

    UnaryExprMetaModel(Optional<BaseNodeMetaModel> superBaseNodeMetaModel) {
        super(superBaseNodeMetaModel, UnaryExpr.class, "UnaryExpr", "com.github.javaparser.ast.expr", false, false);
    }
}

