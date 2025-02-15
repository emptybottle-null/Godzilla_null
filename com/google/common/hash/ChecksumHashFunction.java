/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.google.common.hash;

import com.google.common.base.Preconditions;
import com.google.common.hash.AbstractByteHasher;
import com.google.common.hash.AbstractHashFunction;
import com.google.common.hash.HashCode;
import com.google.common.hash.Hasher;
import com.google.common.hash.ImmutableSupplier;
import com.google.errorprone.annotations.Immutable;
import java.io.Serializable;
import java.util.zip.Checksum;

@Immutable
final class ChecksumHashFunction
extends AbstractHashFunction
implements Serializable {
    private final ImmutableSupplier<? extends Checksum> checksumSupplier;
    private final int bits;
    private final String toString;
    private static final long serialVersionUID = 0L;

    ChecksumHashFunction(ImmutableSupplier<? extends Checksum> checksumSupplier, int bits, String toString) {
        this.checksumSupplier = Preconditions.checkNotNull(checksumSupplier);
        Preconditions.checkArgument(bits == 32 || bits == 64, "bits (%s) must be either 32 or 64", bits);
        this.bits = bits;
        this.toString = Preconditions.checkNotNull(toString);
    }

    @Override
    public int bits() {
        return this.bits;
    }

    @Override
    public Hasher newHasher() {
        return new ChecksumHasher((Checksum)this.checksumSupplier.get());
    }

    public String toString() {
        return this.toString;
    }

    private final class ChecksumHasher
    extends AbstractByteHasher {
        private final Checksum checksum;

        private ChecksumHasher(Checksum checksum) {
            this.checksum = Preconditions.checkNotNull(checksum);
        }

        @Override
        protected void update(byte b) {
            this.checksum.update(b);
        }

        @Override
        protected void update(byte[] bytes, int off, int len) {
            this.checksum.update(bytes, off, len);
        }

        @Override
        public HashCode hash() {
            long value = this.checksum.getValue();
            if (ChecksumHashFunction.this.bits == 32) {
                return HashCode.fromInt((int)value);
            }
            return HashCode.fromLong(value);
        }
    }
}

