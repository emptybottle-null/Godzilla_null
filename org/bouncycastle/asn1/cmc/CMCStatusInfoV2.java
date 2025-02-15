/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.asn1.cmc;

import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.DERSequence;
import org.bouncycastle.asn1.DERUTF8String;
import org.bouncycastle.asn1.cmc.BodyPartID;
import org.bouncycastle.asn1.cmc.CMCStatus;
import org.bouncycastle.asn1.cmc.OtherStatusInfo;
import org.bouncycastle.asn1.cmc.Utils;

public class CMCStatusInfoV2
extends ASN1Object {
    private final CMCStatus cMCStatus;
    private final ASN1Sequence bodyList;
    private final DERUTF8String statusString;
    private final OtherStatusInfo otherStatusInfo;

    CMCStatusInfoV2(CMCStatus cMCStatus, ASN1Sequence aSN1Sequence, DERUTF8String dERUTF8String, OtherStatusInfo otherStatusInfo) {
        this.cMCStatus = cMCStatus;
        this.bodyList = aSN1Sequence;
        this.statusString = dERUTF8String;
        this.otherStatusInfo = otherStatusInfo;
    }

    private CMCStatusInfoV2(ASN1Sequence aSN1Sequence) {
        if (aSN1Sequence.size() < 2 || aSN1Sequence.size() > 4) {
            throw new IllegalArgumentException("incorrect sequence size");
        }
        this.cMCStatus = CMCStatus.getInstance(aSN1Sequence.getObjectAt(0));
        this.bodyList = ASN1Sequence.getInstance(aSN1Sequence.getObjectAt(1));
        if (aSN1Sequence.size() > 2) {
            if (aSN1Sequence.size() == 4) {
                this.statusString = DERUTF8String.getInstance(aSN1Sequence.getObjectAt(2));
                this.otherStatusInfo = OtherStatusInfo.getInstance(aSN1Sequence.getObjectAt(3));
            } else if (aSN1Sequence.getObjectAt(2) instanceof DERUTF8String) {
                this.statusString = DERUTF8String.getInstance(aSN1Sequence.getObjectAt(2));
                this.otherStatusInfo = null;
            } else {
                this.statusString = null;
                this.otherStatusInfo = OtherStatusInfo.getInstance(aSN1Sequence.getObjectAt(2));
            }
        } else {
            this.statusString = null;
            this.otherStatusInfo = null;
        }
    }

    public CMCStatus getcMCStatus() {
        return this.cMCStatus;
    }

    public BodyPartID[] getBodyList() {
        return Utils.toBodyPartIDArray(this.bodyList);
    }

    public DERUTF8String getStatusString() {
        return this.statusString;
    }

    public OtherStatusInfo getOtherStatusInfo() {
        return this.otherStatusInfo;
    }

    public boolean hasOtherInfo() {
        return this.otherStatusInfo != null;
    }

    public static CMCStatusInfoV2 getInstance(Object object) {
        if (object instanceof CMCStatusInfoV2) {
            return (CMCStatusInfoV2)object;
        }
        if (object != null) {
            return new CMCStatusInfoV2(ASN1Sequence.getInstance(object));
        }
        return null;
    }

    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        aSN1EncodableVector.add(this.cMCStatus);
        aSN1EncodableVector.add(this.bodyList);
        if (this.statusString != null) {
            aSN1EncodableVector.add(this.statusString);
        }
        if (this.otherStatusInfo != null) {
            aSN1EncodableVector.add(this.otherStatusInfo);
        }
        return new DERSequence(aSN1EncodableVector);
    }
}

