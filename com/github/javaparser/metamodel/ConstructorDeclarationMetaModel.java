/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.github.javaparser.metamodel;

import com.github.javaparser.ast.body.ConstructorDeclaration;
import com.github.javaparser.metamodel.BaseNodeMetaModel;
import com.github.javaparser.metamodel.CallableDeclarationMetaModel;
import com.github.javaparser.metamodel.PropertyMetaModel;
import java.util.Optional;

public class ConstructorDeclarationMetaModel
extends CallableDeclarationMetaModel {
    public PropertyMetaModel bodyPropertyMetaModel;

    ConstructorDeclarationMetaModel(Optional<BaseNodeMetaModel> superBaseNodeMetaModel) {
        super(superBaseNodeMetaModel, ConstructorDeclaration.class, "ConstructorDeclaration", "com.github.javaparser.ast.body", false, false);
    }
}

