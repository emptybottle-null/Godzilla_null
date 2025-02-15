/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.github.javaparser.metamodel;

import com.github.javaparser.ast.expr.PatternExpr;
import com.github.javaparser.metamodel.BaseNodeMetaModel;
import com.github.javaparser.metamodel.ExpressionMetaModel;
import com.github.javaparser.metamodel.PropertyMetaModel;
import java.util.Optional;

public class PatternExprMetaModel
extends ExpressionMetaModel {
    public PropertyMetaModel namePropertyMetaModel;
    public PropertyMetaModel typePropertyMetaModel;

    PatternExprMetaModel(Optional<BaseNodeMetaModel> superBaseNodeMetaModel) {
        super(superBaseNodeMetaModel, PatternExpr.class, "PatternExpr", "com.github.javaparser.ast.expr", false, false);
    }
}

