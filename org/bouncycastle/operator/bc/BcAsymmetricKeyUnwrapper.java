/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.operator.bc;

import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.pkcs.PKCSObjectIdentifiers;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.crypto.AsymmetricBlockCipher;
import org.bouncycastle.crypto.InvalidCipherTextException;
import org.bouncycastle.crypto.params.AsymmetricKeyParameter;
import org.bouncycastle.operator.AsymmetricKeyUnwrapper;
import org.bouncycastle.operator.GenericKey;
import org.bouncycastle.operator.OperatorException;

public abstract class BcAsymmetricKeyUnwrapper
extends AsymmetricKeyUnwrapper {
    private AsymmetricKeyParameter privateKey;

    public BcAsymmetricKeyUnwrapper(AlgorithmIdentifier algorithmIdentifier, AsymmetricKeyParameter asymmetricKeyParameter) {
        super(algorithmIdentifier);
        this.privateKey = asymmetricKeyParameter;
    }

    public GenericKey generateUnwrappedKey(AlgorithmIdentifier algorithmIdentifier, byte[] byArray) throws OperatorException {
        AsymmetricBlockCipher asymmetricBlockCipher = this.createAsymmetricUnwrapper(this.getAlgorithmIdentifier().getAlgorithm());
        asymmetricBlockCipher.init(false, this.privateKey);
        try {
            byte[] byArray2 = asymmetricBlockCipher.processBlock(byArray, 0, byArray.length);
            if (algorithmIdentifier.getAlgorithm().equals(PKCSObjectIdentifiers.des_EDE3_CBC)) {
                return new GenericKey(algorithmIdentifier, byArray2);
            }
            return new GenericKey(algorithmIdentifier, byArray2);
        } catch (InvalidCipherTextException invalidCipherTextException) {
            throw new OperatorException("unable to recover secret key: " + invalidCipherTextException.getMessage(), invalidCipherTextException);
        }
    }

    protected abstract AsymmetricBlockCipher createAsymmetricUnwrapper(ASN1ObjectIdentifier var1);
}

