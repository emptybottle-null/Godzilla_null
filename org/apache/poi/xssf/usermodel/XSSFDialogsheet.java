/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.xssf.usermodel;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDialogsheet;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTHeaderFooter;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPageBreak;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPageMargins;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPrintOptions;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTSheetFormatPr;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTSheetPr;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTSheetProtection;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTSheetViews;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWorksheet;

public class XSSFDialogsheet
extends XSSFSheet
implements Sheet {
    protected CTDialogsheet dialogsheet = CTDialogsheet.Factory.newInstance();

    protected XSSFDialogsheet(XSSFSheet sheet) {
        super(sheet.getPackagePart());
        this.worksheet = CTWorksheet.Factory.newInstance();
    }

    @Override
    public XSSFRow createRow(int rowNum) {
        return null;
    }

    protected CTHeaderFooter getSheetTypeHeaderFooter() {
        if (this.dialogsheet.getHeaderFooter() == null) {
            this.dialogsheet.setHeaderFooter(CTHeaderFooter.Factory.newInstance());
        }
        return this.dialogsheet.getHeaderFooter();
    }

    protected CTSheetPr getSheetTypeSheetPr() {
        if (this.dialogsheet.getSheetPr() == null) {
            this.dialogsheet.setSheetPr(CTSheetPr.Factory.newInstance());
        }
        return this.dialogsheet.getSheetPr();
    }

    protected CTPageBreak getSheetTypeColumnBreaks() {
        return null;
    }

    protected CTSheetFormatPr getSheetTypeSheetFormatPr() {
        if (this.dialogsheet.getSheetFormatPr() == null) {
            this.dialogsheet.setSheetFormatPr(CTSheetFormatPr.Factory.newInstance());
        }
        return this.dialogsheet.getSheetFormatPr();
    }

    protected CTPageMargins getSheetTypePageMargins() {
        if (this.dialogsheet.getPageMargins() == null) {
            this.dialogsheet.setPageMargins(CTPageMargins.Factory.newInstance());
        }
        return this.dialogsheet.getPageMargins();
    }

    protected CTPageBreak getSheetTypeRowBreaks() {
        return null;
    }

    protected CTSheetViews getSheetTypeSheetViews() {
        if (this.dialogsheet.getSheetViews() == null) {
            this.dialogsheet.setSheetViews(CTSheetViews.Factory.newInstance());
            this.dialogsheet.getSheetViews().addNewSheetView();
        }
        return this.dialogsheet.getSheetViews();
    }

    protected CTPrintOptions getSheetTypePrintOptions() {
        if (this.dialogsheet.getPrintOptions() == null) {
            this.dialogsheet.setPrintOptions(CTPrintOptions.Factory.newInstance());
        }
        return this.dialogsheet.getPrintOptions();
    }

    protected CTSheetProtection getSheetTypeProtection() {
        if (this.dialogsheet.getSheetProtection() == null) {
            this.dialogsheet.setSheetProtection(CTSheetProtection.Factory.newInstance());
        }
        return this.dialogsheet.getSheetProtection();
    }

    public boolean getDialog() {
        return true;
    }
}

