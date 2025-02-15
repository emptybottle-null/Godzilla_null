/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.github.javaparser.metamodel;

import com.github.javaparser.ast.stmt.CatchClause;
import com.github.javaparser.metamodel.BaseNodeMetaModel;
import com.github.javaparser.metamodel.NodeMetaModel;
import com.github.javaparser.metamodel.PropertyMetaModel;
import java.util.Optional;

public class CatchClauseMetaModel
extends NodeMetaModel {
    public PropertyMetaModel bodyPropertyMetaModel;
    public PropertyMetaModel parameterPropertyMetaModel;

    CatchClauseMetaModel(Optional<BaseNodeMetaModel> superBaseNodeMetaModel) {
        super(superBaseNodeMetaModel, CatchClause.class, "CatchClause", "com.github.javaparser.ast.stmt", false, false);
    }
}

