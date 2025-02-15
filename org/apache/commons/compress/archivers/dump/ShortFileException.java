/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.compress.archivers.dump;

import org.apache.commons.compress.archivers.dump.DumpArchiveException;

public class ShortFileException
extends DumpArchiveException {
    private static final long serialVersionUID = 1L;

    public ShortFileException() {
        super("unexpected EOF");
    }
}

