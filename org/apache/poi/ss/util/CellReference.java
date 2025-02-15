/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.ss.util;

import java.util.Locale;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.poi.ss.SpreadsheetVersion;
import org.apache.poi.ss.formula.SheetNameFormatter;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.util.StringUtil;

public class CellReference {
    private static final char ABSOLUTE_REFERENCE_MARKER = '$';
    private static final char SHEET_NAME_DELIMITER = '!';
    private static final char SPECIAL_NAME_DELIMITER = '\'';
    private static final Pattern CELL_REF_PATTERN = Pattern.compile("(\\$?[A-Z]+)?(\\$?[0-9]+)?", 2);
    private static final Pattern STRICTLY_CELL_REF_PATTERN = Pattern.compile("\\$?([A-Z]+)\\$?([0-9]+)", 2);
    private static final Pattern COLUMN_REF_PATTERN = Pattern.compile("\\$?([A-Z]+)", 2);
    private static final Pattern ROW_REF_PATTERN = Pattern.compile("\\$?([0-9]+)");
    private static final Pattern NAMED_RANGE_NAME_PATTERN = Pattern.compile("[_A-Z][_.A-Z0-9]*", 2);
    private final String _sheetName;
    private final int _rowIndex;
    private final int _colIndex;
    private final boolean _isRowAbs;
    private final boolean _isColAbs;

    public CellReference(String cellRef) {
        if (StringUtil.endsWithIgnoreCase(cellRef, "#REF!")) {
            throw new IllegalArgumentException("Cell reference invalid: " + cellRef);
        }
        CellRefParts parts = CellReference.separateRefParts(cellRef);
        this._sheetName = parts.sheetName;
        String colRef = parts.colRef;
        boolean bl = this._isColAbs = colRef.length() > 0 && colRef.charAt(0) == '$';
        if (this._isColAbs) {
            colRef = colRef.substring(1);
        }
        this._colIndex = colRef.length() == 0 ? -1 : CellReference.convertColStringToIndex(colRef);
        String rowRef = parts.rowRef;
        boolean bl2 = this._isRowAbs = rowRef.length() > 0 && rowRef.charAt(0) == '$';
        if (this._isRowAbs) {
            rowRef = rowRef.substring(1);
        }
        this._rowIndex = rowRef.length() == 0 ? -1 : Integer.parseInt(rowRef) - 1;
    }

    public CellReference(int pRow, int pCol) {
        this(pRow, pCol, false, false);
    }

    public CellReference(int pRow, short pCol) {
        this(pRow, pCol & 0xFFFF, false, false);
    }

    public CellReference(Cell cell) {
        this(cell.getSheet().getSheetName(), cell.getRowIndex(), cell.getColumnIndex(), false, false);
    }

    public CellReference(int pRow, int pCol, boolean pAbsRow, boolean pAbsCol) {
        this(null, pRow, pCol, pAbsRow, pAbsCol);
    }

    public CellReference(String pSheetName, int pRow, int pCol, boolean pAbsRow, boolean pAbsCol) {
        if (pRow < -1) {
            throw new IllegalArgumentException("row index may not be negative, but had " + pRow);
        }
        if (pCol < -1) {
            throw new IllegalArgumentException("column index may not be negative, but had " + pCol);
        }
        this._sheetName = pSheetName;
        this._rowIndex = pRow;
        this._colIndex = pCol;
        this._isRowAbs = pAbsRow;
        this._isColAbs = pAbsCol;
    }

    public int getRow() {
        return this._rowIndex;
    }

    public short getCol() {
        return (short)this._colIndex;
    }

    public boolean isRowAbsolute() {
        return this._isRowAbs;
    }

    public boolean isColAbsolute() {
        return this._isColAbs;
    }

    public String getSheetName() {
        return this._sheetName;
    }

    public static boolean isPartAbsolute(String part) {
        return part.charAt(0) == '$';
    }

    public static int convertColStringToIndex(String ref) {
        int retval = 0;
        char[] refs = ref.toUpperCase(Locale.ROOT).toCharArray();
        for (int k = 0; k < refs.length; ++k) {
            char thechar = refs[k];
            if (thechar == '$') {
                if (k == 0) continue;
                throw new IllegalArgumentException("Bad col ref format '" + ref + "'");
            }
            retval = retval * 26 + (thechar - 65 + 1);
        }
        return retval - 1;
    }

    public static NameType classifyCellReference(String str, SpreadsheetVersion ssVersion) {
        String digitsGroup;
        int len = str.length();
        if (len < 1) {
            throw new IllegalArgumentException("Empty string not allowed");
        }
        char firstChar = str.charAt(0);
        switch (firstChar) {
            case '$': 
            case '.': 
            case '_': {
                break;
            }
            default: {
                if (Character.isLetter(firstChar) || Character.isDigit(firstChar)) break;
                throw new IllegalArgumentException("Invalid first char (" + firstChar + ") of cell reference or named range.  Letter expected");
            }
        }
        if (!Character.isDigit(str.charAt(len - 1))) {
            return CellReference.validateNamedRangeName(str, ssVersion);
        }
        Matcher cellRefPatternMatcher = STRICTLY_CELL_REF_PATTERN.matcher(str);
        if (!cellRefPatternMatcher.matches()) {
            return CellReference.validateNamedRangeName(str, ssVersion);
        }
        String lettersGroup = cellRefPatternMatcher.group(1);
        if (CellReference.cellReferenceIsWithinRange(lettersGroup, digitsGroup = cellRefPatternMatcher.group(2), ssVersion)) {
            return NameType.CELL;
        }
        if (str.indexOf(36) >= 0) {
            return NameType.BAD_CELL_OR_NAMED_RANGE;
        }
        return NameType.NAMED_RANGE;
    }

    private static NameType validateNamedRangeName(String str, SpreadsheetVersion ssVersion) {
        String rowStr;
        String colStr;
        Matcher colMatcher = COLUMN_REF_PATTERN.matcher(str);
        if (colMatcher.matches() && CellReference.isColumnWithinRange(colStr = colMatcher.group(1), ssVersion)) {
            return NameType.COLUMN;
        }
        Matcher rowMatcher = ROW_REF_PATTERN.matcher(str);
        if (rowMatcher.matches() && CellReference.isRowWithinRange(rowStr = rowMatcher.group(1), ssVersion)) {
            return NameType.ROW;
        }
        if (!NAMED_RANGE_NAME_PATTERN.matcher(str).matches()) {
            return NameType.BAD_CELL_OR_NAMED_RANGE;
        }
        return NameType.NAMED_RANGE;
    }

    public static boolean cellReferenceIsWithinRange(String colStr, String rowStr, SpreadsheetVersion ssVersion) {
        if (!CellReference.isColumnWithinRange(colStr, ssVersion)) {
            return false;
        }
        return CellReference.isRowWithinRange(rowStr, ssVersion);
    }

    public static boolean isColumnWithinRange(String colStr, SpreadsheetVersion ssVersion) {
        String lastCol = ssVersion.getLastColumnName();
        int lastColLength = lastCol.length();
        int numberOfLetters = colStr.length();
        if (numberOfLetters > lastColLength) {
            return false;
        }
        return numberOfLetters != lastColLength || colStr.toUpperCase(Locale.ROOT).compareTo(lastCol) <= 0;
    }

    public static boolean isRowWithinRange(String rowStr, SpreadsheetVersion ssVersion) {
        long rowNum = Long.parseLong(rowStr) - 1L;
        if (rowNum > Integer.MAX_VALUE) {
            return false;
        }
        return CellReference.isRowWithinRange(Math.toIntExact(rowNum), ssVersion);
    }

    public static boolean isRowWithinRange(int rowNum, SpreadsheetVersion ssVersion) {
        return 0 <= rowNum && rowNum <= ssVersion.getLastRowIndex();
    }

    private static CellRefParts separateRefParts(String reference) {
        int plingPos = reference.lastIndexOf(33);
        String sheetName = CellReference.parseSheetName(reference, plingPos);
        String cell = reference.substring(plingPos + 1).toUpperCase(Locale.ROOT);
        Matcher matcher = CELL_REF_PATTERN.matcher(cell);
        if (!matcher.matches()) {
            throw new IllegalArgumentException("Invalid CellReference: " + reference);
        }
        String col = matcher.group(1);
        String row = matcher.group(2);
        return new CellRefParts(sheetName, row, col);
    }

    private static String parseSheetName(String reference, int indexOfSheetNameDelimiter) {
        boolean isQuoted;
        if (indexOfSheetNameDelimiter < 0) {
            return null;
        }
        boolean bl = isQuoted = reference.charAt(0) == '\'';
        if (!isQuoted) {
            if (!reference.contains(" ")) {
                return reference.substring(0, indexOfSheetNameDelimiter);
            }
            throw new IllegalArgumentException("Sheet names containing spaces must be quoted: (" + reference + ")");
        }
        int lastQuotePos = indexOfSheetNameDelimiter - 1;
        if (reference.charAt(lastQuotePos) != '\'') {
            throw new IllegalArgumentException("Mismatched quotes: (" + reference + ")");
        }
        StringBuilder sb = new StringBuilder(indexOfSheetNameDelimiter);
        for (int i = 1; i < lastQuotePos; ++i) {
            char ch = reference.charAt(i);
            if (ch != '\'') {
                sb.append(ch);
                continue;
            }
            if (i + 1 < lastQuotePos && reference.charAt(i + 1) == '\'') {
                ++i;
                sb.append(ch);
                continue;
            }
            throw new IllegalArgumentException("Bad sheet name quote escaping: (" + reference + ")");
        }
        return sb.toString();
    }

    public static String convertNumToColString(int col) {
        int excelColNum = col + 1;
        StringBuilder colRef = new StringBuilder(2);
        int colRemain = excelColNum;
        while (colRemain > 0) {
            int thisPart = colRemain % 26;
            if (thisPart == 0) {
                thisPart = 26;
            }
            colRemain = (colRemain - thisPart) / 26;
            char colChar = (char)(thisPart + 64);
            colRef.insert(0, colChar);
        }
        return colRef.toString();
    }

    public String formatAsString() {
        return this.formatAsString(true);
    }

    public String formatAsString(boolean includeSheetName) {
        StringBuilder sb = new StringBuilder(32);
        if (includeSheetName && this._sheetName != null) {
            SheetNameFormatter.appendFormat(sb, this._sheetName);
            sb.append('!');
        }
        this.appendCellReference(sb);
        return sb.toString();
    }

    public String toString() {
        return this.getClass().getName() + " [" + this.formatAsString() + "]";
    }

    public String[] getCellRefParts() {
        return new String[]{this._sheetName, Integer.toString(this._rowIndex + 1), CellReference.convertNumToColString(this._colIndex)};
    }

    void appendCellReference(StringBuilder sb) {
        if (this._colIndex != -1) {
            if (this._isColAbs) {
                sb.append('$');
            }
            sb.append(CellReference.convertNumToColString(this._colIndex));
        }
        if (this._rowIndex != -1) {
            if (this._isRowAbs) {
                sb.append('$');
            }
            sb.append(this._rowIndex + 1);
        }
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CellReference)) {
            return false;
        }
        CellReference cr = (CellReference)o;
        return this._rowIndex == cr._rowIndex && this._colIndex == cr._colIndex && this._isRowAbs == cr._isRowAbs && this._isColAbs == cr._isColAbs && (this._sheetName == null ? cr._sheetName == null : this._sheetName.equals(cr._sheetName));
    }

    public int hashCode() {
        return Objects.hash(this._rowIndex, this._colIndex, this._isRowAbs, this._isColAbs, this._sheetName);
    }

    private static final class CellRefParts {
        final String sheetName;
        final String rowRef;
        final String colRef;

        private CellRefParts(String sheetName, String rowRef, String colRef) {
            this.sheetName = sheetName;
            this.rowRef = rowRef != null ? rowRef : "";
            this.colRef = colRef != null ? colRef : "";
        }
    }

    public static enum NameType {
        CELL,
        NAMED_RANGE,
        COLUMN,
        ROW,
        BAD_CELL_OR_NAMED_RANGE;

    }
}

