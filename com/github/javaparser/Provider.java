/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.github.javaparser;

import java.io.Closeable;
import java.io.IOException;

public interface Provider
extends Closeable {
    public int read(char[] var1, int var2, int var3) throws IOException;
}

