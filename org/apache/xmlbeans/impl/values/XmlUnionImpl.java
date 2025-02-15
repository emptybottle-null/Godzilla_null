/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.xmlbeans.impl.values;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.xml.namespace.QName;
import org.apache.xmlbeans.GDate;
import org.apache.xmlbeans.GDateSpecification;
import org.apache.xmlbeans.GDuration;
import org.apache.xmlbeans.GDurationSpecification;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.StringEnumAbstractBase;
import org.apache.xmlbeans.XmlAnySimpleType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.common.QNameHelper;
import org.apache.xmlbeans.impl.common.ValidationContext;
import org.apache.xmlbeans.impl.schema.SchemaTypeImpl;
import org.apache.xmlbeans.impl.values.NamespaceContext;
import org.apache.xmlbeans.impl.values.NamespaceManager;
import org.apache.xmlbeans.impl.values.XmlObjectBase;
import org.apache.xmlbeans.impl.values.XmlValueOutOfRangeException;

public class XmlUnionImpl
extends XmlObjectBase
implements XmlAnySimpleType {
    private SchemaType _schemaType;
    private XmlAnySimpleType _value;
    private String _textvalue = "";
    private static final int JAVA_NUMBER = 47;
    private static final int JAVA_DATE = 48;
    private static final int JAVA_CALENDAR = 49;
    private static final int JAVA_BYTEARRAY = 50;
    private static final int JAVA_LIST = 51;

    public XmlUnionImpl(SchemaType type, boolean complex) {
        this._schemaType = type;
        this.initComplexType(complex, false);
    }

    @Override
    public SchemaType schemaType() {
        return this._schemaType;
    }

    @Override
    public SchemaType instanceType() {
        this.check_dated();
        return this._value == null ? null : ((SimpleValue)((Object)this._value)).instanceType();
    }

    @Override
    protected String compute_text(NamespaceManager nsm) {
        return this._textvalue;
    }

    @Override
    protected boolean is_defaultable_ws(String v) {
        try {
            XmlAnySimpleType savedValue = this._value;
            this.set_text(v);
            this._value = savedValue;
            return false;
        } catch (XmlValueOutOfRangeException e) {
            return true;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    protected void set_text(String s) {
        if (!this._schemaType.matchPatternFacet(s) && this._validateOnSet()) {
            throw new XmlValueOutOfRangeException("cvc-datatype-valid.1.1", new Object[]{"string", s, QNameHelper.readable(this._schemaType)});
        }
        String original = this._textvalue;
        this._textvalue = s;
        SchemaType[] members = this._schemaType.getUnionConstituentTypes();
        assert (members != null);
        boolean pushed = false;
        if (this.has_store()) {
            NamespaceContext.push(new NamespaceContext(this.get_store()));
            pushed = true;
        }
        try {
            boolean validate = true;
            while (validate || !this._validateOnSet()) {
                for (int i = 0; i < members.length; ++i) {
                    try {
                        XmlAnySimpleType newval = ((SchemaTypeImpl)members[i]).newValue(s, validate);
                        if (!XmlUnionImpl.check(newval, this._schemaType)) continue;
                        this._value = newval;
                        return;
                    } catch (XmlValueOutOfRangeException e) {
                        continue;
                    } catch (Exception e) {
                        throw new RuntimeException("Troublesome union exception caused by unexpected " + e, e);
                    }
                }
                if (!validate) {
                    break;
                }
                validate = false;
            }
        } finally {
            if (pushed) {
                NamespaceContext.pop();
            }
        }
        this._textvalue = original;
        throw new XmlValueOutOfRangeException("cvc-datatype-valid.1.2.3", new Object[]{s, QNameHelper.readable(this._schemaType)});
    }

    @Override
    protected void set_nil() {
        this._value = null;
        this._textvalue = null;
    }

    @Override
    protected int get_wscanon_rule() {
        return 1;
    }

    @Override
    public float getFloatValue() {
        this.check_dated();
        return this._value == null ? 0.0f : ((SimpleValue)((Object)this._value)).getFloatValue();
    }

    @Override
    public double getDoubleValue() {
        this.check_dated();
        return this._value == null ? 0.0 : ((SimpleValue)((Object)this._value)).getDoubleValue();
    }

    @Override
    public BigDecimal getBigDecimalValue() {
        this.check_dated();
        return this._value == null ? null : ((SimpleValue)((Object)this._value)).getBigDecimalValue();
    }

    @Override
    public BigInteger getBigIntegerValue() {
        this.check_dated();
        return this._value == null ? null : ((SimpleValue)((Object)this._value)).getBigIntegerValue();
    }

    @Override
    public byte getByteValue() {
        this.check_dated();
        return this._value == null ? (byte)0 : ((SimpleValue)((Object)this._value)).getByteValue();
    }

    @Override
    public short getShortValue() {
        this.check_dated();
        return this._value == null ? (short)0 : ((SimpleValue)((Object)this._value)).getShortValue();
    }

    @Override
    public int getIntValue() {
        this.check_dated();
        return this._value == null ? 0 : ((SimpleValue)((Object)this._value)).getIntValue();
    }

    @Override
    public long getLongValue() {
        this.check_dated();
        return this._value == null ? 0L : ((SimpleValue)((Object)this._value)).getLongValue();
    }

    @Override
    public byte[] getByteArrayValue() {
        this.check_dated();
        return this._value == null ? null : ((SimpleValue)((Object)this._value)).getByteArrayValue();
    }

    @Override
    public boolean getBooleanValue() {
        this.check_dated();
        return this._value == null ? false : ((SimpleValue)((Object)this._value)).getBooleanValue();
    }

    @Override
    public Calendar getCalendarValue() {
        this.check_dated();
        return this._value == null ? null : ((SimpleValue)((Object)this._value)).getCalendarValue();
    }

    @Override
    public Date getDateValue() {
        this.check_dated();
        return this._value == null ? null : ((SimpleValue)((Object)this._value)).getDateValue();
    }

    @Override
    public GDate getGDateValue() {
        this.check_dated();
        return this._value == null ? null : ((SimpleValue)((Object)this._value)).getGDateValue();
    }

    @Override
    public GDuration getGDurationValue() {
        this.check_dated();
        return this._value == null ? null : ((SimpleValue)((Object)this._value)).getGDurationValue();
    }

    @Override
    public QName getQNameValue() {
        this.check_dated();
        return this._value == null ? null : ((SimpleValue)((Object)this._value)).getQNameValue();
    }

    @Override
    public List getListValue() {
        this.check_dated();
        return this._value == null ? null : ((SimpleValue)((Object)this._value)).getListValue();
    }

    @Override
    public List xgetListValue() {
        this.check_dated();
        return this._value == null ? null : ((SimpleValue)((Object)this._value)).xgetListValue();
    }

    @Override
    public StringEnumAbstractBase getEnumValue() {
        this.check_dated();
        return this._value == null ? null : ((SimpleValue)((Object)this._value)).getEnumValue();
    }

    @Override
    public String getStringValue() {
        this.check_dated();
        return this._value == null ? null : this._value.getStringValue();
    }

    static boolean lexical_overlap(int source, int target) {
        if (source == target) {
            return true;
        }
        if (source == 2 || target == 2 || source == 12 || target == 12 || source == 6 || target == 6) {
            return true;
        }
        switch (source) {
            case 3: {
                switch (target) {
                    case 7: 
                    case 8: {
                        return true;
                    }
                }
                return false;
            }
            case 4: {
                switch (target) {
                    case 3: 
                    case 5: 
                    case 7: 
                    case 8: 
                    case 9: 
                    case 10: 
                    case 11: 
                    case 13: 
                    case 18: {
                        return true;
                    }
                }
                return false;
            }
            case 5: {
                switch (target) {
                    case 3: 
                    case 4: 
                    case 7: 
                    case 8: 
                    case 9: 
                    case 10: 
                    case 11: 
                    case 18: {
                        return true;
                    }
                }
                return false;
            }
            case 7: 
            case 8: {
                switch (target) {
                    case 3: 
                    case 4: 
                    case 5: 
                    case 7: 
                    case 8: 
                    case 13: {
                        return true;
                    }
                }
                return false;
            }
            case 9: 
            case 10: 
            case 11: 
            case 18: {
                switch (target) {
                    case 4: 
                    case 5: 
                    case 9: 
                    case 10: 
                    case 11: 
                    case 18: {
                        return true;
                    }
                }
                return false;
            }
            case 13: {
                switch (target) {
                    case 4: 
                    case 7: 
                    case 8: {
                        return true;
                    }
                }
                return false;
            }
        }
        return false;
    }

    private static boolean logical_overlap(SchemaType type, int javacode) {
        assert (type.getSimpleVariety() != 2);
        if (javacode <= 46) {
            if (type.getSimpleVariety() != 1) {
                return false;
            }
            return type.getPrimitiveType().getBuiltinTypeCode() == javacode;
        }
        switch (javacode) {
            case 47: {
                if (type.getSimpleVariety() != 1) {
                    return false;
                }
                switch (type.getPrimitiveType().getBuiltinTypeCode()) {
                    case 9: 
                    case 10: 
                    case 11: 
                    case 18: 
                    case 20: 
                    case 21: {
                        return true;
                    }
                }
                return false;
            }
            case 48: {
                if (type.getSimpleVariety() != 1) {
                    return false;
                }
                switch (type.getPrimitiveType().getBuiltinTypeCode()) {
                    case 14: 
                    case 16: {
                        return true;
                    }
                }
                return false;
            }
            case 49: {
                if (type.getSimpleVariety() != 1) {
                    return false;
                }
                switch (type.getPrimitiveType().getBuiltinTypeCode()) {
                    case 14: 
                    case 15: 
                    case 16: 
                    case 17: 
                    case 18: 
                    case 19: 
                    case 20: 
                    case 21: {
                        return true;
                    }
                }
                return false;
            }
            case 50: {
                if (type.getSimpleVariety() != 1) {
                    return false;
                }
                switch (type.getPrimitiveType().getBuiltinTypeCode()) {
                    case 4: 
                    case 5: {
                        return true;
                    }
                }
                return false;
            }
            case 51: {
                return type.getSimpleVariety() == 3;
            }
        }
        assert (false) : "missing case";
        return false;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private void set_primitive(int typecode, Object val) {
        SchemaType[] members = this._schemaType.getUnionConstituentTypes();
        assert (members != null);
        boolean pushed = false;
        if (this.has_store()) {
            NamespaceContext.push(new NamespaceContext(this.get_store()));
            pushed = true;
        }
        try {
            boolean validate = true;
            while (validate || !this._validateOnSet()) {
                for (int i = 0; i < members.length; ++i) {
                    XmlAnySimpleType newval;
                    if (!XmlUnionImpl.logical_overlap(members[i], typecode)) continue;
                    try {
                        newval = ((SchemaTypeImpl)members[i]).newValue(val, validate);
                    } catch (XmlValueOutOfRangeException e) {
                        continue;
                    } catch (Exception e) {
                        assert (false) : "Unexpected " + e;
                        continue;
                    }
                    this._value = newval;
                    this._textvalue = this._value.stringValue();
                    return;
                }
                if (!validate) {
                    break;
                }
                validate = false;
            }
        } finally {
            if (pushed) {
                NamespaceContext.pop();
            }
        }
        throw new XmlValueOutOfRangeException("cvc-datatype-valid.1.2.3", new Object[]{val.toString(), QNameHelper.readable(this._schemaType)});
    }

    @Override
    protected void set_boolean(boolean v) {
        this.set_primitive(3, new Boolean(v));
    }

    @Override
    protected void set_byte(byte v) {
        this.set_primitive(47, new Byte(v));
    }

    @Override
    protected void set_short(short v) {
        this.set_primitive(47, new Short(v));
    }

    @Override
    protected void set_int(int v) {
        this.set_primitive(47, new Integer(v));
    }

    @Override
    protected void set_long(long v) {
        this.set_primitive(47, new Long(v));
    }

    @Override
    protected void set_float(float v) {
        this.set_primitive(47, new Float(v));
    }

    @Override
    protected void set_double(double v) {
        this.set_primitive(47, new Double(v));
    }

    @Override
    protected void set_ByteArray(byte[] b) {
        this.set_primitive(50, b);
    }

    @Override
    protected void set_hex(byte[] b) {
        this.set_primitive(50, b);
    }

    @Override
    protected void set_b64(byte[] b) {
        this.set_primitive(50, b);
    }

    @Override
    protected void set_BigInteger(BigInteger v) {
        this.set_primitive(47, v);
    }

    @Override
    protected void set_BigDecimal(BigDecimal v) {
        this.set_primitive(47, v);
    }

    @Override
    protected void set_QName(QName v) {
        this.set_primitive(7, v);
    }

    @Override
    protected void set_Calendar(Calendar c) {
        this.set_primitive(49, c);
    }

    @Override
    protected void set_Date(Date d) {
        this.set_primitive(48, d);
    }

    @Override
    protected void set_GDate(GDateSpecification d) {
        int btc = d.getBuiltinTypeCode();
        if (btc <= 0) {
            throw new XmlValueOutOfRangeException();
        }
        this.set_primitive(btc, d);
    }

    @Override
    protected void set_GDuration(GDurationSpecification d) {
        this.set_primitive(13, d);
    }

    @Override
    protected void set_enum(StringEnumAbstractBase e) {
        this.set_primitive(12, e);
    }

    @Override
    protected void set_list(List v) {
        this.set_primitive(51, v);
    }

    protected void set_xmlfloat(XmlObject v) {
        this.set_primitive(9, v);
    }

    protected void set_xmldouble(XmlObject v) {
        this.set_primitive(10, v);
    }

    protected void set_xmldecimal(XmlObject v) {
        this.set_primitive(11, v);
    }

    protected void set_xmlduration(XmlObject v) {
        this.set_primitive(13, v);
    }

    protected void set_xmldatetime(XmlObject v) {
        this.set_primitive(14, v);
    }

    protected void set_xmltime(XmlObject v) {
        this.set_primitive(15, v);
    }

    protected void set_xmldate(XmlObject v) {
        this.set_primitive(16, v);
    }

    protected void set_xmlgyearmonth(XmlObject v) {
        this.set_primitive(17, v);
    }

    protected void set_xmlgyear(XmlObject v) {
        this.set_primitive(18, v);
    }

    protected void set_xmlgmonthday(XmlObject v) {
        this.set_primitive(19, v);
    }

    protected void set_xmlgday(XmlObject v) {
        this.set_primitive(20, v);
    }

    protected void set_xmlgmonth(XmlObject v) {
        this.set_primitive(21, v);
    }

    private static boolean check(XmlObject v, SchemaType sType) {
        XmlAnySimpleType[] vals = sType.getEnumerationValues();
        if (vals != null) {
            for (int i = 0; i < vals.length; ++i) {
                if (!vals[i].valueEquals(v)) continue;
                return true;
            }
            return false;
        }
        return true;
    }

    @Override
    protected boolean equal_to(XmlObject xmlobj) {
        return this._value.valueEquals(xmlobj);
    }

    @Override
    protected int value_hash_code() {
        return this._value.hashCode();
    }

    @Override
    protected void validate_simpleval(String lexical, ValidationContext ctx) {
        try {
            this.check_dated();
        } catch (Exception e) {
            ctx.invalid("union", new Object[]{"'" + lexical + "' does not match any of the member types for " + QNameHelper.readable(this.schemaType())});
            return;
        }
        if (this._value == null) {
            ctx.invalid("union", new Object[]{"'" + lexical + "' does not match any of the member types for " + QNameHelper.readable(this.schemaType())});
            return;
        }
        ((XmlObjectBase)((Object)this._value)).validate_simpleval(lexical, ctx);
    }
}

