/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.ss.usermodel;

public final class BuiltinFormats {
    public static final int FIRST_USER_DEFINED_FORMAT_INDEX = 164;
    private static final String[] _formats = new String[]{"General", "0", "0.00", "#,##0", "#,##0.00", "\"$\"#,##0_);(\"$\"#,##0)", "\"$\"#,##0_);[Red](\"$\"#,##0)", "\"$\"#,##0.00_);(\"$\"#,##0.00)", "\"$\"#,##0.00_);[Red](\"$\"#,##0.00)", "0%", "0.00%", "0.00E+00", "# ?/?", "# ??/??", "m/d/yy", "d-mmm-yy", "d-mmm", "mmm-yy", "h:mm AM/PM", "h:mm:ss AM/PM", "h:mm", "h:mm:ss", "m/d/yy h:mm", "reserved-0x17", "reserved-0x18", "reserved-0x19", "reserved-0x1A", "reserved-0x1B", "reserved-0x1C", "reserved-0x1D", "reserved-0x1E", "reserved-0x1F", "reserved-0x20", "reserved-0x21", "reserved-0x22", "reserved-0x23", "reserved-0x24", "#,##0_);(#,##0)", "#,##0_);[Red](#,##0)", "#,##0.00_);(#,##0.00)", "#,##0.00_);[Red](#,##0.00)", "_(* #,##0_);_(* (#,##0);_(* \"-\"_);_(@_)", "_(\"$\"* #,##0_);_(\"$\"* (#,##0);_(\"$\"* \"-\"_);_(@_)", "_(* #,##0.00_);_(* (#,##0.00);_(* \"-\"??_);_(@_)", "_(\"$\"* #,##0.00_);_(\"$\"* (#,##0.00);_(\"$\"* \"-\"??_);_(@_)", "mm:ss", "[h]:mm:ss", "mm:ss.0", "##0.0E+0", "@"};

    public static String[] getAll() {
        return (String[])_formats.clone();
    }

    public static String getBuiltinFormat(int index) {
        if (index < 0 || index >= _formats.length) {
            return null;
        }
        return _formats[index];
    }

    public static int getBuiltinFormat(String pFmt) {
        String fmt = "TEXT".equalsIgnoreCase(pFmt) ? "@" : pFmt;
        int i = -1;
        for (String f : _formats) {
            ++i;
            if (!f.equals(fmt)) continue;
            return i;
        }
        return -1;
    }
}

