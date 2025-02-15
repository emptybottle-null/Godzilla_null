/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.ss.formula.functions;

import java.util.Locale;
import org.apache.poi.ss.formula.eval.BoolEval;
import org.apache.poi.ss.formula.eval.ErrorEval;
import org.apache.poi.ss.formula.eval.EvaluationException;
import org.apache.poi.ss.formula.eval.NumberEval;
import org.apache.poi.ss.formula.eval.OperandResolver;
import org.apache.poi.ss.formula.eval.StringEval;
import org.apache.poi.ss.formula.eval.ValueEval;
import org.apache.poi.ss.formula.functions.Fixed1ArgFunction;
import org.apache.poi.ss.formula.functions.Fixed2ArgFunction;
import org.apache.poi.ss.formula.functions.Fixed3ArgFunction;
import org.apache.poi.ss.formula.functions.Function;
import org.apache.poi.ss.formula.functions.Var1or2ArgFunction;
import org.apache.poi.ss.formula.functions.Var2or3ArgFunction;
import org.apache.poi.ss.usermodel.DataFormatter;

public abstract class TextFunction
implements Function {
    protected static final DataFormatter formatter = new DataFormatter();
    public static final Function CHAR = new Fixed1ArgFunction(){

        @Override
        public ValueEval evaluate(int srcRowIndex, int srcColumnIndex, ValueEval arg0) {
            int arg;
            try {
                arg = TextFunction.evaluateIntArg(arg0, srcRowIndex, srcColumnIndex);
                if (arg < 0 || arg >= 256) {
                    throw new EvaluationException(ErrorEval.VALUE_INVALID);
                }
            } catch (EvaluationException e) {
                return e.getErrorEval();
            }
            return new StringEval(String.valueOf((char)arg));
        }
    };
    public static final Function LEN = new SingleArgTextFunc(){

        @Override
        protected ValueEval evaluate(String arg) {
            return new NumberEval(arg.length());
        }
    };
    public static final Function LOWER = new SingleArgTextFunc(){

        @Override
        protected ValueEval evaluate(String arg) {
            return new StringEval(arg.toLowerCase(Locale.ROOT));
        }
    };
    public static final Function UPPER = new SingleArgTextFunc(){

        @Override
        protected ValueEval evaluate(String arg) {
            return new StringEval(arg.toUpperCase(Locale.ROOT));
        }
    };
    public static final Function PROPER = new SingleArgTextFunc(){

        @Override
        protected ValueEval evaluate(String text) {
            StringBuilder sb = new StringBuilder();
            boolean shouldMakeUppercase = true;
            for (char ch : text.toCharArray()) {
                if (shouldMakeUppercase) {
                    sb.append(String.valueOf(ch).toUpperCase(Locale.ROOT));
                } else {
                    sb.append(String.valueOf(ch).toLowerCase(Locale.ROOT));
                }
                shouldMakeUppercase = !Character.isLetter(ch);
            }
            return new StringEval(sb.toString());
        }
    };
    public static final Function TRIM = new SingleArgTextFunc(){

        @Override
        protected ValueEval evaluate(String arg) {
            return new StringEval(arg.trim());
        }
    };
    public static final Function CLEAN = new SingleArgTextFunc(){

        @Override
        protected ValueEval evaluate(String arg) {
            StringBuilder result = new StringBuilder();
            for (char c : arg.toCharArray()) {
                if (!this.isPrintable(c)) continue;
                result.append(c);
            }
            return new StringEval(result.toString());
        }

        private boolean isPrintable(char c) {
            return c >= ' ';
        }
    };
    public static final Function MID = new Fixed3ArgFunction(){

        @Override
        public ValueEval evaluate(int srcRowIndex, int srcColumnIndex, ValueEval arg0, ValueEval arg1, ValueEval arg2) {
            int numChars;
            int startCharNum;
            String text;
            try {
                text = TextFunction.evaluateStringArg(arg0, srcRowIndex, srcColumnIndex);
                startCharNum = TextFunction.evaluateIntArg(arg1, srcRowIndex, srcColumnIndex);
                numChars = TextFunction.evaluateIntArg(arg2, srcRowIndex, srcColumnIndex);
            } catch (EvaluationException e) {
                return e.getErrorEval();
            }
            int startIx = startCharNum - 1;
            if (startIx < 0) {
                return ErrorEval.VALUE_INVALID;
            }
            if (numChars < 0) {
                return ErrorEval.VALUE_INVALID;
            }
            int len = text.length();
            if (startIx > len) {
                return new StringEval("");
            }
            int endIx = Math.min(startIx + numChars, len);
            String result = text.substring(startIx, endIx);
            return new StringEval(result);
        }
    };
    public static final Function LEFT = new LeftRight(true);
    public static final Function RIGHT = new LeftRight(false);
    public static final Function CONCATENATE = new Function(){

        @Override
        public ValueEval evaluate(ValueEval[] args, int srcRowIndex, int srcColumnIndex) {
            StringBuilder sb = new StringBuilder();
            for (ValueEval arg : args) {
                try {
                    sb.append(TextFunction.evaluateStringArg(arg, srcRowIndex, srcColumnIndex));
                } catch (EvaluationException e) {
                    return e.getErrorEval();
                }
            }
            return new StringEval(sb.toString());
        }
    };
    public static final Function EXACT = new Fixed2ArgFunction(){

        @Override
        public ValueEval evaluate(int srcRowIndex, int srcColumnIndex, ValueEval arg0, ValueEval arg1) {
            String s1;
            String s0;
            try {
                s0 = TextFunction.evaluateStringArg(arg0, srcRowIndex, srcColumnIndex);
                s1 = TextFunction.evaluateStringArg(arg1, srcRowIndex, srcColumnIndex);
            } catch (EvaluationException e) {
                return e.getErrorEval();
            }
            return BoolEval.valueOf(s0.equals(s1));
        }
    };
    public static final Function TEXT = new Fixed2ArgFunction(){

        @Override
        public ValueEval evaluate(int srcRowIndex, int srcColumnIndex, ValueEval arg0, ValueEval arg1) {
            String s1;
            double s0;
            try {
                s0 = TextFunction.evaluateDoubleArg(arg0, srcRowIndex, srcColumnIndex);
                s1 = TextFunction.evaluateStringArg(arg1, srcRowIndex, srcColumnIndex);
            } catch (EvaluationException e) {
                return e.getErrorEval();
            }
            try {
                String formattedStr = formatter.formatRawCellContents(s0, -1, s1);
                return new StringEval(formattedStr);
            } catch (Exception e) {
                return ErrorEval.VALUE_INVALID;
            }
        }
    };
    public static final Function FIND = new SearchFind(true);
    public static final Function SEARCH = new SearchFind(false);

    protected static String evaluateStringArg(ValueEval eval, int srcRow, int srcCol) throws EvaluationException {
        ValueEval ve = OperandResolver.getSingleValue(eval, srcRow, srcCol);
        return OperandResolver.coerceValueToString(ve);
    }

    protected static int evaluateIntArg(ValueEval arg, int srcCellRow, int srcCellCol) throws EvaluationException {
        ValueEval ve = OperandResolver.getSingleValue(arg, srcCellRow, srcCellCol);
        return OperandResolver.coerceValueToInt(ve);
    }

    protected static double evaluateDoubleArg(ValueEval arg, int srcCellRow, int srcCellCol) throws EvaluationException {
        ValueEval ve = OperandResolver.getSingleValue(arg, srcCellRow, srcCellCol);
        return OperandResolver.coerceValueToDouble(ve);
    }

    @Override
    public final ValueEval evaluate(ValueEval[] args, int srcCellRow, int srcCellCol) {
        try {
            return this.evaluateFunc(args, srcCellRow, srcCellCol);
        } catch (EvaluationException e) {
            return e.getErrorEval();
        }
    }

    protected abstract ValueEval evaluateFunc(ValueEval[] var1, int var2, int var3) throws EvaluationException;

    private static final class SearchFind
    extends Var2or3ArgFunction {
        private final boolean _isCaseSensitive;

        public SearchFind(boolean isCaseSensitive) {
            this._isCaseSensitive = isCaseSensitive;
        }

        @Override
        public ValueEval evaluate(int srcRowIndex, int srcColumnIndex, ValueEval arg0, ValueEval arg1) {
            try {
                String needle = TextFunction.evaluateStringArg(arg0, srcRowIndex, srcColumnIndex);
                String haystack = TextFunction.evaluateStringArg(arg1, srcRowIndex, srcColumnIndex);
                return this.eval(haystack, needle, 0);
            } catch (EvaluationException e) {
                return e.getErrorEval();
            }
        }

        @Override
        public ValueEval evaluate(int srcRowIndex, int srcColumnIndex, ValueEval arg0, ValueEval arg1, ValueEval arg2) {
            try {
                String needle = TextFunction.evaluateStringArg(arg0, srcRowIndex, srcColumnIndex);
                String haystack = TextFunction.evaluateStringArg(arg1, srcRowIndex, srcColumnIndex);
                int startpos = TextFunction.evaluateIntArg(arg2, srcRowIndex, srcColumnIndex) - 1;
                if (startpos < 0) {
                    return ErrorEval.VALUE_INVALID;
                }
                return this.eval(haystack, needle, startpos);
            } catch (EvaluationException e) {
                return e.getErrorEval();
            }
        }

        private ValueEval eval(String haystack, String needle, int startIndex) {
            int result = this._isCaseSensitive ? haystack.indexOf(needle, startIndex) : haystack.toUpperCase(Locale.ROOT).indexOf(needle.toUpperCase(Locale.ROOT), startIndex);
            if (result == -1) {
                return ErrorEval.VALUE_INVALID;
            }
            return new NumberEval(result + 1);
        }
    }

    private static final class LeftRight
    extends Var1or2ArgFunction {
        private static final ValueEval DEFAULT_ARG1 = new NumberEval(1.0);
        private final boolean _isLeft;

        protected LeftRight(boolean isLeft) {
            this._isLeft = isLeft;
        }

        @Override
        public ValueEval evaluate(int srcRowIndex, int srcColumnIndex, ValueEval arg0) {
            return this.evaluate(srcRowIndex, srcColumnIndex, arg0, DEFAULT_ARG1);
        }

        @Override
        public ValueEval evaluate(int srcRowIndex, int srcColumnIndex, ValueEval arg0, ValueEval arg1) {
            int index;
            String arg;
            try {
                arg = TextFunction.evaluateStringArg(arg0, srcRowIndex, srcColumnIndex);
                index = TextFunction.evaluateIntArg(arg1, srcRowIndex, srcColumnIndex);
            } catch (EvaluationException e) {
                return e.getErrorEval();
            }
            if (index < 0) {
                return ErrorEval.VALUE_INVALID;
            }
            String result = this._isLeft ? arg.substring(0, Math.min(arg.length(), index)) : arg.substring(Math.max(0, arg.length() - index));
            return new StringEval(result);
        }
    }

    private static abstract class SingleArgTextFunc
    extends Fixed1ArgFunction {
        protected SingleArgTextFunc() {
        }

        @Override
        public ValueEval evaluate(int srcRowIndex, int srcColumnIndex, ValueEval arg0) {
            String arg;
            try {
                arg = TextFunction.evaluateStringArg(arg0, srcRowIndex, srcColumnIndex);
            } catch (EvaluationException e) {
                return e.getErrorEval();
            }
            return this.evaluate(arg);
        }

        protected abstract ValueEval evaluate(String var1);
    }
}

