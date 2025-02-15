/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.openxmlformats.schemas.drawingml.x2006.main.impl;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.XmlBoolean;
import org.apache.xmlbeans.XmlUnsignedInt;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.drawingml.x2006.main.CTBlip;
import org.openxmlformats.schemas.drawingml.x2006.main.CTBlipFillProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTRelativeRect;
import org.openxmlformats.schemas.drawingml.x2006.main.CTStretchInfoProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTileInfoProperties;

public class CTBlipFillPropertiesImpl
extends XmlComplexContentImpl
implements CTBlipFillProperties {
    private static final long serialVersionUID = 1L;
    private static final QName BLIP$0 = new QName("http://schemas.openxmlformats.org/drawingml/2006/main", "blip");
    private static final QName SRCRECT$2 = new QName("http://schemas.openxmlformats.org/drawingml/2006/main", "srcRect");
    private static final QName TILE$4 = new QName("http://schemas.openxmlformats.org/drawingml/2006/main", "tile");
    private static final QName STRETCH$6 = new QName("http://schemas.openxmlformats.org/drawingml/2006/main", "stretch");
    private static final QName DPI$8 = new QName("", "dpi");
    private static final QName ROTWITHSHAPE$10 = new QName("", "rotWithShape");

    public CTBlipFillPropertiesImpl(SchemaType schemaType) {
        super(schemaType);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTBlip getBlip() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTBlip cTBlip = null;
            cTBlip = (CTBlip)((Object)this.get_store().find_element_user(BLIP$0, 0));
            if (cTBlip == null) {
                return null;
            }
            return cTBlip;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetBlip() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(BLIP$0) != 0;
        }
    }

    @Override
    public void setBlip(CTBlip cTBlip) {
        this.generatedSetterHelperImpl(cTBlip, BLIP$0, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTBlip addNewBlip() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTBlip cTBlip = null;
            cTBlip = (CTBlip)((Object)this.get_store().add_element_user(BLIP$0));
            return cTBlip;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetBlip() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(BLIP$0, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTRelativeRect getSrcRect() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTRelativeRect cTRelativeRect = null;
            cTRelativeRect = (CTRelativeRect)((Object)this.get_store().find_element_user(SRCRECT$2, 0));
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
    public boolean isSetSrcRect() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(SRCRECT$2) != 0;
        }
    }

    @Override
    public void setSrcRect(CTRelativeRect cTRelativeRect) {
        this.generatedSetterHelperImpl(cTRelativeRect, SRCRECT$2, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTRelativeRect addNewSrcRect() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTRelativeRect cTRelativeRect = null;
            cTRelativeRect = (CTRelativeRect)((Object)this.get_store().add_element_user(SRCRECT$2));
            return cTRelativeRect;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetSrcRect() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(SRCRECT$2, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTTileInfoProperties getTile() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTTileInfoProperties cTTileInfoProperties = null;
            cTTileInfoProperties = (CTTileInfoProperties)((Object)this.get_store().find_element_user(TILE$4, 0));
            if (cTTileInfoProperties == null) {
                return null;
            }
            return cTTileInfoProperties;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetTile() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(TILE$4) != 0;
        }
    }

    @Override
    public void setTile(CTTileInfoProperties cTTileInfoProperties) {
        this.generatedSetterHelperImpl(cTTileInfoProperties, TILE$4, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTTileInfoProperties addNewTile() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTTileInfoProperties cTTileInfoProperties = null;
            cTTileInfoProperties = (CTTileInfoProperties)((Object)this.get_store().add_element_user(TILE$4));
            return cTTileInfoProperties;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetTile() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(TILE$4, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTStretchInfoProperties getStretch() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTStretchInfoProperties cTStretchInfoProperties = null;
            cTStretchInfoProperties = (CTStretchInfoProperties)((Object)this.get_store().find_element_user(STRETCH$6, 0));
            if (cTStretchInfoProperties == null) {
                return null;
            }
            return cTStretchInfoProperties;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetStretch() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(STRETCH$6) != 0;
        }
    }

    @Override
    public void setStretch(CTStretchInfoProperties cTStretchInfoProperties) {
        this.generatedSetterHelperImpl(cTStretchInfoProperties, STRETCH$6, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTStretchInfoProperties addNewStretch() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTStretchInfoProperties cTStretchInfoProperties = null;
            cTStretchInfoProperties = (CTStretchInfoProperties)((Object)this.get_store().add_element_user(STRETCH$6));
            return cTStretchInfoProperties;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetStretch() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(STRETCH$6, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public long getDpi() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(DPI$8));
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
    public XmlUnsignedInt xgetDpi() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlUnsignedInt xmlUnsignedInt = null;
            xmlUnsignedInt = (XmlUnsignedInt)((Object)this.get_store().find_attribute_user(DPI$8));
            return xmlUnsignedInt;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetDpi() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().find_attribute_user(DPI$8) != null;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setDpi(long l) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(DPI$8));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(DPI$8));
            }
            simpleValue.setLongValue(l);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetDpi(XmlUnsignedInt xmlUnsignedInt) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlUnsignedInt xmlUnsignedInt2 = null;
            xmlUnsignedInt2 = (XmlUnsignedInt)((Object)this.get_store().find_attribute_user(DPI$8));
            if (xmlUnsignedInt2 == null) {
                xmlUnsignedInt2 = (XmlUnsignedInt)((Object)this.get_store().add_attribute_user(DPI$8));
            }
            xmlUnsignedInt2.set(xmlUnsignedInt);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetDpi() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_attribute(DPI$8);
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

