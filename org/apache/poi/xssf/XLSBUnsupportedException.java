/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.xssf;

import org.apache.poi.UnsupportedFileFormatException;

public class XLSBUnsupportedException
extends UnsupportedFileFormatException {
    private static final long serialVersionUID = 7849681804154571175L;
    public static final String MESSAGE = ".XLSB Binary Workbooks are not supported";

    public XLSBUnsupportedException() {
        super(MESSAGE);
    }
}

