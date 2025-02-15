/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.xssf.usermodel;

import org.apache.poi.ss.usermodel.BorderFormatting;
import org.apache.poi.ss.usermodel.DifferentialStyleProvider;
import org.apache.poi.ss.usermodel.ExcelNumberFormat;
import org.apache.poi.ss.usermodel.FontFormatting;
import org.apache.poi.ss.usermodel.PatternFormatting;
import org.apache.poi.xssf.usermodel.IndexedColorMap;
import org.apache.poi.xssf.usermodel.XSSFBorderFormatting;
import org.apache.poi.xssf.usermodel.XSSFFontFormatting;
import org.apache.poi.xssf.usermodel.XSSFPatternFormatting;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDxf;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTNumFmt;

public class XSSFDxfStyleProvider
implements DifferentialStyleProvider {
    private final IndexedColorMap colorMap;
    private final BorderFormatting border;
    private final FontFormatting font;
    private final ExcelNumberFormat number;
    private final PatternFormatting fill;
    private final int stripeSize;

    public XSSFDxfStyleProvider(CTDxf dxf, int stripeSize, IndexedColorMap colorMap) {
        this.stripeSize = stripeSize;
        this.colorMap = colorMap;
        if (dxf == null) {
            this.border = null;
            this.font = null;
            this.number = null;
            this.fill = null;
        } else {
            this.border = dxf.isSetBorder() ? new XSSFBorderFormatting(dxf.getBorder(), colorMap) : null;
            FontFormatting fontFormatting = this.font = dxf.isSetFont() ? new XSSFFontFormatting(dxf.getFont(), colorMap) : null;
            if (dxf.isSetNumFmt()) {
                CTNumFmt numFmt = dxf.getNumFmt();
                this.number = new ExcelNumberFormat((int)numFmt.getNumFmtId(), numFmt.getFormatCode());
            } else {
                this.number = null;
            }
            this.fill = dxf.isSetFill() ? new XSSFPatternFormatting(dxf.getFill(), colorMap) : null;
        }
    }

    @Override
    public BorderFormatting getBorderFormatting() {
        return this.border;
    }

    @Override
    public FontFormatting getFontFormatting() {
        return this.font;
    }

    @Override
    public ExcelNumberFormat getNumberFormat() {
        return this.number;
    }

    @Override
    public PatternFormatting getPatternFormatting() {
        return this.fill;
    }

    @Override
    public int getStripeSize() {
        return this.stripeSize;
    }
}

