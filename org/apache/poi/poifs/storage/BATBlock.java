/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.poifs.storage;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.List;
import org.apache.poi.poifs.common.POIFSBigBlockSize;
import org.apache.poi.poifs.storage.BlockWritable;
import org.apache.poi.poifs.storage.HeaderBlock;
import org.apache.poi.util.LittleEndian;

public final class BATBlock
implements BlockWritable {
    private POIFSBigBlockSize bigBlockSize;
    private int[] _values;
    private boolean _has_free_sectors;
    private int ourBlockIndex;

    private BATBlock(POIFSBigBlockSize bigBlockSize) {
        this.bigBlockSize = bigBlockSize;
        int _entries_per_block = bigBlockSize.getBATEntriesPerBlock();
        this._values = new int[_entries_per_block];
        this._has_free_sectors = true;
        Arrays.fill(this._values, -1);
    }

    private void recomputeFree() {
        boolean hasFree = false;
        for (int _value : this._values) {
            if (_value != -1) continue;
            hasFree = true;
            break;
        }
        this._has_free_sectors = hasFree;
    }

    public static BATBlock createBATBlock(POIFSBigBlockSize bigBlockSize, ByteBuffer data) {
        BATBlock block = new BATBlock(bigBlockSize);
        byte[] buffer = new byte[4];
        for (int i = 0; i < block._values.length; ++i) {
            data.get(buffer);
            block._values[i] = LittleEndian.getInt(buffer);
        }
        block.recomputeFree();
        return block;
    }

    public static BATBlock createEmptyBATBlock(POIFSBigBlockSize bigBlockSize, boolean isXBAT) {
        BATBlock block = new BATBlock(bigBlockSize);
        if (isXBAT) {
            int _entries_per_xbat_block = bigBlockSize.getXBATEntriesPerBlock();
            block._values[_entries_per_xbat_block] = -2;
        }
        return block;
    }

    public static long calculateMaximumSize(POIFSBigBlockSize bigBlockSize, int numBATs) {
        long size = 1L;
        return (size += (long)numBATs * (long)bigBlockSize.getBATEntriesPerBlock()) * (long)bigBlockSize.getBigBlockSize();
    }

    public static long calculateMaximumSize(HeaderBlock header) {
        return BATBlock.calculateMaximumSize(header.getBigBlockSize(), header.getBATCount());
    }

    public static BATBlockAndIndex getBATBlockAndIndex(int offset, HeaderBlock header, List<BATBlock> bats) {
        POIFSBigBlockSize bigBlockSize = header.getBigBlockSize();
        int entriesPerBlock = bigBlockSize.getBATEntriesPerBlock();
        int whichBAT = offset / entriesPerBlock;
        int index = offset % entriesPerBlock;
        return new BATBlockAndIndex(index, bats.get(whichBAT));
    }

    public static BATBlockAndIndex getSBATBlockAndIndex(int offset, HeaderBlock header, List<BATBlock> sbats) {
        return BATBlock.getBATBlockAndIndex(offset, header, sbats);
    }

    public boolean hasFreeSectors() {
        return this._has_free_sectors;
    }

    public int getUsedSectors(boolean isAnXBAT) {
        int usedSectors = 0;
        int toCheck = this._values.length;
        if (isAnXBAT) {
            --toCheck;
        }
        for (int k = 0; k < toCheck; ++k) {
            if (this._values[k] == -1) continue;
            ++usedSectors;
        }
        return usedSectors;
    }

    public int getValueAt(int relativeOffset) {
        if (relativeOffset >= this._values.length) {
            throw new ArrayIndexOutOfBoundsException("Unable to fetch offset " + relativeOffset + " as the BAT only contains " + this._values.length + " entries");
        }
        return this._values[relativeOffset];
    }

    public void setValueAt(int relativeOffset, int value) {
        int oldValue = this._values[relativeOffset];
        this._values[relativeOffset] = value;
        if (value == -1) {
            this._has_free_sectors = true;
            return;
        }
        if (oldValue == -1) {
            this.recomputeFree();
        }
    }

    public void setOurBlockIndex(int index) {
        this.ourBlockIndex = index;
    }

    public int getOurBlockIndex() {
        return this.ourBlockIndex;
    }

    @Override
    public void writeBlocks(OutputStream stream) throws IOException {
        stream.write(this.serialize());
    }

    public void writeData(ByteBuffer block) {
        block.put(this.serialize());
    }

    private byte[] serialize() {
        byte[] data = new byte[this.bigBlockSize.getBigBlockSize()];
        int offset = 0;
        for (int _value : this._values) {
            LittleEndian.putInt(data, offset, _value);
            offset += 4;
        }
        return data;
    }

    public static final class BATBlockAndIndex {
        private final int index;
        private final BATBlock block;

        private BATBlockAndIndex(int index, BATBlock block) {
            this.index = index;
            this.block = block;
        }

        public int getIndex() {
            return this.index;
        }

        public BATBlock getBlock() {
            return this.block;
        }
    }
}

