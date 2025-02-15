/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.openxmlformats.schemas.drawingml.x2006.main.impl;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.drawingml.x2006.main.CTFontCollection;
import org.openxmlformats.schemas.drawingml.x2006.main.CTFontReference;
import org.openxmlformats.schemas.drawingml.x2006.main.CTHslColor;
import org.openxmlformats.schemas.drawingml.x2006.main.CTOfficeArtExtensionList;
import org.openxmlformats.schemas.drawingml.x2006.main.CTPresetColor;
import org.openxmlformats.schemas.drawingml.x2006.main.CTSRgbColor;
import org.openxmlformats.schemas.drawingml.x2006.main.CTScRgbColor;
import org.openxmlformats.schemas.drawingml.x2006.main.CTSchemeColor;
import org.openxmlformats.schemas.drawingml.x2006.main.CTSystemColor;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTableStyleTextStyle;
import org.openxmlformats.schemas.drawingml.x2006.main.STOnOffStyleType;

public class CTTableStyleTextStyleImpl
extends XmlComplexContentImpl
implements CTTableStyleTextStyle {
    private static final long serialVersionUID = 1L;
    private static final QName FONT$0 = new QName("http://schemas.openxmlformats.org/drawingml/2006/main", "font");
    private static final QName FONTREF$2 = new QName("http://schemas.openxmlformats.org/drawingml/2006/main", "fontRef");
    private static final QName SCRGBCLR$4 = new QName("http://schemas.openxmlformats.org/drawingml/2006/main", "scrgbClr");
    private static final QName SRGBCLR$6 = new QName("http://schemas.openxmlformats.org/drawingml/2006/main", "srgbClr");
    private static final QName HSLCLR$8 = new QName("http://schemas.openxmlformats.org/drawingml/2006/main", "hslClr");
    private static final QName SYSCLR$10 = new QName("http://schemas.openxmlformats.org/drawingml/2006/main", "sysClr");
    private static final QName SCHEMECLR$12 = new QName("http://schemas.openxmlformats.org/drawingml/2006/main", "schemeClr");
    private static final QName PRSTCLR$14 = new QName("http://schemas.openxmlformats.org/drawingml/2006/main", "prstClr");
    private static final QName EXTLST$16 = new QName("http://schemas.openxmlformats.org/drawingml/2006/main", "extLst");
    private static final QName B$18 = new QName("", "b");
    private static final QName I$20 = new QName("", "i");

    public CTTableStyleTextStyleImpl(SchemaType schemaType) {
        super(schemaType);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTFontCollection getFont() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTFontCollection cTFontCollection = null;
            cTFontCollection = (CTFontCollection)((Object)this.get_store().find_element_user(FONT$0, 0));
            if (cTFontCollection == null) {
                return null;
            }
            return cTFontCollection;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetFont() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(FONT$0) != 0;
        }
    }

    @Override
    public void setFont(CTFontCollection cTFontCollection) {
        this.generatedSetterHelperImpl(cTFontCollection, FONT$0, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTFontCollection addNewFont() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTFontCollection cTFontCollection = null;
            cTFontCollection = (CTFontCollection)((Object)this.get_store().add_element_user(FONT$0));
            return cTFontCollection;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetFont() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(FONT$0, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTFontReference getFontRef() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTFontReference cTFontReference = null;
            cTFontReference = (CTFontReference)((Object)this.get_store().find_element_user(FONTREF$2, 0));
            if (cTFontReference == null) {
                return null;
            }
            return cTFontReference;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetFontRef() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(FONTREF$2) != 0;
        }
    }

    @Override
    public void setFontRef(CTFontReference cTFontReference) {
        this.generatedSetterHelperImpl(cTFontReference, FONTREF$2, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTFontReference addNewFontRef() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTFontReference cTFontReference = null;
            cTFontReference = (CTFontReference)((Object)this.get_store().add_element_user(FONTREF$2));
            return cTFontReference;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetFontRef() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(FONTREF$2, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTScRgbColor getScrgbClr() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTScRgbColor cTScRgbColor = null;
            cTScRgbColor = (CTScRgbColor)((Object)this.get_store().find_element_user(SCRGBCLR$4, 0));
            if (cTScRgbColor == null) {
                return null;
            }
            return cTScRgbColor;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetScrgbClr() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(SCRGBCLR$4) != 0;
        }
    }

    @Override
    public void setScrgbClr(CTScRgbColor cTScRgbColor) {
        this.generatedSetterHelperImpl(cTScRgbColor, SCRGBCLR$4, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTScRgbColor addNewScrgbClr() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTScRgbColor cTScRgbColor = null;
            cTScRgbColor = (CTScRgbColor)((Object)this.get_store().add_element_user(SCRGBCLR$4));
            return cTScRgbColor;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetScrgbClr() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(SCRGBCLR$4, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTSRgbColor getSrgbClr() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTSRgbColor cTSRgbColor = null;
            cTSRgbColor = (CTSRgbColor)((Object)this.get_store().find_element_user(SRGBCLR$6, 0));
            if (cTSRgbColor == null) {
                return null;
            }
            return cTSRgbColor;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetSrgbClr() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(SRGBCLR$6) != 0;
        }
    }

    @Override
    public void setSrgbClr(CTSRgbColor cTSRgbColor) {
        this.generatedSetterHelperImpl(cTSRgbColor, SRGBCLR$6, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTSRgbColor addNewSrgbClr() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTSRgbColor cTSRgbColor = null;
            cTSRgbColor = (CTSRgbColor)((Object)this.get_store().add_element_user(SRGBCLR$6));
            return cTSRgbColor;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetSrgbClr() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(SRGBCLR$6, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTHslColor getHslClr() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTHslColor cTHslColor = null;
            cTHslColor = (CTHslColor)((Object)this.get_store().find_element_user(HSLCLR$8, 0));
            if (cTHslColor == null) {
                return null;
            }
            return cTHslColor;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetHslClr() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(HSLCLR$8) != 0;
        }
    }

    @Override
    public void setHslClr(CTHslColor cTHslColor) {
        this.generatedSetterHelperImpl(cTHslColor, HSLCLR$8, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTHslColor addNewHslClr() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTHslColor cTHslColor = null;
            cTHslColor = (CTHslColor)((Object)this.get_store().add_element_user(HSLCLR$8));
            return cTHslColor;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetHslClr() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(HSLCLR$8, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTSystemColor getSysClr() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTSystemColor cTSystemColor = null;
            cTSystemColor = (CTSystemColor)((Object)this.get_store().find_element_user(SYSCLR$10, 0));
            if (cTSystemColor == null) {
                return null;
            }
            return cTSystemColor;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetSysClr() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(SYSCLR$10) != 0;
        }
    }

    @Override
    public void setSysClr(CTSystemColor cTSystemColor) {
        this.generatedSetterHelperImpl(cTSystemColor, SYSCLR$10, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTSystemColor addNewSysClr() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTSystemColor cTSystemColor = null;
            cTSystemColor = (CTSystemColor)((Object)this.get_store().add_element_user(SYSCLR$10));
            return cTSystemColor;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetSysClr() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(SYSCLR$10, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTSchemeColor getSchemeClr() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTSchemeColor cTSchemeColor = null;
            cTSchemeColor = (CTSchemeColor)((Object)this.get_store().find_element_user(SCHEMECLR$12, 0));
            if (cTSchemeColor == null) {
                return null;
            }
            return cTSchemeColor;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetSchemeClr() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(SCHEMECLR$12) != 0;
        }
    }

    @Override
    public void setSchemeClr(CTSchemeColor cTSchemeColor) {
        this.generatedSetterHelperImpl(cTSchemeColor, SCHEMECLR$12, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTSchemeColor addNewSchemeClr() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTSchemeColor cTSchemeColor = null;
            cTSchemeColor = (CTSchemeColor)((Object)this.get_store().add_element_user(SCHEMECLR$12));
            return cTSchemeColor;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetSchemeClr() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(SCHEMECLR$12, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTPresetColor getPrstClr() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTPresetColor cTPresetColor = null;
            cTPresetColor = (CTPresetColor)((Object)this.get_store().find_element_user(PRSTCLR$14, 0));
            if (cTPresetColor == null) {
                return null;
            }
            return cTPresetColor;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetPrstClr() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(PRSTCLR$14) != 0;
        }
    }

    @Override
    public void setPrstClr(CTPresetColor cTPresetColor) {
        this.generatedSetterHelperImpl(cTPresetColor, PRSTCLR$14, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTPresetColor addNewPrstClr() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTPresetColor cTPresetColor = null;
            cTPresetColor = (CTPresetColor)((Object)this.get_store().add_element_user(PRSTCLR$14));
            return cTPresetColor;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetPrstClr() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(PRSTCLR$14, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTOfficeArtExtensionList getExtLst() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTOfficeArtExtensionList cTOfficeArtExtensionList = null;
            cTOfficeArtExtensionList = (CTOfficeArtExtensionList)((Object)this.get_store().find_element_user(EXTLST$16, 0));
            if (cTOfficeArtExtensionList == null) {
                return null;
            }
            return cTOfficeArtExtensionList;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetExtLst() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(EXTLST$16) != 0;
        }
    }

    @Override
    public void setExtLst(CTOfficeArtExtensionList cTOfficeArtExtensionList) {
        this.generatedSetterHelperImpl(cTOfficeArtExtensionList, EXTLST$16, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTOfficeArtExtensionList addNewExtLst() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTOfficeArtExtensionList cTOfficeArtExtensionList = null;
            cTOfficeArtExtensionList = (CTOfficeArtExtensionList)((Object)this.get_store().add_element_user(EXTLST$16));
            return cTOfficeArtExtensionList;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetExtLst() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(EXTLST$16, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STOnOffStyleType.Enum getB() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(B$18));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_default_attribute_value(B$18));
            }
            if (simpleValue == null) {
                return null;
            }
            return (STOnOffStyleType.Enum)simpleValue.getEnumValue();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STOnOffStyleType xgetB() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STOnOffStyleType sTOnOffStyleType = null;
            sTOnOffStyleType = (STOnOffStyleType)((Object)this.get_store().find_attribute_user(B$18));
            if (sTOnOffStyleType == null) {
                sTOnOffStyleType = (STOnOffStyleType)this.get_default_attribute_value(B$18);
            }
            return sTOnOffStyleType;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetB() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().find_attribute_user(B$18) != null;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setB(STOnOffStyleType.Enum enum_) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(B$18));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(B$18));
            }
            simpleValue.setEnumValue(enum_);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetB(STOnOffStyleType sTOnOffStyleType) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STOnOffStyleType sTOnOffStyleType2 = null;
            sTOnOffStyleType2 = (STOnOffStyleType)((Object)this.get_store().find_attribute_user(B$18));
            if (sTOnOffStyleType2 == null) {
                sTOnOffStyleType2 = (STOnOffStyleType)((Object)this.get_store().add_attribute_user(B$18));
            }
            sTOnOffStyleType2.set(sTOnOffStyleType);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetB() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_attribute(B$18);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STOnOffStyleType.Enum getI() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(I$20));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_default_attribute_value(I$20));
            }
            if (simpleValue == null) {
                return null;
            }
            return (STOnOffStyleType.Enum)simpleValue.getEnumValue();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STOnOffStyleType xgetI() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STOnOffStyleType sTOnOffStyleType = null;
            sTOnOffStyleType = (STOnOffStyleType)((Object)this.get_store().find_attribute_user(I$20));
            if (sTOnOffStyleType == null) {
                sTOnOffStyleType = (STOnOffStyleType)this.get_default_attribute_value(I$20);
            }
            return sTOnOffStyleType;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetI() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().find_attribute_user(I$20) != null;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setI(STOnOffStyleType.Enum enum_) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(I$20));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(I$20));
            }
            simpleValue.setEnumValue(enum_);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetI(STOnOffStyleType sTOnOffStyleType) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STOnOffStyleType sTOnOffStyleType2 = null;
            sTOnOffStyleType2 = (STOnOffStyleType)((Object)this.get_store().find_attribute_user(I$20));
            if (sTOnOffStyleType2 == null) {
                sTOnOffStyleType2 = (STOnOffStyleType)((Object)this.get_store().add_attribute_user(I$20));
            }
            sTOnOffStyleType2.set(sTOnOffStyleType);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetI() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_attribute(I$20);
        }
    }
}

