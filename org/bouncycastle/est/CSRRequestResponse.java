/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.est;

import org.bouncycastle.est.CSRAttributesResponse;
import org.bouncycastle.est.Source;

public class CSRRequestResponse {
    private final CSRAttributesResponse attributesResponse;
    private final Source source;

    public CSRRequestResponse(CSRAttributesResponse cSRAttributesResponse, Source source) {
        this.attributesResponse = cSRAttributesResponse;
        this.source = source;
    }

    public boolean hasAttributesResponse() {
        return this.attributesResponse != null;
    }

    public CSRAttributesResponse getAttributesResponse() {
        if (this.attributesResponse == null) {
            throw new IllegalStateException("Response has no CSRAttributesResponse.");
        }
        return this.attributesResponse;
    }

    public Object getSession() {
        return this.source.getSession();
    }

    public Source getSource() {
        return this.source;
    }
}

