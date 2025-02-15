/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.util.encoders;

import java.io.IOException;
import java.io.OutputStream;
import org.bouncycastle.util.encoders.Encoder;

public class HexEncoder
implements Encoder {
    protected final byte[] encodingTable = new byte[]{48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 97, 98, 99, 100, 101, 102};
    protected final byte[] decodingTable = new byte[128];

    protected void initialiseDecodingTable() {
        int n;
        for (n = 0; n < this.decodingTable.length; ++n) {
            this.decodingTable[n] = -1;
        }
        for (n = 0; n < this.encodingTable.length; ++n) {
            this.decodingTable[this.encodingTable[n]] = (byte)n;
        }
        this.decodingTable[65] = this.decodingTable[97];
        this.decodingTable[66] = this.decodingTable[98];
        this.decodingTable[67] = this.decodingTable[99];
        this.decodingTable[68] = this.decodingTable[100];
        this.decodingTable[69] = this.decodingTable[101];
        this.decodingTable[70] = this.decodingTable[102];
    }

    public HexEncoder() {
        this.initialiseDecodingTable();
    }

    public int encode(byte[] byArray, int n, int n2, OutputStream outputStream) throws IOException {
        for (int i = n; i < n + n2; ++i) {
            int n3 = byArray[i] & 0xFF;
            outputStream.write(this.encodingTable[n3 >>> 4]);
            outputStream.write(this.encodingTable[n3 & 0xF]);
        }
        return n2 * 2;
    }

    private static boolean ignore(char c) {
        return c == '\n' || c == '\r' || c == '\t' || c == ' ';
    }

    public int decode(byte[] byArray, int n, int n2, OutputStream outputStream) throws IOException {
        int n3;
        int n4 = 0;
        for (n3 = n + n2; n3 > n && HexEncoder.ignore((char)byArray[n3 - 1]); --n3) {
        }
        int n5 = n;
        while (n5 < n3) {
            byte by;
            while (n5 < n3 && HexEncoder.ignore((char)byArray[n5])) {
                ++n5;
            }
            byte by2 = this.decodingTable[byArray[n5++]];
            while (n5 < n3 && HexEncoder.ignore((char)byArray[n5])) {
                ++n5;
            }
            if ((by2 | (by = this.decodingTable[byArray[n5++]])) < 0) {
                throw new IOException("invalid characters encountered in Hex data");
            }
            outputStream.write(by2 << 4 | by);
            ++n4;
        }
        return n4;
    }

    public int decode(String string, OutputStream outputStream) throws IOException {
        int n;
        int n2 = 0;
        for (n = string.length(); n > 0 && HexEncoder.ignore(string.charAt(n - 1)); --n) {
        }
        int n3 = 0;
        while (n3 < n) {
            byte by;
            while (n3 < n && HexEncoder.ignore(string.charAt(n3))) {
                ++n3;
            }
            byte by2 = this.decodingTable[string.charAt(n3++)];
            while (n3 < n && HexEncoder.ignore(string.charAt(n3))) {
                ++n3;
            }
            if ((by2 | (by = this.decodingTable[string.charAt(n3++)])) < 0) {
                throw new IOException("invalid characters encountered in Hex string");
            }
            outputStream.write(by2 << 4 | by);
            ++n2;
        }
        return n2;
    }
}

