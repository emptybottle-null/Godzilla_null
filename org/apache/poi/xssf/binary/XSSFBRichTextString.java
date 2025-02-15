/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.xssf.binary;

import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.util.Internal;
import org.apache.poi.util.NotImplemented;
import org.apache.poi.xssf.usermodel.XSSFRichTextString;

@Internal
class XSSFBRichTextString
extends XSSFRichTextString {
    private final String string;

    XSSFBRichTextString(String string) {
        this.string = string;
    }

    @Override
    @NotImplemented
    public void applyFont(int startIndex, int endIndex, short fontIndex) {
    }

    @Override
    @NotImplemented
    public void applyFont(int startIndex, int endIndex, Font font) {
    }

    @Override
    @NotImplemented
    public void applyFont(Font font) {
    }

    @Override
    @NotImplemented
    public void clearFormatting() {
    }

    @Override
    public String getString() {
        return this.string;
    }

    @Override
    public int length() {
        return this.string.length();
    }

    @Override
    @NotImplemented
    public int numFormattingRuns() {
        return 0;
    }

    @Override
    @NotImplemented
    public int getIndexOfFormattingRun(int index) {
        return 0;
    }

    @Override
    @NotImplemented
    public void applyFont(short fontIndex) {
    }
}

