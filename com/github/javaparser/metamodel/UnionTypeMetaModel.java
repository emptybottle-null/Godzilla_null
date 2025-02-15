/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.github.javaparser.metamodel;

import com.github.javaparser.ast.type.UnionType;
import com.github.javaparser.metamodel.BaseNodeMetaModel;
import com.github.javaparser.metamodel.PropertyMetaModel;
import com.github.javaparser.metamodel.TypeMetaModel;
import java.util.Optional;

public class UnionTypeMetaModel
extends TypeMetaModel {
    public PropertyMetaModel elementsPropertyMetaModel;

    UnionTypeMetaModel(Optional<BaseNodeMetaModel> superBaseNodeMetaModel) {
        super(superBaseNodeMetaModel, UnionType.class, "UnionType", "com.github.javaparser.ast.type", false, false);
    }
}

