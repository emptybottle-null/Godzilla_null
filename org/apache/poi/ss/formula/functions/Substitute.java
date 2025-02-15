/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.ss.formula.functions;

import org.apache.poi.ss.formula.eval.ErrorEval;
import org.apache.poi.ss.formula.eval.EvaluationException;
import org.apache.poi.ss.formula.eval.StringEval;
import org.apache.poi.ss.formula.eval.ValueEval;
import org.apache.poi.ss.formula.functions.TextFunction;
import org.apache.poi.ss.formula.functions.Var3or4ArgFunction;

public final class Substitute
extends Var3or4ArgFunction {
    @Override
    public ValueEval evaluate(int srcRowIndex, int srcColumnIndex, ValueEval arg0, ValueEval arg1, ValueEval arg2) {
        String result;
        try {
            String oldStr = TextFunction.evaluateStringArg(arg0, srcRowIndex, srcColumnIndex);
            String searchStr = TextFunction.evaluateStringArg(arg1, srcRowIndex, srcColumnIndex);
            String newStr = TextFunction.evaluateStringArg(arg2, srcRowIndex, srcColumnIndex);
            result = Substitute.replaceAllOccurrences(oldStr, searchStr, newStr);
        } catch (EvaluationException e) {
            return e.getErrorEval();
        }
        return new StringEval(result);
    }

    @Override
    public ValueEval evaluate(int srcRowIndex, int srcColumnIndex, ValueEval arg0, ValueEval arg1, ValueEval arg2, ValueEval arg3) {
        String result;
        try {
            String oldStr = TextFunction.evaluateStringArg(arg0, srcRowIndex, srcColumnIndex);
            String searchStr = TextFunction.evaluateStringArg(arg1, srcRowIndex, srcColumnIndex);
            String newStr = TextFunction.evaluateStringArg(arg2, srcRowIndex, srcColumnIndex);
            int instanceNumber = TextFunction.evaluateIntArg(arg3, srcRowIndex, srcColumnIndex);
            if (instanceNumber < 1) {
                return ErrorEval.VALUE_INVALID;
            }
            result = Substitute.replaceOneOccurrence(oldStr, searchStr, newStr, instanceNumber);
        } catch (EvaluationException e) {
            return e.getErrorEval();
        }
        return new StringEval(result);
    }

    private static String replaceAllOccurrences(String oldStr, String searchStr, String newStr) {
        if (searchStr.length() < 1) {
            return oldStr;
        }
        StringBuilder sb = new StringBuilder();
        int startIndex = 0;
        while (true) {
            int nextMatch;
            if ((nextMatch = oldStr.indexOf(searchStr, startIndex)) < 0) {
                sb.append(oldStr.substring(startIndex));
                return sb.toString();
            }
            sb.append(oldStr, startIndex, nextMatch);
            sb.append(newStr);
            startIndex = nextMatch + searchStr.length();
        }
    }

    private static String replaceOneOccurrence(String oldStr, String searchStr, String newStr, int instanceNumber) {
        if (searchStr.length() < 1) {
            return oldStr;
        }
        int startIndex = 0;
        int count = 0;
        int nextMatch;
        while ((nextMatch = oldStr.indexOf(searchStr, startIndex)) >= 0) {
            if (++count == instanceNumber) {
                return oldStr.substring(0, nextMatch) + newStr + oldStr.substring(nextMatch + searchStr.length());
            }
            startIndex = nextMatch + searchStr.length();
        }
        return oldStr;
    }
}

