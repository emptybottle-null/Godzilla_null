/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.xssf.streaming;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.CRC32;
import java.util.zip.Deflater;
import java.util.zip.DeflaterOutputStream;
import org.apache.poi.xssf.streaming.Zip64Impl;

class OpcOutputStream
extends DeflaterOutputStream {
    private final Zip64Impl spec;
    private final List<Zip64Impl.Entry> entries = new ArrayList<Zip64Impl.Entry>();
    private final CRC32 crc = new CRC32();
    private Zip64Impl.Entry current;
    private int written = 0;
    private boolean finished = false;

    public OpcOutputStream(OutputStream out) {
        super(out, new Deflater(-1, true));
        this.spec = new Zip64Impl(out);
    }

    public void setLevel(int level) {
        this.def.setLevel(level);
    }

    public void putNextEntry(String name) throws IOException {
        if (this.current != null) {
            this.closeEntry();
        }
        this.current = new Zip64Impl.Entry(name);
        this.current.offset = this.written;
        this.written += this.spec.writeLFH(this.current);
        this.entries.add(this.current);
    }

    public void closeEntry() throws IOException {
        if (this.current == null) {
            throw new IllegalStateException("not current zip current");
        }
        this.def.finish();
        while (!this.def.finished()) {
            this.deflate();
        }
        this.current.size = this.def.getBytesRead();
        this.current.compressedSize = Math.toIntExact(this.def.getBytesWritten());
        this.current.crc = this.crc.getValue();
        this.written += this.current.compressedSize;
        this.written += this.spec.writeDAT(this.current);
        this.current = null;
        this.def.reset();
        this.crc.reset();
    }

    @Override
    public void finish() throws IOException {
        if (this.finished) {
            return;
        }
        if (this.current != null) {
            this.closeEntry();
        }
        int offset = this.written;
        for (Zip64Impl.Entry entry : this.entries) {
            this.written += this.spec.writeCEN(entry);
        }
        this.written += this.spec.writeEND(this.entries.size(), offset, this.written - offset);
        this.finished = true;
    }

    @Override
    public synchronized void write(byte[] b, int off, int len) throws IOException {
        if (off < 0 || len < 0 || off > b.length - len) {
            throw new IndexOutOfBoundsException();
        }
        if (len == 0) {
            return;
        }
        super.write(b, off, len);
        this.crc.update(b, off, len);
    }

    @Override
    public void close() throws IOException {
        this.finish();
        this.out.close();
    }
}

