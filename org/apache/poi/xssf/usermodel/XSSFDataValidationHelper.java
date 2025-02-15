/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.xssf.usermodel;

import java.util.ArrayList;
import org.apache.poi.ss.usermodel.DataValidation;
import org.apache.poi.ss.usermodel.DataValidationConstraint;
import org.apache.poi.ss.usermodel.DataValidationHelper;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.CellRangeAddressList;
import org.apache.poi.xssf.usermodel.XSSFDataValidation;
import org.apache.poi.xssf.usermodel.XSSFDataValidationConstraint;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDataValidation;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.STDataValidationErrorStyle;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.STDataValidationOperator;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.STDataValidationType;

public class XSSFDataValidationHelper
implements DataValidationHelper {
    public XSSFDataValidationHelper(XSSFSheet xssfSheet) {
    }

    @Override
    public DataValidationConstraint createDateConstraint(int operatorType, String formula1, String formula2, String dateFormat) {
        return new XSSFDataValidationConstraint(4, operatorType, formula1, formula2);
    }

    @Override
    public DataValidationConstraint createDecimalConstraint(int operatorType, String formula1, String formula2) {
        return new XSSFDataValidationConstraint(2, operatorType, formula1, formula2);
    }

    @Override
    public DataValidationConstraint createExplicitListConstraint(String[] listOfValues) {
        return new XSSFDataValidationConstraint(listOfValues);
    }

    @Override
    public DataValidationConstraint createFormulaListConstraint(String listFormula) {
        return new XSSFDataValidationConstraint(3, listFormula);
    }

    @Override
    public DataValidationConstraint createNumericConstraint(int validationType, int operatorType, String formula1, String formula2) {
        if (validationType == 1) {
            return this.createIntegerConstraint(operatorType, formula1, formula2);
        }
        if (validationType == 2) {
            return this.createDecimalConstraint(operatorType, formula1, formula2);
        }
        if (validationType == 6) {
            return this.createTextLengthConstraint(operatorType, formula1, formula2);
        }
        return null;
    }

    @Override
    public DataValidationConstraint createIntegerConstraint(int operatorType, String formula1, String formula2) {
        return new XSSFDataValidationConstraint(1, operatorType, formula1, formula2);
    }

    @Override
    public DataValidationConstraint createTextLengthConstraint(int operatorType, String formula1, String formula2) {
        return new XSSFDataValidationConstraint(6, operatorType, formula1, formula2);
    }

    @Override
    public DataValidationConstraint createTimeConstraint(int operatorType, String formula1, String formula2) {
        return new XSSFDataValidationConstraint(5, operatorType, formula1, formula2);
    }

    @Override
    public DataValidationConstraint createCustomConstraint(String formula) {
        return new XSSFDataValidationConstraint(7, formula);
    }

    @Override
    public DataValidation createValidation(DataValidationConstraint constraint, CellRangeAddressList cellRangeAddressList) {
        XSSFDataValidationConstraint dataValidationConstraint = (XSSFDataValidationConstraint)constraint;
        CTDataValidation newDataValidation = CTDataValidation.Factory.newInstance();
        int validationType = constraint.getValidationType();
        switch (validationType) {
            case 3: {
                newDataValidation.setType(STDataValidationType.LIST);
                newDataValidation.setFormula1(constraint.getFormula1());
                break;
            }
            case 0: {
                newDataValidation.setType(STDataValidationType.NONE);
                break;
            }
            case 6: {
                newDataValidation.setType(STDataValidationType.TEXT_LENGTH);
                break;
            }
            case 4: {
                newDataValidation.setType(STDataValidationType.DATE);
                break;
            }
            case 1: {
                newDataValidation.setType(STDataValidationType.WHOLE);
                break;
            }
            case 2: {
                newDataValidation.setType(STDataValidationType.DECIMAL);
                break;
            }
            case 5: {
                newDataValidation.setType(STDataValidationType.TIME);
                break;
            }
            case 7: {
                newDataValidation.setType(STDataValidationType.CUSTOM);
                break;
            }
            default: {
                newDataValidation.setType(STDataValidationType.NONE);
            }
        }
        if (validationType != 0 && validationType != 3) {
            STDataValidationOperator.Enum op = XSSFDataValidation.operatorTypeMappings.get(constraint.getOperator());
            if (op != null) {
                newDataValidation.setOperator(op);
            }
            if (constraint.getFormula1() != null) {
                newDataValidation.setFormula1(constraint.getFormula1());
            }
            if (constraint.getFormula2() != null) {
                newDataValidation.setFormula2(constraint.getFormula2());
            }
        }
        CellRangeAddress[] cellRangeAddresses = cellRangeAddressList.getCellRangeAddresses();
        ArrayList<String> sqref = new ArrayList<String>();
        for (int i = 0; i < cellRangeAddresses.length; ++i) {
            CellRangeAddress cellRangeAddress = cellRangeAddresses[i];
            sqref.add(cellRangeAddress.formatAsString());
        }
        newDataValidation.setSqref(sqref);
        newDataValidation.setAllowBlank(true);
        newDataValidation.setErrorStyle(STDataValidationErrorStyle.STOP);
        return new XSSFDataValidation(dataValidationConstraint, cellRangeAddressList, newDataValidation);
    }
}

