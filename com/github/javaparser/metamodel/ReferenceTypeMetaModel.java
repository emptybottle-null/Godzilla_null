/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.github.javaparser.metamodel;

import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.type.ReferenceType;
import com.github.javaparser.metamodel.BaseNodeMetaModel;
import com.github.javaparser.metamodel.TypeMetaModel;
import java.util.Optional;

public class ReferenceTypeMetaModel
extends TypeMetaModel {
    ReferenceTypeMetaModel(Optional<BaseNodeMetaModel> superBaseNodeMetaModel) {
        super(superBaseNodeMetaModel, ReferenceType.class, "ReferenceType", "com.github.javaparser.ast.type", true, false);
    }

    protected ReferenceTypeMetaModel(Optional<BaseNodeMetaModel> superNodeMetaModel, Class<? extends Node> type, String name, String packageName, boolean isAbstract, boolean hasWildcard) {
        super(superNodeMetaModel, type, name, packageName, isAbstract, hasWildcard);
    }
}

