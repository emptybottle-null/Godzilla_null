/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.crypto.tls;

public class AlertLevel {
    public static final short warning = 1;
    public static final short fatal = 2;

    public static String getName(short s) {
        switch (s) {
            case 1: {
                return "warning";
            }
            case 2: {
                return "fatal";
            }
        }
        return "UNKNOWN";
    }

    public static String getText(short s) {
        return AlertLevel.getName(s) + "(" + s + ")";
    }
}

