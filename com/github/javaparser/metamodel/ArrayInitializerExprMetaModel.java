/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.github.javaparser.metamodel;

import com.github.javaparser.ast.expr.ArrayInitializerExpr;
import com.github.javaparser.metamodel.BaseNodeMetaModel;
import com.github.javaparser.metamodel.ExpressionMetaModel;
import com.github.javaparser.metamodel.PropertyMetaModel;
import java.util.Optional;

public class ArrayInitializerExprMetaModel
extends ExpressionMetaModel {
    public PropertyMetaModel valuesPropertyMetaModel;

    ArrayInitializerExprMetaModel(Optional<BaseNodeMetaModel> superBaseNodeMetaModel) {
        super(superBaseNodeMetaModel, ArrayInitializerExpr.class, "ArrayInitializerExpr", "com.github.javaparser.ast.expr", false, false);
    }
}

