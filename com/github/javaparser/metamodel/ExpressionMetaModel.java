/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.github.javaparser.metamodel;

import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.expr.Expression;
import com.github.javaparser.metamodel.BaseNodeMetaModel;
import com.github.javaparser.metamodel.NodeMetaModel;
import java.util.Optional;

public class ExpressionMetaModel
extends NodeMetaModel {
    ExpressionMetaModel(Optional<BaseNodeMetaModel> superBaseNodeMetaModel) {
        super(superBaseNodeMetaModel, Expression.class, "Expression", "com.github.javaparser.ast.expr", true, false);
    }

    protected ExpressionMetaModel(Optional<BaseNodeMetaModel> superNodeMetaModel, Class<? extends Node> type, String name, String packageName, boolean isAbstract, boolean hasWildcard) {
        super(superNodeMetaModel, type, name, packageName, isAbstract, hasWildcard);
    }
}

