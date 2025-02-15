/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.xmlbeans.impl.common;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.jar.JarEntry;
import java.util.jar.JarInputStream;
import java.util.jar.JarOutputStream;

public class JarHelper {
    private static final int BUFFER_SIZE = 2156;
    private byte[] mBuffer = new byte[2156];
    private int mByteCount = 0;
    private boolean mVerbose = false;
    private String mDestJarName = "";
    private static final char SEP = '/';

    public void jarDir(File dirOrFile2Jar, File destJar) throws IOException {
        if (dirOrFile2Jar == null || destJar == null) {
            throw new IllegalArgumentException();
        }
        this.mDestJarName = destJar.getCanonicalPath();
        FileOutputStream fout = new FileOutputStream(destJar);
        JarOutputStream jout = new JarOutputStream(fout);
        try {
            this.jarDir(dirOrFile2Jar, jout, null);
        } catch (IOException ioe) {
            throw ioe;
        } finally {
            jout.close();
            fout.close();
        }
    }

    public void unjarDir(File jarFile, File destDir) throws IOException {
        Object dest = null;
        FileInputStream fis = new FileInputStream(jarFile);
        this.unjar(fis, destDir);
    }

    public void unjar(InputStream in, File destDir) throws IOException {
        JarEntry entry;
        BufferedOutputStream dest = null;
        JarInputStream jis = new JarInputStream(in);
        while ((entry = jis.getNextJarEntry()) != null) {
            int count;
            if (entry.isDirectory()) {
                File dir = new File(destDir, entry.getName());
                dir.mkdir();
                if (entry.getTime() == -1L) continue;
                dir.setLastModified(entry.getTime());
                continue;
            }
            byte[] data = new byte[2156];
            File destFile = new File(destDir, entry.getName());
            if (this.mVerbose) {
                System.out.println("unjarring " + destFile + " from " + entry.getName());
            }
            FileOutputStream fos = new FileOutputStream(destFile);
            dest = new BufferedOutputStream(fos, 2156);
            while ((count = jis.read(data, 0, 2156)) != -1) {
                dest.write(data, 0, count);
            }
            dest.flush();
            dest.close();
            if (entry.getTime() == -1L) continue;
            destFile.setLastModified(entry.getTime());
        }
        jis.close();
    }

    public void setVerbose(boolean b) {
        this.mVerbose = b;
    }

    private void jarDir(File dirOrFile2jar, JarOutputStream jos, String path) throws IOException {
        if (this.mVerbose) {
            System.out.println("checking " + dirOrFile2jar);
        }
        if (dirOrFile2jar.isDirectory()) {
            String subPath;
            String[] dirList = dirOrFile2jar.list();
            String string = subPath = path == null ? "" : path + dirOrFile2jar.getName() + '/';
            if (path != null) {
                JarEntry je = new JarEntry(subPath);
                je.setTime(dirOrFile2jar.lastModified());
                jos.putNextEntry(je);
                jos.flush();
                jos.closeEntry();
            }
            for (int i = 0; i < dirList.length; ++i) {
                File f = new File(dirOrFile2jar, dirList[i]);
                this.jarDir(f, jos, subPath);
            }
        } else {
            if (dirOrFile2jar.getCanonicalPath().equals(this.mDestJarName)) {
                if (this.mVerbose) {
                    System.out.println("skipping " + dirOrFile2jar.getPath());
                }
                return;
            }
            if (this.mVerbose) {
                System.out.println("adding " + dirOrFile2jar.getPath());
            }
            FileInputStream fis = new FileInputStream(dirOrFile2jar);
            try {
                JarEntry entry = new JarEntry(path + dirOrFile2jar.getName());
                entry.setTime(dirOrFile2jar.lastModified());
                jos.putNextEntry(entry);
                while ((this.mByteCount = fis.read(this.mBuffer)) != -1) {
                    jos.write(this.mBuffer, 0, this.mByteCount);
                    if (!this.mVerbose) continue;
                    System.out.println("wrote " + this.mByteCount + " bytes");
                }
                jos.flush();
                jos.closeEntry();
            } catch (IOException ioe) {
                throw ioe;
            } finally {
                fis.close();
            }
        }
    }

    public static void main(String[] args) throws IOException {
        if (args.length < 2) {
            System.err.println("Usage: JarHelper jarname.jar directory");
            return;
        }
        JarHelper jarHelper = new JarHelper();
        jarHelper.mVerbose = true;
        File destJar = new File(args[0]);
        File dirOrFile2Jar = new File(args[1]);
        jarHelper.jarDir(dirOrFile2Jar, destJar);
    }
}

