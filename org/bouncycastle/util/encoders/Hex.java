/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.util.encoders;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import org.bouncycastle.util.Strings;
import org.bouncycastle.util.encoders.DecoderException;
import org.bouncycastle.util.encoders.Encoder;
import org.bouncycastle.util.encoders.EncoderException;
import org.bouncycastle.util.encoders.HexEncoder;

public class Hex {
    private static final Encoder encoder = new HexEncoder();

    public static String toHexString(byte[] byArray) {
        return Hex.toHexString(byArray, 0, byArray.length);
    }

    public static String toHexString(byte[] byArray, int n, int n2) {
        byte[] byArray2 = Hex.encode(byArray, n, n2);
        return Strings.fromByteArray(byArray2);
    }

    public static byte[] encode(byte[] byArray) {
        return Hex.encode(byArray, 0, byArray.length);
    }

    public static byte[] encode(byte[] byArray, int n, int n2) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            encoder.encode(byArray, n, n2, byteArrayOutputStream);
        } catch (Exception exception) {
            throw new EncoderException("exception encoding Hex string: " + exception.getMessage(), exception);
        }
        return byteArrayOutputStream.toByteArray();
    }

    public static int encode(byte[] byArray, OutputStream outputStream) throws IOException {
        return encoder.encode(byArray, 0, byArray.length, outputStream);
    }

    public static int encode(byte[] byArray, int n, int n2, OutputStream outputStream) throws IOException {
        return encoder.encode(byArray, n, n2, outputStream);
    }

    public static byte[] decode(byte[] byArray) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            encoder.decode(byArray, 0, byArray.length, byteArrayOutputStream);
        } catch (Exception exception) {
            throw new DecoderException("exception decoding Hex data: " + exception.getMessage(), exception);
        }
        return byteArrayOutputStream.toByteArray();
    }

    public static byte[] decode(String string) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            encoder.decode(string, byteArrayOutputStream);
        } catch (Exception exception) {
            throw new DecoderException("exception decoding Hex string: " + exception.getMessage(), exception);
        }
        return byteArrayOutputStream.toByteArray();
    }

    public static int decode(String string, OutputStream outputStream) throws IOException {
        return encoder.decode(string, outputStream);
    }
}

