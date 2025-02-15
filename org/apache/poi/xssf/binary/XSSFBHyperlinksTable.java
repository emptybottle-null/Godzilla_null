/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.xssf.binary;

import com.zaxxer.sparsebits.SparseBitSet;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.PackagePart;
import org.apache.poi.openxml4j.opc.PackageRelationship;
import org.apache.poi.ss.util.CellAddress;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.CellRangeUtil;
import org.apache.poi.util.Internal;
import org.apache.poi.xssf.binary.XSSFBCellRange;
import org.apache.poi.xssf.binary.XSSFBParseException;
import org.apache.poi.xssf.binary.XSSFBParser;
import org.apache.poi.xssf.binary.XSSFBRecordType;
import org.apache.poi.xssf.binary.XSSFBUtils;
import org.apache.poi.xssf.binary.XSSFHyperlinkRecord;
import org.apache.poi.xssf.usermodel.XSSFRelation;

@Internal
public class XSSFBHyperlinksTable {
    private static final SparseBitSet RECORDS = new SparseBitSet();
    private final List<XSSFHyperlinkRecord> hyperlinkRecords = new ArrayList<XSSFHyperlinkRecord>();
    private Map<String, String> relIdToHyperlink = new HashMap<String, String>();

    public XSSFBHyperlinksTable(PackagePart sheetPart) throws IOException {
        this.loadUrlsFromSheetRels(sheetPart);
        HyperlinkSheetScraper scraper = new HyperlinkSheetScraper(sheetPart.getInputStream());
        scraper.parse();
    }

    public Map<CellAddress, List<XSSFHyperlinkRecord>> getHyperLinks() {
        TreeMap<CellAddress, List<XSSFHyperlinkRecord>> hyperlinkMap = new TreeMap<CellAddress, List<XSSFHyperlinkRecord>>(new TopLeftCellAddressComparator());
        for (XSSFHyperlinkRecord hyperlinkRecord : this.hyperlinkRecords) {
            CellAddress cellAddress = new CellAddress(hyperlinkRecord.getCellRangeAddress().getFirstRow(), hyperlinkRecord.getCellRangeAddress().getFirstColumn());
            ArrayList<XSSFHyperlinkRecord> list = (ArrayList<XSSFHyperlinkRecord>)hyperlinkMap.get(cellAddress);
            if (list == null) {
                list = new ArrayList<XSSFHyperlinkRecord>();
            }
            list.add(hyperlinkRecord);
            hyperlinkMap.put(cellAddress, list);
        }
        return hyperlinkMap;
    }

    public List<XSSFHyperlinkRecord> findHyperlinkRecord(CellAddress cellAddress) {
        ArrayList<XSSFHyperlinkRecord> overlapping = null;
        CellRangeAddress targetCellRangeAddress = new CellRangeAddress(cellAddress.getRow(), cellAddress.getRow(), cellAddress.getColumn(), cellAddress.getColumn());
        for (XSSFHyperlinkRecord record : this.hyperlinkRecords) {
            if (CellRangeUtil.intersect(targetCellRangeAddress, record.getCellRangeAddress()) == 1) continue;
            if (overlapping == null) {
                overlapping = new ArrayList<XSSFHyperlinkRecord>();
            }
            overlapping.add(record);
        }
        return overlapping;
    }

    private void loadUrlsFromSheetRels(PackagePart sheetPart) {
        try {
            for (PackageRelationship rel : sheetPart.getRelationshipsByType(XSSFRelation.SHEET_HYPERLINKS.getRelation())) {
                this.relIdToHyperlink.put(rel.getId(), rel.getTargetURI().toString());
            }
        } catch (InvalidFormatException invalidFormatException) {
            // empty catch block
        }
    }

    static {
        RECORDS.set(XSSFBRecordType.BrtHLink.getId());
    }

    private static class TopLeftCellAddressComparator
    implements Comparator<CellAddress>,
    Serializable {
        private static final long serialVersionUID = 1L;

        private TopLeftCellAddressComparator() {
        }

        @Override
        public int compare(CellAddress o1, CellAddress o2) {
            if (o1.getRow() < o2.getRow()) {
                return -1;
            }
            if (o1.getRow() > o2.getRow()) {
                return 1;
            }
            if (o1.getColumn() < o2.getColumn()) {
                return -1;
            }
            if (o1.getColumn() > o2.getColumn()) {
                return 1;
            }
            return 0;
        }
    }

    private class HyperlinkSheetScraper
    extends XSSFBParser {
        private XSSFBCellRange hyperlinkCellRange;
        private final StringBuilder xlWideStringBuffer;

        HyperlinkSheetScraper(InputStream is) {
            super(is, RECORDS);
            this.hyperlinkCellRange = new XSSFBCellRange();
            this.xlWideStringBuffer = new StringBuilder();
        }

        @Override
        public void handleRecord(int recordType, byte[] data) throws XSSFBParseException {
            if (recordType != XSSFBRecordType.BrtHLink.getId()) {
                return;
            }
            int offset = 0;
            this.hyperlinkCellRange = XSSFBCellRange.parse(data, offset, this.hyperlinkCellRange);
            offset += 16;
            this.xlWideStringBuffer.setLength(0);
            offset += XSSFBUtils.readXLNullableWideString(data, offset, this.xlWideStringBuffer);
            String relId = this.xlWideStringBuffer.toString();
            this.xlWideStringBuffer.setLength(0);
            offset += XSSFBUtils.readXLWideString(data, offset, this.xlWideStringBuffer);
            String location = this.xlWideStringBuffer.toString();
            this.xlWideStringBuffer.setLength(0);
            offset += XSSFBUtils.readXLWideString(data, offset, this.xlWideStringBuffer);
            String toolTip = this.xlWideStringBuffer.toString();
            this.xlWideStringBuffer.setLength(0);
            XSSFBUtils.readXLWideString(data, offset, this.xlWideStringBuffer);
            String display = this.xlWideStringBuffer.toString();
            CellRangeAddress cellRangeAddress = new CellRangeAddress(this.hyperlinkCellRange.firstRow, this.hyperlinkCellRange.lastRow, this.hyperlinkCellRange.firstCol, this.hyperlinkCellRange.lastCol);
            String url = (String)XSSFBHyperlinksTable.this.relIdToHyperlink.get(relId);
            if (location.length() == 0) {
                location = url;
            }
            XSSFBHyperlinksTable.this.hyperlinkRecords.add(new XSSFHyperlinkRecord(cellRangeAddress, relId, location, toolTip, display));
        }
    }
}

