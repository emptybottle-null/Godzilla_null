/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.ss.util;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import org.apache.poi.ss.SpreadsheetVersion;
import org.apache.poi.ss.usermodel.BorderExtent;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellAddress;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.CellUtil;

public final class PropertyTemplate {
    private Map<CellAddress, Map<String, Object>> _propertyTemplate = new HashMap<CellAddress, Map<String, Object>>();

    public PropertyTemplate() {
    }

    public PropertyTemplate(PropertyTemplate template) {
        this();
        for (Map.Entry<CellAddress, Map<String, Object>> entry : template.getTemplate().entrySet()) {
            this._propertyTemplate.put(new CellAddress(entry.getKey()), PropertyTemplate.cloneCellProperties(entry.getValue()));
        }
    }

    private Map<CellAddress, Map<String, Object>> getTemplate() {
        return this._propertyTemplate;
    }

    private static Map<String, Object> cloneCellProperties(Map<String, Object> properties) {
        HashMap<String, Object> newProperties = new HashMap<String, Object>();
        for (Map.Entry<String, Object> entry : properties.entrySet()) {
            newProperties.put(entry.getKey(), entry.getValue());
        }
        return newProperties;
    }

    public void drawBorders(CellRangeAddress range, BorderStyle borderType, BorderExtent extent) {
        switch (extent) {
            case NONE: {
                this.removeBorders(range);
                break;
            }
            case ALL: {
                this.drawHorizontalBorders(range, borderType, BorderExtent.ALL);
                this.drawVerticalBorders(range, borderType, BorderExtent.ALL);
                break;
            }
            case INSIDE: {
                this.drawHorizontalBorders(range, borderType, BorderExtent.INSIDE);
                this.drawVerticalBorders(range, borderType, BorderExtent.INSIDE);
                break;
            }
            case OUTSIDE: {
                this.drawOutsideBorders(range, borderType, BorderExtent.ALL);
                break;
            }
            case TOP: {
                this.drawTopBorder(range, borderType);
                break;
            }
            case BOTTOM: {
                this.drawBottomBorder(range, borderType);
                break;
            }
            case LEFT: {
                this.drawLeftBorder(range, borderType);
                break;
            }
            case RIGHT: {
                this.drawRightBorder(range, borderType);
                break;
            }
            case HORIZONTAL: {
                this.drawHorizontalBorders(range, borderType, BorderExtent.ALL);
                break;
            }
            case INSIDE_HORIZONTAL: {
                this.drawHorizontalBorders(range, borderType, BorderExtent.INSIDE);
                break;
            }
            case OUTSIDE_HORIZONTAL: {
                this.drawOutsideBorders(range, borderType, BorderExtent.HORIZONTAL);
                break;
            }
            case VERTICAL: {
                this.drawVerticalBorders(range, borderType, BorderExtent.ALL);
                break;
            }
            case INSIDE_VERTICAL: {
                this.drawVerticalBorders(range, borderType, BorderExtent.INSIDE);
                break;
            }
            case OUTSIDE_VERTICAL: {
                this.drawOutsideBorders(range, borderType, BorderExtent.VERTICAL);
            }
        }
    }

    public void drawBorders(CellRangeAddress range, BorderStyle borderType, short color, BorderExtent extent) {
        this.drawBorders(range, borderType, extent);
        if (borderType != BorderStyle.NONE) {
            this.drawBorderColors(range, color, extent);
        }
    }

    private void drawTopBorder(CellRangeAddress range, BorderStyle borderType) {
        int row = range.getFirstRow();
        int firstCol = range.getFirstColumn();
        int lastCol = range.getLastColumn();
        for (int i = firstCol; i <= lastCol; ++i) {
            this.addProperty(row, i, "borderTop", (Object)borderType);
            if (borderType != BorderStyle.NONE || row <= 0) continue;
            this.addProperty(row - 1, i, "borderBottom", (Object)borderType);
        }
    }

    private void drawBottomBorder(CellRangeAddress range, BorderStyle borderType) {
        int row = range.getLastRow();
        int firstCol = range.getFirstColumn();
        int lastCol = range.getLastColumn();
        for (int i = firstCol; i <= lastCol; ++i) {
            this.addProperty(row, i, "borderBottom", (Object)borderType);
            if (borderType != BorderStyle.NONE || row >= SpreadsheetVersion.EXCEL2007.getMaxRows() - 1) continue;
            this.addProperty(row + 1, i, "borderTop", (Object)borderType);
        }
    }

    private void drawLeftBorder(CellRangeAddress range, BorderStyle borderType) {
        int firstRow = range.getFirstRow();
        int lastRow = range.getLastRow();
        int col = range.getFirstColumn();
        for (int i = firstRow; i <= lastRow; ++i) {
            this.addProperty(i, col, "borderLeft", (Object)borderType);
            if (borderType != BorderStyle.NONE || col <= 0) continue;
            this.addProperty(i, col - 1, "borderRight", (Object)borderType);
        }
    }

    private void drawRightBorder(CellRangeAddress range, BorderStyle borderType) {
        int firstRow = range.getFirstRow();
        int lastRow = range.getLastRow();
        int col = range.getLastColumn();
        for (int i = firstRow; i <= lastRow; ++i) {
            this.addProperty(i, col, "borderRight", (Object)borderType);
            if (borderType != BorderStyle.NONE || col >= SpreadsheetVersion.EXCEL2007.getMaxColumns() - 1) continue;
            this.addProperty(i, col + 1, "borderLeft", (Object)borderType);
        }
    }

    private void drawOutsideBorders(CellRangeAddress range, BorderStyle borderType, BorderExtent extent) {
        switch (extent) {
            case ALL: 
            case HORIZONTAL: 
            case VERTICAL: {
                if (extent == BorderExtent.ALL || extent == BorderExtent.HORIZONTAL) {
                    this.drawTopBorder(range, borderType);
                    this.drawBottomBorder(range, borderType);
                }
                if (extent != BorderExtent.ALL && extent != BorderExtent.VERTICAL) break;
                this.drawLeftBorder(range, borderType);
                this.drawRightBorder(range, borderType);
                break;
            }
            default: {
                throw new IllegalArgumentException("Unsupported PropertyTemplate.Extent, valid Extents are ALL, HORIZONTAL, and VERTICAL");
            }
        }
    }

    private void drawHorizontalBorders(CellRangeAddress range, BorderStyle borderType, BorderExtent extent) {
        switch (extent) {
            case ALL: 
            case INSIDE: {
                int firstRow = range.getFirstRow();
                int lastRow = range.getLastRow();
                int firstCol = range.getFirstColumn();
                int lastCol = range.getLastColumn();
                for (int i = firstRow; i <= lastRow; ++i) {
                    CellRangeAddress row = new CellRangeAddress(i, i, firstCol, lastCol);
                    if (extent == BorderExtent.ALL || i > firstRow) {
                        this.drawTopBorder(row, borderType);
                    }
                    if (extent != BorderExtent.ALL && i >= lastRow) continue;
                    this.drawBottomBorder(row, borderType);
                }
                break;
            }
            default: {
                throw new IllegalArgumentException("Unsupported PropertyTemplate.Extent, valid Extents are ALL and INSIDE");
            }
        }
    }

    private void drawVerticalBorders(CellRangeAddress range, BorderStyle borderType, BorderExtent extent) {
        switch (extent) {
            case ALL: 
            case INSIDE: {
                int firstRow = range.getFirstRow();
                int lastRow = range.getLastRow();
                int firstCol = range.getFirstColumn();
                int lastCol = range.getLastColumn();
                for (int i = firstCol; i <= lastCol; ++i) {
                    CellRangeAddress row = new CellRangeAddress(firstRow, lastRow, i, i);
                    if (extent == BorderExtent.ALL || i > firstCol) {
                        this.drawLeftBorder(row, borderType);
                    }
                    if (extent != BorderExtent.ALL && i >= lastCol) continue;
                    this.drawRightBorder(row, borderType);
                }
                break;
            }
            default: {
                throw new IllegalArgumentException("Unsupported PropertyTemplate.Extent, valid Extents are ALL and INSIDE");
            }
        }
    }

    private void removeBorders(CellRangeAddress range) {
        HashSet<String> properties = new HashSet<String>();
        properties.add("borderTop");
        properties.add("borderBottom");
        properties.add("borderLeft");
        properties.add("borderRight");
        for (int row = range.getFirstRow(); row <= range.getLastRow(); ++row) {
            for (int col = range.getFirstColumn(); col <= range.getLastColumn(); ++col) {
                this.removeProperties(row, col, properties);
            }
        }
        this.removeBorderColors(range);
    }

    public void applyBorders(Sheet sheet) {
        Workbook wb = sheet.getWorkbook();
        for (Map.Entry<CellAddress, Map<String, Object>> entry : this._propertyTemplate.entrySet()) {
            CellAddress cellAddress = entry.getKey();
            if (cellAddress.getRow() >= wb.getSpreadsheetVersion().getMaxRows() || cellAddress.getColumn() >= wb.getSpreadsheetVersion().getMaxColumns()) continue;
            Map<String, Object> properties = entry.getValue();
            Row row = CellUtil.getRow(cellAddress.getRow(), sheet);
            Cell cell = CellUtil.getCell(row, cellAddress.getColumn());
            CellUtil.setCellStyleProperties(cell, properties);
        }
    }

    public void drawBorderColors(CellRangeAddress range, short color, BorderExtent extent) {
        switch (extent) {
            case NONE: {
                this.removeBorderColors(range);
                break;
            }
            case ALL: {
                this.drawHorizontalBorderColors(range, color, BorderExtent.ALL);
                this.drawVerticalBorderColors(range, color, BorderExtent.ALL);
                break;
            }
            case INSIDE: {
                this.drawHorizontalBorderColors(range, color, BorderExtent.INSIDE);
                this.drawVerticalBorderColors(range, color, BorderExtent.INSIDE);
                break;
            }
            case OUTSIDE: {
                this.drawOutsideBorderColors(range, color, BorderExtent.ALL);
                break;
            }
            case TOP: {
                this.drawTopBorderColor(range, color);
                break;
            }
            case BOTTOM: {
                this.drawBottomBorderColor(range, color);
                break;
            }
            case LEFT: {
                this.drawLeftBorderColor(range, color);
                break;
            }
            case RIGHT: {
                this.drawRightBorderColor(range, color);
                break;
            }
            case HORIZONTAL: {
                this.drawHorizontalBorderColors(range, color, BorderExtent.ALL);
                break;
            }
            case INSIDE_HORIZONTAL: {
                this.drawHorizontalBorderColors(range, color, BorderExtent.INSIDE);
                break;
            }
            case OUTSIDE_HORIZONTAL: {
                this.drawOutsideBorderColors(range, color, BorderExtent.HORIZONTAL);
                break;
            }
            case VERTICAL: {
                this.drawVerticalBorderColors(range, color, BorderExtent.ALL);
                break;
            }
            case INSIDE_VERTICAL: {
                this.drawVerticalBorderColors(range, color, BorderExtent.INSIDE);
                break;
            }
            case OUTSIDE_VERTICAL: {
                this.drawOutsideBorderColors(range, color, BorderExtent.VERTICAL);
            }
        }
    }

    private void drawTopBorderColor(CellRangeAddress range, short color) {
        int row = range.getFirstRow();
        int firstCol = range.getFirstColumn();
        int lastCol = range.getLastColumn();
        for (int i = firstCol; i <= lastCol; ++i) {
            if (this.getBorderStyle(row, i, "borderTop") == BorderStyle.NONE) {
                this.drawTopBorder(new CellRangeAddress(row, row, i, i), BorderStyle.THIN);
            }
            this.addProperty(row, i, "topBorderColor", color);
        }
    }

    private void drawBottomBorderColor(CellRangeAddress range, short color) {
        int row = range.getLastRow();
        int firstCol = range.getFirstColumn();
        int lastCol = range.getLastColumn();
        for (int i = firstCol; i <= lastCol; ++i) {
            if (this.getBorderStyle(row, i, "borderBottom") == BorderStyle.NONE) {
                this.drawBottomBorder(new CellRangeAddress(row, row, i, i), BorderStyle.THIN);
            }
            this.addProperty(row, i, "bottomBorderColor", color);
        }
    }

    private void drawLeftBorderColor(CellRangeAddress range, short color) {
        int firstRow = range.getFirstRow();
        int lastRow = range.getLastRow();
        int col = range.getFirstColumn();
        for (int i = firstRow; i <= lastRow; ++i) {
            if (this.getBorderStyle(i, col, "borderLeft") == BorderStyle.NONE) {
                this.drawLeftBorder(new CellRangeAddress(i, i, col, col), BorderStyle.THIN);
            }
            this.addProperty(i, col, "leftBorderColor", color);
        }
    }

    private void drawRightBorderColor(CellRangeAddress range, short color) {
        int firstRow = range.getFirstRow();
        int lastRow = range.getLastRow();
        int col = range.getLastColumn();
        for (int i = firstRow; i <= lastRow; ++i) {
            if (this.getBorderStyle(i, col, "borderRight") == BorderStyle.NONE) {
                this.drawRightBorder(new CellRangeAddress(i, i, col, col), BorderStyle.THIN);
            }
            this.addProperty(i, col, "rightBorderColor", color);
        }
    }

    private void drawOutsideBorderColors(CellRangeAddress range, short color, BorderExtent extent) {
        switch (extent) {
            case ALL: 
            case HORIZONTAL: 
            case VERTICAL: {
                if (extent == BorderExtent.ALL || extent == BorderExtent.HORIZONTAL) {
                    this.drawTopBorderColor(range, color);
                    this.drawBottomBorderColor(range, color);
                }
                if (extent != BorderExtent.ALL && extent != BorderExtent.VERTICAL) break;
                this.drawLeftBorderColor(range, color);
                this.drawRightBorderColor(range, color);
                break;
            }
            default: {
                throw new IllegalArgumentException("Unsupported PropertyTemplate.Extent, valid Extents are ALL, HORIZONTAL, and VERTICAL");
            }
        }
    }

    private void drawHorizontalBorderColors(CellRangeAddress range, short color, BorderExtent extent) {
        switch (extent) {
            case ALL: 
            case INSIDE: {
                int firstRow = range.getFirstRow();
                int lastRow = range.getLastRow();
                int firstCol = range.getFirstColumn();
                int lastCol = range.getLastColumn();
                for (int i = firstRow; i <= lastRow; ++i) {
                    CellRangeAddress row = new CellRangeAddress(i, i, firstCol, lastCol);
                    if (extent == BorderExtent.ALL || i > firstRow) {
                        this.drawTopBorderColor(row, color);
                    }
                    if (extent != BorderExtent.ALL && i >= lastRow) continue;
                    this.drawBottomBorderColor(row, color);
                }
                break;
            }
            default: {
                throw new IllegalArgumentException("Unsupported PropertyTemplate.Extent, valid Extents are ALL and INSIDE");
            }
        }
    }

    private void drawVerticalBorderColors(CellRangeAddress range, short color, BorderExtent extent) {
        switch (extent) {
            case ALL: 
            case INSIDE: {
                int firstRow = range.getFirstRow();
                int lastRow = range.getLastRow();
                int firstCol = range.getFirstColumn();
                int lastCol = range.getLastColumn();
                for (int i = firstCol; i <= lastCol; ++i) {
                    CellRangeAddress row = new CellRangeAddress(firstRow, lastRow, i, i);
                    if (extent == BorderExtent.ALL || i > firstCol) {
                        this.drawLeftBorderColor(row, color);
                    }
                    if (extent != BorderExtent.ALL && i >= lastCol) continue;
                    this.drawRightBorderColor(row, color);
                }
                break;
            }
            default: {
                throw new IllegalArgumentException("Unsupported PropertyTemplate.Extent, valid Extents are ALL and INSIDE");
            }
        }
    }

    private void removeBorderColors(CellRangeAddress range) {
        HashSet<String> properties = new HashSet<String>();
        properties.add("topBorderColor");
        properties.add("bottomBorderColor");
        properties.add("leftBorderColor");
        properties.add("rightBorderColor");
        for (int row = range.getFirstRow(); row <= range.getLastRow(); ++row) {
            for (int col = range.getFirstColumn(); col <= range.getLastColumn(); ++col) {
                this.removeProperties(row, col, properties);
            }
        }
    }

    private void addProperty(int row, int col, String property, short value) {
        this.addProperty(row, col, property, (Object)value);
    }

    private void addProperty(int row, int col, String property, Object value) {
        CellAddress cell = new CellAddress(row, col);
        Map<String, Object> cellProperties = this._propertyTemplate.get(cell);
        if (cellProperties == null) {
            cellProperties = new HashMap<String, Object>();
        }
        cellProperties.put(property, value);
        this._propertyTemplate.put(cell, cellProperties);
    }

    private void removeProperties(int row, int col, Set<String> properties) {
        CellAddress cell = new CellAddress(row, col);
        Map<String, Object> cellProperties = this._propertyTemplate.get(cell);
        if (cellProperties != null) {
            cellProperties.keySet().removeAll(properties);
            if (cellProperties.isEmpty()) {
                this._propertyTemplate.remove(cell);
            } else {
                this._propertyTemplate.put(cell, cellProperties);
            }
        }
    }

    public int getNumBorders(CellAddress cell) {
        Map<String, Object> cellProperties = this._propertyTemplate.get(cell);
        if (cellProperties == null) {
            return 0;
        }
        int count = 0;
        for (String property : cellProperties.keySet()) {
            if (property.equals("borderTop")) {
                ++count;
            }
            if (property.equals("borderBottom")) {
                ++count;
            }
            if (property.equals("borderLeft")) {
                ++count;
            }
            if (!property.equals("borderRight")) continue;
            ++count;
        }
        return count;
    }

    public int getNumBorders(int row, int col) {
        return this.getNumBorders(new CellAddress(row, col));
    }

    public int getNumBorderColors(CellAddress cell) {
        Map<String, Object> cellProperties = this._propertyTemplate.get(cell);
        if (cellProperties == null) {
            return 0;
        }
        int count = 0;
        for (String property : cellProperties.keySet()) {
            if (property.equals("topBorderColor")) {
                ++count;
            }
            if (property.equals("bottomBorderColor")) {
                ++count;
            }
            if (property.equals("leftBorderColor")) {
                ++count;
            }
            if (!property.equals("rightBorderColor")) continue;
            ++count;
        }
        return count;
    }

    public int getNumBorderColors(int row, int col) {
        return this.getNumBorderColors(new CellAddress(row, col));
    }

    public BorderStyle getBorderStyle(CellAddress cell, String property) {
        Object obj;
        BorderStyle value = BorderStyle.NONE;
        Map<String, Object> cellProperties = this._propertyTemplate.get(cell);
        if (cellProperties != null && (obj = cellProperties.get(property)) instanceof BorderStyle) {
            value = (BorderStyle)((Object)obj);
        }
        return value;
    }

    public BorderStyle getBorderStyle(int row, int col, String property) {
        return this.getBorderStyle(new CellAddress(row, col), property);
    }

    public short getTemplateProperty(CellAddress cell, String property) {
        Object obj;
        short value = 0;
        Map<String, Object> cellProperties = this._propertyTemplate.get(cell);
        if (cellProperties != null && (obj = cellProperties.get(property)) != null) {
            value = PropertyTemplate.getShort(obj);
        }
        return value;
    }

    public short getTemplateProperty(int row, int col, String property) {
        return this.getTemplateProperty(new CellAddress(row, col), property);
    }

    private static short getShort(Object value) {
        if (value instanceof Number) {
            return ((Number)value).shortValue();
        }
        return 0;
    }
}

