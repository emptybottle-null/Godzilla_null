/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.cms.jcajce;

import java.io.IOException;
import java.security.AlgorithmParameters;
import java.security.GeneralSecurityException;
import java.security.Key;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.Provider;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.cert.CertificateEncodingException;
import java.security.cert.X509Certificate;
import java.security.spec.AlgorithmParameterSpec;
import java.util.ArrayList;
import java.util.List;
import javax.crypto.Cipher;
import javax.crypto.KeyAgreement;
import javax.crypto.SecretKey;
import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.DEROctetString;
import org.bouncycastle.asn1.DERSequence;
import org.bouncycastle.asn1.cms.KeyAgreeRecipientIdentifier;
import org.bouncycastle.asn1.cms.OriginatorPublicKey;
import org.bouncycastle.asn1.cms.RecipientEncryptedKey;
import org.bouncycastle.asn1.cms.RecipientKeyIdentifier;
import org.bouncycastle.asn1.cms.ecc.MQVuserKeyingMaterial;
import org.bouncycastle.asn1.cryptopro.CryptoProObjectIdentifiers;
import org.bouncycastle.asn1.cryptopro.Gost2814789EncryptedKey;
import org.bouncycastle.asn1.pkcs.PKCSObjectIdentifiers;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.asn1.x509.SubjectPublicKeyInfo;
import org.bouncycastle.cms.CMSException;
import org.bouncycastle.cms.KeyAgreeRecipientInfoGenerator;
import org.bouncycastle.cms.jcajce.CMSUtils;
import org.bouncycastle.cms.jcajce.DefaultJcaJceExtHelper;
import org.bouncycastle.cms.jcajce.EnvelopedDataHelper;
import org.bouncycastle.cms.jcajce.KeyMaterialGenerator;
import org.bouncycastle.cms.jcajce.NamedJcaJceExtHelper;
import org.bouncycastle.cms.jcajce.ProviderJcaJceExtHelper;
import org.bouncycastle.cms.jcajce.RFC5753KeyMaterialGenerator;
import org.bouncycastle.jcajce.spec.GOST28147WrapParameterSpec;
import org.bouncycastle.jcajce.spec.MQVParameterSpec;
import org.bouncycastle.jcajce.spec.UserKeyingMaterialSpec;
import org.bouncycastle.operator.DefaultSecretKeySizeProvider;
import org.bouncycastle.operator.GenericKey;
import org.bouncycastle.operator.SecretKeySizeProvider;
import org.bouncycastle.util.Arrays;

public class JceKeyAgreeRecipientInfoGenerator
extends KeyAgreeRecipientInfoGenerator {
    private SecretKeySizeProvider keySizeProvider = new DefaultSecretKeySizeProvider();
    private List recipientIDs = new ArrayList();
    private List recipientKeys = new ArrayList();
    private PublicKey senderPublicKey;
    private PrivateKey senderPrivateKey;
    private EnvelopedDataHelper helper = new EnvelopedDataHelper(new DefaultJcaJceExtHelper());
    private SecureRandom random;
    private KeyPair ephemeralKP;
    private byte[] userKeyingMaterial;
    private static KeyMaterialGenerator ecc_cms_Generator = new RFC5753KeyMaterialGenerator();

    public JceKeyAgreeRecipientInfoGenerator(ASN1ObjectIdentifier aSN1ObjectIdentifier, PrivateKey privateKey, PublicKey publicKey, ASN1ObjectIdentifier aSN1ObjectIdentifier2) {
        super(aSN1ObjectIdentifier, SubjectPublicKeyInfo.getInstance(publicKey.getEncoded()), aSN1ObjectIdentifier2);
        this.senderPublicKey = publicKey;
        this.senderPrivateKey = privateKey;
    }

    public JceKeyAgreeRecipientInfoGenerator setUserKeyingMaterial(byte[] byArray) {
        this.userKeyingMaterial = Arrays.clone(byArray);
        return this;
    }

    public JceKeyAgreeRecipientInfoGenerator setProvider(Provider provider) {
        this.helper = new EnvelopedDataHelper(new ProviderJcaJceExtHelper(provider));
        return this;
    }

    public JceKeyAgreeRecipientInfoGenerator setProvider(String string) {
        this.helper = new EnvelopedDataHelper(new NamedJcaJceExtHelper(string));
        return this;
    }

    public JceKeyAgreeRecipientInfoGenerator setSecureRandom(SecureRandom secureRandom) {
        this.random = secureRandom;
        return this;
    }

    public JceKeyAgreeRecipientInfoGenerator addRecipient(X509Certificate x509Certificate) throws CertificateEncodingException {
        this.recipientIDs.add(new KeyAgreeRecipientIdentifier(CMSUtils.getIssuerAndSerialNumber(x509Certificate)));
        this.recipientKeys.add(x509Certificate.getPublicKey());
        return this;
    }

    public JceKeyAgreeRecipientInfoGenerator addRecipient(byte[] byArray, PublicKey publicKey) throws CertificateEncodingException {
        this.recipientIDs.add(new KeyAgreeRecipientIdentifier(new RecipientKeyIdentifier(byArray)));
        this.recipientKeys.add(publicKey);
        return this;
    }

    /*
     * WARNING - void declaration
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public ASN1Sequence generateRecipientEncryptedKeys(AlgorithmIdentifier algorithmIdentifier, AlgorithmIdentifier algorithmIdentifier2, GenericKey genericKey) throws CMSException {
        if (this.recipientIDs.isEmpty()) {
            throw new CMSException("No recipients associated with generator - use addRecipient()");
        }
        this.init(algorithmIdentifier.getAlgorithm());
        PrivateKey privateKey = this.senderPrivateKey;
        ASN1ObjectIdentifier aSN1ObjectIdentifier = algorithmIdentifier.getAlgorithm();
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        for (int i = 0; i != this.recipientIDs.size(); ++i) {
            PublicKey publicKey = (PublicKey)this.recipientKeys.get(i);
            KeyAgreeRecipientIdentifier keyAgreeRecipientIdentifier = (KeyAgreeRecipientIdentifier)this.recipientIDs.get(i);
            try {
                DEROctetString dEROctetString;
                byte[] byArray;
                void var10_10;
                Object object;
                ASN1ObjectIdentifier aSN1ObjectIdentifier2 = algorithmIdentifier2.getAlgorithm();
                if (CMSUtils.isMQV(aSN1ObjectIdentifier)) {
                    MQVParameterSpec mQVParameterSpec = new MQVParameterSpec(this.ephemeralKP, publicKey, this.userKeyingMaterial);
                } else if (CMSUtils.isEC(aSN1ObjectIdentifier)) {
                    object = ecc_cms_Generator.generateKDFMaterial(algorithmIdentifier2, this.keySizeProvider.getKeySize(aSN1ObjectIdentifier2), this.userKeyingMaterial);
                    UserKeyingMaterialSpec userKeyingMaterialSpec = new UserKeyingMaterialSpec((byte[])object);
                } else if (CMSUtils.isRFC2631(aSN1ObjectIdentifier)) {
                    if (this.userKeyingMaterial != null) {
                        UserKeyingMaterialSpec userKeyingMaterialSpec = new UserKeyingMaterialSpec(this.userKeyingMaterial);
                    } else {
                        if (aSN1ObjectIdentifier.equals(PKCSObjectIdentifiers.id_alg_SSDH)) {
                            throw new CMSException("User keying material must be set for static keys.");
                        }
                        Object var10_14 = null;
                    }
                } else {
                    if (!CMSUtils.isGOST(aSN1ObjectIdentifier)) throw new CMSException("Unknown key agreement algorithm: " + aSN1ObjectIdentifier);
                    if (this.userKeyingMaterial == null) throw new CMSException("User keying material must be set for static keys.");
                    UserKeyingMaterialSpec userKeyingMaterialSpec = new UserKeyingMaterialSpec(this.userKeyingMaterial);
                }
                object = this.helper.createKeyAgreement(aSN1ObjectIdentifier);
                ((KeyAgreement)object).init(privateKey, (AlgorithmParameterSpec)var10_10, this.random);
                ((KeyAgreement)object).doPhase(publicKey, true);
                SecretKey secretKey = ((KeyAgreement)object).generateSecret(aSN1ObjectIdentifier2.getId());
                Cipher cipher = this.helper.createCipher(aSN1ObjectIdentifier2);
                if (aSN1ObjectIdentifier2.equals(CryptoProObjectIdentifiers.id_Gost28147_89_None_KeyWrap) || aSN1ObjectIdentifier2.equals(CryptoProObjectIdentifiers.id_Gost28147_89_CryptoPro_KeyWrap)) {
                    cipher.init(3, (Key)secretKey, new GOST28147WrapParameterSpec(CryptoProObjectIdentifiers.id_Gost28147_89_CryptoPro_A_ParamSet, this.userKeyingMaterial));
                    byArray = cipher.wrap(this.helper.getJceKey(genericKey));
                    Gost2814789EncryptedKey gost2814789EncryptedKey = new Gost2814789EncryptedKey(Arrays.copyOfRange(byArray, 0, byArray.length - 4), Arrays.copyOfRange(byArray, byArray.length - 4, byArray.length));
                    dEROctetString = new DEROctetString(gost2814789EncryptedKey.getEncoded("DER"));
                } else {
                    cipher.init(3, (Key)secretKey, this.random);
                    byArray = cipher.wrap(this.helper.getJceKey(genericKey));
                    dEROctetString = new DEROctetString(byArray);
                }
                aSN1EncodableVector.add(new RecipientEncryptedKey(keyAgreeRecipientIdentifier, dEROctetString));
                continue;
            } catch (GeneralSecurityException generalSecurityException) {
                throw new CMSException("cannot perform agreement step: " + generalSecurityException.getMessage(), generalSecurityException);
            } catch (IOException iOException) {
                throw new CMSException("unable to encode wrapped key: " + iOException.getMessage(), iOException);
            }
        }
        return new DERSequence(aSN1EncodableVector);
    }

    protected byte[] getUserKeyingMaterial(AlgorithmIdentifier algorithmIdentifier) throws CMSException {
        this.init(algorithmIdentifier.getAlgorithm());
        if (this.ephemeralKP != null) {
            OriginatorPublicKey originatorPublicKey = this.createOriginatorPublicKey(SubjectPublicKeyInfo.getInstance(this.ephemeralKP.getPublic().getEncoded()));
            try {
                if (this.userKeyingMaterial != null) {
                    return new MQVuserKeyingMaterial(originatorPublicKey, new DEROctetString(this.userKeyingMaterial)).getEncoded();
                }
                return new MQVuserKeyingMaterial(originatorPublicKey, null).getEncoded();
            } catch (IOException iOException) {
                throw new CMSException("unable to encode user keying material: " + iOException.getMessage(), iOException);
            }
        }
        return this.userKeyingMaterial;
    }

    private void init(ASN1ObjectIdentifier aSN1ObjectIdentifier) throws CMSException {
        if (this.random == null) {
            this.random = new SecureRandom();
        }
        if (CMSUtils.isMQV(aSN1ObjectIdentifier) && this.ephemeralKP == null) {
            try {
                SubjectPublicKeyInfo subjectPublicKeyInfo = SubjectPublicKeyInfo.getInstance(this.senderPublicKey.getEncoded());
                AlgorithmParameters algorithmParameters = this.helper.createAlgorithmParameters(aSN1ObjectIdentifier);
                algorithmParameters.init(subjectPublicKeyInfo.getAlgorithm().getParameters().toASN1Primitive().getEncoded());
                KeyPairGenerator keyPairGenerator = this.helper.createKeyPairGenerator(aSN1ObjectIdentifier);
                keyPairGenerator.initialize(algorithmParameters.getParameterSpec(AlgorithmParameterSpec.class), this.random);
                this.ephemeralKP = keyPairGenerator.generateKeyPair();
            } catch (Exception exception) {
                throw new CMSException("cannot determine MQV ephemeral key pair parameters from public key: " + exception, exception);
            }
        }
    }
}

