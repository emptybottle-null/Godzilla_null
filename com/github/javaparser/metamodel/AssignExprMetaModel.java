/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.github.javaparser.metamodel;

import com.github.javaparser.ast.expr.AssignExpr;
import com.github.javaparser.metamodel.BaseNodeMetaModel;
import com.github.javaparser.metamodel.ExpressionMetaModel;
import com.github.javaparser.metamodel.PropertyMetaModel;
import java.util.Optional;

public class AssignExprMetaModel
extends ExpressionMetaModel {
    public PropertyMetaModel operatorPropertyMetaModel;
    public PropertyMetaModel targetPropertyMetaModel;
    public PropertyMetaModel valuePropertyMetaModel;

    AssignExprMetaModel(Optional<BaseNodeMetaModel> superBaseNodeMetaModel) {
        super(superBaseNodeMetaModel, AssignExpr.class, "AssignExpr", "com.github.javaparser.ast.expr", false, false);
    }
}

