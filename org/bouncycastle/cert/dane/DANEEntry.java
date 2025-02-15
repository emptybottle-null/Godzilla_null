/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.cert.dane;

import java.io.IOException;
import org.bouncycastle.cert.X509CertificateHolder;
import org.bouncycastle.util.Arrays;

public class DANEEntry {
    public static final int CERT_USAGE_CA = 0;
    public static final int CERT_USAGE_PKIX_VALIDATE = 1;
    public static final int CERT_USAGE_TRUST_ANCHOR = 2;
    public static final int CERT_USAGE_ACCEPT = 3;
    static final int CERT_USAGE = 0;
    static final int SELECTOR = 1;
    static final int MATCHING_TYPE = 2;
    private final String domainName;
    private final byte[] flags;
    private final X509CertificateHolder certHolder;

    DANEEntry(String string, byte[] byArray, X509CertificateHolder x509CertificateHolder) {
        this.flags = byArray;
        this.domainName = string;
        this.certHolder = x509CertificateHolder;
    }

    public DANEEntry(String string, byte[] byArray) throws IOException {
        this(string, Arrays.copyOfRange(byArray, 0, 3), new X509CertificateHolder(Arrays.copyOfRange(byArray, 3, byArray.length)));
    }

    public byte[] getFlags() {
        return Arrays.clone(this.flags);
    }

    public X509CertificateHolder getCertificate() {
        return this.certHolder;
    }

    public String getDomainName() {
        return this.domainName;
    }

    public byte[] getRDATA() throws IOException {
        byte[] byArray = this.certHolder.getEncoded();
        byte[] byArray2 = new byte[this.flags.length + byArray.length];
        System.arraycopy(this.flags, 0, byArray2, 0, this.flags.length);
        System.arraycopy(byArray, 0, byArray2, this.flags.length, byArray.length);
        return byArray2;
    }

    public static boolean isValidCertificate(byte[] byArray) {
        return (byArray[0] >= 0 || byArray[0] <= 3) && byArray[1] == 0 && byArray[2] == 0;
    }
}

