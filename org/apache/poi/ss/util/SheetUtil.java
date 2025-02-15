/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.ss.util;

import java.awt.font.FontRenderContext;
import java.awt.font.TextAttribute;
import java.awt.font.TextLayout;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.text.AttributedString;
import java.util.Locale;
import java.util.Map;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.CellValue;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.RichTextString;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.util.Internal;
import org.apache.poi.util.Removal;

public class SheetUtil {
    private static final char defaultChar = '0';
    private static final double fontHeightMultiple = 2.0;
    private static final FormulaEvaluator dummyEvaluator = new FormulaEvaluator(){

        @Override
        public void clearAllCachedResultValues() {
        }

        @Override
        public void notifySetFormula(Cell cell) {
        }

        @Override
        public void notifyDeleteCell(Cell cell) {
        }

        @Override
        public void notifyUpdateCell(Cell cell) {
        }

        @Override
        public CellValue evaluate(Cell cell) {
            return null;
        }

        @Override
        public Cell evaluateInCell(Cell cell) {
            return null;
        }

        @Override
        public void setupReferencedWorkbooks(Map<String, FormulaEvaluator> workbooks) {
        }

        @Override
        public void setDebugEvaluationOutputForNextEval(boolean value) {
        }

        @Override
        public void setIgnoreMissingWorkbooks(boolean ignore) {
        }

        @Override
        public void evaluateAll() {
        }

        @Override
        public CellType evaluateFormulaCell(Cell cell) {
            return cell.getCachedFormulaResultType();
        }

        @Override
        @Deprecated
        @Removal(version="4.2")
        @Internal(since="POI 3.15 beta 3")
        public CellType evaluateFormulaCellEnum(Cell cell) {
            return this.evaluateFormulaCell(cell);
        }
    };
    private static final FontRenderContext fontRenderContext = new FontRenderContext(null, true, true);

    public static double getCellWidth(Cell cell, int defaultCharWidth, DataFormatter formatter, boolean useMergedCells) {
        Sheet sheet = cell.getSheet();
        Workbook wb = sheet.getWorkbook();
        Row row = cell.getRow();
        int column = cell.getColumnIndex();
        int colspan = 1;
        for (CellRangeAddress region : sheet.getMergedRegions()) {
            if (!region.isInRange(row.getRowNum(), column)) continue;
            if (!useMergedCells) {
                return -1.0;
            }
            cell = row.getCell(region.getFirstColumn());
            colspan = 1 + region.getLastColumn() - region.getFirstColumn();
        }
        CellStyle style = cell.getCellStyle();
        CellType cellType = cell.getCellType();
        if (cellType == CellType.FORMULA) {
            cellType = cell.getCachedFormulaResultType();
        }
        Font font = wb.getFontAt(style.getFontIndexAsInt());
        double width = -1.0;
        if (cellType == CellType.STRING) {
            String[] lines;
            RichTextString rt = cell.getRichStringCellValue();
            for (String line : lines = rt.getString().split("\\n")) {
                String txt = line + '0';
                AttributedString str = new AttributedString(txt);
                SheetUtil.copyAttributes(font, str, 0, txt.length());
                width = SheetUtil.getCellWidth(defaultCharWidth, colspan, style, width, str);
            }
        } else {
            String sval = null;
            if (cellType == CellType.NUMERIC) {
                try {
                    sval = formatter.formatCellValue(cell, dummyEvaluator);
                } catch (Exception e) {
                    sval = String.valueOf(cell.getNumericCellValue());
                }
            } else if (cellType == CellType.BOOLEAN) {
                sval = String.valueOf(cell.getBooleanCellValue()).toUpperCase(Locale.ROOT);
            }
            if (sval != null) {
                String txt = sval + '0';
                AttributedString str = new AttributedString(txt);
                SheetUtil.copyAttributes(font, str, 0, txt.length());
                width = SheetUtil.getCellWidth(defaultCharWidth, colspan, style, width, str);
            }
        }
        return width;
    }

    private static double getCellWidth(int defaultCharWidth, int colspan, CellStyle style, double minWidth, AttributedString str) {
        Rectangle2D bounds;
        TextLayout layout = new TextLayout(str.getIterator(), fontRenderContext);
        if (style.getRotation() != 0) {
            AffineTransform trans = new AffineTransform();
            trans.concatenate(AffineTransform.getRotateInstance((double)style.getRotation() * 2.0 * Math.PI / 360.0));
            trans.concatenate(AffineTransform.getScaleInstance(1.0, 2.0));
            bounds = layout.getOutline(trans).getBounds();
        } else {
            bounds = layout.getBounds();
        }
        double frameWidth = bounds.getX() + bounds.getWidth();
        return Math.max(minWidth, frameWidth / (double)colspan / (double)defaultCharWidth + (double)style.getIndention());
    }

    public static double getColumnWidth(Sheet sheet, int column, boolean useMergedCells) {
        return SheetUtil.getColumnWidth(sheet, column, useMergedCells, sheet.getFirstRowNum(), sheet.getLastRowNum());
    }

    public static double getColumnWidth(Sheet sheet, int column, boolean useMergedCells, int firstRow, int lastRow) {
        DataFormatter formatter = new DataFormatter();
        int defaultCharWidth = SheetUtil.getDefaultCharWidth(sheet.getWorkbook());
        double width = -1.0;
        for (int rowIdx = firstRow; rowIdx <= lastRow; ++rowIdx) {
            Row row = sheet.getRow(rowIdx);
            if (row == null) continue;
            double cellWidth = SheetUtil.getColumnWidthForRow(row, column, defaultCharWidth, formatter, useMergedCells);
            width = Math.max(width, cellWidth);
        }
        return width;
    }

    @Internal
    public static int getDefaultCharWidth(Workbook wb) {
        Font defaultFont = wb.getFontAt(0);
        AttributedString str = new AttributedString(String.valueOf('0'));
        SheetUtil.copyAttributes(defaultFont, str, 0, 1);
        TextLayout layout = new TextLayout(str.getIterator(), fontRenderContext);
        return (int)layout.getAdvance();
    }

    private static double getColumnWidthForRow(Row row, int column, int defaultCharWidth, DataFormatter formatter, boolean useMergedCells) {
        if (row == null) {
            return -1.0;
        }
        Cell cell = row.getCell(column);
        if (cell == null) {
            return -1.0;
        }
        return SheetUtil.getCellWidth(cell, defaultCharWidth, formatter, useMergedCells);
    }

    public static boolean canComputeColumnWidth(Font font) {
        AttributedString str = new AttributedString("1w");
        SheetUtil.copyAttributes(font, str, 0, "1w".length());
        TextLayout layout = new TextLayout(str.getIterator(), fontRenderContext);
        return layout.getBounds().getWidth() > 0.0;
    }

    private static void copyAttributes(Font font, AttributedString str, int startIdx, int endIdx) {
        str.addAttribute(TextAttribute.FAMILY, font.getFontName(), startIdx, endIdx);
        str.addAttribute(TextAttribute.SIZE, Float.valueOf(font.getFontHeightInPoints()));
        if (font.getBold()) {
            str.addAttribute(TextAttribute.WEIGHT, TextAttribute.WEIGHT_BOLD, startIdx, endIdx);
        }
        if (font.getItalic()) {
            str.addAttribute(TextAttribute.POSTURE, TextAttribute.POSTURE_OBLIQUE, startIdx, endIdx);
        }
        if (font.getUnderline() == 1) {
            str.addAttribute(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON, startIdx, endIdx);
        }
    }

    public static Cell getCell(Sheet sheet, int rowIx, int colIx) {
        Row r = sheet.getRow(rowIx);
        if (r != null) {
            return r.getCell(colIx);
        }
        return null;
    }

    public static Cell getCellWithMerges(Sheet sheet, int rowIx, int colIx) {
        Cell c = SheetUtil.getCell(sheet, rowIx, colIx);
        if (c != null) {
            return c;
        }
        for (CellRangeAddress mergedRegion : sheet.getMergedRegions()) {
            Row r;
            if (!mergedRegion.isInRange(rowIx, colIx) || (r = sheet.getRow(mergedRegion.getFirstRow())) == null) continue;
            return r.getCell(mergedRegion.getFirstColumn());
        }
        return null;
    }
}

