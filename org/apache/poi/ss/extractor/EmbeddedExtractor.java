/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.ss.extractor;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import org.apache.poi.hpsf.ClassID;
import org.apache.poi.hpsf.ClassIDPredefined;
import org.apache.poi.poifs.filesystem.DirectoryNode;
import org.apache.poi.poifs.filesystem.DocumentInputStream;
import org.apache.poi.poifs.filesystem.Entry;
import org.apache.poi.poifs.filesystem.Ole10Native;
import org.apache.poi.poifs.filesystem.Ole10NativeException;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.extractor.EmbeddedData;
import org.apache.poi.ss.usermodel.Drawing;
import org.apache.poi.ss.usermodel.ObjectData;
import org.apache.poi.ss.usermodel.Picture;
import org.apache.poi.ss.usermodel.PictureData;
import org.apache.poi.ss.usermodel.Shape;
import org.apache.poi.ss.usermodel.ShapeContainer;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.util.IOUtils;
import org.apache.poi.util.LocaleUtil;
import org.apache.poi.util.POILogFactory;
import org.apache.poi.util.POILogger;
import org.apache.poi.util.StringUtil;

public class EmbeddedExtractor
implements Iterable<EmbeddedExtractor> {
    private static final POILogger LOG = POILogFactory.getLogger(EmbeddedExtractor.class);
    private static final int MAX_RECORD_LENGTH = 1000000;
    private static final String CONTENT_TYPE_BYTES = "binary/octet-stream";
    private static final String CONTENT_TYPE_PDF = "application/pdf";
    private static final String CONTENT_TYPE_DOC = "application/msword";
    private static final String CONTENT_TYPE_XLS = "application/vnd.ms-excel";

    @Override
    public Iterator<EmbeddedExtractor> iterator() {
        EmbeddedExtractor[] ee = new EmbeddedExtractor[]{new Ole10Extractor(), new PdfExtractor(), new BiffExtractor(), new OOXMLExtractor(), new FsExtractor()};
        return Arrays.asList(ee).iterator();
    }

    public EmbeddedData extractOne(DirectoryNode src) throws IOException {
        for (EmbeddedExtractor ee : this) {
            if (!ee.canExtract(src)) continue;
            return ee.extract(src);
        }
        return null;
    }

    public EmbeddedData extractOne(Picture src) throws IOException {
        for (EmbeddedExtractor ee : this) {
            if (!ee.canExtract(src)) continue;
            return ee.extract(src);
        }
        return null;
    }

    public List<EmbeddedData> extractAll(Sheet sheet) throws IOException {
        Drawing<?> patriarch = sheet.getDrawingPatriarch();
        if (null == patriarch) {
            return Collections.emptyList();
        }
        ArrayList<EmbeddedData> embeddings = new ArrayList<EmbeddedData>();
        this.extractAll(patriarch, embeddings);
        return embeddings;
    }

    protected void extractAll(ShapeContainer<?> parent, List<EmbeddedData> embeddings) throws IOException {
        for (Shape shape : parent) {
            String extension;
            EmbeddedData data;
            block11: {
                data = null;
                if (shape instanceof ObjectData) {
                    ObjectData od = (ObjectData)shape;
                    try {
                        if (od.hasDirectoryEntry()) {
                            data = this.extractOne((DirectoryNode)od.getDirectory());
                            break block11;
                        }
                        data = new EmbeddedData(od.getFileName(), od.getObjectData(), od.getContentType());
                    } catch (Exception e) {
                        LOG.log(5, "Entry not found / readable - ignoring OLE embedding", e);
                    }
                } else if (shape instanceof Picture) {
                    data = this.extractOne((Picture)shape);
                } else if (shape instanceof ShapeContainer) {
                    this.extractAll((ShapeContainer)((Object)shape), embeddings);
                }
            }
            if (data == null) continue;
            data.setShape(shape);
            String filename = data.getFilename();
            String string = extension = filename == null || filename.lastIndexOf(46) == -1 ? ".bin" : filename.substring(filename.lastIndexOf(46));
            if ((filename == null || filename.isEmpty() || filename.startsWith("MBD") || filename.startsWith("Root Entry")) && (filename = shape.getShapeName()) != null) {
                filename = filename + extension;
            }
            if (filename == null || filename.isEmpty()) {
                filename = "picture_" + embeddings.size() + extension;
            }
            filename = filename.trim();
            data.setFilename(filename);
            embeddings.add(data);
        }
    }

    public boolean canExtract(DirectoryNode source) {
        return false;
    }

    public boolean canExtract(Picture source) {
        return false;
    }

    protected EmbeddedData extract(DirectoryNode dn) throws IOException {
        assert (this.canExtract(dn));
        ByteArrayOutputStream bos = new ByteArrayOutputStream(20000);
        try (POIFSFileSystem dest = new POIFSFileSystem();){
            EmbeddedExtractor.copyNodes(dn, dest.getRoot());
            dest.writeFilesystem(bos);
        }
        return new EmbeddedData(dn.getName(), bos.toByteArray(), CONTENT_TYPE_BYTES);
    }

    protected EmbeddedData extract(Picture source) throws IOException {
        return null;
    }

    protected static void copyNodes(DirectoryNode src, DirectoryNode dest) throws IOException {
        for (Entry e : src) {
            if (e instanceof DirectoryNode) {
                DirectoryNode srcDir = (DirectoryNode)e;
                DirectoryNode destDir = (DirectoryNode)dest.createDirectory(srcDir.getName());
                destDir.setStorageClsid(srcDir.getStorageClsid());
                EmbeddedExtractor.copyNodes(srcDir, destDir);
                continue;
            }
            DocumentInputStream is = src.createDocumentInputStream(e);
            Throwable throwable = null;
            try {
                dest.createDocument(e.getName(), is);
            } catch (Throwable throwable2) {
                throwable = throwable2;
                throw throwable2;
            } finally {
                if (is == null) continue;
                if (throwable != null) {
                    try {
                        ((InputStream)is).close();
                    } catch (Throwable throwable3) {
                        throwable.addSuppressed(throwable3);
                    }
                    continue;
                }
                ((InputStream)is).close();
            }
        }
    }

    private static int indexOf(byte[] data, int offset, byte[] pattern) {
        int[] failure = EmbeddedExtractor.computeFailure(pattern);
        int j = 0;
        if (data.length == 0) {
            return -1;
        }
        for (int i = offset; i < data.length; ++i) {
            while (j > 0 && pattern[j] != data[i]) {
                j = failure[j - 1];
            }
            if (pattern[j] == data[i]) {
                ++j;
            }
            if (j != pattern.length) continue;
            return i - pattern.length + 1;
        }
        return -1;
    }

    private static int[] computeFailure(byte[] pattern) {
        int[] failure = new int[pattern.length];
        int j = 0;
        for (int i = 1; i < pattern.length; ++i) {
            while (j > 0 && pattern[j] != pattern[i]) {
                j = failure[j - 1];
            }
            if (pattern[j] == pattern[i]) {
                // empty if block
            }
            failure[i] = ++j;
        }
        return failure;
    }

    static class FsExtractor
    extends EmbeddedExtractor {
        FsExtractor() {
        }

        @Override
        public boolean canExtract(DirectoryNode dn) {
            return true;
        }

        @Override
        public EmbeddedData extract(DirectoryNode dn) throws IOException {
            EmbeddedData ed = super.extract(dn);
            ed.setFilename(dn.getName() + ".ole");
            return ed;
        }
    }

    static class BiffExtractor
    extends EmbeddedExtractor {
        BiffExtractor() {
        }

        @Override
        public boolean canExtract(DirectoryNode dn) {
            return this.canExtractExcel(dn) || this.canExtractWord(dn);
        }

        protected boolean canExtractExcel(DirectoryNode dn) {
            ClassIDPredefined clsId = ClassIDPredefined.lookup(dn.getStorageClsid());
            return ClassIDPredefined.EXCEL_V7 == clsId || ClassIDPredefined.EXCEL_V8 == clsId || dn.hasEntry("Workbook");
        }

        protected boolean canExtractWord(DirectoryNode dn) {
            ClassIDPredefined clsId = ClassIDPredefined.lookup(dn.getStorageClsid());
            return ClassIDPredefined.WORD_V7 == clsId || ClassIDPredefined.WORD_V8 == clsId || dn.hasEntry("WordDocument");
        }

        @Override
        public EmbeddedData extract(DirectoryNode dn) throws IOException {
            EmbeddedData ed = super.extract(dn);
            if (this.canExtractExcel(dn)) {
                ed.setFilename(dn.getName() + ".xls");
                ed.setContentType(EmbeddedExtractor.CONTENT_TYPE_XLS);
            } else if (this.canExtractWord(dn)) {
                ed.setFilename(dn.getName() + ".doc");
                ed.setContentType(EmbeddedExtractor.CONTENT_TYPE_DOC);
            }
            return ed;
        }
    }

    static class OOXMLExtractor
    extends EmbeddedExtractor {
        OOXMLExtractor() {
        }

        @Override
        public boolean canExtract(DirectoryNode dn) {
            return dn.hasEntry("package");
        }

        @Override
        public EmbeddedData extract(DirectoryNode dn) throws IOException {
            ClassIDPredefined clsId = ClassIDPredefined.lookup(dn.getStorageClsid());
            String contentType = null;
            String ext = null;
            if (clsId != null) {
                contentType = clsId.getContentType();
                ext = clsId.getFileExtension();
            }
            if (contentType == null || ext == null) {
                contentType = "application/zip";
                ext = ".zip";
            }
            DocumentInputStream dis = dn.createDocumentInputStream("package");
            byte[] data = IOUtils.toByteArray(dis);
            dis.close();
            return new EmbeddedData(dn.getName() + ext, data, contentType);
        }
    }

    static class PdfExtractor
    extends EmbeddedExtractor {
        PdfExtractor() {
        }

        @Override
        public boolean canExtract(DirectoryNode dn) {
            ClassID clsId = dn.getStorageClsid();
            return ClassIDPredefined.PDF.equals(clsId) || dn.hasEntry("CONTENTS");
        }

        /*
         * Exception decompiling
         */
        @Override
        public EmbeddedData extract(DirectoryNode dn) throws IOException {
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
             *     at org.benf.cfr.reader.entities.ClassFile.analyseInnerClassesPass1(ClassFile.java:923)
             *     at org.benf.cfr.reader.entities.ClassFile.analyseMid(ClassFile.java:1035)
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

        @Override
        public boolean canExtract(Picture source) {
            PictureData pd = source.getPictureData();
            return pd != null && pd.getPictureType() == 2;
        }

        @Override
        protected EmbeddedData extract(Picture source) throws IOException {
            PictureData pd = source.getPictureData();
            if (pd == null || pd.getPictureType() != 2) {
                return null;
            }
            byte[] pictureBytes = pd.getData();
            int idxStart = EmbeddedExtractor.indexOf(pictureBytes, 0, "%PDF-".getBytes(LocaleUtil.CHARSET_1252));
            if (idxStart == -1) {
                return null;
            }
            int idxEnd = EmbeddedExtractor.indexOf(pictureBytes, idxStart, "%%EOF".getBytes(LocaleUtil.CHARSET_1252));
            if (idxEnd == -1) {
                return null;
            }
            int pictureBytesLen = idxEnd - idxStart + 6;
            byte[] pdfBytes = IOUtils.safelyAllocate(pictureBytesLen, 1000000);
            System.arraycopy(pictureBytes, idxStart, pdfBytes, 0, pictureBytesLen);
            String filename = source.getShapeName().trim();
            if (!StringUtil.endsWithIgnoreCase(filename, ".pdf")) {
                filename = filename + ".pdf";
            }
            return new EmbeddedData(filename, pdfBytes, EmbeddedExtractor.CONTENT_TYPE_PDF);
        }
    }

    public static class Ole10Extractor
    extends EmbeddedExtractor {
        @Override
        public boolean canExtract(DirectoryNode dn) {
            ClassID clsId = dn.getStorageClsid();
            return ClassIDPredefined.lookup(clsId) == ClassIDPredefined.OLE_V1_PACKAGE;
        }

        @Override
        public EmbeddedData extract(DirectoryNode dn) throws IOException {
            try {
                Ole10Native ole10 = Ole10Native.createFromEmbeddedOleObject(dn);
                return new EmbeddedData(ole10.getFileName(), ole10.getDataBuffer(), EmbeddedExtractor.CONTENT_TYPE_BYTES);
            } catch (Ole10NativeException e) {
                throw new IOException(e);
            }
        }
    }
}

