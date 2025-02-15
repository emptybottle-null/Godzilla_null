/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.ss.formula.atp;

import org.apache.poi.ss.formula.OperationEvaluationContext;
import org.apache.poi.ss.formula.WorkbookEvaluator;
import org.apache.poi.ss.formula.eval.ErrorEval;
import org.apache.poi.ss.formula.eval.EvaluationException;
import org.apache.poi.ss.formula.eval.ValueEval;
import org.apache.poi.ss.formula.functions.FreeRefFunction;

final class IfError
implements FreeRefFunction {
    public static final FreeRefFunction instance = new IfError();

    private IfError() {
    }

    @Override
    public ValueEval evaluate(ValueEval[] args, OperationEvaluationContext ec) {
        ValueEval val;
        if (args.length != 2) {
            return ErrorEval.VALUE_INVALID;
        }
        try {
            val = IfError.evaluateInternal(args[0], args[1], ec.getRowIndex(), ec.getColumnIndex());
        } catch (EvaluationException e) {
            return e.getErrorEval();
        }
        return val;
    }

    private static ValueEval evaluateInternal(ValueEval arg, ValueEval iferror, int srcCellRow, int srcCellCol) throws EvaluationException {
        if ((arg = WorkbookEvaluator.dereferenceResult(arg, srcCellRow, srcCellCol)) instanceof ErrorEval) {
            return iferror;
        }
        return arg;
    }
}

