/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.ss.usermodel;

import org.apache.poi.ss.usermodel.DataValidation;
import org.apache.poi.ss.usermodel.DataValidationConstraint;
import org.apache.poi.ss.util.CellRangeAddressList;

public interface DataValidationHelper {
    public DataValidationConstraint createFormulaListConstraint(String var1);

    public DataValidationConstraint createExplicitListConstraint(String[] var1);

    public DataValidationConstraint createNumericConstraint(int var1, int var2, String var3, String var4);

    public DataValidationConstraint createTextLengthConstraint(int var1, String var2, String var3);

    public DataValidationConstraint createDecimalConstraint(int var1, String var2, String var3);

    public DataValidationConstraint createIntegerConstraint(int var1, String var2, String var3);

    public DataValidationConstraint createDateConstraint(int var1, String var2, String var3, String var4);

    public DataValidationConstraint createTimeConstraint(int var1, String var2, String var3);

    public DataValidationConstraint createCustomConstraint(String var1);

    public DataValidation createValidation(DataValidationConstraint var1, CellRangeAddressList var2);
}

