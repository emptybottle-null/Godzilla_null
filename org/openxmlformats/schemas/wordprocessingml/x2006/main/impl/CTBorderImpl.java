/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  org.openxmlformats.schemas.wordprocessingml.x2006.main.STUcharHexNumber
 */
package org.openxmlformats.schemas.wordprocessingml.x2006.main.impl;

import java.math.BigInteger;
import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTBorder;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STBorder;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STEighthPointMeasure;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STHexColor;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STOnOff;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STPointMeasure;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STThemeColor;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STUcharHexNumber;

public class CTBorderImpl
extends XmlComplexContentImpl
implements CTBorder {
    private static final long serialVersionUID = 1L;
    private static final QName VAL$0 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "val");
    private static final QName COLOR$2 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "color");
    private static final QName THEMECOLOR$4 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "themeColor");
    private static final QName THEMETINT$6 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "themeTint");
    private static final QName THEMESHADE$8 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "themeShade");
    private static final QName SZ$10 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "sz");
    private static final QName SPACE$12 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "space");
    private static final QName SHADOW$14 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "shadow");
    private static final QName FRAME$16 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "frame");

    public CTBorderImpl(SchemaType schemaType) {
        super(schemaType);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STBorder.Enum getVal() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(VAL$0));
            if (simpleValue == null) {
                return null;
            }
            return (STBorder.Enum)simpleValue.getEnumValue();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STBorder xgetVal() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STBorder sTBorder = null;
            sTBorder = (STBorder)((Object)this.get_store().find_attribute_user(VAL$0));
            return sTBorder;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setVal(STBorder.Enum enum_) {
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
    public void xsetVal(STBorder sTBorder) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STBorder sTBorder2 = null;
            sTBorder2 = (STBorder)((Object)this.get_store().find_attribute_user(VAL$0));
            if (sTBorder2 == null) {
                sTBorder2 = (STBorder)((Object)this.get_store().add_attribute_user(VAL$0));
            }
            sTBorder2.set(sTBorder);
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
    public BigInteger getSz() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(SZ$10));
            if (simpleValue == null) {
                return null;
            }
            return simpleValue.getBigIntegerValue();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STEighthPointMeasure xgetSz() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STEighthPointMeasure sTEighthPointMeasure = null;
            sTEighthPointMeasure = (STEighthPointMeasure)((Object)this.get_store().find_attribute_user(SZ$10));
            return sTEighthPointMeasure;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetSz() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().find_attribute_user(SZ$10) != null;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setSz(BigInteger bigInteger) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(SZ$10));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(SZ$10));
            }
            simpleValue.setBigIntegerValue(bigInteger);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetSz(STEighthPointMeasure sTEighthPointMeasure) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STEighthPointMeasure sTEighthPointMeasure2 = null;
            sTEighthPointMeasure2 = (STEighthPointMeasure)((Object)this.get_store().find_attribute_user(SZ$10));
            if (sTEighthPointMeasure2 == null) {
                sTEighthPointMeasure2 = (STEighthPointMeasure)((Object)this.get_store().add_attribute_user(SZ$10));
            }
            sTEighthPointMeasure2.set(sTEighthPointMeasure);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetSz() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_attribute(SZ$10);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public BigInteger getSpace() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(SPACE$12));
            if (simpleValue == null) {
                return null;
            }
            return simpleValue.getBigIntegerValue();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STPointMeasure xgetSpace() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STPointMeasure sTPointMeasure = null;
            sTPointMeasure = (STPointMeasure)((Object)this.get_store().find_attribute_user(SPACE$12));
            return sTPointMeasure;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetSpace() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().find_attribute_user(SPACE$12) != null;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setSpace(BigInteger bigInteger) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(SPACE$12));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(SPACE$12));
            }
            simpleValue.setBigIntegerValue(bigInteger);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetSpace(STPointMeasure sTPointMeasure) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STPointMeasure sTPointMeasure2 = null;
            sTPointMeasure2 = (STPointMeasure)((Object)this.get_store().find_attribute_user(SPACE$12));
            if (sTPointMeasure2 == null) {
                sTPointMeasure2 = (STPointMeasure)((Object)this.get_store().add_attribute_user(SPACE$12));
            }
            sTPointMeasure2.set(sTPointMeasure);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetSpace() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_attribute(SPACE$12);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STOnOff.Enum getShadow() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(SHADOW$14));
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
    public STOnOff xgetShadow() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STOnOff sTOnOff = null;
            sTOnOff = (STOnOff)((Object)this.get_store().find_attribute_user(SHADOW$14));
            return sTOnOff;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetShadow() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().find_attribute_user(SHADOW$14) != null;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setShadow(STOnOff.Enum enum_) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(SHADOW$14));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(SHADOW$14));
            }
            simpleValue.setEnumValue(enum_);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetShadow(STOnOff sTOnOff) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STOnOff sTOnOff2 = null;
            sTOnOff2 = (STOnOff)((Object)this.get_store().find_attribute_user(SHADOW$14));
            if (sTOnOff2 == null) {
                sTOnOff2 = (STOnOff)((Object)this.get_store().add_attribute_user(SHADOW$14));
            }
            sTOnOff2.set(sTOnOff);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetShadow() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_attribute(SHADOW$14);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STOnOff.Enum getFrame() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(FRAME$16));
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
    public STOnOff xgetFrame() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STOnOff sTOnOff = null;
            sTOnOff = (STOnOff)((Object)this.get_store().find_attribute_user(FRAME$16));
            return sTOnOff;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetFrame() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().find_attribute_user(FRAME$16) != null;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setFrame(STOnOff.Enum enum_) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(FRAME$16));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(FRAME$16));
            }
            simpleValue.setEnumValue(enum_);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetFrame(STOnOff sTOnOff) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STOnOff sTOnOff2 = null;
            sTOnOff2 = (STOnOff)((Object)this.get_store().find_attribute_user(FRAME$16));
            if (sTOnOff2 == null) {
                sTOnOff2 = (STOnOff)((Object)this.get_store().add_attribute_user(FRAME$16));
            }
            sTOnOff2.set(sTOnOff);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetFrame() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_attribute(FRAME$16);
        }
    }
}

