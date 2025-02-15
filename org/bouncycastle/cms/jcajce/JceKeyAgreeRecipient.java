/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.cms.jcajce;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.Provider;
import java.security.PublicKey;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashSet;
import java.util.Set;
import javax.crypto.Cipher;
import javax.crypto.KeyAgreement;
import javax.crypto.SecretKey;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.ASN1OctetString;
import org.bouncycastle.asn1.DERNull;
import org.bouncycastle.asn1.cms.ecc.ECCCMSSharedInfo;
import org.bouncycastle.asn1.cms.ecc.MQVuserKeyingMaterial;
import org.bouncycastle.asn1.pkcs.PrivateKeyInfo;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.asn1.x509.SubjectPublicKeyInfo;
import org.bouncycastle.asn1.x9.X9ObjectIdentifiers;
import org.bouncycastle.cms.CMSException;
import org.bouncycastle.cms.KeyAgreeRecipient;
import org.bouncycastle.cms.jcajce.CMSUtils;
import org.bouncycastle.cms.jcajce.DefaultJcaJceExtHelper;
import org.bouncycastle.cms.jcajce.EnvelopedDataHelper;
import org.bouncycastle.cms.jcajce.KeyMaterialGenerator;
import org.bouncycastle.cms.jcajce.NamedJcaJceExtHelper;
import org.bouncycastle.cms.jcajce.ProviderJcaJceExtHelper;
import org.bouncycastle.cms.jcajce.RFC5753KeyMaterialGenerator;
import org.bouncycastle.jcajce.spec.MQVParameterSpec;
import org.bouncycastle.jcajce.spec.UserKeyingMaterialSpec;
import org.bouncycastle.operator.DefaultSecretKeySizeProvider;
import org.bouncycastle.operator.SecretKeySizeProvider;
import org.bouncycastle.util.Pack;

public abstract class JceKeyAgreeRecipient
implements KeyAgreeRecipient {
    private static final Set possibleOldMessages = new HashSet();
    private PrivateKey recipientKey;
    protected EnvelopedDataHelper helper;
    protected EnvelopedDataHelper contentHelper;
    private SecretKeySizeProvider keySizeProvider;
    private static KeyMaterialGenerator old_ecc_cms_Generator;
    private static KeyMaterialGenerator ecc_cms_Generator;

    public JceKeyAgreeRecipient(PrivateKey privateKey) {
        this.contentHelper = this.helper = new EnvelopedDataHelper(new DefaultJcaJceExtHelper());
        this.keySizeProvider = new DefaultSecretKeySizeProvider();
        this.recipientKey = privateKey;
    }

    public JceKeyAgreeRecipient setProvider(Provider provider) {
        this.contentHelper = this.helper = new EnvelopedDataHelper(new ProviderJcaJceExtHelper(provider));
        return this;
    }

    public JceKeyAgreeRecipient setProvider(String string) {
        this.contentHelper = this.helper = new EnvelopedDataHelper(new NamedJcaJceExtHelper(string));
        return this;
    }

    public JceKeyAgreeRecipient setContentProvider(Provider provider) {
        this.contentHelper = CMSUtils.createContentHelper(provider);
        return this;
    }

    public JceKeyAgreeRecipient setContentProvider(String string) {
        this.contentHelper = CMSUtils.createContentHelper(string);
        return this;
    }

    private SecretKey calculateAgreedWrapKey(AlgorithmIdentifier algorithmIdentifier, AlgorithmIdentifier algorithmIdentifier2, PublicKey publicKey, ASN1OctetString aSN1OctetString, PrivateKey privateKey, KeyMaterialGenerator keyMaterialGenerator) throws CMSException, GeneralSecurityException, IOException {
        if (CMSUtils.isMQV(algorithmIdentifier.getAlgorithm())) {
            byte[] byArray;
            MQVuserKeyingMaterial mQVuserKeyingMaterial = MQVuserKeyingMaterial.getInstance(aSN1OctetString.getOctets());
            SubjectPublicKeyInfo subjectPublicKeyInfo = new SubjectPublicKeyInfo(this.getPrivateKeyAlgorithmIdentifier(), mQVuserKeyingMaterial.getEphemeralPublicKey().getPublicKey().getBytes());
            X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(subjectPublicKeyInfo.getEncoded());
            KeyFactory keyFactory = this.helper.createKeyFactory(algorithmIdentifier.getAlgorithm());
            PublicKey publicKey2 = keyFactory.generatePublic(x509EncodedKeySpec);
            KeyAgreement keyAgreement = this.helper.createKeyAgreement(algorithmIdentifier.getAlgorithm());
            byte[] byArray2 = byArray = mQVuserKeyingMaterial.getAddedukm() != null ? mQVuserKeyingMaterial.getAddedukm().getOctets() : null;
            if (keyMaterialGenerator == old_ecc_cms_Generator) {
                byArray = old_ecc_cms_Generator.generateKDFMaterial(algorithmIdentifier2, this.keySizeProvider.getKeySize(algorithmIdentifier2), byArray);
            }
            keyAgreement.init((Key)privateKey, new MQVParameterSpec(privateKey, publicKey2, byArray));
            keyAgreement.doPhase(publicKey, true);
            return keyAgreement.generateSecret(algorithmIdentifier2.getAlgorithm().getId());
        }
        KeyAgreement keyAgreement = this.helper.createKeyAgreement(algorithmIdentifier.getAlgorithm());
        UserKeyingMaterialSpec userKeyingMaterialSpec = null;
        if (CMSUtils.isEC(algorithmIdentifier.getAlgorithm())) {
            if (aSN1OctetString != null) {
                byte[] byArray = keyMaterialGenerator.generateKDFMaterial(algorithmIdentifier2, this.keySizeProvider.getKeySize(algorithmIdentifier2), aSN1OctetString.getOctets());
                userKeyingMaterialSpec = new UserKeyingMaterialSpec(byArray);
            } else {
                byte[] byArray = keyMaterialGenerator.generateKDFMaterial(algorithmIdentifier2, this.keySizeProvider.getKeySize(algorithmIdentifier2), null);
                userKeyingMaterialSpec = new UserKeyingMaterialSpec(byArray);
            }
        } else if (CMSUtils.isRFC2631(algorithmIdentifier.getAlgorithm())) {
            if (aSN1OctetString != null) {
                userKeyingMaterialSpec = new UserKeyingMaterialSpec(aSN1OctetString.getOctets());
            }
        } else if (CMSUtils.isGOST(algorithmIdentifier.getAlgorithm())) {
            if (aSN1OctetString != null) {
                userKeyingMaterialSpec = new UserKeyingMaterialSpec(aSN1OctetString.getOctets());
            }
        } else {
            throw new CMSException("Unknown key agreement algorithm: " + algorithmIdentifier.getAlgorithm());
        }
        keyAgreement.init((Key)privateKey, userKeyingMaterialSpec);
        keyAgreement.doPhase(publicKey, true);
        return keyAgreement.generateSecret(algorithmIdentifier2.getAlgorithm().getId());
    }

    private Key unwrapSessionKey(ASN1ObjectIdentifier aSN1ObjectIdentifier, SecretKey secretKey, ASN1ObjectIdentifier aSN1ObjectIdentifier2, byte[] byArray) throws CMSException, InvalidKeyException, NoSuchAlgorithmException {
        Cipher cipher = this.helper.createCipher(aSN1ObjectIdentifier);
        cipher.init(4, secretKey);
        return cipher.unwrap(byArray, this.helper.getBaseCipherName(aSN1ObjectIdentifier2), 3);
    }

    /*
     * Exception decompiling
     */
    protected Key extractSecretKey(AlgorithmIdentifier var1_1, AlgorithmIdentifier var2_2, SubjectPublicKeyInfo var3_3, ASN1OctetString var4_4, byte[] var5_5) throws CMSException {
        /*
         * This method has failed to decompile.  When submitting a bug report, please provide this stack trace, and (if you hold appropriate legal rights) the relevant class file.
         * 
         * org.benf.cfr.reader.util.ConfusedCFRException: Started 3 blocks at once
         *     at org.benf.cfr.reader.bytecode.analysis.opgraph.Op04StructuredStatement.getStartingBlocks(Op04StructuredStatement.java:412)
         *     at org.benf.cfr.reader.bytecode.analysis.opgraph.Op04StructuredStatement.buildNestedBlocks(Op04StructuredStatement.java:487)
         *     at org.benf.cfr.reader.bytecode.analysis.opgraph.Op03SimpleStatement.createInitialStructuredBlock(Op03SimpleStatement.java:736)
         *     at org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysisInner(CodeAnalyser.java:850)
         *     at org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysisOrWrapFail(CodeAnalyser.java:278)
         *     at org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysis(CodeAnalyser.java:201)
         *     at org.benf.cfr.reader.entities.attributes.AttributeCode.analyse(AttributeCode.java:94)
         *     at org.benf.cfr.reader.entities.Method.analyse(Method.java:538)
         *     at org.benf.cfr.reader.entities.ClassFile.analyseMid(ClassFile.java:1055)
         *     at org.benf.cfr.reader.entities.ClassFile.analyseTop(ClassFile.java:942)
         *     at org.benf.cfr.reader.Driver.doJarVersionTypes(Driver.java:257)
         *     at org.benf.cfr.reader.Driver.doJar(Driver.java:139)
         *     at org.benf.cfr.reader.CfrDriverImpl.analyse(CfrDriverImpl.java:76)
         *     at org.benf.cfr.reader.Main.main(Main.java:54)
         *     at async.DecompilerRunnable.cfrDecompilation(DecompilerRunnable.java:348)
         *     at async.DecompilerRunnable.call(DecompilerRunnable.java:309)
         *     at async.DecompilerRunnable.call(DecompilerRunnable.java:31)
         *     at java.util.concurrent.FutureTask.run(FutureTask.java:266)
         *     at java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1149)
         *     at java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:624)
         *     at java.lang.Thread.run(Thread.java:750)
         */
        throw new IllegalStateException("Decompilation failed");
    }

    public AlgorithmIdentifier getPrivateKeyAlgorithmIdentifier() {
        return PrivateKeyInfo.getInstance(this.recipientKey.getEncoded()).getPrivateKeyAlgorithm();
    }

    static {
        possibleOldMessages.add(X9ObjectIdentifiers.dhSinglePass_stdDH_sha1kdf_scheme);
        possibleOldMessages.add(X9ObjectIdentifiers.mqvSinglePass_sha1kdf_scheme);
        old_ecc_cms_Generator = new KeyMaterialGenerator(){

            public byte[] generateKDFMaterial(AlgorithmIdentifier algorithmIdentifier, int n, byte[] byArray) {
                ECCCMSSharedInfo eCCCMSSharedInfo = new ECCCMSSharedInfo(new AlgorithmIdentifier(algorithmIdentifier.getAlgorithm(), DERNull.INSTANCE), byArray, Pack.intToBigEndian(n));
                try {
                    return eCCCMSSharedInfo.getEncoded("DER");
                } catch (IOException iOException) {
                    throw new IllegalStateException("Unable to create KDF material: " + iOException);
                }
            }
        };
        ecc_cms_Generator = new RFC5753KeyMaterialGenerator();
    }
}

