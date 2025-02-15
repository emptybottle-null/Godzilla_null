/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.github.javaparser.metamodel;

import com.github.javaparser.ast.body.FieldDeclaration;
import com.github.javaparser.metamodel.BaseNodeMetaModel;
import com.github.javaparser.metamodel.BodyDeclarationMetaModel;
import com.github.javaparser.metamodel.PropertyMetaModel;
import java.util.Optional;

public class FieldDeclarationMetaModel
extends BodyDeclarationMetaModel {
    public PropertyMetaModel modifiersPropertyMetaModel;
    public PropertyMetaModel variablesPropertyMetaModel;
    public PropertyMetaModel maximumCommonTypePropertyMetaModel;

    FieldDeclarationMetaModel(Optional<BaseNodeMetaModel> superBaseNodeMetaModel) {
        super(superBaseNodeMetaModel, FieldDeclaration.class, "FieldDeclaration", "com.github.javaparser.ast.body", false, false);
    }
}

