/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.ddf;

import java.util.function.Supplier;
import org.apache.poi.ddf.EscherBlipRecord;
import org.apache.poi.ddf.EscherContainerRecord;
import org.apache.poi.ddf.EscherRecord;
import org.apache.poi.ddf.EscherRecordFactory;
import org.apache.poi.ddf.EscherRecordTypes;
import org.apache.poi.ddf.EscherTextboxRecord;
import org.apache.poi.ddf.UnknownEscherRecord;
import org.apache.poi.util.BitField;
import org.apache.poi.util.BitFieldFactory;
import org.apache.poi.util.LittleEndian;
import org.apache.poi.util.Removal;

public class DefaultEscherRecordFactory
implements EscherRecordFactory {
    private static final BitField IS_CONTAINER = BitFieldFactory.getInstance(15);

    @Override
    public EscherRecord createRecord(byte[] data, int offset) {
        short options = LittleEndian.getShort(data, offset);
        short recordId = LittleEndian.getShort(data, offset + 2);
        EscherRecord escherRecord = this.getConstructor(options, recordId).get();
        escherRecord.setRecordId(recordId);
        escherRecord.setOptions(options);
        return escherRecord;
    }

    protected Supplier<? extends EscherRecord> getConstructor(short options, short recordId) {
        EscherRecordTypes recordTypes = EscherRecordTypes.forTypeID(recordId);
        if (recordTypes == EscherRecordTypes.UNKNOWN && IS_CONTAINER.isAllSet(options)) {
            return EscherContainerRecord::new;
        }
        if (recordTypes.constructor != null) {
            return recordTypes.constructor;
        }
        if (EscherBlipRecord.RECORD_ID_START <= recordId && recordId <= EscherBlipRecord.RECORD_ID_END) {
            return EscherBlipRecord::new;
        }
        return UnknownEscherRecord::new;
    }

    @Deprecated
    @Removal(version="5.0.0")
    public static boolean isContainer(short options, short recordId) {
        if (recordId >= EscherContainerRecord.DGG_CONTAINER && recordId <= EscherContainerRecord.SOLVER_CONTAINER) {
            return true;
        }
        if (recordId == EscherTextboxRecord.RECORD_ID) {
            return false;
        }
        return (options & 0xF) == 15;
    }
}

