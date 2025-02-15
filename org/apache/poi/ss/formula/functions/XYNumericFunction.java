/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.ss.formula.functions;

import org.apache.poi.ss.formula.TwoDEval;
import org.apache.poi.ss.formula.eval.ErrorEval;
import org.apache.poi.ss.formula.eval.EvaluationException;
import org.apache.poi.ss.formula.eval.NumberEval;
import org.apache.poi.ss.formula.eval.RefEval;
import org.apache.poi.ss.formula.eval.ValueEval;
import org.apache.poi.ss.formula.functions.Fixed2ArgFunction;
import org.apache.poi.ss.formula.functions.LookupUtils;

public abstract class XYNumericFunction
extends Fixed2ArgFunction {
    protected abstract Accumulator createAccumulator();

    @Override
    public ValueEval evaluate(int srcRowIndex, int srcColumnIndex, ValueEval arg0, ValueEval arg1) {
        double result;
        try {
            LookupUtils.ValueVector vvX = XYNumericFunction.createValueVector(arg0);
            LookupUtils.ValueVector vvY = XYNumericFunction.createValueVector(arg1);
            int size = vvX.getSize();
            if (size == 0 || vvY.getSize() != size) {
                return ErrorEval.NA;
            }
            result = this.evaluateInternal(vvX, vvY, size);
        } catch (EvaluationException e) {
            return e.getErrorEval();
        }
        if (Double.isNaN(result) || Double.isInfinite(result)) {
            return ErrorEval.NUM_ERROR;
        }
        return new NumberEval(result);
    }

    private double evaluateInternal(LookupUtils.ValueVector x, LookupUtils.ValueVector y, int size) throws EvaluationException {
        Accumulator acc = this.createAccumulator();
        ErrorEval firstXerr = null;
        ErrorEval firstYerr = null;
        boolean accumlatedSome = false;
        double result = 0.0;
        for (int i = 0; i < size; ++i) {
            ValueEval vx = x.getItem(i);
            ValueEval vy = y.getItem(i);
            if (vx instanceof ErrorEval && firstXerr == null) {
                firstXerr = (ErrorEval)vx;
                continue;
            }
            if (vy instanceof ErrorEval && firstYerr == null) {
                firstYerr = (ErrorEval)vy;
                continue;
            }
            if (!(vx instanceof NumberEval) || !(vy instanceof NumberEval)) continue;
            accumlatedSome = true;
            NumberEval nx = (NumberEval)vx;
            NumberEval ny = (NumberEval)vy;
            result += acc.accumulate(nx.getNumberValue(), ny.getNumberValue());
        }
        if (firstXerr != null) {
            throw new EvaluationException(firstXerr);
        }
        if (firstYerr != null) {
            throw new EvaluationException(firstYerr);
        }
        if (!accumlatedSome) {
            throw new EvaluationException(ErrorEval.DIV_ZERO);
        }
        return result;
    }

    private static LookupUtils.ValueVector createValueVector(ValueEval arg) throws EvaluationException {
        if (arg instanceof ErrorEval) {
            throw new EvaluationException((ErrorEval)arg);
        }
        if (arg instanceof TwoDEval) {
            return new AreaValueArray((TwoDEval)arg);
        }
        if (arg instanceof RefEval) {
            return new RefValueArray((RefEval)arg);
        }
        return new SingleCellValueArray(arg);
    }

    protected static interface Accumulator {
        public double accumulate(double var1, double var3);
    }

    private static final class AreaValueArray
    extends ValueArray {
        private final TwoDEval _ae;
        private final int _width;

        public AreaValueArray(TwoDEval ae) {
            super(ae.getWidth() * ae.getHeight());
            this._ae = ae;
            this._width = ae.getWidth();
        }

        @Override
        protected ValueEval getItemInternal(int index) {
            int rowIx = index / this._width;
            int colIx = index % this._width;
            return this._ae.getValue(rowIx, colIx);
        }
    }

    private static final class RefValueArray
    extends ValueArray {
        private final RefEval _ref;
        private final int _width;

        public RefValueArray(RefEval ref) {
            super(ref.getNumberOfSheets());
            this._ref = ref;
            this._width = ref.getNumberOfSheets();
        }

        @Override
        protected ValueEval getItemInternal(int index) {
            int sIx = index % this._width + this._ref.getFirstSheetIndex();
            return this._ref.getInnerValueEval(sIx);
        }
    }

    private static final class SingleCellValueArray
    extends ValueArray {
        private final ValueEval _value;

        public SingleCellValueArray(ValueEval value) {
            super(1);
            this._value = value;
        }

        @Override
        protected ValueEval getItemInternal(int index) {
            return this._value;
        }
    }

    private static abstract class ValueArray
    implements LookupUtils.ValueVector {
        private final int _size;

        protected ValueArray(int size) {
            this._size = size;
        }

        @Override
        public ValueEval getItem(int index) {
            if (index < 0 || index > this._size) {
                throw new IllegalArgumentException("Specified index " + index + " is outside range (0.." + (this._size - 1) + ")");
            }
            return this.getItemInternal(index);
        }

        protected abstract ValueEval getItemInternal(int var1);

        @Override
        public final int getSize() {
            return this._size;
        }
    }
}

