/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.crypto.tls;

import org.bouncycastle.crypto.tls.AlertDescription;
import org.bouncycastle.crypto.tls.TlsException;

public class TlsFatalAlert
extends TlsException {
    protected short alertDescription;

    public TlsFatalAlert(short s) {
        this(s, null);
    }

    public TlsFatalAlert(short s, Throwable throwable) {
        super(AlertDescription.getText(s), throwable);
        this.alertDescription = s;
    }

    public short getAlertDescription() {
        return this.alertDescription;
    }
}

