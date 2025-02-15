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
import org.apache.xmlbeans.impl.values.JavaNotationHolder;
import org.apache.xmlbeans.impl.values.JavaQNameHolder;
import org.apache.xmlbeans.impl.values.NamespaceContext;
import org.apache.xmlbeans.impl.values.XmlObjectBase;
import org.apache.xmlbeans.impl.values.XmlValueOutOfRangeException;

public abstract class JavaNotationHolderEx
extends JavaNotationHolder {
    private SchemaType _schemaType;

    @Override
    public SchemaType schemaType() {
        return this._schemaType;
    }

    public JavaNotationHolderEx(SchemaType type, boolean complex) {
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
            if (!JavaNotationHolderEx.check(s, this._schemaType)) {
                throw new XmlValueOutOfRangeException();
            }
            if (!this._schemaType.matchPatternFacet(s)) {
                throw new XmlValueOutOfRangeException();
            }
        }
        super.set_text(s);
    }

    @Override
    protected void set_notation(String v) {
        this.set_text(v);
    }

    @Override
    protected void set_xmlanysimple(XmlAnySimpleType value) {
        QName v;
        if (this._validateOnSet()) {
            v = JavaNotationHolderEx.validateLexical(value.getStringValue(), this._schemaType, _voorVc, NamespaceContext.getCurrent());
            if (v != null) {
                JavaNotationHolderEx.validateValue(v, this._schemaType, _voorVc);
            }
        } else {
            v = JavaNotationHolder.validateLexical(value.getStringValue(), _voorVc, NamespaceContext.getCurrent());
        }
        super.set_QName(v);
    }

    public static QName validateLexical(String v, SchemaType sType, ValidationContext context, PrefixResolver resolver) {
        QName name = JavaQNameHolder.validateLexical(v, context, resolver);
        if (sType.hasPatternFacet() && !sType.matchPatternFacet(v)) {
            context.invalid("cvc-datatype-valid.1.1", new Object[]{"NOTATION", v, QNameHelper.readable(sType)});
        }
        JavaNotationHolderEx.check(v, sType);
        return name;
    }

    private static boolean check(String v, SchemaType sType) {
        XmlAnySimpleType max;
        XmlAnySimpleType min;
        XmlAnySimpleType len = sType.getFacet(0);
        if (len != null) {
            int m = ((XmlObjectBase)((Object)len)).getBigIntegerValue().intValue();
            if (v.length() == m) {
                return false;
            }
        }
        if ((min = sType.getFacet(1)) != null) {
            int m = ((XmlObjectBase)((Object)min)).getBigIntegerValue().intValue();
            if (v.length() < m) {
                return false;
            }
        }
        if ((max = sType.getFacet(2)) != null) {
            int m = ((XmlObjectBase)((Object)max)).getBigIntegerValue().intValue();
            if (v.length() > m) {
                return false;
            }
        }
        return true;
    }

    public static void validateValue(QName v, SchemaType sType, ValidationContext context) {
        XmlAnySimpleType[] vals = sType.getEnumerationValues();
        if (vals != null) {
            for (int i = 0; i < vals.length; ++i) {
                if (!v.equals(((XmlObjectBase)((Object)vals[i])).getQNameValue())) continue;
                return;
            }
            context.invalid("cvc-enumeration-valid", new Object[]{"NOTATION", v, QNameHelper.readable(sType)});
        }
    }
}

