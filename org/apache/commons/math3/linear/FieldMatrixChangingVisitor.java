/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.linear;

import org.apache.commons.math3.FieldElement;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public interface FieldMatrixChangingVisitor<T extends FieldElement<?>> {
    public void start(int var1, int var2, int var3, int var4, int var5, int var6);

    public T visit(int var1, int var2, T var3);

    public T end();
}

