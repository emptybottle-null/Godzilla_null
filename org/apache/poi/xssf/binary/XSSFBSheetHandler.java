/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.xssf.binary;

import java.io.InputStream;
import java.util.Queue;
import org.apache.poi.ss.usermodel.BuiltinFormats;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.RichTextString;
import org.apache.poi.ss.util.CellAddress;
import org.apache.poi.util.Internal;
import org.apache.poi.util.LittleEndian;
import org.apache.poi.xssf.binary.XSSFBCellHeader;
import org.apache.poi.xssf.binary.XSSFBCellRange;
import org.apache.poi.xssf.binary.XSSFBComment;
import org.apache.poi.xssf.binary.XSSFBCommentsTable;
import org.apache.poi.xssf.binary.XSSFBHeaderFooter;
import org.apache.poi.xssf.binary.XSSFBHeaderFooters;
import org.apache.poi.xssf.binary.XSSFBParseException;
import org.apache.poi.xssf.binary.XSSFBParser;
import org.apache.poi.xssf.binary.XSSFBRecordType;
import org.apache.poi.xssf.binary.XSSFBStylesTable;
import org.apache.poi.xssf.binary.XSSFBUtils;
import org.apache.poi.xssf.eventusermodel.XSSFSheetXMLHandler;
import org.apache.poi.xssf.model.SharedStrings;
import org.apache.poi.xssf.usermodel.XSSFComment;

@Internal
public class XSSFBSheetHandler
extends XSSFBParser {
    private static final int CHECK_ALL_ROWS = -1;
    private final SharedStrings stringsTable;
    private final XSSFSheetXMLHandler.SheetContentsHandler handler;
    private final XSSFBStylesTable styles;
    private final XSSFBCommentsTable comments;
    private final DataFormatter dataFormatter;
    private final boolean formulasNotResults;
    private int lastEndedRow = -1;
    private int lastStartedRow = -1;
    private int currentRow;
    private byte[] rkBuffer = new byte[8];
    private XSSFBCellRange hyperlinkCellRange;
    private StringBuilder xlWideStringBuffer = new StringBuilder();
    private final XSSFBCellHeader cellBuffer = new XSSFBCellHeader();

    public XSSFBSheetHandler(InputStream is, XSSFBStylesTable styles, XSSFBCommentsTable comments, SharedStrings strings, XSSFSheetXMLHandler.SheetContentsHandler sheetContentsHandler, DataFormatter dataFormatter, boolean formulasNotResults) {
        super(is);
        this.styles = styles;
        this.comments = comments;
        this.stringsTable = strings;
        this.handler = sheetContentsHandler;
        this.dataFormatter = dataFormatter;
        this.formulasNotResults = formulasNotResults;
    }

    @Override
    public void handleRecord(int id, byte[] data) throws XSSFBParseException {
        XSSFBRecordType type = XSSFBRecordType.lookup(id);
        switch (type) {
            case BrtRowHdr: {
                int rw = XSSFBUtils.castToInt(LittleEndian.getUInt(data, 0));
                if (rw > 0x100000) {
                    throw new XSSFBParseException("Row number beyond allowable range: " + rw);
                }
                this.currentRow = rw;
                this.checkMissedComments(this.currentRow);
                this.startRow(this.currentRow);
                break;
            }
            case BrtCellIsst: {
                this.handleBrtCellIsst(data);
                break;
            }
            case BrtCellSt: {
                this.handleCellSt(data);
                break;
            }
            case BrtCellRk: {
                this.handleCellRk(data);
                break;
            }
            case BrtCellReal: {
                this.handleCellReal(data);
                break;
            }
            case BrtCellBool: {
                this.handleBoolean(data);
                break;
            }
            case BrtCellError: {
                this.handleCellError(data);
                break;
            }
            case BrtCellBlank: {
                this.beforeCellValue(data);
                break;
            }
            case BrtFmlaString: {
                this.handleFmlaString(data);
                break;
            }
            case BrtFmlaNum: {
                this.handleFmlaNum(data);
                break;
            }
            case BrtFmlaError: {
                this.handleFmlaError(data);
                break;
            }
            case BrtEndSheetData: {
                this.checkMissedComments(-1);
                this.endRow(this.lastStartedRow);
                break;
            }
            case BrtBeginHeaderFooter: {
                this.handleHeaderFooter(data);
            }
        }
    }

    private void beforeCellValue(byte[] data) {
        XSSFBCellHeader.parse(data, 0, this.currentRow, this.cellBuffer);
        this.checkMissedComments(this.currentRow, this.cellBuffer.getColNum());
    }

    private void handleCellValue(String formattedValue) {
        CellAddress cellAddress = new CellAddress(this.currentRow, this.cellBuffer.getColNum());
        XSSFBComment comment = null;
        if (this.comments != null) {
            comment = this.comments.get(cellAddress);
        }
        this.handler.cell(cellAddress.formatAsString(), formattedValue, comment);
    }

    private void handleFmlaNum(byte[] data) {
        this.beforeCellValue(data);
        double val = LittleEndian.getDouble(data, XSSFBCellHeader.length);
        this.handleCellValue(this.formatVal(val, this.cellBuffer.getStyleIdx()));
    }

    private void handleCellSt(byte[] data) {
        this.beforeCellValue(data);
        this.xlWideStringBuffer.setLength(0);
        XSSFBUtils.readXLWideString(data, XSSFBCellHeader.length, this.xlWideStringBuffer);
        this.handleCellValue(this.xlWideStringBuffer.toString());
    }

    private void handleFmlaString(byte[] data) {
        this.beforeCellValue(data);
        this.xlWideStringBuffer.setLength(0);
        XSSFBUtils.readXLWideString(data, XSSFBCellHeader.length, this.xlWideStringBuffer);
        this.handleCellValue(this.xlWideStringBuffer.toString());
    }

    private void handleCellError(byte[] data) {
        this.beforeCellValue(data);
        this.handleCellValue("ERROR");
    }

    private void handleFmlaError(byte[] data) {
        this.beforeCellValue(data);
        this.handleCellValue("ERROR");
    }

    private void handleBoolean(byte[] data) {
        this.beforeCellValue(data);
        String formattedVal = data[XSSFBCellHeader.length] == 1 ? "TRUE" : "FALSE";
        this.handleCellValue(formattedVal);
    }

    private void handleCellReal(byte[] data) {
        this.beforeCellValue(data);
        double val = LittleEndian.getDouble(data, XSSFBCellHeader.length);
        this.handleCellValue(this.formatVal(val, this.cellBuffer.getStyleIdx()));
    }

    private void handleCellRk(byte[] data) {
        this.beforeCellValue(data);
        double val = this.rkNumber(data, XSSFBCellHeader.length);
        this.handleCellValue(this.formatVal(val, this.cellBuffer.getStyleIdx()));
    }

    private String formatVal(double val, int styleIdx) {
        String formatString = this.styles.getNumberFormatString(styleIdx);
        short styleIndex = this.styles.getNumberFormatIndex(styleIdx);
        if (formatString == null) {
            formatString = BuiltinFormats.getBuiltinFormat(0);
            styleIndex = 0;
        }
        return this.dataFormatter.formatRawCellContents(val, styleIndex, formatString);
    }

    private void handleBrtCellIsst(byte[] data) {
        this.beforeCellValue(data);
        int idx = XSSFBUtils.castToInt(LittleEndian.getUInt(data, XSSFBCellHeader.length));
        RichTextString rtss = this.stringsTable.getItemAt(idx);
        this.handleCellValue(rtss.getString());
    }

    private void handleHeaderFooter(byte[] data) {
        XSSFBHeaderFooters headerFooter = XSSFBHeaderFooters.parse(data);
        this.outputHeaderFooter(headerFooter.getHeader());
        this.outputHeaderFooter(headerFooter.getFooter());
        this.outputHeaderFooter(headerFooter.getHeaderEven());
        this.outputHeaderFooter(headerFooter.getFooterEven());
        this.outputHeaderFooter(headerFooter.getHeaderFirst());
        this.outputHeaderFooter(headerFooter.getFooterFirst());
    }

    private void outputHeaderFooter(XSSFBHeaderFooter headerFooter) {
        String text = headerFooter.getString();
        if (text != null && text.trim().length() > 0) {
            this.handler.headerFooter(text, headerFooter.isHeader(), headerFooter.getHeaderFooterTypeLabel());
        }
    }

    private void checkMissedComments(int currentRow, int colNum) {
        if (this.comments == null) {
            return;
        }
        Queue<CellAddress> queue = this.comments.getAddresses();
        while (queue.size() > 0) {
            CellAddress cellAddress = queue.peek();
            if (cellAddress.getRow() == currentRow && cellAddress.getColumn() < colNum) {
                cellAddress = queue.remove();
                this.dumpEmptyCellComment(cellAddress, this.comments.get(cellAddress));
                continue;
            }
            if (cellAddress.getRow() == currentRow && cellAddress.getColumn() == colNum) {
                queue.remove();
                return;
            }
            if (cellAddress.getRow() == currentRow && cellAddress.getColumn() > colNum) {
                return;
            }
            if (cellAddress.getRow() <= currentRow) continue;
            return;
        }
    }

    private void checkMissedComments(int currentRow) {
        if (this.comments == null) {
            return;
        }
        Queue<CellAddress> queue = this.comments.getAddresses();
        int lastInterpolatedRow = -1;
        while (queue.size() > 0) {
            CellAddress cellAddress = queue.peek();
            if (currentRow != -1 && cellAddress.getRow() >= currentRow) break;
            cellAddress = queue.remove();
            if (cellAddress.getRow() != lastInterpolatedRow) {
                this.startRow(cellAddress.getRow());
            }
            this.dumpEmptyCellComment(cellAddress, this.comments.get(cellAddress));
            lastInterpolatedRow = cellAddress.getRow();
        }
    }

    private void startRow(int row) {
        if (row == this.lastStartedRow) {
            return;
        }
        if (this.lastStartedRow != this.lastEndedRow) {
            this.endRow(this.lastStartedRow);
        }
        this.handler.startRow(row);
        this.lastStartedRow = row;
    }

    private void endRow(int row) {
        if (this.lastEndedRow == row) {
            return;
        }
        this.handler.endRow(row);
        this.lastEndedRow = row;
    }

    private void dumpEmptyCellComment(CellAddress cellAddress, XSSFBComment comment) {
        this.handler.cell(cellAddress.formatAsString(), null, comment);
    }

    private double rkNumber(byte[] data, int offset) {
        byte b0 = data[offset];
        boolean numDivBy100 = (b0 & 1) == 1;
        boolean floatingPoint = (b0 >> 1 & 1) == 0;
        b0 = (byte)(b0 & 0xFFFFFFFE);
        this.rkBuffer[4] = b0 = (byte)(b0 & 0xFFFFFFFD);
        System.arraycopy(data, offset + 1, this.rkBuffer, 5, 3);
        double d = 0.0;
        if (floatingPoint) {
            d = LittleEndian.getDouble(this.rkBuffer);
        } else {
            int rawInt = LittleEndian.getInt(this.rkBuffer, 4);
            d = rawInt >> 2;
        }
        d = numDivBy100 ? d / 100.0 : d;
        return d;
    }

    public static interface SheetContentsHandler
    extends XSSFSheetXMLHandler.SheetContentsHandler {
        public void hyperlinkCell(String var1, String var2, String var3, String var4, XSSFComment var5);
    }
}

