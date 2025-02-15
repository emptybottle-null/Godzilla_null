/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.poifs.macros;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import org.apache.poi.poifs.filesystem.DirectoryNode;
import org.apache.poi.poifs.filesystem.DocumentInputStream;
import org.apache.poi.poifs.filesystem.DocumentNode;
import org.apache.poi.poifs.filesystem.Entry;
import org.apache.poi.poifs.filesystem.FileMagic;
import org.apache.poi.poifs.filesystem.OfficeXmlFileException;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.poifs.macros.Module;
import org.apache.poi.util.CodePageUtil;
import org.apache.poi.util.HexDump;
import org.apache.poi.util.IOUtils;
import org.apache.poi.util.LittleEndian;
import org.apache.poi.util.POILogFactory;
import org.apache.poi.util.POILogger;
import org.apache.poi.util.RLEDecompressingInputStream;
import org.apache.poi.util.StringUtil;

public class VBAMacroReader
implements Closeable {
    private static final POILogger LOGGER = POILogFactory.getLogger(VBAMacroReader.class);
    private static final int MAX_STRING_LENGTH = 20000;
    protected static final String VBA_PROJECT_OOXML = "vbaProject.bin";
    protected static final String VBA_PROJECT_POIFS = "VBA";
    private POIFSFileSystem fs;
    private static final int STREAMNAME_RESERVED = 50;
    private static final int PROJECT_CONSTANTS_RESERVED = 60;
    private static final int HELP_FILE_PATH_RESERVED = 61;
    private static final int REFERENCE_NAME_RESERVED = 62;
    private static final int DOC_STRING_RESERVED = 64;
    private static final int MODULE_DOCSTRING_RESERVED = 72;

    public VBAMacroReader(InputStream rstream) throws IOException {
        InputStream is = FileMagic.prepareToCheckMagic(rstream);
        FileMagic fm = FileMagic.valueOf(is);
        if (fm == FileMagic.OLE2) {
            this.fs = new POIFSFileSystem(is);
        } else {
            this.openOOXML(is);
        }
    }

    public VBAMacroReader(File file) throws IOException {
        try {
            this.fs = new POIFSFileSystem(file);
        } catch (OfficeXmlFileException e) {
            this.openOOXML(new FileInputStream(file));
        }
    }

    public VBAMacroReader(POIFSFileSystem fs) {
        this.fs = fs;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    private void openOOXML(InputStream zipFile) throws IOException {
        Throwable throwable = null;
        try (ZipInputStream zis = new ZipInputStream(zipFile);){
            ZipEntry zipEntry;
            while ((zipEntry = zis.getNextEntry()) != null) {
                if (!StringUtil.endsWithIgnoreCase(zipEntry.getName(), VBA_PROJECT_OOXML)) continue;
                try {
                    this.fs = new POIFSFileSystem(zis);
                    return;
                } catch (IOException e) {
                    try {
                        zis.close();
                        throw e;
                    } catch (Throwable throwable2) {
                        throwable = throwable2;
                        throw throwable2;
                    } catch (Throwable throwable3) {
                        throw throwable3;
                        throw new IllegalArgumentException("No VBA project found");
                    }
                }
            }
        }
    }

    @Override
    public void close() throws IOException {
        this.fs.close();
        this.fs = null;
    }

    public Map<String, Module> readMacroModules() throws IOException {
        ModuleMap modules = new ModuleMap();
        LinkedHashMap<String, String> moduleNameMap = new LinkedHashMap<String, String>();
        this.findMacros(this.fs.getRoot(), modules);
        this.findModuleNameMap(this.fs.getRoot(), moduleNameMap, modules);
        this.findProjectProperties(this.fs.getRoot(), moduleNameMap, modules);
        HashMap<String, Module> moduleSources = new HashMap<String, Module>();
        for (Map.Entry entry : modules.entrySet()) {
            ModuleImpl module = (ModuleImpl)entry.getValue();
            module.charset = modules.charset;
            moduleSources.put((String)entry.getKey(), module);
        }
        return moduleSources;
    }

    public Map<String, String> readMacros() throws IOException {
        Map<String, Module> modules = this.readMacroModules();
        HashMap<String, String> moduleSources = new HashMap<String, String>();
        for (Map.Entry<String, Module> entry : modules.entrySet()) {
            moduleSources.put(entry.getKey(), entry.getValue().getContent());
        }
        return moduleSources;
    }

    protected void findMacros(DirectoryNode dir, ModuleMap modules) throws IOException {
        if (VBA_PROJECT_POIFS.equalsIgnoreCase(dir.getName())) {
            this.readMacros(dir, modules);
        } else {
            for (Entry child : dir) {
                if (!(child instanceof DirectoryNode)) continue;
                this.findMacros((DirectoryNode)child, modules);
            }
        }
    }

    private static void readModuleMetadataFromDirStream(RLEDecompressingInputStream in, String streamName, ModuleMap modules) throws IOException {
        int moduleOffset = in.readInt();
        ModuleImpl module = (ModuleImpl)modules.get(streamName);
        if (module == null) {
            module = new ModuleImpl();
            module.offset = moduleOffset;
            modules.put(streamName, module);
        } else {
            RLEDecompressingInputStream stream = new RLEDecompressingInputStream(new ByteArrayInputStream(module.buf, moduleOffset, module.buf.length - moduleOffset));
            module.read(stream);
            ((InputStream)stream).close();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     * Loose catch block
     */
    private static void readModuleFromDocumentStream(DocumentNode documentNode, String name, ModuleMap modules) throws IOException {
        ModuleImpl module = (ModuleImpl)modules.get(name);
        if (module == null) {
            module = new ModuleImpl();
            modules.put(name, module);
            try (DocumentInputStream dis = new DocumentInputStream(documentNode);){
                module.read(dis);
            }
        }
        if (module.buf == null) {
            byte[] decompressedBytes;
            if (module.offset == null) {
                throw new IOException("Module offset for '" + name + "' was never read.");
            }
            RLEDecompressingInputStream decompressed = null;
            DocumentInputStream compressed = new DocumentInputStream(documentNode);
            try {
                VBAMacroReader.trySkip(compressed, module.offset.intValue());
                decompressed = new RLEDecompressingInputStream(compressed);
                module.read(decompressed);
            } catch (IllegalArgumentException | IllegalStateException runtimeException) {
                IOUtils.closeQuietly(compressed);
                IOUtils.closeQuietly(decompressed);
                catch (Throwable throwable) {
                    IOUtils.closeQuietly(compressed);
                    IOUtils.closeQuietly(decompressed);
                    throw throwable;
                }
            }
            IOUtils.closeQuietly(compressed);
            IOUtils.closeQuietly(decompressed);
            return;
            compressed = new DocumentInputStream(documentNode);
            try {
                decompressedBytes = VBAMacroReader.findCompressedStreamWBruteForce(compressed);
            } finally {
                IOUtils.closeQuietly(compressed);
            }
            if (decompressedBytes != null) {
                module.read(new ByteArrayInputStream(decompressedBytes));
            }
        }
    }

    private static void trySkip(InputStream in, long n) throws IOException {
        long skippedBytes = IOUtils.skipFully(in, n);
        if (skippedBytes != n) {
            if (skippedBytes < 0L) {
                throw new IOException("Tried skipping " + n + " bytes, but no bytes were skipped. The end of the stream has been reached or the stream is closed.");
            }
            throw new IOException("Tried skipping " + n + " bytes, but only " + skippedBytes + " bytes were skipped. This should never happen with a non-corrupt file.");
        }
    }

    protected void readMacros(DirectoryNode macroDir, ModuleMap modules) throws IOException {
        for (String entryName : macroDir.getEntryNames()) {
            if (!"dir".equalsIgnoreCase(entryName)) continue;
            this.processDirStream(macroDir.getEntry(entryName), modules);
            break;
        }
        for (Entry entry : macroDir) {
            if (!(entry instanceof DocumentNode)) continue;
            String name = entry.getName();
            DocumentNode document = (DocumentNode)entry;
            if ("dir".equalsIgnoreCase(name) || StringUtil.startsWithIgnoreCase(name, "__SRP") || StringUtil.startsWithIgnoreCase(name, "_VBA_PROJECT")) continue;
            VBAMacroReader.readModuleFromDocumentStream(document, name, modules);
        }
    }

    protected void findProjectProperties(DirectoryNode node, Map<String, String> moduleNameMap, ModuleMap modules) throws IOException {
        for (Entry entry : node) {
            if ("project".equalsIgnoreCase(entry.getName())) {
                DocumentNode document = (DocumentNode)entry;
                try (DocumentInputStream dis = new DocumentInputStream(document);){
                    this.readProjectProperties(dis, moduleNameMap, modules);
                    return;
                }
            }
            if (!(entry instanceof DirectoryNode)) continue;
            this.findProjectProperties((DirectoryNode)entry, moduleNameMap, modules);
        }
    }

    protected void findModuleNameMap(DirectoryNode node, Map<String, String> moduleNameMap, ModuleMap modules) throws IOException {
        for (Entry entry : node) {
            if ("projectwm".equalsIgnoreCase(entry.getName())) {
                DocumentNode document = (DocumentNode)entry;
                try (DocumentInputStream dis = new DocumentInputStream(document);){
                    this.readNameMapRecords(dis, moduleNameMap, modules.charset);
                    return;
                }
            }
            if (!entry.isDirectoryEntry()) continue;
            this.findModuleNameMap((DirectoryNode)entry, moduleNameMap, modules);
        }
    }

    private void processDirStream(Entry dir, ModuleMap modules) throws IOException {
        DocumentNode dirDocumentNode = (DocumentNode)dir;
        DIR_STATE dirState = DIR_STATE.INFORMATION_RECORD;
        try (DocumentInputStream dis = new DocumentInputStream(dirDocumentNode);){
            String streamName = null;
            int recordId = 0;
            try (RLEDecompressingInputStream in = new RLEDecompressingInputStream(dis);){
                block35: while ((recordId = in.readShort()) != -1) {
                    RecordType type = RecordType.lookup(recordId);
                    if (type.equals((Object)RecordType.EOF)) break;
                    if (type.equals((Object)RecordType.DIR_STREAM_TERMINATOR)) {
                        break;
                    }
                    switch (type) {
                        case PROJECT_VERSION: {
                            VBAMacroReader.trySkip(in, RecordType.PROJECT_VERSION.getConstantLength());
                            break;
                        }
                        case PROJECT_CODEPAGE: {
                            in.readInt();
                            int codepage = in.readShort();
                            modules.charset = Charset.forName(CodePageUtil.codepageToEncoding(codepage, true));
                            break;
                        }
                        case MODULE_STREAM_NAME: {
                            ASCIIUnicodeStringPair pair = this.readStringPair(in, modules.charset, 50);
                            streamName = pair.getAscii();
                            break;
                        }
                        case PROJECT_DOC_STRING: {
                            this.readStringPair(in, modules.charset, 64);
                            break;
                        }
                        case PROJECT_HELP_FILE_PATH: {
                            this.readStringPair(in, modules.charset, 61);
                            break;
                        }
                        case PROJECT_CONSTANTS: {
                            this.readStringPair(in, modules.charset, 60);
                            break;
                        }
                        case REFERENCE_NAME: {
                            ASCIIUnicodeStringPair stringPair;
                            if (dirState.equals((Object)DIR_STATE.INFORMATION_RECORD)) {
                                dirState = DIR_STATE.REFERENCES_RECORD;
                            }
                            if ((stringPair = this.readStringPair(in, modules.charset, 62, false)).getPushbackRecordId() == -1) continue block35;
                            if (stringPair.getPushbackRecordId() != RecordType.REFERENCE_REGISTERED.id) {
                                throw new IllegalArgumentException("Unexpected reserved character. Expected " + Integer.toHexString(62) + " or " + Integer.toHexString(RecordType.REFERENCE_REGISTERED.id) + " not: " + Integer.toHexString(stringPair.getPushbackRecordId()));
                            }
                        }
                        case REFERENCE_REGISTERED: {
                            int recLength = in.readInt();
                            VBAMacroReader.trySkip(in, recLength);
                            break;
                        }
                        case MODULE_DOC_STRING: {
                            int modDocStringLength = in.readInt();
                            VBAMacroReader.readString(in, modDocStringLength, modules.charset);
                            int modDocStringReserved = in.readShort();
                            if (modDocStringReserved != 72) {
                                throw new IOException("Expected x003C after stream name before Unicode stream name, but found: " + Integer.toHexString(modDocStringReserved));
                            }
                            int unicodeModDocStringLength = in.readInt();
                            this.readUnicodeString(in, unicodeModDocStringLength);
                            break;
                        }
                        case MODULE_OFFSET: {
                            int modOffsetSz = in.readInt();
                            VBAMacroReader.readModuleMetadataFromDirStream(in, streamName, modules);
                            break;
                        }
                        case PROJECT_MODULES: {
                            dirState = DIR_STATE.MODULES_RECORD;
                            in.readInt();
                            in.readShort();
                            break;
                        }
                        case REFERENCE_CONTROL_A: {
                            int szTwiddled = in.readInt();
                            VBAMacroReader.trySkip(in, szTwiddled);
                            int nextRecord = in.readShort();
                            if (nextRecord == RecordType.REFERENCE_NAME.id) {
                                this.readStringPair(in, modules.charset, 62);
                                nextRecord = in.readShort();
                            }
                            if (nextRecord != 48) {
                                throw new IOException("Expected 0x30 as Reserved3 in a ReferenceControl record");
                            }
                            int szExtended = in.readInt();
                            VBAMacroReader.trySkip(in, szExtended);
                            break;
                        }
                        case MODULE_TERMINATOR: {
                            int endOfModulesReserved = in.readInt();
                            break;
                        }
                        default: {
                            if (type.getConstantLength() > -1) {
                                VBAMacroReader.trySkip(in, type.getConstantLength());
                                break;
                            }
                            int recordLength = in.readInt();
                            VBAMacroReader.trySkip(in, recordLength);
                        }
                    }
                }
            } catch (IOException e) {
                throw new IOException("Error occurred while reading macros at section id " + recordId + " (" + HexDump.shortToHex(recordId) + ")", e);
            }
        }
    }

    private ASCIIUnicodeStringPair readStringPair(RLEDecompressingInputStream in, Charset charset, int reservedByte) throws IOException {
        return this.readStringPair(in, charset, reservedByte, true);
    }

    private ASCIIUnicodeStringPair readStringPair(RLEDecompressingInputStream in, Charset charset, int reservedByte, boolean throwOnUnexpectedReservedByte) throws IOException {
        int nameLength = in.readInt();
        String ascii = VBAMacroReader.readString(in, nameLength, charset);
        int reserved = in.readShort();
        if (reserved != reservedByte) {
            if (throwOnUnexpectedReservedByte) {
                throw new IOException("Expected " + Integer.toHexString(reservedByte) + "after name before Unicode name, but found: " + Integer.toHexString(reserved));
            }
            return new ASCIIUnicodeStringPair(ascii, reserved);
        }
        int unicodeNameRecordLength = in.readInt();
        String unicode2 = this.readUnicodeString(in, unicodeNameRecordLength);
        return new ASCIIUnicodeStringPair(ascii, unicode2);
    }

    protected void readNameMapRecords(InputStream is, Map<String, String> moduleNames, Charset charset) throws IOException {
        String mbcs = null;
        String unicode2 = null;
        int maxNameRecords = 10000;
        int records = 0;
        while (++records < 10000) {
            try {
                int b = IOUtils.readByte(is);
                if (b == 0 && (b = IOUtils.readByte(is)) == 0) {
                    return;
                }
                mbcs = VBAMacroReader.readMBCS(b, is, charset, 20000);
            } catch (EOFException e) {
                return;
            }
            try {
                unicode2 = VBAMacroReader.readUnicode(is, 20000);
            } catch (EOFException e) {
                return;
            }
            if (mbcs.trim().length() <= 0 || unicode2.trim().length() <= 0) continue;
            moduleNames.put(mbcs, unicode2);
        }
        if (records >= 10000) {
            LOGGER.log(5, "Hit max name records to read (10000). Stopped early.");
        }
    }

    private static String readUnicode(InputStream is, int maxLength) throws IOException {
        int read;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        int b0 = IOUtils.readByte(is);
        int b1 = IOUtils.readByte(is);
        for (read = 2; b0 + b1 != 0 && read < maxLength; read += 2) {
            bos.write(b0);
            bos.write(b1);
            b0 = IOUtils.readByte(is);
            b1 = IOUtils.readByte(is);
        }
        if (read >= maxLength) {
            LOGGER.log(5, "stopped reading unicode name after " + read + " bytes");
        }
        return new String(bos.toByteArray(), StandardCharsets.UTF_16LE);
    }

    private static String readMBCS(int firstByte, InputStream is, Charset charset, int maxLength) throws IOException {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        int b = firstByte;
        for (int len = 0; b > 0 && len < maxLength; ++len) {
            bos.write(b);
            b = IOUtils.readByte(is);
        }
        return new String(bos.toByteArray(), charset);
    }

    private static String readString(InputStream stream, int length, Charset charset) throws IOException {
        byte[] buffer = IOUtils.safelyAllocate(length, 20000);
        int bytesRead = IOUtils.readFully(stream, buffer);
        if (bytesRead != length) {
            throw new IOException("Tried to read: " + length + ", but could only read: " + bytesRead);
        }
        return new String(buffer, 0, length, charset);
    }

    protected void readProjectProperties(DocumentInputStream dis, Map<String, String> moduleNameMap, ModuleMap modules) throws IOException {
        int read;
        InputStreamReader reader = new InputStreamReader((InputStream)dis, modules.charset);
        StringBuilder builder = new StringBuilder();
        char[] buffer = new char[512];
        while ((read = reader.read(buffer)) >= 0) {
            builder.append(buffer, 0, read);
        }
        String properties = builder.toString();
        for (String line : properties.split("\r\n|\n\r")) {
            ModuleImpl module;
            if (line.startsWith("[")) continue;
            String[] tokens = line.split("=");
            if (tokens.length > 1 && tokens[1].length() > 1 && tokens[1].startsWith("\"") && tokens[1].endsWith("\"")) {
                tokens[1] = tokens[1].substring(1, tokens[1].length() - 1);
            }
            if ("Document".equals(tokens[0]) && tokens.length > 1) {
                String mn = tokens[1].substring(0, tokens[1].indexOf("/&H"));
                ModuleImpl module2 = this.getModule(mn, moduleNameMap, modules);
                if (module2 != null) {
                    module2.moduleType = Module.ModuleType.Document;
                    continue;
                }
                LOGGER.log(5, "couldn't find module with name: " + mn);
                continue;
            }
            if ("Module".equals(tokens[0]) && tokens.length > 1) {
                module = this.getModule(tokens[1], moduleNameMap, modules);
                if (module != null) {
                    module.moduleType = Module.ModuleType.Module;
                    continue;
                }
                LOGGER.log(5, "couldn't find module with name: " + tokens[1]);
                continue;
            }
            if (!"Class".equals(tokens[0]) || tokens.length <= 1) continue;
            module = this.getModule(tokens[1], moduleNameMap, modules);
            if (module != null) {
                module.moduleType = Module.ModuleType.Class;
                continue;
            }
            LOGGER.log(5, "couldn't find module with name: " + tokens[1]);
        }
    }

    private ModuleImpl getModule(String moduleName, Map<String, String> moduleNameMap, ModuleMap moduleMap) {
        if (moduleNameMap.containsKey(moduleName)) {
            return (ModuleImpl)moduleMap.get(moduleNameMap.get(moduleName));
        }
        return (ModuleImpl)moduleMap.get(moduleName);
    }

    private String readUnicodeString(RLEDecompressingInputStream in, int unicodeNameRecordLength) throws IOException {
        byte[] buffer = IOUtils.safelyAllocate(unicodeNameRecordLength, 20000);
        int bytesRead = IOUtils.readFully(in, buffer);
        if (bytesRead != unicodeNameRecordLength) {
            throw new EOFException();
        }
        return new String(buffer, StringUtil.UTF16LE);
    }

    private static byte[] findCompressedStreamWBruteForce(InputStream is) throws IOException {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        IOUtils.copy(is, bos);
        byte[] compressed = bos.toByteArray();
        byte[] decompressed = null;
        for (int i = 0; i < compressed.length; ++i) {
            int firstX;
            String start;
            int w;
            if (compressed[i] != 1 || i >= compressed.length - 1 || (w = LittleEndian.getUShort(compressed, i + 1)) <= 0 || (w & 0x7000) != 12288 || (decompressed = VBAMacroReader.tryToDecompress(new ByteArrayInputStream(compressed, i, compressed.length - i))) == null || decompressed.length <= 9 || !(start = new String(decompressed, 0, firstX = Math.min(20, decompressed.length), StringUtil.WIN_1252)).contains("Attribute")) continue;
            return decompressed;
        }
        return decompressed;
    }

    private static byte[] tryToDecompress(InputStream is) {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try {
            IOUtils.copy((InputStream)new RLEDecompressingInputStream(is), bos);
        } catch (IOException | IllegalArgumentException | IllegalStateException e) {
            return null;
        }
        return bos.toByteArray();
    }

    private static class ASCIIUnicodeStringPair {
        private final String ascii;
        private final String unicode;
        private final int pushbackRecordId;

        ASCIIUnicodeStringPair(String ascii, int pushbackRecordId) {
            this.ascii = ascii;
            this.unicode = "";
            this.pushbackRecordId = pushbackRecordId;
        }

        ASCIIUnicodeStringPair(String ascii, String unicode2) {
            this.ascii = ascii;
            this.unicode = unicode2;
            this.pushbackRecordId = -1;
        }

        private String getAscii() {
            return this.ascii;
        }

        private String getUnicode() {
            return this.unicode;
        }

        private int getPushbackRecordId() {
            return this.pushbackRecordId;
        }
    }

    private static enum DIR_STATE {
        INFORMATION_RECORD,
        REFERENCES_RECORD,
        MODULES_RECORD;

    }

    private static enum RecordType {
        MODULE_OFFSET(49),
        PROJECT_SYS_KIND(1),
        PROJECT_LCID(2),
        PROJECT_LCID_INVOKE(20),
        PROJECT_CODEPAGE(3),
        PROJECT_NAME(4),
        PROJECT_DOC_STRING(5),
        PROJECT_HELP_FILE_PATH(6),
        PROJECT_HELP_CONTEXT(7, 8),
        PROJECT_LIB_FLAGS(8),
        PROJECT_VERSION(9, 10),
        PROJECT_CONSTANTS(12),
        PROJECT_MODULES(15),
        DIR_STREAM_TERMINATOR(16),
        PROJECT_COOKIE(19),
        MODULE_NAME(25),
        MODULE_NAME_UNICODE(71),
        MODULE_STREAM_NAME(26),
        MODULE_DOC_STRING(28),
        MODULE_HELP_CONTEXT(30),
        MODULE_COOKIE(44),
        MODULE_TYPE_PROCEDURAL(33, 4),
        MODULE_TYPE_OTHER(34, 4),
        MODULE_PRIVATE(40, 4),
        REFERENCE_NAME(22),
        REFERENCE_REGISTERED(13),
        REFERENCE_PROJECT(14),
        REFERENCE_CONTROL_A(47),
        REFERENCE_CONTROL_B(51),
        MODULE_TERMINATOR(43),
        EOF(-1),
        UNKNOWN(-2);

        private final int VARIABLE_LENGTH = -1;
        private final int id;
        private final int constantLength;

        private RecordType(int id) {
            this.id = id;
            this.constantLength = -1;
        }

        private RecordType(int id, int constantLength) {
            this.id = id;
            this.constantLength = constantLength;
        }

        int getConstantLength() {
            return this.constantLength;
        }

        static RecordType lookup(int id) {
            for (RecordType type : RecordType.values()) {
                if (type.id != id) continue;
                return type;
            }
            return UNKNOWN;
        }
    }

    protected static class ModuleMap
    extends HashMap<String, ModuleImpl> {
        Charset charset = StringUtil.WIN_1252;

        protected ModuleMap() {
        }
    }

    protected static class ModuleImpl
    implements Module {
        Integer offset;
        byte[] buf;
        Module.ModuleType moduleType;
        Charset charset;

        protected ModuleImpl() {
        }

        void read(InputStream in) throws IOException {
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            IOUtils.copy(in, out);
            out.close();
            this.buf = out.toByteArray();
        }

        @Override
        public String getContent() {
            return new String(this.buf, this.charset);
        }

        @Override
        public Module.ModuleType geModuleType() {
            return this.moduleType;
        }
    }
}

