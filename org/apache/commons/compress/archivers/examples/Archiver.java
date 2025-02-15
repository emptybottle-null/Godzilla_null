/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.compress.archivers.examples;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.SeekableByteChannel;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.StandardOpenOption;
import org.apache.commons.compress.archivers.ArchiveEntry;
import org.apache.commons.compress.archivers.ArchiveException;
import org.apache.commons.compress.archivers.ArchiveOutputStream;
import org.apache.commons.compress.archivers.ArchiveStreamFactory;
import org.apache.commons.compress.archivers.examples.CloseableConsumer;
import org.apache.commons.compress.archivers.examples.CloseableConsumerAdapter;
import org.apache.commons.compress.archivers.sevenz.SevenZOutputFile;
import org.apache.commons.compress.archivers.zip.ZipArchiveOutputStream;
import org.apache.commons.compress.utils.IOUtils;

public class Archiver {
    public void create(String format, File target, File directory) throws IOException, ArchiveException {
        if (this.prefersSeekableByteChannel(format)) {
            try (FileChannel c = FileChannel.open(target.toPath(), StandardOpenOption.WRITE, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);){
                this.create(format, c, directory, CloseableConsumer.CLOSING_CONSUMER);
            }
            return;
        }
        try (OutputStream o = Files.newOutputStream(target.toPath(), new OpenOption[0]);){
            this.create(format, o, directory, CloseableConsumer.CLOSING_CONSUMER);
        }
    }

    @Deprecated
    public void create(String format, OutputStream target, File directory) throws IOException, ArchiveException {
        this.create(format, target, directory, CloseableConsumer.NULL_CONSUMER);
    }

    public void create(String format, OutputStream target, File directory, CloseableConsumer closeableConsumer) throws IOException, ArchiveException {
        try (CloseableConsumerAdapter c = new CloseableConsumerAdapter(closeableConsumer);){
            this.create(c.track(new ArchiveStreamFactory().createArchiveOutputStream(format, target)), directory);
        }
    }

    @Deprecated
    public void create(String format, SeekableByteChannel target, File directory) throws IOException, ArchiveException {
        this.create(format, target, directory, CloseableConsumer.NULL_CONSUMER);
    }

    public void create(String format, SeekableByteChannel target, File directory, CloseableConsumer closeableConsumer) throws IOException, ArchiveException {
        block15: {
            try (CloseableConsumerAdapter c = new CloseableConsumerAdapter(closeableConsumer);){
                if (!this.prefersSeekableByteChannel(format)) {
                    this.create(format, c.track(Channels.newOutputStream(target)), directory);
                    break block15;
                }
                if ("zip".equalsIgnoreCase(format)) {
                    this.create(c.track(new ZipArchiveOutputStream(target)), directory);
                    break block15;
                }
                if ("7z".equalsIgnoreCase(format)) {
                    this.create(c.track(new SevenZOutputFile(target)), directory);
                    break block15;
                }
                throw new ArchiveException("Don't know how to handle format " + format);
            }
        }
    }

    public void create(final ArchiveOutputStream target, File directory) throws IOException, ArchiveException {
        this.create(directory, new ArchiveEntryCreator(){

            @Override
            public ArchiveEntry create(File f, String entryName) throws IOException {
                return target.createArchiveEntry(f, entryName);
            }
        }, new ArchiveEntryConsumer(){

            @Override
            public void accept(File source, ArchiveEntry e) throws IOException {
                target.putArchiveEntry(e);
                if (!e.isDirectory()) {
                    try (BufferedInputStream in = new BufferedInputStream(Files.newInputStream(source.toPath(), new OpenOption[0]));){
                        IOUtils.copy(in, target);
                    }
                }
                target.closeArchiveEntry();
            }
        }, new Finisher(){

            @Override
            public void finish() throws IOException {
                target.finish();
            }
        });
    }

    public void create(final SevenZOutputFile target, File directory) throws IOException {
        this.create(directory, new ArchiveEntryCreator(){

            @Override
            public ArchiveEntry create(File f, String entryName) throws IOException {
                return target.createArchiveEntry(f, entryName);
            }
        }, new ArchiveEntryConsumer(){

            @Override
            public void accept(File source, ArchiveEntry e) throws IOException {
                target.putArchiveEntry(e);
                if (!e.isDirectory()) {
                    byte[] buffer = new byte[8024];
                    int n = 0;
                    long count = 0L;
                    try (BufferedInputStream in = new BufferedInputStream(Files.newInputStream(source.toPath(), new OpenOption[0]));){
                        while (-1 != (n = ((InputStream)in).read(buffer))) {
                            target.write(buffer, 0, n);
                            count += (long)n;
                        }
                    }
                }
                target.closeArchiveEntry();
            }
        }, new Finisher(){

            @Override
            public void finish() throws IOException {
                target.finish();
            }
        });
    }

    private boolean prefersSeekableByteChannel(String format) {
        return "zip".equalsIgnoreCase(format) || "7z".equalsIgnoreCase(format);
    }

    private void create(File directory, ArchiveEntryCreator creator, ArchiveEntryConsumer consumer, Finisher finisher) throws IOException {
        this.create("", directory, creator, consumer);
        finisher.finish();
    }

    private void create(String prefix, File directory, ArchiveEntryCreator creator, ArchiveEntryConsumer consumer) throws IOException {
        File[] children = directory.listFiles();
        if (children == null) {
            return;
        }
        for (File f : children) {
            String entryName = prefix + f.getName() + (f.isDirectory() ? "/" : "");
            consumer.accept(f, creator.create(f, entryName));
            if (!f.isDirectory()) continue;
            this.create(entryName, f, creator, consumer);
        }
    }

    private static interface Finisher {
        public void finish() throws IOException;
    }

    private static interface ArchiveEntryConsumer {
        public void accept(File var1, ArchiveEntry var2) throws IOException;
    }

    private static interface ArchiveEntryCreator {
        public ArchiveEntry create(File var1, String var2) throws IOException;
    }
}

