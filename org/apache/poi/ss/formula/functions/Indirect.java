/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.ss.formula.functions;

import org.apache.poi.ss.formula.FormulaParseException;
import org.apache.poi.ss.formula.FormulaParser;
import org.apache.poi.ss.formula.FormulaParsingWorkbook;
import org.apache.poi.ss.formula.OperationEvaluationContext;
import org.apache.poi.ss.formula.eval.BlankEval;
import org.apache.poi.ss.formula.eval.ErrorEval;
import org.apache.poi.ss.formula.eval.EvaluationException;
import org.apache.poi.ss.formula.eval.MissingArgEval;
import org.apache.poi.ss.formula.eval.OperandResolver;
import org.apache.poi.ss.formula.eval.ValueEval;
import org.apache.poi.ss.formula.functions.FreeRefFunction;
import org.apache.poi.ss.formula.ptg.Area3DPxg;
import org.apache.poi.ss.usermodel.Table;

public final class Indirect
implements FreeRefFunction {
    public static final FreeRefFunction instance = new Indirect();

    private Indirect() {
    }

    @Override
    public ValueEval evaluate(ValueEval[] args, OperationEvaluationContext ec) {
        boolean isA1style;
        String text;
        if (args.length < 1) {
            return ErrorEval.VALUE_INVALID;
        }
        try {
            ValueEval ve = OperandResolver.getSingleValue(args[0], ec.getRowIndex(), ec.getColumnIndex());
            text = OperandResolver.coerceValueToString(ve);
            switch (args.length) {
                case 1: {
                    isA1style = true;
                    break;
                }
                case 2: {
                    isA1style = Indirect.evaluateBooleanArg(args[1], ec);
                    break;
                }
                default: {
                    return ErrorEval.VALUE_INVALID;
                }
            }
        } catch (EvaluationException e) {
            return e.getErrorEval();
        }
        return Indirect.evaluateIndirect(ec, text, isA1style);
    }

    private static boolean evaluateBooleanArg(ValueEval arg, OperationEvaluationContext ec) throws EvaluationException {
        ValueEval ve = OperandResolver.getSingleValue(arg, ec.getRowIndex(), ec.getColumnIndex());
        if (ve == BlankEval.instance || ve == MissingArgEval.instance) {
            return false;
        }
        return OperandResolver.coerceValueToBoolean(ve, false);
    }

    private static ValueEval evaluateIndirect(OperationEvaluationContext ec, String text, boolean isA1style) {
        String refStrPart2;
        String refStrPart1;
        String refText;
        String sheetName;
        String workbookName;
        int plingPos = text.lastIndexOf(33);
        if (plingPos < 0) {
            workbookName = null;
            sheetName = null;
            refText = text;
        } else {
            String[] parts = Indirect.parseWorkbookAndSheetName(text.subSequence(0, plingPos));
            if (parts == null) {
                return ErrorEval.REF_INVALID;
            }
            workbookName = parts[0];
            sheetName = parts[1];
            refText = text.substring(plingPos + 1);
        }
        if (Table.isStructuredReference.matcher(refText).matches()) {
            Area3DPxg areaPtg = null;
            try {
                areaPtg = FormulaParser.parseStructuredReference(refText, (FormulaParsingWorkbook)((Object)ec.getWorkbook()), ec.getRowIndex());
            } catch (FormulaParseException e) {
                return ErrorEval.REF_INVALID;
            }
            return ec.getArea3DEval(areaPtg);
        }
        int colonPos = refText.indexOf(58);
        if (colonPos < 0) {
            refStrPart1 = refText.trim();
            refStrPart2 = null;
        } else {
            refStrPart1 = refText.substring(0, colonPos).trim();
            refStrPart2 = refText.substring(colonPos + 1).trim();
        }
        return ec.getDynamicReference(workbookName, sheetName, refStrPart1, refStrPart2, isA1style);
    }

    private static String[] parseWorkbookAndSheetName(CharSequence text) {
        int lastIx = text.length() - 1;
        if (lastIx < 0) {
            return null;
        }
        if (Indirect.canTrim(text)) {
            return null;
        }
        char firstChar = text.charAt(0);
        if (Character.isWhitespace(firstChar)) {
            return null;
        }
        if (firstChar == '\'') {
            int sheetStartPos;
            String wbName;
            if (text.charAt(lastIx) != '\'') {
                return null;
            }
            firstChar = text.charAt(1);
            if (Character.isWhitespace(firstChar)) {
                return null;
            }
            if (firstChar == '[') {
                int rbPos = text.toString().lastIndexOf(93);
                if (rbPos < 0) {
                    return null;
                }
                wbName = Indirect.unescapeString(text.subSequence(2, rbPos));
                if (wbName == null || Indirect.canTrim(wbName)) {
                    return null;
                }
                sheetStartPos = rbPos + 1;
            } else {
                wbName = null;
                sheetStartPos = 1;
            }
            String sheetName = Indirect.unescapeString(text.subSequence(sheetStartPos, lastIx));
            if (sheetName == null) {
                return null;
            }
            return new String[]{wbName, sheetName};
        }
        if (firstChar == '[') {
            int rbPos = text.toString().lastIndexOf(93);
            if (rbPos < 0) {
                return null;
            }
            CharSequence wbName = text.subSequence(1, rbPos);
            if (Indirect.canTrim(wbName)) {
                return null;
            }
            CharSequence sheetName = text.subSequence(rbPos + 1, text.length());
            if (Indirect.canTrim(sheetName)) {
                return null;
            }
            return new String[]{wbName.toString(), sheetName.toString()};
        }
        return new String[]{null, text.toString()};
    }

    private static String unescapeString(CharSequence text) {
        int len = text.length();
        StringBuilder sb = new StringBuilder(len);
        for (int i = 0; i < len; ++i) {
            char ch = text.charAt(i);
            if (ch == '\'') {
                if (++i >= len) {
                    return null;
                }
                ch = text.charAt(i);
                if (ch != '\'') {
                    return null;
                }
            }
            sb.append(ch);
        }
        return sb.toString();
    }

    private static boolean canTrim(CharSequence text) {
        int lastIx = text.length() - 1;
        if (lastIx < 0) {
            return false;
        }
        if (Character.isWhitespace(text.charAt(0))) {
            return true;
        }
        return Character.isWhitespace(text.charAt(lastIx));
    }
}

