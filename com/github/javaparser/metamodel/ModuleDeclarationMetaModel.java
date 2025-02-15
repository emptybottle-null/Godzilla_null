/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.github.javaparser.metamodel;

import com.github.javaparser.ast.modules.ModuleDeclaration;
import com.github.javaparser.metamodel.BaseNodeMetaModel;
import com.github.javaparser.metamodel.NodeMetaModel;
import com.github.javaparser.metamodel.PropertyMetaModel;
import java.util.Optional;

public class ModuleDeclarationMetaModel
extends NodeMetaModel {
    public PropertyMetaModel annotationsPropertyMetaModel;
    public PropertyMetaModel directivesPropertyMetaModel;
    public PropertyMetaModel isOpenPropertyMetaModel;
    public PropertyMetaModel namePropertyMetaModel;

    ModuleDeclarationMetaModel(Optional<BaseNodeMetaModel> superBaseNodeMetaModel) {
        super(superBaseNodeMetaModel, ModuleDeclaration.class, "ModuleDeclaration", "com.github.javaparser.ast.modules", false, false);
    }
}

