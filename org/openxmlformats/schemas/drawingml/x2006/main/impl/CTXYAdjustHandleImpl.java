/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.openxmlformats.schemas.drawingml.x2006.main.impl;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.drawingml.x2006.main.CTAdjPoint2D;
import org.openxmlformats.schemas.drawingml.x2006.main.CTXYAdjustHandle;
import org.openxmlformats.schemas.drawingml.x2006.main.STAdjCoordinate;
import org.openxmlformats.schemas.drawingml.x2006.main.STGeomGuideName;

public class CTXYAdjustHandleImpl
extends XmlComplexContentImpl
implements CTXYAdjustHandle {
    private static final long serialVersionUID = 1L;
    private static final QName POS$0 = new QName("http://schemas.openxmlformats.org/drawingml/2006/main", "pos");
    private static final QName GDREFX$2 = new QName("", "gdRefX");
    private static final QName MINX$4 = new QName("", "minX");
    private static final QName MAXX$6 = new QName("", "maxX");
    private static final QName GDREFY$8 = new QName("", "gdRefY");
    private static final QName MINY$10 = new QName("", "minY");
    private static final QName MAXY$12 = new QName("", "maxY");

    public CTXYAdjustHandleImpl(SchemaType schemaType) {
        super(schemaType);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTAdjPoint2D getPos() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTAdjPoint2D cTAdjPoint2D = null;
            cTAdjPoint2D = (CTAdjPoint2D)((Object)this.get_store().find_element_user(POS$0, 0));
            if (cTAdjPoint2D == null) {
                return null;
            }
            return cTAdjPoint2D;
        }
    }

    @Override
    public void setPos(CTAdjPoint2D cTAdjPoint2D) {
        this.generatedSetterHelperImpl(cTAdjPoint2D, POS$0, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTAdjPoint2D addNewPos() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTAdjPoint2D cTAdjPoint2D = null;
            cTAdjPoint2D = (CTAdjPoint2D)((Object)this.get_store().add_element_user(POS$0));
            return cTAdjPoint2D;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public String getGdRefX() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(GDREFX$2));
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
    public STGeomGuideName xgetGdRefX() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STGeomGuideName sTGeomGuideName = null;
            sTGeomGuideName = (STGeomGuideName)((Object)this.get_store().find_attribute_user(GDREFX$2));
            return sTGeomGuideName;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetGdRefX() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().find_attribute_user(GDREFX$2) != null;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setGdRefX(String string) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(GDREFX$2));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(GDREFX$2));
            }
            simpleValue.setStringValue(string);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetGdRefX(STGeomGuideName sTGeomGuideName) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STGeomGuideName sTGeomGuideName2 = null;
            sTGeomGuideName2 = (STGeomGuideName)((Object)this.get_store().find_attribute_user(GDREFX$2));
            if (sTGeomGuideName2 == null) {
                sTGeomGuideName2 = (STGeomGuideName)((Object)this.get_store().add_attribute_user(GDREFX$2));
            }
            sTGeomGuideName2.set(sTGeomGuideName);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetGdRefX() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_attribute(GDREFX$2);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public Object getMinX() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(MINX$4));
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
    public STAdjCoordinate xgetMinX() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STAdjCoordinate sTAdjCoordinate = null;
            sTAdjCoordinate = (STAdjCoordinate)((Object)this.get_store().find_attribute_user(MINX$4));
            return sTAdjCoordinate;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetMinX() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().find_attribute_user(MINX$4) != null;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setMinX(Object object) {
        Object object2 = this.monitor();
        synchronized (object2) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(MINX$4));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(MINX$4));
            }
            simpleValue.setObjectValue(object);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetMinX(STAdjCoordinate sTAdjCoordinate) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STAdjCoordinate sTAdjCoordinate2 = null;
            sTAdjCoordinate2 = (STAdjCoordinate)((Object)this.get_store().find_attribute_user(MINX$4));
            if (sTAdjCoordinate2 == null) {
                sTAdjCoordinate2 = (STAdjCoordinate)((Object)this.get_store().add_attribute_user(MINX$4));
            }
            sTAdjCoordinate2.set(sTAdjCoordinate);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetMinX() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_attribute(MINX$4);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public Object getMaxX() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(MAXX$6));
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
    public STAdjCoordinate xgetMaxX() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STAdjCoordinate sTAdjCoordinate = null;
            sTAdjCoordinate = (STAdjCoordinate)((Object)this.get_store().find_attribute_user(MAXX$6));
            return sTAdjCoordinate;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetMaxX() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().find_attribute_user(MAXX$6) != null;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setMaxX(Object object) {
        Object object2 = this.monitor();
        synchronized (object2) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(MAXX$6));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(MAXX$6));
            }
            simpleValue.setObjectValue(object);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetMaxX(STAdjCoordinate sTAdjCoordinate) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STAdjCoordinate sTAdjCoordinate2 = null;
            sTAdjCoordinate2 = (STAdjCoordinate)((Object)this.get_store().find_attribute_user(MAXX$6));
            if (sTAdjCoordinate2 == null) {
                sTAdjCoordinate2 = (STAdjCoordinate)((Object)this.get_store().add_attribute_user(MAXX$6));
            }
            sTAdjCoordinate2.set(sTAdjCoordinate);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetMaxX() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_attribute(MAXX$6);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public String getGdRefY() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(GDREFY$8));
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
    public STGeomGuideName xgetGdRefY() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STGeomGuideName sTGeomGuideName = null;
            sTGeomGuideName = (STGeomGuideName)((Object)this.get_store().find_attribute_user(GDREFY$8));
            return sTGeomGuideName;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetGdRefY() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().find_attribute_user(GDREFY$8) != null;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setGdRefY(String string) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(GDREFY$8));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(GDREFY$8));
            }
            simpleValue.setStringValue(string);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetGdRefY(STGeomGuideName sTGeomGuideName) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STGeomGuideName sTGeomGuideName2 = null;
            sTGeomGuideName2 = (STGeomGuideName)((Object)this.get_store().find_attribute_user(GDREFY$8));
            if (sTGeomGuideName2 == null) {
                sTGeomGuideName2 = (STGeomGuideName)((Object)this.get_store().add_attribute_user(GDREFY$8));
            }
            sTGeomGuideName2.set(sTGeomGuideName);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetGdRefY() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_attribute(GDREFY$8);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public Object getMinY() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(MINY$10));
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
    public STAdjCoordinate xgetMinY() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STAdjCoordinate sTAdjCoordinate = null;
            sTAdjCoordinate = (STAdjCoordinate)((Object)this.get_store().find_attribute_user(MINY$10));
            return sTAdjCoordinate;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetMinY() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().find_attribute_user(MINY$10) != null;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setMinY(Object object) {
        Object object2 = this.monitor();
        synchronized (object2) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(MINY$10));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(MINY$10));
            }
            simpleValue.setObjectValue(object);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetMinY(STAdjCoordinate sTAdjCoordinate) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STAdjCoordinate sTAdjCoordinate2 = null;
            sTAdjCoordinate2 = (STAdjCoordinate)((Object)this.get_store().find_attribute_user(MINY$10));
            if (sTAdjCoordinate2 == null) {
                sTAdjCoordinate2 = (STAdjCoordinate)((Object)this.get_store().add_attribute_user(MINY$10));
            }
            sTAdjCoordinate2.set(sTAdjCoordinate);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetMinY() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_attribute(MINY$10);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public Object getMaxY() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(MAXY$12));
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
    public STAdjCoordinate xgetMaxY() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STAdjCoordinate sTAdjCoordinate = null;
            sTAdjCoordinate = (STAdjCoordinate)((Object)this.get_store().find_attribute_user(MAXY$12));
            return sTAdjCoordinate;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetMaxY() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().find_attribute_user(MAXY$12) != null;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setMaxY(Object object) {
        Object object2 = this.monitor();
        synchronized (object2) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(MAXY$12));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(MAXY$12));
            }
            simpleValue.setObjectValue(object);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetMaxY(STAdjCoordinate sTAdjCoordinate) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STAdjCoordinate sTAdjCoordinate2 = null;
            sTAdjCoordinate2 = (STAdjCoordinate)((Object)this.get_store().find_attribute_user(MAXY$12));
            if (sTAdjCoordinate2 == null) {
                sTAdjCoordinate2 = (STAdjCoordinate)((Object)this.get_store().add_attribute_user(MAXY$12));
            }
            sTAdjCoordinate2.set(sTAdjCoordinate);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetMaxY() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_attribute(MAXY$12);
        }
    }
}

