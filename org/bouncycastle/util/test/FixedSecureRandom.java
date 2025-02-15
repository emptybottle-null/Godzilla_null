/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.util.test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.security.Provider;
import java.security.SecureRandom;
import org.bouncycastle.util.Pack;
import org.bouncycastle.util.encoders.Hex;

public class FixedSecureRandom
extends SecureRandom {
    private static java.math.BigInteger REGULAR = new java.math.BigInteger("01020304ffffffff0506070811111111", 16);
    private static java.math.BigInteger ANDROID = new java.math.BigInteger("1111111105060708ffffffff01020304", 16);
    private static java.math.BigInteger CLASSPATH = new java.math.BigInteger("3020104ffffffff05060708111111", 16);
    private static final boolean isAndroidStyle;
    private static final boolean isClasspathStyle;
    private static final boolean isRegularStyle;
    private byte[] _data;
    private int _index;

    public FixedSecureRandom(byte[] byArray) {
        this(new Source[]{new Data(byArray)});
    }

    public FixedSecureRandom(byte[][] byArray) {
        this(FixedSecureRandom.buildDataArray(byArray));
    }

    private static Data[] buildDataArray(byte[][] byArray) {
        Data[] dataArray = new Data[byArray.length];
        for (int i = 0; i != byArray.length; ++i) {
            dataArray[i] = new Data(byArray[i]);
        }
        return dataArray;
    }

    public FixedSecureRandom(Source[] sourceArray) {
        super(null, new DummyProvider());
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        if (isRegularStyle) {
            if (isClasspathStyle) {
                for (int i = 0; i != sourceArray.length; ++i) {
                    try {
                        if (sourceArray[i] instanceof BigInteger) {
                            int n;
                            byte[] byArray = sourceArray[i].data;
                            int n2 = byArray.length - byArray.length % 4;
                            for (n = byArray.length - n2 - 1; n >= 0; --n) {
                                byteArrayOutputStream.write(byArray[n]);
                            }
                            for (n = byArray.length - n2; n < byArray.length; n += 4) {
                                byteArrayOutputStream.write(byArray, n, 4);
                            }
                            continue;
                        }
                        byteArrayOutputStream.write(sourceArray[i].data);
                        continue;
                    } catch (IOException iOException) {
                        throw new IllegalArgumentException("can't save value source.");
                    }
                }
            } else {
                for (int i = 0; i != sourceArray.length; ++i) {
                    try {
                        byteArrayOutputStream.write(sourceArray[i].data);
                        continue;
                    } catch (IOException iOException) {
                        throw new IllegalArgumentException("can't save value source.");
                    }
                }
            }
        } else if (isAndroidStyle) {
            for (int i = 0; i != sourceArray.length; ++i) {
                try {
                    if (sourceArray[i] instanceof BigInteger) {
                        int n;
                        byte[] byArray = sourceArray[i].data;
                        int n3 = byArray.length - byArray.length % 4;
                        for (n = 0; n < n3; n += 4) {
                            byteArrayOutputStream.write(byArray, byArray.length - (n + 4), 4);
                        }
                        if (byArray.length - n3 != 0) {
                            for (n = 0; n != 4 - (byArray.length - n3); ++n) {
                                byteArrayOutputStream.write(0);
                            }
                        }
                        for (n = 0; n != byArray.length - n3; ++n) {
                            byteArrayOutputStream.write(byArray[n3 + n]);
                        }
                        continue;
                    }
                    byteArrayOutputStream.write(sourceArray[i].data);
                    continue;
                } catch (IOException iOException) {
                    throw new IllegalArgumentException("can't save value source.");
                }
            }
        } else {
            throw new IllegalStateException("Unrecognized BigInteger implementation");
        }
        this._data = byteArrayOutputStream.toByteArray();
    }

    public void nextBytes(byte[] byArray) {
        System.arraycopy(this._data, this._index, byArray, 0, byArray.length);
        this._index += byArray.length;
    }

    public byte[] generateSeed(int n) {
        byte[] byArray = new byte[n];
        this.nextBytes(byArray);
        return byArray;
    }

    public int nextInt() {
        int n = 0;
        n |= this.nextValue() << 24;
        n |= this.nextValue() << 16;
        n |= this.nextValue() << 8;
        return n |= this.nextValue();
    }

    public long nextLong() {
        long l = 0L;
        l |= (long)this.nextValue() << 56;
        l |= (long)this.nextValue() << 48;
        l |= (long)this.nextValue() << 40;
        l |= (long)this.nextValue() << 32;
        l |= (long)this.nextValue() << 24;
        l |= (long)this.nextValue() << 16;
        l |= (long)this.nextValue() << 8;
        return l |= (long)this.nextValue();
    }

    public boolean isExhausted() {
        return this._index == this._data.length;
    }

    private int nextValue() {
        return this._data[this._index++] & 0xFF;
    }

    private static byte[] expandToBitLength(int n, byte[] byArray) {
        if ((n + 7) / 8 > byArray.length) {
            byte[] byArray2 = new byte[(n + 7) / 8];
            System.arraycopy(byArray, 0, byArray2, byArray2.length - byArray.length, byArray.length);
            if (isAndroidStyle && n % 8 != 0) {
                int n2 = Pack.bigEndianToInt(byArray2, 0);
                Pack.intToBigEndian(n2 << 8 - n % 8, byArray2, 0);
            }
            return byArray2;
        }
        if (isAndroidStyle && n < byArray.length * 8 && n % 8 != 0) {
            int n3 = Pack.bigEndianToInt(byArray, 0);
            Pack.intToBigEndian(n3 << 8 - n % 8, byArray, 0);
        }
        return byArray;
    }

    static {
        java.math.BigInteger bigInteger = new java.math.BigInteger(128, new RandomChecker());
        java.math.BigInteger bigInteger2 = new java.math.BigInteger(120, new RandomChecker());
        isAndroidStyle = bigInteger.equals(ANDROID);
        isRegularStyle = bigInteger.equals(REGULAR);
        isClasspathStyle = bigInteger2.equals(CLASSPATH);
    }

    public static class BigInteger
    extends Source {
        public BigInteger(byte[] byArray) {
            super(byArray);
        }

        public BigInteger(int n, byte[] byArray) {
            super(FixedSecureRandom.expandToBitLength(n, byArray));
        }

        public BigInteger(String string) {
            this(Hex.decode(string));
        }

        public BigInteger(int n, String string) {
            super(FixedSecureRandom.expandToBitLength(n, Hex.decode(string)));
        }
    }

    public static class Data
    extends Source {
        public Data(byte[] byArray) {
            super(byArray);
        }
    }

    private static class DummyProvider
    extends Provider {
        DummyProvider() {
            super("BCFIPS_FIXED_RNG", 1.0, "BCFIPS Fixed Secure Random Provider");
        }
    }

    private static class RandomChecker
    extends SecureRandom {
        byte[] data = Hex.decode("01020304ffffffff0506070811111111");
        int index = 0;

        RandomChecker() {
            super(null, new DummyProvider());
        }

        public void nextBytes(byte[] byArray) {
            System.arraycopy(this.data, this.index, byArray, 0, byArray.length);
            this.index += byArray.length;
        }
    }

    public static class Source {
        byte[] data;

        Source(byte[] byArray) {
            this.data = byArray;
        }
    }
}

