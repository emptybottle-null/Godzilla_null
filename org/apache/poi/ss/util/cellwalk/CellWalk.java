/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.ss.util.cellwalk;

import org.apache.commons.math3.util.ArithmeticUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.cellwalk.CellHandler;
import org.apache.poi.ss.util.cellwalk.CellWalkContext;

public class CellWalk {
    private Sheet sheet;
    private CellRangeAddress range;
    private boolean traverseEmptyCells;

    public CellWalk(Sheet sheet, CellRangeAddress range) {
        this.sheet = sheet;
        this.range = range;
        this.traverseEmptyCells = false;
    }

    public boolean isTraverseEmptyCells() {
        return this.traverseEmptyCells;
    }

    public void setTraverseEmptyCells(boolean traverseEmptyCells) {
        this.traverseEmptyCells = traverseEmptyCells;
    }

    public void traverse(CellHandler handler) {
        int firstRow = this.range.getFirstRow();
        int lastRow = this.range.getLastRow();
        int firstColumn = this.range.getFirstColumn();
        int lastColumn = this.range.getLastColumn();
        int width = lastColumn - firstColumn + 1;
        SimpleCellWalkContext ctx = new SimpleCellWalkContext();
        Row currentRow = null;
        Cell currentCell = null;
        ctx.rowNumber = firstRow;
        while (ctx.rowNumber <= lastRow) {
            currentRow = this.sheet.getRow(ctx.rowNumber);
            if (currentRow != null) {
                ctx.colNumber = firstColumn;
                while (ctx.colNumber <= lastColumn) {
                    currentCell = currentRow.getCell(ctx.colNumber);
                    if (currentCell != null && (!this.isEmpty(currentCell) || this.traverseEmptyCells)) {
                        long rowSize = ArithmeticUtils.mulAndCheck((long)ArithmeticUtils.subAndCheck(ctx.rowNumber, firstRow), (long)width);
                        ctx.ordinalNumber = ArithmeticUtils.addAndCheck(rowSize, (long)(ctx.colNumber - firstColumn + 1));
                        handler.onCell(currentCell, ctx);
                    }
                    ++ctx.colNumber;
                }
            }
            ++ctx.rowNumber;
        }
    }

    private boolean isEmpty(Cell cell) {
        return cell.getCellType() == CellType.BLANK;
    }

    private static class SimpleCellWalkContext
    implements CellWalkContext {
        public long ordinalNumber;
        public int rowNumber;
        public int colNumber;

        private SimpleCellWalkContext() {
        }

        @Override
        public long getOrdinalNumber() {
            return this.ordinalNumber;
        }

        @Override
        public int getRowNumber() {
            return this.rowNumber;
        }

        @Override
        public int getColumnNumber() {
            return this.colNumber;
        }
    }
}

