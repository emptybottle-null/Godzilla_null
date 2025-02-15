/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  org.openxmlformats.schemas.drawingml.x2006.chart.CTPictureOptions
 */
package org.openxmlformats.schemas.drawingml.x2006.chart.impl;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTExtensionList;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTPictureOptions;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTSurface;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTUnsignedInt;
import org.openxmlformats.schemas.drawingml.x2006.main.CTShapeProperties;

public class CTSurfaceImpl
extends XmlComplexContentImpl
implements CTSurface {
    private static final long serialVersionUID = 1L;
    private static final QName THICKNESS$0 = new QName("http://schemas.openxmlformats.org/drawingml/2006/chart", "thickness");
    private static final QName SPPR$2 = new QName("http://schemas.openxmlformats.org/drawingml/2006/chart", "spPr");
    private static final QName PICTUREOPTIONS$4 = new QName("http://schemas.openxmlformats.org/drawingml/2006/chart", "pictureOptions");
    private static final QName EXTLST$6 = new QName("http://schemas.openxmlformats.org/drawingml/2006/chart", "extLst");

    public CTSurfaceImpl(SchemaType schemaType) {
        super(schemaType);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTUnsignedInt getThickness() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTUnsignedInt cTUnsignedInt = null;
            cTUnsignedInt = (CTUnsignedInt)((Object)this.get_store().find_element_user(THICKNESS$0, 0));
            if (cTUnsignedInt == null) {
                return null;
            }
            return cTUnsignedInt;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetThickness() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(THICKNESS$0) != 0;
        }
    }

    @Override
    public void setThickness(CTUnsignedInt cTUnsignedInt) {
        this.generatedSetterHelperImpl(cTUnsignedInt, THICKNESS$0, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTUnsignedInt addNewThickness() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTUnsignedInt cTUnsignedInt = null;
            cTUnsignedInt = (CTUnsignedInt)((Object)this.get_store().add_element_user(THICKNESS$0));
            return cTUnsignedInt;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetThickness() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(THICKNESS$0, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTShapeProperties getSpPr() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTShapeProperties cTShapeProperties = null;
            cTShapeProperties = (CTShapeProperties)((Object)this.get_store().find_element_user(SPPR$2, 0));
            if (cTShapeProperties == null) {
                return null;
            }
            return cTShapeProperties;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetSpPr() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(SPPR$2) != 0;
        }
    }

    @Override
    public void setSpPr(CTShapeProperties cTShapeProperties) {
        this.generatedSetterHelperImpl(cTShapeProperties, SPPR$2, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTShapeProperties addNewSpPr() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTShapeProperties cTShapeProperties = null;
            cTShapeProperties = (CTShapeProperties)((Object)this.get_store().add_element_user(SPPR$2));
            return cTShapeProperties;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetSpPr() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(SPPR$2, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTPictureOptions getPictureOptions() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTPictureOptions cTPictureOptions = null;
            cTPictureOptions = (CTPictureOptions)this.get_store().find_element_user(PICTUREOPTIONS$4, 0);
            if (cTPictureOptions == null) {
                return null;
            }
            return cTPictureOptions;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetPictureOptions() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(PICTUREOPTIONS$4) != 0;
        }
    }

    @Override
    public void setPictureOptions(CTPictureOptions cTPictureOptions) {
        this.generatedSetterHelperImpl((XmlObject)cTPictureOptions, PICTUREOPTIONS$4, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTPictureOptions addNewPictureOptions() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTPictureOptions cTPictureOptions = null;
            cTPictureOptions = (CTPictureOptions)this.get_store().add_element_user(PICTUREOPTIONS$4);
            return cTPictureOptions;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetPictureOptions() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(PICTUREOPTIONS$4, 0);
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
            cTExtensionList = (CTExtensionList)((Object)this.get_store().find_element_user(EXTLST$6, 0));
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
            return this.get_store().count_elements(EXTLST$6) != 0;
        }
    }

    @Override
    public void setExtLst(CTExtensionList cTExtensionList) {
        this.generatedSetterHelperImpl(cTExtensionList, EXTLST$6, 0, (short)1);
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
            cTExtensionList = (CTExtensionList)((Object)this.get_store().add_element_user(EXTLST$6));
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
            this.get_store().remove_element(EXTLST$6, 0);
        }
    }
}

