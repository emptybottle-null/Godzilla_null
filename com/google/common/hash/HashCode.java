/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.google.common.hash;

import com.google.common.annotations.Beta;
import com.google.common.base.Preconditions;
import com.google.common.primitives.Ints;
import com.google.common.primitives.UnsignedInts;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.io.Serializable;
import org.checkerframework.checker.nullness.qual.Nullable;

@Beta
public abstract class HashCode {
    private static final char[] hexDigits = "0123456789abcdef".toCharArray();

    HashCode() {
    }

    public abstract int bits();

    public abstract int asInt();

    public abstract long asLong();

    public abstract long padToLong();

    public abstract byte[] asBytes();

    @CanIgnoreReturnValue
    public int writeBytesTo(byte[] dest, int offset, int maxLength) {
        maxLength = Ints.min(maxLength, this.bits() / 8);
        Preconditions.checkPositionIndexes(offset, offset + maxLength, dest.length);
        this.writeBytesToImpl(dest, offset, maxLength);
        return maxLength;
    }

    abstract void writeBytesToImpl(byte[] var1, int var2, int var3);

    byte[] getBytesInternal() {
        return this.asBytes();
    }

    abstract boolean equalsSameBits(HashCode var1);

    public static HashCode fromInt(int hash) {
        return new IntHashCode(hash);
    }

    public static HashCode fromLong(long hash) {
        return new LongHashCode(hash);
    }

    public static HashCode fromBytes(byte[] bytes) {
        Preconditions.checkArgument(bytes.length >= 1, "A HashCode must contain at least 1 byte.");
        return HashCode.fromBytesNoCopy((byte[])bytes.clone());
    }

    static HashCode fromBytesNoCopy(byte[] bytes) {
        return new BytesHashCode(bytes);
    }

    public static HashCode fromString(String string) {
        Preconditions.checkArgument(string.length() >= 2, "input string (%s) must have at least 2 characters", (Object)string);
        Preconditions.checkArgument(string.length() % 2 == 0, "input string (%s) must have an even number of characters", (Object)string);
        byte[] bytes = new byte[string.length() / 2];
        for (int i = 0; i < string.length(); i += 2) {
            int ch1 = HashCode.decode(string.charAt(i)) << 4;
            int ch2 = HashCode.decode(string.charAt(i + 1));
            bytes[i / 2] = (byte)(ch1 + ch2);
        }
        return HashCode.fromBytesNoCopy(bytes);
    }

    private static int decode(char ch) {
        if (ch >= '0' && ch <= '9') {
            return ch - 48;
        }
        if (ch >= 'a' && ch <= 'f') {
            return ch - 97 + 10;
        }
        throw new IllegalArgumentException("Illegal hexadecimal character: " + ch);
    }

    public final boolean equals(@Nullable Object object) {
        if (object instanceof HashCode) {
            HashCode that = (HashCode)object;
            return this.bits() == that.bits() && this.equalsSameBits(that);
        }
        return false;
    }

    public final int hashCode() {
        if (this.bits() >= 32) {
            return this.asInt();
        }
        byte[] bytes = this.getBytesInternal();
        int val = bytes[0] & 0xFF;
        for (int i = 1; i < bytes.length; ++i) {
            val |= (bytes[i] & 0xFF) << i * 8;
        }
        return val;
    }

    public final String toString() {
        byte[] bytes = this.getBytesInternal();
        StringBuilder sb = new StringBuilder(2 * bytes.length);
        for (byte b : bytes) {
            sb.append(hexDigits[b >> 4 & 0xF]).append(hexDigits[b & 0xF]);
        }
        return sb.toString();
    }

    private static final class BytesHashCode
    extends HashCode
    implements Serializable {
        final byte[] bytes;
        private static final long serialVersionUID = 0L;

        BytesHashCode(byte[] bytes) {
            this.bytes = Preconditions.checkNotNull(bytes);
        }

        @Override
        public int bits() {
            return this.bytes.length * 8;
        }

        @Override
        public byte[] asBytes() {
            return (byte[])this.bytes.clone();
        }

        @Override
        public int asInt() {
            Preconditions.checkState(this.bytes.length >= 4, "HashCode#asInt() requires >= 4 bytes (it only has %s bytes).", this.bytes.length);
            return this.bytes[0] & 0xFF | (this.bytes[1] & 0xFF) << 8 | (this.bytes[2] & 0xFF) << 16 | (this.bytes[3] & 0xFF) << 24;
        }

        @Override
        public long asLong() {
            Preconditions.checkState(this.bytes.length >= 8, "HashCode#asLong() requires >= 8 bytes (it only has %s bytes).", this.bytes.length);
            return this.padToLong();
        }

        @Override
        public long padToLong() {
            long retVal = this.bytes[0] & 0xFF;
            for (int i = 1; i < Math.min(this.bytes.length, 8); ++i) {
                retVal |= ((long)this.bytes[i] & 0xFFL) << i * 8;
            }
            return retVal;
        }

        @Override
        void writeBytesToImpl(byte[] dest, int offset, int maxLength) {
            System.arraycopy(this.bytes, 0, dest, offset, maxLength);
        }

        @Override
        byte[] getBytesInternal() {
            return this.bytes;
        }

        @Override
        boolean equalsSameBits(HashCode that) {
            if (this.bytes.length != that.getBytesInternal().length) {
                return false;
            }
            boolean areEqual = true;
            for (int i = 0; i < this.bytes.length; ++i) {
                areEqual &= this.bytes[i] == that.getBytesInternal()[i];
            }
            return areEqual;
        }
    }

    private static final class LongHashCode
    extends HashCode
    implements Serializable {
        final long hash;
        private static final long serialVersionUID = 0L;

        LongHashCode(long hash) {
            this.hash = hash;
        }

        @Override
        public int bits() {
            return 64;
        }

        @Override
        public byte[] asBytes() {
            return new byte[]{(byte)this.hash, (byte)(this.hash >> 8), (byte)(this.hash >> 16), (byte)(this.hash >> 24), (byte)(this.hash >> 32), (byte)(this.hash >> 40), (byte)(this.hash >> 48), (byte)(this.hash >> 56)};
        }

        @Override
        public int asInt() {
            return (int)this.hash;
        }

        @Override
        public long asLong() {
            return this.hash;
        }

        @Override
        public long padToLong() {
            return this.hash;
        }

        @Override
        void writeBytesToImpl(byte[] dest, int offset, int maxLength) {
            for (int i = 0; i < maxLength; ++i) {
                dest[offset + i] = (byte)(this.hash >> i * 8);
            }
        }

        @Override
        boolean equalsSameBits(HashCode that) {
            return this.hash == that.asLong();
        }
    }

    private static final class IntHashCode
    extends HashCode
    implements Serializable {
        final int hash;
        private static final long serialVersionUID = 0L;

        IntHashCode(int hash) {
            this.hash = hash;
        }

        @Override
        public int bits() {
            return 32;
        }

        @Override
        public byte[] asBytes() {
            return new byte[]{(byte)this.hash, (byte)(this.hash >> 8), (byte)(this.hash >> 16), (byte)(this.hash >> 24)};
        }

        @Override
        public int asInt() {
            return this.hash;
        }

        @Override
        public long asLong() {
            throw new IllegalStateException("this HashCode only has 32 bits; cannot create a long");
        }

        @Override
        public long padToLong() {
            return UnsignedInts.toLong(this.hash);
        }

        @Override
        void writeBytesToImpl(byte[] dest, int offset, int maxLength) {
            for (int i = 0; i < maxLength; ++i) {
                dest[offset + i] = (byte)(this.hash >> i * 8);
            }
        }

        @Override
        boolean equalsSameBits(HashCode that) {
            return this.hash == that.asInt();
        }
    }
}

