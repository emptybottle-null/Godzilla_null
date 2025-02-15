/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.ddf;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;
import org.apache.poi.ddf.EscherProperty;
import org.apache.poi.ddf.EscherPropertyFactory;
import org.apache.poi.ddf.EscherPropertyTypes;
import org.apache.poi.ddf.EscherRecord;
import org.apache.poi.ddf.EscherRecordFactory;
import org.apache.poi.ddf.EscherSerializationListener;
import org.apache.poi.util.GenericRecordUtil;
import org.apache.poi.util.LittleEndian;
import org.apache.poi.util.Removal;

public abstract class AbstractEscherOptRecord
extends EscherRecord {
    private final List<EscherProperty> properties = new ArrayList<EscherProperty>();

    protected AbstractEscherOptRecord() {
    }

    protected AbstractEscherOptRecord(AbstractEscherOptRecord other) {
        super(other);
        this.properties.addAll(other.properties);
    }

    public void addEscherProperty(EscherProperty prop) {
        this.properties.add(prop);
    }

    @Override
    public int fillFields(byte[] data, int offset, EscherRecordFactory recordFactory) {
        int bytesRemaining = this.readHeader(data, offset);
        short propertiesCount = AbstractEscherOptRecord.readInstance(data, offset);
        int pos = offset + 8;
        EscherPropertyFactory f = new EscherPropertyFactory();
        this.properties.clear();
        this.properties.addAll(f.createProperties(data, pos, propertiesCount));
        return bytesRemaining + 8;
    }

    public List<EscherProperty> getEscherProperties() {
        return this.properties;
    }

    public EscherProperty getEscherProperty(int index) {
        return this.properties.get(index);
    }

    private int getPropertiesSize() {
        int totalSize = 0;
        for (EscherProperty property : this.properties) {
            totalSize += property.getPropertySize();
        }
        return totalSize;
    }

    @Override
    public int getRecordSize() {
        return 8 + this.getPropertiesSize();
    }

    public <T extends EscherProperty> T lookup(EscherPropertyTypes propType) {
        return this.lookup(propType.propNumber);
    }

    public <T extends EscherProperty> T lookup(int propId) {
        return (T)((EscherProperty)this.properties.stream().filter(p -> p.getPropertyNumber() == propId).findFirst().orElse(null));
    }

    @Override
    public int serialize(int offset, byte[] data, EscherSerializationListener listener) {
        listener.beforeRecordSerialize(offset, this.getRecordId(), this);
        LittleEndian.putShort(data, offset, this.getOptions());
        LittleEndian.putShort(data, offset + 2, this.getRecordId());
        LittleEndian.putInt(data, offset + 4, this.getPropertiesSize());
        int pos = offset + 8;
        for (EscherProperty property : this.properties) {
            pos += property.serializeSimplePart(data, pos);
        }
        for (EscherProperty property : this.properties) {
            pos += property.serializeComplexPart(data, pos);
        }
        listener.afterRecordSerialize(pos, this.getRecordId(), pos - offset, this);
        return pos - offset;
    }

    public void sortProperties() {
        this.properties.sort(Comparator.comparingInt(EscherProperty::getPropertyNumber));
    }

    public void setEscherProperty(EscherProperty value) {
        this.properties.removeIf(prop -> prop.getId() == value.getId());
        this.properties.add(value);
        this.sortProperties();
    }

    @Deprecated
    @Removal(version="5.0.0")
    public void removeEscherProperty(int num) {
        this.properties.removeIf(prop -> prop.getPropertyNumber() == num);
    }

    public void removeEscherProperty(EscherPropertyTypes type) {
        this.properties.removeIf(prop -> prop.getPropertyNumber() == type.propNumber);
    }

    @Override
    public Map<String, Supplier<?>> getGenericProperties() {
        return GenericRecordUtil.getGenericProperties("base", () -> super.getGenericProperties(), "isContainer", this::isContainerRecord, "properties", this::getEscherProperties);
    }
}

