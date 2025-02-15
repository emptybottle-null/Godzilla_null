/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.asn1.cmc;

import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.DERSequence;
import org.bouncycastle.asn1.DERUTF8String;
import org.bouncycastle.asn1.cmc.BodyPartID;
import org.bouncycastle.asn1.cmc.CMCFailInfo;
import org.bouncycastle.asn1.cmc.CMCStatus;
import org.bouncycastle.asn1.cmc.CMCStatusInfoV2;
import org.bouncycastle.asn1.cmc.ExtendedFailInfo;
import org.bouncycastle.asn1.cmc.OtherStatusInfo;
import org.bouncycastle.asn1.cmc.PendInfo;

public class CMCStatusInfoV2Builder {
    private final CMCStatus cMCStatus;
    private final ASN1Sequence bodyList;
    private DERUTF8String statusString;
    private OtherStatusInfo otherInfo;

    public CMCStatusInfoV2Builder(CMCStatus cMCStatus, BodyPartID bodyPartID) {
        this.cMCStatus = cMCStatus;
        this.bodyList = new DERSequence(bodyPartID);
    }

    public CMCStatusInfoV2Builder(CMCStatus cMCStatus, BodyPartID[] bodyPartIDArray) {
        this.cMCStatus = cMCStatus;
        this.bodyList = new DERSequence(bodyPartIDArray);
    }

    public CMCStatusInfoV2Builder setStatusString(String string) {
        this.statusString = new DERUTF8String(string);
        return this;
    }

    public CMCStatusInfoV2Builder setOtherInfo(CMCFailInfo cMCFailInfo) {
        this.otherInfo = new OtherStatusInfo(cMCFailInfo);
        return this;
    }

    public CMCStatusInfoV2Builder setOtherInfo(ExtendedFailInfo extendedFailInfo) {
        this.otherInfo = new OtherStatusInfo(extendedFailInfo);
        return this;
    }

    public CMCStatusInfoV2Builder setOtherInfo(PendInfo pendInfo) {
        this.otherInfo = new OtherStatusInfo(pendInfo);
        return this;
    }

    public CMCStatusInfoV2 build() {
        return new CMCStatusInfoV2(this.cMCStatus, this.bodyList, this.statusString, this.otherInfo);
    }
}

