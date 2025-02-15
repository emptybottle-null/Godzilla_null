/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.eac.jcajce;

import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;

interface EACHelper {
    public KeyFactory createKeyFactory(String var1) throws NoSuchProviderException, NoSuchAlgorithmException;
}

