/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.github.javaparser.metamodel;

import com.github.javaparser.ast.type.VoidType;
import com.github.javaparser.metamodel.BaseNodeMetaModel;
import com.github.javaparser.metamodel.TypeMetaModel;
import java.util.Optional;

public class VoidTypeMetaModel
extends TypeMetaModel {
    VoidTypeMetaModel(Optional<BaseNodeMetaModel> superBaseNodeMetaModel) {
        super(superBaseNodeMetaModel, VoidType.class, "VoidType", "com.github.javaparser.ast.type", false, false);
    }
}

