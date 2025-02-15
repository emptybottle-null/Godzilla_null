/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.ss.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.apache.poi.hssf.record.RecordInputStream;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.util.LittleEndianByteArrayOutputStream;
import org.apache.poi.util.LittleEndianOutput;

public class CellRangeAddressList {
    protected final List<CellRangeAddress> _list = new ArrayList<CellRangeAddress>();

    public CellRangeAddressList() {
    }

    public CellRangeAddressList(int firstRow, int lastRow, int firstCol, int lastCol) {
        this();
        this.addCellRangeAddress(firstRow, firstCol, lastRow, lastCol);
    }

    public CellRangeAddressList(RecordInputStream in) {
        this();
        int nItems = in.readUShort();
        for (int k = 0; k < nItems; ++k) {
            this._list.add(new CellRangeAddress(in));
        }
    }

    public int countRanges() {
        return this._list.size();
    }

    public void addCellRangeAddress(int firstRow, int firstCol, int lastRow, int lastCol) {
        CellRangeAddress region = new CellRangeAddress(firstRow, lastRow, firstCol, lastCol);
        this.addCellRangeAddress(region);
    }

    public void addCellRangeAddress(CellRangeAddress cra) {
        this._list.add(cra);
    }

    public CellRangeAddress remove(int rangeIndex) {
        if (this._list.isEmpty()) {
            throw new RuntimeException("List is empty");
        }
        if (rangeIndex < 0 || rangeIndex >= this._list.size()) {
            throw new RuntimeException("Range index (" + rangeIndex + ") is outside allowable range (0.." + (this._list.size() - 1) + ")");
        }
        return this._list.remove(rangeIndex);
    }

    public CellRangeAddress getCellRangeAddress(int index) {
        return this._list.get(index);
    }

    public int getSize() {
        return CellRangeAddressList.getEncodedSize(this._list.size());
    }

    public static int getEncodedSize(int numberOfRanges) {
        return 2 + CellRangeAddress.getEncodedSize(numberOfRanges);
    }

    public int serialize(int offset, byte[] data) {
        int totalSize = this.getSize();
        try (LittleEndianByteArrayOutputStream lebaos = new LittleEndianByteArrayOutputStream(data, offset, totalSize);){
            this.serialize(lebaos);
        } catch (IOException ioe) {
            throw new IllegalStateException(ioe);
        }
        return totalSize;
    }

    public void serialize(LittleEndianOutput out) {
        int nItems = this._list.size();
        out.writeShort(nItems);
        for (int k = 0; k < nItems; ++k) {
            CellRangeAddress region = this._list.get(k);
            region.serialize(out);
        }
    }

    public CellRangeAddressList copy() {
        CellRangeAddressList result = new CellRangeAddressList();
        int nItems = this._list.size();
        for (int k = 0; k < nItems; ++k) {
            CellRangeAddress region = this._list.get(k);
            result.addCellRangeAddress(region.copy());
        }
        return result;
    }

    public CellRangeAddress[] getCellRangeAddresses() {
        CellRangeAddress[] result = new CellRangeAddress[this._list.size()];
        this._list.toArray(result);
        return result;
    }
}

