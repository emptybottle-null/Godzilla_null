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
import org.apache.xmlbeans.impl.values.JavaIntHolder;
import org.apache.xmlbeans.impl.values.XmlObjectBase;
import org.apache.xmlbeans.impl.values.XmlValueOutOfRangeException;

public abstract class JavaIntHolderEx
extends JavaIntHolder {
    private SchemaType _schemaType;

    public JavaIntHolderEx(SchemaType type, boolean complex) {
        this._schemaType = type;
        this.initComplexType(complex, false);
    }

    @Override
    public SchemaType schemaType() {
        return this._schemaType;
    }

    @Override
    protected void set_text(String s) {
        int v;
        try {
            v = XsTypeConverter.lexInt(s);
        } catch (Exception e) {
            throw new XmlValueOutOfRangeException();
        }
        if (this._validateOnSet()) {
            JavaIntHolderEx.validateValue(v, this._schemaType, _voorVc);
            JavaIntHolderEx.validateLexical(s, this._schemaType, _voorVc);
        }
        super.set_int(v);
    }

    @Override
    protected void set_int(int v) {
        if (this._validateOnSet()) {
            JavaIntHolderEx.validateValue(v, this._schemaType, _voorVc);
        }
        super.set_int(v);
    }

    public static void validateLexical(String v, SchemaType sType, ValidationContext context) {
        JavaDecimalHolder.validateLexical(v, context);
        if (sType.hasPatternFacet() && !sType.matchPatternFacet(v)) {
            context.invalid("cvc-datatype-valid.1.1", new Object[]{"int", v, QNameHelper.readable(sType)});
        }
    }

    private static void validateValue(int v, SchemaType sType, ValidationContext context) {
        int m;
        int m2;
        int m3;
        XmlAnySimpleType mine;
        int m4;
        XmlAnySimpleType td = sType.getFacet(7);
        if (td != null) {
            String temp = Integer.toString(v);
            int len = temp.length();
            if (len > 0 && temp.charAt(0) == '-') {
                --len;
            }
            if (len > (m4 = JavaIntHolderEx.getIntValue(td))) {
                context.invalid("cvc-totalDigits-valid", new Object[]{new Integer(len), temp, new Integer(JavaIntHolderEx.getIntValue(td)), QNameHelper.readable(sType)});
                return;
            }
        }
        if ((mine = sType.getFacet(3)) != null && v <= (m3 = JavaIntHolderEx.getIntValue(mine))) {
            context.invalid("cvc-minExclusive-valid", new Object[]{"int", new Integer(v), new Integer(m3), QNameHelper.readable(sType)});
            return;
        }
        XmlAnySimpleType mini = sType.getFacet(4);
        if (mini != null && v < (m4 = JavaIntHolderEx.getIntValue(mini))) {
            context.invalid("cvc-minInclusive-valid", new Object[]{"int", new Integer(v), new Integer(m4), QNameHelper.readable(sType)});
            return;
        }
        XmlAnySimpleType maxi = sType.getFacet(5);
        if (maxi != null && v > (m2 = JavaIntHolderEx.getIntValue(maxi))) {
            context.invalid("cvc-maxExclusive-valid", new Object[]{"int", new Integer(v), new Integer(m2), QNameHelper.readable(sType)});
            return;
        }
        XmlAnySimpleType maxe = sType.getFacet(6);
        if (maxe != null && v >= (m = JavaIntHolderEx.getIntValue(maxe))) {
            context.invalid("cvc-maxExclusive-valid", new Object[]{"int", new Integer(v), new Integer(m), QNameHelper.readable(sType)});
            return;
        }
        XmlAnySimpleType[] vals = sType.getEnumerationValues();
        if (vals != null) {
            for (int i = 0; i < vals.length; ++i) {
                if (v != JavaIntHolderEx.getIntValue(vals[i])) continue;
                return;
            }
            context.invalid("cvc-enumeration-valid", new Object[]{"int", new Integer(v), QNameHelper.readable(sType)});
        }
    }

    private static int getIntValue(XmlObject o) {
        SchemaType s = o.schemaType();
        switch (s.getDecimalSize()) {
            case 1000001: {
                return ((XmlObjectBase)o).getBigDecimalValue().intValue();
            }
            case 1000000: {
                return ((XmlObjectBase)o).getBigIntegerValue().intValue();
            }
            case 64: {
                return (int)((XmlObjectBase)o).getLongValue();
            }
        }
        return ((XmlObjectBase)o).getIntValue();
    }

    @Override
    protected void validate_simpleval(String lexical, ValidationContext ctx) {
        JavaIntHolderEx.validateLexical(lexical, this.schemaType(), ctx);
        JavaIntHolderEx.validateValue(this.getIntValue(), this.schemaType(), ctx);
    }
}

