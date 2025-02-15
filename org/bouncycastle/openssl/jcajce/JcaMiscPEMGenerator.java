/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.openssl.jcajce;

import java.io.IOException;
import java.security.Key;
import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.Provider;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.cert.CRLException;
import java.security.cert.CertificateEncodingException;
import java.security.cert.X509CRL;
import java.security.cert.X509Certificate;
import org.bouncycastle.asn1.pkcs.PrivateKeyInfo;
import org.bouncycastle.asn1.x509.SubjectPublicKeyInfo;
import org.bouncycastle.cert.jcajce.JcaX509CRLHolder;
import org.bouncycastle.cert.jcajce.JcaX509CertificateHolder;
import org.bouncycastle.openssl.MiscPEMGenerator;
import org.bouncycastle.openssl.PEMEncryptor;

public class JcaMiscPEMGenerator
extends MiscPEMGenerator {
    private Object obj;
    private String algorithm;
    private char[] password;
    private SecureRandom random;
    private Provider provider;

    public JcaMiscPEMGenerator(Object object) throws IOException {
        super(JcaMiscPEMGenerator.convertObject(object));
    }

    public JcaMiscPEMGenerator(Object object, PEMEncryptor pEMEncryptor) throws IOException {
        super(JcaMiscPEMGenerator.convertObject(object), pEMEncryptor);
    }

    private static Object convertObject(Object object) throws IOException {
        if (object instanceof X509Certificate) {
            try {
                return new JcaX509CertificateHolder((X509Certificate)object);
            } catch (CertificateEncodingException certificateEncodingException) {
                throw new IllegalArgumentException("Cannot encode object: " + certificateEncodingException.toString());
            }
        }
        if (object instanceof X509CRL) {
            try {
                return new JcaX509CRLHolder((X509CRL)object);
            } catch (CRLException cRLException) {
                throw new IllegalArgumentException("Cannot encode object: " + cRLException.toString());
            }
        }
        if (object instanceof KeyPair) {
            return JcaMiscPEMGenerator.convertObject(((KeyPair)object).getPrivate());
        }
        if (object instanceof PrivateKey) {
            return PrivateKeyInfo.getInstance(((Key)object).getEncoded());
        }
        if (object instanceof PublicKey) {
            return SubjectPublicKeyInfo.getInstance(((PublicKey)object).getEncoded());
        }
        return object;
    }
}

