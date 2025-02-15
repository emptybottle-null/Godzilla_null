/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.codec;

public class EncoderException
extends Exception {
    private static final long serialVersionUID = 1L;

    public EncoderException() {
    }

    public EncoderException(String message) {
        super(message);
    }

    public EncoderException(String message, Throwable cause) {
        super(message, cause);
    }

    public EncoderException(Throwable cause) {
        super(cause);
    }
}

