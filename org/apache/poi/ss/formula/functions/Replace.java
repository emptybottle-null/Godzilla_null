/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.ss.formula.functions;

import org.apache.poi.ss.formula.eval.ErrorEval;
import org.apache.poi.ss.formula.eval.EvaluationException;
import org.apache.poi.ss.formula.eval.StringEval;
import org.apache.poi.ss.formula.eval.ValueEval;
import org.apache.poi.ss.formula.functions.Fixed4ArgFunction;
import org.apache.poi.ss.formula.functions.TextFunction;

public final class Replace
extends Fixed4ArgFunction {
    @Override
    public ValueEval evaluate(int srcRowIndex, int srcColumnIndex, ValueEval arg0, ValueEval arg1, ValueEval arg2, ValueEval arg3) {
        String newStr;
        int numChars;
        int startNum;
        String oldStr;
        try {
            oldStr = TextFunction.evaluateStringArg(arg0, srcRowIndex, srcColumnIndex);
            startNum = TextFunction.evaluateIntArg(arg1, srcRowIndex, srcColumnIndex);
            numChars = TextFunction.evaluateIntArg(arg2, srcRowIndex, srcColumnIndex);
            newStr = TextFunction.evaluateStringArg(arg3, srcRowIndex, srcColumnIndex);
        } catch (EvaluationException e) {
            return e.getErrorEval();
        }
        if (startNum < 1 || numChars < 0) {
            return ErrorEval.VALUE_INVALID;
        }
        StringBuilder strBuff = new StringBuilder(oldStr);
        if (startNum <= oldStr.length() && numChars != 0) {
            strBuff.delete(startNum - 1, startNum - 1 + numChars);
        }
        if (startNum > strBuff.length()) {
            strBuff.append(newStr);
        } else {
            strBuff.insert(startNum - 1, newStr);
        }
        return new StringEval(strBuff.toString());
    }
}

