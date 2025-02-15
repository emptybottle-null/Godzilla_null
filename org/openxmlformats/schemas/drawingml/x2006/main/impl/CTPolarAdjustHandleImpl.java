/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  org.openxmlformats.schemas.drawingml.x2006.main.STAdjAngle
 */
package org.openxmlformats.schemas.drawingml.x2006.main.impl;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.drawingml.x2006.main.CTAdjPoint2D;
import org.openxmlformats.schemas.drawingml.x2006.main.CTPolarAdjustHandle;
import org.openxmlformats.schemas.drawingml.x2006.main.STAdjAngle;
import org.openxmlformats.schemas.drawingml.x2006.main.STAdjCoordinate;
import org.openxmlformats.schemas.drawingml.x2006.main.STGeomGuideName;

public class CTPolarAdjustHandleImpl
extends XmlComplexContentImpl
implements CTPolarAdjustHandle {
    private static final long serialVersionUID = 1L;
    private static final QName POS$0 = new QName("http://schemas.openxmlformats.org/drawingml/2006/main", "pos");
    private static final QName GDREFR$2 = new QName("", "gdRefR");
    private static final QName MINR$4 = new QName("", "minR");
    private static final QName MAXR$6 = new QName("", "maxR");
    private static final QName GDREFANG$8 = new QName("", "gdRefAng");
    private static final QName MINANG$10 = new QName("", "minAng");
    private static final QName MAXANG$12 = new QName("", "maxAng");

    public CTPolarAdjustHandleImpl(SchemaType schemaType) {
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
    public String getGdRefR() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(GDREFR$2));
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
    public STGeomGuideName xgetGdRefR() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STGeomGuideName sTGeomGuideName = null;
            sTGeomGuideName = (STGeomGuideName)((Object)this.get_store().find_attribute_user(GDREFR$2));
            return sTGeomGuideName;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetGdRefR() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().find_attribute_user(GDREFR$2) != null;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setGdRefR(String string) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(GDREFR$2));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(GDREFR$2));
            }
            simpleValue.setStringValue(string);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetGdRefR(STGeomGuideName sTGeomGuideName) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STGeomGuideName sTGeomGuideName2 = null;
            sTGeomGuideName2 = (STGeomGuideName)((Object)this.get_store().find_attribute_user(GDREFR$2));
            if (sTGeomGuideName2 == null) {
                sTGeomGuideName2 = (STGeomGuideName)((Object)this.get_store().add_attribute_user(GDREFR$2));
            }
            sTGeomGuideName2.set(sTGeomGuideName);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetGdRefR() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_attribute(GDREFR$2);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public Object getMinR() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(MINR$4));
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
    public STAdjCoordinate xgetMinR() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STAdjCoordinate sTAdjCoordinate = null;
            sTAdjCoordinate = (STAdjCoordinate)((Object)this.get_store().find_attribute_user(MINR$4));
            return sTAdjCoordinate;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetMinR() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().find_attribute_user(MINR$4) != null;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setMinR(Object object) {
        Object object2 = this.monitor();
        synchronized (object2) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(MINR$4));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(MINR$4));
            }
            simpleValue.setObjectValue(object);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetMinR(STAdjCoordinate sTAdjCoordinate) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STAdjCoordinate sTAdjCoordinate2 = null;
            sTAdjCoordinate2 = (STAdjCoordinate)((Object)this.get_store().find_attribute_user(MINR$4));
            if (sTAdjCoordinate2 == null) {
                sTAdjCoordinate2 = (STAdjCoordinate)((Object)this.get_store().add_attribute_user(MINR$4));
            }
            sTAdjCoordinate2.set(sTAdjCoordinate);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetMinR() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_attribute(MINR$4);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public Object getMaxR() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(MAXR$6));
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
    public STAdjCoordinate xgetMaxR() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STAdjCoordinate sTAdjCoordinate = null;
            sTAdjCoordinate = (STAdjCoordinate)((Object)this.get_store().find_attribute_user(MAXR$6));
            return sTAdjCoordinate;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetMaxR() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().find_attribute_user(MAXR$6) != null;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setMaxR(Object object) {
        Object object2 = this.monitor();
        synchronized (object2) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(MAXR$6));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(MAXR$6));
            }
            simpleValue.setObjectValue(object);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetMaxR(STAdjCoordinate sTAdjCoordinate) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STAdjCoordinate sTAdjCoordinate2 = null;
            sTAdjCoordinate2 = (STAdjCoordinate)((Object)this.get_store().find_attribute_user(MAXR$6));
            if (sTAdjCoordinate2 == null) {
                sTAdjCoordinate2 = (STAdjCoordinate)((Object)this.get_store().add_attribute_user(MAXR$6));
            }
            sTAdjCoordinate2.set(sTAdjCoordinate);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetMaxR() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_attribute(MAXR$6);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public String getGdRefAng() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(GDREFANG$8));
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
    public STGeomGuideName xgetGdRefAng() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STGeomGuideName sTGeomGuideName = null;
            sTGeomGuideName = (STGeomGuideName)((Object)this.get_store().find_attribute_user(GDREFANG$8));
            return sTGeomGuideName;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetGdRefAng() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().find_attribute_user(GDREFANG$8) != null;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setGdRefAng(String string) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(GDREFANG$8));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(GDREFANG$8));
            }
            simpleValue.setStringValue(string);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetGdRefAng(STGeomGuideName sTGeomGuideName) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STGeomGuideName sTGeomGuideName2 = null;
            sTGeomGuideName2 = (STGeomGuideName)((Object)this.get_store().find_attribute_user(GDREFANG$8));
            if (sTGeomGuideName2 == null) {
                sTGeomGuideName2 = (STGeomGuideName)((Object)this.get_store().add_attribute_user(GDREFANG$8));
            }
            sTGeomGuideName2.set(sTGeomGuideName);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetGdRefAng() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_attribute(GDREFANG$8);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public Object getMinAng() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(MINANG$10));
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
    public STAdjAngle xgetMinAng() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STAdjAngle sTAdjAngle = null;
            sTAdjAngle = (STAdjAngle)this.get_store().find_attribute_user(MINANG$10);
            return sTAdjAngle;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetMinAng() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().find_attribute_user(MINANG$10) != null;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setMinAng(Object object) {
        Object object2 = this.monitor();
        synchronized (object2) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(MINANG$10));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(MINANG$10));
            }
            simpleValue.setObjectValue(object);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetMinAng(STAdjAngle sTAdjAngle) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STAdjAngle sTAdjAngle2 = null;
            sTAdjAngle2 = (STAdjAngle)this.get_store().find_attribute_user(MINANG$10);
            if (sTAdjAngle2 == null) {
                sTAdjAngle2 = (STAdjAngle)this.get_store().add_attribute_user(MINANG$10);
            }
            sTAdjAngle2.set((XmlObject)sTAdjAngle);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetMinAng() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_attribute(MINANG$10);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public Object getMaxAng() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(MAXANG$12));
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
    public STAdjAngle xgetMaxAng() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STAdjAngle sTAdjAngle = null;
            sTAdjAngle = (STAdjAngle)this.get_store().find_attribute_user(MAXANG$12);
            return sTAdjAngle;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetMaxAng() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().find_attribute_user(MAXANG$12) != null;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setMaxAng(Object object) {
        Object object2 = this.monitor();
        synchronized (object2) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(MAXANG$12));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(MAXANG$12));
            }
            simpleValue.setObjectValue(object);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetMaxAng(STAdjAngle sTAdjAngle) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STAdjAngle sTAdjAngle2 = null;
            sTAdjAngle2 = (STAdjAngle)this.get_store().find_attribute_user(MAXANG$12);
            if (sTAdjAngle2 == null) {
                sTAdjAngle2 = (STAdjAngle)this.get_store().add_attribute_user(MAXANG$12);
            }
            sTAdjAngle2.set((XmlObject)sTAdjAngle);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetMaxAng() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_attribute(MAXANG$12);
        }
    }
}

