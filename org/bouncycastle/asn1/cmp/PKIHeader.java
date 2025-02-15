/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.asn1.cmp;

import java.util.Enumeration;
import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1GeneralizedTime;
import org.bouncycastle.asn1.ASN1Integer;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1OctetString;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.ASN1TaggedObject;
import org.bouncycastle.asn1.DERSequence;
import org.bouncycastle.asn1.DERTaggedObject;
import org.bouncycastle.asn1.cmp.InfoTypeAndValue;
import org.bouncycastle.asn1.cmp.PKIFreeText;
import org.bouncycastle.asn1.x500.X500Name;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.asn1.x509.GeneralName;

public class PKIHeader
extends ASN1Object {
    public static final GeneralName NULL_NAME = new GeneralName(X500Name.getInstance(new DERSequence()));
    public static final int CMP_1999 = 1;
    public static final int CMP_2000 = 2;
    private ASN1Integer pvno;
    private GeneralName sender;
    private GeneralName recipient;
    private ASN1GeneralizedTime messageTime;
    private AlgorithmIdentifier protectionAlg;
    private ASN1OctetString senderKID;
    private ASN1OctetString recipKID;
    private ASN1OctetString transactionID;
    private ASN1OctetString senderNonce;
    private ASN1OctetString recipNonce;
    private PKIFreeText freeText;
    private ASN1Sequence generalInfo;

    private PKIHeader(ASN1Sequence aSN1Sequence) {
        Enumeration enumeration = aSN1Sequence.getObjects();
        this.pvno = ASN1Integer.getInstance(enumeration.nextElement());
        this.sender = GeneralName.getInstance(enumeration.nextElement());
        this.recipient = GeneralName.getInstance(enumeration.nextElement());
        block11: while (enumeration.hasMoreElements()) {
            ASN1TaggedObject aSN1TaggedObject = (ASN1TaggedObject)enumeration.nextElement();
            switch (aSN1TaggedObject.getTagNo()) {
                case 0: {
                    this.messageTime = ASN1GeneralizedTime.getInstance(aSN1TaggedObject, true);
                    continue block11;
                }
                case 1: {
                    this.protectionAlg = AlgorithmIdentifier.getInstance(aSN1TaggedObject, true);
                    continue block11;
                }
                case 2: {
                    this.senderKID = ASN1OctetString.getInstance(aSN1TaggedObject, true);
                    continue block11;
                }
                case 3: {
                    this.recipKID = ASN1OctetString.getInstance(aSN1TaggedObject, true);
                    continue block11;
                }
                case 4: {
                    this.transactionID = ASN1OctetString.getInstance(aSN1TaggedObject, true);
                    continue block11;
                }
                case 5: {
                    this.senderNonce = ASN1OctetString.getInstance(aSN1TaggedObject, true);
                    continue block11;
                }
                case 6: {
                    this.recipNonce = ASN1OctetString.getInstance(aSN1TaggedObject, true);
                    continue block11;
                }
                case 7: {
                    this.freeText = PKIFreeText.getInstance(aSN1TaggedObject, true);
                    continue block11;
                }
                case 8: {
                    this.generalInfo = ASN1Sequence.getInstance(aSN1TaggedObject, true);
                    continue block11;
                }
            }
            throw new IllegalArgumentException("unknown tag number: " + aSN1TaggedObject.getTagNo());
        }
    }

    public static PKIHeader getInstance(Object object) {
        if (object instanceof PKIHeader) {
            return (PKIHeader)object;
        }
        if (object != null) {
            return new PKIHeader(ASN1Sequence.getInstance(object));
        }
        return null;
    }

    public PKIHeader(int n, GeneralName generalName, GeneralName generalName2) {
        this(new ASN1Integer(n), generalName, generalName2);
    }

    private PKIHeader(ASN1Integer aSN1Integer, GeneralName generalName, GeneralName generalName2) {
        this.pvno = aSN1Integer;
        this.sender = generalName;
        this.recipient = generalName2;
    }

    public ASN1Integer getPvno() {
        return this.pvno;
    }

    public GeneralName getSender() {
        return this.sender;
    }

    public GeneralName getRecipient() {
        return this.recipient;
    }

    public ASN1GeneralizedTime getMessageTime() {
        return this.messageTime;
    }

    public AlgorithmIdentifier getProtectionAlg() {
        return this.protectionAlg;
    }

    public ASN1OctetString getSenderKID() {
        return this.senderKID;
    }

    public ASN1OctetString getRecipKID() {
        return this.recipKID;
    }

    public ASN1OctetString getTransactionID() {
        return this.transactionID;
    }

    public ASN1OctetString getSenderNonce() {
        return this.senderNonce;
    }

    public ASN1OctetString getRecipNonce() {
        return this.recipNonce;
    }

    public PKIFreeText getFreeText() {
        return this.freeText;
    }

    public InfoTypeAndValue[] getGeneralInfo() {
        if (this.generalInfo == null) {
            return null;
        }
        InfoTypeAndValue[] infoTypeAndValueArray = new InfoTypeAndValue[this.generalInfo.size()];
        for (int i = 0; i < infoTypeAndValueArray.length; ++i) {
            infoTypeAndValueArray[i] = InfoTypeAndValue.getInstance(this.generalInfo.getObjectAt(i));
        }
        return infoTypeAndValueArray;
    }

    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        aSN1EncodableVector.add(this.pvno);
        aSN1EncodableVector.add(this.sender);
        aSN1EncodableVector.add(this.recipient);
        this.addOptional(aSN1EncodableVector, 0, this.messageTime);
        this.addOptional(aSN1EncodableVector, 1, this.protectionAlg);
        this.addOptional(aSN1EncodableVector, 2, this.senderKID);
        this.addOptional(aSN1EncodableVector, 3, this.recipKID);
        this.addOptional(aSN1EncodableVector, 4, this.transactionID);
        this.addOptional(aSN1EncodableVector, 5, this.senderNonce);
        this.addOptional(aSN1EncodableVector, 6, this.recipNonce);
        this.addOptional(aSN1EncodableVector, 7, this.freeText);
        this.addOptional(aSN1EncodableVector, 8, this.generalInfo);
        return new DERSequence(aSN1EncodableVector);
    }

    private void addOptional(ASN1EncodableVector aSN1EncodableVector, int n, ASN1Encodable aSN1Encodable) {
        if (aSN1Encodable != null) {
            aSN1EncodableVector.add(new DERTaggedObject(true, n, aSN1Encodable));
        }
    }
}

