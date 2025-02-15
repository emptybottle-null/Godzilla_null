/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.asn1.cms;

import java.util.Enumeration;
import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1Integer;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.ASN1Set;
import org.bouncycastle.asn1.ASN1TaggedObject;
import org.bouncycastle.asn1.BERSequence;
import org.bouncycastle.asn1.BERSet;
import org.bouncycastle.asn1.BERTaggedObject;
import org.bouncycastle.asn1.DERTaggedObject;
import org.bouncycastle.asn1.cms.CMSObjectIdentifiers;
import org.bouncycastle.asn1.cms.ContentInfo;
import org.bouncycastle.asn1.cms.SignerInfo;

public class SignedData
extends ASN1Object {
    private static final ASN1Integer VERSION_1 = new ASN1Integer(1L);
    private static final ASN1Integer VERSION_3 = new ASN1Integer(3L);
    private static final ASN1Integer VERSION_4 = new ASN1Integer(4L);
    private static final ASN1Integer VERSION_5 = new ASN1Integer(5L);
    private ASN1Integer version;
    private ASN1Set digestAlgorithms;
    private ContentInfo contentInfo;
    private ASN1Set certificates;
    private ASN1Set crls;
    private ASN1Set signerInfos;
    private boolean certsBer;
    private boolean crlsBer;

    public static SignedData getInstance(Object object) {
        if (object instanceof SignedData) {
            return (SignedData)object;
        }
        if (object != null) {
            return new SignedData(ASN1Sequence.getInstance(object));
        }
        return null;
    }

    public SignedData(ASN1Set aSN1Set, ContentInfo contentInfo, ASN1Set aSN1Set2, ASN1Set aSN1Set3, ASN1Set aSN1Set4) {
        this.version = this.calculateVersion(contentInfo.getContentType(), aSN1Set2, aSN1Set3, aSN1Set4);
        this.digestAlgorithms = aSN1Set;
        this.contentInfo = contentInfo;
        this.certificates = aSN1Set2;
        this.crls = aSN1Set3;
        this.signerInfos = aSN1Set4;
        this.crlsBer = aSN1Set3 instanceof BERSet;
        this.certsBer = aSN1Set2 instanceof BERSet;
    }

    private ASN1Integer calculateVersion(ASN1ObjectIdentifier aSN1ObjectIdentifier, ASN1Set aSN1Set, ASN1Set aSN1Set2, ASN1Set aSN1Set3) {
        Object e;
        Enumeration enumeration;
        boolean bl = false;
        boolean bl2 = false;
        boolean bl3 = false;
        boolean bl4 = false;
        if (aSN1Set != null) {
            enumeration = aSN1Set.getObjects();
            while (enumeration.hasMoreElements()) {
                e = enumeration.nextElement();
                if (!(e instanceof ASN1TaggedObject)) continue;
                ASN1TaggedObject aSN1TaggedObject = ASN1TaggedObject.getInstance(e);
                if (aSN1TaggedObject.getTagNo() == 1) {
                    bl3 = true;
                    continue;
                }
                if (aSN1TaggedObject.getTagNo() == 2) {
                    bl4 = true;
                    continue;
                }
                if (aSN1TaggedObject.getTagNo() != 3) continue;
                bl = true;
            }
        }
        if (bl) {
            return new ASN1Integer(5L);
        }
        if (aSN1Set2 != null) {
            enumeration = aSN1Set2.getObjects();
            while (enumeration.hasMoreElements()) {
                e = enumeration.nextElement();
                if (!(e instanceof ASN1TaggedObject)) continue;
                bl2 = true;
            }
        }
        if (bl2) {
            return VERSION_5;
        }
        if (bl4) {
            return VERSION_4;
        }
        if (bl3) {
            return VERSION_3;
        }
        if (this.checkForVersion3(aSN1Set3)) {
            return VERSION_3;
        }
        if (!CMSObjectIdentifiers.data.equals(aSN1ObjectIdentifier)) {
            return VERSION_3;
        }
        return VERSION_1;
    }

    private boolean checkForVersion3(ASN1Set aSN1Set) {
        Enumeration enumeration = aSN1Set.getObjects();
        while (enumeration.hasMoreElements()) {
            SignerInfo signerInfo = SignerInfo.getInstance(enumeration.nextElement());
            if (signerInfo.getVersion().getValue().intValue() != 3) continue;
            return true;
        }
        return false;
    }

    private SignedData(ASN1Sequence aSN1Sequence) {
        Enumeration enumeration = aSN1Sequence.getObjects();
        this.version = ASN1Integer.getInstance(enumeration.nextElement());
        this.digestAlgorithms = (ASN1Set)enumeration.nextElement();
        this.contentInfo = ContentInfo.getInstance(enumeration.nextElement());
        while (enumeration.hasMoreElements()) {
            ASN1Primitive aSN1Primitive = (ASN1Primitive)enumeration.nextElement();
            if (aSN1Primitive instanceof ASN1TaggedObject) {
                ASN1TaggedObject aSN1TaggedObject = (ASN1TaggedObject)aSN1Primitive;
                switch (aSN1TaggedObject.getTagNo()) {
                    case 0: {
                        this.certsBer = aSN1TaggedObject instanceof BERTaggedObject;
                        this.certificates = ASN1Set.getInstance(aSN1TaggedObject, false);
                        break;
                    }
                    case 1: {
                        this.crlsBer = aSN1TaggedObject instanceof BERTaggedObject;
                        this.crls = ASN1Set.getInstance(aSN1TaggedObject, false);
                        break;
                    }
                    default: {
                        throw new IllegalArgumentException("unknown tag value " + aSN1TaggedObject.getTagNo());
                    }
                }
                continue;
            }
            this.signerInfos = (ASN1Set)aSN1Primitive;
        }
    }

    public ASN1Integer getVersion() {
        return this.version;
    }

    public ASN1Set getDigestAlgorithms() {
        return this.digestAlgorithms;
    }

    public ContentInfo getEncapContentInfo() {
        return this.contentInfo;
    }

    public ASN1Set getCertificates() {
        return this.certificates;
    }

    public ASN1Set getCRLs() {
        return this.crls;
    }

    public ASN1Set getSignerInfos() {
        return this.signerInfos;
    }

    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        aSN1EncodableVector.add(this.version);
        aSN1EncodableVector.add(this.digestAlgorithms);
        aSN1EncodableVector.add(this.contentInfo);
        if (this.certificates != null) {
            if (this.certsBer) {
                aSN1EncodableVector.add(new BERTaggedObject(false, 0, this.certificates));
            } else {
                aSN1EncodableVector.add(new DERTaggedObject(false, 0, this.certificates));
            }
        }
        if (this.crls != null) {
            if (this.crlsBer) {
                aSN1EncodableVector.add(new BERTaggedObject(false, 1, this.crls));
            } else {
                aSN1EncodableVector.add(new DERTaggedObject(false, 1, this.crls));
            }
        }
        aSN1EncodableVector.add(this.signerInfos);
        return new BERSequence(aSN1EncodableVector);
    }
}

