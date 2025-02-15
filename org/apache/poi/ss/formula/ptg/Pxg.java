/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.ss.formula.ptg;

public interface Pxg {
    public int getExternalWorkbookNumber();

    public String getSheetName();

    public void setSheetName(String var1);

    public String toFormulaString();
}

