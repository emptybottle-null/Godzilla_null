/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.springframework.asm;

public class ByteVector {
    byte[] data;
    int length;

    public ByteVector() {
        this.data = new byte[64];
    }

    public ByteVector(int initialCapacity) {
        this.data = new byte[initialCapacity];
    }

    ByteVector(byte[] data) {
        this.data = data;
        this.length = data.length;
    }

    public ByteVector putByte(int byteValue) {
        int currentLength = this.length;
        if (currentLength + 1 > this.data.length) {
            this.enlarge(1);
        }
        this.data[currentLength++] = (byte)byteValue;
        this.length = currentLength;
        return this;
    }

    final ByteVector put11(int byteValue1, int byteValue2) {
        int currentLength = this.length;
        if (currentLength + 2 > this.data.length) {
            this.enlarge(2);
        }
        byte[] currentData = this.data;
        currentData[currentLength++] = (byte)byteValue1;
        currentData[currentLength++] = (byte)byteValue2;
        this.length = currentLength;
        return this;
    }

    public ByteVector putShort(int shortValue) {
        int currentLength = this.length;
        if (currentLength + 2 > this.data.length) {
            this.enlarge(2);
        }
        byte[] currentData = this.data;
        currentData[currentLength++] = (byte)(shortValue >>> 8);
        currentData[currentLength++] = (byte)shortValue;
        this.length = currentLength;
        return this;
    }

    final ByteVector put12(int byteValue, int shortValue) {
        int currentLength = this.length;
        if (currentLength + 3 > this.data.length) {
            this.enlarge(3);
        }
        byte[] currentData = this.data;
        currentData[currentLength++] = (byte)byteValue;
        currentData[currentLength++] = (byte)(shortValue >>> 8);
        currentData[currentLength++] = (byte)shortValue;
        this.length = currentLength;
        return this;
    }

    final ByteVector put112(int byteValue1, int byteValue2, int shortValue) {
        int currentLength = this.length;
        if (currentLength + 4 > this.data.length) {
            this.enlarge(4);
        }
        byte[] currentData = this.data;
        currentData[currentLength++] = (byte)byteValue1;
        currentData[currentLength++] = (byte)byteValue2;
        currentData[currentLength++] = (byte)(shortValue >>> 8);
        currentData[currentLength++] = (byte)shortValue;
        this.length = currentLength;
        return this;
    }

    public ByteVector putInt(int intValue) {
        int currentLength = this.length;
        if (currentLength + 4 > this.data.length) {
            this.enlarge(4);
        }
        byte[] currentData = this.data;
        currentData[currentLength++] = (byte)(intValue >>> 24);
        currentData[currentLength++] = (byte)(intValue >>> 16);
        currentData[currentLength++] = (byte)(intValue >>> 8);
        currentData[currentLength++] = (byte)intValue;
        this.length = currentLength;
        return this;
    }

    final ByteVector put122(int byteValue, int shortValue1, int shortValue2) {
        int currentLength = this.length;
        if (currentLength + 5 > this.data.length) {
            this.enlarge(5);
        }
        byte[] currentData = this.data;
        currentData[currentLength++] = (byte)byteValue;
        currentData[currentLength++] = (byte)(shortValue1 >>> 8);
        currentData[currentLength++] = (byte)shortValue1;
        currentData[currentLength++] = (byte)(shortValue2 >>> 8);
        currentData[currentLength++] = (byte)shortValue2;
        this.length = currentLength;
        return this;
    }

    public ByteVector putLong(long longValue) {
        int currentLength = this.length;
        if (currentLength + 8 > this.data.length) {
            this.enlarge(8);
        }
        byte[] currentData = this.data;
        int intValue = (int)(longValue >>> 32);
        currentData[currentLength++] = (byte)(intValue >>> 24);
        currentData[currentLength++] = (byte)(intValue >>> 16);
        currentData[currentLength++] = (byte)(intValue >>> 8);
        currentData[currentLength++] = (byte)intValue;
        intValue = (int)longValue;
        currentData[currentLength++] = (byte)(intValue >>> 24);
        currentData[currentLength++] = (byte)(intValue >>> 16);
        currentData[currentLength++] = (byte)(intValue >>> 8);
        currentData[currentLength++] = (byte)intValue;
        this.length = currentLength;
        return this;
    }

    public ByteVector putUTF8(String stringValue) {
        int charLength = stringValue.length();
        if (charLength > 65535) {
            throw new IllegalArgumentException("UTF8 string too large");
        }
        int currentLength = this.length;
        if (currentLength + 2 + charLength > this.data.length) {
            this.enlarge(2 + charLength);
        }
        byte[] currentData = this.data;
        currentData[currentLength++] = (byte)(charLength >>> 8);
        currentData[currentLength++] = (byte)charLength;
        for (int i = 0; i < charLength; ++i) {
            char charValue = stringValue.charAt(i);
            if (charValue < '\u0001' || charValue > '\u007f') {
                this.length = currentLength;
                return this.encodeUtf8(stringValue, i, 65535);
            }
            currentData[currentLength++] = (byte)charValue;
        }
        this.length = currentLength;
        return this;
    }

    final ByteVector encodeUtf8(String stringValue, int offset, int maxByteLength) {
        int charLength = stringValue.length();
        int byteLength = offset;
        for (int i = offset; i < charLength; ++i) {
            char charValue = stringValue.charAt(i);
            if (charValue >= '\u0001' && charValue <= '\u007f') {
                ++byteLength;
                continue;
            }
            if (charValue <= '\u07ff') {
                byteLength += 2;
                continue;
            }
            byteLength += 3;
        }
        if (byteLength > maxByteLength) {
            throw new IllegalArgumentException("UTF8 string too large");
        }
        int byteLengthOffset = this.length - offset - 2;
        if (byteLengthOffset >= 0) {
            this.data[byteLengthOffset] = (byte)(byteLength >>> 8);
            this.data[byteLengthOffset + 1] = (byte)byteLength;
        }
        if (this.length + byteLength - offset > this.data.length) {
            this.enlarge(byteLength - offset);
        }
        int currentLength = this.length;
        for (int i = offset; i < charLength; ++i) {
            char charValue = stringValue.charAt(i);
            if (charValue >= '\u0001' && charValue <= '\u007f') {
                this.data[currentLength++] = (byte)charValue;
                continue;
            }
            if (charValue <= '\u07ff') {
                this.data[currentLength++] = (byte)(0xC0 | charValue >> 6 & 0x1F);
                this.data[currentLength++] = (byte)(0x80 | charValue & 0x3F);
                continue;
            }
            this.data[currentLength++] = (byte)(0xE0 | charValue >> 12 & 0xF);
            this.data[currentLength++] = (byte)(0x80 | charValue >> 6 & 0x3F);
            this.data[currentLength++] = (byte)(0x80 | charValue & 0x3F);
        }
        this.length = currentLength;
        return this;
    }

    public ByteVector putByteArray(byte[] byteArrayValue, int byteOffset, int byteLength) {
        if (this.length + byteLength > this.data.length) {
            this.enlarge(byteLength);
        }
        if (byteArrayValue != null) {
            System.arraycopy(byteArrayValue, byteOffset, this.data, this.length, byteLength);
        }
        this.length += byteLength;
        return this;
    }

    private void enlarge(int size) {
        if (this.length > this.data.length) {
            throw new AssertionError((Object)"Internal error");
        }
        int doubleCapacity = 2 * this.data.length;
        int minimalCapacity = this.length + size;
        byte[] newData = new byte[doubleCapacity > minimalCapacity ? doubleCapacity : minimalCapacity];
        System.arraycopy(this.data, 0, newData, 0, this.length);
        this.data = newData;
    }
}

