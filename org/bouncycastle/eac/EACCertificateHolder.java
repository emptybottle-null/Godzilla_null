/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.eac;

import java.io.IOException;
import java.io.OutputStream;
import org.bouncycastle.asn1.ASN1ParsingException;
import org.bouncycastle.asn1.eac.CVCertificate;
import org.bouncycastle.asn1.eac.PublicKeyDataObject;
import org.bouncycastle.eac.EACException;
import org.bouncycastle.eac.EACIOException;
import org.bouncycastle.eac.operator.EACSignatureVerifier;

public class EACCertificateHolder {
    private CVCertificate cvCertificate;

    private static CVCertificate parseBytes(byte[] byArray) throws IOException {
        try {
            return CVCertificate.getInstance(byArray);
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

    public EACCertificateHolder(byte[] byArray) throws IOException {
        this(EACCertificateHolder.parseBytes(byArray));
    }

    public EACCertificateHolder(CVCertificate cVCertificate) {
        this.cvCertificate = cVCertificate;
    }

    public CVCertificate toASN1Structure() {
        return this.cvCertificate;
    }

    public PublicKeyDataObject getPublicKeyDataObject() {
        return this.cvCertificate.getBody().getPublicKey();
    }

    public boolean isSignatureValid(EACSignatureVerifier eACSignatureVerifier) throws EACException {
        try {
            OutputStream outputStream = eACSignatureVerifier.getOutputStream();
            outputStream.write(this.cvCertificate.getBody().getEncoded("DER"));
            outputStream.close();
            return eACSignatureVerifier.verify(this.cvCertificate.getSignature());
        } catch (Exception exception) {
            throw new EACException("unable to process signature: " + exception.getMessage(), exception);
        }
    }
}

