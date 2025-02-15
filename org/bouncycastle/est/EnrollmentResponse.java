/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.est;

import org.bouncycastle.cert.X509CertificateHolder;
import org.bouncycastle.est.ESTRequest;
import org.bouncycastle.est.Source;
import org.bouncycastle.util.Store;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class EnrollmentResponse {
    private final Store<X509CertificateHolder> store;
    private final long notBefore;
    private final ESTRequest requestToRetry;
    private final Source source;

    public EnrollmentResponse(Store<X509CertificateHolder> store, long l, ESTRequest eSTRequest, Source source) {
        this.store = store;
        this.notBefore = l;
        this.requestToRetry = eSTRequest;
        this.source = source;
    }

    public boolean canRetry() {
        return this.notBefore < System.currentTimeMillis();
    }

    public Store<X509CertificateHolder> getStore() {
        return this.store;
    }

    public long getNotBefore() {
        return this.notBefore;
    }

    public ESTRequest getRequestToRetry() {
        return this.requestToRetry;
    }

    public Object getSession() {
        return this.source.getSession();
    }

    public Source getSource() {
        return this.source;
    }

    public boolean isCompleted() {
        return this.requestToRetry == null;
    }
}

