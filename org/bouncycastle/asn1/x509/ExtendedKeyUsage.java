/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.asn1.x509;

import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;
import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.ASN1TaggedObject;
import org.bouncycastle.asn1.DERSequence;
import org.bouncycastle.asn1.x509.Extension;
import org.bouncycastle.asn1.x509.Extensions;
import org.bouncycastle.asn1.x509.KeyPurposeId;

public class ExtendedKeyUsage
extends ASN1Object {
    Hashtable usageTable = new Hashtable();
    ASN1Sequence seq;

    public static ExtendedKeyUsage getInstance(ASN1TaggedObject aSN1TaggedObject, boolean bl) {
        return ExtendedKeyUsage.getInstance(ASN1Sequence.getInstance(aSN1TaggedObject, bl));
    }

    public static ExtendedKeyUsage getInstance(Object object) {
        if (object instanceof ExtendedKeyUsage) {
            return (ExtendedKeyUsage)object;
        }
        if (object != null) {
            return new ExtendedKeyUsage(ASN1Sequence.getInstance(object));
        }
        return null;
    }

    public static ExtendedKeyUsage fromExtensions(Extensions extensions) {
        return ExtendedKeyUsage.getInstance(extensions.getExtensionParsedValue(Extension.extendedKeyUsage));
    }

    public ExtendedKeyUsage(KeyPurposeId keyPurposeId) {
        this.seq = new DERSequence(keyPurposeId);
        this.usageTable.put(keyPurposeId, keyPurposeId);
    }

    private ExtendedKeyUsage(ASN1Sequence aSN1Sequence) {
        this.seq = aSN1Sequence;
        Enumeration enumeration = aSN1Sequence.getObjects();
        while (enumeration.hasMoreElements()) {
            ASN1Encodable aSN1Encodable = (ASN1Encodable)enumeration.nextElement();
            if (!(aSN1Encodable.toASN1Primitive() instanceof ASN1ObjectIdentifier)) {
                throw new IllegalArgumentException("Only ASN1ObjectIdentifiers allowed in ExtendedKeyUsage.");
            }
            this.usageTable.put(aSN1Encodable, aSN1Encodable);
        }
    }

    public ExtendedKeyUsage(KeyPurposeId[] keyPurposeIdArray) {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        for (int i = 0; i != keyPurposeIdArray.length; ++i) {
            aSN1EncodableVector.add(keyPurposeIdArray[i]);
            this.usageTable.put(keyPurposeIdArray[i], keyPurposeIdArray[i]);
        }
        this.seq = new DERSequence(aSN1EncodableVector);
    }

    public ExtendedKeyUsage(Vector vector) {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        Enumeration enumeration = vector.elements();
        while (enumeration.hasMoreElements()) {
            KeyPurposeId keyPurposeId = KeyPurposeId.getInstance(enumeration.nextElement());
            aSN1EncodableVector.add(keyPurposeId);
            this.usageTable.put(keyPurposeId, keyPurposeId);
        }
        this.seq = new DERSequence(aSN1EncodableVector);
    }

    public boolean hasKeyPurposeId(KeyPurposeId keyPurposeId) {
        return this.usageTable.get(keyPurposeId) != null;
    }

    public KeyPurposeId[] getUsages() {
        KeyPurposeId[] keyPurposeIdArray = new KeyPurposeId[this.seq.size()];
        int n = 0;
        Enumeration enumeration = this.seq.getObjects();
        while (enumeration.hasMoreElements()) {
            keyPurposeIdArray[n++] = KeyPurposeId.getInstance(enumeration.nextElement());
        }
        return keyPurposeIdArray;
    }

    public int size() {
        return this.usageTable.size();
    }

    public ASN1Primitive toASN1Primitive() {
        return this.seq;
    }
}

