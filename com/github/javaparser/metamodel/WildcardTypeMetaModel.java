/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.github.javaparser.metamodel;

import com.github.javaparser.ast.type.WildcardType;
import com.github.javaparser.metamodel.BaseNodeMetaModel;
import com.github.javaparser.metamodel.PropertyMetaModel;
import com.github.javaparser.metamodel.TypeMetaModel;
import java.util.Optional;

public class WildcardTypeMetaModel
extends TypeMetaModel {
    public PropertyMetaModel extendedTypePropertyMetaModel;
    public PropertyMetaModel superTypePropertyMetaModel;

    WildcardTypeMetaModel(Optional<BaseNodeMetaModel> superBaseNodeMetaModel) {
        super(superBaseNodeMetaModel, WildcardType.class, "WildcardType", "com.github.javaparser.ast.type", false, false);
    }
}

