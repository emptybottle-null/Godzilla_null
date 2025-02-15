/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.ss.usermodel;

import org.apache.poi.ss.usermodel.Font;

public interface RichTextString {
    public void applyFont(int var1, int var2, short var3);

    public void applyFont(int var1, int var2, Font var3);

    public void applyFont(Font var1);

    public void clearFormatting();

    public String getString();

    public int length();

    public int numFormattingRuns();

    public int getIndexOfFormattingRun(int var1);

    public void applyFont(short var1);
}

