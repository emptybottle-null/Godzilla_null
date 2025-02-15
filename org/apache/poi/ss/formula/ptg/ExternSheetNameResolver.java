/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.ss.formula.ptg;

import org.apache.poi.ss.formula.EvaluationWorkbook;
import org.apache.poi.ss.formula.FormulaRenderingWorkbook;
import org.apache.poi.ss.formula.SheetNameFormatter;

final class ExternSheetNameResolver {
    private ExternSheetNameResolver() {
    }

    public static String prependSheetName(FormulaRenderingWorkbook book, int field_1_index_extern_sheet, String cellRefText) {
        StringBuilder sb;
        EvaluationWorkbook.ExternalSheet externalSheet = book.getExternalSheet(field_1_index_extern_sheet);
        if (externalSheet != null) {
            EvaluationWorkbook.ExternalSheetRange r;
            String wbName = externalSheet.getWorkbookName();
            String sheetName = externalSheet.getSheetName();
            if (wbName != null) {
                sb = new StringBuilder(wbName.length() + (sheetName == null ? 0 : sheetName.length()) + cellRefText.length() + 4);
                SheetNameFormatter.appendFormat(sb, wbName, sheetName);
            } else {
                sb = new StringBuilder(sheetName.length() + cellRefText.length() + 4);
                SheetNameFormatter.appendFormat(sb, sheetName);
            }
            if (externalSheet instanceof EvaluationWorkbook.ExternalSheetRange && !(r = (EvaluationWorkbook.ExternalSheetRange)externalSheet).getFirstSheetName().equals(r.getLastSheetName())) {
                sb.append(':');
                SheetNameFormatter.appendFormat(sb, r.getLastSheetName());
            }
        } else {
            String firstSheetName = book.getSheetFirstNameByExternSheet(field_1_index_extern_sheet);
            String lastSheetName = book.getSheetLastNameByExternSheet(field_1_index_extern_sheet);
            sb = new StringBuilder(firstSheetName.length() + cellRefText.length() + 4);
            if (firstSheetName.length() < 1) {
                sb.append("#REF");
            } else {
                SheetNameFormatter.appendFormat(sb, firstSheetName);
                if (!firstSheetName.equals(lastSheetName)) {
                    sb.append(':');
                    sb.append(lastSheetName);
                }
            }
        }
        sb.append('!');
        sb.append(cellRefText);
        return sb.toString();
    }
}

