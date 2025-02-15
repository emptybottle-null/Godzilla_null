/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.github.javaparser.metamodel;

import com.github.javaparser.ast.body.Parameter;
import com.github.javaparser.metamodel.BaseNodeMetaModel;
import com.github.javaparser.metamodel.NodeMetaModel;
import com.github.javaparser.metamodel.PropertyMetaModel;
import java.util.Optional;

public class ParameterMetaModel
extends NodeMetaModel {
    public PropertyMetaModel annotationsPropertyMetaModel;
    public PropertyMetaModel isVarArgsPropertyMetaModel;
    public PropertyMetaModel modifiersPropertyMetaModel;
    public PropertyMetaModel namePropertyMetaModel;
    public PropertyMetaModel typePropertyMetaModel;
    public PropertyMetaModel varArgsAnnotationsPropertyMetaModel;

    ParameterMetaModel(Optional<BaseNodeMetaModel> superBaseNodeMetaModel) {
        super(superBaseNodeMetaModel, Parameter.class, "Parameter", "com.github.javaparser.ast.body", false, false);
    }
}

