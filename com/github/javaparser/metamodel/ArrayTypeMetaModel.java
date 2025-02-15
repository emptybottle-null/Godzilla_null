/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.github.javaparser.metamodel;

import com.github.javaparser.ast.type.ArrayType;
import com.github.javaparser.metamodel.BaseNodeMetaModel;
import com.github.javaparser.metamodel.PropertyMetaModel;
import com.github.javaparser.metamodel.ReferenceTypeMetaModel;
import java.util.Optional;

public class ArrayTypeMetaModel
extends ReferenceTypeMetaModel {
    public PropertyMetaModel componentTypePropertyMetaModel;
    public PropertyMetaModel originPropertyMetaModel;

    ArrayTypeMetaModel(Optional<BaseNodeMetaModel> superBaseNodeMetaModel) {
        super(superBaseNodeMetaModel, ArrayType.class, "ArrayType", "com.github.javaparser.ast.type", false, false);
    }
}

