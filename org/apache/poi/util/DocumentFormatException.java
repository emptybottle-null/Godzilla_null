/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.util;

public class DocumentFormatException
extends RuntimeException {
    public DocumentFormatException(String exception) {
        super(exception);
    }

    public DocumentFormatException(String exception, Throwable thr) {
        super(exception, thr);
    }

    public DocumentFormatException(Throwable thr) {
        super(thr);
    }

    public static void check(boolean assertTrue, String message) {
        if (!assertTrue) {
            throw new DocumentFormatException(message);
        }
    }
}

