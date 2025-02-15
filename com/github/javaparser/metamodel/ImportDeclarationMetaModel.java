/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.github.javaparser.metamodel;

import com.github.javaparser.ast.ImportDeclaration;
import com.github.javaparser.metamodel.BaseNodeMetaModel;
import com.github.javaparser.metamodel.NodeMetaModel;
import com.github.javaparser.metamodel.PropertyMetaModel;
import java.util.Optional;

public class ImportDeclarationMetaModel
extends NodeMetaModel {
    public PropertyMetaModel isAsteriskPropertyMetaModel;
    public PropertyMetaModel isStaticPropertyMetaModel;
    public PropertyMetaModel namePropertyMetaModel;

    ImportDeclarationMetaModel(Optional<BaseNodeMetaModel> superBaseNodeMetaModel) {
        super(superBaseNodeMetaModel, ImportDeclaration.class, "ImportDeclaration", "com.github.javaparser.ast", false, false);
    }
}

