/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.openssl;

import java.util.Collections;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.ASN1TaggedObject;
import org.bouncycastle.asn1.DERSequence;
import org.bouncycastle.asn1.DERTaggedObject;
import org.bouncycastle.asn1.DERUTF8String;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class CertificateTrustBlock {
    private ASN1Sequence uses;
    private ASN1Sequence prohibitions;
    private String alias;

    public CertificateTrustBlock(Set<ASN1ObjectIdentifier> set) {
        this(null, set, null);
    }

    public CertificateTrustBlock(String string, Set<ASN1ObjectIdentifier> set) {
        this(string, set, null);
    }

    public CertificateTrustBlock(String string, Set<ASN1ObjectIdentifier> set, Set<ASN1ObjectIdentifier> set2) {
        this.alias = string;
        this.uses = this.toSequence(set);
        this.prohibitions = this.toSequence(set2);
    }

    CertificateTrustBlock(byte[] byArray) {
        ASN1Sequence aSN1Sequence = ASN1Sequence.getInstance(byArray);
        Enumeration enumeration = aSN1Sequence.getObjects();
        while (enumeration.hasMoreElements()) {
            ASN1Encodable aSN1Encodable = (ASN1Encodable)enumeration.nextElement();
            if (aSN1Encodable instanceof ASN1Sequence) {
                this.uses = ASN1Sequence.getInstance(aSN1Encodable);
                continue;
            }
            if (aSN1Encodable instanceof ASN1TaggedObject) {
                this.prohibitions = ASN1Sequence.getInstance((ASN1TaggedObject)aSN1Encodable, false);
                continue;
            }
            if (!(aSN1Encodable instanceof DERUTF8String)) continue;
            this.alias = DERUTF8String.getInstance(aSN1Encodable).getString();
        }
    }

    public String getAlias() {
        return this.alias;
    }

    public Set<ASN1ObjectIdentifier> getUses() {
        return this.toSet(this.uses);
    }

    public Set<ASN1ObjectIdentifier> getProhibitions() {
        return this.toSet(this.prohibitions);
    }

    private Set<ASN1ObjectIdentifier> toSet(ASN1Sequence aSN1Sequence) {
        if (aSN1Sequence != null) {
            HashSet<ASN1ObjectIdentifier> hashSet = new HashSet<ASN1ObjectIdentifier>(aSN1Sequence.size());
            Enumeration enumeration = aSN1Sequence.getObjects();
            while (enumeration.hasMoreElements()) {
                hashSet.add(ASN1ObjectIdentifier.getInstance(enumeration.nextElement()));
            }
            return hashSet;
        }
        return Collections.EMPTY_SET;
    }

    private ASN1Sequence toSequence(Set<ASN1ObjectIdentifier> set) {
        if (set == null || set.isEmpty()) {
            return null;
        }
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        Iterator<ASN1ObjectIdentifier> iterator = set.iterator();
        while (iterator.hasNext()) {
            aSN1EncodableVector.add(iterator.next());
        }
        return new DERSequence(aSN1EncodableVector);
    }

    ASN1Sequence toASN1Sequence() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        if (this.uses != null) {
            aSN1EncodableVector.add(this.uses);
        }
        if (this.prohibitions != null) {
            aSN1EncodableVector.add(new DERTaggedObject(false, 0, this.prohibitions));
        }
        if (this.alias != null) {
            aSN1EncodableVector.add(new DERUTF8String(this.alias));
        }
        return new DERSequence(aSN1EncodableVector);
    }
}

