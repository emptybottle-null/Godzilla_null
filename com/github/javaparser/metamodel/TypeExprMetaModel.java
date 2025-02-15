/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.github.javaparser.metamodel;

import com.github.javaparser.ast.expr.TypeExpr;
import com.github.javaparser.metamodel.BaseNodeMetaModel;
import com.github.javaparser.metamodel.ExpressionMetaModel;
import com.github.javaparser.metamodel.PropertyMetaModel;
import java.util.Optional;

public class TypeExprMetaModel
extends ExpressionMetaModel {
    public PropertyMetaModel typePropertyMetaModel;

    TypeExprMetaModel(Optional<BaseNodeMetaModel> superBaseNodeMetaModel) {
        super(superBaseNodeMetaModel, TypeExpr.class, "TypeExpr", "com.github.javaparser.ast.expr", false, false);
    }
}

