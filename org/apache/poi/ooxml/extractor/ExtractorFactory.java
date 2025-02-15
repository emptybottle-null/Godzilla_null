/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.ooxml.extractor;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.extractor.OLE2ExtractorFactory;
import org.apache.poi.extractor.POIOLE2TextExtractor;
import org.apache.poi.extractor.POITextExtractor;
import org.apache.poi.hssf.extractor.ExcelExtractor;
import org.apache.poi.hssf.record.crypto.Biff8EncryptionKey;
import org.apache.poi.ooxml.POIXMLRelation;
import org.apache.poi.ooxml.extractor.POIXMLTextExtractor;
import org.apache.poi.openxml4j.exceptions.OpenXML4JException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.openxml4j.opc.PackageAccess;
import org.apache.poi.openxml4j.opc.PackagePart;
import org.apache.poi.openxml4j.opc.PackageRelationshipCollection;
import org.apache.poi.poifs.crypt.Decryptor;
import org.apache.poi.poifs.crypt.EncryptionInfo;
import org.apache.poi.poifs.filesystem.DirectoryEntry;
import org.apache.poi.poifs.filesystem.DirectoryNode;
import org.apache.poi.poifs.filesystem.Entry;
import org.apache.poi.poifs.filesystem.FileMagic;
import org.apache.poi.poifs.filesystem.NotOLE2FileException;
import org.apache.poi.poifs.filesystem.OfficeXmlFileException;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.sl.extractor.SlideShowExtractor;
import org.apache.poi.util.IOUtils;
import org.apache.poi.util.NotImplemented;
import org.apache.poi.util.POILogFactory;
import org.apache.poi.util.POILogger;
import org.apache.poi.util.Removal;
import org.apache.poi.xdgf.extractor.XDGFVisioExtractor;
import org.apache.poi.xslf.extractor.XSLFPowerPointExtractor;
import org.apache.poi.xslf.usermodel.XMLSlideShow;
import org.apache.poi.xslf.usermodel.XSLFRelation;
import org.apache.poi.xslf.usermodel.XSLFShape;
import org.apache.poi.xslf.usermodel.XSLFTextParagraph;
import org.apache.poi.xssf.extractor.XSSFBEventBasedExcelExtractor;
import org.apache.poi.xssf.extractor.XSSFEventBasedExcelExtractor;
import org.apache.poi.xssf.extractor.XSSFExcelExtractor;
import org.apache.poi.xssf.usermodel.XSSFRelation;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.xmlbeans.XmlException;

public final class ExtractorFactory {
    private static final POILogger logger = POILogFactory.getLogger(ExtractorFactory.class);
    public static final String CORE_DOCUMENT_REL = "http://schemas.openxmlformats.org/officeDocument/2006/relationships/officeDocument";
    private static final String VISIO_DOCUMENT_REL = "http://schemas.microsoft.com/visio/2010/relationships/document";
    private static final String STRICT_DOCUMENT_REL = "http://purl.oclc.org/ooxml/officeDocument/relationships/officeDocument";

    private ExtractorFactory() {
    }

    public static boolean getThreadPrefersEventExtractors() {
        return OLE2ExtractorFactory.getThreadPrefersEventExtractors();
    }

    public static Boolean getAllThreadsPreferEventExtractors() {
        return OLE2ExtractorFactory.getAllThreadsPreferEventExtractors();
    }

    public static void setThreadPrefersEventExtractors(boolean preferEventExtractors) {
        OLE2ExtractorFactory.setThreadPrefersEventExtractors(preferEventExtractors);
    }

    public static void setAllThreadsPreferEventExtractors(Boolean preferEventExtractors) {
        OLE2ExtractorFactory.setAllThreadsPreferEventExtractors(preferEventExtractors);
    }

    public static boolean getPreferEventExtractor() {
        return OLE2ExtractorFactory.getPreferEventExtractor();
    }

    public static <T extends POITextExtractor> T createExtractor(File f) throws IOException, OpenXML4JException, XmlException {
        POIFSFileSystem fs = null;
        try {
            fs = new POIFSFileSystem(f);
            if (fs.getRoot().hasEntry("EncryptedPackage")) {
                return (T)ExtractorFactory.createEncryptedOOXMLExtractor(fs);
            }
            T extractor = ExtractorFactory.createExtractor(fs);
            ((POITextExtractor)extractor).setFilesystem(fs);
            return extractor;
        } catch (OfficeXmlFileException e) {
            IOUtils.closeQuietly(fs);
            OPCPackage pkg = OPCPackage.open(f.toString(), PackageAccess.READ);
            POITextExtractor t = ExtractorFactory.createExtractor(pkg);
            t.setFilesystem(pkg);
            return (T)t;
        } catch (NotOLE2FileException ne) {
            IOUtils.closeQuietly(fs);
            throw new IllegalArgumentException("Your File was neither an OLE2 file, nor an OOXML file", ne);
        } catch (IOException | Error | RuntimeException | OpenXML4JException | XmlException e) {
            IOUtils.closeQuietly(fs);
            throw e;
        }
    }

    public static POITextExtractor createExtractor(InputStream inp) throws IOException, OpenXML4JException, XmlException {
        InputStream is = FileMagic.prepareToCheckMagic(inp);
        FileMagic fm = FileMagic.valueOf(is);
        switch (fm) {
            case OLE2: {
                POIFSFileSystem fs = new POIFSFileSystem(is);
                boolean isEncrypted = fs.getRoot().hasEntry("EncryptedPackage");
                return isEncrypted ? ExtractorFactory.createEncryptedOOXMLExtractor(fs) : ExtractorFactory.createExtractor(fs);
            }
            case OOXML: {
                return ExtractorFactory.createExtractor(OPCPackage.open(is));
            }
        }
        throw new IllegalArgumentException("Your InputStream was neither an OLE2 stream, nor an OOXML stream, found type: " + (Object)((Object)fm));
    }

    public static POITextExtractor createExtractor(OPCPackage pkg) throws IOException, OpenXML4JException, XmlException {
        try {
            PackageRelationshipCollection core = pkg.getRelationshipsByType(CORE_DOCUMENT_REL);
            if (core.size() == 0) {
                core = pkg.getRelationshipsByType(STRICT_DOCUMENT_REL);
            }
            if (core.size() == 0 && (core = pkg.getRelationshipsByType(VISIO_DOCUMENT_REL)).size() == 1) {
                return new XDGFVisioExtractor(pkg);
            }
            if (core.size() != 1) {
                throw new IllegalArgumentException("Invalid OOXML Package received - expected 1 core document, found " + core.size());
            }
            PackagePart corePart = pkg.getPart(core.getRelationship(0));
            String contentType = corePart.getContentType();
            for (XSSFRelation xSSFRelation : XSSFExcelExtractor.SUPPORTED_TYPES) {
                if (!xSSFRelation.getContentType().equals(contentType)) continue;
                if (ExtractorFactory.getPreferEventExtractor()) {
                    return new XSSFEventBasedExcelExtractor(pkg);
                }
                return new XSSFExcelExtractor(pkg);
            }
            for (POIXMLRelation pOIXMLRelation : XWPFWordExtractor.SUPPORTED_TYPES) {
                if (!pOIXMLRelation.getContentType().equals(contentType)) continue;
                return new XWPFWordExtractor(pkg);
            }
            for (POIXMLRelation pOIXMLRelation : XSLFPowerPointExtractor.SUPPORTED_TYPES) {
                if (!pOIXMLRelation.getContentType().equals(contentType)) continue;
                return new SlideShowExtractor<XSLFShape, XSLFTextParagraph>(new XMLSlideShow(pkg));
            }
            if (XSLFRelation.THEME_MANAGER.getContentType().equals(contentType)) {
                return new SlideShowExtractor<XSLFShape, XSLFTextParagraph>(new XMLSlideShow(pkg));
            }
            for (POIXMLRelation pOIXMLRelation : XSSFBEventBasedExcelExtractor.SUPPORTED_TYPES) {
                if (!pOIXMLRelation.getContentType().equals(contentType)) continue;
                return new XSSFBEventBasedExcelExtractor(pkg);
            }
            throw new IllegalArgumentException("No supported documents found in the OOXML package (found " + contentType + ")");
        } catch (IOException | Error | RuntimeException | OpenXML4JException | XmlException e) {
            pkg.revert();
            throw e;
        }
    }

    public static <T extends POITextExtractor> T createExtractor(POIFSFileSystem fs) throws IOException, OpenXML4JException, XmlException {
        return ExtractorFactory.createExtractor(fs.getRoot());
    }

    public static <T extends POITextExtractor> T createExtractor(DirectoryNode poifsDir) throws IOException, OpenXML4JException, XmlException {
        for (String entryName : poifsDir.getEntryNames()) {
            if (!entryName.equals("Package")) continue;
            OPCPackage pkg = OPCPackage.open(poifsDir.createDocumentInputStream("Package"));
            return (T)ExtractorFactory.createExtractor(pkg);
        }
        return (T)OLE2ExtractorFactory.createExtractor(poifsDir);
    }

    @Deprecated
    @Removal(version="4.2")
    public static POITextExtractor[] getEmbededDocsTextExtractors(POIOLE2TextExtractor ext) throws IOException, OpenXML4JException, XmlException {
        return ExtractorFactory.getEmbeddedDocsTextExtractors(ext);
    }

    public static POITextExtractor[] getEmbeddedDocsTextExtractors(POIOLE2TextExtractor ext) throws IOException, OpenXML4JException, XmlException {
        ArrayList<Entry> dirs = new ArrayList<Entry>();
        ArrayList nonPOIFS = new ArrayList();
        DirectoryEntry root = ext.getRoot();
        if (root == null) {
            throw new IllegalStateException("The extractor didn't know which POIFS it came from!");
        }
        if (ext instanceof ExcelExtractor) {
            Iterator<Entry> it = root.getEntries();
            while (it.hasNext()) {
                Entry entry = it.next();
                if (!entry.getName().startsWith("MBD")) continue;
                dirs.add(entry);
            }
        } else {
            try {
                Class<?> clazz = Class.forName("org.apache.poi.extractor.ole2.OLE2ScratchpadExtractorFactory");
                Method m = clazz.getDeclaredMethod("identifyEmbeddedResources", POIOLE2TextExtractor.class, List.class, List.class);
                m.invoke(null, ext, dirs, nonPOIFS);
            } catch (ReflectiveOperationException e) {
                logger.log(5, "POI Scratchpad jar not included ", e.getLocalizedMessage());
                return new POITextExtractor[0];
            }
        }
        if (dirs.size() == 0 && nonPOIFS.size() == 0) {
            return new POITextExtractor[0];
        }
        ArrayList<Object> textExtractors = new ArrayList<Object>();
        for (Entry dir : dirs) {
            textExtractors.add(ExtractorFactory.createExtractor((DirectoryNode)dir));
        }
        for (InputStream nonPOIF : nonPOIFS) {
            try {
                textExtractors.add(ExtractorFactory.createExtractor(nonPOIF));
            } catch (IllegalArgumentException e) {
                logger.log(3, "Format not supported yet", e.getLocalizedMessage());
            } catch (OpenXML4JException | XmlException e) {
                throw new IOException(e.getMessage(), e);
            }
        }
        return textExtractors.toArray(new POITextExtractor[0]);
    }

    @Deprecated
    @Removal(version="4.2")
    @NotImplemented
    public static POITextExtractor[] getEmbededDocsTextExtractors(POIXMLTextExtractor ext) {
        return ExtractorFactory.getEmbeddedDocsTextExtractors(ext);
    }

    @NotImplemented
    public static POITextExtractor[] getEmbeddedDocsTextExtractors(POIXMLTextExtractor ext) {
        throw new IllegalStateException("Not yet supported");
    }

    /*
     * Loose catch block
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    private static POITextExtractor createEncryptedOOXMLExtractor(POIFSFileSystem fs) throws IOException {
        POITextExtractor pOITextExtractor;
        String pass = Biff8EncryptionKey.getCurrentUserPassword();
        if (pass == null) {
            pass = "VelvetSweatshop";
        }
        EncryptionInfo ei = new EncryptionInfo(fs);
        Decryptor dec = ei.getDecryptor();
        InputStream is = null;
        try {
            if (!dec.verifyPassword(pass)) {
                throw new EncryptedDocumentException("Invalid password specified - use Biff8EncryptionKey.setCurrentUserPassword() before calling extractor");
            }
            is = dec.getDataStream(fs);
            pOITextExtractor = ExtractorFactory.createExtractor(OPCPackage.open(is));
        } catch (IOException e) {
            try {
                throw e;
                catch (Exception e2) {
                    throw new EncryptedDocumentException(e2);
                }
            } catch (Throwable throwable) {
                IOUtils.closeQuietly(is);
                fs.close();
                throw throwable;
            }
        }
        IOUtils.closeQuietly(is);
        fs.close();
        return pOITextExtractor;
    }
}

