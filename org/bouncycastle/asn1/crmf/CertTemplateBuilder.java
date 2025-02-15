/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.asn1.crmf;

import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1Integer;
import org.bouncycastle.asn1.DERBitString;
import org.bouncycastle.asn1.DERSequence;
import org.bouncycastle.asn1.DERTaggedObject;
import org.bouncycastle.asn1.crmf.CertTemplate;
import org.bouncycastle.asn1.crmf.OptionalValidity;
import org.bouncycastle.asn1.x500.X500Name;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.asn1.x509.Extensions;
import org.bouncycastle.asn1.x509.SubjectPublicKeyInfo;
import org.bouncycastle.asn1.x509.X509Extensions;

public class CertTemplateBuilder {
    private ASN1Integer version;
    private ASN1Integer serialNumber;
    private AlgorithmIdentifier signingAlg;
    private X500Name issuer;
    private OptionalValidity validity;
    private X500Name subject;
    private SubjectPublicKeyInfo publicKey;
    private DERBitString issuerUID;
    private DERBitString subjectUID;
    private Extensions extensions;

    public CertTemplateBuilder setVersion(int n) {
        this.version = new ASN1Integer(n);
        return this;
    }

    public CertTemplateBuilder setSerialNumber(ASN1Integer aSN1Integer) {
        this.serialNumber = aSN1Integer;
        return this;
    }

    public CertTemplateBuilder setSigningAlg(AlgorithmIdentifier algorithmIdentifier) {
        this.signingAlg = algorithmIdentifier;
        return this;
    }

    public CertTemplateBuilder setIssuer(X500Name x500Name) {
        this.issuer = x500Name;
        return this;
    }

    public CertTemplateBuilder setValidity(OptionalValidity optionalValidity) {
        this.validity = optionalValidity;
        return this;
    }

    public CertTemplateBuilder setSubject(X500Name x500Name) {
        this.subject = x500Name;
        return this;
    }

    public CertTemplateBuilder setPublicKey(SubjectPublicKeyInfo subjectPublicKeyInfo) {
        this.publicKey = subjectPublicKeyInfo;
        return this;
    }

    public CertTemplateBuilder setIssuerUID(DERBitString dERBitString) {
        this.issuerUID = dERBitString;
        return this;
    }

    public CertTemplateBuilder setSubjectUID(DERBitString dERBitString) {
        this.subjectUID = dERBitString;
        return this;
    }

    public CertTemplateBuilder setExtensions(X509Extensions x509Extensions) {
        return this.setExtensions(Extensions.getInstance(x509Extensions));
    }

    public CertTemplateBuilder setExtensions(Extensions extensions) {
        this.extensions = extensions;
        return this;
    }

    public CertTemplate build() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        this.addOptional(aSN1EncodableVector, 0, false, this.version);
        this.addOptional(aSN1EncodableVector, 1, false, this.serialNumber);
        this.addOptional(aSN1EncodableVector, 2, false, this.signingAlg);
        this.addOptional(aSN1EncodableVector, 3, true, this.issuer);
        this.addOptional(aSN1EncodableVector, 4, false, this.validity);
        this.addOptional(aSN1EncodableVector, 5, true, this.subject);
        this.addOptional(aSN1EncodableVector, 6, false, this.publicKey);
        this.addOptional(aSN1EncodableVector, 7, false, this.issuerUID);
        this.addOptional(aSN1EncodableVector, 8, false, this.subjectUID);
        this.addOptional(aSN1EncodableVector, 9, false, this.extensions);
        return CertTemplate.getInstance(new DERSequence(aSN1EncodableVector));
    }

    private void addOptional(ASN1EncodableVector aSN1EncodableVector, int n, boolean bl, ASN1Encodable aSN1Encodable) {
        if (aSN1Encodable != null) {
            aSN1EncodableVector.add(new DERTaggedObject(bl, n, aSN1Encodable));
        }
    }
}

