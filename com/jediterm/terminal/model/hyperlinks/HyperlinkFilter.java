/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.jediterm.terminal.model.hyperlinks;

import com.jediterm.terminal.model.hyperlinks.LinkResult;
import org.jetbrains.annotations.Nullable;

public interface HyperlinkFilter {
    @Nullable
    public LinkResult apply(String var1);
}

