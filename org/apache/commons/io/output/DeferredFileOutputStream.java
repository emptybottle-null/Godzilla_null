/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.io.output;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.io.output.ByteArrayOutputStream;
import org.apache.commons.io.output.ThresholdingOutputStream;

public class DeferredFileOutputStream
extends ThresholdingOutputStream {
    private ByteArrayOutputStream memoryOutputStream;
    private OutputStream currentOutputStream;
    private File outputFile;
    private final String prefix;
    private final String suffix;
    private final File directory;
    private boolean closed;

    public DeferredFileOutputStream(int threshold, File outputFile) {
        this(threshold, outputFile, null, null, null, 1024);
    }

    private DeferredFileOutputStream(int threshold, File outputFile, String prefix, String suffix, File directory, int initialBufferSize) {
        super(threshold);
        this.outputFile = outputFile;
        this.prefix = prefix;
        this.suffix = suffix;
        this.directory = directory;
        this.memoryOutputStream = new ByteArrayOutputStream(initialBufferSize);
        this.currentOutputStream = this.memoryOutputStream;
    }

    public DeferredFileOutputStream(int threshold, int initialBufferSize, File outputFile) {
        this(threshold, outputFile, null, null, null, initialBufferSize);
        if (initialBufferSize < 0) {
            throw new IllegalArgumentException("Initial buffer size must be atleast 0.");
        }
    }

    public DeferredFileOutputStream(int threshold, int initialBufferSize, String prefix, String suffix, File directory) {
        this(threshold, null, prefix, suffix, directory, initialBufferSize);
        if (prefix == null) {
            throw new IllegalArgumentException("Temporary file prefix is missing");
        }
        if (initialBufferSize < 0) {
            throw new IllegalArgumentException("Initial buffer size must be atleast 0.");
        }
    }

    public DeferredFileOutputStream(int threshold, String prefix, String suffix, File directory) {
        this(threshold, null, prefix, suffix, directory, 1024);
        if (prefix == null) {
            throw new IllegalArgumentException("Temporary file prefix is missing");
        }
    }

    @Override
    public void close() throws IOException {
        super.close();
        this.closed = true;
    }

    public byte[] getData() {
        return this.memoryOutputStream != null ? this.memoryOutputStream.toByteArray() : null;
    }

    public File getFile() {
        return this.outputFile;
    }

    @Override
    protected OutputStream getStream() throws IOException {
        return this.currentOutputStream;
    }

    public boolean isInMemory() {
        return !this.isThresholdExceeded();
    }

    @Override
    protected void thresholdReached() throws IOException {
        if (this.prefix != null) {
            this.outputFile = File.createTempFile(this.prefix, this.suffix, this.directory);
        }
        FileUtils.forceMkdirParent(this.outputFile);
        OutputStream fos = Files.newOutputStream(this.outputFile.toPath(), new OpenOption[0]);
        try {
            this.memoryOutputStream.writeTo(fos);
        } catch (IOException e) {
            fos.close();
            throw e;
        }
        this.currentOutputStream = fos;
        this.memoryOutputStream = null;
    }

    public InputStream toInputStream() throws IOException {
        if (!this.closed) {
            throw new IOException("Stream not closed");
        }
        if (this.isInMemory()) {
            return this.memoryOutputStream.toInputStream();
        }
        return Files.newInputStream(this.outputFile.toPath(), new OpenOption[0]);
    }

    public void writeTo(OutputStream outputStream) throws IOException {
        if (!this.closed) {
            throw new IOException("Stream not closed");
        }
        if (this.isInMemory()) {
            this.memoryOutputStream.writeTo(outputStream);
        } else {
            try (InputStream fis = Files.newInputStream(this.outputFile.toPath(), new OpenOption[0]);){
                IOUtils.copy(fis, outputStream);
            }
        }
    }
}

