/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.crypto.tls;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.bouncycastle.crypto.tls.OCSPStatusRequest;
import org.bouncycastle.crypto.tls.TlsFatalAlert;
import org.bouncycastle.crypto.tls.TlsUtils;

public class CertificateStatusRequest {
    protected short statusType;
    protected Object request;

    public CertificateStatusRequest(short s, Object object) {
        if (!CertificateStatusRequest.isCorrectType(s, object)) {
            throw new IllegalArgumentException("'request' is not an instance of the correct type");
        }
        this.statusType = s;
        this.request = object;
    }

    public short getStatusType() {
        return this.statusType;
    }

    public Object getRequest() {
        return this.request;
    }

    public OCSPStatusRequest getOCSPStatusRequest() {
        if (!CertificateStatusRequest.isCorrectType((short)1, this.request)) {
            throw new IllegalStateException("'request' is not an OCSPStatusRequest");
        }
        return (OCSPStatusRequest)this.request;
    }

    public void encode(OutputStream outputStream) throws IOException {
        TlsUtils.writeUint8(this.statusType, outputStream);
        switch (this.statusType) {
            case 1: {
                ((OCSPStatusRequest)this.request).encode(outputStream);
                break;
            }
            default: {
                throw new TlsFatalAlert(80);
            }
        }
    }

    public static CertificateStatusRequest parse(InputStream inputStream) throws IOException {
        OCSPStatusRequest oCSPStatusRequest;
        short s = TlsUtils.readUint8(inputStream);
        switch (s) {
            case 1: {
                oCSPStatusRequest = OCSPStatusRequest.parse(inputStream);
                break;
            }
            default: {
                throw new TlsFatalAlert(50);
            }
        }
        return new CertificateStatusRequest(s, oCSPStatusRequest);
    }

    protected static boolean isCorrectType(short s, Object object) {
        switch (s) {
            case 1: {
                return object instanceof OCSPStatusRequest;
            }
        }
        throw new IllegalArgumentException("'statusType' is an unsupported CertificateStatusType");
    }
}

