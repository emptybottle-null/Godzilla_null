/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.jcajce.provider.symmetric;

import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import org.bouncycastle.crypto.Mac;
import org.bouncycastle.crypto.digests.SHA256Digest;
import org.bouncycastle.crypto.digests.SHA384Digest;
import org.bouncycastle.crypto.digests.SHA512Digest;
import org.bouncycastle.crypto.macs.HMac;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.crypto.util.DigestFactory;
import org.bouncycastle.jcajce.provider.config.ConfigurableProvider;
import org.bouncycastle.jcajce.provider.symmetric.util.BaseSecretKeyFactory;
import org.bouncycastle.jcajce.provider.util.AlgorithmProvider;
import org.bouncycastle.jcajce.spec.TLSKeyMaterialSpec;
import org.bouncycastle.util.Arrays;
import org.bouncycastle.util.Strings;

public class TLSKDF {
    private static byte[] PRF_legacy(TLSKeyMaterialSpec tLSKeyMaterialSpec) {
        HMac hMac = new HMac(DigestFactory.createMD5());
        HMac hMac2 = new HMac(DigestFactory.createSHA1());
        byte[] byArray = Strings.toByteArray(tLSKeyMaterialSpec.getLabel());
        byte[] byArray2 = Arrays.concatenate(byArray, tLSKeyMaterialSpec.getSeed());
        byte[] byArray3 = tLSKeyMaterialSpec.getSecret();
        int n = (byArray3.length + 1) / 2;
        byte[] byArray4 = new byte[n];
        byte[] byArray5 = new byte[n];
        System.arraycopy(byArray3, 0, byArray4, 0, n);
        System.arraycopy(byArray3, byArray3.length - n, byArray5, 0, n);
        int n2 = tLSKeyMaterialSpec.getLength();
        byte[] byArray6 = new byte[n2];
        byte[] byArray7 = new byte[n2];
        TLSKDF.hmac_hash(hMac, byArray4, byArray2, byArray6);
        TLSKDF.hmac_hash(hMac2, byArray5, byArray2, byArray7);
        for (int i = 0; i < n2; ++i) {
            int n3 = i;
            byArray6[n3] = (byte)(byArray6[n3] ^ byArray7[i]);
        }
        return byArray6;
    }

    private static void hmac_hash(Mac mac, byte[] byArray, byte[] byArray2, byte[] byArray3) {
        mac.init(new KeyParameter(byArray));
        byte[] byArray4 = byArray2;
        int n = mac.getMacSize();
        int n2 = (byArray3.length + n - 1) / n;
        byte[] byArray5 = new byte[mac.getMacSize()];
        byte[] byArray6 = new byte[mac.getMacSize()];
        for (int i = 0; i < n2; ++i) {
            mac.update(byArray4, 0, byArray4.length);
            mac.doFinal(byArray5, 0);
            byArray4 = byArray5;
            mac.update(byArray4, 0, byArray4.length);
            mac.update(byArray2, 0, byArray2.length);
            mac.doFinal(byArray6, 0);
            System.arraycopy(byArray6, 0, byArray3, n * i, Math.min(n, byArray3.length - n * i));
        }
    }

    public static class Mappings
    extends AlgorithmProvider {
        private static final String PREFIX = TLSKDF.class.getName();

        public void configure(ConfigurableProvider configurableProvider) {
            configurableProvider.addAlgorithm("SecretKeyFactory.TLS10KDF", PREFIX + "$TLS10");
            configurableProvider.addAlgorithm("SecretKeyFactory.TLS11KDF", PREFIX + "$TLS11");
            configurableProvider.addAlgorithm("SecretKeyFactory.TLS12WITHSHA256KDF", PREFIX + "$TLS12withSHA256");
            configurableProvider.addAlgorithm("SecretKeyFactory.TLS12WITHSHA384KDF", PREFIX + "$TLS12withSHA384");
            configurableProvider.addAlgorithm("SecretKeyFactory.TLS12WITHSHA512KDF", PREFIX + "$TLS12withSHA512");
        }
    }

    public static final class TLS10
    extends TLSKeyMaterialFactory {
        public TLS10() {
            super("TLS10KDF");
        }

        protected SecretKey engineGenerateSecret(KeySpec keySpec) throws InvalidKeySpecException {
            if (keySpec instanceof TLSKeyMaterialSpec) {
                return new SecretKeySpec(TLSKDF.PRF_legacy((TLSKeyMaterialSpec)keySpec), this.algName);
            }
            throw new InvalidKeySpecException("Invalid KeySpec");
        }
    }

    public static final class TLS11
    extends TLSKeyMaterialFactory {
        public TLS11() {
            super("TLS11KDF");
        }

        protected SecretKey engineGenerateSecret(KeySpec keySpec) throws InvalidKeySpecException {
            if (keySpec instanceof TLSKeyMaterialSpec) {
                return new SecretKeySpec(TLSKDF.PRF_legacy((TLSKeyMaterialSpec)keySpec), this.algName);
            }
            throw new InvalidKeySpecException("Invalid KeySpec");
        }
    }

    public static class TLS12
    extends TLSKeyMaterialFactory {
        private final Mac prf;

        protected TLS12(String string, Mac mac) {
            super(string);
            this.prf = mac;
        }

        protected SecretKey engineGenerateSecret(KeySpec keySpec) throws InvalidKeySpecException {
            if (keySpec instanceof TLSKeyMaterialSpec) {
                return new SecretKeySpec(this.PRF((TLSKeyMaterialSpec)keySpec, this.prf), this.algName);
            }
            throw new InvalidKeySpecException("Invalid KeySpec");
        }

        private byte[] PRF(TLSKeyMaterialSpec tLSKeyMaterialSpec, Mac mac) {
            byte[] byArray = Strings.toByteArray(tLSKeyMaterialSpec.getLabel());
            byte[] byArray2 = Arrays.concatenate(byArray, tLSKeyMaterialSpec.getSeed());
            byte[] byArray3 = tLSKeyMaterialSpec.getSecret();
            byte[] byArray4 = new byte[tLSKeyMaterialSpec.getLength()];
            TLSKDF.hmac_hash(mac, byArray3, byArray2, byArray4);
            return byArray4;
        }
    }

    public static final class TLS12withSHA256
    extends TLS12 {
        public TLS12withSHA256() {
            super("TLS12withSHA256KDF", new HMac(new SHA256Digest()));
        }
    }

    public static final class TLS12withSHA384
    extends TLS12 {
        public TLS12withSHA384() {
            super("TLS12withSHA384KDF", new HMac(new SHA384Digest()));
        }
    }

    public static final class TLS12withSHA512
    extends TLS12 {
        public TLS12withSHA512() {
            super("TLS12withSHA512KDF", new HMac(new SHA512Digest()));
        }
    }

    public static class TLSKeyMaterialFactory
    extends BaseSecretKeyFactory {
        protected TLSKeyMaterialFactory(String string) {
            super(string, null);
        }
    }
}

