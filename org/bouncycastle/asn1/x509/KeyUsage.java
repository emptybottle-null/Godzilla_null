/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.asn1.x509;

import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.DERBitString;
import org.bouncycastle.asn1.x509.Extension;
import org.bouncycastle.asn1.x509.Extensions;

public class KeyUsage
extends ASN1Object {
    public static final int digitalSignature = 128;
    public static final int nonRepudiation = 64;
    public static final int keyEncipherment = 32;
    public static final int dataEncipherment = 16;
    public static final int keyAgreement = 8;
    public static final int keyCertSign = 4;
    public static final int cRLSign = 2;
    public static final int encipherOnly = 1;
    public static final int decipherOnly = 32768;
    private DERBitString bitString;

    public static KeyUsage getInstance(Object object) {
        if (object instanceof KeyUsage) {
            return (KeyUsage)object;
        }
        if (object != null) {
            return new KeyUsage(DERBitString.getInstance(object));
        }
        return null;
    }

    public static KeyUsage fromExtensions(Extensions extensions) {
        return KeyUsage.getInstance(extensions.getExtensionParsedValue(Extension.keyUsage));
    }

    public KeyUsage(int n) {
        this.bitString = new DERBitString(n);
    }

    private KeyUsage(DERBitString dERBitString) {
        this.bitString = dERBitString;
    }

    public boolean hasUsages(int n) {
        return (this.bitString.intValue() & n) == n;
    }

    public byte[] getBytes() {
        return this.bitString.getBytes();
    }

    public int getPadBits() {
        return this.bitString.getPadBits();
    }

    public String toString() {
        byte[] byArray = this.bitString.getBytes();
        if (byArray.length == 1) {
            return "KeyUsage: 0x" + Integer.toHexString(byArray[0] & 0xFF);
        }
        return "KeyUsage: 0x" + Integer.toHexString((byArray[1] & 0xFF) << 8 | byArray[0] & 0xFF);
    }

    public ASN1Primitive toASN1Primitive() {
        return this.bitString;
    }
}

