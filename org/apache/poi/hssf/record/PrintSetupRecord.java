/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.hssf.record;

import org.apache.poi.hssf.record.RecordInputStream;
import org.apache.poi.hssf.record.StandardRecord;
import org.apache.poi.util.BitField;
import org.apache.poi.util.BitFieldFactory;
import org.apache.poi.util.LittleEndianOutput;
import org.apache.poi.util.Removal;

public final class PrintSetupRecord
extends StandardRecord {
    public static final short sid = 161;
    private static final BitField lefttoright = BitFieldFactory.getInstance(1);
    private static final BitField landscape = BitFieldFactory.getInstance(2);
    private static final BitField validsettings = BitFieldFactory.getInstance(4);
    private static final BitField nocolor = BitFieldFactory.getInstance(8);
    private static final BitField draft = BitFieldFactory.getInstance(16);
    private static final BitField notes = BitFieldFactory.getInstance(32);
    private static final BitField noOrientation = BitFieldFactory.getInstance(64);
    private static final BitField usepage = BitFieldFactory.getInstance(128);
    private short field_1_paper_size;
    private short field_2_scale;
    private short field_3_page_start;
    private short field_4_fit_width;
    private short field_5_fit_height;
    private short field_6_options;
    private short field_7_hresolution;
    private short field_8_vresolution;
    private double field_9_headermargin;
    private double field_10_footermargin;
    private short field_11_copies;

    public PrintSetupRecord() {
    }

    public PrintSetupRecord(PrintSetupRecord other) {
        super(other);
        this.field_1_paper_size = other.field_1_paper_size;
        this.field_2_scale = other.field_2_scale;
        this.field_3_page_start = other.field_3_page_start;
        this.field_4_fit_width = other.field_4_fit_width;
        this.field_5_fit_height = other.field_5_fit_height;
        this.field_6_options = other.field_6_options;
        this.field_7_hresolution = other.field_7_hresolution;
        this.field_8_vresolution = other.field_8_vresolution;
        this.field_9_headermargin = other.field_9_headermargin;
        this.field_10_footermargin = other.field_10_footermargin;
        this.field_11_copies = other.field_11_copies;
    }

    public PrintSetupRecord(RecordInputStream in) {
        this.field_1_paper_size = in.readShort();
        this.field_2_scale = in.readShort();
        this.field_3_page_start = in.readShort();
        this.field_4_fit_width = in.readShort();
        this.field_5_fit_height = in.readShort();
        this.field_6_options = in.readShort();
        this.field_7_hresolution = in.readShort();
        this.field_8_vresolution = in.readShort();
        this.field_9_headermargin = in.readDouble();
        this.field_10_footermargin = in.readDouble();
        this.field_11_copies = in.readShort();
    }

    public void setPaperSize(short size) {
        this.field_1_paper_size = size;
    }

    public void setScale(short scale) {
        this.field_2_scale = scale;
    }

    public void setPageStart(short start) {
        this.field_3_page_start = start;
    }

    public void setFitWidth(short width) {
        this.field_4_fit_width = width;
    }

    public void setFitHeight(short height) {
        this.field_5_fit_height = height;
    }

    public void setOptions(short options) {
        this.field_6_options = options;
    }

    public void setLeftToRight(boolean ltor) {
        this.field_6_options = lefttoright.setShortBoolean(this.field_6_options, ltor);
    }

    public void setLandscape(boolean ls) {
        this.field_6_options = landscape.setShortBoolean(this.field_6_options, ls);
    }

    public void setValidSettings(boolean valid) {
        this.field_6_options = validsettings.setShortBoolean(this.field_6_options, valid);
    }

    public void setNoColor(boolean mono) {
        this.field_6_options = nocolor.setShortBoolean(this.field_6_options, mono);
    }

    public void setDraft(boolean d) {
        this.field_6_options = draft.setShortBoolean(this.field_6_options, d);
    }

    public void setNotes(boolean printnotes) {
        this.field_6_options = notes.setShortBoolean(this.field_6_options, printnotes);
    }

    public void setNoOrientation(boolean orientation) {
        this.field_6_options = noOrientation.setShortBoolean(this.field_6_options, orientation);
    }

    public void setUsePage(boolean page) {
        this.field_6_options = usepage.setShortBoolean(this.field_6_options, page);
    }

    public void setHResolution(short resolution) {
        this.field_7_hresolution = resolution;
    }

    public void setVResolution(short resolution) {
        this.field_8_vresolution = resolution;
    }

    public void setHeaderMargin(double headermargin) {
        this.field_9_headermargin = headermargin;
    }

    public void setFooterMargin(double footermargin) {
        this.field_10_footermargin = footermargin;
    }

    public void setCopies(short copies) {
        this.field_11_copies = copies;
    }

    public short getPaperSize() {
        return this.field_1_paper_size;
    }

    public short getScale() {
        return this.field_2_scale;
    }

    public short getPageStart() {
        return this.field_3_page_start;
    }

    public short getFitWidth() {
        return this.field_4_fit_width;
    }

    public short getFitHeight() {
        return this.field_5_fit_height;
    }

    public short getOptions() {
        return this.field_6_options;
    }

    public boolean getLeftToRight() {
        return lefttoright.isSet(this.field_6_options);
    }

    public boolean getLandscape() {
        return landscape.isSet(this.field_6_options);
    }

    public boolean getValidSettings() {
        return validsettings.isSet(this.field_6_options);
    }

    public boolean getNoColor() {
        return nocolor.isSet(this.field_6_options);
    }

    public boolean getDraft() {
        return draft.isSet(this.field_6_options);
    }

    public boolean getNotes() {
        return notes.isSet(this.field_6_options);
    }

    public boolean getNoOrientation() {
        return noOrientation.isSet(this.field_6_options);
    }

    public boolean getUsePage() {
        return usepage.isSet(this.field_6_options);
    }

    public short getHResolution() {
        return this.field_7_hresolution;
    }

    public short getVResolution() {
        return this.field_8_vresolution;
    }

    public double getHeaderMargin() {
        return this.field_9_headermargin;
    }

    public double getFooterMargin() {
        return this.field_10_footermargin;
    }

    public short getCopies() {
        return this.field_11_copies;
    }

    @Override
    public String toString() {
        StringBuilder buffer = new StringBuilder();
        buffer.append("[PRINTSETUP]\n");
        buffer.append("    .papersize      = ").append(this.getPaperSize()).append("\n");
        buffer.append("    .scale          = ").append(this.getScale()).append("\n");
        buffer.append("    .pagestart      = ").append(this.getPageStart()).append("\n");
        buffer.append("    .fitwidth       = ").append(this.getFitWidth()).append("\n");
        buffer.append("    .fitheight      = ").append(this.getFitHeight()).append("\n");
        buffer.append("    .options        = ").append(this.getOptions()).append("\n");
        buffer.append("        .ltor       = ").append(this.getLeftToRight()).append("\n");
        buffer.append("        .landscape  = ").append(this.getLandscape()).append("\n");
        buffer.append("        .valid      = ").append(this.getValidSettings()).append("\n");
        buffer.append("        .mono       = ").append(this.getNoColor()).append("\n");
        buffer.append("        .draft      = ").append(this.getDraft()).append("\n");
        buffer.append("        .notes      = ").append(this.getNotes()).append("\n");
        buffer.append("        .noOrientat = ").append(this.getNoOrientation()).append("\n");
        buffer.append("        .usepage    = ").append(this.getUsePage()).append("\n");
        buffer.append("    .hresolution    = ").append(this.getHResolution()).append("\n");
        buffer.append("    .vresolution    = ").append(this.getVResolution()).append("\n");
        buffer.append("    .headermargin   = ").append(this.getHeaderMargin()).append("\n");
        buffer.append("    .footermargin   = ").append(this.getFooterMargin()).append("\n");
        buffer.append("    .copies         = ").append(this.getCopies()).append("\n");
        buffer.append("[/PRINTSETUP]\n");
        return buffer.toString();
    }

    @Override
    public void serialize(LittleEndianOutput out) {
        out.writeShort(this.getPaperSize());
        out.writeShort(this.getScale());
        out.writeShort(this.getPageStart());
        out.writeShort(this.getFitWidth());
        out.writeShort(this.getFitHeight());
        out.writeShort(this.getOptions());
        out.writeShort(this.getHResolution());
        out.writeShort(this.getVResolution());
        out.writeDouble(this.getHeaderMargin());
        out.writeDouble(this.getFooterMargin());
        out.writeShort(this.getCopies());
    }

    @Override
    protected int getDataSize() {
        return 34;
    }

    @Override
    public short getSid() {
        return 161;
    }

    @Deprecated
    @Removal(version="5.0.0")
    public PrintSetupRecord clone() {
        return this.copy();
    }

    @Override
    public PrintSetupRecord copy() {
        return new PrintSetupRecord(this);
    }
}

