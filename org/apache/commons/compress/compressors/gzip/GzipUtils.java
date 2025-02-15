/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.compress.compressors.gzip;

import java.util.LinkedHashMap;
import org.apache.commons.compress.compressors.FileNameUtil;

public class GzipUtils {
    private static final FileNameUtil fileNameUtil;

    private GzipUtils() {
    }

    public static boolean isCompressedFilename(String fileName) {
        return fileNameUtil.isCompressedFilename(fileName);
    }

    public static String getUncompressedFilename(String fileName) {
        return fileNameUtil.getUncompressedFilename(fileName);
    }

    public static String getCompressedFilename(String fileName) {
        return fileNameUtil.getCompressedFilename(fileName);
    }

    static {
        LinkedHashMap<String, String> uncompressSuffix = new LinkedHashMap<String, String>();
        uncompressSuffix.put(".tgz", ".tar");
        uncompressSuffix.put(".taz", ".tar");
        uncompressSuffix.put(".svgz", ".svg");
        uncompressSuffix.put(".cpgz", ".cpio");
        uncompressSuffix.put(".wmz", ".wmf");
        uncompressSuffix.put(".emz", ".emf");
        uncompressSuffix.put(".gz", "");
        uncompressSuffix.put(".z", "");
        uncompressSuffix.put("-gz", "");
        uncompressSuffix.put("-z", "");
        uncompressSuffix.put("_z", "");
        fileNameUtil = new FileNameUtil(uncompressSuffix, ".gz");
    }
}

