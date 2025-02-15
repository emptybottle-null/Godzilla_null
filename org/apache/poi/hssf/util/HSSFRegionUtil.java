/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.hssf.util;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.RegionUtil;
import org.apache.poi.util.Removal;

@Removal(version="4.2")
public final class HSSFRegionUtil {
    private HSSFRegionUtil() {
    }

    public static void setBorderLeft(int border, CellRangeAddress region, HSSFSheet sheet, HSSFWorkbook workbook) {
        RegionUtil.setBorderLeft(BorderStyle.valueOf((short)border), region, sheet);
    }

    public static void setLeftBorderColor(int color, CellRangeAddress region, HSSFSheet sheet, HSSFWorkbook workbook) {
        RegionUtil.setLeftBorderColor(color, region, sheet);
    }

    public static void setBorderRight(int border, CellRangeAddress region, HSSFSheet sheet, HSSFWorkbook workbook) {
        RegionUtil.setBorderRight(BorderStyle.valueOf((short)border), region, sheet);
    }

    public static void setRightBorderColor(int color, CellRangeAddress region, HSSFSheet sheet, HSSFWorkbook workbook) {
        RegionUtil.setRightBorderColor(color, region, sheet);
    }

    public static void setBorderBottom(int border, CellRangeAddress region, HSSFSheet sheet, HSSFWorkbook workbook) {
        RegionUtil.setBorderBottom(BorderStyle.valueOf((short)border), region, sheet);
    }

    public static void setBottomBorderColor(int color, CellRangeAddress region, HSSFSheet sheet, HSSFWorkbook workbook) {
        RegionUtil.setBottomBorderColor(color, region, sheet);
    }

    public static void setBorderTop(int border, CellRangeAddress region, HSSFSheet sheet, HSSFWorkbook workbook) {
        RegionUtil.setBorderTop(BorderStyle.valueOf((short)border), region, sheet);
    }

    public static void setTopBorderColor(int color, CellRangeAddress region, HSSFSheet sheet, HSSFWorkbook workbook) {
        RegionUtil.setTopBorderColor(color, region, sheet);
    }
}

