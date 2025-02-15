/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.asn1.x509;

import java.util.Enumeration;
import java.util.Vector;
import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.ASN1OctetString;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.ASN1TaggedObject;
import org.bouncycastle.asn1.DEROctetString;
import org.bouncycastle.asn1.DERSequence;
import org.bouncycastle.asn1.DERTaggedObject;
import org.bouncycastle.asn1.DERUTF8String;
import org.bouncycastle.asn1.x509.GeneralNames;

public class IetfAttrSyntax
extends ASN1Object {
    public static final int VALUE_OCTETS = 1;
    public static final int VALUE_OID = 2;
    public static final int VALUE_UTF8 = 3;
    GeneralNames policyAuthority = null;
    Vector values = new Vector();
    int valueChoice = -1;

    public static IetfAttrSyntax getInstance(Object object) {
        if (object instanceof IetfAttrSyntax) {
            return (IetfAttrSyntax)object;
        }
        if (object != null) {
            return new IetfAttrSyntax(ASN1Sequence.getInstance(object));
        }
        return null;
    }

    private IetfAttrSyntax(ASN1Sequence aSN1Sequence) {
        int n = 0;
        if (aSN1Sequence.getObjectAt(0) instanceof ASN1TaggedObject) {
            this.policyAuthority = GeneralNames.getInstance((ASN1TaggedObject)aSN1Sequence.getObjectAt(0), false);
            ++n;
        } else if (aSN1Sequence.size() == 2) {
            this.policyAuthority = GeneralNames.getInstance(aSN1Sequence.getObjectAt(0));
            ++n;
        }
        if (!(aSN1Sequence.getObjectAt(n) instanceof ASN1Sequence)) {
            throw new IllegalArgumentException("Non-IetfAttrSyntax encoding");
        }
        aSN1Sequence = (ASN1Sequence)aSN1Sequence.getObjectAt(n);
        Enumeration enumeration = aSN1Sequence.getObjects();
        while (enumeration.hasMoreElements()) {
            int n2;
            ASN1Primitive aSN1Primitive = (ASN1Primitive)enumeration.nextElement();
            if (aSN1Primitive instanceof ASN1ObjectIdentifier) {
                n2 = 2;
            } else if (aSN1Primitive instanceof DERUTF8String) {
                n2 = 3;
            } else if (aSN1Primitive instanceof DEROctetString) {
                n2 = 1;
            } else {
                throw new IllegalArgumentException("Bad value type encoding IetfAttrSyntax");
            }
            if (this.valueChoice < 0) {
                this.valueChoice = n2;
            }
            if (n2 != this.valueChoice) {
                throw new IllegalArgumentException("Mix of value types in IetfAttrSyntax");
            }
            this.values.addElement(aSN1Primitive);
        }
    }

    public GeneralNames getPolicyAuthority() {
        return this.policyAuthority;
    }

    public int getValueType() {
        return this.valueChoice;
    }

    public Object[] getValues() {
        if (this.getValueType() == 1) {
            Object[] objectArray = new ASN1OctetString[this.values.size()];
            for (int i = 0; i != objectArray.length; ++i) {
                objectArray[i] = (ASN1OctetString)this.values.elementAt(i);
            }
            return objectArray;
        }
        if (this.getValueType() == 2) {
            Object[] objectArray = new ASN1ObjectIdentifier[this.values.size()];
            for (int i = 0; i != objectArray.length; ++i) {
                objectArray[i] = (ASN1ObjectIdentifier)this.values.elementAt(i);
            }
            return objectArray;
        }
        Object[] objectArray = new DERUTF8String[this.values.size()];
        for (int i = 0; i != objectArray.length; ++i) {
            objectArray[i] = (DERUTF8String)this.values.elementAt(i);
        }
        return objectArray;
    }

    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        if (this.policyAuthority != null) {
            aSN1EncodableVector.add(new DERTaggedObject(0, this.policyAuthority));
        }
        ASN1EncodableVector aSN1EncodableVector2 = new ASN1EncodableVector();
        Enumeration enumeration = this.values.elements();
        while (enumeration.hasMoreElements()) {
            aSN1EncodableVector2.add((ASN1Encodable)enumeration.nextElement());
        }
        aSN1EncodableVector.add(new DERSequence(aSN1EncodableVector2));
        return new DERSequence(aSN1EncodableVector);
    }
}

