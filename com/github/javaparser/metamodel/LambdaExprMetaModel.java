/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.github.javaparser.metamodel;

import com.github.javaparser.ast.expr.LambdaExpr;
import com.github.javaparser.metamodel.BaseNodeMetaModel;
import com.github.javaparser.metamodel.ExpressionMetaModel;
import com.github.javaparser.metamodel.PropertyMetaModel;
import java.util.Optional;

public class LambdaExprMetaModel
extends ExpressionMetaModel {
    public PropertyMetaModel bodyPropertyMetaModel;
    public PropertyMetaModel isEnclosingParametersPropertyMetaModel;
    public PropertyMetaModel parametersPropertyMetaModel;
    public PropertyMetaModel expressionBodyPropertyMetaModel;

    LambdaExprMetaModel(Optional<BaseNodeMetaModel> superBaseNodeMetaModel) {
        super(superBaseNodeMetaModel, LambdaExpr.class, "LambdaExpr", "com.github.javaparser.ast.expr", false, false);
    }
}

