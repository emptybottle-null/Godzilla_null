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
import org.bouncycastle.asn1.ASN1Set;
import org.bouncycastle.asn1.ASN1TaggedObject;
import org.bouncycastle.asn1.BERSequence;
import org.bouncycastle.asn1.DERTaggedObject;
import org.bouncycastle.asn1.cms.CMSObjectIdentifiers;
import org.bouncycastle.asn1.cms.EncryptedContentInfo;
import org.bouncycastle.asn1.cms.OriginatorInfo;

public class AuthEnvelopedData
extends ASN1Object {
    private ASN1Integer version;
    private OriginatorInfo originatorInfo;
    private ASN1Set recipientInfos;
    private EncryptedContentInfo authEncryptedContentInfo;
    private ASN1Set authAttrs;
    private ASN1OctetString mac;
    private ASN1Set unauthAttrs;

    public AuthEnvelopedData(OriginatorInfo originatorInfo, ASN1Set aSN1Set, EncryptedContentInfo encryptedContentInfo, ASN1Set aSN1Set2, ASN1OctetString aSN1OctetString, ASN1Set aSN1Set3) {
        this.version = new ASN1Integer(0L);
        this.originatorInfo = originatorInfo;
        this.recipientInfos = aSN1Set;
        if (this.recipientInfos.size() == 0) {
            throw new IllegalArgumentException("AuthEnvelopedData requires at least 1 RecipientInfo");
        }
        this.authEncryptedContentInfo = encryptedContentInfo;
        this.authAttrs = aSN1Set2;
        if (!(encryptedContentInfo.getContentType().equals(CMSObjectIdentifiers.data) || aSN1Set2 != null && aSN1Set2.size() != 0)) {
            throw new IllegalArgumentException("authAttrs must be present with non-data content");
        }
        this.mac = aSN1OctetString;
        this.unauthAttrs = aSN1Set3;
    }

    private AuthEnvelopedData(ASN1Sequence aSN1Sequence) {
        int n = 0;
        ASN1Primitive aSN1Primitive = aSN1Sequence.getObjectAt(n++).toASN1Primitive();
        this.version = (ASN1Integer)aSN1Primitive;
        if (this.version.getValue().intValue() != 0) {
            throw new IllegalArgumentException("AuthEnvelopedData version number must be 0");
        }
        if ((aSN1Primitive = aSN1Sequence.getObjectAt(n++).toASN1Primitive()) instanceof ASN1TaggedObject) {
            this.originatorInfo = OriginatorInfo.getInstance((ASN1TaggedObject)aSN1Primitive, false);
            aSN1Primitive = aSN1Sequence.getObjectAt(n++).toASN1Primitive();
        }
        this.recipientInfos = ASN1Set.getInstance(aSN1Primitive);
        if (this.recipientInfos.size() == 0) {
            throw new IllegalArgumentException("AuthEnvelopedData requires at least 1 RecipientInfo");
        }
        aSN1Primitive = aSN1Sequence.getObjectAt(n++).toASN1Primitive();
        this.authEncryptedContentInfo = EncryptedContentInfo.getInstance(aSN1Primitive);
        if ((aSN1Primitive = aSN1Sequence.getObjectAt(n++).toASN1Primitive()) instanceof ASN1TaggedObject) {
            this.authAttrs = ASN1Set.getInstance((ASN1TaggedObject)aSN1Primitive, false);
            aSN1Primitive = aSN1Sequence.getObjectAt(n++).toASN1Primitive();
        } else if (!(this.authEncryptedContentInfo.getContentType().equals(CMSObjectIdentifiers.data) || this.authAttrs != null && this.authAttrs.size() != 0)) {
            throw new IllegalArgumentException("authAttrs must be present with non-data content");
        }
        this.mac = ASN1OctetString.getInstance(aSN1Primitive);
        if (aSN1Sequence.size() > n) {
            aSN1Primitive = aSN1Sequence.getObjectAt(n).toASN1Primitive();
            this.unauthAttrs = ASN1Set.getInstance((ASN1TaggedObject)aSN1Primitive, false);
        }
    }

    public static AuthEnvelopedData getInstance(ASN1TaggedObject aSN1TaggedObject, boolean bl) {
        return AuthEnvelopedData.getInstance(ASN1Sequence.getInstance(aSN1TaggedObject, bl));
    }

    public static AuthEnvelopedData getInstance(Object object) {
        if (object == null || object instanceof AuthEnvelopedData) {
            return (AuthEnvelopedData)object;
        }
        if (object instanceof ASN1Sequence) {
            return new AuthEnvelopedData((ASN1Sequence)object);
        }
        throw new IllegalArgumentException("Invalid AuthEnvelopedData: " + object.getClass().getName());
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

    public EncryptedContentInfo getAuthEncryptedContentInfo() {
        return this.authEncryptedContentInfo;
    }

    public ASN1Set getAuthAttrs() {
        return this.authAttrs;
    }

    public ASN1OctetString getMac() {
        return this.mac;
    }

    public ASN1Set getUnauthAttrs() {
        return this.unauthAttrs;
    }

    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        aSN1EncodableVector.add(this.version);
        if (this.originatorInfo != null) {
            aSN1EncodableVector.add(new DERTaggedObject(false, 0, this.originatorInfo));
        }
        aSN1EncodableVector.add(this.recipientInfos);
        aSN1EncodableVector.add(this.authEncryptedContentInfo);
        if (this.authAttrs != null) {
            aSN1EncodableVector.add(new DERTaggedObject(false, 1, this.authAttrs));
        }
        aSN1EncodableVector.add(this.mac);
        if (this.unauthAttrs != null) {
            aSN1EncodableVector.add(new DERTaggedObject(false, 2, this.unauthAttrs));
        }
        return new BERSequence(aSN1EncodableVector);
    }
}

