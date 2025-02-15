/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.cert.crmf;

public interface EncryptedValuePadder {
    public byte[] getPaddedData(byte[] var1);

    public byte[] getUnpaddedData(byte[] var1);
}

