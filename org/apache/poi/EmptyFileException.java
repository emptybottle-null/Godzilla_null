/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi;

public class EmptyFileException
extends IllegalArgumentException {
    private static final long serialVersionUID = 1536449292174360166L;

    public EmptyFileException() {
        super("The supplied file was empty (zero bytes long)");
    }
}

