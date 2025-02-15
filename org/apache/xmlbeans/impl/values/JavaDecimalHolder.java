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

public class JavaDecimalHolder
extends XmlObjectBase {
    private BigDecimal _value;
    private static BigInteger _maxlong = BigInteger.valueOf(Long.MAX_VALUE);
    private static BigInteger _minlong = BigInteger.valueOf(Long.MIN_VALUE);

    @Override
    public SchemaType schemaType() {
        return BuiltinSchemaTypeSystem.ST_DECIMAL;
    }

    @Override
    protected String compute_text(NamespaceManager nsm) {
        return XsTypeConverter.printDecimal(this._value);
    }

    @Override
    protected void set_text(String s) {
        if (this._validateOnSet()) {
            JavaDecimalHolder.validateLexical(s, _voorVc);
        }
        try {
            this.set_BigDecimal(new BigDecimal(s));
        } catch (NumberFormatException e) {
            _voorVc.invalid("decimal", new Object[]{s});
        }
    }

    @Override
    protected void set_nil() {
        this._value = null;
    }

    public static void validateLexical(String v, ValidationContext context) {
        char ch;
        int i = 0;
        int l = v.length();
        if (i < l && ((ch = v.charAt(i)) == '+' || ch == '-')) {
            ++i;
        }
        boolean sawDot = false;
        boolean sawDigit = false;
        while (i < l) {
            char ch2 = v.charAt(i);
            if (ch2 == '.') {
                if (sawDot) {
                    context.invalid("decimal", new Object[]{"saw '.' more than once: " + v});
                    return;
                }
                sawDot = true;
            } else if (ch2 >= '0' && ch2 <= '9') {
                sawDigit = true;
            } else {
                context.invalid("decimal", new Object[]{"unexpected char '" + ch2 + "'"});
                return;
            }
            ++i;
        }
        if (!sawDigit) {
            context.invalid("decimal", new Object[]{"expected at least one digit"});
            return;
        }
    }

    @Override
    public BigDecimal getBigDecimalValue() {
        this.check_dated();
        return this._value;
    }

    @Override
    protected void set_BigDecimal(BigDecimal v) {
        this._value = v;
    }

    @Override
    protected int compare_to(XmlObject decimal) {
        return this._value.compareTo(((XmlObjectBase)decimal).bigDecimalValue());
    }

    @Override
    protected boolean equal_to(XmlObject decimal) {
        return this._value.compareTo(((XmlObjectBase)decimal).bigDecimalValue()) == 0;
    }

    @Override
    protected int value_hash_code() {
        if (this._value.scale() > 0 && this._value.setScale(0, 1).compareTo(this._value) != 0) {
            return this.decimalHashCode();
        }
        BigInteger intval = this._value.toBigInteger();
        if (intval.compareTo(_maxlong) > 0 || intval.compareTo(_minlong) < 0) {
            return intval.hashCode();
        }
        long longval = intval.longValue();
        return (int)((longval >> 32) * 19L + longval);
    }

    protected int decimalHashCode() {
        int i;
        assert (this._value.scale() > 0);
        String strValue = this._value.toString();
        for (i = strValue.length() - 1; i >= 0 && strValue.charAt(i) == '0'; --i) {
        }
        assert (strValue.indexOf(46) < i);
        return strValue.substring(0, i + 1).hashCode();
    }
}

