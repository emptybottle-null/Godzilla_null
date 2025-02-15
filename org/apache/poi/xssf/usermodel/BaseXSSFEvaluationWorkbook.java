/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.xssf.usermodel;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import org.apache.poi.ss.SpreadsheetVersion;
import org.apache.poi.ss.formula.EvaluationName;
import org.apache.poi.ss.formula.EvaluationWorkbook;
import org.apache.poi.ss.formula.FormulaParser;
import org.apache.poi.ss.formula.FormulaParsingWorkbook;
import org.apache.poi.ss.formula.FormulaRenderingWorkbook;
import org.apache.poi.ss.formula.FormulaType;
import org.apache.poi.ss.formula.SheetIdentifier;
import org.apache.poi.ss.formula.functions.FreeRefFunction;
import org.apache.poi.ss.formula.ptg.Area3DPxg;
import org.apache.poi.ss.formula.ptg.NamePtg;
import org.apache.poi.ss.formula.ptg.NameXPtg;
import org.apache.poi.ss.formula.ptg.NameXPxg;
import org.apache.poi.ss.formula.ptg.Ptg;
import org.apache.poi.ss.formula.ptg.Ref3DPxg;
import org.apache.poi.ss.formula.udf.IndexedUDFFinder;
import org.apache.poi.ss.formula.udf.UDFFinder;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.AreaReference;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.util.Internal;
import org.apache.poi.util.NotImplemented;
import org.apache.poi.xssf.model.ExternalLinksTable;
import org.apache.poi.xssf.usermodel.XSSFName;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFTable;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDefinedName;

@Internal
public abstract class BaseXSSFEvaluationWorkbook
implements FormulaRenderingWorkbook,
EvaluationWorkbook,
FormulaParsingWorkbook {
    protected final XSSFWorkbook _uBook;
    private Map<String, XSSFTable> _tableCache;

    protected BaseXSSFEvaluationWorkbook(XSSFWorkbook book) {
        this._uBook = book;
    }

    @Override
    public void clearAllCachedResultValues() {
        this._tableCache = null;
    }

    private int convertFromExternalSheetIndex(int externSheetIndex) {
        return externSheetIndex;
    }

    @Override
    public int convertFromExternSheetIndex(int externSheetIndex) {
        return externSheetIndex;
    }

    private int convertToExternalSheetIndex(int sheetIndex) {
        return sheetIndex;
    }

    @Override
    public int getExternalSheetIndex(String sheetName) {
        int sheetIndex = this._uBook.getSheetIndex(sheetName);
        return this.convertToExternalSheetIndex(sheetIndex);
    }

    private int resolveBookIndex(String bookName) {
        if (bookName.startsWith("[") && bookName.endsWith("]")) {
            bookName = bookName.substring(1, bookName.length() - 2);
        }
        try {
            return Integer.parseInt(bookName);
        } catch (NumberFormatException numberFormatException) {
            List<ExternalLinksTable> tables = this._uBook.getExternalLinksTable();
            int index = this.findExternalLinkIndex(bookName, tables);
            if (index != -1) {
                return index;
            }
            if (bookName.startsWith("'file:///") && bookName.endsWith("'")) {
                String relBookName = bookName.substring(bookName.lastIndexOf(47) + 1);
                index = this.findExternalLinkIndex(relBookName = relBookName.substring(0, relBookName.length() - 1), tables);
                if (index != -1) {
                    return index;
                }
                FakeExternalLinksTable fakeLinkTable = new FakeExternalLinksTable(relBookName);
                tables.add(fakeLinkTable);
                return tables.size();
            }
            throw new RuntimeException("Book not linked for filename " + bookName);
        }
    }

    private int findExternalLinkIndex(String bookName, List<ExternalLinksTable> tables) {
        int i = 0;
        for (ExternalLinksTable table : tables) {
            if (table.getLinkedFileName().equals(bookName)) {
                return i + 1;
            }
            ++i;
        }
        return -1;
    }

    @Override
    public EvaluationName getName(String name, int sheetIndex) {
        for (int i = 0; i < this._uBook.getNumberOfNames(); ++i) {
            XSSFName nm = this._uBook.getNameAt(i);
            String nameText = nm.getNameName();
            int nameSheetindex = nm.getSheetIndex();
            if (!name.equalsIgnoreCase(nameText) || nameSheetindex != -1 && nameSheetindex != sheetIndex) continue;
            return new Name(nm, i, this);
        }
        return sheetIndex == -1 ? null : this.getName(name, -1);
    }

    @Override
    public String getSheetName(int sheetIndex) {
        return this._uBook.getSheetName(sheetIndex);
    }

    @Override
    public EvaluationWorkbook.ExternalName getExternalName(int externSheetIndex, int externNameIndex) {
        throw new IllegalStateException("HSSF-style external references are not supported for XSSF");
    }

    @Override
    public EvaluationWorkbook.ExternalName getExternalName(String nameName, String sheetName, int externalWorkbookNumber) {
        if (externalWorkbookNumber > 0) {
            int linkNumber = externalWorkbookNumber - 1;
            ExternalLinksTable linkTable = this._uBook.getExternalLinksTable().get(linkNumber);
            for (org.apache.poi.ss.usermodel.Name name : linkTable.getDefinedNames()) {
                if (!name.getNameName().equals(nameName)) continue;
                int nameSheetIndex = name.getSheetIndex() + 1;
                return new EvaluationWorkbook.ExternalName(nameName, -1, nameSheetIndex);
            }
            throw new IllegalArgumentException("Name '" + nameName + "' not found in reference to " + linkTable.getLinkedFileName());
        }
        int nameIdx = this._uBook.getNameIndex(nameName);
        return new EvaluationWorkbook.ExternalName(nameName, nameIdx, 0);
    }

    @Override
    public NameXPxg getNameXPtg(String name, SheetIdentifier sheet) {
        IndexedUDFFinder udfFinder = (IndexedUDFFinder)this.getUDFFinder();
        FreeRefFunction func = udfFinder.findFunction(name);
        if (func != null) {
            return new NameXPxg(null, name);
        }
        if (sheet == null) {
            if (!this._uBook.getNames(name).isEmpty()) {
                return new NameXPxg(null, name);
            }
            return null;
        }
        if (sheet._sheetIdentifier == null) {
            int bookIndex = this.resolveBookIndex(sheet._bookName);
            return new NameXPxg(bookIndex, null, name);
        }
        String sheetName = sheet._sheetIdentifier.getName();
        if (sheet._bookName != null) {
            int bookIndex = this.resolveBookIndex(sheet._bookName);
            return new NameXPxg(bookIndex, sheetName, name);
        }
        return new NameXPxg(sheetName, name);
    }

    @Override
    public Ptg get3DReferencePtg(CellReference cell, SheetIdentifier sheet) {
        if (sheet._bookName != null) {
            int bookIndex = this.resolveBookIndex(sheet._bookName);
            return new Ref3DPxg(bookIndex, sheet, cell);
        }
        return new Ref3DPxg(sheet, cell);
    }

    @Override
    public Ptg get3DReferencePtg(AreaReference area, SheetIdentifier sheet) {
        if (sheet._bookName != null) {
            int bookIndex = this.resolveBookIndex(sheet._bookName);
            return new Area3DPxg(bookIndex, sheet, area);
        }
        return new Area3DPxg(sheet, area);
    }

    @Override
    public String resolveNameXText(NameXPtg n) {
        int idx = n.getNameIndex();
        String name = null;
        IndexedUDFFinder udfFinder = (IndexedUDFFinder)this.getUDFFinder();
        name = udfFinder.getFunctionName(idx);
        if (name != null) {
            return name;
        }
        XSSFName xname = this._uBook.getNameAt(idx);
        if (xname != null) {
            name = xname.getNameName();
        }
        return name;
    }

    @Override
    public EvaluationWorkbook.ExternalSheet getExternalSheet(int externSheetIndex) {
        throw new IllegalStateException("HSSF-style external references are not supported for XSSF");
    }

    @Override
    public EvaluationWorkbook.ExternalSheet getExternalSheet(String firstSheetName, String lastSheetName, int externalWorkbookNumber) {
        String workbookName;
        if (externalWorkbookNumber > 0) {
            int linkNumber = externalWorkbookNumber - 1;
            ExternalLinksTable linkTable = this._uBook.getExternalLinksTable().get(linkNumber);
            workbookName = linkTable.getLinkedFileName();
        } else {
            workbookName = null;
        }
        if (lastSheetName == null || firstSheetName.equals(lastSheetName)) {
            return new EvaluationWorkbook.ExternalSheet(workbookName, firstSheetName);
        }
        return new EvaluationWorkbook.ExternalSheetRange(workbookName, firstSheetName, lastSheetName);
    }

    @Override
    @NotImplemented
    public int getExternalSheetIndex(String workbookName, String sheetName) {
        throw new RuntimeException("not implemented yet");
    }

    @Override
    public int getSheetIndex(String sheetName) {
        return this._uBook.getSheetIndex(sheetName);
    }

    @Override
    public String getSheetFirstNameByExternSheet(int externSheetIndex) {
        int sheetIndex = this.convertFromExternalSheetIndex(externSheetIndex);
        return this._uBook.getSheetName(sheetIndex);
    }

    @Override
    public String getSheetLastNameByExternSheet(int externSheetIndex) {
        return this.getSheetFirstNameByExternSheet(externSheetIndex);
    }

    @Override
    public String getNameText(NamePtg namePtg) {
        return this._uBook.getNameAt(namePtg.getIndex()).getNameName();
    }

    @Override
    public EvaluationName getName(NamePtg namePtg) {
        int ix = namePtg.getIndex();
        return new Name(this._uBook.getNameAt(ix), ix, this);
    }

    @Override
    public XSSFName createName() {
        return this._uBook.createName();
    }

    private static String caseInsensitive(String s) {
        return s.toUpperCase(Locale.ROOT);
    }

    private Map<String, XSSFTable> getTableCache() {
        if (this._tableCache != null) {
            return this._tableCache;
        }
        this._tableCache = new HashMap<String, XSSFTable>();
        for (Sheet sheet : this._uBook) {
            for (XSSFTable tbl : ((XSSFSheet)sheet).getTables()) {
                String lname = BaseXSSFEvaluationWorkbook.caseInsensitive(tbl.getName());
                this._tableCache.put(lname, tbl);
            }
        }
        return this._tableCache;
    }

    @Override
    public XSSFTable getTable(String name) {
        if (name == null) {
            return null;
        }
        String lname = BaseXSSFEvaluationWorkbook.caseInsensitive(name);
        return this.getTableCache().get(lname);
    }

    @Override
    public UDFFinder getUDFFinder() {
        return this._uBook.getUDFFinder();
    }

    @Override
    public SpreadsheetVersion getSpreadsheetVersion() {
        return SpreadsheetVersion.EXCEL2007;
    }

    private static final class Name
    implements EvaluationName {
        private final XSSFName _nameRecord;
        private final int _index;
        private final FormulaParsingWorkbook _fpBook;

        public Name(XSSFName name, int index, FormulaParsingWorkbook fpBook) {
            this._nameRecord = name;
            this._index = index;
            this._fpBook = fpBook;
        }

        @Override
        public Ptg[] getNameDefinition() {
            return FormulaParser.parse(this._nameRecord.getRefersToFormula(), this._fpBook, FormulaType.NAMEDRANGE, this._nameRecord.getSheetIndex());
        }

        @Override
        public String getNameText() {
            return this._nameRecord.getNameName();
        }

        @Override
        public boolean hasFormula() {
            CTDefinedName ctn = this._nameRecord.getCTName();
            String strVal = ctn.getStringValue();
            return !ctn.getFunction() && strVal != null && strVal.length() > 0;
        }

        @Override
        public boolean isFunctionName() {
            return this._nameRecord.isFunctionName();
        }

        @Override
        public boolean isRange() {
            return this.hasFormula();
        }

        @Override
        public NamePtg createPtg() {
            return new NamePtg(this._index);
        }
    }

    private static class FakeExternalLinksTable
    extends ExternalLinksTable {
        private final String fileName;

        private FakeExternalLinksTable(String fileName) {
            this.fileName = fileName;
        }

        @Override
        public String getLinkedFileName() {
            return this.fileName;
        }
    }
}

