/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.ss.formula.functions;

import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.linear.Array2DRowRealMatrix;
import org.apache.commons.math3.linear.LUDecomposition;
import org.apache.commons.math3.linear.MatrixUtils;
import org.apache.poi.ss.formula.CacheAreaEval;
import org.apache.poi.ss.formula.eval.AreaEval;
import org.apache.poi.ss.formula.eval.ErrorEval;
import org.apache.poi.ss.formula.eval.EvaluationException;
import org.apache.poi.ss.formula.eval.NumberEval;
import org.apache.poi.ss.formula.eval.OperandResolver;
import org.apache.poi.ss.formula.eval.ValueEval;
import org.apache.poi.ss.formula.functions.Fixed1ArgFunction;
import org.apache.poi.ss.formula.functions.Fixed2ArgFunction;
import org.apache.poi.ss.formula.functions.Function;
import org.apache.poi.ss.formula.functions.MultiOperandNumericFunction;
import org.apache.poi.ss.formula.functions.NumericFunction;

public abstract class MatrixFunction
implements Function {
    public static final Function MINVERSE = new OneArrayArg(){
        private final MutableValueCollector instance = new MutableValueCollector(false, false);

        @Override
        protected double[] collectValues(ValueEval arg) throws EvaluationException {
            double[] values = this.instance.collectValues(arg);
            if (arg instanceof AreaEval && values.length == 1) {
                throw new EvaluationException(ErrorEval.VALUE_INVALID);
            }
            return values;
        }

        @Override
        protected double[][] evaluate(double[][] d1) throws EvaluationException {
            if (d1.length != d1[0].length) {
                throw new EvaluationException(ErrorEval.VALUE_INVALID);
            }
            Array2DRowRealMatrix temp = new Array2DRowRealMatrix(d1);
            return MatrixUtils.inverse(temp).getData();
        }
    };
    public static final Function TRANSPOSE = new OneArrayArg(){
        private final MutableValueCollector instance = new MutableValueCollector(false, true);

        @Override
        protected double[] collectValues(ValueEval arg) throws EvaluationException {
            return this.instance.collectValues(arg);
        }

        @Override
        protected double[][] evaluate(double[][] d1) throws EvaluationException {
            Array2DRowRealMatrix temp = new Array2DRowRealMatrix(d1);
            return temp.transpose().getData();
        }
    };
    public static final Function MDETERM = new Mdeterm();
    public static final Function MMULT = new TwoArrayArg(){
        private final MutableValueCollector instance = new MutableValueCollector(false, false);

        @Override
        protected double[] collectValues(ValueEval arg) throws EvaluationException {
            double[] values = this.instance.collectValues(arg);
            if (arg instanceof AreaEval && values.length == 1) {
                throw new EvaluationException(ErrorEval.VALUE_INVALID);
            }
            return values;
        }

        @Override
        protected double[][] evaluate(double[][] d1, double[][] d2) throws EvaluationException {
            Array2DRowRealMatrix first = new Array2DRowRealMatrix(d1);
            Array2DRowRealMatrix second = new Array2DRowRealMatrix(d2);
            try {
                MatrixUtils.checkMultiplicationCompatible(first, second);
            } catch (DimensionMismatchException e) {
                throw new EvaluationException(ErrorEval.VALUE_INVALID);
            }
            return first.multiply(second).getData();
        }
    };

    public static void checkValues(double[] results) throws EvaluationException {
        for (double result : results) {
            if (!Double.isNaN(result) && !Double.isInfinite(result)) continue;
            throw new EvaluationException(ErrorEval.NUM_ERROR);
        }
    }

    protected final double singleOperandEvaluate(ValueEval arg, int srcCellRow, int srcCellCol) throws EvaluationException {
        ValueEval ve = OperandResolver.getSingleValue(arg, srcCellRow, srcCellCol);
        return OperandResolver.coerceValueToDouble(ve);
    }

    private static double[][] fillDoubleArray(double[] vector, int rows, int cols) throws EvaluationException {
        int i = 0;
        int j = 0;
        if (rows < 1 || cols < 1 || vector.length < 1) {
            throw new EvaluationException(ErrorEval.VALUE_INVALID);
        }
        double[][] matrix = new double[rows][cols];
        for (double aVector : vector) {
            if (j >= matrix.length) continue;
            if (i == matrix[0].length) {
                i = 0;
                ++j;
            }
            if (j >= matrix.length) continue;
            matrix[j][i++] = aVector;
        }
        return matrix;
    }

    private static double[] extractDoubleArray(double[][] matrix) throws EvaluationException {
        int idx = 0;
        if (matrix == null || matrix.length < 1 || matrix[0].length < 1) {
            throw new EvaluationException(ErrorEval.VALUE_INVALID);
        }
        double[] vector = new double[matrix.length * matrix[0].length];
        for (double[] aMatrix : matrix) {
            for (int i = 0; i < matrix[0].length; ++i) {
                vector[idx++] = aMatrix[i];
            }
        }
        return vector;
    }

    private static class Mdeterm
    extends OneArrayArg {
        private final MutableValueCollector instance = new MutableValueCollector(false, false);

        public Mdeterm() {
            this.instance.setBlankEvalPolicy(MultiOperandNumericFunction.Policy.ERROR);
        }

        @Override
        protected double[] collectValues(ValueEval arg) throws EvaluationException {
            double[] values = this.instance.collectValues(arg);
            if (arg instanceof AreaEval && values.length == 1) {
                throw new EvaluationException(ErrorEval.VALUE_INVALID);
            }
            return this.instance.collectValues(arg);
        }

        @Override
        protected double[][] evaluate(double[][] d1) throws EvaluationException {
            if (d1.length != d1[0].length) {
                throw new EvaluationException(ErrorEval.VALUE_INVALID);
            }
            double[][] result = new double[1][1];
            Array2DRowRealMatrix temp = new Array2DRowRealMatrix(d1);
            result[0][0] = new LUDecomposition(temp).getDeterminant();
            return result;
        }
    }

    public static final class MutableValueCollector
    extends MultiOperandNumericFunction {
        public MutableValueCollector(boolean isReferenceBoolCounted, boolean isBlankCounted) {
            super(isReferenceBoolCounted, isBlankCounted);
        }

        public double[] collectValues(ValueEval ... operands) throws EvaluationException {
            return this.getNumberArray(operands);
        }

        @Override
        protected double evaluate(double[] values) {
            throw new IllegalStateException("should not be called");
        }
    }

    public static abstract class TwoArrayArg
    extends Fixed2ArgFunction {
        protected TwoArrayArg() {
        }

        @Override
        public ValueEval evaluate(int srcRowIndex, int srcColumnIndex, ValueEval arg0, ValueEval arg1) {
            double[] result;
            int height;
            int width;
            try {
                Object array1;
                Object array0;
                double[] values;
                if (arg0 instanceof AreaEval) {
                    try {
                        values = this.collectValues(arg0);
                        array0 = MatrixFunction.fillDoubleArray(values, ((AreaEval)arg0).getHeight(), ((AreaEval)arg0).getWidth());
                    } catch (EvaluationException e) {
                        return e.getErrorEval();
                    }
                }
                try {
                    double value = NumericFunction.singleOperandEvaluate(arg0, srcRowIndex, srcColumnIndex);
                    array0 = new double[][]{{value}};
                } catch (EvaluationException e) {
                    return e.getErrorEval();
                }
                if (arg1 instanceof AreaEval) {
                    try {
                        values = this.collectValues(arg1);
                        array1 = MatrixFunction.fillDoubleArray(values, ((AreaEval)arg1).getHeight(), ((AreaEval)arg1).getWidth());
                    } catch (EvaluationException e) {
                        return e.getErrorEval();
                    }
                }
                try {
                    double value = NumericFunction.singleOperandEvaluate(arg1, srcRowIndex, srcColumnIndex);
                    array1 = new double[][]{{value}};
                } catch (EvaluationException e) {
                    return e.getErrorEval();
                }
                double[][] resultArray = this.evaluate((double[][])array0, (double[][])array1);
                width = resultArray[0].length;
                height = resultArray.length;
                result = MatrixFunction.extractDoubleArray(resultArray);
                MatrixFunction.checkValues(result);
            } catch (EvaluationException e) {
                return e.getErrorEval();
            } catch (IllegalArgumentException e) {
                return ErrorEval.VALUE_INVALID;
            }
            ValueEval[] vals = new ValueEval[result.length];
            for (int idx = 0; idx < result.length; ++idx) {
                vals[idx] = new NumberEval(result[idx]);
            }
            if (result.length == 1) {
                return vals[0];
            }
            return new CacheAreaEval(((AreaEval)arg0).getFirstRow(), ((AreaEval)arg0).getFirstColumn(), ((AreaEval)arg0).getFirstRow() + height - 1, ((AreaEval)arg0).getFirstColumn() + width - 1, vals);
        }

        protected abstract double[][] evaluate(double[][] var1, double[][] var2) throws EvaluationException;

        protected abstract double[] collectValues(ValueEval var1) throws EvaluationException;
    }

    public static abstract class OneArrayArg
    extends Fixed1ArgFunction {
        protected OneArrayArg() {
        }

        @Override
        public ValueEval evaluate(int srcRowIndex, int srcColumnIndex, ValueEval arg0) {
            double[][] result;
            if (arg0 instanceof AreaEval) {
                double[] result2;
                int height;
                int width;
                try {
                    double[] values = this.collectValues(arg0);
                    double[][] array = MatrixFunction.fillDoubleArray(values, ((AreaEval)arg0).getHeight(), ((AreaEval)arg0).getWidth());
                    double[][] resultArray = this.evaluate(array);
                    width = resultArray[0].length;
                    height = resultArray.length;
                    result2 = MatrixFunction.extractDoubleArray(resultArray);
                    MatrixFunction.checkValues(result2);
                } catch (EvaluationException e) {
                    return e.getErrorEval();
                }
                ValueEval[] vals = new ValueEval[result2.length];
                for (int idx = 0; idx < result2.length; ++idx) {
                    vals[idx] = new NumberEval(result2[idx]);
                }
                if (result2.length == 1) {
                    return vals[0];
                }
                return new CacheAreaEval(((AreaEval)arg0).getFirstRow(), ((AreaEval)arg0).getFirstColumn(), ((AreaEval)arg0).getFirstRow() + height - 1, ((AreaEval)arg0).getFirstColumn() + width - 1, vals);
            }
            try {
                double value = NumericFunction.singleOperandEvaluate(arg0, srcRowIndex, srcColumnIndex);
                double[][] temp = new double[][]{{value}};
                result = this.evaluate(temp);
                NumericFunction.checkValue(result[0][0]);
            } catch (EvaluationException e) {
                return e.getErrorEval();
            }
            return new NumberEval(result[0][0]);
        }

        protected abstract double[][] evaluate(double[][] var1) throws EvaluationException;

        protected abstract double[] collectValues(ValueEval var1) throws EvaluationException;
    }
}

