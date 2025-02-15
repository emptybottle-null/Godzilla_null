/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.asn1.cms;

import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1Integer;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1OctetString;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.ASN1TaggedObject;
import org.bouncycastle.asn1.DERSequence;
import org.bouncycastle.asn1.DERTaggedObject;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;

public class PasswordRecipientInfo
extends ASN1Object {
    private ASN1Integer version;
    private AlgorithmIdentifier keyDerivationAlgorithm;
    private AlgorithmIdentifier keyEncryptionAlgorithm;
    private ASN1OctetString encryptedKey;

    public PasswordRecipientInfo(AlgorithmIdentifier algorithmIdentifier, ASN1OctetString aSN1OctetString) {
        this.version = new ASN1Integer(0L);
        this.keyEncryptionAlgorithm = algorithmIdentifier;
        this.encryptedKey = aSN1OctetString;
    }

    public PasswordRecipientInfo(AlgorithmIdentifier algorithmIdentifier, AlgorithmIdentifier algorithmIdentifier2, ASN1OctetString aSN1OctetString) {
        this.version = new ASN1Integer(0L);
        this.keyDerivationAlgorithm = algorithmIdentifier;
        this.keyEncryptionAlgorithm = algorithmIdentifier2;
        this.encryptedKey = aSN1OctetString;
    }

    public PasswordRecipientInfo(ASN1Sequence aSN1Sequence) {
        this.version = (ASN1Integer)aSN1Sequence.getObjectAt(0);
        if (aSN1Sequence.getObjectAt(1) instanceof ASN1TaggedObject) {
            this.keyDerivationAlgorithm = AlgorithmIdentifier.getInstance((ASN1TaggedObject)aSN1Sequence.getObjectAt(1), false);
            this.keyEncryptionAlgorithm = AlgorithmIdentifier.getInstance(aSN1Sequence.getObjectAt(2));
            this.encryptedKey = (ASN1OctetString)aSN1Sequence.getObjectAt(3);
        } else {
            this.keyEncryptionAlgorithm = AlgorithmIdentifier.getInstance(aSN1Sequence.getObjectAt(1));
            this.encryptedKey = (ASN1OctetString)aSN1Sequence.getObjectAt(2);
        }
    }

    public static PasswordRecipientInfo getInstance(ASN1TaggedObject aSN1TaggedObject, boolean bl) {
        return PasswordRecipientInfo.getInstance(ASN1Sequence.getInstance(aSN1TaggedObject, bl));
    }

    public static PasswordRecipientInfo getInstance(Object object) {
        if (object instanceof PasswordRecipientInfo) {
            return (PasswordRecipientInfo)object;
        }
        if (object != null) {
            return new PasswordRecipientInfo(ASN1Sequence.getInstance(object));
        }
        return null;
    }

    public ASN1Integer getVersion() {
        return this.version;
    }

    public AlgorithmIdentifier getKeyDerivationAlgorithm() {
        return this.keyDerivationAlgorithm;
    }

    public AlgorithmIdentifier getKeyEncryptionAlgorithm() {
        return this.keyEncryptionAlgorithm;
    }

    public ASN1OctetString getEncryptedKey() {
        return this.encryptedKey;
    }

    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        aSN1EncodableVector.add(this.version);
        if (this.keyDerivationAlgorithm != null) {
            aSN1EncodableVector.add(new DERTaggedObject(false, 0, this.keyDerivationAlgorithm));
        }
        aSN1EncodableVector.add(this.keyEncryptionAlgorithm);
        aSN1EncodableVector.add(this.encryptedKey);
        return new DERSequence(aSN1EncodableVector);
    }
}

