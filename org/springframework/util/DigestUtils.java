/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.springframework.util;

import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import org.springframework.util.UpdateMessageDigestInputStream;

public abstract class DigestUtils {
    private static final String MD5_ALGORITHM_NAME = "MD5";
    private static final char[] HEX_CHARS = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

    public static byte[] md5Digest(byte[] bytes) {
        return DigestUtils.digest(MD5_ALGORITHM_NAME, bytes);
    }

    public static byte[] md5Digest(InputStream inputStream) throws IOException {
        return DigestUtils.digest(MD5_ALGORITHM_NAME, inputStream);
    }

    public static String md5DigestAsHex(byte[] bytes) {
        return DigestUtils.digestAsHexString(MD5_ALGORITHM_NAME, bytes);
    }

    public static String md5DigestAsHex(InputStream inputStream) throws IOException {
        return DigestUtils.digestAsHexString(MD5_ALGORITHM_NAME, inputStream);
    }

    public static StringBuilder appendMd5DigestAsHex(byte[] bytes, StringBuilder builder) {
        return DigestUtils.appendDigestAsHex(MD5_ALGORITHM_NAME, bytes, builder);
    }

    public static StringBuilder appendMd5DigestAsHex(InputStream inputStream, StringBuilder builder) throws IOException {
        return DigestUtils.appendDigestAsHex(MD5_ALGORITHM_NAME, inputStream, builder);
    }

    private static MessageDigest getDigest(String algorithm) {
        try {
            return MessageDigest.getInstance(algorithm);
        } catch (NoSuchAlgorithmException ex) {
            throw new IllegalStateException("Could not find MessageDigest with algorithm \"" + algorithm + "\"", ex);
        }
    }

    private static byte[] digest(String algorithm, byte[] bytes) {
        return DigestUtils.getDigest(algorithm).digest(bytes);
    }

    private static byte[] digest(String algorithm, InputStream inputStream) throws IOException {
        MessageDigest messageDigest = DigestUtils.getDigest(algorithm);
        if (inputStream instanceof UpdateMessageDigestInputStream) {
            ((UpdateMessageDigestInputStream)inputStream).updateMessageDigest(messageDigest);
            return messageDigest.digest();
        }
        byte[] buffer = new byte[4096];
        int bytesRead = -1;
        while ((bytesRead = inputStream.read(buffer)) != -1) {
            messageDigest.update(buffer, 0, bytesRead);
        }
        return messageDigest.digest();
    }

    private static String digestAsHexString(String algorithm, byte[] bytes) {
        char[] hexDigest = DigestUtils.digestAsHexChars(algorithm, bytes);
        return new String(hexDigest);
    }

    private static String digestAsHexString(String algorithm, InputStream inputStream) throws IOException {
        char[] hexDigest = DigestUtils.digestAsHexChars(algorithm, inputStream);
        return new String(hexDigest);
    }

    private static StringBuilder appendDigestAsHex(String algorithm, byte[] bytes, StringBuilder builder) {
        char[] hexDigest = DigestUtils.digestAsHexChars(algorithm, bytes);
        return builder.append(hexDigest);
    }

    private static StringBuilder appendDigestAsHex(String algorithm, InputStream inputStream, StringBuilder builder) throws IOException {
        char[] hexDigest = DigestUtils.digestAsHexChars(algorithm, inputStream);
        return builder.append(hexDigest);
    }

    private static char[] digestAsHexChars(String algorithm, byte[] bytes) {
        byte[] digest = DigestUtils.digest(algorithm, bytes);
        return DigestUtils.encodeHex(digest);
    }

    private static char[] digestAsHexChars(String algorithm, InputStream inputStream) throws IOException {
        byte[] digest = DigestUtils.digest(algorithm, inputStream);
        return DigestUtils.encodeHex(digest);
    }

    private static char[] encodeHex(byte[] bytes) {
        char[] chars = new char[32];
        for (int i = 0; i < chars.length; i += 2) {
            byte b = bytes[i / 2];
            chars[i] = HEX_CHARS[b >>> 4 & 0xF];
            chars[i + 1] = HEX_CHARS[b & 0xF];
        }
        return chars;
    }
}

