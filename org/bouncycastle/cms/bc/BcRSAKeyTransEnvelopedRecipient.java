/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.cms.bc;

import java.io.InputStream;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.cms.CMSException;
import org.bouncycastle.cms.RecipientOperator;
import org.bouncycastle.cms.bc.BcKeyTransRecipient;
import org.bouncycastle.cms.bc.EnvelopedDataHelper;
import org.bouncycastle.crypto.BufferedBlockCipher;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.StreamCipher;
import org.bouncycastle.crypto.io.CipherInputStream;
import org.bouncycastle.crypto.params.AsymmetricKeyParameter;
import org.bouncycastle.operator.InputDecryptor;

public class BcRSAKeyTransEnvelopedRecipient
extends BcKeyTransRecipient {
    public BcRSAKeyTransEnvelopedRecipient(AsymmetricKeyParameter asymmetricKeyParameter) {
        super(asymmetricKeyParameter);
    }

    public RecipientOperator getRecipientOperator(AlgorithmIdentifier algorithmIdentifier, final AlgorithmIdentifier algorithmIdentifier2, byte[] byArray) throws CMSException {
        CipherParameters cipherParameters = this.extractSecretKey(algorithmIdentifier, algorithmIdentifier2, byArray);
        final Object object = EnvelopedDataHelper.createContentCipher(false, cipherParameters, algorithmIdentifier2);
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

