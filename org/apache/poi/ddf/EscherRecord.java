/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.ddf;

import java.io.PrintWriter;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;
import org.apache.poi.common.Duplicatable;
import org.apache.poi.common.usermodel.GenericRecord;
import org.apache.poi.ddf.EscherRecordFactory;
import org.apache.poi.ddf.EscherSerializationListener;
import org.apache.poi.ddf.NullEscherSerializationListener;
import org.apache.poi.util.BitField;
import org.apache.poi.util.BitFieldFactory;
import org.apache.poi.util.GenericRecordJsonWriter;
import org.apache.poi.util.GenericRecordUtil;
import org.apache.poi.util.GenericRecordXmlWriter;
import org.apache.poi.util.Internal;
import org.apache.poi.util.LittleEndian;
import org.apache.poi.util.Removal;

public abstract class EscherRecord
implements Duplicatable,
GenericRecord {
    private static final BitField fInstance = BitFieldFactory.getInstance(65520);
    private static final BitField fVersion = BitFieldFactory.getInstance(15);
    private short _options;
    private short _recordId;

    public EscherRecord() {
    }

    protected EscherRecord(EscherRecord other) {
        this._options = other._options;
        this._recordId = other._recordId;
    }

    protected int fillFields(byte[] data, EscherRecordFactory f) {
        return this.fillFields(data, 0, f);
    }

    public abstract int fillFields(byte[] var1, int var2, EscherRecordFactory var3);

    protected int readHeader(byte[] data, int offset) {
        this._options = LittleEndian.getShort(data, offset);
        this._recordId = LittleEndian.getShort(data, offset + 2);
        return LittleEndian.getInt(data, offset + 4);
    }

    protected static short readInstance(byte[] data, int offset) {
        short options = LittleEndian.getShort(data, offset);
        return fInstance.getShortValue(options);
    }

    public boolean isContainerRecord() {
        return this.getVersion() == 15;
    }

    @Internal
    public short getOptions() {
        return this._options;
    }

    @Internal
    public void setOptions(short options) {
        this.setVersion(fVersion.getShortValue(options));
        this.setInstance(fInstance.getShortValue(options));
        this._options = options;
    }

    public byte[] serialize() {
        byte[] retval = new byte[this.getRecordSize()];
        this.serialize(0, retval);
        return retval;
    }

    public int serialize(int offset, byte[] data) {
        return this.serialize(offset, data, new NullEscherSerializationListener());
    }

    public abstract int serialize(int var1, byte[] var2, EscherSerializationListener var3);

    public abstract int getRecordSize();

    public short getRecordId() {
        return this._recordId;
    }

    public void setRecordId(short recordId) {
        this._recordId = recordId;
    }

    public List<EscherRecord> getChildRecords() {
        return Collections.emptyList();
    }

    public void setChildRecords(List<EscherRecord> childRecords) {
        throw new UnsupportedOperationException("This record does not support child records.");
    }

    @Deprecated
    @Removal(version="5.0.0")
    public final EscherRecord clone() {
        return this.copy();
    }

    public EscherRecord getChild(int index) {
        return this.getChildRecords().get(index);
    }

    public void display(PrintWriter w, int indent) {
        for (int i = 0; i < indent * 4; ++i) {
            w.print(' ');
        }
        w.println(this.getRecordName());
    }

    public abstract String getRecordName();

    public short getInstance() {
        return fInstance.getShortValue(this._options);
    }

    public void setInstance(short value) {
        this._options = fInstance.setShortValue(this._options, value);
    }

    public short getVersion() {
        return fVersion.getShortValue(this._options);
    }

    public void setVersion(short value) {
        this._options = fVersion.setShortValue(this._options, value);
    }

    public String toXml() {
        return this.toXml("");
    }

    public final String toXml(String tab) {
        return GenericRecordXmlWriter.marshal(this);
    }

    public final String toString() {
        return GenericRecordJsonWriter.marshal(this);
    }

    @Override
    public List<? extends GenericRecord> getGenericChildren() {
        return this.getChildRecords();
    }

    @Override
    public Map<String, Supplier<?>> getGenericProperties() {
        return GenericRecordUtil.getGenericProperties("recordId", this::getRecordId, "version", this::getVersion, "instance", this::getInstance, "options", this::getOptions, "recordSize", this::getRecordSize);
    }

    @Override
    public abstract EscherRecord copy();
}

