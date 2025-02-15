/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.microsoft.schemas.vml.impl;

import com.microsoft.schemas.vml.CTTextPath;
import com.microsoft.schemas.vml.STTrueFalse;
import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.XmlString;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;

public class CTTextPathImpl
extends XmlComplexContentImpl
implements CTTextPath {
    private static final long serialVersionUID = 1L;
    private static final QName ID$0 = new QName("", "id");
    private static final QName STYLE$2 = new QName("", "style");
    private static final QName ON$4 = new QName("", "on");
    private static final QName FITSHAPE$6 = new QName("", "fitshape");
    private static final QName FITPATH$8 = new QName("", "fitpath");
    private static final QName TRIM$10 = new QName("", "trim");
    private static final QName XSCALE$12 = new QName("", "xscale");
    private static final QName STRING$14 = new QName("", "string");

    public CTTextPathImpl(SchemaType schemaType) {
        super(schemaType);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public String getId() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(ID$0));
            if (simpleValue == null) {
                return null;
            }
            return simpleValue.getStringValue();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public XmlString xgetId() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlString xmlString = null;
            xmlString = (XmlString)((Object)this.get_store().find_attribute_user(ID$0));
            return xmlString;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetId() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().find_attribute_user(ID$0) != null;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setId(String string) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(ID$0));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(ID$0));
            }
            simpleValue.setStringValue(string);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetId(XmlString xmlString) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlString xmlString2 = null;
            xmlString2 = (XmlString)((Object)this.get_store().find_attribute_user(ID$0));
            if (xmlString2 == null) {
                xmlString2 = (XmlString)((Object)this.get_store().add_attribute_user(ID$0));
            }
            xmlString2.set(xmlString);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetId() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_attribute(ID$0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public String getStyle() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(STYLE$2));
            if (simpleValue == null) {
                return null;
            }
            return simpleValue.getStringValue();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public XmlString xgetStyle() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlString xmlString = null;
            xmlString = (XmlString)((Object)this.get_store().find_attribute_user(STYLE$2));
            return xmlString;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetStyle() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().find_attribute_user(STYLE$2) != null;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setStyle(String string) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(STYLE$2));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(STYLE$2));
            }
            simpleValue.setStringValue(string);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetStyle(XmlString xmlString) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlString xmlString2 = null;
            xmlString2 = (XmlString)((Object)this.get_store().find_attribute_user(STYLE$2));
            if (xmlString2 == null) {
                xmlString2 = (XmlString)((Object)this.get_store().add_attribute_user(STYLE$2));
            }
            xmlString2.set(xmlString);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetStyle() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_attribute(STYLE$2);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STTrueFalse.Enum getOn() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(ON$4));
            if (simpleValue == null) {
                return null;
            }
            return (STTrueFalse.Enum)simpleValue.getEnumValue();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STTrueFalse xgetOn() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STTrueFalse sTTrueFalse = null;
            sTTrueFalse = (STTrueFalse)((Object)this.get_store().find_attribute_user(ON$4));
            return sTTrueFalse;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetOn() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().find_attribute_user(ON$4) != null;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setOn(STTrueFalse.Enum enum_) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(ON$4));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(ON$4));
            }
            simpleValue.setEnumValue(enum_);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetOn(STTrueFalse sTTrueFalse) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STTrueFalse sTTrueFalse2 = null;
            sTTrueFalse2 = (STTrueFalse)((Object)this.get_store().find_attribute_user(ON$4));
            if (sTTrueFalse2 == null) {
                sTTrueFalse2 = (STTrueFalse)((Object)this.get_store().add_attribute_user(ON$4));
            }
            sTTrueFalse2.set(sTTrueFalse);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetOn() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_attribute(ON$4);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STTrueFalse.Enum getFitshape() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(FITSHAPE$6));
            if (simpleValue == null) {
                return null;
            }
            return (STTrueFalse.Enum)simpleValue.getEnumValue();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STTrueFalse xgetFitshape() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STTrueFalse sTTrueFalse = null;
            sTTrueFalse = (STTrueFalse)((Object)this.get_store().find_attribute_user(FITSHAPE$6));
            return sTTrueFalse;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetFitshape() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().find_attribute_user(FITSHAPE$6) != null;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setFitshape(STTrueFalse.Enum enum_) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(FITSHAPE$6));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(FITSHAPE$6));
            }
            simpleValue.setEnumValue(enum_);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetFitshape(STTrueFalse sTTrueFalse) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STTrueFalse sTTrueFalse2 = null;
            sTTrueFalse2 = (STTrueFalse)((Object)this.get_store().find_attribute_user(FITSHAPE$6));
            if (sTTrueFalse2 == null) {
                sTTrueFalse2 = (STTrueFalse)((Object)this.get_store().add_attribute_user(FITSHAPE$6));
            }
            sTTrueFalse2.set(sTTrueFalse);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetFitshape() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_attribute(FITSHAPE$6);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STTrueFalse.Enum getFitpath() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(FITPATH$8));
            if (simpleValue == null) {
                return null;
            }
            return (STTrueFalse.Enum)simpleValue.getEnumValue();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STTrueFalse xgetFitpath() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STTrueFalse sTTrueFalse = null;
            sTTrueFalse = (STTrueFalse)((Object)this.get_store().find_attribute_user(FITPATH$8));
            return sTTrueFalse;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetFitpath() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().find_attribute_user(FITPATH$8) != null;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setFitpath(STTrueFalse.Enum enum_) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(FITPATH$8));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(FITPATH$8));
            }
            simpleValue.setEnumValue(enum_);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetFitpath(STTrueFalse sTTrueFalse) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STTrueFalse sTTrueFalse2 = null;
            sTTrueFalse2 = (STTrueFalse)((Object)this.get_store().find_attribute_user(FITPATH$8));
            if (sTTrueFalse2 == null) {
                sTTrueFalse2 = (STTrueFalse)((Object)this.get_store().add_attribute_user(FITPATH$8));
            }
            sTTrueFalse2.set(sTTrueFalse);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetFitpath() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_attribute(FITPATH$8);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STTrueFalse.Enum getTrim() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(TRIM$10));
            if (simpleValue == null) {
                return null;
            }
            return (STTrueFalse.Enum)simpleValue.getEnumValue();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STTrueFalse xgetTrim() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STTrueFalse sTTrueFalse = null;
            sTTrueFalse = (STTrueFalse)((Object)this.get_store().find_attribute_user(TRIM$10));
            return sTTrueFalse;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetTrim() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().find_attribute_user(TRIM$10) != null;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setTrim(STTrueFalse.Enum enum_) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(TRIM$10));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(TRIM$10));
            }
            simpleValue.setEnumValue(enum_);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetTrim(STTrueFalse sTTrueFalse) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STTrueFalse sTTrueFalse2 = null;
            sTTrueFalse2 = (STTrueFalse)((Object)this.get_store().find_attribute_user(TRIM$10));
            if (sTTrueFalse2 == null) {
                sTTrueFalse2 = (STTrueFalse)((Object)this.get_store().add_attribute_user(TRIM$10));
            }
            sTTrueFalse2.set(sTTrueFalse);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetTrim() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_attribute(TRIM$10);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STTrueFalse.Enum getXscale() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(XSCALE$12));
            if (simpleValue == null) {
                return null;
            }
            return (STTrueFalse.Enum)simpleValue.getEnumValue();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STTrueFalse xgetXscale() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STTrueFalse sTTrueFalse = null;
            sTTrueFalse = (STTrueFalse)((Object)this.get_store().find_attribute_user(XSCALE$12));
            return sTTrueFalse;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetXscale() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().find_attribute_user(XSCALE$12) != null;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setXscale(STTrueFalse.Enum enum_) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(XSCALE$12));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(XSCALE$12));
            }
            simpleValue.setEnumValue(enum_);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetXscale(STTrueFalse sTTrueFalse) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STTrueFalse sTTrueFalse2 = null;
            sTTrueFalse2 = (STTrueFalse)((Object)this.get_store().find_attribute_user(XSCALE$12));
            if (sTTrueFalse2 == null) {
                sTTrueFalse2 = (STTrueFalse)((Object)this.get_store().add_attribute_user(XSCALE$12));
            }
            sTTrueFalse2.set(sTTrueFalse);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetXscale() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_attribute(XSCALE$12);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public String getString() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(STRING$14));
            if (simpleValue == null) {
                return null;
            }
            return simpleValue.getStringValue();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public XmlString xgetString() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlString xmlString = null;
            xmlString = (XmlString)((Object)this.get_store().find_attribute_user(STRING$14));
            return xmlString;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetString() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().find_attribute_user(STRING$14) != null;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setString(String string) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(STRING$14));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(STRING$14));
            }
            simpleValue.setStringValue(string);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetString(XmlString xmlString) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlString xmlString2 = null;
            xmlString2 = (XmlString)((Object)this.get_store().find_attribute_user(STRING$14));
            if (xmlString2 == null) {
                xmlString2 = (XmlString)((Object)this.get_store().add_attribute_user(STRING$14));
            }
            xmlString2.set(xmlString);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetString() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_attribute(STRING$14);
        }
    }
}

