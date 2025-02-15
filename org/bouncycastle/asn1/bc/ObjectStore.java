/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.asn1.bc;

import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.DERSequence;
import org.bouncycastle.asn1.bc.EncryptedObjectStoreData;
import org.bouncycastle.asn1.bc.ObjectStoreData;
import org.bouncycastle.asn1.bc.ObjectStoreIntegrityCheck;

public class ObjectStore
extends ASN1Object {
    private final ASN1Encodable storeData;
    private final ObjectStoreIntegrityCheck integrityCheck;

    public ObjectStore(ObjectStoreData objectStoreData, ObjectStoreIntegrityCheck objectStoreIntegrityCheck) {
        this.storeData = objectStoreData;
        this.integrityCheck = objectStoreIntegrityCheck;
    }

    public ObjectStore(EncryptedObjectStoreData encryptedObjectStoreData, ObjectStoreIntegrityCheck objectStoreIntegrityCheck) {
        this.storeData = encryptedObjectStoreData;
        this.integrityCheck = objectStoreIntegrityCheck;
    }

    private ObjectStore(ASN1Sequence aSN1Sequence) {
        ASN1Sequence aSN1Sequence2;
        ASN1Encodable aSN1Encodable = aSN1Sequence.getObjectAt(0);
        this.storeData = aSN1Encodable instanceof EncryptedObjectStoreData ? aSN1Encodable : (aSN1Encodable instanceof ObjectStoreData ? aSN1Encodable : ((aSN1Sequence2 = ASN1Sequence.getInstance(aSN1Encodable)).size() == 2 ? EncryptedObjectStoreData.getInstance(aSN1Sequence2) : ObjectStoreData.getInstance(aSN1Sequence2)));
        this.integrityCheck = ObjectStoreIntegrityCheck.getInstance(aSN1Sequence.getObjectAt(1));
    }

    public static ObjectStore getInstance(Object object) {
        if (object instanceof ObjectStore) {
            return (ObjectStore)object;
        }
        if (object != null) {
            return new ObjectStore(ASN1Sequence.getInstance(object));
        }
        return null;
    }

    public ObjectStoreIntegrityCheck getIntegrityCheck() {
        return this.integrityCheck;
    }

    public ASN1Encodable getStoreData() {
        return this.storeData;
    }

    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        aSN1EncodableVector.add(this.storeData);
        aSN1EncodableVector.add(this.integrityCheck);
        return new DERSequence(aSN1EncodableVector);
    }
}

