/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.openxmlformats.schemas.presentationml.x2006.main.impl;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.drawingml.x2006.main.CTNonVisualConnectorProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTNonVisualDrawingProps;
import org.openxmlformats.schemas.presentationml.x2006.main.CTApplicationNonVisualDrawingProps;
import org.openxmlformats.schemas.presentationml.x2006.main.CTConnectorNonVisual;

public class CTConnectorNonVisualImpl
extends XmlComplexContentImpl
implements CTConnectorNonVisual {
    private static final long serialVersionUID = 1L;
    private static final QName CNVPR$0 = new QName("http://schemas.openxmlformats.org/presentationml/2006/main", "cNvPr");
    private static final QName CNVCXNSPPR$2 = new QName("http://schemas.openxmlformats.org/presentationml/2006/main", "cNvCxnSpPr");
    private static final QName NVPR$4 = new QName("http://schemas.openxmlformats.org/presentationml/2006/main", "nvPr");

    public CTConnectorNonVisualImpl(SchemaType schemaType) {
        super(schemaType);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTNonVisualDrawingProps getCNvPr() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTNonVisualDrawingProps cTNonVisualDrawingProps = null;
            cTNonVisualDrawingProps = (CTNonVisualDrawingProps)((Object)this.get_store().find_element_user(CNVPR$0, 0));
            if (cTNonVisualDrawingProps == null) {
                return null;
            }
            return cTNonVisualDrawingProps;
        }
    }

    @Override
    public void setCNvPr(CTNonVisualDrawingProps cTNonVisualDrawingProps) {
        this.generatedSetterHelperImpl(cTNonVisualDrawingProps, CNVPR$0, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTNonVisualDrawingProps addNewCNvPr() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTNonVisualDrawingProps cTNonVisualDrawingProps = null;
            cTNonVisualDrawingProps = (CTNonVisualDrawingProps)((Object)this.get_store().add_element_user(CNVPR$0));
            return cTNonVisualDrawingProps;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTNonVisualConnectorProperties getCNvCxnSpPr() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTNonVisualConnectorProperties cTNonVisualConnectorProperties = null;
            cTNonVisualConnectorProperties = (CTNonVisualConnectorProperties)((Object)this.get_store().find_element_user(CNVCXNSPPR$2, 0));
            if (cTNonVisualConnectorProperties == null) {
                return null;
            }
            return cTNonVisualConnectorProperties;
        }
    }

    @Override
    public void setCNvCxnSpPr(CTNonVisualConnectorProperties cTNonVisualConnectorProperties) {
        this.generatedSetterHelperImpl(cTNonVisualConnectorProperties, CNVCXNSPPR$2, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTNonVisualConnectorProperties addNewCNvCxnSpPr() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTNonVisualConnectorProperties cTNonVisualConnectorProperties = null;
            cTNonVisualConnectorProperties = (CTNonVisualConnectorProperties)((Object)this.get_store().add_element_user(CNVCXNSPPR$2));
            return cTNonVisualConnectorProperties;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTApplicationNonVisualDrawingProps getNvPr() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTApplicationNonVisualDrawingProps cTApplicationNonVisualDrawingProps = null;
            cTApplicationNonVisualDrawingProps = (CTApplicationNonVisualDrawingProps)((Object)this.get_store().find_element_user(NVPR$4, 0));
            if (cTApplicationNonVisualDrawingProps == null) {
                return null;
            }
            return cTApplicationNonVisualDrawingProps;
        }
    }

    @Override
    public void setNvPr(CTApplicationNonVisualDrawingProps cTApplicationNonVisualDrawingProps) {
        this.generatedSetterHelperImpl(cTApplicationNonVisualDrawingProps, NVPR$4, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTApplicationNonVisualDrawingProps addNewNvPr() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTApplicationNonVisualDrawingProps cTApplicationNonVisualDrawingProps = null;
            cTApplicationNonVisualDrawingProps = (CTApplicationNonVisualDrawingProps)((Object)this.get_store().add_element_user(NVPR$4));
            return cTApplicationNonVisualDrawingProps;
        }
    }
}

