/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.eac.jcajce;

import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import org.bouncycastle.eac.jcajce.EACHelper;

class DefaultEACHelper
implements EACHelper {
    DefaultEACHelper() {
    }

    public KeyFactory createKeyFactory(String string) throws NoSuchAlgorithmException {
        return KeyFactory.getInstance(string);
    }
}

