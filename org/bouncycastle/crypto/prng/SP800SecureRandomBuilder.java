/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.crypto.prng;

import java.security.SecureRandom;
import org.bouncycastle.crypto.BlockCipher;
import org.bouncycastle.crypto.Digest;
import org.bouncycastle.crypto.Mac;
import org.bouncycastle.crypto.prng.BasicEntropySourceProvider;
import org.bouncycastle.crypto.prng.DRBGProvider;
import org.bouncycastle.crypto.prng.EntropySource;
import org.bouncycastle.crypto.prng.EntropySourceProvider;
import org.bouncycastle.crypto.prng.SP800SecureRandom;
import org.bouncycastle.crypto.prng.drbg.CTRSP800DRBG;
import org.bouncycastle.crypto.prng.drbg.HMacSP800DRBG;
import org.bouncycastle.crypto.prng.drbg.HashSP800DRBG;
import org.bouncycastle.crypto.prng.drbg.SP80090DRBG;

public class SP800SecureRandomBuilder {
    private final SecureRandom random;
    private final EntropySourceProvider entropySourceProvider;
    private byte[] personalizationString;
    private int securityStrength = 256;
    private int entropyBitsRequired = 256;

    public SP800SecureRandomBuilder() {
        this(new SecureRandom(), false);
    }

    public SP800SecureRandomBuilder(SecureRandom secureRandom, boolean bl) {
        this.random = secureRandom;
        this.entropySourceProvider = new BasicEntropySourceProvider(this.random, bl);
    }

    public SP800SecureRandomBuilder(EntropySourceProvider entropySourceProvider) {
        this.random = null;
        this.entropySourceProvider = entropySourceProvider;
    }

    public SP800SecureRandomBuilder setPersonalizationString(byte[] byArray) {
        this.personalizationString = byArray;
        return this;
    }

    public SP800SecureRandomBuilder setSecurityStrength(int n) {
        this.securityStrength = n;
        return this;
    }

    public SP800SecureRandomBuilder setEntropyBitsRequired(int n) {
        this.entropyBitsRequired = n;
        return this;
    }

    public SP800SecureRandom buildHash(Digest digest, byte[] byArray, boolean bl) {
        return new SP800SecureRandom(this.random, this.entropySourceProvider.get(this.entropyBitsRequired), new HashDRBGProvider(digest, byArray, this.personalizationString, this.securityStrength), bl);
    }

    public SP800SecureRandom buildCTR(BlockCipher blockCipher, int n, byte[] byArray, boolean bl) {
        return new SP800SecureRandom(this.random, this.entropySourceProvider.get(this.entropyBitsRequired), new CTRDRBGProvider(blockCipher, n, byArray, this.personalizationString, this.securityStrength), bl);
    }

    public SP800SecureRandom buildHMAC(Mac mac, byte[] byArray, boolean bl) {
        return new SP800SecureRandom(this.random, this.entropySourceProvider.get(this.entropyBitsRequired), new HMacDRBGProvider(mac, byArray, this.personalizationString, this.securityStrength), bl);
    }

    private static class CTRDRBGProvider
    implements DRBGProvider {
        private final BlockCipher blockCipher;
        private final int keySizeInBits;
        private final byte[] nonce;
        private final byte[] personalizationString;
        private final int securityStrength;

        public CTRDRBGProvider(BlockCipher blockCipher, int n, byte[] byArray, byte[] byArray2, int n2) {
            this.blockCipher = blockCipher;
            this.keySizeInBits = n;
            this.nonce = byArray;
            this.personalizationString = byArray2;
            this.securityStrength = n2;
        }

        public SP80090DRBG get(EntropySource entropySource) {
            return new CTRSP800DRBG(this.blockCipher, this.keySizeInBits, this.securityStrength, entropySource, this.personalizationString, this.nonce);
        }
    }

    private static class HMacDRBGProvider
    implements DRBGProvider {
        private final Mac hMac;
        private final byte[] nonce;
        private final byte[] personalizationString;
        private final int securityStrength;

        public HMacDRBGProvider(Mac mac, byte[] byArray, byte[] byArray2, int n) {
            this.hMac = mac;
            this.nonce = byArray;
            this.personalizationString = byArray2;
            this.securityStrength = n;
        }

        public SP80090DRBG get(EntropySource entropySource) {
            return new HMacSP800DRBG(this.hMac, this.securityStrength, entropySource, this.personalizationString, this.nonce);
        }
    }

    private static class HashDRBGProvider
    implements DRBGProvider {
        private final Digest digest;
        private final byte[] nonce;
        private final byte[] personalizationString;
        private final int securityStrength;

        public HashDRBGProvider(Digest digest, byte[] byArray, byte[] byArray2, int n) {
            this.digest = digest;
            this.nonce = byArray;
            this.personalizationString = byArray2;
            this.securityStrength = n;
        }

        public SP80090DRBG get(EntropySource entropySource) {
            return new HashSP800DRBG(this.digest, this.securityStrength, entropySource, this.personalizationString, this.nonce);
        }
    }
}

