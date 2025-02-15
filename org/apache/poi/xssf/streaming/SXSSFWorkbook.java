/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.xssf.streaming;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import org.apache.commons.compress.archivers.ArchiveOutputStream;
import org.apache.commons.compress.archivers.zip.Zip64Mode;
import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;
import org.apache.commons.compress.archivers.zip.ZipArchiveOutputStream;
import org.apache.poi.openxml4j.util.ZipArchiveThresholdInputStream;
import org.apache.poi.openxml4j.util.ZipEntrySource;
import org.apache.poi.openxml4j.util.ZipFileZipEntrySource;
import org.apache.poi.openxml4j.util.ZipSecureFile;
import org.apache.poi.ss.SpreadsheetVersion;
import org.apache.poi.ss.formula.udf.UDFFinder;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Name;
import org.apache.poi.ss.usermodel.PictureData;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.SheetVisibility;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.util.IOUtils;
import org.apache.poi.util.Internal;
import org.apache.poi.util.NotImplemented;
import org.apache.poi.util.POILogFactory;
import org.apache.poi.util.POILogger;
import org.apache.poi.util.Removal;
import org.apache.poi.util.TempFile;
import org.apache.poi.xssf.model.SharedStringsTable;
import org.apache.poi.xssf.streaming.GZIPSheetDataWriter;
import org.apache.poi.xssf.streaming.OpcZipArchiveOutputStream;
import org.apache.poi.xssf.streaming.SXSSFCreationHelper;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SheetDataWriter;
import org.apache.poi.xssf.usermodel.XSSFChartSheet;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class SXSSFWorkbook
implements Workbook {
    public static final int DEFAULT_WINDOW_SIZE = 100;
    private static final POILogger logger = POILogFactory.getLogger(SXSSFWorkbook.class);
    private final XSSFWorkbook _wb;
    private final Map<SXSSFSheet, XSSFSheet> _sxFromXHash = new HashMap<SXSSFSheet, XSSFSheet>();
    private final Map<XSSFSheet, SXSSFSheet> _xFromSxHash = new HashMap<XSSFSheet, SXSSFSheet>();
    private int _randomAccessWindowSize = 100;
    private boolean _compressTmpFiles;
    private final SharedStringsTable _sharedStringSource;
    private Zip64Mode zip64Mode = Zip64Mode.AsNeeded;

    public SXSSFWorkbook() {
        this(null);
    }

    public SXSSFWorkbook(XSSFWorkbook workbook) {
        this(workbook, 100);
    }

    public SXSSFWorkbook(XSSFWorkbook workbook, int rowAccessWindowSize) {
        this(workbook, rowAccessWindowSize, false);
    }

    public SXSSFWorkbook(XSSFWorkbook workbook, int rowAccessWindowSize, boolean compressTmpFiles) {
        this(workbook, rowAccessWindowSize, compressTmpFiles, false);
    }

    public SXSSFWorkbook(XSSFWorkbook workbook, int rowAccessWindowSize, boolean compressTmpFiles, boolean useSharedStringsTable) {
        this.setRandomAccessWindowSize(rowAccessWindowSize);
        this.setCompressTempFiles(compressTmpFiles);
        if (workbook == null) {
            this._wb = new XSSFWorkbook();
            this._sharedStringSource = useSharedStringsTable ? this._wb.getSharedStringSource() : null;
        } else {
            this._wb = workbook;
            this._sharedStringSource = useSharedStringsTable ? this._wb.getSharedStringSource() : null;
            for (Sheet sheet : this._wb) {
                this.createAndRegisterSXSSFSheet((XSSFSheet)sheet);
            }
        }
    }

    public SXSSFWorkbook(int rowAccessWindowSize) {
        this(null, rowAccessWindowSize);
    }

    public int getRandomAccessWindowSize() {
        return this._randomAccessWindowSize;
    }

    private void setRandomAccessWindowSize(int rowAccessWindowSize) {
        if (rowAccessWindowSize == 0 || rowAccessWindowSize < -1) {
            throw new IllegalArgumentException("rowAccessWindowSize must be greater than 0 or -1");
        }
        this._randomAccessWindowSize = rowAccessWindowSize;
    }

    public void setZip64Mode(Zip64Mode zip64Mode) {
        this.zip64Mode = zip64Mode;
    }

    public boolean isCompressTempFiles() {
        return this._compressTmpFiles;
    }

    public void setCompressTempFiles(boolean compress) {
        this._compressTmpFiles = compress;
    }

    @Internal
    protected SharedStringsTable getSharedStringSource() {
        return this._sharedStringSource;
    }

    protected SheetDataWriter createSheetDataWriter() throws IOException {
        if (this._compressTmpFiles) {
            return new GZIPSheetDataWriter(this._sharedStringSource);
        }
        return new SheetDataWriter(this._sharedStringSource);
    }

    XSSFSheet getXSSFSheet(SXSSFSheet sheet) {
        return this._sxFromXHash.get(sheet);
    }

    SXSSFSheet getSXSSFSheet(XSSFSheet sheet) {
        return this._xFromSxHash.get(sheet);
    }

    void registerSheetMapping(SXSSFSheet sxSheet, XSSFSheet xSheet) {
        this._sxFromXHash.put(sxSheet, xSheet);
        this._xFromSxHash.put(xSheet, sxSheet);
    }

    void deregisterSheetMapping(XSSFSheet xSheet) {
        SXSSFSheet sxSheet = this.getSXSSFSheet(xSheet);
        try {
            sxSheet.getSheetDataWriter().close();
        } catch (IOException iOException) {
            // empty catch block
        }
        this._sxFromXHash.remove(sxSheet);
        this._xFromSxHash.remove(xSheet);
    }

    private XSSFSheet getSheetFromZipEntryName(String sheetRef) {
        for (XSSFSheet sheet : this._sxFromXHash.values()) {
            if (!sheetRef.equals(sheet.getPackagePart().getPartName().getName().substring(1))) continue;
            return sheet;
        }
        return null;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    protected void injectData(ZipEntrySource zipEntrySource, OutputStream out) throws IOException {
        ZipArchiveOutputStream zos = this.createArchiveOutputStream(out);
        try {
            Enumeration<? extends ZipArchiveEntry> en = zipEntrySource.getEntries();
            while (en.hasMoreElements()) {
                ZipArchiveEntry ze = en.nextElement();
                ZipArchiveEntry zeOut = new ZipArchiveEntry(ze.getName());
                zeOut.setSize(ze.getSize());
                zeOut.setTime(ze.getTime());
                ((ArchiveOutputStream)zos).putArchiveEntry(zeOut);
                try {
                    InputStream is = zipEntrySource.getInputStream(ze);
                    Throwable throwable = null;
                    try {
                        XSSFSheet xSheet;
                        if (is instanceof ZipArchiveThresholdInputStream) {
                            ((ZipArchiveThresholdInputStream)is).setGuardState(false);
                        }
                        if ((xSheet = this.getSheetFromZipEntryName(ze.getName())) != null && !(xSheet instanceof XSSFChartSheet)) {
                            SXSSFSheet sxSheet = this.getSXSSFSheet(xSheet);
                            InputStream xis = sxSheet.getWorksheetXMLInputStream();
                            Throwable throwable2 = null;
                            try {
                                SXSSFWorkbook.copyStreamAndInjectWorksheet(is, zos, xis);
                                continue;
                            } catch (Throwable throwable3) {
                                throwable2 = throwable3;
                                throw throwable3;
                            } finally {
                                if (xis == null) continue;
                                if (throwable2 != null) {
                                    try {
                                        xis.close();
                                    } catch (Throwable throwable4) {
                                        throwable2.addSuppressed(throwable4);
                                    }
                                    continue;
                                }
                                xis.close();
                                continue;
                            }
                        }
                        IOUtils.copy(is, zos);
                    } catch (Throwable throwable5) {
                        throwable = throwable5;
                        throw throwable5;
                    } finally {
                        if (is == null) continue;
                        if (throwable != null) {
                            try {
                                is.close();
                            } catch (Throwable throwable6) {
                                throwable.addSuppressed(throwable6);
                            }
                            continue;
                        }
                        is.close();
                    }
                } finally {
                    ((ArchiveOutputStream)zos).closeArchiveEntry();
                }
            }
        } finally {
            ((ArchiveOutputStream)zos).finish();
            zipEntrySource.close();
        }
    }

    protected ZipArchiveOutputStream createArchiveOutputStream(OutputStream out) {
        if (Zip64Mode.Always.equals((Object)this.zip64Mode)) {
            return new OpcZipArchiveOutputStream(out);
        }
        ZipArchiveOutputStream zos = new ZipArchiveOutputStream(out);
        zos.setUseZip64(this.zip64Mode);
        return zos;
    }

    private static void copyStreamAndInjectWorksheet(InputStream in, OutputStream out, InputStream worksheetData) throws IOException {
        int c;
        InputStreamReader inReader = new InputStreamReader(in, StandardCharsets.UTF_8);
        OutputStreamWriter outWriter = new OutputStreamWriter(out, StandardCharsets.UTF_8);
        boolean needsStartTag = true;
        int pos = 0;
        String s = "<sheetData";
        int n = s.length();
        while ((c = inReader.read()) != -1) {
            if (c == s.charAt(pos)) {
                if (++pos != n) continue;
                if (!"<sheetData".equals(s)) break;
                c = inReader.read();
                if (c == -1) {
                    outWriter.write(s);
                    break;
                }
                if (c == 62) {
                    outWriter.write(s);
                    outWriter.write(c);
                    s = "</sheetData>";
                    n = s.length();
                    pos = 0;
                    needsStartTag = false;
                    continue;
                }
                if (c == 47) {
                    c = inReader.read();
                    if (c == -1) {
                        outWriter.write(s);
                        break;
                    }
                    if (c == 62) break;
                    outWriter.write(s);
                    outWriter.write(47);
                    outWriter.write(c);
                    pos = 0;
                    continue;
                }
                outWriter.write(s);
                outWriter.write(47);
                outWriter.write(c);
                pos = 0;
                continue;
            }
            if (pos > 0) {
                outWriter.write(s, 0, pos);
            }
            if (c == s.charAt(0)) {
                pos = 1;
                continue;
            }
            outWriter.write(c);
            pos = 0;
        }
        outWriter.flush();
        if (needsStartTag) {
            outWriter.write("<sheetData>\n");
            outWriter.flush();
        }
        IOUtils.copy(worksheetData, out);
        outWriter.write("</sheetData>");
        outWriter.flush();
        while ((c = inReader.read()) != -1) {
            outWriter.write(c);
        }
        outWriter.flush();
    }

    public XSSFWorkbook getXSSFWorkbook() {
        return this._wb;
    }

    @Override
    public int getActiveSheetIndex() {
        return this._wb.getActiveSheetIndex();
    }

    @Override
    public void setActiveSheet(int sheetIndex) {
        this._wb.setActiveSheet(sheetIndex);
    }

    @Override
    public int getFirstVisibleTab() {
        return this._wb.getFirstVisibleTab();
    }

    @Override
    public void setFirstVisibleTab(int sheetIndex) {
        this._wb.setFirstVisibleTab(sheetIndex);
    }

    @Override
    public void setSheetOrder(String sheetname, int pos) {
        this._wb.setSheetOrder(sheetname, pos);
    }

    @Override
    public void setSelectedTab(int index) {
        this._wb.setSelectedTab(index);
    }

    @Override
    public void setSheetName(int sheet, String name) {
        this._wb.setSheetName(sheet, name);
    }

    @Override
    public String getSheetName(int sheet) {
        return this._wb.getSheetName(sheet);
    }

    @Override
    public int getSheetIndex(String name) {
        return this._wb.getSheetIndex(name);
    }

    @Override
    public int getSheetIndex(Sheet sheet) {
        return this._wb.getSheetIndex(this.getXSSFSheet((SXSSFSheet)sheet));
    }

    @Override
    public SXSSFSheet createSheet() {
        return this.createAndRegisterSXSSFSheet(this._wb.createSheet());
    }

    SXSSFSheet createAndRegisterSXSSFSheet(XSSFSheet xSheet) {
        SXSSFSheet sxSheet;
        try {
            sxSheet = new SXSSFSheet(this, xSheet);
        } catch (IOException ioe) {
            throw new RuntimeException(ioe);
        }
        this.registerSheetMapping(sxSheet, xSheet);
        return sxSheet;
    }

    @Override
    public SXSSFSheet createSheet(String sheetname) {
        return this.createAndRegisterSXSSFSheet(this._wb.createSheet(sheetname));
    }

    @Override
    @NotImplemented
    public Sheet cloneSheet(int sheetNum) {
        throw new RuntimeException("Not Implemented");
    }

    @Override
    public int getNumberOfSheets() {
        return this._wb.getNumberOfSheets();
    }

    @Override
    public Iterator<Sheet> sheetIterator() {
        return new SheetIterator<Sheet>();
    }

    @Override
    public Iterator<Sheet> iterator() {
        return this.sheetIterator();
    }

    @Override
    public SXSSFSheet getSheetAt(int index) {
        return this.getSXSSFSheet(this._wb.getSheetAt(index));
    }

    @Override
    public SXSSFSheet getSheet(String name) {
        return this.getSXSSFSheet(this._wb.getSheet(name));
    }

    @Override
    public void removeSheetAt(int index) {
        XSSFSheet xSheet = this._wb.getSheetAt(index);
        SXSSFSheet sxSheet = this.getSXSSFSheet(xSheet);
        this._wb.removeSheetAt(index);
        this.deregisterSheetMapping(xSheet);
        try {
            sxSheet.dispose();
        } catch (IOException e) {
            logger.log(5, e);
        }
    }

    @Override
    public Font createFont() {
        return this._wb.createFont();
    }

    @Override
    public Font findFont(boolean bold, short color, short fontHeight, String name, boolean italic, boolean strikeout, short typeOffset, byte underline) {
        return this._wb.findFont(bold, color, fontHeight, name, italic, strikeout, typeOffset, underline);
    }

    @Override
    @Deprecated
    public short getNumberOfFonts() {
        return (short)this.getNumberOfFontsAsInt();
    }

    @Override
    public int getNumberOfFontsAsInt() {
        return this._wb.getNumberOfFontsAsInt();
    }

    @Override
    @Deprecated
    public Font getFontAt(short idx) {
        return this.getFontAt((int)idx);
    }

    @Override
    public Font getFontAt(int idx) {
        return this._wb.getFontAt(idx);
    }

    @Override
    public CellStyle createCellStyle() {
        return this._wb.createCellStyle();
    }

    @Override
    public int getNumCellStyles() {
        return this._wb.getNumCellStyles();
    }

    @Override
    public CellStyle getCellStyleAt(int idx) {
        return this._wb.getCellStyleAt(idx);
    }

    @Override
    public void close() throws IOException {
        for (SXSSFSheet sheet : this._xFromSxHash.values()) {
            try {
                sheet.getSheetDataWriter().close();
            } catch (IOException e) {
                logger.log(5, "An exception occurred while closing sheet data writer for sheet " + sheet.getSheetName() + ".", e);
            }
        }
        this._wb.close();
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void write(OutputStream stream) throws IOException {
        boolean deleted;
        this.flushSheets();
        File tmplFile = TempFile.createTempFile("poi-sxssf-template", ".xlsx");
        try {
            try (FileOutputStream os = new FileOutputStream(tmplFile);){
                this._wb.write(os);
            }
            var5_4 = null;
            try (ZipSecureFile zf = new ZipSecureFile(tmplFile);
                 ZipFileZipEntrySource source = new ZipFileZipEntrySource(zf);){
                this.injectData(source, stream);
            } catch (Throwable throwable) {
                var5_4 = throwable;
                throw throwable;
            }
        } finally {
            deleted = tmplFile.delete();
        }
        if (!deleted) {
            throw new IOException("Could not delete temporary file after processing: " + tmplFile);
        }
    }

    protected void flushSheets() throws IOException {
        for (SXSSFSheet sheet : this._xFromSxHash.values()) {
            sheet.flushRows();
        }
    }

    public boolean dispose() {
        boolean success = true;
        for (SXSSFSheet sheet : this._sxFromXHash.keySet()) {
            try {
                success = sheet.dispose() && success;
            } catch (IOException e) {
                logger.log(5, e);
                success = false;
            }
        }
        return success;
    }

    @Override
    public int getNumberOfNames() {
        return this._wb.getNumberOfNames();
    }

    @Override
    public Name getName(String name) {
        return this._wb.getName(name);
    }

    @Override
    public List<? extends Name> getNames(String name) {
        return this._wb.getNames(name);
    }

    @Override
    public List<? extends Name> getAllNames() {
        return this._wb.getAllNames();
    }

    @Override
    public Name createName() {
        return this._wb.createName();
    }

    @Override
    public void removeName(Name name) {
        this._wb.removeName(name);
    }

    @Override
    public void setPrintArea(int sheetIndex, String reference) {
        this._wb.setPrintArea(sheetIndex, reference);
    }

    @Override
    public void setPrintArea(int sheetIndex, int startColumn, int endColumn, int startRow, int endRow) {
        this._wb.setPrintArea(sheetIndex, startColumn, endColumn, startRow, endRow);
    }

    @Override
    public String getPrintArea(int sheetIndex) {
        return this._wb.getPrintArea(sheetIndex);
    }

    @Override
    public void removePrintArea(int sheetIndex) {
        this._wb.removePrintArea(sheetIndex);
    }

    @Override
    public Row.MissingCellPolicy getMissingCellPolicy() {
        return this._wb.getMissingCellPolicy();
    }

    @Override
    public void setMissingCellPolicy(Row.MissingCellPolicy missingCellPolicy) {
        this._wb.setMissingCellPolicy(missingCellPolicy);
    }

    @Override
    public DataFormat createDataFormat() {
        return this._wb.createDataFormat();
    }

    @Override
    public int addPicture(byte[] pictureData, int format) {
        return this._wb.addPicture(pictureData, format);
    }

    @Override
    public List<? extends PictureData> getAllPictures() {
        return this._wb.getAllPictures();
    }

    @Override
    public CreationHelper getCreationHelper() {
        return new SXSSFCreationHelper(this);
    }

    protected boolean isDate1904() {
        return this._wb.isDate1904();
    }

    @Override
    @NotImplemented(value="XSSFWorkbook#isHidden is not implemented")
    public boolean isHidden() {
        return this._wb.isHidden();
    }

    @Override
    @NotImplemented(value="XSSFWorkbook#setHidden is not implemented")
    public void setHidden(boolean hiddenFlag) {
        this._wb.setHidden(hiddenFlag);
    }

    @Override
    public boolean isSheetHidden(int sheetIx) {
        return this._wb.isSheetHidden(sheetIx);
    }

    @Override
    public boolean isSheetVeryHidden(int sheetIx) {
        return this._wb.isSheetVeryHidden(sheetIx);
    }

    @Override
    public SheetVisibility getSheetVisibility(int sheetIx) {
        return this._wb.getSheetVisibility(sheetIx);
    }

    @Override
    public void setSheetHidden(int sheetIx, boolean hidden) {
        this._wb.setSheetHidden(sheetIx, hidden);
    }

    @Override
    public void setSheetVisibility(int sheetIx, SheetVisibility visibility) {
        this._wb.setSheetVisibility(sheetIx, visibility);
    }

    @Override
    @Deprecated
    @Removal(version="3.20")
    public Name getNameAt(int nameIndex) {
        return this._wb.getNameAt(nameIndex);
    }

    @Override
    @Deprecated
    @Removal(version="3.20")
    public int getNameIndex(String name) {
        return this._wb.getNameIndex(name);
    }

    @Override
    @Deprecated
    @Removal(version="3.20")
    public void removeName(int index) {
        this._wb.removeName(index);
    }

    @Override
    @Deprecated
    @Removal(version="3.20")
    public void removeName(String name) {
        this._wb.removeName(name);
    }

    @Override
    @NotImplemented
    public int linkExternalWorkbook(String name, Workbook workbook) {
        throw new RuntimeException("Not Implemented");
    }

    @Override
    public void addToolPack(UDFFinder toopack) {
        this._wb.addToolPack(toopack);
    }

    @Override
    public void setForceFormulaRecalculation(boolean value) {
        this._wb.setForceFormulaRecalculation(value);
    }

    @Override
    public boolean getForceFormulaRecalculation() {
        return this._wb.getForceFormulaRecalculation();
    }

    @Override
    public SpreadsheetVersion getSpreadsheetVersion() {
        return SpreadsheetVersion.EXCEL2007;
    }

    @Override
    public int addOlePackage(byte[] oleData, String label, String fileName, String command) throws IOException {
        return this._wb.addOlePackage(oleData, label, fileName, command);
    }

    private final class SheetIterator<T extends Sheet>
    implements Iterator<T> {
        private final Iterator<XSSFSheet> it;

        public SheetIterator() {
            this.it = SXSSFWorkbook.this._wb.iterator();
        }

        @Override
        public boolean hasNext() {
            return this.it.hasNext();
        }

        @Override
        public T next() throws NoSuchElementException {
            XSSFSheet xssfSheet = this.it.next();
            return (T)SXSSFWorkbook.this.getSXSSFSheet(xssfSheet);
        }

        @Override
        public void remove() throws IllegalStateException {
            throw new UnsupportedOperationException("remove method not supported on XSSFWorkbook.iterator(). Use Sheet.removeSheetAt(int) instead.");
        }
    }
}

