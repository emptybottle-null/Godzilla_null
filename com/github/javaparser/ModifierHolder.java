/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.github.javaparser;

import com.github.javaparser.JavaToken;
import com.github.javaparser.ast.Modifier;
import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.expr.AnnotationExpr;
import com.github.javaparser.utils.Utils;

class ModifierHolder {
    final NodeList<Modifier> modifiers;
    final NodeList<AnnotationExpr> annotations;
    final JavaToken begin;

    ModifierHolder(JavaToken begin, NodeList<Modifier> modifiers, NodeList<AnnotationExpr> annotations) {
        this.begin = begin;
        this.modifiers = Utils.assertNotNull(modifiers);
        this.annotations = annotations;
    }
}

