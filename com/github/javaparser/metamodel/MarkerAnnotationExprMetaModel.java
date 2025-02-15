/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.github.javaparser.metamodel;

import com.github.javaparser.ast.expr.MarkerAnnotationExpr;
import com.github.javaparser.metamodel.AnnotationExprMetaModel;
import com.github.javaparser.metamodel.BaseNodeMetaModel;
import java.util.Optional;

public class MarkerAnnotationExprMetaModel
extends AnnotationExprMetaModel {
    MarkerAnnotationExprMetaModel(Optional<BaseNodeMetaModel> superBaseNodeMetaModel) {
        super(superBaseNodeMetaModel, MarkerAnnotationExpr.class, "MarkerAnnotationExpr", "com.github.javaparser.ast.expr", false, false);
    }
}

