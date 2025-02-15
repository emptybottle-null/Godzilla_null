/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.github.javaparser.metamodel;

import com.github.javaparser.ast.modules.ModuleExportsDirective;
import com.github.javaparser.metamodel.BaseNodeMetaModel;
import com.github.javaparser.metamodel.ModuleDirectiveMetaModel;
import com.github.javaparser.metamodel.PropertyMetaModel;
import java.util.Optional;

public class ModuleExportsDirectiveMetaModel
extends ModuleDirectiveMetaModel {
    public PropertyMetaModel moduleNamesPropertyMetaModel;
    public PropertyMetaModel namePropertyMetaModel;

    ModuleExportsDirectiveMetaModel(Optional<BaseNodeMetaModel> superBaseNodeMetaModel) {
        super(superBaseNodeMetaModel, ModuleExportsDirective.class, "ModuleExportsDirective", "com.github.javaparser.ast.modules", false, false);
    }
}

