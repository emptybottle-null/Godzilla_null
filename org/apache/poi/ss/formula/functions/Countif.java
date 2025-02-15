/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.ss.formula.functions;

import java.util.regex.Pattern;
import org.apache.poi.ss.formula.ThreeDEval;
import org.apache.poi.ss.formula.eval.BlankEval;
import org.apache.poi.ss.formula.eval.BoolEval;
import org.apache.poi.ss.formula.eval.ErrorEval;
import org.apache.poi.ss.formula.eval.EvaluationException;
import org.apache.poi.ss.formula.eval.NumberEval;
import org.apache.poi.ss.formula.eval.OperandResolver;
import org.apache.poi.ss.formula.eval.RefEval;
import org.apache.poi.ss.formula.eval.StringEval;
import org.apache.poi.ss.formula.eval.ValueEval;
import org.apache.poi.ss.formula.functions.CountUtils;
import org.apache.poi.ss.formula.functions.Fixed2ArgFunction;
import org.apache.poi.ss.usermodel.FormulaError;

public final class Countif
extends Fixed2ArgFunction {
    @Override
    public ValueEval evaluate(int srcRowIndex, int srcColumnIndex, ValueEval arg0, ValueEval arg1) {
        CountUtils.I_MatchPredicate mp = Countif.createCriteriaPredicate(arg1, srcRowIndex, srcColumnIndex);
        if (mp == null) {
            return NumberEval.ZERO;
        }
        double result = this.countMatchingCellsInArea(arg0, mp);
        return new NumberEval(result);
    }

    private double countMatchingCellsInArea(ValueEval rangeArg, CountUtils.I_MatchPredicate criteriaPredicate) {
        if (rangeArg instanceof RefEval) {
            return CountUtils.countMatchingCellsInRef((RefEval)rangeArg, criteriaPredicate);
        }
        if (rangeArg instanceof ThreeDEval) {
            return CountUtils.countMatchingCellsInArea((ThreeDEval)rangeArg, criteriaPredicate);
        }
        throw new IllegalArgumentException("Bad range arg type (" + rangeArg.getClass().getName() + ")");
    }

    static CountUtils.I_MatchPredicate createCriteriaPredicate(ValueEval arg, int srcRowIndex, int srcColumnIndex) {
        ValueEval evaluatedCriteriaArg = Countif.evaluateCriteriaArg(arg, srcRowIndex, srcColumnIndex);
        if (evaluatedCriteriaArg instanceof NumberEval) {
            return new NumberMatcher(((NumberEval)evaluatedCriteriaArg).getNumberValue(), CmpOp.OP_NONE);
        }
        if (evaluatedCriteriaArg instanceof BoolEval) {
            return new BooleanMatcher(((BoolEval)evaluatedCriteriaArg).getBooleanValue(), CmpOp.OP_NONE);
        }
        if (evaluatedCriteriaArg instanceof StringEval) {
            return Countif.createGeneralMatchPredicate((StringEval)evaluatedCriteriaArg);
        }
        if (evaluatedCriteriaArg instanceof ErrorEval) {
            return new ErrorMatcher(((ErrorEval)evaluatedCriteriaArg).getErrorCode(), CmpOp.OP_NONE);
        }
        if (evaluatedCriteriaArg == BlankEval.instance) {
            return null;
        }
        throw new RuntimeException("Unexpected type for criteria (" + evaluatedCriteriaArg.getClass().getName() + ")");
    }

    private static ValueEval evaluateCriteriaArg(ValueEval arg, int srcRowIndex, int srcColumnIndex) {
        try {
            return OperandResolver.getSingleValue(arg, srcRowIndex, srcColumnIndex);
        } catch (EvaluationException e) {
            return e.getErrorEval();
        }
    }

    private static CountUtils.I_MatchPredicate createGeneralMatchPredicate(StringEval stringEval) {
        String value = stringEval.getStringValue();
        CmpOp operator2 = CmpOp.getOperator(value);
        Boolean booleanVal = Countif.parseBoolean(value = value.substring(operator2.getLength()));
        if (booleanVal != null) {
            return new BooleanMatcher(booleanVal, operator2);
        }
        Double doubleVal = OperandResolver.parseDouble(value);
        if (doubleVal != null) {
            return new NumberMatcher(doubleVal, operator2);
        }
        ErrorEval ee = Countif.parseError(value);
        if (ee != null) {
            return new ErrorMatcher(ee.getErrorCode(), operator2);
        }
        return new StringMatcher(value, operator2);
    }

    private static ErrorEval parseError(String value) {
        if (value.length() < 4 || value.charAt(0) != '#') {
            return null;
        }
        if (value.equals("#NULL!")) {
            return ErrorEval.NULL_INTERSECTION;
        }
        if (value.equals("#DIV/0!")) {
            return ErrorEval.DIV_ZERO;
        }
        if (value.equals("#VALUE!")) {
            return ErrorEval.VALUE_INVALID;
        }
        if (value.equals("#REF!")) {
            return ErrorEval.REF_INVALID;
        }
        if (value.equals("#NAME?")) {
            return ErrorEval.NAME_INVALID;
        }
        if (value.equals("#NUM!")) {
            return ErrorEval.NUM_ERROR;
        }
        if (value.equals("#N/A")) {
            return ErrorEval.NA;
        }
        return null;
    }

    static Boolean parseBoolean(String strRep) {
        if (strRep.length() < 1) {
            return null;
        }
        switch (strRep.charAt(0)) {
            case 'T': 
            case 't': {
                if (!"TRUE".equalsIgnoreCase(strRep)) break;
                return Boolean.TRUE;
            }
            case 'F': 
            case 'f': {
                if (!"FALSE".equalsIgnoreCase(strRep)) break;
                return Boolean.FALSE;
            }
        }
        return null;
    }

    public static final class StringMatcher
    extends MatcherBase {
        private final String _value;
        private final Pattern _pattern;

        public StringMatcher(String value, CmpOp operator2) {
            super(operator2);
            this._value = value;
            switch (operator2.getCode()) {
                case 0: 
                case 1: 
                case 2: {
                    this._pattern = StringMatcher.getWildCardPattern(value);
                    break;
                }
                default: {
                    this._pattern = null;
                }
            }
        }

        @Override
        protected String getValueText() {
            if (this._pattern == null) {
                return this._value;
            }
            return this._pattern.pattern();
        }

        @Override
        public boolean matches(ValueEval x) {
            if (x instanceof BlankEval) {
                switch (this.getCode()) {
                    case 0: 
                    case 1: {
                        return this._value.length() == 0;
                    }
                    case 2: {
                        return this._value.length() != 0;
                    }
                }
                return false;
            }
            if (!(x instanceof StringEval)) {
                return false;
            }
            String testedValue = ((StringEval)x).getStringValue();
            if (testedValue.length() < 1 && this._value.length() < 1) {
                switch (this.getCode()) {
                    case 0: {
                        return true;
                    }
                    case 1: {
                        return false;
                    }
                    case 2: {
                        return true;
                    }
                }
                return false;
            }
            if (this._pattern != null) {
                return this.evaluate(this._pattern.matcher(testedValue).matches());
            }
            return this.evaluate(testedValue.compareToIgnoreCase(this._value));
        }

        public static Pattern getWildCardPattern(String value) {
            int len = value.length();
            StringBuilder sb = new StringBuilder(len);
            boolean hasWildCard = false;
            block9: for (int i = 0; i < len; ++i) {
                char ch = value.charAt(i);
                switch (ch) {
                    case '?': {
                        hasWildCard = true;
                        sb.append('.');
                        continue block9;
                    }
                    case '*': {
                        hasWildCard = true;
                        sb.append(".*");
                        continue block9;
                    }
                    case '~': {
                        if (i + 1 < len) {
                            ch = value.charAt(i + 1);
                            switch (ch) {
                                case '*': 
                                case '?': {
                                    hasWildCard = true;
                                    sb.append('[').append(ch).append(']');
                                    ++i;
                                    continue block9;
                                }
                            }
                        }
                        sb.append('~');
                        continue block9;
                    }
                    case '$': 
                    case '(': 
                    case ')': 
                    case '.': 
                    case '[': 
                    case ']': 
                    case '^': {
                        sb.append("\\").append(ch);
                        continue block9;
                    }
                    default: {
                        sb.append(ch);
                    }
                }
            }
            if (hasWildCard) {
                return Pattern.compile(sb.toString(), 2);
            }
            return null;
        }
    }

    public static final class ErrorMatcher
    extends MatcherBase {
        private final int _value;

        public ErrorMatcher(int errorCode, CmpOp operator2) {
            super(operator2);
            this._value = errorCode;
        }

        @Override
        protected String getValueText() {
            return FormulaError.forInt(this._value).getString();
        }

        @Override
        public boolean matches(ValueEval x) {
            if (x instanceof ErrorEval) {
                int testValue = ((ErrorEval)x).getErrorCode();
                return this.evaluate(testValue - this._value);
            }
            return false;
        }

        public int getValue() {
            return this._value;
        }
    }

    private static final class BooleanMatcher
    extends MatcherBase {
        private final int _value;

        public BooleanMatcher(boolean value, CmpOp operator2) {
            super(operator2);
            this._value = BooleanMatcher.boolToInt(value);
        }

        @Override
        protected String getValueText() {
            return this._value == 1 ? "TRUE" : "FALSE";
        }

        private static int boolToInt(boolean value) {
            return value ? 1 : 0;
        }

        @Override
        public boolean matches(ValueEval x) {
            if (x instanceof StringEval) {
                return false;
            }
            if (!(x instanceof BoolEval)) {
                if (x instanceof BlankEval) {
                    switch (this.getCode()) {
                        case 2: {
                            return true;
                        }
                    }
                    return false;
                }
                if (x instanceof NumberEval) {
                    switch (this.getCode()) {
                        case 2: {
                            return true;
                        }
                    }
                    return false;
                }
                return false;
            }
            BoolEval be = (BoolEval)x;
            int testValue = BooleanMatcher.boolToInt(be.getBooleanValue());
            return this.evaluate(testValue - this._value);
        }
    }

    private static final class NumberMatcher
    extends MatcherBase {
        private final double _value;

        public NumberMatcher(double value, CmpOp operator2) {
            super(operator2);
            this._value = value;
        }

        @Override
        protected String getValueText() {
            return String.valueOf(this._value);
        }

        @Override
        public boolean matches(ValueEval x) {
            if (x instanceof StringEval) {
                switch (this.getCode()) {
                    case 0: 
                    case 1: {
                        break;
                    }
                    case 2: {
                        return true;
                    }
                    default: {
                        return false;
                    }
                }
                StringEval se = (StringEval)x;
                Double val = OperandResolver.parseDouble(se.getStringValue());
                if (val == null) {
                    return false;
                }
                return this._value == val;
            }
            if (!(x instanceof NumberEval)) {
                if (x instanceof BlankEval) {
                    switch (this.getCode()) {
                        case 2: {
                            return true;
                        }
                    }
                    return false;
                }
                return false;
            }
            NumberEval ne = (NumberEval)x;
            double testValue = ne.getNumberValue();
            return this.evaluate(Double.compare(testValue, this._value));
        }
    }

    private static abstract class MatcherBase
    implements CountUtils.I_MatchPredicate {
        private final CmpOp _operator;

        MatcherBase(CmpOp operator2) {
            this._operator = operator2;
        }

        protected final int getCode() {
            return this._operator.getCode();
        }

        protected final boolean evaluate(int cmpResult) {
            return this._operator.evaluate(cmpResult);
        }

        protected final boolean evaluate(boolean cmpResult) {
            return this._operator.evaluate(cmpResult);
        }

        public final String toString() {
            return this.getClass().getName() + " [" + this._operator.getRepresentation() + this.getValueText() + "]";
        }

        protected abstract String getValueText();
    }

    private static final class CmpOp {
        public static final int NONE = 0;
        public static final int EQ = 1;
        public static final int NE = 2;
        public static final int LE = 3;
        public static final int LT = 4;
        public static final int GT = 5;
        public static final int GE = 6;
        public static final CmpOp OP_NONE = CmpOp.op("", 0);
        public static final CmpOp OP_EQ = CmpOp.op("=", 1);
        public static final CmpOp OP_NE = CmpOp.op("<>", 2);
        public static final CmpOp OP_LE = CmpOp.op("<=", 3);
        public static final CmpOp OP_LT = CmpOp.op("<", 4);
        public static final CmpOp OP_GT = CmpOp.op(">", 5);
        public static final CmpOp OP_GE = CmpOp.op(">=", 6);
        private final String _representation;
        private final int _code;

        private static CmpOp op(String rep, int code) {
            return new CmpOp(rep, code);
        }

        private CmpOp(String representation, int code) {
            this._representation = representation;
            this._code = code;
        }

        public int getLength() {
            return this._representation.length();
        }

        public int getCode() {
            return this._code;
        }

        public static CmpOp getOperator(String value) {
            int len = value.length();
            if (len < 1) {
                return OP_NONE;
            }
            char firstChar = value.charAt(0);
            switch (firstChar) {
                case '=': {
                    return OP_EQ;
                }
                case '>': {
                    if (len > 1) {
                        switch (value.charAt(1)) {
                            case '=': {
                                return OP_GE;
                            }
                        }
                    }
                    return OP_GT;
                }
                case '<': {
                    if (len > 1) {
                        switch (value.charAt(1)) {
                            case '=': {
                                return OP_LE;
                            }
                            case '>': {
                                return OP_NE;
                            }
                        }
                    }
                    return OP_LT;
                }
            }
            return OP_NONE;
        }

        public boolean evaluate(boolean cmpResult) {
            switch (this._code) {
                case 0: 
                case 1: {
                    return cmpResult;
                }
                case 2: {
                    return !cmpResult;
                }
            }
            throw new RuntimeException("Cannot call boolean evaluate on non-equality operator '" + this._representation + "'");
        }

        public boolean evaluate(int cmpResult) {
            switch (this._code) {
                case 0: 
                case 1: {
                    return cmpResult == 0;
                }
                case 2: {
                    return cmpResult != 0;
                }
                case 4: {
                    return cmpResult < 0;
                }
                case 3: {
                    return cmpResult <= 0;
                }
                case 5: {
                    return cmpResult > 0;
                }
                case 6: {
                    return cmpResult >= 0;
                }
            }
            throw new RuntimeException("Cannot call boolean evaluate on non-equality operator '" + this._representation + "'");
        }

        public String toString() {
            StringBuilder sb = new StringBuilder(64);
            sb.append(this.getClass().getName());
            sb.append(" [").append(this._representation).append("]");
            return sb.toString();
        }

        public String getRepresentation() {
            return this._representation;
        }
    }
}

