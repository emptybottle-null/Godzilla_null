/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.extractor;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.apache.poi.extractor.POIOLE2TextExtractor;
import org.apache.poi.extractor.POITextExtractor;
import org.apache.poi.hssf.OldExcelFormatException;
import org.apache.poi.hssf.extractor.EventBasedExcelExtractor;
import org.apache.poi.hssf.extractor.ExcelExtractor;
import org.apache.poi.hssf.model.InternalWorkbook;
import org.apache.poi.poifs.filesystem.DirectoryEntry;
import org.apache.poi.poifs.filesystem.DirectoryNode;
import org.apache.poi.poifs.filesystem.Entry;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.util.POILogFactory;
import org.apache.poi.util.POILogger;

public final class OLE2ExtractorFactory {
    private static final POILogger LOGGER = POILogFactory.getLogger(OLE2ExtractorFactory.class);
    private static final ThreadLocal<Boolean> threadPreferEventExtractors = ThreadLocal.withInitial(() -> Boolean.FALSE);
    private static Boolean allPreferEventExtractors;

    private OLE2ExtractorFactory() {
    }

    public static boolean getThreadPrefersEventExtractors() {
        return threadPreferEventExtractors.get();
    }

    public static Boolean getAllThreadsPreferEventExtractors() {
        return allPreferEventExtractors;
    }

    public static void setThreadPrefersEventExtractors(boolean preferEventExtractors) {
        threadPreferEventExtractors.set(preferEventExtractors);
    }

    public static void setAllThreadsPreferEventExtractors(Boolean preferEventExtractors) {
        allPreferEventExtractors = preferEventExtractors;
    }

    public static boolean getPreferEventExtractor() {
        if (allPreferEventExtractors != null) {
            return allPreferEventExtractors;
        }
        return threadPreferEventExtractors.get();
    }

    public static <T extends POITextExtractor> T createExtractor(POIFSFileSystem fs) throws IOException {
        return (T)OLE2ExtractorFactory.createExtractor(fs.getRoot());
    }

    public static <T extends POITextExtractor> T createExtractor(InputStream input) throws IOException {
        Class<?> cls = OLE2ExtractorFactory.getOOXMLClass();
        if (cls != null) {
            try {
                Method m = cls.getDeclaredMethod("createExtractor", InputStream.class);
                return (T)((POITextExtractor)m.invoke(null, input));
            } catch (IllegalArgumentException iae) {
                throw iae;
            } catch (Exception e) {
                throw new IllegalArgumentException("Error creating Extractor for InputStream", e);
            }
        }
        return OLE2ExtractorFactory.createExtractor(new POIFSFileSystem(input));
    }

    private static Class<?> getOOXMLClass() {
        try {
            return OLE2ExtractorFactory.class.getClassLoader().loadClass("org.apache.poi.extractor.ExtractorFactory");
        } catch (ClassNotFoundException e) {
            LOGGER.log(5, "POI OOXML jar missing");
            return null;
        }
    }

    private static Class<?> getScratchpadClass() {
        try {
            return OLE2ExtractorFactory.class.getClassLoader().loadClass("org.apache.poi.extractor.ole2.OLE2ScratchpadExtractorFactory");
        } catch (ClassNotFoundException e) {
            LOGGER.log(7, "POI Scratchpad jar missing");
            throw new IllegalStateException("POI Scratchpad jar missing, required for ExtractorFactory");
        }
    }

    public static POITextExtractor createExtractor(DirectoryNode poifsDir) throws IOException {
        for (String workbookName : InternalWorkbook.WORKBOOK_DIR_ENTRY_NAMES) {
            if (!poifsDir.hasEntry(workbookName)) continue;
            if (OLE2ExtractorFactory.getPreferEventExtractor()) {
                return new EventBasedExcelExtractor(poifsDir);
            }
            return new ExcelExtractor(poifsDir);
        }
        if (poifsDir.hasEntry("Book")) {
            throw new OldExcelFormatException("Old Excel Spreadsheet format (1-95) found. Please call OldExcelExtractor directly for basic text extraction");
        }
        Class<?> cls = OLE2ExtractorFactory.getScratchpadClass();
        try {
            Method m = cls.getDeclaredMethod("createExtractor", DirectoryNode.class);
            POITextExtractor ext = (POITextExtractor)m.invoke(null, poifsDir);
            if (ext != null) {
                return ext;
            }
        } catch (IllegalArgumentException iae) {
            throw iae;
        } catch (Exception e) {
            throw new IllegalArgumentException("Error creating Scratchpad Extractor", e);
        }
        throw new IllegalArgumentException("No supported documents found in the OLE2 stream");
    }

    public static POITextExtractor[] getEmbededDocsTextExtractors(POIOLE2TextExtractor ext) throws IOException {
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
            Class<?> cls = OLE2ExtractorFactory.getScratchpadClass();
            try {
                Method m = cls.getDeclaredMethod("identifyEmbeddedResources", POIOLE2TextExtractor.class, List.class, List.class);
                m.invoke(null, ext, dirs, nonPOIFS);
            } catch (Exception e) {
                throw new IllegalArgumentException("Error checking for Scratchpad embedded resources", e);
            }
        }
        if (dirs.size() == 0 && nonPOIFS.size() == 0) {
            return new POITextExtractor[0];
        }
        ArrayList<POITextExtractor> e = new ArrayList<POITextExtractor>();
        for (Entry dir : dirs) {
            e.add(OLE2ExtractorFactory.createExtractor((DirectoryNode)dir));
        }
        for (InputStream stream : nonPOIFS) {
            try {
                e.add((POITextExtractor)OLE2ExtractorFactory.createExtractor(stream));
            } catch (Exception xe) {
                LOGGER.log(5, xe);
            }
        }
        return e.toArray(new POITextExtractor[0]);
    }
}

