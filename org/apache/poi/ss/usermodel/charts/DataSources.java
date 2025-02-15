/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.ss.usermodel.charts;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.CellValue;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.charts.ChartDataSource;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.util.Removal;

@Deprecated
@Removal(version="4.2")
public class DataSources {
    private DataSources() {
    }

    public static <T> ChartDataSource<T> fromArray(T[] elements) {
        return new ArrayDataSource<T>(elements);
    }

    public static ChartDataSource<Number> fromNumericCellRange(Sheet sheet, CellRangeAddress cellRangeAddress) {
        return new AbstractCellRangeDataSource<Number>(sheet, cellRangeAddress){

            @Override
            public Number getPointAt(int index) {
                CellValue cellValue = this.getCellValueAt(index);
                if (cellValue != null && cellValue.getCellType() == CellType.NUMERIC) {
                    return cellValue.getNumberValue();
                }
                return null;
            }

            @Override
            public boolean isNumeric() {
                return true;
            }
        };
    }

    public static ChartDataSource<String> fromStringCellRange(Sheet sheet, CellRangeAddress cellRangeAddress) {
        return new AbstractCellRangeDataSource<String>(sheet, cellRangeAddress){

            @Override
            public String getPointAt(int index) {
                CellValue cellValue = this.getCellValueAt(index);
                if (cellValue != null && cellValue.getCellType() == CellType.STRING) {
                    return cellValue.getStringValue();
                }
                return null;
            }

            @Override
            public boolean isNumeric() {
                return false;
            }
        };
    }

    private static abstract class AbstractCellRangeDataSource<T>
    implements ChartDataSource<T> {
        private final Sheet sheet;
        private final CellRangeAddress cellRangeAddress;
        private final int numOfCells;
        private FormulaEvaluator evaluator;

        protected AbstractCellRangeDataSource(Sheet sheet, CellRangeAddress cellRangeAddress) {
            this.sheet = sheet;
            this.cellRangeAddress = cellRangeAddress.copy();
            this.numOfCells = this.cellRangeAddress.getNumberOfCells();
            this.evaluator = sheet.getWorkbook().getCreationHelper().createFormulaEvaluator();
        }

        @Override
        public int getPointCount() {
            return this.numOfCells;
        }

        @Override
        public boolean isReference() {
            return true;
        }

        @Override
        public String getFormulaString() {
            return this.cellRangeAddress.formatAsString(this.sheet.getSheetName(), true);
        }

        protected CellValue getCellValueAt(int index) {
            if (index < 0 || index >= this.numOfCells) {
                throw new IndexOutOfBoundsException("Index must be between 0 and " + (this.numOfCells - 1) + " (inclusive), given: " + index);
            }
            int firstRow = this.cellRangeAddress.getFirstRow();
            int firstCol = this.cellRangeAddress.getFirstColumn();
            int lastCol = this.cellRangeAddress.getLastColumn();
            int width = lastCol - firstCol + 1;
            int rowIndex = firstRow + index / width;
            int cellIndex = firstCol + index % width;
            Row row = this.sheet.getRow(rowIndex);
            return row == null ? null : this.evaluator.evaluate(row.getCell(cellIndex));
        }
    }

    private static class ArrayDataSource<T>
    implements ChartDataSource<T> {
        private final T[] elements;

        public ArrayDataSource(T[] elements) {
            this.elements = (Object[])elements.clone();
        }

        @Override
        public int getPointCount() {
            return this.elements.length;
        }

        @Override
        public T getPointAt(int index) {
            return this.elements[index];
        }

        @Override
        public boolean isReference() {
            return false;
        }

        @Override
        public boolean isNumeric() {
            Class<?> arrayComponentType = this.elements.getClass().getComponentType();
            return Number.class.isAssignableFrom(arrayComponentType);
        }

        @Override
        public String getFormulaString() {
            throw new UnsupportedOperationException("Literal data source can not be expressed by reference.");
        }
    }
}

