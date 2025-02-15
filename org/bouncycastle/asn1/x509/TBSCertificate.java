/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.asn1.x509;

import java.math.BigInteger;
import org.bouncycastle.asn1.ASN1Integer;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.ASN1TaggedObject;
import org.bouncycastle.asn1.DERBitString;
import org.bouncycastle.asn1.x500.X500Name;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.asn1.x509.Extensions;
import org.bouncycastle.asn1.x509.SubjectPublicKeyInfo;
import org.bouncycastle.asn1.x509.Time;

public class TBSCertificate
extends ASN1Object {
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
    Extensions extensions;

    public static TBSCertificate getInstance(ASN1TaggedObject aSN1TaggedObject, boolean bl) {
        return TBSCertificate.getInstance(ASN1Sequence.getInstance(aSN1TaggedObject, bl));
    }

    public static TBSCertificate getInstance(Object object) {
        if (object instanceof TBSCertificate) {
            return (TBSCertificate)object;
        }
        if (object != null) {
            return new TBSCertificate(ASN1Sequence.getInstance(object));
        }
        return null;
    }

    private TBSCertificate(ASN1Sequence aSN1Sequence) {
        int n;
        int n2 = 0;
        this.seq = aSN1Sequence;
        if (aSN1Sequence.getObjectAt(0) instanceof ASN1TaggedObject) {
            this.version = ASN1Integer.getInstance((ASN1TaggedObject)aSN1Sequence.getObjectAt(0), true);
        } else {
            n2 = -1;
            this.version = new ASN1Integer(0L);
        }
        boolean bl = false;
        boolean bl2 = false;
        if (this.version.getValue().equals(BigInteger.valueOf(0L))) {
            bl = true;
        } else if (this.version.getValue().equals(BigInteger.valueOf(1L))) {
            bl2 = true;
        } else if (!this.version.getValue().equals(BigInteger.valueOf(2L))) {
            throw new IllegalArgumentException("version number not recognised");
        }
        this.serialNumber = ASN1Integer.getInstance(aSN1Sequence.getObjectAt(n2 + 1));
        this.signature = AlgorithmIdentifier.getInstance(aSN1Sequence.getObjectAt(n2 + 2));
        this.issuer = X500Name.getInstance(aSN1Sequence.getObjectAt(n2 + 3));
        ASN1Sequence aSN1Sequence2 = (ASN1Sequence)aSN1Sequence.getObjectAt(n2 + 4);
        this.startDate = Time.getInstance(aSN1Sequence2.getObjectAt(0));
        this.endDate = Time.getInstance(aSN1Sequence2.getObjectAt(1));
        this.subject = X500Name.getInstance(aSN1Sequence.getObjectAt(n2 + 5));
        this.subjectPublicKeyInfo = SubjectPublicKeyInfo.getInstance(aSN1Sequence.getObjectAt(n2 + 6));
        if (n != 0 && bl) {
            throw new IllegalArgumentException("version 1 certificate contains extra data");
        }
        block5: for (n = aSN1Sequence.size() - (n2 + 6) - 1; n > 0; --n) {
            ASN1TaggedObject aSN1TaggedObject = (ASN1TaggedObject)aSN1Sequence.getObjectAt(n2 + 6 + n);
            switch (aSN1TaggedObject.getTagNo()) {
                case 1: {
                    this.issuerUniqueId = DERBitString.getInstance(aSN1TaggedObject, false);
                    continue block5;
                }
                case 2: {
                    this.subjectUniqueId = DERBitString.getInstance(aSN1TaggedObject, false);
                    continue block5;
                }
                case 3: {
                    if (bl2) {
                        throw new IllegalArgumentException("version 2 certificate cannot contain extensions");
                    }
                    this.extensions = Extensions.getInstance(ASN1Sequence.getInstance(aSN1TaggedObject, true));
                }
            }
        }
    }

    public int getVersionNumber() {
        return this.version.getValue().intValue() + 1;
    }

    public ASN1Integer getVersion() {
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

    public Extensions getExtensions() {
        return this.extensions;
    }

    public ASN1Primitive toASN1Primitive() {
        return this.seq;
    }
}

