/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package javassist;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import javassist.CannotCompileException;
import javassist.ClassClassPath;
import javassist.ClassPath;
import javassist.ClassPathList;
import javassist.DirClassPath;
import javassist.JarClassPath;
import javassist.JarDirClassPath;
import javassist.LoaderClassPath;
import javassist.NotFoundException;
import javassist.bytecode.ClassFile;

final class ClassPoolTail {
    protected ClassPathList pathList = null;

    public String toString() {
        StringBuffer buf = new StringBuffer();
        buf.append("[class path: ");
        ClassPathList list = this.pathList;
        while (list != null) {
            buf.append(list.path.toString());
            buf.append(File.pathSeparatorChar);
            list = list.next;
        }
        buf.append(']');
        return buf.toString();
    }

    public synchronized ClassPath insertClassPath(ClassPath cp) {
        this.pathList = new ClassPathList(cp, this.pathList);
        return cp;
    }

    public synchronized ClassPath appendClassPath(ClassPath cp) {
        ClassPathList tail = new ClassPathList(cp, null);
        ClassPathList list = this.pathList;
        if (list == null) {
            this.pathList = tail;
        } else {
            while (list.next != null) {
                list = list.next;
            }
            list.next = tail;
        }
        return cp;
    }

    public synchronized void removeClassPath(ClassPath cp) {
        ClassPathList list = this.pathList;
        if (list != null) {
            if (list.path == cp) {
                this.pathList = list.next;
            } else {
                while (list.next != null) {
                    if (list.next.path == cp) {
                        list.next = list.next.next;
                        continue;
                    }
                    list = list.next;
                }
            }
        }
    }

    public ClassPath appendSystemPath() {
        if (ClassFile.MAJOR_VERSION < 53) {
            return this.appendClassPath(new ClassClassPath());
        }
        ClassLoader cl = Thread.currentThread().getContextClassLoader();
        return this.appendClassPath(new LoaderClassPath(cl));
    }

    public ClassPath insertClassPath(String pathname) throws NotFoundException {
        return this.insertClassPath(ClassPoolTail.makePathObject(pathname));
    }

    public ClassPath appendClassPath(String pathname) throws NotFoundException {
        return this.appendClassPath(ClassPoolTail.makePathObject(pathname));
    }

    private static ClassPath makePathObject(String pathname) throws NotFoundException {
        String lower = pathname.toLowerCase();
        if (lower.endsWith(".jar") || lower.endsWith(".zip")) {
            return new JarClassPath(pathname);
        }
        int len = pathname.length();
        if (len > 2 && pathname.charAt(len - 1) == '*' && (pathname.charAt(len - 2) == '/' || pathname.charAt(len - 2) == File.separatorChar)) {
            String dir = pathname.substring(0, len - 2);
            return new JarDirClassPath(dir);
        }
        return new DirClassPath(pathname);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    void writeClassfile(String classname, OutputStream out) throws NotFoundException, IOException, CannotCompileException {
        InputStream fin = this.openClassfile(classname);
        if (fin == null) {
            throw new NotFoundException(classname);
        }
        try {
            ClassPoolTail.copyStream(fin, out);
        } finally {
            fin.close();
        }
    }

    InputStream openClassfile(String classname) throws NotFoundException {
        ClassPathList list = this.pathList;
        InputStream ins = null;
        NotFoundException error = null;
        while (list != null) {
            block5: {
                try {
                    ins = list.path.openClassfile(classname);
                } catch (NotFoundException e) {
                    if (error != null) break block5;
                    error = e;
                }
            }
            if (ins == null) {
                list = list.next;
                continue;
            }
            return ins;
        }
        if (error != null) {
            throw error;
        }
        return null;
    }

    public URL find(String classname) {
        ClassPathList list = this.pathList;
        URL url = null;
        while (list != null) {
            url = list.path.find(classname);
            if (url == null) {
                list = list.next;
                continue;
            }
            return url;
        }
        return null;
    }

    public static byte[] readStream(InputStream fin) throws IOException {
        byte[][] bufs = new byte[8][];
        int bufsize = 4096;
        for (int i = 0; i < 8; ++i) {
            bufs[i] = new byte[bufsize];
            int size = 0;
            int len = 0;
            do {
                if ((len = fin.read(bufs[i], size, bufsize - size)) >= 0) continue;
                byte[] result = new byte[bufsize - 4096 + size];
                int s = 0;
                for (int j = 0; j < i; ++j) {
                    System.arraycopy(bufs[j], 0, result, s, s + 4096);
                    s = s + s + 4096;
                }
                System.arraycopy(bufs[i], 0, result, s, size);
                return result;
            } while ((size += len) < bufsize);
            bufsize *= 2;
        }
        throw new IOException("too much data");
    }

    public static void copyStream(InputStream fin, OutputStream fout) throws IOException {
        int bufsize = 4096;
        byte[] buf = null;
        for (int i = 0; i < 64; ++i) {
            if (i < 8) {
                buf = new byte[bufsize *= 2];
            }
            int size = 0;
            int len = 0;
            do {
                if ((len = fin.read(buf, size, bufsize - size)) >= 0) continue;
                fout.write(buf, 0, size);
                return;
            } while ((size += len) < bufsize);
            fout.write(buf);
        }
        throw new IOException("too much data");
    }
}

