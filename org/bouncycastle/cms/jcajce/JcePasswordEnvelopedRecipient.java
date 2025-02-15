/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.cms.jcajce;

import java.io.InputStream;
import java.security.Key;
import javax.crypto.Cipher;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.cms.CMSException;
import org.bouncycastle.cms.RecipientOperator;
import org.bouncycastle.cms.jcajce.JcePasswordRecipient;
import org.bouncycastle.jcajce.io.CipherInputStream;
import org.bouncycastle.operator.InputDecryptor;

public class JcePasswordEnvelopedRecipient
extends JcePasswordRecipient {
    public JcePasswordEnvelopedRecipient(char[] cArray) {
        super(cArray);
    }

    public RecipientOperator getRecipientOperator(AlgorithmIdentifier algorithmIdentifier, final AlgorithmIdentifier algorithmIdentifier2, byte[] byArray, byte[] byArray2) throws CMSException {
        Key key = this.extractSecretKey(algorithmIdentifier, algorithmIdentifier2, byArray, byArray2);
        final Cipher cipher = this.helper.createContentCipher(key, algorithmIdentifier2);
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

