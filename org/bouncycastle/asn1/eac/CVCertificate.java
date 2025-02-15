/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.asn1.eac;

import java.io.IOException;
import org.bouncycastle.asn1.ASN1ApplicationSpecific;
import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1InputStream;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.ASN1ParsingException;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.DERApplicationSpecific;
import org.bouncycastle.asn1.DEROctetString;
import org.bouncycastle.asn1.eac.CertificateBody;
import org.bouncycastle.asn1.eac.CertificateHolderAuthorization;
import org.bouncycastle.asn1.eac.CertificateHolderReference;
import org.bouncycastle.asn1.eac.CertificationAuthorityReference;
import org.bouncycastle.asn1.eac.Flags;
import org.bouncycastle.asn1.eac.PackedDate;
import org.bouncycastle.util.Arrays;

public class CVCertificate
extends ASN1Object {
    private CertificateBody certificateBody;
    private byte[] signature;
    private int valid;
    private static int bodyValid = 1;
    private static int signValid = 2;

    private void setPrivateData(ASN1ApplicationSpecific aSN1ApplicationSpecific) throws IOException {
        ASN1InputStream aSN1InputStream;
        this.valid = 0;
        if (aSN1ApplicationSpecific.getApplicationTag() == 33) {
            ASN1Primitive aSN1Primitive;
            aSN1InputStream = new ASN1InputStream(aSN1ApplicationSpecific.getContents());
            block4: while ((aSN1Primitive = aSN1InputStream.readObject()) != null) {
                if (aSN1Primitive instanceof DERApplicationSpecific) {
                    DERApplicationSpecific dERApplicationSpecific = (DERApplicationSpecific)aSN1Primitive;
                    switch (dERApplicationSpecific.getApplicationTag()) {
                        case 78: {
                            this.certificateBody = CertificateBody.getInstance(dERApplicationSpecific);
                            this.valid |= bodyValid;
                            continue block4;
                        }
                        case 55: {
                            this.signature = dERApplicationSpecific.getContents();
                            this.valid |= signValid;
                            continue block4;
                        }
                    }
                    throw new IOException("Invalid tag, not an Iso7816CertificateStructure :" + dERApplicationSpecific.getApplicationTag());
                }
                throw new IOException("Invalid Object, not an Iso7816CertificateStructure");
            }
        } else {
            throw new IOException("not a CARDHOLDER_CERTIFICATE :" + aSN1ApplicationSpecific.getApplicationTag());
        }
        aSN1InputStream.close();
        if (this.valid != (signValid | bodyValid)) {
            throw new IOException("invalid CARDHOLDER_CERTIFICATE :" + aSN1ApplicationSpecific.getApplicationTag());
        }
    }

    public CVCertificate(ASN1InputStream aSN1InputStream) throws IOException {
        this.initFrom(aSN1InputStream);
    }

    private void initFrom(ASN1InputStream aSN1InputStream) throws IOException {
        ASN1Primitive aSN1Primitive;
        while ((aSN1Primitive = aSN1InputStream.readObject()) != null) {
            if (aSN1Primitive instanceof DERApplicationSpecific) {
                this.setPrivateData((DERApplicationSpecific)aSN1Primitive);
                continue;
            }
            throw new IOException("Invalid Input Stream for creating an Iso7816CertificateStructure");
        }
    }

    private CVCertificate(ASN1ApplicationSpecific aSN1ApplicationSpecific) throws IOException {
        this.setPrivateData(aSN1ApplicationSpecific);
    }

    public CVCertificate(CertificateBody certificateBody, byte[] byArray) throws IOException {
        this.certificateBody = certificateBody;
        this.signature = Arrays.clone(byArray);
        this.valid |= bodyValid;
        this.valid |= signValid;
    }

    public static CVCertificate getInstance(Object object) {
        if (object instanceof CVCertificate) {
            return (CVCertificate)object;
        }
        if (object != null) {
            try {
                return new CVCertificate(DERApplicationSpecific.getInstance(object));
            } catch (IOException iOException) {
                throw new ASN1ParsingException("unable to parse data: " + iOException.getMessage(), iOException);
            }
        }
        return null;
    }

    public byte[] getSignature() {
        return Arrays.clone(this.signature);
    }

    public CertificateBody getBody() {
        return this.certificateBody;
    }

    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        aSN1EncodableVector.add(this.certificateBody);
        try {
            aSN1EncodableVector.add(new DERApplicationSpecific(false, 55, new DEROctetString(this.signature)));
        } catch (IOException iOException) {
            throw new IllegalStateException("unable to convert signature!");
        }
        return new DERApplicationSpecific(33, aSN1EncodableVector);
    }

    public ASN1ObjectIdentifier getHolderAuthorization() throws IOException {
        CertificateHolderAuthorization certificateHolderAuthorization = this.certificateBody.getCertificateHolderAuthorization();
        return certificateHolderAuthorization.getOid();
    }

    public PackedDate getEffectiveDate() throws IOException {
        return this.certificateBody.getCertificateEffectiveDate();
    }

    public int getCertificateType() {
        return this.certificateBody.getCertificateType();
    }

    public PackedDate getExpirationDate() throws IOException {
        return this.certificateBody.getCertificateExpirationDate();
    }

    public int getRole() throws IOException {
        CertificateHolderAuthorization certificateHolderAuthorization = this.certificateBody.getCertificateHolderAuthorization();
        return certificateHolderAuthorization.getAccessRights();
    }

    public CertificationAuthorityReference getAuthorityReference() throws IOException {
        return this.certificateBody.getCertificationAuthorityReference();
    }

    public CertificateHolderReference getHolderReference() throws IOException {
        return this.certificateBody.getCertificateHolderReference();
    }

    public int getHolderAuthorizationRole() throws IOException {
        int n = this.certificateBody.getCertificateHolderAuthorization().getAccessRights();
        return n & 0xC0;
    }

    public Flags getHolderAuthorizationRights() throws IOException {
        return new Flags(this.certificateBody.getCertificateHolderAuthorization().getAccessRights() & 0x1F);
    }
}

