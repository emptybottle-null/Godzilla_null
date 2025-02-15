/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.ss.formula.functions;

import java.time.DateTimeException;
import org.apache.poi.ss.formula.eval.ErrorEval;
import org.apache.poi.ss.formula.eval.EvaluationException;
import org.apache.poi.ss.formula.eval.NumberEval;
import org.apache.poi.ss.formula.eval.OperandResolver;
import org.apache.poi.ss.formula.eval.ValueEval;
import org.apache.poi.ss.formula.functions.Fixed1ArgFunction;
import org.apache.poi.ss.usermodel.DateUtil;

public final class Value
extends Fixed1ArgFunction {
    private static final int MIN_DISTANCE_BETWEEN_THOUSANDS_SEPARATOR = 4;
    private static final Double ZERO = 0.0;

    @Override
    public ValueEval evaluate(int srcRowIndex, int srcColumnIndex, ValueEval arg0) {
        ValueEval veText;
        try {
            veText = OperandResolver.getSingleValue(arg0, srcRowIndex, srcColumnIndex);
        } catch (EvaluationException e) {
            return e.getErrorEval();
        }
        String strText = OperandResolver.coerceValueToString(veText);
        Double result = Value.convertTextToNumber(strText);
        if (result == null) {
            result = Value.parseDateTime(strText);
        }
        if (result == null) {
            return ErrorEval.VALUE_INVALID;
        }
        return new NumberEval(result);
    }

    public static Double convertTextToNumber(String strText) {
        double d;
        char ch;
        int i;
        boolean foundCurrency = false;
        boolean foundUnaryPlus = false;
        boolean foundUnaryMinus = false;
        boolean foundPercentage = false;
        int len = strText.length();
        block15: for (i = 0; i < len && !Character.isDigit(ch = strText.charAt(i)) && ch != '.'; ++i) {
            switch (ch) {
                case ' ': {
                    continue block15;
                }
                case '$': {
                    if (foundCurrency) {
                        return null;
                    }
                    foundCurrency = true;
                    continue block15;
                }
                case '+': {
                    if (foundUnaryMinus || foundUnaryPlus) {
                        return null;
                    }
                    foundUnaryPlus = true;
                    continue block15;
                }
                case '-': {
                    if (foundUnaryMinus || foundUnaryPlus) {
                        return null;
                    }
                    foundUnaryMinus = true;
                    continue block15;
                }
                default: {
                    return null;
                }
            }
        }
        if (i >= len) {
            if (foundCurrency || foundUnaryMinus || foundUnaryPlus) {
                return null;
            }
            return ZERO;
        }
        boolean foundDecimalPoint = false;
        int lastThousandsSeparatorIndex = Short.MIN_VALUE;
        StringBuilder sb = new StringBuilder(len);
        while (i < len) {
            char ch2 = strText.charAt(i);
            if (Character.isDigit(ch2)) {
                sb.append(ch2);
            } else {
                switch (ch2) {
                    case ' ': {
                        String remainingTextTrimmed = strText.substring(i).trim();
                        if (remainingTextTrimmed.equals("%")) {
                            foundPercentage = true;
                            break;
                        }
                        if (remainingTextTrimmed.length() <= 0) break;
                        return null;
                    }
                    case '.': {
                        if (foundDecimalPoint) {
                            return null;
                        }
                        if (i - lastThousandsSeparatorIndex < 4) {
                            return null;
                        }
                        foundDecimalPoint = true;
                        sb.append('.');
                        break;
                    }
                    case ',': {
                        if (foundDecimalPoint) {
                            return null;
                        }
                        int distanceBetweenThousandsSeparators = i - lastThousandsSeparatorIndex;
                        if (distanceBetweenThousandsSeparators < 4) {
                            return null;
                        }
                        lastThousandsSeparatorIndex = i;
                        break;
                    }
                    case 'E': 
                    case 'e': {
                        if (i - lastThousandsSeparatorIndex < 4) {
                            return null;
                        }
                        sb.append(strText.substring(i));
                        i = len;
                        break;
                    }
                    case '%': {
                        foundPercentage = true;
                        break;
                    }
                    default: {
                        return null;
                    }
                }
            }
            ++i;
        }
        if (!foundDecimalPoint && i - lastThousandsSeparatorIndex < 4) {
            return null;
        }
        try {
            d = Double.parseDouble(sb.toString());
        } catch (NumberFormatException e) {
            return null;
        }
        double result = foundUnaryMinus ? -d : d;
        return foundPercentage ? result / 100.0 : result;
    }

    public static Double parseDateTime(String pText) {
        try {
            return DateUtil.parseDateTime(pText);
        } catch (DateTimeException e) {
            return null;
        }
    }
}

