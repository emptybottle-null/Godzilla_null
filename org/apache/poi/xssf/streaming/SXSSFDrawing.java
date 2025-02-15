/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.xssf.streaming;

import java.util.Iterator;
import org.apache.poi.ss.usermodel.ClientAnchor;
import org.apache.poi.ss.usermodel.Comment;
import org.apache.poi.ss.usermodel.Drawing;
import org.apache.poi.ss.usermodel.ObjectData;
import org.apache.poi.xssf.streaming.SXSSFPicture;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFDrawing;
import org.apache.poi.xssf.usermodel.XSSFPicture;
import org.apache.poi.xssf.usermodel.XSSFShape;

public class SXSSFDrawing
implements Drawing<XSSFShape> {
    private final SXSSFWorkbook _wb;
    private final XSSFDrawing _drawing;

    public SXSSFDrawing(SXSSFWorkbook workbook, XSSFDrawing drawing) {
        this._wb = workbook;
        this._drawing = drawing;
    }

    @Override
    public SXSSFPicture createPicture(ClientAnchor anchor, int pictureIndex) {
        XSSFPicture pict = this._drawing.createPicture(anchor, pictureIndex);
        return new SXSSFPicture(this._wb, pict);
    }

    @Override
    public Comment createCellComment(ClientAnchor anchor) {
        return this._drawing.createCellComment(anchor);
    }

    @Override
    public ClientAnchor createAnchor(int dx1, int dy1, int dx2, int dy2, int col1, int row1, int col2, int row2) {
        return this._drawing.createAnchor(dx1, dy1, dx2, dy2, col1, row1, col2, row2);
    }

    @Override
    public ObjectData createObjectData(ClientAnchor anchor, int storageId, int pictureIndex) {
        return this._drawing.createObjectData(anchor, storageId, pictureIndex);
    }

    @Override
    public Iterator<XSSFShape> iterator() {
        return this._drawing.getShapes().iterator();
    }
}

