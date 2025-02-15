/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.github.javaparser.metamodel;

import com.github.javaparser.ast.modules.ModuleProvidesDirective;
import com.github.javaparser.metamodel.BaseNodeMetaModel;
import com.github.javaparser.metamodel.ModuleDirectiveMetaModel;
import com.github.javaparser.metamodel.PropertyMetaModel;
import java.util.Optional;

public class ModuleProvidesDirectiveMetaModel
extends ModuleDirectiveMetaModel {
    public PropertyMetaModel namePropertyMetaModel;
    public PropertyMetaModel withPropertyMetaModel;

    ModuleProvidesDirectiveMetaModel(Optional<BaseNodeMetaModel> superBaseNodeMetaModel) {
        super(superBaseNodeMetaModel, ModuleProvidesDirective.class, "ModuleProvidesDirective", "com.github.javaparser.ast.modules", false, false);
    }
}

