/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.compress.compressors;

import java.util.Collections;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class FileNameUtil {
    private final Map<String, String> compressSuffix = new HashMap<String, String>();
    private final Map<String, String> uncompressSuffix;
    private final int longestCompressedSuffix;
    private final int shortestCompressedSuffix;
    private final int longestUncompressedSuffix;
    private final int shortestUncompressedSuffix;
    private final String defaultExtension;

    public FileNameUtil(Map<String, String> uncompressSuffix, String defaultExtension) {
        this.uncompressSuffix = Collections.unmodifiableMap(uncompressSuffix);
        int lc = Integer.MIN_VALUE;
        int sc = Integer.MAX_VALUE;
        int lu = Integer.MIN_VALUE;
        int su = Integer.MAX_VALUE;
        for (Map.Entry<String, String> ent : uncompressSuffix.entrySet()) {
            String u;
            int ul;
            int cl = ent.getKey().length();
            if (cl > lc) {
                lc = cl;
            }
            if (cl < sc) {
                sc = cl;
            }
            if ((ul = (u = ent.getValue()).length()) <= 0) continue;
            if (!this.compressSuffix.containsKey(u)) {
                this.compressSuffix.put(u, ent.getKey());
            }
            if (ul > lu) {
                lu = ul;
            }
            if (ul >= su) continue;
            su = ul;
        }
        this.longestCompressedSuffix = lc;
        this.longestUncompressedSuffix = lu;
        this.shortestCompressedSuffix = sc;
        this.shortestUncompressedSuffix = su;
        this.defaultExtension = defaultExtension;
    }

    public boolean isCompressedFilename(String fileName) {
        String lower = fileName.toLowerCase(Locale.ENGLISH);
        int n = lower.length();
        for (int i = this.shortestCompressedSuffix; i <= this.longestCompressedSuffix && i < n; ++i) {
            if (!this.uncompressSuffix.containsKey(lower.substring(n - i))) continue;
            return true;
        }
        return false;
    }

    public String getUncompressedFilename(String fileName) {
        String lower = fileName.toLowerCase(Locale.ENGLISH);
        int n = lower.length();
        for (int i = this.shortestCompressedSuffix; i <= this.longestCompressedSuffix && i < n; ++i) {
            String suffix = this.uncompressSuffix.get(lower.substring(n - i));
            if (suffix == null) continue;
            return fileName.substring(0, n - i) + suffix;
        }
        return fileName;
    }

    public String getCompressedFilename(String fileName) {
        String lower = fileName.toLowerCase(Locale.ENGLISH);
        int n = lower.length();
        for (int i = this.shortestUncompressedSuffix; i <= this.longestUncompressedSuffix && i < n; ++i) {
            String suffix = this.compressSuffix.get(lower.substring(n - i));
            if (suffix == null) continue;
            return fileName.substring(0, n - i) + suffix;
        }
        return fileName + this.defaultExtension;
    }
}

