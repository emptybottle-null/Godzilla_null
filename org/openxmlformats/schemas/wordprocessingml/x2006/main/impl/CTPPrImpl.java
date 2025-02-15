/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPPrChange
 */
package org.openxmlformats.schemas.wordprocessingml.x2006.main.impl;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPPr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPPrChange;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTParaRPr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSectPr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTPPrBaseImpl;

public class CTPPrImpl
extends CTPPrBaseImpl
implements CTPPr {
    private static final long serialVersionUID = 1L;
    private static final QName RPR$0 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "rPr");
    private static final QName SECTPR$2 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "sectPr");
    private static final QName PPRCHANGE$4 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "pPrChange");

    public CTPPrImpl(SchemaType schemaType) {
        super(schemaType);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTParaRPr getRPr() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTParaRPr cTParaRPr = null;
            cTParaRPr = (CTParaRPr)((Object)this.get_store().find_element_user(RPR$0, 0));
            if (cTParaRPr == null) {
                return null;
            }
            return cTParaRPr;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetRPr() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(RPR$0) != 0;
        }
    }

    @Override
    public void setRPr(CTParaRPr cTParaRPr) {
        this.generatedSetterHelperImpl(cTParaRPr, RPR$0, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTParaRPr addNewRPr() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTParaRPr cTParaRPr = null;
            cTParaRPr = (CTParaRPr)((Object)this.get_store().add_element_user(RPR$0));
            return cTParaRPr;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetRPr() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(RPR$0, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTSectPr getSectPr() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTSectPr cTSectPr = null;
            cTSectPr = (CTSectPr)((Object)this.get_store().find_element_user(SECTPR$2, 0));
            if (cTSectPr == null) {
                return null;
            }
            return cTSectPr;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetSectPr() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(SECTPR$2) != 0;
        }
    }

    @Override
    public void setSectPr(CTSectPr cTSectPr) {
        this.generatedSetterHelperImpl(cTSectPr, SECTPR$2, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTSectPr addNewSectPr() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTSectPr cTSectPr = null;
            cTSectPr = (CTSectPr)((Object)this.get_store().add_element_user(SECTPR$2));
            return cTSectPr;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetSectPr() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(SECTPR$2, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTPPrChange getPPrChange() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTPPrChange cTPPrChange = null;
            cTPPrChange = (CTPPrChange)this.get_store().find_element_user(PPRCHANGE$4, 0);
            if (cTPPrChange == null) {
                return null;
            }
            return cTPPrChange;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetPPrChange() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(PPRCHANGE$4) != 0;
        }
    }

    @Override
    public void setPPrChange(CTPPrChange cTPPrChange) {
        this.generatedSetterHelperImpl((XmlObject)cTPPrChange, PPRCHANGE$4, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTPPrChange addNewPPrChange() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTPPrChange cTPPrChange = null;
            cTPPrChange = (CTPPrChange)this.get_store().add_element_user(PPRCHANGE$4);
            return cTPPrChange;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetPPrChange() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(PPRCHANGE$4, 0);
        }
    }
}

