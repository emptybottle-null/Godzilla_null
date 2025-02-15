/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.ss.util;

import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.CellUtil;

public final class RegionUtil {
    private RegionUtil() {
    }

    public static void setBorderLeft(BorderStyle border, CellRangeAddress region, Sheet sheet) {
        int rowStart = region.getFirstRow();
        int rowEnd = region.getLastRow();
        int column = region.getFirstColumn();
        CellPropertySetter cps = new CellPropertySetter("borderLeft", border);
        for (int i = rowStart; i <= rowEnd; ++i) {
            cps.setProperty(CellUtil.getRow(i, sheet), column);
        }
    }

    public static void setLeftBorderColor(int color, CellRangeAddress region, Sheet sheet) {
        int rowStart = region.getFirstRow();
        int rowEnd = region.getLastRow();
        int column = region.getFirstColumn();
        CellPropertySetter cps = new CellPropertySetter("leftBorderColor", color);
        for (int i = rowStart; i <= rowEnd; ++i) {
            cps.setProperty(CellUtil.getRow(i, sheet), column);
        }
    }

    public static void setBorderRight(BorderStyle border, CellRangeAddress region, Sheet sheet) {
        int rowStart = region.getFirstRow();
        int rowEnd = region.getLastRow();
        int column = region.getLastColumn();
        CellPropertySetter cps = new CellPropertySetter("borderRight", border);
        for (int i = rowStart; i <= rowEnd; ++i) {
            cps.setProperty(CellUtil.getRow(i, sheet), column);
        }
    }

    public static void setRightBorderColor(int color, CellRangeAddress region, Sheet sheet) {
        int rowStart = region.getFirstRow();
        int rowEnd = region.getLastRow();
        int column = region.getLastColumn();
        CellPropertySetter cps = new CellPropertySetter("rightBorderColor", color);
        for (int i = rowStart; i <= rowEnd; ++i) {
            cps.setProperty(CellUtil.getRow(i, sheet), column);
        }
    }

    public static void setBorderBottom(BorderStyle border, CellRangeAddress region, Sheet sheet) {
        int colStart = region.getFirstColumn();
        int colEnd = region.getLastColumn();
        int rowIndex = region.getLastRow();
        CellPropertySetter cps = new CellPropertySetter("borderBottom", border);
        Row row = CellUtil.getRow(rowIndex, sheet);
        for (int i = colStart; i <= colEnd; ++i) {
            cps.setProperty(row, i);
        }
    }

    public static void setBottomBorderColor(int color, CellRangeAddress region, Sheet sheet) {
        int colStart = region.getFirstColumn();
        int colEnd = region.getLastColumn();
        int rowIndex = region.getLastRow();
        CellPropertySetter cps = new CellPropertySetter("bottomBorderColor", color);
        Row row = CellUtil.getRow(rowIndex, sheet);
        for (int i = colStart; i <= colEnd; ++i) {
            cps.setProperty(row, i);
        }
    }

    public static void setBorderTop(BorderStyle border, CellRangeAddress region, Sheet sheet) {
        int colStart = region.getFirstColumn();
        int colEnd = region.getLastColumn();
        int rowIndex = region.getFirstRow();
        CellPropertySetter cps = new CellPropertySetter("borderTop", border);
        Row row = CellUtil.getRow(rowIndex, sheet);
        for (int i = colStart; i <= colEnd; ++i) {
            cps.setProperty(row, i);
        }
    }

    public static void setTopBorderColor(int color, CellRangeAddress region, Sheet sheet) {
        int colStart = region.getFirstColumn();
        int colEnd = region.getLastColumn();
        int rowIndex = region.getFirstRow();
        CellPropertySetter cps = new CellPropertySetter("topBorderColor", color);
        Row row = CellUtil.getRow(rowIndex, sheet);
        for (int i = colStart; i <= colEnd; ++i) {
            cps.setProperty(row, i);
        }
    }

    private static final class CellPropertySetter {
        private final String _propertyName;
        private final Object _propertyValue;

        public CellPropertySetter(String propertyName, int value) {
            this._propertyName = propertyName;
            this._propertyValue = value;
        }

        public CellPropertySetter(String propertyName, BorderStyle value) {
            this._propertyName = propertyName;
            this._propertyValue = value;
        }

        public void setProperty(Row row, int column) {
            Cell cell = CellUtil.getCell(row, column);
            CellUtil.setCellStyleProperty(cell, this._propertyName, this._propertyValue);
        }
    }
}

