/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.hssf.usermodel;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.regex.Pattern;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.POIDocument;
import org.apache.poi.ddf.EscherBSERecord;
import org.apache.poi.ddf.EscherBitmapBlip;
import org.apache.poi.ddf.EscherBlipRecord;
import org.apache.poi.ddf.EscherMetafileBlip;
import org.apache.poi.ddf.EscherRecord;
import org.apache.poi.hpsf.ClassID;
import org.apache.poi.hpsf.ClassIDPredefined;
import org.apache.poi.hssf.OldExcelFormatException;
import org.apache.poi.hssf.model.DrawingManager2;
import org.apache.poi.hssf.model.HSSFFormulaParser;
import org.apache.poi.hssf.model.InternalSheet;
import org.apache.poi.hssf.model.InternalWorkbook;
import org.apache.poi.hssf.model.RecordStream;
import org.apache.poi.hssf.model.WorkbookRecordList;
import org.apache.poi.hssf.record.AbstractEscherHolderRecord;
import org.apache.poi.hssf.record.BackupRecord;
import org.apache.poi.hssf.record.DrawingGroupRecord;
import org.apache.poi.hssf.record.ExtendedFormatRecord;
import org.apache.poi.hssf.record.FilePassRecord;
import org.apache.poi.hssf.record.FontRecord;
import org.apache.poi.hssf.record.LabelRecord;
import org.apache.poi.hssf.record.LabelSSTRecord;
import org.apache.poi.hssf.record.NameRecord;
import org.apache.poi.hssf.record.RecalcIdRecord;
import org.apache.poi.hssf.record.Record;
import org.apache.poi.hssf.record.RecordFactory;
import org.apache.poi.hssf.record.UnknownRecord;
import org.apache.poi.hssf.record.aggregates.RecordAggregate;
import org.apache.poi.hssf.record.common.UnicodeString;
import org.apache.poi.hssf.record.crypto.Biff8DecryptingStream;
import org.apache.poi.hssf.record.crypto.Biff8EncryptionKey;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFCreationHelper;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFName;
import org.apache.poi.hssf.usermodel.HSSFObjectData;
import org.apache.poi.hssf.usermodel.HSSFPalette;
import org.apache.poi.hssf.usermodel.HSSFPatriarch;
import org.apache.poi.hssf.usermodel.HSSFPictureData;
import org.apache.poi.hssf.usermodel.HSSFShape;
import org.apache.poi.hssf.usermodel.HSSFShapeContainer;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.poifs.crypt.ChunkedCipherOutputStream;
import org.apache.poi.poifs.crypt.Decryptor;
import org.apache.poi.poifs.crypt.EncryptionInfo;
import org.apache.poi.poifs.crypt.EncryptionMode;
import org.apache.poi.poifs.crypt.EncryptionVerifier;
import org.apache.poi.poifs.crypt.Encryptor;
import org.apache.poi.poifs.filesystem.DirectoryEntry;
import org.apache.poi.poifs.filesystem.DirectoryNode;
import org.apache.poi.poifs.filesystem.DocumentInputStream;
import org.apache.poi.poifs.filesystem.DocumentNode;
import org.apache.poi.poifs.filesystem.EntryUtils;
import org.apache.poi.poifs.filesystem.FilteringDirectoryNode;
import org.apache.poi.poifs.filesystem.Ole10Native;
import org.apache.poi.poifs.filesystem.POIFSDocument;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.SpreadsheetVersion;
import org.apache.poi.ss.formula.FormulaShifter;
import org.apache.poi.ss.formula.FormulaType;
import org.apache.poi.ss.formula.SheetNameFormatter;
import org.apache.poi.ss.formula.udf.AggregatingUDFFinder;
import org.apache.poi.ss.formula.udf.IndexedUDFFinder;
import org.apache.poi.ss.formula.udf.UDFFinder;
import org.apache.poi.ss.usermodel.Name;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.SheetVisibility;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.util.Configurator;
import org.apache.poi.util.HexDump;
import org.apache.poi.util.IOUtils;
import org.apache.poi.util.Internal;
import org.apache.poi.util.LittleEndian;
import org.apache.poi.util.LittleEndianByteArrayInputStream;
import org.apache.poi.util.LittleEndianByteArrayOutputStream;
import org.apache.poi.util.POILogFactory;
import org.apache.poi.util.POILogger;

public final class HSSFWorkbook
extends POIDocument
implements Workbook {
    private static final int MAX_RECORD_LENGTH = 100000;
    private static final Pattern COMMA_PATTERN = Pattern.compile(",");
    private static final int MAX_STYLES = 4030;
    private static final int DEBUG = 1;
    public static final int INITIAL_CAPACITY = Configurator.getIntValue("HSSFWorkbook.SheetInitialCapacity", 3);
    private InternalWorkbook workbook;
    protected List<HSSFSheet> _sheets;
    private ArrayList<HSSFName> names;
    private Map<Integer, HSSFFont> fonts;
    private boolean preserveNodes;
    private HSSFDataFormat formatter;
    private Row.MissingCellPolicy missingCellPolicy = Row.MissingCellPolicy.RETURN_NULL_AND_BLANK;
    private static final POILogger log = POILogFactory.getLogger(HSSFWorkbook.class);
    private UDFFinder _udfFinder = new IndexedUDFFinder(AggregatingUDFFinder.DEFAULT);

    public static HSSFWorkbook create(InternalWorkbook book) {
        return new HSSFWorkbook(book);
    }

    public HSSFWorkbook() {
        this(InternalWorkbook.createWorkbook());
    }

    private HSSFWorkbook(InternalWorkbook book) {
        super((DirectoryNode)null);
        this.workbook = book;
        this._sheets = new ArrayList<HSSFSheet>(INITIAL_CAPACITY);
        this.names = new ArrayList(INITIAL_CAPACITY);
    }

    public HSSFWorkbook(POIFSFileSystem fs) throws IOException {
        this(fs, true);
    }

    public HSSFWorkbook(POIFSFileSystem fs, boolean preserveNodes) throws IOException {
        this(fs.getRoot(), fs, preserveNodes);
    }

    public static String getWorkbookDirEntryName(DirectoryNode directory) {
        for (String wbName : InternalWorkbook.WORKBOOK_DIR_ENTRY_NAMES) {
            if (!directory.hasEntry(wbName)) continue;
            return wbName;
        }
        if (directory.hasEntry("EncryptedPackage")) {
            throw new EncryptedDocumentException("The supplied spreadsheet seems to be an Encrypted .xlsx file. It must be decrypted before use by XSSF, it cannot be used by HSSF");
        }
        if (directory.hasEntry("Book")) {
            throw new OldExcelFormatException("The supplied spreadsheet seems to be Excel 5.0/7.0 (BIFF5) format. POI only supports BIFF8 format (from Excel versions 97/2000/XP/2003)");
        }
        if (directory.hasEntry("WordDocument")) {
            throw new IllegalArgumentException("The document is really a DOC file");
        }
        throw new IllegalArgumentException("The supplied POIFSFileSystem does not contain a BIFF8 'Workbook' entry. Is it really an excel file? Had: " + directory.getEntryNames());
    }

    public HSSFWorkbook(DirectoryNode directory, POIFSFileSystem fs, boolean preserveNodes) throws IOException {
        this(directory, preserveNodes);
    }

    public HSSFWorkbook(DirectoryNode directory, boolean preserveNodes) throws IOException {
        super(directory);
        String workbookName = HSSFWorkbook.getWorkbookDirEntryName(directory);
        this.preserveNodes = preserveNodes;
        if (!preserveNodes) {
            this.clearDirectory();
        }
        this._sheets = new ArrayList<HSSFSheet>(INITIAL_CAPACITY);
        this.names = new ArrayList(INITIAL_CAPACITY);
        DocumentInputStream stream = directory.createDocumentInputStream(workbookName);
        List<Record> records = RecordFactory.createRecords(stream);
        this.workbook = InternalWorkbook.createWorkbook(records);
        this.setPropertiesFromWorkbook(this.workbook);
        int recOffset = this.workbook.getNumRecords();
        this.convertLabelRecords(records, recOffset);
        RecordStream rs = new RecordStream(records, recOffset);
        while (rs.hasNext()) {
            try {
                InternalSheet sheet = InternalSheet.createSheet(rs);
                this._sheets.add(new HSSFSheet(this, sheet));
            } catch (InternalSheet.UnsupportedBOFType eb) {
                log.log(5, "Unsupported BOF found of type " + eb.getType());
            }
        }
        for (int i = 0; i < this.workbook.getNumNames(); ++i) {
            NameRecord nameRecord = this.workbook.getNameRecord(i);
            HSSFName name = new HSSFName(this, nameRecord, this.workbook.getNameCommentRecord(nameRecord));
            this.names.add(name);
        }
    }

    public HSSFWorkbook(InputStream s) throws IOException {
        this(s, true);
    }

    public HSSFWorkbook(InputStream s, boolean preserveNodes) throws IOException {
        this(new POIFSFileSystem(s).getRoot(), preserveNodes);
    }

    private void setPropertiesFromWorkbook(InternalWorkbook book) {
        this.workbook = book;
    }

    private void convertLabelRecords(List<Record> records, int offset) {
        if (log.check(1)) {
            log.log(1, "convertLabelRecords called");
        }
        for (int k = offset; k < records.size(); ++k) {
            Record rec = records.get(k);
            if (rec.getSid() != 516) continue;
            LabelRecord oldrec = (LabelRecord)rec;
            records.remove(k);
            LabelSSTRecord newrec = new LabelSSTRecord();
            int stringid = this.workbook.addSSTString(new UnicodeString(oldrec.getValue()));
            newrec.setRow(oldrec.getRow());
            newrec.setColumn(oldrec.getColumn());
            newrec.setXFIndex(oldrec.getXFIndex());
            newrec.setSSTIndex(stringid);
            records.add(k, newrec);
        }
        if (log.check(1)) {
            log.log(1, "convertLabelRecords exit");
        }
    }

    @Override
    public Row.MissingCellPolicy getMissingCellPolicy() {
        return this.missingCellPolicy;
    }

    @Override
    public void setMissingCellPolicy(Row.MissingCellPolicy missingCellPolicy) {
        this.missingCellPolicy = missingCellPolicy;
    }

    @Override
    public void setSheetOrder(String sheetname, int pos) {
        int oldSheetIndex = this.getSheetIndex(sheetname);
        this._sheets.add(pos, this._sheets.remove(oldSheetIndex));
        this.workbook.setSheetOrder(sheetname, pos);
        FormulaShifter shifter = FormulaShifter.createForSheetShift(oldSheetIndex, pos);
        for (HSSFSheet sheet : this._sheets) {
            sheet.getSheet().updateFormulasAfterCellShift(shifter, -1);
        }
        this.workbook.updateNamesAfterCellShift(shifter);
        this.updateNamedRangesAfterSheetReorder(oldSheetIndex, pos);
        this.updateActiveSheetAfterSheetReorder(oldSheetIndex, pos);
    }

    private void updateNamedRangesAfterSheetReorder(int oldIndex, int newIndex) {
        for (HSSFName name : this.names) {
            int i = name.getSheetIndex();
            if (i == -1) continue;
            if (i == oldIndex) {
                name.setSheetIndex(newIndex);
                continue;
            }
            if (newIndex <= i && i < oldIndex) {
                name.setSheetIndex(i + 1);
                continue;
            }
            if (oldIndex >= i || i > newIndex) continue;
            name.setSheetIndex(i - 1);
        }
    }

    private void updateActiveSheetAfterSheetReorder(int oldIndex, int newIndex) {
        int active = this.getActiveSheetIndex();
        if (active == oldIndex) {
            this.setActiveSheet(newIndex);
        } else if (!(active < oldIndex && active < newIndex || active > oldIndex && active > newIndex)) {
            if (newIndex > oldIndex) {
                this.setActiveSheet(active - 1);
            } else {
                this.setActiveSheet(active + 1);
            }
        }
    }

    private void validateSheetIndex(int index) {
        int lastSheetIx = this._sheets.size() - 1;
        if (index < 0 || index > lastSheetIx) {
            String range = "(0.." + lastSheetIx + ")";
            if (lastSheetIx == -1) {
                range = "(no sheets)";
            }
            throw new IllegalArgumentException("Sheet index (" + index + ") is out of range " + range);
        }
    }

    @Override
    public void setSelectedTab(int index) {
        this.validateSheetIndex(index);
        int nSheets = this._sheets.size();
        for (int i = 0; i < nSheets; ++i) {
            this.getSheetAt(i).setSelected(i == index);
        }
        this.workbook.getWindowOne().setNumSelectedTabs((short)1);
    }

    public void setSelectedTabs(int[] indexes) {
        ArrayList<Integer> list = new ArrayList<Integer>(indexes.length);
        for (int index : indexes) {
            list.add(index);
        }
        this.setSelectedTabs(list);
    }

    public void setSelectedTabs(Collection<Integer> indexes) {
        for (int index : indexes) {
            this.validateSheetIndex(index);
        }
        HashSet<Integer> set = new HashSet<Integer>(indexes);
        int nSheets = this._sheets.size();
        for (int i = 0; i < nSheets; ++i) {
            boolean bSelect = set.contains(i);
            this.getSheetAt(i).setSelected(bSelect);
        }
        short nSelected = (short)set.size();
        this.workbook.getWindowOne().setNumSelectedTabs(nSelected);
    }

    public Collection<Integer> getSelectedTabs() {
        ArrayList<Integer> indexes = new ArrayList<Integer>();
        int nSheets = this._sheets.size();
        for (int i = 0; i < nSheets; ++i) {
            HSSFSheet sheet = this.getSheetAt(i);
            if (!sheet.isSelected()) continue;
            indexes.add(i);
        }
        return Collections.unmodifiableCollection(indexes);
    }

    @Override
    public void setActiveSheet(int index) {
        this.validateSheetIndex(index);
        int nSheets = this._sheets.size();
        for (int i = 0; i < nSheets; ++i) {
            this.getSheetAt(i).setActive(i == index);
        }
        this.workbook.getWindowOne().setActiveSheetIndex(index);
    }

    @Override
    public int getActiveSheetIndex() {
        return this.workbook.getWindowOne().getActiveSheetIndex();
    }

    @Override
    public void setFirstVisibleTab(int index) {
        this.workbook.getWindowOne().setFirstVisibleTab(index);
    }

    @Override
    public int getFirstVisibleTab() {
        return this.workbook.getWindowOne().getFirstVisibleTab();
    }

    @Override
    public void setSheetName(int sheetIx, String name) {
        if (name == null) {
            throw new IllegalArgumentException("sheetName must not be null");
        }
        if (this.workbook.doesContainsSheetName(name, sheetIx)) {
            throw new IllegalArgumentException("The workbook already contains a sheet named '" + name + "'");
        }
        this.validateSheetIndex(sheetIx);
        this.workbook.setSheetName(sheetIx, name);
    }

    @Override
    public String getSheetName(int sheetIndex) {
        this.validateSheetIndex(sheetIndex);
        return this.workbook.getSheetName(sheetIndex);
    }

    @Override
    public boolean isHidden() {
        return this.workbook.getWindowOne().getHidden();
    }

    @Override
    public void setHidden(boolean hiddenFlag) {
        this.workbook.getWindowOne().setHidden(hiddenFlag);
    }

    @Override
    public boolean isSheetHidden(int sheetIx) {
        this.validateSheetIndex(sheetIx);
        return this.workbook.isSheetHidden(sheetIx);
    }

    @Override
    public boolean isSheetVeryHidden(int sheetIx) {
        this.validateSheetIndex(sheetIx);
        return this.workbook.isSheetVeryHidden(sheetIx);
    }

    @Override
    public SheetVisibility getSheetVisibility(int sheetIx) {
        return this.workbook.getSheetVisibility(sheetIx);
    }

    @Override
    public void setSheetHidden(int sheetIx, boolean hidden) {
        this.setSheetVisibility(sheetIx, hidden ? SheetVisibility.HIDDEN : SheetVisibility.VISIBLE);
    }

    @Override
    public void setSheetVisibility(int sheetIx, SheetVisibility visibility) {
        this.validateSheetIndex(sheetIx);
        this.workbook.setSheetHidden(sheetIx, visibility);
    }

    @Override
    public int getSheetIndex(String name) {
        return this.workbook.getSheetIndex(name);
    }

    @Override
    public int getSheetIndex(Sheet sheet) {
        return this._sheets.indexOf(sheet);
    }

    @Override
    public HSSFSheet createSheet() {
        HSSFSheet sheet = new HSSFSheet(this);
        this._sheets.add(sheet);
        this.workbook.setSheetName(this._sheets.size() - 1, "Sheet" + (this._sheets.size() - 1));
        boolean isOnlySheet = this._sheets.size() == 1;
        sheet.setSelected(isOnlySheet);
        sheet.setActive(isOnlySheet);
        return sheet;
    }

    @Override
    public HSSFSheet cloneSheet(int sheetIndex) {
        this.validateSheetIndex(sheetIndex);
        HSSFSheet srcSheet = this._sheets.get(sheetIndex);
        String srcName = this.workbook.getSheetName(sheetIndex);
        HSSFSheet clonedSheet = srcSheet.cloneSheet(this);
        clonedSheet.setSelected(false);
        clonedSheet.setActive(false);
        String name = this.getUniqueSheetName(srcName);
        int newSheetIndex = this._sheets.size();
        this._sheets.add(clonedSheet);
        this.workbook.setSheetName(newSheetIndex, name);
        int filterDbNameIndex = this.findExistingBuiltinNameRecordIdx(sheetIndex, (byte)13);
        if (filterDbNameIndex != -1) {
            NameRecord newNameRecord = this.workbook.cloneFilter(filterDbNameIndex, newSheetIndex);
            HSSFName newName = new HSSFName(this, newNameRecord);
            this.names.add(newName);
        }
        return clonedSheet;
    }

    private String getUniqueSheetName(String srcName) {
        String index;
        String name;
        int uniqueIndex = 2;
        String baseName = srcName;
        int bracketPos = srcName.lastIndexOf(40);
        if (bracketPos > 0 && srcName.endsWith(")")) {
            String suffix = srcName.substring(bracketPos + 1, srcName.length() - ")".length());
            try {
                uniqueIndex = Integer.parseInt(suffix.trim());
                baseName = srcName.substring(0, bracketPos).trim();
            } catch (NumberFormatException numberFormatException) {
                // empty catch block
            }
        }
        do {
            int n = ++uniqueIndex;
            ++uniqueIndex;
            index = Integer.toString(n);
        } while (this.workbook.getSheetIndex(name = baseName.length() + index.length() + 2 < 31 ? baseName + " (" + index + ")" : baseName.substring(0, 31 - index.length() - 2) + "(" + index + ")") != -1);
        return name;
    }

    @Override
    public HSSFSheet createSheet(String sheetname) {
        if (sheetname == null) {
            throw new IllegalArgumentException("sheetName must not be null");
        }
        if (this.workbook.doesContainsSheetName(sheetname, this._sheets.size())) {
            throw new IllegalArgumentException("The workbook already contains a sheet named '" + sheetname + "'");
        }
        HSSFSheet sheet = new HSSFSheet(this);
        this.workbook.setSheetName(this._sheets.size(), sheetname);
        this._sheets.add(sheet);
        boolean isOnlySheet = this._sheets.size() == 1;
        sheet.setSelected(isOnlySheet);
        sheet.setActive(isOnlySheet);
        return sheet;
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
    public int getNumberOfSheets() {
        return this._sheets.size();
    }

    private HSSFSheet[] getSheets() {
        HSSFSheet[] result = new HSSFSheet[this._sheets.size()];
        this._sheets.toArray(result);
        return result;
    }

    @Override
    public HSSFSheet getSheetAt(int index) {
        this.validateSheetIndex(index);
        return this._sheets.get(index);
    }

    @Override
    public HSSFSheet getSheet(String name) {
        HSSFSheet retval = null;
        for (int k = 0; k < this._sheets.size(); ++k) {
            String sheetname = this.workbook.getSheetName(k);
            if (!sheetname.equalsIgnoreCase(name)) continue;
            retval = this._sheets.get(k);
        }
        return retval;
    }

    @Override
    public void removeSheetAt(int index) {
        int active;
        this.validateSheetIndex(index);
        boolean wasSelected = this.getSheetAt(index).isSelected();
        this._sheets.remove(index);
        this.workbook.removeSheet(index);
        int nSheets = this._sheets.size();
        if (nSheets < 1) {
            return;
        }
        int newSheetIndex = index;
        if (newSheetIndex >= nSheets) {
            newSheetIndex = nSheets - 1;
        }
        if (wasSelected) {
            boolean someOtherSheetIsStillSelected = false;
            for (int i = 0; i < nSheets; ++i) {
                if (!this.getSheetAt(i).isSelected()) continue;
                someOtherSheetIsStillSelected = true;
                break;
            }
            if (!someOtherSheetIsStillSelected) {
                this.setSelectedTab(newSheetIndex);
            }
        }
        if ((active = this.getActiveSheetIndex()) == index) {
            this.setActiveSheet(newSheetIndex);
        } else if (active > index) {
            this.setActiveSheet(active - 1);
        }
    }

    public void setBackupFlag(boolean backupValue) {
        BackupRecord backupRecord = this.workbook.getBackupRecord();
        backupRecord.setBackup(backupValue ? (short)1 : 0);
    }

    public boolean getBackupFlag() {
        BackupRecord backupRecord = this.workbook.getBackupRecord();
        return backupRecord.getBackup() != 0;
    }

    int findExistingBuiltinNameRecordIdx(int sheetIndex, byte builtinCode) {
        for (int defNameIndex = 0; defNameIndex < this.names.size(); ++defNameIndex) {
            NameRecord r = this.workbook.getNameRecord(defNameIndex);
            if (r == null) {
                throw new RuntimeException("Unable to find all defined names to iterate over");
            }
            if (!r.isBuiltInName() || r.getBuiltInName() != builtinCode || r.getSheetNumber() - 1 != sheetIndex) continue;
            return defNameIndex;
        }
        return -1;
    }

    HSSFName createBuiltInName(byte builtinCode, int sheetIndex) {
        NameRecord nameRecord = this.workbook.createBuiltInName(builtinCode, sheetIndex + 1);
        HSSFName newName = new HSSFName(this, nameRecord, null);
        this.names.add(newName);
        return newName;
    }

    HSSFName getBuiltInName(byte builtinCode, int sheetIndex) {
        int index = this.findExistingBuiltinNameRecordIdx(sheetIndex, builtinCode);
        return index < 0 ? null : this.names.get(index);
    }

    @Override
    public HSSFFont createFont() {
        this.workbook.createNewFont();
        int fontindex = this.getNumberOfFontsAsInt() - 1;
        if (fontindex > 3) {
            ++fontindex;
        }
        if (fontindex >= Short.MAX_VALUE) {
            throw new IllegalArgumentException("Maximum number of fonts was exceeded");
        }
        return this.getFontAt(fontindex);
    }

    @Override
    public HSSFFont findFont(boolean bold, short color, short fontHeight, String name, boolean italic, boolean strikeout, short typeOffset, byte underline) {
        int numberOfFonts = this.getNumberOfFontsAsInt();
        for (int i = 0; i <= numberOfFonts; ++i) {
            HSSFFont hssfFont;
            if (i == 4 || (hssfFont = this.getFontAt(i)).getBold() != bold || hssfFont.getColor() != color || hssfFont.getFontHeight() != fontHeight || !hssfFont.getFontName().equals(name) || hssfFont.getItalic() != italic || hssfFont.getStrikeout() != strikeout || hssfFont.getTypeOffset() != typeOffset || hssfFont.getUnderline() != underline) continue;
            return hssfFont;
        }
        return null;
    }

    @Override
    @Deprecated
    public short getNumberOfFonts() {
        return (short)this.getNumberOfFontsAsInt();
    }

    @Override
    public int getNumberOfFontsAsInt() {
        return this.workbook.getNumberOfFontRecords();
    }

    @Override
    @Deprecated
    public HSSFFont getFontAt(short idx) {
        return this.getFontAt((int)idx);
    }

    @Override
    public HSSFFont getFontAt(int idx) {
        Integer sIdx;
        if (this.fonts == null) {
            this.fonts = new HashMap<Integer, HSSFFont>();
        }
        if (this.fonts.containsKey(sIdx = Integer.valueOf(idx))) {
            return this.fonts.get(sIdx);
        }
        FontRecord font = this.workbook.getFontRecordAt(idx);
        HSSFFont retval = new HSSFFont(idx, font);
        this.fonts.put(sIdx, retval);
        return retval;
    }

    void resetFontCache() {
        this.fonts = new HashMap<Integer, HSSFFont>();
    }

    @Override
    public HSSFCellStyle createCellStyle() {
        if (this.workbook.getNumExFormats() == 4030) {
            throw new IllegalStateException("The maximum number of cell styles was exceeded. You can define up to 4000 styles in a .xls workbook");
        }
        ExtendedFormatRecord xfr = this.workbook.createCellXF();
        short index = (short)(this.getNumCellStyles() - 1);
        return new HSSFCellStyle(index, xfr, this);
    }

    @Override
    public int getNumCellStyles() {
        return this.workbook.getNumExFormats();
    }

    @Override
    public HSSFCellStyle getCellStyleAt(int idx) {
        ExtendedFormatRecord xfr = this.workbook.getExFormatAt(idx);
        return new HSSFCellStyle((short)idx, xfr, this);
    }

    @Override
    public void close() throws IOException {
        super.close();
    }

    @Override
    public void write() throws IOException {
        this.validateInPlaceWritePossible();
        DirectoryNode dir = this.getDirectory();
        DocumentNode workbookNode = (DocumentNode)dir.getEntry(HSSFWorkbook.getWorkbookDirEntryName(dir));
        POIFSDocument workbookDoc = new POIFSDocument(workbookNode);
        workbookDoc.replaceContents(new ByteArrayInputStream(this.getBytes()));
        this.writeProperties();
        dir.getFileSystem().writeFilesystem();
    }

    @Override
    public void write(File newFile) throws IOException {
        try (POIFSFileSystem fs = POIFSFileSystem.create(newFile);){
            this.write(fs);
            fs.writeFilesystem();
        }
    }

    @Override
    public void write(OutputStream stream) throws IOException {
        try (POIFSFileSystem fs = new POIFSFileSystem();){
            this.write(fs);
            fs.writeFilesystem(stream);
        }
    }

    private void write(POIFSFileSystem fs) throws IOException {
        ArrayList<String> excepts = new ArrayList<String>(1);
        fs.createDocument(new ByteArrayInputStream(this.getBytes()), "Workbook");
        this.writeProperties(fs, excepts);
        if (this.preserveNodes) {
            excepts.addAll(Arrays.asList(InternalWorkbook.WORKBOOK_DIR_ENTRY_NAMES));
            excepts.addAll(Arrays.asList("\u0005DocumentSummaryInformation", "\u0005SummaryInformation", this.getEncryptedPropertyStreamName()));
            EntryUtils.copyNodes(new FilteringDirectoryNode(this.getDirectory(), excepts), new FilteringDirectoryNode(fs.getRoot(), excepts));
            fs.getRoot().setStorageClsid(this.getDirectory().getStorageClsid());
        }
    }

    public byte[] getBytes() {
        if (log.check(1)) {
            log.log(1, "HSSFWorkbook.getBytes()");
        }
        HSSFSheet[] sheets = this.getSheets();
        int nSheets = sheets.length;
        this.updateEncryptionInfo();
        this.workbook.preSerialize();
        for (HSSFSheet sheet : sheets) {
            sheet.getSheet().preSerialize();
            sheet.preSerialize();
        }
        int totalsize = this.workbook.getSize();
        SheetRecordCollector[] srCollectors = new SheetRecordCollector[nSheets];
        for (int k = 0; k < nSheets; ++k) {
            this.workbook.setSheetBof(k, totalsize);
            SheetRecordCollector src = new SheetRecordCollector();
            sheets[k].getSheet().visitContainedRecords(src, totalsize);
            totalsize += src.getTotalSize();
            srCollectors[k] = src;
        }
        byte[] retval = new byte[totalsize];
        int pos = this.workbook.serialize(0, retval);
        for (int k = 0; k < nSheets; ++k) {
            SheetRecordCollector src = srCollectors[k];
            int serializedSize = src.serialize(pos, retval);
            if (serializedSize != src.getTotalSize()) {
                throw new IllegalStateException("Actual serialized sheet size (" + serializedSize + ") differs from pre-calculated size (" + src.getTotalSize() + ") for sheet (" + k + ")");
            }
            pos += serializedSize;
        }
        this.encryptBytes(retval);
        return retval;
    }

    void encryptBytes(byte[] buf) {
        EncryptionInfo ei = this.getEncryptionInfo();
        if (ei == null) {
            return;
        }
        Encryptor enc = ei.getEncryptor();
        int initialOffset = 0;
        LittleEndianByteArrayInputStream plain = new LittleEndianByteArrayInputStream(buf, 0);
        LittleEndianByteArrayOutputStream leos = new LittleEndianByteArrayOutputStream(buf, 0);
        enc.setChunkSize(1024);
        byte[] tmp = new byte[1024];
        try {
            int len;
            ChunkedCipherOutputStream os = enc.getDataStream(leos, initialOffset);
            for (int totalBytes = 0; totalBytes < buf.length; totalBytes += 4 + len) {
                int nextLen;
                IOUtils.readFully(plain, tmp, 0, 4);
                int sid = LittleEndian.getUShort(tmp, 0);
                len = LittleEndian.getUShort(tmp, 2);
                boolean isPlain = Biff8DecryptingStream.isNeverEncryptedRecord(sid);
                os.setNextRecordSize(len, isPlain);
                os.writePlain(tmp, 0, 4);
                if (sid == 133) {
                    byte[] bsrBuf = IOUtils.safelyAllocate(len, 100000);
                    plain.readFully(bsrBuf);
                    os.writePlain(bsrBuf, 0, 4);
                    os.write(bsrBuf, 4, len - 4);
                    continue;
                }
                for (int todo = len; todo > 0; todo -= nextLen) {
                    nextLen = Math.min(todo, tmp.length);
                    plain.readFully(tmp, 0, nextLen);
                    if (isPlain) {
                        os.writePlain(tmp, 0, nextLen);
                        continue;
                    }
                    os.write(tmp, 0, nextLen);
                }
            }
            os.close();
        } catch (Exception e) {
            throw new EncryptedDocumentException(e);
        }
    }

    InternalWorkbook getWorkbook() {
        return this.workbook;
    }

    @Override
    public int getNumberOfNames() {
        return this.names.size();
    }

    @Override
    public HSSFName getName(String name) {
        int nameIndex = this.getNameIndex(name);
        if (nameIndex < 0) {
            return null;
        }
        return this.names.get(nameIndex);
    }

    public List<HSSFName> getNames(String name) {
        ArrayList<HSSFName> nameList = new ArrayList<HSSFName>();
        for (HSSFName nr : this.names) {
            if (!nr.getNameName().equals(name)) continue;
            nameList.add(nr);
        }
        return Collections.unmodifiableList(nameList);
    }

    @Override
    public HSSFName getNameAt(int nameIndex) {
        int nNames = this.names.size();
        if (nNames < 1) {
            throw new IllegalStateException("There are no defined names in this workbook");
        }
        if (nameIndex < 0 || nameIndex > nNames) {
            throw new IllegalArgumentException("Specified name index " + nameIndex + " is outside the allowable range (0.." + (nNames - 1) + ").");
        }
        return this.names.get(nameIndex);
    }

    public List<HSSFName> getAllNames() {
        return Collections.unmodifiableList(this.names);
    }

    public NameRecord getNameRecord(int nameIndex) {
        return this.getWorkbook().getNameRecord(nameIndex);
    }

    public String getNameName(int index) {
        return this.getNameAt(index).getNameName();
    }

    @Override
    public void setPrintArea(int sheetIndex, String reference) {
        NameRecord name = this.workbook.getSpecificBuiltinRecord((byte)6, sheetIndex + 1);
        if (name == null) {
            name = this.workbook.createBuiltInName((byte)6, sheetIndex + 1);
        }
        String[] parts = COMMA_PATTERN.split(reference);
        StringBuilder sb = new StringBuilder(32);
        for (int i = 0; i < parts.length; ++i) {
            if (i > 0) {
                sb.append(",");
            }
            SheetNameFormatter.appendFormat(sb, this.getSheetName(sheetIndex));
            sb.append("!");
            sb.append(parts[i]);
        }
        name.setNameDefinition(HSSFFormulaParser.parse(sb.toString(), this, FormulaType.NAMEDRANGE, sheetIndex));
    }

    @Override
    public void setPrintArea(int sheetIndex, int startColumn, int endColumn, int startRow, int endRow) {
        CellReference cell = new CellReference(startRow, startColumn, true, true);
        String reference = cell.formatAsString();
        cell = new CellReference(endRow, endColumn, true, true);
        reference = reference + ":" + cell.formatAsString();
        this.setPrintArea(sheetIndex, reference);
    }

    @Override
    public String getPrintArea(int sheetIndex) {
        NameRecord name = this.workbook.getSpecificBuiltinRecord((byte)6, sheetIndex + 1);
        if (name == null) {
            return null;
        }
        return HSSFFormulaParser.toFormulaString(this, name.getNameDefinition());
    }

    @Override
    public void removePrintArea(int sheetIndex) {
        this.getWorkbook().removeBuiltinRecord((byte)6, sheetIndex + 1);
    }

    @Override
    public HSSFName createName() {
        NameRecord nameRecord = this.workbook.createName();
        HSSFName newName = new HSSFName(this, nameRecord);
        this.names.add(newName);
        return newName;
    }

    @Override
    public int getNameIndex(String name) {
        for (int k = 0; k < this.names.size(); ++k) {
            String nameName = this.getNameName(k);
            if (!nameName.equalsIgnoreCase(name)) continue;
            return k;
        }
        return -1;
    }

    int getNameIndex(HSSFName name) {
        for (int k = 0; k < this.names.size(); ++k) {
            if (name != this.names.get(k)) continue;
            return k;
        }
        return -1;
    }

    @Override
    public void removeName(int index) {
        this.names.remove(index);
        this.workbook.removeName(index);
    }

    @Override
    public HSSFDataFormat createDataFormat() {
        if (this.formatter == null) {
            this.formatter = new HSSFDataFormat(this.workbook);
        }
        return this.formatter;
    }

    @Override
    public void removeName(String name) {
        int index = this.getNameIndex(name);
        this.removeName(index);
    }

    @Override
    public void removeName(Name name) {
        int index = this.getNameIndex((HSSFName)name);
        this.removeName(index);
    }

    public HSSFPalette getCustomPalette() {
        return new HSSFPalette(this.workbook.getCustomPalette());
    }

    public void insertChartRecord() {
        int loc = this.workbook.findFirstRecordLocBySid((short)252);
        byte[] data = new byte[]{15, 0, 0, -16, 82, 0, 0, 0, 0, 0, 6, -16, 24, 0, 0, 0, 1, 8, 0, 0, 2, 0, 0, 0, 2, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 3, 0, 0, 0, 51, 0, 11, -16, 18, 0, 0, 0, -65, 0, 8, 0, 8, 0, -127, 1, 9, 0, 0, 8, -64, 1, 64, 0, 0, 8, 64, 0, 30, -15, 16, 0, 0, 0, 13, 0, 0, 8, 12, 0, 0, 8, 23, 0, 0, 8, -9, 0, 0, 16};
        UnknownRecord r = new UnknownRecord(235, data);
        this.workbook.getRecords().add(loc, r);
    }

    public void dumpDrawingGroupRecords(boolean fat) {
        DrawingGroupRecord r = (DrawingGroupRecord)this.workbook.findFirstRecordBySid((short)235);
        if (r == null) {
            return;
        }
        r.decode();
        List<EscherRecord> escherRecords = r.getEscherRecords();
        PrintWriter w = new PrintWriter(new OutputStreamWriter((OutputStream)System.out, Charset.defaultCharset()));
        for (EscherRecord escherRecord : escherRecords) {
            if (fat) {
                System.out.println(escherRecord);
                continue;
            }
            escherRecord.display(w, 0);
        }
        w.flush();
    }

    void initDrawings() {
        DrawingManager2 mgr = this.workbook.findDrawingGroup();
        if (mgr != null) {
            for (HSSFSheet sh : this._sheets) {
                sh.getDrawingPatriarch();
            }
        } else {
            this.workbook.createDrawingGroup();
        }
    }

    @Override
    public int addPicture(byte[] pictureData, int format) {
        short escherTag;
        int blipSize;
        EscherBlipRecord blipRecord;
        this.initDrawings();
        byte[] uid = DigestUtils.md5(pictureData);
        switch (format) {
            case 3: {
                if (LittleEndian.getInt(pictureData) == -1698247209) {
                    byte[] picDataNoHeader = new byte[pictureData.length - 22];
                    System.arraycopy(pictureData, 22, picDataNoHeader, 0, pictureData.length - 22);
                    pictureData = picDataNoHeader;
                }
            }
            case 2: {
                EscherMetafileBlip blipRecordMeta = new EscherMetafileBlip();
                blipRecord = blipRecordMeta;
                blipRecordMeta.setUID(uid);
                blipRecordMeta.setPictureData(pictureData);
                blipRecordMeta.setFilter((byte)-2);
                blipSize = blipRecordMeta.getCompressedSize() + 58;
                escherTag = 0;
                break;
            }
            default: {
                EscherBitmapBlip blipRecordBitmap = new EscherBitmapBlip();
                blipRecord = blipRecordBitmap;
                blipRecordBitmap.setUID(uid);
                blipRecordBitmap.setMarker((byte)-1);
                blipRecordBitmap.setPictureData(pictureData);
                blipSize = pictureData.length + 25;
                escherTag = 255;
            }
        }
        blipRecord.setRecordId((short)(EscherBlipRecord.RECORD_ID_START + format));
        switch (format) {
            case 2: {
                blipRecord.setOptions((short)15680);
                break;
            }
            case 3: {
                blipRecord.setOptions((short)8544);
                break;
            }
            case 4: {
                blipRecord.setOptions((short)21536);
                break;
            }
            case 6: {
                blipRecord.setOptions((short)28160);
                break;
            }
            case 5: {
                blipRecord.setOptions((short)18080);
                break;
            }
            case 7: {
                blipRecord.setOptions((short)31360);
                break;
            }
            default: {
                throw new IllegalStateException("Unexpected picture format: " + format);
            }
        }
        EscherBSERecord r = new EscherBSERecord();
        r.setRecordId(EscherBSERecord.RECORD_ID);
        r.setOptions((short)(2 | format << 4));
        r.setBlipTypeMacOS((byte)format);
        r.setBlipTypeWin32((byte)format);
        r.setUid(uid);
        r.setTag(escherTag);
        r.setSize(blipSize);
        r.setRef(0);
        r.setOffset(0);
        r.setBlipRecord(blipRecord);
        return this.workbook.addBSERecord(r);
    }

    public List<HSSFPictureData> getAllPictures() {
        ArrayList<HSSFPictureData> pictures = new ArrayList<HSSFPictureData>();
        for (Record r : this.workbook.getRecords()) {
            if (!(r instanceof AbstractEscherHolderRecord)) continue;
            ((AbstractEscherHolderRecord)r).decode();
            List<EscherRecord> escherRecords = ((AbstractEscherHolderRecord)r).getEscherRecords();
            this.searchForPictures(escherRecords, pictures);
        }
        return Collections.unmodifiableList(pictures);
    }

    private void searchForPictures(List<EscherRecord> escherRecords, List<HSSFPictureData> pictures) {
        for (EscherRecord escherRecord : escherRecords) {
            EscherBlipRecord blip;
            if (escherRecord instanceof EscherBSERecord && (blip = ((EscherBSERecord)escherRecord).getBlipRecord()) != null) {
                HSSFPictureData picture = new HSSFPictureData(blip);
                pictures.add(picture);
            }
            this.searchForPictures(escherRecord.getChildRecords(), pictures);
        }
    }

    static Map<String, ClassID> getOleMap() {
        HashMap<String, ClassID> olemap = new HashMap<String, ClassID>();
        olemap.put("PowerPoint Document", ClassIDPredefined.POWERPOINT_V8.getClassID());
        for (String str : InternalWorkbook.WORKBOOK_DIR_ENTRY_NAMES) {
            olemap.put(str, ClassIDPredefined.EXCEL_V7_WORKBOOK.getClassID());
        }
        return olemap;
    }

    public int addOlePackage(POIFSFileSystem poiData, String label, String fileName, String command) throws IOException {
        DirectoryNode root = poiData.getRoot();
        Map<String, ClassID> olemap = HSSFWorkbook.getOleMap();
        for (Map.Entry<String, ClassID> entry : olemap.entrySet()) {
            if (!root.hasEntry(entry.getKey())) continue;
            root.setStorageClsid(entry.getValue());
            break;
        }
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        poiData.writeFilesystem(bos);
        return this.addOlePackage(bos.toByteArray(), label, fileName, command);
    }

    @Override
    public int addOlePackage(byte[] oleData, String label, String fileName, String command) throws IOException {
        if (this.initDirectory()) {
            this.preserveNodes = true;
        }
        int storageId = 0;
        DirectoryEntry oleDir = null;
        do {
            String storageStr = "MBD" + HexDump.toHex(++storageId);
            if (this.getDirectory().hasEntry(storageStr)) continue;
            oleDir = this.getDirectory().createDirectory(storageStr);
            oleDir.setStorageClsid(ClassIDPredefined.OLE_V1_PACKAGE.getClassID());
        } while (oleDir == null);
        Ole10Native.createOleMarkerEntry(oleDir);
        Ole10Native oleNative = new Ole10Native(label, fileName, command, oleData);
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        oleNative.writeOut(bos);
        oleDir.createDocument("\u0001Ole10Native", new ByteArrayInputStream(bos.toByteArray()));
        return storageId;
    }

    @Override
    public int linkExternalWorkbook(String name, Workbook workbook) {
        return this.workbook.linkExternalWorkbook(name, workbook);
    }

    public boolean isWriteProtected() {
        return this.workbook.isWriteProtected();
    }

    public void writeProtectWorkbook(String password, String username) {
        this.workbook.writeProtectWorkbook(password, username);
    }

    public void unwriteProtectWorkbook() {
        this.workbook.unwriteProtectWorkbook();
    }

    public List<HSSFObjectData> getAllEmbeddedObjects() {
        ArrayList<HSSFObjectData> objects = new ArrayList<HSSFObjectData>();
        for (HSSFSheet sheet : this._sheets) {
            this.getAllEmbeddedObjects(sheet, objects);
        }
        return Collections.unmodifiableList(objects);
    }

    private void getAllEmbeddedObjects(HSSFSheet sheet, List<HSSFObjectData> objects) {
        HSSFPatriarch patriarch = sheet.getDrawingPatriarch();
        if (null == patriarch) {
            return;
        }
        this.getAllEmbeddedObjects(patriarch, objects);
    }

    private void getAllEmbeddedObjects(HSSFShapeContainer parent, List<HSSFObjectData> objects) {
        for (HSSFShape shape : parent.getChildren()) {
            if (shape instanceof HSSFObjectData) {
                objects.add((HSSFObjectData)shape);
                continue;
            }
            if (!(shape instanceof HSSFShapeContainer)) continue;
            this.getAllEmbeddedObjects((HSSFShapeContainer)((Object)shape), objects);
        }
    }

    @Override
    public HSSFCreationHelper getCreationHelper() {
        return new HSSFCreationHelper(this);
    }

    UDFFinder getUDFFinder() {
        return this._udfFinder;
    }

    @Override
    public void addToolPack(UDFFinder toopack) {
        AggregatingUDFFinder udfs = (AggregatingUDFFinder)this._udfFinder;
        udfs.add(toopack);
    }

    @Override
    public void setForceFormulaRecalculation(boolean value) {
        InternalWorkbook iwb = this.getWorkbook();
        RecalcIdRecord recalc = iwb.getRecalcId();
        recalc.setEngineId(0);
    }

    @Override
    public boolean getForceFormulaRecalculation() {
        InternalWorkbook iwb = this.getWorkbook();
        RecalcIdRecord recalc = (RecalcIdRecord)iwb.findFirstRecordBySid((short)449);
        return recalc != null && recalc.getEngineId() != 0;
    }

    public boolean changeExternalReference(String oldUrl, String newUrl) {
        return this.workbook.changeExternalReference(oldUrl, newUrl);
    }

    @Internal
    public InternalWorkbook getInternalWorkbook() {
        return this.workbook;
    }

    @Override
    public SpreadsheetVersion getSpreadsheetVersion() {
        return SpreadsheetVersion.EXCEL97;
    }

    @Override
    public EncryptionInfo getEncryptionInfo() {
        FilePassRecord fpr = (FilePassRecord)this.workbook.findFirstRecordBySid((short)47);
        return fpr != null ? fpr.getEncryptionInfo() : null;
    }

    private void updateEncryptionInfo() {
        this.readProperties();
        FilePassRecord fpr = (FilePassRecord)this.workbook.findFirstRecordBySid((short)47);
        String password = Biff8EncryptionKey.getCurrentUserPassword();
        WorkbookRecordList wrl = this.workbook.getWorkbookRecordList();
        if (password == null) {
            if (fpr != null) {
                wrl.remove(fpr);
            }
        } else {
            if (fpr == null) {
                fpr = new FilePassRecord(EncryptionMode.cryptoAPI);
                wrl.add(1, fpr);
            }
            EncryptionInfo ei = fpr.getEncryptionInfo();
            EncryptionVerifier ver = ei.getVerifier();
            byte[] encVer = ver.getEncryptedVerifier();
            Decryptor dec = ei.getDecryptor();
            Encryptor enc = ei.getEncryptor();
            try {
                if (encVer == null || !dec.verifyPassword(password)) {
                    enc.confirmPassword(password);
                } else {
                    byte[] verifier = dec.getVerifier();
                    byte[] salt = ver.getSalt();
                    enc.confirmPassword(password, null, null, verifier, salt, null);
                }
            } catch (GeneralSecurityException e) {
                throw new EncryptedDocumentException("can't validate/update encryption setting", e);
            }
        }
    }

    private static final class SheetRecordCollector
    implements RecordAggregate.RecordVisitor {
        private List<Record> _list = new ArrayList<Record>(128);
        private int _totalSize = 0;

        public int getTotalSize() {
            return this._totalSize;
        }

        @Override
        public void visitRecord(Record r) {
            this._list.add(r);
            this._totalSize += r.getRecordSize();
        }

        public int serialize(int offset, byte[] data) {
            int result = 0;
            for (Record rec : this._list) {
                result += rec.serialize(offset + result, data);
            }
            return result;
        }
    }

    private final class SheetIterator<T extends Sheet>
    implements Iterator<T> {
        private final Iterator<T> it;
        private T cursor;

        public SheetIterator() {
            this.it = HSSFWorkbook.this._sheets.iterator();
        }

        @Override
        public boolean hasNext() {
            return this.it.hasNext();
        }

        @Override
        public T next() throws NoSuchElementException {
            this.cursor = (Sheet)this.it.next();
            return this.cursor;
        }

        @Override
        public void remove() throws IllegalStateException {
            throw new UnsupportedOperationException("remove method not supported on HSSFWorkbook.iterator(). Use Sheet.removeSheetAt(int) instead.");
        }
    }
}

