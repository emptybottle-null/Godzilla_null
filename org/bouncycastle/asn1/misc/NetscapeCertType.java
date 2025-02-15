/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.asn1.misc;

import org.bouncycastle.asn1.DERBitString;

public class NetscapeCertType
extends DERBitString {
    public static final int sslClient = 128;
    public static final int sslServer = 64;
    public static final int smime = 32;
    public static final int objectSigning = 16;
    public static final int reserved = 8;
    public static final int sslCA = 4;
    public static final int smimeCA = 2;
    public static final int objectSigningCA = 1;

    public NetscapeCertType(int n) {
        super(NetscapeCertType.getBytes(n), NetscapeCertType.getPadBits(n));
    }

    public NetscapeCertType(DERBitString dERBitString) {
        super(dERBitString.getBytes(), dERBitString.getPadBits());
    }

    public String toString() {
        return "NetscapeCertType: 0x" + Integer.toHexString(this.data[0] & 0xFF);
    }
}

