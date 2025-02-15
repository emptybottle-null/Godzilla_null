/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.xssf.usermodel;

import org.apache.poi.ooxml.POIXMLException;
import org.apache.poi.ss.usermodel.PageOrder;
import org.apache.poi.ss.usermodel.PaperSize;
import org.apache.poi.ss.usermodel.PrintCellComments;
import org.apache.poi.ss.usermodel.PrintOrientation;
import org.apache.poi.ss.usermodel.PrintSetup;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPageMargins;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPageSetup;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWorksheet;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.STCellComments;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.STOrientation;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.STPageOrder;

public class XSSFPrintSetup
implements PrintSetup {
    private CTWorksheet ctWorksheet;
    private CTPageSetup pageSetup;
    private CTPageMargins pageMargins;

    protected XSSFPrintSetup(CTWorksheet worksheet) {
        this.ctWorksheet = worksheet;
        this.pageSetup = this.ctWorksheet.isSetPageSetup() ? this.ctWorksheet.getPageSetup() : this.ctWorksheet.addNewPageSetup();
        this.pageMargins = this.ctWorksheet.isSetPageMargins() ? this.ctWorksheet.getPageMargins() : this.ctWorksheet.addNewPageMargins();
    }

    @Override
    public void setPaperSize(short size) {
        this.pageSetup.setPaperSize(size);
    }

    public void setPaperSize(PaperSize size) {
        this.setPaperSize((short)(size.ordinal() + 1));
    }

    @Override
    public void setScale(short scale) {
        if (scale < 10 || scale > 400) {
            throw new POIXMLException("Scale value not accepted: you must choose a value between 10 and 400.");
        }
        this.pageSetup.setScale(scale);
    }

    @Override
    public void setPageStart(short start) {
        this.pageSetup.setFirstPageNumber(start);
    }

    @Override
    public void setFitWidth(short width) {
        this.pageSetup.setFitToWidth(width);
    }

    @Override
    public void setFitHeight(short height) {
        this.pageSetup.setFitToHeight(height);
    }

    @Override
    public void setLeftToRight(boolean leftToRight) {
        if (leftToRight) {
            this.setPageOrder(PageOrder.OVER_THEN_DOWN);
        } else {
            this.setPageOrder(PageOrder.DOWN_THEN_OVER);
        }
    }

    @Override
    public void setLandscape(boolean ls) {
        if (ls) {
            this.setOrientation(PrintOrientation.LANDSCAPE);
        } else {
            this.setOrientation(PrintOrientation.PORTRAIT);
        }
    }

    @Override
    public void setValidSettings(boolean valid) {
        this.pageSetup.setUsePrinterDefaults(valid);
    }

    @Override
    public void setNoColor(boolean mono) {
        this.pageSetup.setBlackAndWhite(mono);
    }

    @Override
    public void setDraft(boolean d) {
        this.pageSetup.setDraft(d);
    }

    @Override
    public void setNotes(boolean printNotes) {
        if (printNotes) {
            this.pageSetup.setCellComments(STCellComments.AS_DISPLAYED);
        }
    }

    @Override
    public void setNoOrientation(boolean orientation) {
        if (orientation) {
            this.setOrientation(PrintOrientation.DEFAULT);
        }
    }

    @Override
    public void setUsePage(boolean page) {
        this.pageSetup.setUseFirstPageNumber(page);
    }

    @Override
    public void setHResolution(short resolution) {
        this.pageSetup.setHorizontalDpi(resolution);
    }

    @Override
    public void setVResolution(short resolution) {
        this.pageSetup.setVerticalDpi(resolution);
    }

    @Override
    public void setHeaderMargin(double headerMargin) {
        this.pageMargins.setHeader(headerMargin);
    }

    @Override
    public void setFooterMargin(double footerMargin) {
        this.pageMargins.setFooter(footerMargin);
    }

    @Override
    public void setCopies(short copies) {
        this.pageSetup.setCopies(copies);
    }

    public void setOrientation(PrintOrientation orientation) {
        STOrientation.Enum v = STOrientation.Enum.forInt(orientation.getValue());
        this.pageSetup.setOrientation(v);
    }

    public PrintOrientation getOrientation() {
        STOrientation.Enum val = this.pageSetup.getOrientation();
        return val == null ? PrintOrientation.DEFAULT : PrintOrientation.valueOf(val.intValue());
    }

    public PrintCellComments getCellComment() {
        STCellComments.Enum val = this.pageSetup.getCellComments();
        return val == null ? PrintCellComments.NONE : PrintCellComments.valueOf(val.intValue());
    }

    public void setPageOrder(PageOrder pageOrder) {
        STPageOrder.Enum v = STPageOrder.Enum.forInt(pageOrder.getValue());
        this.pageSetup.setPageOrder(v);
    }

    public PageOrder getPageOrder() {
        return this.pageSetup.getPageOrder() == null ? null : PageOrder.valueOf(this.pageSetup.getPageOrder().intValue());
    }

    @Override
    public short getPaperSize() {
        return (short)this.pageSetup.getPaperSize();
    }

    public PaperSize getPaperSizeEnum() {
        return PaperSize.values()[this.getPaperSize() - 1];
    }

    @Override
    public short getScale() {
        return (short)this.pageSetup.getScale();
    }

    @Override
    public short getPageStart() {
        return (short)this.pageSetup.getFirstPageNumber();
    }

    @Override
    public short getFitWidth() {
        return (short)this.pageSetup.getFitToWidth();
    }

    @Override
    public short getFitHeight() {
        return (short)this.pageSetup.getFitToHeight();
    }

    @Override
    public boolean getLeftToRight() {
        return this.getPageOrder() == PageOrder.OVER_THEN_DOWN;
    }

    @Override
    public boolean getLandscape() {
        return this.getOrientation() == PrintOrientation.LANDSCAPE;
    }

    @Override
    public boolean getValidSettings() {
        return this.pageSetup.getUsePrinterDefaults();
    }

    @Override
    public boolean getNoColor() {
        return this.pageSetup.getBlackAndWhite();
    }

    @Override
    public boolean getDraft() {
        return this.pageSetup.getDraft();
    }

    @Override
    public boolean getNotes() {
        return this.getCellComment() == PrintCellComments.AS_DISPLAYED;
    }

    @Override
    public boolean getNoOrientation() {
        return this.getOrientation() == PrintOrientation.DEFAULT;
    }

    @Override
    public boolean getUsePage() {
        return this.pageSetup.getUseFirstPageNumber();
    }

    @Override
    public short getHResolution() {
        return (short)this.pageSetup.getHorizontalDpi();
    }

    @Override
    public short getVResolution() {
        return (short)this.pageSetup.getVerticalDpi();
    }

    @Override
    public double getHeaderMargin() {
        return this.pageMargins.getHeader();
    }

    @Override
    public double getFooterMargin() {
        return this.pageMargins.getFooter();
    }

    @Override
    public short getCopies() {
        return (short)this.pageSetup.getCopies();
    }

    public void setTopMargin(double topMargin) {
        this.pageMargins.setTop(topMargin);
    }

    public double getTopMargin() {
        return this.pageMargins.getTop();
    }

    public void setBottomMargin(double bottomMargin) {
        this.pageMargins.setBottom(bottomMargin);
    }

    public double getBottomMargin() {
        return this.pageMargins.getBottom();
    }

    public void setLeftMargin(double leftMargin) {
        this.pageMargins.setLeft(leftMargin);
    }

    public double getLeftMargin() {
        return this.pageMargins.getLeft();
    }

    public void setRightMargin(double rightMargin) {
        this.pageMargins.setRight(rightMargin);
    }

    public double getRightMargin() {
        return this.pageMargins.getRight();
    }
}

