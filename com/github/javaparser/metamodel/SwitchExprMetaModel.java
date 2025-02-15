/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.github.javaparser.metamodel;

import com.github.javaparser.ast.expr.SwitchExpr;
import com.github.javaparser.metamodel.BaseNodeMetaModel;
import com.github.javaparser.metamodel.ExpressionMetaModel;
import com.github.javaparser.metamodel.PropertyMetaModel;
import java.util.Optional;

public class SwitchExprMetaModel
extends ExpressionMetaModel {
    public PropertyMetaModel entriesPropertyMetaModel;
    public PropertyMetaModel selectorPropertyMetaModel;

    SwitchExprMetaModel(Optional<BaseNodeMetaModel> superBaseNodeMetaModel) {
        super(superBaseNodeMetaModel, SwitchExpr.class, "SwitchExpr", "com.github.javaparser.ast.expr", false, false);
    }
}

