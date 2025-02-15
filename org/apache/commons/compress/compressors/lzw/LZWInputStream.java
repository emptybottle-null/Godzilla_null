/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.compress.compressors.lzw;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteOrder;
import org.apache.commons.compress.MemoryLimitException;
import org.apache.commons.compress.compressors.CompressorInputStream;
import org.apache.commons.compress.utils.BitInputStream;
import org.apache.commons.compress.utils.InputStreamStatistics;

public abstract class LZWInputStream
extends CompressorInputStream
implements InputStreamStatistics {
    protected static final int DEFAULT_CODE_SIZE = 9;
    protected static final int UNUSED_PREFIX = -1;
    private final byte[] oneByte = new byte[1];
    protected final BitInputStream in;
    private int clearCode = -1;
    private int codeSize = 9;
    private byte previousCodeFirstChar;
    private int previousCode = -1;
    private int tableSize;
    private int[] prefixes;
    private byte[] characters;
    private byte[] outputStack;
    private int outputStackLocation;

    protected LZWInputStream(InputStream inputStream, ByteOrder byteOrder) {
        this.in = new BitInputStream(inputStream, byteOrder);
    }

    @Override
    public void close() throws IOException {
        this.in.close();
    }

    @Override
    public int read() throws IOException {
        int ret = this.read(this.oneByte);
        if (ret < 0) {
            return ret;
        }
        return 0xFF & this.oneByte[0];
    }

    @Override
    public int read(byte[] b, int off, int len) throws IOException {
        int bytesRead = this.readFromStack(b, off, len);
        while (len - bytesRead > 0) {
            int result = this.decompressNextSymbol();
            if (result < 0) {
                if (bytesRead > 0) {
                    this.count(bytesRead);
                    return bytesRead;
                }
                return result;
            }
            bytesRead += this.readFromStack(b, off + bytesRead, len - bytesRead);
        }
        this.count(bytesRead);
        return bytesRead;
    }

    @Override
    public long getCompressedCount() {
        return this.in.getBytesRead();
    }

    protected abstract int decompressNextSymbol() throws IOException;

    protected abstract int addEntry(int var1, byte var2) throws IOException;

    protected void setClearCode(int codeSize) {
        this.clearCode = 1 << codeSize - 1;
    }

    protected void initializeTables(int maxCodeSize, int memoryLimitInKb) throws MemoryLimitException {
        int maxTableSize;
        long memoryUsageInBytes;
        long memoryUsageInKb;
        if (maxCodeSize <= 0) {
            throw new IllegalArgumentException("maxCodeSize is " + maxCodeSize + ", must be bigger than 0");
        }
        if (memoryLimitInKb > -1 && (memoryUsageInKb = (memoryUsageInBytes = (long)(maxTableSize = 1 << maxCodeSize) * 6L) >> 10) > (long)memoryLimitInKb) {
            throw new MemoryLimitException(memoryUsageInKb, memoryLimitInKb);
        }
        this.initializeTables(maxCodeSize);
    }

    protected void initializeTables(int maxCodeSize) {
        if (maxCodeSize <= 0) {
            throw new IllegalArgumentException("maxCodeSize is " + maxCodeSize + ", must be bigger than 0");
        }
        int maxTableSize = 1 << maxCodeSize;
        this.prefixes = new int[maxTableSize];
        this.characters = new byte[maxTableSize];
        this.outputStack = new byte[maxTableSize];
        this.outputStackLocation = maxTableSize;
        int max = 256;
        for (int i = 0; i < 256; ++i) {
            this.prefixes[i] = -1;
            this.characters[i] = (byte)i;
        }
    }

    protected int readNextCode() throws IOException {
        if (this.codeSize > 31) {
            throw new IllegalArgumentException("Code size must not be bigger than 31");
        }
        return (int)this.in.readBits(this.codeSize);
    }

    protected int addEntry(int previousCode, byte character, int maxTableSize) {
        if (this.tableSize < maxTableSize) {
            this.prefixes[this.tableSize] = previousCode;
            this.characters[this.tableSize] = character;
            return this.tableSize++;
        }
        return -1;
    }

    protected int addRepeatOfPreviousCode() throws IOException {
        if (this.previousCode == -1) {
            throw new IOException("The first code can't be a reference to its preceding code");
        }
        return this.addEntry(this.previousCode, this.previousCodeFirstChar);
    }

    protected int expandCodeToOutputStack(int code, boolean addedUnfinishedEntry) throws IOException {
        int entry = code;
        while (entry >= 0) {
            this.outputStack[--this.outputStackLocation] = this.characters[entry];
            entry = this.prefixes[entry];
        }
        if (this.previousCode != -1 && !addedUnfinishedEntry) {
            this.addEntry(this.previousCode, this.outputStack[this.outputStackLocation]);
        }
        this.previousCode = code;
        this.previousCodeFirstChar = this.outputStack[this.outputStackLocation];
        return this.outputStackLocation;
    }

    private int readFromStack(byte[] b, int off, int len) {
        int remainingInStack = this.outputStack.length - this.outputStackLocation;
        if (remainingInStack > 0) {
            int maxLength = Math.min(remainingInStack, len);
            System.arraycopy(this.outputStack, this.outputStackLocation, b, off, maxLength);
            this.outputStackLocation += maxLength;
            return maxLength;
        }
        return 0;
    }

    protected int getCodeSize() {
        return this.codeSize;
    }

    protected void resetCodeSize() {
        this.setCodeSize(9);
    }

    protected void setCodeSize(int cs) {
        this.codeSize = cs;
    }

    protected void incrementCodeSize() {
        ++this.codeSize;
    }

    protected void resetPreviousCode() {
        this.previousCode = -1;
    }

    protected int getPrefix(int offset) {
        return this.prefixes[offset];
    }

    protected void setPrefix(int offset, int value) {
        this.prefixes[offset] = value;
    }

    protected int getPrefixesLength() {
        return this.prefixes.length;
    }

    protected int getClearCode() {
        return this.clearCode;
    }

    protected int getTableSize() {
        return this.tableSize;
    }

    protected void setTableSize(int newSize) {
        this.tableSize = newSize;
    }
}

