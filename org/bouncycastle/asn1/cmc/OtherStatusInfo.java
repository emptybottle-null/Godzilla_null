/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.asn1.cmc;

import java.io.IOException;
import org.bouncycastle.asn1.ASN1Choice;
import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1Integer;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.cmc.CMCFailInfo;
import org.bouncycastle.asn1.cmc.ExtendedFailInfo;
import org.bouncycastle.asn1.cmc.PendInfo;

public class OtherStatusInfo
extends ASN1Object
implements ASN1Choice {
    private final CMCFailInfo failInfo;
    private final PendInfo pendInfo;
    private final ExtendedFailInfo extendedFailInfo;

    public static OtherStatusInfo getInstance(Object object) {
        if (object instanceof OtherStatusInfo) {
            return (OtherStatusInfo)object;
        }
        if (object instanceof ASN1Encodable) {
            ASN1Primitive aSN1Primitive = ((ASN1Encodable)object).toASN1Primitive();
            if (aSN1Primitive instanceof ASN1Integer) {
                return new OtherStatusInfo(CMCFailInfo.getInstance(aSN1Primitive));
            }
            if (aSN1Primitive instanceof ASN1Sequence) {
                if (((ASN1Sequence)aSN1Primitive).getObjectAt(0) instanceof ASN1ObjectIdentifier) {
                    return new OtherStatusInfo(ExtendedFailInfo.getInstance(aSN1Primitive));
                }
                return new OtherStatusInfo(PendInfo.getInstance(aSN1Primitive));
            }
        } else if (object instanceof byte[]) {
            try {
                return OtherStatusInfo.getInstance(ASN1Primitive.fromByteArray((byte[])object));
            } catch (IOException iOException) {
                throw new IllegalArgumentException("parsing error: " + iOException.getMessage());
            }
        }
        throw new IllegalArgumentException("unknown object in getInstance(): " + object.getClass().getName());
    }

    OtherStatusInfo(CMCFailInfo cMCFailInfo) {
        this(cMCFailInfo, null, null);
    }

    OtherStatusInfo(PendInfo pendInfo) {
        this(null, pendInfo, null);
    }

    OtherStatusInfo(ExtendedFailInfo extendedFailInfo) {
        this(null, null, extendedFailInfo);
    }

    private OtherStatusInfo(CMCFailInfo cMCFailInfo, PendInfo pendInfo, ExtendedFailInfo extendedFailInfo) {
        this.failInfo = cMCFailInfo;
        this.pendInfo = pendInfo;
        this.extendedFailInfo = extendedFailInfo;
    }

    public boolean isPendingInfo() {
        return this.pendInfo != null;
    }

    public boolean isFailInfo() {
        return this.failInfo != null;
    }

    public boolean isExtendedFailInfo() {
        return this.extendedFailInfo != null;
    }

    public ASN1Primitive toASN1Primitive() {
        if (this.pendInfo != null) {
            return this.pendInfo.toASN1Primitive();
        }
        if (this.failInfo != null) {
            return this.failInfo.toASN1Primitive();
        }
        return this.extendedFailInfo.toASN1Primitive();
    }
}

