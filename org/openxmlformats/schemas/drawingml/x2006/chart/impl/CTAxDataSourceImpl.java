/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  org.openxmlformats.schemas.drawingml.x2006.chart.CTMultiLvlStrRef
 */
package org.openxmlformats.schemas.drawingml.x2006.chart.impl;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTAxDataSource;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTMultiLvlStrRef;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTNumData;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTNumRef;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTStrData;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTStrRef;

public class CTAxDataSourceImpl
extends XmlComplexContentImpl
implements CTAxDataSource {
    private static final long serialVersionUID = 1L;
    private static final QName MULTILVLSTRREF$0 = new QName("http://schemas.openxmlformats.org/drawingml/2006/chart", "multiLvlStrRef");
    private static final QName NUMREF$2 = new QName("http://schemas.openxmlformats.org/drawingml/2006/chart", "numRef");
    private static final QName NUMLIT$4 = new QName("http://schemas.openxmlformats.org/drawingml/2006/chart", "numLit");
    private static final QName STRREF$6 = new QName("http://schemas.openxmlformats.org/drawingml/2006/chart", "strRef");
    private static final QName STRLIT$8 = new QName("http://schemas.openxmlformats.org/drawingml/2006/chart", "strLit");

    public CTAxDataSourceImpl(SchemaType schemaType) {
        super(schemaType);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTMultiLvlStrRef getMultiLvlStrRef() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTMultiLvlStrRef cTMultiLvlStrRef = null;
            cTMultiLvlStrRef = (CTMultiLvlStrRef)this.get_store().find_element_user(MULTILVLSTRREF$0, 0);
            if (cTMultiLvlStrRef == null) {
                return null;
            }
            return cTMultiLvlStrRef;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetMultiLvlStrRef() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(MULTILVLSTRREF$0) != 0;
        }
    }

    @Override
    public void setMultiLvlStrRef(CTMultiLvlStrRef cTMultiLvlStrRef) {
        this.generatedSetterHelperImpl((XmlObject)cTMultiLvlStrRef, MULTILVLSTRREF$0, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTMultiLvlStrRef addNewMultiLvlStrRef() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTMultiLvlStrRef cTMultiLvlStrRef = null;
            cTMultiLvlStrRef = (CTMultiLvlStrRef)this.get_store().add_element_user(MULTILVLSTRREF$0);
            return cTMultiLvlStrRef;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetMultiLvlStrRef() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(MULTILVLSTRREF$0, 0);
        }
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
            cTNumRef = (CTNumRef)((Object)this.get_store().find_element_user(NUMREF$2, 0));
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
            return this.get_store().count_elements(NUMREF$2) != 0;
        }
    }

    @Override
    public void setNumRef(CTNumRef cTNumRef) {
        this.generatedSetterHelperImpl(cTNumRef, NUMREF$2, 0, (short)1);
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
            cTNumRef = (CTNumRef)((Object)this.get_store().add_element_user(NUMREF$2));
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
            this.get_store().remove_element(NUMREF$2, 0);
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
            cTNumData = (CTNumData)((Object)this.get_store().find_element_user(NUMLIT$4, 0));
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
            return this.get_store().count_elements(NUMLIT$4) != 0;
        }
    }

    @Override
    public void setNumLit(CTNumData cTNumData) {
        this.generatedSetterHelperImpl(cTNumData, NUMLIT$4, 0, (short)1);
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
            cTNumData = (CTNumData)((Object)this.get_store().add_element_user(NUMLIT$4));
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
            this.get_store().remove_element(NUMLIT$4, 0);
        }
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
            cTStrRef = (CTStrRef)((Object)this.get_store().find_element_user(STRREF$6, 0));
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
            return this.get_store().count_elements(STRREF$6) != 0;
        }
    }

    @Override
    public void setStrRef(CTStrRef cTStrRef) {
        this.generatedSetterHelperImpl(cTStrRef, STRREF$6, 0, (short)1);
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
            cTStrRef = (CTStrRef)((Object)this.get_store().add_element_user(STRREF$6));
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
            this.get_store().remove_element(STRREF$6, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTStrData getStrLit() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTStrData cTStrData = null;
            cTStrData = (CTStrData)((Object)this.get_store().find_element_user(STRLIT$8, 0));
            if (cTStrData == null) {
                return null;
            }
            return cTStrData;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetStrLit() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(STRLIT$8) != 0;
        }
    }

    @Override
    public void setStrLit(CTStrData cTStrData) {
        this.generatedSetterHelperImpl(cTStrData, STRLIT$8, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTStrData addNewStrLit() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTStrData cTStrData = null;
            cTStrData = (CTStrData)((Object)this.get_store().add_element_user(STRLIT$8));
            return cTStrData;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetStrLit() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(STRLIT$8, 0);
        }
    }
}

