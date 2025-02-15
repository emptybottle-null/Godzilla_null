/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.jediterm.terminal;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface TerminalCopyPasteHandler {
    public void setContents(@NotNull String var1, boolean var2);

    @Nullable
    public String getContents(boolean var1);
}

