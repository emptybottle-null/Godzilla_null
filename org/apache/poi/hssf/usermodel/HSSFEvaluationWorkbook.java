/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.hssf.usermodel;

import org.apache.poi.hssf.model.InternalWorkbook;
import org.apache.poi.hssf.record.NameRecord;
import org.apache.poi.hssf.record.aggregates.FormulaRecordAggregate;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFEvaluationCell;
import org.apache.poi.hssf.usermodel.HSSFEvaluationSheet;
import org.apache.poi.hssf.usermodel.HSSFName;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.SpreadsheetVersion;
import org.apache.poi.ss.formula.EvaluationCell;
import org.apache.poi.ss.formula.EvaluationName;
import org.apache.poi.ss.formula.EvaluationSheet;
import org.apache.poi.ss.formula.EvaluationWorkbook;
import org.apache.poi.ss.formula.FormulaParsingWorkbook;
import org.apache.poi.ss.formula.FormulaRenderingWorkbook;
import org.apache.poi.ss.formula.SheetIdentifier;
import org.apache.poi.ss.formula.SheetRangeIdentifier;
import org.apache.poi.ss.formula.ptg.Area3DPtg;
import org.apache.poi.ss.formula.ptg.NamePtg;
import org.apache.poi.ss.formula.ptg.NameXPtg;
import org.apache.poi.ss.formula.ptg.Ptg;
import org.apache.poi.ss.formula.ptg.Ref3DPtg;
import org.apache.poi.ss.formula.udf.UDFFinder;
import org.apache.poi.ss.usermodel.Table;
import org.apache.poi.ss.util.AreaReference;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.util.Internal;

@Internal
public final class HSSFEvaluationWorkbook
implements FormulaRenderingWorkbook,
EvaluationWorkbook,
FormulaParsingWorkbook {
    private final HSSFWorkbook _uBook;
    private final InternalWorkbook _iBook;

    public static HSSFEvaluationWorkbook create(HSSFWorkbook book) {
        if (book == null) {
            return null;
        }
        return new HSSFEvaluationWorkbook(book);
    }

    private HSSFEvaluationWorkbook(HSSFWorkbook book) {
        this._uBook = book;
        this._iBook = book.getWorkbook();
    }

    @Override
    public void clearAllCachedResultValues() {
    }

    @Override
    public HSSFName createName() {
        return this._uBook.createName();
    }

    @Override
    public int getExternalSheetIndex(String sheetName) {
        int sheetIndex = this._uBook.getSheetIndex(sheetName);
        return this._iBook.checkExternSheet(sheetIndex);
    }

    @Override
    public int getExternalSheetIndex(String workbookName, String sheetName) {
        return this._iBook.getExternalSheetIndex(workbookName, sheetName);
    }

    @Override
    public Ptg get3DReferencePtg(CellReference cr, SheetIdentifier sheet) {
        int extIx = this.getSheetExtIx(sheet);
        return new Ref3DPtg(cr, extIx);
    }

    @Override
    public Ptg get3DReferencePtg(AreaReference areaRef, SheetIdentifier sheet) {
        int extIx = this.getSheetExtIx(sheet);
        return new Area3DPtg(areaRef, extIx);
    }

    @Override
    public NameXPtg getNameXPtg(String name, SheetIdentifier sheet) {
        int sheetRefIndex = this.getSheetExtIx(sheet);
        return this._iBook.getNameXPtg(name, sheetRefIndex, this._uBook.getUDFFinder());
    }

    @Override
    public EvaluationName getName(String name, int sheetIndex) {
        for (int i = 0; i < this._iBook.getNumNames(); ++i) {
            NameRecord nr = this._iBook.getNameRecord(i);
            if (nr.getSheetNumber() != sheetIndex + 1 || !name.equalsIgnoreCase(nr.getNameText())) continue;
            return new Name(nr, i);
        }
        return sheetIndex == -1 ? null : this.getName(name, -1);
    }

    @Override
    public int getSheetIndex(EvaluationSheet evalSheet) {
        HSSFSheet sheet = ((HSSFEvaluationSheet)evalSheet).getHSSFSheet();
        return this._uBook.getSheetIndex(sheet);
    }

    @Override
    public int getSheetIndex(String sheetName) {
        return this._uBook.getSheetIndex(sheetName);
    }

    @Override
    public String getSheetName(int sheetIndex) {
        return this._uBook.getSheetName(sheetIndex);
    }

    @Override
    public EvaluationSheet getSheet(int sheetIndex) {
        return new HSSFEvaluationSheet(this._uBook.getSheetAt(sheetIndex));
    }

    @Override
    public int convertFromExternSheetIndex(int externSheetIndex) {
        return this._iBook.getFirstSheetIndexFromExternSheetIndex(externSheetIndex);
    }

    @Override
    public EvaluationWorkbook.ExternalSheet getExternalSheet(int externSheetIndex) {
        EvaluationWorkbook.ExternalSheet sheet = this._iBook.getExternalSheet(externSheetIndex);
        if (sheet == null) {
            int localSheetIndex = this.convertFromExternSheetIndex(externSheetIndex);
            if (localSheetIndex == -1) {
                return null;
            }
            if (localSheetIndex == -2) {
                return null;
            }
            String sheetName = this.getSheetName(localSheetIndex);
            int lastLocalSheetIndex = this._iBook.getLastSheetIndexFromExternSheetIndex(externSheetIndex);
            if (lastLocalSheetIndex == localSheetIndex) {
                sheet = new EvaluationWorkbook.ExternalSheet(null, sheetName);
            } else {
                String lastSheetName = this.getSheetName(lastLocalSheetIndex);
                sheet = new EvaluationWorkbook.ExternalSheetRange(null, sheetName, lastSheetName);
            }
        }
        return sheet;
    }

    @Override
    public EvaluationWorkbook.ExternalSheet getExternalSheet(String firstSheetName, String lastSheetName, int externalWorkbookNumber) {
        throw new IllegalStateException("XSSF-style external references are not supported for HSSF");
    }

    @Override
    public EvaluationWorkbook.ExternalName getExternalName(int externSheetIndex, int externNameIndex) {
        return this._iBook.getExternalName(externSheetIndex, externNameIndex);
    }

    @Override
    public EvaluationWorkbook.ExternalName getExternalName(String nameName, String sheetName, int externalWorkbookNumber) {
        throw new IllegalStateException("XSSF-style external names are not supported for HSSF");
    }

    @Override
    public String resolveNameXText(NameXPtg n) {
        return this._iBook.resolveNameXText(n.getSheetRefIndex(), n.getNameIndex());
    }

    @Override
    public String getSheetFirstNameByExternSheet(int externSheetIndex) {
        return this._iBook.findSheetFirstNameFromExternSheet(externSheetIndex);
    }

    @Override
    public String getSheetLastNameByExternSheet(int externSheetIndex) {
        return this._iBook.findSheetLastNameFromExternSheet(externSheetIndex);
    }

    @Override
    public String getNameText(NamePtg namePtg) {
        return this._iBook.getNameRecord(namePtg.getIndex()).getNameText();
    }

    @Override
    public EvaluationName getName(NamePtg namePtg) {
        int ix = namePtg.getIndex();
        return new Name(this._iBook.getNameRecord(ix), ix);
    }

    @Override
    public Ptg[] getFormulaTokens(EvaluationCell evalCell) {
        HSSFCell cell = ((HSSFEvaluationCell)evalCell).getHSSFCell();
        FormulaRecordAggregate fra = (FormulaRecordAggregate)cell.getCellValueRecord();
        return fra.getFormulaTokens();
    }

    @Override
    public UDFFinder getUDFFinder() {
        return this._uBook.getUDFFinder();
    }

    private int getSheetExtIx(SheetIdentifier sheetIden) {
        int extIx;
        if (sheetIden == null) {
            extIx = -1;
        } else {
            String firstSheetName;
            String workbookName = sheetIden.getBookName();
            String lastSheetName = firstSheetName = sheetIden.getSheetIdentifier().getName();
            if (sheetIden instanceof SheetRangeIdentifier) {
                lastSheetName = ((SheetRangeIdentifier)sheetIden).getLastSheetIdentifier().getName();
            }
            if (workbookName == null) {
                int firstSheetIndex = this._uBook.getSheetIndex(firstSheetName);
                int lastSheetIndex = this._uBook.getSheetIndex(lastSheetName);
                extIx = this._iBook.checkExternSheet(firstSheetIndex, lastSheetIndex);
            } else {
                extIx = this._iBook.getExternalSheetIndex(workbookName, firstSheetName, lastSheetName);
            }
        }
        return extIx;
    }

    @Override
    public SpreadsheetVersion getSpreadsheetVersion() {
        return SpreadsheetVersion.EXCEL97;
    }

    @Override
    public Table getTable(String name) {
        throw new IllegalStateException("XSSF-style tables are not supported for HSSF");
    }

    private static final class Name
    implements EvaluationName {
        private final NameRecord _nameRecord;
        private final int _index;

        public Name(NameRecord nameRecord, int index) {
            this._nameRecord = nameRecord;
            this._index = index;
        }

        @Override
        public Ptg[] getNameDefinition() {
            return this._nameRecord.getNameDefinition();
        }

        @Override
        public String getNameText() {
            return this._nameRecord.getNameText();
        }

        @Override
        public boolean hasFormula() {
            return this._nameRecord.hasFormula();
        }

        @Override
        public boolean isFunctionName() {
            return this._nameRecord.isFunctionName();
        }

        @Override
        public boolean isRange() {
            return this._nameRecord.hasFormula();
        }

        @Override
        public NamePtg createPtg() {
            return new NamePtg(this._index);
        }
    }
}

