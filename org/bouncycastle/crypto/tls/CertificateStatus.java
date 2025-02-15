/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.crypto.tls;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.bouncycastle.asn1.ocsp.OCSPResponse;
import org.bouncycastle.crypto.tls.TlsFatalAlert;
import org.bouncycastle.crypto.tls.TlsUtils;

public class CertificateStatus {
    protected short statusType;
    protected Object response;

    public CertificateStatus(short s, Object object) {
        if (!CertificateStatus.isCorrectType(s, object)) {
            throw new IllegalArgumentException("'response' is not an instance of the correct type");
        }
        this.statusType = s;
        this.response = object;
    }

    public short getStatusType() {
        return this.statusType;
    }

    public Object getResponse() {
        return this.response;
    }

    public OCSPResponse getOCSPResponse() {
        if (!CertificateStatus.isCorrectType((short)1, this.response)) {
            throw new IllegalStateException("'response' is not an OCSPResponse");
        }
        return (OCSPResponse)this.response;
    }

    public void encode(OutputStream outputStream) throws IOException {
        TlsUtils.writeUint8(this.statusType, outputStream);
        switch (this.statusType) {
            case 1: {
                byte[] byArray = ((OCSPResponse)this.response).getEncoded("DER");
                TlsUtils.writeOpaque24(byArray, outputStream);
                break;
            }
            default: {
                throw new TlsFatalAlert(80);
            }
        }
    }

    public static CertificateStatus parse(InputStream inputStream) throws IOException {
        OCSPResponse oCSPResponse;
        short s = TlsUtils.readUint8(inputStream);
        switch (s) {
            case 1: {
                byte[] byArray = TlsUtils.readOpaque24(inputStream);
                oCSPResponse = OCSPResponse.getInstance(TlsUtils.readDERObject(byArray));
                break;
            }
            default: {
                throw new TlsFatalAlert(50);
            }
        }
        return new CertificateStatus(s, oCSPResponse);
    }

    protected static boolean isCorrectType(short s, Object object) {
        switch (s) {
            case 1: {
                return object instanceof OCSPResponse;
            }
        }
        throw new IllegalArgumentException("'statusType' is an unsupported CertificateStatusType");
    }
}

