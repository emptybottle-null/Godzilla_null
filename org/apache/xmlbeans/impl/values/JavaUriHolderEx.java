/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.xmlbeans.impl.values;

import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.XmlAnySimpleType;
import org.apache.xmlbeans.impl.common.QNameHelper;
import org.apache.xmlbeans.impl.common.ValidationContext;
import org.apache.xmlbeans.impl.values.JavaUriHolder;
import org.apache.xmlbeans.impl.values.XmlAnyUriImpl;
import org.apache.xmlbeans.impl.values.XmlValueOutOfRangeException;

public class JavaUriHolderEx
extends JavaUriHolder {
    private SchemaType _schemaType;

    @Override
    public SchemaType schemaType() {
        return this._schemaType;
    }

    public JavaUriHolderEx(SchemaType type, boolean complex) {
        this._schemaType = type;
        this.initComplexType(complex, false);
    }

    @Override
    protected int get_wscanon_rule() {
        return this.schemaType().getWhiteSpaceRule();
    }

    @Override
    protected void set_text(String s) {
        if (this._validateOnSet()) {
            if (!JavaUriHolderEx.check(s, this._schemaType)) {
                throw new XmlValueOutOfRangeException();
            }
            if (!this._schemaType.matchPatternFacet(s)) {
                throw new XmlValueOutOfRangeException();
            }
        }
        super.set_text(s);
    }

    public static void validateLexical(String v, SchemaType sType, ValidationContext context) {
        int i;
        XmlAnySimpleType x;
        XmlAnyUriImpl.validateLexical(v, context);
        XmlAnySimpleType[] vals = sType.getEnumerationValues();
        if (vals != null) {
            String e;
            int i2;
            for (i2 = 0; i2 < vals.length && !(e = ((SimpleValue)((Object)vals[i2])).getStringValue()).equals(v); ++i2) {
            }
            if (i2 >= vals.length) {
                context.invalid("cvc-enumeration-valid", new Object[]{"anyURI", v, QNameHelper.readable(sType)});
            }
        }
        if (sType.hasPatternFacet() && !sType.matchPatternFacet(v)) {
            context.invalid("cvc-datatype-valid.1.1", new Object[]{"anyURI", v, QNameHelper.readable(sType)});
        }
        if ((x = sType.getFacet(0)) != null && (i = ((SimpleValue)((Object)x)).getBigIntegerValue().intValue()) != v.length()) {
            context.invalid("cvc-length-valid.1.1", new Object[]{"anyURI", v, new Integer(i), QNameHelper.readable(sType)});
        }
        if ((x = sType.getFacet(1)) != null && (i = ((SimpleValue)((Object)x)).getBigIntegerValue().intValue()) > v.length()) {
            context.invalid("cvc-minLength-valid.1.1", new Object[]{"anyURI", v, new Integer(i), QNameHelper.readable(sType)});
        }
        if ((x = sType.getFacet(2)) != null && (i = ((SimpleValue)((Object)x)).getBigIntegerValue().intValue()) < v.length()) {
            context.invalid("cvc-maxLength-valid.1.1", new Object[]{"anyURI", v, new Integer(i), QNameHelper.readable(sType)});
        }
    }

    private static boolean check(String v, SchemaType sType) {
        int m;
        int m2;
        int m3;
        int length = v == null ? 0 : v.length();
        XmlAnySimpleType len = sType.getFacet(0);
        if (len != null && length == (m3 = ((SimpleValue)((Object)len)).getBigIntegerValue().intValue())) {
            return false;
        }
        XmlAnySimpleType min = sType.getFacet(1);
        if (min != null && length < (m2 = ((SimpleValue)((Object)min)).getBigIntegerValue().intValue())) {
            return false;
        }
        XmlAnySimpleType max = sType.getFacet(2);
        return max == null || length <= (m = ((SimpleValue)((Object)max)).getBigIntegerValue().intValue());
    }

    @Override
    protected void validate_simpleval(String lexical, ValidationContext ctx) {
        JavaUriHolderEx.validateLexical(this.stringValue(), this.schemaType(), ctx);
    }
}

