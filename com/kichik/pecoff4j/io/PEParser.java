/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.kichik.pecoff4j.io;

import com.kichik.pecoff4j.AttributeCertificateTable;
import com.kichik.pecoff4j.BoundImport;
import com.kichik.pecoff4j.BoundImportDirectoryTable;
import com.kichik.pecoff4j.COFFHeader;
import com.kichik.pecoff4j.DOSHeader;
import com.kichik.pecoff4j.DOSStub;
import com.kichik.pecoff4j.DebugDirectory;
import com.kichik.pecoff4j.ExportDirectory;
import com.kichik.pecoff4j.ImageData;
import com.kichik.pecoff4j.ImageDataDirectory;
import com.kichik.pecoff4j.ImportDirectory;
import com.kichik.pecoff4j.ImportDirectoryEntry;
import com.kichik.pecoff4j.ImportDirectoryTable;
import com.kichik.pecoff4j.ImportEntry;
import com.kichik.pecoff4j.LoadConfigDirectory;
import com.kichik.pecoff4j.OptionalHeader;
import com.kichik.pecoff4j.PE;
import com.kichik.pecoff4j.PESignature;
import com.kichik.pecoff4j.RVAConverter;
import com.kichik.pecoff4j.ResourceDirectory;
import com.kichik.pecoff4j.ResourceDirectoryTable;
import com.kichik.pecoff4j.ResourceEntry;
import com.kichik.pecoff4j.SectionData;
import com.kichik.pecoff4j.SectionHeader;
import com.kichik.pecoff4j.SectionTable;
import com.kichik.pecoff4j.io.ByteArrayDataReader;
import com.kichik.pecoff4j.io.DataEntry;
import com.kichik.pecoff4j.io.DataReader;
import com.kichik.pecoff4j.io.IDataReader;
import com.kichik.pecoff4j.util.IntMap;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class PEParser {
    public static PE parse(InputStream is) throws IOException {
        try (DataReader dr = new DataReader(is);){
            PE pE = PEParser.read(dr);
            return pE;
        }
    }

    public static PE parse(String filename) throws IOException {
        return PEParser.parse(new File(filename));
    }

    /*
     * Exception decompiling
     */
    public static PE parse(File file) throws IOException {
        /*
         * This method has failed to decompile.  When submitting a bug report, please provide this stack trace, and (if you hold appropriate legal rights) the relevant class file.
         * 
         * org.benf.cfr.reader.util.ConfusedCFRException: Started 2 blocks at once
         *     at org.benf.cfr.reader.bytecode.analysis.opgraph.Op04StructuredStatement.getStartingBlocks(Op04StructuredStatement.java:412)
         *     at org.benf.cfr.reader.bytecode.analysis.opgraph.Op04StructuredStatement.buildNestedBlocks(Op04StructuredStatement.java:487)
         *     at org.benf.cfr.reader.bytecode.analysis.opgraph.Op03SimpleStatement.createInitialStructuredBlock(Op03SimpleStatement.java:736)
         *     at org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysisInner(CodeAnalyser.java:850)
         *     at org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysisOrWrapFail(CodeAnalyser.java:278)
         *     at org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysis(CodeAnalyser.java:201)
         *     at org.benf.cfr.reader.entities.attributes.AttributeCode.analyse(AttributeCode.java:94)
         *     at org.benf.cfr.reader.entities.Method.analyse(Method.java:538)
         *     at org.benf.cfr.reader.entities.ClassFile.analyseMid(ClassFile.java:1055)
         *     at org.benf.cfr.reader.entities.ClassFile.analyseTop(ClassFile.java:942)
         *     at org.benf.cfr.reader.Driver.doJarVersionTypes(Driver.java:257)
         *     at org.benf.cfr.reader.Driver.doJar(Driver.java:139)
         *     at org.benf.cfr.reader.CfrDriverImpl.analyse(CfrDriverImpl.java:76)
         *     at org.benf.cfr.reader.Main.main(Main.java:54)
         *     at async.DecompilerRunnable.cfrDecompilation(DecompilerRunnable.java:348)
         *     at async.DecompilerRunnable.call(DecompilerRunnable.java:309)
         *     at async.DecompilerRunnable.call(DecompilerRunnable.java:31)
         *     at java.util.concurrent.FutureTask.run(FutureTask.java:266)
         *     at java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1149)
         *     at java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:624)
         *     at java.lang.Thread.run(Thread.java:750)
         */
        throw new IllegalStateException("Decompilation failed");
    }

    public static PE read(IDataReader dr) throws IOException {
        PE pe = new PE();
        pe.setDosHeader(PEParser.readDos(dr));
        if (pe.getDosHeader().getAddressOfNewExeHeader() == 0 || pe.getDosHeader().getAddressOfNewExeHeader() > 8192) {
            return pe;
        }
        pe.setStub(PEParser.readStub(pe.getDosHeader(), dr));
        pe.setSignature(PEParser.readSignature(dr));
        if (!pe.getSignature().isValid()) {
            return pe;
        }
        pe.setCoffHeader(PEParser.readCOFF(dr));
        pe.setOptionalHeader(PEParser.readOptional(dr));
        pe.setSectionTable(PEParser.readSectionHeaders(pe, dr));
        pe.set64(pe.getOptionalHeader().isPE32plus());
        DataEntry entry = null;
        while ((entry = PEParser.findNextEntry(pe, dr.getPosition())) != null) {
            if (entry.isSection) {
                PEParser.readSection(pe, entry, dr);
                continue;
            }
            if (entry.isDebugRawData) {
                PEParser.readDebugRawData(pe, entry, dr);
                continue;
            }
            PEParser.readImageData(pe, entry, dr);
        }
        byte[] tb = dr.readAll();
        if (tb.length > 0) {
            pe.getImageData().setTrailingData(tb);
        }
        return pe;
    }

    public static DOSHeader readDos(IDataReader dr) throws IOException {
        DOSHeader dh = new DOSHeader();
        dh.setMagic(dr.readWord());
        dh.setUsedBytesInLastPage(dr.readWord());
        dh.setFileSizeInPages(dr.readWord());
        dh.setNumRelocationItems(dr.readWord());
        dh.setHeaderSizeInParagraphs(dr.readWord());
        dh.setMinExtraParagraphs(dr.readWord());
        dh.setMaxExtraParagraphs(dr.readWord());
        dh.setInitialSS(dr.readWord());
        dh.setInitialSP(dr.readWord());
        dh.setChecksum(dr.readWord());
        dh.setInitialIP(dr.readWord());
        dh.setInitialRelativeCS(dr.readWord());
        dh.setAddressOfRelocationTable(dr.readWord());
        dh.setOverlayNumber(dr.readWord());
        int[] reserved = new int[4];
        for (int i = 0; i < reserved.length; ++i) {
            reserved[i] = dr.readWord();
        }
        dh.setReserved(reserved);
        dh.setOemId(dr.readWord());
        dh.setOemInfo(dr.readWord());
        int[] reserved2 = new int[10];
        for (int i = 0; i < reserved2.length; ++i) {
            reserved2[i] = dr.readWord();
        }
        dh.setReserved2(reserved2);
        dh.setAddressOfNewExeHeader(dr.readDoubleWord());
        int stubSize = dh.getFileSizeInPages() * 512 - (512 - dh.getUsedBytesInLastPage());
        if (stubSize > dh.getAddressOfNewExeHeader()) {
            stubSize = dh.getAddressOfNewExeHeader();
        }
        dh.setStubSize(stubSize -= dh.getHeaderSizeInParagraphs() * 16);
        return dh;
    }

    public static DOSStub readStub(DOSHeader header, IDataReader dr) throws IOException {
        DOSStub ds = new DOSStub();
        int pos = dr.getPosition();
        int add = header.getAddressOfNewExeHeader();
        byte[] stub = new byte[add - pos];
        dr.read(stub);
        ds.setStub(stub);
        return ds;
    }

    public static PESignature readSignature(IDataReader dr) throws IOException {
        PESignature ps = new PESignature();
        byte[] signature = new byte[4];
        dr.read(signature);
        ps.setSignature(signature);
        return ps;
    }

    public static COFFHeader readCOFF(IDataReader dr) throws IOException {
        COFFHeader h = new COFFHeader();
        h.setMachine(dr.readWord());
        h.setNumberOfSections(dr.readWord());
        h.setTimeDateStamp(dr.readDoubleWord());
        h.setPointerToSymbolTable(dr.readDoubleWord());
        h.setNumberOfSymbols(dr.readDoubleWord());
        h.setSizeOfOptionalHeader(dr.readWord());
        h.setCharacteristics(dr.readWord());
        return h;
    }

    public static OptionalHeader readOptional(IDataReader dr) throws IOException {
        OptionalHeader oh = new OptionalHeader();
        oh.setMagic(dr.readWord());
        boolean is64 = oh.isPE32plus();
        oh.setMajorLinkerVersion(dr.readByte());
        oh.setMinorLinkerVersion(dr.readByte());
        oh.setSizeOfCode(dr.readDoubleWord());
        oh.setSizeOfInitializedData(dr.readDoubleWord());
        oh.setSizeOfUninitializedData(dr.readDoubleWord());
        oh.setAddressOfEntryPoint(dr.readDoubleWord());
        oh.setBaseOfCode(dr.readDoubleWord());
        if (!is64) {
            oh.setBaseOfData(dr.readDoubleWord());
        }
        oh.setImageBase(is64 ? dr.readLong() : (long)dr.readDoubleWord());
        oh.setSectionAlignment(dr.readDoubleWord());
        oh.setFileAlignment(dr.readDoubleWord());
        oh.setMajorOperatingSystemVersion(dr.readWord());
        oh.setMinorOperatingSystemVersion(dr.readWord());
        oh.setMajorImageVersion(dr.readWord());
        oh.setMinorImageVersion(dr.readWord());
        oh.setMajorSubsystemVersion(dr.readWord());
        oh.setMinorSubsystemVersion(dr.readWord());
        oh.setWin32VersionValue(dr.readDoubleWord());
        oh.setSizeOfImage(dr.readDoubleWord());
        oh.setSizeOfHeaders(dr.readDoubleWord());
        oh.setCheckSum(dr.readDoubleWord());
        oh.setSubsystem(dr.readWord());
        oh.setDllCharacteristics(dr.readWord());
        oh.setSizeOfStackReserve(is64 ? dr.readLong() : (long)dr.readDoubleWord());
        oh.setSizeOfStackCommit(is64 ? dr.readLong() : (long)dr.readDoubleWord());
        oh.setSizeOfHeapReserve(is64 ? dr.readLong() : (long)dr.readDoubleWord());
        oh.setSizeOfHeapCommit(is64 ? dr.readLong() : (long)dr.readDoubleWord());
        oh.setLoaderFlags(dr.readDoubleWord());
        oh.setNumberOfRvaAndSizes(dr.readDoubleWord());
        ImageDataDirectory[] dds = new ImageDataDirectory[16];
        for (int i = 0; i < dds.length; ++i) {
            dds[i] = PEParser.readImageDD(dr);
        }
        oh.setDataDirectories(dds);
        return oh;
    }

    public static ImageDataDirectory readImageDD(IDataReader dr) throws IOException {
        ImageDataDirectory idd = new ImageDataDirectory();
        idd.setVirtualAddress(dr.readDoubleWord());
        idd.setSize(dr.readDoubleWord());
        return idd;
    }

    public static SectionTable readSectionHeaders(PE pe, IDataReader dr) throws IOException {
        SectionTable st = new SectionTable();
        int ns = pe.getCoffHeader().getNumberOfSections();
        for (int i = 0; i < ns; ++i) {
            st.add(PEParser.readSectionHeader(dr));
        }
        SectionHeader[] sorted = st.getHeadersPointerSorted();
        int[] virtualAddress = new int[sorted.length];
        int[] pointerToRawData = new int[sorted.length];
        for (int i = 0; i < sorted.length; ++i) {
            virtualAddress[i] = sorted[i].getVirtualAddress();
            pointerToRawData[i] = sorted[i].getPointerToRawData();
        }
        st.setRvaConverter(new RVAConverter(virtualAddress, pointerToRawData));
        return st;
    }

    public static SectionHeader readSectionHeader(IDataReader dr) throws IOException {
        SectionHeader sh = new SectionHeader();
        sh.setName(dr.readUtf(8));
        sh.setVirtualSize(dr.readDoubleWord());
        sh.setVirtualAddress(dr.readDoubleWord());
        sh.setSizeOfRawData(dr.readDoubleWord());
        sh.setPointerToRawData(dr.readDoubleWord());
        sh.setPointerToRelocations(dr.readDoubleWord());
        sh.setPointerToLineNumbers(dr.readDoubleWord());
        sh.setNumberOfRelocations(dr.readWord());
        sh.setNumberOfLineNumbers(dr.readWord());
        sh.setCharacteristics(dr.readDoubleWord());
        return sh;
    }

    public static DataEntry findNextEntry(PE pe, int pos) {
        int prd;
        DataEntry de = new DataEntry();
        int ns = pe.getCoffHeader().getNumberOfSections();
        for (int i = 0; i < ns; ++i) {
            SectionHeader sh = pe.getSectionTable().getHeader(i);
            if (sh.getSizeOfRawData() <= 0 || sh.getPointerToRawData() < pos || de.pointer != 0 && sh.getPointerToRawData() >= de.pointer) continue;
            de.pointer = sh.getPointerToRawData();
            de.index = i;
            de.isSection = true;
        }
        RVAConverter rvc = pe.getSectionTable().getRVAConverter();
        int dc = pe.getOptionalHeader().getDataDirectoryCount();
        for (int i = 0; i < dc; ++i) {
            ImageDataDirectory idd = pe.getOptionalHeader().getDataDirectory(i);
            if (idd.getSize() <= 0) continue;
            prd = idd.getVirtualAddress();
            if (i != 4 && PEParser.isInsideSection(pe, idd)) {
                prd = rvc.convertVirtualAddressToRawDataPointer(idd.getVirtualAddress());
            }
            if (prd < pos || de.pointer != 0 && prd >= de.pointer) continue;
            de.pointer = prd;
            de.index = i;
            de.isSection = false;
        }
        ImageData id = pe.getImageData();
        DebugDirectory dd = null;
        if (id != null) {
            dd = id.getDebug();
        }
        if (dd != null && (prd = dd.getPointerToRawData()) >= pos && (de.pointer == 0 || prd < de.pointer)) {
            de.pointer = prd;
            de.index = -1;
            de.isDebugRawData = true;
            de.isSection = false;
            de.baseAddress = prd;
        }
        if (de.pointer == 0) {
            return null;
        }
        return de;
    }

    private static boolean isInsideSection(PE pe, ImageDataDirectory idd) {
        int prd = idd.getVirtualAddress();
        int pex = prd + idd.getSize();
        SectionTable st = pe.getSectionTable();
        int ns = st.getNumberOfSections();
        for (int i = 0; i < ns; ++i) {
            SectionHeader sh = st.getHeader(i);
            int vad = sh.getVirtualAddress();
            int vex = vad + sh.getVirtualSize();
            if (prd < vad || prd >= vex || pex > vex) continue;
            return true;
        }
        return false;
    }

    private static void readImageData(PE pe, DataEntry entry, IDataReader dr) throws IOException {
        ImageData id = pe.getImageData();
        byte[] pa = PEParser.readPreambleData(entry.pointer, dr);
        if (pa != null) {
            id.put(entry.index, pa);
        }
        ImageDataDirectory idd = pe.getOptionalHeader().getDataDirectory(entry.index);
        byte[] b = new byte[idd.getSize()];
        dr.read(b);
        switch (entry.index) {
            case 0: {
                id.setExportTable(PEParser.readExportDirectory(b));
                break;
            }
            case 1: {
                id.setImportTable(PEParser.readImportDirectory(b, entry.baseAddress));
                break;
            }
            case 2: {
                id.setResourceTable(PEParser.readResourceDirectory(b, entry.baseAddress));
                break;
            }
            case 3: {
                id.setExceptionTable(b);
                break;
            }
            case 4: {
                id.setCertificateTable(PEParser.readAttributeCertificateTable(b));
                break;
            }
            case 5: {
                id.setBaseRelocationTable(b);
                break;
            }
            case 6: {
                id.setDebug(PEParser.readDebugDirectory(b));
                break;
            }
            case 7: {
                id.setArchitecture(b);
                break;
            }
            case 8: {
                id.setGlobalPtr(b);
                break;
            }
            case 9: {
                id.setTlsTable(b);
                break;
            }
            case 10: {
                id.setLoadConfigTable(PEParser.readLoadConfigDirectory(pe, b));
                break;
            }
            case 11: {
                id.setBoundImports(PEParser.readBoundImportDirectoryTable(b));
                break;
            }
            case 12: {
                id.setIat(b);
                break;
            }
            case 13: {
                id.setDelayImportDescriptor(b);
                break;
            }
            case 14: {
                id.setClrRuntimeHeader(b);
                break;
            }
            case 15: {
                id.setReserved(b);
            }
        }
    }

    private static byte[] readPreambleData(int pointer, IDataReader dr) throws IOException {
        if (pointer > dr.getPosition()) {
            byte[] pa = new byte[pointer - dr.getPosition()];
            dr.read(pa);
            boolean zeroes = true;
            for (int i = 0; i < pa.length; ++i) {
                if (pa[i] == 0) continue;
                zeroes = false;
                break;
            }
            if (!zeroes) {
                return pa;
            }
        }
        return null;
    }

    private static void readDebugRawData(PE pe, DataEntry entry, IDataReader dr) throws IOException {
        ImageData id = pe.getImageData();
        byte[] pa = PEParser.readPreambleData(entry.pointer, dr);
        if (pa != null) {
            id.setDebugRawDataPreamble(pa);
        }
        DebugDirectory dd = id.getDebug();
        byte[] b = new byte[dd.getSizeOfData()];
        dr.read(b);
        id.setDebugRawData(b);
    }

    private static void readSection(PE pe, DataEntry entry, IDataReader dr) throws IOException {
        SectionTable st = pe.getSectionTable();
        SectionHeader sh = st.getHeader(entry.index);
        SectionData sd = new SectionData();
        byte[] pa = PEParser.readPreambleData(sh.getPointerToRawData(), dr);
        if (pa != null) {
            sd.setPreamble(pa);
        }
        dr.jumpTo(sh.getPointerToRawData());
        byte[] b = new byte[sh.getSizeOfRawData()];
        dr.read(b);
        sd.setData(b);
        st.put(entry.index, sd);
        int ddc = pe.getOptionalHeader().getDataDirectoryCount();
        for (int i = 0; i < ddc; ++i) {
            ImageDataDirectory idd;
            if (i == 4 || (idd = pe.getOptionalHeader().getDataDirectory(i)).getSize() <= 0) continue;
            int vad = sh.getVirtualAddress();
            int vex = vad + sh.getVirtualSize();
            int dad = idd.getVirtualAddress();
            if (dad < vad || dad >= vex) continue;
            int off = dad - vad;
            ByteArrayDataReader idr = new ByteArrayDataReader(b, off, idd.getSize());
            DataEntry de = new DataEntry(i, 0);
            de.baseAddress = sh.getVirtualAddress();
            PEParser.readImageData(pe, de, idr);
        }
    }

    private static BoundImportDirectoryTable readBoundImportDirectoryTable(byte[] b) throws IOException {
        DataReader dr = new DataReader(b);
        BoundImportDirectoryTable bidt = new BoundImportDirectoryTable();
        ArrayList<BoundImport> imports = new ArrayList<BoundImport>();
        BoundImport bi = null;
        while ((bi = PEParser.readBoundImport(dr)) != null) {
            bidt.add(bi);
            imports.add(bi);
        }
        Collections.sort(imports, new Comparator<BoundImport>(){

            @Override
            public int compare(BoundImport o1, BoundImport o2) {
                return o1.getOffsetToModuleName() - o2.getOffsetToModuleName();
            }
        });
        IntMap names = new IntMap();
        for (int i = 0; i < imports.size(); ++i) {
            bi = (BoundImport)imports.get(i);
            int offset = bi.getOffsetToModuleName();
            String n = (String)names.get(offset);
            if (n == null) {
                dr.jumpTo(offset);
                n = dr.readUtf();
                names.put(offset, n);
            }
            bi.setModuleName(n);
        }
        return bidt;
    }

    private static BoundImport readBoundImport(IDataReader dr) throws IOException {
        BoundImport bi = new BoundImport();
        bi.setTimestamp(dr.readDoubleWord());
        bi.setOffsetToModuleName(dr.readWord());
        bi.setNumberOfModuleForwarderRefs(dr.readWord());
        if (bi.getTimestamp() == 0L && bi.getOffsetToModuleName() == 0 && bi.getNumberOfModuleForwarderRefs() == 0) {
            return null;
        }
        return bi;
    }

    public static ImportDirectory readImportDirectory(byte[] b, int baseAddress) throws IOException {
        DataReader dr = new DataReader(b);
        ImportDirectory id = new ImportDirectory();
        ImportDirectoryEntry ide = null;
        while ((ide = PEParser.readImportDirectoryEntry(dr)) != null) {
            id.add(ide);
        }
        return id;
    }

    public static ImportDirectoryEntry readImportDirectoryEntry(IDataReader dr) throws IOException {
        ImportDirectoryEntry id = new ImportDirectoryEntry();
        id.setImportLookupTableRVA(dr.readDoubleWord());
        id.setTimeDateStamp(dr.readDoubleWord());
        id.setForwarderChain(dr.readDoubleWord());
        id.setNameRVA(dr.readDoubleWord());
        id.setImportAddressTableRVA(dr.readDoubleWord());
        if (id.getImportLookupTableRVA() == 0) {
            return null;
        }
        return id;
    }

    public static ImportDirectoryTable readImportDirectoryTable(IDataReader dr, int baseAddress) throws IOException {
        ImportDirectoryTable idt = new ImportDirectoryTable();
        ImportEntry ie = null;
        while ((ie = PEParser.readImportEntry(dr)) != null) {
            idt.add(ie);
        }
        for (int i = 0; i < idt.size(); ++i) {
            ImportEntry iee = idt.getEntry(i);
            if ((iee.getVal() & Integer.MIN_VALUE) != 0) {
                iee.setOrdinal(iee.getVal() & Integer.MAX_VALUE);
                continue;
            }
            dr.jumpTo(iee.getVal() - baseAddress);
            dr.readWord();
            iee.setName(dr.readUtf());
        }
        return idt;
    }

    public static ImportEntry readImportEntry(IDataReader dr) throws IOException {
        ImportEntry ie = new ImportEntry();
        ie.setVal(dr.readDoubleWord());
        if (ie.getVal() == 0) {
            return null;
        }
        return ie;
    }

    public static ExportDirectory readExportDirectory(byte[] b) throws IOException {
        DataReader dr = new DataReader(b);
        ExportDirectory edt = new ExportDirectory();
        edt.set(b);
        edt.setExportFlags(dr.readDoubleWord());
        edt.setTimeDateStamp(dr.readDoubleWord());
        edt.setMajorVersion(dr.readWord());
        edt.setMinorVersion(dr.readWord());
        edt.setNameRVA(dr.readDoubleWord());
        edt.setOrdinalBase(dr.readDoubleWord());
        edt.setAddressTableEntries(dr.readDoubleWord());
        edt.setNumberOfNamePointers(dr.readDoubleWord());
        edt.setExportAddressTableRVA(dr.readDoubleWord());
        edt.setNamePointerRVA(dr.readDoubleWord());
        edt.setOrdinalTableRVA(dr.readDoubleWord());
        return edt;
    }

    public static LoadConfigDirectory readLoadConfigDirectory(PE pe, byte[] b) throws IOException {
        DataReader dr = new DataReader(b);
        LoadConfigDirectory lcd = new LoadConfigDirectory();
        lcd.set(b);
        lcd.setSize(dr.readDoubleWord());
        lcd.setTimeDateStamp(dr.readDoubleWord());
        lcd.setMajorVersion(dr.readWord());
        lcd.setMinorVersion(dr.readWord());
        lcd.setGlobalFlagsClear(dr.readDoubleWord());
        lcd.setGlobalFlagsSet(dr.readDoubleWord());
        lcd.setCriticalSectionDefaultTimeout(dr.readDoubleWord());
        lcd.setDeCommitFreeBlockThreshold(pe.is64() ? dr.readLong() : (long)dr.readDoubleWord());
        lcd.setDeCommitTotalFreeThreshold(pe.is64() ? dr.readLong() : (long)dr.readDoubleWord());
        lcd.setLockPrefixTable(pe.is64() ? dr.readLong() : (long)dr.readDoubleWord());
        lcd.setMaximumAllocationSize(pe.is64() ? dr.readLong() : (long)dr.readDoubleWord());
        lcd.setVirtualMemoryThreshold(pe.is64() ? dr.readLong() : (long)dr.readDoubleWord());
        lcd.setProcessAffinityMask(pe.is64() ? dr.readLong() : (long)dr.readDoubleWord());
        lcd.setProcessHeapFlags(dr.readDoubleWord());
        lcd.setCsdVersion(dr.readWord());
        lcd.setReserved(dr.readWord());
        lcd.setEditList(pe.is64() ? dr.readLong() : (long)dr.readDoubleWord());
        if (dr.hasMore()) {
            lcd.setSecurityCookie(pe.is64() ? dr.readLong() : (long)dr.readDoubleWord());
        }
        if (dr.hasMore()) {
            lcd.setSeHandlerTable(pe.is64() ? dr.readLong() : (long)dr.readDoubleWord());
        }
        if (dr.hasMore()) {
            lcd.setSeHandlerCount(pe.is64() ? dr.readLong() : (long)dr.readDoubleWord());
        }
        return lcd;
    }

    public static DebugDirectory readDebugDirectory(byte[] b) throws IOException {
        return PEParser.readDebugDirectory(b, new DataReader(b));
    }

    public static DebugDirectory readDebugDirectory(byte[] b, IDataReader dr) throws IOException {
        DebugDirectory dd = new DebugDirectory();
        dd.set(b);
        dd.setCharacteristics(dr.readDoubleWord());
        dd.setTimeDateStamp(dr.readDoubleWord());
        dd.setMajorVersion(dr.readWord());
        dd.setMajorVersion(dr.readWord());
        dd.setType(dr.readDoubleWord());
        dd.setSizeOfData(dr.readDoubleWord());
        dd.setAddressOfRawData(dr.readDoubleWord());
        dd.setPointerToRawData(dr.readDoubleWord());
        return dd;
    }

    private static ResourceDirectory readResourceDirectory(byte[] b, int baseAddress) throws IOException {
        ByteArrayDataReader dr = new ByteArrayDataReader(b);
        return PEParser.readResourceDirectory(dr, baseAddress);
    }

    private static ResourceDirectory readResourceDirectory(IDataReader dr, int baseAddress) throws IOException {
        ResourceDirectory d = new ResourceDirectory();
        d.setTable(PEParser.readResourceDirectoryTable(dr));
        int ne = d.getTable().getNumNameEntries() + d.getTable().getNumIdEntries();
        for (int i = 0; i < ne; ++i) {
            d.add(PEParser.readResourceEntry(dr, baseAddress));
        }
        return d;
    }

    private static ResourceEntry readResourceEntry(IDataReader dr, int baseAddress) throws IOException {
        ResourceEntry re = new ResourceEntry();
        int id = dr.readDoubleWord();
        int offset = dr.readDoubleWord();
        re.setOffset(offset);
        int pos = dr.getPosition();
        if ((id & Integer.MIN_VALUE) != 0) {
            dr.jumpTo(id & Integer.MAX_VALUE);
            re.setName(dr.readUnicode(dr.readWord()));
        } else {
            re.setId(id);
        }
        if ((offset & Integer.MIN_VALUE) != 0) {
            dr.jumpTo(offset & Integer.MAX_VALUE);
            re.setDirectory(PEParser.readResourceDirectory(dr, baseAddress));
        } else {
            dr.jumpTo(offset);
            int rva = dr.readDoubleWord();
            int size = dr.readDoubleWord();
            int cp = dr.readDoubleWord();
            int res = dr.readDoubleWord();
            re.setDataRVA(rva);
            re.setCodePage(cp);
            re.setReserved(res);
            dr.jumpTo(rva - baseAddress);
            byte[] b = new byte[size];
            dr.read(b);
            re.setData(b);
        }
        dr.jumpTo(pos);
        return re;
    }

    private static ResourceDirectoryTable readResourceDirectoryTable(IDataReader dr) throws IOException {
        ResourceDirectoryTable t = new ResourceDirectoryTable();
        t.setCharacteristics(dr.readDoubleWord());
        t.setTimeDateStamp(dr.readDoubleWord());
        t.setMajorVersion(dr.readWord());
        t.setMinVersion(dr.readWord());
        t.setNumNameEntries(dr.readWord());
        t.setNumIdEntries(dr.readWord());
        return t;
    }

    public static AttributeCertificateTable readAttributeCertificateTable(byte[] b) throws IOException {
        return PEParser.readAttributeCertificateTable(b, new DataReader(b));
    }

    public static AttributeCertificateTable readAttributeCertificateTable(byte[] b, IDataReader dr) throws IOException {
        AttributeCertificateTable dd = new AttributeCertificateTable();
        dd.set(b);
        dd.setLength(dr.readDoubleWord());
        dd.setRevision(dr.readWord());
        dd.setCertificateType(dr.readWord());
        byte[] certificate = new byte[dd.getLength() - 8];
        dr.read(certificate);
        dd.setCertificate(certificate);
        return dd;
    }
}

