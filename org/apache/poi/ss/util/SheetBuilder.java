/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.ss.util;

import java.util.Calendar;
import java.util.Date;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

public class SheetBuilder {
    private final Workbook workbook;
    private final Object[][] cells;
    private boolean shouldCreateEmptyCells;
    private String sheetName;

    public SheetBuilder(Workbook workbook, Object[][] cells) {
        this.workbook = workbook;
        this.cells = (Object[][])cells.clone();
    }

    public boolean getCreateEmptyCells() {
        return this.shouldCreateEmptyCells;
    }

    public SheetBuilder setCreateEmptyCells(boolean shouldCreateEmptyCells) {
        this.shouldCreateEmptyCells = shouldCreateEmptyCells;
        return this;
    }

    public SheetBuilder setSheetName(String sheetName) {
        this.sheetName = sheetName;
        return this;
    }

    public Sheet build() {
        Sheet sheet = this.sheetName == null ? this.workbook.createSheet() : this.workbook.createSheet(this.sheetName);
        for (int rowIndex = 0; rowIndex < this.cells.length; ++rowIndex) {
            Object[] rowArray = this.cells[rowIndex];
            Row currentRow = sheet.createRow(rowIndex);
            for (int cellIndex = 0; cellIndex < rowArray.length; ++cellIndex) {
                Object cellValue = rowArray[cellIndex];
                if (cellValue == null && !this.shouldCreateEmptyCells) continue;
                Cell currentCell = currentRow.createCell(cellIndex);
                this.setCellValue(currentCell, cellValue);
            }
        }
        return sheet;
    }

    private void setCellValue(Cell cell, Object value) {
        if (value == null || cell == null) {
            return;
        }
        if (value instanceof Number) {
            double doubleValue = ((Number)value).doubleValue();
            cell.setCellValue(doubleValue);
        } else if (value instanceof Date) {
            cell.setCellValue((Date)value);
        } else if (value instanceof Calendar) {
            cell.setCellValue((Calendar)value);
        } else if (this.isFormulaDefinition(value)) {
            cell.setCellFormula(this.getFormula(value));
        } else {
            cell.setCellValue(value.toString());
        }
    }

    private boolean isFormulaDefinition(Object obj) {
        if (obj instanceof String) {
            String str = (String)obj;
            return str.length() >= 2 && str.charAt(0) == '=';
        }
        return false;
    }

    private String getFormula(Object obj) {
        return ((String)obj).substring(1);
    }
}

