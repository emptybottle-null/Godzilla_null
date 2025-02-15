/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.jcajce.util;

import java.security.Provider;
import java.security.Security;
import org.bouncycastle.jcajce.util.ProviderJcaJceHelper;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

public class BCJcaJceHelper
extends ProviderJcaJceHelper {
    private static volatile Provider bcProvider;

    private static Provider getBouncyCastleProvider() {
        if (Security.getProvider("BC") != null) {
            return Security.getProvider("BC");
        }
        if (bcProvider != null) {
            return bcProvider;
        }
        bcProvider = new BouncyCastleProvider();
        return bcProvider;
    }

    public BCJcaJceHelper() {
        super(BCJcaJceHelper.getBouncyCastleProvider());
    }
}

