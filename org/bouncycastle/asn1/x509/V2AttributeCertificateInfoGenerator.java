/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.asn1.x509;

import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1GeneralizedTime;
import org.bouncycastle.asn1.ASN1Integer;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.DERBitString;
import org.bouncycastle.asn1.DERSequence;
import org.bouncycastle.asn1.DERSet;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.asn1.x509.AttCertIssuer;
import org.bouncycastle.asn1.x509.AttCertValidityPeriod;
import org.bouncycastle.asn1.x509.Attribute;
import org.bouncycastle.asn1.x509.AttributeCertificateInfo;
import org.bouncycastle.asn1.x509.Extensions;
import org.bouncycastle.asn1.x509.Holder;
import org.bouncycastle.asn1.x509.X509Extensions;

public class V2AttributeCertificateInfoGenerator {
    private ASN1Integer version = new ASN1Integer(1L);
    private Holder holder;
    private AttCertIssuer issuer;
    private AlgorithmIdentifier signature;
    private ASN1Integer serialNumber;
    private ASN1EncodableVector attributes = new ASN1EncodableVector();
    private DERBitString issuerUniqueID;
    private Extensions extensions;
    private ASN1GeneralizedTime startDate;
    private ASN1GeneralizedTime endDate;

    public void setHolder(Holder holder) {
        this.holder = holder;
    }

    public void addAttribute(String string, ASN1Encodable aSN1Encodable) {
        this.attributes.add(new Attribute(new ASN1ObjectIdentifier(string), new DERSet(aSN1Encodable)));
    }

    public void addAttribute(Attribute attribute) {
        this.attributes.add(attribute);
    }

    public void setSerialNumber(ASN1Integer aSN1Integer) {
        this.serialNumber = aSN1Integer;
    }

    public void setSignature(AlgorithmIdentifier algorithmIdentifier) {
        this.signature = algorithmIdentifier;
    }

    public void setIssuer(AttCertIssuer attCertIssuer) {
        this.issuer = attCertIssuer;
    }

    public void setStartDate(ASN1GeneralizedTime aSN1GeneralizedTime) {
        this.startDate = aSN1GeneralizedTime;
    }

    public void setEndDate(ASN1GeneralizedTime aSN1GeneralizedTime) {
        this.endDate = aSN1GeneralizedTime;
    }

    public void setIssuerUniqueID(DERBitString dERBitString) {
        this.issuerUniqueID = dERBitString;
    }

    public void setExtensions(X509Extensions x509Extensions) {
        this.extensions = Extensions.getInstance(x509Extensions.toASN1Primitive());
    }

    public void setExtensions(Extensions extensions) {
        this.extensions = extensions;
    }

    public AttributeCertificateInfo generateAttributeCertificateInfo() {
        if (this.serialNumber == null || this.signature == null || this.issuer == null || this.startDate == null || this.endDate == null || this.holder == null || this.attributes == null) {
            throw new IllegalStateException("not all mandatory fields set in V2 AttributeCertificateInfo generator");
        }
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        aSN1EncodableVector.add(this.version);
        aSN1EncodableVector.add(this.holder);
        aSN1EncodableVector.add(this.issuer);
        aSN1EncodableVector.add(this.signature);
        aSN1EncodableVector.add(this.serialNumber);
        AttCertValidityPeriod attCertValidityPeriod = new AttCertValidityPeriod(this.startDate, this.endDate);
        aSN1EncodableVector.add(attCertValidityPeriod);
        aSN1EncodableVector.add(new DERSequence(this.attributes));
        if (this.issuerUniqueID != null) {
            aSN1EncodableVector.add(this.issuerUniqueID);
        }
        if (this.extensions != null) {
            aSN1EncodableVector.add(this.extensions);
        }
        return AttributeCertificateInfo.getInstance(new DERSequence(aSN1EncodableVector));
    }
}

