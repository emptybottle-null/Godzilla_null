/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.asn1.pkcs;

import java.util.Enumeration;
import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1Integer;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.ASN1Set;
import org.bouncycastle.asn1.ASN1TaggedObject;
import org.bouncycastle.asn1.BERSequence;
import org.bouncycastle.asn1.DERTaggedObject;
import org.bouncycastle.asn1.pkcs.ContentInfo;
import org.bouncycastle.asn1.pkcs.PKCSObjectIdentifiers;

public class SignedData
extends ASN1Object
implements PKCSObjectIdentifiers {
    private ASN1Integer version;
    private ASN1Set digestAlgorithms;
    private ContentInfo contentInfo;
    private ASN1Set certificates;
    private ASN1Set crls;
    private ASN1Set signerInfos;

    public static SignedData getInstance(Object object) {
        if (object instanceof SignedData) {
            return (SignedData)object;
        }
        if (object != null) {
            return new SignedData(ASN1Sequence.getInstance(object));
        }
        return null;
    }

    public SignedData(ASN1Integer aSN1Integer, ASN1Set aSN1Set, ContentInfo contentInfo, ASN1Set aSN1Set2, ASN1Set aSN1Set3, ASN1Set aSN1Set4) {
        this.version = aSN1Integer;
        this.digestAlgorithms = aSN1Set;
        this.contentInfo = contentInfo;
        this.certificates = aSN1Set2;
        this.crls = aSN1Set3;
        this.signerInfos = aSN1Set4;
    }

    public SignedData(ASN1Sequence aSN1Sequence) {
        Enumeration enumeration = aSN1Sequence.getObjects();
        this.version = (ASN1Integer)enumeration.nextElement();
        this.digestAlgorithms = (ASN1Set)enumeration.nextElement();
        this.contentInfo = ContentInfo.getInstance(enumeration.nextElement());
        while (enumeration.hasMoreElements()) {
            ASN1Primitive aSN1Primitive = (ASN1Primitive)enumeration.nextElement();
            if (aSN1Primitive instanceof ASN1TaggedObject) {
                ASN1TaggedObject aSN1TaggedObject = (ASN1TaggedObject)aSN1Primitive;
                switch (aSN1TaggedObject.getTagNo()) {
                    case 0: {
                        this.certificates = ASN1Set.getInstance(aSN1TaggedObject, false);
                        break;
                    }
                    case 1: {
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

    public ContentInfo getContentInfo() {
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
            aSN1EncodableVector.add(new DERTaggedObject(false, 0, this.certificates));
        }
        if (this.crls != null) {
            aSN1EncodableVector.add(new DERTaggedObject(false, 1, this.crls));
        }
        aSN1EncodableVector.add(this.signerInfos);
        return new BERSequence(aSN1EncodableVector);
    }
}

