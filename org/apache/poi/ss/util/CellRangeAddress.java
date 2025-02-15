/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.ss.util;

import org.apache.poi.hssf.record.RecordInputStream;
import org.apache.poi.ss.formula.SheetNameFormatter;
import org.apache.poi.ss.util.CellRangeAddressBase;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.util.LittleEndianOutput;

public class CellRangeAddress
extends CellRangeAddressBase {
    public static final int ENCODED_SIZE = 8;

    public CellRangeAddress(int firstRow, int lastRow, int firstCol, int lastCol) {
        super(firstRow, lastRow, firstCol, lastCol);
        if (lastRow < firstRow || lastCol < firstCol) {
            throw new IllegalArgumentException("Invalid cell range, having lastRow < firstRow || lastCol < firstCol, had rows " + lastRow + " >= " + firstRow + " or cells " + lastCol + " >= " + firstCol);
        }
    }

    public void serialize(LittleEndianOutput out) {
        out.writeShort(this.getFirstRow());
        out.writeShort(this.getLastRow());
        out.writeShort(this.getFirstColumn());
        out.writeShort(this.getLastColumn());
    }

    public CellRangeAddress(RecordInputStream in) {
        super(CellRangeAddress.readUShortAndCheck(in), in.readUShort(), in.readUShort(), in.readUShort());
    }

    private static int readUShortAndCheck(RecordInputStream in) {
        if (in.remaining() < 8) {
            throw new RuntimeException("Ran out of data reading CellRangeAddress");
        }
        return in.readUShort();
    }

    @Override
    public CellRangeAddress copy() {
        return new CellRangeAddress(this.getFirstRow(), this.getLastRow(), this.getFirstColumn(), this.getLastColumn());
    }

    public static int getEncodedSize(int numberOfItems) {
        return numberOfItems * 8;
    }

    public String formatAsString() {
        return this.formatAsString(null, false);
    }

    public String formatAsString(String sheetName, boolean useAbsoluteAddress) {
        StringBuilder sb = new StringBuilder();
        if (sheetName != null) {
            sb.append(SheetNameFormatter.format(sheetName));
            sb.append("!");
        }
        CellReference cellRefFrom = new CellReference(this.getFirstRow(), this.getFirstColumn(), useAbsoluteAddress, useAbsoluteAddress);
        CellReference cellRefTo = new CellReference(this.getLastRow(), this.getLastColumn(), useAbsoluteAddress, useAbsoluteAddress);
        sb.append(cellRefFrom.formatAsString());
        if (!cellRefFrom.equals(cellRefTo) || this.isFullColumnRange() || this.isFullRowRange()) {
            sb.append(':');
            sb.append(cellRefTo.formatAsString());
        }
        return sb.toString();
    }

    public static CellRangeAddress valueOf(String ref) {
        CellReference b;
        CellReference a;
        int sep = ref.indexOf(":");
        if (sep == -1) {
            b = a = new CellReference(ref);
        } else {
            a = new CellReference(ref.substring(0, sep));
            b = new CellReference(ref.substring(sep + 1));
        }
        return new CellRangeAddress(a.getRow(), b.getRow(), a.getCol(), b.getCol());
    }
}

