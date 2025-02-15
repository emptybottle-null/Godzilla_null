/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.github.javaparser.metamodel;

import com.github.javaparser.ast.expr.EnclosedExpr;
import com.github.javaparser.metamodel.BaseNodeMetaModel;
import com.github.javaparser.metamodel.ExpressionMetaModel;
import com.github.javaparser.metamodel.PropertyMetaModel;
import java.util.Optional;

public class EnclosedExprMetaModel
extends ExpressionMetaModel {
    public PropertyMetaModel innerPropertyMetaModel;

    EnclosedExprMetaModel(Optional<BaseNodeMetaModel> superBaseNodeMetaModel) {
        super(superBaseNodeMetaModel, EnclosedExpr.class, "EnclosedExpr", "com.github.javaparser.ast.expr", false, false);
    }
}

