/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.github.javaparser.metamodel;

import com.github.javaparser.ast.body.InitializerDeclaration;
import com.github.javaparser.metamodel.BaseNodeMetaModel;
import com.github.javaparser.metamodel.BodyDeclarationMetaModel;
import com.github.javaparser.metamodel.PropertyMetaModel;
import java.util.Optional;

public class InitializerDeclarationMetaModel
extends BodyDeclarationMetaModel {
    public PropertyMetaModel bodyPropertyMetaModel;
    public PropertyMetaModel isStaticPropertyMetaModel;

    InitializerDeclarationMetaModel(Optional<BaseNodeMetaModel> superBaseNodeMetaModel) {
        super(superBaseNodeMetaModel, InitializerDeclaration.class, "InitializerDeclaration", "com.github.javaparser.ast.body", false, false);
    }
}

