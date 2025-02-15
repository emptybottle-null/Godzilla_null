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
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTShd;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STHexColor;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STShd;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STThemeColor;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STUcharHexNumber;

public class CTShdImpl
extends XmlComplexContentImpl
implements CTShd {
    private static final long serialVersionUID = 1L;
    private static final QName VAL$0 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "val");
    private static final QName COLOR$2 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "color");
    private static final QName THEMECOLOR$4 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "themeColor");
    private static final QName THEMETINT$6 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "themeTint");
    private static final QName THEMESHADE$8 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "themeShade");
    private static final QName FILL$10 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "fill");
    private static final QName THEMEFILL$12 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "themeFill");
    private static final QName THEMEFILLTINT$14 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "themeFillTint");
    private static final QName THEMEFILLSHADE$16 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "themeFillShade");

    public CTShdImpl(SchemaType schemaType) {
        super(schemaType);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STShd.Enum getVal() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(VAL$0));
            if (simpleValue == null) {
                return null;
            }
            return (STShd.Enum)simpleValue.getEnumValue();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STShd xgetVal() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STShd sTShd = null;
            sTShd = (STShd)((Object)this.get_store().find_attribute_user(VAL$0));
            return sTShd;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setVal(STShd.Enum enum_) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(VAL$0));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(VAL$0));
            }
            simpleValue.setEnumValue(enum_);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetVal(STShd sTShd) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STShd sTShd2 = null;
            sTShd2 = (STShd)((Object)this.get_store().find_attribute_user(VAL$0));
            if (sTShd2 == null) {
                sTShd2 = (STShd)((Object)this.get_store().add_attribute_user(VAL$0));
            }
            sTShd2.set(sTShd);
        }
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
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(COLOR$2));
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
            sTHexColor = (STHexColor)((Object)this.get_store().find_attribute_user(COLOR$2));
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
            return this.get_store().find_attribute_user(COLOR$2) != null;
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
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(COLOR$2));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(COLOR$2));
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
            sTHexColor2 = (STHexColor)((Object)this.get_store().find_attribute_user(COLOR$2));
            if (sTHexColor2 == null) {
                sTHexColor2 = (STHexColor)((Object)this.get_store().add_attribute_user(COLOR$2));
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
            this.get_store().remove_attribute(COLOR$2);
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
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(THEMECOLOR$4));
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
            sTThemeColor = (STThemeColor)((Object)this.get_store().find_attribute_user(THEMECOLOR$4));
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
            return this.get_store().find_attribute_user(THEMECOLOR$4) != null;
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
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(THEMECOLOR$4));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(THEMECOLOR$4));
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
            sTThemeColor2 = (STThemeColor)((Object)this.get_store().find_attribute_user(THEMECOLOR$4));
            if (sTThemeColor2 == null) {
                sTThemeColor2 = (STThemeColor)((Object)this.get_store().add_attribute_user(THEMECOLOR$4));
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
            this.get_store().remove_attribute(THEMECOLOR$4);
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
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(THEMETINT$6));
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
            sTUcharHexNumber = (STUcharHexNumber)this.get_store().find_attribute_user(THEMETINT$6);
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
            return this.get_store().find_attribute_user(THEMETINT$6) != null;
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
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(THEMETINT$6));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(THEMETINT$6));
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
            sTUcharHexNumber2 = (STUcharHexNumber)this.get_store().find_attribute_user(THEMETINT$6);
            if (sTUcharHexNumber2 == null) {
                sTUcharHexNumber2 = (STUcharHexNumber)this.get_store().add_attribute_user(THEMETINT$6);
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
            this.get_store().remove_attribute(THEMETINT$6);
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
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(THEMESHADE$8));
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
            sTUcharHexNumber = (STUcharHexNumber)this.get_store().find_attribute_user(THEMESHADE$8);
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
            return this.get_store().find_attribute_user(THEMESHADE$8) != null;
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
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(THEMESHADE$8));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(THEMESHADE$8));
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
            sTUcharHexNumber2 = (STUcharHexNumber)this.get_store().find_attribute_user(THEMESHADE$8);
            if (sTUcharHexNumber2 == null) {
                sTUcharHexNumber2 = (STUcharHexNumber)this.get_store().add_attribute_user(THEMESHADE$8);
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
            this.get_store().remove_attribute(THEMESHADE$8);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public Object getFill() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(FILL$10));
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
    public STHexColor xgetFill() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STHexColor sTHexColor = null;
            sTHexColor = (STHexColor)((Object)this.get_store().find_attribute_user(FILL$10));
            return sTHexColor;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetFill() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().find_attribute_user(FILL$10) != null;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setFill(Object object) {
        Object object2 = this.monitor();
        synchronized (object2) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(FILL$10));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(FILL$10));
            }
            simpleValue.setObjectValue(object);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetFill(STHexColor sTHexColor) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STHexColor sTHexColor2 = null;
            sTHexColor2 = (STHexColor)((Object)this.get_store().find_attribute_user(FILL$10));
            if (sTHexColor2 == null) {
                sTHexColor2 = (STHexColor)((Object)this.get_store().add_attribute_user(FILL$10));
            }
            sTHexColor2.set(sTHexColor);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetFill() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_attribute(FILL$10);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STThemeColor.Enum getThemeFill() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(THEMEFILL$12));
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
    public STThemeColor xgetThemeFill() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STThemeColor sTThemeColor = null;
            sTThemeColor = (STThemeColor)((Object)this.get_store().find_attribute_user(THEMEFILL$12));
            return sTThemeColor;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetThemeFill() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().find_attribute_user(THEMEFILL$12) != null;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setThemeFill(STThemeColor.Enum enum_) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(THEMEFILL$12));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(THEMEFILL$12));
            }
            simpleValue.setEnumValue(enum_);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetThemeFill(STThemeColor sTThemeColor) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STThemeColor sTThemeColor2 = null;
            sTThemeColor2 = (STThemeColor)((Object)this.get_store().find_attribute_user(THEMEFILL$12));
            if (sTThemeColor2 == null) {
                sTThemeColor2 = (STThemeColor)((Object)this.get_store().add_attribute_user(THEMEFILL$12));
            }
            sTThemeColor2.set(sTThemeColor);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetThemeFill() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_attribute(THEMEFILL$12);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public byte[] getThemeFillTint() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(THEMEFILLTINT$14));
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
    public STUcharHexNumber xgetThemeFillTint() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STUcharHexNumber sTUcharHexNumber = null;
            sTUcharHexNumber = (STUcharHexNumber)this.get_store().find_attribute_user(THEMEFILLTINT$14);
            return sTUcharHexNumber;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetThemeFillTint() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().find_attribute_user(THEMEFILLTINT$14) != null;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setThemeFillTint(byte[] byArray) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(THEMEFILLTINT$14));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(THEMEFILLTINT$14));
            }
            simpleValue.setByteArrayValue(byArray);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetThemeFillTint(STUcharHexNumber sTUcharHexNumber) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STUcharHexNumber sTUcharHexNumber2 = null;
            sTUcharHexNumber2 = (STUcharHexNumber)this.get_store().find_attribute_user(THEMEFILLTINT$14);
            if (sTUcharHexNumber2 == null) {
                sTUcharHexNumber2 = (STUcharHexNumber)this.get_store().add_attribute_user(THEMEFILLTINT$14);
            }
            sTUcharHexNumber2.set((XmlObject)sTUcharHexNumber);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetThemeFillTint() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_attribute(THEMEFILLTINT$14);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public byte[] getThemeFillShade() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(THEMEFILLSHADE$16));
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
    public STUcharHexNumber xgetThemeFillShade() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STUcharHexNumber sTUcharHexNumber = null;
            sTUcharHexNumber = (STUcharHexNumber)this.get_store().find_attribute_user(THEMEFILLSHADE$16);
            return sTUcharHexNumber;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetThemeFillShade() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().find_attribute_user(THEMEFILLSHADE$16) != null;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setThemeFillShade(byte[] byArray) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(THEMEFILLSHADE$16));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(THEMEFILLSHADE$16));
            }
            simpleValue.setByteArrayValue(byArray);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetThemeFillShade(STUcharHexNumber sTUcharHexNumber) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STUcharHexNumber sTUcharHexNumber2 = null;
            sTUcharHexNumber2 = (STUcharHexNumber)this.get_store().find_attribute_user(THEMEFILLSHADE$16);
            if (sTUcharHexNumber2 == null) {
                sTUcharHexNumber2 = (STUcharHexNumber)this.get_store().add_attribute_user(THEMEFILLSHADE$16);
            }
            sTUcharHexNumber2.set((XmlObject)sTUcharHexNumber);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetThemeFillShade() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_attribute(THEMEFILLSHADE$16);
        }
    }
}

