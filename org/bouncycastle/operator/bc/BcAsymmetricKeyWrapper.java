/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.operator.bc;

import java.security.SecureRandom;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.crypto.AsymmetricBlockCipher;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.InvalidCipherTextException;
import org.bouncycastle.crypto.params.AsymmetricKeyParameter;
import org.bouncycastle.crypto.params.ParametersWithRandom;
import org.bouncycastle.operator.AsymmetricKeyWrapper;
import org.bouncycastle.operator.GenericKey;
import org.bouncycastle.operator.OperatorException;
import org.bouncycastle.operator.bc.OperatorUtils;

public abstract class BcAsymmetricKeyWrapper
extends AsymmetricKeyWrapper {
    private AsymmetricKeyParameter publicKey;
    private SecureRandom random;

    public BcAsymmetricKeyWrapper(AlgorithmIdentifier algorithmIdentifier, AsymmetricKeyParameter asymmetricKeyParameter) {
        super(algorithmIdentifier);
        this.publicKey = asymmetricKeyParameter;
    }

    public BcAsymmetricKeyWrapper setSecureRandom(SecureRandom secureRandom) {
        this.random = secureRandom;
        return this;
    }

    public byte[] generateWrappedKey(GenericKey genericKey) throws OperatorException {
        AsymmetricBlockCipher asymmetricBlockCipher = this.createAsymmetricWrapper(this.getAlgorithmIdentifier().getAlgorithm());
        CipherParameters cipherParameters = this.publicKey;
        if (this.random != null) {
            cipherParameters = new ParametersWithRandom(cipherParameters, this.random);
        }
        try {
            byte[] byArray = OperatorUtils.getKeyBytes(genericKey);
            asymmetricBlockCipher.init(true, cipherParameters);
            return asymmetricBlockCipher.processBlock(byArray, 0, byArray.length);
        } catch (InvalidCipherTextException invalidCipherTextException) {
            throw new OperatorException("unable to encrypt contents key", invalidCipherTextException);
        }
    }

    protected abstract AsymmetricBlockCipher createAsymmetricWrapper(ASN1ObjectIdentifier var1);
}

