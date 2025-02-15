/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.github.javaparser.metamodel;

import com.github.javaparser.ast.type.TypeParameter;
import com.github.javaparser.metamodel.BaseNodeMetaModel;
import com.github.javaparser.metamodel.PropertyMetaModel;
import com.github.javaparser.metamodel.ReferenceTypeMetaModel;
import java.util.Optional;

public class TypeParameterMetaModel
extends ReferenceTypeMetaModel {
    public PropertyMetaModel namePropertyMetaModel;
    public PropertyMetaModel typeBoundPropertyMetaModel;

    TypeParameterMetaModel(Optional<BaseNodeMetaModel> superBaseNodeMetaModel) {
        super(superBaseNodeMetaModel, TypeParameter.class, "TypeParameter", "com.github.javaparser.ast.type", false, false);
    }
}

