/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.ddf;

import org.apache.poi.ddf.AbstractEscherOptRecord;
import org.apache.poi.ddf.EscherRecordTypes;

public class EscherTertiaryOptRecord
extends AbstractEscherOptRecord {
    public static final short RECORD_ID = EscherRecordTypes.USER_DEFINED.typeID;

    public EscherTertiaryOptRecord() {
    }

    public EscherTertiaryOptRecord(EscherTertiaryOptRecord other) {
        super(other);
    }

    @Override
    public String getRecordName() {
        return EscherRecordTypes.USER_DEFINED.recordName;
    }

    @Override
    public Enum getGenericRecordType() {
        return EscherRecordTypes.USER_DEFINED;
    }

    @Override
    public EscherTertiaryOptRecord copy() {
        return new EscherTertiaryOptRecord(this);
    }
}

