/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.asn1.dvcs;

import java.math.BigInteger;
import org.bouncycastle.asn1.ASN1Enumerated;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1TaggedObject;

public class ServiceType
extends ASN1Object {
    public static final ServiceType CPD = new ServiceType(1);
    public static final ServiceType VSD = new ServiceType(2);
    public static final ServiceType VPKC = new ServiceType(3);
    public static final ServiceType CCPD = new ServiceType(4);
    private ASN1Enumerated value;

    public ServiceType(int n) {
        this.value = new ASN1Enumerated(n);
    }

    private ServiceType(ASN1Enumerated aSN1Enumerated) {
        this.value = aSN1Enumerated;
    }

    public static ServiceType getInstance(Object object) {
        if (object instanceof ServiceType) {
            return (ServiceType)object;
        }
        if (object != null) {
            return new ServiceType(ASN1Enumerated.getInstance(object));
        }
        return null;
    }

    public static ServiceType getInstance(ASN1TaggedObject aSN1TaggedObject, boolean bl) {
        return ServiceType.getInstance(ASN1Enumerated.getInstance(aSN1TaggedObject, bl));
    }

    public BigInteger getValue() {
        return this.value.getValue();
    }

    public ASN1Primitive toASN1Primitive() {
        return this.value;
    }

    public String toString() {
        int n = this.value.getValue().intValue();
        return "" + n + (n == CPD.getValue().intValue() ? "(CPD)" : (n == VSD.getValue().intValue() ? "(VSD)" : (n == VPKC.getValue().intValue() ? "(VPKC)" : (n == CCPD.getValue().intValue() ? "(CCPD)" : "?"))));
    }
}

