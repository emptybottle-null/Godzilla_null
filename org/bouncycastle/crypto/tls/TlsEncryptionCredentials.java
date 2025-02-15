/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.crypto.tls;

import java.io.IOException;
import org.bouncycastle.crypto.tls.TlsCredentials;

public interface TlsEncryptionCredentials
extends TlsCredentials {
    public byte[] decryptPreMasterSecret(byte[] var1) throws IOException;
}

