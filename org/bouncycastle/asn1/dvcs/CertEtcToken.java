/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.asn1.dvcs;

import org.bouncycastle.asn1.ASN1Choice;
import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.ASN1TaggedObject;
import org.bouncycastle.asn1.DERTaggedObject;
import org.bouncycastle.asn1.cmp.PKIStatusInfo;
import org.bouncycastle.asn1.cms.ContentInfo;
import org.bouncycastle.asn1.ess.ESSCertID;
import org.bouncycastle.asn1.ocsp.CertID;
import org.bouncycastle.asn1.ocsp.CertStatus;
import org.bouncycastle.asn1.ocsp.OCSPResponse;
import org.bouncycastle.asn1.smime.SMIMECapabilities;
import org.bouncycastle.asn1.x509.Certificate;
import org.bouncycastle.asn1.x509.CertificateList;
import org.bouncycastle.asn1.x509.Extension;

public class CertEtcToken
extends ASN1Object
implements ASN1Choice {
    public static final int TAG_CERTIFICATE = 0;
    public static final int TAG_ESSCERTID = 1;
    public static final int TAG_PKISTATUS = 2;
    public static final int TAG_ASSERTION = 3;
    public static final int TAG_CRL = 4;
    public static final int TAG_OCSPCERTSTATUS = 5;
    public static final int TAG_OCSPCERTID = 6;
    public static final int TAG_OCSPRESPONSE = 7;
    public static final int TAG_CAPABILITIES = 8;
    private static final boolean[] explicit = new boolean[]{false, true, false, true, false, true, false, false, true};
    private int tagNo;
    private ASN1Encodable value;
    private Extension extension;

    public CertEtcToken(int n, ASN1Encodable aSN1Encodable) {
        this.tagNo = n;
        this.value = aSN1Encodable;
    }

    public CertEtcToken(Extension extension) {
        this.tagNo = -1;
        this.extension = extension;
    }

    private CertEtcToken(ASN1TaggedObject aSN1TaggedObject) {
        this.tagNo = aSN1TaggedObject.getTagNo();
        switch (this.tagNo) {
            case 0: {
                this.value = Certificate.getInstance(aSN1TaggedObject, false);
                break;
            }
            case 1: {
                this.value = ESSCertID.getInstance(aSN1TaggedObject.getObject());
                break;
            }
            case 2: {
                this.value = PKIStatusInfo.getInstance(aSN1TaggedObject, false);
                break;
            }
            case 3: {
                this.value = ContentInfo.getInstance(aSN1TaggedObject.getObject());
                break;
            }
            case 4: {
                this.value = CertificateList.getInstance(aSN1TaggedObject, false);
                break;
            }
            case 5: {
                this.value = CertStatus.getInstance(aSN1TaggedObject.getObject());
                break;
            }
            case 6: {
                this.value = CertID.getInstance(aSN1TaggedObject, false);
                break;
            }
            case 7: {
                this.value = OCSPResponse.getInstance(aSN1TaggedObject, false);
                break;
            }
            case 8: {
                this.value = SMIMECapabilities.getInstance(aSN1TaggedObject.getObject());
                break;
            }
            default: {
                throw new IllegalArgumentException("Unknown tag: " + this.tagNo);
            }
        }
    }

    public static CertEtcToken getInstance(Object object) {
        if (object instanceof CertEtcToken) {
            return (CertEtcToken)object;
        }
        if (object instanceof ASN1TaggedObject) {
            return new CertEtcToken((ASN1TaggedObject)object);
        }
        if (object != null) {
            return new CertEtcToken(Extension.getInstance(object));
        }
        return null;
    }

    public ASN1Primitive toASN1Primitive() {
        if (this.extension == null) {
            return new DERTaggedObject(explicit[this.tagNo], this.tagNo, this.value);
        }
        return this.extension.toASN1Primitive();
    }

    public int getTagNo() {
        return this.tagNo;
    }

    public ASN1Encodable getValue() {
        return this.value;
    }

    public Extension getExtension() {
        return this.extension;
    }

    public String toString() {
        return "CertEtcToken {\n" + this.value + "}\n";
    }

    public static CertEtcToken[] arrayFromSequence(ASN1Sequence aSN1Sequence) {
        CertEtcToken[] certEtcTokenArray = new CertEtcToken[aSN1Sequence.size()];
        for (int i = 0; i != certEtcTokenArray.length; ++i) {
            certEtcTokenArray[i] = CertEtcToken.getInstance(aSN1Sequence.getObjectAt(i));
        }
        return certEtcTokenArray;
    }
}

