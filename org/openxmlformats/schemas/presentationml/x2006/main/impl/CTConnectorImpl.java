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
import org.openxmlformats.schemas.drawingml.x2006.main.CTShapeProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTShapeStyle;
import org.openxmlformats.schemas.presentationml.x2006.main.CTConnector;
import org.openxmlformats.schemas.presentationml.x2006.main.CTConnectorNonVisual;
import org.openxmlformats.schemas.presentationml.x2006.main.CTExtensionListModify;

public class CTConnectorImpl
extends XmlComplexContentImpl
implements CTConnector {
    private static final long serialVersionUID = 1L;
    private static final QName NVCXNSPPR$0 = new QName("http://schemas.openxmlformats.org/presentationml/2006/main", "nvCxnSpPr");
    private static final QName SPPR$2 = new QName("http://schemas.openxmlformats.org/presentationml/2006/main", "spPr");
    private static final QName STYLE$4 = new QName("http://schemas.openxmlformats.org/presentationml/2006/main", "style");
    private static final QName EXTLST$6 = new QName("http://schemas.openxmlformats.org/presentationml/2006/main", "extLst");

    public CTConnectorImpl(SchemaType schemaType) {
        super(schemaType);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTConnectorNonVisual getNvCxnSpPr() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTConnectorNonVisual cTConnectorNonVisual = null;
            cTConnectorNonVisual = (CTConnectorNonVisual)((Object)this.get_store().find_element_user(NVCXNSPPR$0, 0));
            if (cTConnectorNonVisual == null) {
                return null;
            }
            return cTConnectorNonVisual;
        }
    }

    @Override
    public void setNvCxnSpPr(CTConnectorNonVisual cTConnectorNonVisual) {
        this.generatedSetterHelperImpl(cTConnectorNonVisual, NVCXNSPPR$0, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTConnectorNonVisual addNewNvCxnSpPr() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTConnectorNonVisual cTConnectorNonVisual = null;
            cTConnectorNonVisual = (CTConnectorNonVisual)((Object)this.get_store().add_element_user(NVCXNSPPR$0));
            return cTConnectorNonVisual;
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
    public CTShapeStyle getStyle() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTShapeStyle cTShapeStyle = null;
            cTShapeStyle = (CTShapeStyle)((Object)this.get_store().find_element_user(STYLE$4, 0));
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
            return this.get_store().count_elements(STYLE$4) != 0;
        }
    }

    @Override
    public void setStyle(CTShapeStyle cTShapeStyle) {
        this.generatedSetterHelperImpl(cTShapeStyle, STYLE$4, 0, (short)1);
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
            cTShapeStyle = (CTShapeStyle)((Object)this.get_store().add_element_user(STYLE$4));
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
            this.get_store().remove_element(STYLE$4, 0);
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

