/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.github.javaparser.metamodel;

import com.github.javaparser.ast.modules.ModuleUsesDirective;
import com.github.javaparser.metamodel.BaseNodeMetaModel;
import com.github.javaparser.metamodel.ModuleDirectiveMetaModel;
import com.github.javaparser.metamodel.PropertyMetaModel;
import java.util.Optional;

public class ModuleUsesDirectiveMetaModel
extends ModuleDirectiveMetaModel {
    public PropertyMetaModel namePropertyMetaModel;

    ModuleUsesDirectiveMetaModel(Optional<BaseNodeMetaModel> superBaseNodeMetaModel) {
        super(superBaseNodeMetaModel, ModuleUsesDirective.class, "ModuleUsesDirective", "com.github.javaparser.ast.modules", false, false);
    }
}

