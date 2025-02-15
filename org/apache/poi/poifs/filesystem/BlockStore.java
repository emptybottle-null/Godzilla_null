/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.poifs.filesystem;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.apache.poi.poifs.storage.BATBlock;

public abstract class BlockStore {
    protected abstract int getBlockStoreBlockSize();

    protected abstract ByteBuffer getBlockAt(int var1) throws IOException;

    protected abstract ByteBuffer createBlockIfNeeded(int var1) throws IOException;

    protected abstract BATBlock.BATBlockAndIndex getBATBlockAndIndex(int var1);

    protected abstract int getNextBlock(int var1);

    protected abstract void setNextBlock(int var1, int var2);

    protected abstract int getFreeBlock() throws IOException;

    protected abstract ChainLoopDetector getChainLoopDetector() throws IOException;

    protected class ChainLoopDetector {
        private boolean[] used_blocks;

        protected ChainLoopDetector(long rawSize) {
            int blkSize = BlockStore.this.getBlockStoreBlockSize();
            int numBlocks = (int)(rawSize / (long)blkSize);
            if (rawSize % (long)blkSize != 0L) {
                ++numBlocks;
            }
            this.used_blocks = new boolean[numBlocks];
        }

        protected void claim(int offset) {
            if (offset >= this.used_blocks.length) {
                return;
            }
            if (this.used_blocks[offset]) {
                throw new IllegalStateException("Potential loop detected - Block " + offset + " was already claimed but was just requested again");
            }
            this.used_blocks[offset] = true;
        }
    }
}

