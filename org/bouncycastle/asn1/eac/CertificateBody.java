/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.asn1.eac;

import java.io.IOException;
import org.bouncycastle.asn1.ASN1ApplicationSpecific;
import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1InputStream;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.DERApplicationSpecific;
import org.bouncycastle.asn1.DEROctetString;
import org.bouncycastle.asn1.eac.CertificateHolderAuthorization;
import org.bouncycastle.asn1.eac.CertificateHolderReference;
import org.bouncycastle.asn1.eac.CertificationAuthorityReference;
import org.bouncycastle.asn1.eac.EACTags;
import org.bouncycastle.asn1.eac.PackedDate;
import org.bouncycastle.asn1.eac.PublicKeyDataObject;

public class CertificateBody
extends ASN1Object {
    ASN1InputStream seq;
    private DERApplicationSpecific certificateProfileIdentifier;
    private DERApplicationSpecific certificationAuthorityReference;
    private PublicKeyDataObject publicKey;
    private DERApplicationSpecific certificateHolderReference;
    private CertificateHolderAuthorization certificateHolderAuthorization;
    private DERApplicationSpecific certificateEffectiveDate;
    private DERApplicationSpecific certificateExpirationDate;
    private int certificateType = 0;
    private static final int CPI = 1;
    private static final int CAR = 2;
    private static final int PK = 4;
    private static final int CHR = 8;
    private static final int CHA = 16;
    private static final int CEfD = 32;
    private static final int CExD = 64;
    public static final int profileType = 127;
    public static final int requestType = 13;

    private void setIso7816CertificateBody(ASN1ApplicationSpecific aSN1ApplicationSpecific) throws IOException {
        ASN1Primitive aSN1Primitive;
        if (aSN1ApplicationSpecific.getApplicationTag() != 78) {
            throw new IOException("Bad tag : not an iso7816 CERTIFICATE_CONTENT_TEMPLATE");
        }
        byte[] byArray = aSN1ApplicationSpecific.getContents();
        ASN1InputStream aSN1InputStream = new ASN1InputStream(byArray);
        block9: while ((aSN1Primitive = aSN1InputStream.readObject()) != null) {
            if (!(aSN1Primitive instanceof DERApplicationSpecific)) {
                throw new IOException("Not a valid iso7816 content : not a DERApplicationSpecific Object :" + EACTags.encodeTag(aSN1ApplicationSpecific) + aSN1Primitive.getClass());
            }
            DERApplicationSpecific dERApplicationSpecific = (DERApplicationSpecific)aSN1Primitive;
            switch (dERApplicationSpecific.getApplicationTag()) {
                case 41: {
                    this.setCertificateProfileIdentifier(dERApplicationSpecific);
                    continue block9;
                }
                case 2: {
                    this.setCertificationAuthorityReference(dERApplicationSpecific);
                    continue block9;
                }
                case 73: {
                    this.setPublicKey(PublicKeyDataObject.getInstance(dERApplicationSpecific.getObject(16)));
                    continue block9;
                }
                case 32: {
                    this.setCertificateHolderReference(dERApplicationSpecific);
                    continue block9;
                }
                case 76: {
                    this.setCertificateHolderAuthorization(new CertificateHolderAuthorization(dERApplicationSpecific));
                    continue block9;
                }
                case 37: {
                    this.setCertificateEffectiveDate(dERApplicationSpecific);
                    continue block9;
                }
                case 36: {
                    this.setCertificateExpirationDate(dERApplicationSpecific);
                    continue block9;
                }
            }
            this.certificateType = 0;
            throw new IOException("Not a valid iso7816 DERApplicationSpecific tag " + dERApplicationSpecific.getApplicationTag());
        }
        aSN1InputStream.close();
    }

    public CertificateBody(DERApplicationSpecific dERApplicationSpecific, CertificationAuthorityReference certificationAuthorityReference, PublicKeyDataObject publicKeyDataObject, CertificateHolderReference certificateHolderReference, CertificateHolderAuthorization certificateHolderAuthorization, PackedDate packedDate, PackedDate packedDate2) {
        this.setCertificateProfileIdentifier(dERApplicationSpecific);
        this.setCertificationAuthorityReference(new DERApplicationSpecific(2, certificationAuthorityReference.getEncoded()));
        this.setPublicKey(publicKeyDataObject);
        this.setCertificateHolderReference(new DERApplicationSpecific(32, certificateHolderReference.getEncoded()));
        this.setCertificateHolderAuthorization(certificateHolderAuthorization);
        try {
            this.setCertificateEffectiveDate(new DERApplicationSpecific(false, 37, new DEROctetString(packedDate.getEncoding())));
            this.setCertificateExpirationDate(new DERApplicationSpecific(false, 36, new DEROctetString(packedDate2.getEncoding())));
        } catch (IOException iOException) {
            throw new IllegalArgumentException("unable to encode dates: " + iOException.getMessage());
        }
    }

    private CertificateBody(ASN1ApplicationSpecific aSN1ApplicationSpecific) throws IOException {
        this.setIso7816CertificateBody(aSN1ApplicationSpecific);
    }

    private ASN1Primitive profileToASN1Object() throws IOException {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        aSN1EncodableVector.add(this.certificateProfileIdentifier);
        aSN1EncodableVector.add(this.certificationAuthorityReference);
        aSN1EncodableVector.add(new DERApplicationSpecific(false, 73, this.publicKey));
        aSN1EncodableVector.add(this.certificateHolderReference);
        aSN1EncodableVector.add(this.certificateHolderAuthorization);
        aSN1EncodableVector.add(this.certificateEffectiveDate);
        aSN1EncodableVector.add(this.certificateExpirationDate);
        return new DERApplicationSpecific(78, aSN1EncodableVector);
    }

    private void setCertificateProfileIdentifier(DERApplicationSpecific dERApplicationSpecific) throws IllegalArgumentException {
        if (dERApplicationSpecific.getApplicationTag() == 41) {
            this.certificateProfileIdentifier = dERApplicationSpecific;
            this.certificateType |= 1;
        } else {
            throw new IllegalArgumentException("Not an Iso7816Tags.INTERCHANGE_PROFILE tag :" + EACTags.encodeTag(dERApplicationSpecific));
        }
    }

    private void setCertificateHolderReference(DERApplicationSpecific dERApplicationSpecific) throws IllegalArgumentException {
        if (dERApplicationSpecific.getApplicationTag() == 32) {
            this.certificateHolderReference = dERApplicationSpecific;
            this.certificateType |= 8;
        } else {
            throw new IllegalArgumentException("Not an Iso7816Tags.CARDHOLDER_NAME tag");
        }
    }

    private void setCertificationAuthorityReference(DERApplicationSpecific dERApplicationSpecific) throws IllegalArgumentException {
        if (dERApplicationSpecific.getApplicationTag() == 2) {
            this.certificationAuthorityReference = dERApplicationSpecific;
            this.certificateType |= 2;
        } else {
            throw new IllegalArgumentException("Not an Iso7816Tags.ISSUER_IDENTIFICATION_NUMBER tag");
        }
    }

    private void setPublicKey(PublicKeyDataObject publicKeyDataObject) {
        this.publicKey = PublicKeyDataObject.getInstance(publicKeyDataObject);
        this.certificateType |= 4;
    }

    private ASN1Primitive requestToASN1Object() throws IOException {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        aSN1EncodableVector.add(this.certificateProfileIdentifier);
        aSN1EncodableVector.add(new DERApplicationSpecific(false, 73, this.publicKey));
        aSN1EncodableVector.add(this.certificateHolderReference);
        return new DERApplicationSpecific(78, aSN1EncodableVector);
    }

    public ASN1Primitive toASN1Primitive() {
        try {
            if (this.certificateType == 127) {
                return this.profileToASN1Object();
            }
            if (this.certificateType == 13) {
                return this.requestToASN1Object();
            }
        } catch (IOException iOException) {
            return null;
        }
        return null;
    }

    public int getCertificateType() {
        return this.certificateType;
    }

    public static CertificateBody getInstance(Object object) throws IOException {
        if (object instanceof CertificateBody) {
            return (CertificateBody)object;
        }
        if (object != null) {
            return new CertificateBody(ASN1ApplicationSpecific.getInstance(object));
        }
        return null;
    }

    public PackedDate getCertificateEffectiveDate() {
        if ((this.certificateType & 0x20) == 32) {
            return new PackedDate(this.certificateEffectiveDate.getContents());
        }
        return null;
    }

    private void setCertificateEffectiveDate(DERApplicationSpecific dERApplicationSpecific) throws IllegalArgumentException {
        if (dERApplicationSpecific.getApplicationTag() == 37) {
            this.certificateEffectiveDate = dERApplicationSpecific;
            this.certificateType |= 0x20;
        } else {
            throw new IllegalArgumentException("Not an Iso7816Tags.APPLICATION_EFFECTIVE_DATE tag :" + EACTags.encodeTag(dERApplicationSpecific));
        }
    }

    public PackedDate getCertificateExpirationDate() throws IOException {
        if ((this.certificateType & 0x40) == 64) {
            return new PackedDate(this.certificateExpirationDate.getContents());
        }
        throw new IOException("certificate Expiration Date not set");
    }

    private void setCertificateExpirationDate(DERApplicationSpecific dERApplicationSpecific) throws IllegalArgumentException {
        if (dERApplicationSpecific.getApplicationTag() == 36) {
            this.certificateExpirationDate = dERApplicationSpecific;
            this.certificateType |= 0x40;
        } else {
            throw new IllegalArgumentException("Not an Iso7816Tags.APPLICATION_EXPIRATION_DATE tag");
        }
    }

    public CertificateHolderAuthorization getCertificateHolderAuthorization() throws IOException {
        if ((this.certificateType & 0x10) == 16) {
            return this.certificateHolderAuthorization;
        }
        throw new IOException("Certificate Holder Authorisation not set");
    }

    private void setCertificateHolderAuthorization(CertificateHolderAuthorization certificateHolderAuthorization) {
        this.certificateHolderAuthorization = certificateHolderAuthorization;
        this.certificateType |= 0x10;
    }

    public CertificateHolderReference getCertificateHolderReference() {
        return new CertificateHolderReference(this.certificateHolderReference.getContents());
    }

    public DERApplicationSpecific getCertificateProfileIdentifier() {
        return this.certificateProfileIdentifier;
    }

    public CertificationAuthorityReference getCertificationAuthorityReference() throws IOException {
        if ((this.certificateType & 2) == 2) {
            return new CertificationAuthorityReference(this.certificationAuthorityReference.getContents());
        }
        throw new IOException("Certification authority reference not set");
    }

    public PublicKeyDataObject getPublicKey() {
        return this.publicKey;
    }
}

