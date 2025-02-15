/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.poifs.nio;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.WritableByteChannel;
import java.util.ArrayList;
import java.util.List;
import org.apache.poi.poifs.nio.CleanerUtil;
import org.apache.poi.poifs.nio.DataSource;
import org.apache.poi.util.IOUtils;
import org.apache.poi.util.POILogFactory;
import org.apache.poi.util.POILogger;

public class FileBackedDataSource
extends DataSource {
    private static final POILogger logger = POILogFactory.getLogger(FileBackedDataSource.class);
    private FileChannel channel;
    private boolean writable;
    private RandomAccessFile srcFile;
    private List<ByteBuffer> buffersToClean = new ArrayList<ByteBuffer>();

    public FileBackedDataSource(File file) throws FileNotFoundException {
        this(FileBackedDataSource.newSrcFile(file, "r"), true);
    }

    public FileBackedDataSource(File file, boolean readOnly) throws FileNotFoundException {
        this(FileBackedDataSource.newSrcFile(file, readOnly ? "r" : "rw"), readOnly);
    }

    public FileBackedDataSource(RandomAccessFile srcFile, boolean readOnly) {
        this(srcFile.getChannel(), readOnly);
        this.srcFile = srcFile;
    }

    public FileBackedDataSource(FileChannel channel, boolean readOnly) {
        this.channel = channel;
        this.writable = !readOnly;
    }

    public boolean isWriteable() {
        return this.writable;
    }

    public FileChannel getChannel() {
        return this.channel;
    }

    @Override
    public ByteBuffer read(int length, long position) throws IOException {
        ByteBuffer dst;
        if (position >= this.size()) {
            throw new IndexOutOfBoundsException("Position " + position + " past the end of the file");
        }
        if (this.writable) {
            dst = this.channel.map(FileChannel.MapMode.READ_WRITE, position, length);
            this.buffersToClean.add(dst);
        } else {
            this.channel.position(position);
            dst = ByteBuffer.allocate(length);
            int worked = IOUtils.readFully(this.channel, dst);
            if (worked == -1) {
                throw new IndexOutOfBoundsException("Position " + position + " past the end of the file");
            }
        }
        dst.position(0);
        return dst;
    }

    @Override
    public void write(ByteBuffer src, long position) throws IOException {
        this.channel.write(src, position);
    }

    @Override
    public void copyTo(OutputStream stream) throws IOException {
        try (WritableByteChannel out = Channels.newChannel(stream);){
            this.channel.transferTo(0L, this.channel.size(), out);
        }
    }

    @Override
    public long size() throws IOException {
        return this.channel.size();
    }

    @Override
    public void close() throws IOException {
        for (ByteBuffer buffer : this.buffersToClean) {
            FileBackedDataSource.unmap(buffer);
        }
        this.buffersToClean.clear();
        if (this.srcFile != null) {
            this.srcFile.close();
        } else {
            this.channel.close();
        }
    }

    private static RandomAccessFile newSrcFile(File file, String mode) throws FileNotFoundException {
        if (!file.exists()) {
            throw new FileNotFoundException(file.toString());
        }
        return new RandomAccessFile(file, mode);
    }

    private static void unmap(ByteBuffer buffer) {
        if (buffer.getClass().getName().endsWith("HeapByteBuffer")) {
            return;
        }
        if (CleanerUtil.UNMAP_SUPPORTED) {
            try {
                CleanerUtil.getCleaner().freeBuffer(buffer);
            } catch (IOException e) {
                logger.log(5, "Failed to unmap the buffer", e);
            }
        } else {
            logger.log(1, CleanerUtil.UNMAP_NOT_SUPPORTED_REASON);
        }
    }
}

