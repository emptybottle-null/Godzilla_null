/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.google.common.io;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtIncompatible;
import java.nio.file.FileSystemException;
import org.checkerframework.checker.nullness.qual.Nullable;

@Beta
@GwtIncompatible
public final class InsecureRecursiveDeleteException
extends FileSystemException {
    public InsecureRecursiveDeleteException(@Nullable String file) {
        super(file, null, "unable to guarantee security of recursive delete");
    }
}

