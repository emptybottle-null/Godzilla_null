/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.xmlbeans.impl.values;

import java.math.BigDecimal;
import java.math.BigInteger;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.common.ValidationContext;
import org.apache.xmlbeans.impl.schema.BuiltinSchemaTypeSystem;
import org.apache.xmlbeans.impl.util.XsTypeConverter;
import org.apache.xmlbeans.impl.values.NamespaceManager;
import org.apache.xmlbeans.impl.values.XmlObjectBase;

public abstract class JavaDoubleHolder
extends XmlObjectBase {
    double _value;

    @Override
    public SchemaType schemaType() {
        return BuiltinSchemaTypeSystem.ST_DOUBLE;
    }

    @Override
    protected String compute_text(NamespaceManager nsm) {
        return JavaDoubleHolder.serialize(this._value);
    }

    public static String serialize(double d) {
        if (d == Double.POSITIVE_INFINITY) {
            return "INF";
        }
        if (d == Double.NEGATIVE_INFINITY) {
            return "-INF";
        }
        if (d == Double.NaN) {
            return "NaN";
        }
        return Double.toString(d);
    }

    @Override
    protected void set_text(String s) {
        this.set_double(JavaDoubleHolder.validateLexical(s, _voorVc));
    }

    public static double validateLexical(String v, ValidationContext context) {
        try {
            return XsTypeConverter.lexDouble(v);
        } catch (NumberFormatException e) {
            context.invalid("double", new Object[]{v});
            return Double.NaN;
        }
    }

    @Override
    protected void set_nil() {
        this._value = 0.0;
    }

    @Override
    public BigDecimal getBigDecimalValue() {
        this.check_dated();
        return new BigDecimal(this._value);
    }

    @Override
    public double getDoubleValue() {
        this.check_dated();
        return this._value;
    }

    @Override
    public float getFloatValue() {
        this.check_dated();
        return (float)this._value;
    }

    @Override
    protected void set_double(double v) {
        this._value = v;
    }

    @Override
    protected void set_float(float v) {
        this.set_double(v);
    }

    @Override
    protected void set_long(long v) {
        this.set_double(v);
    }

    @Override
    protected void set_BigDecimal(BigDecimal v) {
        this.set_double(v.doubleValue());
    }

    @Override
    protected void set_BigInteger(BigInteger v) {
        this.set_double(v.doubleValue());
    }

    @Override
    protected int compare_to(XmlObject d) {
        return JavaDoubleHolder.compare(this._value, ((XmlObjectBase)d).doubleValue());
    }

    static int compare(double thisValue, double thatValue) {
        long thatBits;
        if (thisValue < thatValue) {
            return -1;
        }
        if (thisValue > thatValue) {
            return 1;
        }
        long thisBits = Double.doubleToLongBits(thisValue);
        return thisBits == (thatBits = Double.doubleToLongBits(thatValue)) ? 0 : (thisBits < thatBits ? -1 : 1);
    }

    @Override
    protected boolean equal_to(XmlObject d) {
        return JavaDoubleHolder.compare(this._value, ((XmlObjectBase)d).doubleValue()) == 0;
    }

    @Override
    protected int value_hash_code() {
        long v = Double.doubleToLongBits(this._value);
        return (int)((v >> 32) * 19L + v);
    }
}

