/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.github.javaparser.metamodel;

import com.github.javaparser.ast.expr.SimpleName;
import com.github.javaparser.metamodel.BaseNodeMetaModel;
import com.github.javaparser.metamodel.NodeMetaModel;
import com.github.javaparser.metamodel.PropertyMetaModel;
import java.util.Optional;

public class SimpleNameMetaModel
extends NodeMetaModel {
    public PropertyMetaModel identifierPropertyMetaModel;

    SimpleNameMetaModel(Optional<BaseNodeMetaModel> superBaseNodeMetaModel) {
        super(superBaseNodeMetaModel, SimpleName.class, "SimpleName", "com.github.javaparser.ast.expr", false, false);
    }
}

