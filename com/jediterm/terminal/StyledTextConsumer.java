/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.jediterm.terminal;

import com.jediterm.terminal.TextStyle;
import com.jediterm.terminal.model.CharBuffer;
import org.jetbrains.annotations.NotNull;

public interface StyledTextConsumer {
    public void consume(int var1, int var2, @NotNull TextStyle var3, @NotNull CharBuffer var4, int var5);

    public void consumeNul(int var1, int var2, int var3, @NotNull TextStyle var4, @NotNull CharBuffer var5, int var6);

    public void consumeQueue(int var1, int var2, int var3, int var4);
}

