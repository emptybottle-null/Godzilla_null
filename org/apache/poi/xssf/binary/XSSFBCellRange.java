/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.xssf.binary;

import org.apache.poi.util.Internal;
import org.apache.poi.util.LittleEndian;
import org.apache.poi.xssf.binary.XSSFBUtils;

@Internal
class XSSFBCellRange {
    public static final int length = 16;
    int firstRow;
    int lastRow;
    int firstCol;
    int lastCol;

    XSSFBCellRange() {
    }

    public static XSSFBCellRange parse(byte[] data, int offset, XSSFBCellRange cellRange) {
        if (cellRange == null) {
            cellRange = new XSSFBCellRange();
        }
        cellRange.firstRow = XSSFBUtils.castToInt(LittleEndian.getUInt(data, offset));
        cellRange.lastRow = XSSFBUtils.castToInt(LittleEndian.getUInt(data, offset += 4));
        cellRange.firstCol = XSSFBUtils.castToInt(LittleEndian.getUInt(data, offset += 4));
        cellRange.lastCol = XSSFBUtils.castToInt(LittleEndian.getUInt(data, offset += 4));
        return cellRange;
    }
}

