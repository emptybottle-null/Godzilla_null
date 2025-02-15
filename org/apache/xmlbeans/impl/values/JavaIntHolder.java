/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.xmlbeans.impl.values;

import java.math.BigDecimal;
import java.math.BigInteger;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.BuiltinSchemaTypeSystem;
import org.apache.xmlbeans.impl.util.XsTypeConverter;
import org.apache.xmlbeans.impl.values.NamespaceManager;
import org.apache.xmlbeans.impl.values.XmlObjectBase;
import org.apache.xmlbeans.impl.values.XmlValueOutOfRangeException;

public abstract class JavaIntHolder
extends XmlObjectBase {
    private int _value;
    static final BigInteger _max = BigInteger.valueOf(Integer.MAX_VALUE);
    static final BigInteger _min = BigInteger.valueOf(Integer.MIN_VALUE);

    @Override
    public SchemaType schemaType() {
        return BuiltinSchemaTypeSystem.ST_INT;
    }

    @Override
    public String compute_text(NamespaceManager nsm) {
        return Long.toString(this._value);
    }

    @Override
    protected void set_text(String s) {
        try {
            this.set_int(XsTypeConverter.lexInt(s));
        } catch (Exception e) {
            throw new XmlValueOutOfRangeException("int", new Object[]{s});
        }
    }

    @Override
    protected void set_nil() {
        this._value = 0;
    }

    @Override
    public BigDecimal getBigDecimalValue() {
        this.check_dated();
        return new BigDecimal((double)this._value);
    }

    @Override
    public BigInteger getBigIntegerValue() {
        this.check_dated();
        return BigInteger.valueOf(this._value);
    }

    @Override
    public long getLongValue() {
        this.check_dated();
        return this._value;
    }

    @Override
    public int getIntValue() {
        this.check_dated();
        return this._value;
    }

    @Override
    protected void set_BigDecimal(BigDecimal v) {
        this.set_BigInteger(v.toBigInteger());
    }

    @Override
    protected void set_BigInteger(BigInteger v) {
        if (v.compareTo(_max) > 0 || v.compareTo(_min) < 0) {
            throw new XmlValueOutOfRangeException();
        }
        this.set_int(v.intValue());
    }

    @Override
    protected void set_long(long l) {
        if (l > Integer.MAX_VALUE || l < Integer.MIN_VALUE) {
            throw new XmlValueOutOfRangeException();
        }
        this.set_int((int)l);
    }

    @Override
    protected void set_int(int i) {
        this._value = i;
    }

    @Override
    protected int compare_to(XmlObject i) {
        if (((SimpleValue)i).instanceType().getDecimalSize() > 32) {
            return -i.compareTo(this);
        }
        return this._value == ((XmlObjectBase)i).intValue() ? 0 : (this._value < ((XmlObjectBase)i).intValue() ? -1 : 1);
    }

    @Override
    protected boolean equal_to(XmlObject i) {
        if (((SimpleValue)i).instanceType().getDecimalSize() > 32) {
            return i.valueEquals(this);
        }
        return this._value == ((XmlObjectBase)i).intValue();
    }

    @Override
    protected int value_hash_code() {
        return this._value;
    }
}

