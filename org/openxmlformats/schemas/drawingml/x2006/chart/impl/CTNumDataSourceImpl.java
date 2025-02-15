/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.openxmlformats.schemas.drawingml.x2006.chart.impl;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTNumData;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTNumDataSource;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTNumRef;

public class CTNumDataSourceImpl
extends XmlComplexContentImpl
implements CTNumDataSource {
    private static final long serialVersionUID = 1L;
    private static final QName NUMREF$0 = new QName("http://schemas.openxmlformats.org/drawingml/2006/chart", "numRef");
    private static final QName NUMLIT$2 = new QName("http://schemas.openxmlformats.org/drawingml/2006/chart", "numLit");

    public CTNumDataSourceImpl(SchemaType schemaType) {
        super(schemaType);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTNumRef getNumRef() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTNumRef cTNumRef = null;
            cTNumRef = (CTNumRef)((Object)this.get_store().find_element_user(NUMREF$0, 0));
            if (cTNumRef == null) {
                return null;
            }
            return cTNumRef;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetNumRef() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(NUMREF$0) != 0;
        }
    }

    @Override
    public void setNumRef(CTNumRef cTNumRef) {
        this.generatedSetterHelperImpl(cTNumRef, NUMREF$0, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTNumRef addNewNumRef() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTNumRef cTNumRef = null;
            cTNumRef = (CTNumRef)((Object)this.get_store().add_element_user(NUMREF$0));
            return cTNumRef;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetNumRef() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(NUMREF$0, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTNumData getNumLit() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTNumData cTNumData = null;
            cTNumData = (CTNumData)((Object)this.get_store().find_element_user(NUMLIT$2, 0));
            if (cTNumData == null) {
                return null;
            }
            return cTNumData;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetNumLit() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(NUMLIT$2) != 0;
        }
    }

    @Override
    public void setNumLit(CTNumData cTNumData) {
        this.generatedSetterHelperImpl(cTNumData, NUMLIT$2, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTNumData addNewNumLit() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTNumData cTNumData = null;
            cTNumData = (CTNumData)((Object)this.get_store().add_element_user(NUMLIT$2));
            return cTNumData;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetNumLit() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(NUMLIT$2, 0);
        }
    }
}

