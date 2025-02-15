/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.github.javaparser.metamodel;

import com.github.javaparser.ast.expr.CharLiteralExpr;
import com.github.javaparser.metamodel.BaseNodeMetaModel;
import com.github.javaparser.metamodel.LiteralStringValueExprMetaModel;
import java.util.Optional;

public class CharLiteralExprMetaModel
extends LiteralStringValueExprMetaModel {
    CharLiteralExprMetaModel(Optional<BaseNodeMetaModel> superBaseNodeMetaModel) {
        super(superBaseNodeMetaModel, CharLiteralExpr.class, "CharLiteralExpr", "com.github.javaparser.ast.expr", false, false);
    }
}

