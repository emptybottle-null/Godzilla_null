/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package net.miginfocom.layout;

import net.miginfocom.layout.BoundSize;
import net.miginfocom.layout.ComponentWrapper;
import net.miginfocom.layout.UnitValue;

public abstract class LayoutCallback {
    public UnitValue[] getPosition(ComponentWrapper comp) {
        return null;
    }

    public BoundSize[] getSize(ComponentWrapper comp) {
        return null;
    }

    public void correctBounds(ComponentWrapper comp) {
    }
}

