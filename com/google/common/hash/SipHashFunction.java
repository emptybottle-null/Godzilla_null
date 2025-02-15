/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.google.common.hash;

import com.google.common.base.Preconditions;
import com.google.common.hash.AbstractHashFunction;
import com.google.common.hash.AbstractStreamingHasher;
import com.google.common.hash.HashCode;
import com.google.common.hash.HashFunction;
import com.google.common.hash.Hasher;
import com.google.errorprone.annotations.Immutable;
import java.io.Serializable;
import java.nio.ByteBuffer;
import org.checkerframework.checker.nullness.qual.Nullable;

@Immutable
final class SipHashFunction
extends AbstractHashFunction
implements Serializable {
    static final HashFunction SIP_HASH_24 = new SipHashFunction(2, 4, 506097522914230528L, 1084818905618843912L);
    private final int c;
    private final int d;
    private final long k0;
    private final long k1;
    private static final long serialVersionUID = 0L;

    SipHashFunction(int c, int d, long k0, long k1) {
        Preconditions.checkArgument(c > 0, "The number of SipRound iterations (c=%s) during Compression must be positive.", c);
        Preconditions.checkArgument(d > 0, "The number of SipRound iterations (d=%s) during Finalization must be positive.", d);
        this.c = c;
        this.d = d;
        this.k0 = k0;
        this.k1 = k1;
    }

    @Override
    public int bits() {
        return 64;
    }

    @Override
    public Hasher newHasher() {
        return new SipHasher(this.c, this.d, this.k0, this.k1);
    }

    public String toString() {
        return "Hashing.sipHash" + this.c + "" + this.d + "(" + this.k0 + ", " + this.k1 + ")";
    }

    public boolean equals(@Nullable Object object) {
        if (object instanceof SipHashFunction) {
            SipHashFunction other = (SipHashFunction)object;
            return this.c == other.c && this.d == other.d && this.k0 == other.k0 && this.k1 == other.k1;
        }
        return false;
    }

    public int hashCode() {
        return (int)((long)(this.getClass().hashCode() ^ this.c ^ this.d) ^ this.k0 ^ this.k1);
    }

    private static final class SipHasher
    extends AbstractStreamingHasher {
        private static final int CHUNK_SIZE = 8;
        private final int c;
        private final int d;
        private long v0 = 8317987319222330741L;
        private long v1 = 7237128888997146477L;
        private long v2 = 7816392313619706465L;
        private long v3 = 8387220255154660723L;
        private long b = 0L;
        private long finalM = 0L;

        SipHasher(int c, int d, long k0, long k1) {
            super(8);
            this.c = c;
            this.d = d;
            this.v0 ^= k0;
            this.v1 ^= k1;
            this.v2 ^= k0;
            this.v3 ^= k1;
        }

        @Override
        protected void process(ByteBuffer buffer) {
            this.b += 8L;
            this.processM(buffer.getLong());
        }

        @Override
        protected void processRemaining(ByteBuffer buffer) {
            this.b += (long)buffer.remaining();
            int i = 0;
            while (buffer.hasRemaining()) {
                this.finalM ^= ((long)buffer.get() & 0xFFL) << i;
                i += 8;
            }
        }

        @Override
        public HashCode makeHash() {
            this.finalM ^= this.b << 56;
            this.processM(this.finalM);
            this.v2 ^= 0xFFL;
            this.sipRound(this.d);
            return HashCode.fromLong(this.v0 ^ this.v1 ^ this.v2 ^ this.v3);
        }

        private void processM(long m) {
            this.v3 ^= m;
            this.sipRound(this.c);
            this.v0 ^= m;
        }

        private void sipRound(int iterations) {
            for (int i = 0; i < iterations; ++i) {
                this.v0 += this.v1;
                this.v2 += this.v3;
                this.v1 = Long.rotateLeft(this.v1, 13);
                this.v3 = Long.rotateLeft(this.v3, 16);
                this.v1 ^= this.v0;
                this.v3 ^= this.v2;
                this.v0 = Long.rotateLeft(this.v0, 32);
                this.v2 += this.v1;
                this.v0 += this.v3;
                this.v1 = Long.rotateLeft(this.v1, 17);
                this.v3 = Long.rotateLeft(this.v3, 21);
                this.v1 ^= this.v2;
                this.v3 ^= this.v0;
                this.v2 = Long.rotateLeft(this.v2, 32);
            }
        }
    }
}

