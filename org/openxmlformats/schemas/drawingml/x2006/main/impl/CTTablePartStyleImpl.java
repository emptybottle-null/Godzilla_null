/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.openxmlformats.schemas.drawingml.x2006.main.impl;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTablePartStyle;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTableStyleCellStyle;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTableStyleTextStyle;

public class CTTablePartStyleImpl
extends XmlComplexContentImpl
implements CTTablePartStyle {
    private static final long serialVersionUID = 1L;
    private static final QName TCTXSTYLE$0 = new QName("http://schemas.openxmlformats.org/drawingml/2006/main", "tcTxStyle");
    private static final QName TCSTYLE$2 = new QName("http://schemas.openxmlformats.org/drawingml/2006/main", "tcStyle");

    public CTTablePartStyleImpl(SchemaType schemaType) {
        super(schemaType);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTTableStyleTextStyle getTcTxStyle() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTTableStyleTextStyle cTTableStyleTextStyle = null;
            cTTableStyleTextStyle = (CTTableStyleTextStyle)((Object)this.get_store().find_element_user(TCTXSTYLE$0, 0));
            if (cTTableStyleTextStyle == null) {
                return null;
            }
            return cTTableStyleTextStyle;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetTcTxStyle() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(TCTXSTYLE$0) != 0;
        }
    }

    @Override
    public void setTcTxStyle(CTTableStyleTextStyle cTTableStyleTextStyle) {
        this.generatedSetterHelperImpl(cTTableStyleTextStyle, TCTXSTYLE$0, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTTableStyleTextStyle addNewTcTxStyle() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTTableStyleTextStyle cTTableStyleTextStyle = null;
            cTTableStyleTextStyle = (CTTableStyleTextStyle)((Object)this.get_store().add_element_user(TCTXSTYLE$0));
            return cTTableStyleTextStyle;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetTcTxStyle() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(TCTXSTYLE$0, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTTableStyleCellStyle getTcStyle() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTTableStyleCellStyle cTTableStyleCellStyle = null;
            cTTableStyleCellStyle = (CTTableStyleCellStyle)((Object)this.get_store().find_element_user(TCSTYLE$2, 0));
            if (cTTableStyleCellStyle == null) {
                return null;
            }
            return cTTableStyleCellStyle;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetTcStyle() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(TCSTYLE$2) != 0;
        }
    }

    @Override
    public void setTcStyle(CTTableStyleCellStyle cTTableStyleCellStyle) {
        this.generatedSetterHelperImpl(cTTableStyleCellStyle, TCSTYLE$2, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTTableStyleCellStyle addNewTcStyle() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTTableStyleCellStyle cTTableStyleCellStyle = null;
            cTTableStyleCellStyle = (CTTableStyleCellStyle)((Object)this.get_store().add_element_user(TCSTYLE$2));
            return cTTableStyleCellStyle;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetTcStyle() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(TCSTYLE$2, 0);
        }
    }
}

