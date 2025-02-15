/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.openxmlformats.schemas.wordprocessingml.x2006.main.impl;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTDocDefaults;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPPrDefault;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRPrDefault;

public class CTDocDefaultsImpl
extends XmlComplexContentImpl
implements CTDocDefaults {
    private static final long serialVersionUID = 1L;
    private static final QName RPRDEFAULT$0 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "rPrDefault");
    private static final QName PPRDEFAULT$2 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "pPrDefault");

    public CTDocDefaultsImpl(SchemaType schemaType) {
        super(schemaType);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTRPrDefault getRPrDefault() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTRPrDefault cTRPrDefault = null;
            cTRPrDefault = (CTRPrDefault)((Object)this.get_store().find_element_user(RPRDEFAULT$0, 0));
            if (cTRPrDefault == null) {
                return null;
            }
            return cTRPrDefault;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetRPrDefault() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(RPRDEFAULT$0) != 0;
        }
    }

    @Override
    public void setRPrDefault(CTRPrDefault cTRPrDefault) {
        this.generatedSetterHelperImpl(cTRPrDefault, RPRDEFAULT$0, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTRPrDefault addNewRPrDefault() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTRPrDefault cTRPrDefault = null;
            cTRPrDefault = (CTRPrDefault)((Object)this.get_store().add_element_user(RPRDEFAULT$0));
            return cTRPrDefault;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetRPrDefault() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(RPRDEFAULT$0, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTPPrDefault getPPrDefault() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTPPrDefault cTPPrDefault = null;
            cTPPrDefault = (CTPPrDefault)((Object)this.get_store().find_element_user(PPRDEFAULT$2, 0));
            if (cTPPrDefault == null) {
                return null;
            }
            return cTPPrDefault;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetPPrDefault() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(PPRDEFAULT$2) != 0;
        }
    }

    @Override
    public void setPPrDefault(CTPPrDefault cTPPrDefault) {
        this.generatedSetterHelperImpl(cTPPrDefault, PPRDEFAULT$2, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTPPrDefault addNewPPrDefault() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTPPrDefault cTPPrDefault = null;
            cTPPrDefault = (CTPPrDefault)((Object)this.get_store().add_element_user(PPRDEFAULT$2));
            return cTPPrDefault;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetPPrDefault() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(PPRDEFAULT$2, 0);
        }
    }
}

