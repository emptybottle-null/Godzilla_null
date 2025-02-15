/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.asn1.cmc;

import org.bouncycastle.asn1.ASN1Choice;
import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1Integer;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.DERSequence;
import org.bouncycastle.asn1.DERUTF8String;
import org.bouncycastle.asn1.cmc.BodyPartID;
import org.bouncycastle.asn1.cmc.CMCFailInfo;
import org.bouncycastle.asn1.cmc.CMCStatus;
import org.bouncycastle.asn1.cmc.PendInfo;
import org.bouncycastle.asn1.cmc.Utils;

public class CMCStatusInfo
extends ASN1Object {
    private final CMCStatus cMCStatus;
    private final ASN1Sequence bodyList;
    private final DERUTF8String statusString;
    private final OtherInfo otherInfo;

    CMCStatusInfo(CMCStatus cMCStatus, ASN1Sequence aSN1Sequence, DERUTF8String dERUTF8String, OtherInfo otherInfo) {
        this.cMCStatus = cMCStatus;
        this.bodyList = aSN1Sequence;
        this.statusString = dERUTF8String;
        this.otherInfo = otherInfo;
    }

    private CMCStatusInfo(ASN1Sequence aSN1Sequence) {
        if (aSN1Sequence.size() < 2 || aSN1Sequence.size() > 4) {
            throw new IllegalArgumentException("incorrect sequence size");
        }
        this.cMCStatus = CMCStatus.getInstance(aSN1Sequence.getObjectAt(0));
        this.bodyList = ASN1Sequence.getInstance(aSN1Sequence.getObjectAt(1));
        if (aSN1Sequence.size() > 3) {
            this.statusString = DERUTF8String.getInstance(aSN1Sequence.getObjectAt(2));
            this.otherInfo = OtherInfo.getInstance(aSN1Sequence.getObjectAt(3));
        } else if (aSN1Sequence.size() > 2) {
            if (aSN1Sequence.getObjectAt(2) instanceof DERUTF8String) {
                this.statusString = DERUTF8String.getInstance(aSN1Sequence.getObjectAt(2));
                this.otherInfo = null;
            } else {
                this.statusString = null;
                this.otherInfo = OtherInfo.getInstance(aSN1Sequence.getObjectAt(2));
            }
        } else {
            this.statusString = null;
            this.otherInfo = null;
        }
    }

    public static CMCStatusInfo getInstance(Object object) {
        if (object instanceof CMCStatusInfo) {
            return (CMCStatusInfo)object;
        }
        if (object != null) {
            return new CMCStatusInfo(ASN1Sequence.getInstance(object));
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
        if (this.otherInfo != null) {
            aSN1EncodableVector.add(this.otherInfo);
        }
        return new DERSequence(aSN1EncodableVector);
    }

    public CMCStatus getCMCStatus() {
        return this.cMCStatus;
    }

    public BodyPartID[] getBodyList() {
        return Utils.toBodyPartIDArray(this.bodyList);
    }

    public DERUTF8String getStatusString() {
        return this.statusString;
    }

    public boolean hasOtherInfo() {
        return this.otherInfo != null;
    }

    public OtherInfo getOtherInfo() {
        return this.otherInfo;
    }

    public static class OtherInfo
    extends ASN1Object
    implements ASN1Choice {
        private final CMCFailInfo failInfo;
        private final PendInfo pendInfo;

        private static OtherInfo getInstance(Object object) {
            if (object instanceof OtherInfo) {
                return (OtherInfo)object;
            }
            if (object instanceof ASN1Encodable) {
                ASN1Primitive aSN1Primitive = ((ASN1Encodable)object).toASN1Primitive();
                if (aSN1Primitive instanceof ASN1Integer) {
                    return new OtherInfo(CMCFailInfo.getInstance(aSN1Primitive));
                }
                if (aSN1Primitive instanceof ASN1Sequence) {
                    return new OtherInfo(PendInfo.getInstance(aSN1Primitive));
                }
            }
            throw new IllegalArgumentException("unknown object in getInstance(): " + object.getClass().getName());
        }

        OtherInfo(CMCFailInfo cMCFailInfo) {
            this(cMCFailInfo, null);
        }

        OtherInfo(PendInfo pendInfo) {
            this(null, pendInfo);
        }

        private OtherInfo(CMCFailInfo cMCFailInfo, PendInfo pendInfo) {
            this.failInfo = cMCFailInfo;
            this.pendInfo = pendInfo;
        }

        public boolean isFailInfo() {
            return this.failInfo != null;
        }

        public ASN1Primitive toASN1Primitive() {
            if (this.pendInfo != null) {
                return this.pendInfo.toASN1Primitive();
            }
            return this.failInfo.toASN1Primitive();
        }
    }
}

