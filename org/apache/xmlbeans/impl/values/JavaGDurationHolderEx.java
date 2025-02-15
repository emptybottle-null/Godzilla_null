/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.xmlbeans.impl.values;

import org.apache.xmlbeans.GDuration;
import org.apache.xmlbeans.GDurationSpecification;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlAnySimpleType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.common.QNameHelper;
import org.apache.xmlbeans.impl.common.ValidationContext;
import org.apache.xmlbeans.impl.values.NamespaceManager;
import org.apache.xmlbeans.impl.values.XmlObjectBase;

public abstract class JavaGDurationHolderEx
extends XmlObjectBase {
    GDuration _value;
    private SchemaType _schemaType;

    public JavaGDurationHolderEx(SchemaType type, boolean complex) {
        this._schemaType = type;
        this.initComplexType(complex, false);
    }

    @Override
    public SchemaType schemaType() {
        return this._schemaType;
    }

    @Override
    protected void set_text(String s) {
        GDuration newVal = this._validateOnSet() ? JavaGDurationHolderEx.validateLexical(s, this._schemaType, _voorVc) : JavaGDurationHolderEx.lex(s, _voorVc);
        if (this._validateOnSet() && newVal != null) {
            JavaGDurationHolderEx.validateValue(newVal, this._schemaType, _voorVc);
        }
        this._value = newVal;
    }

    @Override
    protected void set_GDuration(GDurationSpecification v) {
        if (this._validateOnSet()) {
            JavaGDurationHolderEx.validateValue(v, this._schemaType, _voorVc);
        }
        this._value = v.isImmutable() && v instanceof GDuration ? (GDuration)v : new GDuration(v);
    }

    @Override
    protected String compute_text(NamespaceManager nsm) {
        return this._value == null ? "" : this._value.toString();
    }

    @Override
    protected void set_nil() {
        this._value = null;
    }

    @Override
    public GDuration getGDurationValue() {
        this.check_dated();
        return this._value == null ? null : this._value;
    }

    public static GDuration lex(String v, ValidationContext context) {
        GDuration duration = null;
        try {
            duration = new GDuration(v);
        } catch (Exception e) {
            context.invalid("duration", new Object[]{v});
        }
        return duration;
    }

    public static GDuration validateLexical(String v, SchemaType sType, ValidationContext context) {
        GDuration duration = JavaGDurationHolderEx.lex(v, context);
        if (duration != null && sType.hasPatternFacet() && !sType.matchPatternFacet(v)) {
            context.invalid("cvc-datatype-valid.1.1", new Object[]{"duration", v, QNameHelper.readable(sType)});
        }
        return duration;
    }

    public static void validateValue(GDurationSpecification v, SchemaType sType, ValidationContext context) {
        XmlAnySimpleType[] vals;
        GDuration g;
        XmlAnySimpleType x = sType.getFacet(3);
        if (x != null && v.compareToGDuration(g = ((XmlObjectBase)((Object)x)).gDurationValue()) <= 0) {
            context.invalid("cvc-minExclusive-valid", new Object[]{"duration", v, g, QNameHelper.readable(sType)});
        }
        if ((x = sType.getFacet(4)) != null && v.compareToGDuration(g = ((XmlObjectBase)((Object)x)).gDurationValue()) < 0) {
            context.invalid("cvc-minInclusive-valid", new Object[]{"duration", v, g, QNameHelper.readable(sType)});
        }
        if ((x = sType.getFacet(6)) != null && v.compareToGDuration(g = ((XmlObjectBase)((Object)x)).gDurationValue()) >= 0) {
            context.invalid("cvc-maxExclusive-valid", new Object[]{"duration", v, g, QNameHelper.readable(sType)});
        }
        if ((x = sType.getFacet(5)) != null && v.compareToGDuration(g = ((XmlObjectBase)((Object)x)).gDurationValue()) > 0) {
            context.invalid("cvc-maxInclusive-valid", new Object[]{"duration", v, g, QNameHelper.readable(sType)});
        }
        if ((vals = sType.getEnumerationValues()) != null) {
            for (int i = 0; i < vals.length; ++i) {
                if (v.compareToGDuration(((XmlObjectBase)((Object)vals[i])).gDurationValue()) != 0) continue;
                return;
            }
            context.invalid("cvc-enumeration-valid", new Object[]{"duration", v, QNameHelper.readable(sType)});
        }
    }

    @Override
    protected int compare_to(XmlObject d) {
        return this._value.compareToGDuration(((XmlObjectBase)d).gDurationValue());
    }

    @Override
    protected boolean equal_to(XmlObject d) {
        return this._value.equals(((XmlObjectBase)d).gDurationValue());
    }

    @Override
    protected int value_hash_code() {
        return this._value.hashCode();
    }

    @Override
    protected void validate_simpleval(String lexical, ValidationContext ctx) {
        JavaGDurationHolderEx.validateLexical(lexical, this.schemaType(), ctx);
        JavaGDurationHolderEx.validateValue(this.gDurationValue(), this.schemaType(), ctx);
    }
}

