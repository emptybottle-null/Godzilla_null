/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.openxml4j.exceptions;

import org.apache.poi.openxml4j.exceptions.OpenXML4JRuntimeException;

public class InvalidOperationException
extends OpenXML4JRuntimeException {
    public InvalidOperationException(String message) {
        super(message);
    }

    public InvalidOperationException(String message, Throwable reason) {
        super(message, reason);
    }
}

