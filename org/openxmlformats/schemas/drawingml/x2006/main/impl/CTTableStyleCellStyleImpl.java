/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  org.openxmlformats.schemas.drawingml.x2006.main.CTCell3D
 *  org.openxmlformats.schemas.drawingml.x2006.main.CTTableCellBorderStyle
 */
package org.openxmlformats.schemas.drawingml.x2006.main.impl;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.drawingml.x2006.main.CTCell3D;
import org.openxmlformats.schemas.drawingml.x2006.main.CTFillProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTStyleMatrixReference;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTableCellBorderStyle;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTableStyleCellStyle;

public class CTTableStyleCellStyleImpl
extends XmlComplexContentImpl
implements CTTableStyleCellStyle {
    private static final long serialVersionUID = 1L;
    private static final QName TCBDR$0 = new QName("http://schemas.openxmlformats.org/drawingml/2006/main", "tcBdr");
    private static final QName FILL$2 = new QName("http://schemas.openxmlformats.org/drawingml/2006/main", "fill");
    private static final QName FILLREF$4 = new QName("http://schemas.openxmlformats.org/drawingml/2006/main", "fillRef");
    private static final QName CELL3D$6 = new QName("http://schemas.openxmlformats.org/drawingml/2006/main", "cell3D");

    public CTTableStyleCellStyleImpl(SchemaType schemaType) {
        super(schemaType);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTTableCellBorderStyle getTcBdr() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTTableCellBorderStyle cTTableCellBorderStyle = null;
            cTTableCellBorderStyle = (CTTableCellBorderStyle)this.get_store().find_element_user(TCBDR$0, 0);
            if (cTTableCellBorderStyle == null) {
                return null;
            }
            return cTTableCellBorderStyle;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetTcBdr() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(TCBDR$0) != 0;
        }
    }

    @Override
    public void setTcBdr(CTTableCellBorderStyle cTTableCellBorderStyle) {
        this.generatedSetterHelperImpl((XmlObject)cTTableCellBorderStyle, TCBDR$0, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTTableCellBorderStyle addNewTcBdr() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTTableCellBorderStyle cTTableCellBorderStyle = null;
            cTTableCellBorderStyle = (CTTableCellBorderStyle)this.get_store().add_element_user(TCBDR$0);
            return cTTableCellBorderStyle;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetTcBdr() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(TCBDR$0, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTFillProperties getFill() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTFillProperties cTFillProperties = null;
            cTFillProperties = (CTFillProperties)((Object)this.get_store().find_element_user(FILL$2, 0));
            if (cTFillProperties == null) {
                return null;
            }
            return cTFillProperties;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetFill() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(FILL$2) != 0;
        }
    }

    @Override
    public void setFill(CTFillProperties cTFillProperties) {
        this.generatedSetterHelperImpl(cTFillProperties, FILL$2, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTFillProperties addNewFill() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTFillProperties cTFillProperties = null;
            cTFillProperties = (CTFillProperties)((Object)this.get_store().add_element_user(FILL$2));
            return cTFillProperties;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetFill() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(FILL$2, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTStyleMatrixReference getFillRef() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTStyleMatrixReference cTStyleMatrixReference = null;
            cTStyleMatrixReference = (CTStyleMatrixReference)((Object)this.get_store().find_element_user(FILLREF$4, 0));
            if (cTStyleMatrixReference == null) {
                return null;
            }
            return cTStyleMatrixReference;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetFillRef() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(FILLREF$4) != 0;
        }
    }

    @Override
    public void setFillRef(CTStyleMatrixReference cTStyleMatrixReference) {
        this.generatedSetterHelperImpl(cTStyleMatrixReference, FILLREF$4, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTStyleMatrixReference addNewFillRef() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTStyleMatrixReference cTStyleMatrixReference = null;
            cTStyleMatrixReference = (CTStyleMatrixReference)((Object)this.get_store().add_element_user(FILLREF$4));
            return cTStyleMatrixReference;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetFillRef() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(FILLREF$4, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTCell3D getCell3D() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTCell3D cTCell3D = null;
            cTCell3D = (CTCell3D)this.get_store().find_element_user(CELL3D$6, 0);
            if (cTCell3D == null) {
                return null;
            }
            return cTCell3D;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetCell3D() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(CELL3D$6) != 0;
        }
    }

    @Override
    public void setCell3D(CTCell3D cTCell3D) {
        this.generatedSetterHelperImpl((XmlObject)cTCell3D, CELL3D$6, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTCell3D addNewCell3D() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTCell3D cTCell3D = null;
            cTCell3D = (CTCell3D)this.get_store().add_element_user(CELL3D$6);
            return cTCell3D;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetCell3D() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(CELL3D$6, 0);
        }
    }
}

