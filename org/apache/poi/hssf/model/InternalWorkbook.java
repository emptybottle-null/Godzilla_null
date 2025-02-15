/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.hssf.model;

import java.security.AccessControlException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.apache.poi.ddf.EscherBSERecord;
import org.apache.poi.ddf.EscherBoolProperty;
import org.apache.poi.ddf.EscherContainerRecord;
import org.apache.poi.ddf.EscherDgRecord;
import org.apache.poi.ddf.EscherDggRecord;
import org.apache.poi.ddf.EscherOptRecord;
import org.apache.poi.ddf.EscherPropertyTypes;
import org.apache.poi.ddf.EscherRGBProperty;
import org.apache.poi.ddf.EscherRecord;
import org.apache.poi.ddf.EscherSimpleProperty;
import org.apache.poi.ddf.EscherSpRecord;
import org.apache.poi.ddf.EscherSplitMenuColorsRecord;
import org.apache.poi.hssf.model.DrawingManager2;
import org.apache.poi.hssf.model.InternalSheet;
import org.apache.poi.hssf.model.LinkTable;
import org.apache.poi.hssf.model.WorkbookRecordList;
import org.apache.poi.hssf.record.BOFRecord;
import org.apache.poi.hssf.record.BackupRecord;
import org.apache.poi.hssf.record.BookBoolRecord;
import org.apache.poi.hssf.record.BoundSheetRecord;
import org.apache.poi.hssf.record.CodepageRecord;
import org.apache.poi.hssf.record.CountryRecord;
import org.apache.poi.hssf.record.DSFRecord;
import org.apache.poi.hssf.record.DateWindow1904Record;
import org.apache.poi.hssf.record.DrawingGroupRecord;
import org.apache.poi.hssf.record.EOFRecord;
import org.apache.poi.hssf.record.EscherAggregate;
import org.apache.poi.hssf.record.ExtSSTRecord;
import org.apache.poi.hssf.record.ExtendedFormatRecord;
import org.apache.poi.hssf.record.FileSharingRecord;
import org.apache.poi.hssf.record.FnGroupCountRecord;
import org.apache.poi.hssf.record.FontRecord;
import org.apache.poi.hssf.record.FormatRecord;
import org.apache.poi.hssf.record.HideObjRecord;
import org.apache.poi.hssf.record.HyperlinkRecord;
import org.apache.poi.hssf.record.InterfaceEndRecord;
import org.apache.poi.hssf.record.InterfaceHdrRecord;
import org.apache.poi.hssf.record.MMSRecord;
import org.apache.poi.hssf.record.NameCommentRecord;
import org.apache.poi.hssf.record.NameRecord;
import org.apache.poi.hssf.record.PaletteRecord;
import org.apache.poi.hssf.record.PasswordRecord;
import org.apache.poi.hssf.record.PasswordRev4Record;
import org.apache.poi.hssf.record.PrecisionRecord;
import org.apache.poi.hssf.record.ProtectRecord;
import org.apache.poi.hssf.record.ProtectionRev4Record;
import org.apache.poi.hssf.record.RecalcIdRecord;
import org.apache.poi.hssf.record.Record;
import org.apache.poi.hssf.record.RefreshAllRecord;
import org.apache.poi.hssf.record.SSTRecord;
import org.apache.poi.hssf.record.StyleRecord;
import org.apache.poi.hssf.record.TabIdRecord;
import org.apache.poi.hssf.record.UseSelFSRecord;
import org.apache.poi.hssf.record.WindowOneRecord;
import org.apache.poi.hssf.record.WindowProtectRecord;
import org.apache.poi.hssf.record.WriteAccessRecord;
import org.apache.poi.hssf.record.WriteProtectRecord;
import org.apache.poi.hssf.record.common.UnicodeString;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.poifs.crypt.CryptoFunctions;
import org.apache.poi.ss.formula.EvaluationWorkbook;
import org.apache.poi.ss.formula.FormulaShifter;
import org.apache.poi.ss.formula.ptg.Area3DPtg;
import org.apache.poi.ss.formula.ptg.NameXPtg;
import org.apache.poi.ss.formula.ptg.OperandPtg;
import org.apache.poi.ss.formula.ptg.Ptg;
import org.apache.poi.ss.formula.ptg.Ref3DPtg;
import org.apache.poi.ss.formula.udf.UDFFinder;
import org.apache.poi.ss.usermodel.BuiltinFormats;
import org.apache.poi.ss.usermodel.SheetVisibility;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.util.Internal;
import org.apache.poi.util.LocaleUtil;
import org.apache.poi.util.POILogFactory;
import org.apache.poi.util.POILogger;
import org.apache.poi.util.RecordFormatException;

@Internal
public final class InternalWorkbook {
    private static final int MAX_SENSITIVE_SHEET_NAME_LEN = 31;
    public static final String[] WORKBOOK_DIR_ENTRY_NAMES = new String[]{"Workbook", "WORKBOOK", "BOOK", "WorkBook"};
    public static final String OLD_WORKBOOK_DIR_ENTRY_NAME = "Book";
    private static final POILogger LOG = POILogFactory.getLogger(InternalWorkbook.class);
    private static final short CODEPAGE = 1200;
    private final WorkbookRecordList records = new WorkbookRecordList();
    protected SSTRecord sst;
    private LinkTable linkTable;
    private final List<BoundSheetRecord> boundsheets = new ArrayList<BoundSheetRecord>();
    private final List<FormatRecord> formats = new ArrayList<FormatRecord>();
    private final List<HyperlinkRecord> hyperlinks = new ArrayList<HyperlinkRecord>();
    private int numxfs = 0;
    private int numfonts = 0;
    private int maxformatid = -1;
    private boolean uses1904datewindowing = false;
    private DrawingManager2 drawingManager;
    private List<EscherBSERecord> escherBSERecords = new ArrayList<EscherBSERecord>();
    private WindowOneRecord windowOne;
    private FileSharingRecord fileShare;
    private WriteAccessRecord writeAccess;
    private WriteProtectRecord writeProtect;
    private final Map<String, NameCommentRecord> commentRecords = new LinkedHashMap<String, NameCommentRecord>();

    private InternalWorkbook() {
    }

    public static InternalWorkbook createWorkbook(List<Record> recs) {
        LOG.log(1, "Workbook (readfile) created with reclen=", recs.size());
        InternalWorkbook retval = new InternalWorkbook();
        ArrayList<Record> records = new ArrayList<Record>(recs.size() / 3);
        retval.records.setRecords(records);
        boolean eofPassed = false;
        block21: for (int k = 0; k < recs.size(); ++k) {
            String logObj;
            Record rec = recs.get(k);
            switch (rec.getSid()) {
                case 10: {
                    logObj = "workbook eof";
                    break;
                }
                case 133: {
                    logObj = "boundsheet";
                    retval.boundsheets.add((BoundSheetRecord)rec);
                    retval.records.setBspos(k);
                    break;
                }
                case 252: {
                    logObj = "sst";
                    retval.sst = (SSTRecord)rec;
                    break;
                }
                case 49: {
                    logObj = "font";
                    retval.records.setFontpos(k);
                    ++retval.numfonts;
                    break;
                }
                case 224: {
                    logObj = "XF";
                    retval.records.setXfpos(k);
                    ++retval.numxfs;
                    break;
                }
                case 317: {
                    logObj = "tabid";
                    retval.records.setTabpos(k);
                    break;
                }
                case 18: {
                    logObj = "protect";
                    retval.records.setProtpos(k);
                    break;
                }
                case 64: {
                    logObj = "backup";
                    retval.records.setBackuppos(k);
                    break;
                }
                case 23: {
                    throw new RecordFormatException("Extern sheet is part of LinkTable");
                }
                case 24: 
                case 430: {
                    LOG.log(1, "found SupBook record at " + k);
                    retval.linkTable = new LinkTable(recs, k, retval.records, retval.commentRecords);
                    k += retval.linkTable.getRecordCount() - 1;
                    continue block21;
                }
                case 1054: {
                    logObj = "format";
                    FormatRecord fr = (FormatRecord)rec;
                    retval.formats.add(fr);
                    retval.maxformatid = retval.maxformatid >= fr.getIndexCode() ? retval.maxformatid : fr.getIndexCode();
                    break;
                }
                case 34: {
                    logObj = "datewindow1904";
                    retval.uses1904datewindowing = ((DateWindow1904Record)rec).getWindowing() == 1;
                    break;
                }
                case 146: {
                    logObj = "palette";
                    retval.records.setPalettepos(k);
                    break;
                }
                case 61: {
                    logObj = "WindowOneRecord";
                    retval.windowOne = (WindowOneRecord)rec;
                    break;
                }
                case 92: {
                    logObj = "WriteAccess";
                    retval.writeAccess = (WriteAccessRecord)rec;
                    break;
                }
                case 134: {
                    logObj = "WriteProtect";
                    retval.writeProtect = (WriteProtectRecord)rec;
                    break;
                }
                case 91: {
                    logObj = "FileSharing";
                    retval.fileShare = (FileSharingRecord)rec;
                    break;
                }
                case 2196: {
                    logObj = "NameComment";
                    NameCommentRecord ncr = (NameCommentRecord)rec;
                    retval.commentRecords.put(ncr.getNameText(), ncr);
                    break;
                }
                case 440: {
                    logObj = "Hyperlink";
                    retval.hyperlinks.add((HyperlinkRecord)rec);
                    break;
                }
                default: {
                    logObj = "(sid=" + rec.getSid() + ")";
                }
            }
            if (!eofPassed) {
                records.add(rec);
            }
            LOG.log(1, "found " + logObj + " record at " + k);
            if (rec.getSid() != 10) continue;
            eofPassed = true;
        }
        if (retval.windowOne == null) {
            retval.windowOne = InternalWorkbook.createWindowOne();
        }
        LOG.log(1, "exit create workbook from existing file function");
        return retval;
    }

    public static InternalWorkbook createWorkbook() {
        int k;
        int k2;
        LOG.log(1, "creating new workbook from scratch");
        InternalWorkbook retval = new InternalWorkbook();
        ArrayList<Record> records = new ArrayList<Record>(30);
        retval.records.setRecords(records);
        List<FormatRecord> formats = retval.formats;
        records.add(InternalWorkbook.createBOF());
        records.add(new InterfaceHdrRecord(1200));
        records.add(InternalWorkbook.createMMS());
        records.add(InterfaceEndRecord.instance);
        records.add(InternalWorkbook.createWriteAccess());
        records.add(InternalWorkbook.createCodepage());
        records.add(InternalWorkbook.createDSF());
        records.add(InternalWorkbook.createTabId());
        retval.records.setTabpos(records.size() - 1);
        records.add(InternalWorkbook.createFnGroupCount());
        records.add(InternalWorkbook.createWindowProtect());
        records.add(InternalWorkbook.createProtect());
        retval.records.setProtpos(records.size() - 1);
        records.add(InternalWorkbook.createPassword());
        records.add(InternalWorkbook.createProtectionRev4());
        records.add(InternalWorkbook.createPasswordRev4());
        retval.windowOne = InternalWorkbook.createWindowOne();
        records.add(retval.windowOne);
        records.add(InternalWorkbook.createBackup());
        retval.records.setBackuppos(records.size() - 1);
        records.add(InternalWorkbook.createHideObj());
        records.add(InternalWorkbook.createDateWindow1904());
        records.add(InternalWorkbook.createPrecision());
        records.add(InternalWorkbook.createRefreshAll());
        records.add(InternalWorkbook.createBookBool());
        records.add(InternalWorkbook.createFont());
        records.add(InternalWorkbook.createFont());
        records.add(InternalWorkbook.createFont());
        records.add(InternalWorkbook.createFont());
        retval.records.setFontpos(records.size() - 1);
        retval.numfonts = 4;
        for (int i = 0; i <= 7; ++i) {
            FormatRecord rec = InternalWorkbook.createFormat(i);
            retval.maxformatid = retval.maxformatid >= rec.getIndexCode() ? retval.maxformatid : rec.getIndexCode();
            formats.add(rec);
            records.add(rec);
        }
        for (k2 = 0; k2 < 21; ++k2) {
            records.add(InternalWorkbook.createExtendedFormat(k2));
            ++retval.numxfs;
        }
        retval.records.setXfpos(records.size() - 1);
        for (k2 = 0; k2 < 6; ++k2) {
            records.add(InternalWorkbook.createStyle(k2));
        }
        records.add(InternalWorkbook.createUseSelFS());
        int nBoundSheets = 1;
        for (k = 0; k < nBoundSheets; ++k) {
            BoundSheetRecord bsr = InternalWorkbook.createBoundSheet(k);
            records.add(bsr);
            retval.boundsheets.add(bsr);
            retval.records.setBspos(records.size() - 1);
        }
        records.add(InternalWorkbook.createCountry());
        for (k = 0; k < nBoundSheets; ++k) {
            retval.getOrCreateLinkTable().checkExternSheet(k);
        }
        retval.sst = new SSTRecord();
        records.add(retval.sst);
        records.add(InternalWorkbook.createExtendedSST());
        records.add(EOFRecord.instance);
        LOG.log(1, "exit create new workbook from scratch");
        return retval;
    }

    public NameRecord getSpecificBuiltinRecord(byte name, int sheetNumber) {
        return this.getOrCreateLinkTable().getSpecificBuiltinRecord(name, sheetNumber);
    }

    public void removeBuiltinRecord(byte name, int sheetIndex) {
        this.linkTable.removeBuiltinRecord(name, sheetIndex);
    }

    public int getNumRecords() {
        return this.records.size();
    }

    public FontRecord getFontRecordAt(int idx) {
        int index = idx;
        if (index > 4) {
            --index;
        }
        if (index > this.numfonts - 1) {
            throw new ArrayIndexOutOfBoundsException("There are only " + this.numfonts + " font records, but you asked for index " + idx);
        }
        return (FontRecord)this.records.get(this.records.getFontpos() - (this.numfonts - 1) + index);
    }

    public int getFontIndex(FontRecord font) {
        for (int i = 0; i <= this.numfonts; ++i) {
            FontRecord thisFont = (FontRecord)this.records.get(this.records.getFontpos() - (this.numfonts - 1) + i);
            if (thisFont != font) continue;
            return i > 3 ? i + 1 : i;
        }
        throw new IllegalArgumentException("Could not find that font!");
    }

    public FontRecord createNewFont() {
        FontRecord rec = InternalWorkbook.createFont();
        this.records.add(this.records.getFontpos() + 1, rec);
        this.records.setFontpos(this.records.getFontpos() + 1);
        ++this.numfonts;
        return rec;
    }

    public void removeFontRecord(FontRecord rec) {
        this.records.remove(rec);
        --this.numfonts;
    }

    public int getNumberOfFontRecords() {
        return this.numfonts;
    }

    public void setSheetBof(int sheetIndex, int pos) {
        LOG.log(1, "setting bof for sheetnum =", sheetIndex, " at pos=", pos);
        this.checkSheets(sheetIndex);
        this.getBoundSheetRec(sheetIndex).setPositionOfBof(pos);
    }

    private BoundSheetRecord getBoundSheetRec(int sheetIndex) {
        return this.boundsheets.get(sheetIndex);
    }

    public BackupRecord getBackupRecord() {
        return (BackupRecord)this.records.get(this.records.getBackuppos());
    }

    public void setSheetName(int sheetnum, String sheetname) {
        this.checkSheets(sheetnum);
        String sn = sheetname.length() > 31 ? sheetname.substring(0, 31) : sheetname;
        BoundSheetRecord sheet = this.boundsheets.get(sheetnum);
        sheet.setSheetname(sn);
    }

    public boolean doesContainsSheetName(String name, int excludeSheetIdx) {
        String aName = name;
        if (aName.length() > 31) {
            aName = aName.substring(0, 31);
        }
        int i = 0;
        for (BoundSheetRecord boundSheetRecord : this.boundsheets) {
            if (excludeSheetIdx == i++) continue;
            String bName = boundSheetRecord.getSheetname();
            if (bName.length() > 31) {
                bName = bName.substring(0, 31);
            }
            if (!aName.equalsIgnoreCase(bName)) continue;
            return true;
        }
        return false;
    }

    public void setSheetOrder(String sheetname, int pos) {
        int sheetNumber = this.getSheetIndex(sheetname);
        this.boundsheets.add(pos, this.boundsheets.remove(sheetNumber));
        int initialBspos = this.records.getBspos();
        int pos0 = initialBspos - (this.boundsheets.size() - 1);
        Record removed = this.records.get(pos0 + sheetNumber);
        this.records.remove(pos0 + sheetNumber);
        this.records.add(pos0 + pos, removed);
        this.records.setBspos(initialBspos);
    }

    public String getSheetName(int sheetIndex) {
        return this.getBoundSheetRec(sheetIndex).getSheetname();
    }

    public boolean isSheetHidden(int sheetnum) {
        return this.getBoundSheetRec(sheetnum).isHidden();
    }

    public boolean isSheetVeryHidden(int sheetnum) {
        return this.getBoundSheetRec(sheetnum).isVeryHidden();
    }

    public SheetVisibility getSheetVisibility(int sheetnum) {
        BoundSheetRecord bsr = this.getBoundSheetRec(sheetnum);
        if (bsr.isVeryHidden()) {
            return SheetVisibility.VERY_HIDDEN;
        }
        if (bsr.isHidden()) {
            return SheetVisibility.HIDDEN;
        }
        return SheetVisibility.VISIBLE;
    }

    public void setSheetHidden(int sheetnum, boolean hidden) {
        this.setSheetHidden(sheetnum, hidden ? SheetVisibility.HIDDEN : SheetVisibility.VISIBLE);
    }

    public void setSheetHidden(int sheetnum, SheetVisibility visibility) {
        this.checkSheets(sheetnum);
        BoundSheetRecord bsr = this.getBoundSheetRec(sheetnum);
        bsr.setHidden(visibility == SheetVisibility.HIDDEN);
        bsr.setVeryHidden(visibility == SheetVisibility.VERY_HIDDEN);
    }

    public int getSheetIndex(String name) {
        int retval = -1;
        int size = this.boundsheets.size();
        for (int k = 0; k < size; ++k) {
            String sheet = this.getSheetName(k);
            if (!sheet.equalsIgnoreCase(name)) continue;
            retval = k;
            break;
        }
        return retval;
    }

    private void checkSheets(int sheetnum) {
        if (this.boundsheets.size() <= sheetnum) {
            if (this.boundsheets.size() + 1 <= sheetnum) {
                throw new RuntimeException("Sheet number out of bounds!");
            }
            BoundSheetRecord bsr = InternalWorkbook.createBoundSheet(sheetnum);
            this.records.add(this.records.getBspos() + 1, bsr);
            this.records.setBspos(this.records.getBspos() + 1);
            this.boundsheets.add(bsr);
            this.getOrCreateLinkTable().checkExternSheet(sheetnum);
            this.fixTabIdRecord();
        }
    }

    public void removeSheet(int sheetIndex) {
        if (this.boundsheets.size() > sheetIndex) {
            this.records.remove(this.records.getBspos() - (this.boundsheets.size() - 1) + sheetIndex);
            this.boundsheets.remove(sheetIndex);
            this.fixTabIdRecord();
        }
        int sheetNum1Based = sheetIndex + 1;
        for (int i = 0; i < this.getNumNames(); ++i) {
            NameRecord nr = this.getNameRecord(i);
            if (nr.getSheetNumber() == sheetNum1Based) {
                nr.setSheetNumber(0);
                continue;
            }
            if (nr.getSheetNumber() <= sheetNum1Based) continue;
            nr.setSheetNumber(nr.getSheetNumber() - 1);
        }
        if (this.linkTable != null) {
            this.linkTable.removeSheet(sheetIndex);
        }
    }

    private void fixTabIdRecord() {
        Record rec = this.records.get(this.records.getTabpos());
        if (this.records.getTabpos() <= 0) {
            return;
        }
        TabIdRecord tir = (TabIdRecord)rec;
        short[] tia = new short[this.boundsheets.size()];
        for (int k = 0; k < tia.length; k = (int)((short)(k + 1))) {
            tia[k] = k;
        }
        tir.setTabIdArray(tia);
    }

    public int getNumSheets() {
        LOG.log(1, "getNumSheets=", this.boundsheets.size());
        return this.boundsheets.size();
    }

    public int getNumExFormats() {
        LOG.log(1, "getXF=", this.numxfs);
        return this.numxfs;
    }

    public ExtendedFormatRecord getExFormatAt(int index) {
        int xfptr = this.records.getXfpos() - (this.numxfs - 1);
        return (ExtendedFormatRecord)this.records.get(xfptr += index);
    }

    public void removeExFormatRecord(ExtendedFormatRecord rec) {
        this.records.remove(rec);
        --this.numxfs;
    }

    public void removeExFormatRecord(int index) {
        int xfptr = this.records.getXfpos() - (this.numxfs - 1) + index;
        this.records.remove(xfptr);
        --this.numxfs;
    }

    public ExtendedFormatRecord createCellXF() {
        ExtendedFormatRecord xf = InternalWorkbook.createExtendedFormat();
        this.records.add(this.records.getXfpos() + 1, xf);
        this.records.setXfpos(this.records.getXfpos() + 1);
        ++this.numxfs;
        return xf;
    }

    public StyleRecord getStyleRecord(int xfIndex) {
        for (int i = this.records.getXfpos(); i < this.records.size(); ++i) {
            StyleRecord sr;
            Record r = this.records.get(i);
            if (!(r instanceof StyleRecord) || (sr = (StyleRecord)r).getXFIndex() != xfIndex) continue;
            return sr;
        }
        return null;
    }

    public void updateStyleRecord(int oldXf, int newXf) {
        for (int i = this.records.getXfpos(); i < this.records.size(); ++i) {
            StyleRecord sr;
            Record r = this.records.get(i);
            if (!(r instanceof StyleRecord) || (sr = (StyleRecord)r).getXFIndex() != oldXf) continue;
            sr.setXFIndex(newXf);
        }
    }

    public StyleRecord createStyleRecord(int xfIndex) {
        StyleRecord newSR = new StyleRecord();
        newSR.setXFIndex(xfIndex);
        int addAt = -1;
        for (int i = this.records.getXfpos(); i < this.records.size() && addAt == -1; ++i) {
            Record r = this.records.get(i);
            if (r instanceof ExtendedFormatRecord || r instanceof StyleRecord) continue;
            addAt = i;
        }
        if (addAt == -1) {
            throw new IllegalStateException("No XF Records found!");
        }
        this.records.add(addAt, newSR);
        return newSR;
    }

    public int addSSTString(UnicodeString string) {
        LOG.log(1, "insert to sst string='", string);
        if (this.sst == null) {
            this.insertSST();
        }
        return this.sst.addString(string);
    }

    public UnicodeString getSSTString(int str) {
        if (this.sst == null) {
            this.insertSST();
        }
        UnicodeString retval = this.sst.getString(str);
        LOG.log(1, "Returning SST for index=", str, " String= ", retval);
        return retval;
    }

    public void insertSST() {
        LOG.log(1, "creating new SST via insertSST!");
        this.sst = new SSTRecord();
        this.records.add(this.records.size() - 1, InternalWorkbook.createExtendedSST());
        this.records.add(this.records.size() - 2, this.sst);
    }

    public int serialize(int offset, byte[] data) {
        LOG.log(1, "Serializing Workbook with offsets");
        int pos = 0;
        SSTRecord lSST = null;
        int sstPos = 0;
        boolean wroteBoundSheets = false;
        for (Record record : this.records.getRecords()) {
            int len = 0;
            if (record instanceof SSTRecord) {
                lSST = (SSTRecord)record;
                sstPos = pos;
            }
            if (record.getSid() == 255 && lSST != null) {
                record = lSST.createExtSSTRecord(sstPos + offset);
            }
            if (record instanceof BoundSheetRecord) {
                if (!wroteBoundSheets) {
                    for (BoundSheetRecord bsr : this.boundsheets) {
                        len += bsr.serialize(pos + offset + len, data);
                    }
                    wroteBoundSheets = true;
                }
            } else {
                len = record.serialize(pos + offset, data);
            }
            pos += len;
        }
        LOG.log(1, "Exiting serialize workbook");
        return pos;
    }

    public void preSerialize() {
        if (this.records.getTabpos() > 0) {
            TabIdRecord tir = (TabIdRecord)this.records.get(this.records.getTabpos());
            if (tir._tabids.length < this.boundsheets.size()) {
                this.fixTabIdRecord();
            }
        }
    }

    public int getSize() {
        int retval = 0;
        SSTRecord lSST = null;
        for (Record record : this.records.getRecords()) {
            if (record instanceof SSTRecord) {
                lSST = (SSTRecord)record;
            }
            if (record.getSid() == 255 && lSST != null) {
                retval += lSST.calcExtSSTRecordSize();
                continue;
            }
            retval += record.getRecordSize();
        }
        return retval;
    }

    private static BOFRecord createBOF() {
        BOFRecord retval = new BOFRecord();
        retval.setVersion(1536);
        retval.setType(5);
        retval.setBuild(4307);
        retval.setBuildYear(1996);
        retval.setHistoryBitMask(65);
        retval.setRequiredVersion(6);
        return retval;
    }

    private static MMSRecord createMMS() {
        MMSRecord retval = new MMSRecord();
        retval.setAddMenuCount((byte)0);
        retval.setDelMenuCount((byte)0);
        return retval;
    }

    private static WriteAccessRecord createWriteAccess() {
        WriteAccessRecord retval = new WriteAccessRecord();
        String defaultUserName = "POI";
        try {
            String username = System.getProperty("user.name");
            if (username == null) {
                username = defaultUserName;
            }
            retval.setUsername(username);
        } catch (AccessControlException e) {
            LOG.log(5, "can't determine user.name", e);
            retval.setUsername(defaultUserName);
        }
        return retval;
    }

    private static CodepageRecord createCodepage() {
        CodepageRecord retval = new CodepageRecord();
        retval.setCodepage((short)1200);
        return retval;
    }

    private static DSFRecord createDSF() {
        return new DSFRecord(false);
    }

    private static TabIdRecord createTabId() {
        return new TabIdRecord();
    }

    private static FnGroupCountRecord createFnGroupCount() {
        FnGroupCountRecord retval = new FnGroupCountRecord();
        retval.setCount((short)14);
        return retval;
    }

    private static WindowProtectRecord createWindowProtect() {
        return new WindowProtectRecord(false);
    }

    private static ProtectRecord createProtect() {
        return new ProtectRecord(false);
    }

    private static PasswordRecord createPassword() {
        return new PasswordRecord(0);
    }

    private static ProtectionRev4Record createProtectionRev4() {
        return new ProtectionRev4Record(false);
    }

    private static PasswordRev4Record createPasswordRev4() {
        return new PasswordRev4Record(0);
    }

    private static WindowOneRecord createWindowOne() {
        WindowOneRecord retval = new WindowOneRecord();
        retval.setHorizontalHold((short)360);
        retval.setVerticalHold((short)270);
        retval.setWidth((short)14940);
        retval.setHeight((short)9150);
        retval.setOptions((short)56);
        retval.setActiveSheetIndex(0);
        retval.setFirstVisibleTab(0);
        retval.setNumSelectedTabs((short)1);
        retval.setTabWidthRatio((short)600);
        return retval;
    }

    private static BackupRecord createBackup() {
        BackupRecord retval = new BackupRecord();
        retval.setBackup((short)0);
        return retval;
    }

    private static HideObjRecord createHideObj() {
        HideObjRecord retval = new HideObjRecord();
        retval.setHideObj((short)0);
        return retval;
    }

    private static DateWindow1904Record createDateWindow1904() {
        DateWindow1904Record retval = new DateWindow1904Record();
        retval.setWindowing((short)0);
        return retval;
    }

    private static PrecisionRecord createPrecision() {
        PrecisionRecord retval = new PrecisionRecord();
        retval.setFullPrecision(true);
        return retval;
    }

    private static RefreshAllRecord createRefreshAll() {
        return new RefreshAllRecord(false);
    }

    private static BookBoolRecord createBookBool() {
        BookBoolRecord retval = new BookBoolRecord();
        retval.setSaveLinkValues((short)0);
        return retval;
    }

    private static FontRecord createFont() {
        FontRecord retval = new FontRecord();
        retval.setFontHeight((short)200);
        retval.setAttributes((short)0);
        retval.setColorPaletteIndex((short)Short.MAX_VALUE);
        retval.setBoldWeight((short)400);
        retval.setFontName("Arial");
        return retval;
    }

    private static FormatRecord createFormat(int id) {
        int[] mappings = new int[]{5, 6, 7, 8, 42, 41, 44, 43};
        if (id < 0 || id >= mappings.length) {
            throw new IllegalArgumentException("Unexpected id " + id);
        }
        return new FormatRecord(mappings[id], BuiltinFormats.getBuiltinFormat(mappings[id]));
    }

    private static ExtendedFormatRecord createExtendedFormat(int id) {
        switch (id) {
            case 0: {
                return InternalWorkbook.createExtendedFormat(0, 0, -11, 0);
            }
            case 1: 
            case 2: {
                return InternalWorkbook.createExtendedFormat(1, 0, -11, -3072);
            }
            case 3: 
            case 4: {
                return InternalWorkbook.createExtendedFormat(2, 0, -11, -3072);
            }
            case 5: 
            case 6: 
            case 7: 
            case 8: 
            case 9: 
            case 10: 
            case 11: 
            case 12: 
            case 13: 
            case 14: {
                return InternalWorkbook.createExtendedFormat(0, 0, -11, -3072);
            }
            case 15: {
                return InternalWorkbook.createExtendedFormat(0, 0, 1, 0);
            }
            case 16: {
                return InternalWorkbook.createExtendedFormat(1, 43, -11, -2048);
            }
            case 17: {
                return InternalWorkbook.createExtendedFormat(1, 41, -11, -2048);
            }
            case 18: {
                return InternalWorkbook.createExtendedFormat(1, 44, -11, -2048);
            }
            case 19: {
                return InternalWorkbook.createExtendedFormat(1, 42, -11, -2048);
            }
            case 20: {
                return InternalWorkbook.createExtendedFormat(1, 9, -11, -2048);
            }
            case 21: {
                return InternalWorkbook.createExtendedFormat(5, 0, 1, 2048);
            }
            case 22: {
                return InternalWorkbook.createExtendedFormat(6, 0, 1, 23552);
            }
            case 23: {
                return InternalWorkbook.createExtendedFormat(0, 49, 1, 23552);
            }
            case 24: {
                return InternalWorkbook.createExtendedFormat(0, 8, 1, 23552);
            }
            case 25: {
                return InternalWorkbook.createExtendedFormat(6, 8, 1, 23552);
            }
        }
        throw new IllegalStateException("Unrecognized format id: " + id);
    }

    private static ExtendedFormatRecord createExtendedFormat(int fontIndex, int formatIndex, int cellOptions, int indentionOptions) {
        ExtendedFormatRecord retval = new ExtendedFormatRecord();
        retval.setFontIndex((short)fontIndex);
        retval.setFormatIndex((short)formatIndex);
        retval.setCellOptions((short)cellOptions);
        retval.setAlignmentOptions((short)32);
        retval.setIndentionOptions((short)indentionOptions);
        retval.setBorderOptions((short)0);
        retval.setPaletteOptions((short)0);
        retval.setAdtlPaletteOptions((short)0);
        retval.setFillPaletteOptions((short)8384);
        return retval;
    }

    private static ExtendedFormatRecord createExtendedFormat() {
        ExtendedFormatRecord retval = new ExtendedFormatRecord();
        retval.setFontIndex((short)0);
        retval.setFormatIndex((short)0);
        retval.setCellOptions((short)1);
        retval.setAlignmentOptions((short)32);
        retval.setIndentionOptions((short)0);
        retval.setBorderOptions((short)0);
        retval.setPaletteOptions((short)0);
        retval.setAdtlPaletteOptions((short)0);
        retval.setFillPaletteOptions((short)8384);
        retval.setTopBorderPaletteIdx(HSSFColor.HSSFColorPredefined.BLACK.getIndex());
        retval.setBottomBorderPaletteIdx(HSSFColor.HSSFColorPredefined.BLACK.getIndex());
        retval.setLeftBorderPaletteIdx(HSSFColor.HSSFColorPredefined.BLACK.getIndex());
        retval.setRightBorderPaletteIdx(HSSFColor.HSSFColorPredefined.BLACK.getIndex());
        return retval;
    }

    private static StyleRecord createStyle(int id) {
        int[][] mappings = new int[][]{{16, 3}, {17, 6}, {18, 4}, {19, 7}, {0, 0}, {20, 5}};
        if (id < 0 || id >= mappings.length) {
            throw new IllegalArgumentException("Unexpected style id " + id);
        }
        StyleRecord retval = new StyleRecord();
        retval.setOutlineStyleLevel(-1);
        retval.setXFIndex(mappings[id][0]);
        retval.setBuiltinStyle(mappings[id][1]);
        return retval;
    }

    private static PaletteRecord createPalette() {
        return new PaletteRecord();
    }

    private static UseSelFSRecord createUseSelFS() {
        return new UseSelFSRecord(false);
    }

    private static BoundSheetRecord createBoundSheet(int id) {
        return new BoundSheetRecord("Sheet" + (id + 1));
    }

    private static CountryRecord createCountry() {
        CountryRecord retval = new CountryRecord();
        retval.setDefaultCountry((short)1);
        if ("ru_RU".equals(LocaleUtil.getUserLocale().toString())) {
            retval.setCurrentCountry((short)7);
        } else {
            retval.setCurrentCountry((short)1);
        }
        return retval;
    }

    private static ExtSSTRecord createExtendedSST() {
        ExtSSTRecord retval = new ExtSSTRecord();
        retval.setNumStringsPerBucket((short)8);
        return retval;
    }

    private LinkTable getOrCreateLinkTable() {
        if (this.linkTable == null) {
            this.linkTable = new LinkTable((short)this.getNumSheets(), this.records);
        }
        return this.linkTable;
    }

    public int linkExternalWorkbook(String name, Workbook externalWorkbook) {
        return this.getOrCreateLinkTable().linkExternalWorkbook(name, externalWorkbook);
    }

    public String findSheetFirstNameFromExternSheet(int externSheetIndex) {
        int indexToSheet = this.linkTable.getFirstInternalSheetIndexForExtIndex(externSheetIndex);
        return this.findSheetNameFromIndex(indexToSheet);
    }

    public String findSheetLastNameFromExternSheet(int externSheetIndex) {
        int indexToSheet = this.linkTable.getLastInternalSheetIndexForExtIndex(externSheetIndex);
        return this.findSheetNameFromIndex(indexToSheet);
    }

    private String findSheetNameFromIndex(int internalSheetIndex) {
        if (internalSheetIndex < 0) {
            return "";
        }
        if (internalSheetIndex >= this.boundsheets.size()) {
            return "";
        }
        return this.getSheetName(internalSheetIndex);
    }

    public EvaluationWorkbook.ExternalSheet getExternalSheet(int externSheetIndex) {
        String[] extNames = this.linkTable.getExternalBookAndSheetName(externSheetIndex);
        if (extNames == null) {
            return null;
        }
        if (extNames.length == 2) {
            return new EvaluationWorkbook.ExternalSheet(extNames[0], extNames[1]);
        }
        return new EvaluationWorkbook.ExternalSheetRange(extNames[0], extNames[1], extNames[2]);
    }

    public EvaluationWorkbook.ExternalName getExternalName(int externSheetIndex, int externNameIndex) {
        String nameName = this.linkTable.resolveNameXText(externSheetIndex, externNameIndex, this);
        if (nameName == null) {
            return null;
        }
        int ix = this.linkTable.resolveNameXIx(externSheetIndex, externNameIndex);
        return new EvaluationWorkbook.ExternalName(nameName, externNameIndex, ix);
    }

    public int getFirstSheetIndexFromExternSheetIndex(int externSheetNumber) {
        return this.linkTable.getFirstInternalSheetIndexForExtIndex(externSheetNumber);
    }

    public int getLastSheetIndexFromExternSheetIndex(int externSheetNumber) {
        return this.linkTable.getLastInternalSheetIndexForExtIndex(externSheetNumber);
    }

    public short checkExternSheet(int sheetNumber) {
        return (short)this.getOrCreateLinkTable().checkExternSheet(sheetNumber);
    }

    public short checkExternSheet(int firstSheetNumber, int lastSheetNumber) {
        return (short)this.getOrCreateLinkTable().checkExternSheet(firstSheetNumber, lastSheetNumber);
    }

    public int getExternalSheetIndex(String workbookName, String sheetName) {
        return this.getOrCreateLinkTable().getExternalSheetIndex(workbookName, sheetName, sheetName);
    }

    public int getExternalSheetIndex(String workbookName, String firstSheetName, String lastSheetName) {
        return this.getOrCreateLinkTable().getExternalSheetIndex(workbookName, firstSheetName, lastSheetName);
    }

    public int getNumNames() {
        if (this.linkTable == null) {
            return 0;
        }
        return this.linkTable.getNumNames();
    }

    public NameRecord getNameRecord(int index) {
        return this.linkTable.getNameRecord(index);
    }

    public NameCommentRecord getNameCommentRecord(NameRecord nameRecord) {
        return this.commentRecords.get(nameRecord.getNameText());
    }

    public NameRecord createName() {
        return this.addName(new NameRecord());
    }

    public NameRecord addName(NameRecord name) {
        this.getOrCreateLinkTable().addName(name);
        return name;
    }

    public NameRecord createBuiltInName(byte builtInName, int sheetNumber) {
        if (sheetNumber < 0 || sheetNumber + 1 > Short.MAX_VALUE) {
            throw new IllegalArgumentException("Sheet number [" + sheetNumber + "]is not valid ");
        }
        NameRecord name = new NameRecord(builtInName, sheetNumber);
        if (this.linkTable.nameAlreadyExists(name)) {
            throw new RuntimeException("Builtin (" + builtInName + ") already exists for sheet (" + sheetNumber + ")");
        }
        this.addName(name);
        return name;
    }

    public void removeName(int nameIndex) {
        if (this.linkTable.getNumNames() > nameIndex) {
            int idx = this.findFirstRecordLocBySid((short)24);
            this.records.remove(idx + nameIndex);
            this.linkTable.removeName(nameIndex);
        }
    }

    public void updateNameCommentRecordCache(NameCommentRecord commentRecord) {
        if (this.commentRecords.containsValue(commentRecord)) {
            for (Map.Entry<String, NameCommentRecord> entry : this.commentRecords.entrySet()) {
                if (!entry.getValue().equals(commentRecord)) continue;
                this.commentRecords.remove(entry.getKey());
                break;
            }
        }
        this.commentRecords.put(commentRecord.getNameText(), commentRecord);
    }

    public short getFormat(String format, boolean createIfNotFound) {
        for (FormatRecord r : this.formats) {
            if (!r.getFormatString().equals(format)) continue;
            return (short)r.getIndexCode();
        }
        if (createIfNotFound) {
            return (short)this.createFormat(format);
        }
        return -1;
    }

    public List<FormatRecord> getFormats() {
        return this.formats;
    }

    public int createFormat(String formatString) {
        this.maxformatid = this.maxformatid >= 164 ? this.maxformatid + 1 : 164;
        FormatRecord rec = new FormatRecord(this.maxformatid, formatString);
        for (int pos = 0; pos < this.records.size() && this.records.get(pos).getSid() != 1054; ++pos) {
        }
        this.formats.add(rec);
        this.records.add(pos += this.formats.size(), rec);
        return this.maxformatid;
    }

    public Record findFirstRecordBySid(short sid) {
        for (Record record : this.records.getRecords()) {
            if (record.getSid() != sid) continue;
            return record;
        }
        return null;
    }

    public int findFirstRecordLocBySid(short sid) {
        int index = 0;
        for (Record record : this.records.getRecords()) {
            if (record.getSid() == sid) {
                return index;
            }
            ++index;
        }
        return -1;
    }

    public Record findNextRecordBySid(short sid, int pos) {
        int matches = 0;
        for (Record record : this.records.getRecords()) {
            if (record.getSid() != sid || matches++ != pos) continue;
            return record;
        }
        return null;
    }

    public List<HyperlinkRecord> getHyperlinks() {
        return this.hyperlinks;
    }

    public List<Record> getRecords() {
        return this.records.getRecords();
    }

    public boolean isUsing1904DateWindowing() {
        return this.uses1904datewindowing;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public PaletteRecord getCustomPalette() {
        int palettePos = this.records.getPalettepos();
        if (palettePos != -1) {
            Record rec = this.records.get(palettePos);
            if (!(rec instanceof PaletteRecord)) throw new RuntimeException("InternalError: Expected PaletteRecord but got a '" + rec + "'");
            return (PaletteRecord)rec;
        }
        PaletteRecord palette = InternalWorkbook.createPalette();
        this.records.add(1, palette);
        this.records.setPalettepos(1);
        return palette;
    }

    public DrawingManager2 findDrawingGroup() {
        if (this.drawingManager != null) {
            return this.drawingManager;
        }
        for (Record r : this.records.getRecords()) {
            if (!(r instanceof DrawingGroupRecord)) continue;
            DrawingGroupRecord dg = (DrawingGroupRecord)r;
            dg.processChildRecords();
            this.drawingManager = InternalWorkbook.findDrawingManager(dg, this.escherBSERecords);
            if (this.drawingManager == null) continue;
            return this.drawingManager;
        }
        DrawingGroupRecord dg = (DrawingGroupRecord)this.findFirstRecordBySid((short)235);
        this.drawingManager = InternalWorkbook.findDrawingManager(dg, this.escherBSERecords);
        return this.drawingManager;
    }

    private static DrawingManager2 findDrawingManager(DrawingGroupRecord dg, List<EscherBSERecord> escherBSERecords) {
        if (dg == null) {
            return null;
        }
        EscherContainerRecord cr = dg.getEscherContainer();
        if (cr == null) {
            return null;
        }
        EscherDggRecord dgg = null;
        EscherContainerRecord bStore = null;
        for (EscherRecord er : cr) {
            if (er instanceof EscherDggRecord) {
                dgg = (EscherDggRecord)er;
                continue;
            }
            if (er.getRecordId() != EscherContainerRecord.BSTORE_CONTAINER) continue;
            bStore = (EscherContainerRecord)er;
        }
        if (dgg == null) {
            return null;
        }
        DrawingManager2 dm = new DrawingManager2(dgg);
        if (bStore != null) {
            for (EscherRecord bs : bStore.getChildRecords()) {
                if (!(bs instanceof EscherBSERecord)) continue;
                escherBSERecords.add((EscherBSERecord)bs);
            }
        }
        return dm;
    }

    public void createDrawingGroup() {
        if (this.drawingManager == null) {
            EscherContainerRecord dggContainer = new EscherContainerRecord();
            EscherDggRecord dgg = new EscherDggRecord();
            EscherOptRecord opt = new EscherOptRecord();
            EscherSplitMenuColorsRecord splitMenuColors = new EscherSplitMenuColorsRecord();
            dggContainer.setRecordId((short)-4096);
            dggContainer.setOptions((short)15);
            dgg.setRecordId(EscherDggRecord.RECORD_ID);
            dgg.setOptions((short)0);
            dgg.setShapeIdMax(1024);
            dgg.setNumShapesSaved(0);
            dgg.setDrawingsSaved(0);
            dgg.setFileIdClusters(new EscherDggRecord.FileIdCluster[0]);
            this.drawingManager = new DrawingManager2(dgg);
            EscherContainerRecord bstoreContainer = null;
            if (!this.escherBSERecords.isEmpty()) {
                bstoreContainer = new EscherContainerRecord();
                bstoreContainer.setRecordId(EscherContainerRecord.BSTORE_CONTAINER);
                bstoreContainer.setOptions((short)(this.escherBSERecords.size() << 4 | 0xF));
                for (EscherRecord escherRecord : this.escherBSERecords) {
                    bstoreContainer.addChildRecord(escherRecord);
                }
            }
            opt.setRecordId((short)-4085);
            opt.setOptions((short)51);
            opt.addEscherProperty(new EscherBoolProperty(EscherPropertyTypes.TEXT__SIZE_TEXT_TO_FIT_SHAPE, 524296));
            opt.addEscherProperty(new EscherRGBProperty(EscherPropertyTypes.FILL__FILLCOLOR, 134217793));
            opt.addEscherProperty(new EscherRGBProperty(EscherPropertyTypes.LINESTYLE__COLOR, 0x8000040));
            splitMenuColors.setRecordId((short)-3810);
            splitMenuColors.setOptions((short)64);
            splitMenuColors.setColor1(0x800000D);
            splitMenuColors.setColor2(0x800000C);
            splitMenuColors.setColor3(134217751);
            splitMenuColors.setColor4(268435703);
            dggContainer.addChildRecord(dgg);
            if (bstoreContainer != null) {
                dggContainer.addChildRecord(bstoreContainer);
            }
            dggContainer.addChildRecord(opt);
            dggContainer.addChildRecord(splitMenuColors);
            int dgLoc = this.findFirstRecordLocBySid((short)235);
            if (dgLoc == -1) {
                DrawingGroupRecord drawingGroupRecord = new DrawingGroupRecord();
                drawingGroupRecord.addEscherRecord(dggContainer);
                int loc = this.findFirstRecordLocBySid((short)140);
                this.getRecords().add(loc + 1, drawingGroupRecord);
            } else {
                DrawingGroupRecord drawingGroupRecord = new DrawingGroupRecord();
                drawingGroupRecord.addEscherRecord(dggContainer);
                this.getRecords().set(dgLoc, drawingGroupRecord);
            }
        }
    }

    public WindowOneRecord getWindowOne() {
        return this.windowOne;
    }

    public EscherBSERecord getBSERecord(int pictureIndex) {
        return this.escherBSERecords.get(pictureIndex - 1);
    }

    public int addBSERecord(EscherBSERecord e) {
        EscherContainerRecord bstoreContainer;
        this.createDrawingGroup();
        this.escherBSERecords.add(e);
        int dgLoc = this.findFirstRecordLocBySid((short)235);
        DrawingGroupRecord drawingGroup = (DrawingGroupRecord)this.getRecords().get(dgLoc);
        EscherContainerRecord dggContainer = (EscherContainerRecord)drawingGroup.getEscherRecord(0);
        if (dggContainer.getChild(1).getRecordId() == EscherContainerRecord.BSTORE_CONTAINER) {
            bstoreContainer = (EscherContainerRecord)dggContainer.getChild(1);
        } else {
            bstoreContainer = new EscherContainerRecord();
            bstoreContainer.setRecordId(EscherContainerRecord.BSTORE_CONTAINER);
            List<EscherRecord> childRecords = dggContainer.getChildRecords();
            childRecords.add(1, bstoreContainer);
            dggContainer.setChildRecords(childRecords);
        }
        bstoreContainer.setOptions((short)(this.escherBSERecords.size() << 4 | 0xF));
        bstoreContainer.addChildRecord(e);
        return this.escherBSERecords.size();
    }

    public DrawingManager2 getDrawingManager() {
        return this.drawingManager;
    }

    public WriteProtectRecord getWriteProtect() {
        if (this.writeProtect == null) {
            this.writeProtect = new WriteProtectRecord();
            int i = this.findFirstRecordLocBySid((short)2057);
            this.records.add(i + 1, this.writeProtect);
        }
        return this.writeProtect;
    }

    public WriteAccessRecord getWriteAccess() {
        if (this.writeAccess == null) {
            this.writeAccess = InternalWorkbook.createWriteAccess();
            int i = this.findFirstRecordLocBySid((short)226);
            this.records.add(i + 1, this.writeAccess);
        }
        return this.writeAccess;
    }

    public FileSharingRecord getFileSharing() {
        if (this.fileShare == null) {
            this.fileShare = new FileSharingRecord();
            int i = this.findFirstRecordLocBySid((short)92);
            this.records.add(i + 1, this.fileShare);
        }
        return this.fileShare;
    }

    public boolean isWriteProtected() {
        if (this.fileShare == null) {
            return false;
        }
        FileSharingRecord frec = this.getFileSharing();
        return frec.getReadOnly() == 1;
    }

    public void writeProtectWorkbook(String password, String username) {
        FileSharingRecord frec = this.getFileSharing();
        WriteAccessRecord waccess = this.getWriteAccess();
        this.getWriteProtect();
        frec.setReadOnly((short)1);
        frec.setPassword((short)CryptoFunctions.createXorVerifier1(password));
        frec.setUsername(username);
        waccess.setUsername(username);
    }

    public void unwriteProtectWorkbook() {
        this.records.remove(this.fileShare);
        this.records.remove(this.writeProtect);
        this.fileShare = null;
        this.writeProtect = null;
    }

    public String resolveNameXText(int refIndex, int definedNameIndex) {
        return this.linkTable.resolveNameXText(refIndex, definedNameIndex, this);
    }

    public NameXPtg getNameXPtg(String name, int sheetRefIndex, UDFFinder udf) {
        LinkTable lnk = this.getOrCreateLinkTable();
        NameXPtg xptg = lnk.getNameXPtg(name, sheetRefIndex);
        if (xptg == null && udf.findFunction(name) != null) {
            xptg = lnk.addNameXPtg(name);
        }
        return xptg;
    }

    public NameXPtg getNameXPtg(String name, UDFFinder udf) {
        return this.getNameXPtg(name, -1, udf);
    }

    public void cloneDrawings(InternalSheet sheet) {
        this.findDrawingGroup();
        if (this.drawingManager == null) {
            return;
        }
        int aggLoc = sheet.aggregateDrawingRecords(this.drawingManager, false);
        if (aggLoc == -1) {
            return;
        }
        EscherAggregate agg = (EscherAggregate)sheet.findFirstRecordBySid((short)9876);
        EscherContainerRecord escherContainer = agg.getEscherContainer();
        if (escherContainer == null) {
            return;
        }
        EscherDggRecord dgg = this.drawingManager.getDgg();
        short dgId = this.drawingManager.findNewDrawingGroupId();
        dgg.addCluster(dgId, 0);
        dgg.setDrawingsSaved(dgg.getDrawingsSaved() + 1);
        EscherDgRecord dg = null;
        for (EscherRecord er : escherContainer) {
            if (er instanceof EscherDgRecord) {
                dg = (EscherDgRecord)er;
                dg.setOptions((short)(dgId << 4));
                continue;
            }
            if (!(er instanceof EscherContainerRecord)) continue;
            for (EscherRecord er2 : (EscherContainerRecord)er) {
                for (EscherRecord shapeChildRecord : (EscherContainerRecord)er2) {
                    EscherOptRecord opt;
                    EscherSimpleProperty prop;
                    short recordId = shapeChildRecord.getRecordId();
                    if (recordId == EscherSpRecord.RECORD_ID) {
                        if (dg == null) {
                            throw new RecordFormatException("EscherDgRecord wasn't set/processed before.");
                        }
                        EscherSpRecord sp = (EscherSpRecord)shapeChildRecord;
                        int shapeId = this.drawingManager.allocateShapeId(dg);
                        dg.setNumShapes(dg.getNumShapes() - 1);
                        sp.setShapeId(shapeId);
                        continue;
                    }
                    if (recordId != EscherOptRecord.RECORD_ID || (prop = (EscherSimpleProperty)(opt = (EscherOptRecord)shapeChildRecord).lookup(EscherPropertyTypes.BLIP__BLIPTODISPLAY)) == null) continue;
                    int pictureIndex = prop.getPropertyValue();
                    EscherBSERecord bse = this.getBSERecord(pictureIndex);
                    bse.setRef(bse.getRef() + 1);
                }
            }
        }
    }

    public NameRecord cloneFilter(int filterDbNameIndex, int newSheetIndex) {
        NameRecord origNameRecord = this.getNameRecord(filterDbNameIndex);
        short newExtSheetIx = this.checkExternSheet(newSheetIndex);
        Ptg[] ptgs = origNameRecord.getNameDefinition();
        for (int i = 0; i < ptgs.length; ++i) {
            Ptg ptg = ptgs[i];
            if (ptg instanceof Area3DPtg) {
                Area3DPtg a3p = (Area3DPtg)((OperandPtg)ptg).copy();
                a3p.setExternSheetIndex(newExtSheetIx);
                ptgs[i] = a3p;
                continue;
            }
            if (!(ptg instanceof Ref3DPtg)) continue;
            Ref3DPtg r3p = (Ref3DPtg)((OperandPtg)ptg).copy();
            r3p.setExternSheetIndex(newExtSheetIx);
            ptgs[i] = r3p;
        }
        NameRecord newNameRecord = this.createBuiltInName((byte)13, newSheetIndex + 1);
        newNameRecord.setNameDefinition(ptgs);
        newNameRecord.setHidden(true);
        return newNameRecord;
    }

    public void updateNamesAfterCellShift(FormulaShifter shifter) {
        for (int i = 0; i < this.getNumNames(); ++i) {
            NameRecord nr = this.getNameRecord(i);
            Ptg[] ptgs = nr.getNameDefinition();
            if (!shifter.adjustFormula(ptgs, nr.getSheetNumber())) continue;
            nr.setNameDefinition(ptgs);
        }
    }

    public RecalcIdRecord getRecalcId() {
        RecalcIdRecord record = (RecalcIdRecord)this.findFirstRecordBySid((short)449);
        if (record == null) {
            record = new RecalcIdRecord();
            int pos = this.findFirstRecordLocBySid((short)140);
            this.records.add(pos + 1, record);
        }
        return record;
    }

    public boolean changeExternalReference(String oldUrl, String newUrl) {
        return this.linkTable.changeExternalReference(oldUrl, newUrl);
    }

    @Internal
    public WorkbookRecordList getWorkbookRecordList() {
        return this.records;
    }
}

