/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.ss.formula.functions;

import org.apache.commons.math3.stat.descriptive.moment.GeometricMean;
import org.apache.poi.ss.formula.eval.ErrorEval;
import org.apache.poi.ss.formula.eval.EvaluationException;
import org.apache.poi.ss.formula.eval.NumberEval;
import org.apache.poi.ss.formula.eval.OperandResolver;
import org.apache.poi.ss.formula.eval.ValueEval;
import org.apache.poi.ss.formula.functions.Fixed2ArgFunction;
import org.apache.poi.ss.formula.functions.Function;
import org.apache.poi.ss.formula.functions.MathX;
import org.apache.poi.ss.formula.functions.MultiOperandNumericFunction;
import org.apache.poi.ss.formula.functions.NumericFunction;
import org.apache.poi.ss.formula.functions.StatsLib;

public abstract class AggregateFunction
extends MultiOperandNumericFunction {
    public static final Function AVEDEV = new AggregateFunction(){

        @Override
        protected double evaluate(double[] values) {
            return StatsLib.avedev(values);
        }
    };
    public static final Function AVERAGE = new AggregateFunction(){

        @Override
        protected double evaluate(double[] values) throws EvaluationException {
            if (values.length < 1) {
                throw new EvaluationException(ErrorEval.DIV_ZERO);
            }
            return MathX.average(values);
        }
    };
    public static final Function DEVSQ = new AggregateFunction(){

        @Override
        protected double evaluate(double[] values) {
            return StatsLib.devsq(values);
        }
    };
    public static final Function LARGE = new LargeSmall(true);
    public static final Function MAX = new AggregateFunction(){

        @Override
        protected double evaluate(double[] values) {
            return values.length > 0 ? MathX.max(values) : 0.0;
        }
    };
    public static final Function MEDIAN = new AggregateFunction(){

        @Override
        protected double evaluate(double[] values) {
            return StatsLib.median(values);
        }
    };
    public static final Function MIN = new AggregateFunction(){

        @Override
        protected double evaluate(double[] values) {
            return values.length > 0 ? MathX.min(values) : 0.0;
        }
    };
    public static final Function PERCENTILE = new Percentile();
    public static final Function PRODUCT = new Product();
    public static final Function SMALL = new LargeSmall(false);
    public static final Function STDEV = new AggregateFunction(){

        @Override
        protected double evaluate(double[] values) throws EvaluationException {
            if (values.length < 1) {
                throw new EvaluationException(ErrorEval.DIV_ZERO);
            }
            return StatsLib.stdev(values);
        }
    };
    public static final Function SUM = new AggregateFunction(){

        @Override
        protected double evaluate(double[] values) {
            return MathX.sum(values);
        }
    };
    public static final Function SUMSQ = new AggregateFunction(){

        @Override
        protected double evaluate(double[] values) {
            return MathX.sumsq(values);
        }
    };
    public static final Function VAR = new AggregateFunction(){

        @Override
        protected double evaluate(double[] values) throws EvaluationException {
            if (values.length < 1) {
                throw new EvaluationException(ErrorEval.DIV_ZERO);
            }
            return StatsLib.var(values);
        }
    };
    public static final Function VARP = new AggregateFunction(){

        @Override
        protected double evaluate(double[] values) throws EvaluationException {
            if (values.length < 1) {
                throw new EvaluationException(ErrorEval.DIV_ZERO);
            }
            return StatsLib.varp(values);
        }
    };
    public static final Function GEOMEAN = new Geomean();

    protected AggregateFunction() {
        super(false, false);
    }

    static Function subtotalInstance(Function func, final boolean countHiddenRows) {
        final AggregateFunction arg = (AggregateFunction)func;
        return new AggregateFunction(){

            @Override
            protected double evaluate(double[] values) throws EvaluationException {
                return arg.evaluate(values);
            }

            @Override
            public boolean isSubtotalCounted() {
                return false;
            }

            @Override
            public boolean isHiddenRowCounted() {
                return countHiddenRows;
            }
        };
    }

    private static class Geomean
    extends AggregateFunction {
        Geomean() {
            this.setMissingArgPolicy(MultiOperandNumericFunction.Policy.COERCE);
        }

        @Override
        protected double evaluate(double[] values) throws EvaluationException {
            for (double value : values) {
                if (!(value <= 0.0)) continue;
                throw new EvaluationException(ErrorEval.NUM_ERROR);
            }
            return new GeometricMean().evaluate(values, 0, values.length);
        }
    }

    private static class Product
    extends AggregateFunction {
        Product() {
            this.setMissingArgPolicy(MultiOperandNumericFunction.Policy.SKIP);
        }

        @Override
        protected double evaluate(double[] values) throws EvaluationException {
            return MathX.product(values);
        }
    }

    static final class ValueCollector
    extends MultiOperandNumericFunction {
        private static final ValueCollector instance = new ValueCollector();

        public ValueCollector() {
            super(false, false);
        }

        public static double[] collectValues(ValueEval ... operands) throws EvaluationException {
            return instance.getNumberArray(operands);
        }

        @Override
        protected double evaluate(double[] values) {
            throw new IllegalStateException("should not be called");
        }
    }

    private static final class Percentile
    extends Fixed2ArgFunction {
        protected Percentile() {
        }

        @Override
        public ValueEval evaluate(int srcRowIndex, int srcColumnIndex, ValueEval arg0, ValueEval arg1) {
            double result;
            double dn;
            try {
                ValueEval ve1 = OperandResolver.getSingleValue(arg1, srcRowIndex, srcColumnIndex);
                dn = OperandResolver.coerceValueToDouble(ve1);
            } catch (EvaluationException e1) {
                return ErrorEval.VALUE_INVALID;
            }
            if (dn < 0.0 || dn > 1.0) {
                return ErrorEval.NUM_ERROR;
            }
            try {
                double[] ds = ValueCollector.collectValues(arg0);
                int N = ds.length;
                if (N == 0 || N > 8191) {
                    return ErrorEval.NUM_ERROR;
                }
                double n = (double)(N - 1) * dn + 1.0;
                if (n == 1.0) {
                    result = StatsLib.kthSmallest(ds, 1);
                } else if (Double.compare(n, N) == 0) {
                    result = StatsLib.kthLargest(ds, 1);
                } else {
                    int k = (int)n;
                    double d = n - (double)k;
                    result = StatsLib.kthSmallest(ds, k) + d * (StatsLib.kthSmallest(ds, k + 1) - StatsLib.kthSmallest(ds, k));
                }
                NumericFunction.checkValue(result);
            } catch (EvaluationException e) {
                return e.getErrorEval();
            }
            return new NumberEval(result);
        }
    }

    private static final class LargeSmall
    extends Fixed2ArgFunction {
        private final boolean _isLarge;

        protected LargeSmall(boolean isLarge) {
            this._isLarge = isLarge;
        }

        @Override
        public ValueEval evaluate(int srcRowIndex, int srcColumnIndex, ValueEval arg0, ValueEval arg1) {
            double result;
            double dn;
            try {
                ValueEval ve1 = OperandResolver.getSingleValue(arg1, srcRowIndex, srcColumnIndex);
                dn = OperandResolver.coerceValueToDouble(ve1);
            } catch (EvaluationException e1) {
                return ErrorEval.VALUE_INVALID;
            }
            if (dn < 1.0) {
                return ErrorEval.NUM_ERROR;
            }
            int k = (int)Math.ceil(dn);
            try {
                double[] ds = ValueCollector.collectValues(arg0);
                if (k > ds.length) {
                    return ErrorEval.NUM_ERROR;
                }
                result = this._isLarge ? StatsLib.kthLargest(ds, k) : StatsLib.kthSmallest(ds, k);
                NumericFunction.checkValue(result);
            } catch (EvaluationException e) {
                return e.getErrorEval();
            }
            return new NumberEval(result);
        }
    }
}

