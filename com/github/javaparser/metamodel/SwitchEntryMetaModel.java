/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.github.javaparser.metamodel;

import com.github.javaparser.ast.stmt.SwitchEntry;
import com.github.javaparser.metamodel.BaseNodeMetaModel;
import com.github.javaparser.metamodel.NodeMetaModel;
import com.github.javaparser.metamodel.PropertyMetaModel;
import java.util.Optional;

public class SwitchEntryMetaModel
extends NodeMetaModel {
    public PropertyMetaModel labelsPropertyMetaModel;
    public PropertyMetaModel statementsPropertyMetaModel;
    public PropertyMetaModel typePropertyMetaModel;

    SwitchEntryMetaModel(Optional<BaseNodeMetaModel> superBaseNodeMetaModel) {
        super(superBaseNodeMetaModel, SwitchEntry.class, "SwitchEntry", "com.github.javaparser.ast.stmt", false, false);
    }
}

