/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.springframework.util;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Random;
import java.util.UUID;
import org.springframework.util.IdGenerator;

public class AlternativeJdkIdGenerator
implements IdGenerator {
    private final Random random;

    public AlternativeJdkIdGenerator() {
        SecureRandom secureRandom = new SecureRandom();
        byte[] seed = new byte[8];
        secureRandom.nextBytes(seed);
        this.random = new Random(new BigInteger(seed).longValue());
    }

    @Override
    public UUID generateId() {
        byte[] randomBytes = new byte[16];
        this.random.nextBytes(randomBytes);
        long mostSigBits = 0L;
        for (int i = 0; i < 8; ++i) {
            mostSigBits = mostSigBits << 8 | (long)(randomBytes[i] & 0xFF);
        }
        long leastSigBits = 0L;
        for (int i = 8; i < 16; ++i) {
            leastSigBits = leastSigBits << 8 | (long)(randomBytes[i] & 0xFF);
        }
        return new UUID(mostSigBits, leastSigBits);
    }
}

