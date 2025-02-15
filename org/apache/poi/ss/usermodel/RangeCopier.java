/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.ss.usermodel;

import java.util.Map;
import org.apache.poi.ss.formula.FormulaShifter;
import org.apache.poi.ss.formula.ptg.Ptg;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellRangeAddress;

public abstract class RangeCopier {
    private Sheet sourceSheet;
    private Sheet destSheet;
    private FormulaShifter horizontalFormulaShifter;
    private FormulaShifter verticalFormulaShifter;

    public RangeCopier(Sheet sourceSheet, Sheet destSheet) {
        this.sourceSheet = sourceSheet;
        this.destSheet = destSheet;
    }

    public RangeCopier(Sheet sheet) {
        this(sheet, sheet);
    }

    public void copyRange(CellRangeAddress tilePatternRange, CellRangeAddress tileDestRange) {
        int heightToCopyMinus1;
        Sheet sourceCopy = this.sourceSheet.getWorkbook().cloneSheet(this.sourceSheet.getWorkbook().getSheetIndex(this.sourceSheet));
        int sourceWidthMinus1 = tilePatternRange.getLastColumn() - tilePatternRange.getFirstColumn();
        int sourceHeightMinus1 = tilePatternRange.getLastRow() - tilePatternRange.getFirstRow();
        int nextRowIndexToCopy = tileDestRange.getFirstRow();
        do {
            int widthToCopyMinus1;
            int nextCellIndexInRowToCopy = tileDestRange.getFirstColumn();
            heightToCopyMinus1 = Math.min(sourceHeightMinus1, tileDestRange.getLastRow() - nextRowIndexToCopy);
            int bottomLimitToCopy = tilePatternRange.getFirstRow() + heightToCopyMinus1;
            do {
                widthToCopyMinus1 = Math.min(sourceWidthMinus1, tileDestRange.getLastColumn() - nextCellIndexInRowToCopy);
                int rightLimitToCopy = tilePatternRange.getFirstColumn() + widthToCopyMinus1;
                CellRangeAddress rangeToCopy = new CellRangeAddress(tilePatternRange.getFirstRow(), bottomLimitToCopy, tilePatternRange.getFirstColumn(), rightLimitToCopy);
                this.copyRange(rangeToCopy, nextCellIndexInRowToCopy - rangeToCopy.getFirstColumn(), nextRowIndexToCopy - rangeToCopy.getFirstRow(), sourceCopy);
            } while ((nextCellIndexInRowToCopy += widthToCopyMinus1 + 1) <= tileDestRange.getLastColumn());
        } while ((nextRowIndexToCopy += heightToCopyMinus1 + 1) <= tileDestRange.getLastRow());
        int tempCopyIndex = this.sourceSheet.getWorkbook().getSheetIndex(sourceCopy);
        this.sourceSheet.getWorkbook().removeSheetAt(tempCopyIndex);
    }

    private void copyRange(CellRangeAddress sourceRange, int deltaX, int deltaY, Sheet sourceClone) {
        if (deltaX != 0) {
            this.horizontalFormulaShifter = FormulaShifter.createForColumnCopy(this.sourceSheet.getWorkbook().getSheetIndex(this.sourceSheet), this.sourceSheet.getSheetName(), sourceRange.getFirstColumn(), sourceRange.getLastColumn(), deltaX, this.sourceSheet.getWorkbook().getSpreadsheetVersion());
        }
        if (deltaY != 0) {
            this.verticalFormulaShifter = FormulaShifter.createForRowCopy(this.sourceSheet.getWorkbook().getSheetIndex(this.sourceSheet), this.sourceSheet.getSheetName(), sourceRange.getFirstRow(), sourceRange.getLastRow(), deltaY, this.sourceSheet.getWorkbook().getSpreadsheetVersion());
        }
        for (int rowNo = sourceRange.getFirstRow(); rowNo <= sourceRange.getLastRow(); ++rowNo) {
            Row sourceRow = sourceClone.getRow(rowNo);
            for (int columnIndex = sourceRange.getFirstColumn(); columnIndex <= sourceRange.getLastColumn(); ++columnIndex) {
                Cell newCell;
                Cell sourceCell = sourceRow.getCell(columnIndex);
                if (sourceCell == null) continue;
                Row destRow = this.destSheet.getRow(rowNo + deltaY);
                if (destRow == null) {
                    destRow = this.destSheet.createRow(rowNo + deltaY);
                }
                if ((newCell = destRow.getCell(columnIndex + deltaX)) == null) {
                    newCell = destRow.createCell(columnIndex + deltaX);
                }
                RangeCopier.cloneCellContent(sourceCell, newCell, null);
                if (newCell.getCellType() != CellType.FORMULA) continue;
                this.adjustCellReferencesInsideFormula(newCell, this.destSheet, deltaX, deltaY);
            }
        }
    }

    protected abstract void adjustCellReferencesInsideFormula(Cell var1, Sheet var2, int var3, int var4);

    protected boolean adjustInBothDirections(Ptg[] ptgs, int sheetIndex, int deltaX, int deltaY) {
        boolean adjustSucceeded = true;
        if (deltaY != 0) {
            adjustSucceeded = this.verticalFormulaShifter.adjustFormula(ptgs, sheetIndex);
        }
        if (deltaX != 0) {
            adjustSucceeded = adjustSucceeded && this.horizontalFormulaShifter.adjustFormula(ptgs, sheetIndex);
        }
        return adjustSucceeded;
    }

    public static void cloneCellContent(Cell srcCell, Cell destCell, Map<Integer, CellStyle> styleMap) {
        if (styleMap != null) {
            if (srcCell.getSheet().getWorkbook() == destCell.getSheet().getWorkbook()) {
                destCell.setCellStyle(srcCell.getCellStyle());
            } else {
                int stHashCode = srcCell.getCellStyle().hashCode();
                CellStyle newCellStyle = styleMap.get(stHashCode);
                if (newCellStyle == null) {
                    newCellStyle = destCell.getSheet().getWorkbook().createCellStyle();
                    newCellStyle.cloneStyleFrom(srcCell.getCellStyle());
                    styleMap.put(stHashCode, newCellStyle);
                }
                destCell.setCellStyle(newCellStyle);
            }
        }
        switch (srcCell.getCellType()) {
            case STRING: {
                destCell.setCellValue(srcCell.getStringCellValue());
                break;
            }
            case NUMERIC: {
                destCell.setCellValue(srcCell.getNumericCellValue());
                break;
            }
            case BLANK: {
                destCell.setBlank();
                break;
            }
            case BOOLEAN: {
                destCell.setCellValue(srcCell.getBooleanCellValue());
                break;
            }
            case ERROR: {
                destCell.setCellErrorValue(srcCell.getErrorCellValue());
                break;
            }
            case FORMULA: {
                String oldFormula = srcCell.getCellFormula();
                destCell.setCellFormula(oldFormula);
                break;
            }
        }
    }
}

