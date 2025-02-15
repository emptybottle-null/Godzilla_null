/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.github.javaparser.ast.nodeTypes.modifiers;

import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.nodeTypes.modifiers.NodeWithPrivateModifier;
import com.github.javaparser.ast.nodeTypes.modifiers.NodeWithProtectedModifier;
import com.github.javaparser.ast.nodeTypes.modifiers.NodeWithPublicModifier;

public interface NodeWithAccessModifiers<N extends Node>
extends NodeWithPublicModifier<N>,
NodeWithPrivateModifier<N>,
NodeWithProtectedModifier<N> {
}

