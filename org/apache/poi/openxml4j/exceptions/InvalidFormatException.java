/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.openxml4j.exceptions;

import org.apache.poi.openxml4j.exceptions.OpenXML4JException;

public final class InvalidFormatException
extends OpenXML4JException {
    public InvalidFormatException(String message) {
        super(message);
    }

    public InvalidFormatException(String message, Throwable cause) {
        super(message, cause);
    }
}

