/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.xssf.binary;

import com.zaxxer.sparsebits.SparseBitSet;
import java.io.IOException;
import java.io.InputStream;
import org.apache.poi.util.IOUtils;
import org.apache.poi.util.Internal;
import org.apache.poi.util.LittleEndianInputStream;
import org.apache.poi.xssf.binary.XSSFBParseException;

@Internal
public abstract class XSSFBParser {
    private static final int MAX_RECORD_LENGTH = 1000000;
    private final LittleEndianInputStream is;
    private final SparseBitSet records;

    public XSSFBParser(InputStream is) {
        this.is = new LittleEndianInputStream(is);
        this.records = null;
    }

    protected XSSFBParser(InputStream is, SparseBitSet bitSet) {
        this.is = new LittleEndianInputStream(is);
        this.records = bitSet;
    }

    public void parse() throws IOException {
        int bInt;
        while ((bInt = this.is.read()) != -1) {
            this.readNext((byte)bInt);
        }
        return;
    }

    private void readNext(byte b1) throws IOException {
        int recordId = 0;
        if ((b1 >> 7 & 1) == 1) {
            byte b2 = this.is.readByte();
            b1 = (byte)(b1 & 0xFFFFFF7F);
            b2 = (byte)(b2 & 0xFFFFFF7F);
            recordId = (b2 << 7) + b1;
        } else {
            recordId = b1;
        }
        long recordLength = 0L;
        boolean halt = false;
        for (int i = 0; i < 4 && !halt; ++i) {
            byte b = this.is.readByte();
            halt = (b >> 7 & 1) == 0;
            b = (byte)(b & 0xFFFFFF7F);
            recordLength += (long)(b << i * 7);
        }
        if (this.records == null || this.records.get(recordId)) {
            byte[] buff = IOUtils.safelyAllocate(recordLength, 1000000);
            this.is.readFully(buff);
            this.handleRecord(recordId, buff);
        } else {
            long length = IOUtils.skipFully(this.is, recordLength);
            if (length != recordLength) {
                throw new XSSFBParseException("End of file reached before expected.\tTried to skip " + recordLength + ", but only skipped " + length);
            }
        }
    }

    public abstract void handleRecord(int var1, byte[] var2) throws XSSFBParseException;
}

