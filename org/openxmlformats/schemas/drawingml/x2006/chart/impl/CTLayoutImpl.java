/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.openxmlformats.schemas.drawingml.x2006.chart.impl;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTExtensionList;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTLayout;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTManualLayout;

public class CTLayoutImpl
extends XmlComplexContentImpl
implements CTLayout {
    private static final long serialVersionUID = 1L;
    private static final QName MANUALLAYOUT$0 = new QName("http://schemas.openxmlformats.org/drawingml/2006/chart", "manualLayout");
    private static final QName EXTLST$2 = new QName("http://schemas.openxmlformats.org/drawingml/2006/chart", "extLst");

    public CTLayoutImpl(SchemaType schemaType) {
        super(schemaType);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTManualLayout getManualLayout() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTManualLayout cTManualLayout = null;
            cTManualLayout = (CTManualLayout)((Object)this.get_store().find_element_user(MANUALLAYOUT$0, 0));
            if (cTManualLayout == null) {
                return null;
            }
            return cTManualLayout;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetManualLayout() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(MANUALLAYOUT$0) != 0;
        }
    }

    @Override
    public void setManualLayout(CTManualLayout cTManualLayout) {
        this.generatedSetterHelperImpl(cTManualLayout, MANUALLAYOUT$0, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTManualLayout addNewManualLayout() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTManualLayout cTManualLayout = null;
            cTManualLayout = (CTManualLayout)((Object)this.get_store().add_element_user(MANUALLAYOUT$0));
            return cTManualLayout;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetManualLayout() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(MANUALLAYOUT$0, 0);
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
            cTExtensionList = (CTExtensionList)((Object)this.get_store().find_element_user(EXTLST$2, 0));
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
            return this.get_store().count_elements(EXTLST$2) != 0;
        }
    }

    @Override
    public void setExtLst(CTExtensionList cTExtensionList) {
        this.generatedSetterHelperImpl(cTExtensionList, EXTLST$2, 0, (short)1);
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
            cTExtensionList = (CTExtensionList)((Object)this.get_store().add_element_user(EXTLST$2));
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
            this.get_store().remove_element(EXTLST$2, 0);
        }
    }
}

