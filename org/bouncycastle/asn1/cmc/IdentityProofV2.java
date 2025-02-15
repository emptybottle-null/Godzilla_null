/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.asn1.cmc;

import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1OctetString;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.DEROctetString;
import org.bouncycastle.asn1.DERSequence;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.util.Arrays;

public class IdentityProofV2
extends ASN1Object {
    private final AlgorithmIdentifier proofAlgID;
    private final AlgorithmIdentifier macAlgId;
    private final byte[] witness;

    public IdentityProofV2(AlgorithmIdentifier algorithmIdentifier, AlgorithmIdentifier algorithmIdentifier2, byte[] byArray) {
        this.proofAlgID = algorithmIdentifier;
        this.macAlgId = algorithmIdentifier2;
        this.witness = Arrays.clone(byArray);
    }

    private IdentityProofV2(ASN1Sequence aSN1Sequence) {
        if (aSN1Sequence.size() != 3) {
            throw new IllegalArgumentException("incorrect sequence size");
        }
        this.proofAlgID = AlgorithmIdentifier.getInstance(aSN1Sequence.getObjectAt(0));
        this.macAlgId = AlgorithmIdentifier.getInstance(aSN1Sequence.getObjectAt(1));
        this.witness = Arrays.clone(ASN1OctetString.getInstance(aSN1Sequence.getObjectAt(2)).getOctets());
    }

    public static IdentityProofV2 getInstance(Object object) {
        if (object instanceof IdentityProofV2) {
            return (IdentityProofV2)object;
        }
        if (object != null) {
            return new IdentityProofV2(ASN1Sequence.getInstance(object));
        }
        return null;
    }

    public AlgorithmIdentifier getProofAlgID() {
        return this.proofAlgID;
    }

    public AlgorithmIdentifier getMacAlgId() {
        return this.macAlgId;
    }

    public byte[] getWitness() {
        return Arrays.clone(this.witness);
    }

    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        aSN1EncodableVector.add(this.proofAlgID);
        aSN1EncodableVector.add(this.macAlgId);
        aSN1EncodableVector.add(new DEROctetString(this.getWitness()));
        return new DERSequence(aSN1EncodableVector);
    }
}

