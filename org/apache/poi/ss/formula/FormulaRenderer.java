/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.ss.formula;

import java.util.Stack;
import org.apache.poi.ss.formula.FormulaRenderingWorkbook;
import org.apache.poi.ss.formula.WorkbookDependentFormula;
import org.apache.poi.ss.formula.ptg.AttrPtg;
import org.apache.poi.ss.formula.ptg.MemAreaPtg;
import org.apache.poi.ss.formula.ptg.MemErrPtg;
import org.apache.poi.ss.formula.ptg.MemFuncPtg;
import org.apache.poi.ss.formula.ptg.OperationPtg;
import org.apache.poi.ss.formula.ptg.ParenthesisPtg;
import org.apache.poi.ss.formula.ptg.Ptg;

public class FormulaRenderer {
    public static String toFormulaString(FormulaRenderingWorkbook book, Ptg[] ptgs) {
        if (ptgs == null || ptgs.length == 0) {
            throw new IllegalArgumentException("ptgs must not be null");
        }
        Stack<String> stack = new Stack<String>();
        for (Ptg ptg : ptgs) {
            String[] operands;
            if (ptg instanceof MemAreaPtg || ptg instanceof MemFuncPtg || ptg instanceof MemErrPtg) continue;
            if (ptg instanceof ParenthesisPtg) {
                String contents = (String)stack.pop();
                stack.push("(" + contents + ")");
                continue;
            }
            if (ptg instanceof AttrPtg) {
                AttrPtg attrPtg = (AttrPtg)ptg;
                if (attrPtg.isOptimizedIf() || attrPtg.isOptimizedChoose() || attrPtg.isSkip() || attrPtg.isSpace() || attrPtg.isSemiVolatile()) continue;
                if (attrPtg.isSum()) {
                    operands = FormulaRenderer.getOperands(stack, attrPtg.getNumberOfOperands());
                    stack.push(attrPtg.toFormulaString(operands));
                    continue;
                }
                throw new RuntimeException("Unexpected tAttr: " + attrPtg);
            }
            if (ptg instanceof WorkbookDependentFormula) {
                WorkbookDependentFormula optg = (WorkbookDependentFormula)((Object)ptg);
                stack.push(optg.toFormulaString(book));
                continue;
            }
            if (!(ptg instanceof OperationPtg)) {
                stack.push(ptg.toFormulaString());
                continue;
            }
            OperationPtg o = (OperationPtg)ptg;
            operands = FormulaRenderer.getOperands(stack, o.getNumberOfOperands());
            stack.push(o.toFormulaString(operands));
        }
        if (stack.isEmpty()) {
            throw new IllegalStateException("Stack underflow");
        }
        String result = (String)stack.pop();
        if (!stack.isEmpty()) {
            throw new IllegalStateException("too much stuff left on the stack");
        }
        return result;
    }

    private static String[] getOperands(Stack<String> stack, int nOperands) {
        String[] operands = new String[nOperands];
        for (int j = nOperands - 1; j >= 0; --j) {
            if (stack.isEmpty()) {
                String msg = "Too few arguments supplied to operation. Expected (" + nOperands + ") operands but got (" + (nOperands - j - 1) + ")";
                throw new IllegalStateException(msg);
            }
            operands[j] = stack.pop();
        }
        return operands;
    }
}

