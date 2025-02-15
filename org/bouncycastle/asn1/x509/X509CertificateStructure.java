/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.asn1.x509;

import org.bouncycastle.asn1.ASN1Integer;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.ASN1TaggedObject;
import org.bouncycastle.asn1.DERBitString;
import org.bouncycastle.asn1.pkcs.PKCSObjectIdentifiers;
import org.bouncycastle.asn1.x500.X500Name;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.asn1.x509.SubjectPublicKeyInfo;
import org.bouncycastle.asn1.x509.TBSCertificateStructure;
import org.bouncycastle.asn1.x509.Time;
import org.bouncycastle.asn1.x509.X509ObjectIdentifiers;

public class X509CertificateStructure
extends ASN1Object
implements X509ObjectIdentifiers,
PKCSObjectIdentifiers {
    ASN1Sequence seq;
    TBSCertificateStructure tbsCert;
    AlgorithmIdentifier sigAlgId;
    DERBitString sig;

    public static X509CertificateStructure getInstance(ASN1TaggedObject aSN1TaggedObject, boolean bl) {
        return X509CertificateStructure.getInstance(ASN1Sequence.getInstance(aSN1TaggedObject, bl));
    }

    public static X509CertificateStructure getInstance(Object object) {
        if (object instanceof X509CertificateStructure) {
            return (X509CertificateStructure)object;
        }
        if (object != null) {
            return new X509CertificateStructure(ASN1Sequence.getInstance(object));
        }
        return null;
    }

    public X509CertificateStructure(ASN1Sequence aSN1Sequence) {
        this.seq = aSN1Sequence;
        if (aSN1Sequence.size() != 3) {
            throw new IllegalArgumentException("sequence wrong size for a certificate");
        }
        this.tbsCert = TBSCertificateStructure.getInstance(aSN1Sequence.getObjectAt(0));
        this.sigAlgId = AlgorithmIdentifier.getInstance(aSN1Sequence.getObjectAt(1));
        this.sig = DERBitString.getInstance(aSN1Sequence.getObjectAt(2));
    }

    public TBSCertificateStructure getTBSCertificate() {
        return this.tbsCert;
    }

    public int getVersion() {
        return this.tbsCert.getVersion();
    }

    public ASN1Integer getSerialNumber() {
        return this.tbsCert.getSerialNumber();
    }

    public X500Name getIssuer() {
        return this.tbsCert.getIssuer();
    }

    public Time getStartDate() {
        return this.tbsCert.getStartDate();
    }

    public Time getEndDate() {
        return this.tbsCert.getEndDate();
    }

    public X500Name getSubject() {
        return this.tbsCert.getSubject();
    }

    public SubjectPublicKeyInfo getSubjectPublicKeyInfo() {
        return this.tbsCert.getSubjectPublicKeyInfo();
    }

    public AlgorithmIdentifier getSignatureAlgorithm() {
        return this.sigAlgId;
    }

    public DERBitString getSignature() {
        return this.sig;
    }

    public ASN1Primitive toASN1Primitive() {
        return this.seq;
    }
}

