/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.asn1.x509;

import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1Integer;
import org.bouncycastle.asn1.ASN1UTCTime;
import org.bouncycastle.asn1.DERBitString;
import org.bouncycastle.asn1.DERSequence;
import org.bouncycastle.asn1.DERTaggedObject;
import org.bouncycastle.asn1.x500.X500Name;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.asn1.x509.Extension;
import org.bouncycastle.asn1.x509.Extensions;
import org.bouncycastle.asn1.x509.SubjectPublicKeyInfo;
import org.bouncycastle.asn1.x509.TBSCertificate;
import org.bouncycastle.asn1.x509.Time;
import org.bouncycastle.asn1.x509.X509Extensions;
import org.bouncycastle.asn1.x509.X509Name;

public class V3TBSCertificateGenerator {
    DERTaggedObject version = new DERTaggedObject(true, 0, new ASN1Integer(2L));
    ASN1Integer serialNumber;
    AlgorithmIdentifier signature;
    X500Name issuer;
    Time startDate;
    Time endDate;
    X500Name subject;
    SubjectPublicKeyInfo subjectPublicKeyInfo;
    Extensions extensions;
    private boolean altNamePresentAndCritical;
    private DERBitString issuerUniqueID;
    private DERBitString subjectUniqueID;

    public void setSerialNumber(ASN1Integer aSN1Integer) {
        this.serialNumber = aSN1Integer;
    }

    public void setSignature(AlgorithmIdentifier algorithmIdentifier) {
        this.signature = algorithmIdentifier;
    }

    public void setIssuer(X509Name x509Name) {
        this.issuer = X500Name.getInstance(x509Name);
    }

    public void setIssuer(X500Name x500Name) {
        this.issuer = x500Name;
    }

    public void setStartDate(ASN1UTCTime aSN1UTCTime) {
        this.startDate = new Time(aSN1UTCTime);
    }

    public void setStartDate(Time time) {
        this.startDate = time;
    }

    public void setEndDate(ASN1UTCTime aSN1UTCTime) {
        this.endDate = new Time(aSN1UTCTime);
    }

    public void setEndDate(Time time) {
        this.endDate = time;
    }

    public void setSubject(X509Name x509Name) {
        this.subject = X500Name.getInstance(x509Name.toASN1Primitive());
    }

    public void setSubject(X500Name x500Name) {
        this.subject = x500Name;
    }

    public void setIssuerUniqueID(DERBitString dERBitString) {
        this.issuerUniqueID = dERBitString;
    }

    public void setSubjectUniqueID(DERBitString dERBitString) {
        this.subjectUniqueID = dERBitString;
    }

    public void setSubjectPublicKeyInfo(SubjectPublicKeyInfo subjectPublicKeyInfo) {
        this.subjectPublicKeyInfo = subjectPublicKeyInfo;
    }

    public void setExtensions(X509Extensions x509Extensions) {
        this.setExtensions(Extensions.getInstance(x509Extensions));
    }

    public void setExtensions(Extensions extensions) {
        Extension extension;
        this.extensions = extensions;
        if (extensions != null && (extension = extensions.getExtension(Extension.subjectAlternativeName)) != null && extension.isCritical()) {
            this.altNamePresentAndCritical = true;
        }
    }

    public TBSCertificate generateTBSCertificate() {
        if (this.serialNumber == null || this.signature == null || this.issuer == null || this.startDate == null || this.endDate == null || this.subject == null && !this.altNamePresentAndCritical || this.subjectPublicKeyInfo == null) {
            throw new IllegalStateException("not all mandatory fields set in V3 TBScertificate generator");
        }
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        aSN1EncodableVector.add(this.version);
        aSN1EncodableVector.add(this.serialNumber);
        aSN1EncodableVector.add(this.signature);
        aSN1EncodableVector.add(this.issuer);
        ASN1EncodableVector aSN1EncodableVector2 = new ASN1EncodableVector();
        aSN1EncodableVector2.add(this.startDate);
        aSN1EncodableVector2.add(this.endDate);
        aSN1EncodableVector.add(new DERSequence(aSN1EncodableVector2));
        if (this.subject != null) {
            aSN1EncodableVector.add(this.subject);
        } else {
            aSN1EncodableVector.add(new DERSequence());
        }
        aSN1EncodableVector.add(this.subjectPublicKeyInfo);
        if (this.issuerUniqueID != null) {
            aSN1EncodableVector.add(new DERTaggedObject(false, 1, this.issuerUniqueID));
        }
        if (this.subjectUniqueID != null) {
            aSN1EncodableVector.add(new DERTaggedObject(false, 2, this.subjectUniqueID));
        }
        if (this.extensions != null) {
            aSN1EncodableVector.add(new DERTaggedObject(true, 3, this.extensions));
        }
        return TBSCertificate.getInstance(new DERSequence(aSN1EncodableVector));
    }
}

