/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.cert.ocsp;

import org.bouncycastle.asn1.ocsp.Request;
import org.bouncycastle.asn1.x509.Extensions;
import org.bouncycastle.cert.ocsp.CertificateID;

public class Req {
    private Request req;

    public Req(Request request) {
        this.req = request;
    }

    public CertificateID getCertID() {
        return new CertificateID(this.req.getReqCert());
    }

    public Extensions getSingleRequestExtensions() {
        return this.req.getSingleRequestExtensions();
    }
}

