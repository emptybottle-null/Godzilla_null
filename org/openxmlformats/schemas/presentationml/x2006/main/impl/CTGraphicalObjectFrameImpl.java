/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  org.openxmlformats.schemas.presentationml.x2006.main.CTExtensionListModify
 */
package org.openxmlformats.schemas.presentationml.x2006.main.impl;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.drawingml.x2006.main.CTGraphicalObject;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTransform2D;
import org.openxmlformats.schemas.presentationml.x2006.main.CTExtensionListModify;
import org.openxmlformats.schemas.presentationml.x2006.main.CTGraphicalObjectFrame;
import org.openxmlformats.schemas.presentationml.x2006.main.CTGraphicalObjectFrameNonVisual;

public class CTGraphicalObjectFrameImpl
extends XmlComplexContentImpl
implements CTGraphicalObjectFrame {
    private static final long serialVersionUID = 1L;
    private static final QName NVGRAPHICFRAMEPR$0 = new QName("http://schemas.openxmlformats.org/presentationml/2006/main", "nvGraphicFramePr");
    private static final QName XFRM$2 = new QName("http://schemas.openxmlformats.org/presentationml/2006/main", "xfrm");
    private static final QName GRAPHIC$4 = new QName("http://schemas.openxmlformats.org/drawingml/2006/main", "graphic");
    private static final QName EXTLST$6 = new QName("http://schemas.openxmlformats.org/presentationml/2006/main", "extLst");

    public CTGraphicalObjectFrameImpl(SchemaType schemaType) {
        super(schemaType);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTGraphicalObjectFrameNonVisual getNvGraphicFramePr() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTGraphicalObjectFrameNonVisual cTGraphicalObjectFrameNonVisual = null;
            cTGraphicalObjectFrameNonVisual = (CTGraphicalObjectFrameNonVisual)((Object)this.get_store().find_element_user(NVGRAPHICFRAMEPR$0, 0));
            if (cTGraphicalObjectFrameNonVisual == null) {
                return null;
            }
            return cTGraphicalObjectFrameNonVisual;
        }
    }

    @Override
    public void setNvGraphicFramePr(CTGraphicalObjectFrameNonVisual cTGraphicalObjectFrameNonVisual) {
        this.generatedSetterHelperImpl(cTGraphicalObjectFrameNonVisual, NVGRAPHICFRAMEPR$0, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTGraphicalObjectFrameNonVisual addNewNvGraphicFramePr() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTGraphicalObjectFrameNonVisual cTGraphicalObjectFrameNonVisual = null;
            cTGraphicalObjectFrameNonVisual = (CTGraphicalObjectFrameNonVisual)((Object)this.get_store().add_element_user(NVGRAPHICFRAMEPR$0));
            return cTGraphicalObjectFrameNonVisual;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTTransform2D getXfrm() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTTransform2D cTTransform2D = null;
            cTTransform2D = (CTTransform2D)((Object)this.get_store().find_element_user(XFRM$2, 0));
            if (cTTransform2D == null) {
                return null;
            }
            return cTTransform2D;
        }
    }

    @Override
    public void setXfrm(CTTransform2D cTTransform2D) {
        this.generatedSetterHelperImpl(cTTransform2D, XFRM$2, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTTransform2D addNewXfrm() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTTransform2D cTTransform2D = null;
            cTTransform2D = (CTTransform2D)((Object)this.get_store().add_element_user(XFRM$2));
            return cTTransform2D;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTGraphicalObject getGraphic() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTGraphicalObject cTGraphicalObject = null;
            cTGraphicalObject = (CTGraphicalObject)((Object)this.get_store().find_element_user(GRAPHIC$4, 0));
            if (cTGraphicalObject == null) {
                return null;
            }
            return cTGraphicalObject;
        }
    }

    @Override
    public void setGraphic(CTGraphicalObject cTGraphicalObject) {
        this.generatedSetterHelperImpl(cTGraphicalObject, GRAPHIC$4, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTGraphicalObject addNewGraphic() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTGraphicalObject cTGraphicalObject = null;
            cTGraphicalObject = (CTGraphicalObject)((Object)this.get_store().add_element_user(GRAPHIC$4));
            return cTGraphicalObject;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTExtensionListModify getExtLst() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTExtensionListModify cTExtensionListModify = null;
            cTExtensionListModify = (CTExtensionListModify)this.get_store().find_element_user(EXTLST$6, 0);
            if (cTExtensionListModify == null) {
                return null;
            }
            return cTExtensionListModify;
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
    public void setExtLst(CTExtensionListModify cTExtensionListModify) {
        this.generatedSetterHelperImpl((XmlObject)cTExtensionListModify, EXTLST$6, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTExtensionListModify addNewExtLst() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTExtensionListModify cTExtensionListModify = null;
            cTExtensionListModify = (CTExtensionListModify)this.get_store().add_element_user(EXTLST$6);
            return cTExtensionListModify;
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

