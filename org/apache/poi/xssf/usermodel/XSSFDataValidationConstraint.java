/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.xssf.usermodel;

import java.util.Arrays;
import java.util.regex.Pattern;
import org.apache.poi.ss.usermodel.DataValidationConstraint;
import org.apache.poi.xssf.usermodel.XSSFDataValidation;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.STDataValidationOperator;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.STDataValidationType;

public class XSSFDataValidationConstraint
implements DataValidationConstraint {
    private static final String LIST_SEPARATOR = ",";
    private static final Pattern LIST_SPLIT_REGEX = Pattern.compile("\\s*,\\s*");
    private static final String QUOTE = "\"";
    private String formula1;
    private String formula2;
    private int validationType = -1;
    private int operator = -1;
    private String[] explicitListOfValues;

    public XSSFDataValidationConstraint(String[] explicitListOfValues) {
        if (explicitListOfValues == null || explicitListOfValues.length == 0) {
            throw new IllegalArgumentException("List validation with explicit values must specify at least one value");
        }
        this.validationType = 3;
        this.setExplicitListValues(explicitListOfValues);
        this.validate();
    }

    public XSSFDataValidationConstraint(int validationType, String formula1) {
        this.setFormula1(formula1);
        this.validationType = validationType;
        this.validate();
    }

    public XSSFDataValidationConstraint(int validationType, int operator2, String formula1) {
        this.setFormula1(formula1);
        this.validationType = validationType;
        this.operator = operator2;
        this.validate();
    }

    public XSSFDataValidationConstraint(int validationType, int operator2, String formula1, String formula2) {
        this.setFormula1(formula1);
        this.setFormula2(formula2);
        this.validationType = validationType;
        this.operator = operator2;
        this.validate();
        if (3 == validationType && this.formula1 != null && XSSFDataValidationConstraint.isQuoted(this.formula1)) {
            this.explicitListOfValues = LIST_SPLIT_REGEX.split(XSSFDataValidationConstraint.unquote(this.formula1));
        }
    }

    @Override
    public String[] getExplicitListValues() {
        return this.explicitListOfValues;
    }

    @Override
    public String getFormula1() {
        return this.formula1;
    }

    @Override
    public String getFormula2() {
        return this.formula2;
    }

    @Override
    public int getOperator() {
        return this.operator;
    }

    @Override
    public int getValidationType() {
        return this.validationType;
    }

    @Override
    public void setExplicitListValues(String[] explicitListValues) {
        this.explicitListOfValues = explicitListValues;
        if (this.explicitListOfValues != null && this.explicitListOfValues.length > 0) {
            StringBuilder builder = new StringBuilder(QUOTE);
            for (int i = 0; i < explicitListValues.length; ++i) {
                String string = explicitListValues[i];
                if (builder.length() > 1) {
                    builder.append(LIST_SEPARATOR);
                }
                builder.append(string);
            }
            builder.append(QUOTE);
            this.setFormula1(builder.toString());
        }
    }

    @Override
    public void setFormula1(String formula1) {
        this.formula1 = XSSFDataValidationConstraint.removeLeadingEquals(formula1);
    }

    protected static String removeLeadingEquals(String formula1) {
        return XSSFDataValidationConstraint.isFormulaEmpty(formula1) ? formula1 : (formula1.charAt(0) == '=' ? formula1.substring(1) : formula1);
    }

    private static boolean isQuoted(String s) {
        return s.startsWith(QUOTE) && s.endsWith(QUOTE);
    }

    private static String unquote(String s) {
        if (XSSFDataValidationConstraint.isQuoted(s)) {
            return s.substring(1, s.length() - 1);
        }
        return s;
    }

    protected static boolean isFormulaEmpty(String formula1) {
        return formula1 == null || formula1.trim().length() == 0;
    }

    @Override
    public void setFormula2(String formula2) {
        this.formula2 = XSSFDataValidationConstraint.removeLeadingEquals(formula2);
    }

    @Override
    public void setOperator(int operator2) {
        this.operator = operator2;
    }

    public void validate() {
        if (this.validationType == 0) {
            return;
        }
        if (this.validationType == 3) {
            if (XSSFDataValidationConstraint.isFormulaEmpty(this.formula1)) {
                throw new IllegalArgumentException("A valid formula or a list of values must be specified for list validation.");
            }
        } else {
            if (XSSFDataValidationConstraint.isFormulaEmpty(this.formula1)) {
                throw new IllegalArgumentException("Formula is not specified. Formula is required for all validation types except explicit list validation.");
            }
            if (this.validationType != 7) {
                if (this.operator == -1) {
                    throw new IllegalArgumentException("This validation type requires an operator to be specified.");
                }
                if ((this.operator == 0 || this.operator == 1) && XSSFDataValidationConstraint.isFormulaEmpty(this.formula2)) {
                    throw new IllegalArgumentException("Between and not between comparisons require two formulae to be specified.");
                }
            }
        }
    }

    public String prettyPrint() {
        StringBuilder builder = new StringBuilder();
        STDataValidationType.Enum vt = XSSFDataValidation.validationTypeMappings.get(this.validationType);
        STDataValidationOperator.Enum ot = XSSFDataValidation.operatorTypeMappings.get(this.operator);
        builder.append(vt);
        builder.append(' ');
        if (this.validationType != 0) {
            if (this.validationType != 3 && this.validationType != 7) {
                builder.append(LIST_SEPARATOR).append(ot).append(", ");
            }
            String NOQUOTE = "";
            if (this.validationType == 3 && this.explicitListOfValues != null) {
                builder.append("").append(Arrays.asList(this.explicitListOfValues)).append("").append(' ');
            } else {
                builder.append("").append(this.formula1).append("").append(' ');
            }
            if (this.formula2 != null) {
                builder.append("").append(this.formula2).append("").append(' ');
            }
        }
        return builder.toString();
    }
}

