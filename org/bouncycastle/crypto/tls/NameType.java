/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.crypto.tls;

public class NameType {
    public static final short host_name = 0;

    public static boolean isValid(short s) {
        return s == 0;
    }
}

