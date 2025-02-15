/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.openxmlformats.schemas.drawingml.x2006.chart.impl;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTBoolean;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTDepthPercent;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTExtensionList;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTHPercent;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTPerspective;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTRotX;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTRotY;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTView3D;

public class CTView3DImpl
extends XmlComplexContentImpl
implements CTView3D {
    private static final long serialVersionUID = 1L;
    private static final QName ROTX$0 = new QName("http://schemas.openxmlformats.org/drawingml/2006/chart", "rotX");
    private static final QName HPERCENT$2 = new QName("http://schemas.openxmlformats.org/drawingml/2006/chart", "hPercent");
    private static final QName ROTY$4 = new QName("http://schemas.openxmlformats.org/drawingml/2006/chart", "rotY");
    private static final QName DEPTHPERCENT$6 = new QName("http://schemas.openxmlformats.org/drawingml/2006/chart", "depthPercent");
    private static final QName RANGAX$8 = new QName("http://schemas.openxmlformats.org/drawingml/2006/chart", "rAngAx");
    private static final QName PERSPECTIVE$10 = new QName("http://schemas.openxmlformats.org/drawingml/2006/chart", "perspective");
    private static final QName EXTLST$12 = new QName("http://schemas.openxmlformats.org/drawingml/2006/chart", "extLst");

    public CTView3DImpl(SchemaType schemaType) {
        super(schemaType);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTRotX getRotX() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTRotX cTRotX = null;
            cTRotX = (CTRotX)((Object)this.get_store().find_element_user(ROTX$0, 0));
            if (cTRotX == null) {
                return null;
            }
            return cTRotX;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetRotX() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(ROTX$0) != 0;
        }
    }

    @Override
    public void setRotX(CTRotX cTRotX) {
        this.generatedSetterHelperImpl(cTRotX, ROTX$0, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTRotX addNewRotX() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTRotX cTRotX = null;
            cTRotX = (CTRotX)((Object)this.get_store().add_element_user(ROTX$0));
            return cTRotX;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetRotX() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(ROTX$0, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTHPercent getHPercent() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTHPercent cTHPercent = null;
            cTHPercent = (CTHPercent)((Object)this.get_store().find_element_user(HPERCENT$2, 0));
            if (cTHPercent == null) {
                return null;
            }
            return cTHPercent;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetHPercent() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(HPERCENT$2) != 0;
        }
    }

    @Override
    public void setHPercent(CTHPercent cTHPercent) {
        this.generatedSetterHelperImpl(cTHPercent, HPERCENT$2, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTHPercent addNewHPercent() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTHPercent cTHPercent = null;
            cTHPercent = (CTHPercent)((Object)this.get_store().add_element_user(HPERCENT$2));
            return cTHPercent;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetHPercent() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(HPERCENT$2, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTRotY getRotY() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTRotY cTRotY = null;
            cTRotY = (CTRotY)((Object)this.get_store().find_element_user(ROTY$4, 0));
            if (cTRotY == null) {
                return null;
            }
            return cTRotY;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetRotY() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(ROTY$4) != 0;
        }
    }

    @Override
    public void setRotY(CTRotY cTRotY) {
        this.generatedSetterHelperImpl(cTRotY, ROTY$4, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTRotY addNewRotY() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTRotY cTRotY = null;
            cTRotY = (CTRotY)((Object)this.get_store().add_element_user(ROTY$4));
            return cTRotY;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetRotY() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(ROTY$4, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTDepthPercent getDepthPercent() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTDepthPercent cTDepthPercent = null;
            cTDepthPercent = (CTDepthPercent)((Object)this.get_store().find_element_user(DEPTHPERCENT$6, 0));
            if (cTDepthPercent == null) {
                return null;
            }
            return cTDepthPercent;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetDepthPercent() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(DEPTHPERCENT$6) != 0;
        }
    }

    @Override
    public void setDepthPercent(CTDepthPercent cTDepthPercent) {
        this.generatedSetterHelperImpl(cTDepthPercent, DEPTHPERCENT$6, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTDepthPercent addNewDepthPercent() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTDepthPercent cTDepthPercent = null;
            cTDepthPercent = (CTDepthPercent)((Object)this.get_store().add_element_user(DEPTHPERCENT$6));
            return cTDepthPercent;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetDepthPercent() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(DEPTHPERCENT$6, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTBoolean getRAngAx() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTBoolean cTBoolean = null;
            cTBoolean = (CTBoolean)((Object)this.get_store().find_element_user(RANGAX$8, 0));
            if (cTBoolean == null) {
                return null;
            }
            return cTBoolean;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetRAngAx() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(RANGAX$8) != 0;
        }
    }

    @Override
    public void setRAngAx(CTBoolean cTBoolean) {
        this.generatedSetterHelperImpl(cTBoolean, RANGAX$8, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTBoolean addNewRAngAx() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTBoolean cTBoolean = null;
            cTBoolean = (CTBoolean)((Object)this.get_store().add_element_user(RANGAX$8));
            return cTBoolean;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetRAngAx() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(RANGAX$8, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTPerspective getPerspective() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTPerspective cTPerspective = null;
            cTPerspective = (CTPerspective)((Object)this.get_store().find_element_user(PERSPECTIVE$10, 0));
            if (cTPerspective == null) {
                return null;
            }
            return cTPerspective;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetPerspective() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(PERSPECTIVE$10) != 0;
        }
    }

    @Override
    public void setPerspective(CTPerspective cTPerspective) {
        this.generatedSetterHelperImpl(cTPerspective, PERSPECTIVE$10, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTPerspective addNewPerspective() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTPerspective cTPerspective = null;
            cTPerspective = (CTPerspective)((Object)this.get_store().add_element_user(PERSPECTIVE$10));
            return cTPerspective;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetPerspective() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(PERSPECTIVE$10, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTExtensionList getExtLst() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTExtensionList cTExtensionList = null;
            cTExtensionList = (CTExtensionList)((Object)this.get_store().find_element_user(EXTLST$12, 0));
            if (cTExtensionList == null) {
                return null;
            }
            return cTExtensionList;
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
            return this.get_store().count_elements(EXTLST$12) != 0;
        }
    }

    @Override
    public void setExtLst(CTExtensionList cTExtensionList) {
        this.generatedSetterHelperImpl(cTExtensionList, EXTLST$12, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTExtensionList addNewExtLst() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTExtensionList cTExtensionList = null;
            cTExtensionList = (CTExtensionList)((Object)this.get_store().add_element_user(EXTLST$12));
            return cTExtensionList;
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
            this.get_store().remove_element(EXTLST$12, 0);
        }
    }
}

