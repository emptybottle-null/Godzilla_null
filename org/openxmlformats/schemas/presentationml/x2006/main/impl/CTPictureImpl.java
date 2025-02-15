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
import org.openxmlformats.schemas.drawingml.x2006.main.CTBlipFillProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTShapeProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTShapeStyle;
import org.openxmlformats.schemas.presentationml.x2006.main.CTExtensionListModify;
import org.openxmlformats.schemas.presentationml.x2006.main.CTPicture;
import org.openxmlformats.schemas.presentationml.x2006.main.CTPictureNonVisual;

public class CTPictureImpl
extends XmlComplexContentImpl
implements CTPicture {
    private static final long serialVersionUID = 1L;
    private static final QName NVPICPR$0 = new QName("http://schemas.openxmlformats.org/presentationml/2006/main", "nvPicPr");
    private static final QName BLIPFILL$2 = new QName("http://schemas.openxmlformats.org/presentationml/2006/main", "blipFill");
    private static final QName SPPR$4 = new QName("http://schemas.openxmlformats.org/presentationml/2006/main", "spPr");
    private static final QName STYLE$6 = new QName("http://schemas.openxmlformats.org/presentationml/2006/main", "style");
    private static final QName EXTLST$8 = new QName("http://schemas.openxmlformats.org/presentationml/2006/main", "extLst");

    public CTPictureImpl(SchemaType schemaType) {
        super(schemaType);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTPictureNonVisual getNvPicPr() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTPictureNonVisual cTPictureNonVisual = null;
            cTPictureNonVisual = (CTPictureNonVisual)((Object)this.get_store().find_element_user(NVPICPR$0, 0));
            if (cTPictureNonVisual == null) {
                return null;
            }
            return cTPictureNonVisual;
        }
    }

    @Override
    public void setNvPicPr(CTPictureNonVisual cTPictureNonVisual) {
        this.generatedSetterHelperImpl(cTPictureNonVisual, NVPICPR$0, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTPictureNonVisual addNewNvPicPr() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTPictureNonVisual cTPictureNonVisual = null;
            cTPictureNonVisual = (CTPictureNonVisual)((Object)this.get_store().add_element_user(NVPICPR$0));
            return cTPictureNonVisual;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTBlipFillProperties getBlipFill() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTBlipFillProperties cTBlipFillProperties = null;
            cTBlipFillProperties = (CTBlipFillProperties)((Object)this.get_store().find_element_user(BLIPFILL$2, 0));
            if (cTBlipFillProperties == null) {
                return null;
            }
            return cTBlipFillProperties;
        }
    }

    @Override
    public void setBlipFill(CTBlipFillProperties cTBlipFillProperties) {
        this.generatedSetterHelperImpl(cTBlipFillProperties, BLIPFILL$2, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTBlipFillProperties addNewBlipFill() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTBlipFillProperties cTBlipFillProperties = null;
            cTBlipFillProperties = (CTBlipFillProperties)((Object)this.get_store().add_element_user(BLIPFILL$2));
            return cTBlipFillProperties;
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
            cTShapeProperties = (CTShapeProperties)((Object)this.get_store().find_element_user(SPPR$4, 0));
            if (cTShapeProperties == null) {
                return null;
            }
            return cTShapeProperties;
        }
    }

    @Override
    public void setSpPr(CTShapeProperties cTShapeProperties) {
        this.generatedSetterHelperImpl(cTShapeProperties, SPPR$4, 0, (short)1);
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
            cTShapeProperties = (CTShapeProperties)((Object)this.get_store().add_element_user(SPPR$4));
            return cTShapeProperties;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTShapeStyle getStyle() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTShapeStyle cTShapeStyle = null;
            cTShapeStyle = (CTShapeStyle)((Object)this.get_store().find_element_user(STYLE$6, 0));
            if (cTShapeStyle == null) {
                return null;
            }
            return cTShapeStyle;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetStyle() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(STYLE$6) != 0;
        }
    }

    @Override
    public void setStyle(CTShapeStyle cTShapeStyle) {
        this.generatedSetterHelperImpl(cTShapeStyle, STYLE$6, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTShapeStyle addNewStyle() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTShapeStyle cTShapeStyle = null;
            cTShapeStyle = (CTShapeStyle)((Object)this.get_store().add_element_user(STYLE$6));
            return cTShapeStyle;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetStyle() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(STYLE$6, 0);
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
            cTExtensionListModify = (CTExtensionListModify)this.get_store().find_element_user(EXTLST$8, 0);
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
            return this.get_store().count_elements(EXTLST$8) != 0;
        }
    }

    @Override
    public void setExtLst(CTExtensionListModify cTExtensionListModify) {
        this.generatedSetterHelperImpl((XmlObject)cTExtensionListModify, EXTLST$8, 0, (short)1);
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
            cTExtensionListModify = (CTExtensionListModify)this.get_store().add_element_user(EXTLST$8);
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
            this.get_store().remove_element(EXTLST$8, 0);
        }
    }
}

