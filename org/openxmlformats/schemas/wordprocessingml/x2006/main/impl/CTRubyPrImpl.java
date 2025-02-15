/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.openxmlformats.schemas.wordprocessingml.x2006.main.impl;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTHpsMeasure;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTLang;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTOnOff;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRubyAlign;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRubyPr;

public class CTRubyPrImpl
extends XmlComplexContentImpl
implements CTRubyPr {
    private static final long serialVersionUID = 1L;
    private static final QName RUBYALIGN$0 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "rubyAlign");
    private static final QName HPS$2 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "hps");
    private static final QName HPSRAISE$4 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "hpsRaise");
    private static final QName HPSBASETEXT$6 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "hpsBaseText");
    private static final QName LID$8 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "lid");
    private static final QName DIRTY$10 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "dirty");

    public CTRubyPrImpl(SchemaType schemaType) {
        super(schemaType);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTRubyAlign getRubyAlign() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTRubyAlign cTRubyAlign = null;
            cTRubyAlign = (CTRubyAlign)((Object)this.get_store().find_element_user(RUBYALIGN$0, 0));
            if (cTRubyAlign == null) {
                return null;
            }
            return cTRubyAlign;
        }
    }

    @Override
    public void setRubyAlign(CTRubyAlign cTRubyAlign) {
        this.generatedSetterHelperImpl(cTRubyAlign, RUBYALIGN$0, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTRubyAlign addNewRubyAlign() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTRubyAlign cTRubyAlign = null;
            cTRubyAlign = (CTRubyAlign)((Object)this.get_store().add_element_user(RUBYALIGN$0));
            return cTRubyAlign;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTHpsMeasure getHps() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTHpsMeasure cTHpsMeasure = null;
            cTHpsMeasure = (CTHpsMeasure)((Object)this.get_store().find_element_user(HPS$2, 0));
            if (cTHpsMeasure == null) {
                return null;
            }
            return cTHpsMeasure;
        }
    }

    @Override
    public void setHps(CTHpsMeasure cTHpsMeasure) {
        this.generatedSetterHelperImpl(cTHpsMeasure, HPS$2, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTHpsMeasure addNewHps() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTHpsMeasure cTHpsMeasure = null;
            cTHpsMeasure = (CTHpsMeasure)((Object)this.get_store().add_element_user(HPS$2));
            return cTHpsMeasure;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTHpsMeasure getHpsRaise() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTHpsMeasure cTHpsMeasure = null;
            cTHpsMeasure = (CTHpsMeasure)((Object)this.get_store().find_element_user(HPSRAISE$4, 0));
            if (cTHpsMeasure == null) {
                return null;
            }
            return cTHpsMeasure;
        }
    }

    @Override
    public void setHpsRaise(CTHpsMeasure cTHpsMeasure) {
        this.generatedSetterHelperImpl(cTHpsMeasure, HPSRAISE$4, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTHpsMeasure addNewHpsRaise() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTHpsMeasure cTHpsMeasure = null;
            cTHpsMeasure = (CTHpsMeasure)((Object)this.get_store().add_element_user(HPSRAISE$4));
            return cTHpsMeasure;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTHpsMeasure getHpsBaseText() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTHpsMeasure cTHpsMeasure = null;
            cTHpsMeasure = (CTHpsMeasure)((Object)this.get_store().find_element_user(HPSBASETEXT$6, 0));
            if (cTHpsMeasure == null) {
                return null;
            }
            return cTHpsMeasure;
        }
    }

    @Override
    public void setHpsBaseText(CTHpsMeasure cTHpsMeasure) {
        this.generatedSetterHelperImpl(cTHpsMeasure, HPSBASETEXT$6, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTHpsMeasure addNewHpsBaseText() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTHpsMeasure cTHpsMeasure = null;
            cTHpsMeasure = (CTHpsMeasure)((Object)this.get_store().add_element_user(HPSBASETEXT$6));
            return cTHpsMeasure;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTLang getLid() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTLang cTLang = null;
            cTLang = (CTLang)((Object)this.get_store().find_element_user(LID$8, 0));
            if (cTLang == null) {
                return null;
            }
            return cTLang;
        }
    }

    @Override
    public void setLid(CTLang cTLang) {
        this.generatedSetterHelperImpl(cTLang, LID$8, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTLang addNewLid() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTLang cTLang = null;
            cTLang = (CTLang)((Object)this.get_store().add_element_user(LID$8));
            return cTLang;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTOnOff getDirty() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTOnOff cTOnOff = null;
            cTOnOff = (CTOnOff)((Object)this.get_store().find_element_user(DIRTY$10, 0));
            if (cTOnOff == null) {
                return null;
            }
            return cTOnOff;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetDirty() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(DIRTY$10) != 0;
        }
    }

    @Override
    public void setDirty(CTOnOff cTOnOff) {
        this.generatedSetterHelperImpl(cTOnOff, DIRTY$10, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTOnOff addNewDirty() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTOnOff cTOnOff = null;
            cTOnOff = (CTOnOff)((Object)this.get_store().add_element_user(DIRTY$10));
            return cTOnOff;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetDirty() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(DIRTY$10, 0);
        }
    }
}

