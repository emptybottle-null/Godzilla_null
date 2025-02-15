/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.jediterm.terminal;

import com.jediterm.terminal.StyledTextConsumer;
import com.jediterm.terminal.TextStyle;
import com.jediterm.terminal.model.CharBuffer;

public class StyledTextConsumerAdapter
implements StyledTextConsumer {
    @Override
    public void consume(int x, int y, TextStyle style, CharBuffer characters, int startRow) {
    }

    @Override
    public void consumeNul(int x, int y, int nulIndex, TextStyle style, CharBuffer characters, int startRow) {
    }

    @Override
    public void consumeQueue(int x, int y, int nulIndex, int startRow) {
    }
}

