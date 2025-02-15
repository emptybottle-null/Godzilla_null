/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi;

import org.apache.poi.UnsupportedFileFormatException;

public abstract class OldFileFormatException
extends UnsupportedFileFormatException {
    private static final long serialVersionUID = 7849681804154571175L;

    public OldFileFormatException(String s) {
        super(s);
    }
}

