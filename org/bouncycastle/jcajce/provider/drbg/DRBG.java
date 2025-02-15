/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.jcajce.provider.drbg;

import java.security.AccessController;
import java.security.PrivilegedAction;
import java.security.Provider;
import java.security.SecureRandom;
import java.security.SecureRandomSpi;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import org.bouncycastle.crypto.digests.SHA512Digest;
import org.bouncycastle.crypto.macs.HMac;
import org.bouncycastle.crypto.prng.EntropySource;
import org.bouncycastle.crypto.prng.EntropySourceProvider;
import org.bouncycastle.crypto.prng.SP800SecureRandom;
import org.bouncycastle.crypto.prng.SP800SecureRandomBuilder;
import org.bouncycastle.jcajce.provider.config.ConfigurableProvider;
import org.bouncycastle.jcajce.provider.symmetric.util.ClassUtil;
import org.bouncycastle.jcajce.provider.util.AsymmetricAlgorithmProvider;
import org.bouncycastle.util.Arrays;
import org.bouncycastle.util.Pack;
import org.bouncycastle.util.Strings;

public class DRBG {
    private static final String PREFIX = DRBG.class.getName();
    private static final String[][] initialEntropySourceNames = new String[][]{{"sun.security.provider.Sun", "sun.security.provider.SecureRandom"}, {"org.apache.harmony.security.provider.crypto.CryptoProvider", "org.apache.harmony.security.provider.crypto.SHA1PRNG_SecureRandomImpl"}, {"com.android.org.conscrypt.OpenSSLProvider", "com.android.org.conscrypt.OpenSSLRandom"}, {"org.conscrypt.OpenSSLProvider", "org.conscrypt.OpenSSLRandom"}};
    private static final Object[] initialEntropySourceAndSpi = DRBG.findSource();

    private static final Object[] findSource() {
        for (int i = 0; i < initialEntropySourceNames.length; ++i) {
            String[] stringArray = initialEntropySourceNames[i];
            try {
                Object[] objectArray = new Object[]{Class.forName(stringArray[0]).newInstance(), Class.forName(stringArray[1]).newInstance()};
                return objectArray;
            } catch (Throwable throwable) {
                continue;
            }
        }
        return null;
    }

    private static SecureRandom createInitialEntropySource() {
        if (initialEntropySourceAndSpi != null) {
            return new CoreSecureRandom();
        }
        return new SecureRandom();
    }

    private static EntropySourceProvider createEntropySource() {
        final String string = System.getProperty("org.bouncycastle.drbg.entropysource");
        return AccessController.doPrivileged(new PrivilegedAction<EntropySourceProvider>(){

            @Override
            public EntropySourceProvider run() {
                try {
                    Class clazz = ClassUtil.loadClass(DRBG.class, string);
                    return (EntropySourceProvider)clazz.newInstance();
                } catch (Exception exception) {
                    throw new IllegalStateException("entropy source " + string + " not created: " + exception.getMessage(), exception);
                }
            }
        });
    }

    private static SecureRandom createBaseRandom(boolean bl) {
        if (System.getProperty("org.bouncycastle.drbg.entropysource") != null) {
            EntropySourceProvider entropySourceProvider = DRBG.createEntropySource();
            EntropySource entropySource = entropySourceProvider.get(128);
            byte[] byArray = bl ? DRBG.generateDefaultPersonalizationString(entropySource.getEntropy()) : DRBG.generateNonceIVPersonalizationString(entropySource.getEntropy());
            return new SP800SecureRandomBuilder(entropySourceProvider).setPersonalizationString(byArray).buildHash(new SHA512Digest(), Arrays.concatenate(entropySource.getEntropy(), entropySource.getEntropy()), bl);
        }
        HybridSecureRandom hybridSecureRandom = new HybridSecureRandom();
        byte[] byArray = bl ? DRBG.generateDefaultPersonalizationString(((SecureRandom)hybridSecureRandom).generateSeed(16)) : DRBG.generateNonceIVPersonalizationString(((SecureRandom)hybridSecureRandom).generateSeed(16));
        return new SP800SecureRandomBuilder(hybridSecureRandom, true).setPersonalizationString(byArray).buildHash(new SHA512Digest(), ((SecureRandom)hybridSecureRandom).generateSeed(32), bl);
    }

    private static byte[] generateDefaultPersonalizationString(byte[] byArray) {
        return Arrays.concatenate(Strings.toByteArray("Default"), byArray, Pack.longToBigEndian(Thread.currentThread().getId()), Pack.longToBigEndian(System.currentTimeMillis()));
    }

    private static byte[] generateNonceIVPersonalizationString(byte[] byArray) {
        return Arrays.concatenate(Strings.toByteArray("Nonce"), byArray, Pack.longToLittleEndian(Thread.currentThread().getId()), Pack.longToLittleEndian(System.currentTimeMillis()));
    }

    static /* synthetic */ SecureRandom access$100(boolean bl) {
        return DRBG.createBaseRandom(bl);
    }

    static /* synthetic */ SecureRandom access$300() {
        return DRBG.createInitialEntropySource();
    }

    private static class CoreSecureRandom
    extends SecureRandom {
        CoreSecureRandom() {
            super((SecureRandomSpi)initialEntropySourceAndSpi[1], (Provider)initialEntropySourceAndSpi[0]);
        }
    }

    public static class Default
    extends SecureRandomSpi {
        private static final SecureRandom random = DRBG.access$100(true);

        protected void engineSetSeed(byte[] byArray) {
            random.setSeed(byArray);
        }

        protected void engineNextBytes(byte[] byArray) {
            random.nextBytes(byArray);
        }

        protected byte[] engineGenerateSeed(int n) {
            return random.generateSeed(n);
        }
    }

    private static class HybridSecureRandom
    extends SecureRandom {
        private final AtomicBoolean seedAvailable = new AtomicBoolean(false);
        private final AtomicInteger samples = new AtomicInteger(0);
        private final SecureRandom baseRandom = DRBG.access$300();
        private final SP800SecureRandom drbg = new SP800SecureRandomBuilder(new EntropySourceProvider(){

            public EntropySource get(int n) {
                return new SignallingEntropySource(n);
            }
        }).setPersonalizationString(Strings.toByteArray("Bouncy Castle Hybrid Entropy Source")).buildHMAC(new HMac(new SHA512Digest()), this.baseRandom.generateSeed(32), false);

        HybridSecureRandom() {
            super(null, null);
        }

        public void setSeed(byte[] byArray) {
            if (this.drbg != null) {
                this.drbg.setSeed(byArray);
            }
        }

        public void setSeed(long l) {
            if (this.drbg != null) {
                this.drbg.setSeed(l);
            }
        }

        public byte[] generateSeed(int n) {
            byte[] byArray = new byte[n];
            if (this.samples.getAndIncrement() > 20 && this.seedAvailable.getAndSet(false)) {
                this.samples.set(0);
                this.drbg.reseed(null);
            }
            this.drbg.nextBytes(byArray);
            return byArray;
        }

        private class SignallingEntropySource
        implements EntropySource {
            private final int byteLength;
            private final AtomicReference entropy = new AtomicReference();
            private final AtomicBoolean scheduled = new AtomicBoolean(false);

            SignallingEntropySource(int n) {
                this.byteLength = (n + 7) / 8;
            }

            public boolean isPredictionResistant() {
                return true;
            }

            public byte[] getEntropy() {
                byte[] byArray = this.entropy.getAndSet(null);
                if (byArray == null || byArray.length != this.byteLength) {
                    byArray = HybridSecureRandom.this.baseRandom.generateSeed(this.byteLength);
                } else {
                    this.scheduled.set(false);
                }
                if (!this.scheduled.getAndSet(true)) {
                    new Thread(new EntropyGatherer(this.byteLength)).start();
                }
                return byArray;
            }

            public int entropySize() {
                return this.byteLength * 8;
            }

            private class EntropyGatherer
            implements Runnable {
                private final int numBytes;

                EntropyGatherer(int n) {
                    this.numBytes = n;
                }

                public void run() {
                    SignallingEntropySource.this.entropy.set(HybridSecureRandom.this.baseRandom.generateSeed(this.numBytes));
                    HybridSecureRandom.this.seedAvailable.set(true);
                }
            }
        }
    }

    public static class Mappings
    extends AsymmetricAlgorithmProvider {
        public void configure(ConfigurableProvider configurableProvider) {
            configurableProvider.addAlgorithm("SecureRandom.DEFAULT", PREFIX + "$Default");
            configurableProvider.addAlgorithm("SecureRandom.NONCEANDIV", PREFIX + "$NonceAndIV");
        }
    }

    public static class NonceAndIV
    extends SecureRandomSpi {
        private static final SecureRandom random = DRBG.access$100(false);

        protected void engineSetSeed(byte[] byArray) {
            random.setSeed(byArray);
        }

        protected void engineNextBytes(byte[] byArray) {
            random.nextBytes(byArray);
        }

        protected byte[] engineGenerateSeed(int n) {
            return random.generateSeed(n);
        }
    }
}

