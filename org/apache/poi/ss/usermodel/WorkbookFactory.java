/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.ss.usermodel;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.hssf.record.crypto.Biff8EncryptionKey;
import org.apache.poi.poifs.filesystem.DirectoryNode;
import org.apache.poi.poifs.filesystem.DocumentFactoryHelper;
import org.apache.poi.poifs.filesystem.FileMagic;
import org.apache.poi.poifs.filesystem.OfficeXmlFileException;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.util.IOUtils;
import org.apache.poi.util.Removal;

public abstract class WorkbookFactory {
    protected static CreateWorkbook0 createHssfFromScratch;
    protected static CreateWorkbook1<DirectoryNode> createHssfByNode;
    protected static CreateWorkbook0 createXssfFromScratch;
    protected static CreateWorkbook1<InputStream> createXssfByStream;
    protected static CreateWorkbook1<Object> createXssfByPackage;
    protected static CreateWorkbook2<File, Boolean> createXssfByFile;

    public static Workbook create(boolean xssf) throws IOException {
        if (xssf) {
            WorkbookFactory.initXssf();
            return createXssfFromScratch.apply();
        }
        WorkbookFactory.initHssf();
        return createHssfFromScratch.apply();
    }

    public static Workbook create(POIFSFileSystem fs) throws IOException {
        return WorkbookFactory.create(fs, null);
    }

    private static Workbook create(POIFSFileSystem fs, String password) throws IOException {
        return WorkbookFactory.create(fs.getRoot(), password);
    }

    public static Workbook create(DirectoryNode root) throws IOException {
        return WorkbookFactory.create(root, null);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static Workbook create(DirectoryNode root, String password) throws IOException {
        if (root.hasEntry("EncryptedPackage")) {
            InputStream stream = null;
            try {
                stream = DocumentFactoryHelper.getDecryptedStream(root, password);
                WorkbookFactory.initXssf();
                Workbook workbook = createXssfByStream.apply(stream);
                return workbook;
            } finally {
                IOUtils.closeQuietly(stream);
                root.getFileSystem().close();
            }
        }
        boolean passwordSet = false;
        if (password != null) {
            Biff8EncryptionKey.setCurrentUserPassword(password);
            passwordSet = true;
        }
        try {
            WorkbookFactory.initHssf();
            Workbook workbook = createHssfByNode.apply(root);
            return workbook;
        } finally {
            if (passwordSet) {
                Biff8EncryptionKey.setCurrentUserPassword(null);
            }
        }
    }

    @Deprecated
    @Removal(version="4.2.0")
    public static Workbook create(Object pkg) throws IOException {
        WorkbookFactory.initXssf();
        return createXssfByPackage.apply(pkg);
    }

    public static Workbook create(InputStream inp) throws IOException, EncryptedDocumentException {
        return WorkbookFactory.create(inp, null);
    }

    public static Workbook create(InputStream inp, String password) throws IOException, EncryptedDocumentException {
        InputStream is = FileMagic.prepareToCheckMagic(inp);
        FileMagic fm = FileMagic.valueOf(is);
        switch (fm) {
            case OLE2: {
                POIFSFileSystem fs = new POIFSFileSystem(is);
                return WorkbookFactory.create(fs, password);
            }
            case OOXML: {
                WorkbookFactory.initXssf();
                return createXssfByStream.apply(is);
            }
        }
        throw new IOException("Your InputStream was neither an OLE2 stream, nor an OOXML stream");
    }

    public static Workbook create(File file) throws IOException, EncryptedDocumentException {
        return WorkbookFactory.create(file, null);
    }

    public static Workbook create(File file, String password) throws IOException, EncryptedDocumentException {
        return WorkbookFactory.create(file, password, false);
    }

    public static Workbook create(File file, String password, boolean readOnly) throws IOException, EncryptedDocumentException {
        if (!file.exists()) {
            throw new FileNotFoundException(file.toString());
        }
        POIFSFileSystem fs = null;
        try {
            fs = new POIFSFileSystem(file, readOnly);
            return WorkbookFactory.create(fs, password);
        } catch (OfficeXmlFileException e) {
            IOUtils.closeQuietly(fs);
            WorkbookFactory.initXssf();
            return createXssfByFile.apply(file, readOnly);
        } catch (RuntimeException e) {
            IOUtils.closeQuietly(fs);
            throw e;
        }
    }

    private static void initXssf() throws IOException {
        if (createXssfFromScratch == null) {
            WorkbookFactory.initFactory("org.apache.poi.xssf.usermodel.XSSFWorkbookFactory", "poi-ooxml-*.jar");
        }
    }

    private static void initHssf() throws IOException {
        if (createHssfFromScratch == null) {
            WorkbookFactory.initFactory("org.apache.poi.hssf.usermodel.HSSFWorkbookFactory", "poi-*.jar");
        }
    }

    private static void initFactory(String factoryClass, String jar) throws IOException {
        try {
            Class.forName(factoryClass, true, WorkbookFactory.class.getClassLoader());
        } catch (ClassNotFoundException e) {
            throw new IOException(factoryClass + " not found - check if " + jar + " is on the classpath.");
        }
    }

    protected static interface CreateWorkbook2<T, U> {
        public Workbook apply(T var1, U var2) throws IOException;
    }

    protected static interface CreateWorkbook1<T> {
        public Workbook apply(T var1) throws IOException;
    }

    protected static interface CreateWorkbook0 {
        public Workbook apply() throws IOException;
    }
}

