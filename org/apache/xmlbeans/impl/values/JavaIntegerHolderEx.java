/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.xmlbeans.impl.values;

import java.math.BigInteger;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlAnySimpleType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlPositiveInteger;
import org.apache.xmlbeans.impl.common.QNameHelper;
import org.apache.xmlbeans.impl.common.ValidationContext;
import org.apache.xmlbeans.impl.values.JavaDecimalHolder;
import org.apache.xmlbeans.impl.values.JavaIntegerHolder;
import org.apache.xmlbeans.impl.values.XmlObjectBase;

public class JavaIntegerHolderEx
extends JavaIntegerHolder {
    private SchemaType _schemaType;

    public JavaIntegerHolderEx(SchemaType type, boolean complex) {
        this._schemaType = type;
        this.initComplexType(complex, false);
    }

    @Override
    public SchemaType schemaType() {
        return this._schemaType;
    }

    @Override
    protected void set_text(String s) {
        BigInteger v = JavaIntegerHolderEx.lex(s, _voorVc);
        if (this._validateOnSet()) {
            JavaIntegerHolderEx.validateValue(v, this._schemaType, _voorVc);
        }
        if (this._validateOnSet()) {
            JavaIntegerHolderEx.validateLexical(s, this._schemaType, _voorVc);
        }
        super.set_BigInteger(v);
    }

    @Override
    protected void set_BigInteger(BigInteger v) {
        if (this._validateOnSet()) {
            JavaIntegerHolderEx.validateValue(v, this._schemaType, _voorVc);
        }
        super.set_BigInteger(v);
    }

    public static void validateLexical(String v, SchemaType sType, ValidationContext context) {
        JavaDecimalHolder.validateLexical(v, context);
        if (v.lastIndexOf(46) >= 0) {
            context.invalid("integer", new Object[]{v});
        }
        if (sType.hasPatternFacet() && !sType.matchPatternFacet(v)) {
            context.invalid("cvc-datatype-valid.1.1", new Object[]{"integer", v, QNameHelper.readable(sType)});
        }
    }

    private static void validateValue(BigInteger v, SchemaType sType, ValidationContext context) {
        BigInteger m;
        BigInteger m2;
        BigInteger m3;
        BigInteger m4;
        XmlAnySimpleType mine;
        XmlPositiveInteger td = (XmlPositiveInteger)sType.getFacet(7);
        if (td != null) {
            String temp = v.toString();
            int len = temp.length();
            if (len > 0 && temp.charAt(0) == '-') {
                --len;
            }
            if (len > td.getBigIntegerValue().intValue()) {
                context.invalid("cvc-totalDigits-valid", new Object[]{new Integer(len), temp, new Integer(td.getBigIntegerValue().intValue()), QNameHelper.readable(sType)});
                return;
            }
        }
        if ((mine = sType.getFacet(3)) != null && v.compareTo(m4 = JavaIntegerHolderEx.getBigIntegerValue(mine)) <= 0) {
            context.invalid("cvc-minExclusive-valid", new Object[]{"integer", v, m4, QNameHelper.readable(sType)});
            return;
        }
        XmlAnySimpleType mini = sType.getFacet(4);
        if (mini != null && v.compareTo(m3 = JavaIntegerHolderEx.getBigIntegerValue(mini)) < 0) {
            context.invalid("cvc-minInclusive-valid", new Object[]{"integer", v, m3, QNameHelper.readable(sType)});
            return;
        }
        XmlAnySimpleType maxi = sType.getFacet(5);
        if (maxi != null && v.compareTo(m2 = JavaIntegerHolderEx.getBigIntegerValue(maxi)) > 0) {
            context.invalid("cvc-maxInclusive-valid", new Object[]{"integer", v, m2, QNameHelper.readable(sType)});
            return;
        }
        XmlAnySimpleType maxe = sType.getFacet(6);
        if (maxe != null && v.compareTo(m = JavaIntegerHolderEx.getBigIntegerValue(maxe)) >= 0) {
            context.invalid("cvc-maxExclusive-valid", new Object[]{"integer", v, m, QNameHelper.readable(sType)});
            return;
        }
        XmlAnySimpleType[] vals = sType.getEnumerationValues();
        if (vals != null) {
            for (int i = 0; i < vals.length; ++i) {
                if (!v.equals(JavaIntegerHolderEx.getBigIntegerValue(vals[i]))) continue;
                return;
            }
            context.invalid("cvc-enumeration-valid", new Object[]{"integer", v, QNameHelper.readable(sType)});
        }
    }

    private static BigInteger getBigIntegerValue(XmlObject o) {
        SchemaType s = o.schemaType();
        switch (s.getDecimalSize()) {
            case 1000001: {
                return ((XmlObjectBase)o).bigDecimalValue().toBigInteger();
            }
            case 1000000: {
                return ((XmlObjectBase)o).bigIntegerValue();
            }
        }
        throw new IllegalStateException("Bad facet type for Big Int: " + s);
    }

    @Override
    protected void validate_simpleval(String lexical, ValidationContext ctx) {
        JavaIntegerHolderEx.validateLexical(lexical, this.schemaType(), ctx);
        JavaIntegerHolderEx.validateValue(this.getBigIntegerValue(), this.schemaType(), ctx);
    }
}

