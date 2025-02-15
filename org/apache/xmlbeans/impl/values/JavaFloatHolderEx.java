/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.xmlbeans.impl.values;

import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlAnySimpleType;
import org.apache.xmlbeans.impl.common.QNameHelper;
import org.apache.xmlbeans.impl.common.ValidationContext;
import org.apache.xmlbeans.impl.values.JavaFloatHolder;
import org.apache.xmlbeans.impl.values.XmlObjectBase;

public abstract class JavaFloatHolderEx
extends JavaFloatHolder {
    private SchemaType _schemaType;

    public JavaFloatHolderEx(SchemaType type, boolean complex) {
        this._schemaType = type;
        this.initComplexType(complex, false);
    }

    @Override
    public SchemaType schemaType() {
        return this._schemaType;
    }

    @Override
    protected void set_float(float v) {
        if (this._validateOnSet()) {
            JavaFloatHolderEx.validateValue(v, this._schemaType, _voorVc);
        }
        super.set_float(v);
    }

    public static float validateLexical(String v, SchemaType sType, ValidationContext context) {
        float f = JavaFloatHolder.validateLexical(v, context);
        if (!sType.matchPatternFacet(v)) {
            context.invalid("cvc-datatype-valid.1.1", new Object[]{"float", v, QNameHelper.readable(sType)});
        }
        return f;
    }

    public static void validateValue(float v, SchemaType sType, ValidationContext context) {
        XmlAnySimpleType[] vals;
        float f;
        XmlAnySimpleType x = sType.getFacet(3);
        if (x != null && JavaFloatHolderEx.compare(v, f = ((XmlObjectBase)((Object)x)).floatValue()) <= 0) {
            context.invalid("cvc-minExclusive-valid", new Object[]{"float", new Float(v), new Float(f), QNameHelper.readable(sType)});
        }
        if ((x = sType.getFacet(4)) != null && JavaFloatHolderEx.compare(v, f = ((XmlObjectBase)((Object)x)).floatValue()) < 0) {
            context.invalid("cvc-minInclusive-valid", new Object[]{"float", new Float(v), new Float(f), QNameHelper.readable(sType)});
        }
        if ((x = sType.getFacet(5)) != null && JavaFloatHolderEx.compare(v, f = ((XmlObjectBase)((Object)x)).floatValue()) > 0) {
            context.invalid("cvc-maxInclusive-valid", new Object[]{"float", new Float(v), new Float(f), QNameHelper.readable(sType)});
        }
        if ((x = sType.getFacet(6)) != null && JavaFloatHolderEx.compare(v, f = ((XmlObjectBase)((Object)x)).floatValue()) >= 0) {
            context.invalid("cvc-maxExclusive-valid", new Object[]{"float", new Float(v), new Float(f), QNameHelper.readable(sType)});
        }
        if ((vals = sType.getEnumerationValues()) != null) {
            for (int i = 0; i < vals.length; ++i) {
                if (JavaFloatHolderEx.compare(v, ((XmlObjectBase)((Object)vals[i])).floatValue()) != 0) continue;
                return;
            }
            context.invalid("cvc-enumeration-valid", new Object[]{"float", new Float(v), QNameHelper.readable(sType)});
        }
    }

    @Override
    protected void validate_simpleval(String lexical, ValidationContext ctx) {
        JavaFloatHolderEx.validateLexical(lexical, this.schemaType(), ctx);
        JavaFloatHolderEx.validateValue(this.floatValue(), this.schemaType(), ctx);
    }
}

