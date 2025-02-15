/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.ss.formula.functions;

import org.apache.poi.ss.formula.eval.BoolEval;
import org.apache.poi.ss.formula.eval.ErrorEval;
import org.apache.poi.ss.formula.eval.EvaluationException;
import org.apache.poi.ss.formula.eval.MissingArgEval;
import org.apache.poi.ss.formula.eval.NumberEval;
import org.apache.poi.ss.formula.eval.ValueEval;
import org.apache.poi.ss.formula.functions.FinanceLib;
import org.apache.poi.ss.formula.functions.Function;
import org.apache.poi.ss.formula.functions.Function3Arg;
import org.apache.poi.ss.formula.functions.Function4Arg;
import org.apache.poi.ss.formula.functions.NumericFunction;

public abstract class FinanceFunction
implements Function3Arg,
Function4Arg {
    private static final ValueEval DEFAULT_ARG3 = NumberEval.ZERO;
    private static final ValueEval DEFAULT_ARG4 = BoolEval.FALSE;
    public static final Function FV = new FinanceFunction(){

        @Override
        protected double evaluate(double rate, double arg1, double arg2, double arg3, boolean type) {
            return FinanceLib.fv(rate, arg1, arg2, arg3, type);
        }
    };
    public static final Function NPER = new FinanceFunction(){

        @Override
        protected double evaluate(double rate, double arg1, double arg2, double arg3, boolean type) {
            return FinanceLib.nper(rate, arg1, arg2, arg3, type);
        }
    };
    public static final Function PMT = new FinanceFunction(){

        @Override
        protected double evaluate(double rate, double arg1, double arg2, double arg3, boolean type) {
            return FinanceLib.pmt(rate, arg1, arg2, arg3, type);
        }
    };
    public static final Function PV = new FinanceFunction(){

        @Override
        protected double evaluate(double rate, double arg1, double arg2, double arg3, boolean type) {
            return FinanceLib.pv(rate, arg1, arg2, arg3, type);
        }
    };

    protected FinanceFunction() {
    }

    @Override
    public ValueEval evaluate(int srcRowIndex, int srcColumnIndex, ValueEval arg0, ValueEval arg1, ValueEval arg2) {
        return this.evaluate(srcRowIndex, srcColumnIndex, arg0, arg1, arg2, DEFAULT_ARG3);
    }

    @Override
    public ValueEval evaluate(int srcRowIndex, int srcColumnIndex, ValueEval arg0, ValueEval arg1, ValueEval arg2, ValueEval arg3) {
        return this.evaluate(srcRowIndex, srcColumnIndex, arg0, arg1, arg2, arg3, DEFAULT_ARG4);
    }

    public ValueEval evaluate(int srcRowIndex, int srcColumnIndex, ValueEval arg0, ValueEval arg1, ValueEval arg2, ValueEval arg3, ValueEval arg4) {
        double result;
        try {
            double d0 = NumericFunction.singleOperandEvaluate(arg0, srcRowIndex, srcColumnIndex);
            double d1 = NumericFunction.singleOperandEvaluate(arg1, srcRowIndex, srcColumnIndex);
            double d2 = NumericFunction.singleOperandEvaluate(arg2, srcRowIndex, srcColumnIndex);
            double d3 = NumericFunction.singleOperandEvaluate(arg3, srcRowIndex, srcColumnIndex);
            double d4 = NumericFunction.singleOperandEvaluate(arg4, srcRowIndex, srcColumnIndex);
            result = this.evaluate(d0, d1, d2, d3, d4 != 0.0);
            NumericFunction.checkValue(result);
        } catch (EvaluationException e) {
            return e.getErrorEval();
        }
        return new NumberEval(result);
    }

    @Override
    public ValueEval evaluate(ValueEval[] args, int srcRowIndex, int srcColumnIndex) {
        switch (args.length) {
            case 3: {
                return this.evaluate(srcRowIndex, srcColumnIndex, args[0], args[1], args[2], DEFAULT_ARG3, DEFAULT_ARG4);
            }
            case 4: {
                ValueEval arg3 = args[3];
                if (arg3 == MissingArgEval.instance) {
                    arg3 = DEFAULT_ARG3;
                }
                return this.evaluate(srcRowIndex, srcColumnIndex, args[0], args[1], args[2], arg3, DEFAULT_ARG4);
            }
            case 5: {
                ValueEval arg4;
                ValueEval arg3 = args[3];
                if (arg3 == MissingArgEval.instance) {
                    arg3 = DEFAULT_ARG3;
                }
                if ((arg4 = args[4]) == MissingArgEval.instance) {
                    arg4 = DEFAULT_ARG4;
                }
                return this.evaluate(srcRowIndex, srcColumnIndex, args[0], args[1], args[2], arg3, arg4);
            }
        }
        return ErrorEval.VALUE_INVALID;
    }

    protected double evaluate(double[] ds) throws EvaluationException {
        double arg3 = 0.0;
        double arg4 = 0.0;
        switch (ds.length) {
            case 5: {
                arg4 = ds[4];
            }
            case 4: {
                arg3 = ds[3];
            }
            case 3: {
                break;
            }
            default: {
                throw new IllegalStateException("Wrong number of arguments");
            }
        }
        return this.evaluate(ds[0], ds[1], ds[2], arg3, arg4 != 0.0);
    }

    protected abstract double evaluate(double var1, double var3, double var5, double var7, boolean var9) throws EvaluationException;
}

