/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.util;

import org.bouncycastle.crypto.digests.SHA512tDigest;
import org.bouncycastle.util.Arrays;

public class Fingerprint {
    private static char[] encodingTable = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
    private final byte[] fingerprint;

    public Fingerprint(byte[] byArray) {
        this.fingerprint = Fingerprint.calculateFingerprint(byArray);
    }

    public byte[] getFingerprint() {
        return Arrays.clone(this.fingerprint);
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i != this.fingerprint.length; ++i) {
            if (i > 0) {
                stringBuffer.append(":");
            }
            stringBuffer.append(encodingTable[this.fingerprint[i] >>> 4 & 0xF]);
            stringBuffer.append(encodingTable[this.fingerprint[i] & 0xF]);
        }
        return stringBuffer.toString();
    }

    public boolean equals(Object object) {
        if (object == this) {
            return true;
        }
        if (object instanceof Fingerprint) {
            return Arrays.areEqual(((Fingerprint)object).fingerprint, this.fingerprint);
        }
        return false;
    }

    public int hashCode() {
        return Arrays.hashCode(this.fingerprint);
    }

    public static byte[] calculateFingerprint(byte[] byArray) {
        SHA512tDigest sHA512tDigest = new SHA512tDigest(160);
        sHA512tDigest.update(byArray, 0, byArray.length);
        byte[] byArray2 = new byte[sHA512tDigest.getDigestSize()];
        sHA512tDigest.doFinal(byArray2, 0);
        return byArray2;
    }
}

