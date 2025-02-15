/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.github.javaparser.metamodel;

import com.github.javaparser.ast.expr.BinaryExpr;
import com.github.javaparser.metamodel.BaseNodeMetaModel;
import com.github.javaparser.metamodel.ExpressionMetaModel;
import com.github.javaparser.metamodel.PropertyMetaModel;
import java.util.Optional;

public class BinaryExprMetaModel
extends ExpressionMetaModel {
    public PropertyMetaModel leftPropertyMetaModel;
    public PropertyMetaModel operatorPropertyMetaModel;
    public PropertyMetaModel rightPropertyMetaModel;

    BinaryExprMetaModel(Optional<BaseNodeMetaModel> superBaseNodeMetaModel) {
        super(superBaseNodeMetaModel, BinaryExpr.class, "BinaryExpr", "com.github.javaparser.ast.expr", false, false);
    }
}

