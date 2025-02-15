/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.operator.jcajce;

import java.security.Key;
import java.security.Provider;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import java.util.HashMap;
import javax.crypto.Cipher;
import org.bouncycastle.asn1.cms.GenericHybridParameters;
import org.bouncycastle.asn1.cms.RsaKemParameters;
import org.bouncycastle.asn1.iso.ISOIECObjectIdentifiers;
import org.bouncycastle.asn1.nist.NISTObjectIdentifiers;
import org.bouncycastle.asn1.pkcs.PKCSObjectIdentifiers;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.asn1.x9.X9ObjectIdentifiers;
import org.bouncycastle.crypto.util.DEROtherInfo;
import org.bouncycastle.jcajce.spec.KTSParameterSpec;
import org.bouncycastle.jcajce.util.DefaultJcaJceHelper;
import org.bouncycastle.jcajce.util.NamedJcaJceHelper;
import org.bouncycastle.jcajce.util.ProviderJcaJceHelper;
import org.bouncycastle.operator.AsymmetricKeyWrapper;
import org.bouncycastle.operator.GenericKey;
import org.bouncycastle.operator.OperatorException;
import org.bouncycastle.operator.jcajce.JceSymmetricKeyWrapper;
import org.bouncycastle.operator.jcajce.OperatorHelper;
import org.bouncycastle.operator.jcajce.OperatorUtils;
import org.bouncycastle.util.Arrays;

public class JceKTSKeyWrapper
extends AsymmetricKeyWrapper {
    private final String symmetricWrappingAlg;
    private final int keySizeInBits;
    private final byte[] partyUInfo;
    private final byte[] partyVInfo;
    private OperatorHelper helper = new OperatorHelper(new DefaultJcaJceHelper());
    private PublicKey publicKey;
    private SecureRandom random;

    public JceKTSKeyWrapper(PublicKey publicKey, String string, int n, byte[] byArray, byte[] byArray2) {
        super(new AlgorithmIdentifier(PKCSObjectIdentifiers.id_rsa_KEM, new GenericHybridParameters(new AlgorithmIdentifier(ISOIECObjectIdentifiers.id_kem_rsa, new RsaKemParameters(new AlgorithmIdentifier(X9ObjectIdentifiers.id_kdf_kdf3, new AlgorithmIdentifier(NISTObjectIdentifiers.id_sha256)), (n + 7) / 8)), JceSymmetricKeyWrapper.determineKeyEncAlg(string, n))));
        this.publicKey = publicKey;
        this.symmetricWrappingAlg = string;
        this.keySizeInBits = n;
        this.partyUInfo = Arrays.clone(byArray);
        this.partyVInfo = Arrays.clone(byArray2);
    }

    public JceKTSKeyWrapper(X509Certificate x509Certificate, String string, int n, byte[] byArray, byte[] byArray2) {
        this(x509Certificate.getPublicKey(), string, n, byArray, byArray2);
    }

    public JceKTSKeyWrapper setProvider(Provider provider) {
        this.helper = new OperatorHelper(new ProviderJcaJceHelper(provider));
        return this;
    }

    public JceKTSKeyWrapper setProvider(String string) {
        this.helper = new OperatorHelper(new NamedJcaJceHelper(string));
        return this;
    }

    public JceKTSKeyWrapper setSecureRandom(SecureRandom secureRandom) {
        this.random = secureRandom;
        return this;
    }

    public byte[] generateWrappedKey(GenericKey genericKey) throws OperatorException {
        Cipher cipher = this.helper.createAsymmetricWrapper(this.getAlgorithmIdentifier().getAlgorithm(), new HashMap());
        try {
            DEROtherInfo dEROtherInfo = new DEROtherInfo.Builder(JceSymmetricKeyWrapper.determineKeyEncAlg(this.symmetricWrappingAlg, this.keySizeInBits), this.partyUInfo, this.partyVInfo).build();
            KTSParameterSpec kTSParameterSpec = new KTSParameterSpec.Builder(this.symmetricWrappingAlg, this.keySizeInBits, dEROtherInfo.getEncoded()).build();
            cipher.init(3, (Key)this.publicKey, kTSParameterSpec, this.random);
            return cipher.wrap(OperatorUtils.getJceKey(genericKey));
        } catch (Exception exception) {
            throw new OperatorException("Unable to wrap contents key: " + exception.getMessage(), exception);
        }
    }
}

