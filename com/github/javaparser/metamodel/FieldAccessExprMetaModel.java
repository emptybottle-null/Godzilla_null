/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.github.javaparser.metamodel;

import com.github.javaparser.ast.expr.FieldAccessExpr;
import com.github.javaparser.metamodel.BaseNodeMetaModel;
import com.github.javaparser.metamodel.ExpressionMetaModel;
import com.github.javaparser.metamodel.PropertyMetaModel;
import java.util.Optional;

public class FieldAccessExprMetaModel
extends ExpressionMetaModel {
    public PropertyMetaModel namePropertyMetaModel;
    public PropertyMetaModel scopePropertyMetaModel;
    public PropertyMetaModel typeArgumentsPropertyMetaModel;
    public PropertyMetaModel usingDiamondOperatorPropertyMetaModel;

    FieldAccessExprMetaModel(Optional<BaseNodeMetaModel> superBaseNodeMetaModel) {
        super(superBaseNodeMetaModel, FieldAccessExpr.class, "FieldAccessExpr", "com.github.javaparser.ast.expr", false, false);
    }
}

