/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.hpsf;

import java.io.IOException;
import java.io.OutputStream;
import java.math.BigInteger;
import java.util.Date;
import org.apache.poi.util.Internal;
import org.apache.poi.util.LittleEndian;
import org.apache.poi.util.LittleEndianByteArrayInputStream;

@Internal
public class Filetime {
    private static final BigInteger EPOCH_DIFF = BigInteger.valueOf(-11644473600000L);
    private static final BigInteger NANO_100 = BigInteger.valueOf(10000L);
    private long fileTime;

    public Filetime() {
    }

    public Filetime(Date date) {
        this.fileTime = Filetime.dateToFileTime(date);
    }

    public void read(LittleEndianByteArrayInputStream lei) {
        this.fileTime = lei.readLong();
    }

    public byte[] toByteArray() {
        byte[] result = new byte[8];
        LittleEndian.putLong(result, 0, this.fileTime);
        return result;
    }

    public int write(OutputStream out) throws IOException {
        out.write(this.toByteArray());
        return 8;
    }

    public Date getJavaValue() {
        return Filetime.filetimeToDate(this.fileTime);
    }

    public static Date filetimeToDate(long filetime) {
        BigInteger bi = filetime < 0L ? Filetime.twoComplement(filetime) : BigInteger.valueOf(filetime);
        return new Date(bi.divide(NANO_100).add(EPOCH_DIFF).longValue());
    }

    public static long dateToFileTime(Date date) {
        return BigInteger.valueOf(date.getTime()).subtract(EPOCH_DIFF).multiply(NANO_100).longValue();
    }

    public static boolean isUndefined(Date date) {
        return date == null || Filetime.dateToFileTime(date) == 0L;
    }

    private static BigInteger twoComplement(long val) {
        byte[] contents = new byte[]{(byte)(val < 0L ? 0 : -1), (byte)(val >> 56 & 0xFFL), (byte)(val >> 48 & 0xFFL), (byte)(val >> 40 & 0xFFL), (byte)(val >> 32 & 0xFFL), (byte)(val >> 24 & 0xFFL), (byte)(val >> 16 & 0xFFL), (byte)(val >> 8 & 0xFFL), (byte)(val & 0xFFL)};
        return new BigInteger(contents);
    }
}

