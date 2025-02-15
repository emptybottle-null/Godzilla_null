/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.eac;

import java.io.IOException;
import java.io.OutputStream;
import org.bouncycastle.asn1.ASN1ParsingException;
import org.bouncycastle.asn1.eac.CVCertificateRequest;
import org.bouncycastle.asn1.eac.PublicKeyDataObject;
import org.bouncycastle.eac.EACException;
import org.bouncycastle.eac.EACIOException;
import org.bouncycastle.eac.operator.EACSignatureVerifier;

public class EACCertificateRequestHolder {
    private CVCertificateRequest request;

    private static CVCertificateRequest parseBytes(byte[] byArray) throws IOException {
        try {
            return CVCertificateRequest.getInstance(byArray);
        } catch (ClassCastException classCastException) {
            throw new EACIOException("malformed data: " + classCastException.getMessage(), classCastException);
        } catch (IllegalArgumentException illegalArgumentException) {
            throw new EACIOException("malformed data: " + illegalArgumentException.getMessage(), illegalArgumentException);
        } catch (ASN1ParsingException aSN1ParsingException) {
            if (aSN1ParsingException.getCause() instanceof IOException) {
                throw (IOException)aSN1ParsingException.getCause();
            }
            throw new EACIOException("malformed data: " + aSN1ParsingException.getMessage(), aSN1ParsingException);
        }
    }

    public EACCertificateRequestHolder(byte[] byArray) throws IOException {
        this(EACCertificateRequestHolder.parseBytes(byArray));
    }

    public EACCertificateRequestHolder(CVCertificateRequest cVCertificateRequest) {
        this.request = cVCertificateRequest;
    }

    public CVCertificateRequest toASN1Structure() {
        return this.request;
    }

    public PublicKeyDataObject getPublicKeyDataObject() {
        return this.request.getPublicKey();
    }

    public boolean isInnerSignatureValid(EACSignatureVerifier eACSignatureVerifier) throws EACException {
        try {
            OutputStream outputStream = eACSignatureVerifier.getOutputStream();
            outputStream.write(this.request.getCertificateBody().getEncoded("DER"));
            outputStream.close();
            return eACSignatureVerifier.verify(this.request.getInnerSignature());
        } catch (Exception exception) {
            throw new EACException("unable to process signature: " + exception.getMessage(), exception);
        }
    }
}

