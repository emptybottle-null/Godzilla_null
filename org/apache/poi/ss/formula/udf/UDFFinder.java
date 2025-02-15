/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.ss.formula.udf;

import org.apache.poi.ss.formula.functions.FreeRefFunction;

public interface UDFFinder {
    public FreeRefFunction findFunction(String var1);
}

