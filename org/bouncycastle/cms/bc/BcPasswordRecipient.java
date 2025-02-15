/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.cms.bc;

import org.bouncycastle.asn1.ASN1OctetString;
import org.bouncycastle.asn1.pkcs.PBKDF2Params;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.cms.CMSException;
import org.bouncycastle.cms.PasswordRecipient;
import org.bouncycastle.cms.bc.EnvelopedDataHelper;
import org.bouncycastle.crypto.InvalidCipherTextException;
import org.bouncycastle.crypto.PBEParametersGenerator;
import org.bouncycastle.crypto.Wrapper;
import org.bouncycastle.crypto.generators.PKCS5S2ParametersGenerator;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.crypto.params.ParametersWithIV;

public abstract class BcPasswordRecipient
implements PasswordRecipient {
    private final char[] password;
    private int schemeID = 1;

    BcPasswordRecipient(char[] cArray) {
        this.password = cArray;
    }

    public BcPasswordRecipient setPasswordConversionScheme(int n) {
        this.schemeID = n;
        return this;
    }

    protected KeyParameter extractSecretKey(AlgorithmIdentifier algorithmIdentifier, AlgorithmIdentifier algorithmIdentifier2, byte[] byArray, byte[] byArray2) throws CMSException {
        Wrapper wrapper = EnvelopedDataHelper.createRFC3211Wrapper(algorithmIdentifier.getAlgorithm());
        wrapper.init(false, new ParametersWithIV(new KeyParameter(byArray), ASN1OctetString.getInstance(algorithmIdentifier.getParameters()).getOctets()));
        try {
            return new KeyParameter(wrapper.unwrap(byArray2, 0, byArray2.length));
        } catch (InvalidCipherTextException invalidCipherTextException) {
            throw new CMSException("unable to unwrap key: " + invalidCipherTextException.getMessage(), invalidCipherTextException);
        }
    }

    public byte[] calculateDerivedKey(int n, AlgorithmIdentifier algorithmIdentifier, int n2) throws CMSException {
        PBKDF2Params pBKDF2Params = PBKDF2Params.getInstance(algorithmIdentifier.getParameters());
        byte[] byArray = n == 0 ? PBEParametersGenerator.PKCS5PasswordToBytes(this.password) : PBEParametersGenerator.PKCS5PasswordToUTF8Bytes(this.password);
        try {
            PKCS5S2ParametersGenerator pKCS5S2ParametersGenerator = new PKCS5S2ParametersGenerator(EnvelopedDataHelper.getPRF(pBKDF2Params.getPrf()));
            pKCS5S2ParametersGenerator.init(byArray, pBKDF2Params.getSalt(), pBKDF2Params.getIterationCount().intValue());
            return ((KeyParameter)pKCS5S2ParametersGenerator.generateDerivedParameters(n2)).getKey();
        } catch (Exception exception) {
            throw new CMSException("exception creating derived key: " + exception.getMessage(), exception);
        }
    }

    public int getPasswordConversionScheme() {
        return this.schemeID;
    }

    public char[] getPassword() {
        return this.password;
    }
}

