/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.ss.formula;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.poi.ss.SpreadsheetVersion;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.util.Removal;

public final class SheetNameFormatter {
    private static final char DELIMITER = '\'';
    private static final Pattern CELL_REF_PATTERN = Pattern.compile("([A-Za-z]+)([0-9]+)");

    private SheetNameFormatter() {
    }

    public static String format(String rawSheetName) {
        StringBuilder sb = new StringBuilder((rawSheetName == null ? 0 : rawSheetName.length()) + 2);
        SheetNameFormatter.appendFormat(sb, rawSheetName);
        return sb.toString();
    }

    @Deprecated
    @Removal(version="5.0.0")
    public static void appendFormat(StringBuffer out, String rawSheetName) {
        SheetNameFormatter.appendFormat((Appendable)out, rawSheetName);
    }

    @Deprecated
    @Removal(version="5.0.0")
    public static void appendFormat(StringBuffer out, String workbookName, String rawSheetName) {
        SheetNameFormatter.appendFormat((Appendable)out, workbookName, rawSheetName);
    }

    @Removal(version="5.0.0")
    public static void appendFormat(StringBuilder out, String rawSheetName) {
        SheetNameFormatter.appendFormat((Appendable)out, rawSheetName);
    }

    @Removal(version="5.0.0")
    public static void appendFormat(StringBuilder out, String workbookName, String rawSheetName) {
        SheetNameFormatter.appendFormat((Appendable)out, workbookName, rawSheetName);
    }

    public static void appendFormat(Appendable out, String rawSheetName) {
        try {
            boolean needsQuotes = SheetNameFormatter.needsDelimiting(rawSheetName);
            if (needsQuotes) {
                out.append('\'');
                SheetNameFormatter.appendAndEscape(out, rawSheetName);
                out.append('\'');
            } else {
                SheetNameFormatter.appendAndEscape(out, rawSheetName);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void appendFormat(Appendable out, String workbookName, String rawSheetName) {
        try {
            boolean needsQuotes;
            boolean bl = needsQuotes = SheetNameFormatter.needsDelimiting(workbookName) || SheetNameFormatter.needsDelimiting(rawSheetName);
            if (needsQuotes) {
                out.append('\'');
                out.append('[');
                SheetNameFormatter.appendAndEscape(out, workbookName.replace('[', '(').replace(']', ')'));
                out.append(']');
                SheetNameFormatter.appendAndEscape(out, rawSheetName);
                out.append('\'');
            } else {
                out.append('[');
                SheetNameFormatter.appendOrREF(out, workbookName);
                out.append(']');
                SheetNameFormatter.appendOrREF(out, rawSheetName);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static void appendOrREF(Appendable out, String name) throws IOException {
        if (name == null) {
            out.append("#REF");
        } else {
            out.append(name);
        }
    }

    static void appendAndEscape(Appendable sb, String rawSheetName) {
        try {
            if (rawSheetName == null) {
                sb.append("#REF");
                return;
            }
            int len = rawSheetName.length();
            for (int i = 0; i < len; ++i) {
                char ch = rawSheetName.charAt(i);
                if (ch == '\'') {
                    sb.append('\'');
                }
                sb.append(ch);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    static boolean needsDelimiting(String rawSheetName) {
        if (rawSheetName == null) {
            return false;
        }
        int len = rawSheetName.length();
        if (len < 1) {
            return false;
        }
        if (Character.isDigit(rawSheetName.charAt(0))) {
            return true;
        }
        for (int i = 0; i < len; ++i) {
            char ch = rawSheetName.charAt(i);
            if (!SheetNameFormatter.isSpecialChar(ch)) continue;
            return true;
        }
        if (Character.isLetter(rawSheetName.charAt(0)) && Character.isDigit(rawSheetName.charAt(len - 1)) && SheetNameFormatter.nameLooksLikePlainCellReference(rawSheetName)) {
            return true;
        }
        return SheetNameFormatter.nameLooksLikeBooleanLiteral(rawSheetName);
    }

    private static boolean nameLooksLikeBooleanLiteral(String rawSheetName) {
        switch (rawSheetName.charAt(0)) {
            case 'T': 
            case 't': {
                return "TRUE".equalsIgnoreCase(rawSheetName);
            }
            case 'F': 
            case 'f': {
                return "FALSE".equalsIgnoreCase(rawSheetName);
            }
        }
        return false;
    }

    static boolean isSpecialChar(char ch) {
        if (Character.isLetterOrDigit(ch)) {
            return false;
        }
        switch (ch) {
            case '.': 
            case '_': {
                return false;
            }
            case '\t': 
            case '\n': 
            case '\r': {
                throw new RuntimeException("Illegal character (0x" + Integer.toHexString(ch) + ") found in sheet name");
            }
        }
        return true;
    }

    static boolean cellReferenceIsWithinRange(String lettersPrefix, String numbersSuffix) {
        return CellReference.cellReferenceIsWithinRange(lettersPrefix, numbersSuffix, SpreadsheetVersion.EXCEL97);
    }

    static boolean nameLooksLikePlainCellReference(String rawSheetName) {
        Matcher matcher = CELL_REF_PATTERN.matcher(rawSheetName);
        if (!matcher.matches()) {
            return false;
        }
        String lettersPrefix = matcher.group(1);
        String numbersSuffix = matcher.group(2);
        return SheetNameFormatter.cellReferenceIsWithinRange(lettersPrefix, numbersSuffix);
    }
}

