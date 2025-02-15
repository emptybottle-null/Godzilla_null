/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.asn1.x509;

import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.ASN1TaggedObject;
import org.bouncycastle.asn1.DERSequence;
import org.bouncycastle.asn1.x509.Extensions;
import org.bouncycastle.asn1.x509.GeneralName;
import org.bouncycastle.util.Strings;

public class GeneralNames
extends ASN1Object {
    private final GeneralName[] names;

    public static GeneralNames getInstance(Object object) {
        if (object instanceof GeneralNames) {
            return (GeneralNames)object;
        }
        if (object != null) {
            return new GeneralNames(ASN1Sequence.getInstance(object));
        }
        return null;
    }

    public static GeneralNames getInstance(ASN1TaggedObject aSN1TaggedObject, boolean bl) {
        return GeneralNames.getInstance(ASN1Sequence.getInstance(aSN1TaggedObject, bl));
    }

    public static GeneralNames fromExtensions(Extensions extensions, ASN1ObjectIdentifier aSN1ObjectIdentifier) {
        return GeneralNames.getInstance(extensions.getExtensionParsedValue(aSN1ObjectIdentifier));
    }

    public GeneralNames(GeneralName generalName) {
        this.names = new GeneralName[]{generalName};
    }

    public GeneralNames(GeneralName[] generalNameArray) {
        this.names = generalNameArray;
    }

    private GeneralNames(ASN1Sequence aSN1Sequence) {
        this.names = new GeneralName[aSN1Sequence.size()];
        for (int i = 0; i != aSN1Sequence.size(); ++i) {
            this.names[i] = GeneralName.getInstance(aSN1Sequence.getObjectAt(i));
        }
    }

    public GeneralName[] getNames() {
        GeneralName[] generalNameArray = new GeneralName[this.names.length];
        System.arraycopy(this.names, 0, generalNameArray, 0, this.names.length);
        return generalNameArray;
    }

    public ASN1Primitive toASN1Primitive() {
        return new DERSequence(this.names);
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        String string = Strings.lineSeparator();
        stringBuffer.append("GeneralNames:");
        stringBuffer.append(string);
        for (int i = 0; i != this.names.length; ++i) {
            stringBuffer.append("    ");
            stringBuffer.append(this.names[i]);
            stringBuffer.append(string);
        }
        return stringBuffer.toString();
    }
}

