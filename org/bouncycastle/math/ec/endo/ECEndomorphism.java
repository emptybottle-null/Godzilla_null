/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.math.ec.endo;

import org.bouncycastle.math.ec.ECPointMap;

public interface ECEndomorphism {
    public ECPointMap getPointMap();

    public boolean hasEfficientPointMap();
}

