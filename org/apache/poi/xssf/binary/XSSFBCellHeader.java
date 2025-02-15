/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.xssf.binary;

import org.apache.poi.util.Internal;
import org.apache.poi.util.LittleEndian;
import org.apache.poi.xssf.binary.XSSFBUtils;

@Internal
class XSSFBCellHeader {
    public static int length = 8;
    private int rowNum;
    private int colNum;
    private int styleIdx;
    private boolean showPhonetic;

    XSSFBCellHeader() {
    }

    public static void parse(byte[] data, int offset, int currentRow, XSSFBCellHeader cell) {
        int colNum = XSSFBUtils.castToInt(LittleEndian.getUInt(data, offset));
        int styleIdx = XSSFBUtils.get24BitInt(data, offset += 4);
        offset += 3;
        boolean showPhonetic = false;
        cell.reset(currentRow, colNum, styleIdx, showPhonetic);
    }

    public void reset(int rowNum, int colNum, int styleIdx, boolean showPhonetic) {
        this.rowNum = rowNum;
        this.colNum = colNum;
        this.styleIdx = styleIdx;
        this.showPhonetic = showPhonetic;
    }

    int getColNum() {
        return this.colNum;
    }

    int getStyleIdx() {
        return this.styleIdx;
    }
}

