/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.xssf.binary;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;
import org.apache.poi.ooxml.POIXMLException;
import org.apache.poi.ss.usermodel.BuiltinFormats;
import org.apache.poi.util.Internal;
import org.apache.poi.xssf.binary.XSSFBParseException;
import org.apache.poi.xssf.binary.XSSFBParser;
import org.apache.poi.xssf.binary.XSSFBRecordType;
import org.apache.poi.xssf.binary.XSSFBUtils;

@Internal
public class XSSFBStylesTable
extends XSSFBParser {
    private final SortedMap<Short, String> numberFormats = new TreeMap<Short, String>();
    private final List<Short> styleIds = new ArrayList<Short>();
    private boolean inCellXFS;
    private boolean inFmts;

    public XSSFBStylesTable(InputStream is) throws IOException {
        super(is);
        this.parse();
    }

    String getNumberFormatString(int idx) {
        short numberFormatIdx = this.getNumberFormatIndex(idx);
        if (this.numberFormats.containsKey(numberFormatIdx)) {
            return (String)this.numberFormats.get(numberFormatIdx);
        }
        return BuiltinFormats.getBuiltinFormat(numberFormatIdx);
    }

    short getNumberFormatIndex(int idx) {
        return this.styleIds.get(idx);
    }

    @Override
    public void handleRecord(int recordType, byte[] data) throws XSSFBParseException {
        XSSFBRecordType type = XSSFBRecordType.lookup(recordType);
        switch (type) {
            case BrtBeginCellXFs: {
                this.inCellXFS = true;
                break;
            }
            case BrtEndCellXFs: {
                this.inCellXFS = false;
                break;
            }
            case BrtXf: {
                if (!this.inCellXFS) break;
                this.handleBrtXFInCellXF(data);
                break;
            }
            case BrtBeginFmts: {
                this.inFmts = true;
                break;
            }
            case BrtEndFmts: {
                this.inFmts = false;
                break;
            }
            case BrtFmt: {
                if (!this.inFmts) break;
                this.handleFormat(data);
            }
        }
    }

    private void handleFormat(byte[] data) {
        int ifmt = data[0] & 0xFF;
        if (ifmt > Short.MAX_VALUE) {
            throw new POIXMLException("Format id must be a short");
        }
        StringBuilder sb = new StringBuilder();
        XSSFBUtils.readXLWideString(data, 2, sb);
        String fmt = sb.toString();
        this.numberFormats.put((short)ifmt, fmt);
    }

    private void handleBrtXFInCellXF(byte[] data) {
        int ifmtOffset = 2;
        int ifmt = data[ifmtOffset] & 0xFF;
        this.styleIds.add((short)ifmt);
    }
}

