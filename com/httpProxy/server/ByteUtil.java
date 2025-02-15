/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.httpProxy.server;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;

public class ByteUtil {
    public static byte[] readNextLine(InputStream inputStream, boolean appendCRLF) {
        byte last = 0;
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(1024);
        byte current = 0;
        try {
            while ((current = (byte)inputStream.read()) != -1) {
                if (current == 13) {
                    last = current;
                    continue;
                }
                if (last == 13 && current == 10) {
                    if (appendCRLF) {
                        outputStream.write(13);
                        outputStream.write(10);
                    }
                    break;
                }
                if (last == 13) {
                    outputStream.write(last);
                } else {
                    outputStream.write(current);
                }
                last = current;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return outputStream.toByteArray();
    }

    public static byte[] readNextLine(InputStream inputStream) {
        return ByteUtil.readNextLine(inputStream, false);
    }

    public static byte[] readInputStream(InputStream inputStream) {
        ByteArrayOutputStream bos;
        block3: {
            byte[] temp = new byte[5120];
            int readOneNum = 0;
            bos = new ByteArrayOutputStream();
            try {
                while ((readOneNum = inputStream.read(temp)) != -1) {
                    bos.write(temp, 0, readOneNum);
                }
            } catch (Exception e) {
                if (bos.size() != 0) break block3;
                throw new RuntimeException(e);
            }
        }
        return bos.toByteArray();
    }
}

