/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.github.javaparser.metamodel;

import com.github.javaparser.ast.expr.MethodReferenceExpr;
import com.github.javaparser.metamodel.BaseNodeMetaModel;
import com.github.javaparser.metamodel.ExpressionMetaModel;
import com.github.javaparser.metamodel.PropertyMetaModel;
import java.util.Optional;

public class MethodReferenceExprMetaModel
extends ExpressionMetaModel {
    public PropertyMetaModel identifierPropertyMetaModel;
    public PropertyMetaModel scopePropertyMetaModel;
    public PropertyMetaModel typeArgumentsPropertyMetaModel;
    public PropertyMetaModel usingDiamondOperatorPropertyMetaModel;

    MethodReferenceExprMetaModel(Optional<BaseNodeMetaModel> superBaseNodeMetaModel) {
        super(superBaseNodeMetaModel, MethodReferenceExpr.class, "MethodReferenceExpr", "com.github.javaparser.ast.expr", false, false);
    }
}

