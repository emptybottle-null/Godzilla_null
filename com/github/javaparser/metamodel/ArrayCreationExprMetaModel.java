/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.github.javaparser.metamodel;

import com.github.javaparser.ast.expr.ArrayCreationExpr;
import com.github.javaparser.metamodel.BaseNodeMetaModel;
import com.github.javaparser.metamodel.ExpressionMetaModel;
import com.github.javaparser.metamodel.PropertyMetaModel;
import java.util.Optional;

public class ArrayCreationExprMetaModel
extends ExpressionMetaModel {
    public PropertyMetaModel elementTypePropertyMetaModel;
    public PropertyMetaModel initializerPropertyMetaModel;
    public PropertyMetaModel levelsPropertyMetaModel;

    ArrayCreationExprMetaModel(Optional<BaseNodeMetaModel> superBaseNodeMetaModel) {
        super(superBaseNodeMetaModel, ArrayCreationExpr.class, "ArrayCreationExpr", "com.github.javaparser.ast.expr", false, false);
    }
}

