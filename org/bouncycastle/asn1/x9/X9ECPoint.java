/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.asn1.x9;

import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1OctetString;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.DEROctetString;
import org.bouncycastle.math.ec.ECCurve;
import org.bouncycastle.math.ec.ECPoint;
import org.bouncycastle.util.Arrays;

public class X9ECPoint
extends ASN1Object {
    private final ASN1OctetString encoding;
    private ECCurve c;
    private ECPoint p;

    public X9ECPoint(ECPoint eCPoint) {
        this(eCPoint, false);
    }

    public X9ECPoint(ECPoint eCPoint, boolean bl) {
        this.p = eCPoint.normalize();
        this.encoding = new DEROctetString(eCPoint.getEncoded(bl));
    }

    public X9ECPoint(ECCurve eCCurve, byte[] byArray) {
        this.c = eCCurve;
        this.encoding = new DEROctetString(Arrays.clone(byArray));
    }

    public X9ECPoint(ECCurve eCCurve, ASN1OctetString aSN1OctetString) {
        this(eCCurve, aSN1OctetString.getOctets());
    }

    public byte[] getPointEncoding() {
        return Arrays.clone(this.encoding.getOctets());
    }

    public synchronized ECPoint getPoint() {
        if (this.p == null) {
            this.p = this.c.decodePoint(this.encoding.getOctets()).normalize();
        }
        return this.p;
    }

    public boolean isPointCompressed() {
        byte[] byArray = this.encoding.getOctets();
        return byArray != null && byArray.length > 0 && (byArray[0] == 2 || byArray[0] == 3);
    }

    public ASN1Primitive toASN1Primitive() {
        return this.encoding;
    }
}

