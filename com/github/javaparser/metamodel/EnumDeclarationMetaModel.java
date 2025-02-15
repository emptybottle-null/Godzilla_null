/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.github.javaparser.metamodel;

import com.github.javaparser.ast.body.EnumDeclaration;
import com.github.javaparser.metamodel.BaseNodeMetaModel;
import com.github.javaparser.metamodel.PropertyMetaModel;
import com.github.javaparser.metamodel.TypeDeclarationMetaModel;
import java.util.Optional;

public class EnumDeclarationMetaModel
extends TypeDeclarationMetaModel {
    public PropertyMetaModel entriesPropertyMetaModel;
    public PropertyMetaModel implementedTypesPropertyMetaModel;

    EnumDeclarationMetaModel(Optional<BaseNodeMetaModel> superBaseNodeMetaModel) {
        super(superBaseNodeMetaModel, EnumDeclaration.class, "EnumDeclaration", "com.github.javaparser.ast.body", false, false);
    }
}

