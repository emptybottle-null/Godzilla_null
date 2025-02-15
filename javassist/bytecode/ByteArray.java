/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package javassist.bytecode;

public class ByteArray {
    public static int readU16bit(byte[] code, int index) {
        return (code[index] & 0xFF) << 8 | code[index + 1] & 0xFF;
    }

    public static int readS16bit(byte[] code, int index) {
        return code[index] << 8 | code[index + 1] & 0xFF;
    }

    public static void write16bit(int value, byte[] code, int index) {
        code[index] = (byte)(value >>> 8);
        code[index + 1] = (byte)value;
    }

    public static int read32bit(byte[] code, int index) {
        return code[index] << 24 | (code[index + 1] & 0xFF) << 16 | (code[index + 2] & 0xFF) << 8 | code[index + 3] & 0xFF;
    }

    public static void write32bit(int value, byte[] code, int index) {
        code[index] = (byte)(value >>> 24);
        code[index + 1] = (byte)(value >>> 16);
        code[index + 2] = (byte)(value >>> 8);
        code[index + 3] = (byte)value;
    }

    static void copy32bit(byte[] src, int isrc, byte[] dest, int idest) {
        dest[idest] = src[isrc];
        dest[idest + 1] = src[isrc + 1];
        dest[idest + 2] = src[isrc + 2];
        dest[idest + 3] = src[isrc + 3];
    }
}

