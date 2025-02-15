/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.hssf.usermodel;

import org.apache.poi.hssf.record.PrintSetupRecord;
import org.apache.poi.ss.usermodel.PrintSetup;

public class HSSFPrintSetup
implements PrintSetup {
    PrintSetupRecord printSetupRecord;

    protected HSSFPrintSetup(PrintSetupRecord printSetupRecord) {
        this.printSetupRecord = printSetupRecord;
    }

    @Override
    public void setPaperSize(short size) {
        this.printSetupRecord.setPaperSize(size);
    }

    @Override
    public void setScale(short scale) {
        this.printSetupRecord.setScale(scale);
    }

    @Override
    public void setPageStart(short start) {
        this.printSetupRecord.setPageStart(start);
    }

    @Override
    public void setFitWidth(short width) {
        this.printSetupRecord.setFitWidth(width);
    }

    @Override
    public void setFitHeight(short height) {
        this.printSetupRecord.setFitHeight(height);
    }

    public void setOptions(short options) {
        this.printSetupRecord.setOptions(options);
    }

    @Override
    public void setLeftToRight(boolean ltor) {
        this.printSetupRecord.setLeftToRight(ltor);
    }

    @Override
    public void setLandscape(boolean ls) {
        this.printSetupRecord.setLandscape(!ls);
    }

    @Override
    public void setValidSettings(boolean valid) {
        this.printSetupRecord.setValidSettings(valid);
    }

    @Override
    public void setNoColor(boolean mono) {
        this.printSetupRecord.setNoColor(mono);
    }

    @Override
    public void setDraft(boolean d) {
        this.printSetupRecord.setDraft(d);
    }

    @Override
    public void setNotes(boolean printnotes) {
        this.printSetupRecord.setNotes(printnotes);
    }

    @Override
    public void setNoOrientation(boolean orientation) {
        this.printSetupRecord.setNoOrientation(orientation);
    }

    @Override
    public void setUsePage(boolean page) {
        this.printSetupRecord.setUsePage(page);
    }

    @Override
    public void setHResolution(short resolution) {
        this.printSetupRecord.setHResolution(resolution);
    }

    @Override
    public void setVResolution(short resolution) {
        this.printSetupRecord.setVResolution(resolution);
    }

    @Override
    public void setHeaderMargin(double headermargin) {
        this.printSetupRecord.setHeaderMargin(headermargin);
    }

    @Override
    public void setFooterMargin(double footermargin) {
        this.printSetupRecord.setFooterMargin(footermargin);
    }

    @Override
    public void setCopies(short copies) {
        this.printSetupRecord.setCopies(copies);
    }

    @Override
    public short getPaperSize() {
        return this.printSetupRecord.getPaperSize();
    }

    @Override
    public short getScale() {
        return this.printSetupRecord.getScale();
    }

    @Override
    public short getPageStart() {
        return this.printSetupRecord.getPageStart();
    }

    @Override
    public short getFitWidth() {
        return this.printSetupRecord.getFitWidth();
    }

    @Override
    public short getFitHeight() {
        return this.printSetupRecord.getFitHeight();
    }

    public short getOptions() {
        return this.printSetupRecord.getOptions();
    }

    @Override
    public boolean getLeftToRight() {
        return this.printSetupRecord.getLeftToRight();
    }

    @Override
    public boolean getLandscape() {
        return !this.printSetupRecord.getLandscape();
    }

    @Override
    public boolean getValidSettings() {
        return this.printSetupRecord.getValidSettings();
    }

    @Override
    public boolean getNoColor() {
        return this.printSetupRecord.getNoColor();
    }

    @Override
    public boolean getDraft() {
        return this.printSetupRecord.getDraft();
    }

    @Override
    public boolean getNotes() {
        return this.printSetupRecord.getNotes();
    }

    @Override
    public boolean getNoOrientation() {
        return this.printSetupRecord.getNoOrientation();
    }

    @Override
    public boolean getUsePage() {
        return this.printSetupRecord.getUsePage();
    }

    @Override
    public short getHResolution() {
        return this.printSetupRecord.getHResolution();
    }

    @Override
    public short getVResolution() {
        return this.printSetupRecord.getVResolution();
    }

    @Override
    public double getHeaderMargin() {
        return this.printSetupRecord.getHeaderMargin();
    }

    @Override
    public double getFooterMargin() {
        return this.printSetupRecord.getFooterMargin();
    }

    @Override
    public short getCopies() {
        return this.printSetupRecord.getCopies();
    }
}

