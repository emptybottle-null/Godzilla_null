/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.asn1.x509;

import java.io.IOException;
import java.util.Enumeration;
import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1InputStream;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.ASN1TaggedObject;
import org.bouncycastle.asn1.DERBitString;
import org.bouncycastle.asn1.DERSequence;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;

public class SubjectPublicKeyInfo
extends ASN1Object {
    private AlgorithmIdentifier algId;
    private DERBitString keyData;

    public static SubjectPublicKeyInfo getInstance(ASN1TaggedObject aSN1TaggedObject, boolean bl) {
        return SubjectPublicKeyInfo.getInstance(ASN1Sequence.getInstance(aSN1TaggedObject, bl));
    }

    public static SubjectPublicKeyInfo getInstance(Object object) {
        if (object instanceof SubjectPublicKeyInfo) {
            return (SubjectPublicKeyInfo)object;
        }
        if (object != null) {
            return new SubjectPublicKeyInfo(ASN1Sequence.getInstance(object));
        }
        return null;
    }

    public SubjectPublicKeyInfo(AlgorithmIdentifier algorithmIdentifier, ASN1Encodable aSN1Encodable) throws IOException {
        this.keyData = new DERBitString(aSN1Encodable);
        this.algId = algorithmIdentifier;
    }

    public SubjectPublicKeyInfo(AlgorithmIdentifier algorithmIdentifier, byte[] byArray) {
        this.keyData = new DERBitString(byArray);
        this.algId = algorithmIdentifier;
    }

    public SubjectPublicKeyInfo(ASN1Sequence aSN1Sequence) {
        if (aSN1Sequence.size() != 2) {
            throw new IllegalArgumentException("Bad sequence size: " + aSN1Sequence.size());
        }
        Enumeration enumeration = aSN1Sequence.getObjects();
        this.algId = AlgorithmIdentifier.getInstance(enumeration.nextElement());
        this.keyData = DERBitString.getInstance(enumeration.nextElement());
    }

    public AlgorithmIdentifier getAlgorithm() {
        return this.algId;
    }

    public AlgorithmIdentifier getAlgorithmId() {
        return this.algId;
    }

    public ASN1Primitive parsePublicKey() throws IOException {
        ASN1InputStream aSN1InputStream = new ASN1InputStream(this.keyData.getOctets());
        return aSN1InputStream.readObject();
    }

    public ASN1Primitive getPublicKey() throws IOException {
        ASN1InputStream aSN1InputStream = new ASN1InputStream(this.keyData.getOctets());
        return aSN1InputStream.readObject();
    }

    public DERBitString getPublicKeyData() {
        return this.keyData;
    }

    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        aSN1EncodableVector.add(this.algId);
        aSN1EncodableVector.add(this.keyData);
        return new DERSequence(aSN1EncodableVector);
    }
}

