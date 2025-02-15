/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.asn1.pkcs;

import java.util.Enumeration;
import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1OctetString;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.DEROctetString;
import org.bouncycastle.asn1.DERSequence;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;

public class EncryptedPrivateKeyInfo
extends ASN1Object {
    private AlgorithmIdentifier algId;
    private ASN1OctetString data;

    private EncryptedPrivateKeyInfo(ASN1Sequence aSN1Sequence) {
        Enumeration enumeration = aSN1Sequence.getObjects();
        this.algId = AlgorithmIdentifier.getInstance(enumeration.nextElement());
        this.data = ASN1OctetString.getInstance(enumeration.nextElement());
    }

    public EncryptedPrivateKeyInfo(AlgorithmIdentifier algorithmIdentifier, byte[] byArray) {
        this.algId = algorithmIdentifier;
        this.data = new DEROctetString(byArray);
    }

    public static EncryptedPrivateKeyInfo getInstance(Object object) {
        if (object instanceof EncryptedPrivateKeyInfo) {
            return (EncryptedPrivateKeyInfo)object;
        }
        if (object != null) {
            return new EncryptedPrivateKeyInfo(ASN1Sequence.getInstance(object));
        }
        return null;
    }

    public AlgorithmIdentifier getEncryptionAlgorithm() {
        return this.algId;
    }

    public byte[] getEncryptedData() {
        return this.data.getOctets();
    }

    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        aSN1EncodableVector.add(this.algId);
        aSN1EncodableVector.add(this.data);
        return new DERSequence(aSN1EncodableVector);
    }
}

