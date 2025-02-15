/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.asn1.eac;

import java.io.IOException;
import java.util.Enumeration;
import org.bouncycastle.asn1.ASN1ApplicationSpecific;
import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1ParsingException;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.DERApplicationSpecific;
import org.bouncycastle.asn1.DEROctetString;
import org.bouncycastle.asn1.eac.CertificateBody;
import org.bouncycastle.asn1.eac.PublicKeyDataObject;
import org.bouncycastle.util.Arrays;

public class CVCertificateRequest
extends ASN1Object {
    private final ASN1ApplicationSpecific original;
    private CertificateBody certificateBody;
    private byte[] innerSignature = null;
    private byte[] outerSignature = null;
    private static final int bodyValid = 1;
    private static final int signValid = 2;

    private CVCertificateRequest(ASN1ApplicationSpecific aSN1ApplicationSpecific) throws IOException {
        this.original = aSN1ApplicationSpecific;
        if (aSN1ApplicationSpecific.isConstructed() && aSN1ApplicationSpecific.getApplicationTag() == 7) {
            ASN1Sequence aSN1Sequence = ASN1Sequence.getInstance(aSN1ApplicationSpecific.getObject(16));
            this.initCertBody(ASN1ApplicationSpecific.getInstance(aSN1Sequence.getObjectAt(0)));
            this.outerSignature = ASN1ApplicationSpecific.getInstance(aSN1Sequence.getObjectAt(aSN1Sequence.size() - 1)).getContents();
        } else {
            this.initCertBody(aSN1ApplicationSpecific);
        }
    }

    private void initCertBody(ASN1ApplicationSpecific aSN1ApplicationSpecific) throws IOException {
        if (aSN1ApplicationSpecific.getApplicationTag() == 33) {
            int n = 0;
            ASN1Sequence aSN1Sequence = ASN1Sequence.getInstance(aSN1ApplicationSpecific.getObject(16));
            Enumeration enumeration = aSN1Sequence.getObjects();
            block4: while (enumeration.hasMoreElements()) {
                ASN1ApplicationSpecific aSN1ApplicationSpecific2 = ASN1ApplicationSpecific.getInstance(enumeration.nextElement());
                switch (aSN1ApplicationSpecific2.getApplicationTag()) {
                    case 78: {
                        this.certificateBody = CertificateBody.getInstance(aSN1ApplicationSpecific2);
                        n |= 1;
                        continue block4;
                    }
                    case 55: {
                        this.innerSignature = aSN1ApplicationSpecific2.getContents();
                        n |= 2;
                        continue block4;
                    }
                }
                throw new IOException("Invalid tag, not an CV Certificate Request element:" + aSN1ApplicationSpecific2.getApplicationTag());
            }
            if ((n & 3) == 0) {
                throw new IOException("Invalid CARDHOLDER_CERTIFICATE in request:" + aSN1ApplicationSpecific.getApplicationTag());
            }
        } else {
            throw new IOException("not a CARDHOLDER_CERTIFICATE in request:" + aSN1ApplicationSpecific.getApplicationTag());
        }
    }

    public static CVCertificateRequest getInstance(Object object) {
        if (object instanceof CVCertificateRequest) {
            return (CVCertificateRequest)object;
        }
        if (object != null) {
            try {
                return new CVCertificateRequest(ASN1ApplicationSpecific.getInstance(object));
            } catch (IOException iOException) {
                throw new ASN1ParsingException("unable to parse data: " + iOException.getMessage(), iOException);
            }
        }
        return null;
    }

    public CertificateBody getCertificateBody() {
        return this.certificateBody;
    }

    public PublicKeyDataObject getPublicKey() {
        return this.certificateBody.getPublicKey();
    }

    public byte[] getInnerSignature() {
        return Arrays.clone(this.innerSignature);
    }

    public byte[] getOuterSignature() {
        return Arrays.clone(this.outerSignature);
    }

    public boolean hasOuterSignature() {
        return this.outerSignature != null;
    }

    public ASN1Primitive toASN1Primitive() {
        if (this.original != null) {
            return this.original;
        }
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        aSN1EncodableVector.add(this.certificateBody);
        try {
            aSN1EncodableVector.add(new DERApplicationSpecific(false, 55, new DEROctetString(this.innerSignature)));
        } catch (IOException iOException) {
            throw new IllegalStateException("unable to convert signature!");
        }
        return new DERApplicationSpecific(33, aSN1EncodableVector);
    }
}

