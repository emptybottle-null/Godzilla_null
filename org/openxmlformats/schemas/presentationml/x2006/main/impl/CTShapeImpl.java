/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  org.openxmlformats.schemas.presentationml.x2006.main.CTExtensionListModify
 */
package org.openxmlformats.schemas.presentationml.x2006.main.impl;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.XmlBoolean;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.drawingml.x2006.main.CTShapeProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTShapeStyle;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextBody;
import org.openxmlformats.schemas.presentationml.x2006.main.CTExtensionListModify;
import org.openxmlformats.schemas.presentationml.x2006.main.CTShape;
import org.openxmlformats.schemas.presentationml.x2006.main.CTShapeNonVisual;

public class CTShapeImpl
extends XmlComplexContentImpl
implements CTShape {
    private static final long serialVersionUID = 1L;
    private static final QName NVSPPR$0 = new QName("http://schemas.openxmlformats.org/presentationml/2006/main", "nvSpPr");
    private static final QName SPPR$2 = new QName("http://schemas.openxmlformats.org/presentationml/2006/main", "spPr");
    private static final QName STYLE$4 = new QName("http://schemas.openxmlformats.org/presentationml/2006/main", "style");
    private static final QName TXBODY$6 = new QName("http://schemas.openxmlformats.org/presentationml/2006/main", "txBody");
    private static final QName EXTLST$8 = new QName("http://schemas.openxmlformats.org/presentationml/2006/main", "extLst");
    private static final QName USEBGFILL$10 = new QName("", "useBgFill");

    public CTShapeImpl(SchemaType schemaType) {
        super(schemaType);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTShapeNonVisual getNvSpPr() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTShapeNonVisual cTShapeNonVisual = null;
            cTShapeNonVisual = (CTShapeNonVisual)((Object)this.get_store().find_element_user(NVSPPR$0, 0));
            if (cTShapeNonVisual == null) {
                return null;
            }
            return cTShapeNonVisual;
        }
    }

    @Override
    public void setNvSpPr(CTShapeNonVisual cTShapeNonVisual) {
        this.generatedSetterHelperImpl(cTShapeNonVisual, NVSPPR$0, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTShapeNonVisual addNewNvSpPr() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTShapeNonVisual cTShapeNonVisual = null;
            cTShapeNonVisual = (CTShapeNonVisual)((Object)this.get_store().add_element_user(NVSPPR$0));
            return cTShapeNonVisual;
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
    public CTTextBody getTxBody() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTTextBody cTTextBody = null;
            cTTextBody = (CTTextBody)((Object)this.get_store().find_element_user(TXBODY$6, 0));
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
    public boolean isSetTxBody() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(TXBODY$6) != 0;
        }
    }

    @Override
    public void setTxBody(CTTextBody cTTextBody) {
        this.generatedSetterHelperImpl(cTTextBody, TXBODY$6, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTTextBody addNewTxBody() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTTextBody cTTextBody = null;
            cTTextBody = (CTTextBody)((Object)this.get_store().add_element_user(TXBODY$6));
            return cTTextBody;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetTxBody() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(TXBODY$6, 0);
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

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean getUseBgFill() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(USEBGFILL$10));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_default_attribute_value(USEBGFILL$10));
            }
            if (simpleValue == null) {
                return false;
            }
            return simpleValue.getBooleanValue();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public XmlBoolean xgetUseBgFill() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlBoolean xmlBoolean = null;
            xmlBoolean = (XmlBoolean)((Object)this.get_store().find_attribute_user(USEBGFILL$10));
            if (xmlBoolean == null) {
                xmlBoolean = (XmlBoolean)this.get_default_attribute_value(USEBGFILL$10);
            }
            return xmlBoolean;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetUseBgFill() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().find_attribute_user(USEBGFILL$10) != null;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setUseBgFill(boolean bl) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(USEBGFILL$10));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(USEBGFILL$10));
            }
            simpleValue.setBooleanValue(bl);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetUseBgFill(XmlBoolean xmlBoolean) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlBoolean xmlBoolean2 = null;
            xmlBoolean2 = (XmlBoolean)((Object)this.get_store().find_attribute_user(USEBGFILL$10));
            if (xmlBoolean2 == null) {
                xmlBoolean2 = (XmlBoolean)((Object)this.get_store().add_attribute_user(USEBGFILL$10));
            }
            xmlBoolean2.set(xmlBoolean);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetUseBgFill() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_attribute(USEBGFILL$10);
        }
    }
}

