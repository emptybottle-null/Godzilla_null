/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.compress.archivers.zip;

import java.io.Closeable;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicBoolean;
import org.apache.commons.compress.archivers.zip.StreamCompressor;
import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;
import org.apache.commons.compress.archivers.zip.ZipArchiveEntryRequest;
import org.apache.commons.compress.archivers.zip.ZipArchiveOutputStream;
import org.apache.commons.compress.parallel.FileBasedScatterGatherBackingStore;
import org.apache.commons.compress.parallel.ScatterGatherBackingStore;
import org.apache.commons.compress.utils.BoundedInputStream;

public class ScatterZipOutputStream
implements Closeable {
    private final Queue<CompressedEntry> items = new ConcurrentLinkedQueue<CompressedEntry>();
    private final ScatterGatherBackingStore backingStore;
    private final StreamCompressor streamCompressor;
    private AtomicBoolean isClosed = new AtomicBoolean();
    private ZipEntryWriter zipEntryWriter = null;

    public ScatterZipOutputStream(ScatterGatherBackingStore backingStore, StreamCompressor streamCompressor) {
        this.backingStore = backingStore;
        this.streamCompressor = streamCompressor;
    }

    public void addArchiveEntry(ZipArchiveEntryRequest zipArchiveEntryRequest) throws IOException {
        try (InputStream payloadStream = zipArchiveEntryRequest.getPayloadStream();){
            this.streamCompressor.deflate(payloadStream, zipArchiveEntryRequest.getMethod());
        }
        this.items.add(new CompressedEntry(zipArchiveEntryRequest, this.streamCompressor.getCrc32(), this.streamCompressor.getBytesWrittenForLastEntry(), this.streamCompressor.getBytesRead()));
    }

    public void writeTo(ZipArchiveOutputStream target) throws IOException {
        this.backingStore.closeForWriting();
        try (InputStream data = this.backingStore.getInputStream();){
            for (CompressedEntry compressedEntry : this.items) {
                BoundedInputStream rawStream = new BoundedInputStream(data, compressedEntry.compressedSize);
                Throwable throwable = null;
                try {
                    target.addRawArchiveEntry(compressedEntry.transferToArchiveEntry(), rawStream);
                } catch (Throwable throwable2) {
                    throwable = throwable2;
                    throw throwable2;
                } finally {
                    if (rawStream == null) continue;
                    if (throwable != null) {
                        try {
                            rawStream.close();
                        } catch (Throwable throwable3) {
                            throwable.addSuppressed(throwable3);
                        }
                        continue;
                    }
                    rawStream.close();
                }
            }
        }
    }

    public ZipEntryWriter zipEntryWriter() throws IOException {
        if (this.zipEntryWriter == null) {
            this.zipEntryWriter = new ZipEntryWriter(this);
        }
        return this.zipEntryWriter;
    }

    @Override
    public void close() throws IOException {
        if (!this.isClosed.compareAndSet(false, true)) {
            return;
        }
        try {
            if (this.zipEntryWriter != null) {
                this.zipEntryWriter.close();
            }
            this.backingStore.close();
        } finally {
            this.streamCompressor.close();
        }
    }

    public static ScatterZipOutputStream fileBased(File file) throws FileNotFoundException {
        return ScatterZipOutputStream.fileBased(file, -1);
    }

    public static ScatterZipOutputStream fileBased(File file, int compressionLevel) throws FileNotFoundException {
        FileBasedScatterGatherBackingStore bs = new FileBasedScatterGatherBackingStore(file);
        StreamCompressor sc = StreamCompressor.create(compressionLevel, bs);
        return new ScatterZipOutputStream(bs, sc);
    }

    public static class ZipEntryWriter
    implements Closeable {
        private final Iterator<CompressedEntry> itemsIterator;
        private final InputStream itemsIteratorData;

        public ZipEntryWriter(ScatterZipOutputStream scatter) throws IOException {
            scatter.backingStore.closeForWriting();
            this.itemsIterator = scatter.items.iterator();
            this.itemsIteratorData = scatter.backingStore.getInputStream();
        }

        @Override
        public void close() throws IOException {
            if (this.itemsIteratorData != null) {
                this.itemsIteratorData.close();
            }
        }

        public void writeNextZipEntry(ZipArchiveOutputStream target) throws IOException {
            CompressedEntry compressedEntry = this.itemsIterator.next();
            try (BoundedInputStream rawStream = new BoundedInputStream(this.itemsIteratorData, compressedEntry.compressedSize);){
                target.addRawArchiveEntry(compressedEntry.transferToArchiveEntry(), rawStream);
            }
        }
    }

    private static class CompressedEntry {
        final ZipArchiveEntryRequest zipArchiveEntryRequest;
        final long crc;
        final long compressedSize;
        final long size;

        public CompressedEntry(ZipArchiveEntryRequest zipArchiveEntryRequest, long crc, long compressedSize, long size) {
            this.zipArchiveEntryRequest = zipArchiveEntryRequest;
            this.crc = crc;
            this.compressedSize = compressedSize;
            this.size = size;
        }

        public ZipArchiveEntry transferToArchiveEntry() {
            ZipArchiveEntry entry = this.zipArchiveEntryRequest.getZipArchiveEntry();
            entry.setCompressedSize(this.compressedSize);
            entry.setSize(this.size);
            entry.setCrc(this.crc);
            entry.setMethod(this.zipArchiveEntryRequest.getMethod());
            return entry;
        }
    }
}

