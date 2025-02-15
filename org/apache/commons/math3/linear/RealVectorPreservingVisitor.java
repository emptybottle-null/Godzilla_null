/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.linear;

public interface RealVectorPreservingVisitor {
    public void start(int var1, int var2, int var3);

    public void visit(int var1, double var2);

    public double end();
}

