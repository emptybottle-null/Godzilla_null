/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.github.javaparser.metamodel;

import com.github.javaparser.ast.type.VarType;
import com.github.javaparser.metamodel.BaseNodeMetaModel;
import com.github.javaparser.metamodel.TypeMetaModel;
import java.util.Optional;

public class VarTypeMetaModel
extends TypeMetaModel {
    VarTypeMetaModel(Optional<BaseNodeMetaModel> superBaseNodeMetaModel) {
        super(superBaseNodeMetaModel, VarType.class, "VarType", "com.github.javaparser.ast.type", false, false);
    }
}

