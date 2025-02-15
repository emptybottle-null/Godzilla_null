/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.crypto.tls;

public class CertChainType {
    public static final short individual_certs = 0;
    public static final short pkipath = 1;

    public static boolean isValid(short s) {
        return s >= 0 && s <= 1;
    }
}

