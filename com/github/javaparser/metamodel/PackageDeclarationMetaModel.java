/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.github.javaparser.metamodel;

import com.github.javaparser.ast.PackageDeclaration;
import com.github.javaparser.metamodel.BaseNodeMetaModel;
import com.github.javaparser.metamodel.NodeMetaModel;
import com.github.javaparser.metamodel.PropertyMetaModel;
import java.util.Optional;

public class PackageDeclarationMetaModel
extends NodeMetaModel {
    public PropertyMetaModel annotationsPropertyMetaModel;
    public PropertyMetaModel namePropertyMetaModel;

    PackageDeclarationMetaModel(Optional<BaseNodeMetaModel> superBaseNodeMetaModel) {
        super(superBaseNodeMetaModel, PackageDeclaration.class, "PackageDeclaration", "com.github.javaparser.ast", false, false);
    }
}

