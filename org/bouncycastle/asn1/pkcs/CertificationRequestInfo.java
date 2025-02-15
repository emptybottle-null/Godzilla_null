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
import org.bouncycastle.asn1.DERSequence;
import org.bouncycastle.asn1.DERTaggedObject;
import org.bouncycastle.asn1.pkcs.Attribute;
import org.bouncycastle.asn1.pkcs.PKCSObjectIdentifiers;
import org.bouncycastle.asn1.x500.X500Name;
import org.bouncycastle.asn1.x509.SubjectPublicKeyInfo;
import org.bouncycastle.asn1.x509.X509Name;

public class CertificationRequestInfo
extends ASN1Object {
    ASN1Integer version = new ASN1Integer(0L);
    X500Name subject;
    SubjectPublicKeyInfo subjectPKInfo;
    ASN1Set attributes = null;

    public static CertificationRequestInfo getInstance(Object object) {
        if (object instanceof CertificationRequestInfo) {
            return (CertificationRequestInfo)object;
        }
        if (object != null) {
            return new CertificationRequestInfo(ASN1Sequence.getInstance(object));
        }
        return null;
    }

    public CertificationRequestInfo(X500Name x500Name, SubjectPublicKeyInfo subjectPublicKeyInfo, ASN1Set aSN1Set) {
        if (x500Name == null || subjectPublicKeyInfo == null) {
            throw new IllegalArgumentException("Not all mandatory fields set in CertificationRequestInfo generator.");
        }
        CertificationRequestInfo.validateAttributes(aSN1Set);
        this.subject = x500Name;
        this.subjectPKInfo = subjectPublicKeyInfo;
        this.attributes = aSN1Set;
    }

    public CertificationRequestInfo(X509Name x509Name, SubjectPublicKeyInfo subjectPublicKeyInfo, ASN1Set aSN1Set) {
        this(X500Name.getInstance(x509Name.toASN1Primitive()), subjectPublicKeyInfo, aSN1Set);
    }

    public CertificationRequestInfo(ASN1Sequence aSN1Sequence) {
        this.version = (ASN1Integer)aSN1Sequence.getObjectAt(0);
        this.subject = X500Name.getInstance(aSN1Sequence.getObjectAt(1));
        this.subjectPKInfo = SubjectPublicKeyInfo.getInstance(aSN1Sequence.getObjectAt(2));
        if (aSN1Sequence.size() > 3) {
            ASN1TaggedObject aSN1TaggedObject = (ASN1TaggedObject)aSN1Sequence.getObjectAt(3);
            this.attributes = ASN1Set.getInstance(aSN1TaggedObject, false);
        }
        CertificationRequestInfo.validateAttributes(this.attributes);
        if (this.subject == null || this.version == null || this.subjectPKInfo == null) {
            throw new IllegalArgumentException("Not all mandatory fields set in CertificationRequestInfo generator.");
        }
    }

    public ASN1Integer getVersion() {
        return this.version;
    }

    public X500Name getSubject() {
        return this.subject;
    }

    public SubjectPublicKeyInfo getSubjectPublicKeyInfo() {
        return this.subjectPKInfo;
    }

    public ASN1Set getAttributes() {
        return this.attributes;
    }

    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        aSN1EncodableVector.add(this.version);
        aSN1EncodableVector.add(this.subject);
        aSN1EncodableVector.add(this.subjectPKInfo);
        if (this.attributes != null) {
            aSN1EncodableVector.add(new DERTaggedObject(false, 0, this.attributes));
        }
        return new DERSequence(aSN1EncodableVector);
    }

    private static void validateAttributes(ASN1Set aSN1Set) {
        if (aSN1Set == null) {
            return;
        }
        Enumeration enumeration = aSN1Set.getObjects();
        while (enumeration.hasMoreElements()) {
            Attribute attribute = Attribute.getInstance(enumeration.nextElement());
            if (!attribute.getAttrType().equals(PKCSObjectIdentifiers.pkcs_9_at_challengePassword) || attribute.getAttrValues().size() == 1) continue;
            throw new IllegalArgumentException("challengePassword attribute must have one value");
        }
    }
}

