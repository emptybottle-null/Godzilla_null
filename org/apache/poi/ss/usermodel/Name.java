/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.ss.usermodel;

public interface Name {
    public String getSheetName();

    public String getNameName();

    public void setNameName(String var1);

    public String getRefersToFormula();

    public void setRefersToFormula(String var1);

    public boolean isFunctionName();

    public boolean isDeleted();

    public void setSheetIndex(int var1);

    public int getSheetIndex();

    public String getComment();

    public void setComment(String var1);

    public void setFunction(boolean var1);
}

