/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.compress.archivers.sevenz;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import org.apache.commons.compress.archivers.sevenz.SevenZArchiveEntry;
import org.apache.commons.compress.archivers.sevenz.SevenZFile;
import org.apache.commons.compress.archivers.sevenz.SevenZMethodConfiguration;

public class CLI {
    public static void main(String[] args) throws Exception {
        if (args.length == 0) {
            CLI.usage();
            return;
        }
        Mode mode = CLI.grabMode(args);
        System.out.println(mode.getMessage() + " " + args[0]);
        File f = new File(args[0]);
        if (!f.isFile()) {
            System.err.println(f + " doesn't exist or is a directory");
        }
        try (SevenZFile archive = new SevenZFile(f);){
            SevenZArchiveEntry ae;
            while ((ae = archive.getNextEntry()) != null) {
                mode.takeAction(archive, ae);
            }
        }
    }

    private static void usage() {
        System.out.println("Parameters: archive-name [list|extract]");
    }

    private static Mode grabMode(String[] args) {
        if (args.length < 2) {
            return Mode.LIST;
        }
        return Enum.valueOf(Mode.class, args[1].toUpperCase());
    }

    private static enum Mode {
        LIST("Analysing"){

            @Override
            public void takeAction(SevenZFile archive, SevenZArchiveEntry entry) {
                System.out.print(entry.getName());
                if (entry.isDirectory()) {
                    System.out.print(" dir");
                } else {
                    System.out.print(" " + entry.getCompressedSize() + "/" + entry.getSize());
                }
                if (entry.getHasLastModifiedDate()) {
                    System.out.print(" " + entry.getLastModifiedDate());
                } else {
                    System.out.print(" no last modified date");
                }
                if (!entry.isDirectory()) {
                    System.out.println(" " + this.getContentMethods(entry));
                } else {
                    System.out.println("");
                }
            }

            private String getContentMethods(SevenZArchiveEntry entry) {
                StringBuilder sb = new StringBuilder();
                boolean first = true;
                for (SevenZMethodConfiguration sevenZMethodConfiguration : entry.getContentMethods()) {
                    if (!first) {
                        sb.append(", ");
                    }
                    first = false;
                    sb.append((Object)sevenZMethodConfiguration.getMethod());
                    if (sevenZMethodConfiguration.getOptions() == null) continue;
                    sb.append("(").append(sevenZMethodConfiguration.getOptions()).append(")");
                }
                return sb.toString();
            }
        }
        ,
        EXTRACT("Extracting"){
            private final byte[] buf = new byte[8192];

            @Override
            public void takeAction(SevenZFile archive, SevenZArchiveEntry entry) throws IOException {
                File outFile = new File(entry.getName());
                if (entry.isDirectory()) {
                    if (!outFile.isDirectory() && !outFile.mkdirs()) {
                        throw new IOException("Cannot create directory " + outFile);
                    }
                    System.out.println("created directory " + outFile);
                    return;
                }
                System.out.println("extracting to " + outFile);
                File parent = outFile.getParentFile();
                if (parent != null && !parent.exists() && !parent.mkdirs()) {
                    throw new IOException("Cannot create " + parent);
                }
                try (OutputStream fos = Files.newOutputStream(outFile.toPath(), new OpenOption[0]);){
                    int bytesRead;
                    long total = entry.getSize();
                    for (long off = 0L; off < total; off += (long)bytesRead) {
                        int toRead = (int)Math.min(total - off, (long)this.buf.length);
                        bytesRead = archive.read(this.buf, 0, toRead);
                        if (bytesRead < 1) {
                            throw new IOException("Reached end of entry " + entry.getName() + " after " + off + " bytes, expected " + total);
                        }
                        fos.write(this.buf, 0, bytesRead);
                    }
                }
            }
        };

        private final String message;

        private Mode(String message) {
            this.message = message;
        }

        public String getMessage() {
            return this.message;
        }

        public abstract void takeAction(SevenZFile var1, SevenZArchiveEntry var2) throws IOException;
    }
}

