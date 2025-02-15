/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.github.javaparser.metamodel;

import com.github.javaparser.ast.expr.IntegerLiteralExpr;
import com.github.javaparser.metamodel.BaseNodeMetaModel;
import com.github.javaparser.metamodel.LiteralStringValueExprMetaModel;
import java.util.Optional;

public class IntegerLiteralExprMetaModel
extends LiteralStringValueExprMetaModel {
    IntegerLiteralExprMetaModel(Optional<BaseNodeMetaModel> superBaseNodeMetaModel) {
        super(superBaseNodeMetaModel, IntegerLiteralExpr.class, "IntegerLiteralExpr", "com.github.javaparser.ast.expr", false, false);
    }
}

