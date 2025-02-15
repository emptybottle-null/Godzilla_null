/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  org.openxmlformats.schemas.wordprocessingml.x2006.main.STHint
 */
package org.openxmlformats.schemas.wordprocessingml.x2006.main.impl;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFonts;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STHint;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STString;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STTheme;

public class CTFontsImpl
extends XmlComplexContentImpl
implements CTFonts {
    private static final long serialVersionUID = 1L;
    private static final QName HINT$0 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "hint");
    private static final QName ASCII$2 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "ascii");
    private static final QName HANSI$4 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "hAnsi");
    private static final QName EASTASIA$6 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "eastAsia");
    private static final QName CS$8 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "cs");
    private static final QName ASCIITHEME$10 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "asciiTheme");
    private static final QName HANSITHEME$12 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "hAnsiTheme");
    private static final QName EASTASIATHEME$14 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "eastAsiaTheme");
    private static final QName CSTHEME$16 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "cstheme");

    public CTFontsImpl(SchemaType schemaType) {
        super(schemaType);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STHint.Enum getHint() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(HINT$0));
            if (simpleValue == null) {
                return null;
            }
            return (STHint.Enum)simpleValue.getEnumValue();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STHint xgetHint() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STHint sTHint = null;
            sTHint = (STHint)this.get_store().find_attribute_user(HINT$0);
            return sTHint;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetHint() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().find_attribute_user(HINT$0) != null;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setHint(STHint.Enum enum_) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(HINT$0));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(HINT$0));
            }
            simpleValue.setEnumValue(enum_);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetHint(STHint sTHint) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STHint sTHint2 = null;
            sTHint2 = (STHint)this.get_store().find_attribute_user(HINT$0);
            if (sTHint2 == null) {
                sTHint2 = (STHint)this.get_store().add_attribute_user(HINT$0);
            }
            sTHint2.set((XmlObject)sTHint);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetHint() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_attribute(HINT$0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public String getAscii() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(ASCII$2));
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
    public STString xgetAscii() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STString sTString = null;
            sTString = (STString)((Object)this.get_store().find_attribute_user(ASCII$2));
            return sTString;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetAscii() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().find_attribute_user(ASCII$2) != null;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setAscii(String string) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(ASCII$2));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(ASCII$2));
            }
            simpleValue.setStringValue(string);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetAscii(STString sTString) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STString sTString2 = null;
            sTString2 = (STString)((Object)this.get_store().find_attribute_user(ASCII$2));
            if (sTString2 == null) {
                sTString2 = (STString)((Object)this.get_store().add_attribute_user(ASCII$2));
            }
            sTString2.set(sTString);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetAscii() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_attribute(ASCII$2);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public String getHAnsi() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(HANSI$4));
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
    public STString xgetHAnsi() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STString sTString = null;
            sTString = (STString)((Object)this.get_store().find_attribute_user(HANSI$4));
            return sTString;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetHAnsi() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().find_attribute_user(HANSI$4) != null;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setHAnsi(String string) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(HANSI$4));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(HANSI$4));
            }
            simpleValue.setStringValue(string);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetHAnsi(STString sTString) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STString sTString2 = null;
            sTString2 = (STString)((Object)this.get_store().find_attribute_user(HANSI$4));
            if (sTString2 == null) {
                sTString2 = (STString)((Object)this.get_store().add_attribute_user(HANSI$4));
            }
            sTString2.set(sTString);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetHAnsi() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_attribute(HANSI$4);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public String getEastAsia() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(EASTASIA$6));
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
    public STString xgetEastAsia() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STString sTString = null;
            sTString = (STString)((Object)this.get_store().find_attribute_user(EASTASIA$6));
            return sTString;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetEastAsia() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().find_attribute_user(EASTASIA$6) != null;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setEastAsia(String string) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(EASTASIA$6));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(EASTASIA$6));
            }
            simpleValue.setStringValue(string);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetEastAsia(STString sTString) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STString sTString2 = null;
            sTString2 = (STString)((Object)this.get_store().find_attribute_user(EASTASIA$6));
            if (sTString2 == null) {
                sTString2 = (STString)((Object)this.get_store().add_attribute_user(EASTASIA$6));
            }
            sTString2.set(sTString);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetEastAsia() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_attribute(EASTASIA$6);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public String getCs() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(CS$8));
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
    public STString xgetCs() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STString sTString = null;
            sTString = (STString)((Object)this.get_store().find_attribute_user(CS$8));
            return sTString;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetCs() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().find_attribute_user(CS$8) != null;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setCs(String string) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(CS$8));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(CS$8));
            }
            simpleValue.setStringValue(string);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetCs(STString sTString) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STString sTString2 = null;
            sTString2 = (STString)((Object)this.get_store().find_attribute_user(CS$8));
            if (sTString2 == null) {
                sTString2 = (STString)((Object)this.get_store().add_attribute_user(CS$8));
            }
            sTString2.set(sTString);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetCs() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_attribute(CS$8);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STTheme.Enum getAsciiTheme() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(ASCIITHEME$10));
            if (simpleValue == null) {
                return null;
            }
            return (STTheme.Enum)simpleValue.getEnumValue();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STTheme xgetAsciiTheme() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STTheme sTTheme = null;
            sTTheme = (STTheme)((Object)this.get_store().find_attribute_user(ASCIITHEME$10));
            return sTTheme;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetAsciiTheme() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().find_attribute_user(ASCIITHEME$10) != null;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setAsciiTheme(STTheme.Enum enum_) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(ASCIITHEME$10));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(ASCIITHEME$10));
            }
            simpleValue.setEnumValue(enum_);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetAsciiTheme(STTheme sTTheme) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STTheme sTTheme2 = null;
            sTTheme2 = (STTheme)((Object)this.get_store().find_attribute_user(ASCIITHEME$10));
            if (sTTheme2 == null) {
                sTTheme2 = (STTheme)((Object)this.get_store().add_attribute_user(ASCIITHEME$10));
            }
            sTTheme2.set(sTTheme);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetAsciiTheme() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_attribute(ASCIITHEME$10);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STTheme.Enum getHAnsiTheme() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(HANSITHEME$12));
            if (simpleValue == null) {
                return null;
            }
            return (STTheme.Enum)simpleValue.getEnumValue();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STTheme xgetHAnsiTheme() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STTheme sTTheme = null;
            sTTheme = (STTheme)((Object)this.get_store().find_attribute_user(HANSITHEME$12));
            return sTTheme;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetHAnsiTheme() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().find_attribute_user(HANSITHEME$12) != null;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setHAnsiTheme(STTheme.Enum enum_) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(HANSITHEME$12));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(HANSITHEME$12));
            }
            simpleValue.setEnumValue(enum_);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetHAnsiTheme(STTheme sTTheme) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STTheme sTTheme2 = null;
            sTTheme2 = (STTheme)((Object)this.get_store().find_attribute_user(HANSITHEME$12));
            if (sTTheme2 == null) {
                sTTheme2 = (STTheme)((Object)this.get_store().add_attribute_user(HANSITHEME$12));
            }
            sTTheme2.set(sTTheme);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetHAnsiTheme() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_attribute(HANSITHEME$12);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STTheme.Enum getEastAsiaTheme() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(EASTASIATHEME$14));
            if (simpleValue == null) {
                return null;
            }
            return (STTheme.Enum)simpleValue.getEnumValue();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STTheme xgetEastAsiaTheme() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STTheme sTTheme = null;
            sTTheme = (STTheme)((Object)this.get_store().find_attribute_user(EASTASIATHEME$14));
            return sTTheme;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetEastAsiaTheme() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().find_attribute_user(EASTASIATHEME$14) != null;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setEastAsiaTheme(STTheme.Enum enum_) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(EASTASIATHEME$14));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(EASTASIATHEME$14));
            }
            simpleValue.setEnumValue(enum_);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetEastAsiaTheme(STTheme sTTheme) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STTheme sTTheme2 = null;
            sTTheme2 = (STTheme)((Object)this.get_store().find_attribute_user(EASTASIATHEME$14));
            if (sTTheme2 == null) {
                sTTheme2 = (STTheme)((Object)this.get_store().add_attribute_user(EASTASIATHEME$14));
            }
            sTTheme2.set(sTTheme);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetEastAsiaTheme() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_attribute(EASTASIATHEME$14);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STTheme.Enum getCstheme() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(CSTHEME$16));
            if (simpleValue == null) {
                return null;
            }
            return (STTheme.Enum)simpleValue.getEnumValue();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STTheme xgetCstheme() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STTheme sTTheme = null;
            sTTheme = (STTheme)((Object)this.get_store().find_attribute_user(CSTHEME$16));
            return sTTheme;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetCstheme() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().find_attribute_user(CSTHEME$16) != null;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setCstheme(STTheme.Enum enum_) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(CSTHEME$16));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(CSTHEME$16));
            }
            simpleValue.setEnumValue(enum_);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetCstheme(STTheme sTTheme) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STTheme sTTheme2 = null;
            sTTheme2 = (STTheme)((Object)this.get_store().find_attribute_user(CSTHEME$16));
            if (sTTheme2 == null) {
                sTTheme2 = (STTheme)((Object)this.get_store().add_attribute_user(CSTHEME$16));
            }
            sTTheme2.set(sTTheme);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetCstheme() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_attribute(CSTHEME$16);
        }
    }
}

