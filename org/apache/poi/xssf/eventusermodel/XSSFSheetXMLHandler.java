/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.xssf.eventusermodel;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import org.apache.poi.ss.usermodel.BuiltinFormats;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.RichTextString;
import org.apache.poi.ss.util.CellAddress;
import org.apache.poi.util.POILogFactory;
import org.apache.poi.util.POILogger;
import org.apache.poi.xssf.model.Comments;
import org.apache.poi.xssf.model.SharedStrings;
import org.apache.poi.xssf.model.Styles;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFComment;
import org.apache.poi.xssf.usermodel.XSSFRichTextString;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class XSSFSheetXMLHandler
extends DefaultHandler {
    private static final POILogger logger = POILogFactory.getLogger(XSSFSheetXMLHandler.class);
    private Styles stylesTable;
    private Comments comments;
    private SharedStrings sharedStringsTable;
    private final SheetContentsHandler output;
    private boolean vIsOpen;
    private boolean fIsOpen;
    private boolean isIsOpen;
    private boolean hfIsOpen;
    private xssfDataType nextDataType;
    private short formatIndex;
    private String formatString;
    private final DataFormatter formatter;
    private int rowNum;
    private int nextRowNum;
    private String cellRef;
    private boolean formulasNotResults;
    private StringBuilder value = new StringBuilder(64);
    private StringBuilder formula = new StringBuilder(64);
    private StringBuilder headerFooter = new StringBuilder(64);
    private Queue<CellAddress> commentCellRefs;

    public XSSFSheetXMLHandler(Styles styles, Comments comments, SharedStrings strings, SheetContentsHandler sheetContentsHandler, DataFormatter dataFormatter, boolean formulasNotResults) {
        this.stylesTable = styles;
        this.comments = comments;
        this.sharedStringsTable = strings;
        this.output = sheetContentsHandler;
        this.formulasNotResults = formulasNotResults;
        this.nextDataType = xssfDataType.NUMBER;
        this.formatter = dataFormatter;
        this.init(comments);
    }

    public XSSFSheetXMLHandler(Styles styles, SharedStrings strings, SheetContentsHandler sheetContentsHandler, DataFormatter dataFormatter, boolean formulasNotResults) {
        this(styles, null, strings, sheetContentsHandler, dataFormatter, formulasNotResults);
    }

    public XSSFSheetXMLHandler(Styles styles, SharedStrings strings, SheetContentsHandler sheetContentsHandler, boolean formulasNotResults) {
        this(styles, strings, sheetContentsHandler, new DataFormatter(), formulasNotResults);
    }

    private void init(Comments commentsTable) {
        if (commentsTable != null) {
            this.commentCellRefs = new LinkedList<CellAddress>();
            Iterator<CellAddress> iter = commentsTable.getCellAddresses();
            while (iter.hasNext()) {
                this.commentCellRefs.add(iter.next());
            }
        }
    }

    private boolean isTextTag(String name) {
        if ("v".equals(name)) {
            return true;
        }
        if ("inlineStr".equals(name)) {
            return true;
        }
        return "t".equals(name) && this.isIsOpen;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if (uri != null && !uri.equals("http://schemas.openxmlformats.org/spreadsheetml/2006/main")) {
            return;
        }
        if (this.isTextTag(localName)) {
            this.vIsOpen = true;
            this.value.setLength(0);
        } else if ("is".equals(localName)) {
            this.isIsOpen = true;
        } else if ("f".equals(localName)) {
            String type;
            this.formula.setLength(0);
            if (this.nextDataType == xssfDataType.NUMBER) {
                this.nextDataType = xssfDataType.FORMULA;
            }
            if ((type = attributes.getValue("t")) != null && type.equals("shared")) {
                String ref = attributes.getValue("ref");
                String si = attributes.getValue("si");
                if (ref != null) {
                    this.fIsOpen = true;
                } else if (this.formulasNotResults) {
                    logger.log(5, "shared formulas not yet supported!");
                }
            } else {
                this.fIsOpen = true;
            }
        } else if ("oddHeader".equals(localName) || "evenHeader".equals(localName) || "firstHeader".equals(localName) || "firstFooter".equals(localName) || "oddFooter".equals(localName) || "evenFooter".equals(localName)) {
            this.hfIsOpen = true;
            this.headerFooter.setLength(0);
        } else if ("row".equals(localName)) {
            String rowNumStr = attributes.getValue("r");
            this.rowNum = rowNumStr != null ? Integer.parseInt(rowNumStr) - 1 : this.nextRowNum;
            this.output.startRow(this.rowNum);
        } else if ("c".equals(localName)) {
            this.nextDataType = xssfDataType.NUMBER;
            this.formatIndex = (short)-1;
            this.formatString = null;
            this.cellRef = attributes.getValue("r");
            String cellType = attributes.getValue("t");
            String cellStyleStr = attributes.getValue("s");
            if ("b".equals(cellType)) {
                this.nextDataType = xssfDataType.BOOLEAN;
            } else if ("e".equals(cellType)) {
                this.nextDataType = xssfDataType.ERROR;
            } else if ("inlineStr".equals(cellType)) {
                this.nextDataType = xssfDataType.INLINE_STRING;
            } else if ("s".equals(cellType)) {
                this.nextDataType = xssfDataType.SST_STRING;
            } else if ("str".equals(cellType)) {
                this.nextDataType = xssfDataType.FORMULA;
            } else {
                XSSFCellStyle style = null;
                if (this.stylesTable != null) {
                    if (cellStyleStr != null) {
                        int styleIndex = Integer.parseInt(cellStyleStr);
                        style = this.stylesTable.getStyleAt(styleIndex);
                    } else if (this.stylesTable.getNumCellStyles() > 0) {
                        style = this.stylesTable.getStyleAt(0);
                    }
                }
                if (style != null) {
                    this.formatIndex = style.getDataFormat();
                    this.formatString = style.getDataFormatString();
                    if (this.formatString == null) {
                        this.formatString = BuiltinFormats.getBuiltinFormat(this.formatIndex);
                    }
                }
            }
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (uri != null && !uri.equals("http://schemas.openxmlformats.org/spreadsheetml/2006/main")) {
            return;
        }
        String thisStr = null;
        if (this.isTextTag(localName)) {
            this.vIsOpen = false;
            switch (this.nextDataType) {
                case BOOLEAN: {
                    char first = this.value.charAt(0);
                    thisStr = first == '0' ? "FALSE" : "TRUE";
                    break;
                }
                case ERROR: {
                    thisStr = "ERROR:" + this.value;
                    break;
                }
                case FORMULA: {
                    if (this.formulasNotResults) {
                        thisStr = this.formula.toString();
                        break;
                    }
                    String fv = this.value.toString();
                    if (this.formatString != null) {
                        try {
                            double d = Double.parseDouble(fv);
                            thisStr = this.formatter.formatRawCellContents(d, this.formatIndex, this.formatString);
                        } catch (NumberFormatException e) {
                            thisStr = fv;
                        }
                        break;
                    }
                    thisStr = fv;
                    break;
                }
                case INLINE_STRING: {
                    XSSFRichTextString rtsi = new XSSFRichTextString(this.value.toString());
                    thisStr = rtsi.toString();
                    break;
                }
                case SST_STRING: {
                    String sstIndex = this.value.toString();
                    try {
                        int idx = Integer.parseInt(sstIndex);
                        RichTextString rtss = this.sharedStringsTable.getItemAt(idx);
                        thisStr = rtss.toString();
                    } catch (NumberFormatException ex) {
                        logger.log(7, "Failed to parse SST index '" + sstIndex, ex);
                    }
                    break;
                }
                case NUMBER: {
                    String n = this.value.toString();
                    if (this.formatString != null && n.length() > 0) {
                        thisStr = this.formatter.formatRawCellContents(Double.parseDouble(n), this.formatIndex, this.formatString);
                        break;
                    }
                    thisStr = n;
                    break;
                }
                default: {
                    thisStr = "(TODO: Unexpected type: " + (Object)((Object)this.nextDataType) + ")";
                }
            }
            this.checkForEmptyCellComments(EmptyCellCommentsCheckType.CELL);
            XSSFComment comment = this.comments != null ? this.comments.findCellComment(new CellAddress(this.cellRef)) : null;
            this.output.cell(this.cellRef, thisStr, comment);
        } else if ("f".equals(localName)) {
            this.fIsOpen = false;
        } else if ("is".equals(localName)) {
            this.isIsOpen = false;
        } else if ("row".equals(localName)) {
            this.checkForEmptyCellComments(EmptyCellCommentsCheckType.END_OF_ROW);
            this.output.endRow(this.rowNum);
            this.nextRowNum = this.rowNum + 1;
        } else if ("sheetData".equals(localName)) {
            this.checkForEmptyCellComments(EmptyCellCommentsCheckType.END_OF_SHEET_DATA);
            this.output.endSheet();
        } else if ("oddHeader".equals(localName) || "evenHeader".equals(localName) || "firstHeader".equals(localName)) {
            this.hfIsOpen = false;
            this.output.headerFooter(this.headerFooter.toString(), true, localName);
        } else if ("oddFooter".equals(localName) || "evenFooter".equals(localName) || "firstFooter".equals(localName)) {
            this.hfIsOpen = false;
            this.output.headerFooter(this.headerFooter.toString(), false, localName);
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        if (this.vIsOpen) {
            this.value.append(ch, start, length);
        }
        if (this.fIsOpen) {
            this.formula.append(ch, start, length);
        }
        if (this.hfIsOpen) {
            this.headerFooter.append(ch, start, length);
        }
    }

    private void checkForEmptyCellComments(EmptyCellCommentsCheckType type) {
        if (this.commentCellRefs != null && !this.commentCellRefs.isEmpty()) {
            CellAddress nextCommentCellRef;
            if (type == EmptyCellCommentsCheckType.END_OF_SHEET_DATA) {
                while (!this.commentCellRefs.isEmpty()) {
                    this.outputEmptyCellComment(this.commentCellRefs.remove());
                }
                return;
            }
            if (this.cellRef == null) {
                if (type == EmptyCellCommentsCheckType.END_OF_ROW) {
                    while (!this.commentCellRefs.isEmpty()) {
                        if (this.commentCellRefs.peek().getRow() == this.rowNum) {
                            this.outputEmptyCellComment(this.commentCellRefs.remove());
                            continue;
                        }
                        return;
                    }
                    return;
                }
                throw new IllegalStateException("Cell ref should be null only if there are only empty cells in the row; rowNum: " + this.rowNum);
            }
            do {
                CellAddress cellRef = new CellAddress(this.cellRef);
                CellAddress peekCellRef = this.commentCellRefs.peek();
                if (type == EmptyCellCommentsCheckType.CELL && cellRef.equals(peekCellRef)) {
                    this.commentCellRefs.remove();
                    return;
                }
                int comparison = peekCellRef.compareTo(cellRef);
                if (comparison > 0 && type == EmptyCellCommentsCheckType.END_OF_ROW && peekCellRef.getRow() <= this.rowNum) {
                    nextCommentCellRef = this.commentCellRefs.remove();
                    this.outputEmptyCellComment(nextCommentCellRef);
                    continue;
                }
                if (comparison < 0 && type == EmptyCellCommentsCheckType.CELL && peekCellRef.getRow() <= this.rowNum) {
                    nextCommentCellRef = this.commentCellRefs.remove();
                    this.outputEmptyCellComment(nextCommentCellRef);
                    continue;
                }
                nextCommentCellRef = null;
            } while (nextCommentCellRef != null && !this.commentCellRefs.isEmpty());
        }
    }

    private void outputEmptyCellComment(CellAddress cellRef) {
        XSSFComment comment = this.comments.findCellComment(cellRef);
        this.output.cell(cellRef.formatAsString(), null, comment);
    }

    public static interface SheetContentsHandler {
        public void startRow(int var1);

        public void endRow(int var1);

        public void cell(String var1, String var2, XSSFComment var3);

        default public void headerFooter(String text, boolean isHeader, String tagName) {
        }

        default public void endSheet() {
        }
    }

    private static enum EmptyCellCommentsCheckType {
        CELL,
        END_OF_ROW,
        END_OF_SHEET_DATA;

    }

    static enum xssfDataType {
        BOOLEAN,
        ERROR,
        FORMULA,
        INLINE_STRING,
        SST_STRING,
        NUMBER;

    }
}

