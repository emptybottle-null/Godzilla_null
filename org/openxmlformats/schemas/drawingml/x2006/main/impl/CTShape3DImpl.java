/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  org.openxmlformats.schemas.drawingml.x2006.main.CTBevel
 *  org.openxmlformats.schemas.drawingml.x2006.main.STPresetMaterialType
 */
package org.openxmlformats.schemas.drawingml.x2006.main.impl;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.drawingml.x2006.main.CTBevel;
import org.openxmlformats.schemas.drawingml.x2006.main.CTColor;
import org.openxmlformats.schemas.drawingml.x2006.main.CTOfficeArtExtensionList;
import org.openxmlformats.schemas.drawingml.x2006.main.CTShape3D;
import org.openxmlformats.schemas.drawingml.x2006.main.STCoordinate;
import org.openxmlformats.schemas.drawingml.x2006.main.STPositiveCoordinate;
import org.openxmlformats.schemas.drawingml.x2006.main.STPresetMaterialType;

public class CTShape3DImpl
extends XmlComplexContentImpl
implements CTShape3D {
    private static final long serialVersionUID = 1L;
    private static final QName BEVELT$0 = new QName("http://schemas.openxmlformats.org/drawingml/2006/main", "bevelT");
    private static final QName BEVELB$2 = new QName("http://schemas.openxmlformats.org/drawingml/2006/main", "bevelB");
    private static final QName EXTRUSIONCLR$4 = new QName("http://schemas.openxmlformats.org/drawingml/2006/main", "extrusionClr");
    private static final QName CONTOURCLR$6 = new QName("http://schemas.openxmlformats.org/drawingml/2006/main", "contourClr");
    private static final QName EXTLST$8 = new QName("http://schemas.openxmlformats.org/drawingml/2006/main", "extLst");
    private static final QName Z$10 = new QName("", "z");
    private static final QName EXTRUSIONH$12 = new QName("", "extrusionH");
    private static final QName CONTOURW$14 = new QName("", "contourW");
    private static final QName PRSTMATERIAL$16 = new QName("", "prstMaterial");

    public CTShape3DImpl(SchemaType schemaType) {
        super(schemaType);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTBevel getBevelT() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTBevel cTBevel = null;
            cTBevel = (CTBevel)this.get_store().find_element_user(BEVELT$0, 0);
            if (cTBevel == null) {
                return null;
            }
            return cTBevel;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetBevelT() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(BEVELT$0) != 0;
        }
    }

    @Override
    public void setBevelT(CTBevel cTBevel) {
        this.generatedSetterHelperImpl((XmlObject)cTBevel, BEVELT$0, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTBevel addNewBevelT() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTBevel cTBevel = null;
            cTBevel = (CTBevel)this.get_store().add_element_user(BEVELT$0);
            return cTBevel;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetBevelT() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(BEVELT$0, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTBevel getBevelB() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTBevel cTBevel = null;
            cTBevel = (CTBevel)this.get_store().find_element_user(BEVELB$2, 0);
            if (cTBevel == null) {
                return null;
            }
            return cTBevel;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetBevelB() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(BEVELB$2) != 0;
        }
    }

    @Override
    public void setBevelB(CTBevel cTBevel) {
        this.generatedSetterHelperImpl((XmlObject)cTBevel, BEVELB$2, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTBevel addNewBevelB() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTBevel cTBevel = null;
            cTBevel = (CTBevel)this.get_store().add_element_user(BEVELB$2);
            return cTBevel;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetBevelB() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(BEVELB$2, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTColor getExtrusionClr() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTColor cTColor = null;
            cTColor = (CTColor)((Object)this.get_store().find_element_user(EXTRUSIONCLR$4, 0));
            if (cTColor == null) {
                return null;
            }
            return cTColor;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetExtrusionClr() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(EXTRUSIONCLR$4) != 0;
        }
    }

    @Override
    public void setExtrusionClr(CTColor cTColor) {
        this.generatedSetterHelperImpl(cTColor, EXTRUSIONCLR$4, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTColor addNewExtrusionClr() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTColor cTColor = null;
            cTColor = (CTColor)((Object)this.get_store().add_element_user(EXTRUSIONCLR$4));
            return cTColor;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetExtrusionClr() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(EXTRUSIONCLR$4, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTColor getContourClr() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTColor cTColor = null;
            cTColor = (CTColor)((Object)this.get_store().find_element_user(CONTOURCLR$6, 0));
            if (cTColor == null) {
                return null;
            }
            return cTColor;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetContourClr() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(CONTOURCLR$6) != 0;
        }
    }

    @Override
    public void setContourClr(CTColor cTColor) {
        this.generatedSetterHelperImpl(cTColor, CONTOURCLR$6, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTColor addNewContourClr() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTColor cTColor = null;
            cTColor = (CTColor)((Object)this.get_store().add_element_user(CONTOURCLR$6));
            return cTColor;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetContourClr() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(CONTOURCLR$6, 0);
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
            cTOfficeArtExtensionList = (CTOfficeArtExtensionList)((Object)this.get_store().find_element_user(EXTLST$8, 0));
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
            return this.get_store().count_elements(EXTLST$8) != 0;
        }
    }

    @Override
    public void setExtLst(CTOfficeArtExtensionList cTOfficeArtExtensionList) {
        this.generatedSetterHelperImpl(cTOfficeArtExtensionList, EXTLST$8, 0, (short)1);
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
            cTOfficeArtExtensionList = (CTOfficeArtExtensionList)((Object)this.get_store().add_element_user(EXTLST$8));
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
            this.get_store().remove_element(EXTLST$8, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public long getZ() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(Z$10));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_default_attribute_value(Z$10));
            }
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
    public STCoordinate xgetZ() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STCoordinate sTCoordinate = null;
            sTCoordinate = (STCoordinate)((Object)this.get_store().find_attribute_user(Z$10));
            if (sTCoordinate == null) {
                sTCoordinate = (STCoordinate)this.get_default_attribute_value(Z$10);
            }
            return sTCoordinate;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetZ() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().find_attribute_user(Z$10) != null;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setZ(long l) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(Z$10));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(Z$10));
            }
            simpleValue.setLongValue(l);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetZ(STCoordinate sTCoordinate) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STCoordinate sTCoordinate2 = null;
            sTCoordinate2 = (STCoordinate)((Object)this.get_store().find_attribute_user(Z$10));
            if (sTCoordinate2 == null) {
                sTCoordinate2 = (STCoordinate)((Object)this.get_store().add_attribute_user(Z$10));
            }
            sTCoordinate2.set(sTCoordinate);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetZ() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_attribute(Z$10);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public long getExtrusionH() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(EXTRUSIONH$12));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_default_attribute_value(EXTRUSIONH$12));
            }
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
    public STPositiveCoordinate xgetExtrusionH() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STPositiveCoordinate sTPositiveCoordinate = null;
            sTPositiveCoordinate = (STPositiveCoordinate)((Object)this.get_store().find_attribute_user(EXTRUSIONH$12));
            if (sTPositiveCoordinate == null) {
                sTPositiveCoordinate = (STPositiveCoordinate)this.get_default_attribute_value(EXTRUSIONH$12);
            }
            return sTPositiveCoordinate;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetExtrusionH() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().find_attribute_user(EXTRUSIONH$12) != null;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setExtrusionH(long l) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(EXTRUSIONH$12));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(EXTRUSIONH$12));
            }
            simpleValue.setLongValue(l);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetExtrusionH(STPositiveCoordinate sTPositiveCoordinate) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STPositiveCoordinate sTPositiveCoordinate2 = null;
            sTPositiveCoordinate2 = (STPositiveCoordinate)((Object)this.get_store().find_attribute_user(EXTRUSIONH$12));
            if (sTPositiveCoordinate2 == null) {
                sTPositiveCoordinate2 = (STPositiveCoordinate)((Object)this.get_store().add_attribute_user(EXTRUSIONH$12));
            }
            sTPositiveCoordinate2.set(sTPositiveCoordinate);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetExtrusionH() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_attribute(EXTRUSIONH$12);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public long getContourW() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(CONTOURW$14));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_default_attribute_value(CONTOURW$14));
            }
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
    public STPositiveCoordinate xgetContourW() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STPositiveCoordinate sTPositiveCoordinate = null;
            sTPositiveCoordinate = (STPositiveCoordinate)((Object)this.get_store().find_attribute_user(CONTOURW$14));
            if (sTPositiveCoordinate == null) {
                sTPositiveCoordinate = (STPositiveCoordinate)this.get_default_attribute_value(CONTOURW$14);
            }
            return sTPositiveCoordinate;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetContourW() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().find_attribute_user(CONTOURW$14) != null;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setContourW(long l) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(CONTOURW$14));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(CONTOURW$14));
            }
            simpleValue.setLongValue(l);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetContourW(STPositiveCoordinate sTPositiveCoordinate) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STPositiveCoordinate sTPositiveCoordinate2 = null;
            sTPositiveCoordinate2 = (STPositiveCoordinate)((Object)this.get_store().find_attribute_user(CONTOURW$14));
            if (sTPositiveCoordinate2 == null) {
                sTPositiveCoordinate2 = (STPositiveCoordinate)((Object)this.get_store().add_attribute_user(CONTOURW$14));
            }
            sTPositiveCoordinate2.set(sTPositiveCoordinate);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetContourW() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_attribute(CONTOURW$14);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STPresetMaterialType.Enum getPrstMaterial() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(PRSTMATERIAL$16));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_default_attribute_value(PRSTMATERIAL$16));
            }
            if (simpleValue == null) {
                return null;
            }
            return (STPresetMaterialType.Enum)simpleValue.getEnumValue();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STPresetMaterialType xgetPrstMaterial() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STPresetMaterialType sTPresetMaterialType = null;
            sTPresetMaterialType = (STPresetMaterialType)this.get_store().find_attribute_user(PRSTMATERIAL$16);
            if (sTPresetMaterialType == null) {
                sTPresetMaterialType = (STPresetMaterialType)this.get_default_attribute_value(PRSTMATERIAL$16);
            }
            return sTPresetMaterialType;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetPrstMaterial() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().find_attribute_user(PRSTMATERIAL$16) != null;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setPrstMaterial(STPresetMaterialType.Enum enum_) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(PRSTMATERIAL$16));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(PRSTMATERIAL$16));
            }
            simpleValue.setEnumValue(enum_);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetPrstMaterial(STPresetMaterialType sTPresetMaterialType) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STPresetMaterialType sTPresetMaterialType2 = null;
            sTPresetMaterialType2 = (STPresetMaterialType)this.get_store().find_attribute_user(PRSTMATERIAL$16);
            if (sTPresetMaterialType2 == null) {
                sTPresetMaterialType2 = (STPresetMaterialType)this.get_store().add_attribute_user(PRSTMATERIAL$16);
            }
            sTPresetMaterialType2.set((XmlObject)sTPresetMaterialType);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetPrstMaterial() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_attribute(PRSTMATERIAL$16);
        }
    }
}

