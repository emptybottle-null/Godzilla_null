/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.crypto.tls;

import org.bouncycastle.crypto.tls.TlsPSKIdentity;
import org.bouncycastle.util.Arrays;
import org.bouncycastle.util.Strings;

public class BasicTlsPSKIdentity
implements TlsPSKIdentity {
    protected byte[] identity;
    protected byte[] psk;

    public BasicTlsPSKIdentity(byte[] byArray, byte[] byArray2) {
        this.identity = Arrays.clone(byArray);
        this.psk = Arrays.clone(byArray2);
    }

    public BasicTlsPSKIdentity(String string, byte[] byArray) {
        this.identity = Strings.toUTF8ByteArray(string);
        this.psk = Arrays.clone(byArray);
    }

    public void skipIdentityHint() {
    }

    public void notifyIdentityHint(byte[] byArray) {
    }

    public byte[] getPSKIdentity() {
        return this.identity;
    }

    public byte[] getPSK() {
        return this.psk;
    }
}

