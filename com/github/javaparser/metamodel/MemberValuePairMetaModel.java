/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.github.javaparser.metamodel;

import com.github.javaparser.ast.expr.MemberValuePair;
import com.github.javaparser.metamodel.BaseNodeMetaModel;
import com.github.javaparser.metamodel.NodeMetaModel;
import com.github.javaparser.metamodel.PropertyMetaModel;
import java.util.Optional;

public class MemberValuePairMetaModel
extends NodeMetaModel {
    public PropertyMetaModel namePropertyMetaModel;
    public PropertyMetaModel valuePropertyMetaModel;

    MemberValuePairMetaModel(Optional<BaseNodeMetaModel> superBaseNodeMetaModel) {
        super(superBaseNodeMetaModel, MemberValuePair.class, "MemberValuePair", "com.github.javaparser.ast.expr", false, false);
    }
}

