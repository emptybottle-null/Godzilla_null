/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.ddf;

import org.apache.poi.ddf.AbstractEscherOptRecord;
import org.apache.poi.ddf.EscherRecordTypes;
import org.apache.poi.util.Internal;

public class EscherOptRecord
extends AbstractEscherOptRecord {
    public static final short RECORD_ID = EscherRecordTypes.OPT.typeID;
    public static final String RECORD_DESCRIPTION = EscherRecordTypes.OPT.description;

    public EscherOptRecord() {
    }

    public EscherOptRecord(EscherOptRecord other) {
        super(other);
    }

    @Override
    public short getInstance() {
        this.setInstance((short)this.getEscherProperties().size());
        return super.getInstance();
    }

    @Override
    @Internal
    public short getOptions() {
        this.getInstance();
        this.getVersion();
        return super.getOptions();
    }

    @Override
    public String getRecordName() {
        return EscherRecordTypes.OPT.recordName;
    }

    @Override
    public short getVersion() {
        this.setVersion((short)3);
        return super.getVersion();
    }

    @Override
    public void setVersion(short value) {
        if (value != 3) {
            throw new IllegalArgumentException(RECORD_DESCRIPTION + " can have only '0x3' version");
        }
        super.setVersion(value);
    }

    @Override
    public Enum getGenericRecordType() {
        return EscherRecordTypes.OPT;
    }

    @Override
    public EscherOptRecord copy() {
        return new EscherOptRecord(this);
    }
}

