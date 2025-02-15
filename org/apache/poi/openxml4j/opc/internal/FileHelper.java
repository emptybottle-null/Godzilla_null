/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.openxml4j.opc.internal;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

public final class FileHelper {
    public static File getDirectory(File f) {
        if (f != null) {
            String path = f.getPath();
            int num2 = path.length();
            while (--num2 >= 0) {
                char ch1 = path.charAt(num2);
                if (ch1 != File.separatorChar) continue;
                return new File(path.substring(0, num2));
            }
        }
        return null;
    }

    public static void copyFile(File in, File out) throws IOException {
        try (FileInputStream fis = new FileInputStream(in);
             FileOutputStream fos = new FileOutputStream(out);
             FileChannel sourceChannel = fis.getChannel();
             FileChannel destinationChannel = fos.getChannel();){
            sourceChannel.transferTo(0L, sourceChannel.size(), destinationChannel);
        }
    }

    public static String getFilename(File file) {
        if (file != null) {
            int len;
            String path = file.getPath();
            int num2 = len = path.length();
            while (--num2 >= 0) {
                char ch1 = path.charAt(num2);
                if (ch1 != File.separatorChar) continue;
                return path.substring(num2 + 1, len);
            }
        }
        return "";
    }
}

