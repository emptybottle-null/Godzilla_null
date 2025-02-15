/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.jce;

import java.io.IOException;
import java.security.Principal;
import java.util.Hashtable;
import java.util.Vector;
import org.bouncycastle.asn1.ASN1InputStream;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.x500.X500Name;
import org.bouncycastle.asn1.x509.X509Name;

public class X509Principal
extends X509Name
implements Principal {
    private static ASN1Sequence readSequence(ASN1InputStream aSN1InputStream) throws IOException {
        try {
            return ASN1Sequence.getInstance(aSN1InputStream.readObject());
        } catch (IllegalArgumentException illegalArgumentException) {
            throw new IOException("not an ASN.1 Sequence: " + illegalArgumentException);
        }
    }

    public X509Principal(byte[] byArray) throws IOException {
        super(X509Principal.readSequence(new ASN1InputStream(byArray)));
    }

    public X509Principal(X509Name x509Name) {
        super((ASN1Sequence)x509Name.toASN1Primitive());
    }

    public X509Principal(X500Name x500Name) {
        super((ASN1Sequence)x500Name.toASN1Primitive());
    }

    public X509Principal(Hashtable hashtable) {
        super(hashtable);
    }

    public X509Principal(Vector vector, Hashtable hashtable) {
        super(vector, hashtable);
    }

    public X509Principal(Vector vector, Vector vector2) {
        super(vector, vector2);
    }

    public X509Principal(String string) {
        super(string);
    }

    public X509Principal(boolean bl, String string) {
        super(bl, string);
    }

    public X509Principal(boolean bl, Hashtable hashtable, String string) {
        super(bl, hashtable, string);
    }

    public String getName() {
        return this.toString();
    }

    public byte[] getEncoded() {
        try {
            return this.getEncoded("DER");
        } catch (IOException iOException) {
            throw new RuntimeException(iOException.toString());
        }
    }
}

