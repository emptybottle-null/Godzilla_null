/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.github.javaparser.metamodel;

import com.github.javaparser.ast.expr.Name;
import com.github.javaparser.metamodel.BaseNodeMetaModel;
import com.github.javaparser.metamodel.NodeMetaModel;
import com.github.javaparser.metamodel.PropertyMetaModel;
import java.util.Optional;

public class NameMetaModel
extends NodeMetaModel {
    public PropertyMetaModel identifierPropertyMetaModel;
    public PropertyMetaModel qualifierPropertyMetaModel;

    NameMetaModel(Optional<BaseNodeMetaModel> superBaseNodeMetaModel) {
        super(superBaseNodeMetaModel, Name.class, "Name", "com.github.javaparser.ast.expr", false, false);
    }
}

