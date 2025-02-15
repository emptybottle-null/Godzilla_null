/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.springframework.cglib.transform;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.util.zip.CRC32;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;
import org.springframework.asm.Attribute;
import org.springframework.asm.ClassReader;
import org.springframework.cglib.core.ClassNameReader;
import org.springframework.cglib.core.DebuggingClassWriter;
import org.springframework.cglib.transform.AbstractProcessTask;
import org.springframework.cglib.transform.ClassReaderGenerator;
import org.springframework.cglib.transform.ClassTransformer;
import org.springframework.cglib.transform.TransformingClassGenerator;

public abstract class AbstractTransformTask
extends AbstractProcessTask {
    private static final int ZIP_MAGIC = 1347093252;
    private static final int CLASS_MAGIC = -889275714;
    private boolean verbose;

    public void setVerbose(boolean verbose) {
        this.verbose = verbose;
    }

    protected abstract ClassTransformer getClassTransformer(String[] var1);

    protected Attribute[] attributes() {
        return null;
    }

    protected void processFile(File file) throws Exception {
        if (this.isClassFile(file)) {
            this.processClassFile(file);
        } else if (this.isJarFile(file)) {
            this.processJarFile(file);
        } else {
            this.log("ignoring " + file.toURI(), 1);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private void processClassFile(File file) throws Exception, FileNotFoundException, IOException, MalformedURLException {
        ClassReader reader = AbstractTransformTask.getClassReader(file);
        String[] name = ClassNameReader.getClassInfo(reader);
        DebuggingClassWriter w = new DebuggingClassWriter(2);
        ClassTransformer t = this.getClassTransformer(name);
        if (t != null) {
            if (this.verbose) {
                this.log("processing " + file.toURI());
            }
            new TransformingClassGenerator(new ClassReaderGenerator(AbstractTransformTask.getClassReader(file), this.attributes(), this.getFlags()), t).generateClass(w);
            FileOutputStream fos = new FileOutputStream(file);
            try {
                fos.write(w.toByteArray());
            } finally {
                fos.close();
            }
        }
    }

    protected int getFlags() {
        return 0;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private static ClassReader getClassReader(File file) throws Exception {
        BufferedInputStream in = new BufferedInputStream(new FileInputStream(file));
        try {
            ClassReader r;
            ClassReader classReader = r = new ClassReader(in);
            return classReader;
        } finally {
            ((InputStream)in).close();
        }
    }

    protected boolean isClassFile(File file) throws IOException {
        return this.checkMagic(file, -889275714L);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    protected void processJarFile(File file) throws Exception {
        block19: {
            if (this.verbose) {
                this.log("processing " + file.toURI());
            }
            File tempFile = File.createTempFile(file.getName(), null, new File(file.getAbsoluteFile().getParent()));
            try {
                ZipInputStream zip = new ZipInputStream(new FileInputStream(file));
                try {
                    FileOutputStream fout = new FileOutputStream(tempFile);
                    try {
                        ZipEntry entry;
                        ZipOutputStream out = new ZipOutputStream(fout);
                        while ((entry = zip.getNextEntry()) != null) {
                            byte[] bytes = this.getBytes(zip);
                            if (!entry.isDirectory()) {
                                DataInputStream din = new DataInputStream(new ByteArrayInputStream(bytes));
                                if (din.readInt() == -889275714) {
                                    bytes = this.process(bytes);
                                } else if (this.verbose) {
                                    this.log("ignoring " + entry.toString());
                                }
                            }
                            ZipEntry outEntry = new ZipEntry(entry.getName());
                            outEntry.setMethod(entry.getMethod());
                            outEntry.setComment(entry.getComment());
                            outEntry.setSize(bytes.length);
                            if (outEntry.getMethod() == 0) {
                                CRC32 crc = new CRC32();
                                crc.update(bytes);
                                outEntry.setCrc(crc.getValue());
                                outEntry.setCompressedSize(bytes.length);
                            }
                            out.putNextEntry(outEntry);
                            out.write(bytes);
                            out.closeEntry();
                            zip.closeEntry();
                        }
                        out.close();
                    } finally {
                        fout.close();
                    }
                } finally {
                    zip.close();
                }
                if (file.delete()) {
                    File newFile = new File(tempFile.getAbsolutePath());
                    if (!newFile.renameTo(file)) {
                        throw new IOException("can not rename " + tempFile + " to " + file);
                    }
                    break block19;
                }
                throw new IOException("can not delete " + file);
            } finally {
                tempFile.delete();
            }
        }
    }

    private byte[] process(byte[] bytes) throws Exception {
        ClassReader reader = new ClassReader(new ByteArrayInputStream(bytes));
        String[] name = ClassNameReader.getClassInfo(reader);
        DebuggingClassWriter w = new DebuggingClassWriter(2);
        ClassTransformer t = this.getClassTransformer(name);
        if (t != null) {
            if (this.verbose) {
                this.log("processing " + name[0]);
            }
            new TransformingClassGenerator(new ClassReaderGenerator(new ClassReader(new ByteArrayInputStream(bytes)), this.attributes(), this.getFlags()), t).generateClass(w);
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            out.write(w.toByteArray());
            return out.toByteArray();
        }
        return bytes;
    }

    private byte[] getBytes(ZipInputStream zip) throws IOException {
        int b;
        ByteArrayOutputStream bout = new ByteArrayOutputStream();
        BufferedInputStream in = new BufferedInputStream(zip);
        while ((b = ((InputStream)in).read()) != -1) {
            bout.write(b);
        }
        return bout.toByteArray();
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private boolean checkMagic(File file, long magic) throws IOException {
        DataInputStream in = new DataInputStream(new FileInputStream(file));
        try {
            int m = in.readInt();
            boolean bl = magic == (long)m;
            return bl;
        } finally {
            in.close();
        }
    }

    protected boolean isJarFile(File file) throws IOException {
        return this.checkMagic(file, 1347093252L);
    }
}

