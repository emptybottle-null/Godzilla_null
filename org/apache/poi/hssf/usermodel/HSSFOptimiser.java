/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.hssf.usermodel;

import java.util.HashSet;
import org.apache.poi.hssf.record.ExtendedFormatRecord;
import org.apache.poi.hssf.record.FontRecord;
import org.apache.poi.hssf.record.StyleRecord;
import org.apache.poi.hssf.record.common.UnicodeString;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;

public class HSSFOptimiser {
    public static void optimiseFonts(HSSFWorkbook workbook) {
        int i;
        short[] newPos = new short[workbook.getWorkbook().getNumberOfFontRecords() + 1];
        boolean[] zapRecords = new boolean[newPos.length];
        for (int i2 = 0; i2 < newPos.length; ++i2) {
            newPos[i2] = (short)i2;
            zapRecords[i2] = false;
        }
        FontRecord[] frecs = new FontRecord[newPos.length];
        for (i = 0; i < newPos.length; ++i) {
            if (i == 4) continue;
            frecs[i] = workbook.getWorkbook().getFontRecordAt(i);
        }
        for (i = 5; i < newPos.length; ++i) {
            int earlierDuplicate = -1;
            for (int j = 0; j < i && earlierDuplicate == -1; ++j) {
                FontRecord frCheck;
                if (j == 4 || !(frCheck = workbook.getWorkbook().getFontRecordAt(j)).sameProperties(frecs[i])) continue;
                earlierDuplicate = j;
            }
            if (earlierDuplicate == -1) continue;
            newPos[i] = (short)earlierDuplicate;
            zapRecords[i] = true;
        }
        for (i = 5; i < newPos.length; ++i) {
            int preDeletePos;
            int newPosition = preDeletePos = newPos[i];
            for (int j = 0; j < preDeletePos; ++j) {
                if (!zapRecords[j]) continue;
                newPosition = (short)(newPosition - 1);
            }
            newPos[i] = newPosition;
        }
        for (i = 5; i < newPos.length; ++i) {
            if (!zapRecords[i]) continue;
            workbook.getWorkbook().removeFontRecord(frecs[i]);
        }
        workbook.resetFontCache();
        for (i = 0; i < workbook.getWorkbook().getNumExFormats(); ++i) {
            ExtendedFormatRecord xfr = workbook.getWorkbook().getExFormatAt(i);
            xfr.setFontIndex(newPos[xfr.getFontIndex()]);
        }
        HashSet<UnicodeString> doneUnicodeStrings = new HashSet<UnicodeString>();
        for (int sheetNum = 0; sheetNum < workbook.getNumberOfSheets(); ++sheetNum) {
            HSSFSheet s = workbook.getSheetAt(sheetNum);
            for (Row row : s) {
                for (Cell cell : row) {
                    HSSFRichTextString rtr;
                    UnicodeString u;
                    if (cell.getCellType() != CellType.STRING || doneUnicodeStrings.contains(u = (rtr = (HSSFRichTextString)cell.getRichStringCellValue()).getRawUnicodeString())) continue;
                    for (short i3 = 5; i3 < newPos.length; i3 = (short)((short)(i3 + 1))) {
                        if (i3 == newPos[i3]) continue;
                        u.swapFontUse(i3, newPos[i3]);
                    }
                    doneUnicodeStrings.add(u);
                }
            }
        }
    }

    public static void optimiseCellStyles(HSSFWorkbook workbook) {
        int i;
        short[] newPos = new short[workbook.getWorkbook().getNumExFormats()];
        boolean[] isUsed = new boolean[newPos.length];
        boolean[] zapRecords = new boolean[newPos.length];
        for (int i2 = 0; i2 < newPos.length; ++i2) {
            isUsed[i2] = false;
            newPos[i2] = (short)i2;
            zapRecords[i2] = false;
        }
        ExtendedFormatRecord[] xfrs = new ExtendedFormatRecord[newPos.length];
        for (i = 0; i < newPos.length; ++i) {
            xfrs[i] = workbook.getWorkbook().getExFormatAt(i);
        }
        for (i = 21; i < newPos.length; ++i) {
            int earlierDuplicate = -1;
            for (int j = 0; j < i && earlierDuplicate == -1; ++j) {
                ExtendedFormatRecord xfCheck = workbook.getWorkbook().getExFormatAt(j);
                if (!xfCheck.equals(xfrs[i]) || HSSFOptimiser.isUserDefined(workbook, j)) continue;
                earlierDuplicate = j;
            }
            if (earlierDuplicate == -1) continue;
            newPos[i] = (short)earlierDuplicate;
            zapRecords[i] = true;
        }
        for (int sheetNum = 0; sheetNum < workbook.getNumberOfSheets(); ++sheetNum) {
            HSSFSheet s = workbook.getSheetAt(sheetNum);
            for (Row row : s) {
                for (Cell cellI : row) {
                    HSSFCell cell = (HSSFCell)cellI;
                    short oldXf = cell.getCellValueRecord().getXFIndex();
                    if (oldXf >= newPos.length) continue;
                    isUsed[oldXf] = true;
                }
                short oldXf = ((HSSFRow)row).getRowRecord().getXFIndex();
                if (oldXf >= newPos.length) continue;
                isUsed[oldXf] = true;
            }
            for (int col = s.getSheet().getMinColumnIndex(); col <= s.getSheet().getMaxColumnIndex(); ++col) {
                short oldXf = s.getSheet().getXFIndexForColAt((short)col);
                if (oldXf >= newPos.length) continue;
                isUsed[oldXf] = true;
            }
        }
        for (i = 21; i < isUsed.length; ++i) {
            if (HSSFOptimiser.isUserDefined(workbook, i)) {
                isUsed[i] = true;
            }
            if (newPos[i] == i || !isUsed[i]) continue;
            isUsed[newPos[i]] = true;
        }
        for (i = 21; i < isUsed.length; ++i) {
            if (isUsed[i]) continue;
            zapRecords[i] = true;
            newPos[i] = 0;
        }
        for (i = 21; i < newPos.length; ++i) {
            int preDeletePos;
            int newPosition = preDeletePos = newPos[i];
            for (int j = 0; j < preDeletePos; ++j) {
                if (!zapRecords[j]) continue;
                newPosition = (short)(newPosition - 1);
            }
            newPos[i] = newPosition;
            if (i == newPosition || newPosition == 0) continue;
            workbook.getWorkbook().updateStyleRecord(i, newPosition);
            ExtendedFormatRecord exFormat = workbook.getWorkbook().getExFormatAt(i);
            short oldParent = exFormat.getParentIndex();
            if (oldParent >= newPos.length) continue;
            short newParent = newPos[oldParent];
            exFormat.setParentIndex(newParent);
        }
        int max = newPos.length;
        int removed = 0;
        for (int i3 = 21; i3 < max; ++i3) {
            if (!zapRecords[i3 + removed]) continue;
            workbook.getWorkbook().removeExFormatRecord(i3);
            --i3;
            --max;
            ++removed;
        }
        for (int sheetNum = 0; sheetNum < workbook.getNumberOfSheets(); ++sheetNum) {
            HSSFSheet s = workbook.getSheetAt(sheetNum);
            for (Row row : s) {
                for (Cell cell : row) {
                    short oldXf = ((HSSFCell)cell).getCellValueRecord().getXFIndex();
                    if (oldXf >= newPos.length) continue;
                    HSSFCellStyle newStyle = workbook.getCellStyleAt(newPos[oldXf]);
                    cell.setCellStyle(newStyle);
                }
                short oldXf = ((HSSFRow)row).getRowRecord().getXFIndex();
                if (oldXf >= newPos.length) continue;
                HSSFCellStyle newStyle = workbook.getCellStyleAt(newPos[oldXf]);
                row.setRowStyle(newStyle);
            }
            for (int col = s.getSheet().getMinColumnIndex(); col <= s.getSheet().getMaxColumnIndex(); ++col) {
                short oldXf = s.getSheet().getXFIndexForColAt((short)col);
                if (oldXf >= newPos.length) continue;
                HSSFCellStyle newStyle = workbook.getCellStyleAt(newPos[oldXf]);
                s.setDefaultColumnStyle(col, newStyle);
            }
        }
    }

    private static boolean isUserDefined(HSSFWorkbook workbook, int index) {
        StyleRecord styleRecord = workbook.getWorkbook().getStyleRecord(index);
        return styleRecord != null && !styleRecord.isBuiltin() && styleRecord.getName() != null;
    }
}

