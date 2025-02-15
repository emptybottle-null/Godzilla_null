/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.xssf.binary;

import org.apache.poi.util.Internal;
import org.apache.poi.xssf.binary.XSSFBHeaderFooter;
import org.apache.poi.xssf.binary.XSSFBUtils;

@Internal
class XSSFBHeaderFooters {
    private XSSFBHeaderFooter header;
    private XSSFBHeaderFooter footer;
    private XSSFBHeaderFooter headerEven;
    private XSSFBHeaderFooter footerEven;
    private XSSFBHeaderFooter headerFirst;
    private XSSFBHeaderFooter footerFirst;

    XSSFBHeaderFooters() {
    }

    public static XSSFBHeaderFooters parse(byte[] data) {
        boolean diffOddEven = false;
        boolean diffFirst = false;
        boolean scaleWDoc = false;
        boolean alignMargins = false;
        int offset = 2;
        XSSFBHeaderFooters xssfbHeaderFooter = new XSSFBHeaderFooters();
        xssfbHeaderFooter.header = new XSSFBHeaderFooter("header", true);
        xssfbHeaderFooter.footer = new XSSFBHeaderFooter("footer", false);
        xssfbHeaderFooter.headerEven = new XSSFBHeaderFooter("evenHeader", true);
        xssfbHeaderFooter.footerEven = new XSSFBHeaderFooter("evenFooter", false);
        xssfbHeaderFooter.headerFirst = new XSSFBHeaderFooter("firstHeader", true);
        xssfbHeaderFooter.footerFirst = new XSSFBHeaderFooter("firstFooter", false);
        offset += XSSFBHeaderFooters.readHeaderFooter(data, offset, xssfbHeaderFooter.header);
        offset += XSSFBHeaderFooters.readHeaderFooter(data, offset, xssfbHeaderFooter.footer);
        offset += XSSFBHeaderFooters.readHeaderFooter(data, offset, xssfbHeaderFooter.headerEven);
        offset += XSSFBHeaderFooters.readHeaderFooter(data, offset, xssfbHeaderFooter.footerEven);
        offset += XSSFBHeaderFooters.readHeaderFooter(data, offset, xssfbHeaderFooter.headerFirst);
        XSSFBHeaderFooters.readHeaderFooter(data, offset, xssfbHeaderFooter.footerFirst);
        return xssfbHeaderFooter;
    }

    private static int readHeaderFooter(byte[] data, int offset, XSSFBHeaderFooter headerFooter) {
        if (offset + 4 >= data.length) {
            return 0;
        }
        StringBuilder sb = new StringBuilder();
        int bytesRead = XSSFBUtils.readXLNullableWideString(data, offset, sb);
        headerFooter.setRawString(sb.toString());
        return bytesRead;
    }

    public XSSFBHeaderFooter getHeader() {
        return this.header;
    }

    public XSSFBHeaderFooter getFooter() {
        return this.footer;
    }

    public XSSFBHeaderFooter getHeaderEven() {
        return this.headerEven;
    }

    public XSSFBHeaderFooter getFooterEven() {
        return this.footerEven;
    }

    public XSSFBHeaderFooter getHeaderFirst() {
        return this.headerFirst;
    }

    public XSSFBHeaderFooter getFooterFirst() {
        return this.footerFirst;
    }
}

