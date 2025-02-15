/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.github.javaparser.metamodel;

import com.github.javaparser.ast.expr.ArrayAccessExpr;
import com.github.javaparser.metamodel.BaseNodeMetaModel;
import com.github.javaparser.metamodel.ExpressionMetaModel;
import com.github.javaparser.metamodel.PropertyMetaModel;
import java.util.Optional;

public class ArrayAccessExprMetaModel
extends ExpressionMetaModel {
    public PropertyMetaModel indexPropertyMetaModel;
    public PropertyMetaModel namePropertyMetaModel;

    ArrayAccessExprMetaModel(Optional<BaseNodeMetaModel> superBaseNodeMetaModel) {
        super(superBaseNodeMetaModel, ArrayAccessExpr.class, "ArrayAccessExpr", "com.github.javaparser.ast.expr", false, false);
    }
}

