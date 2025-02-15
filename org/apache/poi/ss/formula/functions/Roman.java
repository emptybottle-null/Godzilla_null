/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.ss.formula.functions;

import org.apache.poi.ss.formula.eval.ErrorEval;
import org.apache.poi.ss.formula.eval.EvaluationException;
import org.apache.poi.ss.formula.eval.OperandResolver;
import org.apache.poi.ss.formula.eval.StringEval;
import org.apache.poi.ss.formula.eval.ValueEval;
import org.apache.poi.ss.formula.functions.Fixed2ArgFunction;

public class Roman
extends Fixed2ArgFunction {
    public static final int[] VALUES = new int[]{1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
    public static final String[] ROMAN = new String[]{"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};

    @Override
    public ValueEval evaluate(int srcRowIndex, int srcColumnIndex, ValueEval numberVE, ValueEval formVE) {
        int number = 0;
        try {
            ValueEval ve = OperandResolver.getSingleValue(numberVE, srcRowIndex, srcColumnIndex);
            number = OperandResolver.coerceValueToInt(ve);
        } catch (EvaluationException e) {
            return ErrorEval.VALUE_INVALID;
        }
        if (number < 0) {
            return ErrorEval.VALUE_INVALID;
        }
        if (number > 3999) {
            return ErrorEval.VALUE_INVALID;
        }
        if (number == 0) {
            return new StringEval("");
        }
        int form = 0;
        try {
            ValueEval ve = OperandResolver.getSingleValue(formVE, srcRowIndex, srcColumnIndex);
            form = OperandResolver.coerceValueToInt(ve);
        } catch (EvaluationException e) {
            return ErrorEval.NUM_ERROR;
        }
        if (form > 4 || form < 0) {
            return ErrorEval.VALUE_INVALID;
        }
        String result = this.integerToRoman(number);
        if (form == 0) {
            return new StringEval(result);
        }
        return new StringEval(this.makeConcise(result, form));
    }

    private String integerToRoman(int number) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < 13; ++i) {
            while (number >= VALUES[i]) {
                number -= VALUES[i];
                result.append(ROMAN[i]);
            }
        }
        return result.toString();
    }

    public String makeConcise(String result, int form) {
        if (form > 0) {
            result = result.replaceAll("XLV", "VL");
            result = result.replaceAll("XCV", "VC");
            result = result.replaceAll("CDL", "LD");
            result = result.replaceAll("CML", "LM");
            result = result.replaceAll("CMVC", "LMVL");
        }
        if (form == 1) {
            result = result.replaceAll("CDXC", "LDXL");
            result = result.replaceAll("CDVC", "LDVL");
            result = result.replaceAll("CMXC", "LMXL");
            result = result.replaceAll("XCIX", "VCIV");
            result = result.replaceAll("XLIX", "VLIV");
        }
        if (form > 1) {
            result = result.replaceAll("XLIX", "IL");
            result = result.replaceAll("XCIX", "IC");
            result = result.replaceAll("CDXC", "XD");
            result = result.replaceAll("CDVC", "XDV");
            result = result.replaceAll("CDIC", "XDIX");
            result = result.replaceAll("LMVL", "XMV");
            result = result.replaceAll("CMIC", "XMIX");
            result = result.replaceAll("CMXC", "XM");
        }
        if (form > 2) {
            result = result.replaceAll("XDV", "VD");
            result = result.replaceAll("XDIX", "VDIV");
            result = result.replaceAll("XMV", "VM");
            result = result.replaceAll("XMIX", "VMIV");
        }
        if (form == 4) {
            result = result.replaceAll("VDIV", "ID");
            result = result.replaceAll("VMIV", "IM");
        }
        return result;
    }
}

