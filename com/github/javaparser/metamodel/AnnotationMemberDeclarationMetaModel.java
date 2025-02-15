/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.github.javaparser.metamodel;

import com.github.javaparser.ast.body.AnnotationMemberDeclaration;
import com.github.javaparser.metamodel.BaseNodeMetaModel;
import com.github.javaparser.metamodel.BodyDeclarationMetaModel;
import com.github.javaparser.metamodel.PropertyMetaModel;
import java.util.Optional;

public class AnnotationMemberDeclarationMetaModel
extends BodyDeclarationMetaModel {
    public PropertyMetaModel defaultValuePropertyMetaModel;
    public PropertyMetaModel modifiersPropertyMetaModel;
    public PropertyMetaModel namePropertyMetaModel;
    public PropertyMetaModel typePropertyMetaModel;

    AnnotationMemberDeclarationMetaModel(Optional<BaseNodeMetaModel> superBaseNodeMetaModel) {
        super(superBaseNodeMetaModel, AnnotationMemberDeclaration.class, "AnnotationMemberDeclaration", "com.github.javaparser.ast.body", false, false);
    }
}

