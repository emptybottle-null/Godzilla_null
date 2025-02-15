/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.github.javaparser.metamodel;

import com.github.javaparser.ast.body.AnnotationDeclaration;
import com.github.javaparser.metamodel.BaseNodeMetaModel;
import com.github.javaparser.metamodel.TypeDeclarationMetaModel;
import java.util.Optional;

public class AnnotationDeclarationMetaModel
extends TypeDeclarationMetaModel {
    AnnotationDeclarationMetaModel(Optional<BaseNodeMetaModel> superBaseNodeMetaModel) {
        super(superBaseNodeMetaModel, AnnotationDeclaration.class, "AnnotationDeclaration", "com.github.javaparser.ast.body", false, false);
    }
}

