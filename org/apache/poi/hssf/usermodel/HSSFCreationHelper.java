/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.hssf.usermodel;

import org.apache.poi.common.usermodel.HyperlinkType;
import org.apache.poi.hssf.record.common.ExtendedColor;
import org.apache.poi.hssf.usermodel.HSSFClientAnchor;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFExtendedColor;
import org.apache.poi.hssf.usermodel.HSSFFormulaEvaluator;
import org.apache.poi.hssf.usermodel.HSSFHyperlink;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.util.AreaReference;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.util.Internal;

public class HSSFCreationHelper
implements CreationHelper {
    private final HSSFWorkbook workbook;

    @Internal(since="3.15 beta 3")
    HSSFCreationHelper(HSSFWorkbook wb) {
        this.workbook = wb;
    }

    @Override
    public HSSFRichTextString createRichTextString(String text) {
        return new HSSFRichTextString(text);
    }

    @Override
    public HSSFDataFormat createDataFormat() {
        return this.workbook.createDataFormat();
    }

    @Override
    public HSSFHyperlink createHyperlink(HyperlinkType type) {
        return new HSSFHyperlink(type);
    }

    @Override
    public HSSFExtendedColor createExtendedColor() {
        return new HSSFExtendedColor(new ExtendedColor());
    }

    @Override
    public HSSFFormulaEvaluator createFormulaEvaluator() {
        return new HSSFFormulaEvaluator(this.workbook);
    }

    @Override
    public HSSFClientAnchor createClientAnchor() {
        return new HSSFClientAnchor();
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

