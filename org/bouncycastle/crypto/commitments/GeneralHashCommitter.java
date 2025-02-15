/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.crypto.commitments;

import java.security.SecureRandom;
import org.bouncycastle.crypto.Commitment;
import org.bouncycastle.crypto.Committer;
import org.bouncycastle.crypto.DataLengthException;
import org.bouncycastle.crypto.Digest;
import org.bouncycastle.crypto.ExtendedDigest;
import org.bouncycastle.util.Arrays;

public class GeneralHashCommitter
implements Committer {
    private final Digest digest;
    private final int byteLength;
    private final SecureRandom random;

    public GeneralHashCommitter(ExtendedDigest extendedDigest, SecureRandom secureRandom) {
        this.digest = extendedDigest;
        this.byteLength = extendedDigest.getByteLength();
        this.random = secureRandom;
    }

    public Commitment commit(byte[] byArray) {
        if (byArray.length > this.byteLength / 2) {
            throw new DataLengthException("Message to be committed to too large for digest.");
        }
        byte[] byArray2 = new byte[this.byteLength - byArray.length];
        this.random.nextBytes(byArray2);
        return new Commitment(byArray2, this.calculateCommitment(byArray2, byArray));
    }

    public boolean isRevealed(Commitment commitment, byte[] byArray) {
        if (byArray.length + commitment.getSecret().length != this.byteLength) {
            throw new DataLengthException("Message and witness secret lengths do not match.");
        }
        byte[] byArray2 = this.calculateCommitment(commitment.getSecret(), byArray);
        return Arrays.constantTimeAreEqual(commitment.getCommitment(), byArray2);
    }

    private byte[] calculateCommitment(byte[] byArray, byte[] byArray2) {
        byte[] byArray3 = new byte[this.digest.getDigestSize()];
        this.digest.update(byArray, 0, byArray.length);
        this.digest.update(byArray2, 0, byArray2.length);
        this.digest.update((byte)(byArray2.length >>> 8));
        this.digest.update((byte)byArray2.length);
        this.digest.doFinal(byArray3, 0);
        return byArray3;
    }
}

