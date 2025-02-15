/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.openssl.jcajce;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.PrivateKey;
import java.security.Provider;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import org.bouncycastle.asn1.pkcs.PrivateKeyInfo;
import org.bouncycastle.cert.X509CertificateHolder;
import org.bouncycastle.cert.jcajce.JcaX509CertificateConverter;
import org.bouncycastle.openssl.PEMKeyPair;
import org.bouncycastle.openssl.PEMParser;
import org.bouncycastle.openssl.jcajce.JcaPEMKeyConverter;
import org.bouncycastle.pkix.jcajce.JcaPKIXIdentity;

public class JcaPKIXIdentityBuilder {
    private JcaPEMKeyConverter keyConverter = new JcaPEMKeyConverter();
    private JcaX509CertificateConverter certConverter = new JcaX509CertificateConverter();

    public JcaPKIXIdentityBuilder setProvider(Provider provider) {
        this.keyConverter = this.keyConverter.setProvider(provider);
        this.certConverter = this.certConverter.setProvider(provider);
        return this;
    }

    public JcaPKIXIdentityBuilder setProvider(String string) {
        this.keyConverter = this.keyConverter.setProvider(string);
        this.certConverter = this.certConverter.setProvider(string);
        return this;
    }

    public JcaPKIXIdentity build(File file, File file2) throws IOException, CertificateException {
        this.checkFile(file);
        this.checkFile(file2);
        FileInputStream fileInputStream = new FileInputStream(file);
        FileInputStream fileInputStream2 = new FileInputStream(file2);
        JcaPKIXIdentity jcaPKIXIdentity = this.build(fileInputStream, fileInputStream2);
        fileInputStream.close();
        fileInputStream2.close();
        return jcaPKIXIdentity;
    }

    public JcaPKIXIdentity build(InputStream inputStream, InputStream inputStream2) throws IOException, CertificateException {
        Object object;
        PrivateKey privateKey;
        Object object2;
        PEMParser pEMParser = new PEMParser(new InputStreamReader(inputStream));
        Object object3 = pEMParser.readObject();
        if (object3 instanceof PEMKeyPair) {
            object2 = (PEMKeyPair)object3;
            privateKey = this.keyConverter.getPrivateKey(((PEMKeyPair)object2).getPrivateKeyInfo());
        } else if (object3 instanceof PrivateKeyInfo) {
            privateKey = this.keyConverter.getPrivateKey((PrivateKeyInfo)object3);
        } else {
            throw new IOException("unrecognised private key file");
        }
        object2 = new PEMParser(new InputStreamReader(inputStream2));
        ArrayList<X509Certificate> arrayList = new ArrayList<X509Certificate>();
        while ((object = ((PEMParser)object2).readObject()) != null) {
            arrayList.add(this.certConverter.getCertificate((X509CertificateHolder)object));
        }
        return new JcaPKIXIdentity(privateKey, arrayList.toArray(new X509Certificate[arrayList.size()]));
    }

    private void checkFile(File file) throws IOException {
        if (file.canRead()) {
            if (file.exists()) {
                throw new IOException("Unable to open file " + file.getPath() + " for reading.");
            }
            throw new FileNotFoundException("Unable to open " + file.getPath() + ": it does not exist.");
        }
    }
}

