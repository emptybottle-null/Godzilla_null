/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.github.javaparser.metamodel;

import com.github.javaparser.ast.expr.InstanceOfExpr;
import com.github.javaparser.metamodel.BaseNodeMetaModel;
import com.github.javaparser.metamodel.ExpressionMetaModel;
import com.github.javaparser.metamodel.PropertyMetaModel;
import java.util.Optional;

public class InstanceOfExprMetaModel
extends ExpressionMetaModel {
    public PropertyMetaModel expressionPropertyMetaModel;
    public PropertyMetaModel patternPropertyMetaModel;
    public PropertyMetaModel typePropertyMetaModel;

    InstanceOfExprMetaModel(Optional<BaseNodeMetaModel> superBaseNodeMetaModel) {
        super(superBaseNodeMetaModel, InstanceOfExpr.class, "InstanceOfExpr", "com.github.javaparser.ast.expr", false, false);
    }
}

