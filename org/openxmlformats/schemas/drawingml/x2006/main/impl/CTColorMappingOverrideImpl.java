/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.openxmlformats.schemas.drawingml.x2006.main.impl;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.drawingml.x2006.main.CTColorMapping;
import org.openxmlformats.schemas.drawingml.x2006.main.CTColorMappingOverride;
import org.openxmlformats.schemas.drawingml.x2006.main.CTEmptyElement;

public class CTColorMappingOverrideImpl
extends XmlComplexContentImpl
implements CTColorMappingOverride {
    private static final long serialVersionUID = 1L;
    private static final QName MASTERCLRMAPPING$0 = new QName("http://schemas.openxmlformats.org/drawingml/2006/main", "masterClrMapping");
    private static final QName OVERRIDECLRMAPPING$2 = new QName("http://schemas.openxmlformats.org/drawingml/2006/main", "overrideClrMapping");

    public CTColorMappingOverrideImpl(SchemaType schemaType) {
        super(schemaType);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTEmptyElement getMasterClrMapping() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTEmptyElement cTEmptyElement = null;
            cTEmptyElement = (CTEmptyElement)((Object)this.get_store().find_element_user(MASTERCLRMAPPING$0, 0));
            if (cTEmptyElement == null) {
                return null;
            }
            return cTEmptyElement;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetMasterClrMapping() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(MASTERCLRMAPPING$0) != 0;
        }
    }

    @Override
    public void setMasterClrMapping(CTEmptyElement cTEmptyElement) {
        this.generatedSetterHelperImpl(cTEmptyElement, MASTERCLRMAPPING$0, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTEmptyElement addNewMasterClrMapping() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTEmptyElement cTEmptyElement = null;
            cTEmptyElement = (CTEmptyElement)((Object)this.get_store().add_element_user(MASTERCLRMAPPING$0));
            return cTEmptyElement;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetMasterClrMapping() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(MASTERCLRMAPPING$0, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTColorMapping getOverrideClrMapping() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTColorMapping cTColorMapping = null;
            cTColorMapping = (CTColorMapping)((Object)this.get_store().find_element_user(OVERRIDECLRMAPPING$2, 0));
            if (cTColorMapping == null) {
                return null;
            }
            return cTColorMapping;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetOverrideClrMapping() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(OVERRIDECLRMAPPING$2) != 0;
        }
    }

    @Override
    public void setOverrideClrMapping(CTColorMapping cTColorMapping) {
        this.generatedSetterHelperImpl(cTColorMapping, OVERRIDECLRMAPPING$2, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTColorMapping addNewOverrideClrMapping() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTColorMapping cTColorMapping = null;
            cTColorMapping = (CTColorMapping)((Object)this.get_store().add_element_user(OVERRIDECLRMAPPING$2));
            return cTColorMapping;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetOverrideClrMapping() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(OVERRIDECLRMAPPING$2, 0);
        }
    }
}

