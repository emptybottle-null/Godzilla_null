/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.ss.usermodel;

public interface DataValidationConstraint {
    public int getValidationType();

    public int getOperator();

    public void setOperator(int var1);

    public String[] getExplicitListValues();

    public void setExplicitListValues(String[] var1);

    public String getFormula1();

    public void setFormula1(String var1);

    public String getFormula2();

    public void setFormula2(String var1);

    public static final class OperatorType {
        public static final int BETWEEN = 0;
        public static final int NOT_BETWEEN = 1;
        public static final int EQUAL = 2;
        public static final int NOT_EQUAL = 3;
        public static final int GREATER_THAN = 4;
        public static final int LESS_THAN = 5;
        public static final int GREATER_OR_EQUAL = 6;
        public static final int LESS_OR_EQUAL = 7;
        public static final int IGNORED = 0;

        private OperatorType() {
        }

        public static void validateSecondArg(int comparisonOperator, String paramValue) {
            switch (comparisonOperator) {
                case 0: 
                case 1: {
                    if (paramValue != null) break;
                    throw new IllegalArgumentException("expr2 must be supplied for 'between' comparisons");
                }
            }
        }
    }

    public static final class ValidationType {
        public static final int ANY = 0;
        public static final int INTEGER = 1;
        public static final int DECIMAL = 2;
        public static final int LIST = 3;
        public static final int DATE = 4;
        public static final int TIME = 5;
        public static final int TEXT_LENGTH = 6;
        public static final int FORMULA = 7;

        private ValidationType() {
        }
    }
}

