/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.hssf.usermodel;

import org.apache.poi.ddf.EscherClientAnchorRecord;
import org.apache.poi.ddf.EscherRecord;
import org.apache.poi.hssf.usermodel.HSSFAnchor;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.ss.SpreadsheetVersion;
import org.apache.poi.ss.usermodel.ClientAnchor;

public final class HSSFClientAnchor
extends HSSFAnchor
implements ClientAnchor {
    public static final int MAX_COL = SpreadsheetVersion.EXCEL97.getLastColumnIndex();
    public static final int MAX_ROW = SpreadsheetVersion.EXCEL97.getLastRowIndex();
    private EscherClientAnchorRecord _escherClientAnchor;

    public HSSFClientAnchor(EscherClientAnchorRecord escherClientAnchorRecord) {
        this._escherClientAnchor = escherClientAnchorRecord;
    }

    public HSSFClientAnchor() {
    }

    public HSSFClientAnchor(int dx1, int dy1, int dx2, int dy2, short col1, int row1, short col2, int row2) {
        super(dx1, dy1, dx2, dy2);
        this.checkRange(dx1, 0, 1023, "dx1");
        this.checkRange(dx2, 0, 1023, "dx2");
        this.checkRange(dy1, 0, 255, "dy1");
        this.checkRange(dy2, 0, 255, "dy2");
        this.checkRange(col1, 0, MAX_COL, "col1");
        this.checkRange(col2, 0, MAX_COL, "col2");
        this.checkRange(row1, 0, MAX_ROW, "row1");
        this.checkRange(row2, 0, MAX_ROW, "row2");
        this.setCol1((short)Math.min(col1, col2));
        this.setCol2((short)Math.max(col1, col2));
        this.setRow1(Math.min(row1, row2));
        this.setRow2(Math.max(row1, row2));
        if (col1 > col2) {
            this._isHorizontallyFlipped = true;
        }
        if (row1 > row2) {
            this._isVerticallyFlipped = true;
        }
    }

    public float getAnchorHeightInPoints(HSSFSheet sheet) {
        int y1 = this.getDy1();
        int y2 = this.getDy2();
        int row1 = Math.min(this.getRow1(), this.getRow2());
        int row2 = Math.max(this.getRow1(), this.getRow2());
        float points = 0.0f;
        if (row1 == row2) {
            points = (float)(y2 - y1) / 256.0f * this.getRowHeightInPoints(sheet, row2);
        } else {
            points += (256.0f - (float)y1) / 256.0f * this.getRowHeightInPoints(sheet, row1);
            for (int i = row1 + 1; i < row2; ++i) {
                points += this.getRowHeightInPoints(sheet, i);
            }
            points += (float)y2 / 256.0f * this.getRowHeightInPoints(sheet, row2);
        }
        return points;
    }

    private float getRowHeightInPoints(HSSFSheet sheet, int rowNum) {
        HSSFRow row = sheet.getRow(rowNum);
        if (row == null) {
            return sheet.getDefaultRowHeightInPoints();
        }
        return row.getHeightInPoints();
    }

    @Override
    public short getCol1() {
        return this._escherClientAnchor.getCol1();
    }

    public void setCol1(short col1) {
        this.checkRange(col1, 0, MAX_COL, "col1");
        this._escherClientAnchor.setCol1(col1);
    }

    @Override
    public void setCol1(int col1) {
        this.setCol1((short)col1);
    }

    @Override
    public short getCol2() {
        return this._escherClientAnchor.getCol2();
    }

    public void setCol2(short col2) {
        this.checkRange(col2, 0, MAX_COL, "col2");
        this._escherClientAnchor.setCol2(col2);
    }

    @Override
    public void setCol2(int col2) {
        this.setCol2((short)col2);
    }

    @Override
    public int getRow1() {
        return HSSFClientAnchor.unsignedValue(this._escherClientAnchor.getRow1());
    }

    @Override
    public void setRow1(int row1) {
        this.checkRange(row1, 0, MAX_ROW, "row1");
        this._escherClientAnchor.setRow1(Integer.valueOf(row1).shortValue());
    }

    @Override
    public int getRow2() {
        return HSSFClientAnchor.unsignedValue(this._escherClientAnchor.getRow2());
    }

    @Override
    public void setRow2(int row2) {
        this.checkRange(row2, 0, MAX_ROW, "row2");
        this._escherClientAnchor.setRow2(Integer.valueOf(row2).shortValue());
    }

    public void setAnchor(short col1, int row1, int x1, int y1, short col2, int row2, int x2, int y2) {
        this.checkRange(this.getDx1(), 0, 1023, "dx1");
        this.checkRange(this.getDx2(), 0, 1023, "dx2");
        this.checkRange(this.getDy1(), 0, 255, "dy1");
        this.checkRange(this.getDy2(), 0, 255, "dy2");
        this.checkRange(this.getCol1(), 0, MAX_COL, "col1");
        this.checkRange(this.getCol2(), 0, MAX_COL, "col2");
        this.checkRange(this.getRow1(), 0, MAX_ROW, "row1");
        this.checkRange(this.getRow2(), 0, MAX_ROW, "row2");
        this.setCol1(col1);
        this.setRow1(row1);
        this.setDx1(x1);
        this.setDy1(y1);
        this.setCol2(col2);
        this.setRow2(row2);
        this.setDx2(x2);
        this.setDy2(y2);
    }

    @Override
    public boolean isHorizontallyFlipped() {
        return this._isHorizontallyFlipped;
    }

    @Override
    public boolean isVerticallyFlipped() {
        return this._isVerticallyFlipped;
    }

    @Override
    protected EscherRecord getEscherAnchor() {
        return this._escherClientAnchor;
    }

    @Override
    protected void createEscherAnchor() {
        this._escherClientAnchor = new EscherClientAnchorRecord();
    }

    @Override
    public ClientAnchor.AnchorType getAnchorType() {
        return ClientAnchor.AnchorType.byId(this._escherClientAnchor.getFlag());
    }

    @Override
    public void setAnchorType(ClientAnchor.AnchorType anchorType) {
        this._escherClientAnchor.setFlag(anchorType.value);
    }

    private void checkRange(int value, int minRange, int maxRange, String varName) {
        if (value < minRange || value > maxRange) {
            throw new IllegalArgumentException(varName + " must be between " + minRange + " and " + maxRange + ", but was: " + value);
        }
    }

    private static int unsignedValue(short s) {
        return s < 0 ? 65536 + s : s;
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        if (obj.getClass() != this.getClass()) {
            return false;
        }
        HSSFClientAnchor anchor = (HSSFClientAnchor)obj;
        return anchor.getCol1() == this.getCol1() && anchor.getCol2() == this.getCol2() && anchor.getDx1() == this.getDx1() && anchor.getDx2() == this.getDx2() && anchor.getDy1() == this.getDy1() && anchor.getDy2() == this.getDy2() && anchor.getRow1() == this.getRow1() && anchor.getRow2() == this.getRow2() && anchor.getAnchorType() == this.getAnchorType();
    }

    public int hashCode() {
        assert (false) : "hashCode not designed";
        return 42;
    }

    @Override
    public int getDx1() {
        return this._escherClientAnchor.getDx1();
    }

    @Override
    public void setDx1(int dx1) {
        this._escherClientAnchor.setDx1(Integer.valueOf(dx1).shortValue());
    }

    @Override
    public int getDy1() {
        return this._escherClientAnchor.getDy1();
    }

    @Override
    public void setDy1(int dy1) {
        this._escherClientAnchor.setDy1(Integer.valueOf(dy1).shortValue());
    }

    @Override
    public int getDy2() {
        return this._escherClientAnchor.getDy2();
    }

    @Override
    public void setDy2(int dy2) {
        this._escherClientAnchor.setDy2(Integer.valueOf(dy2).shortValue());
    }

    @Override
    public int getDx2() {
        return this._escherClientAnchor.getDx2();
    }

    @Override
    public void setDx2(int dx2) {
        this._escherClientAnchor.setDx2(Integer.valueOf(dx2).shortValue());
    }
}

