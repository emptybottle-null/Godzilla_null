/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.openxmlformats.schemas.drawingml.x2006.main.impl;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextSpacing;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextSpacingPercent;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextSpacingPoint;

public class CTTextSpacingImpl
extends XmlComplexContentImpl
implements CTTextSpacing {
    private static final long serialVersionUID = 1L;
    private static final QName SPCPCT$0 = new QName("http://schemas.openxmlformats.org/drawingml/2006/main", "spcPct");
    private static final QName SPCPTS$2 = new QName("http://schemas.openxmlformats.org/drawingml/2006/main", "spcPts");

    public CTTextSpacingImpl(SchemaType schemaType) {
        super(schemaType);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTTextSpacingPercent getSpcPct() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTTextSpacingPercent cTTextSpacingPercent = null;
            cTTextSpacingPercent = (CTTextSpacingPercent)((Object)this.get_store().find_element_user(SPCPCT$0, 0));
            if (cTTextSpacingPercent == null) {
                return null;
            }
            return cTTextSpacingPercent;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetSpcPct() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(SPCPCT$0) != 0;
        }
    }

    @Override
    public void setSpcPct(CTTextSpacingPercent cTTextSpacingPercent) {
        this.generatedSetterHelperImpl(cTTextSpacingPercent, SPCPCT$0, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTTextSpacingPercent addNewSpcPct() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTTextSpacingPercent cTTextSpacingPercent = null;
            cTTextSpacingPercent = (CTTextSpacingPercent)((Object)this.get_store().add_element_user(SPCPCT$0));
            return cTTextSpacingPercent;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetSpcPct() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(SPCPCT$0, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTTextSpacingPoint getSpcPts() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTTextSpacingPoint cTTextSpacingPoint = null;
            cTTextSpacingPoint = (CTTextSpacingPoint)((Object)this.get_store().find_element_user(SPCPTS$2, 0));
            if (cTTextSpacingPoint == null) {
                return null;
            }
            return cTTextSpacingPoint;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetSpcPts() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(SPCPTS$2) != 0;
        }
    }

    @Override
    public void setSpcPts(CTTextSpacingPoint cTTextSpacingPoint) {
        this.generatedSetterHelperImpl(cTTextSpacingPoint, SPCPTS$2, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTTextSpacingPoint addNewSpcPts() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTTextSpacingPoint cTTextSpacingPoint = null;
            cTTextSpacingPoint = (CTTextSpacingPoint)((Object)this.get_store().add_element_user(SPCPTS$2));
            return cTTextSpacingPoint;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetSpcPts() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(SPCPTS$2, 0);
        }
    }
}

