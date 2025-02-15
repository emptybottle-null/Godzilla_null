/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  org.openxmlformats.schemas.drawingml.x2006.main.CTBackdrop
 *  org.openxmlformats.schemas.drawingml.x2006.main.CTCamera
 *  org.openxmlformats.schemas.drawingml.x2006.main.CTLightRig
 */
package org.openxmlformats.schemas.drawingml.x2006.main.impl;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.drawingml.x2006.main.CTBackdrop;
import org.openxmlformats.schemas.drawingml.x2006.main.CTCamera;
import org.openxmlformats.schemas.drawingml.x2006.main.CTLightRig;
import org.openxmlformats.schemas.drawingml.x2006.main.CTOfficeArtExtensionList;
import org.openxmlformats.schemas.drawingml.x2006.main.CTScene3D;

public class CTScene3DImpl
extends XmlComplexContentImpl
implements CTScene3D {
    private static final long serialVersionUID = 1L;
    private static final QName CAMERA$0 = new QName("http://schemas.openxmlformats.org/drawingml/2006/main", "camera");
    private static final QName LIGHTRIG$2 = new QName("http://schemas.openxmlformats.org/drawingml/2006/main", "lightRig");
    private static final QName BACKDROP$4 = new QName("http://schemas.openxmlformats.org/drawingml/2006/main", "backdrop");
    private static final QName EXTLST$6 = new QName("http://schemas.openxmlformats.org/drawingml/2006/main", "extLst");

    public CTScene3DImpl(SchemaType schemaType) {
        super(schemaType);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTCamera getCamera() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTCamera cTCamera = null;
            cTCamera = (CTCamera)this.get_store().find_element_user(CAMERA$0, 0);
            if (cTCamera == null) {
                return null;
            }
            return cTCamera;
        }
    }

    @Override
    public void setCamera(CTCamera cTCamera) {
        this.generatedSetterHelperImpl((XmlObject)cTCamera, CAMERA$0, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTCamera addNewCamera() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTCamera cTCamera = null;
            cTCamera = (CTCamera)this.get_store().add_element_user(CAMERA$0);
            return cTCamera;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTLightRig getLightRig() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTLightRig cTLightRig = null;
            cTLightRig = (CTLightRig)this.get_store().find_element_user(LIGHTRIG$2, 0);
            if (cTLightRig == null) {
                return null;
            }
            return cTLightRig;
        }
    }

    @Override
    public void setLightRig(CTLightRig cTLightRig) {
        this.generatedSetterHelperImpl((XmlObject)cTLightRig, LIGHTRIG$2, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTLightRig addNewLightRig() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTLightRig cTLightRig = null;
            cTLightRig = (CTLightRig)this.get_store().add_element_user(LIGHTRIG$2);
            return cTLightRig;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTBackdrop getBackdrop() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTBackdrop cTBackdrop = null;
            cTBackdrop = (CTBackdrop)this.get_store().find_element_user(BACKDROP$4, 0);
            if (cTBackdrop == null) {
                return null;
            }
            return cTBackdrop;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetBackdrop() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(BACKDROP$4) != 0;
        }
    }

    @Override
    public void setBackdrop(CTBackdrop cTBackdrop) {
        this.generatedSetterHelperImpl((XmlObject)cTBackdrop, BACKDROP$4, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTBackdrop addNewBackdrop() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTBackdrop cTBackdrop = null;
            cTBackdrop = (CTBackdrop)this.get_store().add_element_user(BACKDROP$4);
            return cTBackdrop;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetBackdrop() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(BACKDROP$4, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTOfficeArtExtensionList getExtLst() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTOfficeArtExtensionList cTOfficeArtExtensionList = null;
            cTOfficeArtExtensionList = (CTOfficeArtExtensionList)((Object)this.get_store().find_element_user(EXTLST$6, 0));
            if (cTOfficeArtExtensionList == null) {
                return null;
            }
            return cTOfficeArtExtensionList;
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
    public void setExtLst(CTOfficeArtExtensionList cTOfficeArtExtensionList) {
        this.generatedSetterHelperImpl(cTOfficeArtExtensionList, EXTLST$6, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTOfficeArtExtensionList addNewExtLst() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTOfficeArtExtensionList cTOfficeArtExtensionList = null;
            cTOfficeArtExtensionList = (CTOfficeArtExtensionList)((Object)this.get_store().add_element_user(EXTLST$6));
            return cTOfficeArtExtensionList;
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

