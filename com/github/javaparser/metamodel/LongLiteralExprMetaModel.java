/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.github.javaparser.metamodel;

import com.github.javaparser.ast.expr.LongLiteralExpr;
import com.github.javaparser.metamodel.BaseNodeMetaModel;
import com.github.javaparser.metamodel.LiteralStringValueExprMetaModel;
import java.util.Optional;

public class LongLiteralExprMetaModel
extends LiteralStringValueExprMetaModel {
    LongLiteralExprMetaModel(Optional<BaseNodeMetaModel> superBaseNodeMetaModel) {
        super(superBaseNodeMetaModel, LongLiteralExpr.class, "LongLiteralExpr", "com.github.javaparser.ast.expr", false, false);
    }
}

