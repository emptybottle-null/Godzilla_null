/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.asn1.esf;

import java.util.Enumeration;
import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.ASN1TaggedObject;
import org.bouncycastle.asn1.DERSequence;
import org.bouncycastle.asn1.DERTaggedObject;
import org.bouncycastle.asn1.DERUTF8String;
import org.bouncycastle.asn1.x500.DirectoryString;

public class SignerLocation
extends ASN1Object {
    private DirectoryString countryName;
    private DirectoryString localityName;
    private ASN1Sequence postalAddress;

    private SignerLocation(ASN1Sequence aSN1Sequence) {
        Enumeration enumeration = aSN1Sequence.getObjects();
        block5: while (enumeration.hasMoreElements()) {
            ASN1TaggedObject aSN1TaggedObject = (ASN1TaggedObject)enumeration.nextElement();
            switch (aSN1TaggedObject.getTagNo()) {
                case 0: {
                    this.countryName = DirectoryString.getInstance(aSN1TaggedObject, true);
                    continue block5;
                }
                case 1: {
                    this.localityName = DirectoryString.getInstance(aSN1TaggedObject, true);
                    continue block5;
                }
                case 2: {
                    this.postalAddress = aSN1TaggedObject.isExplicit() ? ASN1Sequence.getInstance(aSN1TaggedObject, true) : ASN1Sequence.getInstance(aSN1TaggedObject, false);
                    if (this.postalAddress == null || this.postalAddress.size() <= 6) continue block5;
                    throw new IllegalArgumentException("postal address must contain less than 6 strings");
                }
            }
            throw new IllegalArgumentException("illegal tag");
        }
    }

    private SignerLocation(DirectoryString directoryString, DirectoryString directoryString2, ASN1Sequence aSN1Sequence) {
        if (aSN1Sequence != null && aSN1Sequence.size() > 6) {
            throw new IllegalArgumentException("postal address must contain less than 6 strings");
        }
        this.countryName = directoryString;
        this.localityName = directoryString2;
        this.postalAddress = aSN1Sequence;
    }

    public SignerLocation(DirectoryString directoryString, DirectoryString directoryString2, DirectoryString[] directoryStringArray) {
        this(directoryString, directoryString2, (ASN1Sequence)new DERSequence(directoryStringArray));
    }

    public SignerLocation(DERUTF8String dERUTF8String, DERUTF8String dERUTF8String2, ASN1Sequence aSN1Sequence) {
        this(DirectoryString.getInstance(dERUTF8String), DirectoryString.getInstance(dERUTF8String2), aSN1Sequence);
    }

    public static SignerLocation getInstance(Object object) {
        if (object == null || object instanceof SignerLocation) {
            return (SignerLocation)object;
        }
        return new SignerLocation(ASN1Sequence.getInstance(object));
    }

    public DirectoryString getCountry() {
        return this.countryName;
    }

    public DirectoryString getLocality() {
        return this.localityName;
    }

    public DirectoryString[] getPostal() {
        if (this.postalAddress == null) {
            return null;
        }
        DirectoryString[] directoryStringArray = new DirectoryString[this.postalAddress.size()];
        for (int i = 0; i != directoryStringArray.length; ++i) {
            directoryStringArray[i] = DirectoryString.getInstance(this.postalAddress.getObjectAt(i));
        }
        return directoryStringArray;
    }

    public DERUTF8String getCountryName() {
        if (this.countryName == null) {
            return null;
        }
        return new DERUTF8String(this.getCountry().getString());
    }

    public DERUTF8String getLocalityName() {
        if (this.localityName == null) {
            return null;
        }
        return new DERUTF8String(this.getLocality().getString());
    }

    public ASN1Sequence getPostalAddress() {
        return this.postalAddress;
    }

    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        if (this.countryName != null) {
            aSN1EncodableVector.add(new DERTaggedObject(true, 0, this.countryName));
        }
        if (this.localityName != null) {
            aSN1EncodableVector.add(new DERTaggedObject(true, 1, this.localityName));
        }
        if (this.postalAddress != null) {
            aSN1EncodableVector.add(new DERTaggedObject(true, 2, this.postalAddress));
        }
        return new DERSequence(aSN1EncodableVector);
    }
}

