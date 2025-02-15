/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.openxmlformats.schemas.drawingml.x2006.main.impl;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.drawingml.x2006.main.CTBlipFillProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTEffectContainer;
import org.openxmlformats.schemas.drawingml.x2006.main.CTEffectList;
import org.openxmlformats.schemas.drawingml.x2006.main.CTGradientFillProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTGroupFillProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTGroupShapeProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTGroupTransform2D;
import org.openxmlformats.schemas.drawingml.x2006.main.CTNoFillProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTOfficeArtExtensionList;
import org.openxmlformats.schemas.drawingml.x2006.main.CTPatternFillProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTScene3D;
import org.openxmlformats.schemas.drawingml.x2006.main.CTSolidColorFillProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.STBlackWhiteMode;

public class CTGroupShapePropertiesImpl
extends XmlComplexContentImpl
implements CTGroupShapeProperties {
    private static final long serialVersionUID = 1L;
    private static final QName XFRM$0 = new QName("http://schemas.openxmlformats.org/drawingml/2006/main", "xfrm");
    private static final QName NOFILL$2 = new QName("http://schemas.openxmlformats.org/drawingml/2006/main", "noFill");
    private static final QName SOLIDFILL$4 = new QName("http://schemas.openxmlformats.org/drawingml/2006/main", "solidFill");
    private static final QName GRADFILL$6 = new QName("http://schemas.openxmlformats.org/drawingml/2006/main", "gradFill");
    private static final QName BLIPFILL$8 = new QName("http://schemas.openxmlformats.org/drawingml/2006/main", "blipFill");
    private static final QName PATTFILL$10 = new QName("http://schemas.openxmlformats.org/drawingml/2006/main", "pattFill");
    private static final QName GRPFILL$12 = new QName("http://schemas.openxmlformats.org/drawingml/2006/main", "grpFill");
    private static final QName EFFECTLST$14 = new QName("http://schemas.openxmlformats.org/drawingml/2006/main", "effectLst");
    private static final QName EFFECTDAG$16 = new QName("http://schemas.openxmlformats.org/drawingml/2006/main", "effectDag");
    private static final QName SCENE3D$18 = new QName("http://schemas.openxmlformats.org/drawingml/2006/main", "scene3d");
    private static final QName EXTLST$20 = new QName("http://schemas.openxmlformats.org/drawingml/2006/main", "extLst");
    private static final QName BWMODE$22 = new QName("", "bwMode");

    public CTGroupShapePropertiesImpl(SchemaType schemaType) {
        super(schemaType);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTGroupTransform2D getXfrm() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTGroupTransform2D cTGroupTransform2D = null;
            cTGroupTransform2D = (CTGroupTransform2D)((Object)this.get_store().find_element_user(XFRM$0, 0));
            if (cTGroupTransform2D == null) {
                return null;
            }
            return cTGroupTransform2D;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetXfrm() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(XFRM$0) != 0;
        }
    }

    @Override
    public void setXfrm(CTGroupTransform2D cTGroupTransform2D) {
        this.generatedSetterHelperImpl(cTGroupTransform2D, XFRM$0, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTGroupTransform2D addNewXfrm() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTGroupTransform2D cTGroupTransform2D = null;
            cTGroupTransform2D = (CTGroupTransform2D)((Object)this.get_store().add_element_user(XFRM$0));
            return cTGroupTransform2D;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetXfrm() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(XFRM$0, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTNoFillProperties getNoFill() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTNoFillProperties cTNoFillProperties = null;
            cTNoFillProperties = (CTNoFillProperties)((Object)this.get_store().find_element_user(NOFILL$2, 0));
            if (cTNoFillProperties == null) {
                return null;
            }
            return cTNoFillProperties;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetNoFill() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(NOFILL$2) != 0;
        }
    }

    @Override
    public void setNoFill(CTNoFillProperties cTNoFillProperties) {
        this.generatedSetterHelperImpl(cTNoFillProperties, NOFILL$2, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTNoFillProperties addNewNoFill() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTNoFillProperties cTNoFillProperties = null;
            cTNoFillProperties = (CTNoFillProperties)((Object)this.get_store().add_element_user(NOFILL$2));
            return cTNoFillProperties;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetNoFill() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(NOFILL$2, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTSolidColorFillProperties getSolidFill() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTSolidColorFillProperties cTSolidColorFillProperties = null;
            cTSolidColorFillProperties = (CTSolidColorFillProperties)((Object)this.get_store().find_element_user(SOLIDFILL$4, 0));
            if (cTSolidColorFillProperties == null) {
                return null;
            }
            return cTSolidColorFillProperties;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetSolidFill() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(SOLIDFILL$4) != 0;
        }
    }

    @Override
    public void setSolidFill(CTSolidColorFillProperties cTSolidColorFillProperties) {
        this.generatedSetterHelperImpl(cTSolidColorFillProperties, SOLIDFILL$4, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTSolidColorFillProperties addNewSolidFill() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTSolidColorFillProperties cTSolidColorFillProperties = null;
            cTSolidColorFillProperties = (CTSolidColorFillProperties)((Object)this.get_store().add_element_user(SOLIDFILL$4));
            return cTSolidColorFillProperties;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetSolidFill() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(SOLIDFILL$4, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTGradientFillProperties getGradFill() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTGradientFillProperties cTGradientFillProperties = null;
            cTGradientFillProperties = (CTGradientFillProperties)((Object)this.get_store().find_element_user(GRADFILL$6, 0));
            if (cTGradientFillProperties == null) {
                return null;
            }
            return cTGradientFillProperties;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetGradFill() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(GRADFILL$6) != 0;
        }
    }

    @Override
    public void setGradFill(CTGradientFillProperties cTGradientFillProperties) {
        this.generatedSetterHelperImpl(cTGradientFillProperties, GRADFILL$6, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTGradientFillProperties addNewGradFill() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTGradientFillProperties cTGradientFillProperties = null;
            cTGradientFillProperties = (CTGradientFillProperties)((Object)this.get_store().add_element_user(GRADFILL$6));
            return cTGradientFillProperties;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetGradFill() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(GRADFILL$6, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTBlipFillProperties getBlipFill() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTBlipFillProperties cTBlipFillProperties = null;
            cTBlipFillProperties = (CTBlipFillProperties)((Object)this.get_store().find_element_user(BLIPFILL$8, 0));
            if (cTBlipFillProperties == null) {
                return null;
            }
            return cTBlipFillProperties;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetBlipFill() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(BLIPFILL$8) != 0;
        }
    }

    @Override
    public void setBlipFill(CTBlipFillProperties cTBlipFillProperties) {
        this.generatedSetterHelperImpl(cTBlipFillProperties, BLIPFILL$8, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTBlipFillProperties addNewBlipFill() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTBlipFillProperties cTBlipFillProperties = null;
            cTBlipFillProperties = (CTBlipFillProperties)((Object)this.get_store().add_element_user(BLIPFILL$8));
            return cTBlipFillProperties;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetBlipFill() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(BLIPFILL$8, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTPatternFillProperties getPattFill() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTPatternFillProperties cTPatternFillProperties = null;
            cTPatternFillProperties = (CTPatternFillProperties)((Object)this.get_store().find_element_user(PATTFILL$10, 0));
            if (cTPatternFillProperties == null) {
                return null;
            }
            return cTPatternFillProperties;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetPattFill() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(PATTFILL$10) != 0;
        }
    }

    @Override
    public void setPattFill(CTPatternFillProperties cTPatternFillProperties) {
        this.generatedSetterHelperImpl(cTPatternFillProperties, PATTFILL$10, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTPatternFillProperties addNewPattFill() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTPatternFillProperties cTPatternFillProperties = null;
            cTPatternFillProperties = (CTPatternFillProperties)((Object)this.get_store().add_element_user(PATTFILL$10));
            return cTPatternFillProperties;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetPattFill() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(PATTFILL$10, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTGroupFillProperties getGrpFill() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTGroupFillProperties cTGroupFillProperties = null;
            cTGroupFillProperties = (CTGroupFillProperties)((Object)this.get_store().find_element_user(GRPFILL$12, 0));
            if (cTGroupFillProperties == null) {
                return null;
            }
            return cTGroupFillProperties;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetGrpFill() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(GRPFILL$12) != 0;
        }
    }

    @Override
    public void setGrpFill(CTGroupFillProperties cTGroupFillProperties) {
        this.generatedSetterHelperImpl(cTGroupFillProperties, GRPFILL$12, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTGroupFillProperties addNewGrpFill() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTGroupFillProperties cTGroupFillProperties = null;
            cTGroupFillProperties = (CTGroupFillProperties)((Object)this.get_store().add_element_user(GRPFILL$12));
            return cTGroupFillProperties;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetGrpFill() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(GRPFILL$12, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTEffectList getEffectLst() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTEffectList cTEffectList = null;
            cTEffectList = (CTEffectList)((Object)this.get_store().find_element_user(EFFECTLST$14, 0));
            if (cTEffectList == null) {
                return null;
            }
            return cTEffectList;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetEffectLst() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(EFFECTLST$14) != 0;
        }
    }

    @Override
    public void setEffectLst(CTEffectList cTEffectList) {
        this.generatedSetterHelperImpl(cTEffectList, EFFECTLST$14, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTEffectList addNewEffectLst() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTEffectList cTEffectList = null;
            cTEffectList = (CTEffectList)((Object)this.get_store().add_element_user(EFFECTLST$14));
            return cTEffectList;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetEffectLst() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(EFFECTLST$14, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTEffectContainer getEffectDag() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTEffectContainer cTEffectContainer = null;
            cTEffectContainer = (CTEffectContainer)((Object)this.get_store().find_element_user(EFFECTDAG$16, 0));
            if (cTEffectContainer == null) {
                return null;
            }
            return cTEffectContainer;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetEffectDag() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(EFFECTDAG$16) != 0;
        }
    }

    @Override
    public void setEffectDag(CTEffectContainer cTEffectContainer) {
        this.generatedSetterHelperImpl(cTEffectContainer, EFFECTDAG$16, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTEffectContainer addNewEffectDag() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTEffectContainer cTEffectContainer = null;
            cTEffectContainer = (CTEffectContainer)((Object)this.get_store().add_element_user(EFFECTDAG$16));
            return cTEffectContainer;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetEffectDag() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(EFFECTDAG$16, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTScene3D getScene3D() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTScene3D cTScene3D = null;
            cTScene3D = (CTScene3D)((Object)this.get_store().find_element_user(SCENE3D$18, 0));
            if (cTScene3D == null) {
                return null;
            }
            return cTScene3D;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetScene3D() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(SCENE3D$18) != 0;
        }
    }

    @Override
    public void setScene3D(CTScene3D cTScene3D) {
        this.generatedSetterHelperImpl(cTScene3D, SCENE3D$18, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTScene3D addNewScene3D() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTScene3D cTScene3D = null;
            cTScene3D = (CTScene3D)((Object)this.get_store().add_element_user(SCENE3D$18));
            return cTScene3D;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetScene3D() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(SCENE3D$18, 0);
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
            cTOfficeArtExtensionList = (CTOfficeArtExtensionList)((Object)this.get_store().find_element_user(EXTLST$20, 0));
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
            return this.get_store().count_elements(EXTLST$20) != 0;
        }
    }

    @Override
    public void setExtLst(CTOfficeArtExtensionList cTOfficeArtExtensionList) {
        this.generatedSetterHelperImpl(cTOfficeArtExtensionList, EXTLST$20, 0, (short)1);
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
            cTOfficeArtExtensionList = (CTOfficeArtExtensionList)((Object)this.get_store().add_element_user(EXTLST$20));
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
            this.get_store().remove_element(EXTLST$20, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STBlackWhiteMode.Enum getBwMode() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(BWMODE$22));
            if (simpleValue == null) {
                return null;
            }
            return (STBlackWhiteMode.Enum)simpleValue.getEnumValue();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STBlackWhiteMode xgetBwMode() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STBlackWhiteMode sTBlackWhiteMode = null;
            sTBlackWhiteMode = (STBlackWhiteMode)((Object)this.get_store().find_attribute_user(BWMODE$22));
            return sTBlackWhiteMode;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetBwMode() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().find_attribute_user(BWMODE$22) != null;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setBwMode(STBlackWhiteMode.Enum enum_) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(BWMODE$22));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(BWMODE$22));
            }
            simpleValue.setEnumValue(enum_);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetBwMode(STBlackWhiteMode sTBlackWhiteMode) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STBlackWhiteMode sTBlackWhiteMode2 = null;
            sTBlackWhiteMode2 = (STBlackWhiteMode)((Object)this.get_store().find_attribute_user(BWMODE$22));
            if (sTBlackWhiteMode2 == null) {
                sTBlackWhiteMode2 = (STBlackWhiteMode)((Object)this.get_store().add_attribute_user(BWMODE$22));
            }
            sTBlackWhiteMode2.set(sTBlackWhiteMode);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetBwMode() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_attribute(BWMODE$22);
        }
    }
}

