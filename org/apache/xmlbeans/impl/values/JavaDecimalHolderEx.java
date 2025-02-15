/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.xmlbeans.impl.values;

import java.math.BigDecimal;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlAnySimpleType;
import org.apache.xmlbeans.impl.common.QNameHelper;
import org.apache.xmlbeans.impl.common.ValidationContext;
import org.apache.xmlbeans.impl.values.JavaDecimalHolder;
import org.apache.xmlbeans.impl.values.XmlObjectBase;

public abstract class JavaDecimalHolderEx
extends JavaDecimalHolder {
    private SchemaType _schemaType;

    @Override
    public SchemaType schemaType() {
        return this._schemaType;
    }

    public JavaDecimalHolderEx(SchemaType type, boolean complex) {
        this._schemaType = type;
        this.initComplexType(complex, false);
    }

    @Override
    protected void set_text(String s) {
        if (this._validateOnSet()) {
            JavaDecimalHolderEx.validateLexical(s, this._schemaType, _voorVc);
        }
        BigDecimal v = null;
        try {
            v = new BigDecimal(s);
        } catch (NumberFormatException e) {
            _voorVc.invalid("decimal", new Object[]{s});
        }
        if (this._validateOnSet()) {
            JavaDecimalHolderEx.validateValue(v, this._schemaType, _voorVc);
        }
        super.set_BigDecimal(v);
    }

    @Override
    protected void set_BigDecimal(BigDecimal v) {
        if (this._validateOnSet()) {
            JavaDecimalHolderEx.validateValue(v, this._schemaType, _voorVc);
        }
        super.set_BigDecimal(v);
    }

    public static void validateLexical(String v, SchemaType sType, ValidationContext context) {
        JavaDecimalHolder.validateLexical(v, context);
        if (sType.hasPatternFacet() && !sType.matchPatternFacet(v)) {
            context.invalid("cvc-datatype-valid.1.1", new Object[]{"decimal", v, QNameHelper.readable(sType)});
        }
    }

    public static void validateValue(BigDecimal v, SchemaType sType, ValidationContext context) {
        BigDecimal m;
        BigDecimal m2;
        BigDecimal m3;
        BigDecimal m4;
        XmlAnySimpleType mine;
        XmlAnySimpleType td;
        XmlAnySimpleType fd = sType.getFacet(8);
        if (fd != null) {
            int scale = ((XmlObjectBase)((Object)fd)).getBigIntegerValue().intValue();
            try {
                v.setScale(scale);
            } catch (ArithmeticException e) {
                context.invalid("cvc-fractionDigits-valid", new Object[]{new Integer(v.scale()), v.toString(), new Integer(scale), QNameHelper.readable(sType)});
                return;
            }
        }
        if ((td = sType.getFacet(7)) != null) {
            int origLen;
            String temp = v.unscaledValue().toString();
            int tdf = ((XmlObjectBase)((Object)td)).getBigIntegerValue().intValue();
            int len = origLen = temp.length();
            if (origLen > 0) {
                if (temp.charAt(0) == '-') {
                    --len;
                }
                int insignificantTrailingZeros = 0;
                int vScale = v.scale();
                for (int j = origLen - 1; temp.charAt(j) == '0' && j > 0 && insignificantTrailingZeros < vScale; ++insignificantTrailingZeros, --j) {
                }
                len -= insignificantTrailingZeros;
            }
            if (len > tdf) {
                context.invalid("cvc-totalDigits-valid", new Object[]{new Integer(len), v.toString(), new Integer(tdf), QNameHelper.readable(sType)});
                return;
            }
        }
        if ((mine = sType.getFacet(3)) != null && v.compareTo(m4 = ((XmlObjectBase)((Object)mine)).getBigDecimalValue()) <= 0) {
            context.invalid("cvc-minExclusive-valid", new Object[]{"decimal", v, m4, QNameHelper.readable(sType)});
            return;
        }
        XmlAnySimpleType mini = sType.getFacet(4);
        if (mini != null && v.compareTo(m3 = ((XmlObjectBase)((Object)mini)).getBigDecimalValue()) < 0) {
            context.invalid("cvc-minInclusive-valid", new Object[]{"decimal", v, m3, QNameHelper.readable(sType)});
            return;
        }
        XmlAnySimpleType maxi = sType.getFacet(5);
        if (maxi != null && v.compareTo(m2 = ((XmlObjectBase)((Object)maxi)).getBigDecimalValue()) > 0) {
            context.invalid("cvc-maxInclusive-valid", new Object[]{"decimal", v, m2, QNameHelper.readable(sType)});
            return;
        }
        XmlAnySimpleType maxe = sType.getFacet(6);
        if (maxe != null && v.compareTo(m = ((XmlObjectBase)((Object)maxe)).getBigDecimalValue()) >= 0) {
            context.invalid("cvc-maxExclusive-valid", new Object[]{"decimal", v, m, QNameHelper.readable(sType)});
            return;
        }
        XmlAnySimpleType[] vals = sType.getEnumerationValues();
        if (vals != null) {
            for (int i = 0; i < vals.length; ++i) {
                if (!v.equals(((XmlObjectBase)((Object)vals[i])).getBigDecimalValue())) continue;
                return;
            }
            context.invalid("cvc-enumeration-valid", new Object[]{"decimal", v, QNameHelper.readable(sType)});
        }
    }

    @Override
    protected void validate_simpleval(String lexical, ValidationContext ctx) {
        JavaDecimalHolderEx.validateLexical(lexical, this.schemaType(), ctx);
        JavaDecimalHolderEx.validateValue(this.getBigDecimalValue(), this.schemaType(), ctx);
    }
}

