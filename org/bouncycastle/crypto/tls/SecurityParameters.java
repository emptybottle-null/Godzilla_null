/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.crypto.tls;

import org.bouncycastle.util.Arrays;

public class SecurityParameters {
    int entity = -1;
    int cipherSuite = -1;
    short compressionAlgorithm = 0;
    int prfAlgorithm = -1;
    int verifyDataLength = -1;
    byte[] masterSecret = null;
    byte[] clientRandom = null;
    byte[] serverRandom = null;
    byte[] sessionHash = null;
    byte[] pskIdentity = null;
    byte[] srpIdentity = null;
    short maxFragmentLength = (short)-1;
    boolean truncatedHMac = false;
    boolean encryptThenMAC = false;
    boolean extendedMasterSecret = false;

    void clear() {
        if (this.masterSecret != null) {
            Arrays.fill(this.masterSecret, (byte)0);
            this.masterSecret = null;
        }
    }

    public int getEntity() {
        return this.entity;
    }

    public int getCipherSuite() {
        return this.cipherSuite;
    }

    public short getCompressionAlgorithm() {
        return this.compressionAlgorithm;
    }

    public int getPrfAlgorithm() {
        return this.prfAlgorithm;
    }

    public int getVerifyDataLength() {
        return this.verifyDataLength;
    }

    public byte[] getMasterSecret() {
        return this.masterSecret;
    }

    public byte[] getClientRandom() {
        return this.clientRandom;
    }

    public byte[] getServerRandom() {
        return this.serverRandom;
    }

    public byte[] getSessionHash() {
        return this.sessionHash;
    }

    public byte[] getPskIdentity() {
        return this.pskIdentity;
    }

    public byte[] getPSKIdentity() {
        return this.pskIdentity;
    }

    public byte[] getSRPIdentity() {
        return this.srpIdentity;
    }
}

