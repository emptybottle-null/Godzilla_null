/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.asn1.cmc;

import java.util.HashMap;
import java.util.Map;
import org.bouncycastle.asn1.ASN1Integer;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1Primitive;

public class CMCFailInfo
extends ASN1Object {
    public static final CMCFailInfo badAlg = new CMCFailInfo(new ASN1Integer(0L));
    public static final CMCFailInfo badMessageCheck = new CMCFailInfo(new ASN1Integer(1L));
    public static final CMCFailInfo badRequest = new CMCFailInfo(new ASN1Integer(2L));
    public static final CMCFailInfo badTime = new CMCFailInfo(new ASN1Integer(3L));
    public static final CMCFailInfo badCertId = new CMCFailInfo(new ASN1Integer(4L));
    public static final CMCFailInfo unsupportedExt = new CMCFailInfo(new ASN1Integer(5L));
    public static final CMCFailInfo mustArchiveKeys = new CMCFailInfo(new ASN1Integer(6L));
    public static final CMCFailInfo badIdentity = new CMCFailInfo(new ASN1Integer(7L));
    public static final CMCFailInfo popRequired = new CMCFailInfo(new ASN1Integer(8L));
    public static final CMCFailInfo popFailed = new CMCFailInfo(new ASN1Integer(9L));
    public static final CMCFailInfo noKeyReuse = new CMCFailInfo(new ASN1Integer(10L));
    public static final CMCFailInfo internalCAError = new CMCFailInfo(new ASN1Integer(11L));
    public static final CMCFailInfo tryLater = new CMCFailInfo(new ASN1Integer(12L));
    public static final CMCFailInfo authDataFail = new CMCFailInfo(new ASN1Integer(13L));
    private static Map range = new HashMap();
    private final ASN1Integer value;

    private CMCFailInfo(ASN1Integer aSN1Integer) {
        this.value = aSN1Integer;
    }

    public static CMCFailInfo getInstance(Object object) {
        if (object instanceof CMCFailInfo) {
            return (CMCFailInfo)object;
        }
        if (object != null) {
            CMCFailInfo cMCFailInfo = (CMCFailInfo)range.get(ASN1Integer.getInstance(object));
            if (cMCFailInfo != null) {
                return cMCFailInfo;
            }
            throw new IllegalArgumentException("unknown object in getInstance(): " + object.getClass().getName());
        }
        return null;
    }

    public ASN1Primitive toASN1Primitive() {
        return this.value;
    }

    static {
        range.put(CMCFailInfo.badAlg.value, badAlg);
        range.put(CMCFailInfo.badMessageCheck.value, badMessageCheck);
        range.put(CMCFailInfo.badRequest.value, badRequest);
        range.put(CMCFailInfo.badTime.value, badTime);
        range.put(CMCFailInfo.badCertId.value, badCertId);
        range.put(CMCFailInfo.popRequired.value, popRequired);
        range.put(CMCFailInfo.unsupportedExt.value, unsupportedExt);
        range.put(CMCFailInfo.mustArchiveKeys.value, mustArchiveKeys);
        range.put(CMCFailInfo.badIdentity.value, badIdentity);
        range.put(CMCFailInfo.popRequired.value, popRequired);
        range.put(CMCFailInfo.popFailed.value, popFailed);
        range.put(CMCFailInfo.badCertId.value, badCertId);
        range.put(CMCFailInfo.popRequired.value, popRequired);
        range.put(CMCFailInfo.noKeyReuse.value, noKeyReuse);
        range.put(CMCFailInfo.internalCAError.value, internalCAError);
        range.put(CMCFailInfo.tryLater.value, tryLater);
        range.put(CMCFailInfo.authDataFail.value, authDataFail);
    }
}

