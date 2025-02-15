/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.openxml4j.exceptions;

import org.apache.poi.UnsupportedFileFormatException;

public class NotOfficeXmlFileException
extends UnsupportedFileFormatException {
    public NotOfficeXmlFileException(String message) {
        super(message);
    }

    public NotOfficeXmlFileException(String message, Throwable cause) {
        super(message, cause);
    }
}

