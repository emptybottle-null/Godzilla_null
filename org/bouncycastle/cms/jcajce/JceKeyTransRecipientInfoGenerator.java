/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.cms.jcajce;

import java.security.Provider;
import java.security.PublicKey;
import java.security.cert.CertificateEncodingException;
import java.security.cert.X509Certificate;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.cms.IssuerAndSerialNumber;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.cert.jcajce.JcaX509CertificateHolder;
import org.bouncycastle.cms.KeyTransRecipientInfoGenerator;
import org.bouncycastle.operator.AsymmetricKeyWrapper;
import org.bouncycastle.operator.jcajce.JceAsymmetricKeyWrapper;

public class JceKeyTransRecipientInfoGenerator
extends KeyTransRecipientInfoGenerator {
    public JceKeyTransRecipientInfoGenerator(X509Certificate x509Certificate) throws CertificateEncodingException {
        super(new IssuerAndSerialNumber(new JcaX509CertificateHolder(x509Certificate).toASN1Structure()), (AsymmetricKeyWrapper)new JceAsymmetricKeyWrapper(x509Certificate));
    }

    public JceKeyTransRecipientInfoGenerator(byte[] byArray, PublicKey publicKey) {
        super(byArray, (AsymmetricKeyWrapper)new JceAsymmetricKeyWrapper(publicKey));
    }

    public JceKeyTransRecipientInfoGenerator(X509Certificate x509Certificate, AlgorithmIdentifier algorithmIdentifier) throws CertificateEncodingException {
        super(new IssuerAndSerialNumber(new JcaX509CertificateHolder(x509Certificate).toASN1Structure()), (AsymmetricKeyWrapper)new JceAsymmetricKeyWrapper(algorithmIdentifier, x509Certificate.getPublicKey()));
    }

    public JceKeyTransRecipientInfoGenerator(byte[] byArray, AlgorithmIdentifier algorithmIdentifier, PublicKey publicKey) {
        super(byArray, (AsymmetricKeyWrapper)new JceAsymmetricKeyWrapper(algorithmIdentifier, publicKey));
    }

    public JceKeyTransRecipientInfoGenerator setProvider(String string) {
        ((JceAsymmetricKeyWrapper)this.wrapper).setProvider(string);
        return this;
    }

    public JceKeyTransRecipientInfoGenerator setProvider(Provider provider) {
        ((JceAsymmetricKeyWrapper)this.wrapper).setProvider(provider);
        return this;
    }

    public JceKeyTransRecipientInfoGenerator setAlgorithmMapping(ASN1ObjectIdentifier aSN1ObjectIdentifier, String string) {
        ((JceAsymmetricKeyWrapper)this.wrapper).setAlgorithmMapping(aSN1ObjectIdentifier, string);
        return this;
    }
}

