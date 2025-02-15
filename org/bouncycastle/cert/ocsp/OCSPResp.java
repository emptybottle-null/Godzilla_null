/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.cert.ocsp;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import org.bouncycastle.asn1.ASN1Exception;
import org.bouncycastle.asn1.ASN1InputStream;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ocsp.BasicOCSPResponse;
import org.bouncycastle.asn1.ocsp.OCSPObjectIdentifiers;
import org.bouncycastle.asn1.ocsp.OCSPResponse;
import org.bouncycastle.asn1.ocsp.ResponseBytes;
import org.bouncycastle.cert.CertIOException;
import org.bouncycastle.cert.ocsp.BasicOCSPResp;
import org.bouncycastle.cert.ocsp.OCSPException;

public class OCSPResp {
    public static final int SUCCESSFUL = 0;
    public static final int MALFORMED_REQUEST = 1;
    public static final int INTERNAL_ERROR = 2;
    public static final int TRY_LATER = 3;
    public static final int SIG_REQUIRED = 5;
    public static final int UNAUTHORIZED = 6;
    private OCSPResponse resp;

    public OCSPResp(OCSPResponse oCSPResponse) {
        this.resp = oCSPResponse;
    }

    public OCSPResp(byte[] byArray) throws IOException {
        this(new ByteArrayInputStream(byArray));
    }

    public OCSPResp(InputStream inputStream) throws IOException {
        this(new ASN1InputStream(inputStream));
    }

    private OCSPResp(ASN1InputStream aSN1InputStream) throws IOException {
        try {
            this.resp = OCSPResponse.getInstance(aSN1InputStream.readObject());
        } catch (IllegalArgumentException illegalArgumentException) {
            throw new CertIOException("malformed response: " + illegalArgumentException.getMessage(), illegalArgumentException);
        } catch (ClassCastException classCastException) {
            throw new CertIOException("malformed response: " + classCastException.getMessage(), classCastException);
        } catch (ASN1Exception aSN1Exception) {
            throw new CertIOException("malformed response: " + aSN1Exception.getMessage(), aSN1Exception);
        }
        if (this.resp == null) {
            throw new CertIOException("malformed response: no response data found");
        }
    }

    public int getStatus() {
        return this.resp.getResponseStatus().getValue().intValue();
    }

    public Object getResponseObject() throws OCSPException {
        ResponseBytes responseBytes = this.resp.getResponseBytes();
        if (responseBytes == null) {
            return null;
        }
        if (responseBytes.getResponseType().equals(OCSPObjectIdentifiers.id_pkix_ocsp_basic)) {
            try {
                ASN1Primitive aSN1Primitive = ASN1Primitive.fromByteArray(responseBytes.getResponse().getOctets());
                return new BasicOCSPResp(BasicOCSPResponse.getInstance(aSN1Primitive));
            } catch (Exception exception) {
                throw new OCSPException("problem decoding object: " + exception, exception);
            }
        }
        return responseBytes.getResponse();
    }

    public byte[] getEncoded() throws IOException {
        return this.resp.getEncoded();
    }

    public boolean equals(Object object) {
        if (object == this) {
            return true;
        }
        if (!(object instanceof OCSPResp)) {
            return false;
        }
        OCSPResp oCSPResp = (OCSPResp)object;
        return this.resp.equals(oCSPResp.resp);
    }

    public int hashCode() {
        return this.resp.hashCode();
    }

    public OCSPResponse toASN1Structure() {
        return this.resp;
    }
}

