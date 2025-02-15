/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.util;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import org.apache.poi.util.Internal;

@Internal
public class ReplacingInputStream
extends FilterInputStream {
    final int[] buf;
    private int matchedIndex;
    private int unbufferIndex;
    private int replacedIndex;
    private final byte[] pattern;
    private final byte[] replacement;
    private State state = State.NOT_MATCHED;

    public ReplacingInputStream(InputStream in, String pattern, String replacement) {
        this(in, pattern.getBytes(StandardCharsets.UTF_8), replacement == null ? null : replacement.getBytes(StandardCharsets.UTF_8));
    }

    public ReplacingInputStream(InputStream in, byte[] pattern, byte[] replacement) {
        super(in);
        if (pattern == null || pattern.length == 0) {
            throw new IllegalArgumentException("pattern length should be > 0");
        }
        this.pattern = pattern;
        this.replacement = replacement;
        this.buf = new int[pattern.length];
    }

    @Override
    public int read(byte[] b, int off, int len) throws IOException {
        int i;
        if (b == null) {
            throw new NullPointerException();
        }
        if (off < 0 || len < 0 || len > b.length - off) {
            throw new IndexOutOfBoundsException();
        }
        if (len == 0) {
            return 0;
        }
        int c = this.read();
        if (c == -1) {
            return -1;
        }
        b[off] = (byte)c;
        for (i = 1; i < len && (c = this.read()) != -1; ++i) {
            b[off + i] = (byte)c;
        }
        return i;
    }

    @Override
    public int read(byte[] b) throws IOException {
        return this.read(b, 0, b.length);
    }

    @Override
    public int read() throws IOException {
        switch (this.state) {
            default: {
                int next = super.read();
                if (this.pattern[0] != next) {
                    return next;
                }
                Arrays.fill(this.buf, 0);
                this.matchedIndex = 0;
                this.buf[this.matchedIndex++] = next;
                if (this.pattern.length == 1) {
                    this.state = State.REPLACING;
                    this.replacedIndex = 0;
                } else {
                    this.state = State.MATCHING;
                }
                return this.read();
            }
            case MATCHING: {
                int next = super.read();
                if (this.pattern[this.matchedIndex] == next) {
                    this.buf[this.matchedIndex++] = next;
                    if (this.matchedIndex == this.pattern.length) {
                        if (this.replacement == null || this.replacement.length == 0) {
                            this.state = State.NOT_MATCHED;
                            this.matchedIndex = 0;
                        } else {
                            this.state = State.REPLACING;
                            this.replacedIndex = 0;
                        }
                    }
                } else {
                    this.buf[this.matchedIndex++] = next;
                    this.state = State.UNBUFFER;
                    this.unbufferIndex = 0;
                }
                return this.read();
            }
            case REPLACING: {
                byte next = this.replacement[this.replacedIndex++];
                if (this.replacedIndex == this.replacement.length) {
                    this.state = State.NOT_MATCHED;
                    this.replacedIndex = 0;
                }
                return next;
            }
            case UNBUFFER: 
        }
        int next = this.buf[this.unbufferIndex++];
        if (this.unbufferIndex == this.matchedIndex) {
            this.state = State.NOT_MATCHED;
            this.matchedIndex = 0;
        }
        return next;
    }

    public String toString() {
        return this.state.name() + " " + this.matchedIndex + " " + this.replacedIndex + " " + this.unbufferIndex;
    }

    private static enum State {
        NOT_MATCHED,
        MATCHING,
        REPLACING,
        UNBUFFER;

    }
}

