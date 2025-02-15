/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.graphbuilder.curve;

import com.graphbuilder.curve.ValueVector;

public interface ValueVectorListener {
    public void valueChanged(ValueVector var1, int var2, double var3);

    public void valueInserted(ValueVector var1, int var2, double var3);

    public void valueRemoved(ValueVector var1, int var2, double var3);
}

