/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.xmlbeans.impl.values;

import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlAnySimpleType;
import org.apache.xmlbeans.impl.common.QNameHelper;
import org.apache.xmlbeans.impl.common.ValidationContext;
import org.apache.xmlbeans.impl.values.JavaDoubleHolder;
import org.apache.xmlbeans.impl.values.XmlObjectBase;

public abstract class JavaDoubleHolderEx
extends JavaDoubleHolder {
    private SchemaType _schemaType;

    public JavaDoubleHolderEx(SchemaType type, boolean complex) {
        this._schemaType = type;
        this.initComplexType(complex, false);
    }

    @Override
    public SchemaType schemaType() {
        return this._schemaType;
    }

    @Override
    protected void set_double(double v) {
        if (this._validateOnSet()) {
            JavaDoubleHolderEx.validateValue(v, this._schemaType, _voorVc);
        }
        super.set_double(v);
    }

    public static double validateLexical(String v, SchemaType sType, ValidationContext context) {
        double d = JavaDoubleHolder.validateLexical(v, context);
        if (!sType.matchPatternFacet(v)) {
            context.invalid("cvc-datatype-valid.1.1", new Object[]{"double", v, QNameHelper.readable(sType)});
        }
        return d;
    }

    public static void validateValue(double v, SchemaType sType, ValidationContext context) {
        XmlAnySimpleType[] vals;
        double d;
        XmlAnySimpleType x = sType.getFacet(3);
        if (x != null && JavaDoubleHolderEx.compare(v, d = ((XmlObjectBase)((Object)x)).doubleValue()) <= 0) {
            context.invalid("cvc-minExclusive-valid", new Object[]{"double", new Double(v), new Double(d), QNameHelper.readable(sType)});
        }
        if ((x = sType.getFacet(4)) != null && JavaDoubleHolderEx.compare(v, d = ((XmlObjectBase)((Object)x)).doubleValue()) < 0) {
            context.invalid("cvc-minInclusive-valid", new Object[]{"double", new Double(v), new Double(d), QNameHelper.readable(sType)});
        }
        if ((x = sType.getFacet(5)) != null && JavaDoubleHolderEx.compare(v, d = ((XmlObjectBase)((Object)x)).doubleValue()) > 0) {
            context.invalid("cvc-maxInclusive-valid", new Object[]{"double", new Double(v), new Double(d), QNameHelper.readable(sType)});
        }
        if ((x = sType.getFacet(6)) != null && JavaDoubleHolderEx.compare(v, d = ((XmlObjectBase)((Object)x)).doubleValue()) >= 0) {
            context.invalid("cvc-maxExclusive-valid", new Object[]{"double", new Double(v), new Double(d), QNameHelper.readable(sType)});
        }
        if ((vals = sType.getEnumerationValues()) != null) {
            for (int i = 0; i < vals.length; ++i) {
                if (JavaDoubleHolderEx.compare(v, ((XmlObjectBase)((Object)vals[i])).doubleValue()) != 0) continue;
                return;
            }
            context.invalid("cvc-enumeration-valid", new Object[]{"double", new Double(v), QNameHelper.readable(sType)});
        }
    }

    @Override
    protected void validate_simpleval(String lexical, ValidationContext ctx) {
        JavaDoubleHolderEx.validateLexical(lexical, this.schemaType(), ctx);
        JavaDoubleHolderEx.validateValue(this.doubleValue(), this.schemaType(), ctx);
    }
}

