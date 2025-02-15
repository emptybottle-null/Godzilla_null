/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.ddf;

import java.util.List;
import java.util.Map;
import java.util.function.Supplier;
import org.apache.poi.common.usermodel.GenericRecord;
import org.apache.poi.ddf.EscherPropertyTypes;
import org.apache.poi.util.GenericRecordJsonWriter;
import org.apache.poi.util.GenericRecordUtil;
import org.apache.poi.util.GenericRecordXmlWriter;

public abstract class EscherProperty
implements GenericRecord {
    private final short id;
    static final int IS_BLIP = 16384;
    static final int IS_COMPLEX = 32768;
    private static final int[] FLAG_MASK = new int[]{16384, 32768};
    private static final String[] FLAG_NAMES = new String[]{"IS_BLIP", "IS_COMPLEX"};

    protected EscherProperty(short id) {
        this.id = id;
    }

    protected EscherProperty(short propertyNumber, boolean isComplex, boolean isBlipId) {
        this((short)(propertyNumber | (isComplex ? 32768 : 0) | (isBlipId ? 16384 : 0)));
    }

    protected EscherProperty(EscherPropertyTypes type, boolean isComplex, boolean isBlipId) {
        this((short)(type.propNumber | (isComplex ? 32768 : 0) | (isBlipId ? 16384 : 0)));
    }

    public short getId() {
        return this.id;
    }

    public short getPropertyNumber() {
        return (short)(this.id & 0x3FFF);
    }

    public boolean isComplex() {
        return (this.id & 0x8000) != 0;
    }

    public boolean isBlipId() {
        return (this.id & 0x4000) != 0;
    }

    public String getName() {
        return EscherPropertyTypes.forPropertyID((int)this.getPropertyNumber()).propName;
    }

    public int getPropertySize() {
        return 6;
    }

    public abstract int serializeSimplePart(byte[] var1, int var2);

    public abstract int serializeComplexPart(byte[] var1, int var2);

    public final String toString() {
        return GenericRecordJsonWriter.marshal(this);
    }

    public final String toXml(String tab) {
        return GenericRecordXmlWriter.marshal(this);
    }

    @Override
    public Map<String, Supplier<?>> getGenericProperties() {
        return GenericRecordUtil.getGenericProperties("id", this::getId, "name", this::getName, "propertyNumber", this::getPropertyNumber, "propertySize", this::getPropertySize, "flags", GenericRecordUtil.getBitsAsString(this::getId, FLAG_MASK, FLAG_NAMES));
    }

    @Override
    public List<? extends GenericRecord> getGenericChildren() {
        return null;
    }

    @Override
    public EscherPropertyTypes getGenericRecordType() {
        return EscherPropertyTypes.forPropertyID(this.id);
    }
}

