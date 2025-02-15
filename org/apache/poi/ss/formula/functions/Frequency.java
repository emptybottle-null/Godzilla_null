/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.ss.formula.functions;

import java.util.Arrays;
import org.apache.poi.ss.formula.CacheAreaEval;
import org.apache.poi.ss.formula.eval.EvaluationException;
import org.apache.poi.ss.formula.eval.NumberEval;
import org.apache.poi.ss.formula.eval.ValueEval;
import org.apache.poi.ss.formula.functions.Fixed2ArgFunction;
import org.apache.poi.ss.formula.functions.Function;
import org.apache.poi.ss.formula.functions.MatrixFunction;

public class Frequency
extends Fixed2ArgFunction {
    public static final Function instance = new Frequency();

    private Frequency() {
    }

    @Override
    public ValueEval evaluate(int srcRowIndex, int srcColumnIndex, ValueEval arg0, ValueEval arg1) {
        double[] bins;
        double[] values;
        MatrixFunction.MutableValueCollector collector = new MatrixFunction.MutableValueCollector(false, false);
        try {
            values = collector.collectValues(arg0);
            bins = collector.collectValues(arg1);
        } catch (EvaluationException e) {
            return e.getErrorEval();
        }
        int[] histogram = Frequency.histogram(values, bins);
        ValueEval[] result = (NumberEval[])Arrays.stream(histogram).boxed().map(NumberEval::new).toArray(NumberEval[]::new);
        return new CacheAreaEval(srcRowIndex, srcColumnIndex, srcRowIndex + result.length - 1, srcColumnIndex, result);
    }

    static int findBin(double value, double[] bins) {
        int idx = Arrays.binarySearch(bins, value);
        return idx >= 0 ? idx + 1 : -idx;
    }

    static int[] histogram(double[] values, double[] bins) {
        int[] histogram = new int[bins.length + 1];
        for (double val : values) {
            int n = Frequency.findBin(val, bins) - 1;
            histogram[n] = histogram[n] + 1;
        }
        return histogram;
    }
}

