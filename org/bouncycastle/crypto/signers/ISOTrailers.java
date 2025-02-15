/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.crypto.signers;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import org.bouncycastle.crypto.Digest;
import org.bouncycastle.util.Integers;

public class ISOTrailers {
    private static final Map<String, Integer> trailerMap;
    public static final int TRAILER_IMPLICIT = 188;
    public static final int TRAILER_RIPEMD160 = 12748;
    public static final int TRAILER_RIPEMD128 = 13004;
    public static final int TRAILER_SHA1 = 13260;
    public static final int TRAILER_SHA256 = 13516;
    public static final int TRAILER_SHA512 = 13772;
    public static final int TRAILER_SHA384 = 14028;
    public static final int TRAILER_WHIRLPOOL = 14284;
    public static final int TRAILER_SHA224 = 14540;
    public static final int TRAILER_SHA512_224 = 14796;
    public static final int TRAILER_SHA512_256 = 16588;

    public static Integer getTrailer(Digest digest) {
        return trailerMap.get(digest.getAlgorithmName());
    }

    public static boolean noTrailerAvailable(Digest digest) {
        return !trailerMap.containsKey(digest.getAlgorithmName());
    }

    static {
        HashMap<String, Integer> hashMap = new HashMap<String, Integer>();
        hashMap.put("RIPEMD128", Integers.valueOf(13004));
        hashMap.put("RIPEMD160", Integers.valueOf(12748));
        hashMap.put("SHA-1", Integers.valueOf(13260));
        hashMap.put("SHA-224", Integers.valueOf(14540));
        hashMap.put("SHA-256", Integers.valueOf(13516));
        hashMap.put("SHA-384", Integers.valueOf(14028));
        hashMap.put("SHA-512", Integers.valueOf(13772));
        hashMap.put("SHA-512/224", Integers.valueOf(14796));
        hashMap.put("SHA-512/256", Integers.valueOf(16588));
        hashMap.put("Whirlpool", Integers.valueOf(14284));
        trailerMap = Collections.unmodifiableMap(hashMap);
    }
}

