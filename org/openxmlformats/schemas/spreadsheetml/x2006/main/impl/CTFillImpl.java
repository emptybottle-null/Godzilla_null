/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  org.openxmlformats.schemas.spreadsheetml.x2006.main.CTGradientFill
 */
package org.openxmlformats.schemas.spreadsheetml.x2006.main.impl;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTFill;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTGradientFill;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPatternFill;

public class CTFillImpl
extends XmlComplexContentImpl
implements CTFill {
    private static final long serialVersionUID = 1L;
    private static final QName PATTERNFILL$0 = new QName("http://schemas.openxmlformats.org/spreadsheetml/2006/main", "patternFill");
    private static final QName GRADIENTFILL$2 = new QName("http://schemas.openxmlformats.org/spreadsheetml/2006/main", "gradientFill");

    public CTFillImpl(SchemaType schemaType) {
        super(schemaType);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTPatternFill getPatternFill() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTPatternFill cTPatternFill = null;
            cTPatternFill = (CTPatternFill)((Object)this.get_store().find_element_user(PATTERNFILL$0, 0));
            if (cTPatternFill == null) {
                return null;
            }
            return cTPatternFill;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetPatternFill() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(PATTERNFILL$0) != 0;
        }
    }

    @Override
    public void setPatternFill(CTPatternFill cTPatternFill) {
        this.generatedSetterHelperImpl(cTPatternFill, PATTERNFILL$0, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTPatternFill addNewPatternFill() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTPatternFill cTPatternFill = null;
            cTPatternFill = (CTPatternFill)((Object)this.get_store().add_element_user(PATTERNFILL$0));
            return cTPatternFill;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetPatternFill() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(PATTERNFILL$0, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTGradientFill getGradientFill() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTGradientFill cTGradientFill = null;
            cTGradientFill = (CTGradientFill)this.get_store().find_element_user(GRADIENTFILL$2, 0);
            if (cTGradientFill == null) {
                return null;
            }
            return cTGradientFill;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetGradientFill() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(GRADIENTFILL$2) != 0;
        }
    }

    @Override
    public void setGradientFill(CTGradientFill cTGradientFill) {
        this.generatedSetterHelperImpl((XmlObject)cTGradientFill, GRADIENTFILL$2, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTGradientFill addNewGradientFill() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTGradientFill cTGradientFill = null;
            cTGradientFill = (CTGradientFill)this.get_store().add_element_user(GRADIENTFILL$2);
            return cTGradientFill;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetGradientFill() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(GRADIENTFILL$2, 0);
        }
    }
}

