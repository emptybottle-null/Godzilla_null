/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.sl.usermodel;

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
import org.apache.poi.sl.usermodel.Shape;
import org.apache.poi.sl.usermodel.SlideShow;
import org.apache.poi.sl.usermodel.TextParagraph;
import org.apache.poi.sl.usermodel.TextRun;
import org.apache.poi.util.IOUtils;

public abstract class SlideShowFactory {
    protected static CreateSlideShow1<InputStream> createXslfByStream;
    protected static CreateSlideShow2<File, Boolean> createXslfByFile;
    protected static CreateSlideShow1<POIFSFileSystem> createHslfByPoifs;
    protected static CreateSlideShow1<DirectoryNode> createHslfByNode;

    public static <S extends Shape<S, P>, P extends TextParagraph<S, P, ? extends TextRun>> SlideShow<S, P> create(POIFSFileSystem fs) throws IOException {
        return SlideShowFactory.create(fs, null);
    }

    public static <S extends Shape<S, P>, P extends TextParagraph<S, P, ? extends TextRun>> SlideShow<S, P> create(POIFSFileSystem fs, String password) throws IOException {
        return SlideShowFactory.create(fs.getRoot(), password);
    }

    public static <S extends Shape<S, P>, P extends TextParagraph<S, P, ? extends TextRun>> SlideShow<S, P> create(DirectoryNode root) throws IOException {
        return SlideShowFactory.create(root, null);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static <S extends Shape<S, P>, P extends TextParagraph<S, P, ? extends TextRun>> SlideShow<S, P> create(DirectoryNode root, String password) throws IOException {
        if (root.hasEntry("EncryptedPackage")) {
            InputStream stream = null;
            try {
                stream = DocumentFactoryHelper.getDecryptedStream(root, password);
                SlideShowFactory.initXslf();
                SlideShow<?, ?> slideShow = createXslfByStream.apply(stream);
                return slideShow;
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
            SlideShowFactory.initHslf();
            SlideShow<?, ?> slideShow = createHslfByNode.apply(root);
            return slideShow;
        } finally {
            if (passwordSet) {
                Biff8EncryptionKey.setCurrentUserPassword(null);
            }
        }
    }

    public static <S extends Shape<S, P>, P extends TextParagraph<S, P, ? extends TextRun>> SlideShow<S, P> create(InputStream inp) throws IOException, EncryptedDocumentException {
        return SlideShowFactory.create(inp, null);
    }

    public static <S extends Shape<S, P>, P extends TextParagraph<S, P, ? extends TextRun>> SlideShow<S, P> create(InputStream inp, String password) throws IOException, EncryptedDocumentException {
        InputStream is = FileMagic.prepareToCheckMagic(inp);
        FileMagic fm = FileMagic.valueOf(is);
        switch (fm) {
            case OLE2: {
                POIFSFileSystem fs = new POIFSFileSystem(is);
                return SlideShowFactory.create(fs, password);
            }
            case OOXML: {
                SlideShowFactory.initXslf();
                return createXslfByStream.apply(is);
            }
        }
        throw new IOException("Your InputStream was neither an OLE2 stream, nor an OOXML stream");
    }

    public static <S extends Shape<S, P>, P extends TextParagraph<S, P, ? extends TextRun>> SlideShow<S, P> create(File file) throws IOException, EncryptedDocumentException {
        return SlideShowFactory.create(file, null);
    }

    public static <S extends Shape<S, P>, P extends TextParagraph<S, P, ? extends TextRun>> SlideShow<S, P> create(File file, String password) throws IOException, EncryptedDocumentException {
        return SlideShowFactory.create(file, password, false);
    }

    public static <S extends Shape<S, P>, P extends TextParagraph<S, P, ? extends TextRun>> SlideShow<S, P> create(File file, String password, boolean readOnly) throws IOException, EncryptedDocumentException {
        if (!file.exists()) {
            throw new FileNotFoundException(file.toString());
        }
        POIFSFileSystem fs = null;
        try {
            fs = new POIFSFileSystem(file, readOnly);
            return SlideShowFactory.create(fs, password);
        } catch (OfficeXmlFileException e) {
            IOUtils.closeQuietly(fs);
            SlideShowFactory.initXslf();
            return createXslfByFile.apply(file, readOnly);
        } catch (RuntimeException e) {
            IOUtils.closeQuietly(fs);
            throw e;
        }
    }

    private static void initXslf() throws IOException {
        if (createXslfByFile == null) {
            SlideShowFactory.initFactory("org.apache.poi.xslf.usermodel.XSLFSlideShowFactory", "poi-ooxml-*.jar");
        }
    }

    private static void initHslf() throws IOException {
        if (createHslfByPoifs == null) {
            SlideShowFactory.initFactory("org.apache.poi.hslf.usermodel.HSLFSlideShowFactory", "poi-scratchpad-*.jar");
        }
    }

    private static void initFactory(String factoryClass, String jar) throws IOException {
        try {
            Class.forName(factoryClass, true, SlideShowFactory.class.getClassLoader());
        } catch (ClassNotFoundException e) {
            throw new IOException(factoryClass + " not found - check if " + jar + " is on the classpath.");
        }
    }

    protected static interface CreateSlideShow2<T, U> {
        public SlideShow<?, ?> apply(T var1, U var2) throws IOException;
    }

    protected static interface CreateSlideShow1<T> {
        public SlideShow<?, ?> apply(T var1) throws IOException;
    }
}

