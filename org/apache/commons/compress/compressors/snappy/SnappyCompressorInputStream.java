/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.compress.compressors.snappy;

import java.io.IOException;
import java.io.InputStream;
import org.apache.commons.compress.compressors.lz77support.AbstractLZ77CompressorInputStream;
import org.apache.commons.compress.utils.ByteUtils;

public class SnappyCompressorInputStream
extends AbstractLZ77CompressorInputStream {
    private static final int TAG_MASK = 3;
    public static final int DEFAULT_BLOCK_SIZE = 32768;
    private final int size;
    private int uncompressedBytesRemaining;
    private State state = State.NO_BLOCK;
    private boolean endReached = false;

    public SnappyCompressorInputStream(InputStream is) throws IOException {
        this(is, 32768);
    }

    public SnappyCompressorInputStream(InputStream is, int blockSize) throws IOException {
        super(is, blockSize);
        this.uncompressedBytesRemaining = this.size = (int)this.readSize();
    }

    @Override
    public int read(byte[] b, int off, int len) throws IOException {
        if (this.endReached) {
            return -1;
        }
        switch (this.state) {
            case NO_BLOCK: {
                this.fill();
                return this.read(b, off, len);
            }
            case IN_LITERAL: {
                int litLen = this.readLiteral(b, off, len);
                if (!this.hasMoreDataInBlock()) {
                    this.state = State.NO_BLOCK;
                }
                return litLen > 0 ? litLen : this.read(b, off, len);
            }
            case IN_BACK_REFERENCE: {
                int backReferenceLen = this.readBackReference(b, off, len);
                if (!this.hasMoreDataInBlock()) {
                    this.state = State.NO_BLOCK;
                }
                return backReferenceLen > 0 ? backReferenceLen : this.read(b, off, len);
            }
        }
        throw new IOException("Unknown stream state " + (Object)((Object)this.state));
    }

    private void fill() throws IOException {
        if (this.uncompressedBytesRemaining == 0) {
            this.endReached = true;
            return;
        }
        int b = this.readOneByte();
        if (b == -1) {
            throw new IOException("Premature end of stream reading block start");
        }
        int length = 0;
        int offset = 0;
        switch (b & 3) {
            case 0: {
                length = this.readLiteralLength(b);
                if (length < 0) {
                    throw new IOException("Illegal block with a negative literal size found");
                }
                this.uncompressedBytesRemaining -= length;
                this.startLiteral(length);
                this.state = State.IN_LITERAL;
                break;
            }
            case 1: {
                length = 4 + (b >> 2 & 7);
                if (length < 0) {
                    throw new IOException("Illegal block with a negative match length found");
                }
                this.uncompressedBytesRemaining -= length;
                offset = (b & 0xE0) << 3;
                b = this.readOneByte();
                if (b == -1) {
                    throw new IOException("Premature end of stream reading back-reference length");
                }
                offset |= b;
                try {
                    this.startBackReference(offset, length);
                } catch (IllegalArgumentException ex) {
                    throw new IOException("Illegal block with bad offset found", ex);
                }
                this.state = State.IN_BACK_REFERENCE;
                break;
            }
            case 2: {
                length = (b >> 2) + 1;
                if (length < 0) {
                    throw new IOException("Illegal block with a negative match length found");
                }
                this.uncompressedBytesRemaining -= length;
                offset = (int)ByteUtils.fromLittleEndian(this.supplier, 2);
                try {
                    this.startBackReference(offset, length);
                } catch (IllegalArgumentException ex) {
                    throw new IOException("Illegal block with bad offset found", ex);
                }
                this.state = State.IN_BACK_REFERENCE;
                break;
            }
            case 3: {
                length = (b >> 2) + 1;
                if (length < 0) {
                    throw new IOException("Illegal block with a negative match length found");
                }
                this.uncompressedBytesRemaining -= length;
                offset = (int)ByteUtils.fromLittleEndian(this.supplier, 4) & Integer.MAX_VALUE;
                try {
                    this.startBackReference(offset, length);
                } catch (IllegalArgumentException ex) {
                    throw new IOException("Illegal block with bad offset found", ex);
                }
                this.state = State.IN_BACK_REFERENCE;
                break;
            }
        }
    }

    private int readLiteralLength(int b) throws IOException {
        int length;
        switch (b >> 2) {
            case 60: {
                length = this.readOneByte();
                if (length != -1) break;
                throw new IOException("Premature end of stream reading literal length");
            }
            case 61: {
                length = (int)ByteUtils.fromLittleEndian(this.supplier, 2);
                break;
            }
            case 62: {
                length = (int)ByteUtils.fromLittleEndian(this.supplier, 3);
                break;
            }
            case 63: {
                length = (int)ByteUtils.fromLittleEndian(this.supplier, 4);
                break;
            }
            default: {
                length = b >> 2;
            }
        }
        return length + 1;
    }

    private long readSize() throws IOException {
        int index = 0;
        long sz = 0L;
        int b = 0;
        do {
            if ((b = this.readOneByte()) == -1) {
                throw new IOException("Premature end of stream reading size");
            }
            sz |= (long)((b & 0x7F) << index++ * 7);
        } while (0 != (b & 0x80));
        return sz;
    }

    @Override
    public int getSize() {
        return this.size;
    }

    private static enum State {
        NO_BLOCK,
        IN_LITERAL,
        IN_BACK_REFERENCE;

    }
}

