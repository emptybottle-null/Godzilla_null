/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.asn1.cms;

import java.util.Enumeration;
import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1Integer;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.ASN1Set;
import org.bouncycastle.asn1.ASN1TaggedObject;
import org.bouncycastle.asn1.BERSequence;
import org.bouncycastle.asn1.DERTaggedObject;
import org.bouncycastle.asn1.cms.Attributes;
import org.bouncycastle.asn1.cms.EncryptedContentInfo;
import org.bouncycastle.asn1.cms.OriginatorInfo;
import org.bouncycastle.asn1.cms.RecipientInfo;

public class EnvelopedData
extends ASN1Object {
    private ASN1Integer version;
    private OriginatorInfo originatorInfo;
    private ASN1Set recipientInfos;
    private EncryptedContentInfo encryptedContentInfo;
    private ASN1Set unprotectedAttrs;

    public EnvelopedData(OriginatorInfo originatorInfo, ASN1Set aSN1Set, EncryptedContentInfo encryptedContentInfo, ASN1Set aSN1Set2) {
        this.version = new ASN1Integer(EnvelopedData.calculateVersion(originatorInfo, aSN1Set, aSN1Set2));
        this.originatorInfo = originatorInfo;
        this.recipientInfos = aSN1Set;
        this.encryptedContentInfo = encryptedContentInfo;
        this.unprotectedAttrs = aSN1Set2;
    }

    public EnvelopedData(OriginatorInfo originatorInfo, ASN1Set aSN1Set, EncryptedContentInfo encryptedContentInfo, Attributes attributes) {
        this.version = new ASN1Integer(EnvelopedData.calculateVersion(originatorInfo, aSN1Set, ASN1Set.getInstance(attributes)));
        this.originatorInfo = originatorInfo;
        this.recipientInfos = aSN1Set;
        this.encryptedContentInfo = encryptedContentInfo;
        this.unprotectedAttrs = ASN1Set.getInstance(attributes);
    }

    public EnvelopedData(ASN1Sequence aSN1Sequence) {
        int n = 0;
        this.version = (ASN1Integer)aSN1Sequence.getObjectAt(n++);
        ASN1Encodable aSN1Encodable = aSN1Sequence.getObjectAt(n++);
        if (aSN1Encodable instanceof ASN1TaggedObject) {
            this.originatorInfo = OriginatorInfo.getInstance((ASN1TaggedObject)aSN1Encodable, false);
            aSN1Encodable = aSN1Sequence.getObjectAt(n++);
        }
        this.recipientInfos = ASN1Set.getInstance(aSN1Encodable);
        this.encryptedContentInfo = EncryptedContentInfo.getInstance(aSN1Sequence.getObjectAt(n++));
        if (aSN1Sequence.size() > n) {
            this.unprotectedAttrs = ASN1Set.getInstance((ASN1TaggedObject)aSN1Sequence.getObjectAt(n), false);
        }
    }

    public static EnvelopedData getInstance(ASN1TaggedObject aSN1TaggedObject, boolean bl) {
        return EnvelopedData.getInstance(ASN1Sequence.getInstance(aSN1TaggedObject, bl));
    }

    public static EnvelopedData getInstance(Object object) {
        if (object instanceof EnvelopedData) {
            return (EnvelopedData)object;
        }
        if (object != null) {
            return new EnvelopedData(ASN1Sequence.getInstance(object));
        }
        return null;
    }

    public ASN1Integer getVersion() {
        return this.version;
    }

    public OriginatorInfo getOriginatorInfo() {
        return this.originatorInfo;
    }

    public ASN1Set getRecipientInfos() {
        return this.recipientInfos;
    }

    public EncryptedContentInfo getEncryptedContentInfo() {
        return this.encryptedContentInfo;
    }

    public ASN1Set getUnprotectedAttrs() {
        return this.unprotectedAttrs;
    }

    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        aSN1EncodableVector.add(this.version);
        if (this.originatorInfo != null) {
            aSN1EncodableVector.add(new DERTaggedObject(false, 0, this.originatorInfo));
        }
        aSN1EncodableVector.add(this.recipientInfos);
        aSN1EncodableVector.add(this.encryptedContentInfo);
        if (this.unprotectedAttrs != null) {
            aSN1EncodableVector.add(new DERTaggedObject(false, 1, this.unprotectedAttrs));
        }
        return new BERSequence(aSN1EncodableVector);
    }

    public static int calculateVersion(OriginatorInfo originatorInfo, ASN1Set aSN1Set, ASN1Set aSN1Set2) {
        int n;
        if (originatorInfo != null || aSN1Set2 != null) {
            n = 2;
        } else {
            n = 0;
            Enumeration enumeration = aSN1Set.getObjects();
            while (enumeration.hasMoreElements()) {
                RecipientInfo recipientInfo = RecipientInfo.getInstance(enumeration.nextElement());
                if (recipientInfo.getVersion().getValue().intValue() == n) continue;
                n = 2;
                break;
            }
        }
        return n;
    }
}

