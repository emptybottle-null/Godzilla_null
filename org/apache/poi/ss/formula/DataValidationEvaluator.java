/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.ss.formula;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.poi.ss.formula.TwoDEval;
import org.apache.poi.ss.formula.WorkbookEvaluator;
import org.apache.poi.ss.formula.WorkbookEvaluatorProvider;
import org.apache.poi.ss.formula.eval.BlankEval;
import org.apache.poi.ss.formula.eval.BoolEval;
import org.apache.poi.ss.formula.eval.ErrorEval;
import org.apache.poi.ss.formula.eval.NumberEval;
import org.apache.poi.ss.formula.eval.RefEval;
import org.apache.poi.ss.formula.eval.StringEval;
import org.apache.poi.ss.formula.eval.ValueEval;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DataValidation;
import org.apache.poi.ss.usermodel.DataValidationConstraint;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.CellRangeAddressBase;
import org.apache.poi.ss.util.CellRangeAddressList;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.ss.util.SheetUtil;

public class DataValidationEvaluator {
    private final Map<String, List<? extends DataValidation>> validations = new HashMap<String, List<? extends DataValidation>>();
    private final Workbook workbook;
    private final WorkbookEvaluator workbookEvaluator;

    public DataValidationEvaluator(Workbook wb, WorkbookEvaluatorProvider provider) {
        this.workbook = wb;
        this.workbookEvaluator = provider._getWorkbookEvaluator();
    }

    protected WorkbookEvaluator getWorkbookEvaluator() {
        return this.workbookEvaluator;
    }

    public void clearAllCachedValues() {
        this.validations.clear();
    }

    private List<? extends DataValidation> getValidations(Sheet sheet) {
        List<? extends DataValidation> dvs = this.validations.get(sheet.getSheetName());
        if (dvs == null && !this.validations.containsKey(sheet.getSheetName())) {
            dvs = sheet.getDataValidations();
            this.validations.put(sheet.getSheetName(), dvs);
        }
        return dvs;
    }

    public DataValidation getValidationForCell(CellReference cell) {
        DataValidationContext vc = this.getValidationContextForCell(cell);
        return vc == null ? null : vc.getValidation();
    }

    public DataValidationContext getValidationContextForCell(CellReference cell) {
        Sheet sheet = this.workbook.getSheet(cell.getSheetName());
        if (sheet == null) {
            return null;
        }
        List<? extends DataValidation> dataValidations = this.getValidations(sheet);
        if (dataValidations == null) {
            return null;
        }
        for (DataValidation dataValidation : dataValidations) {
            CellRangeAddressList regions = dataValidation.getRegions();
            if (regions == null) {
                return null;
            }
            for (CellRangeAddress range : regions.getCellRangeAddresses()) {
                if (!range.isInRange(cell)) continue;
                return new DataValidationContext(dataValidation, this, range, cell);
            }
        }
        return null;
    }

    public List<ValueEval> getValidationValuesForCell(CellReference cell) {
        DataValidationContext context = this.getValidationContextForCell(cell);
        if (context == null) {
            return null;
        }
        return DataValidationEvaluator.getValidationValuesForConstraint(context);
    }

    protected static List<ValueEval> getValidationValuesForConstraint(DataValidationContext context) {
        ArrayList<ValueEval> values;
        block4: {
            ValueEval eval;
            String formula;
            block3: {
                DataValidationConstraint val = context.getValidation().getValidationConstraint();
                if (val.getValidationType() != 3) {
                    return null;
                }
                formula = val.getFormula1();
                values = new ArrayList<ValueEval>();
                if (val.getExplicitListValues() == null || val.getExplicitListValues().length <= 0) break block3;
                for (String s : val.getExplicitListValues()) {
                    if (s == null) continue;
                    values.add(new StringEval(s));
                }
                break block4;
            }
            if (formula == null || !((eval = context.getEvaluator().getWorkbookEvaluator().evaluateList(formula, context.getTarget(), context.getRegion())) instanceof TwoDEval)) break block4;
            TwoDEval twod = (TwoDEval)eval;
            for (int i = 0; i < twod.getHeight(); ++i) {
                ValueEval cellValue = twod.getValue(i, 0);
                values.add(cellValue);
            }
        }
        return Collections.unmodifiableList(values);
    }

    public boolean isValidCell(CellReference cellRef) {
        DataValidationContext context = this.getValidationContextForCell(cellRef);
        if (context == null) {
            return true;
        }
        Cell cell = SheetUtil.getCell(this.workbook.getSheet(cellRef.getSheetName()), cellRef.getRow(), cellRef.getCol());
        if (cell == null || DataValidationEvaluator.isType(cell, CellType.BLANK) || DataValidationEvaluator.isType(cell, CellType.STRING) && (cell.getStringCellValue() == null || cell.getStringCellValue().isEmpty())) {
            return context.getValidation().getEmptyCellAllowed();
        }
        return ValidationEnum.isValid(cell, context);
    }

    public static boolean isType(Cell cell, CellType type) {
        CellType cellType = cell.getCellType();
        return cellType == type || cellType == CellType.FORMULA && cell.getCachedFormulaResultType() == type;
    }

    public static class DataValidationContext {
        private final DataValidation dv;
        private final DataValidationEvaluator dve;
        private final CellRangeAddressBase region;
        private final CellReference target;

        public DataValidationContext(DataValidation dv, DataValidationEvaluator dve, CellRangeAddressBase region, CellReference target) {
            this.dv = dv;
            this.dve = dve;
            this.region = region;
            this.target = target;
        }

        public DataValidation getValidation() {
            return this.dv;
        }

        public DataValidationEvaluator getEvaluator() {
            return this.dve;
        }

        public CellRangeAddressBase getRegion() {
            return this.region;
        }

        public CellReference getTarget() {
            return this.target;
        }

        public int getOffsetColumns() {
            return this.target.getCol() - this.region.getFirstColumn();
        }

        public int getOffsetRows() {
            return this.target.getRow() - this.region.getFirstRow();
        }

        public int getSheetIndex() {
            return this.dve.getWorkbookEvaluator().getSheetIndex(this.target.getSheetName());
        }

        public String getFormula1() {
            return this.dv.getValidationConstraint().getFormula1();
        }

        public String getFormula2() {
            return this.dv.getValidationConstraint().getFormula2();
        }

        public int getOperator() {
            return this.dv.getValidationConstraint().getOperator();
        }
    }

    public static enum OperatorEnum {
        BETWEEN{

            @Override
            public boolean isValid(Double cellValue, Double v1, Double v2) {
                return cellValue.compareTo(v1) >= 0 && cellValue.compareTo(v2) <= 0;
            }
        }
        ,
        NOT_BETWEEN{

            @Override
            public boolean isValid(Double cellValue, Double v1, Double v2) {
                return cellValue.compareTo(v1) < 0 || cellValue.compareTo(v2) > 0;
            }
        }
        ,
        EQUAL{

            @Override
            public boolean isValid(Double cellValue, Double v1, Double v2) {
                return cellValue.compareTo(v1) == 0;
            }
        }
        ,
        NOT_EQUAL{

            @Override
            public boolean isValid(Double cellValue, Double v1, Double v2) {
                return cellValue.compareTo(v1) != 0;
            }
        }
        ,
        GREATER_THAN{

            @Override
            public boolean isValid(Double cellValue, Double v1, Double v2) {
                return cellValue.compareTo(v1) > 0;
            }
        }
        ,
        LESS_THAN{

            @Override
            public boolean isValid(Double cellValue, Double v1, Double v2) {
                return cellValue.compareTo(v1) < 0;
            }
        }
        ,
        GREATER_OR_EQUAL{

            @Override
            public boolean isValid(Double cellValue, Double v1, Double v2) {
                return cellValue.compareTo(v1) >= 0;
            }
        }
        ,
        LESS_OR_EQUAL{

            @Override
            public boolean isValid(Double cellValue, Double v1, Double v2) {
                return cellValue.compareTo(v1) <= 0;
            }
        };

        public static final OperatorEnum IGNORED;

        public abstract boolean isValid(Double var1, Double var2, Double var3);

        static {
            IGNORED = BETWEEN;
        }
    }

    public static enum ValidationEnum {
        ANY{

            @Override
            public boolean isValidValue(Cell cell, DataValidationContext context) {
                return true;
            }
        }
        ,
        INTEGER{

            @Override
            public boolean isValidValue(Cell cell, DataValidationContext context) {
                if (super.isValidValue(cell, context)) {
                    double value = cell.getNumericCellValue();
                    return Double.compare(value, (int)value) == 0;
                }
                return false;
            }
        }
        ,
        DECIMAL,
        LIST{

            @Override
            public boolean isValidValue(Cell cell, DataValidationContext context) {
                List<ValueEval> valueList = DataValidationEvaluator.getValidationValuesForConstraint(context);
                if (valueList == null) {
                    return true;
                }
                for (ValueEval listVal : valueList) {
                    ValueEval comp;
                    ValueEval valueEval = comp = listVal instanceof RefEval ? ((RefEval)listVal).getInnerValueEval(context.getSheetIndex()) : listVal;
                    if (comp instanceof BlankEval) {
                        return true;
                    }
                    if (comp instanceof ErrorEval || !(comp instanceof BoolEval ? DataValidationEvaluator.isType(cell, CellType.BOOLEAN) && ((BoolEval)comp).getBooleanValue() == cell.getBooleanCellValue() : (comp instanceof NumberEval ? DataValidationEvaluator.isType(cell, CellType.NUMERIC) && ((NumberEval)comp).getNumberValue() == cell.getNumericCellValue() : comp instanceof StringEval && DataValidationEvaluator.isType(cell, CellType.STRING) && ((StringEval)comp).getStringValue().equalsIgnoreCase(cell.getStringCellValue())))) continue;
                    return true;
                }
                return false;
            }
        }
        ,
        DATE,
        TIME,
        TEXT_LENGTH{

            @Override
            public boolean isValidValue(Cell cell, DataValidationContext context) {
                if (!DataValidationEvaluator.isType(cell, CellType.STRING)) {
                    return false;
                }
                String v = cell.getStringCellValue();
                return this.isValidNumericValue(Double.valueOf(v.length()), context);
            }
        }
        ,
        FORMULA{

            @Override
            public boolean isValidValue(Cell cell, DataValidationContext context) {
                ValueEval comp = context.getEvaluator().getWorkbookEvaluator().evaluate(context.getFormula1(), context.getTarget(), context.getRegion());
                if (comp instanceof RefEval) {
                    comp = ((RefEval)comp).getInnerValueEval(((RefEval)comp).getFirstSheetIndex());
                }
                if (comp instanceof BlankEval) {
                    return true;
                }
                if (comp instanceof ErrorEval) {
                    return false;
                }
                if (comp instanceof BoolEval) {
                    return ((BoolEval)comp).getBooleanValue();
                }
                if (comp instanceof NumberEval) {
                    return ((NumberEval)comp).getNumberValue() != 0.0;
                }
                return false;
            }
        };


        public boolean isValidValue(Cell cell, DataValidationContext context) {
            return this.isValidNumericCell(cell, context);
        }

        protected boolean isValidNumericCell(Cell cell, DataValidationContext context) {
            if (!DataValidationEvaluator.isType(cell, CellType.NUMERIC)) {
                return false;
            }
            Double value = cell.getNumericCellValue();
            return this.isValidNumericValue(value, context);
        }

        protected boolean isValidNumericValue(Double value, DataValidationContext context) {
            try {
                Double t1 = this.evalOrConstant(context.getFormula1(), context);
                if (t1 == null) {
                    return true;
                }
                Double t2 = null;
                if ((context.getOperator() == 0 || context.getOperator() == 1) && (t2 = this.evalOrConstant(context.getFormula2(), context)) == null) {
                    return true;
                }
                return OperatorEnum.values()[context.getOperator()].isValid(value, t1, t2);
            } catch (NumberFormatException e) {
                return false;
            }
        }

        private Double evalOrConstant(String formula, DataValidationContext context) throws NumberFormatException {
            if (formula == null || formula.trim().isEmpty()) {
                return null;
            }
            try {
                return Double.valueOf(formula);
            } catch (NumberFormatException numberFormatException) {
                ValueEval eval = context.getEvaluator().getWorkbookEvaluator().evaluate(formula, context.getTarget(), context.getRegion());
                if (eval instanceof RefEval) {
                    eval = ((RefEval)eval).getInnerValueEval(((RefEval)eval).getFirstSheetIndex());
                }
                if (eval instanceof BlankEval) {
                    return null;
                }
                if (eval instanceof NumberEval) {
                    return ((NumberEval)eval).getNumberValue();
                }
                if (eval instanceof StringEval) {
                    String value = ((StringEval)eval).getStringValue();
                    if (value == null || value.trim().isEmpty()) {
                        return null;
                    }
                    return Double.valueOf(value);
                }
                throw new NumberFormatException("Formula '" + formula + "' evaluates to something other than a number");
            }
        }

        public static boolean isValid(Cell cell, DataValidationContext context) {
            return ValidationEnum.values()[context.getValidation().getValidationConstraint().getValidationType()].isValidValue(cell, context);
        }
    }
}

