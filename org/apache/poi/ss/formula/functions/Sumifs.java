/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.ss.formula.functions;

import org.apache.poi.ss.formula.functions.Baseifs;
import org.apache.poi.ss.formula.functions.FreeRefFunction;

public final class Sumifs
extends Baseifs {
    public static final FreeRefFunction instance = new Sumifs();

    @Override
    protected boolean hasInitialRange() {
        return true;
    }
}

