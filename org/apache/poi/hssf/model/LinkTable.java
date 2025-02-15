/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.hssf.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.apache.poi.hssf.model.InternalWorkbook;
import org.apache.poi.hssf.model.RecordStream;
import org.apache.poi.hssf.model.WorkbookRecordList;
import org.apache.poi.hssf.record.CRNCountRecord;
import org.apache.poi.hssf.record.CRNRecord;
import org.apache.poi.hssf.record.ExternSheetRecord;
import org.apache.poi.hssf.record.ExternalNameRecord;
import org.apache.poi.hssf.record.NameCommentRecord;
import org.apache.poi.hssf.record.NameRecord;
import org.apache.poi.hssf.record.Record;
import org.apache.poi.hssf.record.SupBookRecord;
import org.apache.poi.ss.formula.SheetNameFormatter;
import org.apache.poi.ss.formula.ptg.ErrPtg;
import org.apache.poi.ss.formula.ptg.NameXPtg;
import org.apache.poi.ss.formula.ptg.Ptg;
import org.apache.poi.ss.usermodel.Workbook;

final class LinkTable {
    private ExternalBookBlock[] _externalBookBlocks;
    private final ExternSheetRecord _externSheetRecord;
    private final List<NameRecord> _definedNames;
    private final int _recordCount;
    private final WorkbookRecordList _workbookRecordList;

    public LinkTable(List<Record> inputList, int startIndex, WorkbookRecordList workbookRecordList, Map<String, NameCommentRecord> commentRecords) {
        this._workbookRecordList = workbookRecordList;
        RecordStream rs = new RecordStream(inputList, startIndex);
        ArrayList<ExternalBookBlock> temp = new ArrayList<ExternalBookBlock>();
        while (rs.peekNextClass() == SupBookRecord.class) {
            temp.add(new ExternalBookBlock(rs));
        }
        this._externalBookBlocks = new ExternalBookBlock[temp.size()];
        temp.toArray(this._externalBookBlocks);
        temp.clear();
        this._externSheetRecord = this._externalBookBlocks.length > 0 ? (rs.peekNextClass() != ExternSheetRecord.class ? null : LinkTable.readExtSheetRecord(rs)) : null;
        this._definedNames = new ArrayList<NameRecord>();
        while (true) {
            Class<? extends Record> nextClass;
            if ((nextClass = rs.peekNextClass()) == NameRecord.class) {
                NameRecord nr = (NameRecord)rs.getNext();
                this._definedNames.add(nr);
                continue;
            }
            if (nextClass != NameCommentRecord.class) break;
            NameCommentRecord ncr = (NameCommentRecord)rs.getNext();
            commentRecords.put(ncr.getNameText(), ncr);
        }
        this._recordCount = rs.getCountRead();
        this._workbookRecordList.getRecords().addAll(inputList.subList(startIndex, startIndex + this._recordCount));
    }

    private static ExternSheetRecord readExtSheetRecord(RecordStream rs) {
        ArrayList<ExternSheetRecord> temp = new ArrayList<ExternSheetRecord>(2);
        while (rs.peekNextClass() == ExternSheetRecord.class) {
            temp.add((ExternSheetRecord)rs.getNext());
        }
        int nItems = temp.size();
        if (nItems < 1) {
            throw new RuntimeException("Expected an EXTERNSHEET record but got (" + rs.peekNextClass().getName() + ")");
        }
        if (nItems == 1) {
            return (ExternSheetRecord)temp.get(0);
        }
        ExternSheetRecord[] esrs = new ExternSheetRecord[nItems];
        temp.toArray(esrs);
        return ExternSheetRecord.combine(esrs);
    }

    public LinkTable(int numberOfSheets, WorkbookRecordList workbookRecordList) {
        this._workbookRecordList = workbookRecordList;
        this._definedNames = new ArrayList<NameRecord>();
        this._externalBookBlocks = new ExternalBookBlock[]{new ExternalBookBlock(numberOfSheets)};
        this._externSheetRecord = new ExternSheetRecord();
        this._recordCount = 2;
        SupBookRecord supbook = this._externalBookBlocks[0].getExternalBookRecord();
        int idx = this.findFirstRecordLocBySid((short)140);
        if (idx < 0) {
            throw new RuntimeException("CountryRecord not found");
        }
        this._workbookRecordList.add(idx + 1, this._externSheetRecord);
        this._workbookRecordList.add(idx + 1, supbook);
    }

    public int getRecordCount() {
        return this._recordCount;
    }

    public NameRecord getSpecificBuiltinRecord(byte builtInCode, int sheetNumber) {
        for (NameRecord record : this._definedNames) {
            if (record.getBuiltInName() != builtInCode || record.getSheetNumber() != sheetNumber) continue;
            return record;
        }
        return null;
    }

    public void removeBuiltinRecord(byte name, int sheetIndex) {
        NameRecord record = this.getSpecificBuiltinRecord(name, sheetIndex);
        if (record != null) {
            this._definedNames.remove(record);
        }
    }

    public int getNumNames() {
        return this._definedNames.size();
    }

    public NameRecord getNameRecord(int index) {
        return this._definedNames.get(index);
    }

    public void addName(NameRecord name) {
        this._definedNames.add(name);
        int idx = this.findFirstRecordLocBySid((short)23);
        if (idx == -1) {
            idx = this.findFirstRecordLocBySid((short)430);
        }
        if (idx == -1) {
            idx = this.findFirstRecordLocBySid((short)140);
        }
        int countNames = this._definedNames.size();
        this._workbookRecordList.add(idx + countNames, name);
    }

    public void removeName(int namenum) {
        this._definedNames.remove(namenum);
    }

    public boolean nameAlreadyExists(NameRecord name) {
        for (int i = this.getNumNames() - 1; i >= 0; --i) {
            NameRecord rec = this.getNameRecord(i);
            if (rec == name || !LinkTable.isDuplicatedNames(name, rec)) continue;
            return true;
        }
        return false;
    }

    private static boolean isDuplicatedNames(NameRecord firstName, NameRecord lastName) {
        return lastName.getNameText().equalsIgnoreCase(firstName.getNameText()) && LinkTable.isSameSheetNames(firstName, lastName);
    }

    private static boolean isSameSheetNames(NameRecord firstName, NameRecord lastName) {
        return lastName.getSheetNumber() == firstName.getSheetNumber();
    }

    public String[] getExternalBookAndSheetName(int extRefIndex) {
        int ebIx = this._externSheetRecord.getExtbookIndexFromRefIndex(extRefIndex);
        SupBookRecord ebr = this._externalBookBlocks[ebIx].getExternalBookRecord();
        if (!ebr.isExternalReferences()) {
            return null;
        }
        int shIx1 = this._externSheetRecord.getFirstSheetIndexFromRefIndex(extRefIndex);
        int shIx2 = this._externSheetRecord.getLastSheetIndexFromRefIndex(extRefIndex);
        String firstSheetName = null;
        String lastSheetName = null;
        if (shIx1 >= 0) {
            firstSheetName = ebr.getSheetNames()[shIx1];
        }
        if (shIx2 >= 0) {
            lastSheetName = ebr.getSheetNames()[shIx2];
        }
        if (shIx1 == shIx2) {
            return new String[]{ebr.getURL(), firstSheetName};
        }
        return new String[]{ebr.getURL(), firstSheetName, lastSheetName};
    }

    private int getExternalWorkbookIndex(String workbookName) {
        for (int i = 0; i < this._externalBookBlocks.length; ++i) {
            SupBookRecord ebr = this._externalBookBlocks[i].getExternalBookRecord();
            if (!ebr.isExternalReferences() || !workbookName.equals(ebr.getURL())) continue;
            return i;
        }
        return -1;
    }

    public int linkExternalWorkbook(String name, Workbook externalWorkbook) {
        int extBookIndex = this.getExternalWorkbookIndex(name);
        if (extBookIndex != -1) {
            return extBookIndex;
        }
        String[] sheetNames = new String[externalWorkbook.getNumberOfSheets()];
        for (int sn = 0; sn < sheetNames.length; ++sn) {
            sheetNames[sn] = externalWorkbook.getSheetName(sn);
        }
        String url = "\u0000" + name;
        ExternalBookBlock block = new ExternalBookBlock(url, sheetNames);
        extBookIndex = this.extendExternalBookBlocks(block);
        int idx = this.findFirstRecordLocBySid((short)23);
        if (idx == -1) {
            idx = this._workbookRecordList.size();
        }
        this._workbookRecordList.add(idx, block.getExternalBookRecord());
        for (int sn = 0; sn < sheetNames.length; ++sn) {
            this._externSheetRecord.addRef(extBookIndex, sn, sn);
        }
        return extBookIndex;
    }

    public int getExternalSheetIndex(String workbookName, String firstSheetName, String lastSheetName) {
        int lastSheetIndex;
        int externalBookIndex = this.getExternalWorkbookIndex(workbookName);
        if (externalBookIndex == -1) {
            throw new RuntimeException("No external workbook with name '" + workbookName + "'");
        }
        SupBookRecord ebrTarget = this._externalBookBlocks[externalBookIndex].getExternalBookRecord();
        int firstSheetIndex = LinkTable.getSheetIndex(ebrTarget.getSheetNames(), firstSheetName);
        int result = this._externSheetRecord.getRefIxForSheet(externalBookIndex, firstSheetIndex, lastSheetIndex = LinkTable.getSheetIndex(ebrTarget.getSheetNames(), lastSheetName));
        if (result < 0) {
            result = this._externSheetRecord.addRef(externalBookIndex, firstSheetIndex, lastSheetIndex);
        }
        return result;
    }

    private static int getSheetIndex(String[] sheetNames, String sheetName) {
        for (int i = 0; i < sheetNames.length; ++i) {
            if (!sheetNames[i].equals(sheetName)) continue;
            return i;
        }
        throw new RuntimeException("External workbook does not contain sheet '" + sheetName + "'");
    }

    public int getFirstInternalSheetIndexForExtIndex(int extRefIndex) {
        if (extRefIndex >= this._externSheetRecord.getNumOfRefs() || extRefIndex < 0) {
            return -1;
        }
        return this._externSheetRecord.getFirstSheetIndexFromRefIndex(extRefIndex);
    }

    public int getLastInternalSheetIndexForExtIndex(int extRefIndex) {
        if (extRefIndex >= this._externSheetRecord.getNumOfRefs() || extRefIndex < 0) {
            return -1;
        }
        return this._externSheetRecord.getLastSheetIndexFromRefIndex(extRefIndex);
    }

    public void removeSheet(int sheetIdx) {
        this._externSheetRecord.removeSheet(sheetIdx);
    }

    public int checkExternSheet(int sheetIndex) {
        return this.checkExternSheet(sheetIndex, sheetIndex);
    }

    public int checkExternSheet(int firstSheetIndex, int lastSheetIndex) {
        int i;
        int thisWbIndex = -1;
        for (i = 0; i < this._externalBookBlocks.length; ++i) {
            SupBookRecord ebr = this._externalBookBlocks[i].getExternalBookRecord();
            if (!ebr.isInternalReferences()) continue;
            thisWbIndex = i;
            break;
        }
        if (thisWbIndex < 0) {
            throw new RuntimeException("Could not find 'internal references' EXTERNALBOOK");
        }
        i = this._externSheetRecord.getRefIxForSheet(thisWbIndex, firstSheetIndex, lastSheetIndex);
        if (i >= 0) {
            return i;
        }
        return this._externSheetRecord.addRef(thisWbIndex, firstSheetIndex, lastSheetIndex);
    }

    private int findFirstRecordLocBySid(short sid) {
        int index = 0;
        for (Record record : this._workbookRecordList.getRecords()) {
            if (record.getSid() == sid) {
                return index;
            }
            ++index;
        }
        return -1;
    }

    public String resolveNameXText(int refIndex, int definedNameIndex, InternalWorkbook workbook) {
        int extBookIndex = this._externSheetRecord.getExtbookIndexFromRefIndex(refIndex);
        int firstTabIndex = this._externSheetRecord.getFirstSheetIndexFromRefIndex(refIndex);
        if (firstTabIndex == -1) {
            throw new RuntimeException("Referenced sheet could not be found");
        }
        ExternalBookBlock externalBook = this._externalBookBlocks[extBookIndex];
        if (externalBook._externalNameRecords.length > definedNameIndex) {
            return this._externalBookBlocks[extBookIndex].getNameText(definedNameIndex);
        }
        if (firstTabIndex == -2) {
            NameRecord nr = this.getNameRecord(definedNameIndex);
            int sheetNumber = nr.getSheetNumber();
            StringBuilder text = new StringBuilder(64);
            if (sheetNumber > 0) {
                String sheetName = workbook.getSheetName(sheetNumber - 1);
                SheetNameFormatter.appendFormat(text, sheetName);
                text.append("!");
            }
            text.append(nr.getNameText());
            return text.toString();
        }
        throw new ArrayIndexOutOfBoundsException("Ext Book Index relative but beyond the supported length, was " + extBookIndex + " but maximum is " + this._externalBookBlocks.length);
    }

    public int resolveNameXIx(int refIndex, int definedNameIndex) {
        int extBookIndex = this._externSheetRecord.getExtbookIndexFromRefIndex(refIndex);
        return this._externalBookBlocks[extBookIndex].getNameIx(definedNameIndex);
    }

    public NameXPtg getNameXPtg(String name, int sheetRefIndex) {
        for (int i = 0; i < this._externalBookBlocks.length; ++i) {
            int thisSheetRefIndex;
            int definedNameIndex = this._externalBookBlocks[i].getIndexOfName(name);
            if (definedNameIndex < 0 || (thisSheetRefIndex = this.findRefIndexFromExtBookIndex(i)) < 0 || sheetRefIndex != -1 && thisSheetRefIndex != sheetRefIndex) continue;
            return new NameXPtg(thisSheetRefIndex, definedNameIndex);
        }
        return null;
    }

    public NameXPtg addNameXPtg(String name) {
        Record record;
        int extBlockIndex = -1;
        ExternalBookBlock extBlock = null;
        for (int i = 0; i < this._externalBookBlocks.length; ++i) {
            SupBookRecord ebr = this._externalBookBlocks[i].getExternalBookRecord();
            if (!ebr.isAddInFunctions()) continue;
            extBlock = this._externalBookBlocks[i];
            extBlockIndex = i;
            break;
        }
        if (extBlock == null) {
            extBlock = new ExternalBookBlock();
            extBlockIndex = this.extendExternalBookBlocks(extBlock);
            int idx = this.findFirstRecordLocBySid((short)23);
            this._workbookRecordList.add(idx, extBlock.getExternalBookRecord());
            this._externSheetRecord.addRef(this._externalBookBlocks.length - 1, -2, -2);
        }
        ExternalNameRecord extNameRecord = new ExternalNameRecord();
        extNameRecord.setText(name);
        extNameRecord.setParsedExpression(new Ptg[]{ErrPtg.REF_INVALID});
        int nameIndex = extBlock.addExternalName(extNameRecord);
        int supLinkIndex = 0;
        Iterator<Record> iterator = this._workbookRecordList.getRecords().iterator();
        while (!(!iterator.hasNext() || (record = iterator.next()) instanceof SupBookRecord && ((SupBookRecord)record).isAddInFunctions())) {
            ++supLinkIndex;
        }
        int numberOfNames = extBlock.getNumberOfNames();
        this._workbookRecordList.add(supLinkIndex + numberOfNames, extNameRecord);
        int fakeSheetIdx = -2;
        int ix = this._externSheetRecord.getRefIxForSheet(extBlockIndex, fakeSheetIdx, fakeSheetIdx);
        return new NameXPtg(ix, nameIndex);
    }

    private int extendExternalBookBlocks(ExternalBookBlock newBlock) {
        ExternalBookBlock[] tmp = new ExternalBookBlock[this._externalBookBlocks.length + 1];
        System.arraycopy(this._externalBookBlocks, 0, tmp, 0, this._externalBookBlocks.length);
        tmp[tmp.length - 1] = newBlock;
        this._externalBookBlocks = tmp;
        return this._externalBookBlocks.length - 1;
    }

    private int findRefIndexFromExtBookIndex(int extBookIndex) {
        return this._externSheetRecord.findRefIndexFromExtBookIndex(extBookIndex);
    }

    public boolean changeExternalReference(String oldUrl, String newUrl) {
        for (ExternalBookBlock ex : this._externalBookBlocks) {
            SupBookRecord externalRecord = ex.getExternalBookRecord();
            if (!externalRecord.isExternalReferences() || !externalRecord.getURL().equals(oldUrl)) continue;
            externalRecord.setURL(newUrl);
            return true;
        }
        return false;
    }

    private static final class ExternalBookBlock {
        private final SupBookRecord _externalBookRecord;
        private ExternalNameRecord[] _externalNameRecords;
        private final CRNBlock[] _crnBlocks;

        public ExternalBookBlock(RecordStream rs) {
            this._externalBookRecord = (SupBookRecord)rs.getNext();
            ArrayList<Object> temp = new ArrayList<Object>();
            while (rs.peekNextClass() == ExternalNameRecord.class) {
                temp.add(rs.getNext());
            }
            this._externalNameRecords = new ExternalNameRecord[temp.size()];
            temp.toArray(this._externalNameRecords);
            temp.clear();
            while (rs.peekNextClass() == CRNCountRecord.class) {
                temp.add(new CRNBlock(rs));
            }
            this._crnBlocks = new CRNBlock[temp.size()];
            temp.toArray(this._crnBlocks);
        }

        public ExternalBookBlock(String url, String[] sheetNames) {
            this._externalBookRecord = SupBookRecord.createExternalReferences(url, sheetNames);
            this._crnBlocks = new CRNBlock[0];
        }

        public ExternalBookBlock(int numberOfSheets) {
            this._externalBookRecord = SupBookRecord.createInternalReferences((short)numberOfSheets);
            this._externalNameRecords = new ExternalNameRecord[0];
            this._crnBlocks = new CRNBlock[0];
        }

        public ExternalBookBlock() {
            this._externalBookRecord = SupBookRecord.createAddInFunctions();
            this._externalNameRecords = new ExternalNameRecord[0];
            this._crnBlocks = new CRNBlock[0];
        }

        public SupBookRecord getExternalBookRecord() {
            return this._externalBookRecord;
        }

        public String getNameText(int definedNameIndex) {
            return this._externalNameRecords[definedNameIndex].getText();
        }

        public int getNameIx(int definedNameIndex) {
            return this._externalNameRecords[definedNameIndex].getIx();
        }

        public int getIndexOfName(String name) {
            for (int i = 0; i < this._externalNameRecords.length; ++i) {
                if (!this._externalNameRecords[i].getText().equalsIgnoreCase(name)) continue;
                return i;
            }
            return -1;
        }

        public int getNumberOfNames() {
            return this._externalNameRecords.length;
        }

        public int addExternalName(ExternalNameRecord rec) {
            ExternalNameRecord[] tmp = new ExternalNameRecord[this._externalNameRecords.length + 1];
            System.arraycopy(this._externalNameRecords, 0, tmp, 0, this._externalNameRecords.length);
            tmp[tmp.length - 1] = rec;
            this._externalNameRecords = tmp;
            return this._externalNameRecords.length - 1;
        }
    }

    private static final class CRNBlock {
        private final CRNCountRecord _countRecord;
        private final CRNRecord[] _crns;

        public CRNBlock(RecordStream rs) {
            this._countRecord = (CRNCountRecord)rs.getNext();
            int nCRNs = this._countRecord.getNumberOfCRNs();
            CRNRecord[] crns = new CRNRecord[nCRNs];
            for (int i = 0; i < crns.length; ++i) {
                crns[i] = (CRNRecord)rs.getNext();
            }
            this._crns = crns;
        }

        public CRNRecord[] getCrns() {
            return (CRNRecord[])this._crns.clone();
        }
    }
}

