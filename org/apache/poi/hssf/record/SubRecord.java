/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.hssf.record;

import java.io.ByteArrayOutputStream;
import org.apache.poi.common.Duplicatable;
import org.apache.poi.hssf.record.CommonObjectDataSubRecord;
import org.apache.poi.hssf.record.EmbeddedObjectRefSubRecord;
import org.apache.poi.hssf.record.EndSubRecord;
import org.apache.poi.hssf.record.FtCblsSubRecord;
import org.apache.poi.hssf.record.FtCfSubRecord;
import org.apache.poi.hssf.record.FtPioGrbitSubRecord;
import org.apache.poi.hssf.record.GroupMarkerSubRecord;
import org.apache.poi.hssf.record.LbsDataSubRecord;
import org.apache.poi.hssf.record.NoteStructureSubRecord;
import org.apache.poi.util.HexDump;
import org.apache.poi.util.IOUtils;
import org.apache.poi.util.LittleEndianInput;
import org.apache.poi.util.LittleEndianOutput;
import org.apache.poi.util.LittleEndianOutputStream;
import org.apache.poi.util.Removal;

public abstract class SubRecord
implements Duplicatable {
    private static final int MAX_RECORD_LENGTH = 1000000;

    protected SubRecord() {
    }

    protected SubRecord(SubRecord other) {
    }

    public static SubRecord createSubRecord(LittleEndianInput in, int cmoOt) {
        int sid = in.readUShort();
        int secondUShort = in.readUShort();
        switch (sid) {
            case 21: {
                return new CommonObjectDataSubRecord(in, secondUShort);
            }
            case 9: {
                return new EmbeddedObjectRefSubRecord(in, secondUShort);
            }
            case 6: {
                return new GroupMarkerSubRecord(in, secondUShort);
            }
            case 0: {
                return new EndSubRecord(in, secondUShort);
            }
            case 13: {
                return new NoteStructureSubRecord(in, secondUShort);
            }
            case 19: {
                return new LbsDataSubRecord(in, secondUShort, cmoOt);
            }
            case 12: {
                return new FtCblsSubRecord(in, secondUShort);
            }
            case 8: {
                return new FtPioGrbitSubRecord(in, secondUShort);
            }
            case 7: {
                return new FtCfSubRecord(in, secondUShort);
            }
        }
        return new UnknownSubRecord(in, sid, secondUShort);
    }

    protected abstract int getDataSize();

    public byte[] serialize() {
        int size = this.getDataSize() + 4;
        ByteArrayOutputStream baos = new ByteArrayOutputStream(size);
        this.serialize(new LittleEndianOutputStream(baos));
        if (baos.size() != size) {
            throw new RuntimeException("write size mismatch");
        }
        return baos.toByteArray();
    }

    public abstract void serialize(LittleEndianOutput var1);

    public boolean isTerminating() {
        return false;
    }

    @Deprecated
    @Removal(version="5.0.0")
    public abstract SubRecord clone();

    @Override
    public abstract SubRecord copy();

    private static final class UnknownSubRecord
    extends SubRecord {
        private final int _sid;
        private final byte[] _data;

        public UnknownSubRecord(LittleEndianInput in, int sid, int size) {
            this._sid = sid;
            byte[] buf = IOUtils.safelyAllocate(size, 1000000);
            in.readFully(buf);
            this._data = buf;
        }

        @Override
        protected int getDataSize() {
            return this._data.length;
        }

        @Override
        public void serialize(LittleEndianOutput out) {
            out.writeShort(this._sid);
            out.writeShort(this._data.length);
            out.write(this._data);
        }

        @Override
        @Deprecated
        @Removal(version="5.0.0")
        public UnknownSubRecord clone() {
            return this.copy();
        }

        @Override
        public UnknownSubRecord copy() {
            return this;
        }

        public String toString() {
            StringBuilder sb = new StringBuilder(64);
            sb.append(this.getClass().getName()).append(" [");
            sb.append("sid=").append(HexDump.shortToHex(this._sid));
            sb.append(" size=").append(this._data.length);
            sb.append(" : ").append(HexDump.toHex(this._data));
            sb.append("]\n");
            return sb.toString();
        }
    }
}

