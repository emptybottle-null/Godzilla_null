/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi;

public class EncryptedDocumentException
extends IllegalStateException {
    private static final long serialVersionUID = 7276950444540469193L;

    public EncryptedDocumentException(String s) {
        super(s);
    }

    public EncryptedDocumentException(String message, Throwable cause) {
        super(message, cause);
    }

    public EncryptedDocumentException(Throwable cause) {
        super(cause);
    }
}

