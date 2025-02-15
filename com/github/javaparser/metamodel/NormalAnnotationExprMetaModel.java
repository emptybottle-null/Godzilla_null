/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.github.javaparser.metamodel;

import com.github.javaparser.ast.expr.NormalAnnotationExpr;
import com.github.javaparser.metamodel.AnnotationExprMetaModel;
import com.github.javaparser.metamodel.BaseNodeMetaModel;
import com.github.javaparser.metamodel.PropertyMetaModel;
import java.util.Optional;

public class NormalAnnotationExprMetaModel
extends AnnotationExprMetaModel {
    public PropertyMetaModel pairsPropertyMetaModel;

    NormalAnnotationExprMetaModel(Optional<BaseNodeMetaModel> superBaseNodeMetaModel) {
        super(superBaseNodeMetaModel, NormalAnnotationExpr.class, "NormalAnnotationExpr", "com.github.javaparser.ast.expr", false, false);
    }
}

