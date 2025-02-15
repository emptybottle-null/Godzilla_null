/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.asn1.cmp;

import java.util.Enumeration;
import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.ASN1TaggedObject;
import org.bouncycastle.asn1.DERBitString;
import org.bouncycastle.asn1.DERSequence;
import org.bouncycastle.asn1.DERTaggedObject;
import org.bouncycastle.asn1.cmp.CMPCertificate;
import org.bouncycastle.asn1.cmp.PKIBody;
import org.bouncycastle.asn1.cmp.PKIHeader;

public class PKIMessage
extends ASN1Object {
    private PKIHeader header;
    private PKIBody body;
    private DERBitString protection;
    private ASN1Sequence extraCerts;

    private PKIMessage(ASN1Sequence aSN1Sequence) {
        Enumeration enumeration = aSN1Sequence.getObjects();
        this.header = PKIHeader.getInstance(enumeration.nextElement());
        this.body = PKIBody.getInstance(enumeration.nextElement());
        while (enumeration.hasMoreElements()) {
            ASN1TaggedObject aSN1TaggedObject = (ASN1TaggedObject)enumeration.nextElement();
            if (aSN1TaggedObject.getTagNo() == 0) {
                this.protection = DERBitString.getInstance(aSN1TaggedObject, true);
                continue;
            }
            this.extraCerts = ASN1Sequence.getInstance(aSN1TaggedObject, true);
        }
    }

    public static PKIMessage getInstance(Object object) {
        if (object instanceof PKIMessage) {
            return (PKIMessage)object;
        }
        if (object != null) {
            return new PKIMessage(ASN1Sequence.getInstance(object));
        }
        return null;
    }

    public PKIMessage(PKIHeader pKIHeader, PKIBody pKIBody, DERBitString dERBitString, CMPCertificate[] cMPCertificateArray) {
        this.header = pKIHeader;
        this.body = pKIBody;
        this.protection = dERBitString;
        if (cMPCertificateArray != null) {
            ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
            for (int i = 0; i < cMPCertificateArray.length; ++i) {
                aSN1EncodableVector.add(cMPCertificateArray[i]);
            }
            this.extraCerts = new DERSequence(aSN1EncodableVector);
        }
    }

    public PKIMessage(PKIHeader pKIHeader, PKIBody pKIBody, DERBitString dERBitString) {
        this(pKIHeader, pKIBody, dERBitString, null);
    }

    public PKIMessage(PKIHeader pKIHeader, PKIBody pKIBody) {
        this(pKIHeader, pKIBody, null, null);
    }

    public PKIHeader getHeader() {
        return this.header;
    }

    public PKIBody getBody() {
        return this.body;
    }

    public DERBitString getProtection() {
        return this.protection;
    }

    public CMPCertificate[] getExtraCerts() {
        if (this.extraCerts == null) {
            return null;
        }
        CMPCertificate[] cMPCertificateArray = new CMPCertificate[this.extraCerts.size()];
        for (int i = 0; i < cMPCertificateArray.length; ++i) {
            cMPCertificateArray[i] = CMPCertificate.getInstance(this.extraCerts.getObjectAt(i));
        }
        return cMPCertificateArray;
    }

    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        aSN1EncodableVector.add(this.header);
        aSN1EncodableVector.add(this.body);
        this.addOptional(aSN1EncodableVector, 0, this.protection);
        this.addOptional(aSN1EncodableVector, 1, this.extraCerts);
        return new DERSequence(aSN1EncodableVector);
    }

    private void addOptional(ASN1EncodableVector aSN1EncodableVector, int n, ASN1Encodable aSN1Encodable) {
        if (aSN1Encodable != null) {
            aSN1EncodableVector.add(new DERTaggedObject(true, n, aSN1Encodable));
        }
    }
}

