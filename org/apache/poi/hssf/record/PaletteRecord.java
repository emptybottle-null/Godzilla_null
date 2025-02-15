/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.hssf.record;

import java.util.ArrayList;
import java.util.Collections;
import org.apache.poi.hssf.record.RecordInputStream;
import org.apache.poi.hssf.record.StandardRecord;
import org.apache.poi.util.LittleEndianOutput;

public final class PaletteRecord
extends StandardRecord {
    public static final short sid = 146;
    public static final byte STANDARD_PALETTE_SIZE = 56;
    public static final short FIRST_COLOR_INDEX = 8;
    private final ArrayList<PColor> _colors = new ArrayList();

    public PaletteRecord() {
        PColor[] defaultPalette = PaletteRecord.createDefaultPalette();
        this._colors.ensureCapacity(defaultPalette.length);
        Collections.addAll(this._colors, defaultPalette);
    }

    public PaletteRecord(PaletteRecord other) {
        super(other);
        this._colors.ensureCapacity(other._colors.size());
        other._colors.stream().map(PColor::new).forEach(this._colors::add);
    }

    public PaletteRecord(RecordInputStream in) {
        int field_1_numcolors = in.readShort();
        this._colors.ensureCapacity(field_1_numcolors);
        for (int k = 0; k < field_1_numcolors; ++k) {
            this._colors.add(new PColor(in));
        }
    }

    @Override
    public String toString() {
        StringBuilder buffer = new StringBuilder();
        buffer.append("[PALETTE]\n");
        buffer.append("  numcolors     = ").append(this._colors.size()).append('\n');
        for (int i = 0; i < this._colors.size(); ++i) {
            PColor c = this._colors.get(i);
            buffer.append("* colornum      = ").append(i).append('\n');
            buffer.append(c);
            buffer.append("/*colornum      = ").append(i).append('\n');
        }
        buffer.append("[/PALETTE]\n");
        return buffer.toString();
    }

    @Override
    public void serialize(LittleEndianOutput out) {
        out.writeShort(this._colors.size());
        for (PColor color : this._colors) {
            color.serialize(out);
        }
    }

    @Override
    protected int getDataSize() {
        return 2 + this._colors.size() * 4;
    }

    @Override
    public short getSid() {
        return 146;
    }

    public byte[] getColor(int byteIndex) {
        int i = byteIndex - 8;
        if (i < 0 || i >= this._colors.size()) {
            return null;
        }
        return this._colors.get(i).getTriplet();
    }

    public void setColor(short byteIndex, byte red, byte green, byte blue) {
        int i = byteIndex - 8;
        if (i < 0 || i >= 56) {
            return;
        }
        while (this._colors.size() <= i) {
            this._colors.add(new PColor(0, 0, 0));
        }
        PColor custColor = new PColor(red, green, blue);
        this._colors.set(i, custColor);
    }

    @Override
    public PaletteRecord copy() {
        return new PaletteRecord(this);
    }

    private static PColor[] createDefaultPalette() {
        return new PColor[]{PaletteRecord.pc(0, 0, 0), PaletteRecord.pc(255, 255, 255), PaletteRecord.pc(255, 0, 0), PaletteRecord.pc(0, 255, 0), PaletteRecord.pc(0, 0, 255), PaletteRecord.pc(255, 255, 0), PaletteRecord.pc(255, 0, 255), PaletteRecord.pc(0, 255, 255), PaletteRecord.pc(128, 0, 0), PaletteRecord.pc(0, 128, 0), PaletteRecord.pc(0, 0, 128), PaletteRecord.pc(128, 128, 0), PaletteRecord.pc(128, 0, 128), PaletteRecord.pc(0, 128, 128), PaletteRecord.pc(192, 192, 192), PaletteRecord.pc(128, 128, 128), PaletteRecord.pc(153, 153, 255), PaletteRecord.pc(153, 51, 102), PaletteRecord.pc(255, 255, 204), PaletteRecord.pc(204, 255, 255), PaletteRecord.pc(102, 0, 102), PaletteRecord.pc(255, 128, 128), PaletteRecord.pc(0, 102, 204), PaletteRecord.pc(204, 204, 255), PaletteRecord.pc(0, 0, 128), PaletteRecord.pc(255, 0, 255), PaletteRecord.pc(255, 255, 0), PaletteRecord.pc(0, 255, 255), PaletteRecord.pc(128, 0, 128), PaletteRecord.pc(128, 0, 0), PaletteRecord.pc(0, 128, 128), PaletteRecord.pc(0, 0, 255), PaletteRecord.pc(0, 204, 255), PaletteRecord.pc(204, 255, 255), PaletteRecord.pc(204, 255, 204), PaletteRecord.pc(255, 255, 153), PaletteRecord.pc(153, 204, 255), PaletteRecord.pc(255, 153, 204), PaletteRecord.pc(204, 153, 255), PaletteRecord.pc(255, 204, 153), PaletteRecord.pc(51, 102, 255), PaletteRecord.pc(51, 204, 204), PaletteRecord.pc(153, 204, 0), PaletteRecord.pc(255, 204, 0), PaletteRecord.pc(255, 153, 0), PaletteRecord.pc(255, 102, 0), PaletteRecord.pc(102, 102, 153), PaletteRecord.pc(150, 150, 150), PaletteRecord.pc(0, 51, 102), PaletteRecord.pc(51, 153, 102), PaletteRecord.pc(0, 51, 0), PaletteRecord.pc(51, 51, 0), PaletteRecord.pc(153, 51, 0), PaletteRecord.pc(153, 51, 102), PaletteRecord.pc(51, 51, 153), PaletteRecord.pc(51, 51, 51)};
    }

    private static PColor pc(int r, int g, int b) {
        return new PColor(r, g, b);
    }

    private static final class PColor {
        public static final short ENCODED_SIZE = 4;
        private final int _red;
        private final int _green;
        private final int _blue;

        public PColor(int red, int green, int blue) {
            this._red = red;
            this._green = green;
            this._blue = blue;
        }

        public PColor(PColor other) {
            this._red = other._red;
            this._green = other._green;
            this._blue = other._blue;
        }

        public PColor(RecordInputStream in) {
            this._red = in.readByte();
            this._green = in.readByte();
            this._blue = in.readByte();
            in.readByte();
        }

        public byte[] getTriplet() {
            return new byte[]{(byte)this._red, (byte)this._green, (byte)this._blue};
        }

        public void serialize(LittleEndianOutput out) {
            out.writeByte(this._red);
            out.writeByte(this._green);
            out.writeByte(this._blue);
            out.writeByte(0);
        }

        public String toString() {
            StringBuilder buffer = new StringBuilder();
            buffer.append("  red   = ").append(this._red & 0xFF).append('\n');
            buffer.append("  green = ").append(this._green & 0xFF).append('\n');
            buffer.append("  blue  = ").append(this._blue & 0xFF).append('\n');
            return buffer.toString();
        }
    }
}

