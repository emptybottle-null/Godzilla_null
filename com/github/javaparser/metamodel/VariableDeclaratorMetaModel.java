/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.github.javaparser.metamodel;

import com.github.javaparser.ast.body.VariableDeclarator;
import com.github.javaparser.metamodel.BaseNodeMetaModel;
import com.github.javaparser.metamodel.NodeMetaModel;
import com.github.javaparser.metamodel.PropertyMetaModel;
import java.util.Optional;

public class VariableDeclaratorMetaModel
extends NodeMetaModel {
    public PropertyMetaModel initializerPropertyMetaModel;
    public PropertyMetaModel namePropertyMetaModel;
    public PropertyMetaModel typePropertyMetaModel;

    VariableDeclaratorMetaModel(Optional<BaseNodeMetaModel> superBaseNodeMetaModel) {
        super(superBaseNodeMetaModel, VariableDeclarator.class, "VariableDeclarator", "com.github.javaparser.ast.body", false, false);
    }
}

