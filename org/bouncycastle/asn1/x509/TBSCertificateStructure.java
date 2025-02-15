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
import org.bouncycastle.asn1.DERTaggedObject;
import org.bouncycastle.asn1.pkcs.PKCSObjectIdentifiers;
import org.bouncycastle.asn1.x500.X500Name;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.asn1.x509.SubjectPublicKeyInfo;
import org.bouncycastle.asn1.x509.Time;
import org.bouncycastle.asn1.x509.X509Extensions;
import org.bouncycastle.asn1.x509.X509ObjectIdentifiers;

public class TBSCertificateStructure
extends ASN1Object
implements X509ObjectIdentifiers,
PKCSObjectIdentifiers {
    ASN1Sequence seq;
    ASN1Integer version;
    ASN1Integer serialNumber;
    AlgorithmIdentifier signature;
    X500Name issuer;
    Time startDate;
    Time endDate;
    X500Name subject;
    SubjectPublicKeyInfo subjectPublicKeyInfo;
    DERBitString issuerUniqueId;
    DERBitString subjectUniqueId;
    X509Extensions extensions;

    public static TBSCertificateStructure getInstance(ASN1TaggedObject aSN1TaggedObject, boolean bl) {
        return TBSCertificateStructure.getInstance(ASN1Sequence.getInstance(aSN1TaggedObject, bl));
    }

    public static TBSCertificateStructure getInstance(Object object) {
        if (object instanceof TBSCertificateStructure) {
            return (TBSCertificateStructure)object;
        }
        if (object != null) {
            return new TBSCertificateStructure(ASN1Sequence.getInstance(object));
        }
        return null;
    }

    public TBSCertificateStructure(ASN1Sequence aSN1Sequence) {
        int n = 0;
        this.seq = aSN1Sequence;
        if (aSN1Sequence.getObjectAt(0) instanceof DERTaggedObject) {
            this.version = ASN1Integer.getInstance((ASN1TaggedObject)aSN1Sequence.getObjectAt(0), true);
        } else {
            n = -1;
            this.version = new ASN1Integer(0L);
        }
        this.serialNumber = ASN1Integer.getInstance(aSN1Sequence.getObjectAt(n + 1));
        this.signature = AlgorithmIdentifier.getInstance(aSN1Sequence.getObjectAt(n + 2));
        this.issuer = X500Name.getInstance(aSN1Sequence.getObjectAt(n + 3));
        ASN1Sequence aSN1Sequence2 = (ASN1Sequence)aSN1Sequence.getObjectAt(n + 4);
        this.startDate = Time.getInstance(aSN1Sequence2.getObjectAt(0));
        this.endDate = Time.getInstance(aSN1Sequence2.getObjectAt(1));
        this.subject = X500Name.getInstance(aSN1Sequence.getObjectAt(n + 5));
        this.subjectPublicKeyInfo = SubjectPublicKeyInfo.getInstance(aSN1Sequence.getObjectAt(n + 6));
        block5: for (int i = aSN1Sequence.size() - (n + 6) - 1; i > 0; --i) {
            DERTaggedObject dERTaggedObject = (DERTaggedObject)aSN1Sequence.getObjectAt(n + 6 + i);
            switch (dERTaggedObject.getTagNo()) {
                case 1: {
                    this.issuerUniqueId = DERBitString.getInstance(dERTaggedObject, false);
                    continue block5;
                }
                case 2: {
                    this.subjectUniqueId = DERBitString.getInstance(dERTaggedObject, false);
                    continue block5;
                }
                case 3: {
                    this.extensions = X509Extensions.getInstance(dERTaggedObject);
                }
            }
        }
    }

    public int getVersion() {
        return this.version.getValue().intValue() + 1;
    }

    public ASN1Integer getVersionNumber() {
        return this.version;
    }

    public ASN1Integer getSerialNumber() {
        return this.serialNumber;
    }

    public AlgorithmIdentifier getSignature() {
        return this.signature;
    }

    public X500Name getIssuer() {
        return this.issuer;
    }

    public Time getStartDate() {
        return this.startDate;
    }

    public Time getEndDate() {
        return this.endDate;
    }

    public X500Name getSubject() {
        return this.subject;
    }

    public SubjectPublicKeyInfo getSubjectPublicKeyInfo() {
        return this.subjectPublicKeyInfo;
    }

    public DERBitString getIssuerUniqueId() {
        return this.issuerUniqueId;
    }

    public DERBitString getSubjectUniqueId() {
        return this.subjectUniqueId;
    }

    public X509Extensions getExtensions() {
        return this.extensions;
    }

    public ASN1Primitive toASN1Primitive() {
        return this.seq;
    }
}

