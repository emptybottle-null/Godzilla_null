/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.compress.compressors.brotli;

public class BrotliUtils {
    private static volatile CachedAvailability cachedBrotliAvailability = CachedAvailability.DONT_CACHE;

    private BrotliUtils() {
    }

    public static boolean isBrotliCompressionAvailable() {
        CachedAvailability cachedResult = cachedBrotliAvailability;
        if (cachedResult != CachedAvailability.DONT_CACHE) {
            return cachedResult == CachedAvailability.CACHED_AVAILABLE;
        }
        return BrotliUtils.internalIsBrotliCompressionAvailable();
    }

    private static boolean internalIsBrotliCompressionAvailable() {
        try {
            Class.forName("org.brotli.dec.BrotliInputStream");
            return true;
        } catch (Exception | NoClassDefFoundError error) {
            return false;
        }
    }

    public static void setCacheBrotliAvailablity(boolean doCache) {
        if (!doCache) {
            cachedBrotliAvailability = CachedAvailability.DONT_CACHE;
        } else if (cachedBrotliAvailability == CachedAvailability.DONT_CACHE) {
            boolean hasBrotli = BrotliUtils.internalIsBrotliCompressionAvailable();
            cachedBrotliAvailability = hasBrotli ? CachedAvailability.CACHED_AVAILABLE : CachedAvailability.CACHED_UNAVAILABLE;
        }
    }

    static CachedAvailability getCachedBrotliAvailability() {
        return cachedBrotliAvailability;
    }

    static {
        try {
            Class.forName("org.osgi.framework.BundleEvent");
        } catch (Exception ex) {
            BrotliUtils.setCacheBrotliAvailablity(true);
        }
    }

    static enum CachedAvailability {
        DONT_CACHE,
        CACHED_AVAILABLE,
        CACHED_UNAVAILABLE;

    }
}

