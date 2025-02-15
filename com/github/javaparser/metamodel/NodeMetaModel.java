/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.github.javaparser.metamodel;

import com.github.javaparser.ast.Node;
import com.github.javaparser.metamodel.BaseNodeMetaModel;
import com.github.javaparser.metamodel.PropertyMetaModel;
import java.util.Optional;

public class NodeMetaModel
extends BaseNodeMetaModel {
    public PropertyMetaModel commentPropertyMetaModel;

    NodeMetaModel(Optional<BaseNodeMetaModel> superBaseNodeMetaModel) {
        super(superBaseNodeMetaModel, Node.class, "Node", "com.github.javaparser.ast", true, false);
    }

    protected NodeMetaModel(Optional<BaseNodeMetaModel> superNodeMetaModel, Class<? extends Node> type, String name, String packageName, boolean isAbstract, boolean hasWildcard) {
        super(superNodeMetaModel, type, name, packageName, isAbstract, hasWildcard);
    }
}

