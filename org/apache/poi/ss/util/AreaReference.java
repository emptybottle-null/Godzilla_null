/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.ss.util;

import java.util.ArrayList;
import java.util.StringTokenizer;
import org.apache.poi.ss.SpreadsheetVersion;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.util.StringUtil;

public class AreaReference {
    private static final char SHEET_NAME_DELIMITER = '!';
    private static final char CELL_DELIMITER = ':';
    private static final char SPECIAL_NAME_DELIMITER = '\'';
    private static final SpreadsheetVersion DEFAULT_SPREADSHEET_VERSION = SpreadsheetVersion.EXCEL97;
    private final CellReference _firstCell;
    private final CellReference _lastCell;
    private final boolean _isSingleCell;
    private final SpreadsheetVersion _version;

    public AreaReference(String reference, SpreadsheetVersion version) {
        SpreadsheetVersion spreadsheetVersion = this._version = null != version ? version : DEFAULT_SPREADSHEET_VERSION;
        if (!AreaReference.isContiguous(reference)) {
            throw new IllegalArgumentException("References passed to the AreaReference must be contiguous, use generateContiguous(ref) if you have non-contiguous references");
        }
        String[] parts = AreaReference.separateAreaRefs(reference);
        String part0 = parts[0];
        if (parts.length == 1) {
            this._lastCell = this._firstCell = new CellReference(part0);
            this._isSingleCell = true;
            return;
        }
        if (parts.length != 2) {
            throw new IllegalArgumentException("Bad area ref '" + reference + "'");
        }
        String part1 = parts[1];
        if (AreaReference.isPlainColumn(part0)) {
            if (!AreaReference.isPlainColumn(part1)) {
                throw new RuntimeException("Bad area ref '" + reference + "'");
            }
            boolean firstIsAbs = CellReference.isPartAbsolute(part0);
            boolean lastIsAbs = CellReference.isPartAbsolute(part1);
            int col0 = CellReference.convertColStringToIndex(part0);
            int col1 = CellReference.convertColStringToIndex(part1);
            this._firstCell = new CellReference(0, col0, true, firstIsAbs);
            this._lastCell = new CellReference(65535, col1, true, lastIsAbs);
            this._isSingleCell = false;
        } else {
            this._firstCell = new CellReference(part0);
            this._lastCell = new CellReference(part1);
            this._isSingleCell = part0.equals(part1);
        }
    }

    public AreaReference(CellReference topLeft, CellReference botRight, SpreadsheetVersion version) {
        boolean swapCols;
        this._version = null != version ? version : DEFAULT_SPREADSHEET_VERSION;
        boolean swapRows = topLeft.getRow() > botRight.getRow();
        boolean bl = swapCols = topLeft.getCol() > botRight.getCol();
        if (swapRows || swapCols) {
            boolean lastColAbs;
            short lastColumn;
            String lastSheet;
            boolean firstColAbs;
            short firstColumn;
            String firstSheet;
            boolean lastRowAbs;
            int lastRow;
            boolean firstRowAbs;
            int firstRow;
            if (swapRows) {
                firstRow = botRight.getRow();
                firstRowAbs = botRight.isRowAbsolute();
                lastRow = topLeft.getRow();
                lastRowAbs = topLeft.isRowAbsolute();
            } else {
                firstRow = topLeft.getRow();
                firstRowAbs = topLeft.isRowAbsolute();
                lastRow = botRight.getRow();
                lastRowAbs = botRight.isRowAbsolute();
            }
            if (swapCols) {
                firstSheet = botRight.getSheetName();
                firstColumn = botRight.getCol();
                firstColAbs = botRight.isColAbsolute();
                lastSheet = topLeft.getSheetName();
                lastColumn = topLeft.getCol();
                lastColAbs = topLeft.isColAbsolute();
            } else {
                firstSheet = topLeft.getSheetName();
                firstColumn = topLeft.getCol();
                firstColAbs = topLeft.isColAbsolute();
                lastSheet = botRight.getSheetName();
                lastColumn = botRight.getCol();
                lastColAbs = botRight.isColAbsolute();
            }
            this._firstCell = new CellReference(firstSheet, firstRow, firstColumn, firstRowAbs, firstColAbs);
            this._lastCell = new CellReference(lastSheet, lastRow, lastColumn, lastRowAbs, lastColAbs);
        } else {
            this._firstCell = topLeft;
            this._lastCell = botRight;
        }
        this._isSingleCell = false;
    }

    private static boolean isPlainColumn(String refPart) {
        for (int i = refPart.length() - 1; i >= 0; --i) {
            char ch = refPart.charAt(i);
            if (ch == '$' && i == 0 || ch >= 'A' && ch <= 'Z') continue;
            return false;
        }
        return true;
    }

    public static boolean isContiguous(String reference) {
        return AreaReference.splitAreaReferences(reference).length == 1;
    }

    public static AreaReference getWholeRow(SpreadsheetVersion version, String start, String end) {
        if (null == version) {
            version = DEFAULT_SPREADSHEET_VERSION;
        }
        return new AreaReference("$A" + start + ":$" + version.getLastColumnName() + end, version);
    }

    public static AreaReference getWholeColumn(SpreadsheetVersion version, String start, String end) {
        if (null == version) {
            version = DEFAULT_SPREADSHEET_VERSION;
        }
        return new AreaReference(start + "$1:" + end + "$" + version.getMaxRows(), version);
    }

    public static boolean isWholeColumnReference(SpreadsheetVersion version, CellReference topLeft, CellReference botRight) {
        if (null == version) {
            version = DEFAULT_SPREADSHEET_VERSION;
        }
        return topLeft.getRow() == 0 && topLeft.isRowAbsolute() && botRight.getRow() == version.getLastRowIndex() && botRight.isRowAbsolute();
    }

    public static AreaReference[] generateContiguous(SpreadsheetVersion version, String reference) {
        String[] splitReferences;
        if (null == version) {
            version = DEFAULT_SPREADSHEET_VERSION;
        }
        ArrayList<AreaReference> refs = new ArrayList<AreaReference>();
        for (String ref : splitReferences = AreaReference.splitAreaReferences(reference)) {
            refs.add(new AreaReference(ref, version));
        }
        return refs.toArray(new AreaReference[0]);
    }

    private static String[] separateAreaRefs(String reference) {
        int len = reference.length();
        int delimiterPos = -1;
        boolean insideDelimitedName = false;
        block4: for (int i = 0; i < len; ++i) {
            switch (reference.charAt(i)) {
                case ':': {
                    if (insideDelimitedName) continue block4;
                    if (delimiterPos >= 0) {
                        throw new IllegalArgumentException("More than one cell delimiter ':' appears in area reference '" + reference + "'");
                    }
                    delimiterPos = i;
                    continue block4;
                }
                case '\'': {
                    break;
                }
                default: {
                    continue block4;
                }
            }
            if (!insideDelimitedName) {
                insideDelimitedName = true;
                continue;
            }
            if (i >= len - 1) {
                throw new IllegalArgumentException("Area reference '" + reference + "' ends with special name delimiter '" + '\'' + "'");
            }
            if (reference.charAt(i + 1) == '\'') {
                ++i;
                continue;
            }
            insideDelimitedName = false;
        }
        if (delimiterPos < 0) {
            return new String[]{reference};
        }
        String partA = reference.substring(0, delimiterPos);
        String partB = reference.substring(delimiterPos + 1);
        if (partB.indexOf(33) >= 0) {
            throw new RuntimeException("Unexpected ! in second cell reference of '" + reference + "'");
        }
        int plingPos = partA.lastIndexOf(33);
        if (plingPos < 0) {
            return new String[]{partA, partB};
        }
        String sheetName = partA.substring(0, plingPos + 1);
        return new String[]{partA, sheetName + partB};
    }

    private static String[] splitAreaReferences(String reference) {
        ArrayList<String> results = new ArrayList<String>();
        String currentSegment = "";
        StringTokenizer st = new StringTokenizer(reference, ",");
        while (st.hasMoreTokens()) {
            int numSingleQuotes;
            if (currentSegment.length() > 0) {
                currentSegment = currentSegment + ",";
            }
            if ((numSingleQuotes = StringUtil.countMatches(currentSegment = currentSegment + st.nextToken(), '\'')) != 0 && numSingleQuotes != 2) continue;
            results.add(currentSegment);
            currentSegment = "";
        }
        if (currentSegment.length() > 0) {
            results.add(currentSegment);
        }
        return results.toArray(new String[0]);
    }

    public boolean isWholeColumnReference() {
        return AreaReference.isWholeColumnReference(this._version, this._firstCell, this._lastCell);
    }

    public boolean isSingleCell() {
        return this._isSingleCell;
    }

    public CellReference getFirstCell() {
        return this._firstCell;
    }

    public CellReference getLastCell() {
        return this._lastCell;
    }

    public CellReference[] getAllReferencedCells() {
        if (this._isSingleCell) {
            return new CellReference[]{this._firstCell};
        }
        int minRow = Math.min(this._firstCell.getRow(), this._lastCell.getRow());
        int maxRow = Math.max(this._firstCell.getRow(), this._lastCell.getRow());
        int minCol = Math.min(this._firstCell.getCol(), this._lastCell.getCol());
        int maxCol = Math.max(this._firstCell.getCol(), this._lastCell.getCol());
        String sheetName = this._firstCell.getSheetName();
        ArrayList<CellReference> refs = new ArrayList<CellReference>();
        for (int row = minRow; row <= maxRow; ++row) {
            for (int col = minCol; col <= maxCol; ++col) {
                CellReference ref = new CellReference(sheetName, row, col, this._firstCell.isRowAbsolute(), this._firstCell.isColAbsolute());
                refs.add(ref);
            }
        }
        return refs.toArray(new CellReference[0]);
    }

    public String formatAsString() {
        if (this.isWholeColumnReference()) {
            return CellReference.convertNumToColString(this._firstCell.getCol()) + ":" + CellReference.convertNumToColString(this._lastCell.getCol());
        }
        StringBuilder sb = new StringBuilder(32);
        sb.append(this._firstCell.formatAsString());
        if (!this._isSingleCell) {
            sb.append(':');
            if (this._lastCell.getSheetName() == null) {
                sb.append(this._lastCell.formatAsString());
            } else {
                this._lastCell.appendCellReference(sb);
            }
        }
        return sb.toString();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(64);
        sb.append(this.getClass().getName()).append(" [");
        try {
            sb.append(this.formatAsString());
        } catch (Exception e) {
            sb.append(e);
        }
        sb.append(']');
        return sb.toString();
    }
}

