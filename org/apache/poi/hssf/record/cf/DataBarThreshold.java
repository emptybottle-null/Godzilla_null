/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.hssf.record.cf;

import org.apache.poi.common.Duplicatable;
import org.apache.poi.hssf.record.cf.Threshold;
import org.apache.poi.util.LittleEndianInput;
import org.apache.poi.util.Removal;

public final class DataBarThreshold
extends Threshold
implements Duplicatable {
    public DataBarThreshold() {
    }

    public DataBarThreshold(DataBarThreshold other) {
        super(other);
    }

    public DataBarThreshold(LittleEndianInput in) {
        super(in);
    }

    @Deprecated
    @Removal(version="5.0.0")
    public DataBarThreshold clone() {
        return this.copy();
    }

    @Override
    public DataBarThreshold copy() {
        return new DataBarThreshold(this);
    }
}

