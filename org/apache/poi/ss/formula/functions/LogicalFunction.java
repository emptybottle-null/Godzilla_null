/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.ss.formula.functions;

import org.apache.poi.ss.formula.eval.AreaEval;
import org.apache.poi.ss.formula.eval.BlankEval;
import org.apache.poi.ss.formula.eval.BoolEval;
import org.apache.poi.ss.formula.eval.ErrorEval;
import org.apache.poi.ss.formula.eval.EvaluationException;
import org.apache.poi.ss.formula.eval.NumberEval;
import org.apache.poi.ss.formula.eval.OperandResolver;
import org.apache.poi.ss.formula.eval.RefEval;
import org.apache.poi.ss.formula.eval.RefListEval;
import org.apache.poi.ss.formula.eval.StringEval;
import org.apache.poi.ss.formula.eval.ValueEval;
import org.apache.poi.ss.formula.functions.ArrayFunction;
import org.apache.poi.ss.formula.functions.Fixed1ArgFunction;
import org.apache.poi.ss.formula.functions.Function;

public abstract class LogicalFunction
extends Fixed1ArgFunction
implements ArrayFunction {
    public static final Function ISLOGICAL = new LogicalFunction(){

        @Override
        protected boolean evaluate(ValueEval arg) {
            return arg instanceof BoolEval;
        }
    };
    public static final Function ISNONTEXT = new LogicalFunction(){

        @Override
        protected boolean evaluate(ValueEval arg) {
            return !(arg instanceof StringEval);
        }
    };
    public static final Function ISNUMBER = new LogicalFunction(){

        @Override
        protected boolean evaluate(ValueEval arg) {
            return arg instanceof NumberEval;
        }
    };
    public static final Function ISTEXT = new LogicalFunction(){

        @Override
        protected boolean evaluate(ValueEval arg) {
            return arg instanceof StringEval;
        }
    };
    public static final Function ISBLANK = new LogicalFunction(){

        @Override
        protected boolean evaluate(ValueEval arg) {
            return arg instanceof BlankEval;
        }
    };
    public static final Function ISERROR = new LogicalFunction(){

        @Override
        protected boolean evaluate(ValueEval arg) {
            return arg instanceof ErrorEval;
        }
    };
    public static final Function ISERR = new LogicalFunction(){

        @Override
        protected boolean evaluate(ValueEval arg) {
            if (arg instanceof ErrorEval) {
                return arg != ErrorEval.NA;
            }
            return false;
        }
    };
    public static final Function ISNA = new LogicalFunction(){

        @Override
        protected boolean evaluate(ValueEval arg) {
            return arg == ErrorEval.NA;
        }
    };
    public static final Function ISREF = new Fixed1ArgFunction(){

        @Override
        public ValueEval evaluate(int srcRowIndex, int srcColumnIndex, ValueEval arg0) {
            if (arg0 instanceof RefEval || arg0 instanceof AreaEval || arg0 instanceof RefListEval) {
                return BoolEval.TRUE;
            }
            return BoolEval.FALSE;
        }
    };

    @Override
    public ValueEval evaluate(int srcRowIndex, int srcColumnIndex, ValueEval arg0) {
        ValueEval ve;
        try {
            ve = OperandResolver.getSingleValue(arg0, srcRowIndex, srcColumnIndex);
        } catch (EvaluationException e) {
            ve = e.getErrorEval();
        }
        return BoolEval.valueOf(this.evaluate(ve));
    }

    @Override
    public ValueEval evaluateArray(ValueEval[] args, int srcRowIndex, int srcColumnIndex) {
        if (args.length != 1) {
            return ErrorEval.VALUE_INVALID;
        }
        return this.evaluateOneArrayArg(args[0], srcRowIndex, srcColumnIndex, valA -> BoolEval.valueOf(this.evaluate((ValueEval)valA)));
    }

    protected abstract boolean evaluate(ValueEval var1);
}

