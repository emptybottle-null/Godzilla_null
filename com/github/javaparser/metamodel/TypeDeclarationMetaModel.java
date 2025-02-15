/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.github.javaparser.metamodel;

import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.body.TypeDeclaration;
import com.github.javaparser.metamodel.BaseNodeMetaModel;
import com.github.javaparser.metamodel.BodyDeclarationMetaModel;
import com.github.javaparser.metamodel.PropertyMetaModel;
import java.util.Optional;

public class TypeDeclarationMetaModel
extends BodyDeclarationMetaModel {
    public PropertyMetaModel membersPropertyMetaModel;
    public PropertyMetaModel modifiersPropertyMetaModel;
    public PropertyMetaModel namePropertyMetaModel;

    TypeDeclarationMetaModel(Optional<BaseNodeMetaModel> superBaseNodeMetaModel) {
        super(superBaseNodeMetaModel, TypeDeclaration.class, "TypeDeclaration", "com.github.javaparser.ast.body", true, true);
    }

    protected TypeDeclarationMetaModel(Optional<BaseNodeMetaModel> superNodeMetaModel, Class<? extends Node> type, String name, String packageName, boolean isAbstract, boolean hasWildcard) {
        super(superNodeMetaModel, type, name, packageName, isAbstract, hasWildcard);
    }
}

