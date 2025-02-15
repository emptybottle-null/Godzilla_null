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

public abstract class JavaLongHolder
extends XmlObjectBase {
    private long _value;
    private static final BigInteger _max = BigInteger.valueOf(Long.MAX_VALUE);
    private static final BigInteger _min = BigInteger.valueOf(Long.MIN_VALUE);

    @Override
    public SchemaType schemaType() {
        return BuiltinSchemaTypeSystem.ST_LONG;
    }

    @Override
    protected String compute_text(NamespaceManager nsm) {
        return Long.toString(this._value);
    }

    @Override
    protected void set_text(String s) {
        try {
            this.set_long(XsTypeConverter.lexLong(s));
        } catch (Exception e) {
            throw new XmlValueOutOfRangeException("long", new Object[]{s});
        }
    }

    @Override
    protected void set_nil() {
        this._value = 0L;
    }

    @Override
    public BigDecimal getBigDecimalValue() {
        this.check_dated();
        return BigDecimal.valueOf(this._value);
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
    protected void set_BigDecimal(BigDecimal v) {
        this.set_BigInteger(v.toBigInteger());
    }

    @Override
    protected void set_BigInteger(BigInteger v) {
        if (v.compareTo(_max) > 0 || v.compareTo(_min) < 0) {
            throw new XmlValueOutOfRangeException();
        }
        this._value = v.longValue();
    }

    @Override
    protected void set_long(long l) {
        this._value = l;
    }

    @Override
    protected int compare_to(XmlObject l) {
        if (((SimpleValue)l).instanceType().getDecimalSize() > 64) {
            return -l.compareTo(this);
        }
        return this._value == ((XmlObjectBase)l).longValue() ? 0 : (this._value < ((XmlObjectBase)l).longValue() ? -1 : 1);
    }

    @Override
    protected boolean equal_to(XmlObject l) {
        if (((SimpleValue)l).instanceType().getDecimalSize() > 64) {
            return l.valueEquals(this);
        }
        return this._value == ((XmlObjectBase)l).longValue();
    }

    @Override
    protected int value_hash_code() {
        return (int)((this._value >> 32) * 19L + this._value);
    }
}

