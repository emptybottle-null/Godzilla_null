/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.linear;

public interface RealMatrixChangingVisitor {
    public void start(int var1, int var2, int var3, int var4, int var5, int var6);

    public double visit(int var1, int var2, double var3);

    public double end();
}

