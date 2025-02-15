/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.x509;

import java.io.IOException;
import java.math.BigInteger;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PublicKey;
import java.security.SignatureException;
import java.security.cert.CertificateException;
import java.security.cert.CertificateExpiredException;
import java.security.cert.CertificateNotYetValidException;
import java.security.cert.X509Extension;
import java.util.Date;
import org.bouncycastle.x509.AttributeCertificateHolder;
import org.bouncycastle.x509.AttributeCertificateIssuer;
import org.bouncycastle.x509.X509Attribute;

public interface X509AttributeCertificate
extends X509Extension {
    public int getVersion();

    public BigInteger getSerialNumber();

    public Date getNotBefore();

    public Date getNotAfter();

    public AttributeCertificateHolder getHolder();

    public AttributeCertificateIssuer getIssuer();

    public X509Attribute[] getAttributes();

    public X509Attribute[] getAttributes(String var1);

    public boolean[] getIssuerUniqueID();

    public void checkValidity() throws CertificateExpiredException, CertificateNotYetValidException;

    public void checkValidity(Date var1) throws CertificateExpiredException, CertificateNotYetValidException;

    public byte[] getSignature();

    public void verify(PublicKey var1, String var2) throws CertificateException, NoSuchAlgorithmException, InvalidKeyException, NoSuchProviderException, SignatureException;

    public byte[] getEncoded() throws IOException;
}

