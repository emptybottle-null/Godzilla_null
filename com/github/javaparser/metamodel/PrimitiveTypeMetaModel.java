/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.github.javaparser.metamodel;

import com.github.javaparser.ast.type.PrimitiveType;
import com.github.javaparser.metamodel.BaseNodeMetaModel;
import com.github.javaparser.metamodel.PropertyMetaModel;
import com.github.javaparser.metamodel.TypeMetaModel;
import java.util.Optional;

public class PrimitiveTypeMetaModel
extends TypeMetaModel {
    public PropertyMetaModel typePropertyMetaModel;

    PrimitiveTypeMetaModel(Optional<BaseNodeMetaModel> superBaseNodeMetaModel) {
        super(superBaseNodeMetaModel, PrimitiveType.class, "PrimitiveType", "com.github.javaparser.ast.type", false, false);
    }
}

