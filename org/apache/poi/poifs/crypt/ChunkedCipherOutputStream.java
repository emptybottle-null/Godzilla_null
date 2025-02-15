/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.poifs.crypt;

import com.zaxxer.sparsebits.SparseBitSet;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.GeneralSecurityException;
import javax.crypto.Cipher;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.poifs.filesystem.DirectoryNode;
import org.apache.poi.poifs.filesystem.DocumentOutputStream;
import org.apache.poi.poifs.filesystem.POIFSWriterEvent;
import org.apache.poi.poifs.filesystem.POIFSWriterListener;
import org.apache.poi.util.IOUtils;
import org.apache.poi.util.Internal;
import org.apache.poi.util.LittleEndian;
import org.apache.poi.util.POILogFactory;
import org.apache.poi.util.POILogger;
import org.apache.poi.util.TempFile;

@Internal
public abstract class ChunkedCipherOutputStream
extends FilterOutputStream {
    private static final POILogger LOG = POILogFactory.getLogger(ChunkedCipherOutputStream.class);
    private static final int MAX_RECORD_LENGTH = 100000;
    private static final int STREAMING = -1;
    private final int chunkSize;
    private final int chunkBits;
    private final byte[] chunk;
    private final SparseBitSet plainByteFlags;
    private final File fileOut;
    private final DirectoryNode dir;
    private long pos;
    private long totalPos;
    private long written;
    private Cipher cipher;
    private boolean isClosed;

    public ChunkedCipherOutputStream(DirectoryNode dir, int chunkSize) throws IOException, GeneralSecurityException {
        super(null);
        this.chunkSize = chunkSize;
        int cs = chunkSize == -1 ? 4096 : chunkSize;
        this.chunk = IOUtils.safelyAllocate(cs, 100000);
        this.plainByteFlags = new SparseBitSet(cs);
        this.chunkBits = Integer.bitCount(cs - 1);
        this.fileOut = TempFile.createTempFile("encrypted_package", "crypt");
        this.fileOut.deleteOnExit();
        this.out = new FileOutputStream(this.fileOut);
        this.dir = dir;
        this.cipher = this.initCipherForBlock(null, 0, false);
    }

    public ChunkedCipherOutputStream(OutputStream stream, int chunkSize) throws IOException, GeneralSecurityException {
        super(stream);
        this.chunkSize = chunkSize;
        int cs = chunkSize == -1 ? 4096 : chunkSize;
        this.chunk = IOUtils.safelyAllocate(cs, 100000);
        this.plainByteFlags = new SparseBitSet(cs);
        this.chunkBits = Integer.bitCount(cs - 1);
        this.fileOut = null;
        this.dir = null;
        this.cipher = this.initCipherForBlock(null, 0, false);
    }

    public final Cipher initCipherForBlock(int block, boolean lastChunk) throws IOException, GeneralSecurityException {
        return this.initCipherForBlock(this.cipher, block, lastChunk);
    }

    @Internal
    protected Cipher initCipherForBlockNoFlush(Cipher existing, int block, boolean lastChunk) throws IOException, GeneralSecurityException {
        return this.initCipherForBlock(this.cipher, block, lastChunk);
    }

    protected abstract Cipher initCipherForBlock(Cipher var1, int var2, boolean var3) throws IOException, GeneralSecurityException;

    protected abstract void calculateChecksum(File var1, int var2) throws GeneralSecurityException, IOException;

    protected abstract void createEncryptionInfoEntry(DirectoryNode var1, File var2) throws IOException, GeneralSecurityException;

    @Override
    public void write(int b) throws IOException {
        this.write(new byte[]{(byte)b});
    }

    @Override
    public void write(byte[] b) throws IOException {
        this.write(b, 0, b.length);
    }

    @Override
    public void write(byte[] b, int off, int len) throws IOException {
        this.write(b, off, len, false);
    }

    public void writePlain(byte[] b, int off, int len) throws IOException {
        this.write(b, off, len, true);
    }

    protected void write(byte[] b, int off, int len, boolean writePlain) throws IOException {
        if (len == 0) {
            return;
        }
        if (len < 0 || b.length < off + len) {
            throw new IOException("not enough bytes in your input buffer");
        }
        int chunkMask = this.getChunkMask();
        while (len > 0) {
            int posInChunk = (int)(this.pos & (long)chunkMask);
            int nextLen = Math.min(this.chunk.length - posInChunk, len);
            System.arraycopy(b, off, this.chunk, posInChunk, nextLen);
            if (writePlain) {
                this.plainByteFlags.set(posInChunk, posInChunk + nextLen);
            }
            this.pos += (long)nextLen;
            this.totalPos += (long)nextLen;
            off += nextLen;
            len -= nextLen;
            if ((this.pos & (long)chunkMask) != 0L) continue;
            this.writeChunk(len > 0);
        }
    }

    protected int getChunkMask() {
        return this.chunk.length - 1;
    }

    protected void writeChunk(boolean continued) throws IOException {
        int ciLen;
        boolean lastChunk;
        if (this.pos == 0L || this.totalPos == this.written) {
            return;
        }
        int posInChunk = (int)(this.pos & (long)this.getChunkMask());
        int index = (int)(this.pos >> this.chunkBits);
        if (posInChunk == 0) {
            --index;
            posInChunk = this.chunk.length;
            lastChunk = false;
        } else {
            lastChunk = true;
        }
        try {
            boolean doFinal = true;
            long oldPos = this.pos;
            this.pos = 0L;
            if (this.chunkSize == -1) {
                if (continued) {
                    doFinal = false;
                }
            } else {
                this.cipher = this.initCipherForBlock(this.cipher, index, lastChunk);
                this.pos = oldPos;
            }
            ciLen = this.invokeCipher(posInChunk, doFinal);
        } catch (GeneralSecurityException e) {
            throw new IOException("can't re-/initialize cipher", e);
        }
        this.out.write(this.chunk, 0, ciLen);
        this.plainByteFlags.clear();
        this.written += (long)ciLen;
    }

    protected int invokeCipher(int posInChunk, boolean doFinal) throws GeneralSecurityException, IOException {
        int ciLen;
        byte[] plain = this.plainByteFlags.isEmpty() ? null : (byte[])this.chunk.clone();
        int n = ciLen = doFinal ? this.cipher.doFinal(this.chunk, 0, posInChunk, this.chunk) : this.cipher.update(this.chunk, 0, posInChunk, this.chunk);
        if (doFinal && "IBMJCE".equals(this.cipher.getProvider().getName()) && "RC4".equals(this.cipher.getAlgorithm())) {
            boolean lastChunk;
            int index = (int)(this.pos >> this.chunkBits);
            if (posInChunk == 0) {
                --index;
                posInChunk = this.chunk.length;
                lastChunk = false;
            } else {
                lastChunk = true;
            }
            this.cipher = this.initCipherForBlockNoFlush(this.cipher, index, lastChunk);
        }
        if (plain != null) {
            int i = this.plainByteFlags.nextSetBit(0);
            while (i >= 0 && i < posInChunk) {
                this.chunk[i] = plain[i];
                i = this.plainByteFlags.nextSetBit(i + 1);
            }
        }
        return ciLen;
    }

    @Override
    public void close() throws IOException {
        if (this.isClosed) {
            LOG.log(1, "ChunkedCipherOutputStream was already closed - ignoring");
            return;
        }
        this.isClosed = true;
        try {
            this.writeChunk(false);
            super.close();
            if (this.fileOut != null) {
                int oleStreamSize = (int)(this.fileOut.length() + 8L);
                this.calculateChecksum(this.fileOut, (int)this.pos);
                this.dir.createDocument("EncryptedPackage", oleStreamSize, new EncryptedPackageWriter());
                this.createEncryptionInfoEntry(this.dir, this.fileOut);
            }
        } catch (GeneralSecurityException e) {
            throw new IOException(e);
        }
    }

    protected byte[] getChunk() {
        return this.chunk;
    }

    protected SparseBitSet getPlainByteFlags() {
        return this.plainByteFlags;
    }

    protected long getPos() {
        return this.pos;
    }

    protected long getTotalPos() {
        return this.totalPos;
    }

    public void setNextRecordSize(int recordSize, boolean isPlain) {
    }

    private class EncryptedPackageWriter
    implements POIFSWriterListener {
        private EncryptedPackageWriter() {
        }

        @Override
        public void processPOIFSWriterEvent(POIFSWriterEvent event) {
            try {
                try (DocumentOutputStream os = event.getStream();
                     FileInputStream fis = new FileInputStream(ChunkedCipherOutputStream.this.fileOut);){
                    byte[] buf = new byte[8];
                    LittleEndian.putLong(buf, 0, ChunkedCipherOutputStream.this.pos);
                    os.write(buf);
                    IOUtils.copy((InputStream)fis, os);
                }
                if (!ChunkedCipherOutputStream.this.fileOut.delete()) {
                    LOG.log(7, "Can't delete temporary encryption file: " + ChunkedCipherOutputStream.this.fileOut);
                }
            } catch (IOException e) {
                throw new EncryptedDocumentException(e);
            }
        }
    }
}

