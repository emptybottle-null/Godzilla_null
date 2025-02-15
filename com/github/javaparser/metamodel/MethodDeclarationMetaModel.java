/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.github.javaparser.metamodel;

import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.metamodel.BaseNodeMetaModel;
import com.github.javaparser.metamodel.CallableDeclarationMetaModel;
import com.github.javaparser.metamodel.PropertyMetaModel;
import java.util.Optional;

public class MethodDeclarationMetaModel
extends CallableDeclarationMetaModel {
    public PropertyMetaModel bodyPropertyMetaModel;
    public PropertyMetaModel typePropertyMetaModel;

    MethodDeclarationMetaModel(Optional<BaseNodeMetaModel> superBaseNodeMetaModel) {
        super(superBaseNodeMetaModel, MethodDeclaration.class, "MethodDeclaration", "com.github.javaparser.ast.body", false, false);
    }
}

