/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.ss.usermodel;

import org.apache.poi.ss.usermodel.DataValidationConstraint;
import org.apache.poi.ss.util.CellRangeAddressList;

public interface DataValidation {
    public DataValidationConstraint getValidationConstraint();

    public void setErrorStyle(int var1);

    public int getErrorStyle();

    public void setEmptyCellAllowed(boolean var1);

    public boolean getEmptyCellAllowed();

    public void setSuppressDropDownArrow(boolean var1);

    public boolean getSuppressDropDownArrow();

    public void setShowPromptBox(boolean var1);

    public boolean getShowPromptBox();

    public void setShowErrorBox(boolean var1);

    public boolean getShowErrorBox();

    public void createPromptBox(String var1, String var2);

    public String getPromptBoxTitle();

    public String getPromptBoxText();

    public void createErrorBox(String var1, String var2);

    public String getErrorBoxTitle();

    public String getErrorBoxText();

    public CellRangeAddressList getRegions();

    public static final class ErrorStyle {
        public static final int STOP = 0;
        public static final int WARNING = 1;
        public static final int INFO = 2;
    }
}

