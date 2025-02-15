/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.openxmlformats.schemas.drawingml.x2006.chart.impl;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTDouble;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTExtensionList;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTLayoutMode;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTLayoutTarget;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTManualLayout;

public class CTManualLayoutImpl
extends XmlComplexContentImpl
implements CTManualLayout {
    private static final long serialVersionUID = 1L;
    private static final QName LAYOUTTARGET$0 = new QName("http://schemas.openxmlformats.org/drawingml/2006/chart", "layoutTarget");
    private static final QName XMODE$2 = new QName("http://schemas.openxmlformats.org/drawingml/2006/chart", "xMode");
    private static final QName YMODE$4 = new QName("http://schemas.openxmlformats.org/drawingml/2006/chart", "yMode");
    private static final QName WMODE$6 = new QName("http://schemas.openxmlformats.org/drawingml/2006/chart", "wMode");
    private static final QName HMODE$8 = new QName("http://schemas.openxmlformats.org/drawingml/2006/chart", "hMode");
    private static final QName X$10 = new QName("http://schemas.openxmlformats.org/drawingml/2006/chart", "x");
    private static final QName Y$12 = new QName("http://schemas.openxmlformats.org/drawingml/2006/chart", "y");
    private static final QName W$14 = new QName("http://schemas.openxmlformats.org/drawingml/2006/chart", "w");
    private static final QName H$16 = new QName("http://schemas.openxmlformats.org/drawingml/2006/chart", "h");
    private static final QName EXTLST$18 = new QName("http://schemas.openxmlformats.org/drawingml/2006/chart", "extLst");

    public CTManualLayoutImpl(SchemaType schemaType) {
        super(schemaType);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTLayoutTarget getLayoutTarget() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTLayoutTarget cTLayoutTarget = null;
            cTLayoutTarget = (CTLayoutTarget)((Object)this.get_store().find_element_user(LAYOUTTARGET$0, 0));
            if (cTLayoutTarget == null) {
                return null;
            }
            return cTLayoutTarget;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetLayoutTarget() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(LAYOUTTARGET$0) != 0;
        }
    }

    @Override
    public void setLayoutTarget(CTLayoutTarget cTLayoutTarget) {
        this.generatedSetterHelperImpl(cTLayoutTarget, LAYOUTTARGET$0, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTLayoutTarget addNewLayoutTarget() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTLayoutTarget cTLayoutTarget = null;
            cTLayoutTarget = (CTLayoutTarget)((Object)this.get_store().add_element_user(LAYOUTTARGET$0));
            return cTLayoutTarget;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetLayoutTarget() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(LAYOUTTARGET$0, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTLayoutMode getXMode() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTLayoutMode cTLayoutMode = null;
            cTLayoutMode = (CTLayoutMode)((Object)this.get_store().find_element_user(XMODE$2, 0));
            if (cTLayoutMode == null) {
                return null;
            }
            return cTLayoutMode;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetXMode() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(XMODE$2) != 0;
        }
    }

    @Override
    public void setXMode(CTLayoutMode cTLayoutMode) {
        this.generatedSetterHelperImpl(cTLayoutMode, XMODE$2, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTLayoutMode addNewXMode() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTLayoutMode cTLayoutMode = null;
            cTLayoutMode = (CTLayoutMode)((Object)this.get_store().add_element_user(XMODE$2));
            return cTLayoutMode;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetXMode() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(XMODE$2, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTLayoutMode getYMode() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTLayoutMode cTLayoutMode = null;
            cTLayoutMode = (CTLayoutMode)((Object)this.get_store().find_element_user(YMODE$4, 0));
            if (cTLayoutMode == null) {
                return null;
            }
            return cTLayoutMode;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetYMode() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(YMODE$4) != 0;
        }
    }

    @Override
    public void setYMode(CTLayoutMode cTLayoutMode) {
        this.generatedSetterHelperImpl(cTLayoutMode, YMODE$4, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTLayoutMode addNewYMode() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTLayoutMode cTLayoutMode = null;
            cTLayoutMode = (CTLayoutMode)((Object)this.get_store().add_element_user(YMODE$4));
            return cTLayoutMode;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetYMode() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(YMODE$4, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTLayoutMode getWMode() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTLayoutMode cTLayoutMode = null;
            cTLayoutMode = (CTLayoutMode)((Object)this.get_store().find_element_user(WMODE$6, 0));
            if (cTLayoutMode == null) {
                return null;
            }
            return cTLayoutMode;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetWMode() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(WMODE$6) != 0;
        }
    }

    @Override
    public void setWMode(CTLayoutMode cTLayoutMode) {
        this.generatedSetterHelperImpl(cTLayoutMode, WMODE$6, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTLayoutMode addNewWMode() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTLayoutMode cTLayoutMode = null;
            cTLayoutMode = (CTLayoutMode)((Object)this.get_store().add_element_user(WMODE$6));
            return cTLayoutMode;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetWMode() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(WMODE$6, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTLayoutMode getHMode() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTLayoutMode cTLayoutMode = null;
            cTLayoutMode = (CTLayoutMode)((Object)this.get_store().find_element_user(HMODE$8, 0));
            if (cTLayoutMode == null) {
                return null;
            }
            return cTLayoutMode;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetHMode() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(HMODE$8) != 0;
        }
    }

    @Override
    public void setHMode(CTLayoutMode cTLayoutMode) {
        this.generatedSetterHelperImpl(cTLayoutMode, HMODE$8, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTLayoutMode addNewHMode() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTLayoutMode cTLayoutMode = null;
            cTLayoutMode = (CTLayoutMode)((Object)this.get_store().add_element_user(HMODE$8));
            return cTLayoutMode;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetHMode() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(HMODE$8, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTDouble getX() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTDouble cTDouble = null;
            cTDouble = (CTDouble)((Object)this.get_store().find_element_user(X$10, 0));
            if (cTDouble == null) {
                return null;
            }
            return cTDouble;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetX() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(X$10) != 0;
        }
    }

    @Override
    public void setX(CTDouble cTDouble) {
        this.generatedSetterHelperImpl(cTDouble, X$10, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTDouble addNewX() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTDouble cTDouble = null;
            cTDouble = (CTDouble)((Object)this.get_store().add_element_user(X$10));
            return cTDouble;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetX() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(X$10, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTDouble getY() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTDouble cTDouble = null;
            cTDouble = (CTDouble)((Object)this.get_store().find_element_user(Y$12, 0));
            if (cTDouble == null) {
                return null;
            }
            return cTDouble;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetY() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(Y$12) != 0;
        }
    }

    @Override
    public void setY(CTDouble cTDouble) {
        this.generatedSetterHelperImpl(cTDouble, Y$12, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTDouble addNewY() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTDouble cTDouble = null;
            cTDouble = (CTDouble)((Object)this.get_store().add_element_user(Y$12));
            return cTDouble;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetY() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(Y$12, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTDouble getW() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTDouble cTDouble = null;
            cTDouble = (CTDouble)((Object)this.get_store().find_element_user(W$14, 0));
            if (cTDouble == null) {
                return null;
            }
            return cTDouble;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetW() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(W$14) != 0;
        }
    }

    @Override
    public void setW(CTDouble cTDouble) {
        this.generatedSetterHelperImpl(cTDouble, W$14, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTDouble addNewW() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTDouble cTDouble = null;
            cTDouble = (CTDouble)((Object)this.get_store().add_element_user(W$14));
            return cTDouble;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetW() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(W$14, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTDouble getH() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTDouble cTDouble = null;
            cTDouble = (CTDouble)((Object)this.get_store().find_element_user(H$16, 0));
            if (cTDouble == null) {
                return null;
            }
            return cTDouble;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetH() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(H$16) != 0;
        }
    }

    @Override
    public void setH(CTDouble cTDouble) {
        this.generatedSetterHelperImpl(cTDouble, H$16, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTDouble addNewH() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTDouble cTDouble = null;
            cTDouble = (CTDouble)((Object)this.get_store().add_element_user(H$16));
            return cTDouble;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetH() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(H$16, 0);
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
            cTExtensionList = (CTExtensionList)((Object)this.get_store().find_element_user(EXTLST$18, 0));
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
            return this.get_store().count_elements(EXTLST$18) != 0;
        }
    }

    @Override
    public void setExtLst(CTExtensionList cTExtensionList) {
        this.generatedSetterHelperImpl(cTExtensionList, EXTLST$18, 0, (short)1);
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
            cTExtensionList = (CTExtensionList)((Object)this.get_store().add_element_user(EXTLST$18));
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
            this.get_store().remove_element(EXTLST$18, 0);
        }
    }
}

