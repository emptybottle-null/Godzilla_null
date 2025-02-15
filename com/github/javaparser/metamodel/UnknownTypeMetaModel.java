/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.github.javaparser.metamodel;

import com.github.javaparser.ast.type.UnknownType;
import com.github.javaparser.metamodel.BaseNodeMetaModel;
import com.github.javaparser.metamodel.TypeMetaModel;
import java.util.Optional;

public class UnknownTypeMetaModel
extends TypeMetaModel {
    UnknownTypeMetaModel(Optional<BaseNodeMetaModel> superBaseNodeMetaModel) {
        super(superBaseNodeMetaModel, UnknownType.class, "UnknownType", "com.github.javaparser.ast.type", false, false);
    }
}

