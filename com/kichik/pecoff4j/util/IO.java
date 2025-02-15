/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.kichik.pecoff4j.util;

import com.kichik.pecoff4j.util.FindFilesCallback;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;
import java.util.HashSet;
import java.util.Set;

public class IO {
    public static byte[] toBytes(File f) throws IOException {
        byte[] b = new byte[(int)f.length()];
        FileInputStream fis = new FileInputStream(f);
        fis.read(b);
        return b;
    }

    public static byte[] toBytes(InputStream is) throws IOException {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        IO.copy(is, bos, true);
        return bos.toByteArray();
    }

    public static void findFiles(File dir, FilenameFilter filter, FindFilesCallback callback) {
        File[] f;
        for (File fs : f = dir.listFiles()) {
            if (fs.isDirectory()) {
                IO.findFiles(fs, filter, callback);
                continue;
            }
            if (filter != null && !filter.accept(dir, fs.getName())) continue;
            callback.fileFound(fs);
        }
    }

    public static File[] findFiles(File dir, FilenameFilter filter) {
        HashSet<File> files = new HashSet<File>();
        IO.findFiles(dir, filter, files);
        return files.toArray(new File[0]);
    }

    private static void findFiles(File dir, FilenameFilter filter, Set<File> files) {
        File[] f = dir.listFiles();
        if (f == null) {
            return;
        }
        for (File ff : f) {
            if (ff.isDirectory()) {
                IO.findFiles(ff, filter, files);
                continue;
            }
            if (!filter.accept(ff.getParentFile(), ff.getName())) continue;
            files.add(ff);
        }
    }

    public static void copy(Reader r, Writer w, boolean close) throws IOException {
        char[] buf = new char[4096];
        int len = 0;
        while ((len = r.read(buf)) > 0) {
            w.write(buf, 0, len);
        }
        if (close) {
            r.close();
            w.close();
        }
    }

    public static void copy(InputStream r, OutputStream w, boolean close) throws IOException {
        byte[] buf = new byte[4096];
        int len = 0;
        while ((len = r.read(buf)) > 0) {
            w.write(buf, 0, len);
        }
        if (close) {
            r.close();
            w.close();
        }
    }
}

