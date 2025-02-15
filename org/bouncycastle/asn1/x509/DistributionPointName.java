/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.asn1.x509;

import org.bouncycastle.asn1.ASN1Choice;
import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Set;
import org.bouncycastle.asn1.ASN1TaggedObject;
import org.bouncycastle.asn1.DERTaggedObject;
import org.bouncycastle.asn1.x509.GeneralNames;
import org.bouncycastle.util.Strings;

public class DistributionPointName
extends ASN1Object
implements ASN1Choice {
    ASN1Encodable name;
    int type;
    public static final int FULL_NAME = 0;
    public static final int NAME_RELATIVE_TO_CRL_ISSUER = 1;

    public static DistributionPointName getInstance(ASN1TaggedObject aSN1TaggedObject, boolean bl) {
        return DistributionPointName.getInstance(ASN1TaggedObject.getInstance(aSN1TaggedObject, true));
    }

    public static DistributionPointName getInstance(Object object) {
        if (object == null || object instanceof DistributionPointName) {
            return (DistributionPointName)object;
        }
        if (object instanceof ASN1TaggedObject) {
            return new DistributionPointName((ASN1TaggedObject)object);
        }
        throw new IllegalArgumentException("unknown object in factory: " + object.getClass().getName());
    }

    public DistributionPointName(int n, ASN1Encodable aSN1Encodable) {
        this.type = n;
        this.name = aSN1Encodable;
    }

    public DistributionPointName(GeneralNames generalNames) {
        this(0, generalNames);
    }

    public int getType() {
        return this.type;
    }

    public ASN1Encodable getName() {
        return this.name;
    }

    public DistributionPointName(ASN1TaggedObject aSN1TaggedObject) {
        this.type = aSN1TaggedObject.getTagNo();
        this.name = this.type == 0 ? GeneralNames.getInstance(aSN1TaggedObject, false) : ASN1Set.getInstance(aSN1TaggedObject, false);
    }

    public ASN1Primitive toASN1Primitive() {
        return new DERTaggedObject(false, this.type, this.name);
    }

    public String toString() {
        String string = Strings.lineSeparator();
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("DistributionPointName: [");
        stringBuffer.append(string);
        if (this.type == 0) {
            this.appendObject(stringBuffer, string, "fullName", this.name.toString());
        } else {
            this.appendObject(stringBuffer, string, "nameRelativeToCRLIssuer", this.name.toString());
        }
        stringBuffer.append("]");
        stringBuffer.append(string);
        return stringBuffer.toString();
    }

    private void appendObject(StringBuffer stringBuffer, String string, String string2, String string3) {
        String string4 = "    ";
        stringBuffer.append(string4);
        stringBuffer.append(string2);
        stringBuffer.append(":");
        stringBuffer.append(string);
        stringBuffer.append(string4);
        stringBuffer.append(string4);
        stringBuffer.append(string3);
        stringBuffer.append(string);
    }
}

