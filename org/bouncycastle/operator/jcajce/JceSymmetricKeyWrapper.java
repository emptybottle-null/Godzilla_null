/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.operator.jcajce;

import java.security.GeneralSecurityException;
import java.security.Key;
import java.security.Provider;
import java.security.SecureRandom;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import org.bouncycastle.asn1.ASN1Integer;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.DERNull;
import org.bouncycastle.asn1.kisa.KISAObjectIdentifiers;
import org.bouncycastle.asn1.nist.NISTObjectIdentifiers;
import org.bouncycastle.asn1.ntt.NTTObjectIdentifiers;
import org.bouncycastle.asn1.pkcs.PKCSObjectIdentifiers;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.jcajce.util.DefaultJcaJceHelper;
import org.bouncycastle.jcajce.util.NamedJcaJceHelper;
import org.bouncycastle.jcajce.util.ProviderJcaJceHelper;
import org.bouncycastle.operator.GenericKey;
import org.bouncycastle.operator.OperatorException;
import org.bouncycastle.operator.SymmetricKeyWrapper;
import org.bouncycastle.operator.jcajce.OperatorHelper;
import org.bouncycastle.operator.jcajce.OperatorUtils;

public class JceSymmetricKeyWrapper
extends SymmetricKeyWrapper {
    private OperatorHelper helper = new OperatorHelper(new DefaultJcaJceHelper());
    private SecureRandom random;
    private SecretKey wrappingKey;

    public JceSymmetricKeyWrapper(SecretKey secretKey) {
        super(JceSymmetricKeyWrapper.determineKeyEncAlg(secretKey));
        this.wrappingKey = secretKey;
    }

    public JceSymmetricKeyWrapper setProvider(Provider provider) {
        this.helper = new OperatorHelper(new ProviderJcaJceHelper(provider));
        return this;
    }

    public JceSymmetricKeyWrapper setProvider(String string) {
        this.helper = new OperatorHelper(new NamedJcaJceHelper(string));
        return this;
    }

    public JceSymmetricKeyWrapper setSecureRandom(SecureRandom secureRandom) {
        this.random = secureRandom;
        return this;
    }

    public byte[] generateWrappedKey(GenericKey genericKey) throws OperatorException {
        Key key = OperatorUtils.getJceKey(genericKey);
        Cipher cipher = this.helper.createSymmetricWrapper(this.getAlgorithmIdentifier().getAlgorithm());
        try {
            cipher.init(3, (Key)this.wrappingKey, this.random);
            return cipher.wrap(key);
        } catch (GeneralSecurityException generalSecurityException) {
            throw new OperatorException("cannot wrap key: " + generalSecurityException.getMessage(), generalSecurityException);
        }
    }

    private static AlgorithmIdentifier determineKeyEncAlg(SecretKey secretKey) {
        return JceSymmetricKeyWrapper.determineKeyEncAlg(secretKey.getAlgorithm(), secretKey.getEncoded().length * 8);
    }

    static AlgorithmIdentifier determineKeyEncAlg(String string, int n) {
        if (string.startsWith("DES") || string.startsWith("TripleDES")) {
            return new AlgorithmIdentifier(PKCSObjectIdentifiers.id_alg_CMS3DESwrap, DERNull.INSTANCE);
        }
        if (string.startsWith("RC2")) {
            return new AlgorithmIdentifier(new ASN1ObjectIdentifier("1.2.840.113549.1.9.16.3.7"), new ASN1Integer(58L));
        }
        if (string.startsWith("AES")) {
            ASN1ObjectIdentifier aSN1ObjectIdentifier;
            if (n == 128) {
                aSN1ObjectIdentifier = NISTObjectIdentifiers.id_aes128_wrap;
            } else if (n == 192) {
                aSN1ObjectIdentifier = NISTObjectIdentifiers.id_aes192_wrap;
            } else if (n == 256) {
                aSN1ObjectIdentifier = NISTObjectIdentifiers.id_aes256_wrap;
            } else {
                throw new IllegalArgumentException("illegal keysize in AES");
            }
            return new AlgorithmIdentifier(aSN1ObjectIdentifier);
        }
        if (string.startsWith("SEED")) {
            return new AlgorithmIdentifier(KISAObjectIdentifiers.id_npki_app_cmsSeed_wrap);
        }
        if (string.startsWith("Camellia")) {
            ASN1ObjectIdentifier aSN1ObjectIdentifier;
            if (n == 128) {
                aSN1ObjectIdentifier = NTTObjectIdentifiers.id_camellia128_wrap;
            } else if (n == 192) {
                aSN1ObjectIdentifier = NTTObjectIdentifiers.id_camellia192_wrap;
            } else if (n == 256) {
                aSN1ObjectIdentifier = NTTObjectIdentifiers.id_camellia256_wrap;
            } else {
                throw new IllegalArgumentException("illegal keysize in Camellia");
            }
            return new AlgorithmIdentifier(aSN1ObjectIdentifier);
        }
        throw new IllegalArgumentException("unknown algorithm");
    }
}

