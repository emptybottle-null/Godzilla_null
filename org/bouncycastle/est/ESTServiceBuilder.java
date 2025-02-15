/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.est;

import org.bouncycastle.est.ESTClientProvider;
import org.bouncycastle.est.ESTService;

public class ESTServiceBuilder {
    protected final String server;
    protected ESTClientProvider clientProvider;
    protected String label;

    public ESTServiceBuilder(String string) {
        this.server = string;
    }

    public ESTServiceBuilder withLabel(String string) {
        this.label = string;
        return this;
    }

    public ESTServiceBuilder withClientProvider(ESTClientProvider eSTClientProvider) {
        this.clientProvider = eSTClientProvider;
        return this;
    }

    public ESTService build() {
        return new ESTService(this.server, this.label, this.clientProvider);
    }
}

