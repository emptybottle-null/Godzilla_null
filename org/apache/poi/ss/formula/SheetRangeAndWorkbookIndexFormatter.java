/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.ss.formula;

import org.apache.poi.ss.formula.SheetNameFormatter;

public class SheetRangeAndWorkbookIndexFormatter {
    private SheetRangeAndWorkbookIndexFormatter() {
    }

    public static String format(StringBuilder sb, int workbookIndex, String firstSheetName, String lastSheetName) {
        if (SheetRangeAndWorkbookIndexFormatter.anySheetNameNeedsEscaping(firstSheetName, lastSheetName)) {
            return SheetRangeAndWorkbookIndexFormatter.formatWithDelimiting(sb, workbookIndex, firstSheetName, lastSheetName);
        }
        return SheetRangeAndWorkbookIndexFormatter.formatWithoutDelimiting(sb, workbookIndex, firstSheetName, lastSheetName);
    }

    private static String formatWithDelimiting(StringBuilder sb, int workbookIndex, String firstSheetName, String lastSheetName) {
        sb.append('\'');
        if (workbookIndex >= 0) {
            sb.append('[');
            sb.append(workbookIndex);
            sb.append(']');
        }
        SheetNameFormatter.appendAndEscape(sb, firstSheetName);
        if (lastSheetName != null) {
            sb.append(':');
            SheetNameFormatter.appendAndEscape(sb, lastSheetName);
        }
        sb.append('\'');
        return sb.toString();
    }

    private static String formatWithoutDelimiting(StringBuilder sb, int workbookIndex, String firstSheetName, String lastSheetName) {
        if (workbookIndex >= 0) {
            sb.append('[');
            sb.append(workbookIndex);
            sb.append(']');
        }
        sb.append(firstSheetName);
        if (lastSheetName != null) {
            sb.append(':');
            sb.append(lastSheetName);
        }
        return sb.toString();
    }

    private static boolean anySheetNameNeedsEscaping(String firstSheetName, String lastSheetName) {
        boolean anySheetNameNeedsDelimiting = SheetNameFormatter.needsDelimiting(firstSheetName);
        return anySheetNameNeedsDelimiting |= SheetNameFormatter.needsDelimiting(lastSheetName);
    }
}

