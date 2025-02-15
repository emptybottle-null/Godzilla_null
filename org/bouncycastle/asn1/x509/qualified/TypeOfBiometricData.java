/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.asn1.x509.qualified;

import org.bouncycastle.asn1.ASN1Choice;
import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1Integer;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.ASN1Primitive;

public class TypeOfBiometricData
extends ASN1Object
implements ASN1Choice {
    public static final int PICTURE = 0;
    public static final int HANDWRITTEN_SIGNATURE = 1;
    ASN1Encodable obj;

    public static TypeOfBiometricData getInstance(Object object) {
        if (object == null || object instanceof TypeOfBiometricData) {
            return (TypeOfBiometricData)object;
        }
        if (object instanceof ASN1Integer) {
            ASN1Integer aSN1Integer = ASN1Integer.getInstance(object);
            int n = aSN1Integer.getValue().intValue();
            return new TypeOfBiometricData(n);
        }
        if (object instanceof ASN1ObjectIdentifier) {
            ASN1ObjectIdentifier aSN1ObjectIdentifier = ASN1ObjectIdentifier.getInstance(object);
            return new TypeOfBiometricData(aSN1ObjectIdentifier);
        }
        throw new IllegalArgumentException("unknown object in getInstance");
    }

    public TypeOfBiometricData(int n) {
        if (n != 0 && n != 1) {
            throw new IllegalArgumentException("unknow PredefinedBiometricType : " + n);
        }
        this.obj = new ASN1Integer(n);
    }

    public TypeOfBiometricData(ASN1ObjectIdentifier aSN1ObjectIdentifier) {
        this.obj = aSN1ObjectIdentifier;
    }

    public boolean isPredefined() {
        return this.obj instanceof ASN1Integer;
    }

    public int getPredefinedBiometricType() {
        return ((ASN1Integer)this.obj).getValue().intValue();
    }

    public ASN1ObjectIdentifier getBiometricDataOid() {
        return (ASN1ObjectIdentifier)this.obj;
    }

    public ASN1Primitive toASN1Primitive() {
        return this.obj.toASN1Primitive();
    }
}

