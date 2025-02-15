/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.util.encoders;

import java.io.IOException;
import java.io.OutputStream;
import org.bouncycastle.util.encoders.Encoder;

public class Base64Encoder
implements Encoder {
    protected final byte[] encodingTable = new byte[]{65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 43, 47};
    protected byte padding = (byte)61;
    protected final byte[] decodingTable = new byte[128];

    protected void initialiseDecodingTable() {
        int n;
        for (n = 0; n < this.decodingTable.length; ++n) {
            this.decodingTable[n] = -1;
        }
        for (n = 0; n < this.encodingTable.length; ++n) {
            this.decodingTable[this.encodingTable[n]] = (byte)n;
        }
    }

    public Base64Encoder() {
        this.initialiseDecodingTable();
    }

    public int encode(byte[] byArray, int n, int n2, OutputStream outputStream) throws IOException {
        int n3;
        int n4 = n2 % 3;
        int n5 = n2 - n4;
        for (n3 = n; n3 < n + n5; n3 += 3) {
            int n6 = byArray[n3] & 0xFF;
            int n7 = byArray[n3 + 1] & 0xFF;
            int n8 = byArray[n3 + 2] & 0xFF;
            outputStream.write(this.encodingTable[n6 >>> 2 & 0x3F]);
            outputStream.write(this.encodingTable[(n6 << 4 | n7 >>> 4) & 0x3F]);
            outputStream.write(this.encodingTable[(n7 << 2 | n8 >>> 6) & 0x3F]);
            outputStream.write(this.encodingTable[n8 & 0x3F]);
        }
        switch (n4) {
            case 0: {
                break;
            }
            case 1: {
                int n9 = byArray[n + n5] & 0xFF;
                n3 = n9 >>> 2 & 0x3F;
                int n10 = n9 << 4 & 0x3F;
                outputStream.write(this.encodingTable[n3]);
                outputStream.write(this.encodingTable[n10]);
                outputStream.write(this.padding);
                outputStream.write(this.padding);
                break;
            }
            case 2: {
                int n11 = byArray[n + n5] & 0xFF;
                int n12 = byArray[n + n5 + 1] & 0xFF;
                n3 = n11 >>> 2 & 0x3F;
                int n13 = (n11 << 4 | n12 >>> 4) & 0x3F;
                int n14 = n12 << 2 & 0x3F;
                outputStream.write(this.encodingTable[n3]);
                outputStream.write(this.encodingTable[n13]);
                outputStream.write(this.encodingTable[n14]);
                outputStream.write(this.padding);
            }
        }
        return n5 / 3 * 4 + (n4 == 0 ? 0 : 4);
    }

    private boolean ignore(char c) {
        return c == '\n' || c == '\r' || c == '\t' || c == ' ';
    }

    public int decode(byte[] byArray, int n, int n2, OutputStream outputStream) throws IOException {
        int n3;
        int n4 = 0;
        for (n3 = n + n2; n3 > n && this.ignore((char)byArray[n3 - 1]); --n3) {
        }
        int n5 = n;
        int n6 = n3 - 4;
        n5 = this.nextI(byArray, n5, n6);
        while (n5 < n6) {
            byte by;
            byte by2 = this.decodingTable[byArray[n5++]];
            n5 = this.nextI(byArray, n5, n6);
            byte by3 = this.decodingTable[byArray[n5++]];
            n5 = this.nextI(byArray, n5, n6);
            byte by4 = this.decodingTable[byArray[n5++]];
            n5 = this.nextI(byArray, n5, n6);
            if ((by2 | by3 | by4 | (by = this.decodingTable[byArray[n5++]])) < 0) {
                throw new IOException("invalid characters encountered in base64 data");
            }
            outputStream.write(by2 << 2 | by3 >> 4);
            outputStream.write(by3 << 4 | by4 >> 2);
            outputStream.write(by4 << 6 | by);
            n4 += 3;
            n5 = this.nextI(byArray, n5, n6);
        }
        return n4 += this.decodeLastBlock(outputStream, (char)byArray[n3 - 4], (char)byArray[n3 - 3], (char)byArray[n3 - 2], (char)byArray[n3 - 1]);
    }

    private int nextI(byte[] byArray, int n, int n2) {
        while (n < n2 && this.ignore((char)byArray[n])) {
            ++n;
        }
        return n;
    }

    public int decode(String string, OutputStream outputStream) throws IOException {
        int n;
        int n2 = 0;
        for (n = string.length(); n > 0 && this.ignore(string.charAt(n - 1)); --n) {
        }
        int n3 = 0;
        int n4 = n - 4;
        n3 = this.nextI(string, n3, n4);
        while (n3 < n4) {
            byte by;
            byte by2 = this.decodingTable[string.charAt(n3++)];
            n3 = this.nextI(string, n3, n4);
            byte by3 = this.decodingTable[string.charAt(n3++)];
            n3 = this.nextI(string, n3, n4);
            byte by4 = this.decodingTable[string.charAt(n3++)];
            n3 = this.nextI(string, n3, n4);
            if ((by2 | by3 | by4 | (by = this.decodingTable[string.charAt(n3++)])) < 0) {
                throw new IOException("invalid characters encountered in base64 data");
            }
            outputStream.write(by2 << 2 | by3 >> 4);
            outputStream.write(by3 << 4 | by4 >> 2);
            outputStream.write(by4 << 6 | by);
            n2 += 3;
            n3 = this.nextI(string, n3, n4);
        }
        return n2 += this.decodeLastBlock(outputStream, string.charAt(n - 4), string.charAt(n - 3), string.charAt(n - 2), string.charAt(n - 1));
    }

    private int decodeLastBlock(OutputStream outputStream, char c, char c2, char c3, char c4) throws IOException {
        if (c3 == this.padding) {
            if (c4 != this.padding) {
                throw new IOException("invalid characters encountered at end of base64 data");
            }
            byte by = this.decodingTable[c];
            byte by2 = this.decodingTable[c2];
            if ((by | by2) < 0) {
                throw new IOException("invalid characters encountered at end of base64 data");
            }
            outputStream.write(by << 2 | by2 >> 4);
            return 1;
        }
        if (c4 == this.padding) {
            byte by = this.decodingTable[c];
            byte by3 = this.decodingTable[c2];
            byte by4 = this.decodingTable[c3];
            if ((by | by3 | by4) < 0) {
                throw new IOException("invalid characters encountered at end of base64 data");
            }
            outputStream.write(by << 2 | by3 >> 4);
            outputStream.write(by3 << 4 | by4 >> 2);
            return 2;
        }
        byte by = this.decodingTable[c];
        byte by5 = this.decodingTable[c2];
        byte by6 = this.decodingTable[c3];
        byte by7 = this.decodingTable[c4];
        if ((by | by5 | by6 | by7) < 0) {
            throw new IOException("invalid characters encountered at end of base64 data");
        }
        outputStream.write(by << 2 | by5 >> 4);
        outputStream.write(by5 << 4 | by6 >> 2);
        outputStream.write(by6 << 6 | by7);
        return 3;
    }

    private int nextI(String string, int n, int n2) {
        while (n < n2 && this.ignore(string.charAt(n))) {
            ++n;
        }
        return n;
    }
}

