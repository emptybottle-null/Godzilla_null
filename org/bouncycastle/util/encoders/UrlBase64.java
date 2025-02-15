/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.util.encoders;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import org.bouncycastle.util.encoders.DecoderException;
import org.bouncycastle.util.encoders.Encoder;
import org.bouncycastle.util.encoders.EncoderException;
import org.bouncycastle.util.encoders.UrlBase64Encoder;

public class UrlBase64 {
    private static final Encoder encoder = new UrlBase64Encoder();

    public static byte[] encode(byte[] byArray) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            encoder.encode(byArray, 0, byArray.length, byteArrayOutputStream);
        } catch (Exception exception) {
            throw new EncoderException("exception encoding URL safe base64 data: " + exception.getMessage(), exception);
        }
        return byteArrayOutputStream.toByteArray();
    }

    public static int encode(byte[] byArray, OutputStream outputStream) throws IOException {
        return encoder.encode(byArray, 0, byArray.length, outputStream);
    }

    public static byte[] decode(byte[] byArray) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            encoder.decode(byArray, 0, byArray.length, byteArrayOutputStream);
        } catch (Exception exception) {
            throw new DecoderException("exception decoding URL safe base64 string: " + exception.getMessage(), exception);
        }
        return byteArrayOutputStream.toByteArray();
    }

    public static int decode(byte[] byArray, OutputStream outputStream) throws IOException {
        return encoder.decode(byArray, 0, byArray.length, outputStream);
    }

    public static byte[] decode(String string) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            encoder.decode(string, byteArrayOutputStream);
        } catch (Exception exception) {
            throw new DecoderException("exception decoding URL safe base64 string: " + exception.getMessage(), exception);
        }
        return byteArrayOutputStream.toByteArray();
    }

    public static int decode(String string, OutputStream outputStream) throws IOException {
        return encoder.decode(string, outputStream);
    }
}

