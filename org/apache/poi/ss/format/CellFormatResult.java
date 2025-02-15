/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.ss.format;

import java.awt.Color;

public class CellFormatResult {
    public final boolean applies;
    public final String text;
    public final Color textColor;

    public CellFormatResult(boolean applies, String text, Color textColor) throws IllegalArgumentException {
        this.applies = applies;
        if (text == null) {
            throw new IllegalArgumentException("CellFormatResult text may not be null");
        }
        this.text = text;
        this.textColor = applies ? textColor : null;
    }
}

