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
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.ASN1OctetString;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.cms.CMSException;
import org.bouncycastle.cms.PasswordRecipientInfoGenerator;
import org.bouncycastle.cms.jcajce.DefaultJcaJceExtHelper;
import org.bouncycastle.cms.jcajce.EnvelopedDataHelper;
import org.bouncycastle.cms.jcajce.NamedJcaJceExtHelper;
import org.bouncycastle.cms.jcajce.ProviderJcaJceExtHelper;
import org.bouncycastle.operator.GenericKey;

public class JcePasswordRecipientInfoGenerator
extends PasswordRecipientInfoGenerator {
    private EnvelopedDataHelper helper = new EnvelopedDataHelper(new DefaultJcaJceExtHelper());

    public JcePasswordRecipientInfoGenerator(ASN1ObjectIdentifier aSN1ObjectIdentifier, char[] cArray) {
        super(aSN1ObjectIdentifier, cArray);
    }

    public JcePasswordRecipientInfoGenerator setProvider(Provider provider) {
        this.helper = new EnvelopedDataHelper(new ProviderJcaJceExtHelper(provider));
        return this;
    }

    public JcePasswordRecipientInfoGenerator setProvider(String string) {
        this.helper = new EnvelopedDataHelper(new NamedJcaJceExtHelper(string));
        return this;
    }

    protected byte[] calculateDerivedKey(int n, AlgorithmIdentifier algorithmIdentifier, int n2) throws CMSException {
        return this.helper.calculateDerivedKey(n, this.password, algorithmIdentifier, n2);
    }

    public byte[] generateEncryptedBytes(AlgorithmIdentifier algorithmIdentifier, byte[] byArray, GenericKey genericKey) throws CMSException {
        Key key = this.helper.getJceKey(genericKey);
        Cipher cipher = this.helper.createRFC3211Wrapper(algorithmIdentifier.getAlgorithm());
        try {
            IvParameterSpec ivParameterSpec = new IvParameterSpec(ASN1OctetString.getInstance(algorithmIdentifier.getParameters()).getOctets());
            cipher.init(3, (Key)new SecretKeySpec(byArray, cipher.getAlgorithm()), ivParameterSpec);
            return cipher.wrap(key);
        } catch (GeneralSecurityException generalSecurityException) {
            throw new CMSException("cannot process content encryption key: " + generalSecurityException.getMessage(), generalSecurityException);
        }
    }
}

