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

public abstract class JavaFloatHolder
extends XmlObjectBase {
    private float _value;

    @Override
    public SchemaType schemaType() {
        return BuiltinSchemaTypeSystem.ST_FLOAT;
    }

    @Override
    protected String compute_text(NamespaceManager nsm) {
        return JavaFloatHolder.serialize(this._value);
    }

    public static String serialize(float f) {
        if (f == Float.POSITIVE_INFINITY) {
            return "INF";
        }
        if (f == Float.NEGATIVE_INFINITY) {
            return "-INF";
        }
        if (f == Float.NaN) {
            return "NaN";
        }
        return Float.toString(f);
    }

    @Override
    protected void set_text(String s) {
        this.set_float(JavaFloatHolder.validateLexical(s, _voorVc));
    }

    public static float validateLexical(String v, ValidationContext context) {
        try {
            return XsTypeConverter.lexFloat(v);
        } catch (NumberFormatException e) {
            context.invalid("float", new Object[]{v});
            return Float.NaN;
        }
    }

    @Override
    protected void set_nil() {
        this._value = 0.0f;
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
        return this._value;
    }

    @Override
    protected void set_double(double v) {
        this.set_float((float)v);
    }

    @Override
    protected void set_float(float v) {
        this._value = v;
    }

    @Override
    protected void set_long(long v) {
        this.set_float(v);
    }

    @Override
    protected void set_BigDecimal(BigDecimal v) {
        this.set_float(v.floatValue());
    }

    @Override
    protected void set_BigInteger(BigInteger v) {
        this.set_float(v.floatValue());
    }

    @Override
    protected int compare_to(XmlObject f) {
        return JavaFloatHolder.compare(this._value, ((XmlObjectBase)f).floatValue());
    }

    static int compare(float thisValue, float thatValue) {
        int thatBits;
        if (thisValue < thatValue) {
            return -1;
        }
        if (thisValue > thatValue) {
            return 1;
        }
        int thisBits = Float.floatToIntBits(thisValue);
        return thisBits == (thatBits = Float.floatToIntBits(thatValue)) ? 0 : (thisBits < thatBits ? -1 : 1);
    }

    @Override
    protected boolean equal_to(XmlObject f) {
        return JavaFloatHolder.compare(this._value, ((XmlObjectBase)f).floatValue()) == 0;
    }

    @Override
    protected int value_hash_code() {
        return Float.floatToIntBits(this._value);
    }
}

