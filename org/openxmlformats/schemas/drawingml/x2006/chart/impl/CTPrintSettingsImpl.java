/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.openxmlformats.schemas.drawingml.x2006.chart.impl;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTHeaderFooter;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTPageMargins;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTPageSetup;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTPrintSettings;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTRelId;

public class CTPrintSettingsImpl
extends XmlComplexContentImpl
implements CTPrintSettings {
    private static final long serialVersionUID = 1L;
    private static final QName HEADERFOOTER$0 = new QName("http://schemas.openxmlformats.org/drawingml/2006/chart", "headerFooter");
    private static final QName PAGEMARGINS$2 = new QName("http://schemas.openxmlformats.org/drawingml/2006/chart", "pageMargins");
    private static final QName PAGESETUP$4 = new QName("http://schemas.openxmlformats.org/drawingml/2006/chart", "pageSetup");
    private static final QName LEGACYDRAWINGHF$6 = new QName("http://schemas.openxmlformats.org/drawingml/2006/chart", "legacyDrawingHF");

    public CTPrintSettingsImpl(SchemaType schemaType) {
        super(schemaType);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTHeaderFooter getHeaderFooter() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTHeaderFooter cTHeaderFooter = null;
            cTHeaderFooter = (CTHeaderFooter)((Object)this.get_store().find_element_user(HEADERFOOTER$0, 0));
            if (cTHeaderFooter == null) {
                return null;
            }
            return cTHeaderFooter;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetHeaderFooter() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(HEADERFOOTER$0) != 0;
        }
    }

    @Override
    public void setHeaderFooter(CTHeaderFooter cTHeaderFooter) {
        this.generatedSetterHelperImpl(cTHeaderFooter, HEADERFOOTER$0, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTHeaderFooter addNewHeaderFooter() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTHeaderFooter cTHeaderFooter = null;
            cTHeaderFooter = (CTHeaderFooter)((Object)this.get_store().add_element_user(HEADERFOOTER$0));
            return cTHeaderFooter;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetHeaderFooter() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(HEADERFOOTER$0, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTPageMargins getPageMargins() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTPageMargins cTPageMargins = null;
            cTPageMargins = (CTPageMargins)((Object)this.get_store().find_element_user(PAGEMARGINS$2, 0));
            if (cTPageMargins == null) {
                return null;
            }
            return cTPageMargins;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetPageMargins() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(PAGEMARGINS$2) != 0;
        }
    }

    @Override
    public void setPageMargins(CTPageMargins cTPageMargins) {
        this.generatedSetterHelperImpl(cTPageMargins, PAGEMARGINS$2, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTPageMargins addNewPageMargins() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTPageMargins cTPageMargins = null;
            cTPageMargins = (CTPageMargins)((Object)this.get_store().add_element_user(PAGEMARGINS$2));
            return cTPageMargins;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetPageMargins() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(PAGEMARGINS$2, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTPageSetup getPageSetup() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTPageSetup cTPageSetup = null;
            cTPageSetup = (CTPageSetup)((Object)this.get_store().find_element_user(PAGESETUP$4, 0));
            if (cTPageSetup == null) {
                return null;
            }
            return cTPageSetup;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetPageSetup() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(PAGESETUP$4) != 0;
        }
    }

    @Override
    public void setPageSetup(CTPageSetup cTPageSetup) {
        this.generatedSetterHelperImpl(cTPageSetup, PAGESETUP$4, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTPageSetup addNewPageSetup() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTPageSetup cTPageSetup = null;
            cTPageSetup = (CTPageSetup)((Object)this.get_store().add_element_user(PAGESETUP$4));
            return cTPageSetup;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetPageSetup() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(PAGESETUP$4, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTRelId getLegacyDrawingHF() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTRelId cTRelId = null;
            cTRelId = (CTRelId)((Object)this.get_store().find_element_user(LEGACYDRAWINGHF$6, 0));
            if (cTRelId == null) {
                return null;
            }
            return cTRelId;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetLegacyDrawingHF() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(LEGACYDRAWINGHF$6) != 0;
        }
    }

    @Override
    public void setLegacyDrawingHF(CTRelId cTRelId) {
        this.generatedSetterHelperImpl(cTRelId, LEGACYDRAWINGHF$6, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTRelId addNewLegacyDrawingHF() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTRelId cTRelId = null;
            cTRelId = (CTRelId)((Object)this.get_store().add_element_user(LEGACYDRAWINGHF$6));
            return cTRelId;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetLegacyDrawingHF() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(LEGACYDRAWINGHF$6, 0);
        }
    }
}

