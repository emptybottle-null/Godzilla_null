/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.asn1.crmf;

import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.ASN1TaggedObject;
import org.bouncycastle.asn1.DERBitString;
import org.bouncycastle.asn1.DERSequence;
import org.bouncycastle.asn1.DERTaggedObject;
import org.bouncycastle.asn1.crmf.POPOSigningKeyInput;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;

public class POPOSigningKey
extends ASN1Object {
    private POPOSigningKeyInput poposkInput;
    private AlgorithmIdentifier algorithmIdentifier;
    private DERBitString signature;

    private POPOSigningKey(ASN1Sequence aSN1Sequence) {
        int n = 0;
        if (aSN1Sequence.getObjectAt(n) instanceof ASN1TaggedObject) {
            ASN1TaggedObject aSN1TaggedObject;
            if ((aSN1TaggedObject = (ASN1TaggedObject)aSN1Sequence.getObjectAt(n++)).getTagNo() != 0) {
                throw new IllegalArgumentException("Unknown POPOSigningKeyInput tag: " + aSN1TaggedObject.getTagNo());
            }
            this.poposkInput = POPOSigningKeyInput.getInstance(aSN1TaggedObject.getObject());
        }
        this.algorithmIdentifier = AlgorithmIdentifier.getInstance(aSN1Sequence.getObjectAt(n++));
        this.signature = DERBitString.getInstance(aSN1Sequence.getObjectAt(n));
    }

    public static POPOSigningKey getInstance(Object object) {
        if (object instanceof POPOSigningKey) {
            return (POPOSigningKey)object;
        }
        if (object != null) {
            return new POPOSigningKey(ASN1Sequence.getInstance(object));
        }
        return null;
    }

    public static POPOSigningKey getInstance(ASN1TaggedObject aSN1TaggedObject, boolean bl) {
        return POPOSigningKey.getInstance(ASN1Sequence.getInstance(aSN1TaggedObject, bl));
    }

    public POPOSigningKey(POPOSigningKeyInput pOPOSigningKeyInput, AlgorithmIdentifier algorithmIdentifier, DERBitString dERBitString) {
        this.poposkInput = pOPOSigningKeyInput;
        this.algorithmIdentifier = algorithmIdentifier;
        this.signature = dERBitString;
    }

    public POPOSigningKeyInput getPoposkInput() {
        return this.poposkInput;
    }

    public AlgorithmIdentifier getAlgorithmIdentifier() {
        return this.algorithmIdentifier;
    }

    public DERBitString getSignature() {
        return this.signature;
    }

    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        if (this.poposkInput != null) {
            aSN1EncodableVector.add(new DERTaggedObject(false, 0, this.poposkInput));
        }
        aSN1EncodableVector.add(this.algorithmIdentifier);
        aSN1EncodableVector.add(this.signature);
        return new DERSequence(aSN1EncodableVector);
    }
}

