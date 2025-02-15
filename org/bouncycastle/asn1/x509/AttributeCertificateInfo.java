/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.asn1.x509;

import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1Integer;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.ASN1TaggedObject;
import org.bouncycastle.asn1.DERBitString;
import org.bouncycastle.asn1.DERSequence;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.asn1.x509.AttCertIssuer;
import org.bouncycastle.asn1.x509.AttCertValidityPeriod;
import org.bouncycastle.asn1.x509.Extensions;
import org.bouncycastle.asn1.x509.Holder;

public class AttributeCertificateInfo
extends ASN1Object {
    private ASN1Integer version;
    private Holder holder;
    private AttCertIssuer issuer;
    private AlgorithmIdentifier signature;
    private ASN1Integer serialNumber;
    private AttCertValidityPeriod attrCertValidityPeriod;
    private ASN1Sequence attributes;
    private DERBitString issuerUniqueID;
    private Extensions extensions;

    public static AttributeCertificateInfo getInstance(ASN1TaggedObject aSN1TaggedObject, boolean bl) {
        return AttributeCertificateInfo.getInstance(ASN1Sequence.getInstance(aSN1TaggedObject, bl));
    }

    public static AttributeCertificateInfo getInstance(Object object) {
        if (object instanceof AttributeCertificateInfo) {
            return (AttributeCertificateInfo)object;
        }
        if (object != null) {
            return new AttributeCertificateInfo(ASN1Sequence.getInstance(object));
        }
        return null;
    }

    private AttributeCertificateInfo(ASN1Sequence aSN1Sequence) {
        int n;
        if (aSN1Sequence.size() < 6 || aSN1Sequence.size() > 9) {
            throw new IllegalArgumentException("Bad sequence size: " + aSN1Sequence.size());
        }
        if (aSN1Sequence.getObjectAt(0) instanceof ASN1Integer) {
            this.version = ASN1Integer.getInstance(aSN1Sequence.getObjectAt(0));
            n = 1;
        } else {
            this.version = new ASN1Integer(0L);
            n = 0;
        }
        this.holder = Holder.getInstance(aSN1Sequence.getObjectAt(n));
        this.issuer = AttCertIssuer.getInstance(aSN1Sequence.getObjectAt(n + 1));
        this.signature = AlgorithmIdentifier.getInstance(aSN1Sequence.getObjectAt(n + 2));
        this.serialNumber = ASN1Integer.getInstance(aSN1Sequence.getObjectAt(n + 3));
        this.attrCertValidityPeriod = AttCertValidityPeriod.getInstance(aSN1Sequence.getObjectAt(n + 4));
        this.attributes = ASN1Sequence.getInstance(aSN1Sequence.getObjectAt(n + 5));
        for (int i = n + 6; i < aSN1Sequence.size(); ++i) {
            ASN1Encodable aSN1Encodable = aSN1Sequence.getObjectAt(i);
            if (aSN1Encodable instanceof DERBitString) {
                this.issuerUniqueID = DERBitString.getInstance(aSN1Sequence.getObjectAt(i));
                continue;
            }
            if (!(aSN1Encodable instanceof ASN1Sequence) && !(aSN1Encodable instanceof Extensions)) continue;
            this.extensions = Extensions.getInstance(aSN1Sequence.getObjectAt(i));
        }
    }

    public ASN1Integer getVersion() {
        return this.version;
    }

    public Holder getHolder() {
        return this.holder;
    }

    public AttCertIssuer getIssuer() {
        return this.issuer;
    }

    public AlgorithmIdentifier getSignature() {
        return this.signature;
    }

    public ASN1Integer getSerialNumber() {
        return this.serialNumber;
    }

    public AttCertValidityPeriod getAttrCertValidityPeriod() {
        return this.attrCertValidityPeriod;
    }

    public ASN1Sequence getAttributes() {
        return this.attributes;
    }

    public DERBitString getIssuerUniqueID() {
        return this.issuerUniqueID;
    }

    public Extensions getExtensions() {
        return this.extensions;
    }

    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        if (this.version.getValue().intValue() != 0) {
            aSN1EncodableVector.add(this.version);
        }
        aSN1EncodableVector.add(this.holder);
        aSN1EncodableVector.add(this.issuer);
        aSN1EncodableVector.add(this.signature);
        aSN1EncodableVector.add(this.serialNumber);
        aSN1EncodableVector.add(this.attrCertValidityPeriod);
        aSN1EncodableVector.add(this.attributes);
        if (this.issuerUniqueID != null) {
            aSN1EncodableVector.add(this.issuerUniqueID);
        }
        if (this.extensions != null) {
            aSN1EncodableVector.add(this.extensions);
        }
        return new DERSequence(aSN1EncodableVector);
    }
}

