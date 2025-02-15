/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.ss.formula.functions;

import java.util.function.BiFunction;
import java.util.function.Function;
import org.apache.poi.ss.formula.CacheAreaEval;
import org.apache.poi.ss.formula.FormulaParseException;
import org.apache.poi.ss.formula.eval.AreaEval;
import org.apache.poi.ss.formula.eval.ErrorEval;
import org.apache.poi.ss.formula.eval.EvaluationException;
import org.apache.poi.ss.formula.eval.OperandResolver;
import org.apache.poi.ss.formula.eval.RefEval;
import org.apache.poi.ss.formula.eval.ValueEval;

public interface ArrayFunction {
    public ValueEval evaluateArray(ValueEval[] var1, int var2, int var3);

    default public ValueEval evaluateTwoArrayArgs(ValueEval arg0, ValueEval arg1, int srcRowIndex, int srcColumnIndex, BiFunction<ValueEval, ValueEval, ValueEval> evalFunc) {
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
        int width = Math.max(w1, w2);
        int height = Math.max(h1, h2);
        ValueEval[] vals = new ValueEval[height * width];
        int idx = 0;
        for (int i = 0; i < height; ++i) {
            for (int j = 0; j < width; ++j) {
                ValueEval vB;
                ValueEval vA;
                try {
                    vA = OperandResolver.getSingleValue(arg0, a1FirstRow + i, a1FirstCol + j);
                } catch (FormulaParseException e) {
                    vA = ErrorEval.NAME_INVALID;
                } catch (EvaluationException e) {
                    vA = e.getErrorEval();
                } catch (RuntimeException e) {
                    if (e.getMessage().startsWith("Don't know how to evaluate name")) {
                        vA = ErrorEval.NAME_INVALID;
                    }
                    throw e;
                }
                try {
                    vB = OperandResolver.getSingleValue(arg1, a2FirstRow + i, a2FirstCol + j);
                } catch (FormulaParseException e) {
                    vB = ErrorEval.NAME_INVALID;
                } catch (EvaluationException e) {
                    vB = e.getErrorEval();
                } catch (RuntimeException e) {
                    if (e.getMessage().startsWith("Don't know how to evaluate name")) {
                        vB = ErrorEval.NAME_INVALID;
                    }
                    throw e;
                }
                vals[idx++] = vA instanceof ErrorEval ? vA : (vB instanceof ErrorEval ? vB : evalFunc.apply(vA, vB));
            }
        }
        if (vals.length == 1) {
            return vals[0];
        }
        return new CacheAreaEval(srcRowIndex, srcColumnIndex, srcRowIndex + height - 1, srcColumnIndex + width - 1, vals);
    }

    default public ValueEval evaluateOneArrayArg(ValueEval arg0, int srcRowIndex, int srcColumnIndex, Function<ValueEval, ValueEval> evalFunc) {
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
        int w2 = 1;
        int h2 = 1;
        int width = Math.max(w1, w2);
        int height = Math.max(h1, h2);
        ValueEval[] vals = new ValueEval[height * width];
        int idx = 0;
        for (int i = 0; i < height; ++i) {
            for (int j = 0; j < width; ++j) {
                ValueEval vA;
                try {
                    vA = OperandResolver.getSingleValue(arg0, a1FirstRow + i, a1FirstCol + j);
                } catch (FormulaParseException e) {
                    vA = ErrorEval.NAME_INVALID;
                } catch (EvaluationException e) {
                    vA = e.getErrorEval();
                } catch (RuntimeException e) {
                    if (e.getMessage().startsWith("Don't know how to evaluate name")) {
                        vA = ErrorEval.NAME_INVALID;
                    }
                    throw e;
                }
                vals[idx++] = evalFunc.apply(vA);
            }
        }
        if (vals.length == 1) {
            return vals[0];
        }
        return new CacheAreaEval(srcRowIndex, srcColumnIndex, srcRowIndex + height - 1, srcColumnIndex + width - 1, vals);
    }
}

