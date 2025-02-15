/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.xmlbeans.impl.values;

import java.util.Calendar;
import java.util.Date;
import org.apache.xmlbeans.GDate;
import org.apache.xmlbeans.GDateBuilder;
import org.apache.xmlbeans.GDateSpecification;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlAnySimpleType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.common.QNameHelper;
import org.apache.xmlbeans.impl.common.ValidationContext;
import org.apache.xmlbeans.impl.values.NamespaceManager;
import org.apache.xmlbeans.impl.values.XmlObjectBase;
import org.apache.xmlbeans.impl.values.XmlValueOutOfRangeException;

public abstract class JavaGDateHolderEx
extends XmlObjectBase {
    private SchemaType _schemaType;
    private GDate _value;

    public JavaGDateHolderEx(SchemaType type, boolean complex) {
        this._schemaType = type;
        this.initComplexType(complex, false);
    }

    @Override
    public SchemaType schemaType() {
        return this._schemaType;
    }

    @Override
    protected String compute_text(NamespaceManager nsm) {
        return this._value == null ? "" : this._value.toString();
    }

    @Override
    protected void set_text(String s) {
        GDate newVal = this._validateOnSet() ? JavaGDateHolderEx.validateLexical(s, this._schemaType, _voorVc) : JavaGDateHolderEx.lex(s, this._schemaType, _voorVc);
        if (this._validateOnSet() && newVal != null) {
            JavaGDateHolderEx.validateValue(newVal, this._schemaType, _voorVc);
        }
        this._value = newVal;
    }

    public static GDate lex(String v, SchemaType sType, ValidationContext context) {
        GDate date = null;
        try {
            date = new GDate(v);
        } catch (Exception e) {
            context.invalid("date", new Object[]{v});
        }
        if (date != null) {
            if (date.getBuiltinTypeCode() != sType.getPrimitiveType().getBuiltinTypeCode()) {
                context.invalid("date", new Object[]{"wrong type: " + v});
                date = null;
            } else if (!date.isValid()) {
                context.invalid("date", new Object[]{v});
                date = null;
            }
        }
        return date;
    }

    public static GDate validateLexical(String v, SchemaType sType, ValidationContext context) {
        GDate date = JavaGDateHolderEx.lex(v, sType, context);
        if (date != null && sType.hasPatternFacet() && !sType.matchPatternFacet(v)) {
            context.invalid("cvc-datatype-valid.1.1", new Object[]{"date", v, QNameHelper.readable(sType)});
        }
        return date;
    }

    public static void validateValue(GDateSpecification v, SchemaType sType, ValidationContext context) {
        XmlAnySimpleType[] vals;
        GDate g;
        XmlAnySimpleType x;
        if (v.getBuiltinTypeCode() != sType.getPrimitiveType().getBuiltinTypeCode()) {
            context.invalid("date", new Object[]{"Date (" + v + ") does not have the set of fields required for " + QNameHelper.readable(sType)});
        }
        if ((x = sType.getFacet(3)) != null && v.compareToGDate(g = ((XmlObjectBase)((Object)x)).gDateValue()) <= 0) {
            context.invalid("cvc-minExclusive-valid", new Object[]{"date", v, g, QNameHelper.readable(sType)});
        }
        if ((x = sType.getFacet(4)) != null && v.compareToGDate(g = ((XmlObjectBase)((Object)x)).gDateValue()) < 0) {
            context.invalid("cvc-minInclusive-valid", new Object[]{"date", v, g, QNameHelper.readable(sType)});
        }
        if ((x = sType.getFacet(6)) != null && v.compareToGDate(g = ((XmlObjectBase)((Object)x)).gDateValue()) >= 0) {
            context.invalid("cvc-maxExclusive-valid", new Object[]{"date", v, g, QNameHelper.readable(sType)});
        }
        if ((x = sType.getFacet(5)) != null && v.compareToGDate(g = ((XmlObjectBase)((Object)x)).gDateValue()) > 0) {
            context.invalid("cvc-maxInclusive-valid", new Object[]{"date", v, g, QNameHelper.readable(sType)});
        }
        if ((vals = sType.getEnumerationValues()) != null) {
            for (int i = 0; i < vals.length; ++i) {
                if (v.compareToGDate(((XmlObjectBase)((Object)vals[i])).gDateValue()) != 0) continue;
                return;
            }
            context.invalid("cvc-enumeration-valid", new Object[]{"date", v, QNameHelper.readable(sType)});
        }
    }

    @Override
    protected void set_nil() {
        this._value = null;
    }

    @Override
    public int getIntValue() {
        int code = this.schemaType().getPrimitiveType().getBuiltinTypeCode();
        if (code != 20 && code != 21 && code != 18) {
            throw new XmlValueOutOfRangeException();
        }
        this.check_dated();
        if (this._value == null) {
            return 0;
        }
        switch (code) {
            case 20: {
                return this._value.getDay();
            }
            case 21: {
                return this._value.getMonth();
            }
            case 18: {
                return this._value.getYear();
            }
        }
        assert (false);
        throw new IllegalStateException();
    }

    @Override
    public GDate getGDateValue() {
        this.check_dated();
        if (this._value == null) {
            return null;
        }
        return this._value;
    }

    @Override
    public Calendar getCalendarValue() {
        this.check_dated();
        if (this._value == null) {
            return null;
        }
        return this._value.getCalendar();
    }

    @Override
    public Date getDateValue() {
        this.check_dated();
        if (this._value == null) {
            return null;
        }
        return this._value.getDate();
    }

    @Override
    protected void set_int(int v) {
        int code = this.schemaType().getPrimitiveType().getBuiltinTypeCode();
        if (code != 20 && code != 21 && code != 18) {
            throw new XmlValueOutOfRangeException();
        }
        GDateBuilder value = new GDateBuilder();
        switch (code) {
            case 20: {
                value.setDay(v);
                break;
            }
            case 21: {
                value.setMonth(v);
                break;
            }
            case 18: {
                value.setYear(v);
            }
        }
        if (this._validateOnSet()) {
            JavaGDateHolderEx.validateValue(value, this._schemaType, _voorVc);
        }
        this._value = value.toGDate();
    }

    @Override
    protected void set_GDate(GDateSpecification v) {
        GDate candidate;
        int code = this.schemaType().getPrimitiveType().getBuiltinTypeCode();
        if (v.isImmutable() && v instanceof GDate && v.getBuiltinTypeCode() == code) {
            candidate = (GDate)v;
        } else {
            if (v.getBuiltinTypeCode() != code) {
                GDateBuilder gDateBuilder = new GDateBuilder(v);
                gDateBuilder.setBuiltinTypeCode(code);
                v = gDateBuilder;
            }
            candidate = new GDate(v);
        }
        if (this._validateOnSet()) {
            JavaGDateHolderEx.validateValue(candidate, this._schemaType, _voorVc);
        }
        this._value = candidate;
    }

    @Override
    protected void set_Calendar(Calendar c) {
        int code = this.schemaType().getPrimitiveType().getBuiltinTypeCode();
        GDateBuilder gDateBuilder = new GDateBuilder(c);
        gDateBuilder.setBuiltinTypeCode(code);
        GDate value = gDateBuilder.toGDate();
        if (this._validateOnSet()) {
            JavaGDateHolderEx.validateValue(value, this._schemaType, _voorVc);
        }
        this._value = value;
    }

    @Override
    protected void set_Date(Date v) {
        int code = this.schemaType().getPrimitiveType().getBuiltinTypeCode();
        if (code != 16 && code != 14 || v == null) {
            throw new XmlValueOutOfRangeException();
        }
        GDateBuilder gDateBuilder = new GDateBuilder(v);
        gDateBuilder.setBuiltinTypeCode(code);
        GDate value = gDateBuilder.toGDate();
        if (this._validateOnSet()) {
            JavaGDateHolderEx.validateValue(value, this._schemaType, _voorVc);
        }
        this._value = value;
    }

    @Override
    protected int compare_to(XmlObject obj) {
        return this._value.compareToGDate(((XmlObjectBase)obj).gDateValue());
    }

    @Override
    protected boolean equal_to(XmlObject obj) {
        return this._value.equals(((XmlObjectBase)obj).gDateValue());
    }

    @Override
    protected int value_hash_code() {
        return this._value.hashCode();
    }

    @Override
    protected void validate_simpleval(String lexical, ValidationContext ctx) {
        JavaGDateHolderEx.validateLexical(lexical, this.schemaType(), ctx);
        JavaGDateHolderEx.validateValue(this.gDateValue(), this.schemaType(), ctx);
    }
}

