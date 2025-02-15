/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.wp.usermodel;

public interface Paragraph {
    public int getIndentFromRight();

    public void setIndentFromRight(int var1);

    public int getIndentFromLeft();

    public void setIndentFromLeft(int var1);

    public int getFirstLineIndent();

    public void setFirstLineIndent(int var1);

    public int getFontAlignment();

    public void setFontAlignment(int var1);

    public boolean isWordWrapped();

    public void setWordWrapped(boolean var1);
}

