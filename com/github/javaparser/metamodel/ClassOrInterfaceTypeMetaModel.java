/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.github.javaparser.metamodel;

import com.github.javaparser.ast.type.ClassOrInterfaceType;
import com.github.javaparser.metamodel.BaseNodeMetaModel;
import com.github.javaparser.metamodel.PropertyMetaModel;
import com.github.javaparser.metamodel.ReferenceTypeMetaModel;
import java.util.Optional;

public class ClassOrInterfaceTypeMetaModel
extends ReferenceTypeMetaModel {
    public PropertyMetaModel namePropertyMetaModel;
    public PropertyMetaModel scopePropertyMetaModel;
    public PropertyMetaModel typeArgumentsPropertyMetaModel;
    public PropertyMetaModel usingDiamondOperatorPropertyMetaModel;

    ClassOrInterfaceTypeMetaModel(Optional<BaseNodeMetaModel> superBaseNodeMetaModel) {
        super(superBaseNodeMetaModel, ClassOrInterfaceType.class, "ClassOrInterfaceType", "com.github.javaparser.ast.type", false, false);
    }
}

