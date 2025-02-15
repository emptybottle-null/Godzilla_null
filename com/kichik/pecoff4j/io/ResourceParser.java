/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.kichik.pecoff4j.io;

import com.kichik.pecoff4j.io.DataReader;
import com.kichik.pecoff4j.io.IDataReader;
import com.kichik.pecoff4j.resources.Bitmap;
import com.kichik.pecoff4j.resources.BitmapFileHeader;
import com.kichik.pecoff4j.resources.BitmapInfoHeader;
import com.kichik.pecoff4j.resources.FixedFileInfo;
import com.kichik.pecoff4j.resources.IconDirectory;
import com.kichik.pecoff4j.resources.IconDirectoryEntry;
import com.kichik.pecoff4j.resources.IconImage;
import com.kichik.pecoff4j.resources.Manifest;
import com.kichik.pecoff4j.resources.RGBQuad;
import com.kichik.pecoff4j.resources.StringFileInfo;
import com.kichik.pecoff4j.resources.StringPair;
import com.kichik.pecoff4j.resources.StringTable;
import com.kichik.pecoff4j.resources.VarFileInfo;
import com.kichik.pecoff4j.resources.VersionInfo;
import java.io.EOFException;
import java.io.IOException;

public class ResourceParser {
    public static Bitmap readBitmap(IDataReader dr) throws IOException {
        Bitmap bm = new Bitmap();
        bm.setFileHeader(ResourceParser.readBitmapFileHeader(dr));
        bm.setInfoHeader(ResourceParser.readBitmapInfoHeader(dr));
        return bm;
    }

    public static BitmapFileHeader readBitmapFileHeader(IDataReader dr) throws IOException {
        BitmapFileHeader bfh = new BitmapFileHeader();
        bfh.setType(dr.readWord());
        bfh.setSize(dr.readDoubleWord());
        bfh.setReserved1(dr.readWord());
        bfh.setReserved2(dr.readWord());
        bfh.setOffBits(dr.readDoubleWord());
        return bfh;
    }

    public static BitmapInfoHeader readBitmapInfoHeader(IDataReader dr) throws IOException {
        BitmapInfoHeader bh = new BitmapInfoHeader();
        bh.setSize(dr.readDoubleWord());
        bh.setWidth(dr.readDoubleWord());
        bh.setHeight(dr.readDoubleWord());
        bh.setPlanes(dr.readWord());
        bh.setBitCount(dr.readWord());
        bh.setCompression(dr.readDoubleWord());
        bh.setSizeImage(dr.readDoubleWord());
        bh.setXpelsPerMeter(dr.readDoubleWord());
        bh.setYpelsPerMeter(dr.readDoubleWord());
        bh.setClrUsed(dr.readDoubleWord());
        bh.setClrImportant(dr.readDoubleWord());
        return bh;
    }

    public static FixedFileInfo readFixedFileInfo(IDataReader dr) throws IOException {
        FixedFileInfo ffi = new FixedFileInfo();
        ffi.setSignature(dr.readDoubleWord());
        ffi.setStrucVersion(dr.readDoubleWord());
        ffi.setFileVersionMS(dr.readDoubleWord());
        ffi.setFileVersionLS(dr.readDoubleWord());
        ffi.setProductVersionMS(dr.readDoubleWord());
        ffi.setProductVersionLS(dr.readDoubleWord());
        ffi.setFileFlagMask(dr.readDoubleWord());
        ffi.setFileFlags(dr.readDoubleWord());
        ffi.setFileOS(dr.readDoubleWord());
        ffi.setFileType(dr.readDoubleWord());
        ffi.setFileSubtype(dr.readDoubleWord());
        ffi.setFileDateMS(dr.readDoubleWord());
        ffi.setFileDateLS(dr.readDoubleWord());
        return ffi;
    }

    public static IconImage readIconImage(IDataReader dr, int bytesInRes) throws IOException {
        IconImage ii = new IconImage();
        int quadSize = 0;
        ii.setHeader(ResourceParser.readBitmapInfoHeader(dr));
        quadSize = ii.getHeader().getClrUsed() != 0 ? ii.getHeader().getClrUsed() : (ii.getHeader().getBitCount() <= 8 ? 1 << ii.getHeader().getBitCount() : 0);
        int numBytesPerLine = ii.getHeader().getWidth() * ii.getHeader().getPlanes() * ii.getHeader().getBitCount() + 31 >> 5 << 2;
        int xorSize = numBytesPerLine * ii.getHeader().getHeight() / 2;
        int andSize = bytesInRes - quadSize * 4 - ii.getHeader().getSize() - xorSize;
        if (quadSize > 0) {
            RGBQuad[] colors = new RGBQuad[quadSize];
            for (int i = 0; i < quadSize; ++i) {
                colors[i] = ResourceParser.readRGB(dr);
            }
            ii.setColors(colors);
        }
        byte[] xorMask = new byte[xorSize];
        dr.read(xorMask);
        ii.setXorMask(xorMask);
        byte[] andMask = new byte[andSize];
        dr.read(andMask);
        ii.setAndMask(andMask);
        return ii;
    }

    public static IconImage readPNG(byte[] data) {
        IconImage ii = new IconImage();
        ii.setPngData(data);
        return ii;
    }

    public static VersionInfo readVersionInfo(byte[] data) throws IOException {
        return ResourceParser.readVersionInfo(new DataReader(data));
    }

    public static VersionInfo readVersionInfo(IDataReader dr) throws IOException {
        VersionInfo vi;
        block3: {
            String key;
            int type;
            int valueLength;
            int length;
            int initialPos;
            int padding;
            vi = new VersionInfo();
            vi.setLength(dr.readWord());
            vi.setValueLength(dr.readWord());
            vi.setType(dr.readWord());
            vi.setKey(dr.readUnicode());
            ResourceParser.alignDataReader(dr);
            vi.setFixedFileInfo(ResourceParser.readFixedFileInfo(dr));
            while (true) {
                padding = ResourceParser.alignDataReader(dr);
                initialPos = dr.getPosition();
                length = dr.readWord();
                if (length == 0) break block3;
                valueLength = dr.readWord();
                type = dr.readWord();
                key = dr.readUnicode();
                if (!"VarFileInfo".equals(key)) break;
                vi.setVarFileInfo(ResourceParser.readVarFileInfo(dr, initialPos, length, valueLength, type, key, padding));
            }
            if ("StringFileInfo".equals(key)) {
                vi.setStringFileInfo(ResourceParser.readStringFileInfo(dr, initialPos, length, valueLength, type, key, padding));
            } else {
                dr.jumpTo(initialPos + length);
            }
        }
        return vi;
    }

    public static VarFileInfo readVarFileInfo(IDataReader dr, int initialPos, int length, int valueLength, int type, String key, int padding) throws IOException {
        VarFileInfo vfi = new VarFileInfo();
        vfi.setKey(key);
        String name = null;
        while ((name = dr.readUnicode()) != null) {
            if (name.length() == 2) {
                name = dr.readUnicode();
            }
            vfi.add(name, dr.readUnicode());
        }
        dr.jumpTo(initialPos + length - 2);
        return vfi;
    }

    public static VarFileInfo readVarFileInfo(IDataReader dr) throws IOException {
        VarFileInfo vfi = new VarFileInfo();
        vfi.setKey(dr.readUnicode());
        String name = null;
        while ((name = dr.readUnicode()) != null) {
            if (name.length() % 2 == 1) {
                dr.readWord();
            }
            vfi.add(name, dr.readUnicode());
        }
        return vfi;
    }

    public static StringTable readStringTable(IDataReader dr) throws IOException {
        int initialPos = dr.getPosition();
        StringTable vfi = new StringTable();
        vfi.setLength(dr.readWord());
        if (vfi.getLength() == 0) {
            return null;
        }
        vfi.setValueLength(dr.readWord());
        vfi.setType(dr.readWord());
        vfi.setKey(dr.readUnicode());
        vfi.setPadding(ResourceParser.alignDataReader(dr));
        while (dr.getPosition() - initialPos < vfi.getLength()) {
            vfi.add(ResourceParser.readStringPair(dr));
        }
        return vfi;
    }

    public static StringPair readStringPair(IDataReader dr) throws IOException {
        int initialPos = dr.getPosition();
        StringPair sp = new StringPair();
        sp.setLength(dr.readWord());
        sp.setValueLength(dr.readWord());
        sp.setType(dr.readWord());
        sp.setKey(dr.readUnicode());
        sp.setPadding(ResourceParser.alignDataReader(dr));
        int remainingWords = (sp.getLength() - (dr.getPosition() - initialPos)) / 2;
        int valueLength = sp.getValueLength();
        if (sp.getType() == 0) {
            valueLength /= 2;
        }
        if (valueLength > remainingWords) {
            valueLength = remainingWords;
        }
        sp.setValue(dr.readUnicode(valueLength).trim());
        int remainingBytes = sp.getLength() - (dr.getPosition() - initialPos);
        dr.skipBytes(remainingBytes);
        ResourceParser.alignDataReader(dr);
        return sp;
    }

    public static Manifest readManifest(IDataReader dr, int length) throws IOException {
        Manifest mf = new Manifest();
        mf.set(dr.readUtf(length));
        return mf;
    }

    public static RGBQuad readRGB(IDataReader dr) throws IOException {
        RGBQuad r = new RGBQuad();
        r.setBlue(dr.readByte());
        r.setGreen(dr.readByte());
        r.setRed(dr.readByte());
        r.setReserved(dr.readByte());
        return r;
    }

    public static StringFileInfo readStringFileInfo(IDataReader dr, int initialPos, int length, int valueLength, int type, String key, int padding) throws IOException {
        StringFileInfo sfi = new StringFileInfo();
        sfi.setLength(length);
        sfi.setValueLength(valueLength);
        sfi.setType(type);
        sfi.setKey(key);
        sfi.setPadding(padding);
        while (dr.getPosition() - initialPos < sfi.getLength()) {
            sfi.add(ResourceParser.readStringTable(dr));
        }
        return sfi;
    }

    public static StringFileInfo readStringFileInfo(IDataReader dr) throws IOException {
        int initialPos = dr.getPosition();
        StringFileInfo sfi = new StringFileInfo();
        sfi.setLength(dr.readWord());
        sfi.setValueLength(dr.readWord());
        sfi.setType(dr.readWord());
        sfi.setKey(dr.readUnicode());
        sfi.setPadding(ResourceParser.alignDataReader(dr));
        while (dr.getPosition() - initialPos < sfi.getLength()) {
            sfi.add(ResourceParser.readStringTable(dr));
        }
        return sfi;
    }

    public static IconDirectoryEntry readIconDirectoryEntry(IDataReader dr) throws IOException {
        IconDirectoryEntry ge = new IconDirectoryEntry();
        ge.setWidth(dr.readByte());
        ge.setHeight(dr.readByte());
        ge.setColorCount(dr.readByte());
        ge.setReserved(dr.readByte());
        ge.setPlanes(dr.readWord());
        ge.setBitCount(dr.readWord());
        ge.setBytesInRes(dr.readDoubleWord());
        ge.setOffset(dr.readDoubleWord());
        return ge;
    }

    public static IconDirectory readIconDirectory(IDataReader dr) throws IOException {
        IconDirectory gi = new IconDirectory();
        gi.setReserved(dr.readWord());
        gi.setType(dr.readWord());
        int count = dr.readWord();
        for (int i = 0; i < count; ++i) {
            gi.add(ResourceParser.readIconDirectoryEntry(dr));
        }
        return gi;
    }

    private static int alignDataReader(IDataReader dr) throws IOException {
        int off = (4 - dr.getPosition() % 4) % 4;
        try {
            dr.skipBytes(off);
        } catch (EOFException eOFException) {
            // empty catch block
        }
        return off;
    }
}

