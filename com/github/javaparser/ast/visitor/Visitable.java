/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.github.javaparser.ast.visitor;

import com.github.javaparser.ast.visitor.GenericVisitor;
import com.github.javaparser.ast.visitor.VoidVisitor;

public interface Visitable {
    public <R, A> R accept(GenericVisitor<R, A> var1, A var2);

    public <A> void accept(VoidVisitor<A> var1, A var2);
}

