/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.openxmlformats.schemas.drawingml.x2006.main.impl;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.drawingml.x2006.main.CTAdjustHandleList;
import org.openxmlformats.schemas.drawingml.x2006.main.CTConnectionSiteList;
import org.openxmlformats.schemas.drawingml.x2006.main.CTCustomGeometry2D;
import org.openxmlformats.schemas.drawingml.x2006.main.CTGeomGuideList;
import org.openxmlformats.schemas.drawingml.x2006.main.CTGeomRect;
import org.openxmlformats.schemas.drawingml.x2006.main.CTPath2DList;

public class CTCustomGeometry2DImpl
extends XmlComplexContentImpl
implements CTCustomGeometry2D {
    private static final long serialVersionUID = 1L;
    private static final QName AVLST$0 = new QName("http://schemas.openxmlformats.org/drawingml/2006/main", "avLst");
    private static final QName GDLST$2 = new QName("http://schemas.openxmlformats.org/drawingml/2006/main", "gdLst");
    private static final QName AHLST$4 = new QName("http://schemas.openxmlformats.org/drawingml/2006/main", "ahLst");
    private static final QName CXNLST$6 = new QName("http://schemas.openxmlformats.org/drawingml/2006/main", "cxnLst");
    private static final QName RECT$8 = new QName("http://schemas.openxmlformats.org/drawingml/2006/main", "rect");
    private static final QName PATHLST$10 = new QName("http://schemas.openxmlformats.org/drawingml/2006/main", "pathLst");

    public CTCustomGeometry2DImpl(SchemaType schemaType) {
        super(schemaType);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTGeomGuideList getAvLst() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTGeomGuideList cTGeomGuideList = null;
            cTGeomGuideList = (CTGeomGuideList)((Object)this.get_store().find_element_user(AVLST$0, 0));
            if (cTGeomGuideList == null) {
                return null;
            }
            return cTGeomGuideList;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetAvLst() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(AVLST$0) != 0;
        }
    }

    @Override
    public void setAvLst(CTGeomGuideList cTGeomGuideList) {
        this.generatedSetterHelperImpl(cTGeomGuideList, AVLST$0, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTGeomGuideList addNewAvLst() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTGeomGuideList cTGeomGuideList = null;
            cTGeomGuideList = (CTGeomGuideList)((Object)this.get_store().add_element_user(AVLST$0));
            return cTGeomGuideList;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetAvLst() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(AVLST$0, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTGeomGuideList getGdLst() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTGeomGuideList cTGeomGuideList = null;
            cTGeomGuideList = (CTGeomGuideList)((Object)this.get_store().find_element_user(GDLST$2, 0));
            if (cTGeomGuideList == null) {
                return null;
            }
            return cTGeomGuideList;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetGdLst() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(GDLST$2) != 0;
        }
    }

    @Override
    public void setGdLst(CTGeomGuideList cTGeomGuideList) {
        this.generatedSetterHelperImpl(cTGeomGuideList, GDLST$2, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTGeomGuideList addNewGdLst() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTGeomGuideList cTGeomGuideList = null;
            cTGeomGuideList = (CTGeomGuideList)((Object)this.get_store().add_element_user(GDLST$2));
            return cTGeomGuideList;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetGdLst() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(GDLST$2, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTAdjustHandleList getAhLst() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTAdjustHandleList cTAdjustHandleList = null;
            cTAdjustHandleList = (CTAdjustHandleList)((Object)this.get_store().find_element_user(AHLST$4, 0));
            if (cTAdjustHandleList == null) {
                return null;
            }
            return cTAdjustHandleList;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetAhLst() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(AHLST$4) != 0;
        }
    }

    @Override
    public void setAhLst(CTAdjustHandleList cTAdjustHandleList) {
        this.generatedSetterHelperImpl(cTAdjustHandleList, AHLST$4, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTAdjustHandleList addNewAhLst() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTAdjustHandleList cTAdjustHandleList = null;
            cTAdjustHandleList = (CTAdjustHandleList)((Object)this.get_store().add_element_user(AHLST$4));
            return cTAdjustHandleList;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetAhLst() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(AHLST$4, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTConnectionSiteList getCxnLst() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTConnectionSiteList cTConnectionSiteList = null;
            cTConnectionSiteList = (CTConnectionSiteList)((Object)this.get_store().find_element_user(CXNLST$6, 0));
            if (cTConnectionSiteList == null) {
                return null;
            }
            return cTConnectionSiteList;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetCxnLst() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(CXNLST$6) != 0;
        }
    }

    @Override
    public void setCxnLst(CTConnectionSiteList cTConnectionSiteList) {
        this.generatedSetterHelperImpl(cTConnectionSiteList, CXNLST$6, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTConnectionSiteList addNewCxnLst() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTConnectionSiteList cTConnectionSiteList = null;
            cTConnectionSiteList = (CTConnectionSiteList)((Object)this.get_store().add_element_user(CXNLST$6));
            return cTConnectionSiteList;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetCxnLst() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(CXNLST$6, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTGeomRect getRect() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTGeomRect cTGeomRect = null;
            cTGeomRect = (CTGeomRect)((Object)this.get_store().find_element_user(RECT$8, 0));
            if (cTGeomRect == null) {
                return null;
            }
            return cTGeomRect;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetRect() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(RECT$8) != 0;
        }
    }

    @Override
    public void setRect(CTGeomRect cTGeomRect) {
        this.generatedSetterHelperImpl(cTGeomRect, RECT$8, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTGeomRect addNewRect() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTGeomRect cTGeomRect = null;
            cTGeomRect = (CTGeomRect)((Object)this.get_store().add_element_user(RECT$8));
            return cTGeomRect;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetRect() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(RECT$8, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTPath2DList getPathLst() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTPath2DList cTPath2DList = null;
            cTPath2DList = (CTPath2DList)((Object)this.get_store().find_element_user(PATHLST$10, 0));
            if (cTPath2DList == null) {
                return null;
            }
            return cTPath2DList;
        }
    }

    @Override
    public void setPathLst(CTPath2DList cTPath2DList) {
        this.generatedSetterHelperImpl(cTPath2DList, PATHLST$10, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTPath2DList addNewPathLst() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTPath2DList cTPath2DList = null;
            cTPath2DList = (CTPath2DList)((Object)this.get_store().add_element_user(PATHLST$10));
            return cTPath2DList;
        }
    }
}

