/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.io.input;

import org.apache.commons.io.input.CircularInputStream;

public class InfiniteCircularInputStream
extends CircularInputStream {
    public InfiniteCircularInputStream(byte[] repeatContent) {
        super(repeatContent, -1L);
    }
}

