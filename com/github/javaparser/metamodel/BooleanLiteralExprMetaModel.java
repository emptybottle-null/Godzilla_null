/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.github.javaparser.metamodel;

import com.github.javaparser.ast.expr.BooleanLiteralExpr;
import com.github.javaparser.metamodel.BaseNodeMetaModel;
import com.github.javaparser.metamodel.LiteralExprMetaModel;
import com.github.javaparser.metamodel.PropertyMetaModel;
import java.util.Optional;

public class BooleanLiteralExprMetaModel
extends LiteralExprMetaModel {
    public PropertyMetaModel valuePropertyMetaModel;

    BooleanLiteralExprMetaModel(Optional<BaseNodeMetaModel> superBaseNodeMetaModel) {
        super(superBaseNodeMetaModel, BooleanLiteralExpr.class, "BooleanLiteralExpr", "com.github.javaparser.ast.expr", false, false);
    }
}

