/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.cms.bc;

import java.io.InputStream;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.cms.CMSException;
import org.bouncycastle.cms.RecipientOperator;
import org.bouncycastle.cms.bc.BcKEKRecipient;
import org.bouncycastle.cms.bc.EnvelopedDataHelper;
import org.bouncycastle.crypto.BufferedBlockCipher;
import org.bouncycastle.crypto.StreamCipher;
import org.bouncycastle.crypto.io.CipherInputStream;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.operator.InputDecryptor;
import org.bouncycastle.operator.bc.BcSymmetricKeyUnwrapper;

public class BcKEKEnvelopedRecipient
extends BcKEKRecipient {
    public BcKEKEnvelopedRecipient(BcSymmetricKeyUnwrapper bcSymmetricKeyUnwrapper) {
        super(bcSymmetricKeyUnwrapper);
    }

    public RecipientOperator getRecipientOperator(AlgorithmIdentifier algorithmIdentifier, final AlgorithmIdentifier algorithmIdentifier2, byte[] byArray) throws CMSException {
        KeyParameter keyParameter = (KeyParameter)this.extractSecretKey(algorithmIdentifier, algorithmIdentifier2, byArray);
        final Object object = EnvelopedDataHelper.createContentCipher(false, keyParameter, algorithmIdentifier2);
        return new RecipientOperator(new InputDecryptor(){

            public AlgorithmIdentifier getAlgorithmIdentifier() {
                return algorithmIdentifier2;
            }

            public InputStream getInputStream(InputStream inputStream) {
                if (object instanceof BufferedBlockCipher) {
                    return new CipherInputStream(inputStream, (BufferedBlockCipher)object);
                }
                return new CipherInputStream(inputStream, (StreamCipher)object);
            }
        });
    }
}

