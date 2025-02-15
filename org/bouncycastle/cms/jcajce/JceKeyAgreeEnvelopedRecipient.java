/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.cms.jcajce;

import java.io.InputStream;
import java.security.Key;
import java.security.PrivateKey;
import javax.crypto.Cipher;
import org.bouncycastle.asn1.ASN1OctetString;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.asn1.x509.SubjectPublicKeyInfo;
import org.bouncycastle.cms.CMSException;
import org.bouncycastle.cms.RecipientOperator;
import org.bouncycastle.cms.jcajce.JceKeyAgreeRecipient;
import org.bouncycastle.jcajce.io.CipherInputStream;
import org.bouncycastle.operator.InputDecryptor;

public class JceKeyAgreeEnvelopedRecipient
extends JceKeyAgreeRecipient {
    public JceKeyAgreeEnvelopedRecipient(PrivateKey privateKey) {
        super(privateKey);
    }

    public RecipientOperator getRecipientOperator(AlgorithmIdentifier algorithmIdentifier, final AlgorithmIdentifier algorithmIdentifier2, SubjectPublicKeyInfo subjectPublicKeyInfo, ASN1OctetString aSN1OctetString, byte[] byArray) throws CMSException {
        Key key = this.extractSecretKey(algorithmIdentifier, algorithmIdentifier2, subjectPublicKeyInfo, aSN1OctetString, byArray);
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

