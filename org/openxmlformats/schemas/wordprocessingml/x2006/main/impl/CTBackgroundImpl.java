/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  org.openxmlformats.schemas.wordprocessingml.x2006.main.STUcharHexNumber
 */
package org.openxmlformats.schemas.wordprocessingml.x2006.main.impl;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.XmlObject;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTBackground;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STHexColor;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STThemeColor;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STUcharHexNumber;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTPictureBaseImpl;

public class CTBackgroundImpl
extends CTPictureBaseImpl
implements CTBackground {
    private static final long serialVersionUID = 1L;
    private static final QName COLOR$0 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "color");
    private static final QName THEMECOLOR$2 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "themeColor");
    private static final QName THEMETINT$4 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "themeTint");
    private static final QName THEMESHADE$6 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "themeShade");

    public CTBackgroundImpl(SchemaType schemaType) {
        super(schemaType);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public Object getColor() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(COLOR$0));
            if (simpleValue == null) {
                return null;
            }
            return simpleValue.getObjectValue();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STHexColor xgetColor() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STHexColor sTHexColor = null;
            sTHexColor = (STHexColor)((Object)this.get_store().find_attribute_user(COLOR$0));
            return sTHexColor;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetColor() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().find_attribute_user(COLOR$0) != null;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setColor(Object object) {
        Object object2 = this.monitor();
        synchronized (object2) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(COLOR$0));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(COLOR$0));
            }
            simpleValue.setObjectValue(object);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetColor(STHexColor sTHexColor) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STHexColor sTHexColor2 = null;
            sTHexColor2 = (STHexColor)((Object)this.get_store().find_attribute_user(COLOR$0));
            if (sTHexColor2 == null) {
                sTHexColor2 = (STHexColor)((Object)this.get_store().add_attribute_user(COLOR$0));
            }
            sTHexColor2.set(sTHexColor);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetColor() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_attribute(COLOR$0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STThemeColor.Enum getThemeColor() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(THEMECOLOR$2));
            if (simpleValue == null) {
                return null;
            }
            return (STThemeColor.Enum)simpleValue.getEnumValue();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STThemeColor xgetThemeColor() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STThemeColor sTThemeColor = null;
            sTThemeColor = (STThemeColor)((Object)this.get_store().find_attribute_user(THEMECOLOR$2));
            return sTThemeColor;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetThemeColor() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().find_attribute_user(THEMECOLOR$2) != null;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setThemeColor(STThemeColor.Enum enum_) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(THEMECOLOR$2));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(THEMECOLOR$2));
            }
            simpleValue.setEnumValue(enum_);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetThemeColor(STThemeColor sTThemeColor) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STThemeColor sTThemeColor2 = null;
            sTThemeColor2 = (STThemeColor)((Object)this.get_store().find_attribute_user(THEMECOLOR$2));
            if (sTThemeColor2 == null) {
                sTThemeColor2 = (STThemeColor)((Object)this.get_store().add_attribute_user(THEMECOLOR$2));
            }
            sTThemeColor2.set(sTThemeColor);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetThemeColor() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_attribute(THEMECOLOR$2);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public byte[] getThemeTint() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(THEMETINT$4));
            if (simpleValue == null) {
                return null;
            }
            return simpleValue.getByteArrayValue();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STUcharHexNumber xgetThemeTint() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STUcharHexNumber sTUcharHexNumber = null;
            sTUcharHexNumber = (STUcharHexNumber)this.get_store().find_attribute_user(THEMETINT$4);
            return sTUcharHexNumber;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetThemeTint() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().find_attribute_user(THEMETINT$4) != null;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setThemeTint(byte[] byArray) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(THEMETINT$4));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(THEMETINT$4));
            }
            simpleValue.setByteArrayValue(byArray);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetThemeTint(STUcharHexNumber sTUcharHexNumber) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STUcharHexNumber sTUcharHexNumber2 = null;
            sTUcharHexNumber2 = (STUcharHexNumber)this.get_store().find_attribute_user(THEMETINT$4);
            if (sTUcharHexNumber2 == null) {
                sTUcharHexNumber2 = (STUcharHexNumber)this.get_store().add_attribute_user(THEMETINT$4);
            }
            sTUcharHexNumber2.set((XmlObject)sTUcharHexNumber);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetThemeTint() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_attribute(THEMETINT$4);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public byte[] getThemeShade() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(THEMESHADE$6));
            if (simpleValue == null) {
                return null;
            }
            return simpleValue.getByteArrayValue();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STUcharHexNumber xgetThemeShade() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STUcharHexNumber sTUcharHexNumber = null;
            sTUcharHexNumber = (STUcharHexNumber)this.get_store().find_attribute_user(THEMESHADE$6);
            return sTUcharHexNumber;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetThemeShade() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().find_attribute_user(THEMESHADE$6) != null;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setThemeShade(byte[] byArray) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(THEMESHADE$6));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(THEMESHADE$6));
            }
            simpleValue.setByteArrayValue(byArray);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetThemeShade(STUcharHexNumber sTUcharHexNumber) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STUcharHexNumber sTUcharHexNumber2 = null;
            sTUcharHexNumber2 = (STUcharHexNumber)this.get_store().find_attribute_user(THEMESHADE$6);
            if (sTUcharHexNumber2 == null) {
                sTUcharHexNumber2 = (STUcharHexNumber)this.get_store().add_attribute_user(THEMESHADE$6);
            }
            sTUcharHexNumber2.set((XmlObject)sTUcharHexNumber);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetThemeShade() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_attribute(THEMESHADE$6);
        }
    }
}

