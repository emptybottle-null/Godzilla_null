/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.compress.archivers;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.util.Enumeration;
import org.apache.commons.compress.archivers.ArchiveEntry;
import org.apache.commons.compress.archivers.ArchiveException;
import org.apache.commons.compress.archivers.ArchiveInputStream;
import org.apache.commons.compress.archivers.ArchiveStreamFactory;
import org.apache.commons.compress.archivers.sevenz.SevenZArchiveEntry;
import org.apache.commons.compress.archivers.sevenz.SevenZFile;
import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;
import org.apache.commons.compress.archivers.zip.ZipFile;

public final class Lister {
    private static final ArchiveStreamFactory factory = new ArchiveStreamFactory();

    public static void main(String[] args) throws Exception {
        String format;
        if (args.length == 0) {
            Lister.usage();
            return;
        }
        System.out.println("Analysing " + args[0]);
        File f = new File(args[0]);
        if (!f.isFile()) {
            System.err.println(f + " doesn't exist or is a directory");
        }
        String string = format = args.length > 1 ? args[1] : Lister.detectFormat(f);
        if ("7z".equalsIgnoreCase(format)) {
            Lister.list7z(f);
        } else if ("zipfile".equals(format)) {
            Lister.listZipUsingZipFile(f);
        } else {
            Lister.listStream(f, args);
        }
    }

    private static void listStream(File f, String[] args) throws ArchiveException, IOException {
        try (BufferedInputStream fis = new BufferedInputStream(Files.newInputStream(f.toPath(), new OpenOption[0]));
             ArchiveInputStream ais = Lister.createArchiveInputStream(args, fis);){
            ArchiveEntry ae;
            System.out.println("Created " + ais.toString());
            while ((ae = ais.getNextEntry()) != null) {
                System.out.println(ae.getName());
            }
        }
    }

    private static ArchiveInputStream createArchiveInputStream(String[] args, InputStream fis) throws ArchiveException {
        if (args.length > 1) {
            return factory.createArchiveInputStream(args[1], fis);
        }
        return factory.createArchiveInputStream(fis);
    }

    private static String detectFormat(File f) throws ArchiveException, IOException {
        try (BufferedInputStream fis = new BufferedInputStream(Files.newInputStream(f.toPath(), new OpenOption[0]));){
            String string = ArchiveStreamFactory.detect(fis);
            return string;
        }
    }

    private static void list7z(File f) throws ArchiveException, IOException {
        try (SevenZFile z = new SevenZFile(f);){
            SevenZArchiveEntry ae;
            System.out.println("Created " + z.toString());
            while ((ae = z.getNextEntry()) != null) {
                String name = ae.getName() == null ? z.getDefaultName() + " (entry name was null)" : ae.getName();
                System.out.println(name);
            }
        }
    }

    private static void listZipUsingZipFile(File f) throws ArchiveException, IOException {
        try (ZipFile z = new ZipFile(f);){
            System.out.println("Created " + z.toString());
            Enumeration<ZipArchiveEntry> en = z.getEntries();
            while (en.hasMoreElements()) {
                System.out.println(en.nextElement().getName());
            }
        }
    }

    private static void usage() {
        System.out.println("Parameters: archive-name [archive-type]\n");
        System.out.println("the magic archive-type 'zipfile' prefers ZipFile over ZipArchiveInputStream");
    }
}

