/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.xssf.usermodel;

import org.apache.poi.common.usermodel.HyperlinkType;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.util.AreaReference;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.util.Internal;
import org.apache.poi.xssf.usermodel.XSSFClientAnchor;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFDataFormat;
import org.apache.poi.xssf.usermodel.XSSFFormulaEvaluator;
import org.apache.poi.xssf.usermodel.XSSFHyperlink;
import org.apache.poi.xssf.usermodel.XSSFRichTextString;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTColor;

public class XSSFCreationHelper
implements CreationHelper {
    private final XSSFWorkbook workbook;

    @Internal
    public XSSFCreationHelper(XSSFWorkbook wb) {
        this.workbook = wb;
    }

    @Override
    public XSSFRichTextString createRichTextString(String text) {
        XSSFRichTextString rt = new XSSFRichTextString(text);
        rt.setStylesTableReference(this.workbook.getStylesSource());
        return rt;
    }

    @Override
    public XSSFDataFormat createDataFormat() {
        return this.workbook.createDataFormat();
    }

    @Override
    public XSSFColor createExtendedColor() {
        return XSSFColor.from(CTColor.Factory.newInstance(), this.workbook.getStylesSource().getIndexedColors());
    }

    @Override
    public XSSFHyperlink createHyperlink(HyperlinkType type) {
        return new XSSFHyperlink(type);
    }

    @Override
    public XSSFFormulaEvaluator createFormulaEvaluator() {
        return new XSSFFormulaEvaluator(this.workbook);
    }

    @Override
    public XSSFClientAnchor createClientAnchor() {
        return new XSSFClientAnchor();
    }

    @Override
    public AreaReference createAreaReference(String reference) {
        return new AreaReference(reference, this.workbook.getSpreadsheetVersion());
    }

    @Override
    public AreaReference createAreaReference(CellReference topLeft, CellReference bottomRight) {
        return new AreaReference(topLeft, bottomRight, this.workbook.getSpreadsheetVersion());
    }
}

