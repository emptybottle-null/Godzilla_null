/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.compress.compressors.zstandard;

public class ZstdUtils {
    private static final byte[] ZSTANDARD_FRAME_MAGIC = new byte[]{40, -75, 47, -3};
    private static final byte[] SKIPPABLE_FRAME_MAGIC = new byte[]{42, 77, 24};
    private static volatile CachedAvailability cachedZstdAvailability = CachedAvailability.DONT_CACHE;

    private ZstdUtils() {
    }

    public static boolean isZstdCompressionAvailable() {
        CachedAvailability cachedResult = cachedZstdAvailability;
        if (cachedResult != CachedAvailability.DONT_CACHE) {
            return cachedResult == CachedAvailability.CACHED_AVAILABLE;
        }
        return ZstdUtils.internalIsZstdCompressionAvailable();
    }

    private static boolean internalIsZstdCompressionAvailable() {
        try {
            Class.forName("com.github.luben.zstd.ZstdInputStream");
            return true;
        } catch (Exception | NoClassDefFoundError error) {
            return false;
        }
    }

    public static void setCacheZstdAvailablity(boolean doCache) {
        if (!doCache) {
            cachedZstdAvailability = CachedAvailability.DONT_CACHE;
        } else if (cachedZstdAvailability == CachedAvailability.DONT_CACHE) {
            boolean hasZstd = ZstdUtils.internalIsZstdCompressionAvailable();
            cachedZstdAvailability = hasZstd ? CachedAvailability.CACHED_AVAILABLE : CachedAvailability.CACHED_UNAVAILABLE;
        }
    }

    public static boolean matches(byte[] signature, int length) {
        int i;
        if (length < ZSTANDARD_FRAME_MAGIC.length) {
            return false;
        }
        boolean isZstandard = true;
        for (i = 0; i < ZSTANDARD_FRAME_MAGIC.length; ++i) {
            if (signature[i] == ZSTANDARD_FRAME_MAGIC[i]) continue;
            isZstandard = false;
            break;
        }
        if (isZstandard) {
            return true;
        }
        if (80 == (signature[0] & 0xF0)) {
            for (i = 0; i < SKIPPABLE_FRAME_MAGIC.length; ++i) {
                if (signature[i + 1] == SKIPPABLE_FRAME_MAGIC[i]) continue;
                return false;
            }
            return true;
        }
        return false;
    }

    static CachedAvailability getCachedZstdAvailability() {
        return cachedZstdAvailability;
    }

    static {
        try {
            Class.forName("org.osgi.framework.BundleEvent");
        } catch (Exception ex) {
            ZstdUtils.setCacheZstdAvailablity(true);
        }
    }

    static enum CachedAvailability {
        DONT_CACHE,
        CACHED_AVAILABLE,
        CACHED_UNAVAILABLE;

    }
}

