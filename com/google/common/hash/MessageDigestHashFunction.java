/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.google.common.hash;

import com.google.common.base.Preconditions;
import com.google.common.hash.AbstractByteHasher;
import com.google.common.hash.AbstractHashFunction;
import com.google.common.hash.HashCode;
import com.google.common.hash.Hasher;
import com.google.errorprone.annotations.Immutable;
import java.io.Serializable;
import java.nio.ByteBuffer;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

@Immutable
final class MessageDigestHashFunction
extends AbstractHashFunction
implements Serializable {
    private final MessageDigest prototype;
    private final int bytes;
    private final boolean supportsClone;
    private final String toString;

    MessageDigestHashFunction(String algorithmName, String toString) {
        this.prototype = MessageDigestHashFunction.getMessageDigest(algorithmName);
        this.bytes = this.prototype.getDigestLength();
        this.toString = Preconditions.checkNotNull(toString);
        this.supportsClone = MessageDigestHashFunction.supportsClone(this.prototype);
    }

    MessageDigestHashFunction(String algorithmName, int bytes, String toString) {
        this.toString = Preconditions.checkNotNull(toString);
        this.prototype = MessageDigestHashFunction.getMessageDigest(algorithmName);
        int maxLength = this.prototype.getDigestLength();
        Preconditions.checkArgument(bytes >= 4 && bytes <= maxLength, "bytes (%s) must be >= 4 and < %s", bytes, maxLength);
        this.bytes = bytes;
        this.supportsClone = MessageDigestHashFunction.supportsClone(this.prototype);
    }

    private static boolean supportsClone(MessageDigest digest) {
        try {
            digest.clone();
            return true;
        } catch (CloneNotSupportedException e) {
            return false;
        }
    }

    @Override
    public int bits() {
        return this.bytes * 8;
    }

    public String toString() {
        return this.toString;
    }

    private static MessageDigest getMessageDigest(String algorithmName) {
        try {
            return MessageDigest.getInstance(algorithmName);
        } catch (NoSuchAlgorithmException e) {
            throw new AssertionError((Object)e);
        }
    }

    @Override
    public Hasher newHasher() {
        if (this.supportsClone) {
            try {
                return new MessageDigestHasher((MessageDigest)this.prototype.clone(), this.bytes);
            } catch (CloneNotSupportedException cloneNotSupportedException) {
                // empty catch block
            }
        }
        return new MessageDigestHasher(MessageDigestHashFunction.getMessageDigest(this.prototype.getAlgorithm()), this.bytes);
    }

    Object writeReplace() {
        return new SerializedForm(this.prototype.getAlgorithm(), this.bytes, this.toString);
    }

    private static final class MessageDigestHasher
    extends AbstractByteHasher {
        private final MessageDigest digest;
        private final int bytes;
        private boolean done;

        private MessageDigestHasher(MessageDigest digest, int bytes) {
            this.digest = digest;
            this.bytes = bytes;
        }

        @Override
        protected void update(byte b) {
            this.checkNotDone();
            this.digest.update(b);
        }

        @Override
        protected void update(byte[] b, int off, int len) {
            this.checkNotDone();
            this.digest.update(b, off, len);
        }

        @Override
        protected void update(ByteBuffer bytes) {
            this.checkNotDone();
            this.digest.update(bytes);
        }

        private void checkNotDone() {
            Preconditions.checkState(!this.done, "Cannot re-use a Hasher after calling hash() on it");
        }

        @Override
        public HashCode hash() {
            this.checkNotDone();
            this.done = true;
            return this.bytes == this.digest.getDigestLength() ? HashCode.fromBytesNoCopy(this.digest.digest()) : HashCode.fromBytesNoCopy(Arrays.copyOf(this.digest.digest(), this.bytes));
        }
    }

    private static final class SerializedForm
    implements Serializable {
        private final String algorithmName;
        private final int bytes;
        private final String toString;
        private static final long serialVersionUID = 0L;

        private SerializedForm(String algorithmName, int bytes, String toString) {
            this.algorithmName = algorithmName;
            this.bytes = bytes;
            this.toString = toString;
        }

        private Object readResolve() {
            return new MessageDigestHashFunction(this.algorithmName, this.bytes, this.toString);
        }
    }
}

