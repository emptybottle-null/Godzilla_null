/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.hssf.usermodel;

import org.apache.poi.hssf.usermodel.DVConstraint;
import org.apache.poi.hssf.usermodel.HSSFDataValidation;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.ss.usermodel.DataValidation;
import org.apache.poi.ss.usermodel.DataValidationConstraint;
import org.apache.poi.ss.usermodel.DataValidationHelper;
import org.apache.poi.ss.util.CellRangeAddressList;

public class HSSFDataValidationHelper
implements DataValidationHelper {
    public HSSFDataValidationHelper(HSSFSheet sheet) {
    }

    @Override
    public DataValidationConstraint createDateConstraint(int operatorType, String formula1, String formula2, String dateFormat) {
        return DVConstraint.createDateConstraint(operatorType, formula1, formula2, dateFormat);
    }

    @Override
    public DataValidationConstraint createExplicitListConstraint(String[] listOfValues) {
        return DVConstraint.createExplicitListConstraint(listOfValues);
    }

    @Override
    public DataValidationConstraint createFormulaListConstraint(String listFormula) {
        return DVConstraint.createFormulaListConstraint(listFormula);
    }

    @Override
    public DataValidationConstraint createNumericConstraint(int validationType, int operatorType, String formula1, String formula2) {
        return DVConstraint.createNumericConstraint(validationType, operatorType, formula1, formula2);
    }

    @Override
    public DataValidationConstraint createIntegerConstraint(int operatorType, String formula1, String formula2) {
        return DVConstraint.createNumericConstraint(1, operatorType, formula1, formula2);
    }

    @Override
    public DataValidationConstraint createDecimalConstraint(int operatorType, String formula1, String formula2) {
        return DVConstraint.createNumericConstraint(2, operatorType, formula1, formula2);
    }

    @Override
    public DataValidationConstraint createTextLengthConstraint(int operatorType, String formula1, String formula2) {
        return DVConstraint.createNumericConstraint(6, operatorType, formula1, formula2);
    }

    @Override
    public DataValidationConstraint createTimeConstraint(int operatorType, String formula1, String formula2) {
        return DVConstraint.createTimeConstraint(operatorType, formula1, formula2);
    }

    @Override
    public DataValidationConstraint createCustomConstraint(String formula) {
        return DVConstraint.createCustomFormulaConstraint(formula);
    }

    @Override
    public DataValidation createValidation(DataValidationConstraint constraint, CellRangeAddressList cellRangeAddressList) {
        return new HSSFDataValidation(cellRangeAddressList, constraint);
    }
}

