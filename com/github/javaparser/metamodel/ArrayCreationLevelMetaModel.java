/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.github.javaparser.metamodel;

import com.github.javaparser.ast.ArrayCreationLevel;
import com.github.javaparser.metamodel.BaseNodeMetaModel;
import com.github.javaparser.metamodel.NodeMetaModel;
import com.github.javaparser.metamodel.PropertyMetaModel;
import java.util.Optional;

public class ArrayCreationLevelMetaModel
extends NodeMetaModel {
    public PropertyMetaModel annotationsPropertyMetaModel;
    public PropertyMetaModel dimensionPropertyMetaModel;

    ArrayCreationLevelMetaModel(Optional<BaseNodeMetaModel> superBaseNodeMetaModel) {
        super(superBaseNodeMetaModel, ArrayCreationLevel.class, "ArrayCreationLevel", "com.github.javaparser.ast", false, false);
    }
}

