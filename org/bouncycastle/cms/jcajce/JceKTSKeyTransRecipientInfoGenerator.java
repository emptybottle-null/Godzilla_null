/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.cms.jcajce;

import java.io.IOException;
import java.security.Provider;
import java.security.PublicKey;
import java.security.cert.CertificateEncodingException;
import java.security.cert.X509Certificate;
import org.bouncycastle.asn1.DEROctetString;
import org.bouncycastle.asn1.cms.IssuerAndSerialNumber;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.cert.jcajce.JcaX509CertificateHolder;
import org.bouncycastle.cms.KeyTransRecipientInfoGenerator;
import org.bouncycastle.operator.AsymmetricKeyWrapper;
import org.bouncycastle.operator.jcajce.JceAsymmetricKeyWrapper;
import org.bouncycastle.operator.jcajce.JceKTSKeyWrapper;
import org.bouncycastle.util.encoders.Hex;

public class JceKTSKeyTransRecipientInfoGenerator
extends KeyTransRecipientInfoGenerator {
    private static final byte[] ANONYMOUS_SENDER = Hex.decode("0c14416e6f6e796d6f75732053656e64657220202020");

    private JceKTSKeyTransRecipientInfoGenerator(X509Certificate x509Certificate, IssuerAndSerialNumber issuerAndSerialNumber, String string, int n) throws CertificateEncodingException {
        super(issuerAndSerialNumber, (AsymmetricKeyWrapper)new JceKTSKeyWrapper(x509Certificate, string, n, ANONYMOUS_SENDER, JceKTSKeyTransRecipientInfoGenerator.getEncodedRecipID(issuerAndSerialNumber)));
    }

    public JceKTSKeyTransRecipientInfoGenerator(X509Certificate x509Certificate, String string, int n) throws CertificateEncodingException {
        this(x509Certificate, new IssuerAndSerialNumber(new JcaX509CertificateHolder(x509Certificate).toASN1Structure()), string, n);
    }

    public JceKTSKeyTransRecipientInfoGenerator(byte[] byArray, PublicKey publicKey, String string, int n) {
        super(byArray, (AsymmetricKeyWrapper)new JceKTSKeyWrapper(publicKey, string, n, ANONYMOUS_SENDER, JceKTSKeyTransRecipientInfoGenerator.getEncodedSubKeyId(byArray)));
    }

    private static byte[] getEncodedRecipID(IssuerAndSerialNumber issuerAndSerialNumber) throws CertificateEncodingException {
        try {
            return issuerAndSerialNumber.getEncoded("DER");
        } catch (IOException iOException) {
            throw new CertificateEncodingException("Cannot process extracted IssuerAndSerialNumber: " + iOException.getMessage()){

                public Throwable getCause() {
                    return iOException;
                }
            };
        }
    }

    private static byte[] getEncodedSubKeyId(byte[] byArray) {
        try {
            return new DEROctetString(byArray).getEncoded();
        } catch (IOException iOException) {
            throw new IllegalArgumentException("Cannot process subject key identifier: " + iOException.getMessage()){

                public Throwable getCause() {
                    return iOException;
                }
            };
        }
    }

    public JceKTSKeyTransRecipientInfoGenerator(X509Certificate x509Certificate, AlgorithmIdentifier algorithmIdentifier) throws CertificateEncodingException {
        super(new IssuerAndSerialNumber(new JcaX509CertificateHolder(x509Certificate).toASN1Structure()), (AsymmetricKeyWrapper)new JceAsymmetricKeyWrapper(algorithmIdentifier, x509Certificate.getPublicKey()));
    }

    public JceKTSKeyTransRecipientInfoGenerator(byte[] byArray, AlgorithmIdentifier algorithmIdentifier, PublicKey publicKey) {
        super(byArray, (AsymmetricKeyWrapper)new JceAsymmetricKeyWrapper(algorithmIdentifier, publicKey));
    }

    public JceKTSKeyTransRecipientInfoGenerator setProvider(String string) {
        ((JceKTSKeyWrapper)this.wrapper).setProvider(string);
        return this;
    }

    public JceKTSKeyTransRecipientInfoGenerator setProvider(Provider provider) {
        ((JceKTSKeyWrapper)this.wrapper).setProvider(provider);
        return this;
    }
}

