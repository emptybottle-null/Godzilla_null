/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.openxmlformats.schemas.wordprocessingml.x2006.main.impl;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTBorder;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPBdr;

public class CTPBdrImpl
extends XmlComplexContentImpl
implements CTPBdr {
    private static final long serialVersionUID = 1L;
    private static final QName TOP$0 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "top");
    private static final QName LEFT$2 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "left");
    private static final QName BOTTOM$4 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "bottom");
    private static final QName RIGHT$6 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "right");
    private static final QName BETWEEN$8 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "between");
    private static final QName BAR$10 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "bar");

    public CTPBdrImpl(SchemaType schemaType) {
        super(schemaType);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTBorder getTop() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTBorder cTBorder = null;
            cTBorder = (CTBorder)((Object)this.get_store().find_element_user(TOP$0, 0));
            if (cTBorder == null) {
                return null;
            }
            return cTBorder;
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
    public void setTop(CTBorder cTBorder) {
        this.generatedSetterHelperImpl(cTBorder, TOP$0, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTBorder addNewTop() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTBorder cTBorder = null;
            cTBorder = (CTBorder)((Object)this.get_store().add_element_user(TOP$0));
            return cTBorder;
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
    public CTBorder getLeft() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTBorder cTBorder = null;
            cTBorder = (CTBorder)((Object)this.get_store().find_element_user(LEFT$2, 0));
            if (cTBorder == null) {
                return null;
            }
            return cTBorder;
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
    public void setLeft(CTBorder cTBorder) {
        this.generatedSetterHelperImpl(cTBorder, LEFT$2, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTBorder addNewLeft() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTBorder cTBorder = null;
            cTBorder = (CTBorder)((Object)this.get_store().add_element_user(LEFT$2));
            return cTBorder;
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
    public CTBorder getBottom() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTBorder cTBorder = null;
            cTBorder = (CTBorder)((Object)this.get_store().find_element_user(BOTTOM$4, 0));
            if (cTBorder == null) {
                return null;
            }
            return cTBorder;
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
    public void setBottom(CTBorder cTBorder) {
        this.generatedSetterHelperImpl(cTBorder, BOTTOM$4, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTBorder addNewBottom() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTBorder cTBorder = null;
            cTBorder = (CTBorder)((Object)this.get_store().add_element_user(BOTTOM$4));
            return cTBorder;
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
    public CTBorder getRight() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTBorder cTBorder = null;
            cTBorder = (CTBorder)((Object)this.get_store().find_element_user(RIGHT$6, 0));
            if (cTBorder == null) {
                return null;
            }
            return cTBorder;
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
    public void setRight(CTBorder cTBorder) {
        this.generatedSetterHelperImpl(cTBorder, RIGHT$6, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTBorder addNewRight() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTBorder cTBorder = null;
            cTBorder = (CTBorder)((Object)this.get_store().add_element_user(RIGHT$6));
            return cTBorder;
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

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTBorder getBetween() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTBorder cTBorder = null;
            cTBorder = (CTBorder)((Object)this.get_store().find_element_user(BETWEEN$8, 0));
            if (cTBorder == null) {
                return null;
            }
            return cTBorder;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetBetween() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(BETWEEN$8) != 0;
        }
    }

    @Override
    public void setBetween(CTBorder cTBorder) {
        this.generatedSetterHelperImpl(cTBorder, BETWEEN$8, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTBorder addNewBetween() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTBorder cTBorder = null;
            cTBorder = (CTBorder)((Object)this.get_store().add_element_user(BETWEEN$8));
            return cTBorder;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetBetween() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(BETWEEN$8, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTBorder getBar() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTBorder cTBorder = null;
            cTBorder = (CTBorder)((Object)this.get_store().find_element_user(BAR$10, 0));
            if (cTBorder == null) {
                return null;
            }
            return cTBorder;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetBar() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(BAR$10) != 0;
        }
    }

    @Override
    public void setBar(CTBorder cTBorder) {
        this.generatedSetterHelperImpl(cTBorder, BAR$10, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTBorder addNewBar() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTBorder cTBorder = null;
            cTBorder = (CTBorder)((Object)this.get_store().add_element_user(BAR$10));
            return cTBorder;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetBar() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(BAR$10, 0);
        }
    }
}

