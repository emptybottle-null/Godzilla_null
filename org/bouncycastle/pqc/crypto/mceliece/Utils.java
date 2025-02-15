/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.pqc.crypto.mceliece;

import org.bouncycastle.crypto.Digest;
import org.bouncycastle.crypto.digests.SHA1Digest;
import org.bouncycastle.crypto.digests.SHA224Digest;
import org.bouncycastle.crypto.digests.SHA256Digest;
import org.bouncycastle.crypto.digests.SHA384Digest;
import org.bouncycastle.crypto.digests.SHA512Digest;

class Utils {
    Utils() {
    }

    static Digest getDigest(String string) {
        if (string.equals("SHA-1")) {
            return new SHA1Digest();
        }
        if (string.equals("SHA-224")) {
            return new SHA224Digest();
        }
        if (string.equals("SHA-256")) {
            return new SHA256Digest();
        }
        if (string.equals("SHA-384")) {
            return new SHA384Digest();
        }
        if (string.equals("SHA-512")) {
            return new SHA512Digest();
        }
        throw new IllegalArgumentException("unrecognised digest algorithm: " + string);
    }
}

