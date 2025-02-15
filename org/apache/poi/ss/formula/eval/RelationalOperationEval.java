/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.ss.formula.eval;

import org.apache.poi.ss.formula.eval.BlankEval;
import org.apache.poi.ss.formula.eval.BoolEval;
import org.apache.poi.ss.formula.eval.EvaluationException;
import org.apache.poi.ss.formula.eval.NumberEval;
import org.apache.poi.ss.formula.eval.OperandResolver;
import org.apache.poi.ss.formula.eval.StringEval;
import org.apache.poi.ss.formula.eval.ValueEval;
import org.apache.poi.ss.formula.functions.ArrayFunction;
import org.apache.poi.ss.formula.functions.Fixed2ArgFunction;
import org.apache.poi.ss.formula.functions.Function;
import org.apache.poi.ss.util.NumberComparer;

public abstract class RelationalOperationEval
extends Fixed2ArgFunction
implements ArrayFunction {
    public static final Function EqualEval = new RelationalOperationEval(){

        @Override
        protected boolean convertComparisonResult(int cmpResult) {
            return cmpResult == 0;
        }
    };
    public static final Function GreaterEqualEval = new RelationalOperationEval(){

        @Override
        protected boolean convertComparisonResult(int cmpResult) {
            return cmpResult >= 0;
        }
    };
    public static final Function GreaterThanEval = new RelationalOperationEval(){

        @Override
        protected boolean convertComparisonResult(int cmpResult) {
            return cmpResult > 0;
        }
    };
    public static final Function LessEqualEval = new RelationalOperationEval(){

        @Override
        protected boolean convertComparisonResult(int cmpResult) {
            return cmpResult <= 0;
        }
    };
    public static final Function LessThanEval = new RelationalOperationEval(){

        @Override
        protected boolean convertComparisonResult(int cmpResult) {
            return cmpResult < 0;
        }
    };
    public static final Function NotEqualEval = new RelationalOperationEval(){

        @Override
        protected boolean convertComparisonResult(int cmpResult) {
            return cmpResult != 0;
        }
    };

    protected abstract boolean convertComparisonResult(int var1);

    @Override
    public ValueEval evaluate(int srcRowIndex, int srcColumnIndex, ValueEval arg0, ValueEval arg1) {
        ValueEval vB;
        ValueEval vA;
        try {
            vA = OperandResolver.getSingleValue(arg0, srcRowIndex, srcColumnIndex);
            vB = OperandResolver.getSingleValue(arg1, srcRowIndex, srcColumnIndex);
        } catch (EvaluationException e) {
            return e.getErrorEval();
        }
        int cmpResult = RelationalOperationEval.doCompare(vA, vB);
        boolean result = this.convertComparisonResult(cmpResult);
        return BoolEval.valueOf(result);
    }

    @Override
    public ValueEval evaluateArray(ValueEval[] args, int srcRowIndex, int srcColumnIndex) {
        ValueEval arg0 = args[0];
        ValueEval arg1 = args[1];
        return this.evaluateTwoArrayArgs(arg0, arg1, srcRowIndex, srcColumnIndex, (vA, vB) -> {
            int cmpResult = RelationalOperationEval.doCompare(vA, vB);
            boolean result = this.convertComparisonResult(cmpResult);
            return BoolEval.valueOf(result);
        });
    }

    private static int doCompare(ValueEval va, ValueEval vb) {
        if (va == BlankEval.instance) {
            return RelationalOperationEval.compareBlank(vb);
        }
        if (vb == BlankEval.instance) {
            return -RelationalOperationEval.compareBlank(va);
        }
        if (va instanceof BoolEval) {
            if (vb instanceof BoolEval) {
                BoolEval bA = (BoolEval)va;
                BoolEval bB = (BoolEval)vb;
                if (bA.getBooleanValue() == bB.getBooleanValue()) {
                    return 0;
                }
                return bA.getBooleanValue() ? 1 : -1;
            }
            return 1;
        }
        if (vb instanceof BoolEval) {
            return -1;
        }
        if (va instanceof StringEval) {
            if (vb instanceof StringEval) {
                StringEval sA = (StringEval)va;
                StringEval sB = (StringEval)vb;
                return sA.getStringValue().compareToIgnoreCase(sB.getStringValue());
            }
            return 1;
        }
        if (vb instanceof StringEval) {
            return -1;
        }
        if (va instanceof NumberEval && vb instanceof NumberEval) {
            NumberEval nA = (NumberEval)va;
            NumberEval nB = (NumberEval)vb;
            return NumberComparer.compare(nA.getNumberValue(), nB.getNumberValue());
        }
        throw new IllegalArgumentException("Bad operand types (" + va.getClass().getName() + "), (" + vb.getClass().getName() + ")");
    }

    private static int compareBlank(ValueEval v) {
        if (v == BlankEval.instance) {
            return 0;
        }
        if (v instanceof BoolEval) {
            BoolEval boolEval = (BoolEval)v;
            return boolEval.getBooleanValue() ? -1 : 0;
        }
        if (v instanceof NumberEval) {
            NumberEval ne = (NumberEval)v;
            return NumberComparer.compare(0.0, ne.getNumberValue());
        }
        if (v instanceof StringEval) {
            StringEval se = (StringEval)v;
            return se.getStringValue().length() < 1 ? 0 : -1;
        }
        throw new IllegalArgumentException("bad value class (" + v.getClass().getName() + ")");
    }
}

