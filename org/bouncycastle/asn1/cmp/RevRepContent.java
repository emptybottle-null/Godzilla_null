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
import org.bouncycastle.asn1.DERSequence;
import org.bouncycastle.asn1.DERTaggedObject;
import org.bouncycastle.asn1.cmp.PKIStatusInfo;
import org.bouncycastle.asn1.crmf.CertId;
import org.bouncycastle.asn1.x509.CertificateList;

public class RevRepContent
extends ASN1Object {
    private ASN1Sequence status;
    private ASN1Sequence revCerts;
    private ASN1Sequence crls;

    private RevRepContent(ASN1Sequence aSN1Sequence) {
        Enumeration enumeration = aSN1Sequence.getObjects();
        this.status = ASN1Sequence.getInstance(enumeration.nextElement());
        while (enumeration.hasMoreElements()) {
            ASN1TaggedObject aSN1TaggedObject = ASN1TaggedObject.getInstance(enumeration.nextElement());
            if (aSN1TaggedObject.getTagNo() == 0) {
                this.revCerts = ASN1Sequence.getInstance(aSN1TaggedObject, true);
                continue;
            }
            this.crls = ASN1Sequence.getInstance(aSN1TaggedObject, true);
        }
    }

    public static RevRepContent getInstance(Object object) {
        if (object instanceof RevRepContent) {
            return (RevRepContent)object;
        }
        if (object != null) {
            return new RevRepContent(ASN1Sequence.getInstance(object));
        }
        return null;
    }

    public PKIStatusInfo[] getStatus() {
        PKIStatusInfo[] pKIStatusInfoArray = new PKIStatusInfo[this.status.size()];
        for (int i = 0; i != pKIStatusInfoArray.length; ++i) {
            pKIStatusInfoArray[i] = PKIStatusInfo.getInstance(this.status.getObjectAt(i));
        }
        return pKIStatusInfoArray;
    }

    public CertId[] getRevCerts() {
        if (this.revCerts == null) {
            return null;
        }
        CertId[] certIdArray = new CertId[this.revCerts.size()];
        for (int i = 0; i != certIdArray.length; ++i) {
            certIdArray[i] = CertId.getInstance(this.revCerts.getObjectAt(i));
        }
        return certIdArray;
    }

    public CertificateList[] getCrls() {
        if (this.crls == null) {
            return null;
        }
        CertificateList[] certificateListArray = new CertificateList[this.crls.size()];
        for (int i = 0; i != certificateListArray.length; ++i) {
            certificateListArray[i] = CertificateList.getInstance(this.crls.getObjectAt(i));
        }
        return certificateListArray;
    }

    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        aSN1EncodableVector.add(this.status);
        this.addOptional(aSN1EncodableVector, 0, this.revCerts);
        this.addOptional(aSN1EncodableVector, 1, this.crls);
        return new DERSequence(aSN1EncodableVector);
    }

    private void addOptional(ASN1EncodableVector aSN1EncodableVector, int n, ASN1Encodable aSN1Encodable) {
        if (aSN1Encodable != null) {
            aSN1EncodableVector.add(new DERTaggedObject(true, n, aSN1Encodable));
        }
    }
}

