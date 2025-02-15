/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.github.javaparser.metamodel;

import com.github.javaparser.ast.modules.ModuleOpensDirective;
import com.github.javaparser.metamodel.BaseNodeMetaModel;
import com.github.javaparser.metamodel.ModuleDirectiveMetaModel;
import com.github.javaparser.metamodel.PropertyMetaModel;
import java.util.Optional;

public class ModuleOpensDirectiveMetaModel
extends ModuleDirectiveMetaModel {
    public PropertyMetaModel moduleNamesPropertyMetaModel;
    public PropertyMetaModel namePropertyMetaModel;

    ModuleOpensDirectiveMetaModel(Optional<BaseNodeMetaModel> superBaseNodeMetaModel) {
        super(superBaseNodeMetaModel, ModuleOpensDirective.class, "ModuleOpensDirective", "com.github.javaparser.ast.modules", false, false);
    }
}

