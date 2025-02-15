/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.compress.compressors.z;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteOrder;
import org.apache.commons.compress.compressors.lzw.LZWInputStream;

public class ZCompressorInputStream
extends LZWInputStream {
    private static final int MAGIC_1 = 31;
    private static final int MAGIC_2 = 157;
    private static final int BLOCK_MODE_MASK = 128;
    private static final int MAX_CODE_SIZE_MASK = 31;
    private final boolean blockMode;
    private final int maxCodeSize;
    private long totalCodesRead = 0L;

    public ZCompressorInputStream(InputStream inputStream, int memoryLimitInKb) throws IOException {
        super(inputStream, ByteOrder.LITTLE_ENDIAN);
        int firstByte = (int)this.in.readBits(8);
        int secondByte = (int)this.in.readBits(8);
        int thirdByte = (int)this.in.readBits(8);
        if (firstByte != 31 || secondByte != 157 || thirdByte < 0) {
            throw new IOException("Input is not in .Z format");
        }
        this.blockMode = (thirdByte & 0x80) != 0;
        this.maxCodeSize = thirdByte & 0x1F;
        if (this.blockMode) {
            this.setClearCode(9);
        }
        this.initializeTables(this.maxCodeSize, memoryLimitInKb);
        this.clearEntries();
    }

    public ZCompressorInputStream(InputStream inputStream) throws IOException {
        this(inputStream, -1);
    }

    private void clearEntries() {
        this.setTableSize(256 + (this.blockMode ? 1 : 0));
    }

    @Override
    protected int readNextCode() throws IOException {
        int code = super.readNextCode();
        if (code >= 0) {
            ++this.totalCodesRead;
        }
        return code;
    }

    private void reAlignReading() throws IOException {
        long codeReadsToThrowAway = 8L - this.totalCodesRead % 8L;
        if (codeReadsToThrowAway == 8L) {
            codeReadsToThrowAway = 0L;
        }
        for (long i = 0L; i < codeReadsToThrowAway; ++i) {
            this.readNextCode();
        }
        this.in.clearBitCache();
    }

    @Override
    protected int addEntry(int previousCode, byte character) throws IOException {
        int maxTableSize = 1 << this.getCodeSize();
        int r = this.addEntry(previousCode, character, maxTableSize);
        if (this.getTableSize() == maxTableSize && this.getCodeSize() < this.maxCodeSize) {
            this.reAlignReading();
            this.incrementCodeSize();
        }
        return r;
    }

    @Override
    protected int decompressNextSymbol() throws IOException {
        int code = this.readNextCode();
        if (code < 0) {
            return -1;
        }
        if (this.blockMode && code == this.getClearCode()) {
            this.clearEntries();
            this.reAlignReading();
            this.resetCodeSize();
            this.resetPreviousCode();
            return 0;
        }
        boolean addedUnfinishedEntry = false;
        if (code == this.getTableSize()) {
            this.addRepeatOfPreviousCode();
            addedUnfinishedEntry = true;
        } else if (code > this.getTableSize()) {
            throw new IOException(String.format("Invalid %d bit code 0x%x", this.getCodeSize(), code));
        }
        return this.expandCodeToOutputStack(code, addedUnfinishedEntry);
    }

    public static boolean matches(byte[] signature, int length) {
        return length > 3 && signature[0] == 31 && signature[1] == -99;
    }
}

