/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.compress.compressors.deflate64;

import java.io.Closeable;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteOrder;
import java.util.Arrays;
import org.apache.commons.compress.compressors.deflate64.HuffmanState;
import org.apache.commons.compress.utils.BitInputStream;

class HuffmanDecoder
implements Closeable {
    private static final short[] RUN_LENGTH_TABLE = new short[]{96, 128, 160, 192, 224, 256, 288, 320, 353, 417, 481, 545, 610, 738, 866, 994, 1123, 1379, 1635, 1891, 2148, 2660, 3172, 3684, 4197, 5221, 6245, 7269, 112};
    private static final int[] DISTANCE_TABLE = new int[]{16, 32, 48, 64, 81, 113, 146, 210, 275, 403, 532, 788, 1045, 1557, 2070, 3094, 4119, 6167, 8216, 12312, 16409, 24601, 32794, 49178, 65563, 98331, 131100, 196636, 262173, 393245, 524318, 786462};
    private static final int[] CODE_LENGTHS_ORDER = new int[]{16, 17, 18, 0, 8, 7, 9, 6, 10, 5, 11, 4, 12, 3, 13, 2, 14, 1, 15};
    private static final int[] FIXED_LITERALS = new int[288];
    private static final int[] FIXED_DISTANCE;
    private boolean finalBlock = false;
    private DecoderState state;
    private BitInputStream reader;
    private final InputStream in;
    private final DecodingMemory memory = new DecodingMemory();

    HuffmanDecoder(InputStream in) {
        this.reader = new BitInputStream(in, ByteOrder.LITTLE_ENDIAN);
        this.in = in;
        this.state = new InitialState();
    }

    @Override
    public void close() {
        this.state = new InitialState();
        this.reader = null;
    }

    public int decode(byte[] b) throws IOException {
        return this.decode(b, 0, b.length);
    }

    public int decode(byte[] b, int off, int len) throws IOException {
        block5: while (!this.finalBlock || this.state.hasData()) {
            if (this.state.state() == HuffmanState.INITIAL) {
                this.finalBlock = this.readBits(1) == 1L;
                int mode = (int)this.readBits(2);
                switch (mode) {
                    case 0: {
                        this.switchToUncompressedState();
                        continue block5;
                    }
                    case 1: {
                        this.state = new HuffmanCodes(HuffmanState.FIXED_CODES, FIXED_LITERALS, FIXED_DISTANCE);
                        continue block5;
                    }
                    case 2: {
                        int[][] tables = this.readDynamicTables();
                        this.state = new HuffmanCodes(HuffmanState.DYNAMIC_CODES, tables[0], tables[1]);
                        continue block5;
                    }
                }
                throw new IllegalStateException("Unsupported compression: " + mode);
            }
            return this.state.read(b, off, len);
        }
        return -1;
    }

    long getBytesRead() {
        return this.reader.getBytesRead();
    }

    private void switchToUncompressedState() throws IOException {
        this.reader.alignWithByteBoundary();
        long bLen = this.readBits(16);
        long bNLen = this.readBits(16);
        if (((bLen ^ 0xFFFFL) & 0xFFFFL) != bNLen) {
            throw new IllegalStateException("Illegal LEN / NLEN values");
        }
        this.state = new UncompressedState(bLen);
    }

    private int[][] readDynamicTables() throws IOException {
        int[][] result = new int[2][];
        int literals = (int)(this.readBits(5) + 257L);
        result[0] = new int[literals];
        int distances = (int)(this.readBits(5) + 1L);
        result[1] = new int[distances];
        HuffmanDecoder.populateDynamicTables(this.reader, result[0], result[1]);
        return result;
    }

    int available() throws IOException {
        return this.state.available();
    }

    private static int nextSymbol(BitInputStream reader, BinaryTreeNode tree) throws IOException {
        BinaryTreeNode node = tree;
        while (node != null && node.literal == -1) {
            long bit = HuffmanDecoder.readBits(reader, 1);
            node = bit == 0L ? node.leftNode : node.rightNode;
        }
        return node != null ? node.literal : -1;
    }

    private static void populateDynamicTables(BitInputStream reader, int[] literals, int[] distances) throws IOException {
        int codeLengths = (int)(HuffmanDecoder.readBits(reader, 4) + 4L);
        int[] codeLengthValues = new int[19];
        for (int cLen = 0; cLen < codeLengths; ++cLen) {
            codeLengthValues[HuffmanDecoder.CODE_LENGTHS_ORDER[cLen]] = (int)HuffmanDecoder.readBits(reader, 3);
        }
        BinaryTreeNode codeLengthTree = HuffmanDecoder.buildTree(codeLengthValues);
        int[] auxBuffer = new int[literals.length + distances.length];
        int value = -1;
        int length = 0;
        int off = 0;
        while (off < auxBuffer.length) {
            if (length > 0) {
                auxBuffer[off++] = value;
                --length;
                continue;
            }
            int symbol = HuffmanDecoder.nextSymbol(reader, codeLengthTree);
            if (symbol < 16) {
                value = symbol;
                auxBuffer[off++] = value;
                continue;
            }
            if (symbol == 16) {
                length = (int)(HuffmanDecoder.readBits(reader, 2) + 3L);
                continue;
            }
            if (symbol == 17) {
                value = 0;
                length = (int)(HuffmanDecoder.readBits(reader, 3) + 3L);
                continue;
            }
            if (symbol != 18) continue;
            value = 0;
            length = (int)(HuffmanDecoder.readBits(reader, 7) + 11L);
        }
        System.arraycopy(auxBuffer, 0, literals, 0, literals.length);
        System.arraycopy(auxBuffer, literals.length, distances, 0, distances.length);
    }

    private static BinaryTreeNode buildTree(int[] litTable) {
        int[] literalCodes = HuffmanDecoder.getCodes(litTable);
        BinaryTreeNode root = new BinaryTreeNode(0);
        for (int i = 0; i < litTable.length; ++i) {
            int len = litTable[i];
            if (len == 0) continue;
            BinaryTreeNode node = root;
            int lit = literalCodes[len - 1];
            for (int p = len - 1; p >= 0; --p) {
                int bit = lit & 1 << p;
                node = bit == 0 ? node.left() : node.right();
            }
            node.leaf(i);
            int n = len - 1;
            literalCodes[n] = literalCodes[n] + 1;
        }
        return root;
    }

    private static int[] getCodes(int[] litTable) {
        int max = 0;
        int[] blCount = new int[65];
        for (int aLitTable : litTable) {
            max = Math.max(max, aLitTable);
            int n = aLitTable;
            blCount[n] = blCount[n] + 1;
        }
        blCount = Arrays.copyOf(blCount, max + 1);
        int code = 0;
        int[] nextCode = new int[max + 1];
        for (int i = 0; i <= max; ++i) {
            nextCode[i] = code = code + blCount[i] << 1;
        }
        return nextCode;
    }

    private long readBits(int numBits) throws IOException {
        return HuffmanDecoder.readBits(this.reader, numBits);
    }

    private static long readBits(BitInputStream reader, int numBits) throws IOException {
        long r = reader.readBits(numBits);
        if (r == -1L) {
            throw new EOFException("Truncated Deflate64 Stream");
        }
        return r;
    }

    static {
        Arrays.fill(FIXED_LITERALS, 0, 144, 8);
        Arrays.fill(FIXED_LITERALS, 144, 256, 9);
        Arrays.fill(FIXED_LITERALS, 256, 280, 7);
        Arrays.fill(FIXED_LITERALS, 280, 288, 8);
        FIXED_DISTANCE = new int[32];
        Arrays.fill(FIXED_DISTANCE, 5);
    }

    private static class DecodingMemory {
        private final byte[] memory;
        private final int mask;
        private int wHead;
        private boolean wrappedAround;

        private DecodingMemory() {
            this(16);
        }

        private DecodingMemory(int bits) {
            this.memory = new byte[1 << bits];
            this.mask = this.memory.length - 1;
        }

        byte add(byte b) {
            this.memory[this.wHead] = b;
            this.wHead = this.incCounter(this.wHead);
            return b;
        }

        void add(byte[] b, int off, int len) {
            for (int i = off; i < off + len; ++i) {
                this.add(b[i]);
            }
        }

        void recordToBuffer(int distance, int length, byte[] buff) {
            if (distance > this.memory.length) {
                throw new IllegalStateException("Illegal distance parameter: " + distance);
            }
            int start = this.wHead - distance & this.mask;
            if (!this.wrappedAround && start >= this.wHead) {
                throw new IllegalStateException("Attempt to read beyond memory: dist=" + distance);
            }
            int pos = start;
            for (int i = 0; i < length; ++i) {
                buff[i] = this.add(this.memory[pos]);
                pos = this.incCounter(pos);
            }
        }

        private int incCounter(int counter) {
            int newCounter = counter + 1 & this.mask;
            if (!this.wrappedAround && newCounter < counter) {
                this.wrappedAround = true;
            }
            return newCounter;
        }
    }

    private static class BinaryTreeNode {
        private final int bits;
        int literal = -1;
        BinaryTreeNode leftNode;
        BinaryTreeNode rightNode;

        private BinaryTreeNode(int bits) {
            this.bits = bits;
        }

        void leaf(int symbol) {
            this.literal = symbol;
            this.leftNode = null;
            this.rightNode = null;
        }

        BinaryTreeNode left() {
            if (this.leftNode == null && this.literal == -1) {
                this.leftNode = new BinaryTreeNode(this.bits + 1);
            }
            return this.leftNode;
        }

        BinaryTreeNode right() {
            if (this.rightNode == null && this.literal == -1) {
                this.rightNode = new BinaryTreeNode(this.bits + 1);
            }
            return this.rightNode;
        }
    }

    private class HuffmanCodes
    extends DecoderState {
        private boolean endOfBlock;
        private final HuffmanState state;
        private final BinaryTreeNode lengthTree;
        private final BinaryTreeNode distanceTree;
        private int runBufferPos;
        private byte[] runBuffer;
        private int runBufferLength;

        HuffmanCodes(HuffmanState state, int[] lengths, int[] distance) {
            this.endOfBlock = false;
            this.runBufferPos = 0;
            this.runBuffer = new byte[0];
            this.runBufferLength = 0;
            this.state = state;
            this.lengthTree = HuffmanDecoder.buildTree(lengths);
            this.distanceTree = HuffmanDecoder.buildTree(distance);
        }

        @Override
        HuffmanState state() {
            return this.endOfBlock ? HuffmanState.INITIAL : this.state;
        }

        @Override
        int read(byte[] b, int off, int len) throws IOException {
            return this.decodeNext(b, off, len);
        }

        private int decodeNext(byte[] b, int off, int len) throws IOException {
            if (this.endOfBlock) {
                return -1;
            }
            int result = this.copyFromRunBuffer(b, off, len);
            while (result < len) {
                int symbol = HuffmanDecoder.nextSymbol(HuffmanDecoder.this.reader, this.lengthTree);
                if (symbol < 256) {
                    b[off + result++] = HuffmanDecoder.this.memory.add((byte)symbol);
                    continue;
                }
                if (symbol > 256) {
                    short runMask = RUN_LENGTH_TABLE[symbol - 257];
                    int run = runMask >>> 5;
                    int runXtra = runMask & 0x1F;
                    run = (int)((long)run + HuffmanDecoder.this.readBits(runXtra));
                    int distSym = HuffmanDecoder.nextSymbol(HuffmanDecoder.this.reader, this.distanceTree);
                    int distMask = DISTANCE_TABLE[distSym];
                    int dist = distMask >>> 4;
                    int distXtra = distMask & 0xF;
                    dist = (int)((long)dist + HuffmanDecoder.this.readBits(distXtra));
                    if (this.runBuffer.length < run) {
                        this.runBuffer = new byte[run];
                    }
                    this.runBufferLength = run;
                    this.runBufferPos = 0;
                    HuffmanDecoder.this.memory.recordToBuffer(dist, run, this.runBuffer);
                    result += this.copyFromRunBuffer(b, off + result, len - result);
                    continue;
                }
                this.endOfBlock = true;
                return result;
            }
            return result;
        }

        private int copyFromRunBuffer(byte[] b, int off, int len) {
            int bytesInBuffer = this.runBufferLength - this.runBufferPos;
            int copiedBytes = 0;
            if (bytesInBuffer > 0) {
                copiedBytes = Math.min(len, bytesInBuffer);
                System.arraycopy(this.runBuffer, this.runBufferPos, b, off, copiedBytes);
                this.runBufferPos += copiedBytes;
            }
            return copiedBytes;
        }

        @Override
        boolean hasData() {
            return !this.endOfBlock;
        }

        @Override
        int available() {
            return this.runBufferLength - this.runBufferPos;
        }
    }

    private class InitialState
    extends DecoderState {
        private InitialState() {
        }

        @Override
        HuffmanState state() {
            return HuffmanState.INITIAL;
        }

        @Override
        int read(byte[] b, int off, int len) throws IOException {
            throw new IllegalStateException("Cannot read in this state");
        }

        @Override
        boolean hasData() {
            return false;
        }

        @Override
        int available() {
            return 0;
        }
    }

    private class UncompressedState
    extends DecoderState {
        private final long blockLength;
        private long read;

        private UncompressedState(long blockLength) {
            this.blockLength = blockLength;
        }

        @Override
        HuffmanState state() {
            return this.read < this.blockLength ? HuffmanState.STORED : HuffmanState.INITIAL;
        }

        @Override
        int read(byte[] b, int off, int len) throws IOException {
            int readNow;
            int max = (int)Math.min(this.blockLength - this.read, (long)len);
            for (int readSoFar = 0; readSoFar < max; readSoFar += readNow) {
                if (HuffmanDecoder.this.reader.bitsCached() > 0) {
                    byte next = (byte)HuffmanDecoder.this.readBits(8);
                    b[off + readSoFar] = HuffmanDecoder.this.memory.add(next);
                    readNow = 1;
                } else {
                    readNow = HuffmanDecoder.this.in.read(b, off + readSoFar, max - readSoFar);
                    if (readNow == -1) {
                        throw new EOFException("Truncated Deflate64 Stream");
                    }
                    HuffmanDecoder.this.memory.add(b, off + readSoFar, readNow);
                }
                this.read += (long)readNow;
            }
            return max;
        }

        @Override
        boolean hasData() {
            return this.read < this.blockLength;
        }

        @Override
        int available() throws IOException {
            return (int)Math.min(this.blockLength - this.read, HuffmanDecoder.this.reader.bitsAvailable() / 8L);
        }
    }

    private static abstract class DecoderState {
        private DecoderState() {
        }

        abstract HuffmanState state();

        abstract int read(byte[] var1, int var2, int var3) throws IOException;

        abstract boolean hasData();

        abstract int available() throws IOException;
    }
}

