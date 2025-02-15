/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.xwpf.usermodel;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import org.apache.poi.util.Internal;
import org.apache.poi.xwpf.model.WMLHelper;
import org.apache.poi.xwpf.usermodel.ICell;
import org.apache.poi.xwpf.usermodel.XWPFSDTCell;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.apache.xmlbeans.XmlCursor;
import org.apache.xmlbeans.XmlObject;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTHeight;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTOnOff;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRow;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSdtCell;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTc;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTrPr;

public class XWPFTableRow {
    private CTRow ctRow;
    private XWPFTable table;
    private List<XWPFTableCell> tableCells;

    public XWPFTableRow(CTRow row, XWPFTable table) {
        this.table = table;
        this.ctRow = row;
        this.getTableCells();
    }

    @Internal
    public CTRow getCtRow() {
        return this.ctRow;
    }

    public XWPFTableCell createCell() {
        XWPFTableCell tableCell = new XWPFTableCell(this.ctRow.addNewTc(), this, this.table.getBody());
        this.tableCells.add(tableCell);
        return tableCell;
    }

    public XWPFTableCell getCell(int pos) {
        if (pos >= 0 && pos < this.ctRow.sizeOfTcArray()) {
            return this.getTableCells().get(pos);
        }
        return null;
    }

    public void removeCell(int pos) {
        if (pos >= 0 && pos < this.ctRow.sizeOfTcArray()) {
            this.tableCells.remove(pos);
        }
    }

    public XWPFTableCell addNewTableCell() {
        CTTc cell = this.ctRow.addNewTc();
        XWPFTableCell tableCell = new XWPFTableCell(cell, this, this.table.getBody());
        this.tableCells.add(tableCell);
        return tableCell;
    }

    public int getHeight() {
        CTTrPr properties = this.getTrPr();
        return properties.sizeOfTrHeightArray() == 0 ? 0 : properties.getTrHeightArray(0).getVal().intValue();
    }

    public void setHeight(int height) {
        CTTrPr properties = this.getTrPr();
        CTHeight h = properties.sizeOfTrHeightArray() == 0 ? properties.addNewTrHeight() : properties.getTrHeightArray(0);
        h.setVal(new BigInteger(Integer.toString(height)));
    }

    private CTTrPr getTrPr() {
        return this.ctRow.isSetTrPr() ? this.ctRow.getTrPr() : this.ctRow.addNewTrPr();
    }

    public XWPFTable getTable() {
        return this.table;
    }

    public List<ICell> getTableICells() {
        ArrayList<ICell> cells = new ArrayList<ICell>();
        XmlCursor cursor = this.ctRow.newCursor();
        cursor.selectPath("./*");
        while (cursor.toNextSelection()) {
            XmlObject o = cursor.getObject();
            if (o instanceof CTTc) {
                cells.add(new XWPFTableCell((CTTc)o, this, this.table.getBody()));
                continue;
            }
            if (!(o instanceof CTSdtCell)) continue;
            cells.add(new XWPFSDTCell((CTSdtCell)o, this, this.table.getBody()));
        }
        cursor.dispose();
        return cells;
    }

    public List<XWPFTableCell> getTableCells() {
        if (this.tableCells == null) {
            ArrayList<XWPFTableCell> cells = new ArrayList<XWPFTableCell>();
            for (CTTc tableCell : this.ctRow.getTcArray()) {
                cells.add(new XWPFTableCell(tableCell, this, this.table.getBody()));
            }
            this.tableCells = cells;
        }
        return this.tableCells;
    }

    public XWPFTableCell getTableCell(CTTc cell) {
        for (int i = 0; i < this.tableCells.size(); ++i) {
            if (this.tableCells.get(i).getCTTc() != cell) continue;
            return this.tableCells.get(i);
        }
        return null;
    }

    public boolean isCantSplitRow() {
        CTTrPr trpr;
        boolean isCant = false;
        if (this.ctRow.isSetTrPr() && (trpr = this.getTrPr()).sizeOfCantSplitArray() > 0) {
            CTOnOff onoff = trpr.getCantSplitArray(0);
            isCant = onoff.isSetVal() ? WMLHelper.convertSTOnOffToBoolean(onoff.getVal()) : true;
        }
        return isCant;
    }

    public void setCantSplitRow(boolean split) {
        CTTrPr trpr = this.getTrPr();
        CTOnOff onoff = trpr.sizeOfCantSplitArray() > 0 ? trpr.getCantSplitArray(0) : trpr.addNewCantSplit();
        onoff.setVal(WMLHelper.convertBooleanToSTOnOff(split));
    }

    public boolean isRepeatHeader() {
        boolean repeat = false;
        for (XWPFTableRow row : this.table.getRows()) {
            repeat = row.getRepeat();
            if (row != this && repeat) continue;
            break;
        }
        return repeat;
    }

    private boolean getRepeat() {
        CTTrPr trpr;
        boolean repeat = false;
        if (this.ctRow.isSetTrPr() && (trpr = this.getTrPr()).sizeOfTblHeaderArray() > 0) {
            CTOnOff rpt = trpr.getTblHeaderArray(0);
            repeat = rpt.isSetVal() ? WMLHelper.convertSTOnOffToBoolean(rpt.getVal()) : true;
        }
        return repeat;
    }

    public void setRepeatHeader(boolean repeat) {
        CTTrPr trpr = this.getTrPr();
        CTOnOff onoff = trpr.sizeOfTblHeaderArray() > 0 ? trpr.getTblHeaderArray(0) : trpr.addNewTblHeader();
        onoff.setVal(WMLHelper.convertBooleanToSTOnOff(repeat));
    }
}

