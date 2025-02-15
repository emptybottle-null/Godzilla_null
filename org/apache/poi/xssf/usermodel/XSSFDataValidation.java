/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.xssf.usermodel;

import java.util.HashMap;
import java.util.Map;
import org.apache.commons.collections4.MapUtils;
import org.apache.poi.ss.usermodel.DataValidation;
import org.apache.poi.ss.usermodel.DataValidationConstraint;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.CellRangeAddressList;
import org.apache.poi.xssf.usermodel.XSSFDataValidationConstraint;
import org.apache.xmlbeans.StringEnumAbstractBase;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDataValidation;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.STDataValidationErrorStyle;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.STDataValidationOperator;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.STDataValidationType;

public class XSSFDataValidation
implements DataValidation {
    private static final int MAX_TEXT_LENGTH = 255;
    private CTDataValidation ctDdataValidation;
    private XSSFDataValidationConstraint validationConstraint;
    private CellRangeAddressList regions;
    static Map<Integer, STDataValidationOperator.Enum> operatorTypeMappings = new HashMap<Integer, STDataValidationOperator.Enum>();
    static Map<STDataValidationOperator.Enum, Integer> operatorTypeReverseMappings = new HashMap<STDataValidationOperator.Enum, Integer>();
    static Map<Integer, STDataValidationType.Enum> validationTypeMappings = new HashMap<Integer, STDataValidationType.Enum>();
    static Map<STDataValidationType.Enum, Integer> validationTypeReverseMappings = new HashMap<STDataValidationType.Enum, Integer>();
    static Map<Integer, STDataValidationErrorStyle.Enum> errorStyleMappings = new HashMap<Integer, STDataValidationErrorStyle.Enum>();
    static Map<STDataValidationErrorStyle.Enum, Integer> reverseErrorStyleMappings;

    XSSFDataValidation(CellRangeAddressList regions, CTDataValidation ctDataValidation) {
        this(XSSFDataValidation.getConstraint(ctDataValidation), regions, ctDataValidation);
    }

    public XSSFDataValidation(XSSFDataValidationConstraint constraint, CellRangeAddressList regions, CTDataValidation ctDataValidation) {
        this.validationConstraint = constraint;
        this.ctDdataValidation = ctDataValidation;
        this.regions = regions;
    }

    CTDataValidation getCtDdataValidation() {
        return this.ctDdataValidation;
    }

    @Override
    public void createErrorBox(String title, String text) {
        if (title != null && title.length() > 255) {
            throw new IllegalStateException("Error-title cannot be longer than 32 characters, but had: " + title);
        }
        if (text != null && text.length() > 255) {
            throw new IllegalStateException("Error-text cannot be longer than 255 characters, but had: " + text);
        }
        this.ctDdataValidation.setErrorTitle(this.encodeUtf(title));
        this.ctDdataValidation.setError(this.encodeUtf(text));
    }

    @Override
    public void createPromptBox(String title, String text) {
        if (title != null && title.length() > 255) {
            throw new IllegalStateException("Error-title cannot be longer than 32 characters, but had: " + title);
        }
        if (text != null && text.length() > 255) {
            throw new IllegalStateException("Error-text cannot be longer than 255 characters, but had: " + text);
        }
        this.ctDdataValidation.setPromptTitle(this.encodeUtf(title));
        this.ctDdataValidation.setPrompt(this.encodeUtf(text));
    }

    private String encodeUtf(String text) {
        if (text == null) {
            return null;
        }
        StringBuilder builder = new StringBuilder();
        for (char c : text.toCharArray()) {
            if (c < ' ') {
                builder.append("_x").append(c < '\u0010' ? "000" : "00").append(Integer.toHexString(c)).append("_");
                continue;
            }
            builder.append(c);
        }
        return builder.toString();
    }

    @Override
    public boolean getEmptyCellAllowed() {
        return this.ctDdataValidation.getAllowBlank();
    }

    @Override
    public String getErrorBoxText() {
        return this.ctDdataValidation.getError();
    }

    @Override
    public String getErrorBoxTitle() {
        return this.ctDdataValidation.getErrorTitle();
    }

    @Override
    public int getErrorStyle() {
        return reverseErrorStyleMappings.get(this.ctDdataValidation.getErrorStyle());
    }

    @Override
    public String getPromptBoxText() {
        return this.ctDdataValidation.getPrompt();
    }

    @Override
    public String getPromptBoxTitle() {
        return this.ctDdataValidation.getPromptTitle();
    }

    @Override
    public boolean getShowErrorBox() {
        return this.ctDdataValidation.getShowErrorMessage();
    }

    @Override
    public boolean getShowPromptBox() {
        return this.ctDdataValidation.getShowInputMessage();
    }

    @Override
    public boolean getSuppressDropDownArrow() {
        return !this.ctDdataValidation.getShowDropDown();
    }

    @Override
    public DataValidationConstraint getValidationConstraint() {
        return this.validationConstraint;
    }

    @Override
    public void setEmptyCellAllowed(boolean allowed) {
        this.ctDdataValidation.setAllowBlank(allowed);
    }

    @Override
    public void setErrorStyle(int errorStyle) {
        this.ctDdataValidation.setErrorStyle(errorStyleMappings.get(errorStyle));
    }

    @Override
    public void setShowErrorBox(boolean show) {
        this.ctDdataValidation.setShowErrorMessage(show);
    }

    @Override
    public void setShowPromptBox(boolean show) {
        this.ctDdataValidation.setShowInputMessage(show);
    }

    @Override
    public void setSuppressDropDownArrow(boolean suppress) {
        if (this.validationConstraint.getValidationType() == 3) {
            this.ctDdataValidation.setShowDropDown(!suppress);
        }
    }

    @Override
    public CellRangeAddressList getRegions() {
        return this.regions;
    }

    public String prettyPrint() {
        StringBuilder builder = new StringBuilder();
        for (CellRangeAddress address : this.regions.getCellRangeAddresses()) {
            builder.append(address.formatAsString());
        }
        builder.append(" => ");
        builder.append(this.validationConstraint.prettyPrint());
        return builder.toString();
    }

    private static XSSFDataValidationConstraint getConstraint(CTDataValidation ctDataValidation) {
        String formula1 = ctDataValidation.getFormula1();
        String formula2 = ctDataValidation.getFormula2();
        STDataValidationOperator.Enum operator2 = ctDataValidation.getOperator();
        STDataValidationType.Enum type = ctDataValidation.getType();
        Integer validationType = validationTypeReverseMappings.get(type);
        Integer operatorType = operatorTypeReverseMappings.get(operator2);
        return new XSSFDataValidationConstraint(validationType, operatorType, formula1, formula2);
    }

    static {
        errorStyleMappings.put(2, STDataValidationErrorStyle.INFORMATION);
        errorStyleMappings.put(0, STDataValidationErrorStyle.STOP);
        errorStyleMappings.put(1, STDataValidationErrorStyle.WARNING);
        reverseErrorStyleMappings = MapUtils.invertMap(errorStyleMappings);
        operatorTypeMappings.put(0, STDataValidationOperator.BETWEEN);
        operatorTypeMappings.put(1, STDataValidationOperator.NOT_BETWEEN);
        operatorTypeMappings.put(2, STDataValidationOperator.EQUAL);
        operatorTypeMappings.put(3, STDataValidationOperator.NOT_EQUAL);
        operatorTypeMappings.put(4, STDataValidationOperator.GREATER_THAN);
        operatorTypeMappings.put(6, STDataValidationOperator.GREATER_THAN_OR_EQUAL);
        operatorTypeMappings.put(5, STDataValidationOperator.LESS_THAN);
        operatorTypeMappings.put(7, STDataValidationOperator.LESS_THAN_OR_EQUAL);
        for (Map.Entry<Integer, STDataValidationOperator.Enum> entry : operatorTypeMappings.entrySet()) {
            operatorTypeReverseMappings.put(entry.getValue(), entry.getKey());
        }
        validationTypeMappings.put(7, STDataValidationType.CUSTOM);
        validationTypeMappings.put(4, STDataValidationType.DATE);
        validationTypeMappings.put(2, STDataValidationType.DECIMAL);
        validationTypeMappings.put(3, STDataValidationType.LIST);
        validationTypeMappings.put(0, STDataValidationType.NONE);
        validationTypeMappings.put(6, STDataValidationType.TEXT_LENGTH);
        validationTypeMappings.put(5, STDataValidationType.TIME);
        validationTypeMappings.put(1, STDataValidationType.WHOLE);
        for (Map.Entry<Integer, StringEnumAbstractBase> entry : validationTypeMappings.entrySet()) {
            validationTypeReverseMappings.put((STDataValidationType.Enum)entry.getValue(), entry.getKey());
        }
    }
}

