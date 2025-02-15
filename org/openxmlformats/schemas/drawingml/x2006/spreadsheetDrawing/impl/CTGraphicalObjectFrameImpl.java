/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.impl;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.XmlBoolean;
import org.apache.xmlbeans.XmlString;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.drawingml.x2006.main.CTGraphicalObject;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTransform2D;
import org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.CTGraphicalObjectFrame;
import org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.CTGraphicalObjectFrameNonVisual;

public class CTGraphicalObjectFrameImpl
extends XmlComplexContentImpl
implements CTGraphicalObjectFrame {
    private static final long serialVersionUID = 1L;
    private static final QName NVGRAPHICFRAMEPR$0 = new QName("http://schemas.openxmlformats.org/drawingml/2006/spreadsheetDrawing", "nvGraphicFramePr");
    private static final QName XFRM$2 = new QName("http://schemas.openxmlformats.org/drawingml/2006/spreadsheetDrawing", "xfrm");
    private static final QName GRAPHIC$4 = new QName("http://schemas.openxmlformats.org/drawingml/2006/main", "graphic");
    private static final QName MACRO$6 = new QName("", "macro");
    private static final QName FPUBLISHED$8 = new QName("", "fPublished");

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
    public String getMacro() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(MACRO$6));
            if (simpleValue == null) {
                return null;
            }
            return simpleValue.getStringValue();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public XmlString xgetMacro() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlString xmlString = null;
            xmlString = (XmlString)((Object)this.get_store().find_attribute_user(MACRO$6));
            return xmlString;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetMacro() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().find_attribute_user(MACRO$6) != null;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setMacro(String string) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(MACRO$6));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(MACRO$6));
            }
            simpleValue.setStringValue(string);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetMacro(XmlString xmlString) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlString xmlString2 = null;
            xmlString2 = (XmlString)((Object)this.get_store().find_attribute_user(MACRO$6));
            if (xmlString2 == null) {
                xmlString2 = (XmlString)((Object)this.get_store().add_attribute_user(MACRO$6));
            }
            xmlString2.set(xmlString);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetMacro() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_attribute(MACRO$6);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean getFPublished() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(FPUBLISHED$8));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_default_attribute_value(FPUBLISHED$8));
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
    public XmlBoolean xgetFPublished() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlBoolean xmlBoolean = null;
            xmlBoolean = (XmlBoolean)((Object)this.get_store().find_attribute_user(FPUBLISHED$8));
            if (xmlBoolean == null) {
                xmlBoolean = (XmlBoolean)this.get_default_attribute_value(FPUBLISHED$8);
            }
            return xmlBoolean;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetFPublished() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().find_attribute_user(FPUBLISHED$8) != null;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setFPublished(boolean bl) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(FPUBLISHED$8));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(FPUBLISHED$8));
            }
            simpleValue.setBooleanValue(bl);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetFPublished(XmlBoolean xmlBoolean) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlBoolean xmlBoolean2 = null;
            xmlBoolean2 = (XmlBoolean)((Object)this.get_store().find_attribute_user(FPUBLISHED$8));
            if (xmlBoolean2 == null) {
                xmlBoolean2 = (XmlBoolean)((Object)this.get_store().add_attribute_user(FPUBLISHED$8));
            }
            xmlBoolean2.set(xmlBoolean);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetFPublished() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_attribute(FPUBLISHED$8);
        }
    }
}

