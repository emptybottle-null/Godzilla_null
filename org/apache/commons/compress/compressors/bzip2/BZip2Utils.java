/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.compress.compressors.bzip2;

import java.util.LinkedHashMap;
import org.apache.commons.compress.compressors.FileNameUtil;

public abstract class BZip2Utils {
    private static final FileNameUtil fileNameUtil;

    private BZip2Utils() {
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
        uncompressSuffix.put(".tar.bz2", ".tar");
        uncompressSuffix.put(".tbz2", ".tar");
        uncompressSuffix.put(".tbz", ".tar");
        uncompressSuffix.put(".bz2", "");
        uncompressSuffix.put(".bz", "");
        fileNameUtil = new FileNameUtil(uncompressSuffix, ".bz2");
    }
}

