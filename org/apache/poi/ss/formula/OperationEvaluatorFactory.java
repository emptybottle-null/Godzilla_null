/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.ss.formula;

import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;
import org.apache.poi.ss.formula.EvaluationCell;
import org.apache.poi.ss.formula.EvaluationSheet;
import org.apache.poi.ss.formula.OperationEvaluationContext;
import org.apache.poi.ss.formula.UserDefinedFunction;
import org.apache.poi.ss.formula.eval.ConcatEval;
import org.apache.poi.ss.formula.eval.FunctionEval;
import org.apache.poi.ss.formula.eval.IntersectionEval;
import org.apache.poi.ss.formula.eval.PercentEval;
import org.apache.poi.ss.formula.eval.RangeEval;
import org.apache.poi.ss.formula.eval.RelationalOperationEval;
import org.apache.poi.ss.formula.eval.TwoOperandNumericOperation;
import org.apache.poi.ss.formula.eval.UnaryMinusEval;
import org.apache.poi.ss.formula.eval.UnaryPlusEval;
import org.apache.poi.ss.formula.eval.ValueEval;
import org.apache.poi.ss.formula.functions.ArrayFunction;
import org.apache.poi.ss.formula.functions.FreeRefFunction;
import org.apache.poi.ss.formula.functions.Function;
import org.apache.poi.ss.formula.functions.Indirect;
import org.apache.poi.ss.formula.ptg.AbstractFunctionPtg;
import org.apache.poi.ss.formula.ptg.AddPtg;
import org.apache.poi.ss.formula.ptg.ConcatPtg;
import org.apache.poi.ss.formula.ptg.DividePtg;
import org.apache.poi.ss.formula.ptg.EqualPtg;
import org.apache.poi.ss.formula.ptg.GreaterEqualPtg;
import org.apache.poi.ss.formula.ptg.GreaterThanPtg;
import org.apache.poi.ss.formula.ptg.IntersectionPtg;
import org.apache.poi.ss.formula.ptg.LessEqualPtg;
import org.apache.poi.ss.formula.ptg.LessThanPtg;
import org.apache.poi.ss.formula.ptg.MultiplyPtg;
import org.apache.poi.ss.formula.ptg.NotEqualPtg;
import org.apache.poi.ss.formula.ptg.OperationPtg;
import org.apache.poi.ss.formula.ptg.PercentPtg;
import org.apache.poi.ss.formula.ptg.PowerPtg;
import org.apache.poi.ss.formula.ptg.RangePtg;
import org.apache.poi.ss.formula.ptg.SubtractPtg;
import org.apache.poi.ss.formula.ptg.UnaryMinusPtg;
import org.apache.poi.ss.formula.ptg.UnaryPlusPtg;
import org.apache.poi.ss.util.CellRangeAddress;

final class OperationEvaluatorFactory {
    private static final Map<OperationPtg, Function> _instancesByPtgClass = OperationEvaluatorFactory.initialiseInstancesMap();

    private OperationEvaluatorFactory() {
    }

    private static Map<OperationPtg, Function> initialiseInstancesMap() {
        HashMap<OperationPtg, Function> m = new HashMap<OperationPtg, Function>(32);
        OperationEvaluatorFactory.put(m, EqualPtg.instance, RelationalOperationEval.EqualEval);
        OperationEvaluatorFactory.put(m, GreaterEqualPtg.instance, RelationalOperationEval.GreaterEqualEval);
        OperationEvaluatorFactory.put(m, GreaterThanPtg.instance, RelationalOperationEval.GreaterThanEval);
        OperationEvaluatorFactory.put(m, LessEqualPtg.instance, RelationalOperationEval.LessEqualEval);
        OperationEvaluatorFactory.put(m, LessThanPtg.instance, RelationalOperationEval.LessThanEval);
        OperationEvaluatorFactory.put(m, NotEqualPtg.instance, RelationalOperationEval.NotEqualEval);
        OperationEvaluatorFactory.put(m, ConcatPtg.instance, ConcatEval.instance);
        OperationEvaluatorFactory.put(m, AddPtg.instance, TwoOperandNumericOperation.AddEval);
        OperationEvaluatorFactory.put(m, DividePtg.instance, TwoOperandNumericOperation.DivideEval);
        OperationEvaluatorFactory.put(m, MultiplyPtg.instance, TwoOperandNumericOperation.MultiplyEval);
        OperationEvaluatorFactory.put(m, PercentPtg.instance, PercentEval.instance);
        OperationEvaluatorFactory.put(m, PowerPtg.instance, TwoOperandNumericOperation.PowerEval);
        OperationEvaluatorFactory.put(m, SubtractPtg.instance, TwoOperandNumericOperation.SubtractEval);
        OperationEvaluatorFactory.put(m, UnaryMinusPtg.instance, UnaryMinusEval.instance);
        OperationEvaluatorFactory.put(m, UnaryPlusPtg.instance, UnaryPlusEval.instance);
        OperationEvaluatorFactory.put(m, RangePtg.instance, RangeEval.instance);
        OperationEvaluatorFactory.put(m, IntersectionPtg.instance, IntersectionEval.instance);
        return m;
    }

    private static void put(Map<OperationPtg, Function> m, OperationPtg ptgKey, Function instance) {
        Constructor<?>[] cc = ptgKey.getClass().getDeclaredConstructors();
        if (cc.length > 1 || !Modifier.isPrivate(cc[0].getModifiers())) {
            throw new RuntimeException("Failed to verify instance (" + ptgKey.getClass().getName() + ") is a singleton.");
        }
        m.put(ptgKey, instance);
    }

    public static ValueEval evaluate(OperationPtg ptg, ValueEval[] args, OperationEvaluationContext ec) {
        if (ptg == null) {
            throw new IllegalArgumentException("ptg must not be null");
        }
        Function result = _instancesByPtgClass.get(ptg);
        FreeRefFunction udfFunc = null;
        if (result == null && ptg instanceof AbstractFunctionPtg) {
            AbstractFunctionPtg fptg = (AbstractFunctionPtg)ptg;
            short functionIndex = fptg.getFunctionIndex();
            switch (functionIndex) {
                case 148: {
                    udfFunc = Indirect.instance;
                    break;
                }
                case 255: {
                    udfFunc = UserDefinedFunction.instance;
                    break;
                }
                default: {
                    result = FunctionEval.getBasicFunction(functionIndex);
                }
            }
        }
        if (result != null) {
            EvaluationSheet evalSheet = ec.getWorkbook().getSheet(ec.getSheetIndex());
            EvaluationCell evalCell = evalSheet.getCell(ec.getRowIndex(), ec.getColumnIndex());
            if (evalCell != null && result instanceof ArrayFunction) {
                ArrayFunction func = (ArrayFunction)((Object)result);
                if (evalCell.isPartOfArrayFormulaGroup()) {
                    CellRangeAddress ca = evalCell.getArrayFormulaRange();
                    return func.evaluateArray(args, ca.getFirstRow(), ca.getFirstColumn());
                }
                if (ec.isArraymode()) {
                    return func.evaluateArray(args, ec.getRowIndex(), ec.getColumnIndex());
                }
            }
            return result.evaluate(args, ec.getRowIndex(), ec.getColumnIndex());
        }
        if (udfFunc != null) {
            return udfFunc.evaluate(args, ec);
        }
        throw new RuntimeException("Unexpected operation ptg class (" + ptg.getClass().getName() + ")");
    }
}

