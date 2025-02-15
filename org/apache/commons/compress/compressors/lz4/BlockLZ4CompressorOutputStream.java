/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.compress.compressors.lz4;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.Deque;
import java.util.Iterator;
import java.util.LinkedList;
import org.apache.commons.compress.compressors.CompressorOutputStream;
import org.apache.commons.compress.compressors.lz77support.LZ77Compressor;
import org.apache.commons.compress.compressors.lz77support.Parameters;
import org.apache.commons.compress.utils.ByteUtils;

public class BlockLZ4CompressorOutputStream
extends CompressorOutputStream {
    private static final int MIN_BACK_REFERENCE_LENGTH = 4;
    private static final int MIN_OFFSET_OF_LAST_BACK_REFERENCE = 12;
    private final LZ77Compressor compressor;
    private final OutputStream os;
    private final byte[] oneByte = new byte[1];
    private boolean finished = false;
    private Deque<Pair> pairs = new LinkedList<Pair>();
    private Deque<byte[]> expandedBlocks = new LinkedList<byte[]>();

    public BlockLZ4CompressorOutputStream(OutputStream os) throws IOException {
        this(os, BlockLZ4CompressorOutputStream.createParameterBuilder().build());
    }

    public BlockLZ4CompressorOutputStream(OutputStream os, Parameters params) throws IOException {
        this.os = os;
        this.compressor = new LZ77Compressor(params, new LZ77Compressor.Callback(){

            @Override
            public void accept(LZ77Compressor.Block block) throws IOException {
                switch (block.getType()) {
                    case LITERAL: {
                        BlockLZ4CompressorOutputStream.this.addLiteralBlock((LZ77Compressor.LiteralBlock)block);
                        break;
                    }
                    case BACK_REFERENCE: {
                        BlockLZ4CompressorOutputStream.this.addBackReference((LZ77Compressor.BackReference)block);
                        break;
                    }
                    case EOD: {
                        BlockLZ4CompressorOutputStream.this.writeFinalLiteralBlock();
                    }
                }
            }
        });
    }

    @Override
    public void write(int b) throws IOException {
        this.oneByte[0] = (byte)(b & 0xFF);
        this.write(this.oneByte);
    }

    @Override
    public void write(byte[] data, int off, int len) throws IOException {
        this.compressor.compress(data, off, len);
    }

    @Override
    public void close() throws IOException {
        try {
            this.finish();
        } finally {
            this.os.close();
        }
    }

    public void finish() throws IOException {
        if (!this.finished) {
            this.compressor.finish();
            this.finished = true;
        }
    }

    public void prefill(byte[] data, int off, int len) {
        if (len > 0) {
            byte[] b = Arrays.copyOfRange(data, off, off + len);
            this.compressor.prefill(b);
            this.recordLiteral(b);
        }
    }

    private void addLiteralBlock(LZ77Compressor.LiteralBlock block) throws IOException {
        Pair last = this.writeBlocksAndReturnUnfinishedPair(block.getLength());
        this.recordLiteral(last.addLiteral(block));
        this.clearUnusedBlocksAndPairs();
    }

    private void addBackReference(LZ77Compressor.BackReference block) throws IOException {
        Pair last = this.writeBlocksAndReturnUnfinishedPair(block.getLength());
        last.setBackReference(block);
        this.recordBackReference(block);
        this.clearUnusedBlocksAndPairs();
    }

    private Pair writeBlocksAndReturnUnfinishedPair(int length) throws IOException {
        this.writeWritablePairs(length);
        Pair last = this.pairs.peekLast();
        if (last == null || last.hasBackReference()) {
            last = new Pair();
            this.pairs.addLast(last);
        }
        return last;
    }

    private void recordLiteral(byte[] b) {
        this.expandedBlocks.addFirst(b);
    }

    private void clearUnusedBlocksAndPairs() {
        this.clearUnusedBlocks();
        this.clearUnusedPairs();
    }

    private void clearUnusedBlocks() {
        int blockLengths = 0;
        int blocksToKeep = 0;
        for (byte[] b : this.expandedBlocks) {
            ++blocksToKeep;
            if ((blockLengths += b.length) < 65536) continue;
            break;
        }
        int size = this.expandedBlocks.size();
        for (int i = blocksToKeep; i < size; ++i) {
            this.expandedBlocks.removeLast();
        }
    }

    private void recordBackReference(LZ77Compressor.BackReference block) {
        this.expandedBlocks.addFirst(this.expand(block.getOffset(), block.getLength()));
    }

    private byte[] expand(int offset, int length) {
        byte[] expanded = new byte[length];
        if (offset == 1) {
            byte[] block = this.expandedBlocks.peekFirst();
            byte b = block[block.length - 1];
            if (b != 0) {
                Arrays.fill(expanded, b);
            }
        } else {
            this.expandFromList(expanded, offset, length);
        }
        return expanded;
    }

    private void expandFromList(byte[] expanded, int offset, int length) {
        int offsetRemaining = offset;
        int lengthRemaining = length;
        int writeOffset = 0;
        while (lengthRemaining > 0) {
            int copyLen;
            int copyOffset;
            byte[] block = null;
            if (offsetRemaining > 0) {
                int blockOffset = 0;
                for (byte[] b : this.expandedBlocks) {
                    if (b.length + blockOffset >= offsetRemaining) {
                        block = b;
                        break;
                    }
                    blockOffset += b.length;
                }
                if (block == null) {
                    throw new IllegalStateException("Failed to find a block containing offset " + offset);
                }
                copyOffset = blockOffset + block.length - offsetRemaining;
                copyLen = Math.min(lengthRemaining, block.length - copyOffset);
            } else {
                block = expanded;
                copyOffset = -offsetRemaining;
                copyLen = Math.min(lengthRemaining, writeOffset + offsetRemaining);
            }
            System.arraycopy(block, copyOffset, expanded, writeOffset, copyLen);
            offsetRemaining -= copyLen;
            lengthRemaining -= copyLen;
            writeOffset += copyLen;
        }
    }

    private void clearUnusedPairs() {
        Pair p;
        int pairLengths = 0;
        int pairsToKeep = 0;
        Iterator<Pair> it = this.pairs.descendingIterator();
        while (it.hasNext()) {
            Pair p2 = it.next();
            ++pairsToKeep;
            if ((pairLengths += p2.length()) < 65536) continue;
            break;
        }
        int size = this.pairs.size();
        for (int i = pairsToKeep; i < size && (p = this.pairs.peekFirst()).hasBeenWritten(); ++i) {
            this.pairs.removeFirst();
        }
    }

    private void writeFinalLiteralBlock() throws IOException {
        this.rewriteLastPairs();
        for (Pair p : this.pairs) {
            if (p.hasBeenWritten()) continue;
            p.writeTo(this.os);
        }
        this.pairs.clear();
    }

    private void writeWritablePairs(int lengthOfBlocksAfterLastPair) throws IOException {
        Pair p2;
        int unwrittenLength = lengthOfBlocksAfterLastPair;
        Iterator<Pair> it = this.pairs.descendingIterator();
        while (it.hasNext() && !(p2 = it.next()).hasBeenWritten()) {
            unwrittenLength += p2.length();
        }
        for (Pair p2 : this.pairs) {
            if (p2.hasBeenWritten()) continue;
            if (!p2.canBeWritten(unwrittenLength -= p2.length())) break;
            p2.writeTo(this.os);
        }
    }

    private void rewriteLastPairs() {
        int brLen;
        Pair p2;
        LinkedList<Pair> lastPairs = new LinkedList<Pair>();
        LinkedList<Integer> pairLength = new LinkedList<Integer>();
        int offset = 0;
        Iterator<Pair> it = this.pairs.descendingIterator();
        while (it.hasNext() && !(p2 = it.next()).hasBeenWritten()) {
            int len = p2.length();
            pairLength.addFirst(len);
            lastPairs.addFirst(p2);
            if ((offset += len) < 12) continue;
            break;
        }
        for (Pair p2 : lastPairs) {
            this.pairs.remove(p2);
        }
        int lastPairsSize = lastPairs.size();
        int toExpand = 0;
        for (int i = 1; i < lastPairsSize; ++i) {
            toExpand += ((Integer)pairLength.get(i)).intValue();
        }
        Pair replacement = new Pair();
        if (toExpand > 0) {
            replacement.prependLiteral(this.expand(toExpand, toExpand));
        }
        Pair splitCandidate = (Pair)lastPairs.get(0);
        int stillNeeded = 12 - toExpand;
        int n = brLen = splitCandidate.hasBackReference() ? splitCandidate.backReferenceLength() : 0;
        if (splitCandidate.hasBackReference() && brLen >= 4 + stillNeeded) {
            replacement.prependLiteral(this.expand(toExpand + stillNeeded, stillNeeded));
            this.pairs.add(splitCandidate.splitWithNewBackReferenceLengthOf(brLen - stillNeeded));
        } else {
            if (splitCandidate.hasBackReference()) {
                replacement.prependLiteral(this.expand(toExpand + brLen, brLen));
            }
            splitCandidate.prependTo(replacement);
        }
        this.pairs.add(replacement);
    }

    public static Parameters.Builder createParameterBuilder() {
        int maxLen = 65535;
        return Parameters.builder(65536).withMinBackReferenceLength(4).withMaxBackReferenceLength(maxLen).withMaxOffset(maxLen).withMaxLiteralLength(maxLen);
    }

    static final class Pair {
        private final Deque<byte[]> literals = new LinkedList<byte[]>();
        private int brOffset;
        private int brLength;
        private boolean written;

        Pair() {
        }

        private void prependLiteral(byte[] data) {
            this.literals.addFirst(data);
        }

        byte[] addLiteral(LZ77Compressor.LiteralBlock block) {
            byte[] copy = Arrays.copyOfRange(block.getData(), block.getOffset(), block.getOffset() + block.getLength());
            this.literals.add(copy);
            return copy;
        }

        void setBackReference(LZ77Compressor.BackReference block) {
            if (this.hasBackReference()) {
                throw new IllegalStateException();
            }
            this.brOffset = block.getOffset();
            this.brLength = block.getLength();
        }

        boolean hasBackReference() {
            return this.brOffset > 0;
        }

        boolean canBeWritten(int lengthOfBlocksAfterThisPair) {
            return this.hasBackReference() && lengthOfBlocksAfterThisPair >= 16;
        }

        int length() {
            return this.literalLength() + this.brLength;
        }

        private boolean hasBeenWritten() {
            return this.written;
        }

        void writeTo(OutputStream out) throws IOException {
            int litLength = this.literalLength();
            out.write(Pair.lengths(litLength, this.brLength));
            if (litLength >= 15) {
                Pair.writeLength(litLength - 15, out);
            }
            for (byte[] b : this.literals) {
                out.write(b);
            }
            if (this.hasBackReference()) {
                ByteUtils.toLittleEndian(out, (long)this.brOffset, 2);
                if (this.brLength - 4 >= 15) {
                    Pair.writeLength(this.brLength - 4 - 15, out);
                }
            }
            this.written = true;
        }

        private int literalLength() {
            int length = 0;
            for (byte[] b : this.literals) {
                length += b.length;
            }
            return length;
        }

        private static int lengths(int litLength, int brLength) {
            int l;
            int n = l = litLength < 15 ? litLength : 15;
            int br = brLength < 4 ? 0 : (brLength < 19 ? brLength - 4 : 15);
            return l << 4 | br;
        }

        private static void writeLength(int length, OutputStream out) throws IOException {
            while (length >= 255) {
                out.write(255);
                length -= 255;
            }
            out.write(length);
        }

        private int backReferenceLength() {
            return this.brLength;
        }

        private void prependTo(Pair other) {
            Iterator<byte[]> listBackwards = this.literals.descendingIterator();
            while (listBackwards.hasNext()) {
                other.prependLiteral(listBackwards.next());
            }
        }

        private Pair splitWithNewBackReferenceLengthOf(int newBackReferenceLength) {
            Pair p = new Pair();
            p.literals.addAll(this.literals);
            p.brOffset = this.brOffset;
            p.brLength = newBackReferenceLength;
            return p;
        }
    }
}

