/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.xmlbeans.impl.values;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlAnySimpleType;
import org.apache.xmlbeans.impl.common.PrefixResolver;
import org.apache.xmlbeans.impl.common.QNameHelper;
import org.apache.xmlbeans.impl.common.ValidationContext;
import org.apache.xmlbeans.impl.values.JavaQNameHolder;
import org.apache.xmlbeans.impl.values.NamespaceContext;
import org.apache.xmlbeans.impl.values.XmlObjectBase;

public abstract class JavaQNameHolderEx
extends JavaQNameHolder {
    private SchemaType _schemaType;

    @Override
    public SchemaType schemaType() {
        return this._schemaType;
    }

    public JavaQNameHolderEx(SchemaType type, boolean complex) {
        this._schemaType = type;
        this.initComplexType(complex, false);
    }

    @Override
    protected int get_wscanon_rule() {
        return this.schemaType().getWhiteSpaceRule();
    }

    @Override
    protected void set_text(String s) {
        QName v;
        PrefixResolver resolver = NamespaceContext.getCurrent();
        if (resolver == null && this.has_store()) {
            resolver = this.get_store();
        }
        if (this._validateOnSet()) {
            v = JavaQNameHolderEx.validateLexical(s, this._schemaType, _voorVc, resolver);
            if (v != null) {
                JavaQNameHolderEx.validateValue(v, this._schemaType, _voorVc);
            }
        } else {
            v = JavaQNameHolder.validateLexical(s, _voorVc, resolver);
        }
        super.set_QName(v);
    }

    @Override
    protected void set_QName(QName name) {
        if (this._validateOnSet()) {
            JavaQNameHolderEx.validateValue(name, this._schemaType, _voorVc);
        }
        super.set_QName(name);
    }

    @Override
    protected void set_xmlanysimple(XmlAnySimpleType value) {
        QName v;
        if (this._validateOnSet()) {
            v = JavaQNameHolderEx.validateLexical(value.getStringValue(), this._schemaType, _voorVc, NamespaceContext.getCurrent());
            if (v != null) {
                JavaQNameHolderEx.validateValue(v, this._schemaType, _voorVc);
            }
        } else {
            v = JavaQNameHolder.validateLexical(value.getStringValue(), _voorVc, NamespaceContext.getCurrent());
        }
        super.set_QName(v);
    }

    public static QName validateLexical(String v, SchemaType sType, ValidationContext context, PrefixResolver resolver) {
        QName name = JavaQNameHolder.validateLexical(v, context, resolver);
        if (sType.hasPatternFacet() && !sType.matchPatternFacet(v)) {
            context.invalid("cvc-datatype-valid.1.1", new Object[]{"QName", v, QNameHelper.readable(sType)});
        }
        return name;
    }

    public static void validateValue(QName v, SchemaType sType, ValidationContext context) {
        XmlAnySimpleType[] vals = sType.getEnumerationValues();
        if (vals != null) {
            for (int i = 0; i < vals.length; ++i) {
                if (!v.equals(((XmlObjectBase)((Object)vals[i])).getQNameValue())) continue;
                return;
            }
            context.invalid("cvc-enumeration-valid", new Object[]{"QName", v, QNameHelper.readable(sType)});
        }
    }

    @Override
    protected void validate_simpleval(String lexical, ValidationContext ctx) {
        JavaQNameHolderEx.validateValue(this.getQNameValue(), this.schemaType(), ctx);
    }
}

