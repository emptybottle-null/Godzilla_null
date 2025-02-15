/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.ss.formula.functions;

import org.apache.poi.ss.formula.SheetNameFormatter;
import org.apache.poi.ss.formula.eval.ErrorEval;
import org.apache.poi.ss.formula.eval.EvaluationException;
import org.apache.poi.ss.formula.eval.MissingArgEval;
import org.apache.poi.ss.formula.eval.OperandResolver;
import org.apache.poi.ss.formula.eval.StringEval;
import org.apache.poi.ss.formula.eval.ValueEval;
import org.apache.poi.ss.formula.functions.Function;
import org.apache.poi.ss.formula.functions.NumericFunction;
import org.apache.poi.ss.util.CellReference;

public class Address
implements Function {
    public static final int REF_ABSOLUTE = 1;
    public static final int REF_ROW_ABSOLUTE_COLUMN_RELATIVE = 2;
    public static final int REF_ROW_RELATIVE_RELATIVE_ABSOLUTE = 3;
    public static final int REF_RELATIVE = 4;

    @Override
    public ValueEval evaluate(ValueEval[] args, int srcRowIndex, int srcColumnIndex) {
        if (args.length < 2 || args.length > 5) {
            return ErrorEval.VALUE_INVALID;
        }
        try {
            ValueEval ve;
            boolean pAbsCol;
            boolean pAbsRow;
            int row = (int)NumericFunction.singleOperandEvaluate(args[0], srcRowIndex, srcColumnIndex);
            int col = (int)NumericFunction.singleOperandEvaluate(args[1], srcRowIndex, srcColumnIndex);
            int refType = args.length > 2 && args[2] != MissingArgEval.instance ? (int)NumericFunction.singleOperandEvaluate(args[2], srcRowIndex, srcColumnIndex) : 1;
            switch (refType) {
                case 1: {
                    pAbsRow = true;
                    pAbsCol = true;
                    break;
                }
                case 2: {
                    pAbsRow = true;
                    pAbsCol = false;
                    break;
                }
                case 3: {
                    pAbsRow = false;
                    pAbsCol = true;
                    break;
                }
                case 4: {
                    pAbsRow = false;
                    pAbsCol = false;
                    break;
                }
                default: {
                    throw new EvaluationException(ErrorEval.VALUE_INVALID);
                }
            }
            String sheetName = args.length == 5 ? ((ve = OperandResolver.getSingleValue(args[4], srcRowIndex, srcColumnIndex)) == MissingArgEval.instance ? null : OperandResolver.coerceValueToString(ve)) : null;
            CellReference ref = new CellReference(row - 1, col - 1, pAbsRow, pAbsCol);
            StringBuilder sb = new StringBuilder(32);
            if (sheetName != null) {
                SheetNameFormatter.appendFormat(sb, sheetName);
                sb.append('!');
            }
            sb.append(ref.formatAsString());
            return new StringEval(sb.toString());
        } catch (EvaluationException e) {
            return e.getErrorEval();
        }
    }
}

