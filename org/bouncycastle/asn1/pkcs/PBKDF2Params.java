/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.asn1.pkcs;

import java.math.BigInteger;
import java.util.Enumeration;
import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1Integer;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1OctetString;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.DERNull;
import org.bouncycastle.asn1.DEROctetString;
import org.bouncycastle.asn1.DERSequence;
import org.bouncycastle.asn1.pkcs.PKCSObjectIdentifiers;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.util.Arrays;

public class PBKDF2Params
extends ASN1Object {
    private static final AlgorithmIdentifier algid_hmacWithSHA1 = new AlgorithmIdentifier(PKCSObjectIdentifiers.id_hmacWithSHA1, DERNull.INSTANCE);
    private final ASN1OctetString octStr;
    private final ASN1Integer iterationCount;
    private final ASN1Integer keyLength;
    private final AlgorithmIdentifier prf;

    public static PBKDF2Params getInstance(Object object) {
        if (object instanceof PBKDF2Params) {
            return (PBKDF2Params)object;
        }
        if (object != null) {
            return new PBKDF2Params(ASN1Sequence.getInstance(object));
        }
        return null;
    }

    public PBKDF2Params(byte[] byArray, int n) {
        this(byArray, n, 0);
    }

    public PBKDF2Params(byte[] byArray, int n, int n2) {
        this(byArray, n, n2, null);
    }

    public PBKDF2Params(byte[] byArray, int n, int n2, AlgorithmIdentifier algorithmIdentifier) {
        this.octStr = new DEROctetString(Arrays.clone(byArray));
        this.iterationCount = new ASN1Integer(n);
        this.keyLength = n2 > 0 ? new ASN1Integer(n2) : null;
        this.prf = algorithmIdentifier;
    }

    public PBKDF2Params(byte[] byArray, int n, AlgorithmIdentifier algorithmIdentifier) {
        this(byArray, n, 0, algorithmIdentifier);
    }

    private PBKDF2Params(ASN1Sequence aSN1Sequence) {
        Enumeration enumeration = aSN1Sequence.getObjects();
        this.octStr = (ASN1OctetString)enumeration.nextElement();
        this.iterationCount = (ASN1Integer)enumeration.nextElement();
        if (enumeration.hasMoreElements()) {
            Object object = enumeration.nextElement();
            if (object instanceof ASN1Integer) {
                this.keyLength = ASN1Integer.getInstance(object);
                object = enumeration.hasMoreElements() ? enumeration.nextElement() : null;
            } else {
                this.keyLength = null;
            }
            this.prf = object != null ? AlgorithmIdentifier.getInstance(object) : null;
        } else {
            this.keyLength = null;
            this.prf = null;
        }
    }

    public byte[] getSalt() {
        return this.octStr.getOctets();
    }

    public BigInteger getIterationCount() {
        return this.iterationCount.getValue();
    }

    public BigInteger getKeyLength() {
        if (this.keyLength != null) {
            return this.keyLength.getValue();
        }
        return null;
    }

    public boolean isDefaultPrf() {
        return this.prf == null || this.prf.equals(algid_hmacWithSHA1);
    }

    public AlgorithmIdentifier getPrf() {
        if (this.prf != null) {
            return this.prf;
        }
        return algid_hmacWithSHA1;
    }

    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        aSN1EncodableVector.add(this.octStr);
        aSN1EncodableVector.add(this.iterationCount);
        if (this.keyLength != null) {
            aSN1EncodableVector.add(this.keyLength);
        }
        if (this.prf != null && !this.prf.equals(algid_hmacWithSHA1)) {
            aSN1EncodableVector.add(this.prf);
        }
        return new DERSequence(aSN1EncodableVector);
    }
}

