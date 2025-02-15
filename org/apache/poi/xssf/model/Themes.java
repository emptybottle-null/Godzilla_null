/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.xssf.model;

import org.apache.poi.xssf.usermodel.XSSFColor;

public interface Themes {
    public XSSFColor getThemeColor(int var1);

    public void inheritFromThemeAsRequired(XSSFColor var1);
}

