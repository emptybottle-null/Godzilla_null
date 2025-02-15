/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.ss.formula.functions;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.poi.ss.formula.TwoDEval;
import org.apache.poi.ss.formula.eval.BlankEval;
import org.apache.poi.ss.formula.eval.BoolEval;
import org.apache.poi.ss.formula.eval.ErrorEval;
import org.apache.poi.ss.formula.eval.EvaluationException;
import org.apache.poi.ss.formula.eval.NumberEval;
import org.apache.poi.ss.formula.eval.NumericValueEval;
import org.apache.poi.ss.formula.eval.OperandResolver;
import org.apache.poi.ss.formula.eval.RefEval;
import org.apache.poi.ss.formula.eval.StringEval;
import org.apache.poi.ss.formula.eval.ValueEval;
import org.apache.poi.ss.formula.functions.Countif;

final class LookupUtils {
    LookupUtils() {
    }

    public static ValueVector createRowVector(TwoDEval tableArray, int relativeRowIndex) {
        return new RowVector(tableArray, relativeRowIndex);
    }

    public static ValueVector createColumnVector(TwoDEval tableArray, int relativeColumnIndex) {
        return new ColumnVector(tableArray, relativeColumnIndex);
    }

    public static ValueVector createVector(TwoDEval ae) {
        if (ae.isColumn()) {
            return LookupUtils.createColumnVector(ae, 0);
        }
        if (ae.isRow()) {
            return LookupUtils.createRowVector(ae, 0);
        }
        return null;
    }

    public static ValueVector createVector(RefEval re) {
        return new SheetVector(re);
    }

    public static int resolveRowOrColIndexArg(ValueEval rowColIndexArg, int srcCellRow, int srcCellCol) throws EvaluationException {
        StringEval se;
        String strVal;
        Double dVal;
        ValueEval veRowColIndexArg;
        if (rowColIndexArg == null) {
            throw new IllegalArgumentException("argument must not be null");
        }
        try {
            veRowColIndexArg = OperandResolver.getSingleValue(rowColIndexArg, srcCellRow, (short)srcCellCol);
        } catch (EvaluationException e) {
            throw EvaluationException.invalidRef();
        }
        if (veRowColIndexArg instanceof StringEval && (dVal = OperandResolver.parseDouble(strVal = (se = (StringEval)veRowColIndexArg).getStringValue())) == null) {
            throw EvaluationException.invalidRef();
        }
        int oneBasedIndex = OperandResolver.coerceValueToInt(veRowColIndexArg);
        if (oneBasedIndex < 1) {
            throw EvaluationException.invalidValue();
        }
        return oneBasedIndex - 1;
    }

    public static TwoDEval resolveTableArrayArg(ValueEval eval) throws EvaluationException {
        if (eval instanceof TwoDEval) {
            return (TwoDEval)eval;
        }
        if (eval instanceof RefEval) {
            RefEval refEval = (RefEval)eval;
            return refEval.offset(0, 0, 0, 0);
        }
        throw EvaluationException.invalidValue();
    }

    public static boolean resolveRangeLookupArg(ValueEval rangeLookupArg, int srcCellRow, int srcCellCol) throws EvaluationException {
        ValueEval valEval = OperandResolver.getSingleValue(rangeLookupArg, srcCellRow, srcCellCol);
        if (valEval instanceof BlankEval) {
            return false;
        }
        if (valEval instanceof BoolEval) {
            BoolEval boolEval = (BoolEval)valEval;
            return boolEval.getBooleanValue();
        }
        if (valEval instanceof StringEval) {
            String stringValue = ((StringEval)valEval).getStringValue();
            if (stringValue.length() < 1) {
                throw EvaluationException.invalidValue();
            }
            Boolean b = Countif.parseBoolean(stringValue);
            if (b != null) {
                return b;
            }
            throw EvaluationException.invalidValue();
        }
        if (valEval instanceof NumericValueEval) {
            NumericValueEval nve = (NumericValueEval)valEval;
            return 0.0 != nve.getNumberValue();
        }
        throw new RuntimeException("Unexpected eval type (" + valEval + ")");
    }

    public static int lookupIndexOfValue(ValueEval lookupValue, ValueVector vector, boolean isRangeLookup) throws EvaluationException {
        LookupValueComparer lookupComparer = LookupUtils.createLookupComparer(lookupValue, isRangeLookup, false);
        int result = isRangeLookup ? LookupUtils.performBinarySearch(vector, lookupComparer) : LookupUtils.lookupIndexOfExactValue(lookupComparer, vector);
        if (result < 0) {
            throw new EvaluationException(ErrorEval.NA);
        }
        return result;
    }

    private static int lookupIndexOfExactValue(LookupValueComparer lookupComparer, ValueVector vector) {
        int size = vector.getSize();
        for (int i = 0; i < size; ++i) {
            if (!lookupComparer.compareTo(vector.getItem(i)).isEqual()) continue;
            return i;
        }
        return -1;
    }

    private static int performBinarySearch(ValueVector vector, LookupValueComparer lookupComparer) {
        BinarySearchIndexes bsi = new BinarySearchIndexes(vector.getSize());
        int midIx;
        while ((midIx = bsi.getMidIx()) >= 0) {
            CompareResult cr = lookupComparer.compareTo(vector.getItem(midIx));
            if (cr.isTypeMismatch()) {
                int newMidIx = LookupUtils.handleMidValueTypeMismatch(lookupComparer, vector, bsi, midIx);
                if (newMidIx < 0) continue;
                midIx = newMidIx;
                cr = lookupComparer.compareTo(vector.getItem(midIx));
            }
            if (cr.isEqual()) {
                return LookupUtils.findLastIndexInRunOfEqualValues(lookupComparer, vector, midIx, bsi.getHighIx());
            }
            bsi.narrowSearch(midIx, cr.isLessThan());
        }
        return bsi.getLowIx();
    }

    private static int handleMidValueTypeMismatch(LookupValueComparer lookupComparer, ValueVector vector, BinarySearchIndexes bsi, int midIx) {
        CompareResult cr;
        int newMid = midIx;
        int highIx = bsi.getHighIx();
        do {
            if (++newMid == highIx) {
                bsi.narrowSearch(midIx, true);
                return -1;
            }
            cr = lookupComparer.compareTo(vector.getItem(newMid));
            if (!cr.isLessThan() || newMid != highIx - 1) continue;
            bsi.narrowSearch(midIx, true);
            return -1;
        } while (cr.isTypeMismatch());
        if (cr.isEqual()) {
            return newMid;
        }
        bsi.narrowSearch(newMid, cr.isLessThan());
        return -1;
    }

    private static int findLastIndexInRunOfEqualValues(LookupValueComparer lookupComparer, ValueVector vector, int firstFoundIndex, int maxIx) {
        for (int i = firstFoundIndex + 1; i < maxIx; ++i) {
            if (lookupComparer.compareTo(vector.getItem(i)).isEqual()) continue;
            return i - 1;
        }
        return maxIx - 1;
    }

    public static LookupValueComparer createLookupComparer(ValueEval lookupValue, boolean matchExact, boolean isMatchFunction) {
        if (lookupValue == BlankEval.instance) {
            return new NumberLookupComparer(NumberEval.ZERO);
        }
        if (lookupValue instanceof StringEval) {
            return new StringLookupComparer((StringEval)lookupValue, matchExact, isMatchFunction);
        }
        if (lookupValue instanceof NumberEval) {
            return new NumberLookupComparer((NumberEval)lookupValue);
        }
        if (lookupValue instanceof BoolEval) {
            return new BooleanLookupComparer((BoolEval)lookupValue);
        }
        throw new IllegalArgumentException("Bad lookup value type (" + lookupValue.getClass().getName() + ")");
    }

    private static final class BinarySearchIndexes {
        private int _lowIx = -1;
        private int _highIx;

        public BinarySearchIndexes(int highIx) {
            this._highIx = highIx;
        }

        public int getMidIx() {
            int ixDiff = this._highIx - this._lowIx;
            if (ixDiff < 2) {
                return -1;
            }
            return this._lowIx + ixDiff / 2;
        }

        public int getLowIx() {
            return this._lowIx;
        }

        public int getHighIx() {
            return this._highIx;
        }

        public void narrowSearch(int midIx, boolean isLessThan) {
            if (isLessThan) {
                this._highIx = midIx;
            } else {
                this._lowIx = midIx;
            }
        }
    }

    private static final class BooleanLookupComparer
    extends LookupValueComparerBase {
        private boolean _value;

        protected BooleanLookupComparer(BoolEval be) {
            super(be);
            this._value = be.getBooleanValue();
        }

        @Override
        protected CompareResult compareSameType(ValueEval other) {
            BoolEval be = (BoolEval)other;
            boolean otherVal = be.getBooleanValue();
            if (this._value == otherVal) {
                return CompareResult.EQUAL;
            }
            if (this._value) {
                return CompareResult.GREATER_THAN;
            }
            return CompareResult.LESS_THAN;
        }

        @Override
        protected String getValueAsString() {
            return String.valueOf(this._value);
        }
    }

    private static final class NumberLookupComparer
    extends LookupValueComparerBase {
        private double _value;

        protected NumberLookupComparer(NumberEval ne) {
            super(ne);
            this._value = ne.getNumberValue();
        }

        @Override
        protected CompareResult compareSameType(ValueEval other) {
            NumberEval ne = (NumberEval)other;
            return CompareResult.valueOf(Double.compare(this._value, ne.getNumberValue()));
        }

        @Override
        protected String getValueAsString() {
            return String.valueOf(this._value);
        }
    }

    private static final class StringLookupComparer
    extends LookupValueComparerBase {
        private String _value;
        private final Pattern _wildCardPattern;
        private boolean _matchExact;
        private boolean _isMatchFunction;

        protected StringLookupComparer(StringEval se, boolean matchExact, boolean isMatchFunction) {
            super(se);
            this._value = se.getStringValue();
            this._wildCardPattern = Countif.StringMatcher.getWildCardPattern(this._value);
            this._matchExact = matchExact;
            this._isMatchFunction = isMatchFunction;
        }

        @Override
        protected CompareResult compareSameType(ValueEval other) {
            StringEval se = (StringEval)other;
            String stringValue = se.getStringValue();
            if (this._wildCardPattern != null) {
                Matcher matcher = this._wildCardPattern.matcher(stringValue);
                boolean matches = matcher.matches();
                if (this._isMatchFunction || !this._matchExact) {
                    return CompareResult.valueOf(matches);
                }
            }
            return CompareResult.valueOf(this._value.compareToIgnoreCase(stringValue));
        }

        @Override
        protected String getValueAsString() {
            return this._value;
        }
    }

    private static abstract class LookupValueComparerBase
    implements LookupValueComparer {
        private final Class<? extends ValueEval> _targetClass;

        protected LookupValueComparerBase(ValueEval targetValue) {
            if (targetValue == null) {
                throw new RuntimeException("targetValue cannot be null");
            }
            this._targetClass = targetValue.getClass();
        }

        @Override
        public final CompareResult compareTo(ValueEval other) {
            if (other == null) {
                throw new RuntimeException("compare to value cannot be null");
            }
            if (this._targetClass != other.getClass()) {
                return CompareResult.TYPE_MISMATCH;
            }
            return this.compareSameType(other);
        }

        public String toString() {
            return this.getClass().getName() + " [" + this.getValueAsString() + "]";
        }

        protected abstract CompareResult compareSameType(ValueEval var1);

        protected abstract String getValueAsString();
    }

    public static interface LookupValueComparer {
        public CompareResult compareTo(ValueEval var1);
    }

    public static final class CompareResult {
        private final boolean _isTypeMismatch;
        private final boolean _isLessThan;
        private final boolean _isEqual;
        private final boolean _isGreaterThan;
        public static final CompareResult TYPE_MISMATCH = new CompareResult(true, 0);
        public static final CompareResult LESS_THAN = new CompareResult(false, -1);
        public static final CompareResult EQUAL = new CompareResult(false, 0);
        public static final CompareResult GREATER_THAN = new CompareResult(false, 1);

        private CompareResult(boolean isTypeMismatch, int simpleCompareResult) {
            if (isTypeMismatch) {
                this._isTypeMismatch = true;
                this._isLessThan = false;
                this._isEqual = false;
                this._isGreaterThan = false;
            } else {
                this._isTypeMismatch = false;
                this._isLessThan = simpleCompareResult < 0;
                this._isEqual = simpleCompareResult == 0;
                this._isGreaterThan = simpleCompareResult > 0;
            }
        }

        public static CompareResult valueOf(int simpleCompareResult) {
            if (simpleCompareResult < 0) {
                return LESS_THAN;
            }
            if (simpleCompareResult > 0) {
                return GREATER_THAN;
            }
            return EQUAL;
        }

        public static CompareResult valueOf(boolean matches) {
            if (matches) {
                return EQUAL;
            }
            return LESS_THAN;
        }

        public boolean isTypeMismatch() {
            return this._isTypeMismatch;
        }

        public boolean isLessThan() {
            return this._isLessThan;
        }

        public boolean isEqual() {
            return this._isEqual;
        }

        public boolean isGreaterThan() {
            return this._isGreaterThan;
        }

        public String toString() {
            return this.getClass().getName() + " [" + this.formatAsString() + "]";
        }

        private String formatAsString() {
            if (this._isTypeMismatch) {
                return "TYPE_MISMATCH";
            }
            if (this._isLessThan) {
                return "LESS_THAN";
            }
            if (this._isEqual) {
                return "EQUAL";
            }
            if (this._isGreaterThan) {
                return "GREATER_THAN";
            }
            return "??error??";
        }
    }

    private static final class SheetVector
    implements ValueVector {
        private final RefEval _re;
        private final int _size;

        public SheetVector(RefEval re) {
            this._size = re.getNumberOfSheets();
            this._re = re;
        }

        @Override
        public ValueEval getItem(int index) {
            if (index >= this._size) {
                throw new ArrayIndexOutOfBoundsException("Specified index (" + index + ") is outside the allowed range (0.." + (this._size - 1) + ")");
            }
            int sheetIndex = this._re.getFirstSheetIndex() + index;
            return this._re.getInnerValueEval(sheetIndex);
        }

        @Override
        public int getSize() {
            return this._size;
        }
    }

    private static final class ColumnVector
    implements ValueVector {
        private final TwoDEval _tableArray;
        private final int _size;
        private final int _columnIndex;

        public ColumnVector(TwoDEval tableArray, int columnIndex) {
            this._columnIndex = columnIndex;
            int lastColIx = tableArray.getWidth() - 1;
            if (columnIndex < 0 || columnIndex > lastColIx) {
                throw new IllegalArgumentException("Specified column index (" + columnIndex + ") is outside the allowed range (0.." + lastColIx + ")");
            }
            this._tableArray = tableArray;
            this._size = this._tableArray.getHeight();
        }

        @Override
        public ValueEval getItem(int index) {
            if (index > this._size) {
                throw new ArrayIndexOutOfBoundsException("Specified index (" + index + ") is outside the allowed range (0.." + (this._size - 1) + ")");
            }
            return this._tableArray.getValue(index, this._columnIndex);
        }

        @Override
        public int getSize() {
            return this._size;
        }
    }

    private static final class RowVector
    implements ValueVector {
        private final TwoDEval _tableArray;
        private final int _size;
        private final int _rowIndex;

        public RowVector(TwoDEval tableArray, int rowIndex) {
            this._rowIndex = rowIndex;
            int lastRowIx = tableArray.getHeight() - 1;
            if (rowIndex < 0 || rowIndex > lastRowIx) {
                throw new IllegalArgumentException("Specified row index (" + rowIndex + ") is outside the allowed range (0.." + lastRowIx + ")");
            }
            this._tableArray = tableArray;
            this._size = tableArray.getWidth();
        }

        @Override
        public ValueEval getItem(int index) {
            if (index > this._size) {
                throw new ArrayIndexOutOfBoundsException("Specified index (" + index + ") is outside the allowed range (0.." + (this._size - 1) + ")");
            }
            return this._tableArray.getValue(this._rowIndex, index);
        }

        @Override
        public int getSize() {
            return this._size;
        }
    }

    public static interface ValueVector {
        public ValueEval getItem(int var1);

        public int getSize();
    }
}

