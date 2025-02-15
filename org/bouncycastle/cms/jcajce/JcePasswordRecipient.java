/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.cms.jcajce;

import java.security.GeneralSecurityException;
import java.security.Key;
import java.security.Provider;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import org.bouncycastle.asn1.ASN1OctetString;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.cms.CMSException;
import org.bouncycastle.cms.PasswordRecipient;
import org.bouncycastle.cms.jcajce.DefaultJcaJceExtHelper;
import org.bouncycastle.cms.jcajce.EnvelopedDataHelper;
import org.bouncycastle.cms.jcajce.NamedJcaJceExtHelper;
import org.bouncycastle.cms.jcajce.ProviderJcaJceExtHelper;

public abstract class JcePasswordRecipient
implements PasswordRecipient {
    private int schemeID = 1;
    protected EnvelopedDataHelper helper = new EnvelopedDataHelper(new DefaultJcaJceExtHelper());
    private char[] password;

    JcePasswordRecipient(char[] cArray) {
        this.password = cArray;
    }

    public JcePasswordRecipient setPasswordConversionScheme(int n) {
        this.schemeID = n;
        return this;
    }

    public JcePasswordRecipient setProvider(Provider provider) {
        this.helper = new EnvelopedDataHelper(new ProviderJcaJceExtHelper(provider));
        return this;
    }

    public JcePasswordRecipient setProvider(String string) {
        this.helper = new EnvelopedDataHelper(new NamedJcaJceExtHelper(string));
        return this;
    }

    protected Key extractSecretKey(AlgorithmIdentifier algorithmIdentifier, AlgorithmIdentifier algorithmIdentifier2, byte[] byArray, byte[] byArray2) throws CMSException {
        Cipher cipher = this.helper.createRFC3211Wrapper(algorithmIdentifier.getAlgorithm());
        try {
            IvParameterSpec ivParameterSpec = new IvParameterSpec(ASN1OctetString.getInstance(algorithmIdentifier.getParameters()).getOctets());
            cipher.init(4, (Key)new SecretKeySpec(byArray, cipher.getAlgorithm()), ivParameterSpec);
            return cipher.unwrap(byArray2, algorithmIdentifier2.getAlgorithm().getId(), 3);
        } catch (GeneralSecurityException generalSecurityException) {
            throw new CMSException("cannot process content encryption key: " + generalSecurityException.getMessage(), generalSecurityException);
        }
    }

    public byte[] calculateDerivedKey(int n, AlgorithmIdentifier algorithmIdentifier, int n2) throws CMSException {
        return this.helper.calculateDerivedKey(n, this.password, algorithmIdentifier, n2);
    }

    public int getPasswordConversionScheme() {
        return this.schemeID;
    }

    public char[] getPassword() {
        return this.password;
    }
}

