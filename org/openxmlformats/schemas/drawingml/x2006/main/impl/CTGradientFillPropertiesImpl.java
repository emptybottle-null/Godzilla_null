/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.openxmlformats.schemas.drawingml.x2006.main.impl;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.XmlBoolean;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.drawingml.x2006.main.CTGradientFillProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTGradientStopList;
import org.openxmlformats.schemas.drawingml.x2006.main.CTLinearShadeProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTPathShadeProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTRelativeRect;
import org.openxmlformats.schemas.drawingml.x2006.main.STTileFlipMode;

public class CTGradientFillPropertiesImpl
extends XmlComplexContentImpl
implements CTGradientFillProperties {
    private static final long serialVersionUID = 1L;
    private static final QName GSLST$0 = new QName("http://schemas.openxmlformats.org/drawingml/2006/main", "gsLst");
    private static final QName LIN$2 = new QName("http://schemas.openxmlformats.org/drawingml/2006/main", "lin");
    private static final QName PATH$4 = new QName("http://schemas.openxmlformats.org/drawingml/2006/main", "path");
    private static final QName TILERECT$6 = new QName("http://schemas.openxmlformats.org/drawingml/2006/main", "tileRect");
    private static final QName FLIP$8 = new QName("", "flip");
    private static final QName ROTWITHSHAPE$10 = new QName("", "rotWithShape");

    public CTGradientFillPropertiesImpl(SchemaType schemaType) {
        super(schemaType);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTGradientStopList getGsLst() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTGradientStopList cTGradientStopList = null;
            cTGradientStopList = (CTGradientStopList)((Object)this.get_store().find_element_user(GSLST$0, 0));
            if (cTGradientStopList == null) {
                return null;
            }
            return cTGradientStopList;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetGsLst() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(GSLST$0) != 0;
        }
    }

    @Override
    public void setGsLst(CTGradientStopList cTGradientStopList) {
        this.generatedSetterHelperImpl(cTGradientStopList, GSLST$0, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTGradientStopList addNewGsLst() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTGradientStopList cTGradientStopList = null;
            cTGradientStopList = (CTGradientStopList)((Object)this.get_store().add_element_user(GSLST$0));
            return cTGradientStopList;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetGsLst() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(GSLST$0, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTLinearShadeProperties getLin() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTLinearShadeProperties cTLinearShadeProperties = null;
            cTLinearShadeProperties = (CTLinearShadeProperties)((Object)this.get_store().find_element_user(LIN$2, 0));
            if (cTLinearShadeProperties == null) {
                return null;
            }
            return cTLinearShadeProperties;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetLin() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(LIN$2) != 0;
        }
    }

    @Override
    public void setLin(CTLinearShadeProperties cTLinearShadeProperties) {
        this.generatedSetterHelperImpl(cTLinearShadeProperties, LIN$2, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTLinearShadeProperties addNewLin() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTLinearShadeProperties cTLinearShadeProperties = null;
            cTLinearShadeProperties = (CTLinearShadeProperties)((Object)this.get_store().add_element_user(LIN$2));
            return cTLinearShadeProperties;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetLin() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(LIN$2, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTPathShadeProperties getPath() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTPathShadeProperties cTPathShadeProperties = null;
            cTPathShadeProperties = (CTPathShadeProperties)((Object)this.get_store().find_element_user(PATH$4, 0));
            if (cTPathShadeProperties == null) {
                return null;
            }
            return cTPathShadeProperties;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetPath() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(PATH$4) != 0;
        }
    }

    @Override
    public void setPath(CTPathShadeProperties cTPathShadeProperties) {
        this.generatedSetterHelperImpl(cTPathShadeProperties, PATH$4, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTPathShadeProperties addNewPath() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTPathShadeProperties cTPathShadeProperties = null;
            cTPathShadeProperties = (CTPathShadeProperties)((Object)this.get_store().add_element_user(PATH$4));
            return cTPathShadeProperties;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetPath() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(PATH$4, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTRelativeRect getTileRect() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTRelativeRect cTRelativeRect = null;
            cTRelativeRect = (CTRelativeRect)((Object)this.get_store().find_element_user(TILERECT$6, 0));
            if (cTRelativeRect == null) {
                return null;
            }
            return cTRelativeRect;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetTileRect() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(TILERECT$6) != 0;
        }
    }

    @Override
    public void setTileRect(CTRelativeRect cTRelativeRect) {
        this.generatedSetterHelperImpl(cTRelativeRect, TILERECT$6, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTRelativeRect addNewTileRect() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTRelativeRect cTRelativeRect = null;
            cTRelativeRect = (CTRelativeRect)((Object)this.get_store().add_element_user(TILERECT$6));
            return cTRelativeRect;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetTileRect() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(TILERECT$6, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STTileFlipMode.Enum getFlip() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(FLIP$8));
            if (simpleValue == null) {
                return null;
            }
            return (STTileFlipMode.Enum)simpleValue.getEnumValue();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STTileFlipMode xgetFlip() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STTileFlipMode sTTileFlipMode = null;
            sTTileFlipMode = (STTileFlipMode)((Object)this.get_store().find_attribute_user(FLIP$8));
            return sTTileFlipMode;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetFlip() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().find_attribute_user(FLIP$8) != null;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setFlip(STTileFlipMode.Enum enum_) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(FLIP$8));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(FLIP$8));
            }
            simpleValue.setEnumValue(enum_);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetFlip(STTileFlipMode sTTileFlipMode) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STTileFlipMode sTTileFlipMode2 = null;
            sTTileFlipMode2 = (STTileFlipMode)((Object)this.get_store().find_attribute_user(FLIP$8));
            if (sTTileFlipMode2 == null) {
                sTTileFlipMode2 = (STTileFlipMode)((Object)this.get_store().add_attribute_user(FLIP$8));
            }
            sTTileFlipMode2.set(sTTileFlipMode);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetFlip() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_attribute(FLIP$8);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean getRotWithShape() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(ROTWITHSHAPE$10));
            if (simpleValue == null) {
                return false;
            }
            return simpleValue.getBooleanValue();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public XmlBoolean xgetRotWithShape() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlBoolean xmlBoolean = null;
            xmlBoolean = (XmlBoolean)((Object)this.get_store().find_attribute_user(ROTWITHSHAPE$10));
            return xmlBoolean;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetRotWithShape() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().find_attribute_user(ROTWITHSHAPE$10) != null;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setRotWithShape(boolean bl) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(ROTWITHSHAPE$10));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(ROTWITHSHAPE$10));
            }
            simpleValue.setBooleanValue(bl);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetRotWithShape(XmlBoolean xmlBoolean) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlBoolean xmlBoolean2 = null;
            xmlBoolean2 = (XmlBoolean)((Object)this.get_store().find_attribute_user(ROTWITHSHAPE$10));
            if (xmlBoolean2 == null) {
                xmlBoolean2 = (XmlBoolean)((Object)this.get_store().add_attribute_user(ROTWITHSHAPE$10));
            }
            xmlBoolean2.set(xmlBoolean);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetRotWithShape() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_attribute(ROTWITHSHAPE$10);
        }
    }
}

