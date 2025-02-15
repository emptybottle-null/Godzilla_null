/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.github.javaparser.metamodel;

import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.metamodel.BaseNodeMetaModel;
import com.github.javaparser.metamodel.PropertyMetaModel;
import com.github.javaparser.metamodel.TypeDeclarationMetaModel;
import java.util.Optional;

public class ClassOrInterfaceDeclarationMetaModel
extends TypeDeclarationMetaModel {
    public PropertyMetaModel extendedTypesPropertyMetaModel;
    public PropertyMetaModel implementedTypesPropertyMetaModel;
    public PropertyMetaModel isInterfacePropertyMetaModel;
    public PropertyMetaModel typeParametersPropertyMetaModel;

    ClassOrInterfaceDeclarationMetaModel(Optional<BaseNodeMetaModel> superBaseNodeMetaModel) {
        super(superBaseNodeMetaModel, ClassOrInterfaceDeclaration.class, "ClassOrInterfaceDeclaration", "com.github.javaparser.ast.body", false, false);
    }
}

