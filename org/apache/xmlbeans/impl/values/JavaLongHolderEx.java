/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.xmlbeans.impl.values;

import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlAnySimpleType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.common.QNameHelper;
import org.apache.xmlbeans.impl.common.ValidationContext;
import org.apache.xmlbeans.impl.util.XsTypeConverter;
import org.apache.xmlbeans.impl.values.JavaDecimalHolder;
import org.apache.xmlbeans.impl.values.JavaLongHolder;
import org.apache.xmlbeans.impl.values.XmlObjectBase;
import org.apache.xmlbeans.impl.values.XmlValueOutOfRangeException;

public abstract class JavaLongHolderEx
extends JavaLongHolder {
    private SchemaType _schemaType;

    public JavaLongHolderEx(SchemaType type, boolean complex) {
        this._schemaType = type;
        this.initComplexType(complex, false);
    }

    @Override
    public SchemaType schemaType() {
        return this._schemaType;
    }

    @Override
    protected void set_text(String s) {
        long v;
        try {
            v = XsTypeConverter.lexLong(s);
        } catch (Exception e) {
            throw new XmlValueOutOfRangeException();
        }
        if (this._validateOnSet()) {
            JavaLongHolderEx.validateValue(v, this._schemaType, _voorVc);
            JavaLongHolderEx.validateLexical(s, this._schemaType, _voorVc);
        }
        super.set_long(v);
    }

    @Override
    protected void set_long(long v) {
        if (this._validateOnSet()) {
            JavaLongHolderEx.validateValue(v, this._schemaType, _voorVc);
        }
        super.set_long(v);
    }

    public static void validateLexical(String v, SchemaType sType, ValidationContext context) {
        JavaDecimalHolder.validateLexical(v, context);
        if (sType.hasPatternFacet() && !sType.matchPatternFacet(v)) {
            context.invalid("cvc-datatype-valid.1.1", new Object[]{"long", v, QNameHelper.readable(sType)});
        }
    }

    private static void validateValue(long v, SchemaType sType, ValidationContext context) {
        long m;
        long m2;
        long m3;
        long m4;
        XmlAnySimpleType mine;
        XmlAnySimpleType td = sType.getFacet(7);
        if (td != null) {
            long m5 = JavaLongHolderEx.getLongValue(td);
            String temp = Long.toString(v);
            int len = temp.length();
            if (len > 0 && temp.charAt(0) == '-') {
                --len;
            }
            if ((long)len > m5) {
                context.invalid("cvc-totalDigits-valid", new Object[]{new Integer(len), temp, new Long(m5), QNameHelper.readable(sType)});
                return;
            }
        }
        if ((mine = sType.getFacet(3)) != null && v <= (m4 = JavaLongHolderEx.getLongValue(mine))) {
            context.invalid("cvc-minExclusive-valid", new Object[]{"long", new Long(v), new Long(m4), QNameHelper.readable(sType)});
            return;
        }
        XmlAnySimpleType mini = sType.getFacet(4);
        if (mini != null && v < (m3 = JavaLongHolderEx.getLongValue(mini))) {
            context.invalid("cvc-minInclusive-valid", new Object[]{"long", new Long(v), new Long(m3), QNameHelper.readable(sType)});
            return;
        }
        XmlAnySimpleType maxi = sType.getFacet(5);
        if (maxi != null && v > (m2 = JavaLongHolderEx.getLongValue(maxi))) {
            context.invalid("cvc-maxInclusive-valid", new Object[]{"long", new Long(v), new Long(m2), QNameHelper.readable(sType)});
            return;
        }
        XmlAnySimpleType maxe = sType.getFacet(6);
        if (maxe != null && v >= (m = JavaLongHolderEx.getLongValue(maxe))) {
            context.invalid("cvc-maxExclusive-valid", new Object[]{"long", new Long(v), new Long(m), QNameHelper.readable(sType)});
            return;
        }
        XmlAnySimpleType[] vals = sType.getEnumerationValues();
        if (vals != null) {
            for (int i = 0; i < vals.length; ++i) {
                if (v != JavaLongHolderEx.getLongValue(vals[i])) continue;
                return;
            }
            context.invalid("cvc-enumeration-valid", new Object[]{"long", new Long(v), QNameHelper.readable(sType)});
        }
    }

    private static long getLongValue(XmlObject o) {
        SchemaType s = o.schemaType();
        switch (s.getDecimalSize()) {
            case 1000001: {
                return ((XmlObjectBase)o).getBigDecimalValue().longValue();
            }
            case 1000000: {
                return ((XmlObjectBase)o).getBigIntegerValue().longValue();
            }
            case 64: {
                return ((XmlObjectBase)o).getLongValue();
            }
        }
        throw new IllegalStateException("Bad facet type: " + s);
    }

    @Override
    protected void validate_simpleval(String lexical, ValidationContext ctx) {
        JavaLongHolderEx.validateLexical(lexical, this.schemaType(), ctx);
        JavaLongHolderEx.validateValue(this.getLongValue(), this.schemaType(), ctx);
    }
}

