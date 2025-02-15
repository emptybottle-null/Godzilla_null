/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.xssf.usermodel;

import org.apache.poi.ss.SpreadsheetVersion;
import org.apache.poi.ss.formula.FormulaParser;
import org.apache.poi.ss.formula.FormulaType;
import org.apache.poi.ss.formula.ptg.Ptg;
import org.apache.poi.ss.usermodel.Name;
import org.apache.poi.ss.util.AreaReference;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.xssf.usermodel.XSSFEvaluationWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDefinedName;

public final class XSSFName
implements Name {
    public static final String BUILTIN_PRINT_AREA = "_xlnm.Print_Area";
    public static final String BUILTIN_PRINT_TITLE = "_xlnm.Print_Titles";
    public static final String BUILTIN_CRITERIA = "_xlnm.Criteria:";
    public static final String BUILTIN_EXTRACT = "_xlnm.Extract:";
    public static final String BUILTIN_FILTER_DB = "_xlnm._FilterDatabase";
    public static final String BUILTIN_CONSOLIDATE_AREA = "_xlnm.Consolidate_Area";
    public static final String BUILTIN_DATABASE = "_xlnm.Database";
    public static final String BUILTIN_SHEET_TITLE = "_xlnm.Sheet_Title";
    private XSSFWorkbook _workbook;
    private CTDefinedName _ctName;

    protected XSSFName(CTDefinedName name, XSSFWorkbook workbook) {
        this._workbook = workbook;
        this._ctName = name;
    }

    protected CTDefinedName getCTName() {
        return this._ctName;
    }

    @Override
    public String getNameName() {
        return this._ctName.getName();
    }

    @Override
    public void setNameName(String name) {
        XSSFName.validateName(name);
        String oldName = this.getNameName();
        int sheetIndex = this.getSheetIndex();
        for (XSSFName foundName : this._workbook.getNames(name)) {
            if (foundName.getSheetIndex() != sheetIndex || foundName == this) continue;
            String msg = "The " + (sheetIndex == -1 ? "workbook" : "sheet") + " already contains this name: " + name;
            throw new IllegalArgumentException(msg);
        }
        this._ctName.setName(name);
        this._workbook.updateName(this, oldName);
    }

    @Override
    public String getRefersToFormula() {
        String result = this._ctName.getStringValue();
        if (result == null || result.length() < 1) {
            return null;
        }
        return result;
    }

    @Override
    public void setRefersToFormula(String formulaText) {
        XSSFEvaluationWorkbook fpb = XSSFEvaluationWorkbook.create(this._workbook);
        FormulaParser.parse(formulaText, fpb, FormulaType.NAMEDRANGE, this.getSheetIndex(), -1);
        this._ctName.setStringValue(formulaText);
    }

    @Override
    public boolean isDeleted() {
        String formulaText = this.getRefersToFormula();
        if (formulaText == null) {
            return false;
        }
        XSSFEvaluationWorkbook fpb = XSSFEvaluationWorkbook.create(this._workbook);
        Ptg[] ptgs = FormulaParser.parse(formulaText, fpb, FormulaType.NAMEDRANGE, this.getSheetIndex(), -1);
        return Ptg.doesFormulaReferToDeletedCell(ptgs);
    }

    @Override
    public void setSheetIndex(int index) {
        int lastSheetIx = this._workbook.getNumberOfSheets() - 1;
        if (index < -1 || index > lastSheetIx) {
            throw new IllegalArgumentException("Sheet index (" + index + ") is out of range" + (lastSheetIx == -1 ? "" : " (0.." + lastSheetIx + ")"));
        }
        if (index == -1) {
            if (this._ctName.isSetLocalSheetId()) {
                this._ctName.unsetLocalSheetId();
            }
        } else {
            this._ctName.setLocalSheetId(index);
        }
    }

    @Override
    public int getSheetIndex() {
        return this._ctName.isSetLocalSheetId() ? (int)this._ctName.getLocalSheetId() : -1;
    }

    @Override
    public void setFunction(boolean value) {
        this._ctName.setFunction(value);
    }

    public boolean getFunction() {
        return this._ctName.getFunction();
    }

    public void setFunctionGroupId(int functionGroupId) {
        this._ctName.setFunctionGroupId(functionGroupId);
    }

    public int getFunctionGroupId() {
        return (int)this._ctName.getFunctionGroupId();
    }

    @Override
    public String getSheetName() {
        if (this._ctName.isSetLocalSheetId()) {
            int sheetId = (int)this._ctName.getLocalSheetId();
            return this._workbook.getSheetName(sheetId);
        }
        String ref = this.getRefersToFormula();
        AreaReference areaRef = new AreaReference(ref, SpreadsheetVersion.EXCEL2007);
        return areaRef.getFirstCell().getSheetName();
    }

    @Override
    public boolean isFunctionName() {
        return this.getFunction();
    }

    @Override
    public String getComment() {
        return this._ctName.getComment();
    }

    @Override
    public void setComment(String comment) {
        this._ctName.setComment(comment);
    }

    public int hashCode() {
        return this._ctName.toString().hashCode();
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof XSSFName)) {
            return false;
        }
        XSSFName cf = (XSSFName)o;
        return this._ctName.toString().equals(cf.getCTName().toString());
    }

    private static void validateName(String name) {
        boolean characterIsValid;
        if (name.length() == 0) {
            throw new IllegalArgumentException("Name cannot be blank");
        }
        if (name.length() > 255) {
            throw new IllegalArgumentException("Invalid name: '" + name + "': cannot exceed 255 characters in length");
        }
        if (name.equalsIgnoreCase("R") || name.equalsIgnoreCase("C")) {
            throw new IllegalArgumentException("Invalid name: '" + name + "': cannot be special shorthand R or C");
        }
        char c = name.charAt(0);
        String allowedSymbols = "_\\";
        boolean bl = characterIsValid = Character.isLetter(c) || allowedSymbols.indexOf(c) != -1;
        if (!characterIsValid) {
            throw new IllegalArgumentException("Invalid name: '" + name + "': first character must be underscore or a letter");
        }
        allowedSymbols = "_.\\";
        for (char ch : name.toCharArray()) {
            boolean bl2 = characterIsValid = Character.isLetterOrDigit(ch) || allowedSymbols.indexOf(ch) != -1;
            if (characterIsValid) continue;
            throw new IllegalArgumentException("Invalid name: '" + name + "': name must be letter, digit, period, or underscore");
        }
        if (name.matches("[A-Za-z]+\\d+")) {
            String col = name.replaceAll("\\d", "");
            String row = name.replaceAll("[A-Za-z]", "");
            try {
                if (CellReference.cellReferenceIsWithinRange(col, row, SpreadsheetVersion.EXCEL2007)) {
                    throw new IllegalArgumentException("Invalid name: '" + name + "': cannot be $A$1-style cell reference");
                }
            } catch (NumberFormatException numberFormatException) {
                // empty catch block
            }
        }
        if (name.matches("[Rr]\\d+[Cc]\\d+")) {
            throw new IllegalArgumentException("Invalid name: '" + name + "': cannot be R1C1-style cell reference");
        }
    }
}

