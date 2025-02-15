/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.asn1.cmc;

import java.math.BigInteger;
import org.bouncycastle.asn1.ASN1Integer;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1Primitive;

public class BodyPartID
extends ASN1Object {
    public static final long bodyIdMax = 0xFFFFFFFFL;
    private final long id;

    public BodyPartID(long l) {
        if (l < 0L || l > 0xFFFFFFFFL) {
            throw new IllegalArgumentException("id out of range");
        }
        this.id = l;
    }

    private static long convert(BigInteger bigInteger) {
        if (bigInteger.bitLength() > 32) {
            throw new IllegalArgumentException("id out of range");
        }
        return bigInteger.longValue();
    }

    private BodyPartID(ASN1Integer aSN1Integer) {
        this(BodyPartID.convert(aSN1Integer.getValue()));
    }

    public static BodyPartID getInstance(Object object) {
        if (object instanceof BodyPartID) {
            return (BodyPartID)object;
        }
        if (object != null) {
            return new BodyPartID(ASN1Integer.getInstance(object));
        }
        return null;
    }

    public long getID() {
        return this.id;
    }

    public ASN1Primitive toASN1Primitive() {
        return new ASN1Integer(this.id);
    }
}

