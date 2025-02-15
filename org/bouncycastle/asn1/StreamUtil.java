/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.asn1;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.channels.FileChannel;
import org.bouncycastle.asn1.ASN1InputStream;
import org.bouncycastle.asn1.LimitedInputStream;

class StreamUtil {
    private static final long MAX_MEMORY = Runtime.getRuntime().maxMemory();

    StreamUtil() {
    }

    static int findLimit(InputStream inputStream) {
        if (inputStream instanceof LimitedInputStream) {
            return ((LimitedInputStream)inputStream).getRemaining();
        }
        if (inputStream instanceof ASN1InputStream) {
            return ((ASN1InputStream)inputStream).getLimit();
        }
        if (inputStream instanceof ByteArrayInputStream) {
            return ((ByteArrayInputStream)inputStream).available();
        }
        if (inputStream instanceof FileInputStream) {
            try {
                long l;
                FileChannel fileChannel = ((FileInputStream)inputStream).getChannel();
                long l2 = l = fileChannel != null ? fileChannel.size() : Integer.MAX_VALUE;
                if (l < Integer.MAX_VALUE) {
                    return (int)l;
                }
            } catch (IOException iOException) {
                // empty catch block
            }
        }
        if (MAX_MEMORY > Integer.MAX_VALUE) {
            return Integer.MAX_VALUE;
        }
        return (int)MAX_MEMORY;
    }

    static int calculateBodyLength(int n) {
        int n2 = 1;
        if (n > 127) {
            int n3 = 1;
            int n4 = n;
            while ((n4 >>>= 8) != 0) {
                ++n3;
            }
            for (int i = (n3 - 1) * 8; i >= 0; i -= 8) {
                ++n2;
            }
        }
        return n2;
    }

    static int calculateTagLength(int n) throws IOException {
        int n2 = 1;
        if (n >= 31) {
            if (n < 128) {
                ++n2;
            } else {
                byte[] byArray = new byte[5];
                int n3 = byArray.length;
                byArray[--n3] = (byte)(n & 0x7F);
                do {
                    byArray[--n3] = (byte)((n >>= 7) & 0x7F | 0x80);
                } while (n > 127);
                n2 += byArray.length - n3;
            }
        }
        return n2;
    }
}

