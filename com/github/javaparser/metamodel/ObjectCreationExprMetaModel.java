/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.github.javaparser.metamodel;

import com.github.javaparser.ast.expr.ObjectCreationExpr;
import com.github.javaparser.metamodel.BaseNodeMetaModel;
import com.github.javaparser.metamodel.ExpressionMetaModel;
import com.github.javaparser.metamodel.PropertyMetaModel;
import java.util.Optional;

public class ObjectCreationExprMetaModel
extends ExpressionMetaModel {
    public PropertyMetaModel anonymousClassBodyPropertyMetaModel;
    public PropertyMetaModel argumentsPropertyMetaModel;
    public PropertyMetaModel scopePropertyMetaModel;
    public PropertyMetaModel typePropertyMetaModel;
    public PropertyMetaModel typeArgumentsPropertyMetaModel;
    public PropertyMetaModel usingDiamondOperatorPropertyMetaModel;

    ObjectCreationExprMetaModel(Optional<BaseNodeMetaModel> superBaseNodeMetaModel) {
        super(superBaseNodeMetaModel, ObjectCreationExpr.class, "ObjectCreationExpr", "com.github.javaparser.ast.expr", false, false);
    }
}

