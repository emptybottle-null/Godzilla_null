/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.openxmlformats.schemas.drawingml.x2006.chart.impl;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTStrRef;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTTx;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextBody;

public class CTTxImpl
extends XmlComplexContentImpl
implements CTTx {
    private static final long serialVersionUID = 1L;
    private static final QName STRREF$0 = new QName("http://schemas.openxmlformats.org/drawingml/2006/chart", "strRef");
    private static final QName RICH$2 = new QName("http://schemas.openxmlformats.org/drawingml/2006/chart", "rich");

    public CTTxImpl(SchemaType schemaType) {
        super(schemaType);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTStrRef getStrRef() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTStrRef cTStrRef = null;
            cTStrRef = (CTStrRef)((Object)this.get_store().find_element_user(STRREF$0, 0));
            if (cTStrRef == null) {
                return null;
            }
            return cTStrRef;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetStrRef() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(STRREF$0) != 0;
        }
    }

    @Override
    public void setStrRef(CTStrRef cTStrRef) {
        this.generatedSetterHelperImpl(cTStrRef, STRREF$0, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTStrRef addNewStrRef() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTStrRef cTStrRef = null;
            cTStrRef = (CTStrRef)((Object)this.get_store().add_element_user(STRREF$0));
            return cTStrRef;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetStrRef() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(STRREF$0, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTTextBody getRich() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTTextBody cTTextBody = null;
            cTTextBody = (CTTextBody)((Object)this.get_store().find_element_user(RICH$2, 0));
            if (cTTextBody == null) {
                return null;
            }
            return cTTextBody;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetRich() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(RICH$2) != 0;
        }
    }

    @Override
    public void setRich(CTTextBody cTTextBody) {
        this.generatedSetterHelperImpl(cTTextBody, RICH$2, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTTextBody addNewRich() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTTextBody cTTextBody = null;
            cTTextBody = (CTTextBody)((Object)this.get_store().add_element_user(RICH$2));
            return cTTextBody;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetRich() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(RICH$2, 0);
        }
    }
}

