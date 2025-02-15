/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.ss.formula.functions;

import java.util.Arrays;
import org.apache.commons.math3.linear.SingularMatrixException;
import org.apache.commons.math3.stat.regression.OLSMultipleLinearRegression;
import org.apache.poi.ss.formula.CacheAreaEval;
import org.apache.poi.ss.formula.eval.AreaEval;
import org.apache.poi.ss.formula.eval.BoolEval;
import org.apache.poi.ss.formula.eval.ErrorEval;
import org.apache.poi.ss.formula.eval.EvaluationException;
import org.apache.poi.ss.formula.eval.MissingArgEval;
import org.apache.poi.ss.formula.eval.NotImplementedException;
import org.apache.poi.ss.formula.eval.NumberEval;
import org.apache.poi.ss.formula.eval.NumericValueEval;
import org.apache.poi.ss.formula.eval.RefEval;
import org.apache.poi.ss.formula.eval.ValueEval;
import org.apache.poi.ss.formula.functions.Function;
import org.apache.poi.ss.formula.functions.MatrixFunction;

public final class Trend
implements Function {
    MatrixFunction.MutableValueCollector collector = new MatrixFunction.MutableValueCollector(false, false);

    @Override
    public ValueEval evaluate(ValueEval[] args, int srcRowIndex, int srcColumnIndex) {
        if (args.length < 1 || args.length > 4) {
            return ErrorEval.VALUE_INVALID;
        }
        try {
            TrendResults tr = Trend.getNewY(args);
            ValueEval[] vals = new ValueEval[tr.vals.length];
            for (int i = 0; i < tr.vals.length; ++i) {
                vals[i] = new NumberEval(tr.vals[i]);
            }
            if (tr.vals.length == 1) {
                return vals[0];
            }
            return new CacheAreaEval(srcRowIndex, srcColumnIndex, srcRowIndex + tr.resultHeight - 1, srcColumnIndex + tr.resultWidth - 1, vals);
        } catch (EvaluationException e) {
            return e.getErrorEval();
        }
    }

    private static double[][] evalToArray(ValueEval arg) throws EvaluationException {
        double[][] ar;
        ValueEval eval;
        if (arg instanceof MissingArgEval) {
            return new double[0][0];
        }
        if (arg instanceof RefEval) {
            RefEval re = (RefEval)arg;
            if (re.getNumberOfSheets() > 1) {
                throw new EvaluationException(ErrorEval.VALUE_INVALID);
            }
            eval = re.getInnerValueEval(re.getFirstSheetIndex());
        } else {
            eval = arg;
        }
        if (eval == null) {
            throw new RuntimeException("Parameter may not be null.");
        }
        if (eval instanceof AreaEval) {
            AreaEval ae = (AreaEval)eval;
            int w = ae.getWidth();
            int h = ae.getHeight();
            ar = new double[h][w];
            for (int i = 0; i < h; ++i) {
                for (int j = 0; j < w; ++j) {
                    ValueEval ve = ae.getRelativeValue(i, j);
                    if (!(ve instanceof NumericValueEval)) {
                        throw new EvaluationException(ErrorEval.VALUE_INVALID);
                    }
                    ar[i][j] = ((NumericValueEval)ve).getNumberValue();
                }
            }
        } else if (eval instanceof NumericValueEval) {
            ar = new double[1][1];
            ar[0][0] = ((NumericValueEval)eval).getNumberValue();
        } else {
            throw new EvaluationException(ErrorEval.VALUE_INVALID);
        }
        return ar;
    }

    private static double[][] getDefaultArrayOneD(int w) {
        double[][] array = new double[w][1];
        for (int i = 0; i < w; ++i) {
            array[i][0] = i + 1;
        }
        return array;
    }

    private static double[] flattenArray(double[][] twoD) {
        if (twoD.length < 1) {
            return new double[0];
        }
        double[] oneD = new double[twoD.length * twoD[0].length];
        for (int i = 0; i < twoD.length; ++i) {
            System.arraycopy(twoD[i], 0, oneD, i * twoD[0].length + 0, twoD[0].length);
        }
        return oneD;
    }

    private static double[][] flattenArrayToRow(double[][] twoD) {
        if (twoD.length < 1) {
            return new double[0][0];
        }
        double[][] oneD = new double[twoD.length * twoD[0].length][1];
        for (int i = 0; i < twoD.length; ++i) {
            for (int j = 0; j < twoD[0].length; ++j) {
                oneD[i * twoD[0].length + j][0] = twoD[i][j];
            }
        }
        return oneD;
    }

    private static double[][] switchRowsColumns(double[][] array) {
        double[][] newArray = new double[array[0].length][array.length];
        for (int i = 0; i < array.length; ++i) {
            for (int j = 0; j < array[0].length; ++j) {
                newArray[j][i] = array[i][j];
            }
        }
        return newArray;
    }

    private static boolean isAllColumnsSame(double[][] matrix) {
        if (matrix.length == 0) {
            return false;
        }
        boolean[] cols = new boolean[matrix[0].length];
        block0: for (int j = 0; j < matrix[0].length; ++j) {
            double prev = Double.NaN;
            for (int i = 0; i < matrix.length; ++i) {
                double v = matrix[i][j];
                if (i > 0 && v != prev) {
                    cols[j] = true;
                    continue block0;
                }
                prev = v;
            }
        }
        boolean allEquals = true;
        for (boolean x : cols) {
            if (!x) continue;
            allEquals = false;
            break;
        }
        return allEquals;
    }

    private static TrendResults getNewY(ValueEval[] args) throws EvaluationException {
        double[] par;
        double[][] x;
        double[][] newXOrig;
        double[][] xOrig;
        double[][] yOrig;
        boolean passThroughOrigin = false;
        switch (args.length) {
            case 1: {
                yOrig = Trend.evalToArray(args[0]);
                xOrig = new double[0][0];
                newXOrig = new double[0][0];
                break;
            }
            case 2: {
                yOrig = Trend.evalToArray(args[0]);
                xOrig = Trend.evalToArray(args[1]);
                newXOrig = new double[0][0];
                break;
            }
            case 3: {
                yOrig = Trend.evalToArray(args[0]);
                xOrig = Trend.evalToArray(args[1]);
                newXOrig = Trend.evalToArray(args[2]);
                break;
            }
            case 4: {
                yOrig = Trend.evalToArray(args[0]);
                xOrig = Trend.evalToArray(args[1]);
                newXOrig = Trend.evalToArray(args[2]);
                if (!(args[3] instanceof BoolEval)) {
                    throw new EvaluationException(ErrorEval.VALUE_INVALID);
                }
                passThroughOrigin = !((BoolEval)args[3]).getBooleanValue();
                break;
            }
            default: {
                throw new EvaluationException(ErrorEval.VALUE_INVALID);
            }
        }
        if (yOrig.length < 1) {
            throw new EvaluationException(ErrorEval.VALUE_INVALID);
        }
        double[] y = Trend.flattenArray(yOrig);
        double[][] newX = newXOrig;
        double[][] resultSize = newXOrig.length > 0 ? newXOrig : new double[1][1];
        if (y.length == 1) {
            throw new NotImplementedException("Sample size too small");
        }
        if (yOrig.length == 1 || yOrig[0].length == 1) {
            if (xOrig.length < 1) {
                x = Trend.getDefaultArrayOneD(y.length);
                if (newXOrig.length < 1) {
                    resultSize = yOrig;
                }
            } else {
                x = xOrig;
                if (xOrig[0].length > 1 && yOrig.length == 1) {
                    x = Trend.switchRowsColumns(x);
                }
                if (newXOrig.length < 1) {
                    resultSize = xOrig;
                }
            }
            if (newXOrig.length > 0 && (x.length == 1 || x[0].length == 1)) {
                newX = Trend.flattenArrayToRow(newXOrig);
            }
        } else {
            if (xOrig.length < 1) {
                x = Trend.getDefaultArrayOneD(y.length);
                if (newXOrig.length < 1) {
                    resultSize = yOrig;
                }
            } else {
                x = Trend.flattenArrayToRow(xOrig);
                if (newXOrig.length < 1) {
                    resultSize = xOrig;
                }
            }
            if (newXOrig.length > 0) {
                newX = Trend.flattenArrayToRow(newXOrig);
            }
            if (y.length != x.length || yOrig.length != xOrig.length) {
                throw new EvaluationException(ErrorEval.REF_INVALID);
            }
        }
        if (newXOrig.length < 1) {
            newX = x;
        } else if (newXOrig.length == 1 && newXOrig[0].length > 1 && xOrig.length > 1 && xOrig[0].length == 1) {
            newX = Trend.switchRowsColumns(newXOrig);
        }
        if (newX[0].length != x[0].length) {
            throw new EvaluationException(ErrorEval.REF_INVALID);
        }
        if (x[0].length >= x.length) {
            throw new NotImplementedException("Sample size too small");
        }
        int resultHeight = resultSize.length;
        int resultWidth = resultSize[0].length;
        if (Trend.isAllColumnsSame(x)) {
            double[] result = new double[newX.length];
            double avg = Arrays.stream(y).average().orElse(0.0);
            for (int i = 0; i < result.length; ++i) {
                result[i] = avg;
            }
            return new TrendResults(result, resultWidth, resultHeight);
        }
        OLSMultipleLinearRegression reg = new OLSMultipleLinearRegression();
        if (passThroughOrigin) {
            reg.setNoIntercept(true);
        }
        try {
            reg.newSampleData(y, x);
        } catch (IllegalArgumentException e) {
            throw new EvaluationException(ErrorEval.REF_INVALID);
        }
        try {
            par = reg.estimateRegressionParameters();
        } catch (SingularMatrixException e) {
            throw new NotImplementedException("Singular matrix in input");
        }
        double[] result = new double[newX.length];
        for (int i = 0; i < newX.length; ++i) {
            int j;
            result[i] = 0.0;
            if (passThroughOrigin) {
                for (j = 0; j < par.length; ++j) {
                    int n = i;
                    result[n] = result[n] + par[j] * newX[i][j];
                }
                continue;
            }
            result[i] = par[0];
            for (j = 1; j < par.length; ++j) {
                int n = i;
                result[n] = result[n] + par[j] * newX[i][j - 1];
            }
        }
        return new TrendResults(result, resultWidth, resultHeight);
    }

    private static final class TrendResults {
        public double[] vals;
        public int resultWidth;
        public int resultHeight;

        public TrendResults(double[] vals, int resultWidth, int resultHeight) {
            this.vals = vals;
            this.resultWidth = resultWidth;
            this.resultHeight = resultHeight;
        }
    }
}

