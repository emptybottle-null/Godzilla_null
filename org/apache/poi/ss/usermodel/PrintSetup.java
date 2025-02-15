/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.ss.usermodel;

public interface PrintSetup {
    public static final short PRINTER_DEFAULT_PAPERSIZE = 0;
    public static final short LETTER_PAPERSIZE = 1;
    public static final short LETTER_SMALL_PAGESIZE = 2;
    public static final short TABLOID_PAPERSIZE = 3;
    public static final short LEDGER_PAPERSIZE = 4;
    public static final short LEGAL_PAPERSIZE = 5;
    public static final short STATEMENT_PAPERSIZE = 6;
    public static final short EXECUTIVE_PAPERSIZE = 7;
    public static final short A3_PAPERSIZE = 8;
    public static final short A4_PAPERSIZE = 9;
    public static final short A4_SMALL_PAPERSIZE = 10;
    public static final short A5_PAPERSIZE = 11;
    public static final short B4_PAPERSIZE = 12;
    public static final short B5_PAPERSIZE = 13;
    public static final short FOLIO8_PAPERSIZE = 14;
    public static final short QUARTO_PAPERSIZE = 15;
    public static final short TEN_BY_FOURTEEN_PAPERSIZE = 16;
    public static final short ELEVEN_BY_SEVENTEEN_PAPERSIZE = 17;
    public static final short NOTE8_PAPERSIZE = 18;
    public static final short ENVELOPE_9_PAPERSIZE = 19;
    public static final short ENVELOPE_10_PAPERSIZE = 20;
    public static final short ENVELOPE_DL_PAPERSIZE = 27;
    public static final short ENVELOPE_CS_PAPERSIZE = 28;
    public static final short ENVELOPE_C5_PAPERSIZE = 28;
    public static final short ENVELOPE_C3_PAPERSIZE = 29;
    public static final short ENVELOPE_C4_PAPERSIZE = 30;
    public static final short ENVELOPE_C6_PAPERSIZE = 31;
    public static final short ENVELOPE_MONARCH_PAPERSIZE = 37;
    public static final short A4_EXTRA_PAPERSIZE = 53;
    public static final short A4_TRANSVERSE_PAPERSIZE = 55;
    public static final short A4_PLUS_PAPERSIZE = 60;
    public static final short LETTER_ROTATED_PAPERSIZE = 75;
    public static final short A4_ROTATED_PAPERSIZE = 77;

    public void setPaperSize(short var1);

    public void setScale(short var1);

    public void setPageStart(short var1);

    public void setFitWidth(short var1);

    public void setFitHeight(short var1);

    public void setLeftToRight(boolean var1);

    public void setLandscape(boolean var1);

    public void setValidSettings(boolean var1);

    public void setNoColor(boolean var1);

    public void setDraft(boolean var1);

    public void setNotes(boolean var1);

    public void setNoOrientation(boolean var1);

    public void setUsePage(boolean var1);

    public void setHResolution(short var1);

    public void setVResolution(short var1);

    public void setHeaderMargin(double var1);

    public void setFooterMargin(double var1);

    public void setCopies(short var1);

    public short getPaperSize();

    public short getScale();

    public short getPageStart();

    public short getFitWidth();

    public short getFitHeight();

    public boolean getLeftToRight();

    public boolean getLandscape();

    public boolean getValidSettings();

    public boolean getNoColor();

    public boolean getDraft();

    public boolean getNotes();

    public boolean getNoOrientation();

    public boolean getUsePage();

    public short getHResolution();

    public short getVResolution();

    public double getHeaderMargin();

    public double getFooterMargin();

    public short getCopies();
}

