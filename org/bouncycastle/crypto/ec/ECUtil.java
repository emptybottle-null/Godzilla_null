/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.crypto.ec;

import java.math.BigInteger;
import java.security.SecureRandom;
import org.bouncycastle.math.ec.ECConstants;

class ECUtil {
    ECUtil() {
    }

    static BigInteger generateK(BigInteger bigInteger, SecureRandom secureRandom) {
        BigInteger bigInteger2;
        int n = bigInteger.bitLength();
        while ((bigInteger2 = new BigInteger(n, secureRandom)).equals(ECConstants.ZERO) || bigInteger2.compareTo(bigInteger) >= 0) {
        }
        return bigInteger2;
    }
}

