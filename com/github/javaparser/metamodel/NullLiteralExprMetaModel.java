/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.github.javaparser.metamodel;

import com.github.javaparser.ast.expr.NullLiteralExpr;
import com.github.javaparser.metamodel.BaseNodeMetaModel;
import com.github.javaparser.metamodel.LiteralExprMetaModel;
import java.util.Optional;

public class NullLiteralExprMetaModel
extends LiteralExprMetaModel {
    NullLiteralExprMetaModel(Optional<BaseNodeMetaModel> superBaseNodeMetaModel) {
        super(superBaseNodeMetaModel, NullLiteralExpr.class, "NullLiteralExpr", "com.github.javaparser.ast.expr", false, false);
    }
}

