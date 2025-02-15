/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.poifs.storage;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.util.Arrays;
import org.apache.poi.hssf.OldExcelFormatException;
import org.apache.poi.poifs.common.POIFSBigBlockSize;
import org.apache.poi.poifs.common.POIFSConstants;
import org.apache.poi.poifs.filesystem.FileMagic;
import org.apache.poi.poifs.filesystem.NotOLE2FileException;
import org.apache.poi.poifs.filesystem.OfficeXmlFileException;
import org.apache.poi.poifs.storage.HeaderBlockConstants;
import org.apache.poi.util.HexDump;
import org.apache.poi.util.IOUtils;
import org.apache.poi.util.IntegerField;
import org.apache.poi.util.LittleEndian;
import org.apache.poi.util.LongField;
import org.apache.poi.util.ShortField;

public final class HeaderBlock
implements HeaderBlockConstants {
    private static final int MAX_RECORD_LENGTH = 100000;
    private static final byte _default_value = -1;
    private final POIFSBigBlockSize bigBlockSize;
    private int _bat_count;
    private int _property_start;
    private int _sbat_start;
    private int _sbat_count;
    private int _xbat_start;
    private int _xbat_count;
    private final byte[] _data;

    public HeaderBlock(InputStream stream) throws IOException {
        this(HeaderBlock.readFirst512(stream));
        if (this.bigBlockSize.getBigBlockSize() != 512) {
            int rest = this.bigBlockSize.getBigBlockSize() - 512;
            byte[] tmp = IOUtils.safelyAllocate(rest, 100000);
            IOUtils.readFully(stream, tmp);
        }
    }

    public HeaderBlock(ByteBuffer buffer) throws IOException {
        this(IOUtils.toByteArray(buffer, 512));
    }

    private HeaderBlock(byte[] data) throws IOException {
        this._data = (byte[])data.clone();
        FileMagic fm = FileMagic.valueOf(data);
        switch (fm) {
            case OLE2: {
                break;
            }
            case OOXML: {
                throw new OfficeXmlFileException("The supplied data appears to be in the Office 2007+ XML. You are calling the part of POI that deals with OLE2 Office Documents. You need to call a different part of POI to process this data (eg XSSF instead of HSSF)");
            }
            case XML: {
                throw new NotOLE2FileException("The supplied data appears to be a raw XML file. Formats such as Office 2003 XML are not supported");
            }
            case MSWRITE: {
                throw new NotOLE2FileException("The supplied data appears to be in the old MS Write format. Apache POI doesn't currently support this format");
            }
            case WORD2: {
                throw new NotOLE2FileException("The supplied data appears to be an old Word version 2 file. Apache POI doesn't currently support this format");
            }
            case BIFF2: 
            case BIFF3: 
            case BIFF4: {
                throw new OldExcelFormatException("The supplied data appears to be in " + (Object)((Object)fm) + " format. HSSF only supports the BIFF8 format, try OldExcelExtractor");
            }
            default: {
                String exp = HexDump.longToHex(-2226271756974174256L);
                String act = HexDump.longToHex(LittleEndian.getLong(data, 0));
                throw new NotOLE2FileException("Invalid header signature; read " + act + ", expected " + exp + " - Your file appears not to be a valid OLE2 document");
            }
        }
        if (this._data[30] == 12) {
            this.bigBlockSize = POIFSConstants.LARGER_BIG_BLOCK_SIZE_DETAILS;
        } else if (this._data[30] == 9) {
            this.bigBlockSize = POIFSConstants.SMALLER_BIG_BLOCK_SIZE_DETAILS;
        } else {
            throw new IOException("Unsupported blocksize  (2^" + this._data[30] + "). Expected 2^9 or 2^12.");
        }
        this._bat_count = new IntegerField(44, data).get();
        this._property_start = new IntegerField(48, this._data).get();
        this._sbat_start = new IntegerField(60, this._data).get();
        this._sbat_count = new IntegerField(64, this._data).get();
        this._xbat_start = new IntegerField(68, this._data).get();
        this._xbat_count = new IntegerField(72, this._data).get();
    }

    public HeaderBlock(POIFSBigBlockSize bigBlockSize) {
        this.bigBlockSize = bigBlockSize;
        this._data = new byte[512];
        Arrays.fill(this._data, (byte)-1);
        new LongField(0, -2226271756974174256L, this._data);
        new IntegerField(8, 0, this._data);
        new IntegerField(12, 0, this._data);
        new IntegerField(16, 0, this._data);
        new IntegerField(20, 0, this._data);
        new ShortField(24, 59, this._data);
        new ShortField(26, 3, this._data);
        new ShortField(28, -2, this._data);
        new ShortField(30, bigBlockSize.getHeaderValue(), this._data);
        new IntegerField(32, 6, this._data);
        new IntegerField(36, 0, this._data);
        new IntegerField(40, 0, this._data);
        new IntegerField(52, 0, this._data);
        new IntegerField(56, 4096, this._data);
        this._bat_count = 0;
        this._sbat_count = 0;
        this._xbat_count = 0;
        this._property_start = -2;
        this._sbat_start = -2;
        this._xbat_start = -2;
    }

    private static byte[] readFirst512(InputStream stream) throws IOException {
        byte[] data = new byte[512];
        int bsCount = IOUtils.readFully(stream, data);
        if (bsCount != 512) {
            throw HeaderBlock.alertShortRead(bsCount);
        }
        return data;
    }

    private static IOException alertShortRead(int pRead) {
        int read = pRead < 0 ? 0 : pRead;
        String type = " byte" + (read == 1 ? "" : "s");
        return new IOException("Unable to read entire header; " + read + type + " read; expected 512 bytes");
    }

    public int getPropertyStart() {
        return this._property_start;
    }

    public void setPropertyStart(int startBlock) {
        this._property_start = startBlock;
    }

    public int getSBATStart() {
        return this._sbat_start;
    }

    public int getSBATCount() {
        return this._sbat_count;
    }

    public void setSBATStart(int startBlock) {
        this._sbat_start = startBlock;
    }

    public void setSBATBlockCount(int count) {
        this._sbat_count = count;
    }

    public int getBATCount() {
        return this._bat_count;
    }

    public void setBATCount(int count) {
        this._bat_count = count;
    }

    public int[] getBATArray() {
        int[] result = new int[Math.min(this._bat_count, 109)];
        int offset = 76;
        for (int j = 0; j < result.length; ++j) {
            result[j] = LittleEndian.getInt(this._data, offset);
            offset += 4;
        }
        return result;
    }

    public void setBATArray(int[] bat_array) {
        int i;
        int count = Math.min(bat_array.length, 109);
        int blank = 109 - count;
        int offset = 76;
        for (i = 0; i < count; ++i) {
            LittleEndian.putInt(this._data, offset, bat_array[i]);
            offset += 4;
        }
        for (i = 0; i < blank; ++i) {
            LittleEndian.putInt(this._data, offset, -1);
            offset += 4;
        }
    }

    public int getXBATCount() {
        return this._xbat_count;
    }

    public void setXBATCount(int count) {
        this._xbat_count = count;
    }

    public int getXBATIndex() {
        return this._xbat_start;
    }

    public void setXBATStart(int startBlock) {
        this._xbat_start = startBlock;
    }

    public POIFSBigBlockSize getBigBlockSize() {
        return this.bigBlockSize;
    }

    public void writeData(OutputStream stream) throws IOException {
        new IntegerField(44, this._bat_count, this._data);
        new IntegerField(48, this._property_start, this._data);
        new IntegerField(60, this._sbat_start, this._data);
        new IntegerField(64, this._sbat_count, this._data);
        new IntegerField(68, this._xbat_start, this._data);
        new IntegerField(72, this._xbat_count, this._data);
        stream.write(this._data, 0, 512);
        for (int i = 512; i < this.bigBlockSize.getBigBlockSize(); ++i) {
            stream.write(0);
        }
    }
}

