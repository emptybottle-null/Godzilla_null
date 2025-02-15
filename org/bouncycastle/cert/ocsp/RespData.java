/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.cert.ocsp;

import java.util.Date;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.ocsp.ResponseData;
import org.bouncycastle.asn1.ocsp.SingleResponse;
import org.bouncycastle.asn1.x509.Extensions;
import org.bouncycastle.cert.ocsp.OCSPUtils;
import org.bouncycastle.cert.ocsp.RespID;
import org.bouncycastle.cert.ocsp.SingleResp;

public class RespData {
    private ResponseData data;

    public RespData(ResponseData responseData) {
        this.data = responseData;
    }

    public int getVersion() {
        return this.data.getVersion().getValue().intValue() + 1;
    }

    public RespID getResponderId() {
        return new RespID(this.data.getResponderID());
    }

    public Date getProducedAt() {
        return OCSPUtils.extractDate(this.data.getProducedAt());
    }

    public SingleResp[] getResponses() {
        ASN1Sequence aSN1Sequence = this.data.getResponses();
        SingleResp[] singleRespArray = new SingleResp[aSN1Sequence.size()];
        for (int i = 0; i != singleRespArray.length; ++i) {
            singleRespArray[i] = new SingleResp(SingleResponse.getInstance(aSN1Sequence.getObjectAt(i)));
        }
        return singleRespArray;
    }

    public Extensions getResponseExtensions() {
        return this.data.getResponseExtensions();
    }
}

