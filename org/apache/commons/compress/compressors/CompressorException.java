/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.compress.compressors;

public class CompressorException
extends Exception {
    private static final long serialVersionUID = -2932901310255908814L;

    public CompressorException(String message) {
        super(message);
    }

    public CompressorException(String message, Throwable cause) {
        super(message, cause);
    }
}

