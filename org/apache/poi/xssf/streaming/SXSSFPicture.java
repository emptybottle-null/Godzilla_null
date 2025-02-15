/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.xssf.streaming;

import java.awt.Dimension;
import java.io.IOException;
import org.apache.poi.openxml4j.opc.PackagePart;
import org.apache.poi.ss.usermodel.Picture;
import org.apache.poi.ss.usermodel.Shape;
import org.apache.poi.ss.util.ImageUtils;
import org.apache.poi.util.Internal;
import org.apache.poi.util.POILogFactory;
import org.apache.poi.util.POILogger;
import org.apache.poi.xssf.streaming.SXSSFRow;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFAnchor;
import org.apache.poi.xssf.usermodel.XSSFClientAnchor;
import org.apache.poi.xssf.usermodel.XSSFDrawing;
import org.apache.poi.xssf.usermodel.XSSFPicture;
import org.apache.poi.xssf.usermodel.XSSFPictureData;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.openxmlformats.schemas.drawingml.x2006.main.CTPositiveSize2D;
import org.openxmlformats.schemas.drawingml.x2006.main.CTShapeProperties;
import org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.CTPicture;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCol;

public final class SXSSFPicture
implements Picture {
    private static final POILogger logger = POILogFactory.getLogger(SXSSFPicture.class);
    private static float DEFAULT_COLUMN_WIDTH = 9.140625f;
    private final SXSSFWorkbook _wb;
    private final XSSFPicture _picture;

    SXSSFPicture(SXSSFWorkbook _wb, XSSFPicture _picture) {
        this._wb = _wb;
        this._picture = _picture;
    }

    @Internal
    public CTPicture getCTPicture() {
        return this._picture.getCTPicture();
    }

    @Override
    public void resize() {
        this.resize(1.0);
    }

    @Override
    public void resize(double scale) {
        XSSFClientAnchor anchor = this.getClientAnchor();
        XSSFClientAnchor pref = this.getPreferredSize(scale);
        if (anchor == null || pref == null) {
            logger.log(5, "picture is not anchored via client anchor - ignoring resize call");
            return;
        }
        int row2 = anchor.getRow1() + (pref.getRow2() - pref.getRow1());
        int col2 = anchor.getCol1() + (pref.getCol2() - pref.getCol1());
        anchor.setCol2(col2);
        anchor.setDx1(0);
        anchor.setDx2(pref.getDx2());
        anchor.setRow2(row2);
        anchor.setDy1(0);
        anchor.setDy2(pref.getDy2());
    }

    @Override
    public XSSFClientAnchor getPreferredSize() {
        return this.getPreferredSize(1.0);
    }

    public XSSFClientAnchor getPreferredSize(double scale) {
        double h;
        XSSFClientAnchor anchor = this.getClientAnchor();
        if (anchor == null) {
            logger.log(5, "picture is not anchored via client anchor - ignoring resize call");
            return null;
        }
        XSSFPictureData data = this.getPictureData();
        Dimension size = SXSSFPicture.getImageDimension(data.getPackagePart(), data.getPictureType());
        double scaledWidth = size.getWidth() * scale;
        double scaledHeight = size.getHeight() * scale;
        float w = 0.0f;
        int col2 = anchor.getCol1() - 1;
        while ((double)w <= scaledWidth) {
            w += this.getColumnWidthInPixels(++col2);
        }
        assert ((double)w > scaledWidth);
        double cw = this.getColumnWidthInPixels(col2);
        double deltaW = (double)w - scaledWidth;
        int dx2 = (int)(9525.0 * (cw - deltaW));
        anchor.setCol2(col2);
        anchor.setDx2(dx2);
        int row2 = anchor.getRow1() - 1;
        for (h = 0.0; h <= scaledHeight; h += (double)this.getRowHeightInPixels(++row2)) {
        }
        assert (h > scaledHeight);
        double ch = this.getRowHeightInPixels(row2);
        double deltaH = h - scaledHeight;
        int dy2 = (int)(9525.0 * (ch - deltaH));
        anchor.setRow2(row2);
        anchor.setDy2(dy2);
        CTPositiveSize2D size2d = this.getCTPicture().getSpPr().getXfrm().getExt();
        size2d.setCx((long)(scaledWidth * 9525.0));
        size2d.setCy((long)(scaledHeight * 9525.0));
        return anchor;
    }

    private float getColumnWidthInPixels(int columnIndex) {
        XSSFSheet sheet = this.getSheet();
        CTCol col = sheet.getColumnHelper().getColumn(columnIndex, false);
        double numChars = col == null || !col.isSetWidth() ? (double)DEFAULT_COLUMN_WIDTH : col.getWidth();
        return (float)numChars * 7.0017f;
    }

    private float getRowHeightInPixels(int rowIndex) {
        XSSFSheet xssfSheet = this.getSheet();
        SXSSFSheet sheet = this._wb.getSXSSFSheet(xssfSheet);
        SXSSFRow row = sheet.getRow(rowIndex);
        float height = row != null ? row.getHeightInPoints() : sheet.getDefaultRowHeightInPoints();
        return height * 96.0f / 72.0f;
    }

    protected static Dimension getImageDimension(PackagePart part, int type) {
        try {
            return ImageUtils.getImageDimension(part.getInputStream(), type);
        } catch (IOException e) {
            logger.log(5, e);
            return new Dimension();
        }
    }

    @Override
    public XSSFPictureData getPictureData() {
        return this._picture.getPictureData();
    }

    protected CTShapeProperties getShapeProperties() {
        return this.getCTPicture().getSpPr();
    }

    @Override
    public XSSFAnchor getAnchor() {
        return this._picture.getAnchor();
    }

    @Override
    public void resize(double scaleX, double scaleY) {
        this._picture.resize(scaleX, scaleY);
    }

    @Override
    public XSSFClientAnchor getPreferredSize(double scaleX, double scaleY) {
        return this._picture.getPreferredSize(scaleX, scaleY);
    }

    @Override
    public Dimension getImageDimension() {
        return this._picture.getImageDimension();
    }

    @Override
    public XSSFClientAnchor getClientAnchor() {
        XSSFAnchor a = this.getAnchor();
        return a instanceof XSSFClientAnchor ? (XSSFClientAnchor)a : null;
    }

    public XSSFDrawing getDrawing() {
        return this._picture.getDrawing();
    }

    @Override
    public XSSFSheet getSheet() {
        return this._picture.getSheet();
    }

    @Override
    public String getShapeName() {
        return this._picture.getShapeName();
    }

    @Override
    public Shape getParent() {
        return this._picture.getParent();
    }

    @Override
    public boolean isNoFill() {
        return this._picture.isNoFill();
    }

    @Override
    public void setNoFill(boolean noFill) {
        this._picture.setNoFill(noFill);
    }

    @Override
    public void setFillColor(int red, int green, int blue) {
        this._picture.setFillColor(red, green, blue);
    }

    @Override
    public void setLineStyleColor(int red, int green, int blue) {
        this._picture.setLineStyleColor(red, green, blue);
    }
}

