/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.xmlbeans.impl.values;

import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlAnySimpleType;
import org.apache.xmlbeans.impl.common.QNameHelper;
import org.apache.xmlbeans.impl.common.ValidationContext;
import org.apache.xmlbeans.impl.values.JavaHexBinaryHolder;
import org.apache.xmlbeans.impl.values.XmlObjectBase;

public abstract class JavaHexBinaryHolderEx
extends JavaHexBinaryHolder {
    private SchemaType _schemaType;

    @Override
    public SchemaType schemaType() {
        return this._schemaType;
    }

    public JavaHexBinaryHolderEx(SchemaType type, boolean complex) {
        this._schemaType = type;
        this.initComplexType(complex, false);
    }

    @Override
    protected int get_wscanon_rule() {
        return this.schemaType().getWhiteSpaceRule();
    }

    @Override
    protected void set_text(String s) {
        byte[] v = this._validateOnSet() ? JavaHexBinaryHolderEx.validateLexical(s, this.schemaType(), _voorVc) : JavaHexBinaryHolderEx.lex(s, _voorVc);
        if (this._validateOnSet() && v != null) {
            JavaHexBinaryHolderEx.validateValue(v, this.schemaType(), XmlObjectBase._voorVc);
        }
        super.set_ByteArray(v);
        this._value = v;
    }

    @Override
    protected void set_ByteArray(byte[] v) {
        if (this._validateOnSet()) {
            JavaHexBinaryHolderEx.validateValue(v, this.schemaType(), _voorVc);
        }
        super.set_ByteArray(v);
    }

    public static void validateValue(byte[] v, SchemaType sType, ValidationContext context) {
        XmlAnySimpleType[] vals;
        int i;
        XmlAnySimpleType o = sType.getFacet(0);
        if (o != null && (i = ((XmlObjectBase)((Object)o)).bigIntegerValue().intValue()) != v.length) {
            context.invalid("cvc-length-valid.1.2", new Object[]{"hexBinary", new Integer(v.length), new Integer(i), QNameHelper.readable(sType)});
        }
        if ((o = sType.getFacet(1)) != null && (i = ((XmlObjectBase)((Object)o)).bigIntegerValue().intValue()) > v.length) {
            context.invalid("cvc-minLength-valid.1.2", new Object[]{"hexBinary", new Integer(v.length), new Integer(i), QNameHelper.readable(sType)});
        }
        if ((o = sType.getFacet(2)) != null && (i = ((XmlObjectBase)((Object)o)).bigIntegerValue().intValue()) < v.length) {
            context.invalid("cvc-maxLength-valid.1.2", new Object[]{"hexBinary", new Integer(v.length), new Integer(i), QNameHelper.readable(sType)});
        }
        if ((vals = sType.getEnumerationValues()) != null) {
            block0: for (i = 0; i < vals.length; ++i) {
                byte[] enumBytes = ((XmlObjectBase)((Object)vals[i])).byteArrayValue();
                if (enumBytes.length != v.length) continue;
                for (int j = 0; j < enumBytes.length; ++j) {
                    if (enumBytes[j] != v[j]) continue block0;
                }
            }
            if (i >= vals.length) {
                context.invalid("cvc-enumeration-valid.b", new Object[]{"hexBinary", QNameHelper.readable(sType)});
            }
        }
    }

    @Override
    protected void validate_simpleval(String lexical, ValidationContext ctx) {
        JavaHexBinaryHolderEx.validateLexical(lexical, this.schemaType(), ctx);
        JavaHexBinaryHolderEx.validateValue(this.byteArrayValue(), this.schemaType(), ctx);
    }
}

