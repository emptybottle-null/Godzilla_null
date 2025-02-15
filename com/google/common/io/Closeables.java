/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.google.common.io;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.annotations.VisibleForTesting;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.checkerframework.checker.nullness.qual.Nullable;

@Beta
@GwtIncompatible
public final class Closeables {
    @VisibleForTesting
    static final Logger logger = Logger.getLogger(Closeables.class.getName());

    private Closeables() {
    }

    public static void close(@Nullable Closeable closeable, boolean swallowIOException) throws IOException {
        if (closeable == null) {
            return;
        }
        try {
            closeable.close();
        } catch (IOException e) {
            if (swallowIOException) {
                logger.log(Level.WARNING, "IOException thrown while closing Closeable.", e);
            }
            throw e;
        }
    }

    public static void closeQuietly(@Nullable InputStream inputStream) {
        try {
            Closeables.close(inputStream, true);
        } catch (IOException impossible) {
            throw new AssertionError((Object)impossible);
        }
    }

    public static void closeQuietly(@Nullable Reader reader) {
        try {
            Closeables.close(reader, true);
        } catch (IOException impossible) {
            throw new AssertionError((Object)impossible);
        }
    }
}

