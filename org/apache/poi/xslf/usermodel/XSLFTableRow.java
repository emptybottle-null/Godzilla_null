/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.xslf.usermodel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import org.apache.poi.util.Units;
import org.apache.poi.xslf.usermodel.XSLFTable;
import org.apache.poi.xslf.usermodel.XSLFTableCell;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTableCell;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTableRow;

public class XSLFTableRow
implements Iterable<XSLFTableCell> {
    private final CTTableRow _row;
    private final List<XSLFTableCell> _cells;
    private final XSLFTable _table;

    XSLFTableRow(CTTableRow row, XSLFTable table) {
        this._row = row;
        this._table = table;
        CTTableCell[] tcArray = this._row.getTcArray();
        this._cells = new ArrayList<XSLFTableCell>(tcArray.length);
        for (CTTableCell cell : tcArray) {
            this._cells.add(new XSLFTableCell(cell, table));
        }
    }

    public CTTableRow getXmlObject() {
        return this._row;
    }

    @Override
    public Iterator<XSLFTableCell> iterator() {
        return this._cells.iterator();
    }

    public List<XSLFTableCell> getCells() {
        return Collections.unmodifiableList(this._cells);
    }

    public double getHeight() {
        return Units.toPoints(this._row.getH());
    }

    public void setHeight(double height) {
        this._row.setH(Units.toEMU(height));
    }

    public XSLFTableCell addCell() {
        CTTableCell c = this._row.addNewTc();
        c.set(XSLFTableCell.prototype());
        XSLFTableCell cell = new XSLFTableCell(c, this._table);
        this._cells.add(cell);
        if (this._table.getNumberOfColumns() < this._row.sizeOfTcArray()) {
            this._table.getCTTable().getTblGrid().addNewGridCol().setW(Units.toEMU(100.0));
        }
        this._table.updateRowColIndexes();
        return cell;
    }

    public XSLFTableCell insertCell(int colIdx) {
        CTTableCell c = this._row.insertNewTc(colIdx);
        c.set(XSLFTableCell.prototype());
        XSLFTableCell cell = new XSLFTableCell(c, this._table);
        this._cells.add(colIdx, cell);
        if (this._table.getNumberOfColumns() < this._row.sizeOfTcArray()) {
            this._table.getCTTable().getTblGrid().insertNewGridCol(colIdx).setW(Units.toEMU(100.0));
        }
        this._table.updateRowColIndexes();
        return cell;
    }

    public void removeCell(int colIdx) {
        this._row.removeTc(colIdx);
        this._cells.remove(colIdx);
        this._table.updateRowColIndexes();
    }

    public void mergeCells(int firstCol, int lastCol) {
        if (firstCol >= lastCol) {
            throw new IllegalArgumentException("Cannot merge, first column >= last column : " + firstCol + " >= " + lastCol);
        }
        int colSpan = lastCol - firstCol + 1;
        this._cells.get(firstCol).setGridSpan(colSpan);
        for (XSLFTableCell cell : this._cells.subList(firstCol + 1, lastCol + 1)) {
            cell.setHMerge();
        }
    }
}

