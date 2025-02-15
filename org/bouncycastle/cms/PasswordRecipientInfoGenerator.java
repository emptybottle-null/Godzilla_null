/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.cms;

import java.security.SecureRandom;
import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.DEROctetString;
import org.bouncycastle.asn1.DERSequence;
import org.bouncycastle.asn1.cms.PasswordRecipientInfo;
import org.bouncycastle.asn1.cms.RecipientInfo;
import org.bouncycastle.asn1.pkcs.PBKDF2Params;
import org.bouncycastle.asn1.pkcs.PKCSObjectIdentifiers;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.cms.CMSException;
import org.bouncycastle.cms.PasswordRecipient;
import org.bouncycastle.cms.PasswordRecipientInformation;
import org.bouncycastle.cms.RecipientInfoGenerator;
import org.bouncycastle.operator.GenericKey;
import org.bouncycastle.util.Arrays;

public abstract class PasswordRecipientInfoGenerator
implements RecipientInfoGenerator {
    protected char[] password;
    private AlgorithmIdentifier keyDerivationAlgorithm;
    private ASN1ObjectIdentifier kekAlgorithm;
    private SecureRandom random;
    private int schemeID;
    private int keySize;
    private int blockSize;
    private PasswordRecipient.PRF prf;
    private byte[] salt;
    private int iterationCount;

    protected PasswordRecipientInfoGenerator(ASN1ObjectIdentifier aSN1ObjectIdentifier, char[] cArray) {
        this(aSN1ObjectIdentifier, cArray, PasswordRecipientInfoGenerator.getKeySize(aSN1ObjectIdentifier), (Integer)PasswordRecipientInformation.BLOCKSIZES.get(aSN1ObjectIdentifier));
    }

    protected PasswordRecipientInfoGenerator(ASN1ObjectIdentifier aSN1ObjectIdentifier, char[] cArray, int n, int n2) {
        this.password = cArray;
        this.schemeID = 1;
        this.kekAlgorithm = aSN1ObjectIdentifier;
        this.keySize = n;
        this.blockSize = n2;
        this.prf = PasswordRecipient.PRF.HMacSHA1;
        this.iterationCount = 1024;
    }

    private static int getKeySize(ASN1ObjectIdentifier aSN1ObjectIdentifier) {
        Integer n = (Integer)PasswordRecipientInformation.KEYSIZES.get(aSN1ObjectIdentifier);
        if (n == null) {
            throw new IllegalArgumentException("cannot find key size for algorithm: " + aSN1ObjectIdentifier);
        }
        return n;
    }

    public PasswordRecipientInfoGenerator setPasswordConversionScheme(int n) {
        this.schemeID = n;
        return this;
    }

    public PasswordRecipientInfoGenerator setPRF(PasswordRecipient.PRF pRF) {
        this.prf = pRF;
        return this;
    }

    public PasswordRecipientInfoGenerator setSaltAndIterationCount(byte[] byArray, int n) {
        this.salt = Arrays.clone(byArray);
        this.iterationCount = n;
        return this;
    }

    public PasswordRecipientInfoGenerator setSecureRandom(SecureRandom secureRandom) {
        this.random = secureRandom;
        return this;
    }

    public RecipientInfo generate(GenericKey genericKey) throws CMSException {
        byte[] byArray = new byte[this.blockSize];
        if (this.random == null) {
            this.random = new SecureRandom();
        }
        this.random.nextBytes(byArray);
        if (this.salt == null) {
            this.salt = new byte[20];
            this.random.nextBytes(this.salt);
        }
        this.keyDerivationAlgorithm = new AlgorithmIdentifier(PKCSObjectIdentifiers.id_PBKDF2, new PBKDF2Params(this.salt, this.iterationCount, this.prf.prfAlgID));
        byte[] byArray2 = this.calculateDerivedKey(this.schemeID, this.keyDerivationAlgorithm, this.keySize);
        AlgorithmIdentifier algorithmIdentifier = new AlgorithmIdentifier(this.kekAlgorithm, new DEROctetString(byArray));
        byte[] byArray3 = this.generateEncryptedBytes(algorithmIdentifier, byArray2, genericKey);
        DEROctetString dEROctetString = new DEROctetString(byArray3);
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        aSN1EncodableVector.add(this.kekAlgorithm);
        aSN1EncodableVector.add(new DEROctetString(byArray));
        AlgorithmIdentifier algorithmIdentifier2 = new AlgorithmIdentifier(PKCSObjectIdentifiers.id_alg_PWRI_KEK, new DERSequence(aSN1EncodableVector));
        return new RecipientInfo(new PasswordRecipientInfo(this.keyDerivationAlgorithm, algorithmIdentifier2, dEROctetString));
    }

    protected abstract byte[] calculateDerivedKey(int var1, AlgorithmIdentifier var2, int var3) throws CMSException;

    protected abstract byte[] generateEncryptedBytes(AlgorithmIdentifier var1, byte[] var2, GenericKey var3) throws CMSException;
}

