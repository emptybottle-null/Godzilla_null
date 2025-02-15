/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.kichik.pecoff4j.io;

import com.kichik.pecoff4j.BoundImport;
import com.kichik.pecoff4j.BoundImportDirectoryTable;
import com.kichik.pecoff4j.COFFHeader;
import com.kichik.pecoff4j.DOSHeader;
import com.kichik.pecoff4j.DOSStub;
import com.kichik.pecoff4j.ImageData;
import com.kichik.pecoff4j.ImageDataDirectory;
import com.kichik.pecoff4j.OptionalHeader;
import com.kichik.pecoff4j.PE;
import com.kichik.pecoff4j.PESignature;
import com.kichik.pecoff4j.RVAConverter;
import com.kichik.pecoff4j.SectionData;
import com.kichik.pecoff4j.SectionHeader;
import com.kichik.pecoff4j.SectionTable;
import com.kichik.pecoff4j.io.DataEntry;
import com.kichik.pecoff4j.io.DataWriter;
import com.kichik.pecoff4j.io.IDataWriter;
import com.kichik.pecoff4j.io.PEParser;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;

public class PEAssembler {
    public static byte[] toBytes(PE pe) throws IOException {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        PEAssembler.write(pe, bos);
        return bos.toByteArray();
    }

    public static void write(PE pe, String filename) throws IOException {
        PEAssembler.write(pe, new FileOutputStream(filename));
    }

    public static void write(PE pe, File file) throws IOException {
        PEAssembler.write(pe, new FileOutputStream(file));
    }

    public static void write(PE pe, OutputStream os) throws IOException {
        DataWriter dw = new DataWriter(os);
        PEAssembler.write(pe, (IDataWriter)dw);
        dw.flush();
    }

    public static void write(PE pe, IDataWriter dw) throws IOException {
        PEAssembler.write(pe.getDosHeader(), dw);
        PEAssembler.write(pe.getStub(), dw);
        PEAssembler.write(pe.getSignature(), dw);
        PEAssembler.write(pe.getCoffHeader(), dw);
        PEAssembler.write(pe.getOptionalHeader(), dw);
        PEAssembler.writeSectionHeaders(pe, dw);
        DataEntry entry = null;
        while ((entry = PEParser.findNextEntry(pe, dw.getPosition())) != null) {
            if (entry.isSection) {
                PEAssembler.writeSection(pe, entry, dw);
                continue;
            }
            if (entry.isDebugRawData) {
                PEAssembler.writeDebugRawData(pe, entry, dw);
                continue;
            }
            PEAssembler.writeImageData(pe, entry, dw);
        }
        byte[] tb = pe.getImageData().getTrailingData();
        if (tb != null) {
            dw.writeBytes(tb);
        }
    }

    private static void write(DOSHeader dh, IDataWriter dw) throws IOException {
        dw.writeWord(dh.getMagic());
        dw.writeWord(dh.getUsedBytesInLastPage());
        dw.writeWord(dh.getFileSizeInPages());
        dw.writeWord(dh.getNumRelocationItems());
        dw.writeWord(dh.getHeaderSizeInParagraphs());
        dw.writeWord(dh.getMinExtraParagraphs());
        dw.writeWord(dh.getMaxExtraParagraphs());
        dw.writeWord(dh.getInitialSS());
        dw.writeWord(dh.getInitialSP());
        dw.writeWord(dh.getChecksum());
        dw.writeWord(dh.getInitialIP());
        dw.writeWord(dh.getInitialRelativeCS());
        dw.writeWord(dh.getAddressOfRelocationTable());
        dw.writeWord(dh.getOverlayNumber());
        int[] res = dh.getReserved();
        for (int i = 0; i < res.length; ++i) {
            dw.writeWord(res[i]);
        }
        dw.writeWord(dh.getOemId());
        dw.writeWord(dh.getOemInfo());
        int[] res2 = dh.getReserved2();
        for (int i = 0; i < res2.length; ++i) {
            dw.writeWord(res2[i]);
        }
        dw.writeDoubleWord(dh.getAddressOfNewExeHeader());
    }

    private static void write(DOSStub stub, IDataWriter dw) throws IOException {
        dw.writeBytes(stub.getStub());
    }

    private static void write(PESignature s, IDataWriter dw) throws IOException {
        dw.writeBytes(s.getSignature());
    }

    private static void write(COFFHeader ch, IDataWriter dw) throws IOException {
        dw.writeWord(ch.getMachine());
        dw.writeWord(ch.getNumberOfSections());
        dw.writeDoubleWord(ch.getTimeDateStamp());
        dw.writeDoubleWord(ch.getPointerToSymbolTable());
        dw.writeDoubleWord(ch.getNumberOfSymbols());
        dw.writeWord(ch.getSizeOfOptionalHeader());
        dw.writeWord(ch.getCharacteristics());
    }

    private static void write(OptionalHeader oh, IDataWriter dw) throws IOException {
        boolean is64 = oh.isPE32plus();
        dw.writeWord(oh.getMagic());
        dw.writeByte(oh.getMajorLinkerVersion());
        dw.writeByte(oh.getMinorLinkerVersion());
        dw.writeDoubleWord(oh.getSizeOfCode());
        dw.writeDoubleWord(oh.getSizeOfInitializedData());
        dw.writeDoubleWord(oh.getSizeOfUninitializedData());
        dw.writeDoubleWord(oh.getAddressOfEntryPoint());
        dw.writeDoubleWord(oh.getBaseOfCode());
        if (!is64) {
            dw.writeDoubleWord(oh.getBaseOfData());
        }
        if (is64) {
            dw.writeLong(oh.getImageBase());
        } else {
            dw.writeDoubleWord((int)oh.getImageBase());
        }
        dw.writeDoubleWord(oh.getSectionAlignment());
        dw.writeDoubleWord(oh.getFileAlignment());
        dw.writeWord(oh.getMajorOperatingSystemVersion());
        dw.writeWord(oh.getMinorOperatingSystemVersion());
        dw.writeWord(oh.getMajorImageVersion());
        dw.writeWord(oh.getMinorImageVersion());
        dw.writeWord(oh.getMajorSubsystemVersion());
        dw.writeWord(oh.getMinorSubsystemVersion());
        dw.writeDoubleWord(oh.getWin32VersionValue());
        dw.writeDoubleWord(oh.getSizeOfImage());
        dw.writeDoubleWord(oh.getSizeOfHeaders());
        dw.writeDoubleWord(oh.getCheckSum());
        dw.writeWord(oh.getSubsystem());
        dw.writeWord(oh.getDllCharacteristics());
        if (is64) {
            dw.writeLong(oh.getSizeOfStackReserve());
            dw.writeLong(oh.getSizeOfStackCommit());
            dw.writeLong(oh.getSizeOfHeapReserve());
            dw.writeLong(oh.getSizeOfHeapCommit());
        } else {
            dw.writeDoubleWord((int)oh.getSizeOfStackReserve());
            dw.writeDoubleWord((int)oh.getSizeOfStackCommit());
            dw.writeDoubleWord((int)oh.getSizeOfHeapReserve());
            dw.writeDoubleWord((int)oh.getSizeOfHeapCommit());
        }
        dw.writeDoubleWord(oh.getLoaderFlags());
        dw.writeDoubleWord(oh.getNumberOfRvaAndSizes());
        int ddc = oh.getDataDirectoryCount();
        for (int i = 0; i < ddc; ++i) {
            PEAssembler.write(oh.getDataDirectory(i), dw);
        }
    }

    private static void write(ImageDataDirectory idd, IDataWriter dw) throws IOException {
        dw.writeDoubleWord(idd.getVirtualAddress());
        dw.writeDoubleWord(idd.getSize());
    }

    private static void writeSectionHeaders(PE pe, IDataWriter dw) throws IOException {
        SectionTable st = pe.getSectionTable();
        int ns = st.getNumberOfSections();
        for (int i = 0; i < ns; ++i) {
            SectionHeader sh = st.getHeader(i);
            PEAssembler.writeSectionHeader(sh, dw);
        }
    }

    private static void writeSectionHeader(SectionHeader sh, IDataWriter dw) throws IOException {
        dw.writeUtf(sh.getName(), 8);
        dw.writeDoubleWord(sh.getVirtualSize());
        dw.writeDoubleWord(sh.getVirtualAddress());
        dw.writeDoubleWord(sh.getSizeOfRawData());
        dw.writeDoubleWord(sh.getPointerToRawData());
        dw.writeDoubleWord(sh.getPointerToRelocations());
        dw.writeDoubleWord(sh.getPointerToLineNumbers());
        dw.writeWord(sh.getNumberOfRelocations());
        dw.writeWord(sh.getNumberOfLineNumbers());
        dw.writeDoubleWord(sh.getCharacteristics());
    }

    private static void writeImageData(PE pe, DataEntry entry, IDataWriter dw) throws IOException {
        ImageDataDirectory idd = pe.getOptionalHeader().getDataDirectory(entry.index);
        RVAConverter rvc = pe.getSectionTable().getRVAConverter();
        int prd = idd.getVirtualAddress();
        if (entry.index != 4) {
            prd = rvc.convertVirtualAddressToRawDataPointer(idd.getVirtualAddress());
        }
        if (prd > dw.getPosition()) {
            byte[] pa = pe.getImageData().getPreamble(entry.index);
            if (pa != null) {
                dw.writeBytes(pa);
            } else {
                dw.writeByte(0, prd - dw.getPosition());
            }
        }
        ImageData id = pe.getImageData();
        switch (entry.index) {
            case 0: {
                dw.writeBytes(id.getExportTable().get());
                break;
            }
            case 1: {
                dw.writeBytes(id.getImportTable().get());
                break;
            }
            case 2: {
                dw.writeBytes(id.getResourceTable().get());
                break;
            }
            case 3: {
                dw.writeBytes(id.getExceptionTable());
                break;
            }
            case 4: {
                dw.writeBytes(id.getCertificateTable().get());
                break;
            }
            case 5: {
                dw.writeBytes(id.getBaseRelocationTable());
                break;
            }
            case 6: {
                dw.writeBytes(id.getDebug().get());
                break;
            }
            case 7: {
                dw.writeBytes(id.getArchitecture());
                break;
            }
            case 8: {
                dw.writeBytes(id.getGlobalPtr());
                break;
            }
            case 9: {
                dw.writeBytes(id.getTlsTable());
                break;
            }
            case 10: {
                break;
            }
            case 11: {
                PEAssembler.write(pe, id.getBoundImports(), dw);
                break;
            }
            case 12: {
                dw.writeBytes(id.getIat());
                break;
            }
            case 13: {
                dw.writeBytes(id.getDelayImportDescriptor());
                break;
            }
            case 14: {
                dw.writeBytes(id.getClrRuntimeHeader());
                break;
            }
            case 15: {
                dw.writeBytes(id.getReserved());
            }
        }
    }

    private static void writeDebugRawData(PE pe, DataEntry entry, IDataWriter dw) throws IOException {
        if (entry.pointer > dw.getPosition()) {
            byte[] pa = pe.getImageData().getDebugRawDataPreamble();
            if (pa != null) {
                dw.writeBytes(pa);
            } else {
                dw.writeByte(0, entry.pointer - dw.getPosition());
            }
        }
        dw.writeBytes(pe.getImageData().getDebugRawData());
    }

    private static void writeSection(PE pe, DataEntry entry, IDataWriter dw) throws IOException {
        SectionTable st = pe.getSectionTable();
        SectionHeader sh = st.getHeader(entry.index);
        SectionData sd = st.getSection(entry.index);
        int prd = sh.getPointerToRawData();
        if (prd > dw.getPosition()) {
            byte[] pa = sd.getPreamble();
            if (pa != null) {
                dw.writeBytes(pa);
            } else {
                dw.writeByte(0, prd - dw.getPosition());
            }
        }
        byte[] b = sd.getData();
        dw.writeBytes(b);
    }

    private static void write(PE pe, BoundImportDirectoryTable bidt, IDataWriter dw) throws IOException {
        int pos = dw.getPosition();
        ArrayList<BoundImport> bil = new ArrayList<BoundImport>();
        for (int i = 0; i < bidt.size(); ++i) {
            BoundImport bi = bidt.get(i);
            bil.add(bi);
            dw.writeDoubleWord((int)bi.getTimestamp());
            dw.writeWord(bi.getOffsetToModuleName());
            dw.writeWord(bi.getNumberOfModuleForwarderRefs());
        }
        Collections.sort(bil, new Comparator<BoundImport>(){

            @Override
            public int compare(BoundImport o1, BoundImport o2) {
                return o1.getOffsetToModuleName() - o2.getOffsetToModuleName();
            }
        });
        dw.writeDoubleWord(0);
        dw.writeDoubleWord(0);
        HashSet<String> names = new HashSet<String>();
        for (int i = 0; i < bil.size(); ++i) {
            String s = ((BoundImport)bil.get(i)).getModuleName();
            if (!names.contains(s)) {
                dw.writeUtf(s);
            }
            names.add(s);
        }
        int dpos = dw.getPosition() - pos;
        int bis = pe.getOptionalHeader().getDataDirectory(11).getSize();
        if (bis > dpos) {
            dw.writeByte(0, bis - dpos);
        }
    }
}

