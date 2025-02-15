/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.ss.util;

import java.lang.reflect.Array;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellRange;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.util.Internal;

@Internal
public final class SSCellRange<K extends Cell>
implements CellRange<K> {
    private final int _height;
    private final int _width;
    private final K[] _flattenedArray;
    private final int _firstRow;
    private final int _firstColumn;

    private SSCellRange(int firstRow, int firstColumn, int height, int width, K[] flattenedArray) {
        this._firstRow = firstRow;
        this._firstColumn = firstColumn;
        this._height = height;
        this._width = width;
        this._flattenedArray = (Cell[])flattenedArray.clone();
    }

    public static <B extends Cell> SSCellRange<B> create(int firstRow, int firstColumn, int height, int width, List<B> flattenedList, Class<B> cellClass) {
        int nItems = flattenedList.size();
        if (height * width != nItems) {
            throw new IllegalArgumentException("Array size mismatch.");
        }
        Cell[] flattenedArray = (Cell[])Array.newInstance(cellClass, nItems);
        flattenedList.toArray(flattenedArray);
        return new SSCellRange(firstRow, firstColumn, height, width, flattenedArray);
    }

    @Override
    public int getHeight() {
        return this._height;
    }

    @Override
    public int getWidth() {
        return this._width;
    }

    @Override
    public int size() {
        return this._height * this._width;
    }

    @Override
    public String getReferenceText() {
        CellRangeAddress cra = new CellRangeAddress(this._firstRow, this._firstRow + this._height - 1, this._firstColumn, this._firstColumn + this._width - 1);
        return cra.formatAsString();
    }

    @Override
    public K getTopLeftCell() {
        return this._flattenedArray[0];
    }

    @Override
    public K getCell(int relativeRowIndex, int relativeColumnIndex) {
        if (relativeRowIndex < 0 || relativeRowIndex >= this._height) {
            throw new ArrayIndexOutOfBoundsException("Specified row " + relativeRowIndex + " is outside the allowable range (0.." + (this._height - 1) + ").");
        }
        if (relativeColumnIndex < 0 || relativeColumnIndex >= this._width) {
            throw new ArrayIndexOutOfBoundsException("Specified colummn " + relativeColumnIndex + " is outside the allowable range (0.." + (this._width - 1) + ").");
        }
        int flatIndex = this._width * relativeRowIndex + relativeColumnIndex;
        return this._flattenedArray[flatIndex];
    }

    @Override
    public K[] getFlattenedCells() {
        return (Cell[])this._flattenedArray.clone();
    }

    @Override
    public K[][] getCells() {
        Class<?> itemCls = this._flattenedArray.getClass();
        Cell[][] result = (Cell[][])Array.newInstance(itemCls, this._height);
        itemCls = itemCls.getComponentType();
        for (int r = this._height - 1; r >= 0; --r) {
            Cell[] row = (Cell[])Array.newInstance(itemCls, this._width);
            int flatIndex = this._width * r;
            System.arraycopy(this._flattenedArray, flatIndex, row, 0, this._width);
        }
        return result;
    }

    @Override
    public Iterator<K> iterator() {
        return Stream.of(this._flattenedArray).iterator();
    }
}

