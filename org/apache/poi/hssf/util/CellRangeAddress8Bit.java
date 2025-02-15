/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.hssf.util;

import org.apache.poi.ss.util.CellRangeAddressBase;
import org.apache.poi.util.LittleEndianInput;
import org.apache.poi.util.LittleEndianOutput;

public final class CellRangeAddress8Bit
extends CellRangeAddressBase {
    public static final int ENCODED_SIZE = 6;

    public CellRangeAddress8Bit(int firstRow, int lastRow, int firstCol, int lastCol) {
        super(firstRow, lastRow, firstCol, lastCol);
    }

    public CellRangeAddress8Bit(LittleEndianInput in) {
        super(CellRangeAddress8Bit.readUShortAndCheck(in), in.readUShort(), in.readUByte(), in.readUByte());
    }

    private static int readUShortAndCheck(LittleEndianInput in) {
        if (in.available() < 6) {
            throw new RuntimeException("Ran out of data reading CellRangeAddress");
        }
        return in.readUShort();
    }

    public void serialize(LittleEndianOutput out) {
        out.writeShort(this.getFirstRow());
        out.writeShort(this.getLastRow());
        out.writeByte(this.getFirstColumn());
        out.writeByte(this.getLastColumn());
    }

    @Override
    public CellRangeAddress8Bit copy() {
        return new CellRangeAddress8Bit(this.getFirstRow(), this.getLastRow(), this.getFirstColumn(), this.getLastColumn());
    }

    public static int getEncodedSize(int numberOfItems) {
        return numberOfItems * 6;
    }
}

