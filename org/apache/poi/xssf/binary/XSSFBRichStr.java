/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.xssf.binary;

import org.apache.poi.util.Internal;
import org.apache.poi.xssf.binary.XSSFBParseException;
import org.apache.poi.xssf.binary.XSSFBUtils;

@Internal
class XSSFBRichStr {
    private final String string;
    private final String phoneticString;

    public static XSSFBRichStr build(byte[] bytes, int offset) throws XSSFBParseException {
        byte first = bytes[offset];
        boolean dwSizeStrRunExists = (first >> 7 & 1) == 1;
        boolean phoneticExists = (first >> 6 & 1) == 1;
        StringBuilder sb = new StringBuilder();
        int read = XSSFBUtils.readXLWideString(bytes, offset + 1, sb);
        return new XSSFBRichStr(sb.toString(), "");
    }

    XSSFBRichStr(String string, String phoneticString) {
        this.string = string;
        this.phoneticString = phoneticString;
    }

    public String getString() {
        return this.string;
    }
}

