/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.asn1.x509;

import java.math.BigInteger;
import java.util.Enumeration;
import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1Integer;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1OctetString;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.ASN1TaggedObject;
import org.bouncycastle.asn1.DEROctetString;
import org.bouncycastle.asn1.DERSequence;
import org.bouncycastle.asn1.DERTaggedObject;
import org.bouncycastle.asn1.x509.Extension;
import org.bouncycastle.asn1.x509.Extensions;
import org.bouncycastle.asn1.x509.GeneralNames;
import org.bouncycastle.asn1.x509.SubjectPublicKeyInfo;
import org.bouncycastle.crypto.digests.SHA1Digest;

public class AuthorityKeyIdentifier
extends ASN1Object {
    ASN1OctetString keyidentifier = null;
    GeneralNames certissuer = null;
    ASN1Integer certserno = null;

    public static AuthorityKeyIdentifier getInstance(ASN1TaggedObject aSN1TaggedObject, boolean bl) {
        return AuthorityKeyIdentifier.getInstance(ASN1Sequence.getInstance(aSN1TaggedObject, bl));
    }

    public static AuthorityKeyIdentifier getInstance(Object object) {
        if (object instanceof AuthorityKeyIdentifier) {
            return (AuthorityKeyIdentifier)object;
        }
        if (object != null) {
            return new AuthorityKeyIdentifier(ASN1Sequence.getInstance(object));
        }
        return null;
    }

    public static AuthorityKeyIdentifier fromExtensions(Extensions extensions) {
        return AuthorityKeyIdentifier.getInstance(extensions.getExtensionParsedValue(Extension.authorityKeyIdentifier));
    }

    protected AuthorityKeyIdentifier(ASN1Sequence aSN1Sequence) {
        Enumeration enumeration = aSN1Sequence.getObjects();
        block5: while (enumeration.hasMoreElements()) {
            ASN1TaggedObject aSN1TaggedObject = DERTaggedObject.getInstance(enumeration.nextElement());
            switch (aSN1TaggedObject.getTagNo()) {
                case 0: {
                    this.keyidentifier = ASN1OctetString.getInstance(aSN1TaggedObject, false);
                    continue block5;
                }
                case 1: {
                    this.certissuer = GeneralNames.getInstance(aSN1TaggedObject, false);
                    continue block5;
                }
                case 2: {
                    this.certserno = ASN1Integer.getInstance(aSN1TaggedObject, false);
                    continue block5;
                }
            }
            throw new IllegalArgumentException("illegal tag");
        }
    }

    public AuthorityKeyIdentifier(SubjectPublicKeyInfo subjectPublicKeyInfo) {
        SHA1Digest sHA1Digest = new SHA1Digest();
        byte[] byArray = new byte[sHA1Digest.getDigestSize()];
        byte[] byArray2 = subjectPublicKeyInfo.getPublicKeyData().getBytes();
        sHA1Digest.update(byArray2, 0, byArray2.length);
        sHA1Digest.doFinal(byArray, 0);
        this.keyidentifier = new DEROctetString(byArray);
    }

    public AuthorityKeyIdentifier(SubjectPublicKeyInfo subjectPublicKeyInfo, GeneralNames generalNames, BigInteger bigInteger) {
        SHA1Digest sHA1Digest = new SHA1Digest();
        byte[] byArray = new byte[sHA1Digest.getDigestSize()];
        byte[] byArray2 = subjectPublicKeyInfo.getPublicKeyData().getBytes();
        sHA1Digest.update(byArray2, 0, byArray2.length);
        sHA1Digest.doFinal(byArray, 0);
        this.keyidentifier = new DEROctetString(byArray);
        this.certissuer = GeneralNames.getInstance(generalNames.toASN1Primitive());
        this.certserno = new ASN1Integer(bigInteger);
    }

    public AuthorityKeyIdentifier(GeneralNames generalNames, BigInteger bigInteger) {
        this((byte[])null, generalNames, bigInteger);
    }

    public AuthorityKeyIdentifier(byte[] byArray) {
        this(byArray, null, null);
    }

    public AuthorityKeyIdentifier(byte[] byArray, GeneralNames generalNames, BigInteger bigInteger) {
        this.keyidentifier = byArray != null ? new DEROctetString(byArray) : null;
        this.certissuer = generalNames;
        this.certserno = bigInteger != null ? new ASN1Integer(bigInteger) : null;
    }

    public byte[] getKeyIdentifier() {
        if (this.keyidentifier != null) {
            return this.keyidentifier.getOctets();
        }
        return null;
    }

    public GeneralNames getAuthorityCertIssuer() {
        return this.certissuer;
    }

    public BigInteger getAuthorityCertSerialNumber() {
        if (this.certserno != null) {
            return this.certserno.getValue();
        }
        return null;
    }

    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        if (this.keyidentifier != null) {
            aSN1EncodableVector.add(new DERTaggedObject(false, 0, this.keyidentifier));
        }
        if (this.certissuer != null) {
            aSN1EncodableVector.add(new DERTaggedObject(false, 1, this.certissuer));
        }
        if (this.certserno != null) {
            aSN1EncodableVector.add(new DERTaggedObject(false, 2, this.certserno));
        }
        return new DERSequence(aSN1EncodableVector);
    }

    public String toString() {
        return "AuthorityKeyIdentifier: KeyID(" + this.keyidentifier.getOctets() + ")";
    }
}

