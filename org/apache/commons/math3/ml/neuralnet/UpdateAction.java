/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.ml.neuralnet;

import org.apache.commons.math3.ml.neuralnet.Network;

public interface UpdateAction {
    public void update(Network var1, double[] var2);
}

