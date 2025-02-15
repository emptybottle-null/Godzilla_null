/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.xmlbeans.impl.values;

import java.math.BigDecimal;
import java.math.BigInteger;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.common.ValidationContext;
import org.apache.xmlbeans.impl.schema.BuiltinSchemaTypeSystem;
import org.apache.xmlbeans.impl.values.NamespaceManager;
import org.apache.xmlbeans.impl.values.XmlObjectBase;

public abstract class JavaIntegerHolder
extends XmlObjectBase {
    private BigInteger _value;
    private static BigInteger _maxlong = BigInteger.valueOf(Long.MAX_VALUE);
    private static BigInteger _minlong = BigInteger.valueOf(Long.MIN_VALUE);

    @Override
    public SchemaType schemaType() {
        return BuiltinSchemaTypeSystem.ST_INTEGER;
    }

    @Override
    protected String compute_text(NamespaceManager nsm) {
        return this._value.toString();
    }

    @Override
    protected void set_text(String s) {
        this.set_BigInteger(JavaIntegerHolder.lex(s, _voorVc));
    }

    public static BigInteger lex(String s, ValidationContext vc) {
        if (s.length() > 0 && s.charAt(0) == '+') {
            s = s.substring(1);
        }
        try {
            return new BigInteger(s);
        } catch (Exception e) {
            vc.invalid("integer", new Object[]{s});
            return null;
        }
    }

    @Override
    protected void set_nil() {
        this._value = null;
    }

    @Override
    public BigDecimal getBigDecimalValue() {
        this.check_dated();
        return this._value == null ? null : new BigDecimal(this._value);
    }

    @Override
    public BigInteger getBigIntegerValue() {
        this.check_dated();
        return this._value;
    }

    @Override
    protected void set_BigDecimal(BigDecimal v) {
        this._value = v.toBigInteger();
    }

    @Override
    protected void set_BigInteger(BigInteger v) {
        this._value = v;
    }

    @Override
    protected int compare_to(XmlObject i) {
        if (((SimpleValue)i).instanceType().getDecimalSize() > 1000000) {
            return -i.compareTo(this);
        }
        return this._value.compareTo(((XmlObjectBase)i).bigIntegerValue());
    }

    @Override
    protected boolean equal_to(XmlObject i) {
        if (((SimpleValue)i).instanceType().getDecimalSize() > 1000000) {
            return i.valueEquals(this);
        }
        return this._value.equals(((XmlObjectBase)i).bigIntegerValue());
    }

    @Override
    protected int value_hash_code() {
        if (this._value.compareTo(_maxlong) > 0 || this._value.compareTo(_minlong) < 0) {
            return this._value.hashCode();
        }
        long longval = this._value.longValue();
        return (int)((longval >> 32) * 19L + longval);
    }
}

