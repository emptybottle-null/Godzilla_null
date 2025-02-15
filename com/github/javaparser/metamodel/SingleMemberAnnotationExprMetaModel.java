/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.github.javaparser.metamodel;

import com.github.javaparser.ast.expr.SingleMemberAnnotationExpr;
import com.github.javaparser.metamodel.AnnotationExprMetaModel;
import com.github.javaparser.metamodel.BaseNodeMetaModel;
import com.github.javaparser.metamodel.PropertyMetaModel;
import java.util.Optional;

public class SingleMemberAnnotationExprMetaModel
extends AnnotationExprMetaModel {
    public PropertyMetaModel memberValuePropertyMetaModel;

    SingleMemberAnnotationExprMetaModel(Optional<BaseNodeMetaModel> superBaseNodeMetaModel) {
        super(superBaseNodeMetaModel, SingleMemberAnnotationExpr.class, "SingleMemberAnnotationExpr", "com.github.javaparser.ast.expr", false, false);
    }
}

