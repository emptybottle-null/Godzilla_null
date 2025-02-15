/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.ooxml;

public final class POIXMLException
extends RuntimeException {
    public POIXMLException() {
    }

    public POIXMLException(String msg) {
        super(msg);
    }

    public POIXMLException(String msg, Throwable cause) {
        super(msg, cause);
    }

    public POIXMLException(Throwable cause) {
        super(cause);
    }
}

