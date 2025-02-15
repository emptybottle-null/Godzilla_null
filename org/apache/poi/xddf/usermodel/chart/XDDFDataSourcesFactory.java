/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.xddf.usermodel.chart;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.CellValue;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xddf.usermodel.chart.XDDFCategoryDataSource;
import org.apache.poi.xddf.usermodel.chart.XDDFDataSource;
import org.apache.poi.xddf.usermodel.chart.XDDFNumericalDataSource;
import org.apache.poi.xssf.usermodel.XSSFFormulaEvaluator;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTAxDataSource;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTNumData;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTNumDataSource;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTStrData;

public class XDDFDataSourcesFactory {
    private XDDFDataSourcesFactory() {
    }

    public static XDDFCategoryDataSource fromDataSource(final CTAxDataSource categoryDS) {
        if (categoryDS.getStrRef() == null) {
            return new XDDFCategoryDataSource(){
                private CTNumData category;
                {
                    this.category = (CTNumData)categoryDS.getNumRef().getNumCache().copy();
                }

                @Override
                public boolean isCellRange() {
                    return true;
                }

                @Override
                public boolean isNumeric() {
                    return true;
                }

                @Override
                public String getFormula() {
                    return categoryDS.getNumRef().getF();
                }

                @Override
                public int getPointCount() {
                    return (int)this.category.getPtCount().getVal();
                }

                @Override
                public String getPointAt(int index) {
                    return this.category.getPtArray(index).getV();
                }
            };
        }
        return new XDDFCategoryDataSource(){
            private CTStrData category;
            {
                this.category = (CTStrData)categoryDS.getStrRef().getStrCache().copy();
            }

            @Override
            public boolean isCellRange() {
                return true;
            }

            @Override
            public String getFormula() {
                return categoryDS.getStrRef().getF();
            }

            @Override
            public int getPointCount() {
                return (int)this.category.getPtCount().getVal();
            }

            @Override
            public String getPointAt(int index) {
                return this.category.getPtArray(index).getV();
            }
        };
    }

    public static XDDFNumericalDataSource<Double> fromDataSource(final CTNumDataSource valuesDS) {
        return new XDDFNumericalDataSource<Double>(){
            private CTNumData values;
            private String formatCode;
            {
                this.values = (CTNumData)valuesDS.getNumRef().getNumCache().copy();
                this.formatCode = this.values.isSetFormatCode() ? this.values.getFormatCode() : null;
            }

            @Override
            public String getFormula() {
                return valuesDS.getNumRef().getF();
            }

            @Override
            public String getFormatCode() {
                return this.formatCode;
            }

            @Override
            public void setFormatCode(String formatCode) {
                this.formatCode = formatCode;
            }

            @Override
            public boolean isCellRange() {
                return true;
            }

            @Override
            public boolean isNumeric() {
                return true;
            }

            @Override
            public boolean isReference() {
                return true;
            }

            @Override
            public int getPointCount() {
                return (int)this.values.getPtCount().getVal();
            }

            @Override
            public Double getPointAt(int index) {
                return Double.valueOf(this.values.getPtArray(index).getV());
            }

            @Override
            public String getDataRangeReference() {
                return valuesDS.getNumRef().getF();
            }

            @Override
            public int getColIndex() {
                return 0;
            }
        };
    }

    public static <T extends Number> XDDFNumericalDataSource<T> fromArray(T[] elements) {
        return new LiteralNumericalArrayDataSource(elements);
    }

    public static XDDFCategoryDataSource fromArray(String[] elements) {
        return new LiteralStringArrayDataSource(elements);
    }

    public static <T extends Number> XDDFNumericalDataSource<T> fromArray(T[] elements, String dataRange) {
        return new NumericalArrayDataSource(elements, dataRange);
    }

    public static XDDFCategoryDataSource fromArray(String[] elements, String dataRange) {
        return new StringArrayDataSource(elements, dataRange);
    }

    public static <T extends Number> XDDFNumericalDataSource<T> fromArray(T[] elements, String dataRange, int col) {
        return new NumericalArrayDataSource(elements, dataRange, col);
    }

    public static XDDFCategoryDataSource fromArray(String[] elements, String dataRange, int col) {
        return new StringArrayDataSource(elements, dataRange, col);
    }

    public static XDDFNumericalDataSource<Double> fromNumericCellRange(XSSFSheet sheet, CellRangeAddress cellRangeAddress) {
        return new NumericalCellRangeDataSource(sheet, cellRangeAddress);
    }

    public static XDDFCategoryDataSource fromStringCellRange(XSSFSheet sheet, CellRangeAddress cellRangeAddress) {
        return new StringCellRangeDataSource(sheet, cellRangeAddress);
    }

    private static class StringCellRangeDataSource
    extends AbstractCellRangeDataSource<String>
    implements XDDFCategoryDataSource {
        protected StringCellRangeDataSource(XSSFSheet sheet, CellRangeAddress cellRangeAddress) {
            super(sheet, cellRangeAddress);
        }

        @Override
        public String getFormula() {
            return this.getDataRangeReference();
        }

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
    }

    private static class NumericalCellRangeDataSource
    extends AbstractCellRangeDataSource<Double>
    implements XDDFNumericalDataSource<Double> {
        private String formatCode;

        protected NumericalCellRangeDataSource(XSSFSheet sheet, CellRangeAddress cellRangeAddress) {
            super(sheet, cellRangeAddress);
        }

        @Override
        public String getFormula() {
            return this.getDataRangeReference();
        }

        @Override
        public String getFormatCode() {
            return this.formatCode;
        }

        @Override
        public void setFormatCode(String formatCode) {
            this.formatCode = formatCode;
        }

        @Override
        public Double getPointAt(int index) {
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
    }

    private static abstract class AbstractCellRangeDataSource<T>
    implements XDDFDataSource<T> {
        private final XSSFSheet sheet;
        private final CellRangeAddress cellRangeAddress;
        private final int numOfCells;
        private XSSFFormulaEvaluator evaluator;

        protected AbstractCellRangeDataSource(XSSFSheet sheet, CellRangeAddress cellRangeAddress) {
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
        public boolean isCellRange() {
            return true;
        }

        @Override
        public boolean isReference() {
            return true;
        }

        @Override
        public int getColIndex() {
            return this.cellRangeAddress.getFirstColumn();
        }

        @Override
        public String getDataRangeReference() {
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
            XSSFRow row = this.sheet.getRow(rowIndex);
            return row == null ? null : this.evaluator.evaluate(row.getCell(cellIndex));
        }
    }

    private static class LiteralStringArrayDataSource
    extends StringArrayDataSource {
        public LiteralStringArrayDataSource(String[] elements) {
            super(elements, (String)null, 0);
        }

        @Override
        public boolean isLiteral() {
            return true;
        }
    }

    private static class LiteralNumericalArrayDataSource<T extends Number>
    extends NumericalArrayDataSource<T> {
        public LiteralNumericalArrayDataSource(T[] elements) {
            super(elements, null, 0);
        }

        @Override
        public boolean isLiteral() {
            return true;
        }
    }

    private static class StringArrayDataSource
    extends AbstractArrayDataSource<String>
    implements XDDFCategoryDataSource {
        public StringArrayDataSource(String[] elements, String dataRange) {
            super(elements, dataRange);
        }

        public StringArrayDataSource(String[] elements, String dataRange, int col) {
            super(elements, dataRange, col);
        }

        @Override
        public String getFormula() {
            return this.getDataRangeReference();
        }
    }

    private static class NumericalArrayDataSource<T extends Number>
    extends AbstractArrayDataSource<T>
    implements XDDFNumericalDataSource<T> {
        private String formatCode;

        public NumericalArrayDataSource(T[] elements, String dataRange) {
            super(elements, dataRange);
        }

        public NumericalArrayDataSource(T[] elements, String dataRange, int col) {
            super(elements, dataRange, col);
        }

        @Override
        public String getFormula() {
            return this.getDataRangeReference();
        }

        @Override
        public String getFormatCode() {
            return this.formatCode;
        }

        @Override
        public void setFormatCode(String formatCode) {
            this.formatCode = formatCode;
        }
    }

    private static abstract class AbstractArrayDataSource<T>
    implements XDDFDataSource<T> {
        private final T[] elements;
        private final String dataRange;
        private int col = 0;

        public AbstractArrayDataSource(T[] elements, String dataRange) {
            this.elements = (Object[])elements.clone();
            this.dataRange = dataRange;
        }

        public AbstractArrayDataSource(T[] elements, String dataRange, int col) {
            this.elements = (Object[])elements.clone();
            this.dataRange = dataRange;
            this.col = col;
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
        public boolean isCellRange() {
            return false;
        }

        @Override
        public boolean isReference() {
            return this.dataRange != null;
        }

        @Override
        public boolean isNumeric() {
            Class<?> arrayComponentType = this.elements.getClass().getComponentType();
            return Number.class.isAssignableFrom(arrayComponentType);
        }

        @Override
        public String getDataRangeReference() {
            if (this.dataRange == null) {
                throw new UnsupportedOperationException("Literal data source can not be expressed by reference.");
            }
            return this.dataRange;
        }

        @Override
        public int getColIndex() {
            return this.col;
        }
    }
}

