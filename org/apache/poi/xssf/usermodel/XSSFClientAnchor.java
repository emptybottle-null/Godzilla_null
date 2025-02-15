/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.xssf.usermodel;

import org.apache.poi.ss.usermodel.ClientAnchor;
import org.apache.poi.util.Internal;
import org.apache.poi.util.Units;
import org.apache.poi.xssf.usermodel.XSSFAnchor;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.openxmlformats.schemas.drawingml.x2006.main.CTPoint2D;
import org.openxmlformats.schemas.drawingml.x2006.main.CTPositiveSize2D;
import org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.CTMarker;

public class XSSFClientAnchor
extends XSSFAnchor
implements ClientAnchor {
    private static final CTMarker EMPTY_MARKER = CTMarker.Factory.newInstance();
    private ClientAnchor.AnchorType anchorType;
    private CTMarker cell1;
    private CTMarker cell2;
    private CTPositiveSize2D size;
    private CTPoint2D position;
    private XSSFSheet sheet;

    public XSSFClientAnchor() {
        this(0, 0, 0, 0, 0, 0, 0, 0);
    }

    public XSSFClientAnchor(int dx1, int dy1, int dx2, int dy2, int col1, int row1, int col2, int row2) {
        this.anchorType = ClientAnchor.AnchorType.MOVE_AND_RESIZE;
        this.cell1 = CTMarker.Factory.newInstance();
        this.cell1.setCol(col1);
        this.cell1.setColOff(dx1);
        this.cell1.setRow(row1);
        this.cell1.setRowOff(dy1);
        this.cell2 = CTMarker.Factory.newInstance();
        this.cell2.setCol(col2);
        this.cell2.setColOff(dx2);
        this.cell2.setRow(row2);
        this.cell2.setRowOff(dy2);
    }

    protected XSSFClientAnchor(CTMarker cell1, CTMarker cell2) {
        this.anchorType = ClientAnchor.AnchorType.MOVE_AND_RESIZE;
        this.cell1 = cell1;
        this.cell2 = cell2;
    }

    protected XSSFClientAnchor(XSSFSheet sheet, CTMarker cell1, CTPositiveSize2D size) {
        this.anchorType = ClientAnchor.AnchorType.MOVE_DONT_RESIZE;
        this.sheet = sheet;
        this.size = size;
        this.cell1 = cell1;
    }

    protected XSSFClientAnchor(XSSFSheet sheet, CTPoint2D position, CTPositiveSize2D size) {
        this.anchorType = ClientAnchor.AnchorType.DONT_MOVE_AND_RESIZE;
        this.sheet = sheet;
        this.position = position;
        this.size = size;
    }

    private CTMarker calcCell(CTMarker cell, long w, long h) {
        long hPos;
        long wPos;
        CTMarker c2 = CTMarker.Factory.newInstance();
        int r = cell.getRow();
        int c = cell.getCol();
        int cw = Units.columnWidthToEMU(this.sheet.getColumnWidth(c));
        for (wPos = (long)cw - cell.getColOff(); wPos < w; wPos += (long)cw) {
            cw = Units.columnWidthToEMU(this.sheet.getColumnWidth(++c));
        }
        c2.setCol(c);
        c2.setColOff((long)cw - (wPos - w));
        int rh = Units.toEMU(XSSFClientAnchor.getRowHeight(this.sheet, r));
        for (hPos = (long)rh - cell.getRowOff(); hPos < h; hPos += (long)rh) {
            rh = Units.toEMU(XSSFClientAnchor.getRowHeight(this.sheet, ++r));
        }
        c2.setRow(r);
        c2.setRowOff((long)rh - (hPos - h));
        return c2;
    }

    private static float getRowHeight(XSSFSheet sheet, int row) {
        XSSFRow r = sheet.getRow(row);
        return r == null ? sheet.getDefaultRowHeightInPoints() : r.getHeightInPoints();
    }

    private CTMarker getCell1() {
        return this.cell1 != null ? this.cell1 : this.calcCell(EMPTY_MARKER, this.position.getX(), this.position.getY());
    }

    private CTMarker getCell2() {
        return this.cell2 != null ? this.cell2 : this.calcCell(this.getCell1(), this.size.getCx(), this.size.getCy());
    }

    @Override
    public short getCol1() {
        return (short)this.getCell1().getCol();
    }

    @Override
    public void setCol1(int col1) {
        this.cell1.setCol(col1);
    }

    @Override
    public short getCol2() {
        return (short)this.getCell2().getCol();
    }

    @Override
    public void setCol2(int col2) {
        this.cell2.setCol(col2);
    }

    @Override
    public int getRow1() {
        return this.getCell1().getRow();
    }

    @Override
    public void setRow1(int row1) {
        this.cell1.setRow(row1);
    }

    @Override
    public int getRow2() {
        return this.getCell2().getRow();
    }

    @Override
    public void setRow2(int row2) {
        this.cell2.setRow(row2);
    }

    @Override
    public int getDx1() {
        return Math.toIntExact(this.getCell1().getColOff());
    }

    @Override
    public void setDx1(int dx1) {
        this.cell1.setColOff(dx1);
    }

    @Override
    public int getDy1() {
        return Math.toIntExact(this.getCell1().getRowOff());
    }

    @Override
    public void setDy1(int dy1) {
        this.cell1.setRowOff(dy1);
    }

    @Override
    public int getDy2() {
        return Math.toIntExact(this.getCell2().getRowOff());
    }

    @Override
    public void setDy2(int dy2) {
        this.cell2.setRowOff(dy2);
    }

    @Override
    public int getDx2() {
        return Math.toIntExact(this.getCell2().getColOff());
    }

    @Override
    public void setDx2(int dx2) {
        this.cell2.setColOff(dx2);
    }

    public boolean equals(Object o) {
        if (o == null || !(o instanceof XSSFClientAnchor)) {
            return false;
        }
        XSSFClientAnchor anchor = (XSSFClientAnchor)o;
        return this.getDx1() == anchor.getDx1() && this.getDx2() == anchor.getDx2() && this.getDy1() == anchor.getDy1() && this.getDy2() == anchor.getDy2() && this.getCol1() == anchor.getCol1() && this.getCol2() == anchor.getCol2() && this.getRow1() == anchor.getRow1() && this.getRow2() == anchor.getRow2();
    }

    public int hashCode() {
        assert (false) : "hashCode not designed";
        return 42;
    }

    public String toString() {
        return "from : " + this.getCell1() + "; to: " + this.getCell2();
    }

    @Internal
    public CTMarker getFrom() {
        return this.getCell1();
    }

    protected void setFrom(CTMarker from) {
        this.cell1 = from;
    }

    @Internal
    public CTMarker getTo() {
        return this.getCell2();
    }

    protected void setTo(CTMarker to) {
        this.cell2 = to;
    }

    public CTPoint2D getPosition() {
        return this.position;
    }

    public void setPosition(CTPoint2D position) {
        this.position = position;
    }

    public CTPositiveSize2D getSize() {
        return this.size;
    }

    public void setSize(CTPositiveSize2D size) {
        this.size = size;
    }

    @Override
    public void setAnchorType(ClientAnchor.AnchorType anchorType) {
        this.anchorType = anchorType;
    }

    @Override
    public ClientAnchor.AnchorType getAnchorType() {
        return this.anchorType;
    }

    public boolean isSet() {
        CTMarker c1 = this.getCell1();
        CTMarker c2 = this.getCell2();
        return c1.getCol() != 0 || c2.getCol() != 0 || c1.getRow() != 0 || c2.getRow() != 0;
    }
}

