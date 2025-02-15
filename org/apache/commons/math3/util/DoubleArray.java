/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.util;

public interface DoubleArray {
    public int getNumElements();

    public double getElement(int var1);

    public void setElement(int var1, double var2);

    public void addElement(double var1);

    public void addElements(double[] var1);

    public double addElementRolling(double var1);

    public double[] getElements();

    public void clear();
}

