/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.ss.formula.functions;

import org.apache.poi.ss.formula.CacheAreaEval;
import org.apache.poi.ss.formula.FormulaParseException;
import org.apache.poi.ss.formula.eval.AreaEval;
import org.apache.poi.ss.formula.eval.BlankEval;
import org.apache.poi.ss.formula.eval.BoolEval;
import org.apache.poi.ss.formula.eval.ErrorEval;
import org.apache.poi.ss.formula.eval.EvaluationException;
import org.apache.poi.ss.formula.eval.MissingArgEval;
import org.apache.poi.ss.formula.eval.OperandResolver;
import org.apache.poi.ss.formula.eval.RefEval;
import org.apache.poi.ss.formula.eval.ValueEval;
import org.apache.poi.ss.formula.functions.ArrayFunction;
import org.apache.poi.ss.formula.functions.Var2or3ArgFunction;

public final class IfFunc
extends Var2or3ArgFunction
implements ArrayFunction {
    @Override
    public ValueEval evaluate(int srcRowIndex, int srcColumnIndex, ValueEval arg0, ValueEval arg1) {
        boolean b;
        try {
            b = IfFunc.evaluateFirstArg(arg0, srcRowIndex, srcColumnIndex);
        } catch (EvaluationException e) {
            return e.getErrorEval();
        }
        if (b) {
            if (arg1 == MissingArgEval.instance) {
                return BlankEval.instance;
            }
            return arg1;
        }
        return BoolEval.FALSE;
    }

    @Override
    public ValueEval evaluate(int srcRowIndex, int srcColumnIndex, ValueEval arg0, ValueEval arg1, ValueEval arg2) {
        boolean b;
        try {
            b = IfFunc.evaluateFirstArg(arg0, srcRowIndex, srcColumnIndex);
        } catch (EvaluationException e) {
            return e.getErrorEval();
        }
        if (b) {
            if (arg1 == MissingArgEval.instance) {
                return BlankEval.instance;
            }
            return arg1;
        }
        if (arg2 == MissingArgEval.instance) {
            return BlankEval.instance;
        }
        return arg2;
    }

    public static boolean evaluateFirstArg(ValueEval arg, int srcCellRow, int srcCellCol) throws EvaluationException {
        ValueEval ve = OperandResolver.getSingleValue(arg, srcCellRow, srcCellCol);
        Boolean b = OperandResolver.coerceValueToBoolean(ve, false);
        if (b == null) {
            return false;
        }
        return b;
    }

    @Override
    public ValueEval evaluateArray(ValueEval[] args, int srcRowIndex, int srcColumnIndex) {
        if (args.length < 2 || args.length > 3) {
            return ErrorEval.VALUE_INVALID;
        }
        ValueEval arg0 = args[0];
        ValueEval arg1 = args[1];
        BoolEval arg2 = args.length == 2 ? BoolEval.FALSE : args[2];
        return this.evaluateArrayArgs(arg0, arg1, arg2, srcRowIndex, srcColumnIndex);
    }

    ValueEval evaluateArrayArgs(ValueEval arg0, ValueEval arg1, ValueEval arg2, int srcRowIndex, int srcColumnIndex) {
        int h2;
        int w2;
        int h1;
        int w1;
        int a1FirstCol = 0;
        int a1FirstRow = 0;
        if (arg0 instanceof AreaEval) {
            AreaEval ae = (AreaEval)arg0;
            w1 = ae.getWidth();
            h1 = ae.getHeight();
            a1FirstCol = ae.getFirstColumn();
            a1FirstRow = ae.getFirstRow();
        } else if (arg0 instanceof RefEval) {
            RefEval ref = (RefEval)arg0;
            w1 = 1;
            h1 = 1;
            a1FirstCol = ref.getColumn();
            a1FirstRow = ref.getRow();
        } else {
            w1 = 1;
            h1 = 1;
        }
        int a2FirstCol = 0;
        int a2FirstRow = 0;
        if (arg1 instanceof AreaEval) {
            AreaEval ae = (AreaEval)arg1;
            w2 = ae.getWidth();
            h2 = ae.getHeight();
            a2FirstCol = ae.getFirstColumn();
            a2FirstRow = ae.getFirstRow();
        } else if (arg1 instanceof RefEval) {
            RefEval ref = (RefEval)arg1;
            w2 = 1;
            h2 = 1;
            a2FirstCol = ref.getColumn();
            a2FirstRow = ref.getRow();
        } else {
            w2 = 1;
            h2 = 1;
        }
        int a3FirstCol = 0;
        int a3FirstRow = 0;
        if (arg2 instanceof AreaEval) {
            AreaEval ae = (AreaEval)arg2;
            a3FirstCol = ae.getFirstColumn();
            a3FirstRow = ae.getFirstRow();
        } else if (arg2 instanceof RefEval) {
            RefEval ref = (RefEval)arg2;
            a3FirstCol = ref.getColumn();
            a3FirstRow = ref.getRow();
        }
        int width = Math.max(w1, w2);
        int height = Math.max(h1, h2);
        ValueEval[] vals = new ValueEval[height * width];
        int idx = 0;
        for (int i = 0; i < height; ++i) {
            for (int j = 0; j < width; ++j) {
                ValueEval vC;
                ValueEval vB;
                ValueEval vA;
                try {
                    vA = OperandResolver.getSingleValue(arg0, a1FirstRow + i, a1FirstCol + j);
                } catch (FormulaParseException e) {
                    vA = ErrorEval.NAME_INVALID;
                } catch (EvaluationException e) {
                    vA = e.getErrorEval();
                }
                try {
                    vB = OperandResolver.getSingleValue(arg1, a2FirstRow + i, a2FirstCol + j);
                } catch (FormulaParseException e) {
                    vB = ErrorEval.NAME_INVALID;
                } catch (EvaluationException e) {
                    vB = e.getErrorEval();
                }
                try {
                    vC = OperandResolver.getSingleValue(arg2, a3FirstRow + i, a3FirstCol + j);
                } catch (FormulaParseException e) {
                    vC = ErrorEval.NAME_INVALID;
                } catch (EvaluationException e) {
                    vC = e.getErrorEval();
                }
                if (vA instanceof ErrorEval) {
                    vals[idx++] = vA;
                    continue;
                }
                if (vB instanceof ErrorEval) {
                    vals[idx++] = vB;
                    continue;
                }
                try {
                    Boolean b = OperandResolver.coerceValueToBoolean(vA, false);
                    vals[idx++] = b != null && b != false ? vB : vC;
                    continue;
                } catch (EvaluationException e) {
                    vals[idx++] = e.getErrorEval();
                }
            }
        }
        if (vals.length == 1) {
            return vals[0];
        }
        return new CacheAreaEval(srcRowIndex, srcColumnIndex, srcRowIndex + height - 1, srcColumnIndex + width - 1, vals);
    }
}

