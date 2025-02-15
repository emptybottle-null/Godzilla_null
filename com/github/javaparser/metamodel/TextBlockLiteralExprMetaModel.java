/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.github.javaparser.metamodel;

import com.github.javaparser.ast.expr.TextBlockLiteralExpr;
import com.github.javaparser.metamodel.BaseNodeMetaModel;
import com.github.javaparser.metamodel.LiteralStringValueExprMetaModel;
import java.util.Optional;

public class TextBlockLiteralExprMetaModel
extends LiteralStringValueExprMetaModel {
    TextBlockLiteralExprMetaModel(Optional<BaseNodeMetaModel> superBaseNodeMetaModel) {
        super(superBaseNodeMetaModel, TextBlockLiteralExpr.class, "TextBlockLiteralExpr", "com.github.javaparser.ast.expr", false, false);
    }
}

