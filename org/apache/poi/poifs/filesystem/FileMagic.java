/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.poifs.filesystem;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import org.apache.poi.poifs.common.POIFSConstants;
import org.apache.poi.util.IOUtils;
import org.apache.poi.util.LittleEndian;
import org.apache.poi.util.LocaleUtil;

public enum FileMagic {
    OLE2(-2226271756974174256L),
    OOXML(new byte[][]{POIFSConstants.OOXML_FILE_HEADER}),
    XML(new byte[][]{POIFSConstants.RAW_XML_FILE_HEADER}),
    BIFF2(new byte[][]{{9, 0, 4, 0, 0, 0, 63, 0}}),
    BIFF3(new byte[][]{{9, 2, 6, 0, 0, 0, 63, 0}}),
    BIFF4({9, 4, 6, 0, 0, 0, 63, 0}, {9, 4, 6, 0, 0, 0, 0, 1}),
    MSWRITE({49, -66, 0, 0}, {50, -66, 0, 0}),
    RTF("{\\rtf"),
    PDF("%PDF"),
    HTML("<!DOCTYP", "<html", "\n\r<html", "\r\n<html", "\r<html", "\n<html", "<HTML", "\r\n<HTML", "\n\r<HTML", "\r<HTML", "\n<HTML"),
    WORD2(new byte[][]{{-37, -91, 45, 0}}),
    JPEG({-1, -40, -1, -37}, {-1, -40, -1, -32, 63, 63, 74, 70, 73, 70, 0, 1}, {-1, -40, -1, -18}, {-1, -40, -1, -31, 63, 63, 69, 120, 105, 102, 0, 0}),
    GIF("GIF87a", "GIF89a"),
    PNG(new byte[][]{{-119, 80, 78, 71, 13, 10, 26, 10}}),
    TIFF("II*\u0000", "MM\u0000*"),
    WMF(new byte[][]{{-41, -51, -58, -102}}),
    EMF(new byte[][]{{1, 0, 0, 0, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 32, 69, 77, 70}}),
    BMP(new byte[][]{{66, 77}}),
    UNKNOWN(new byte[][]{new byte[0]});

    static final int MAX_PATTERN_LENGTH = 44;
    final byte[][] magic;

    private FileMagic(long magic) {
        this.magic = new byte[1][8];
        LittleEndian.putLong(this.magic[0], 0, magic);
    }

    private FileMagic(byte[] ... magic) {
        this.magic = magic;
    }

    private FileMagic(String ... magic) {
        this.magic = new byte[magic.length][];
        int i = 0;
        for (String s : magic) {
            this.magic[i++] = s.getBytes(LocaleUtil.CHARSET_1252);
        }
    }

    public static FileMagic valueOf(byte[] magic) {
        for (FileMagic fm : FileMagic.values()) {
            for (byte[] ma : fm.magic) {
                if (magic.length < ma.length || !FileMagic.findMagic(ma, magic)) continue;
                return fm;
            }
        }
        return UNKNOWN;
    }

    private static boolean findMagic(byte[] expected, byte[] actual) {
        int i = 0;
        for (byte expectedByte : expected) {
            if (actual[i++] == expectedByte || expectedByte == 63) continue;
            return false;
        }
        return true;
    }

    public static FileMagic valueOf(File inp) throws IOException {
        try (FileInputStream fis = new FileInputStream(inp);){
            byte[] data = new byte[44];
            int read = IOUtils.readFully(fis, data, 0, 44);
            if (read == -1) {
                FileMagic fileMagic = UNKNOWN;
                return fileMagic;
            }
            data = Arrays.copyOf(data, read);
            FileMagic fileMagic = FileMagic.valueOf(data);
            return fileMagic;
        }
    }

    public static FileMagic valueOf(InputStream inp) throws IOException {
        if (!inp.markSupported()) {
            throw new IOException("getFileMagic() only operates on streams which support mark(int)");
        }
        byte[] data = IOUtils.peekFirstNBytes(inp, 44);
        return FileMagic.valueOf(data);
    }

    public static InputStream prepareToCheckMagic(InputStream stream) {
        if (stream.markSupported()) {
            return stream;
        }
        return new BufferedInputStream(stream);
    }
}

