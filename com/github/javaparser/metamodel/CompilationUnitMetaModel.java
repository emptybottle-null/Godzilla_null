/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.github.javaparser.metamodel;

import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.metamodel.BaseNodeMetaModel;
import com.github.javaparser.metamodel.NodeMetaModel;
import com.github.javaparser.metamodel.PropertyMetaModel;
import java.util.Optional;

public class CompilationUnitMetaModel
extends NodeMetaModel {
    public PropertyMetaModel importsPropertyMetaModel;
    public PropertyMetaModel modulePropertyMetaModel;
    public PropertyMetaModel packageDeclarationPropertyMetaModel;
    public PropertyMetaModel typesPropertyMetaModel;

    CompilationUnitMetaModel(Optional<BaseNodeMetaModel> superBaseNodeMetaModel) {
        super(superBaseNodeMetaModel, CompilationUnit.class, "CompilationUnit", "com.github.javaparser.ast", false, false);
    }
}

