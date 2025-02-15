/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.asn1.isismtt.x509;

import java.util.Enumeration;
import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.ASN1String;
import org.bouncycastle.asn1.ASN1TaggedObject;
import org.bouncycastle.asn1.DERIA5String;
import org.bouncycastle.asn1.DERSequence;
import org.bouncycastle.asn1.isismtt.ISISMTTObjectIdentifiers;
import org.bouncycastle.asn1.x500.DirectoryString;

public class NamingAuthority
extends ASN1Object {
    public static final ASN1ObjectIdentifier id_isismtt_at_namingAuthorities_RechtWirtschaftSteuern = new ASN1ObjectIdentifier(ISISMTTObjectIdentifiers.id_isismtt_at_namingAuthorities + ".1");
    private ASN1ObjectIdentifier namingAuthorityId;
    private String namingAuthorityUrl;
    private DirectoryString namingAuthorityText;

    public static NamingAuthority getInstance(Object object) {
        if (object == null || object instanceof NamingAuthority) {
            return (NamingAuthority)object;
        }
        if (object instanceof ASN1Sequence) {
            return new NamingAuthority((ASN1Sequence)object);
        }
        throw new IllegalArgumentException("illegal object in getInstance: " + object.getClass().getName());
    }

    public static NamingAuthority getInstance(ASN1TaggedObject aSN1TaggedObject, boolean bl) {
        return NamingAuthority.getInstance(ASN1Sequence.getInstance(aSN1TaggedObject, bl));
    }

    private NamingAuthority(ASN1Sequence aSN1Sequence) {
        ASN1Encodable aSN1Encodable;
        if (aSN1Sequence.size() > 3) {
            throw new IllegalArgumentException("Bad sequence size: " + aSN1Sequence.size());
        }
        Enumeration enumeration = aSN1Sequence.getObjects();
        if (enumeration.hasMoreElements()) {
            aSN1Encodable = (ASN1Encodable)enumeration.nextElement();
            if (aSN1Encodable instanceof ASN1ObjectIdentifier) {
                this.namingAuthorityId = (ASN1ObjectIdentifier)aSN1Encodable;
            } else if (aSN1Encodable instanceof DERIA5String) {
                this.namingAuthorityUrl = DERIA5String.getInstance(aSN1Encodable).getString();
            } else if (aSN1Encodable instanceof ASN1String) {
                this.namingAuthorityText = DirectoryString.getInstance(aSN1Encodable);
            } else {
                throw new IllegalArgumentException("Bad object encountered: " + aSN1Encodable.getClass());
            }
        }
        if (enumeration.hasMoreElements()) {
            aSN1Encodable = (ASN1Encodable)enumeration.nextElement();
            if (aSN1Encodable instanceof DERIA5String) {
                this.namingAuthorityUrl = DERIA5String.getInstance(aSN1Encodable).getString();
            } else if (aSN1Encodable instanceof ASN1String) {
                this.namingAuthorityText = DirectoryString.getInstance(aSN1Encodable);
            } else {
                throw new IllegalArgumentException("Bad object encountered: " + aSN1Encodable.getClass());
            }
        }
        if (enumeration.hasMoreElements()) {
            aSN1Encodable = (ASN1Encodable)enumeration.nextElement();
            if (aSN1Encodable instanceof ASN1String) {
                this.namingAuthorityText = DirectoryString.getInstance(aSN1Encodable);
            } else {
                throw new IllegalArgumentException("Bad object encountered: " + aSN1Encodable.getClass());
            }
        }
    }

    public ASN1ObjectIdentifier getNamingAuthorityId() {
        return this.namingAuthorityId;
    }

    public DirectoryString getNamingAuthorityText() {
        return this.namingAuthorityText;
    }

    public String getNamingAuthorityUrl() {
        return this.namingAuthorityUrl;
    }

    public NamingAuthority(ASN1ObjectIdentifier aSN1ObjectIdentifier, String string, DirectoryString directoryString) {
        this.namingAuthorityId = aSN1ObjectIdentifier;
        this.namingAuthorityUrl = string;
        this.namingAuthorityText = directoryString;
    }

    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        if (this.namingAuthorityId != null) {
            aSN1EncodableVector.add(this.namingAuthorityId);
        }
        if (this.namingAuthorityUrl != null) {
            aSN1EncodableVector.add(new DERIA5String(this.namingAuthorityUrl, true));
        }
        if (this.namingAuthorityText != null) {
            aSN1EncodableVector.add(this.namingAuthorityText);
        }
        return new DERSequence(aSN1EncodableVector);
    }
}

