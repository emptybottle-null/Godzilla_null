/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  org.openxmlformats.schemas.spreadsheetml.x2006.main.STPhoneticAlignment
 *  org.openxmlformats.schemas.spreadsheetml.x2006.main.STPhoneticType
 */
package org.openxmlformats.schemas.spreadsheetml.x2006.main.impl;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPhoneticPr;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.STFontId;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.STPhoneticAlignment;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.STPhoneticType;

public class CTPhoneticPrImpl
extends XmlComplexContentImpl
implements CTPhoneticPr {
    private static final long serialVersionUID = 1L;
    private static final QName FONTID$0 = new QName("", "fontId");
    private static final QName TYPE$2 = new QName("", "type");
    private static final QName ALIGNMENT$4 = new QName("", "alignment");

    public CTPhoneticPrImpl(SchemaType schemaType) {
        super(schemaType);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public long getFontId() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(FONTID$0));
            if (simpleValue == null) {
                return 0L;
            }
            return simpleValue.getLongValue();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STFontId xgetFontId() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STFontId sTFontId = null;
            sTFontId = (STFontId)((Object)this.get_store().find_attribute_user(FONTID$0));
            return sTFontId;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setFontId(long l) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(FONTID$0));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(FONTID$0));
            }
            simpleValue.setLongValue(l);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetFontId(STFontId sTFontId) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STFontId sTFontId2 = null;
            sTFontId2 = (STFontId)((Object)this.get_store().find_attribute_user(FONTID$0));
            if (sTFontId2 == null) {
                sTFontId2 = (STFontId)((Object)this.get_store().add_attribute_user(FONTID$0));
            }
            sTFontId2.set(sTFontId);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STPhoneticType.Enum getType() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(TYPE$2));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_default_attribute_value(TYPE$2));
            }
            if (simpleValue == null) {
                return null;
            }
            return (STPhoneticType.Enum)simpleValue.getEnumValue();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STPhoneticType xgetType() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STPhoneticType sTPhoneticType = null;
            sTPhoneticType = (STPhoneticType)this.get_store().find_attribute_user(TYPE$2);
            if (sTPhoneticType == null) {
                sTPhoneticType = (STPhoneticType)this.get_default_attribute_value(TYPE$2);
            }
            return sTPhoneticType;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetType() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().find_attribute_user(TYPE$2) != null;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setType(STPhoneticType.Enum enum_) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(TYPE$2));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(TYPE$2));
            }
            simpleValue.setEnumValue(enum_);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetType(STPhoneticType sTPhoneticType) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STPhoneticType sTPhoneticType2 = null;
            sTPhoneticType2 = (STPhoneticType)this.get_store().find_attribute_user(TYPE$2);
            if (sTPhoneticType2 == null) {
                sTPhoneticType2 = (STPhoneticType)this.get_store().add_attribute_user(TYPE$2);
            }
            sTPhoneticType2.set((XmlObject)sTPhoneticType);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetType() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_attribute(TYPE$2);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STPhoneticAlignment.Enum getAlignment() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(ALIGNMENT$4));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_default_attribute_value(ALIGNMENT$4));
            }
            if (simpleValue == null) {
                return null;
            }
            return (STPhoneticAlignment.Enum)simpleValue.getEnumValue();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STPhoneticAlignment xgetAlignment() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STPhoneticAlignment sTPhoneticAlignment = null;
            sTPhoneticAlignment = (STPhoneticAlignment)this.get_store().find_attribute_user(ALIGNMENT$4);
            if (sTPhoneticAlignment == null) {
                sTPhoneticAlignment = (STPhoneticAlignment)this.get_default_attribute_value(ALIGNMENT$4);
            }
            return sTPhoneticAlignment;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetAlignment() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().find_attribute_user(ALIGNMENT$4) != null;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setAlignment(STPhoneticAlignment.Enum enum_) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(ALIGNMENT$4));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(ALIGNMENT$4));
            }
            simpleValue.setEnumValue(enum_);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetAlignment(STPhoneticAlignment sTPhoneticAlignment) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STPhoneticAlignment sTPhoneticAlignment2 = null;
            sTPhoneticAlignment2 = (STPhoneticAlignment)this.get_store().find_attribute_user(ALIGNMENT$4);
            if (sTPhoneticAlignment2 == null) {
                sTPhoneticAlignment2 = (STPhoneticAlignment)this.get_store().add_attribute_user(ALIGNMENT$4);
            }
            sTPhoneticAlignment2.set((XmlObject)sTPhoneticAlignment);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetAlignment() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_attribute(ALIGNMENT$4);
        }
    }
}

