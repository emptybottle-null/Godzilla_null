/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.xssf.usermodel.helpers;

import org.apache.poi.ss.util.CellReference;
import org.apache.poi.xssf.model.SingleXmlCells;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTSingleXmlCell;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTXmlCellPr;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTXmlPr;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.STXmlDataType;

public class XSSFSingleXmlCell {
    private CTSingleXmlCell singleXmlCell;
    private SingleXmlCells parent;

    public XSSFSingleXmlCell(CTSingleXmlCell singleXmlCell, SingleXmlCells parent) {
        this.singleXmlCell = singleXmlCell;
        this.parent = parent;
    }

    public XSSFCell getReferencedCell() {
        XSSFCell cell = null;
        CellReference cellReference = new CellReference(this.singleXmlCell.getR());
        XSSFRow row = this.parent.getXSSFSheet().getRow(cellReference.getRow());
        if (row == null) {
            row = this.parent.getXSSFSheet().createRow(cellReference.getRow());
        }
        if ((cell = row.getCell(cellReference.getCol())) == null) {
            cell = row.createCell(cellReference.getCol());
        }
        return cell;
    }

    public String getXpath() {
        CTXmlCellPr xmlCellPr = this.singleXmlCell.getXmlCellPr();
        CTXmlPr xmlPr = xmlCellPr.getXmlPr();
        return xmlPr.getXpath();
    }

    public long getMapId() {
        return this.singleXmlCell.getXmlCellPr().getXmlPr().getMapId();
    }

    public STXmlDataType.Enum getXmlDataType() {
        CTXmlCellPr xmlCellPr = this.singleXmlCell.getXmlCellPr();
        CTXmlPr xmlPr = xmlCellPr.getXmlPr();
        return xmlPr.getXmlDataType();
    }
}

