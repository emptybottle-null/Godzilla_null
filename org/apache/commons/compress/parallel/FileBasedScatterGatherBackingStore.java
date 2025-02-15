/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.compress.parallel;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import org.apache.commons.compress.parallel.ScatterGatherBackingStore;

public class FileBasedScatterGatherBackingStore
implements ScatterGatherBackingStore {
    private final File target;
    private final OutputStream os;
    private boolean closed;

    public FileBasedScatterGatherBackingStore(File target) throws FileNotFoundException {
        this.target = target;
        try {
            this.os = Files.newOutputStream(target.toPath(), new OpenOption[0]);
        } catch (FileNotFoundException ex) {
            throw ex;
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public InputStream getInputStream() throws IOException {
        return Files.newInputStream(this.target.toPath(), new OpenOption[0]);
    }

    @Override
    public void closeForWriting() throws IOException {
        if (!this.closed) {
            this.os.close();
            this.closed = true;
        }
    }

    @Override
    public void writeOut(byte[] data, int offset, int length) throws IOException {
        this.os.write(data, offset, length);
    }

    @Override
    public void close() throws IOException {
        try {
            this.closeForWriting();
        } finally {
            if (this.target.exists() && !this.target.delete()) {
                this.target.deleteOnExit();
            }
        }
    }
}

