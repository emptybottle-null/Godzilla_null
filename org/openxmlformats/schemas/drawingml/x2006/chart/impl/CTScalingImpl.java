/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.openxmlformats.schemas.drawingml.x2006.chart.impl;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTDouble;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTExtensionList;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTLogBase;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTOrientation;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTScaling;

public class CTScalingImpl
extends XmlComplexContentImpl
implements CTScaling {
    private static final long serialVersionUID = 1L;
    private static final QName LOGBASE$0 = new QName("http://schemas.openxmlformats.org/drawingml/2006/chart", "logBase");
    private static final QName ORIENTATION$2 = new QName("http://schemas.openxmlformats.org/drawingml/2006/chart", "orientation");
    private static final QName MAX$4 = new QName("http://schemas.openxmlformats.org/drawingml/2006/chart", "max");
    private static final QName MIN$6 = new QName("http://schemas.openxmlformats.org/drawingml/2006/chart", "min");
    private static final QName EXTLST$8 = new QName("http://schemas.openxmlformats.org/drawingml/2006/chart", "extLst");

    public CTScalingImpl(SchemaType schemaType) {
        super(schemaType);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTLogBase getLogBase() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTLogBase cTLogBase = null;
            cTLogBase = (CTLogBase)((Object)this.get_store().find_element_user(LOGBASE$0, 0));
            if (cTLogBase == null) {
                return null;
            }
            return cTLogBase;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetLogBase() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(LOGBASE$0) != 0;
        }
    }

    @Override
    public void setLogBase(CTLogBase cTLogBase) {
        this.generatedSetterHelperImpl(cTLogBase, LOGBASE$0, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTLogBase addNewLogBase() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTLogBase cTLogBase = null;
            cTLogBase = (CTLogBase)((Object)this.get_store().add_element_user(LOGBASE$0));
            return cTLogBase;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetLogBase() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(LOGBASE$0, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTOrientation getOrientation() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTOrientation cTOrientation = null;
            cTOrientation = (CTOrientation)((Object)this.get_store().find_element_user(ORIENTATION$2, 0));
            if (cTOrientation == null) {
                return null;
            }
            return cTOrientation;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetOrientation() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(ORIENTATION$2) != 0;
        }
    }

    @Override
    public void setOrientation(CTOrientation cTOrientation) {
        this.generatedSetterHelperImpl(cTOrientation, ORIENTATION$2, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTOrientation addNewOrientation() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTOrientation cTOrientation = null;
            cTOrientation = (CTOrientation)((Object)this.get_store().add_element_user(ORIENTATION$2));
            return cTOrientation;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetOrientation() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(ORIENTATION$2, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTDouble getMax() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTDouble cTDouble = null;
            cTDouble = (CTDouble)((Object)this.get_store().find_element_user(MAX$4, 0));
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
    public boolean isSetMax() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(MAX$4) != 0;
        }
    }

    @Override
    public void setMax(CTDouble cTDouble) {
        this.generatedSetterHelperImpl(cTDouble, MAX$4, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTDouble addNewMax() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTDouble cTDouble = null;
            cTDouble = (CTDouble)((Object)this.get_store().add_element_user(MAX$4));
            return cTDouble;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetMax() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(MAX$4, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTDouble getMin() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTDouble cTDouble = null;
            cTDouble = (CTDouble)((Object)this.get_store().find_element_user(MIN$6, 0));
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
    public boolean isSetMin() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(MIN$6) != 0;
        }
    }

    @Override
    public void setMin(CTDouble cTDouble) {
        this.generatedSetterHelperImpl(cTDouble, MIN$6, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTDouble addNewMin() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTDouble cTDouble = null;
            cTDouble = (CTDouble)((Object)this.get_store().add_element_user(MIN$6));
            return cTDouble;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetMin() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(MIN$6, 0);
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
            cTExtensionList = (CTExtensionList)((Object)this.get_store().find_element_user(EXTLST$8, 0));
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
            return this.get_store().count_elements(EXTLST$8) != 0;
        }
    }

    @Override
    public void setExtLst(CTExtensionList cTExtensionList) {
        this.generatedSetterHelperImpl(cTExtensionList, EXTLST$8, 0, (short)1);
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
            cTExtensionList = (CTExtensionList)((Object)this.get_store().add_element_user(EXTLST$8));
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
            this.get_store().remove_element(EXTLST$8, 0);
        }
    }
}

