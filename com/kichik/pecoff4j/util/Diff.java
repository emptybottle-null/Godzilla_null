/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.kichik.pecoff4j.util;

import com.kichik.pecoff4j.util.HexDump;
import com.kichik.pecoff4j.util.IO;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;

public class Diff {
    public static boolean equals(File f1, File f2) throws IOException {
        return Diff.equals(IO.toBytes(f1), IO.toBytes(f2));
    }

    public static boolean equals(byte[] b1, byte[] b2) {
        return Arrays.equals(b1, b2);
    }

    public static boolean equals(byte[] b1, byte[] b2, boolean ignoreLength) {
        if (ignoreLength) {
            for (int i = 0; i < b1.length && i < b2.length; ++i) {
                if (b1[i] == b2[i]) continue;
                return false;
            }
            return true;
        }
        return Arrays.equals(b1, b2);
    }

    public static boolean findDiff(byte[] b1, byte[] b2, boolean ignoreLength) {
        boolean diff = false;
        if (b1.length != b2.length && !ignoreLength) {
            System.out.println("Different lengths: " + Integer.toHexString(b1.length) + ", " + Integer.toHexString(b2.length));
            diff = true;
        }
        for (int i = 0; i < b1.length && i < b2.length; ++i) {
            if (b1[i] == b2[i]) continue;
            int p = i;
            if (p < 0) {
                p = 0;
            }
            System.out.println("Diff at " + Integer.toHexString(i));
            HexDump.dump(b1, p, 100);
            System.out.println("-----");
            HexDump.dump(b2, p, 100);
            return true;
        }
        return diff;
    }
}

