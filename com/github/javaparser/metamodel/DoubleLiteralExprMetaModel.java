/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.github.javaparser.metamodel;

import com.github.javaparser.ast.expr.DoubleLiteralExpr;
import com.github.javaparser.metamodel.BaseNodeMetaModel;
import com.github.javaparser.metamodel.LiteralStringValueExprMetaModel;
import java.util.Optional;

public class DoubleLiteralExprMetaModel
extends LiteralStringValueExprMetaModel {
    DoubleLiteralExprMetaModel(Optional<BaseNodeMetaModel> superBaseNodeMetaModel) {
        super(superBaseNodeMetaModel, DoubleLiteralExpr.class, "DoubleLiteralExpr", "com.github.javaparser.ast.expr", false, false);
    }
}

