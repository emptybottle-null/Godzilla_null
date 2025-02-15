/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.asn1.esf;

import org.bouncycastle.asn1.ASN1Choice;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1OctetString;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.DEROctetString;
import org.bouncycastle.asn1.esf.OtherHashAlgAndValue;
import org.bouncycastle.asn1.oiw.OIWObjectIdentifiers;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;

public class OtherHash
extends ASN1Object
implements ASN1Choice {
    private ASN1OctetString sha1Hash;
    private OtherHashAlgAndValue otherHash;

    public static OtherHash getInstance(Object object) {
        if (object instanceof OtherHash) {
            return (OtherHash)object;
        }
        if (object instanceof ASN1OctetString) {
            return new OtherHash((ASN1OctetString)object);
        }
        return new OtherHash(OtherHashAlgAndValue.getInstance(object));
    }

    private OtherHash(ASN1OctetString aSN1OctetString) {
        this.sha1Hash = aSN1OctetString;
    }

    public OtherHash(OtherHashAlgAndValue otherHashAlgAndValue) {
        this.otherHash = otherHashAlgAndValue;
    }

    public OtherHash(byte[] byArray) {
        this.sha1Hash = new DEROctetString(byArray);
    }

    public AlgorithmIdentifier getHashAlgorithm() {
        if (null == this.otherHash) {
            return new AlgorithmIdentifier(OIWObjectIdentifiers.idSHA1);
        }
        return this.otherHash.getHashAlgorithm();
    }

    public byte[] getHashValue() {
        if (null == this.otherHash) {
            return this.sha1Hash.getOctets();
        }
        return this.otherHash.getHashValue().getOctets();
    }

    public ASN1Primitive toASN1Primitive() {
        if (null == this.otherHash) {
            return this.sha1Hash;
        }
        return this.otherHash.toASN1Primitive();
    }
}

