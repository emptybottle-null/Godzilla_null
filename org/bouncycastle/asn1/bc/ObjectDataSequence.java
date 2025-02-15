/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.asn1.bc;

import java.util.Iterator;
import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.DERSequence;
import org.bouncycastle.asn1.bc.ObjectData;
import org.bouncycastle.util.Arrays;
import org.bouncycastle.util.Iterable;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class ObjectDataSequence
extends ASN1Object
implements Iterable<ASN1Encodable> {
    private final ASN1Encodable[] dataSequence;

    public ObjectDataSequence(ObjectData[] objectDataArray) {
        this.dataSequence = new ASN1Encodable[objectDataArray.length];
        System.arraycopy(objectDataArray, 0, this.dataSequence, 0, objectDataArray.length);
    }

    private ObjectDataSequence(ASN1Sequence aSN1Sequence) {
        this.dataSequence = new ASN1Encodable[aSN1Sequence.size()];
        for (int i = 0; i != this.dataSequence.length; ++i) {
            this.dataSequence[i] = ObjectData.getInstance(aSN1Sequence.getObjectAt(i));
        }
    }

    public static ObjectDataSequence getInstance(Object object) {
        if (object instanceof ObjectDataSequence) {
            return (ObjectDataSequence)object;
        }
        if (object != null) {
            return new ObjectDataSequence(ASN1Sequence.getInstance(object));
        }
        return null;
    }

    @Override
    public ASN1Primitive toASN1Primitive() {
        return new DERSequence(this.dataSequence);
    }

    @Override
    public Iterator<ASN1Encodable> iterator() {
        return new Arrays.Iterator<ASN1Encodable>(this.dataSequence);
    }
}

