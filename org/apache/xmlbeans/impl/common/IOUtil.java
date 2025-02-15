/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.xmlbeans.impl.common;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;
import java.net.URI;
import java.nio.channels.FileChannel;

public class IOUtil {
    public static void copyCompletely(InputStream input, OutputStream output) throws IOException {
        int length;
        if (output instanceof FileOutputStream && input instanceof FileInputStream) {
            try {
                FileChannel target = ((FileOutputStream)output).getChannel();
                FileChannel source = ((FileInputStream)input).getChannel();
                source.transferTo(0L, Integer.MAX_VALUE, target);
                source.close();
                target.close();
                return;
            } catch (Exception e) {
                // empty catch block
            }
        }
        byte[] buf = new byte[8192];
        while ((length = input.read(buf)) >= 0) {
            output.write(buf, 0, length);
        }
        try {
            input.close();
        } catch (IOException ignore) {
            // empty catch block
        }
        try {
            output.close();
        } catch (IOException iOException) {
            // empty catch block
        }
    }

    public static void copyCompletely(Reader input, Writer output) throws IOException {
        int length;
        char[] buf = new char[8192];
        while ((length = input.read(buf)) >= 0) {
            output.write(buf, 0, length);
        }
        try {
            input.close();
        } catch (IOException ignore) {
            // empty catch block
        }
        try {
            output.close();
        } catch (IOException iOException) {
            // empty catch block
        }
    }

    public static void copyCompletely(URI input, URI output) throws IOException {
        try {
            InputStream in = null;
            try {
                File f = new File(input);
                if (f.exists()) {
                    in = new FileInputStream(f);
                }
            } catch (Exception notAFile) {
                // empty catch block
            }
            File out = new File(output);
            File dir = out.getParentFile();
            dir.mkdirs();
            if (in == null) {
                in = input.toURL().openStream();
            }
            IOUtil.copyCompletely(in, new FileOutputStream(out));
        } catch (IllegalArgumentException e) {
            throw new IOException("Cannot copy to " + output);
        }
    }

    public static File createDir(File rootdir, String subdir) {
        boolean created;
        File newdir = subdir == null ? rootdir : new File(rootdir, subdir);
        boolean bl = created = newdir.exists() && newdir.isDirectory() || newdir.mkdirs();
        assert (created) : "Could not create " + newdir.getAbsolutePath();
        return newdir;
    }
}

