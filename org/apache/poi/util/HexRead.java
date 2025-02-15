/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.util;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import org.apache.poi.util.StringUtil;

public class HexRead {
    public static byte[] readData(String filename) throws IOException {
        File file = new File(filename);
        try (FileInputStream stream = new FileInputStream(file);){
            byte[] byArray = HexRead.readData((InputStream)stream, -1);
            return byArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static byte[] readData(InputStream stream, String section) throws IOException {
        try {
            StringBuilder sectionText = new StringBuilder();
            boolean inSection = false;
            int c = stream.read();
            while (c != -1) {
                switch (c) {
                    case 91: {
                        inSection = true;
                        break;
                    }
                    case 10: 
                    case 13: {
                        inSection = false;
                        sectionText = new StringBuilder();
                        break;
                    }
                    case 93: {
                        inSection = false;
                        if (sectionText.toString().equals(section)) {
                            byte[] byArray = HexRead.readData(stream, 91);
                            return byArray;
                        }
                        sectionText = new StringBuilder();
                        break;
                    }
                    default: {
                        if (!inSection) break;
                        sectionText.append((char)c);
                    }
                }
                c = stream.read();
            }
        } finally {
            stream.close();
        }
        throw new IOException("Section '" + section + "' not found");
    }

    public static byte[] readData(String filename, String section) throws IOException {
        return HexRead.readData((InputStream)new FileInputStream(filename), section);
    }

    public static byte[] readData(InputStream stream, int eofChar) throws IOException {
        int characterCount = 0;
        byte b = 0;
        ArrayList<Byte> bytes = new ArrayList<Byte>();
        int a = 87;
        int A2 = 55;
        while (true) {
            int count = stream.read();
            int digitValue = -1;
            if (48 <= count && count <= 57) {
                digitValue = count - 48;
            } else if (65 <= count && count <= 70) {
                digitValue = count - 55;
            } else if (97 <= count && count <= 102) {
                digitValue = count - 87;
            } else if (35 == count) {
                HexRead.readToEOL(stream);
            } else if (-1 == count || eofChar == count) break;
            if (digitValue == -1) continue;
            b = (byte)(b << 4);
            b = (byte)(b + (byte)digitValue);
            if (++characterCount != 2) continue;
            bytes.add(b);
            characterCount = 0;
            b = 0;
        }
        Byte[] polished = bytes.toArray(new Byte[0]);
        byte[] rval = new byte[polished.length];
        for (int j = 0; j < polished.length; ++j) {
            rval[j] = polished[j];
        }
        return rval;
    }

    public static byte[] readFromString(String data) {
        try {
            return HexRead.readData((InputStream)new ByteArrayInputStream(data.getBytes(StringUtil.UTF8)), -1);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void readToEOL(InputStream stream) throws IOException {
        int c = stream.read();
        while (c != -1 && c != 10 && c != 13) {
            c = stream.read();
        }
    }
}

