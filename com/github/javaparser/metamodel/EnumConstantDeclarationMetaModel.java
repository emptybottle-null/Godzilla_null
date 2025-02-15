/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.github.javaparser.metamodel;

import com.github.javaparser.ast.body.EnumConstantDeclaration;
import com.github.javaparser.metamodel.BaseNodeMetaModel;
import com.github.javaparser.metamodel.BodyDeclarationMetaModel;
import com.github.javaparser.metamodel.PropertyMetaModel;
import java.util.Optional;

public class EnumConstantDeclarationMetaModel
extends BodyDeclarationMetaModel {
    public PropertyMetaModel argumentsPropertyMetaModel;
    public PropertyMetaModel classBodyPropertyMetaModel;
    public PropertyMetaModel namePropertyMetaModel;

    EnumConstantDeclarationMetaModel(Optional<BaseNodeMetaModel> superBaseNodeMetaModel) {
        super(superBaseNodeMetaModel, EnumConstantDeclaration.class, "EnumConstantDeclaration", "com.github.javaparser.ast.body", false, false);
    }
}

