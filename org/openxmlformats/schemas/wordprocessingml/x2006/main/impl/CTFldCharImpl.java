/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTrackChangeNumbering
 */
package org.openxmlformats.schemas.wordprocessingml.x2006.main.impl;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFFData;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFldChar;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTText;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTrackChangeNumbering;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STFldCharType;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STOnOff;

public class CTFldCharImpl
extends XmlComplexContentImpl
implements CTFldChar {
    private static final long serialVersionUID = 1L;
    private static final QName FLDDATA$0 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "fldData");
    private static final QName FFDATA$2 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "ffData");
    private static final QName NUMBERINGCHANGE$4 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "numberingChange");
    private static final QName FLDCHARTYPE$6 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "fldCharType");
    private static final QName FLDLOCK$8 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "fldLock");
    private static final QName DIRTY$10 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "dirty");

    public CTFldCharImpl(SchemaType schemaType) {
        super(schemaType);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTText getFldData() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTText cTText = null;
            cTText = (CTText)((Object)this.get_store().find_element_user(FLDDATA$0, 0));
            if (cTText == null) {
                return null;
            }
            return cTText;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetFldData() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(FLDDATA$0) != 0;
        }
    }

    @Override
    public void setFldData(CTText cTText) {
        this.generatedSetterHelperImpl(cTText, FLDDATA$0, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTText addNewFldData() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTText cTText = null;
            cTText = (CTText)((Object)this.get_store().add_element_user(FLDDATA$0));
            return cTText;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetFldData() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(FLDDATA$0, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTFFData getFfData() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTFFData cTFFData = null;
            cTFFData = (CTFFData)((Object)this.get_store().find_element_user(FFDATA$2, 0));
            if (cTFFData == null) {
                return null;
            }
            return cTFFData;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetFfData() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(FFDATA$2) != 0;
        }
    }

    @Override
    public void setFfData(CTFFData cTFFData) {
        this.generatedSetterHelperImpl(cTFFData, FFDATA$2, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTFFData addNewFfData() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTFFData cTFFData = null;
            cTFFData = (CTFFData)((Object)this.get_store().add_element_user(FFDATA$2));
            return cTFFData;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetFfData() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(FFDATA$2, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTTrackChangeNumbering getNumberingChange() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTTrackChangeNumbering cTTrackChangeNumbering = null;
            cTTrackChangeNumbering = (CTTrackChangeNumbering)this.get_store().find_element_user(NUMBERINGCHANGE$4, 0);
            if (cTTrackChangeNumbering == null) {
                return null;
            }
            return cTTrackChangeNumbering;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetNumberingChange() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(NUMBERINGCHANGE$4) != 0;
        }
    }

    @Override
    public void setNumberingChange(CTTrackChangeNumbering cTTrackChangeNumbering) {
        this.generatedSetterHelperImpl((XmlObject)cTTrackChangeNumbering, NUMBERINGCHANGE$4, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTTrackChangeNumbering addNewNumberingChange() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTTrackChangeNumbering cTTrackChangeNumbering = null;
            cTTrackChangeNumbering = (CTTrackChangeNumbering)this.get_store().add_element_user(NUMBERINGCHANGE$4);
            return cTTrackChangeNumbering;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetNumberingChange() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(NUMBERINGCHANGE$4, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STFldCharType.Enum getFldCharType() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(FLDCHARTYPE$6));
            if (simpleValue == null) {
                return null;
            }
            return (STFldCharType.Enum)simpleValue.getEnumValue();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STFldCharType xgetFldCharType() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STFldCharType sTFldCharType = null;
            sTFldCharType = (STFldCharType)((Object)this.get_store().find_attribute_user(FLDCHARTYPE$6));
            return sTFldCharType;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setFldCharType(STFldCharType.Enum enum_) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(FLDCHARTYPE$6));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(FLDCHARTYPE$6));
            }
            simpleValue.setEnumValue(enum_);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetFldCharType(STFldCharType sTFldCharType) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STFldCharType sTFldCharType2 = null;
            sTFldCharType2 = (STFldCharType)((Object)this.get_store().find_attribute_user(FLDCHARTYPE$6));
            if (sTFldCharType2 == null) {
                sTFldCharType2 = (STFldCharType)((Object)this.get_store().add_attribute_user(FLDCHARTYPE$6));
            }
            sTFldCharType2.set(sTFldCharType);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STOnOff.Enum getFldLock() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(FLDLOCK$8));
            if (simpleValue == null) {
                return null;
            }
            return (STOnOff.Enum)simpleValue.getEnumValue();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STOnOff xgetFldLock() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STOnOff sTOnOff = null;
            sTOnOff = (STOnOff)((Object)this.get_store().find_attribute_user(FLDLOCK$8));
            return sTOnOff;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetFldLock() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().find_attribute_user(FLDLOCK$8) != null;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setFldLock(STOnOff.Enum enum_) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(FLDLOCK$8));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(FLDLOCK$8));
            }
            simpleValue.setEnumValue(enum_);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetFldLock(STOnOff sTOnOff) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STOnOff sTOnOff2 = null;
            sTOnOff2 = (STOnOff)((Object)this.get_store().find_attribute_user(FLDLOCK$8));
            if (sTOnOff2 == null) {
                sTOnOff2 = (STOnOff)((Object)this.get_store().add_attribute_user(FLDLOCK$8));
            }
            sTOnOff2.set(sTOnOff);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetFldLock() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_attribute(FLDLOCK$8);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STOnOff.Enum getDirty() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(DIRTY$10));
            if (simpleValue == null) {
                return null;
            }
            return (STOnOff.Enum)simpleValue.getEnumValue();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STOnOff xgetDirty() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STOnOff sTOnOff = null;
            sTOnOff = (STOnOff)((Object)this.get_store().find_attribute_user(DIRTY$10));
            return sTOnOff;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetDirty() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().find_attribute_user(DIRTY$10) != null;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setDirty(STOnOff.Enum enum_) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(DIRTY$10));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(DIRTY$10));
            }
            simpleValue.setEnumValue(enum_);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetDirty(STOnOff sTOnOff) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STOnOff sTOnOff2 = null;
            sTOnOff2 = (STOnOff)((Object)this.get_store().find_attribute_user(DIRTY$10));
            if (sTOnOff2 == null) {
                sTOnOff2 = (STOnOff)((Object)this.get_store().add_attribute_user(DIRTY$10));
            }
            sTOnOff2.set(sTOnOff);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetDirty() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_attribute(DIRTY$10);
        }
    }
}

