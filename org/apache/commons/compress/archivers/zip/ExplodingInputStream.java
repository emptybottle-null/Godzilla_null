/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.compress.archivers.zip;

import java.io.IOException;
import java.io.InputStream;
import org.apache.commons.compress.archivers.zip.BinaryTree;
import org.apache.commons.compress.archivers.zip.BitStream;
import org.apache.commons.compress.archivers.zip.CircularBuffer;
import org.apache.commons.compress.utils.CountingInputStream;
import org.apache.commons.compress.utils.InputStreamStatistics;

class ExplodingInputStream
extends InputStream
implements InputStreamStatistics {
    private final InputStream in;
    private BitStream bits;
    private final int dictionarySize;
    private final int numberOfTrees;
    private final int minimumMatchLength;
    private BinaryTree literalTree;
    private BinaryTree lengthTree;
    private BinaryTree distanceTree;
    private final CircularBuffer buffer = new CircularBuffer(32768);
    private long uncompressedCount = 0L;
    private long treeSizes = 0L;

    public ExplodingInputStream(int dictionarySize, int numberOfTrees, InputStream in) {
        if (dictionarySize != 4096 && dictionarySize != 8192) {
            throw new IllegalArgumentException("The dictionary size must be 4096 or 8192");
        }
        if (numberOfTrees != 2 && numberOfTrees != 3) {
            throw new IllegalArgumentException("The number of trees must be 2 or 3");
        }
        this.dictionarySize = dictionarySize;
        this.numberOfTrees = numberOfTrees;
        this.minimumMatchLength = numberOfTrees;
        this.in = in;
    }

    private void init() throws IOException {
        if (this.bits == null) {
            try (CountingInputStream i = new CountingInputStream(this.in){

                @Override
                public void close() {
                }
            };){
                if (this.numberOfTrees == 3) {
                    this.literalTree = BinaryTree.decode(i, 256);
                }
                this.lengthTree = BinaryTree.decode(i, 64);
                this.distanceTree = BinaryTree.decode(i, 64);
                this.treeSizes += i.getBytesRead();
            }
            this.bits = new BitStream(this.in);
        }
    }

    @Override
    public int read() throws IOException {
        int ret;
        if (!this.buffer.available()) {
            this.fillBuffer();
        }
        if ((ret = this.buffer.get()) > -1) {
            ++this.uncompressedCount;
        }
        return ret;
    }

    @Override
    public long getCompressedCount() {
        return this.bits.getBytesRead() + this.treeSizes;
    }

    @Override
    public long getUncompressedCount() {
        return this.uncompressedCount;
    }

    @Override
    public void close() throws IOException {
        this.in.close();
    }

    private void fillBuffer() throws IOException {
        this.init();
        int bit = this.bits.nextBit();
        if (bit == -1) {
            return;
        }
        if (bit == 1) {
            int literal = this.literalTree != null ? this.literalTree.read(this.bits) : this.bits.nextByte();
            if (literal == -1) {
                return;
            }
            this.buffer.put(literal);
        } else {
            int distanceLowSize = this.dictionarySize == 4096 ? 6 : 7;
            int distanceLow = (int)this.bits.nextBits(distanceLowSize);
            int distanceHigh = this.distanceTree.read(this.bits);
            if (distanceHigh == -1 && distanceLow <= 0) {
                return;
            }
            int distance = distanceHigh << distanceLowSize | distanceLow;
            int length = this.lengthTree.read(this.bits);
            if (length == 63) {
                long nextByte = this.bits.nextBits(8);
                if (nextByte == -1L) {
                    return;
                }
                length = (int)((long)length + nextByte);
            }
            this.buffer.copy(distance + 1, length += this.minimumMatchLength);
        }
    }
}

