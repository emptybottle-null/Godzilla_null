/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.xdgf.exceptions;

import org.apache.poi.ooxml.POIXMLDocumentPart;
import org.apache.poi.ooxml.POIXMLException;

public class XDGFException {
    public static POIXMLException error(String message, Object o) {
        return new POIXMLException(o + ": " + message);
    }

    public static POIXMLException error(String message, Object o, Throwable t) {
        return new POIXMLException(o + ": " + message, t);
    }

    public static POIXMLException wrap(POIXMLDocumentPart part, POIXMLException e) {
        return new POIXMLException(part.getPackagePart().getPartName() + ": " + e.getMessage(), e.getCause() == null ? e : e.getCause());
    }

    public static POIXMLException wrap(String where, POIXMLException e) {
        return new POIXMLException(where + ": " + e.getMessage(), e.getCause() == null ? e : e.getCause());
    }
}

