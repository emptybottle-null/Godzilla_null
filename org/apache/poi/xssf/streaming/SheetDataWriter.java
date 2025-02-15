/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.xssf.streaming;

import java.io.BufferedWriter;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.FormulaError;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.util.POILogFactory;
import org.apache.poi.util.POILogger;
import org.apache.poi.util.StringCodepointsIterable;
import org.apache.poi.util.TempFile;
import org.apache.poi.xssf.model.SharedStringsTable;
import org.apache.poi.xssf.streaming.SXSSFRow;
import org.apache.poi.xssf.usermodel.XSSFRichTextString;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.STCellType;

public class SheetDataWriter
implements Closeable {
    private static final POILogger logger = POILogFactory.getLogger(SheetDataWriter.class);
    private final File _fd = this.createTempFile();
    private final Writer _out = this.createWriter(this._fd);
    private int _rownum;
    private int _numberOfFlushedRows;
    private int _lowestIndexOfFlushedRows;
    private int _numberOfCellsOfLastFlushedRow;
    private int _numberLastFlushedRow = -1;
    private SharedStringsTable _sharedStringSource;

    public SheetDataWriter() throws IOException {
    }

    public SheetDataWriter(SharedStringsTable sharedStringsTable) throws IOException {
        this();
        this._sharedStringSource = sharedStringsTable;
    }

    public File createTempFile() throws IOException {
        return TempFile.createTempFile("poi-sxssf-sheet", ".xml");
    }

    public Writer createWriter(File fd) throws IOException {
        OutputStream decorated;
        FileOutputStream fos = new FileOutputStream(fd);
        try {
            decorated = this.decorateOutputStream(fos);
        } catch (IOException e) {
            fos.close();
            throw e;
        }
        return new BufferedWriter(new OutputStreamWriter(decorated, StandardCharsets.UTF_8));
    }

    protected OutputStream decorateOutputStream(FileOutputStream fos) throws IOException {
        return fos;
    }

    @Override
    public void close() throws IOException {
        this._out.flush();
        this._out.close();
    }

    protected File getTempFile() {
        return this._fd;
    }

    public InputStream getWorksheetXMLInputStream() throws IOException {
        File fd = this.getTempFile();
        FileInputStream fis = new FileInputStream(fd);
        try {
            return this.decorateInputStream(fis);
        } catch (IOException e) {
            fis.close();
            throw e;
        }
    }

    protected InputStream decorateInputStream(FileInputStream fis) throws IOException {
        return fis;
    }

    public int getNumberOfFlushedRows() {
        return this._numberOfFlushedRows;
    }

    public int getNumberOfCellsOfLastFlushedRow() {
        return this._numberOfCellsOfLastFlushedRow;
    }

    public int getLowestIndexOfFlushedRows() {
        return this._lowestIndexOfFlushedRows;
    }

    public int getLastFlushedRow() {
        return this._numberLastFlushedRow;
    }

    protected void finalize() throws Throwable {
        if (!this._fd.delete()) {
            logger.log(7, "Can't delete temporary encryption file: " + this._fd);
        }
        super.finalize();
    }

    public void writeRow(int rownum, SXSSFRow row) throws IOException {
        if (this._numberOfFlushedRows == 0) {
            this._lowestIndexOfFlushedRows = rownum;
        }
        this._numberLastFlushedRow = Math.max(rownum, this._numberLastFlushedRow);
        this._numberOfCellsOfLastFlushedRow = row.getLastCellNum();
        ++this._numberOfFlushedRows;
        this.beginRow(rownum, row);
        Iterator<Cell> cells = row.allCellsIterator();
        int columnIndex = 0;
        while (cells.hasNext()) {
            this.writeCell(columnIndex++, cells.next());
        }
        this.endRow();
    }

    void beginRow(int rownum, SXSSFRow row) throws IOException {
        this._out.write("<row");
        this.writeAttribute("r", Integer.toString(rownum + 1));
        if (row.hasCustomHeight()) {
            this.writeAttribute("customHeight", "true");
            this.writeAttribute("ht", Float.toString(row.getHeightInPoints()));
        }
        if (row.getZeroHeight()) {
            this.writeAttribute("hidden", "true");
        }
        if (row.isFormatted()) {
            this.writeAttribute("s", Integer.toString(row.getRowStyleIndex()));
            this.writeAttribute("customFormat", "1");
        }
        if (row.getOutlineLevel() != 0) {
            this.writeAttribute("outlineLevel", Integer.toString(row.getOutlineLevel()));
        }
        if (row.getHidden() != null) {
            this.writeAttribute("hidden", row.getHidden() != false ? "1" : "0");
        }
        if (row.getCollapsed() != null) {
            this.writeAttribute("collapsed", row.getCollapsed() != false ? "1" : "0");
        }
        this._out.write(">\n");
        this._rownum = rownum;
    }

    void endRow() throws IOException {
        this._out.write("</row>\n");
    }

    public void writeCell(int columnIndex, Cell cell) throws IOException {
        if (cell == null) {
            return;
        }
        String ref = new CellReference(this._rownum, columnIndex).formatAsString();
        this._out.write("<c");
        this.writeAttribute("r", ref);
        CellStyle cellStyle = cell.getCellStyle();
        if (cellStyle.getIndex() != 0) {
            this.writeAttribute("s", Integer.toString(cellStyle.getIndex() & 0xFFFF));
        }
        CellType cellType = cell.getCellType();
        switch (cellType) {
            case BLANK: {
                this._out.write(62);
                break;
            }
            case FORMULA: {
                switch (cell.getCachedFormulaResultType()) {
                    case NUMERIC: {
                        this.writeAttribute("t", "n");
                        break;
                    }
                    case STRING: {
                        this.writeAttribute("t", STCellType.STR.toString());
                        break;
                    }
                    case BOOLEAN: {
                        this.writeAttribute("t", "b");
                        break;
                    }
                    case ERROR: {
                        this.writeAttribute("t", "e");
                    }
                }
                this._out.write("><f>");
                this.outputQuotedString(cell.getCellFormula());
                this._out.write("</f>");
                switch (cell.getCachedFormulaResultType()) {
                    case NUMERIC: {
                        double nval = cell.getNumericCellValue();
                        if (Double.isNaN(nval)) break;
                        this._out.write("<v>");
                        this._out.write(Double.toString(nval));
                        this._out.write("</v>");
                        break;
                    }
                    case STRING: {
                        String value = cell.getStringCellValue();
                        if (value == null || value.isEmpty()) break;
                        this._out.write("<v>");
                        this._out.write(value);
                        this._out.write("</v>");
                        break;
                    }
                    case BOOLEAN: {
                        this._out.write("><v>");
                        this._out.write(cell.getBooleanCellValue() ? "1" : "0");
                        this._out.write("</v>");
                        break;
                    }
                    case ERROR: {
                        FormulaError error = FormulaError.forInt(cell.getErrorCellValue());
                        this._out.write("><v>");
                        this._out.write(error.getString());
                        this._out.write("</v>");
                        break;
                    }
                }
                break;
            }
            case STRING: {
                if (this._sharedStringSource != null) {
                    XSSFRichTextString rt = new XSSFRichTextString(cell.getStringCellValue());
                    int sRef = this._sharedStringSource.addSharedStringItem(rt);
                    this.writeAttribute("t", STCellType.S.toString());
                    this._out.write("><v>");
                    this._out.write(String.valueOf(sRef));
                    this._out.write("</v>");
                    break;
                }
                this.writeAttribute("t", "inlineStr");
                this._out.write("><is><t");
                if (this.hasLeadingTrailingSpaces(cell.getStringCellValue())) {
                    this.writeAttribute("xml:space", "preserve");
                }
                this._out.write(">");
                this.outputQuotedString(cell.getStringCellValue());
                this._out.write("</t></is>");
                break;
            }
            case NUMERIC: {
                this.writeAttribute("t", "n");
                this._out.write("><v>");
                this._out.write(Double.toString(cell.getNumericCellValue()));
                this._out.write("</v>");
                break;
            }
            case BOOLEAN: {
                this.writeAttribute("t", "b");
                this._out.write("><v>");
                this._out.write(cell.getBooleanCellValue() ? "1" : "0");
                this._out.write("</v>");
                break;
            }
            case ERROR: {
                FormulaError error = FormulaError.forInt(cell.getErrorCellValue());
                this.writeAttribute("t", "e");
                this._out.write("><v>");
                this._out.write(error.getString());
                this._out.write("</v>");
                break;
            }
            default: {
                throw new IllegalStateException("Invalid cell type: " + (Object)((Object)cellType));
            }
        }
        this._out.write("</c>");
    }

    private void writeAttribute(String name, String value) throws IOException {
        this._out.write(32);
        this._out.write(name);
        this._out.write("=\"");
        this._out.write(value);
        this._out.write(34);
    }

    boolean hasLeadingTrailingSpaces(String str) {
        if (str != null && str.length() > 0) {
            char firstChar = str.charAt(0);
            char lastChar = str.charAt(str.length() - 1);
            return Character.isWhitespace(firstChar) || Character.isWhitespace(lastChar);
        }
        return false;
    }

    protected void outputQuotedString(String s) throws IOException {
        if (s == null || s.length() == 0) {
            return;
        }
        Iterator<String> iterator = new StringCodepointsIterable(s).iterator();
        block20: while (iterator.hasNext()) {
            String codepoint;
            switch (codepoint = iterator.next()) {
                case "<": {
                    this._out.write("&lt;");
                    continue block20;
                }
                case ">": {
                    this._out.write("&gt;");
                    continue block20;
                }
                case "&": {
                    this._out.write("&amp;");
                    continue block20;
                }
                case "\"": {
                    this._out.write("&quot;");
                    continue block20;
                }
                case "\n": {
                    this._out.write("&#xa;");
                    continue block20;
                }
                case "\r": {
                    this._out.write("&#xd;");
                    continue block20;
                }
                case "\t": {
                    this._out.write("&#x9;");
                    continue block20;
                }
                case "\u00a0": {
                    this._out.write("&#xa0;");
                    continue block20;
                }
            }
            if (codepoint.length() == 1) {
                char c = codepoint.charAt(0);
                if (SheetDataWriter.replaceWithQuestionMark(c)) {
                    this._out.write(63);
                    continue;
                }
                this._out.write(c);
                continue;
            }
            this._out.write(codepoint);
        }
    }

    static boolean replaceWithQuestionMark(char c) {
        return c < ' ' || '\ufffe' <= c && c <= '\uffff';
    }

    boolean dispose() throws IOException {
        boolean ret;
        try {
            this._out.close();
        } finally {
            ret = this._fd.delete();
        }
        return ret;
    }
}

