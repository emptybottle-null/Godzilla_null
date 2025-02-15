/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.asn1.ua;

import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1OctetString;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.DEROctetString;
import org.bouncycastle.asn1.ua.DSTU4145PointEncoder;
import org.bouncycastle.math.ec.ECPoint;

public class DSTU4145PublicKey
extends ASN1Object {
    private ASN1OctetString pubKey;

    public DSTU4145PublicKey(ECPoint eCPoint) {
        this.pubKey = new DEROctetString(DSTU4145PointEncoder.encodePoint(eCPoint));
    }

    private DSTU4145PublicKey(ASN1OctetString aSN1OctetString) {
        this.pubKey = aSN1OctetString;
    }

    public static DSTU4145PublicKey getInstance(Object object) {
        if (object instanceof DSTU4145PublicKey) {
            return (DSTU4145PublicKey)object;
        }
        if (object != null) {
            return new DSTU4145PublicKey(ASN1OctetString.getInstance(object));
        }
        return null;
    }

    public ASN1Primitive toASN1Primitive() {
        return this.pubKey;
    }
}

