/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.google.common.base;

import com.google.common.annotations.GwtIncompatible;
import com.google.common.base.CommonPattern;

@GwtIncompatible
interface PatternCompiler {
    public CommonPattern compile(String var1);

    public boolean isPcreLike();
}

