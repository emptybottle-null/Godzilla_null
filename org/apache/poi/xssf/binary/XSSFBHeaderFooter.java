/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.xssf.binary;

import org.apache.poi.util.Internal;
import org.apache.poi.xssf.usermodel.helpers.HeaderFooterHelper;

@Internal
class XSSFBHeaderFooter {
    private static final HeaderFooterHelper HEADER_FOOTER_HELPER = new HeaderFooterHelper();
    private final String headerFooterTypeLabel;
    private final boolean isHeader;
    private String rawString;

    XSSFBHeaderFooter(String headerFooterTypeLabel, boolean isHeader) {
        this.headerFooterTypeLabel = headerFooterTypeLabel;
        this.isHeader = isHeader;
    }

    String getHeaderFooterTypeLabel() {
        return this.headerFooterTypeLabel;
    }

    String getRawString() {
        return this.rawString;
    }

    String getString() {
        StringBuilder sb = new StringBuilder();
        String left = HEADER_FOOTER_HELPER.getLeftSection(this.rawString);
        String center = HEADER_FOOTER_HELPER.getCenterSection(this.rawString);
        String right = HEADER_FOOTER_HELPER.getRightSection(this.rawString);
        if (left != null && left.length() > 0) {
            sb.append(left);
        }
        if (center != null && center.length() > 0) {
            if (sb.length() > 0) {
                sb.append(" ");
            }
            sb.append(center);
        }
        if (right != null && right.length() > 0) {
            if (sb.length() > 0) {
                sb.append(" ");
            }
            sb.append(right);
        }
        return sb.toString();
    }

    void setRawString(String rawString) {
        this.rawString = rawString;
    }

    boolean isHeader() {
        return this.isHeader;
    }
}

