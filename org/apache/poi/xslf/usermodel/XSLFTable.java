/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.xslf.usermodel;

import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import javax.xml.namespace.QName;
import org.apache.poi.sl.draw.DrawFactory;
import org.apache.poi.sl.draw.DrawTextShape;
import org.apache.poi.sl.usermodel.TableShape;
import org.apache.poi.util.Internal;
import org.apache.poi.util.Units;
import org.apache.poi.xddf.usermodel.text.XDDFTextBody;
import org.apache.poi.xslf.usermodel.XSLFGraphicFrame;
import org.apache.poi.xslf.usermodel.XSLFShape;
import org.apache.poi.xslf.usermodel.XSLFSheet;
import org.apache.poi.xslf.usermodel.XSLFTableCell;
import org.apache.poi.xslf.usermodel.XSLFTableRow;
import org.apache.poi.xslf.usermodel.XSLFTableStyle;
import org.apache.poi.xslf.usermodel.XSLFTableStyles;
import org.apache.poi.xslf.usermodel.XSLFTextParagraph;
import org.apache.xmlbeans.XmlCursor;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.values.XmlAnyTypeImpl;
import org.openxmlformats.schemas.drawingml.x2006.main.CTGraphicalObjectData;
import org.openxmlformats.schemas.drawingml.x2006.main.CTNonVisualDrawingProps;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTable;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTableCol;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTableRow;
import org.openxmlformats.schemas.presentationml.x2006.main.CTGraphicalObjectFrame;
import org.openxmlformats.schemas.presentationml.x2006.main.CTGraphicalObjectFrameNonVisual;

public class XSLFTable
extends XSLFGraphicFrame
implements Iterable<XSLFTableRow>,
TableShape<XSLFShape, XSLFTextParagraph> {
    static final String TABLE_URI = "http://schemas.openxmlformats.org/drawingml/2006/table";
    private CTTable _table;
    private List<XSLFTableRow> _rows;

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    XSLFTable(CTGraphicalObjectFrame shape, XSLFSheet sheet) {
        super(shape, sheet);
        CTGraphicalObjectData god = shape.getGraphic().getGraphicData();
        XmlCursor xc = god.newCursor();
        try {
            if (!xc.toChild("http://schemas.openxmlformats.org/drawingml/2006/main", "tbl")) {
                throw new IllegalStateException("a:tbl element was not found in\n " + god);
            }
            XmlObject xo = xc.getObject();
            if (xo instanceof XmlAnyTypeImpl) {
                String errStr = "Schemas (*.xsb) for CTTable can't be loaded - usually this happens when OSGI loading is used and the thread context classloader has no reference to the xmlbeans classes";
                throw new IllegalStateException(errStr);
            }
            this._table = (CTTable)xo;
        } finally {
            xc.dispose();
        }
        this._rows = new ArrayList<XSLFTableRow>(this._table.sizeOfTrArray());
        for (CTTableRow row : this._table.getTrList()) {
            this._rows.add(new XSLFTableRow(row, this));
        }
        this.updateRowColIndexes();
    }

    public XSLFTableCell getCell(int row, int col) {
        List<XSLFTableRow> rows = this.getRows();
        if (row < 0 || rows.size() <= row) {
            return null;
        }
        XSLFTableRow r = rows.get(row);
        if (r == null) {
            return null;
        }
        List<XSLFTableCell> cells = r.getCells();
        if (col < 0 || cells.size() <= col) {
            return null;
        }
        return cells.get(col);
    }

    @Internal
    public CTTable getCTTable() {
        return this._table;
    }

    @Override
    public int getNumberOfColumns() {
        return this._table.getTblGrid().sizeOfGridColArray();
    }

    @Override
    public int getNumberOfRows() {
        return this._table.sizeOfTrArray();
    }

    @Override
    public double getColumnWidth(int idx) {
        return Units.toPoints(this._table.getTblGrid().getGridColArray(idx).getW());
    }

    @Override
    public void setColumnWidth(int idx, double width) {
        this._table.getTblGrid().getGridColArray(idx).setW(Units.toEMU(width));
    }

    @Override
    public double getRowHeight(int row) {
        return Units.toPoints(this._table.getTrArray(row).getH());
    }

    @Override
    public void setRowHeight(int row, double height) {
        this._table.getTrArray(row).setH(Units.toEMU(height));
    }

    @Override
    public Iterator<XSLFTableRow> iterator() {
        return this._rows.iterator();
    }

    public List<XSLFTableRow> getRows() {
        return Collections.unmodifiableList(this._rows);
    }

    public XSLFTableRow addRow() {
        CTTableRow tr = this._table.addNewTr();
        XSLFTableRow row = new XSLFTableRow(tr, this);
        row.setHeight(20.0);
        this._rows.add(row);
        this.updateRowColIndexes();
        return row;
    }

    public void removeRow(int rowIdx) {
        this._table.removeTr(rowIdx);
        this._rows.remove(rowIdx);
        this.updateRowColIndexes();
    }

    public void addColumn() {
        long width = this._table.getTblGrid().getGridColArray(this._table.getTblGrid().sizeOfGridColArray() - 1).getW();
        CTTableCol col = this._table.getTblGrid().addNewGridCol();
        col.setW(width);
        for (XSLFTableRow row : this._rows) {
            XSLFTableCell cell = row.addCell();
            new XDDFTextBody(cell, cell.getTextBody(true)).initialize();
        }
        this.updateRowColIndexes();
    }

    public void insertColumn(int colIdx) {
        if (this._table.getTblGrid().sizeOfGridColArray() < colIdx) {
            throw new IndexOutOfBoundsException("Cannot insert column at " + colIdx + "; table has only " + this._table.getTblGrid().sizeOfGridColArray() + "columns.");
        }
        long width = this._table.getTblGrid().getGridColArray(colIdx).getW();
        CTTableCol col = this._table.getTblGrid().insertNewGridCol(colIdx);
        col.setW(width);
        for (XSLFTableRow row : this._rows) {
            XSLFTableCell cell = row.insertCell(colIdx);
            new XDDFTextBody(cell, cell.getTextBody(true)).initialize();
        }
        this.updateRowColIndexes();
    }

    public void removeColumn(int colIdx) {
        this._table.getTblGrid().removeGridCol(colIdx);
        for (XSLFTableRow row : this._rows) {
            row.removeCell(colIdx);
        }
        this.updateRowColIndexes();
    }

    static CTGraphicalObjectFrame prototype(int shapeId) {
        CTGraphicalObjectFrame frame = CTGraphicalObjectFrame.Factory.newInstance();
        CTGraphicalObjectFrameNonVisual nvGr = frame.addNewNvGraphicFramePr();
        CTNonVisualDrawingProps cnv = nvGr.addNewCNvPr();
        cnv.setName("Table " + shapeId);
        cnv.setId(shapeId);
        nvGr.addNewCNvGraphicFramePr().addNewGraphicFrameLocks().setNoGrp(true);
        nvGr.addNewNvPr();
        frame.addNewXfrm();
        CTGraphicalObjectData gr = frame.addNewGraphic().addNewGraphicData();
        XmlCursor grCur = gr.newCursor();
        grCur.toNextToken();
        grCur.beginElement(new QName("http://schemas.openxmlformats.org/drawingml/2006/main", "tbl"));
        CTTable tbl = CTTable.Factory.newInstance();
        tbl.addNewTblPr();
        tbl.addNewTblGrid();
        XmlCursor tblCur = tbl.newCursor();
        tblCur.moveXmlContents(grCur);
        tblCur.dispose();
        grCur.dispose();
        gr.setUri(TABLE_URI);
        return frame;
    }

    public void mergeCells(int firstRow, int lastRow, int firstCol, int lastCol) {
        if (firstRow > lastRow) {
            throw new IllegalArgumentException("Cannot merge, first row > last row : " + firstRow + " > " + lastRow);
        }
        if (firstCol > lastCol) {
            throw new IllegalArgumentException("Cannot merge, first column > last column : " + firstCol + " > " + lastCol);
        }
        int rowSpan = lastRow - firstRow + 1;
        boolean mergeRowRequired = rowSpan > 1;
        int colSpan = lastCol - firstCol + 1;
        boolean mergeColumnRequired = colSpan > 1;
        for (int i = firstRow; i <= lastRow; ++i) {
            XSLFTableRow row = this._rows.get(i);
            for (int colPos = firstCol; colPos <= lastCol; ++colPos) {
                XSLFTableCell cell = row.getCells().get(colPos);
                if (mergeRowRequired) {
                    if (i == firstRow) {
                        cell.setRowSpan(rowSpan);
                    } else {
                        cell.setVMerge();
                    }
                }
                if (!mergeColumnRequired) continue;
                if (colPos == firstCol) {
                    cell.setGridSpan(colSpan);
                    continue;
                }
                cell.setHMerge();
            }
        }
    }

    protected XSLFTableStyle getTableStyle() {
        CTTable tab = this.getCTTable();
        if (!tab.isSetTblPr() || !tab.getTblPr().isSetTableStyleId()) {
            return null;
        }
        String styleId = tab.getTblPr().getTableStyleId();
        XSLFTableStyles styles = this.getSheet().getSlideShow().getTableStyles();
        for (XSLFTableStyle style : styles.getStyles()) {
            if (!style.getStyleId().equals(styleId)) continue;
            return style;
        }
        return null;
    }

    void updateRowColIndexes() {
        int rowIdx = 0;
        for (XSLFTableRow xr : this) {
            int colIdx = 0;
            for (XSLFTableCell tc : xr) {
                tc.setRowColIndex(rowIdx, colIdx);
                ++colIdx;
            }
            ++rowIdx;
        }
    }

    public void updateCellAnchor() {
        int row;
        int rows = this.getNumberOfRows();
        int cols = this.getNumberOfColumns();
        double[] colWidths = new double[cols];
        double[] rowHeights = new double[rows];
        for (int row2 = 0; row2 < rows; ++row2) {
            rowHeights[row2] = this.getRowHeight(row2);
        }
        for (int col = 0; col < cols; ++col) {
            colWidths[col] = this.getColumnWidth(col);
        }
        Rectangle2D tblAnc = this.getAnchor();
        DrawFactory df = DrawFactory.getInstance(null);
        double nextY = tblAnc.getY();
        double nextX = tblAnc.getX();
        for (row = 0; row < rows; ++row) {
            double maxHeight = 0.0;
            for (int col = 0; col < cols; ++col) {
                XSLFTableCell tc = this.getCell(row, col);
                if (tc == null || tc.getGridSpan() != 1 || tc.getRowSpan() != 1) continue;
                tc.setAnchor(new Rectangle2D.Double(0.0, 0.0, colWidths[col], 0.0));
                DrawTextShape dts = df.getDrawable(tc);
                maxHeight = Math.max(maxHeight, dts.getTextHeight());
            }
            rowHeights[row] = Math.max(rowHeights[row], maxHeight);
        }
        for (row = 0; row < rows; ++row) {
            nextX = tblAnc.getX();
            for (int col = 0; col < cols; ++col) {
                Rectangle2D.Double bounds = new Rectangle2D.Double(nextX, nextY, colWidths[col], rowHeights[row]);
                XSLFTableCell tc = this.getCell(row, col);
                if (tc == null) continue;
                tc.setAnchor(bounds);
                nextX += colWidths[col] + 2.0;
            }
            nextY += rowHeights[row] + 2.0;
        }
        for (row = 0; row < rows; ++row) {
            for (int col = 0; col < cols; ++col) {
                XSLFTableCell tc2;
                XSLFTableCell tc = this.getCell(row, col);
                if (tc == null) continue;
                Rectangle2D mergedBounds = tc.getAnchor();
                for (int col2 = col + 1; col2 < col + tc.getGridSpan(); ++col2) {
                    assert (col2 < cols);
                    tc2 = this.getCell(row, col2);
                    assert (tc2.getGridSpan() == 1 && tc2.getRowSpan() == 1);
                    mergedBounds.add(tc2.getAnchor());
                }
                for (int row2 = row + 1; row2 < row + tc.getRowSpan(); ++row2) {
                    assert (row2 < rows);
                    tc2 = this.getCell(row2, col);
                    assert (tc2.getGridSpan() == 1 && tc2.getRowSpan() == 1);
                    mergedBounds.add(tc2.getAnchor());
                }
                tc.setAnchor(mergedBounds);
            }
        }
        this.setAnchor(new Rectangle2D.Double(tblAnc.getX(), tblAnc.getY(), nextX - tblAnc.getX(), nextY - tblAnc.getY()));
    }
}

