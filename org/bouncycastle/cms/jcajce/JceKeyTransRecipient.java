/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.cms.jcajce;

import java.security.Key;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.Provider;
import java.security.PublicKey;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;
import javax.crypto.Cipher;
import javax.crypto.KeyAgreement;
import javax.crypto.SecretKey;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.cryptopro.CryptoProObjectIdentifiers;
import org.bouncycastle.asn1.cryptopro.Gost2814789EncryptedKey;
import org.bouncycastle.asn1.cryptopro.GostR3410KeyTransport;
import org.bouncycastle.asn1.cryptopro.GostR3410TransportParameters;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.cms.CMSException;
import org.bouncycastle.cms.KeyTransRecipient;
import org.bouncycastle.cms.jcajce.CMSUtils;
import org.bouncycastle.cms.jcajce.DefaultJcaJceExtHelper;
import org.bouncycastle.cms.jcajce.EnvelopedDataHelper;
import org.bouncycastle.cms.jcajce.NamedJcaJceExtHelper;
import org.bouncycastle.cms.jcajce.ProviderJcaJceExtHelper;
import org.bouncycastle.jcajce.spec.GOST28147WrapParameterSpec;
import org.bouncycastle.jcajce.spec.UserKeyingMaterialSpec;
import org.bouncycastle.operator.OperatorException;
import org.bouncycastle.operator.jcajce.JceAsymmetricKeyUnwrapper;
import org.bouncycastle.util.Arrays;

public abstract class JceKeyTransRecipient
implements KeyTransRecipient {
    private PrivateKey recipientKey;
    protected EnvelopedDataHelper helper;
    protected EnvelopedDataHelper contentHelper;
    protected Map extraMappings;
    protected boolean validateKeySize;
    protected boolean unwrappedKeyMustBeEncodable;

    public JceKeyTransRecipient(PrivateKey privateKey) {
        this.contentHelper = this.helper = new EnvelopedDataHelper(new DefaultJcaJceExtHelper());
        this.extraMappings = new HashMap();
        this.validateKeySize = false;
        this.recipientKey = privateKey;
    }

    public JceKeyTransRecipient setProvider(Provider provider) {
        this.contentHelper = this.helper = new EnvelopedDataHelper(new ProviderJcaJceExtHelper(provider));
        return this;
    }

    public JceKeyTransRecipient setProvider(String string) {
        this.contentHelper = this.helper = new EnvelopedDataHelper(new NamedJcaJceExtHelper(string));
        return this;
    }

    public JceKeyTransRecipient setAlgorithmMapping(ASN1ObjectIdentifier aSN1ObjectIdentifier, String string) {
        this.extraMappings.put(aSN1ObjectIdentifier, string);
        return this;
    }

    public JceKeyTransRecipient setContentProvider(Provider provider) {
        this.contentHelper = CMSUtils.createContentHelper(provider);
        return this;
    }

    public JceKeyTransRecipient setMustProduceEncodableUnwrappedKey(boolean bl) {
        this.unwrappedKeyMustBeEncodable = bl;
        return this;
    }

    public JceKeyTransRecipient setContentProvider(String string) {
        this.contentHelper = CMSUtils.createContentHelper(string);
        return this;
    }

    public JceKeyTransRecipient setKeySizeValidation(boolean bl) {
        this.validateKeySize = bl;
        return this;
    }

    protected Key extractSecretKey(AlgorithmIdentifier algorithmIdentifier, AlgorithmIdentifier algorithmIdentifier2, byte[] byArray) throws CMSException {
        if (CMSUtils.isGOST(algorithmIdentifier.getAlgorithm())) {
            try {
                GostR3410KeyTransport gostR3410KeyTransport = GostR3410KeyTransport.getInstance(byArray);
                GostR3410TransportParameters gostR3410TransportParameters = gostR3410KeyTransport.getTransportParameters();
                KeyFactory keyFactory = this.helper.createKeyFactory(algorithmIdentifier.getAlgorithm());
                PublicKey publicKey = keyFactory.generatePublic(new X509EncodedKeySpec(gostR3410TransportParameters.getEphemeralPublicKey().getEncoded()));
                KeyAgreement keyAgreement = this.helper.createKeyAgreement(algorithmIdentifier.getAlgorithm());
                keyAgreement.init((Key)this.recipientKey, new UserKeyingMaterialSpec(gostR3410TransportParameters.getUkm()));
                keyAgreement.doPhase(publicKey, true);
                SecretKey secretKey = keyAgreement.generateSecret("GOST28147");
                Cipher cipher = this.helper.createCipher(CryptoProObjectIdentifiers.id_Gost28147_89_CryptoPro_KeyWrap);
                cipher.init(4, (Key)secretKey, new GOST28147WrapParameterSpec(gostR3410TransportParameters.getEncryptionParamSet(), gostR3410TransportParameters.getUkm()));
                Gost2814789EncryptedKey gost2814789EncryptedKey = gostR3410KeyTransport.getSessionEncryptedKey();
                return cipher.unwrap(Arrays.concatenate(gost2814789EncryptedKey.getEncryptedKey(), gost2814789EncryptedKey.getMacKey()), this.helper.getBaseCipherName(algorithmIdentifier2.getAlgorithm()), 3);
            } catch (Exception exception) {
                throw new CMSException("exception unwrapping key: " + exception.getMessage(), exception);
            }
        }
        JceAsymmetricKeyUnwrapper jceAsymmetricKeyUnwrapper = this.helper.createAsymmetricUnwrapper(algorithmIdentifier, this.recipientKey).setMustProduceEncodableUnwrappedKey(this.unwrappedKeyMustBeEncodable);
        if (!this.extraMappings.isEmpty()) {
            for (ASN1ObjectIdentifier aSN1ObjectIdentifier : this.extraMappings.keySet()) {
                jceAsymmetricKeyUnwrapper.setAlgorithmMapping(aSN1ObjectIdentifier, (String)this.extraMappings.get(aSN1ObjectIdentifier));
            }
        }
        try {
            Key key = this.helper.getJceKey(algorithmIdentifier2.getAlgorithm(), jceAsymmetricKeyUnwrapper.generateUnwrappedKey(algorithmIdentifier2, byArray));
            if (this.validateKeySize) {
                this.helper.keySizeCheck(algorithmIdentifier2, key);
            }
            return key;
        } catch (OperatorException operatorException) {
            throw new CMSException("exception unwrapping key: " + operatorException.getMessage(), operatorException);
        }
    }
}

