/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.ss.format;

import java.util.HashMap;
import java.util.Map;

public abstract class CellFormatCondition {
    private static final int LT = 0;
    private static final int LE = 1;
    private static final int GT = 2;
    private static final int GE = 3;
    private static final int EQ = 4;
    private static final int NE = 5;
    private static final Map<String, Integer> TESTS = new HashMap<String, Integer>();

    public static CellFormatCondition getInstance(String opString, String constStr) {
        if (!TESTS.containsKey(opString)) {
            throw new IllegalArgumentException("Unknown test: " + opString);
        }
        int test = TESTS.get(opString);
        final double c = Double.parseDouble(constStr);
        switch (test) {
            case 0: {
                return new CellFormatCondition(){

                    @Override
                    public boolean pass(double value) {
                        return value < c;
                    }
                };
            }
            case 1: {
                return new CellFormatCondition(){

                    @Override
                    public boolean pass(double value) {
                        return value <= c;
                    }
                };
            }
            case 2: {
                return new CellFormatCondition(){

                    @Override
                    public boolean pass(double value) {
                        return value > c;
                    }
                };
            }
            case 3: {
                return new CellFormatCondition(){

                    @Override
                    public boolean pass(double value) {
                        return value >= c;
                    }
                };
            }
            case 4: {
                return new CellFormatCondition(){

                    @Override
                    public boolean pass(double value) {
                        return value == c;
                    }
                };
            }
            case 5: {
                return new CellFormatCondition(){

                    @Override
                    public boolean pass(double value) {
                        return value != c;
                    }
                };
            }
        }
        throw new IllegalArgumentException("Cannot create for test number " + test + "(\"" + opString + "\")");
    }

    public abstract boolean pass(double var1);

    static {
        TESTS.put("<", 0);
        TESTS.put("<=", 1);
        TESTS.put(">", 2);
        TESTS.put(">=", 3);
        TESTS.put("=", 4);
        TESTS.put("==", 4);
        TESTS.put("!=", 5);
        TESTS.put("<>", 5);
    }
}

