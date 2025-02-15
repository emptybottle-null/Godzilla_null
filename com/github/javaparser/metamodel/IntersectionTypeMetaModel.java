/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.github.javaparser.metamodel;

import com.github.javaparser.ast.type.IntersectionType;
import com.github.javaparser.metamodel.BaseNodeMetaModel;
import com.github.javaparser.metamodel.PropertyMetaModel;
import com.github.javaparser.metamodel.TypeMetaModel;
import java.util.Optional;

public class IntersectionTypeMetaModel
extends TypeMetaModel {
    public PropertyMetaModel elementsPropertyMetaModel;

    IntersectionTypeMetaModel(Optional<BaseNodeMetaModel> superBaseNodeMetaModel) {
        super(superBaseNodeMetaModel, IntersectionType.class, "IntersectionType", "com.github.javaparser.ast.type", false, false);
    }
}

