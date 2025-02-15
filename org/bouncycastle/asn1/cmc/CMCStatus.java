/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.asn1.cmc;

import java.util.HashMap;
import java.util.Map;
import org.bouncycastle.asn1.ASN1Integer;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1Primitive;

public class CMCStatus
extends ASN1Object {
    public static final CMCStatus success = new CMCStatus(new ASN1Integer(0L));
    public static final CMCStatus failed = new CMCStatus(new ASN1Integer(2L));
    public static final CMCStatus pending = new CMCStatus(new ASN1Integer(3L));
    public static final CMCStatus noSupport = new CMCStatus(new ASN1Integer(4L));
    public static final CMCStatus confirmRequired = new CMCStatus(new ASN1Integer(5L));
    public static final CMCStatus popRequired = new CMCStatus(new ASN1Integer(6L));
    public static final CMCStatus partial = new CMCStatus(new ASN1Integer(7L));
    private static Map range = new HashMap();
    private final ASN1Integer value;

    private CMCStatus(ASN1Integer aSN1Integer) {
        this.value = aSN1Integer;
    }

    public static CMCStatus getInstance(Object object) {
        if (object instanceof CMCStatus) {
            return (CMCStatus)object;
        }
        if (object != null) {
            CMCStatus cMCStatus = (CMCStatus)range.get(ASN1Integer.getInstance(object));
            if (cMCStatus != null) {
                return cMCStatus;
            }
            throw new IllegalArgumentException("unknown object in getInstance(): " + object.getClass().getName());
        }
        return null;
    }

    public ASN1Primitive toASN1Primitive() {
        return this.value;
    }

    static {
        range.put(CMCStatus.success.value, success);
        range.put(CMCStatus.failed.value, failed);
        range.put(CMCStatus.pending.value, pending);
        range.put(CMCStatus.noSupport.value, noSupport);
        range.put(CMCStatus.confirmRequired.value, confirmRequired);
        range.put(CMCStatus.popRequired.value, popRequired);
        range.put(CMCStatus.partial.value, partial);
    }
}

