/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.hssf.record.chart;

import java.util.stream.Stream;
import org.apache.poi.hssf.record.RecordInputStream;
import org.apache.poi.hssf.record.StandardRecord;
import org.apache.poi.util.LittleEndianOutput;

public class ChartTitleFormatRecord
extends StandardRecord {
    public static final short sid = 4176;
    private final CTFormat[] _formats;

    public ChartTitleFormatRecord(ChartTitleFormatRecord other) {
        super(other);
        this._formats = (CTFormat[])Stream.of(other._formats).map(CTFormat::new).toArray(CTFormat[]::new);
    }

    public ChartTitleFormatRecord(RecordInputStream in) {
        int nRecs = in.readUShort();
        this._formats = new CTFormat[nRecs];
        for (int i = 0; i < nRecs; ++i) {
            this._formats[i] = new CTFormat(in);
        }
    }

    @Override
    public void serialize(LittleEndianOutput out) {
        out.writeShort(this._formats.length);
        for (int i = 0; i < this._formats.length; ++i) {
            this._formats[i].serialize(out);
        }
    }

    @Override
    protected int getDataSize() {
        return 2 + 4 * this._formats.length;
    }

    @Override
    public short getSid() {
        return 4176;
    }

    public int getFormatCount() {
        return this._formats.length;
    }

    public void modifyFormatRun(short oldPos, short newLen) {
        int shift = 0;
        for (int i = 0; i < this._formats.length; ++i) {
            CTFormat ctf = this._formats[i];
            if (shift != 0) {
                ctf.setOffset(ctf.getOffset() + shift);
                continue;
            }
            if (oldPos != ctf.getOffset() || i >= this._formats.length - 1) continue;
            CTFormat nextCTF = this._formats[i + 1];
            shift = newLen - (nextCTF.getOffset() - ctf.getOffset());
        }
    }

    @Override
    public String toString() {
        StringBuilder buffer = new StringBuilder();
        buffer.append("[CHARTTITLEFORMAT]\n");
        buffer.append("    .format_runs       = ").append(this._formats.length).append("\n");
        for (int i = 0; i < this._formats.length; ++i) {
            CTFormat ctf = this._formats[i];
            buffer.append("       .char_offset= ").append(ctf.getOffset());
            buffer.append(",.fontidx= ").append(ctf.getFontIndex());
            buffer.append("\n");
        }
        buffer.append("[/CHARTTITLEFORMAT]\n");
        return buffer.toString();
    }

    @Override
    public ChartTitleFormatRecord copy() {
        return new ChartTitleFormatRecord(this);
    }

    private static final class CTFormat {
        public static final int ENCODED_SIZE = 4;
        private int _offset;
        private int _fontIndex;

        public CTFormat(CTFormat other) {
            this._offset = other._offset;
            this._fontIndex = other._fontIndex;
        }

        public CTFormat(RecordInputStream in) {
            this._offset = in.readShort();
            this._fontIndex = in.readShort();
        }

        public int getOffset() {
            return this._offset;
        }

        public void setOffset(int newOff) {
            this._offset = newOff;
        }

        public int getFontIndex() {
            return this._fontIndex;
        }

        public void serialize(LittleEndianOutput out) {
            out.writeShort(this._offset);
            out.writeShort(this._fontIndex);
        }
    }
}

