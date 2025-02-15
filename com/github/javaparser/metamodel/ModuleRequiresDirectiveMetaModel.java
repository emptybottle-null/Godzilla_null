/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.github.javaparser.metamodel;

import com.github.javaparser.ast.modules.ModuleRequiresDirective;
import com.github.javaparser.metamodel.BaseNodeMetaModel;
import com.github.javaparser.metamodel.ModuleDirectiveMetaModel;
import com.github.javaparser.metamodel.PropertyMetaModel;
import java.util.Optional;

public class ModuleRequiresDirectiveMetaModel
extends ModuleDirectiveMetaModel {
    public PropertyMetaModel modifiersPropertyMetaModel;
    public PropertyMetaModel namePropertyMetaModel;

    ModuleRequiresDirectiveMetaModel(Optional<BaseNodeMetaModel> superBaseNodeMetaModel) {
        super(superBaseNodeMetaModel, ModuleRequiresDirective.class, "ModuleRequiresDirective", "com.github.javaparser.ast.modules", false, false);
    }
}

