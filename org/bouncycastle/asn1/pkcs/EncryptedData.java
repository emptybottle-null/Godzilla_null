/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.asn1.pkcs;

import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1Integer;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.ASN1OctetString;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.ASN1TaggedObject;
import org.bouncycastle.asn1.BERSequence;
import org.bouncycastle.asn1.BERTaggedObject;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;

public class EncryptedData
extends ASN1Object {
    ASN1Sequence data;
    ASN1ObjectIdentifier bagId;
    ASN1Primitive bagValue;

    public static EncryptedData getInstance(Object object) {
        if (object instanceof EncryptedData) {
            return (EncryptedData)object;
        }
        if (object != null) {
            return new EncryptedData(ASN1Sequence.getInstance(object));
        }
        return null;
    }

    private EncryptedData(ASN1Sequence aSN1Sequence) {
        int n = ((ASN1Integer)aSN1Sequence.getObjectAt(0)).getValue().intValue();
        if (n != 0) {
            throw new IllegalArgumentException("sequence not version 0");
        }
        this.data = ASN1Sequence.getInstance(aSN1Sequence.getObjectAt(1));
    }

    public EncryptedData(ASN1ObjectIdentifier aSN1ObjectIdentifier, AlgorithmIdentifier algorithmIdentifier, ASN1Encodable aSN1Encodable) {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        aSN1EncodableVector.add(aSN1ObjectIdentifier);
        aSN1EncodableVector.add(algorithmIdentifier.toASN1Primitive());
        aSN1EncodableVector.add(new BERTaggedObject(false, 0, aSN1Encodable));
        this.data = new BERSequence(aSN1EncodableVector);
    }

    public ASN1ObjectIdentifier getContentType() {
        return ASN1ObjectIdentifier.getInstance(this.data.getObjectAt(0));
    }

    public AlgorithmIdentifier getEncryptionAlgorithm() {
        return AlgorithmIdentifier.getInstance(this.data.getObjectAt(1));
    }

    public ASN1OctetString getContent() {
        if (this.data.size() == 3) {
            ASN1TaggedObject aSN1TaggedObject = ASN1TaggedObject.getInstance(this.data.getObjectAt(2));
            return ASN1OctetString.getInstance(aSN1TaggedObject, false);
        }
        return null;
    }

    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        aSN1EncodableVector.add(new ASN1Integer(0L));
        aSN1EncodableVector.add(this.data);
        return new BERSequence(aSN1EncodableVector);
    }
}

