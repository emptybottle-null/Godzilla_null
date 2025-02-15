/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.crypto.tls;

import java.io.IOException;
import org.bouncycastle.crypto.params.AsymmetricKeyParameter;
import org.bouncycastle.crypto.tls.TlsCredentials;

public interface TlsAgreementCredentials
extends TlsCredentials {
    public byte[] generateAgreement(AsymmetricKeyParameter var1) throws IOException;
}

