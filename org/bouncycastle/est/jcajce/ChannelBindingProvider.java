/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.est.jcajce;

import java.net.Socket;

public interface ChannelBindingProvider {
    public boolean canAccessChannelBinding(Socket var1);

    public byte[] getChannelBinding(Socket var1, String var2);
}

