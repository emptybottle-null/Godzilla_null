/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.xssf.binary;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.TreeMap;
import org.apache.poi.ss.util.CellAddress;
import org.apache.poi.util.Internal;
import org.apache.poi.util.LittleEndian;
import org.apache.poi.xssf.binary.XSSFBCellRange;
import org.apache.poi.xssf.binary.XSSFBComment;
import org.apache.poi.xssf.binary.XSSFBParseException;
import org.apache.poi.xssf.binary.XSSFBParser;
import org.apache.poi.xssf.binary.XSSFBRecordType;
import org.apache.poi.xssf.binary.XSSFBRichStr;
import org.apache.poi.xssf.binary.XSSFBUtils;

@Internal
public class XSSFBCommentsTable
extends XSSFBParser {
    private Map<CellAddress, XSSFBComment> comments = new TreeMap<CellAddress, XSSFBComment>();
    private Queue<CellAddress> commentAddresses = new LinkedList<CellAddress>();
    private List<String> authors = new ArrayList<String>();
    private int authorId = -1;
    private CellAddress cellAddress;
    private XSSFBCellRange cellRange;
    private String comment;
    private StringBuilder authorBuffer = new StringBuilder();

    public XSSFBCommentsTable(InputStream is) throws IOException {
        super(is);
        this.parse();
        this.commentAddresses.addAll(this.comments.keySet());
    }

    @Override
    public void handleRecord(int id, byte[] data) throws XSSFBParseException {
        XSSFBRecordType recordType = XSSFBRecordType.lookup(id);
        switch (recordType) {
            case BrtBeginComment: {
                int offset = 0;
                this.authorId = XSSFBUtils.castToInt(LittleEndian.getUInt(data));
                this.cellRange = XSSFBCellRange.parse(data, offset += 4, this.cellRange);
                offset += 16;
                this.cellAddress = new CellAddress(this.cellRange.firstRow, this.cellRange.firstCol);
                break;
            }
            case BrtCommentText: {
                XSSFBRichStr xssfbRichStr = XSSFBRichStr.build(data, 0);
                this.comment = xssfbRichStr.getString();
                break;
            }
            case BrtEndComment: {
                this.comments.put(this.cellAddress, new XSSFBComment(this.cellAddress, this.authors.get(this.authorId), this.comment));
                this.authorId = -1;
                this.cellAddress = null;
                break;
            }
            case BrtCommentAuthor: {
                this.authorBuffer.setLength(0);
                XSSFBUtils.readXLWideString(data, 0, this.authorBuffer);
                this.authors.add(this.authorBuffer.toString());
            }
        }
    }

    public Queue<CellAddress> getAddresses() {
        return this.commentAddresses;
    }

    public XSSFBComment get(CellAddress cellAddress) {
        if (cellAddress == null) {
            return null;
        }
        return this.comments.get(cellAddress);
    }
}

