/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.ss.formula;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.IdentityHashMap;
import java.util.Map;
import java.util.Stack;
import java.util.TreeSet;
import org.apache.poi.ss.SpreadsheetVersion;
import org.apache.poi.ss.formula.CollaboratingWorkbooksEnvironment;
import org.apache.poi.ss.formula.EvaluationCache;
import org.apache.poi.ss.formula.EvaluationCell;
import org.apache.poi.ss.formula.EvaluationName;
import org.apache.poi.ss.formula.EvaluationSheet;
import org.apache.poi.ss.formula.EvaluationTracker;
import org.apache.poi.ss.formula.EvaluationWorkbook;
import org.apache.poi.ss.formula.FormulaCellCacheEntry;
import org.apache.poi.ss.formula.FormulaParser;
import org.apache.poi.ss.formula.FormulaParsingWorkbook;
import org.apache.poi.ss.formula.FormulaType;
import org.apache.poi.ss.formula.IEvaluationListener;
import org.apache.poi.ss.formula.IStabilityClassifier;
import org.apache.poi.ss.formula.OperationEvaluationContext;
import org.apache.poi.ss.formula.OperationEvaluatorFactory;
import org.apache.poi.ss.formula.atp.AnalysisToolPak;
import org.apache.poi.ss.formula.eval.AreaEval;
import org.apache.poi.ss.formula.eval.BlankEval;
import org.apache.poi.ss.formula.eval.BoolEval;
import org.apache.poi.ss.formula.eval.ErrorEval;
import org.apache.poi.ss.formula.eval.EvaluationException;
import org.apache.poi.ss.formula.eval.ExternalNameEval;
import org.apache.poi.ss.formula.eval.FunctionEval;
import org.apache.poi.ss.formula.eval.FunctionNameEval;
import org.apache.poi.ss.formula.eval.MissingArgEval;
import org.apache.poi.ss.formula.eval.NotImplementedException;
import org.apache.poi.ss.formula.eval.NumberEval;
import org.apache.poi.ss.formula.eval.OperandResolver;
import org.apache.poi.ss.formula.eval.RefListEval;
import org.apache.poi.ss.formula.eval.StringEval;
import org.apache.poi.ss.formula.eval.ValueEval;
import org.apache.poi.ss.formula.functions.ArrayMode;
import org.apache.poi.ss.formula.functions.Choose;
import org.apache.poi.ss.formula.functions.FreeRefFunction;
import org.apache.poi.ss.formula.functions.Function;
import org.apache.poi.ss.formula.functions.IfFunc;
import org.apache.poi.ss.formula.ptg.Area3DPtg;
import org.apache.poi.ss.formula.ptg.Area3DPxg;
import org.apache.poi.ss.formula.ptg.AreaErrPtg;
import org.apache.poi.ss.formula.ptg.AreaPtg;
import org.apache.poi.ss.formula.ptg.ArrayPtg;
import org.apache.poi.ss.formula.ptg.AttrPtg;
import org.apache.poi.ss.formula.ptg.BoolPtg;
import org.apache.poi.ss.formula.ptg.ControlPtg;
import org.apache.poi.ss.formula.ptg.DeletedArea3DPtg;
import org.apache.poi.ss.formula.ptg.DeletedRef3DPtg;
import org.apache.poi.ss.formula.ptg.ErrPtg;
import org.apache.poi.ss.formula.ptg.ExpPtg;
import org.apache.poi.ss.formula.ptg.FuncVarPtg;
import org.apache.poi.ss.formula.ptg.IntPtg;
import org.apache.poi.ss.formula.ptg.MemAreaPtg;
import org.apache.poi.ss.formula.ptg.MemErrPtg;
import org.apache.poi.ss.formula.ptg.MemFuncPtg;
import org.apache.poi.ss.formula.ptg.MissingArgPtg;
import org.apache.poi.ss.formula.ptg.NamePtg;
import org.apache.poi.ss.formula.ptg.NameXPtg;
import org.apache.poi.ss.formula.ptg.NameXPxg;
import org.apache.poi.ss.formula.ptg.NumberPtg;
import org.apache.poi.ss.formula.ptg.OperationPtg;
import org.apache.poi.ss.formula.ptg.Ptg;
import org.apache.poi.ss.formula.ptg.Ref3DPtg;
import org.apache.poi.ss.formula.ptg.Ref3DPxg;
import org.apache.poi.ss.formula.ptg.RefErrorPtg;
import org.apache.poi.ss.formula.ptg.RefPtg;
import org.apache.poi.ss.formula.ptg.RefPtgBase;
import org.apache.poi.ss.formula.ptg.StringPtg;
import org.apache.poi.ss.formula.ptg.UnionPtg;
import org.apache.poi.ss.formula.ptg.UnknownPtg;
import org.apache.poi.ss.formula.udf.AggregatingUDFFinder;
import org.apache.poi.ss.formula.udf.UDFFinder;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.util.CellRangeAddressBase;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.util.Internal;
import org.apache.poi.util.POILogFactory;
import org.apache.poi.util.POILogger;

@Internal
public final class WorkbookEvaluator {
    private static final POILogger LOG = POILogFactory.getLogger(WorkbookEvaluator.class);
    private final EvaluationWorkbook _workbook;
    private EvaluationCache _cache;
    private int _workbookIx;
    private final IEvaluationListener _evaluationListener;
    private final Map<EvaluationSheet, Integer> _sheetIndexesBySheet;
    private final Map<String, Integer> _sheetIndexesByName;
    private CollaboratingWorkbooksEnvironment _collaboratingWorkbookEnvironment;
    private final IStabilityClassifier _stabilityClassifier;
    private final AggregatingUDFFinder _udfFinder;
    private boolean _ignoreMissingWorkbooks;
    private boolean dbgEvaluationOutputForNextEval;
    private final POILogger EVAL_LOG = POILogFactory.getLogger("POI.FormulaEval");
    private int dbgEvaluationOutputIndent = -1;

    public WorkbookEvaluator(EvaluationWorkbook workbook, IStabilityClassifier stabilityClassifier, UDFFinder udfFinder) {
        this(workbook, null, stabilityClassifier, udfFinder);
    }

    WorkbookEvaluator(EvaluationWorkbook workbook, IEvaluationListener evaluationListener, IStabilityClassifier stabilityClassifier, UDFFinder udfFinder) {
        AggregatingUDFFinder defaultToolkit;
        this._workbook = workbook;
        this._evaluationListener = evaluationListener;
        this._cache = new EvaluationCache(evaluationListener);
        this._sheetIndexesBySheet = new IdentityHashMap<EvaluationSheet, Integer>();
        this._sheetIndexesByName = new IdentityHashMap<String, Integer>();
        this._collaboratingWorkbookEnvironment = CollaboratingWorkbooksEnvironment.EMPTY;
        this._workbookIx = 0;
        this._stabilityClassifier = stabilityClassifier;
        AggregatingUDFFinder aggregatingUDFFinder = defaultToolkit = workbook == null ? null : (AggregatingUDFFinder)workbook.getUDFFinder();
        if (defaultToolkit != null && udfFinder != null) {
            defaultToolkit.add(udfFinder);
        }
        this._udfFinder = defaultToolkit;
    }

    String getSheetName(int sheetIndex) {
        return this._workbook.getSheetName(sheetIndex);
    }

    EvaluationSheet getSheet(int sheetIndex) {
        return this._workbook.getSheet(sheetIndex);
    }

    EvaluationWorkbook getWorkbook() {
        return this._workbook;
    }

    EvaluationName getName(String name, int sheetIndex) {
        return this._workbook.getName(name, sheetIndex);
    }

    private static boolean isDebugLogEnabled() {
        return LOG.check(1);
    }

    private static boolean isInfoLogEnabled() {
        return LOG.check(3);
    }

    private static void logDebug(String s) {
        if (WorkbookEvaluator.isDebugLogEnabled()) {
            LOG.log(1, s);
        }
    }

    private static void logInfo(String s) {
        if (WorkbookEvaluator.isInfoLogEnabled()) {
            LOG.log(3, s);
        }
    }

    void attachToEnvironment(CollaboratingWorkbooksEnvironment collaboratingWorkbooksEnvironment, EvaluationCache cache, int workbookIx) {
        this._collaboratingWorkbookEnvironment = collaboratingWorkbooksEnvironment;
        this._cache = cache;
        this._workbookIx = workbookIx;
    }

    CollaboratingWorkbooksEnvironment getEnvironment() {
        return this._collaboratingWorkbookEnvironment;
    }

    void detachFromEnvironment() {
        this._collaboratingWorkbookEnvironment = CollaboratingWorkbooksEnvironment.EMPTY;
        this._cache = new EvaluationCache(this._evaluationListener);
        this._workbookIx = 0;
    }

    WorkbookEvaluator getOtherWorkbookEvaluator(String workbookName) throws CollaboratingWorkbooksEnvironment.WorkbookNotFoundException {
        return this._collaboratingWorkbookEnvironment.getWorkbookEvaluator(workbookName);
    }

    IEvaluationListener getEvaluationListener() {
        return this._evaluationListener;
    }

    public void clearAllCachedResultValues() {
        this._cache.clear();
        this._sheetIndexesBySheet.clear();
        this._workbook.clearAllCachedResultValues();
    }

    public void notifyUpdateCell(EvaluationCell cell) {
        int sheetIndex = this.getSheetIndex(cell.getSheet());
        this._cache.notifyUpdateCell(this._workbookIx, sheetIndex, cell);
    }

    public void notifyDeleteCell(EvaluationCell cell) {
        int sheetIndex = this.getSheetIndex(cell.getSheet());
        this._cache.notifyDeleteCell(this._workbookIx, sheetIndex, cell);
    }

    private int getSheetIndex(EvaluationSheet sheet) {
        Integer result = this._sheetIndexesBySheet.get(sheet);
        if (result == null) {
            int sheetIndex = this._workbook.getSheetIndex(sheet);
            if (sheetIndex < 0) {
                throw new RuntimeException("Specified sheet from a different book");
            }
            result = sheetIndex;
            this._sheetIndexesBySheet.put(sheet, result);
        }
        return result;
    }

    public ValueEval evaluate(EvaluationCell srcCell) {
        int sheetIndex = this.getSheetIndex(srcCell.getSheet());
        return this.evaluateAny(srcCell, sheetIndex, srcCell.getRowIndex(), srcCell.getColumnIndex(), new EvaluationTracker(this._cache));
    }

    int getSheetIndex(String sheetName) {
        Integer result = this._sheetIndexesByName.get(sheetName);
        if (result == null) {
            int sheetIndex = this._workbook.getSheetIndex(sheetName);
            if (sheetIndex < 0) {
                return -1;
            }
            result = sheetIndex;
            this._sheetIndexesByName.put(sheetName, result);
        }
        return result;
    }

    int getSheetIndexByExternIndex(int externSheetIndex) {
        return this._workbook.convertFromExternSheetIndex(externSheetIndex);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private ValueEval evaluateAny(EvaluationCell srcCell, int sheetIndex, int rowIndex, int columnIndex, EvaluationTracker tracker) {
        ValueEval result;
        boolean shouldCellDependencyBeRecorded;
        boolean bl = this._stabilityClassifier == null ? true : (shouldCellDependencyBeRecorded = !this._stabilityClassifier.isCellFinal(sheetIndex, rowIndex, columnIndex));
        if (srcCell == null || srcCell.getCellType() != CellType.FORMULA) {
            ValueEval result2 = WorkbookEvaluator.getValueFromNonFormulaCell(srcCell);
            if (shouldCellDependencyBeRecorded) {
                tracker.acceptPlainValueDependency(this._workbook, this._workbookIx, sheetIndex, rowIndex, columnIndex, result2);
            }
            return result2;
        }
        FormulaCellCacheEntry cce = this._cache.getOrCreateFormulaCellEntry(srcCell);
        if (shouldCellDependencyBeRecorded || cce.isInputSensitive()) {
            tracker.acceptFormulaDependency(cce);
        }
        IEvaluationListener evalListener = this._evaluationListener;
        if (cce.getValue() == null) {
            if (!tracker.startEvaluate(cce)) {
                return ErrorEval.CIRCULAR_REF_ERROR;
            }
            try {
                Ptg[] ptgs = this._workbook.getFormulaTokens(srcCell);
                OperationEvaluationContext ec = new OperationEvaluationContext(this, this._workbook, sheetIndex, rowIndex, columnIndex, tracker);
                if (evalListener == null) {
                    result = this.evaluateFormula(ec, ptgs);
                } else {
                    evalListener.onStartEvaluate(srcCell, cce);
                    result = this.evaluateFormula(ec, ptgs);
                    evalListener.onEndEvaluate(cce, result);
                }
                tracker.updateCacheResult(result);
            } catch (NotImplementedException e) {
                throw this.addExceptionInfo(e, sheetIndex, rowIndex, columnIndex);
            } catch (RuntimeException re) {
                if (re.getCause() instanceof CollaboratingWorkbooksEnvironment.WorkbookNotFoundException && this._ignoreMissingWorkbooks) {
                    WorkbookEvaluator.logInfo(re.getCause().getMessage() + " - Continuing with cached value!");
                    switch (srcCell.getCachedFormulaResultType()) {
                        case NUMERIC: {
                            result = new NumberEval(srcCell.getNumericCellValue());
                        }
                        case STRING: {
                            result = new StringEval(srcCell.getStringCellValue());
                        }
                        case BLANK: {
                            result = BlankEval.instance;
                        }
                        case BOOLEAN: {
                            result = BoolEval.valueOf(srcCell.getBooleanCellValue());
                        }
                        case ERROR: {
                            result = ErrorEval.valueOf(srcCell.getErrorCellValue());
                        }
                        default: {
                            throw new RuntimeException("Unexpected cell type '" + (Object)((Object)srcCell.getCellType()) + "' found!");
                        }
                    }
                }
                throw re;
            } finally {
                tracker.endEvaluate(cce);
            }
        } else {
            if (evalListener != null) {
                evalListener.onCacheHit(sheetIndex, rowIndex, columnIndex, cce.getValue());
            }
            return cce.getValue();
        }
        if (WorkbookEvaluator.isDebugLogEnabled()) {
            String sheetName = this.getSheetName(sheetIndex);
            CellReference cr = new CellReference(rowIndex, columnIndex);
            WorkbookEvaluator.logDebug("Evaluated " + sheetName + "!" + cr.formatAsString() + " to " + result);
        }
        return result;
    }

    private NotImplementedException addExceptionInfo(NotImplementedException inner, int sheetIndex, int rowIndex, int columnIndex) {
        try {
            String sheetName = this._workbook.getSheetName(sheetIndex);
            CellReference cr = new CellReference(sheetName, rowIndex, columnIndex, false, false);
            String msg = "Error evaluating cell " + cr.formatAsString();
            return new NotImplementedException(msg, inner);
        } catch (Exception e) {
            LOG.log(7, "Can't add exception info", e);
            return inner;
        }
    }

    static ValueEval getValueFromNonFormulaCell(EvaluationCell cell) {
        if (cell == null) {
            return BlankEval.instance;
        }
        CellType cellType = cell.getCellType();
        switch (cellType) {
            case NUMERIC: {
                return new NumberEval(cell.getNumericCellValue());
            }
            case STRING: {
                return new StringEval(cell.getStringCellValue());
            }
            case BOOLEAN: {
                return BoolEval.valueOf(cell.getBooleanCellValue());
            }
            case BLANK: {
                return BlankEval.instance;
            }
            case ERROR: {
                return ErrorEval.valueOf(cell.getErrorCellValue());
            }
        }
        throw new RuntimeException("Unexpected cell type (" + (Object)((Object)cellType) + ")");
    }

    @Internal
    ValueEval evaluateFormula(OperationEvaluationContext ec, Ptg[] ptgs) {
        String dbgIndentStr = "";
        if (this.dbgEvaluationOutputForNextEval) {
            this.dbgEvaluationOutputIndent = 1;
            this.dbgEvaluationOutputForNextEval = false;
        }
        if (this.dbgEvaluationOutputIndent > 0) {
            dbgIndentStr = "                                                                                                    ";
            dbgIndentStr = dbgIndentStr.substring(0, Math.min(dbgIndentStr.length(), this.dbgEvaluationOutputIndent * 2));
            this.EVAL_LOG.log(5, dbgIndentStr + "- evaluateFormula('" + ec.getRefEvaluatorForCurrentSheet().getSheetNameRange() + "'/" + new CellReference(ec.getRowIndex(), ec.getColumnIndex()).formatAsString() + "): " + Arrays.toString(ptgs).replaceAll("\\Qorg.apache.poi.ss.formula.ptg.\\E", ""));
            ++this.dbgEvaluationOutputIndent;
        }
        EvaluationSheet evalSheet = ec.getWorkbook().getSheet(ec.getSheetIndex());
        EvaluationCell evalCell = evalSheet.getCell(ec.getRowIndex(), ec.getColumnIndex());
        Stack<ValueEval> stack = new Stack<ValueEval>();
        int iSize = ptgs.length;
        for (int i = 0; i < iSize; ++i) {
            ValueEval opResult;
            Ptg ptg = ptgs[i];
            if (this.dbgEvaluationOutputIndent > 0) {
                this.EVAL_LOG.log(3, dbgIndentStr + "  * ptg " + i + ": " + ptg + ", stack: " + stack);
            }
            if (ptg instanceof AttrPtg) {
                AttrPtg attrPtg = (AttrPtg)ptg;
                if (attrPtg.isSum()) {
                    ptg = FuncVarPtg.SUM;
                }
                if (attrPtg.isOptimizedChoose()) {
                    int dist;
                    ValueEval arg0 = (ValueEval)stack.pop();
                    int[] jumpTable = attrPtg.getJumpTable();
                    int nChoices = jumpTable.length;
                    try {
                        int switchIndex = Choose.evaluateFirstArg(arg0, ec.getRowIndex(), ec.getColumnIndex());
                        if (switchIndex < 1 || switchIndex > nChoices) {
                            stack.push(ErrorEval.VALUE_INVALID);
                            dist = attrPtg.getChooseFuncOffset() + 4;
                        } else {
                            dist = jumpTable[switchIndex - 1];
                        }
                    } catch (EvaluationException e) {
                        stack.push(e.getErrorEval());
                        dist = attrPtg.getChooseFuncOffset() + 4;
                    }
                    i += WorkbookEvaluator.countTokensToBeSkipped(ptgs, i, dist -= nChoices * 2 + 2);
                    continue;
                }
                if (attrPtg.isOptimizedIf()) {
                    boolean evaluatedPredicate;
                    if (evalCell.isPartOfArrayFormulaGroup()) continue;
                    ValueEval arg0 = (ValueEval)stack.pop();
                    try {
                        evaluatedPredicate = IfFunc.evaluateFirstArg(arg0, ec.getRowIndex(), ec.getColumnIndex());
                    } catch (EvaluationException e) {
                        stack.push(e.getErrorEval());
                        int dist = attrPtg.getData();
                        i += WorkbookEvaluator.countTokensToBeSkipped(ptgs, i, dist);
                        attrPtg = (AttrPtg)ptgs[i];
                        dist = attrPtg.getData() + 1;
                        i += WorkbookEvaluator.countTokensToBeSkipped(ptgs, i, dist);
                        continue;
                    }
                    if (evaluatedPredicate) continue;
                    short dist = attrPtg.getData();
                    i += WorkbookEvaluator.countTokensToBeSkipped(ptgs, i, dist);
                    Ptg nextPtg = ptgs[i + 1];
                    if (!(ptgs[i] instanceof AttrPtg) || !(nextPtg instanceof FuncVarPtg) || ((FuncVarPtg)nextPtg).getFunctionIndex() != 1) continue;
                    stack.push(arg0);
                    stack.push(BoolEval.FALSE);
                    continue;
                }
                if (attrPtg.isSkip() && !evalCell.isPartOfArrayFormulaGroup()) {
                    int dist = attrPtg.getData() + 1;
                    i += WorkbookEvaluator.countTokensToBeSkipped(ptgs, i, dist);
                    if (stack.peek() != MissingArgEval.instance) continue;
                    stack.pop();
                    stack.push(BlankEval.instance);
                    continue;
                }
            }
            if (ptg instanceof ControlPtg || ptg instanceof MemFuncPtg || ptg instanceof MemAreaPtg || ptg instanceof MemErrPtg) continue;
            if (ptg instanceof UnionPtg) {
                ValueEval v2 = (ValueEval)stack.pop();
                ValueEval v1 = (ValueEval)stack.pop();
                stack.push(new RefListEval(v1, v2));
                continue;
            }
            if (ptg instanceof OperationPtg) {
                OperationPtg optg = (OperationPtg)ptg;
                int numops = optg.getNumberOfOperands();
                ValueEval[] ops = new ValueEval[numops];
                boolean areaArg = false;
                for (int j = numops - 1; j >= 0; --j) {
                    ValueEval p;
                    ops[j] = p = (ValueEval)stack.pop();
                    if (!(p instanceof AreaEval)) continue;
                    areaArg = true;
                }
                boolean arrayMode = false;
                if (areaArg) {
                    for (int ii = i; ii < iSize; ++ii) {
                        if (!(ptgs[ii] instanceof FuncVarPtg)) continue;
                        FuncVarPtg f = (FuncVarPtg)ptgs[ii];
                        try {
                            Function func = FunctionEval.getBasicFunction(f.getFunctionIndex());
                            if (func == null || !(func instanceof ArrayMode)) break;
                            arrayMode = true;
                        } catch (NotImplementedException notImplementedException) {}
                        break;
                    }
                }
                ec.setArrayMode(arrayMode);
                opResult = OperationEvaluatorFactory.evaluate(optg, ops, ec);
                ec.setArrayMode(false);
            } else {
                opResult = this.getEvalForPtg(ptg, ec);
            }
            if (opResult == null) {
                throw new RuntimeException("Evaluation result must not be null");
            }
            stack.push(opResult);
            if (this.dbgEvaluationOutputIndent <= 0) continue;
            this.EVAL_LOG.log(3, dbgIndentStr + "    = " + opResult);
        }
        ValueEval value = (ValueEval)stack.pop();
        if (!stack.isEmpty()) {
            throw new IllegalStateException("evaluation stack not empty");
        }
        ValueEval result = ec.isSingleValue() ? WorkbookEvaluator.dereferenceResult(value, ec) : value;
        if (this.dbgEvaluationOutputIndent > 0) {
            this.EVAL_LOG.log(3, dbgIndentStr + "finshed eval of " + new CellReference(ec.getRowIndex(), ec.getColumnIndex()).formatAsString() + ": " + result);
            --this.dbgEvaluationOutputIndent;
            if (this.dbgEvaluationOutputIndent == 1) {
                this.dbgEvaluationOutputIndent = -1;
            }
        }
        return result;
    }

    private static int countTokensToBeSkipped(Ptg[] ptgs, int startIndex, int distInBytes) {
        int remBytes = distInBytes;
        int index = startIndex;
        while (remBytes != 0) {
            if ((remBytes -= ptgs[++index].getSize()) < 0) {
                throw new RuntimeException("Bad skip distance (wrong token size calculation).");
            }
            if (index < ptgs.length) continue;
            throw new RuntimeException("Skip distance too far (ran out of formula tokens).");
        }
        return index - startIndex;
    }

    private static ValueEval dereferenceResult(ValueEval evaluationResult, OperationEvaluationContext ec) {
        if (ec == null) {
            throw new IllegalArgumentException("OperationEvaluationContext ec is null");
        }
        if (ec.getWorkbook() == null) {
            throw new IllegalArgumentException("OperationEvaluationContext ec.getWorkbook() is null");
        }
        EvaluationSheet evalSheet = ec.getWorkbook().getSheet(ec.getSheetIndex());
        EvaluationCell evalCell = evalSheet.getCell(ec.getRowIndex(), ec.getColumnIndex());
        ValueEval value = evalCell != null && evalCell.isPartOfArrayFormulaGroup() && evaluationResult instanceof AreaEval ? OperandResolver.getElementFromArray((AreaEval)evaluationResult, evalCell) : WorkbookEvaluator.dereferenceResult(evaluationResult, ec.getRowIndex(), ec.getColumnIndex());
        return value;
    }

    public static ValueEval dereferenceResult(ValueEval evaluationResult, int srcRowNum, int srcColNum) {
        ValueEval value;
        try {
            value = OperandResolver.getSingleValue(evaluationResult, srcRowNum, srcColNum);
        } catch (EvaluationException e) {
            return e.getErrorEval();
        }
        if (value == BlankEval.instance) {
            return NumberEval.ZERO;
        }
        return value;
    }

    private ValueEval getEvalForPtg(Ptg ptg, OperationEvaluationContext ec) {
        if (ptg instanceof NamePtg) {
            NamePtg namePtg = (NamePtg)ptg;
            EvaluationName nameRecord = this._workbook.getName(namePtg);
            return this.getEvalForNameRecord(nameRecord, ec);
        }
        if (ptg instanceof NameXPtg) {
            return this.processNameEval(ec.getNameXEval((NameXPtg)ptg), ec);
        }
        if (ptg instanceof NameXPxg) {
            return this.processNameEval(ec.getNameXEval((NameXPxg)ptg), ec);
        }
        if (ptg instanceof IntPtg) {
            return new NumberEval(((IntPtg)ptg).getValue());
        }
        if (ptg instanceof NumberPtg) {
            return new NumberEval(((NumberPtg)ptg).getValue());
        }
        if (ptg instanceof StringPtg) {
            return new StringEval(((StringPtg)ptg).getValue());
        }
        if (ptg instanceof BoolPtg) {
            return BoolEval.valueOf(((BoolPtg)ptg).getValue());
        }
        if (ptg instanceof ErrPtg) {
            return ErrorEval.valueOf(((ErrPtg)ptg).getErrorCode());
        }
        if (ptg instanceof MissingArgPtg) {
            return MissingArgEval.instance;
        }
        if (ptg instanceof AreaErrPtg || ptg instanceof RefErrorPtg || ptg instanceof DeletedArea3DPtg || ptg instanceof DeletedRef3DPtg) {
            return ErrorEval.REF_INVALID;
        }
        if (ptg instanceof Ref3DPtg) {
            return ec.getRef3DEval((Ref3DPtg)ptg);
        }
        if (ptg instanceof Ref3DPxg) {
            return ec.getRef3DEval((Ref3DPxg)ptg);
        }
        if (ptg instanceof Area3DPtg) {
            return ec.getArea3DEval((Area3DPtg)ptg);
        }
        if (ptg instanceof Area3DPxg) {
            return ec.getArea3DEval((Area3DPxg)ptg);
        }
        if (ptg instanceof RefPtg) {
            RefPtg rptg = (RefPtg)ptg;
            return ec.getRefEval(rptg.getRow(), rptg.getColumn());
        }
        if (ptg instanceof AreaPtg) {
            AreaPtg aptg = (AreaPtg)ptg;
            return ec.getAreaEval(aptg.getFirstRow(), aptg.getFirstColumn(), aptg.getLastRow(), aptg.getLastColumn());
        }
        if (ptg instanceof ArrayPtg) {
            ArrayPtg aptg = (ArrayPtg)ptg;
            return ec.getAreaValueEval(0, 0, aptg.getRowCount() - 1, aptg.getColumnCount() - 1, aptg.getTokenArrayValues());
        }
        if (ptg instanceof UnknownPtg) {
            throw new RuntimeException("UnknownPtg not allowed");
        }
        if (ptg instanceof ExpPtg) {
            throw new RuntimeException("ExpPtg currently not supported");
        }
        throw new RuntimeException("Unexpected ptg class (" + ptg.getClass().getName() + ")");
    }

    private ValueEval processNameEval(ValueEval eval, OperationEvaluationContext ec) {
        if (eval instanceof ExternalNameEval) {
            EvaluationName name = ((ExternalNameEval)eval).getName();
            return this.getEvalForNameRecord(name, ec);
        }
        return eval;
    }

    private ValueEval getEvalForNameRecord(EvaluationName nameRecord, OperationEvaluationContext ec) {
        if (nameRecord.isFunctionName()) {
            return new FunctionNameEval(nameRecord.getNameText());
        }
        if (nameRecord.hasFormula()) {
            return this.evaluateNameFormula(nameRecord.getNameDefinition(), ec);
        }
        throw new RuntimeException("Don't know how to evaluate name '" + nameRecord.getNameText() + "'");
    }

    ValueEval evaluateNameFormula(Ptg[] ptgs, OperationEvaluationContext ec) {
        if (ptgs.length == 1 && !(ptgs[0] instanceof FuncVarPtg)) {
            return this.getEvalForPtg(ptgs[0], ec);
        }
        OperationEvaluationContext anyValueContext = new OperationEvaluationContext(this, ec.getWorkbook(), ec.getSheetIndex(), ec.getRowIndex(), ec.getColumnIndex(), new EvaluationTracker(this._cache), false);
        return this.evaluateFormula(anyValueContext, ptgs);
    }

    ValueEval evaluateReference(EvaluationSheet sheet, int sheetIndex, int rowIndex, int columnIndex, EvaluationTracker tracker) {
        EvaluationCell cell = sheet.getCell(rowIndex, columnIndex);
        return this.evaluateAny(cell, sheetIndex, rowIndex, columnIndex, tracker);
    }

    public FreeRefFunction findUserDefinedFunction(String functionName) {
        return this._udfFinder.findFunction(functionName);
    }

    public ValueEval evaluate(String formula, CellReference ref) {
        String sheetName = ref == null ? null : ref.getSheetName();
        int sheetIndex = sheetName == null ? -1 : this.getWorkbook().getSheetIndex(sheetName);
        int rowIndex = ref == null ? -1 : ref.getRow();
        int colIndex = ref == null ? -1 : (int)ref.getCol();
        OperationEvaluationContext ec = new OperationEvaluationContext(this, this.getWorkbook(), sheetIndex, rowIndex, colIndex, new EvaluationTracker(this._cache));
        Ptg[] ptgs = FormulaParser.parse(formula, (FormulaParsingWorkbook)((Object)this.getWorkbook()), FormulaType.CELL, sheetIndex, rowIndex);
        return this.evaluateNameFormula(ptgs, ec);
    }

    public ValueEval evaluate(String formula, CellReference target, CellRangeAddressBase region) {
        return this.evaluate(formula, target, region, FormulaType.CELL);
    }

    public ValueEval evaluateList(String formula, CellReference target, CellRangeAddressBase region) {
        return this.evaluate(formula, target, region, FormulaType.DATAVALIDATION_LIST);
    }

    private ValueEval evaluate(String formula, CellReference target, CellRangeAddressBase region, FormulaType formulaType) {
        String sheetName;
        String string = sheetName = target == null ? null : target.getSheetName();
        if (sheetName == null) {
            throw new IllegalArgumentException("Sheet name is required");
        }
        int sheetIndex = this.getWorkbook().getSheetIndex(sheetName);
        Ptg[] ptgs = FormulaParser.parse(formula, (FormulaParsingWorkbook)((Object)this.getWorkbook()), formulaType, sheetIndex, target.getRow());
        this.adjustRegionRelativeReference(ptgs, target, region);
        OperationEvaluationContext ec = new OperationEvaluationContext(this, this.getWorkbook(), sheetIndex, target.getRow(), target.getCol(), new EvaluationTracker(this._cache), formulaType.isSingleValue());
        return this.evaluateNameFormula(ptgs, ec);
    }

    protected boolean adjustRegionRelativeReference(Ptg[] ptgs, CellReference target, CellRangeAddressBase region) {
        int deltaRow = target.getRow() - region.getFirstRow();
        int deltaColumn = target.getCol() - region.getFirstColumn();
        boolean shifted = false;
        for (Ptg ptg : ptgs) {
            if (!(ptg instanceof RefPtgBase)) continue;
            RefPtgBase ref = (RefPtgBase)ptg;
            SpreadsheetVersion version = this._workbook.getSpreadsheetVersion();
            if (ref.isRowRelative() && deltaRow > 0) {
                int rowIndex = ref.getRow() + deltaRow;
                if (rowIndex > version.getMaxRows()) {
                    throw new IndexOutOfBoundsException(version.name() + " files can only have " + version.getMaxRows() + " rows, but row " + rowIndex + " was requested.");
                }
                ref.setRow(rowIndex);
                shifted = true;
            }
            if (!ref.isColRelative() || deltaColumn <= 0) continue;
            int colIndex = ref.getColumn() + deltaColumn;
            if (colIndex > version.getMaxColumns()) {
                throw new IndexOutOfBoundsException(version.name() + " files can only have " + version.getMaxColumns() + " columns, but column " + colIndex + " was requested.");
            }
            ref.setColumn(colIndex);
            shifted = true;
        }
        return shifted;
    }

    public void setIgnoreMissingWorkbooks(boolean ignore) {
        this._ignoreMissingWorkbooks = ignore;
    }

    public boolean isIgnoreMissingWorkbooks() {
        return this._ignoreMissingWorkbooks;
    }

    public static Collection<String> getSupportedFunctionNames() {
        TreeSet<String> lst = new TreeSet<String>();
        lst.addAll(FunctionEval.getSupportedFunctionNames());
        lst.addAll(AnalysisToolPak.getSupportedFunctionNames());
        return Collections.unmodifiableCollection(lst);
    }

    public static Collection<String> getNotSupportedFunctionNames() {
        TreeSet<String> lst = new TreeSet<String>();
        lst.addAll(FunctionEval.getNotSupportedFunctionNames());
        lst.addAll(AnalysisToolPak.getNotSupportedFunctionNames());
        return Collections.unmodifiableCollection(lst);
    }

    public static void registerFunction(String name, FreeRefFunction func) {
        AnalysisToolPak.registerFunction(name, func);
    }

    public static void registerFunction(String name, Function func) {
        FunctionEval.registerFunction(name, func);
    }

    public void setDebugEvaluationOutputForNextEval(boolean value) {
        this.dbgEvaluationOutputForNextEval = value;
    }

    public boolean isDebugEvaluationOutputForNextEval() {
        return this.dbgEvaluationOutputForNextEval;
    }
}

