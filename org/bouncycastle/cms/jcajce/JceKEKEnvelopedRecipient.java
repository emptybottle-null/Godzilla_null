/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.cms.jcajce;

import java.io.InputStream;
import java.security.Key;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.cms.CMSException;
import org.bouncycastle.cms.RecipientOperator;
import org.bouncycastle.cms.jcajce.JceKEKRecipient;
import org.bouncycastle.jcajce.io.CipherInputStream;
import org.bouncycastle.operator.InputDecryptor;

public class JceKEKEnvelopedRecipient
extends JceKEKRecipient {
    public JceKEKEnvelopedRecipient(SecretKey secretKey) {
        super(secretKey);
    }

    public RecipientOperator getRecipientOperator(AlgorithmIdentifier algorithmIdentifier, final AlgorithmIdentifier algorithmIdentifier2, byte[] byArray) throws CMSException {
        Key key = this.extractSecretKey(algorithmIdentifier, algorithmIdentifier2, byArray);
        final Cipher cipher = this.contentHelper.createContentCipher(key, algorithmIdentifier2);
        return new RecipientOperator(new InputDecryptor(){

            public AlgorithmIdentifier getAlgorithmIdentifier() {
                return algorithmIdentifier2;
            }

            public InputStream getInputStream(InputStream inputStream) {
                return new CipherInputStream(inputStream, cipher);
            }
        });
    }
}

