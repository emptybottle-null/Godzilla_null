/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.openxmlformats.schemas.wordprocessingml.x2006.main.impl;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblCellMar;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblWidth;

public class CTTblCellMarImpl
extends XmlComplexContentImpl
implements CTTblCellMar {
    private static final long serialVersionUID = 1L;
    private static final QName TOP$0 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "top");
    private static final QName LEFT$2 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "left");
    private static final QName BOTTOM$4 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "bottom");
    private static final QName RIGHT$6 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "right");

    public CTTblCellMarImpl(SchemaType schemaType) {
        super(schemaType);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTTblWidth getTop() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTTblWidth cTTblWidth = null;
            cTTblWidth = (CTTblWidth)((Object)this.get_store().find_element_user(TOP$0, 0));
            if (cTTblWidth == null) {
                return null;
            }
            return cTTblWidth;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetTop() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(TOP$0) != 0;
        }
    }

    @Override
    public void setTop(CTTblWidth cTTblWidth) {
        this.generatedSetterHelperImpl(cTTblWidth, TOP$0, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTTblWidth addNewTop() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTTblWidth cTTblWidth = null;
            cTTblWidth = (CTTblWidth)((Object)this.get_store().add_element_user(TOP$0));
            return cTTblWidth;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetTop() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(TOP$0, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTTblWidth getLeft() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTTblWidth cTTblWidth = null;
            cTTblWidth = (CTTblWidth)((Object)this.get_store().find_element_user(LEFT$2, 0));
            if (cTTblWidth == null) {
                return null;
            }
            return cTTblWidth;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetLeft() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(LEFT$2) != 0;
        }
    }

    @Override
    public void setLeft(CTTblWidth cTTblWidth) {
        this.generatedSetterHelperImpl(cTTblWidth, LEFT$2, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTTblWidth addNewLeft() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTTblWidth cTTblWidth = null;
            cTTblWidth = (CTTblWidth)((Object)this.get_store().add_element_user(LEFT$2));
            return cTTblWidth;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetLeft() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(LEFT$2, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTTblWidth getBottom() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTTblWidth cTTblWidth = null;
            cTTblWidth = (CTTblWidth)((Object)this.get_store().find_element_user(BOTTOM$4, 0));
            if (cTTblWidth == null) {
                return null;
            }
            return cTTblWidth;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetBottom() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(BOTTOM$4) != 0;
        }
    }

    @Override
    public void setBottom(CTTblWidth cTTblWidth) {
        this.generatedSetterHelperImpl(cTTblWidth, BOTTOM$4, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTTblWidth addNewBottom() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTTblWidth cTTblWidth = null;
            cTTblWidth = (CTTblWidth)((Object)this.get_store().add_element_user(BOTTOM$4));
            return cTTblWidth;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetBottom() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(BOTTOM$4, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTTblWidth getRight() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTTblWidth cTTblWidth = null;
            cTTblWidth = (CTTblWidth)((Object)this.get_store().find_element_user(RIGHT$6, 0));
            if (cTTblWidth == null) {
                return null;
            }
            return cTTblWidth;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetRight() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(RIGHT$6) != 0;
        }
    }

    @Override
    public void setRight(CTTblWidth cTTblWidth) {
        this.generatedSetterHelperImpl(cTTblWidth, RIGHT$6, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTTblWidth addNewRight() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTTblWidth cTTblWidth = null;
            cTTblWidth = (CTTblWidth)((Object)this.get_store().add_element_user(RIGHT$6));
            return cTTblWidth;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetRight() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(RIGHT$6, 0);
        }
    }
}

