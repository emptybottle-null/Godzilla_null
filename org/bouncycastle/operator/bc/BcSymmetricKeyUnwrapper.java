/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.operator.bc;

import java.security.SecureRandom;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.crypto.InvalidCipherTextException;
import org.bouncycastle.crypto.Wrapper;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.operator.GenericKey;
import org.bouncycastle.operator.OperatorException;
import org.bouncycastle.operator.SymmetricKeyUnwrapper;

public class BcSymmetricKeyUnwrapper
extends SymmetricKeyUnwrapper {
    private SecureRandom random;
    private Wrapper wrapper;
    private KeyParameter wrappingKey;

    public BcSymmetricKeyUnwrapper(AlgorithmIdentifier algorithmIdentifier, Wrapper wrapper, KeyParameter keyParameter) {
        super(algorithmIdentifier);
        this.wrapper = wrapper;
        this.wrappingKey = keyParameter;
    }

    public BcSymmetricKeyUnwrapper setSecureRandom(SecureRandom secureRandom) {
        this.random = secureRandom;
        return this;
    }

    public GenericKey generateUnwrappedKey(AlgorithmIdentifier algorithmIdentifier, byte[] byArray) throws OperatorException {
        this.wrapper.init(false, this.wrappingKey);
        try {
            return new GenericKey(algorithmIdentifier, this.wrapper.unwrap(byArray, 0, byArray.length));
        } catch (InvalidCipherTextException invalidCipherTextException) {
            throw new OperatorException("unable to unwrap key: " + invalidCipherTextException.getMessage(), invalidCipherTextException);
        }
    }
}

